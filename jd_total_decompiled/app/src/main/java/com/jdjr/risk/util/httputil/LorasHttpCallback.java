package com.jdjr.risk.util.httputil;

/* loaded from: classes18.dex */
public interface LorasHttpCallback {
    void onFailInCurentThread(int i2, String str);

    void onFailInNetThread(int i2, String str);

    void onSuccess(String str);
}
