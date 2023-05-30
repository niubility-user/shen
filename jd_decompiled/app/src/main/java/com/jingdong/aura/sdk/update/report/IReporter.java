package com.jingdong.aura.sdk.update.report;

/* loaded from: classes4.dex */
public interface IReporter {
    void onException(String str, int i2, String str2, String str3, Throwable th);

    void onTrace(String str, String str2, int i2, String str3, String str4);

    void onTrace(String str, String str2, String str3);
}
