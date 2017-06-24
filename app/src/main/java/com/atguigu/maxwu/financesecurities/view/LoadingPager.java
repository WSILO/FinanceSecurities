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
    private LoadState loadState;

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
        //保证在主线程更新UI
        UIUtils.runOnUIThread(new Runnable() {
            @Override
            public void run() {
                showPager();
            }
        });
    }

    //显示页面
    private void showPager() {
        loadView.setVisibility(currentState == STATE_LOAD ? VISIBLE : GONE);
        errorView.setVisibility(currentState == STATE_ERROR ? VISIBLE : GONE);
        int id = getlayout();
        if (successView == null && id != 0) {
            successView = View.inflate(getContext(), id, null);
            addView(successView);
        }
        if (successView != null) {
            successView.setVisibility(currentState == STATE_SUCCESS ? VISIBLE : GONE);
        }
    }

    public void loadNet() {
        String url = getUrl();
        if (TextUtils.isEmpty(url)) {//url为空时直接加载子布局
            currentState = STATE_SUCCESS;
            showSafePager();
            setData(successView, "");
        } else {//否则进行网络请求
            HttpUtils.getInstance().get(url, new HttpUtils.CallBackListener() {
                @Override
                public void onSuccess(String content) {
                    if (TextUtils.isEmpty(content)) {
                        loadState = LoadState.ERROR;
                        showState();
                    } else {
                        loadState = LoadState.SUCCESS;
                        loadState.setJson(content);
                        showState();
                    }
                }

                @Override
                public void onFailure(String content) {
                    loadState = LoadState.ERROR;
                    showState();
                }
            });
        }
    }

    private void showState() {
        switch (loadState) {
            case SUCCESS:
                currentState = STATE_SUCCESS;
                break;
            case LOADING:
                currentState = STATE_LOAD;
                break;
            case ERROR:
                currentState = STATE_ERROR;
                break;
        }
        showSafePager();
        if (currentState == STATE_SUCCESS) {
            setData(successView, LoadState.SUCCESS.getJson());
        }
    }


    //成功,失败,加载中的三个状态列举
    public enum LoadState {
        SUCCESS(0), LOADING(1), ERROR(2);

        private final int state;
        public String json;

        LoadState(int state) {
            this.state = state;
        }

        public String getJson() {
            return json;
        }

        public void setJson(String json) {
            this.json = json;
        }

    }

    protected abstract void setData(View successView, String content);

    protected abstract String getUrl();

    public abstract int getlayout();
}
