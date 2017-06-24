package com.atguigu.maxwu.financesecurities.invest.investfragment;

import android.text.TextUtils;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.atguigu.maxwu.financesecurities.R;
import com.atguigu.maxwu.financesecurities.base.BaseFragment;
import com.atguigu.maxwu.financesecurities.common.NetConfig;
import com.atguigu.maxwu.financesecurities.invest.adapter.ProductAdapter;
import com.atguigu.maxwu.financesecurities.invest.bean.ProductListBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 作者: WuKai
 * 时间: 2017/6/24
 * 邮箱: 648838173@qq.com
 * 作用:
 */
public class InvestAllFragment extends BaseFragment {

    @BindView(R.id.lv_invest)
    ListView lvInvest;
    private List<ProductListBean.DataBean> datas = new ArrayList<>();
    private ProductAdapter adapter;

    @Override
    protected String getUrl() {
        return NetConfig.PRODUCT;
    }

    @Override
    protected void setContent(String content) {
        if (TextUtils.isEmpty(content)) {
        } else {
            parseJson(content);
        }
    }

    private void parseJson(String content) {
        ProductListBean listBean = JSON.parseObject(content, ProductListBean.class);
        List<ProductListBean.DataBean> data = listBean.getData();
        if(data != null && data.size() >0) {
            adapter.setData(data);
        }
    }

    @Override
    public void setListener() {

    }

    @Override
    public int setLayoutId() {
        return R.layout.all_fragment;
    }

    @Override
    public void initView() {
        adapter = new ProductAdapter(mContext, datas);
        lvInvest.setAdapter(adapter);
    }
}
