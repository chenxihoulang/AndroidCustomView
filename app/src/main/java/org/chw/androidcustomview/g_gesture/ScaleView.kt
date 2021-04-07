package org.chw.androidcustomview.g_gesture

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.view.View

/**
 * @author chaihongwei
 * @date 2021-04-07 14:14:47
 */
class ScaleView(context: Context, attr: AttributeSet) : View(context, attr) {
    companion object {
        const val TAG = "ScaleView"
    }

    private var mScaleFactor = 1F

    private val mScaleDetector = ScaleGestureDetector(context, MyScaleListener())

    private var mPosX: Float = 0F
    private var mPosY: Float = 0F

    private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG)

    init {
        mPaint.color = Color.RED
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        //手势检测
        return if (mScaleDetector.onTouchEvent(event)) {
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
        canvas.drawCircle(mPosX, mPosY, 50F * mScaleFactor, mPaint)
    }

    private inner class MyScaleListener : ScaleGestureDetector.SimpleOnScaleGestureListener() {
        override fun onScale(detector: ScaleGestureDetector): Boolean {
            Log.d(TAG, "onScale: ${detector.scaleFactor}")

            mScaleFactor *= detector.scaleFactor

            mScaleFactor = 0.5F.coerceAtLeast(mScaleFactor.coerceAtMost(5.0F))

            invalidate()

            return true
        }
    }
}