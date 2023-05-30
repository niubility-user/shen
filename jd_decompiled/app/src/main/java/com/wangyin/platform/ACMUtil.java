package com.wangyin.platform;

import android.content.Context;
import com.jdjr.tools.JDJRLog;
import java.util.ArrayList;

/* loaded from: classes11.dex */
public class ACMUtil {
    static final int SO_LOAD_FAILED = 22222;
    static final String SO_LOAD_FAILED_STR = "22222";
    private static final String TAG = "ACMUtil";
    private static ACMUtil instance = null;
    static boolean isLoadLibrary = true;
    private static final Object lock;
    private final Context context;
    private final NativeACMUtil nativeAcmutil;

    static {
        try {
            System.loadLibrary("WangyinCryptoLib");
        } catch (Throwable unused) {
            isLoadLibrary = false;
        }
        try {
            System.loadLibrary("AntiCheat");
        } catch (Throwable unused2) {
            isLoadLibrary = false;
        }
        lock = new Object();
    }

    private ACMUtil(Context context) {
        this.context = context;
        this.nativeAcmutil = new NativeACMUtil(context);
    }

    public static ACMUtil newInstance(Context context) {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new ACMUtil(context);
                }
            }
        }
        return instance;
    }

    public byte[] GetACMsg(Context context, String str, String str2, String str3, String str4, ArrayList arrayList) {
        if (isLoadLibrary) {
            JDJRLog.i(TAG, "JDPin in ACMsg = " + str4);
            return NativeACMUtil.NativeGetACMsg(context, str, str2, str3, str4, arrayList);
        }
        return SO_LOAD_FAILED_STR.getBytes();
    }

    public int InitACMsg() {
        if (isLoadLibrary) {
            return NativeACMUtil.NativeInitACMsg();
        }
        return 22222;
    }

    public boolean NativePerformAccessibilityAction(int i2, Object obj, int i3, Object obj2) {
        if (isLoadLibrary) {
            return NativeACMUtil.NativePerformAccessibilityAction(i2, obj, i3, obj2);
        }
        return false;
    }

    public byte[] NativeSetCheckAccessibility(Object obj, boolean z, String str, String str2, Object obj2) {
        if (isLoadLibrary) {
            return NativeACMUtil.NativeSetCheckAccessibility(obj, z, str, str2, obj2);
        }
        return SO_LOAD_FAILED_STR.getBytes();
    }

    public byte[] NativeonInitializeAccessibilityNodeInfo(int i2, Object obj, Object obj2) {
        if (isLoadLibrary) {
            return NativeACMUtil.NativeonInitializeAccessibilityNodeInfo(i2, obj, obj2);
        }
        return SO_LOAD_FAILED_STR.getBytes();
    }

    public byte[] checkCameraHook(Context context, ArrayList arrayList, String str, String str2, String str3, String str4) {
        if (isLoadLibrary) {
            return NativeACMUtil.NativeCheckCameraHook(context, arrayList, str, str2, str3, str4);
        }
        return SO_LOAD_FAILED_STR.getBytes();
    }

    public byte[] getAccessFlags(Object obj, int i2) {
        if (isLoadLibrary) {
            return NativeACMUtil.NativeGetAccessFlags(obj, i2);
        }
        return SO_LOAD_FAILED_STR.getBytes();
    }

    public byte[] getAccessFlagsOffset(Object obj, int i2, int i3, int i4) {
        if (isLoadLibrary) {
            return NativeACMUtil.NativeGetAccessFlagsOffset(obj, i2, i3, i4);
        }
        return SO_LOAD_FAILED_STR.getBytes();
    }

    Context getContext() {
        return this.context;
    }

    public String getIntegrityCheckData(Context context, boolean z) {
        return isLoadLibrary ? NativeACMUtil.NativeGetIntegrityCheckData(context, z) : SO_LOAD_FAILED_STR;
    }

    public byte[] methodSize(Object obj, Object obj2) {
        if (isLoadLibrary) {
            return NativeACMUtil.NativeMethodSize(obj, obj2);
        }
        return SO_LOAD_FAILED_STR.getBytes();
    }
}
