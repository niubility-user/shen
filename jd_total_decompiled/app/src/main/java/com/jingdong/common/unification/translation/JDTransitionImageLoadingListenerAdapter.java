package com.jingdong.common.unification.translation;

import android.graphics.Bitmap;
import android.view.View;
import com.jingdong.app.util.image.assist.JDFailReason;
import com.jingdong.app.util.image.listener.JDImageLoadingListener;

/* loaded from: classes6.dex */
public class JDTransitionImageLoadingListenerAdapter implements JDImageLoadingListener {
    public void onLoadFailed() {
    }

    public void onLoadFinish() {
    }

    @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
    public void onLoadingCancelled(String str, View view) {
        onLoadFailed();
    }

    @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
    public void onLoadingComplete(String str, View view, Bitmap bitmap) {
        onLoadFinish();
    }

    @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
    public void onLoadingFailed(String str, View view, JDFailReason jDFailReason) {
        onLoadFailed();
    }

    @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
    public void onLoadingStarted(String str, View view) {
    }
}
