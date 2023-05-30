package com.tencent.smtt.export.external.interfaces;

/* loaded from: classes9.dex */
public interface HttpAuthHandler {
    void cancel();

    void proceed(String str, String str2);

    boolean useHttpAuthUsernamePassword();
}
