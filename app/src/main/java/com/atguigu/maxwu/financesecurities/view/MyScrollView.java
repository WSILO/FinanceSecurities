package com.atguigu.maxwu.financesecurities.view;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.youth.banner.Banner;

/**
 * 作者: WuKai
 * 时间: 2017/6/23
 * 邮箱: 648838173@qq.com
 * 作用:
 */

public class MyScrollView extends ScrollView {

    private boolean isAnFinish = true;
    private int startX;
    private int startY;
    private Rect rect;
    private LinearLayout childView;
    private Banner banner;
    private FrameLayout fl;
    private int height;

    public MyScrollView(Context context) {
        this(context, null);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        rect = new Rect();

    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (getChildCount() > 0) {
            childView = (LinearLayout) getChildAt(0);
            if (childView.getChildCount() > 0) {
                fl = (FrameLayout) childView.getChildAt(0);
            }
            if (fl.getChildCount() > 0) {
                banner = (Banner) fl.getChildAt(0);
            }
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        height = getMeasuredHeight();
        height = getHeight();
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (getChildCount() == 0 || !isAnFinish) {
            return super.onTouchEvent(ev);
        }
        int y = (int) ev.getY();
        switch (ev.getAction()) {
            case MotionEvent.ACTION_MOVE:
                stopBanner();
                if (y > height) {
                    y = height;
                }
                if (y < 0) {
                    y = 0;
                }
                int disY = (int) (y - startY);
                if (rect.isEmpty()) {
                    rect.set(childView.getLeft(), childView.getTop(), childView.getRight(), childView.getBottom());
                }
                childView.layout(childView.getLeft(), childView.getTop() + disY, childView.getRight(), childView.getBottom() + disY);
                startY = y;
                break;
            case MotionEvent.ACTION_UP:
                if (!rect.isEmpty()) {
                    float anOffset = rect.top - childView.getTop();
                    TranslateAnimation ta = new TranslateAnimation(0, 0, 0, anOffset);
                    ta.setDuration(200);
                    ta.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                            isAnFinish = false;
                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            startBanner();
                            isAnFinish = true;
                            childView.clearAnimation();
                            childView.layout(rect.left, rect.top, rect.right, rect.bottom);
                            rect.setEmpty();
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {
                        }
                    });
                    childView.startAnimation(ta);
                }
                break;
        }
        return super.onTouchEvent(ev);
    }

    private void startBanner() {
        if (banner != null) {
            banner.startAutoPlay();
        }
    }

    private void stopBanner() {
        if (banner != null) {
            banner.stopAutoPlay();
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean isIntercept = false;
        int y = (int) ev.getY();
        int x = (int) ev.getX();
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startY = y;
                startX = x;
                break;
            case MotionEvent.ACTION_MOVE:
                if (Math.abs(y - startY) > Math.abs(x - startX) && Math.abs(y - startY) > 20) {
                    isIntercept = true;
                }
                break;
        }
        return isIntercept;
    }
}

