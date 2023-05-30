package com.jingdong.sdk.platform.lib.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import com.jingdong.jdsdk.utils.cache.GlobalImageCache;
import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes10.dex */
public class BitmapUtil {
    public static Bitmap drawableToBitmap(Drawable drawable) {
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        Bitmap createBitmap = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, drawable.getOpacity() != -1 ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(createBitmap);
        drawable.setBounds(0, 0, intrinsicWidth, intrinsicHeight);
        drawable.draw(canvas);
        return createBitmap;
    }

    public static Drawable drawableToDrawable16(Drawable drawable) {
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        Bitmap createBitmap = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, Bitmap.Config.ARGB_4444);
        Canvas canvas = new Canvas(createBitmap);
        drawable.setBounds(0, 0, intrinsicWidth, intrinsicHeight);
        drawable.draw(canvas);
        return new BitmapDrawable(createBitmap);
    }

    public static Bitmap getRoundedCornerBitmap(Drawable drawable, float f2) {
        return getRoundedCornerBitmap(drawableToBitmap(drawable), f2);
    }

    public static Bitmap toRoundBitmap(Bitmap bitmap, int i2, int i3) {
        float f2;
        float f3;
        float f4;
        float f5;
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int i4 = width > height ? height : width;
        if (OKLog.D) {
            OKLog.d("ImageUile", "toRoundBitmap width-->> " + width);
            OKLog.d("ImageUile", "toRoundBitmap height-->> " + height);
            OKLog.d("ImageUile", "toRoundBitmap dw-->> " + i4);
            OKLog.d("ImageUile", "toRoundBitmap dh-->> " + i4);
        }
        if (i4 < width || i4 < height) {
            try {
                bitmap = Bitmap.createScaledBitmap(bitmap, i4, i4, true);
            } catch (Throwable unused) {
                GlobalImageCache.getLruBitmapCache().clean();
                try {
                    bitmap = Bitmap.createScaledBitmap(bitmap, i4, i4, true);
                } catch (Throwable th) {
                    if (OKLog.E) {
                        OKLog.e("ImageUtil", " -->> ", th);
                    }
                }
            }
        }
        int i5 = width > i4 ? i4 : width;
        if (height <= i4) {
            i4 = height;
        }
        if (width <= height) {
            f2 = i5 / 2;
            f4 = i5;
            f5 = f4;
            f3 = 0.0f;
        } else {
            f2 = i4 / 2;
            f3 = (i5 - i4) / 2;
            f4 = i5 - f3;
            f5 = i4;
        }
        Bitmap createBitmap = Bitmap.createBitmap(i5, i4, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint();
        Rect rect = new Rect((int) f3, (int) 0.0f, (int) f4, (int) f5);
        Rect rect2 = new Rect((int) 0.0f, (int) 0.0f, (int) f5, (int) f5);
        RectF rectF = new RectF(rect2);
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(-12434878);
        canvas.drawRoundRect(rectF, f2, f2, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect2, paint);
        return createBitmap;
    }

    public static Bitmap getRoundedCornerBitmap(Bitmap bitmap, float f2) {
        Bitmap createBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint();
        Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        RectF rectF = new RectF(rect);
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(-12434878);
        canvas.drawRoundRect(rectF, f2, f2, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return createBitmap;
    }
}
