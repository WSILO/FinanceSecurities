package com.atguigu.maxwu.financesecurities.invest.investfragment;

import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.atguigu.maxwu.financesecurities.R;
import com.atguigu.maxwu.financesecurities.base.BaseFragment;
import com.atguigu.maxwu.financesecurities.utils.UIUtils;
import com.atguigu.maxwu.financesecurities.view.randomLayout.StellarMap;

import java.util.Random;

import butterknife.BindView;

/**
 * 作者: WuKai
 * 时间: 2017/6/24
 * 邮箱: 648838173@qq.com
 * 作用:
 */
public class InvestReFragment extends BaseFragment {

    @BindView(R.id.stell)
    StellarMap stell;
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

    }

    @Override
    public int setLayoutId() {
        return R.layout.re_fragment;
    }

    @Override
    public void initView() {
        stell.setAdapter(new MyAdapter());
        stell.setInnerPadding(UIUtils.dip2px(mContext, 10), UIUtils.dip2px(mContext, 10), UIUtils.dip2px(mContext, 10), UIUtils.dip2px(mContext, 10));
        stell.setGroup(0, true);
        stell.setRegularity(10, 10);
    }

    class MyAdapter implements StellarMap.Adapter {
        @Override
        public int getGroupCount() {
            int num = datas.length / 7;
            if (datas.length % 7 == 0) {
                return num;
            } else {
                return num + 1;
            }
        }

        @Override
        public int getCount(int group) {
            if (datas.length % 7 == 0) {
                return 7;
            } else {
                if (group == datas.length / 7) {
                    return datas.length & 7;
                } else {
                    return 7;
                }
            }
        }

        @Override
        public View getView(int group, int position, View convertView) {
            TextView textView = new TextView(mContext);
            textView.setText(datas[group*7 +position]);
            textView.setGravity(Gravity.CENTER);
            Random random = new Random();
            int r = random.nextInt(100) + 100;
            int g = random.nextInt(100) + 100;
            int b = random.nextInt(100) + 100;
            textView.setTextColor(Color.rgb(r, g, b));
            textView.setTextSize(random.nextInt(UIUtils.dip2px(mContext, 10)) + 10);
            return textView;
        }

        @Override
        public int getNextGroupOnPan(int group, float degree) {
            return 0;
        }

        @Override
        public int getNextGroupOnZoom(int group, boolean isZoomIn) {
            return 0;
        }
    }
}
