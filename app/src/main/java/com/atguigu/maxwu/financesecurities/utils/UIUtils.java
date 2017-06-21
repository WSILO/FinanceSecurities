package com.atguigu.maxwu.financesecurities.utils;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.atguigu.maxwu.financesecurities.common.MyApplication;

/**
 * 作者: WuKai
 * 时间: 2017/6/21
 * 邮箱: 648838173@qq.com
 * 作用:
 */

public class UIUtils {


    public static void setText(View view, int id, String value){
        String versionName = String.format(getString(id), value);
        if(view instanceof TextView) {
            ((TextView)view).setText(versionName);
        }
    }
    public static String getString(int id) {
        return getContext().getResources().getString(id);
    }

    private static Context getContext() {
        return MyApplication.getContext();
    }
}
