package org.chw.androidcustomview.g_gesture

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.ViewConfiguration
import android.view.ViewGroup
import kotlin.math.abs

/**
 * @author chaihongwei
 * @date 2021-04-07 17:12:49
 */
class MyViewGroup(context: Context, attr: AttributeSet) : ViewGroup(context, attr) {
    companion object {
        const val TAG = "MyView"
    }

    private var mIsScrolling: Boolean = false

    /**
     * 使用 ViewConfiguration 类来获取 Android 系统常用的距离、速度和时间
     * “Touch slop”是指在系统将手势解读为滚动之前，用户的轻触手势可以滑动的距离（以像素为单位）
     * Touch slop 通常用于防止在用户执行某些其他轻触操作（例如轻触屏幕上的元素）时发生意外滚动
     *
     * getScaledMinimumFlingVelocity() 和 getScaledMaximumFlingVelocity()。
     * 这两个方法会分别返回发起滑动的最小速度和最大速度，以像素/秒为单位
     */
    private val vc: ViewConfiguration = ViewConfiguration.get(context)
    private val mTouchSlop: Int = vc.scaledTouchSlop
    private val mMinFlingVelocity: Int = vc.scaledMinimumFlingVelocity
    private val mMaxFlingVelocity: Int = vc.scaledMaximumFlingVelocity

    private var mLastTouchX: Float = 0F

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        //这里默认只有一个子View
        getChildAt(0).layout(100, 100, 500, 500)
    }

    /**
     * 此方法决定ViewGroup是否拦截事件,如果返回true,则拦截事件,那么ViewGroup的onTouchEvent会被调用
     * 如果返回false,那么子View的onTouchEvent有可能被调用
     */
    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        return when (ev.actionMasked) {
            //取消滚动,并且不拦截事件
            MotionEvent.ACTION_CANCEL, MotionEvent.ACTION_UP -> {
                mIsScrolling = false
                false
            }

            MotionEvent.ACTION_MOVE -> {
                //当前正在滚动,所以继续拦截事件
                if (mIsScrolling) {
                    true
                } else {
                    val x = ev.getX(ev.actionIndex)

                    //计算水平滑动距离
                    val xDiff: Int = abs(x - mLastTouchX).toInt()

                    //记录最后的x坐标
                    mLastTouchX = x

                    //水平滑动距离如果超过系统设置的最小距离,则判断为水平滑动,进行拦截
                    if (xDiff > mTouchSlop) {
                        mIsScrolling = true
                        true
                    } else {
                        false
                    }
                }
            }
            else -> {
                ev.actionIndex.also { pointerIndex ->
                    mLastTouchX = ev.getX(pointerIndex)
                }

                //其他事件默认不拦截
                false
            }
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        Log.d(TAG, "ViewGroup onTouchEvent:")

        return true
    }
}