package com.atguigu.maxwu.financesecurities.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.atguigu.maxwu.financesecurities.R;
import com.atguigu.maxwu.financesecurities.utils.HttpUtils;
import com.atguigu.maxwu.financesecurities.utils.UIUtils;

/**
 * 作者: WuKai
 * 时间: 2017/6/23
 * 邮箱: 648838173@qq.com
 * 作用:
 */

public abstract class LoadingPager extends FrameLayout {

    private View errorView;
    private View loadView;
    private View successView;
    private final static int STATE_LOAD = 0;
    private final static int STATE_ERROR = 1;
    private final static int STATE_SUCCESS = 2;
    private int currentState = STATE_LOAD;

    public LoadingPager(Context context) {
        this(context, null);
    }

    public LoadingPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        loadView = UIUtils.inflate(R.layout.load_view);
        addView(loadView);
        errorView = UIUtils.inflate(R.layout.error_view);
        addView(errorView);
        showSafePager();
    }

    private void showSafePager() {
        UIUtils.runOnUIThread(new Runnable() {
            @Override
            public void run() {
                showPager();
            }
        });
    }

    private void showPager() {
        loadView.setVisibility(currentState == STATE_LOAD ? VISIBLE : GONE);
        errorView.setVisibility(currentState == STATE_ERROR ? VISIBLE : GONE);
        int id = getlayout();
        if (successView == null && id != 0) {
            successView = View.inflate(getContext(), id, null);
            addView(successView);
        }
        successView.setVisibility(currentState == STATE_SUCCESS ? VISIBLE : GONE);
    }

    public void loadNet() {
        String url = getUrl();
        if (TextUtils.isEmpty(url)) {
            return;
        } else {
            HttpUtils.getInstance().get(url, new HttpUtils.CallBackListener() {
                @Override
                public void onSuccess(String content) {
                    if (TextUtils.isEmpty(content)) {
                        currentState = STATE_ERROR;
                        showSafePager();
                    } else {
                        currentState = STATE_SUCCESS;
                        showSafePager();
                        setData(successView,content);
                    }
                }

                @Override
                public void onFailure(String content) {

                }
            });
        }
    }

    protected abstract void setData(View successView, String content);

    protected abstract String getUrl();

    public abstract int getlayout();
}
