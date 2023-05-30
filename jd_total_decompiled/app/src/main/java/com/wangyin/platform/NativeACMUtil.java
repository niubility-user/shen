package com.wangyin.platform;

import android.content.Context;
import java.util.ArrayList;

/* loaded from: classes11.dex */
public class NativeACMUtil {
    private final Context context;

    public NativeACMUtil(Context context) {
        this.context = context;
    }

    public static native byte[] NativeCheckCameraHook(Context context, ArrayList arrayList, String str, String str2, String str3, String str4);

    public static native byte[] NativeGetACMsg(Context context, String str, String str2, String str3, String str4, ArrayList arrayList);

    public static native byte[] NativeGetAccessFlags(Object obj, int i2);

    public static native byte[] NativeGetAccessFlagsOffset(Object obj, int i2, int i3, int i4);

    public static native String NativeGetIntegrityCheckData(Context context, boolean z);

    public static native int NativeInitACMsg();

    public static native byte[] NativeMethodSize(Object obj, Object obj2);

    public static native boolean NativePerformAccessibilityAction(int i2, Object obj, int i3, Object obj2);

    public static native byte[] NativeSetCheckAccessibility(Object obj, boolean z, String str, String str2, Object obj2);

    public static native byte[] NativeonInitializeAccessibilityNodeInfo(int i2, Object obj, Object obj2);

    Context getContext() {
        return this.context;
    }
}
