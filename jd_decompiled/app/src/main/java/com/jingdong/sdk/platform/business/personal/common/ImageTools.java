package com.jingdong.sdk.platform.business.personal.common;

import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.app.util.image.assist.JDFailReason;
import com.jingdong.app.util.image.listener.JDImageLoadingListener;
import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.sdk.oklog.OKLog;
import com.jingdong.sdk.platform.business.personal.R;

/* loaded from: classes10.dex */
public class ImageTools {
    private static final String TAG = "ImageTools";

    public static void displayImage(String str, ImageView imageView, JDDisplayImageOptions jDDisplayImageOptions) {
        displayImage(str, imageView, jDDisplayImageOptions, true, null);
    }

    public static void displayImage(final String str, ImageView imageView, JDDisplayImageOptions jDDisplayImageOptions, boolean z, final JDImageLoadingListener jDImageLoadingListener) {
        if (imageView == null) {
            return;
        }
        Object tag = imageView.getTag(R.id.personal_image_tag);
        if (tag != null && (tag instanceof CharSequence) && imageView.getDrawable() != null) {
            if (TextUtils.equals((CharSequence) tag, str)) {
                if (OKLog.D) {
                    OKLog.d(TAG, "the same Tag:" + str);
                    return;
                }
                return;
            } else if (OKLog.D) {
                OKLog.d(TAG, "the Tag Not Same:" + str);
            }
        }
        JDImageUtils.displayImage(str, imageView, jDDisplayImageOptions, z, new JDImageLoadingListener() { // from class: com.jingdong.sdk.platform.business.personal.common.ImageTools.1
            @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
            public void onLoadingCancelled(String str2, View view) {
                JDImageLoadingListener jDImageLoadingListener2 = jDImageLoadingListener;
                if (jDImageLoadingListener2 != null) {
                    jDImageLoadingListener2.onLoadingCancelled(str2, view);
                }
            }

            @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
            public void onLoadingComplete(String str2, View view, Bitmap bitmap) {
                if (view != null) {
                    view.setTag(R.id.personal_image_tag, str);
                    view.setTag(R.id.personal_4G_not_load_picture_tag, str);
                }
                if (OKLog.D) {
                    OKLog.d(ImageTools.TAG, "onLoadingComplete" + str);
                }
                JDImageLoadingListener jDImageLoadingListener2 = jDImageLoadingListener;
                if (jDImageLoadingListener2 != null) {
                    jDImageLoadingListener2.onLoadingComplete(str2, view, bitmap);
                }
            }

            @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
            public void onLoadingFailed(String str2, View view, JDFailReason jDFailReason) {
                JDImageLoadingListener jDImageLoadingListener2 = jDImageLoadingListener;
                if (jDImageLoadingListener2 != null) {
                    jDImageLoadingListener2.onLoadingFailed(str2, view, jDFailReason);
                }
            }

            @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
            public void onLoadingStarted(String str2, View view) {
                if (view != null) {
                    view.setTag(R.id.personal_image_tag, "");
                }
                JDImageLoadingListener jDImageLoadingListener2 = jDImageLoadingListener;
                if (jDImageLoadingListener2 != null) {
                    jDImageLoadingListener2.onLoadingStarted(str2, view);
                }
            }
        }, null);
    }
}
