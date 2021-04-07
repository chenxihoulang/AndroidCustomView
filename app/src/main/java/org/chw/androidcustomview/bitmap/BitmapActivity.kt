package org.chw.androidcustomview.bitmap

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.View
import kotlinx.android.synthetic.main.activity_bitmap.*
import org.chw.androidcustomview.BaseActivity
import org.chw.androidcustomview.BitmapUtils
import org.chw.androidcustomview.R


/**
 * @author chaihongwei
 * @date 2021-04-06 16:14:54
 */
class BitmapActivity : BaseActivity(), View.OnClickListener {
    override fun getLayoutId() = R.layout.activity_bitmap

    var ratio = 1.0F
    var degree = 0F

    override fun init() {
        btn_scaleBitmap.setOnClickListener(this)
        btn_scaleBitmap1.setOnClickListener(this)
        btn_crop.setOnClickListener(this)
        btn_rotate.setOnClickListener(this)
        btn_skewBitmap.setOnClickListener(this)
        btn_CycleBitmap.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        val originBM = BitmapFactory.decodeResource(
            resources,
            R.drawable.ic_launcher
        )

        when (v) {
            btn_scaleBitmap -> {
                val nBM: Bitmap = BitmapUtils.scaleBitmap(originBM, 100, 72)
                ivDestImg.setImageBitmap(nBM)
            }
            btn_scaleBitmap1 -> {
                if (ratio < 3) {
                    ratio += 0.05f
                } else {
                    ratio = 0.1f
                }
                val nBM: Bitmap = BitmapUtils.scaleBitmap(originBM, ratio)
                ivDestImg.setImageBitmap(nBM)
            }
            btn_crop -> {
                val nBM: Bitmap = BitmapUtils.cropBitmap(originBM)
                ivDestImg.setImageBitmap(nBM)
            }

            btn_rotate -> {
                if (degree < 345) {
                    degree += 15F
                } else {
                    degree = 0F
                }
                val rotateBitmap = BitmapUtils.rotateBitmap(originBM, degree)
                ivDestImg.setImageBitmap(rotateBitmap)
            }
            btn_skewBitmap -> {
                val skewBitmap = BitmapUtils.skewBitmap(originBM)
                ivDestImg.setImageBitmap(skewBitmap)
            }
            btn_CycleBitmap->{
                val originBM = BitmapFactory.decodeResource(
                    resources,
                    R.drawable.test2
                )
                val cycleBitmap = BitmapUtils.toRoundBitmap(originBM)
                ivDestImg.setImageBitmap(cycleBitmap)
            }
        }
    }
}