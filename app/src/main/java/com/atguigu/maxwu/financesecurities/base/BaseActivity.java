package com.atguigu.maxwu.financesecurities.base;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

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

    public LoginBean getUser() {
        SharedPreferences sp = getSharedPreferences("atguigu", MODE_PRIVATE);
        String json = sp.getString("json", "");
        LoginBean loginBean = null;
        if (json != null) {
            loginBean = JSONObject.parseObject(json, LoginBean.class);
        }
        return loginBean;
    }
    public void clearSp(){
        getSharedPreferences("atguigu", MODE_PRIVATE).edit().clear().commit();
    }

    public void saveState(String key,boolean isOk) {
        SharedPreferences sp = getSharedPreferences("atguigu", MODE_PRIVATE);
        sp.edit().putBoolean(key, isOk).commit();
    }
    public void saveImage(String path) {
        SharedPreferences sp = getSharedPreferences("atguigu", MODE_PRIVATE);
        sp.edit().putString("imageurl", path).putBoolean("isFile", true).commit();
    }

    public boolean isState(String key) {
        SharedPreferences sp = getSharedPreferences("atguigu", MODE_PRIVATE);
        return sp.getBoolean(key, false);
    }

    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
    public String getImage() {
        SharedPreferences sp = getSharedPreferences("atguigu", MODE_PRIVATE);
        boolean isFile = sp.getBoolean("isFile", false);
        if (isFile) {
            return sp.getString("imageurl", "");
        } else {
            return "";
        }
    }
    public void saveMoney(double m) {
        SharedPreferences sp = getSharedPreferences("atguigu", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putFloat("money", (float) m);
        editor.commit();
    }
    public float getMoney() {
        SharedPreferences sp = getSharedPreferences("atguigu", MODE_PRIVATE);
        return sp.getFloat("money", 0);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
        AppManager.getInstance().removeActivity(this);
    }
}
