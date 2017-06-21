package com.atguigu.maxwu.financesecurities.utils;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.youth.banner.loader.ImageLoader;

/**
 * 作者: WuKai
 * 时间: 2017/6/21
 * 邮箱: 648838173@qq.com
 * 作用:
 */

public class PicassoImageLoader extends ImageLoader {

    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        Picasso.with(context).load((String) path).into( imageView);
    }

}
