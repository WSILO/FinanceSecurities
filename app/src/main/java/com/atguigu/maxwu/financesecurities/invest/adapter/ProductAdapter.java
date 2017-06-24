package com.atguigu.maxwu.financesecurities.invest.adapter;

import android.content.Context;

import com.atguigu.maxwu.financesecurities.invest.bean.ProductListBean;
import com.atguigu.maxwu.financesecurities.invest.holder.BaseHolder;
import com.atguigu.maxwu.financesecurities.invest.holder.ProductHolder;

import java.util.List;

/**
 * 作者: WuKai
 * 时间: 2017/6/24
 * 邮箱: 648838173@qq.com
 * 作用:
 */
public class ProductAdapter extends MyBaseAdapter<ProductListBean.DataBean>{


    public ProductAdapter(Context mContext, List<ProductListBean.DataBean> datas) {
        super(mContext, datas);
    }

    @Override
    public BaseHolder getHolder() {
        return new ProductHolder(mContext);
    }

    @Override
    public void setData(List<ProductListBean.DataBean> data) {
        super.setData(data);

    }
}
