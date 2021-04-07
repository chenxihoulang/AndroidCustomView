package org.chw.androidcustomview.g_gesture

import android.util.Log
import android.view.MotionEvent
import org.chw.androidcustomview.BaseActivity
import org.chw.androidcustomview.R

/**
 * @author chaihongwei
 * @date 2021-04-07 13:00:34
 */
class G1Activity : BaseActivity() {
    companion object {
        const val DEBUG_TAG = "Gestures"
    }

    override fun getLayoutId() = R.layout.actiivty_g1

    override fun init() {

    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        return when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                Log.d(DEBUG_TAG, "Action was DOWN")
                true
            }
            MotionEvent.ACTION_MOVE -> {
                Log.d(DEBUG_TAG, "Action was MOVE")
                true
            }
            MotionEvent.ACTION_UP -> {
                Log.d(DEBUG_TAG, "Action was UP")
                true
            }
            MotionEvent.ACTION_CANCEL -> {
                Log.d(DEBUG_TAG, "Action was CANCEL")
                true
            }
            MotionEvent.ACTION_OUTSIDE -> {
                Log.d(DEBUG_TAG, "Movement occurred outside bounds of current screen element")
                true
            }
            else -> super.onTouchEvent(event)
        }
    }
}