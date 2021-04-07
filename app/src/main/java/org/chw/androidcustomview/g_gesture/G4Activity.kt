package org.chw.androidcustomview.g_gesture

import android.util.Log
import android.view.MotionEvent
import android.view.VelocityTracker
import org.chw.androidcustomview.BaseActivity
import org.chw.androidcustomview.R

/**
 * @author chaihongwei
 * @date 2021-04-07 13:00:34
 */
class G4Activity : BaseActivity() {
    companion object {
        const val DEBUG_TAG = "Velocity"
    }

    private var mVelocityTracker: VelocityTracker? = null

    override fun getLayoutId() = R.layout.actiivty_g1

    override fun init() {
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {

        when (event.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                mVelocityTracker?.clear()
                mVelocityTracker = mVelocityTracker ?: VelocityTracker.obtain()
                mVelocityTracker?.addMovement(event)
            }
            MotionEvent.ACTION_MOVE -> {
                mVelocityTracker?.apply {
                    val pointerId: Int = event.getPointerId(event.actionIndex)
                    addMovement(event)

                    //在 ACTION_MOVE 事件（而不是 ACTION_UP）发生后计算速度。在 ACTION_UP 发生后，X 速度和 Y 速度将为 0
                    computeCurrentVelocity(1000)

                    Log.d(DEBUG_TAG, "X velocity: ${getXVelocity(pointerId)}")
                    Log.d(DEBUG_TAG, "Y velocity: ${getYVelocity(pointerId)}")
                }
            }
            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                mVelocityTracker?.recycle()
                mVelocityTracker = null
            }
        }
        return true
    }
}