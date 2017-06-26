package com.atguigu.maxwu.financesecurities.activity;

import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.atguigu.maxwu.financesecurities.R;
import com.atguigu.maxwu.financesecurities.base.BaseActivity;
import com.atguigu.maxwu.financesecurities.common.NetConfig;
import com.atguigu.maxwu.financesecurities.utils.HttpUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class RegisterActiviity extends BaseActivity {


    @BindView(R.id.ib_back)
    ImageButton ibBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.ib_settings)
    ImageButton ibSettings;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.et_register_number)
    EditText etRegisterNumber;
    @BindView(R.id.et_register_name)
    EditText etRegisterName;
    @BindView(R.id.et_register_pwd)
    EditText etRegisterPwd;
    @BindView(R.id.et_register_pwdAgain)
    EditText etRegisterPwdAgain;
    @BindView(R.id.btn_register)
    Button btnRegister;

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_register;
    }

    @OnClick({R.id.ib_back, R.id.btn_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ib_back:
                finish();
                break;
            case R.id.btn_register:
                String name = etRegisterName.getText().toString().trim();
                String number = etRegisterNumber.getText().toString().trim();
                String pwd = etRegisterPwd.getText().toString().trim();
                String pwdAgain = etRegisterPwdAgain.getText().toString().trim();
                if (TextUtils.isEmpty(number)) {
                    showToast("手机号不能为空");
                    return;
                }
                if (TextUtils.isEmpty(name)) {
                    showToast("用户名不能为空");
                    return;
                }
                if (TextUtils.isEmpty(pwd)) {
                    showToast("用户名不能为空");
                    return;
                } else {
                    if (pwd.equals(pwdAgain)) {
                        Map<String, String> map = new HashMap<>();
                        map.put("name", name);
                        map.put("phone", number);
                        map.put("password", pwd);
                        HttpUtils.getInstance().post(NetConfig.REGISTER, map, new HttpUtils.CallBackListener() {
                            @Override
                            public void onSuccess(String content) {
                                if (!TextUtils.isEmpty(content)) {
                                    try {
                                        JSONObject jsonObject = new JSONObject(content);
                                        boolean isExist = jsonObject.optBoolean("isExist");
                                        if (isExist) {
                                            showToast("用户名已存在,注册失败");
                                        } else {
                                            showToast("注册成功");
                                            finish();
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }

                            @Override
                            public void onFailure(String content) {

                            }
                        });
                    }
                }
                break;
        }
    }
}
