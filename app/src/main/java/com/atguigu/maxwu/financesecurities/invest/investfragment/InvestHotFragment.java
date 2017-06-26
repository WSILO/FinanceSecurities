package com.atguigu.maxwu.financesecurities.invest.investfragment;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.view.View;
import android.widget.TextView;

import com.atguigu.maxwu.financesecurities.R;
import com.atguigu.maxwu.financesecurities.base.BaseFragment;
import com.atguigu.maxwu.financesecurities.utils.UIUtils;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import butterknife.BindView;

/**
 * 作者: WuKai
 * 时间: 2017/6/24
 * 邮箱: 648838173@qq.com
 * 作用:
 */
public class InvestHotFragment extends BaseFragment {

    @BindView(R.id.flowLayout)
    TagFlowLayout idFlowlayout;

    private String[] datas = new String[]{"超级新手计划", "乐享活系列90天计划", "钱包计划", "30天理财计划(加息2%)", "90天理财计划(加息5%)", "180天理财计划(加息10%)",
            "林业局投资商业经营", "中学老师购买车辆", "屌丝下海经商计划", "新西游影视拍摄投资", "Java培训老师自己周转", "养猪场扩大经营",
            "旅游公司扩大规模", "阿里巴巴洗钱计划", "铁路局回款计划", "高级白领赢取白富美投资计划"
    };

    @Override
    protected String getUrl() {
        return null;
    }

    @Override
    protected void setContent(String content) {

    }

    @Override
    public void setListener() {
        idFlowlayout.setAdapter(new TagAdapter<String>(datas) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView tv = new TextView(mContext);
                tv.setText(s);
                tv.setTextColor(Color.WHITE);
                Drawable drawable = mContext.getResources().getDrawable(R.drawable.hot_shape);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    tv.setBackground(drawable);
                }
                GradientDrawable tvDrawable = (GradientDrawable) tv.getBackground();
                tvDrawable.setColor(UIUtils.setColor());
                return tv;
            }
        });
    }

    @Override
    public int setLayoutId() {
        return R.layout.invest_hot_fragment;
    }

    @Override
    public void initView() {

    }
}
