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
class G2Activity : BaseActivity(),
    GestureDetector.OnGestureListener,
    GestureDetector.OnDoubleTapListener {
    companion object {
        const val DEBUG_TAG = "Gestures"
    }

    private lateinit var mDetector: GestureDetector

    override fun getLayoutId() = R.layout.actiivty_g1

    override fun init() {
        mDetector = GestureDetector(this, this)
        mDetector.setOnDoubleTapListener(this)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        return if (mDetector.onTouchEvent(event)) {
            true
        } else {
            super.onTouchEvent(event)
        }
    }

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
        Log.d(DEBUG_TAG, "onFling: $event1 $event2 $velocityX $velocityY")
        return true
    }

    override fun onLongPress(event: MotionEvent) {
        Log.d(DEBUG_TAG, "onLongPress: $event")
    }

    override fun onScroll(
        event1: MotionEvent,
        event2: MotionEvent,
        distanceX: Float,
        distanceY: Float
    ): Boolean {
        Log.d(DEBUG_TAG, "onScroll: $event1 $event2 $distanceX $distanceY")
        return true
    }

    override fun onShowPress(event: MotionEvent) {
        Log.d(DEBUG_TAG, "onShowPress: $event")
    }

    override fun onSingleTapUp(event: MotionEvent): Boolean {
        Log.d(DEBUG_TAG, "onSingleTapUp: $event")
        return true
    }

    override fun onDoubleTap(event: MotionEvent): Boolean {
        Log.d(DEBUG_TAG, "onDoubleTap: $event")
        return true
    }

    override fun onDoubleTapEvent(event: MotionEvent): Boolean {
        Log.d(DEBUG_TAG, "onDoubleTapEvent: $event")
        return true
    }

    override fun onSingleTapConfirmed(event: MotionEvent): Boolean {
        Log.d(DEBUG_TAG, "onSingleTapConfirmed: $event")
        return true
    }

}