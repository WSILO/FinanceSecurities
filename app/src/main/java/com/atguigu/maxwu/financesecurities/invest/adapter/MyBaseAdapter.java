package com.atguigu.maxwu.financesecurities.invest.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.atguigu.maxwu.financesecurities.invest.holder.BaseHolder;

import java.util.List;

/**
 * 作者: WuKai
 * 时间: 2017/6/24
 * 邮箱: 648838173@qq.com
 * 作用:
 */
public abstract class MyBaseAdapter<T> extends BaseAdapter {


    public List<T> datas;
    public final Context mContext;

    public MyBaseAdapter(Context mContext, List<T> datas) {
        this.datas = datas;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return datas == null ? 0 : datas.size();
    }

    @Override
    public T getItem(int i) {
        return datas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        BaseHolder holder;
        if (view == null) {
            holder = getHolder();
        } else {
            holder = (BaseHolder) view.getTag();
        }
        holder.setT(datas.get(i));
        return holder.getView();
    }

    public abstract BaseHolder getHolder();

    public void setData(List<T> data) {
        this.datas = data;
        notifyDataSetChanged();
    }
}
