package com.jd.manto.center.k;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.text.TextUtils;
import android.view.View;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.app.util.image.assist.JDFailReason;
import com.jingdong.app.util.image.listener.JDImageLoadingListener;
import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.remoteimage.RemoteImageManager;
import java.lang.ref.WeakReference;

/* loaded from: classes17.dex */
public class d {

    /* loaded from: classes17.dex */
    class a extends b {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ WeakReference f6546g;

        a(WeakReference weakReference) {
            this.f6546g = weakReference;
        }

        @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
        public void onLoadingComplete(String str, View view, Bitmap bitmap) {
            View view2 = (View) this.f6546g.get();
            if (view2 == null || bitmap == null) {
                return;
            }
            view2.setBackground(new BitmapDrawable(view2.getResources(), bitmap));
        }
    }

    /* loaded from: classes17.dex */
    public static abstract class b implements JDImageLoadingListener {
        @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
        public void onLoadingCancelled(String str, View view) {
        }

        @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
        public void onLoadingFailed(String str, View view, JDFailReason jDFailReason) {
        }

        @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
        public void onLoadingStarted(String str, View view) {
        }
    }

    static {
        JDDisplayImageOptions.createSimple().setPlaceholder(18);
    }

    public static void a(String str, JDImageLoadingListener jDImageLoadingListener) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        JDImageUtils.loadImage(d(str), jDImageLoadingListener);
    }

    static String b(String str) {
        return c("101", str);
    }

    static String c(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return null;
        }
        return str + CartConstant.KEY_YB_INFO_LINK + str2;
    }

    public static String d(String str) {
        return RemoteImageManager.getImageUrlById(b(str), RemoteImageManager.XHDPI);
    }

    public static void e(View view, String str) {
        if (view == null || TextUtils.isEmpty(str)) {
            return;
        }
        a(str, new a(new WeakReference(view)));
    }
}
