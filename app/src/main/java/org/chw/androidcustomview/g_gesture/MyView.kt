package org.chw.androidcustomview.g_gesture

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View

/**
 * @author chaihongwei
 * @date 2021-04-07 14:14:47
 */
class MyView(context: Context, attr: AttributeSet) : View(context, attr) {
    companion object {
        const val TAG = "MyView"
    }

    private var mPosX: Float = 0F
    private var mPosY: Float = 0F

    private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG)

    init {
        mPaint.color = Color.RED
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        mPosX = w / 2F
        mPosY = h / 2F
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        Log.d(TAG, "View onTouchEvent:")
        return true
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.drawCircle(mPosX, mPosY, 50F, mPaint)
    }
}