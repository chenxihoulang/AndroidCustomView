package org.chw.androidcustomview.g_gesture

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View

/**
 * @author chaihongwei
 * @date 2021-04-07 14:14:47
 */
class OnScrollView(context: Context, attr: AttributeSet) : View(context, attr) {
    companion object {
        const val TAG = "OnScrollView"
    }

    private var mDetector: GestureDetector = GestureDetector(context, MyGestureListener())

    private var mPosX: Float = 0F
    private var mPosY: Float = 0F

    private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG)

    init {
        mPaint.color = Color.RED
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        //手势检测
        return if (mDetector.onTouchEvent(event)) {
            true
        } else {
            super.onTouchEvent(event)
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        mPosX = w / 2F
        mPosY = h / 2F
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawCircle(mPosX, mPosY, 50F, mPaint)
    }

    private inner class MyGestureListener : GestureDetector.SimpleOnGestureListener() {
        //注意:一定要重新onDown方法,并且返回true,否则所有事件都不会触发,参考:View事件分发机制
        override fun onDown(e: MotionEvent): Boolean {
            return true
        }

        override fun onScroll(
            e1: MotionEvent,
            e2: MotionEvent,
            distanceX: Float,
            distanceY: Float
        ): Boolean {

            //累加滑动距离
            mPosX += -distanceX
            mPosY += -distanceY

            Log.d(TAG, "onScroll: $mPosX $mPosY")

            //添加边界约束判断
            if (mPosX < 0F) {
                mPosX = 0F
            }
            if (mPosY < 0) {
                mPosY = 0F;
            }

            //重绘
            invalidate()

            return true
        }
    }
}