package org.chw.androidcustomview.f_activity

import kotlinx.android.synthetic.main.activity_main.*
import org.chw.androidcustomview.BaseActivity
import org.chw.androidcustomview.R
import org.chw.androidcustomview.bitmap.BitmapActivity
import org.chw.androidcustomview.g_gesture.*

class MainActivity : BaseActivity() {

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun init() {
        btn_a_basic.start(ABasicActivity::class.java)
        btn_b_path.start(BPathActivity::class.java)
        btn_c_text.start(CTextActivity::class.java)
        btn_d_region.start(DRegionActivity::class.java)
        btn_e_canvas.start(ECanvasActivity::class.java)
        btn_bitmap.start(BitmapActivity::class.java)
        btn_gesture.start(G11Activity::class.java)
    }

}
