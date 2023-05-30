package com.jd.mobile.image.utils;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.PictureDrawable;
import com.facebook.common.logging.FLog;
import com.facebook.fresco.animation.drawable.AnimatedDrawable2;
import com.jingdong.jdsdk.constant.JshopConst;
import com.jingdong.sdk.baseinfo.BaseInfo;

/* loaded from: classes17.dex */
public class b {
    public static int a() {
        int screenHeight = BaseInfo.getScreenHeight();
        if (screenHeight > 0) {
            return screenHeight;
        }
        return 320;
    }

    public static Bitmap b(Drawable drawable, int i2, int i3) {
        try {
            if (drawable instanceof PictureDrawable) {
                return null;
            }
            if (drawable instanceof BitmapDrawable) {
                return ((BitmapDrawable) drawable).getBitmap();
            }
            if (drawable instanceof AnimatedDrawable2) {
                return Bitmap.createBitmap(i2, i3, drawable.getOpacity() != -1 ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
            } else if (drawable != null) {
                return Bitmap.createBitmap(i2, i3, drawable.getOpacity() != -1 ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
            } else {
                return null;
            }
        } catch (OutOfMemoryError e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static Drawable c(Drawable drawable) {
        if (drawable != null) {
            int intrinsicWidth = drawable.getIntrinsicWidth();
            int intrinsicHeight = drawable.getIntrinsicHeight();
            if (intrinsicWidth > d() || intrinsicHeight > a()) {
                FLog.d("DrawableUtil", "too big drawable\uff1a" + intrinsicWidth + JshopConst.JSHOP_PROMOTIO_X + intrinsicHeight);
                return null;
            }
            return drawable;
        }
        return drawable;
    }

    public static int d() {
        int screenWidth = BaseInfo.getScreenWidth();
        if (screenWidth > 0) {
            return screenWidth;
        }
        return 240;
    }
}
