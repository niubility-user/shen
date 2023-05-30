package com.jingdong.app.util.image.listener;

import android.graphics.Bitmap;
import android.view.View;
import com.jingdong.app.util.image.assist.JDFailReason;

/* loaded from: classes4.dex */
public interface JDImageLoadingListener {
    void onLoadingCancelled(String str, View view);

    void onLoadingComplete(String str, View view, Bitmap bitmap);

    void onLoadingFailed(String str, View view, JDFailReason jDFailReason);

    void onLoadingStarted(String str, View view);
}
