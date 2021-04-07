package org.chw.androidcustomview.g_gesture

import android.util.Log
import android.view.MotionEvent
import org.chw.androidcustomview.BaseActivity
import org.chw.androidcustomview.R

/**
 * @author chaihongwei
 * @date 2021-04-07 13:00:34
 */
class G6Activity : BaseActivity() {
    companion object {
        const val TAG = "multiPoint"
    }

    private var mActivePointerId: Int = 0

    override fun getLayoutId() = R.layout.actiivty_g1

    override fun init() {
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
//        mActivePointerId = event.getPointerId(0)
//
//        val (x: Float, y: Float) = event.findPointerIndex(mActivePointerId).let { pointerIndex ->
//            event.getX(pointerIndex) to event.getY(pointerIndex)
//        }
//
//        Log.d(TAG, "onTouchEvent:$mActivePointerId $x $y")


        val (xPos: Int, yPos: Int) = event.actionMasked.let { action ->
            Log.d(TAG, "The action is ${actionToString(action)}")
            event.actionIndex.let { index ->
                event.getX(index).toInt() to event.getY(index).toInt()
            }
        }

        if (event.pointerCount > 1) {
            Log.d(TAG, "Multitouch")

        } else {
            // Single touch event
            Log.d(TAG, "Single touch")
        }

        return true
    }

    private fun actionToString(action: Int): String {
        return when (action) {
            MotionEvent.ACTION_DOWN -> "Down"
            MotionEvent.ACTION_MOVE -> "Move"
            MotionEvent.ACTION_POINTER_DOWN -> "Pointer Down"
            MotionEvent.ACTION_UP -> "Up"
            MotionEvent.ACTION_POINTER_UP -> "Pointer Up"
            MotionEvent.ACTION_OUTSIDE -> "Outside"
            MotionEvent.ACTION_CANCEL -> "Cancel"
            else -> ""
        }
    }
}