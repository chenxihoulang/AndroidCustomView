package org.chw.androidcustomview.h_matrix;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.chw.androidcustomview.BaseActivity;
import org.chw.androidcustomview.R;

/**
 * @author chaihongwei
 * @date 2021-04-12 16:37:48
 */
public class MatrixActivity extends BaseActivity {
    private ImageView ivDestImg;
    private Bitmap baseBitmap;
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private TextView tvTip, tvClose;

    @Override
    public int getLayoutId() {
        return R.layout.activity_matrix;
    }

    @Override
    public void init() {
        ivDestImg = findViewById(R.id.ivDestImg);
        tvTip = findViewById(R.id.tvTip);
        tvClose = findViewById(R.id.tvClose);
        tvTip.setText("" + System.currentTimeMillis());

        baseBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.icon_logo);
//        bitmapTranslate(100, 100);
//        bitmapScale(1.5F, 1.5F);
        bitmapXMirror();
//        bitmapYMirror();
//        bitmapRotate(45);
//        bitmapSkew(1, 0);

        tvTip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MatrixActivity.this, MatrixActivity.class);
                startActivity(intent);
            }
        });

        tvClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MatrixActivity.this.finish();
            }
        });
    }

    /**
     * 图片移动
     */
    private void bitmapTranslate(float dx, float dy) {
        // 需要根据移动的距离来创建图片的拷贝图大小
        Bitmap afterBitmap = Bitmap.createBitmap(
                (int) (baseBitmap.getWidth() + dx),
                (int) (baseBitmap.getHeight() + dy), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(afterBitmap);
        Matrix matrix = new Matrix();
        // 设置移动的距离
        matrix.setTranslate(dx, dy);
        canvas.drawBitmap(baseBitmap, matrix, paint);
        ivDestImg.setImageBitmap(afterBitmap);
    }

    /**
     * 缩放图片
     */
    private void bitmapScale(float x, float y) {
        // 因为要将图片放大，所以要根据放大的尺寸重新创建Bitmap
        Bitmap afterBitmap = Bitmap.createBitmap(
                (int) (baseBitmap.getWidth() * x),
                (int) (baseBitmap.getHeight() * y), baseBitmap.getConfig());
        Canvas canvas = new Canvas(afterBitmap);
        // 初始化Matrix对象
        Matrix matrix = new Matrix();
        // 根据传入的参数设置缩放比例
        matrix.setScale(x, y);
//        matrix.setScale(x, y, baseBitmap.getWidth() / 2, baseBitmap.getHeight() / 2);
        // 根据缩放比例，把图片draw到Canvas上
        canvas.drawBitmap(baseBitmap, matrix, paint);
        ivDestImg.setImageBitmap(afterBitmap);
    }

    /**
     * x轴镜像
     */
    private void bitmapXMirror() {
        // 因为要将图片放大，所以要根据放大的尺寸重新创建Bitmap
        Bitmap afterBitmap = Bitmap.createBitmap(
                (int) (baseBitmap.getWidth() * 1.5F + 200),
                baseBitmap.getHeight(), baseBitmap.getConfig());
        Canvas canvas = new Canvas(afterBitmap);
        canvas.drawColor(Color.GREEN);


        // 初始化Matrix对象
        Matrix matrix = new Matrix();
        // 根据传入的参数设置缩放比例
//        matrix.preTranslate(200,0);
//        matrix.preScale(1.5F, 1);
//        matrix.postTranslate(baseBitmap.getWidth(), 0); // width((M[200,0])[1.5F,1]) M先移动图片宽度,再移动200,再x缩放1.5倍

        matrix.preTranslate(200, 0);
        //x为正数,向右缩放,x为负数,向左缩放.将这个负数带入到Matrix矩阵中,用矩阵乘法计算,原来小的x,乘以-1后,反而变大了,这样就实现了翻转
        matrix.preScale(-1, 1);
        matrix.postTranslate(baseBitmap.getWidth(), 0); // width((M[200,0])[-1,1]) M先移动图片宽度,再移动200,再x缩放-1倍

        // 根据缩放比例，把图片draw到Canvas上
        canvas.drawBitmap(baseBitmap, matrix, paint);
        ivDestImg.setImageBitmap(afterBitmap);
    }

    /**
     * y轴镜像
     */
    private void bitmapYMirror() {
        // 初始化Matrix对象
        Matrix matrix = new Matrix();
        // 根据传入的参数设置缩放比例
        matrix.postScale(1, -1);
        //根据变换矩阵，绘制新的图片
        Bitmap afterBitmap = Bitmap.createBitmap(baseBitmap, 0, 0,
                baseBitmap.getWidth(), baseBitmap.getHeight(),
                matrix, true);
        ivDestImg.setImageBitmap(afterBitmap);
    }

    /**
     * 图片旋转
     */
    private void bitmapRotate(float degrees) {
        //对角线长度
        int maxSize = (int) Math.hypot(baseBitmap.getWidth(), baseBitmap.getHeight());
        // 创建一个和原图一样大小的图片
        Bitmap afterBitmap = Bitmap.createBitmap(maxSize, maxSize, baseBitmap.getConfig());
        Canvas canvas = new Canvas(afterBitmap);
        Matrix matrix = new Matrix();
        // 根据原图的中心位置旋转
        matrix.setRotate(degrees, baseBitmap.getWidth() / 2, baseBitmap.getHeight() / 2);
        canvas.drawBitmap(baseBitmap, matrix, paint);
        ivDestImg.setImageBitmap(afterBitmap);
    }

    /**
     * 倾斜图片
     */
    private void bitmapSkew(float dx, float dy) {
        // 根据图片的倾斜比例，计算变换后图片的大小，
        Matrix matrix = new Matrix();
        // 设置图片倾斜的比例
        matrix.setSkew(dx, dy);
        //根据变换矩阵，绘制新的图片
        Bitmap afterBitmap = Bitmap.createBitmap(baseBitmap, 0, 0,
                baseBitmap.getWidth(), baseBitmap.getHeight(),
                matrix, true);
        ivDestImg.setImageBitmap(afterBitmap);
    }
}
