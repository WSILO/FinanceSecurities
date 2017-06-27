package com.atguigu.maxwu.financesecurities.utils;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import java.io.File;

import cn.finalteam.galleryfinal.ImageLoader;
import cn.finalteam.galleryfinal.widget.GFImageView;

/**
 * 作者: WuKai
 * 时间: 2017/6/27
 * 邮箱: 648838173@qq.com
 * 作用:
 */
public class PicassoGalleryImageLoader implements ImageLoader {
    private Bitmap.Config config;

    public PicassoGalleryImageLoader(Bitmap.Config config) {
        this.config = config;
    }

    public PicassoGalleryImageLoader() {
        this(Bitmap.Config.RGB_565);
    }

    @Override
    public void displayImage(Activity activity, String path, GFImageView imageView, Drawable defaultDrawable, int width, int height) {
        Picasso.with(activity)
                .load(new File(path))
                .placeholder(defaultDrawable)
                .error(defaultDrawable)
                .config(config)
                .resize(width,height)
                .centerInside()
                .memoryPolicy(MemoryPolicy.NO_CACHE,MemoryPolicy.NO_CACHE)
                .into(imageView);
    }

    @Override
    public void clearMemoryCache() {

    }
}
