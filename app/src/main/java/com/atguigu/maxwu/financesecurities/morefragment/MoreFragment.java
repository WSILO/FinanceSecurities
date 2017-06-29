package com.atguigu.maxwu.financesecurities.morefragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.atguigu.maxwu.financesecurities.R;
import com.atguigu.maxwu.financesecurities.activity.MainActivity;
import com.atguigu.maxwu.financesecurities.activity.gesture.GestureEditActivity;
import com.atguigu.maxwu.financesecurities.base.BaseFragment;
import com.atguigu.maxwu.financesecurities.common.NetConfig;
import com.atguigu.maxwu.financesecurities.utils.HttpUtils;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * 作者: WuKai
 * 时间: 2017/6/20
 * 邮箱: 648838173@qq.com
 * 作用:
 */
public class MoreFragment extends BaseFragment {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_more_regist)
    TextView tvMoreRegist;
    @BindView(R.id.toggle_more)
    ToggleButton toggleMore;
    @BindView(R.id.tv_more_reset)
    TextView tvMoreReset;
    @BindView(R.id.rl_more_contact)
    RelativeLayout rlMoreContact;
    @BindView(R.id.tv_more_fankui)
    TextView tvMoreFankui;
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
        toggleMore.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    if (activity.isState("isSetting")) {
                        activity.saveState("toggle", b);
                    } else {
                        startActivity(new Intent(mContext, GestureEditActivity.class));
                        activity.saveState("isSetting", true);
                    }
                } else {
                    activity.saveState("toggle", b);
                }
            }
        });
    }

    @Override
    public int setLayoutId() {
        return R.layout.fragment_more;
    }

    @Override
    public void initView() {
        activity = (MainActivity) mContext;
        toggleMore.setChecked(activity.isState("toggle"));
    }

    @Override
    public void initData() {
        super.initData();
        tvTitle.setText("更多");
    }

    @Override
    protected void immersionInit() {
        super.immersionInit();
    }


    @OnClick({R.id.tv_more_regist, R.id.toggle_more, R.id.tv_more_reset, R.id.rl_more_contact, R.id.tv_more_fankui})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_more_regist:
                activity.showToast("注册");
                break;
            case R.id.toggle_more:
                break;
            case R.id.tv_more_reset:
                if (activity.isState("toggle")) {
                    startActivity(new Intent(mContext, GestureEditActivity.class));
                } else {
                    activity.showToast("您未开启手势密码...");
                }
                break;
            case R.id.rl_more_contact:
                new AlertDialog.Builder(MoreFragment.this.getActivity())
                        .setTitle("联系客服")
                        .setMessage("是否联系客服010-56253825")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(Intent.ACTION_CALL);

                                Uri uri = Uri.parse("tel:010-56253825");
                                intent.setData(uri);
                                try {
                                    startActivity(intent);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        })
                        .setNegativeButton("取消", null)
                        .show();
                break;
            case R.id.tv_more_fankui:
                final View feedbackView = View.inflate(mContext, R.layout.feedback_view, null);
                final RadioGroup fbGroup = (RadioGroup) feedbackView.findViewById(R.id.rg_fankui);
                final EditText et = (EditText) feedbackView.findViewById(R.id.et_fankui_content);
                new AlertDialog.Builder(mContext)
                        .setView(feedbackView)
                        .setNegativeButton("取消", null)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                final HashMap<String, String> map = new HashMap<String, String>();
                                fbGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                                    @Override
                                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                                        RadioButton rb = (RadioButton) feedbackView.findViewById(checkedId);
                                        map.put("department", rb.getText().toString().trim());
                                    }
                                });
                                map.put("content", et.getText().toString().trim());
                                HttpUtils.getInstance().post(NetConfig.FEEDBACK, map, new HttpUtils.CallBackListener() {
                                    @Override
                                    public void onSuccess(String json) {
                                        Log.e("TAG", "onSuccess===" + json);
                                    }

                                    @Override
                                    public void onFailure(String json) {
                                        Log.e("TAG", "onFailure===" + json);
                                    }
                                });
                            }
                        })
                        .show();
                break;
        }
    }
}