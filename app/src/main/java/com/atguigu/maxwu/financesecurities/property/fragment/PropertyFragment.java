package com.atguigu.maxwu.financesecurities.property.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.atguigu.maxwu.financesecurities.R;
import com.atguigu.maxwu.financesecurities.activity.MainActivity;
import com.atguigu.maxwu.financesecurities.base.BaseFragment;
import com.atguigu.maxwu.financesecurities.bean.LoginBean;
import com.atguigu.maxwu.financesecurities.common.NetConfig;
import com.atguigu.maxwu.financesecurities.property.activity.InvestActivity;
import com.atguigu.maxwu.financesecurities.property.activity.InvestVisualActivity;
import com.atguigu.maxwu.financesecurities.property.activity.PropertyActivity;
import com.atguigu.maxwu.financesecurities.property.activity.RechargeActivity;
import com.atguigu.maxwu.financesecurities.property.activity.SettingsActivity;
import com.atguigu.maxwu.financesecurities.property.activity.WithdrawActivity;
import com.atguigu.maxwu.financesecurities.utils.BitmapUtils;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * 作者: WuKai
 * 时间: 2017/6/20
 * 邮箱: 648838173@qq.com
 * 作用:
 */
public class PropertyFragment extends BaseFragment {


    @BindView(R.id.ib_back)
    ImageButton ibBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.ib_settings)
    ImageButton ibSettings;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.iv_me_icon)
    ImageView ivMeIcon;
    @BindView(R.id.rl_me_icon)
    RelativeLayout rlMeIcon;
    @BindView(R.id.tv_me_name)
    TextView tvMeName;
    @BindView(R.id.rl_me)
    RelativeLayout rlMe;
    @BindView(R.id.recharge)
    ImageView recharge;
    @BindView(R.id.withdraw)
    ImageView withdraw;
    @BindView(R.id.ll_touzi)
    TextView llTouzi;
    @BindView(R.id.ll_touzi_zhiguan)
    TextView llTouziZhiguan;
    @BindView(R.id.ll_zichan)
    TextView llZichan;
    private Transformation transformation;
    private MainActivity activity;

    @Override
    protected String getUrl() {
        return null;
    }

    @Override
    protected void setContent(String content) {

    }

    @Override
    public void setListener() {

    }

    @Override
    public int setLayoutId() {
        return R.layout.fragment_property;
    }

    @Override
    public void initView() {
       transformation = new Transformation() {
            @Override
            public Bitmap transform(Bitmap source) {
                return BitmapUtils.getBitmap(source);
            }

            @Override
            public String key() {
                return "cir";
            }
        };
        tvTitle.setText("资产");
        activity = (MainActivity) mContext;
        LoginBean.DataBean dataBean = activity.getUser().getData();
        Picasso.with(mContext).load(NetConfig.BASE_URL + "images/tx.png").transform(transformation).into(ivMeIcon);
        String name = dataBean.getName();
        tvMeName.setText(name);
    }

    @Override
    public void initData() {
        super.initData();
    }

    @Override
    protected void immersionInit() {
        super.immersionInit();
    }

    @OnClick({R.id.ib_settings, R.id.recharge, R.id.withdraw, R.id.ll_touzi, R.id.ll_touzi_zhiguan, R.id.ll_zichan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ib_settings:
                startActivity(new Intent(mContext, SettingsActivity.class));
                break;
            case R.id.recharge:
                startActivity(new Intent(mContext, RechargeActivity.class));
                break;
            case R.id.withdraw:
                startActivity(new Intent(mContext, WithdrawActivity.class));
                break;
            case R.id.ll_touzi:
                startActivity(new Intent(mContext, InvestActivity.class));
                break;
            case R.id.ll_touzi_zhiguan:
                startActivity(new Intent(mContext, InvestVisualActivity.class));
                break;
            case R.id.ll_zichan:
                startActivity(new Intent(mContext, PropertyActivity.class));
                break;
        }
    }
    @Override
    public void onResume() {
        super.onResume();

        String image = activity.getImage();

        /*
        * 判断加载网络图片还是本地图片
        * */
        if (TextUtils.isEmpty(image)) {
            //加载头像
            Picasso.with(getActivity())
                    .load(NetConfig.BASE_URL + "images/tx.png")
                    .transform(transformation)
                    .into(ivMeIcon);
        } else {
            //加载头像
            Picasso.with(getActivity())
                    .load(new File(image))
                    .transform(transformation)
                    .into(ivMeIcon);
        }
    }
}
