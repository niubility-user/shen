package com.jd.verify.j;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.text.format.Formatter;
import android.view.accessibility.AccessibilityManager;
import com.jd.dynamic.DYConstants;
import com.jingdong.jdsdk.constant.CartConstant;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Pattern;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

/* loaded from: classes18.dex */
public class b {
    private static ConnectivityManager a = null;
    private static String b = "";

    /* renamed from: c  reason: collision with root package name */
    private static String f7173c = "";
    private static String d = "";

    /* renamed from: e  reason: collision with root package name */
    private static String f7174e = "";

    /* renamed from: f  reason: collision with root package name */
    private static String f7175f = "";

    /* renamed from: g  reason: collision with root package name */
    private static String f7176g = "";

    /* renamed from: h  reason: collision with root package name */
    private static String f7177h = "";

    /* renamed from: i  reason: collision with root package name */
    private static final FileFilter f7178i = new a();

    /* loaded from: classes18.dex */
    class a implements FileFilter {
        a() {
        }

        @Override // java.io.FileFilter
        public boolean accept(File file) {
            return Pattern.matches("cpu[0-9]", file.getName());
        }
    }

    public static int a(Context context, float f2) {
        return (int) ((f2 * context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static String b() {
        return d;
    }

    public static void c(String str) {
        f7175f = str;
    }

    public static void d(String str) {
        f7174e = str;
    }

    public static String e() {
        return f7173c;
    }

    public static void f(String str) {
        b = str;
    }

    public static String g() {
        return b;
    }

    public static void h(String str) {
        f7176g = str;
    }

    public static String i() {
        return f7175f;
    }

    public static String j() {
        return f7174e;
    }

    public static String k() {
        return f7177h;
    }

    public static String l() {
        return f7176g;
    }

    public static String m(Context context) {
        return k(context) ? "1" : "0";
    }

    public static String n(Context context) {
        return context.getPackageManager().hasSystemFeature("android.hardware.sensor.compass") ? "1" : "0";
    }

    public static String o(Context context) {
        return context.getPackageManager().hasSystemFeature("android.hardware.sensor.proximity") ? "1" : "0";
    }

    public static String p(Context context) {
        return context.getPackageManager().hasSystemFeature("android.hardware.fingerprint") ? "1" : "0";
    }

    public static String q(Context context) {
        return context.getPackageManager().hasSystemFeature("android.hardware.sensor.gyroscope") ? "1" : "0";
    }

    public static String r(Context context) {
        return context.getPackageManager().hasSystemFeature("android.hardware.sensor.light") ? "1" : "0";
    }

    public static String s(Context context) {
        return context.getPackageManager().hasSystemFeature("android.hardware.touchscreen.multitouch") ? "1" : "0";
    }

    public static String t(Context context) {
        return context.getPackageManager().hasSystemFeature("android.hardware.nfc") ? "1" : "0";
    }

    private static Locale a(Context context) {
        Configuration configuration = context.getResources().getConfiguration();
        if (Build.VERSION.SDK_INT >= 24) {
            return configuration.getLocales().get(0);
        }
        return configuration.locale;
    }

    public static void b(String str) {
        d = str;
    }

    public static String c() {
        try {
            String[] split = new BufferedReader(new FileReader("/proc/cpuinfo")).readLine().split(":\\s+", 2);
            for (int i2 = 0; i2 < split.length; i2++) {
            }
            return split[1];
        } catch (FileNotFoundException e2) {
            e2.printStackTrace();
            return "";
        } catch (IOException e3) {
            e3.printStackTrace();
            return "";
        }
    }

    public static String d(Context context) {
        Locale a2 = a(context);
        if (a2 != null) {
            return a2.getLanguage() + CartConstant.KEY_YB_INFO_LINK + a2.getCountry();
        }
        return "";
    }

    public static void e(String str) {
        f7173c = str;
    }

    public static String f() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("lan:");
        stringBuffer.append(e());
        stringBuffer.append(DYConstants.DY_REGEX_COMMA);
        stringBuffer.append("lon:");
        stringBuffer.append(g());
        return stringBuffer.toString();
    }

    public static void g(String str) {
        f7177h = str;
    }

    public static int h(Context context) {
        PackageInfo f2 = f(context);
        if (f2 == null) {
            return 0;
        }
        return f2.versionCode;
    }

    public static String i(Context context) {
        PackageInfo f2;
        String str;
        return (context == null || (f2 = f(context)) == null || (str = f2.versionName) == null) ? "" : str;
    }

    public static String j(Context context) {
        long[] e2 = e(context);
        return e2 != null ? Formatter.formatFileSize(context, e2[0]) : "";
    }

    private static boolean k(Context context) {
        AccessibilityManager accessibilityManager;
        List<AccessibilityServiceInfo> enabledAccessibilityServiceList;
        return a() && (accessibilityManager = (AccessibilityManager) context.getSystemService("accessibility")) != null && accessibilityManager.isEnabled() && (enabledAccessibilityServiceList = accessibilityManager.getEnabledAccessibilityServiceList(1)) != null && !enabledAccessibilityServiceList.isEmpty() && accessibilityManager.isTouchExplorationEnabled();
    }

    public static boolean l(Context context) {
        NetworkInfo activeNetworkInfo;
        if (a == null) {
            if (context == null) {
                return true;
            }
            a = (ConnectivityManager) context.getSystemService("connectivity");
        }
        ConnectivityManager connectivityManager = a;
        return (connectivityManager == null || (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) == null || !activeNetworkInfo.isConnectedOrConnecting()) ? false : true;
    }

    public static int b(Context context, float f2) {
        return (int) ((f2 / context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    private static long[] e(Context context) {
        BufferedReader bufferedReader;
        BufferedReader bufferedReader2 = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(new File("/proc/meminfo"))), 1024);
            String str = null;
            String str2 = null;
            while (true) {
                try {
                    String readLine = bufferedReader.readLine();
                    if (readLine != null) {
                        if (readLine.startsWith("MemTotal")) {
                            str = readLine;
                        } else if (readLine.startsWith("MemFree")) {
                            str2 = readLine;
                        }
                        if (str != null && str2 != null) {
                            break;
                        }
                    } else {
                        break;
                    }
                } catch (Exception unused) {
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException unused2) {
                        }
                    }
                    return null;
                } catch (Throwable th) {
                    th = th;
                    bufferedReader2 = bufferedReader;
                    if (bufferedReader2 != null) {
                        try {
                            bufferedReader2.close();
                        } catch (IOException unused3) {
                        }
                    }
                    throw th;
                }
            }
            ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
            ((ActivityManager) context.getSystemService("activity")).getMemoryInfo(memoryInfo);
            long[] jArr = {a(str), a(str2), memoryInfo.availMem};
            try {
                bufferedReader.close();
            } catch (IOException unused4) {
            }
            return jArr;
        } catch (Exception unused5) {
            bufferedReader = null;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static String g(Context context) {
        PackageInfo f2;
        String str;
        return (context == null || (f2 = f(context)) == null || (str = f2.packageName) == null) ? "" : str;
    }

    public static String b(Context context) {
        try {
            return context.getResources().getString(context.getPackageManager().getPackageInfo(context.getPackageName(), 0).applicationInfo.labelRes);
        } catch (Throwable unused) {
            return "";
        }
    }

    public static String d() {
        return TimeZone.getDefault().getDisplayName(false, 0);
    }

    public static int h() {
        if (Build.VERSION.SDK_INT <= 10) {
            return 1;
        }
        try {
            return new File("/sys/devices/system/cpu/").listFiles(f7178i).length;
        } catch (NullPointerException | SecurityException unused) {
            return 1;
        }
    }

    public static PackageInfo f(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
        } catch (Exception unused) {
            return null;
        }
    }

    private static long a(String str) {
        int indexOf;
        String trim;
        int lastIndexOf;
        long j2;
        if (str == null || (indexOf = str.indexOf(58)) == -1 || (lastIndexOf = (trim = str.substring(indexOf + 1).trim()).lastIndexOf(32)) == -1) {
            return -1L;
        }
        String substring = trim.substring(lastIndexOf + 1);
        try {
            long parseLong = Long.parseLong(trim.substring(0, lastIndexOf).trim());
            if ("kb".equalsIgnoreCase(substring)) {
                j2 = 1024;
            } else if ("mb".equalsIgnoreCase(substring)) {
                j2 = 1048576;
            } else if (!"gb".equalsIgnoreCase(substring)) {
                return parseLong;
            } else {
                j2 = IjkMediaMeta.AV_CH_STEREO_RIGHT;
            }
            return parseLong * j2;
        } catch (Exception unused) {
            return -1L;
        }
    }

    public static String c(Context context) {
        if (context == null) {
            return "";
        }
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        ((ActivityManager) context.getSystemService("activity")).getMemoryInfo(memoryInfo);
        return Formatter.formatFileSize(context, memoryInfo.availMem);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:71:0x0081 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:77:0x0077 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r4v10 */
    /* JADX WARN: Type inference failed for: r4v12, types: [java.io.IOException] */
    /* JADX WARN: Type inference failed for: r4v14, types: [java.io.FileReader] */
    /* JADX WARN: Type inference failed for: r4v15, types: [java.io.IOException] */
    /* JADX WARN: Type inference failed for: r4v20, types: [java.io.FileReader, java.io.Reader] */
    /* JADX WARN: Type inference failed for: r4v21, types: [java.io.IOException] */
    /* JADX WARN: Type inference failed for: r4v38 */
    /* JADX WARN: Type inference failed for: r4v39 */
    /* JADX WARN: Type inference failed for: r4v5 */
    /* JADX WARN: Type inference failed for: r4v7, types: [java.io.IOException] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:49:0x006e -> B:65:0x0071). Please submit an issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String a(boolean r4) {
        /*
            r0 = 0
            java.lang.String r1 = ""
            if (r4 == 0) goto Ld
            java.io.FileReader r4 = new java.io.FileReader     // Catch: java.lang.Throwable -> L3b java.io.IOException -> L3f java.io.FileNotFoundException -> L56
            java.lang.String r2 = "/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_min_freq"
            r4.<init>(r2)     // Catch: java.lang.Throwable -> L3b java.io.IOException -> L3f java.io.FileNotFoundException -> L56
            goto L14
        Ld:
            java.io.FileReader r4 = new java.io.FileReader     // Catch: java.lang.Throwable -> L3b java.io.IOException -> L3f java.io.FileNotFoundException -> L56
            java.lang.String r2 = "/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq"
            r4.<init>(r2)     // Catch: java.lang.Throwable -> L3b java.io.IOException -> L3f java.io.FileNotFoundException -> L56
        L14:
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L2e java.io.IOException -> L31 java.io.FileNotFoundException -> L36
            r2.<init>(r4)     // Catch: java.lang.Throwable -> L2e java.io.IOException -> L31 java.io.FileNotFoundException -> L36
            java.lang.String r1 = r2.readLine()     // Catch: java.io.IOException -> L2a java.io.FileNotFoundException -> L2c java.lang.Throwable -> L72
            r4.close()     // Catch: java.io.IOException -> L21
            goto L25
        L21:
            r4 = move-exception
            r4.printStackTrace()
        L25:
            r2.close()     // Catch: java.io.IOException -> L6d
            goto L71
        L2a:
            r0 = move-exception
            goto L43
        L2c:
            r0 = move-exception
            goto L5a
        L2e:
            r1 = move-exception
            r2 = r0
            goto L74
        L31:
            r2 = move-exception
            r3 = r2
            r2 = r0
            r0 = r3
            goto L43
        L36:
            r2 = move-exception
            r3 = r2
            r2 = r0
            r0 = r3
            goto L5a
        L3b:
            r4 = move-exception
            r1 = r4
            r2 = r0
            goto L75
        L3f:
            r4 = move-exception
            r2 = r0
            r0 = r4
            r4 = r2
        L43:
            r0.printStackTrace()     // Catch: java.lang.Throwable -> L72
            if (r4 == 0) goto L50
            r4.close()     // Catch: java.io.IOException -> L4c
            goto L50
        L4c:
            r4 = move-exception
            r4.printStackTrace()
        L50:
            if (r2 == 0) goto L71
            r2.close()     // Catch: java.io.IOException -> L6d
            goto L71
        L56:
            r4 = move-exception
            r2 = r0
            r0 = r4
            r4 = r2
        L5a:
            r0.printStackTrace()     // Catch: java.lang.Throwable -> L72
            if (r4 == 0) goto L67
            r4.close()     // Catch: java.io.IOException -> L63
            goto L67
        L63:
            r4 = move-exception
            r4.printStackTrace()
        L67:
            if (r2 == 0) goto L71
            r2.close()     // Catch: java.io.IOException -> L6d
            goto L71
        L6d:
            r4 = move-exception
            r4.printStackTrace()
        L71:
            return r1
        L72:
            r0 = move-exception
            r1 = r0
        L74:
            r0 = r4
        L75:
            if (r0 == 0) goto L7f
            r0.close()     // Catch: java.io.IOException -> L7b
            goto L7f
        L7b:
            r4 = move-exception
            r4.printStackTrace()
        L7f:
            if (r2 == 0) goto L89
            r2.close()     // Catch: java.io.IOException -> L85
            goto L89
        L85:
            r4 = move-exception
            r4.printStackTrace()
        L89:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.verify.j.b.a(boolean):java.lang.String");
    }

    private static boolean a() {
        return Build.VERSION.SDK_INT >= 16;
    }
}
