package com.atguigu.maxwu.financesecurities.common;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

import com.atguigu.maxwu.financesecurities.utils.PicassoGalleryImageLoader;

import cn.finalteam.galleryfinal.CoreConfig;
import cn.finalteam.galleryfinal.FunctionConfig;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.ThemeConfig;

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
        initGallery();
    }

    public static Context getContext() {
        return context;
    }
    private void initGallery() {
        ThemeConfig theme = new ThemeConfig.Builder()
                .build();
        //配置功能
        FunctionConfig functionConfig = new FunctionConfig.Builder()
                .setEnableCamera(true)
                .setEnableEdit(true)
                .setEnableCrop(true)
                .setEnableRotate(true)
                .setCropSquare(true)
                .setEnablePreview(true)
                .build();

        //配置imageloader
        PicassoGalleryImageLoader imageloader = new PicassoGalleryImageLoader();
        CoreConfig coreConfig =
                new CoreConfig.Builder(this, imageloader, theme)
                        .setFunctionConfig(functionConfig).build();
        GalleryFinal.init(coreConfig);

    }
}
