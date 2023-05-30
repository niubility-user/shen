package com.jingdong.service.callback;

/* loaded from: classes10.dex */
public interface BiometricTokenCallback {
    void onFailInCurrentThread(int i2, String str);

    void onFailInNetThread(int i2, String str);

    void onSuccess(String str);
}
