package com.jingdong.app.mall.home.floor.ctrl;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.widget.ImageView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.jd.mobile.image.JDImageLoader;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.app.util.image.listener.JDImageLoadingListener;
import com.jingdong.app.util.image.listener.JDImageLoadingProgressListener;
import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.sdk.platform.business.personal.R2;

/* loaded from: classes4.dex */
public class f {
    private static final JDDisplayImageOptions a = a().setResizeOptions(new ResizeOptions(R2.attr.iv_id, R2.color.BW_70_dark, 2500.0f)).resetViewBeforeLoading(true).isScale(false);

    public static JDDisplayImageOptions a() {
        JDDisplayImageOptions jDDisplayImageOptions = new JDDisplayImageOptions();
        jDDisplayImageOptions.setReferer("Image_JDAppHome");
        return jDDisplayImageOptions;
    }

    public static Bitmap b(byte[] bArr, int i2, int i3, BitmapFactory.Options options) {
        return BitmapFactory.decodeByteArray(bArr, i2, i3, options);
    }

    public static Bitmap c(Resources resources, int i2) {
        return BitmapFactory.decodeResource(resources, i2);
    }

    public static void d(String str, ImageView imageView) {
        g(str, imageView, null, null, null);
    }

    public static void e(String str, ImageView imageView, JDDisplayImageOptions jDDisplayImageOptions) {
        g(str, imageView, jDDisplayImageOptions, null, null);
    }

    public static void f(String str, ImageView imageView, JDDisplayImageOptions jDDisplayImageOptions, JDImageLoadingListener jDImageLoadingListener) {
        g(str, imageView, jDDisplayImageOptions, jDImageLoadingListener, null);
    }

    public static void g(String str, ImageView imageView, JDDisplayImageOptions jDDisplayImageOptions, JDImageLoadingListener jDImageLoadingListener, JDImageLoadingProgressListener jDImageLoadingProgressListener) {
        h(str, imageView, jDDisplayImageOptions, true, jDImageLoadingListener, jDImageLoadingProgressListener);
    }

    public static void h(String str, ImageView imageView, JDDisplayImageOptions jDDisplayImageOptions, boolean z, JDImageLoadingListener jDImageLoadingListener, JDImageLoadingProgressListener jDImageLoadingProgressListener) {
        if (jDDisplayImageOptions == null) {
            jDDisplayImageOptions = e.f9402h;
        }
        JDImageUtils.displayImage(com.jingdong.app.mall.home.m.a.b(str), imageView, jDDisplayImageOptions, z, jDImageLoadingListener, jDImageLoadingProgressListener);
    }

    public static void i(String str, com.jingdong.app.mall.home.t.a aVar) {
        JDDisplayImageOptions jDDisplayImageOptions = a;
        jDDisplayImageOptions.bitmapConfig(Bitmap.Config.ARGB_8888);
        j(str, jDDisplayImageOptions, aVar);
    }

    public static void j(String str, JDDisplayImageOptions jDDisplayImageOptions, com.jingdong.app.mall.home.t.a aVar) {
        if (TextUtils.isEmpty(str)) {
            if (aVar != null) {
                aVar.onBitmapWithUiNull(null);
                return;
            }
            return;
        }
        if (jDDisplayImageOptions == null) {
            jDDisplayImageOptions = a;
            jDDisplayImageOptions.bitmapConfig(Bitmap.Config.ARGB_8888);
        }
        JDImageLoader.getBitmap(str, jDDisplayImageOptions, aVar);
    }
}
