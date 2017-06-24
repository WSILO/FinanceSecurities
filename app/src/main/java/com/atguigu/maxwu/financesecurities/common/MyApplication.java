package com.atguigu.maxwu.financesecurities.common;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

/**
 * 作者: WuKai
 * 时间: 2017/6/20
 * 邮箱: 648838173@qq.com
 * 作用:
 */

public class MyApplication extends Application {

    private static Context context;
    private static Handler handler;
    private static int pid;

    public static Handler getHandler() {
        return handler;
    }

    public static int getPid() {
        return pid;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        pid = android.os.Process.myPid();
        handler = new Handler();

    }

    public static Context getContext() {
        return context;
    }
}
