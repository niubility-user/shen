package com.tencent.tencentmap.lbssdk.service;

/* loaded from: classes9.dex */
public class TxRtkSvr {
    public static native int jni_init_txgpos();

    public static native int jni_set_ntrip_mode(int i2);

    public static native void jni_setlogger_path(String str);

    public static native void jni_setsol_path(String str);

    public static native void jni_settrace_path(int i2, String str);

    public static native int jni_stop_txgpos();

    public static native int jni_upd_android_data(int i2, byte[] bArr, a aVar, int i3);
}
