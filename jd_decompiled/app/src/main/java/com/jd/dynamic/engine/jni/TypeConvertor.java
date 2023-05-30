package com.jd.dynamic.engine.jni;

/* loaded from: classes13.dex */
public class TypeConvertor {
    public static native long JSFunctionCall(long j2, long j3, long j4, long[] jArr);

    public static native long JSTransDataCall(long j2, long j3, long j4, String str, String str2, String str3, boolean z);

    public static native String JSTransDataCallToString(long j2, long j3, long j4, String str, String str2, String str3, boolean z);

    public static native boolean JSValue2Boolean(long j2, long j3);

    public static native double JSValue2Double(long j2, long j3);

    public static native String JSValue2String(long j2, long j3);

    public static native long JSValueGetProperty(long j2, long j3, String str);

    public static native void JSValueProtect(long j2, long j3);

    public static native void JSValueUnProtect(long j2, long j3);

    public static native String createJSONString(long j2, long j3);

    public static native boolean isJSBoolean(long j2, long j3);

    public static native boolean isJSFunction(long j2, long j3);

    public static native boolean isJSNull(long j2, long j3);

    public static native boolean isJSNumber(long j2, long j3);

    public static native boolean isJSObject(long j2, long j3);

    public static native boolean isJSString(long j2, long j3);

    public static native boolean isJSUndefined(long j2, long j3);

    public static native boolean isJSValueValid(long j2, long j3);

    public static native long makeBoolean(long j2, boolean z);

    public static native long makeFromJsonString(long j2, String str);

    public static native long makeNumber(long j2, double d);

    public static native long makeString(long j2, String str);

    public static native long makeUndefined(long j2);
}
