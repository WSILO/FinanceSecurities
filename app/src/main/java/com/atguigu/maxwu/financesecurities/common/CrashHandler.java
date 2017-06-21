package com.atguigu.maxwu.financesecurities.common;

import android.content.Context;
import android.os.Looper;
import android.os.Process;
import android.widget.Toast;

/**
 * 作者: WuKai
 * 时间: 2017/6/21
 * 邮箱: 648838173@qq.com
 * 作用:
 */

public class CrashHandler implements Thread.UncaughtExceptionHandler {

    private static CrashHandler handler = new CrashHandler();
    private Context context;

    private CrashHandler() {
    }

    public static CrashHandler getInstance() {
        return handler;
    }

    public void init(Context context) {
        this.context = context;
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override
    public void uncaughtException(Thread thread, Throwable throwable) {
        ThreadManager.getInstance().getThread().execute(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                Toast.makeText(context, "系统崩溃", Toast.LENGTH_SHORT).show();
                Looper.loop();
            }
        });

        AppManager.getInstance().removeAll();
        android.os.Process.killProcess(Process.myPid());
        System.exit(0);
    }
}
