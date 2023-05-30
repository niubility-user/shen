package com.jd.lib.cashier.sdk.core.utils;

import android.graphics.Bitmap;
import android.text.TextUtils;
import android.widget.ImageView;
import com.jd.cashier.app.jdlibcutter.initialize.DependInitializer;
import com.jd.cashier.app.jdlibcutter.protocol.imageloader.IImageLoader;
import com.jd.cashier.app.jdlibcutter.protocol.imageloader.ImageLoaderOptions;
import com.jd.cashier.app.jdlibcutter.protocol.imageloader.ImageLoadingListener;

/* loaded from: classes14.dex */
public class k {

    /* loaded from: classes14.dex */
    class a implements ImageLoadingListener {
        final /* synthetic */ ImageLoadingListener a;
        final /* synthetic */ ImageView b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ int f3093c;

        a(ImageLoadingListener imageLoadingListener, ImageView imageView, int i2) {
            this.a = imageLoadingListener;
            this.b = imageView;
            this.f3093c = i2;
        }

        @Override // com.jd.cashier.app.jdlibcutter.protocol.imageloader.ImageLoadingListener
        public void onLoadingCancelled(String str) {
            ImageLoadingListener imageLoadingListener = this.a;
            if (imageLoadingListener != null) {
                imageLoadingListener.onLoadingCancelled(str);
            }
        }

        @Override // com.jd.cashier.app.jdlibcutter.protocol.imageloader.ImageLoadingListener
        public void onLoadingComplete(String str, Bitmap bitmap) {
            this.b.setTag(this.f3093c, str);
            ImageLoadingListener imageLoadingListener = this.a;
            if (imageLoadingListener != null) {
                imageLoadingListener.onLoadingComplete(str, bitmap);
            }
        }

        @Override // com.jd.cashier.app.jdlibcutter.protocol.imageloader.ImageLoadingListener
        public void onLoadingFailed(String str) {
            ImageLoadingListener imageLoadingListener = this.a;
            if (imageLoadingListener != null) {
                imageLoadingListener.onLoadingFailed(str);
            }
        }

        @Override // com.jd.cashier.app.jdlibcutter.protocol.imageloader.ImageLoadingListener
        public void onLoadingStarted(String str) {
            ImageLoadingListener imageLoadingListener = this.a;
            if (imageLoadingListener != null) {
                imageLoadingListener.onLoadingStarted(str);
            }
        }
    }

    public static void a(int i2, String str, ImageView imageView, ImageLoaderOptions imageLoaderOptions, boolean z, ImageLoadingListener imageLoadingListener) {
        if (imageView == null) {
            return;
        }
        Object tag = imageView.getTag(i2);
        if ((tag instanceof CharSequence) && imageView.getDrawable() != null && TextUtils.equals((CharSequence) tag, str)) {
            return;
        }
        if (imageLoaderOptions != null) {
            imageLoaderOptions.showDefault = z;
        }
        imageView.setTag(i2, null);
        IImageLoader imageLoader = DependInitializer.getImageLoader();
        if (imageLoader != null) {
            imageLoader.displayImage(str, imageView, imageLoaderOptions, new a(imageLoadingListener, imageView, i2));
        }
    }

    public static void b(ImageView imageView, String str, ImageLoaderOptions imageLoaderOptions, boolean z) {
        if (TextUtils.isEmpty(str)) {
            if (z) {
                j0.b(imageView);
                return;
            } else {
                j0.c(imageView);
                return;
            }
        }
        j0.d(imageView);
        IImageLoader imageLoader = DependInitializer.getImageLoader();
        if (imageLoader != null) {
            imageLoader.displayImage(str, imageView, imageLoaderOptions, null);
        }
    }

    public static void c(ImageView imageView, String str) {
        b(imageView, str, null, true);
    }
}
