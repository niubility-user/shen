package com.jingdong.common.jdreactFramework.utils;

import android.os.Build;
import android.text.TextUtils;
import com.facebook.soloader.SoLoader;
import com.jingdong.common.jdreactFramework.JDReactHelper;
import com.jingdong.common.web.managers.PerformanceManager;

/* loaded from: classes5.dex */
public class CpuUtils {
    private static final String TAG = "CpuUtils";
    private static boolean deviceSupportReactNativeLib;

    static {
        try {
            SoLoader.init(JDReactHelper.newInstance().getApplicationContext(), false);
            SoLoader.loadLibrary("reactnativejni");
            deviceSupportReactNativeLib = true;
        } catch (Throwable th) {
            th.printStackTrace();
            if (JDReactHelper.newInstance().isDebug()) {
                JLog.d(CpuUtils.class.getSimpleName(), "This device not support JDReact jni lib, so downgrade JDReact features!!");
            }
            deviceSupportReactNativeLib = false;
            JLog.e(CpuUtils.class.getSimpleName(), th.getMessage());
        }
    }

    public static boolean checkIfSupportReactNativeLib() {
        return deviceSupportReactNativeLib;
    }

    public static boolean isX86() {
        try {
            String str = Build.CPU_ABI.contains("x86") ? "x86" : "arm";
            if (JDReactHelper.newInstance().isDebug()) {
                JLog.d(PerformanceManager.CUP, "cpu architecture is " + str);
            }
            if (TextUtils.isEmpty(str)) {
                return false;
            }
            return str.equalsIgnoreCase("x86");
        } catch (Exception e2) {
            JLog.e(CpuUtils.class.getSimpleName(), e2.getMessage());
            return false;
        }
    }
}
