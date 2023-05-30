package com.jdjr.risk.device.c;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Process;
import android.text.TextUtils;
import com.jingdong.common.utils.LangUtils;
import com.jingdong.sdk.baseinfo.BaseInfo;
import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Modifier;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes18.dex */
public class t {
    public static String a() {
        String str;
        try {
            ClassLoader.getSystemClassLoader().loadClass("de.robv.android.xposed.XposedHelpers").newInstance();
        } catch (ClassNotFoundException | Exception unused) {
        } catch (IllegalAccessException | InstantiationException unused2) {
            str = "XposedBridge-hook";
        }
        str = "";
        if (str.equals("")) {
            try {
                ClassLoader.getSystemClassLoader().loadClass("de.robv.android.xposed.XposedBridge").newInstance();
            } catch (ClassNotFoundException | Exception unused3) {
            } catch (IllegalAccessException | InstantiationException unused4) {
                return "XposedBridge-hook";
            }
        }
        return str;
    }

    public static String a(Context context) {
        if (TextUtils.isEmpty(null)) {
            String b = b();
            if (TextUtils.isEmpty(b)) {
                String c2 = c();
                if (TextUtils.isEmpty(c2)) {
                    String b2 = b(context);
                    if (TextUtils.isEmpty(b2)) {
                        String a = a();
                        if (TextUtils.isEmpty(a)) {
                            String d = d();
                            if (TextUtils.isEmpty(d)) {
                                String e2 = e();
                                if (TextUtils.isEmpty(e2)) {
                                    String f2 = f();
                                    return TextUtils.isEmpty(f2) ? g() : f2;
                                }
                                return e2;
                            }
                            return d;
                        }
                        return a;
                    }
                    return b2;
                }
                return c2;
            }
            return b;
        }
        return null;
    }

    private static String b() {
        try {
            throw new Exception("Deteck hook");
        } catch (Exception e2) {
            String str = "";
            int i2 = 0;
            for (StackTraceElement stackTraceElement : e2.getStackTrace()) {
                if ("com.android.internal.os.ZygoteInit".equals(stackTraceElement.getClassName()) && (i2 = i2 + 1) == 2) {
                    str = "saurik-hook";
                }
                String str2 = ("com.saurik.substrate.MS$2".equals(stackTraceElement.getClassName()) && "invoke".equals(stackTraceElement.getMethodName())) ? "saurik-hook" : str;
                if ("de.robv.android.xposed.XposedBridge".equals(stackTraceElement.getClassName()) && "main".equals(stackTraceElement.getMethodName())) {
                    str2 = "XposedBridge-hook";
                }
                str = ("de.robv.android.xposed.XposedBridge".equals(stackTraceElement.getClassName()) && "handleHookedMethod".equals(stackTraceElement.getMethodName())) ? "XposedBridge-hook" : str2;
            }
            return str;
        }
    }

    private static String b(Context context) {
        String str = "";
        try {
            List<ActivityManager.RunningServiceInfo> runningServices = BaseInfo.getRunningServices(context);
            if (runningServices != null && runningServices.size() > 0) {
                for (int i2 = 0; i2 < runningServices.size(); i2++) {
                    if (runningServices.get(i2).process.contains("fridaserver")) {
                        str = "frida-hook";
                    }
                }
            }
        } catch (Exception unused) {
        }
        return str;
    }

    private static String c() {
        String str = "";
        try {
            HashSet hashSet = new HashSet();
            BufferedReader bufferedReader = new BufferedReader(new FileReader("/proc/" + Process.myPid() + "/maps"));
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                } else if (readLine.toLowerCase().contains("frida")) {
                    str = "frida-hook";
                    break;
                } else if (readLine.endsWith(".so") || readLine.endsWith(".jar")) {
                    hashSet.add(readLine.substring(readLine.lastIndexOf(LangUtils.SINGLE_SPACE) + 1));
                }
            }
            Iterator it = hashSet.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                String str2 = (String) it.next();
                if (str2.contains("com.saurik.substrate")) {
                    str = "saurik-hook";
                    break;
                } else if (str2.contains("XposedBridge.jar")) {
                    str = "XposedBridge-hook";
                    break;
                }
            }
            bufferedReader.close();
        } catch (Exception unused) {
        }
        return str;
    }

    private static String d() {
        try {
            return Modifier.isNative(Throwable.class.getDeclaredMethod("getStackTrace", new Class[0]).getModifiers()) ? "XposedBridge-hook" : "";
        } catch (NoSuchMethodException unused) {
            return "";
        }
    }

    private static String e() {
        try {
            return System.getProperty("vxp") != null ? "XposedBridge-hook" : "";
        } catch (Exception unused) {
            return "";
        }
    }

    private static String f() {
        try {
            String b = j.b("ls /system/lib");
            return !TextUtils.isEmpty(b) ? b.contains("xposed") ? "XposedBridge-hook" : "" : "";
        } catch (Exception unused) {
            return "";
        }
    }

    private static String g() {
        try {
            String str = System.getenv("CLASSPATH");
            return !TextUtils.isEmpty(str) ? str.contains("XposedBridge") ? "XposedBridge-hook" : "" : "";
        } catch (Exception unused) {
            return "";
        }
    }
}
