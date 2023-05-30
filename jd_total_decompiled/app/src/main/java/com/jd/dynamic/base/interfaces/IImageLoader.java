package com.jd.dynamic.base.interfaces;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import java.io.File;

/* loaded from: classes13.dex */
public interface IImageLoader {

    /* loaded from: classes13.dex */
    public interface ImageDisplayListener {
        void onCancel();

        void onFailure(Throwable th);

        void onSuccess(ImageInfos imageInfos);
    }

    /* loaded from: classes13.dex */
    public static class ImageInfos {
        public int height;
        public int width;
    }

    /* loaded from: classes13.dex */
    public interface ImageRequestListener<T> {
        void onCancel();

        void onFailure(Throwable th);

        void onSuccess(T t);
    }

    void displayImage(ImageView imageView, String str, int i2, ImageDisplayListener imageDisplayListener);

    File getCacheImageFile(String str);

    Drawable getDefaultPlaceHolder();

    void loadImage(String str, ImageRequestListener<Bitmap> imageRequestListener);

    void loadNineImage(String str, ImageRequestListener<Drawable> imageRequestListener);
}
