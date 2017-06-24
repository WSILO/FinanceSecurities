package com.atguigu.maxwu.financesecurities.invest.holder;

import android.content.Context;
import android.view.View;

import butterknife.ButterKnife;

/**
 * 作者: WuKai
 * 时间: 2017/6/24
 * 邮箱: 648838173@qq.com
 * 作用:
 */
public abstract class BaseHolder<T> {

    private View rootview;
    private T t;
    public Context mContext;

    public void setT(T t) {
        this.t = t;
        setData(t);
    }

    public abstract void setData(T t);

    public BaseHolder(Context mContext) {
        this.mContext = mContext;
        rootview = initView();
        ButterKnife.bind(this, rootview);
        rootview.setTag(this);
    }

    public abstract View initView();

    public View getView() {
        return rootview;
    }
}
