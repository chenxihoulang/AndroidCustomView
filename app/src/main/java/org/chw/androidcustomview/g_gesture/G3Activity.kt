package org.chw.androidcustomview.g_gesture

import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import org.chw.androidcustomview.BaseActivity
import org.chw.androidcustomview.R

/**
 * @author chaihongwei
 * @date 2021-04-07 13:00:34
 */
class G3Activity : BaseActivity() {
    companion object {
        const val DEBUG_TAG = "Gestures"
    }

    private lateinit var mDetector: GestureDetector

    override fun getLayoutId() = R.layout.actiivty_g1

    override fun init() {
        mDetector = GestureDetector(this, MyGestureListener())
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        return if (mDetector.onTouchEvent(event)) {
            true
        } else {
            Log.d(DEBUG_TAG, "onTouchEvent: $event ${event.historySize}")
            super.onTouchEvent(event)
        }
    }

    private class MyGestureListener : GestureDetector.SimpleOnGestureListener() {

        override fun onDown(event: MotionEvent): Boolean {

            Log.d(DEBUG_TAG, "onDown: $event")
            return true
        }

        override fun onFling(
            event1: MotionEvent,
            event2: MotionEvent,
            velocityX: Float,
            velocityY: Float
        ): Boolean {
            Log.d(G2Activity.DEBUG_TAG, "onFling: $event1 $event2 $velocityX $velocityY")
            return true
        }
    }

}