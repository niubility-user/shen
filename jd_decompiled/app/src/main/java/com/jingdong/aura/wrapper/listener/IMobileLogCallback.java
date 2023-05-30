package com.jingdong.aura.wrapper.listener;

/* loaded from: classes.dex */
public interface IMobileLogCallback {
    void debug(String str, String str2);

    void error(String str, String str2);

    void error(String str, String str2, Throwable th);

    void error(String str, StringBuffer stringBuffer, Throwable th);

    void fatal(String str, String str2);

    void fatal(String str, String str2, Throwable th);

    void fatal(String str, StringBuffer stringBuffer, Throwable th);

    void info(String str, String str2);

    void verbose(String str, String str2);

    void warn(String str, String str2);

    void warn(String str, String str2, Throwable th);

    void warn(String str, StringBuffer stringBuffer, Throwable th);
}
