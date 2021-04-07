package org.chw.androidcustomview.g_gesture

import android.graphics.Rect
import android.view.TouchDelegate
import android.view.View
import android.widget.ImageButton
import android.widget.Toast
import org.chw.androidcustomview.BaseActivity
import org.chw.androidcustomview.R

/**
 * @author chaihongwei
 * @date 2021-04-07 13:00:34
 */
class G11Activity : BaseActivity() {

    override fun getLayoutId() = R.layout.actiivty_g11

    override fun init() {
        //确保父视图已经布局完子视图,这样才能通过getHitRect()方法获取到子视图的可点击区域
        findViewById<View>(R.id.parent_layout).post {
            //子视图的可点击区域
            val delegateArea = Rect()
            val myButton = findViewById<ImageButton>(R.id.button).apply {
                isEnabled = true
                setOnClickListener {
                    Toast.makeText(
                        this@G11Activity,
                        "点击了图片的扩展区域",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                //以父视图坐标的形式获取子视图点击矩形（可轻触区域）
                getHitRect(delegateArea)
            }

            //扩展按钮的右边和下边
            delegateArea.right += 100
            delegateArea.bottom += 100

            //在父视图上设置 TouchDelegate，以便将轻触代理边界内的轻触操作传递给子视图
            (myButton.parent as? View)?.apply {
                touchDelegate = TouchDelegate(delegateArea, myButton)
            }
        }
    }
}