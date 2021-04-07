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
class DraggerView(context: Context, attr: AttributeSet) : View(context, attr) {
    companion object {
        const val TAG = "DraggerView"
    }

    private var mLastTouchX: Float = 0F
    private var mLastTouchY: Float = 0F

    private var mPosX: Float = 0F
    private var mPosY: Float = 0F

    //当前执行拖动的手指
    private var mActivePointerId = MotionEvent.INVALID_POINTER_ID

    private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG)

    init {
        mPaint.color = Color.RED
    }

    override fun onTouchEvent(ev: MotionEvent): Boolean {
        when (ev.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                Log.d(TAG, "ACTION_DOWN: 第一个手指按下")
                //记录初始位置
                ev.actionIndex.also { pointerIndex ->
                    mLastTouchX = ev.getX(pointerIndex)
                    mLastTouchY = ev.getY(pointerIndex)
                }

                //记录第一个手指id
                mActivePointerId = ev.getPointerId(0)
            }

            MotionEvent.ACTION_POINTER_DOWN -> {
                Log.d(TAG, "ACTION_POINTER_DOWN: 多个手指,非第一个手指按下")
            }

            MotionEvent.ACTION_MOVE -> {
//                Log.d(TAG, "ACTION_MOVE: 手指移动")
                //记录拖动手指当前的位置
                val (x: Float, y: Float) =
                    ev.findPointerIndex(mActivePointerId).let { pointerIndex ->
                        ev.getX(pointerIndex) to ev.getY(pointerIndex)
                    }

                //累加移动的距离
                mPosX += x - mLastTouchX
                mPosY += y - mLastTouchY

//                Log.d(TAG, "ACTION_MOVE: $mPosX $mPosY")

                //重绘
                invalidate()

                mLastTouchX = x
                mLastTouchY = y
            }
            //当最后一个手指抬起时,重置
            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                Log.d(TAG, "ACTION_UP ACTION_CANCEL: 最后一个手指抬起")
                mActivePointerId = MotionEvent.INVALID_POINTER_ID
            }

            //多个手指,有一个手指抬起,如果抬起的是当前正在执行拖动的手指,则将拖动手指切换成其他按下的手指
            MotionEvent.ACTION_POINTER_UP -> {
                Log.d(TAG, "ACTION_POINTER_UP: 多个手指,有一个手指抬起")
                ev.actionIndex.also { pointerIndex ->
                    ev.getPointerId(pointerIndex)
                        .takeIf { it == mActivePointerId }//抬起的是当前正在执行拖动的手指
                        ?.run {
                            val newPointerIndex = if (pointerIndex == 0) 1 else 0

                            //获取切换后的手指位置
                            mLastTouchX = ev.getX(newPointerIndex)
                            mLastTouchY = ev.getY(newPointerIndex)

                            //重新设置当前拖动手指
                            mActivePointerId = ev.getPointerId(newPointerIndex)
                        }
                }
            }
        }

        return true
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.drawCircle(mPosX, mPosY, 50F, mPaint)
    }
}