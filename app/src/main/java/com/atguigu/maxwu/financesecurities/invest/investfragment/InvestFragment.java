package com.atguigu.maxwu.financesecurities.invest.investfragment;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.atguigu.maxwu.financesecurities.R;
import com.atguigu.maxwu.financesecurities.base.BaseFragment;
import com.atguigu.maxwu.financesecurities.common.NetConfig;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * 作者: WuKai
 * 时间: 2017/6/20
 * 邮箱: 648838173@qq.com
 * 作用:
 */
public class InvestFragment extends BaseFragment implements View.OnClickListener {


    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_all)
    TextView tvAll;
    @BindView(R.id.tv_re)
    TextView tvRe;
    @BindView(R.id.tv_hot)
    TextView tvHot;
    @BindView(R.id.vp_invest)
    ViewPager vpInvest;
    private List<BaseFragment> fragments = new ArrayList<>();

    @Override
    protected String getUrl() {
        return NetConfig.PRODUCT;
    }

    @Override
    protected void setContent(String content) {
    }

    @Override
    public void setListener() {
        vpInvest.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        tvAll.setOnClickListener(this);
        tvRe.setOnClickListener(this);
        tvHot.setOnClickListener(this);
    }

    private void setTab(int position) {
        switch (position) {
            case 0:
                setSelectedTab(tvAll);
                break;
            case 1:
                setSelectedTab(tvRe);
                break;
            case 2:
                setSelectedTab(tvHot);
                break;
        }
    }

    private void setSelectedTab(TextView view) {
        tvAll.setTextColor(Color.BLACK);
        tvHot.setTextColor(Color.BLACK);
        tvRe.setTextColor(Color.BLACK);
        tvAll.setBackgroundColor(Color.WHITE);
        tvHot.setBackgroundColor(Color.WHITE);
        tvRe.setBackgroundColor(Color.WHITE);

        view.setTextColor(Color.WHITE);
        view.setBackgroundColor(Color.parseColor("#88ff0000"));
    }

    @Override
    public int setLayoutId() {
        return R.layout.fragment_invest;
    }

    @Override
    public void initView() {
        vpInvest.setAdapter(new MyAdapter(getFragmentManager()));
    }

    @Override
    public void initTitle() {
        super.initTitle();
        tvTitle.setText("投资");
        setSelectedTab(tvAll);
    }

    @Override
    public void initData() {
        super.initData();
    }

    @Override
    protected void immersionInit() {
        super.immersionInit();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_all:
                vpInvest.setCurrentItem(0);
                break;
            case R.id.tv_re:
                vpInvest.setCurrentItem(1);
                break;
            case R.id.tv_hot:
                vpInvest.setCurrentItem(2);
                break;
        }
    }

    class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
            initFragment(fm);
        }

        private void initFragment(FragmentManager fm) {
            fragments.add(new InvestAllFragment());
            fragments.add(new InvestReFragment());
            fragments.add(new InvestHotFragment());
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }
}