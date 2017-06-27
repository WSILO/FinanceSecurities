package com.atguigu.maxwu.financesecurities.activity;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.atguigu.maxwu.financesecurities.R;
import com.atguigu.maxwu.financesecurities.base.BaseActivity;
import com.atguigu.maxwu.financesecurities.common.NetConfig;
import com.atguigu.maxwu.financesecurities.utils.HttpUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.ib_back)
    ImageButton ibBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.ib_settings)
    ImageButton ibSettings;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.et_user)
    EditText etUser;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.regitster_tv)
    TextView regitsterTv;
    @BindView(R.id.activity_login)
    LinearLayout activityLogin;
    private boolean isExit;

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
        return R.layout.activity_login;
    }

    @OnClick({R.id.btn_login, R.id.regitster_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_login:

                String password = etPassword.getText().toString().trim();
                String number = etUser.getText().toString().trim();
                if (TextUtils.isEmpty(password) || TextUtils.isEmpty(number)) {
                    showToast("用户名或密码不能为空");
                    return;
                }
                Map<String, String> map = new HashMap<>();
                map.put("phone", number);
                map.put("password", password);
                HttpUtils.getInstance().post(NetConfig.LOGIN, map, new HttpUtils.CallBackListener() {
                    @Override
                    public void onSuccess(String content) {
                        if (TextUtils.isEmpty(content)) {
                            showToast("用户名或密码错误");
                            return;
                        }
                        try {
                            JSONObject jsonObject = new JSONObject(content);
                            boolean isOk = jsonObject.optBoolean("success");
                            saveState(isOk);
                            if (isOk) {
                                saveJson(content);
                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                finish();
                            } else {
                                showToast("用户名或密码错误");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(String content) {
                        showToast("网络连接失败");
                    }
                });
                break;
            case R.id.regitster_tv:
                startActivity(new Intent(this, RegisterActiviity.class));
                break;
        }
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && !isExit) {
            isExit = true;
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit = false;
                }
            }, 2000);
            Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
