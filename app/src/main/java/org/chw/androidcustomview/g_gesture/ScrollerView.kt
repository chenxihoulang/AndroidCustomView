package org.chw.androidcustomview.g_gesture

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.widget.OverScroller
import androidx.core.view.ViewCompat

/**
 * @author chaihongwei
 * @date 2021-04-07 14:14:47
 */
class ScrollerView(context: Context, attr: AttributeSet) : View(context, attr) {
    companion object {
        const val TAG = "ScrollerView"
    }

    private var mDetector: GestureDetector = GestureDetector(context, MyGestureListener())
    private var mScroller: OverScroller = OverScroller(context)

    private var mCenterX: Int = 0
    private var mCenterY: Int = 0

    private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG)

    init {
        mPaint.color = Color.RED
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mCenterX = w / 2
        mCenterY = h / 2
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return if (mDetector.onTouchEvent(event)) {
            true
        } else {
            super.onTouchEvent(event)
        }
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawCircle(mCenterX.toFloat(), mCenterY.toFloat(), 100F, mPaint)
    }

    private inner class MyGestureListener : GestureDetector.SimpleOnGestureListener() {
        override fun onDown(e: MotionEvent): Boolean {
            // 停止滚动动画
            mScroller.forceFinished(true)
            //重绘,会调用computeScroll()
            ViewCompat.postInvalidateOnAnimation(this@ScrollerView)
            return true
        }

        override fun onScroll(
            e1: MotionEvent,
            e2: MotionEvent,
            distanceX: Float,
            distanceY: Float
        ): Boolean {
            return super.onScroll(e1, e2, distanceX, distanceY)
        }

        override fun onFling(
            e1: MotionEvent,
            e2: MotionEvent,
            velocityX: Float,
            velocityY: Float
        ): Boolean {
            fling((-velocityX).toInt(), (-velocityY).toInt())
            return true
        }
    }

    private fun fling(velocityX: Int, velocityY: Int) {
        // 先停止以前的动画
        mScroller.forceFinished(true)

        mScroller.fling(
            mCenterX,
            mCenterY,
            velocityX,
            velocityY,
            /*
             * Minimum and maximum scroll positions. The minimum scroll
             * position is generally zero and the maximum scroll position
             * is generally the content size less the screen size. So if the
             * content width is 1000 pixels and the screen width is 200
             * pixels, the maximum scroll offset should be 800 pixels.
             */
            0,
            1000,
            0,
            2000
        )
        //触发computeScroll()
        ViewCompat.postInvalidateOnAnimation(this)
    }

    override fun computeScroll() {
        super.computeScroll()

        var needsInvalidate = false

        // 快速滑动动画还没有结束
        if (mScroller.computeScrollOffset()) {
            val currX: Int = mScroller.currX
            val currY: Int = mScroller.currY

            Log.d(TAG, "computeScroll: $currX $currY")

            mCenterX = currX
            mCenterY = currY

            if (currX in 101..700 || currY in 101..700) {
                needsInvalidate = true
            }
        }

        if (needsInvalidate) {
            ViewCompat.postInvalidateOnAnimation(this)
        }
    }
}