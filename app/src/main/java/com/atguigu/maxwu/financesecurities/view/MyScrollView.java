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
    private float startX;
    private float startY;
    private Rect rect;
    private LinearLayout chidView;
    private Banner banner;
    private FrameLayout fl;

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
            chidView = (LinearLayout) getChildAt(0);
            if (chidView.getChildCount() > 0) {
                fl = (FrameLayout) chidView.getChildAt(0);
            }
            if (fl.getChildCount() > 0) {
                banner = (Banner) fl.getChildAt(0);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (getChildCount() == 0 || !isAnFinish) {
            return super.onTouchEvent(ev);
        }
        float y = ev.getY();
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startY = y;
                banner.stopAutoPlay();
                break;
            case MotionEvent.ACTION_MOVE:
                float disY = y - startY;
                if (rect.isEmpty()) {
                    rect.set(chidView.getLeft(), chidView.getTop(), chidView.getRight(), chidView.getBottom());
                }
                chidView.layout(chidView.getLeft(), (int) (chidView.getTop() + disY), chidView.getRight(), (int) (chidView.getBottom() + disY));
                startY = y;
                break;
            case MotionEvent.ACTION_UP:
                if (!rect.isEmpty()) {
                    float anOffset = rect.top - chidView.getTop();
                    TranslateAnimation ta = new TranslateAnimation(0, 0, 0, anOffset);
                    ta.setDuration(200);
                    ta.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                            isAnFinish = false;

                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            banner.startAutoPlay();
                            isAnFinish = true;
                            chidView.clearAnimation();
                            chidView.layout(rect.left, rect.top, rect.right, rect.bottom);
                            rect.setEmpty();
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });
                    chidView.startAnimation(ta);
                }
                break;
        }
        return true;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean isIntercept = false;
        float y = ev.getY();
        float x = ev.getX();
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
