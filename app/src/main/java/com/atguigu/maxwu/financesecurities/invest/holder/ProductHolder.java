package com.atguigu.maxwu.financesecurities.invest.holder;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.atguigu.maxwu.financesecurities.R;
import com.atguigu.maxwu.financesecurities.invest.bean.ProductListBean;
import com.atguigu.maxwu.financesecurities.view.ProgressView;

import butterknife.BindView;

/**
 * 作者: WuKai
 * 时间: 2017/6/24
 * 邮箱: 648838173@qq.com
 * 作用:
 */
public class ProductHolder extends BaseHolder<ProductListBean.DataBean> {

    @BindView(R.id.p_name)
    TextView pName;
    @BindView(R.id.p_money)
    TextView pMoney;
    @BindView(R.id.p_yearlv)
    TextView pYearlv;
    @BindView(R.id.p_suodingdays)
    TextView pSuodingdays;
    @BindView(R.id.p_minzouzi)
    TextView pMinzouzi;
    @BindView(R.id.p_minnum)
    TextView pMinnum;
    @BindView(R.id.p_progresss)
    ProgressView pProgresss;

    @Override
    public void setData(ProductListBean.DataBean dataBean) {
        pName.setText(dataBean.getName());
        pProgresss.setProgress(dataBean.getProgress());
        pMinnum.setText(dataBean.getMinTouMoney());
        pMinzouzi.setText(dataBean.getMemberNum());
        pMoney.setText(dataBean.getMoney());
        pYearlv.setText(dataBean.getYearRate());
        pSuodingdays.setText(dataBean.getSuodingDays());
    }

    public ProductHolder(Context mContext) {
        super(mContext);
    }

    @Override
    public View initView() {
        return View.inflate(mContext, R.layout.all_item, null);
    }
}
