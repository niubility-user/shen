package com.jd.lib.babel.servicekit.imagekit;

import android.graphics.drawable.Drawable;
import android.view.View;

/* loaded from: classes13.dex */
public interface BabelImageLoadingListener {
    void onLoadingComplete(String str, View view, Drawable drawable);

    void onLoadingFailed(String str, View view);
}
