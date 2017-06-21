package com.atguigu.maxwu.financesecurities.common;

import android.app.Activity;

import java.util.Stack;

/**
 * 作者: WuKai
 * 时间: 2017/6/21
 * 邮箱: 648838173@qq.com
 * 作用:
 */
public class AppManager {

    private static AppManager appManager = new AppManager();
    private Stack<Activity> stack = new Stack<>();

    private AppManager() {
    }

    public static AppManager getInstance() {
        return appManager;
    }

    public void removeAll() {
        for (Activity activity : stack) {
            if (activity != null) {
                activity.finish();
                stack.remove(activity);
            }
        }
    }

    public void removeActivity(Activity a) {
        for (Activity activity : stack) {
            if (activity == a) {
                activity.finish();
                stack.remove(activity);
                break;
            }
        }
    }

    public void addActivity(Activity activity) {
        if (activity != null) {
            stack.add(activity);
        }
    }

}
