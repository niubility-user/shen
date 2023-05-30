package com.jd.fireeye.a.d;

import android.content.Context;
import android.os.Process;
import com.jingdong.common.utils.LangUtils;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashSet;

/* loaded from: classes13.dex */
public class d {
    private static long a;

    public static Boolean a(Context context) {
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

    public static String b(Context context) {
        try {
            if (c(context).booleanValue()) {
                a |= 1;
            }
            if (a(context).booleanValue()) {
                a |= 2;
            }
            if (c().booleanValue()) {
                a |= 4;
            }
            if (a().booleanValue()) {
                a |= 8;
            }
            if (b().booleanValue()) {
                a |= 16;
            }
        } catch (Exception unused) {
        }
        return String.valueOf(a);
    }

    public static Boolean c(Context context) {
        return Boolean.FALSE;
    }

    public static Boolean c() {
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

    public static Boolean b() {
        Boolean bool = Boolean.FALSE;
        try {
            return System.getProperty("vxp") != null ? Boolean.TRUE : bool;
        } catch (Exception unused) {
            return bool;
        }
    }
}
