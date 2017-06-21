package com.atguigu.maxwu.financesecurities.home.homefragment;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.atguigu.maxwu.financesecurities.R;
import com.atguigu.maxwu.financesecurities.base.BaseFragment;
import com.atguigu.maxwu.financesecurities.common.NetConfig;
import com.atguigu.maxwu.financesecurities.home.bean.IndexBean;
import com.atguigu.maxwu.financesecurities.utils.HttpUtils;
import com.atguigu.maxwu.financesecurities.utils.PicassoImageLoader;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * 作者: WuKai
 * 时间: 2017/6/20
 * 邮箱: 648838173@qq.com
 * 作用:
 */
public class HomeFragment extends BaseFragment {


    @BindView(R.id.ib_back)
    ImageButton ibBack;
    @BindView(R.id.ib_settings)
    ImageButton ibSettings;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.tv_home_yearrate)
    TextView tvHomeYearrate;
    @BindView(R.id.bt_join)
    Button btJoin;
    private List<String> list;
    private HttpUtils.CallBackListener listener = new HttpUtils.CallBackListener() {
        @Override
        public void onSuccess(String content) {
            pareJson(content);
        }

        @Override
        public void onFailure(String content) {

        }
    };

    private void pareJson(String content) {
        IndexBean indexBean = JSON.parseObject(content, IndexBean.class);
        List<IndexBean.ImageArrBean> imageArr = indexBean.getImageArr();
        if(imageArr != null && imageArr.size() > 0) {
            for(int i = 0; i < imageArr.size(); i++) {
                list.add(NetConfig.BASE_URL+imageArr.get(i).getIMAURL());
            }
            initBanner();
        }
    }

    private void initBanner() {
        banner.setImageLoader(new PicassoImageLoader()).setImages(list).start();
    }

    @Override
    public void setListener() {

    }

    @Override
    public int setLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void initView() {
    }

    @Override
    public void initData() {
        super.initData();
        list = new ArrayList<>();
        loadNet();
    }

    private void loadNet() {
        HttpUtils.getInstance().get(NetConfig.INDEX, listener);
    }

    @Override
    protected void immersionInit() {
        super.immersionInit();
    }


    @OnClick({R.id.ib_back, R.id.ib_settings, R.id.bt_join})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ib_back:
                break;
            case R.id.ib_settings:
                break;
            case R.id.bt_join:
                break;
        }
    }
}
