package com.jd.jdsec.a.k;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.os.Process;
import com.jingdong.common.utils.LangUtils;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashSet;
import java.util.List;

/* loaded from: classes13.dex */
public class e {
    private static long a;

    public static Boolean a() {
        try {
            try {
                Class.forName("de.robv.android.xposed.XC_MethodHook");
                return Boolean.TRUE;
            } catch (Exception unused) {
                Class.forName("de.robv.android.xposed.XposedHelpers");
                return Boolean.FALSE;
            }
        } catch (Exception unused2) {
            return Boolean.FALSE;
        }
    }

    public static Boolean b(Context context) {
        try {
            throw new Exception("Deteck hook");
        } catch (Exception e2) {
            int i2 = 0;
            for (StackTraceElement stackTraceElement : e2.getStackTrace()) {
                if (("com.saurik.substrate.MS$2".equals(stackTraceElement.getClassName()) && "invoke".equals(stackTraceElement.getMethodName())) || (("de.robv.android.xposed.XposedBridge".equals(stackTraceElement.getClassName()) && "main".equals(stackTraceElement.getMethodName())) || ("de.robv.android.xposed.XposedBridge".equals(stackTraceElement.getClassName()) && "handleHookedMethod".equals(stackTraceElement.getMethodName())))) {
                    return Boolean.TRUE;
                }
                if ("com.android.internal.os.ZygoteInit".equals(stackTraceElement.getClassName()) && (i2 = i2 + 1) == 2) {
                    return Boolean.TRUE;
                }
            }
            return Boolean.FALSE;
        }
    }

    public static Boolean c() {
        Boolean bool = Boolean.FALSE;
        try {
            return System.getProperty("vxp") != null ? Boolean.TRUE : bool;
        } catch (Exception unused) {
            return bool;
        }
    }

    public static Long d(Context context) {
        try {
            if (f(context).booleanValue()) {
                a |= 1;
            }
            if (b(context).booleanValue()) {
                a |= 2;
            }
            if (e().booleanValue()) {
                a |= 4;
            }
            if (a().booleanValue()) {
                a |= 8;
            }
            if (c().booleanValue()) {
                a |= 16;
            }
        } catch (Exception unused) {
        }
        return Long.valueOf(a);
    }

    public static Boolean e() {
        BufferedReader bufferedReader;
        String readLine;
        HashSet<String> hashSet = new HashSet();
        try {
            bufferedReader = new BufferedReader(new FileReader("/proc/" + Process.myPid() + "/maps"));
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        do {
            readLine = bufferedReader.readLine();
            if (readLine != null) {
                if (readLine.endsWith(".jar") || readLine.endsWith(".so")) {
                    hashSet.add(readLine.substring(readLine.lastIndexOf(LangUtils.SINGLE_SPACE) + 1));
                }
            } else {
                for (String str : hashSet) {
                    if (str.contains("com.saurik.substrate")) {
                        return Boolean.TRUE;
                    }
                    if (str.contains("XposedBridge.jar")) {
                        return Boolean.TRUE;
                    }
                }
                bufferedReader.close();
                return Boolean.FALSE;
            }
        } while (!readLine.toLowerCase().contains("frida"));
        return Boolean.TRUE;
    }

    public static Boolean f(Context context) {
        List<PackageInfo> r;
        try {
            r = com.jd.jdsec.a.g.r(context);
        } catch (Exception unused) {
        }
        if (r == null) {
            return Boolean.FALSE;
        }
        for (PackageInfo packageInfo : r) {
            if ("de.robv.android.xposed.installer".equals(packageInfo.packageName) || "com.saurik.substrate".equals(packageInfo.packageName)) {
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }
}
