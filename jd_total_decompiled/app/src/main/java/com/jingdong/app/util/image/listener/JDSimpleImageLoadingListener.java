package com.jingdong.app.util.image.listener;

import android.graphics.Bitmap;
import android.view.View;
import com.jingdong.app.util.image.assist.JDFailReason;

/* loaded from: classes4.dex */
public class JDSimpleImageLoadingListener implements JDImageLoadingListener {
    @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
    public void onLoadingCancelled(String str, View view) {
    }

    @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
    public void onLoadingComplete(String str, View view, Bitmap bitmap) {
    }

    @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
    public void onLoadingFailed(String str, View view, JDFailReason jDFailReason) {
    }

    @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
    public void onLoadingStarted(String str, View view) {
    }
}
