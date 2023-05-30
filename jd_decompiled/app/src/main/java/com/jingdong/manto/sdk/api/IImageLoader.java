package com.jingdong.manto.sdk.api;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;
import com.jingdong.manto.sdk.IMantoSdkBase;

/* loaded from: classes16.dex */
public interface IImageLoader extends IMantoSdkBase {

    /* loaded from: classes16.dex */
    public interface ImageLoaderCallback {
        void onFail();

        void onSuccess(Bitmap bitmap);
    }

    void loadImage(Context context, String str, ImageLoaderCallback imageLoaderCallback);

    void loadImage(ImageView imageView, String str);
}
