package com.atguigu.maxwu.financesecurities.base;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.atguigu.maxwu.financesecurities.R;
import com.atguigu.maxwu.financesecurities.view.LoadingPager;
import com.gyf.barlibrary.ImmersionBar;
import com.gyf.barlibrary.ImmersionFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 作者: WuKai
 * 时间: 2017/6/20
 * 邮箱: 648838173@qq.com
 * 作用:
 */

public abstract class BaseFragment extends ImmersionFragment {

    public Context mContext;
    private Unbinder bind;
    private LoadingPager loadingPager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        loadingPager = new LoadingPager(mContext) {
            @Override
            protected void setData(View successView, String content) {
                bind = ButterKnife.bind(BaseFragment.this, successView);
                setContent(content);
            }

            @Override
            protected String getUrl() {
                return BaseFragment.this.getUrl();
            }

            @Override
            public int getlayout() {
                return setLayoutId();
            }
        };

        initView();
        initData();
        setListener();
        return loadingPager;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loadingPager.loadNet();
    }

    protected abstract String getUrl();

    protected abstract void setContent(String content);

    public abstract void setListener();

    public abstract int setLayoutId();

    ;

    public abstract void initView();

    public void initData() {

    }

    @Override
    protected void immersionInit() {
        if (loadingPager != null) {
            ImmersionBar.with(this).titleBar(R.id.toolbar, loadingPager, true).statusBarDarkFont(false, 0.2f)
                    .navigationBarColor(R.color.shape1).init();
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ImmersionBar.with(this).destroy();
        if (bind != null) {
            bind.unbind();
        }
    }

}
