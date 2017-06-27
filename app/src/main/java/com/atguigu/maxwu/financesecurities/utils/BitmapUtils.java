package com.atguigu.maxwu.financesecurities.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;

/**
 * 作者: WuKai
 * 时间: 2017/6/26
 * 邮箱: 648838173@qq.com
 * 作用:
 */

public class BitmapUtils {

    public static Bitmap getBitmap(Bitmap source) {
        int size = Math.min(source.getWidth(), source.getHeight());
        Bitmap bitmap = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
        Paint paint = new Paint();
        Canvas canvas = new Canvas(bitmap);

        BitmapShader shader = new BitmapShader(source, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP);
        int width = (source.getWidth() - size) / 2;
        int height = (source.getHeight() - size) / 2;
        if (width != 0 || height != 0) {
            Matrix matrix = new Matrix();
            matrix.setTranslate(width + 20, height + 20);
            shader.setLocalMatrix(matrix);
        }
        paint.setShader(shader);
        paint.setAntiAlias(true);
        float r = size / 2f;
        canvas.drawCircle(r, r, r, paint);
        source.recycle();
        return bitmap;
    }
}
