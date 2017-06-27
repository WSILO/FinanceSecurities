package com.atguigu.maxwu.financesecurities.common;

import android.app.Activity;
import android.util.Log;

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

        for (int i = stack.size() - 1; i >= 0; i--) {

            Activity currentActivity = stack.get(i);

            if (currentActivity != null) {
                currentActivity.finish();
                stack.remove(i);
                Log.e("TAG", "removeAll--activity==" + currentActivity);
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
