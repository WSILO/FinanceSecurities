package com.atguigu.maxwu.financesecurities.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.atguigu.maxwu.financesecurities.R;
import com.atguigu.maxwu.financesecurities.utils.UIUtils;

/**
 * 作者: WuKai
 * 时间: 2017/6/23
 * 邮箱: 648838173@qq.com
 * 作用:
 */

public class ProgressView extends View {

    private Context context;
    private Paint paint;
    private int width;
    private int height;
    private int textColor;
    private int borderColor;
    private int progressColor;
    private int progress;
    private float radius;
    private int borderWidth;

    public ProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context =context;
        initAtrrs(attrs);
        initPaint();
    }

    private void initAtrrs(AttributeSet attrs) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.ProgressView);
        borderWidth = typedArray.getInteger(R.styleable.ProgressView_borderWidth, UIUtils.dip2px(context, 10));
        textColor = typedArray.getColor(R.styleable.ProgressView_textColor, Color.parseColor("#66cc00cc"));
        progressColor = typedArray.getColor(R.styleable.ProgressView_progressColor, Color.parseColor("#ff0000"));
        borderColor = typedArray.getColor(R.styleable.ProgressView_borderColor, Color.parseColor("#000000"));
        progress = typedArray.getInt(R.styleable.ProgressView_progress, 0);
        radius = typedArray.getFloat(R.styleable.ProgressView_radius, UIUtils.dip2px(context, 55));
        typedArray.recycle();
    }

    private void initPaint() {
        paint = new Paint();
        paint.setTextSize(28);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = getMeasuredWidth();
        height = getMeasuredHeight();
        Log.e("TAG","width=="+width);
        Log.e("TAG","height=="+height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //绘制眼环
        paint.setColor(borderColor);
        paint.setStrokeWidth(borderWidth);
        float cx = width/2;
        float cy = height/2;
        radius = cx - borderWidth / 2;
        canvas.drawCircle(cx, cy, radius, paint);

        //绘制进度条
        paint.setColor(progressColor);
        RectF rectF = new RectF();
        rectF.set(borderWidth / 2, borderWidth / 2, width - borderWidth / 2, height - borderWidth / 2);
        canvas.drawArc(rectF, 0, progress * 3.6f, false, paint);

        //绘制文本
        paint.setStrokeWidth(0);
        paint.setColor(textColor);
        String s = progress + "%";
        Rect rect = new Rect();
        paint.getTextBounds(s, 0, s.length(), rect);
        int tvWidth = rect.width();
        int tvHeight = rect.height();
        canvas.drawText(s, width / 2 - tvWidth / 2, height / 2 + tvHeight / 2, paint);
    }

    public void setProgress(final String p) {
        ValueAnimator animator = new ValueAnimator().ofInt(Integer.parseInt(p));
        animator.setDuration(3000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int a = (int) valueAnimator.getAnimatedValue();
                progress = a;
                invalidate();
            }
        });
        animator.start();
    }
}
