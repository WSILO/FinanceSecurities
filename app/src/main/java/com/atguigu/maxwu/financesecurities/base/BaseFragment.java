package com.atguigu.maxwu.financesecurities.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.atguigu.maxwu.financesecurities.R;
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
    public View mRootView;
    private Unbinder bind;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(setLayoutId(), container, false);
        bind = ButterKnife.bind(this, mRootView);
        initView();
        initData();
        setListener();
        return mRootView;
    }

    public abstract void setListener();

    public abstract int setLayoutId();

    ;

    public abstract void initView();

    public void initData() {

    }

    @Override
    protected void immersionInit() {
        if (mRootView != null) {
            ImmersionBar.with(this).titleBar(R.id.toolbar, mRootView, true).statusBarDarkFont(false, 0.2f)
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
