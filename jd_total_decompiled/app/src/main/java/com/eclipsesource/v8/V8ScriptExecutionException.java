package com.eclipsesource.v8;

/* loaded from: classes.dex */
public class V8ScriptExecutionException extends V8ScriptException {
    V8ScriptExecutionException(String str, int i2, String str2, String str3, int i3, int i4, String str4) {
        this(str, i2, str2, str3, i3, i4, str4, null);
    }

    V8ScriptExecutionException(String str, int i2, String str2, String str3, int i3, int i4, String str4, Throwable th) {
        super(str, i2, str2, str3, i3, i4, str4, th);
    }
}
