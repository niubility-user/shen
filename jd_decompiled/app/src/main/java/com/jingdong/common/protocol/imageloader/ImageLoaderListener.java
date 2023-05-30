package com.jingdong.common.protocol.imageloader;

import android.graphics.Bitmap;
import android.view.View;

/* loaded from: classes5.dex */
public interface ImageLoaderListener {
    void onLoadingCancelled(String str, View view);

    void onLoadingComplete(String str, View view, Bitmap bitmap);

    void onLoadingFailed(String str, View view, String str2);

    void onLoadingStarted(String str, View view);
}
