package com.atguigu.maxwu.financesecurities.activity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.atguigu.maxwu.financesecurities.R;
import com.atguigu.maxwu.financesecurities.common.AppManager;
import com.atguigu.maxwu.financesecurities.utils.UIUtils;

public class SplashActivity extends AppCompatActivity {

    private RelativeLayout rlSplash;
    private TextView tvVersion;
    private AnimationSet set;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        initView();
        initData();
        initListener();
        AppManager.getInstance().addActivity(this);
    }

    public <T> T instance(int id) {
        return (T) findViewById(id);
    }

    private void initListener() {

        set.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    private void initData() {
        String versionName = getVersionName();
        if (!TextUtils.isEmpty(versionName)) {
            UIUtils.setText(tvVersion, R.string.splash_version, versionName);
        }
    }

    private String getVersionName() {
        try {
            PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            String versionName = packageInfo.versionName;
            return versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void initView() {
        tvVersion = instance(R.id.tv_version);
        rlSplash = instance(R.id.rl_splash);

        setAnimation();
        rlSplash.startAnimation(set);
    }

    private void setAnimation() {
        ScaleAnimation sa = new ScaleAnimation(0, 1, 0, 1, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        sa.setDuration(2000);
        sa.setFillAfter(true);
        AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
        alphaAnimation.setFillAfter(true);
        alphaAnimation.setDuration(2000);
        RotateAnimation ra = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        ra.setFillAfter(true);
        ra.setDuration(2000);
        set = new AnimationSet(false);

        set.addAnimation(alphaAnimation);
        set.addAnimation(ra);
        set.addAnimation(sa);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getInstance().removeActivity(this);
    }
}
