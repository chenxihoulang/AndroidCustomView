package org.chw.androidcustomview;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;

/**
 * @author chaihongwei
 * @date 2021-04-06 16:03:58
 */
public class BitmapUtils {
    /**
     * 根据给定的宽和高进行拉伸
     *
     * @param origin    原图
     * @param newWidth  新图的宽
     * @param newHeight 新图的高
     * @return new Bitmap
     */
    public static Bitmap scaleBitmap(Bitmap origin, int newWidth, int newHeight) {
        if (origin == null) {
            return null;
        }
        int height = origin.getHeight();
        int width = origin.getWidth();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);// 使用后乘
        Bitmap newBM = Bitmap.createBitmap(origin, 0, 0, width, height, matrix, false);
        if (!origin.isRecycled()) {
            origin.recycle();
        }
        return newBM;
    }

    /**
     * 按比例缩放图片
     *
     * @param origin 原图
     * @param ratio  比例
     * @return 新的bitmap
     */
    public static Bitmap scaleBitmap(Bitmap origin, float ratio) {
        if (origin == null) {
            return null;
        }
        int width = origin.getWidth();
        int height = origin.getHeight();
        Matrix matrix = new Matrix();
        matrix.postScale(ratio, ratio);
        Bitmap newBM = Bitmap.createBitmap(origin, 0, 0, width, height, matrix, false);
        if (newBM.equals(origin)) {
            return newBM;
        }
        origin.recycle();
        return newBM;
    }

    /**
     * 裁剪
     *
     * @param bitmap 原图
     * @return 裁剪后的图像
     */
    public static Bitmap cropBitmap(Bitmap bitmap) {
        int w = bitmap.getWidth(); // 得到图片的宽，高
        int h = bitmap.getHeight();
        int cropWidth = w >= h ? h : w;// 裁切后所取的正方形区域边长
        cropWidth /= 2;
        int cropHeight = (int) (cropWidth / 1.2);
        return Bitmap.createBitmap(bitmap, w / 3, 0, cropWidth, cropHeight, null, false);
    }

    /**
     * 选择变换
     *
     * @param origin 原图
     * @param degree 旋转角度，可正可负
     * @return 旋转后的图片
     */
    public static Bitmap rotateBitmap(Bitmap origin, float degree) {
        if (origin == null) {
            return null;
        }
        int width = origin.getWidth();
        int height = origin.getHeight();
        Matrix matrix = new Matrix();
        matrix.setRotate(degree);
        // 围绕原地进行旋转
        Bitmap newBM = Bitmap.createBitmap(origin, 0, 0, width, height, matrix, false);
        if (newBM.equals(origin)) {
            return newBM;
        }
        origin.recycle();
        return newBM;
    }

    /**
     * 偏移效果
     *
     * @param origin 原图
     * @return 偏移后的bitmap
     */
    public static Bitmap skewBitmap(Bitmap origin) {
        if (origin == null) {
            return null;
        }
        int width = origin.getWidth();
        int height = origin.getHeight();
        Matrix matrix = new Matrix();
        matrix.postSkew(-0.6f, -0.3f);
        Bitmap newBM = Bitmap.createBitmap(origin, 0, 0, width, height, matrix, false);
        if (newBM.equals(origin)) {
            return newBM;
        }
        origin.recycle();
        return newBM;
    }

    /**
     * 转换图片成圆形
     *
     * @param bitmap 传入Bitmap对象
     * @return
     */
    public static Bitmap toRoundBitmap(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }

        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        float roundPx;
        float left, top, right, bottom, dst_left, dst_top, dst_right, dst_bottom;
        if (width <= height) {
            roundPx = width / 2;

            left = 0;
            top = 0;
            right = width;
            bottom = width;

            height = width;

            dst_left = 0;
            dst_top = 0;
            dst_right = width;
            dst_bottom = width;
        } else {
            roundPx = height / 2;
            float clip = (width - height) / 2;

            left = clip;
            top = 0;
            right = width - clip;
            bottom = height;

            width = height;

            dst_left = 0;
            dst_top = 0;
            dst_right = height;
            dst_bottom = height;
        }

        /**
         * 图片裁剪核心代码
         */
        //1.创建一个和原图片一样大小的空Bitmap
        Bitmap output = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        //2.创建一个Canvas对象,并传入空Bitmap,后续的canvas绘制就会在Bitmap上
        Canvas canvas = new Canvas(output);

        final Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        //原图片区域(动态计算)
        final Rect src = new Rect((int) left, (int) top, (int) right, (int) bottom);
        //裁剪框的位置(动态计算)
        final RectF dst = new RectF(dst_left, dst_top, dst_right, dst_bottom);

        paint.setAntiAlias(true);

        //3.使用canvas进行绘制,先绘制透明背景
        canvas.drawColor(Color.TRANSPARENT);

        //4.绘制裁剪框形状
        canvas.drawRoundRect(dst, roundPx, roundPx, paint);

        //5.设置混合模式,如:PorterDuff.Mode.SRC显示上层绘制图片,PorterDuff.Mode.DST显示下层绘制图片
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        //6.最后绘制原始Bitmap对象,最后绘制的肯定在上层
        canvas.drawBitmap(bitmap, src, dst, paint);

        return output;
    }
}
