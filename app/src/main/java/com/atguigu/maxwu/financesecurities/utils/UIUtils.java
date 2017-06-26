package com.atguigu.maxwu.financesecurities.utils;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.atguigu.maxwu.financesecurities.common.MyApplication;

import java.util.Random;

/**
 * 作者: WuKai
 * 时间: 2017/6/21
 * 邮箱: 648838173@qq.com
 * 作用:
 */

public class UIUtils {


    private static Context getContext() {
        return MyApplication.getContext();
    }

    public static View inflate(int viewId) {
        return View.inflate(getContext(), viewId, null);
    }

    public static void setText(View view, int id, String value) {
        String versionName = String.format(getString(id), value);
        if (view instanceof TextView) {
            ((TextView) view).setText(versionName);
        }
    }

    public static String getString(int id) {
        return getContext().getResources().getString(id);
    }


    public static void runOnUIThread(Runnable runnable) {
        if (MyApplication.getPid() == android.os.Process.myTid()) {
            runnable.run();
        } else {
            MyApplication.getHandler().post(runnable);
        }
    }
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
    public static int setColor() {
        Random random = new Random();
        int r = random.nextInt(100) + 100;
        int g = random.nextInt(100) + 100;
        int b = random.nextInt(100) + 100;
        return Color.rgb(r, g, b);
    }
}
