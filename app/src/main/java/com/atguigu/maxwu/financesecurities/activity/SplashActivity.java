package com.atguigu.maxwu.financesecurities.activity;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.atguigu.maxwu.financesecurities.R;
import com.atguigu.maxwu.financesecurities.base.BaseActivity;
import com.atguigu.maxwu.financesecurities.bean.UpdateBean;
import com.atguigu.maxwu.financesecurities.common.NetConfig;
import com.atguigu.maxwu.financesecurities.common.ThreadManager;
import com.atguigu.maxwu.financesecurities.utils.HttpUtils;
import com.atguigu.maxwu.financesecurities.utils.UIUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class SplashActivity extends BaseActivity {

    private RelativeLayout rlSplash;
    private TextView tvVersion;
    private AnimationSet set;

    protected void initListener() {

        set.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (isLoadNet()) {
                    HttpUtils.getInstance().get(NetConfig.UPDATE, new HttpUtils.CallBackListener() {
                        @Override
                        public void onSuccess(String content) {
                            if (TextUtils.isEmpty(content)) {
                                startActivity();
                            } else {
                                UpdateBean updateBean = JSON.parseObject(content, UpdateBean.class);
                                if (updateBean.getVersion() == getVersionName()) {
                                    startActivity();
                                } else {
                                    showDialog(updateBean);
                                }
                            }
                        }

                        @Override
                        public void onFailure(String content) {

                        }
                    });
                } else {
                    startActivity();
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    private void showDialog(final UpdateBean updateBean) {

        new AlertDialog.Builder(this)
                .setMessage(updateBean.getDesc())
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startActivity();
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        downLoadAPK(updateBean);
                    }
                })
                .show();
    }

    private void downLoadAPK(final UpdateBean updateBean) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.show();
        File fileDir;
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            fileDir = getExternalFilesDir("");
        } else {
            fileDir = getFilesDir();
        }
        final File file = new File(fileDir, "update.apk");
        ThreadManager.getInstance().getThread().execute(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(NetConfig.BASE_URL + updateBean.getApkUrl().substring(35));
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setConnectTimeout(5000);
                    connection.setReadTimeout(5000);
                    connection.connect();
                    if (connection.getResponseCode() == 200) {
                        progressDialog.setMax(connection.getContentLength());
                        InputStream is = connection.getInputStream();
                        FileOutputStream fos = new FileOutputStream(file);
                        int length;
                        byte[] buffer = new byte[1024];
                        while ((length = is.read(buffer)) != -1) {
                            fos.write(buffer, 0, length);
                            progressDialog.incrementProgressBy(length);
                        }
                        fos.close();
                        is.close();
                        progressDialog.dismiss();
                        Intent intent = new Intent("android.intent.action.INSTALL_PACKAGE");
                        intent.setData(Uri.parse("file:" + file.getAbsolutePath()));
                        startActivity(intent);
                    } else {
                        showToast("下载失败");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

    private void startActivity() {
        if (isLogin()) {
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
            finish();
        } else {
            startActivity(new Intent(SplashActivity.this, LoginActivity.class));
            finish();
        }
    }

    private boolean isLogin() {
        return isState("isOk");
    }

    protected void initData() {
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

    protected void initView() {
        tvVersion = instance(R.id.tv_version);
        rlSplash = instance(R.id.rl_splash);

        setAnimation();
        rlSplash.startAnimation(set);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
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

    //判断是否联网
    private boolean isLoadNet() {
        boolean connected = false;
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        if (networkInfo != null) {
            connected = networkInfo.isConnected();
        }
        return connected;
    }
}
