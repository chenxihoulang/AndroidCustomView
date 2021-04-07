package org.chw.androidcustomview.b_path;

import android.content.Context;
import android.graphics.*;
import android.util.AttributeSet;
import androidx.annotation.Nullable;
import org.chw.androidcustomview.BaseView;

// 弧线起点示例
public class B3ArcStartView extends BaseView {

    private int width;
    private Paint ovalPaint;
    private Paint arcPaint;

    public B3ArcStartView(Context context) {
        this(context, null);
    }

    public B3ArcStartView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public B3ArcStartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        ovalPaint = new Paint();
        ovalPaint.setColor(Color.parseColor("#484848"));
        ovalPaint.setAntiAlias(true);
        ovalPaint.setAntiAlias(true);

        arcPaint = new Paint();
        arcPaint.setColor(Color.GREEN);
        arcPaint.setAntiAlias(true);
        arcPaint.setStrokeWidth(10);
        arcPaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
    }

    @Override
    protected void onDraw(Canvas canvas) {

        float offset = dp2px(100);
        drawArc(canvas, offset, false);

        offset = dp2px(160);
        drawArc(canvas, width - offset, true);

    }

    private void drawArc(Canvas canvas, float left,
                         boolean force) {

        // 椭圆范围
        float top = dp2px(30);
        RectF ovalRect = getRect(left, top);

        // 椭圆背景
        canvas.drawOval(ovalRect, ovalPaint);

        // 改变起点
        Path path = new Path();
        path.moveTo(left, top);

        // 重置起点
        path.arcTo(ovalRect, 0, 90, force);

        // 绘制路径
        canvas.drawPath(path, arcPaint);
    }

    private RectF getRect(float left, float top) {
        float width = dp2px(80);
        float right = left + width;
        float bottom = top + width;
        RectF ovalRect = new RectF(left, top, right, bottom);
        return ovalRect;
    }

}
