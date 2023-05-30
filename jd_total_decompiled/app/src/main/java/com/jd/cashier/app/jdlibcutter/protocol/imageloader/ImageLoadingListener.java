package com.jd.cashier.app.jdlibcutter.protocol.imageloader;

import android.graphics.Bitmap;

/* loaded from: classes13.dex */
public interface ImageLoadingListener {
    void onLoadingCancelled(String str);

    void onLoadingComplete(String str, Bitmap bitmap);

    void onLoadingFailed(String str);

    void onLoadingStarted(String str);
}
