package com.jingdong.manto.sdk.api;

import com.jingdong.manto.sdk.IMantoSdkBase;

/* loaded from: classes16.dex */
public interface IMantoLog extends IMantoSdkBase {
    void d(String str, String str2);

    void d(String str, String str2, Throwable th);

    void d(String str, Throwable th);

    void d(String str, Object... objArr);

    void e(String str, String str2);

    void e(String str, String str2, Throwable th);

    void e(String str, Throwable th);

    void e(String str, Object... objArr);

    void i(String str, String str2);

    void i(String str, String str2, Throwable th);

    void i(String str, Throwable th);

    void i(String str, Object... objArr);

    void json(String str, String str2);

    void v(String str, String str2);

    void v(String str, String str2, Throwable th);

    void v(String str, Throwable th);

    void v(String str, Object... objArr);

    void w(String str, String str2);

    void w(String str, String str2, Throwable th);

    void w(String str, Throwable th);

    void w(String str, Object... objArr);
}
