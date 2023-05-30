package com.jingdong.app.mall.home.n.h;

import android.graphics.Bitmap;
import android.widget.ImageView;
import com.jingdong.app.mall.home.R;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.app.util.image.listener.JDImageLoadingListener;
import com.jingdong.common.utils.JDImageUtils;

/* loaded from: classes4.dex */
public class d {
    private static JDDisplayImageOptions a = com.jingdong.app.mall.home.floor.ctrl.f.a().resetViewBeforeLoading(false);
    private static final int b = R.id.image_last_url;

    public static void a(ImageView imageView, String str) {
        b(imageView, str, null, null, true);
    }

    public static void b(ImageView imageView, String str, JDImageLoadingListener jDImageLoadingListener, JDDisplayImageOptions jDDisplayImageOptions, boolean z) {
        if (imageView == null) {
            return;
        }
        int i2 = b;
        Object tag = imageView.getTag(i2);
        if (str != null && str.equals(tag)) {
            Object tag2 = imageView.getTag(JDImageUtils.STATUS_TAG);
            if (tag2 != null && tag2.equals(1)) {
                return;
            }
            if (tag2 != null && tag2.equals(2)) {
                if (jDImageLoadingListener != null) {
                    jDImageLoadingListener.onLoadingComplete(str, imageView, null);
                    return;
                }
                return;
            }
        }
        if (jDDisplayImageOptions == null) {
            try {
                jDDisplayImageOptions = a;
            } catch (Exception e2) {
                e2.printStackTrace();
                return;
            }
        }
        JDDisplayImageOptions jDDisplayImageOptions2 = jDDisplayImageOptions;
        jDDisplayImageOptions2.bitmapConfig(Bitmap.Config.ARGB_8888);
        com.jingdong.app.mall.home.floor.ctrl.f.h(str, imageView, jDDisplayImageOptions2, z, jDImageLoadingListener, null);
        imageView.setTag(i2, str);
    }
}
