package com.jingdong.service.callback;

import android.graphics.Bitmap;

/* loaded from: classes10.dex */
public interface WebPageListener {
    void onPageFinished(String str);

    void onPageStarted(String str, Bitmap bitmap);

    void onReceivedError(int i2, String str, String str2);

    void shouldOverrideUrlLoading(String str);
}
