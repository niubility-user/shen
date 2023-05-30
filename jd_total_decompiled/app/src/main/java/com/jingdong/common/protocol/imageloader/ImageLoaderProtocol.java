package com.jingdong.common.protocol.imageloader;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;
import com.jingdong.common.protocol.imageloader.AbstractImageOptionsHolder;

/* loaded from: classes5.dex */
public interface ImageLoaderProtocol<DisplayOptionsHolder extends AbstractImageOptionsHolder> {
    void cancelDisplayTask(ImageView imageView);

    void displayImage(String str, ImageView imageView);

    void displayImage(String str, ImageView imageView, DisplayOptionsHolder displayoptionsholder);

    void displayImage(String str, ImageView imageView, DisplayOptionsHolder displayoptionsholder, ImageLoaderListener imageLoaderListener);

    void displayImage(String str, ImageView imageView, DisplayOptionsHolder displayoptionsholder, ImageLoaderListener imageLoaderListener, ImageLoadingProgressListener imageLoadingProgressListener);

    void displayImage(String str, ImageView imageView, DisplayOptionsHolder displayoptionsholder, boolean z);

    void displayImage(String str, ImageView imageView, DisplayOptionsHolder displayoptionsholder, boolean z, ImageLoaderListener imageLoaderListener, ImageLoadingProgressListener imageLoadingProgressListener);

    void displayImage(String str, ImageView imageView, ImageLoaderListener imageLoaderListener);

    Bitmap getBitmap(Context context, int i2);

    Bitmap getBitmapWithJpg16(Context context, int i2);
}
