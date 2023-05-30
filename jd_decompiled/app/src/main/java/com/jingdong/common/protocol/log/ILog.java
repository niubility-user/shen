package com.jingdong.common.protocol.log;

/* loaded from: classes5.dex */
public interface ILog {
    void d(String str, String str2);

    void d(String str, String str2, Throwable th);

    void e(String str, String str2);

    void e(String str, String str2, Throwable th);

    void i(String str, String str2);

    void i(String str, String str2, Throwable th);

    void s(int i2);

    void s(String str);

    void s(String str, String str2);

    void s(String str, String str2, Throwable th);

    void v(String str, String str2);

    void v(String str, String str2, Throwable th);

    void w(String str, String str2);

    void w(String str, String str2, Throwable th);

    void w(String str, Throwable th);
}
