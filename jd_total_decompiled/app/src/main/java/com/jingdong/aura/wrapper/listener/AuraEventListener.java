package com.jingdong.aura.wrapper.listener;

/* loaded from: classes4.dex */
public interface AuraEventListener {
    void onCloseAura(String str, int i2, String str2, String str3);

    void onTrace(String str, int i2, String str2, String str3, String str4, String str5, Throwable th);

    void onTrace(String str, int i2, String str2, String str3, Throwable th);

    void onTrace(String str, String str2, int i2, String str3, String str4);
}
