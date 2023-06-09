package com.facebook.common.logging;

import com.facebook.infer.annotation.NullsafeStrict;

@NullsafeStrict
/* loaded from: classes.dex */
public interface LoggingDelegate {
    void d(String str, String str2);

    void d(String str, String str2, Throwable th);

    void e(String str, String str2);

    void e(String str, String str2, Throwable th);

    int getMinimumLoggingLevel();

    void i(String str, String str2);

    void i(String str, String str2, Throwable th);

    boolean isLoggable(int i2);

    void log(int i2, String str, String str2);

    void setMinimumLoggingLevel(int i2);

    void v(String str, String str2);

    void v(String str, String str2, Throwable th);

    void w(String str, String str2);

    void w(String str, String str2, Throwable th);

    void wtf(String str, String str2);

    void wtf(String str, String str2, Throwable th);
}
