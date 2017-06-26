package com.atguigu.maxwu.financesecurities.base;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.alibaba.fastjson.JSONObject;
import com.atguigu.maxwu.financesecurities.bean.LoginBean;
import com.atguigu.maxwu.financesecurities.common.AppManager;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 作者: WuKai
 * 时间: 2017/6/26
 * 邮箱: 648838173@qq.com
 * 作用:
 */

public abstract class BaseActivity extends AppCompatActivity {
    private Unbinder bind;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        bind = ButterKnife.bind(this);
        AppManager.getInstance().addActivity(this);
        initView();
        initData();
        initListener();
    }

    protected abstract void initListener();

    protected abstract void initData();

    protected abstract void initView();

    public abstract int getLayoutId();

    public <T> T instance(int id) {
        return (T) findViewById(id);
    }

    public void saveJson(String json) {
        if (json != null) {
            SharedPreferences sp = getSharedPreferences("atguigu", MODE_PRIVATE);
            sp.edit().putString("json", json).commit();
        }
    }

    public void saveState(boolean isOk) {
        SharedPreferences sp = getSharedPreferences("atguigu", MODE_PRIVATE);
        sp.edit().putBoolean("isOk", isOk).commit();
    }

    public LoginBean getUser() {
        SharedPreferences sp = getSharedPreferences("atguigu", MODE_PRIVATE);
        String json = sp.getString("json", "");
        LoginBean loginBean = null;
        if (json != null) {
            loginBean = JSONObject.parseObject(json, LoginBean.class);
        }
        return loginBean;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
        AppManager.getInstance().removeActivity(this);
    }
}
