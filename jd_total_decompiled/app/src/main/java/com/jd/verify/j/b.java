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
    */
    public static String a(boolean z) {
        Throwable th;
        BufferedReader bufferedReader;
        IOException e2;
        FileReader fileReader;
        FileNotFoundException e3;
        ?? r4;
        boolean z2;
        FileReader fileReader2 = null;
        String str = "";
        try {
            try {
                try {
                    if (z) {
                        fileReader = new FileReader("/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_min_freq");
                    } else {
                        fileReader = new FileReader("/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq");
                    }
                } catch (FileNotFoundException e4) {
                    bufferedReader = null;
                    e3 = e4;
                    fileReader = 0;
                } catch (IOException e5) {
                    bufferedReader = null;
                    e2 = e5;
                    fileReader = null;
                } catch (Throwable th2) {
                    th = th2;
                    bufferedReader = null;
                    if (fileReader2 != null) {
                    }
                    if (bufferedReader != null) {
                    }
                    throw th;
                }
                try {
                    bufferedReader = new BufferedReader(fileReader);
                    try {
                        str = bufferedReader.readLine();
                        try {
                            fileReader.close();
                            z2 = fileReader;
                        } catch (IOException e6) {
                            e6.printStackTrace();
                            z2 = e6;
                        }
                        bufferedReader.close();
                        z = z2;
                    } catch (FileNotFoundException e7) {
                        e3 = e7;
                        e3.printStackTrace();
                        z = fileReader;
                        if (fileReader != 0) {
                            try {
                                fileReader.close();
                                z = fileReader;
                            } catch (IOException e8) {
                                e8.printStackTrace();
                                z = e8;
                            }
                        }
                        if (bufferedReader != null) {
                            bufferedReader.close();
                            z = z;
                        }
                        return str;
                    } catch (IOException e9) {
                        e2 = e9;
                        e2.printStackTrace();
                        z = fileReader;
                        if (fileReader != null) {
                            try {
                                fileReader.close();
                                z = fileReader;
                            } catch (IOException e10) {
                                e10.printStackTrace();
                                z = e10;
                            }
                        }
                        if (bufferedReader != null) {
                            bufferedReader.close();
                            z = z;
                        }
                        return str;
                    }
                } catch (FileNotFoundException e11) {
                    bufferedReader = null;
                    e3 = e11;
                } catch (IOException e12) {
                    bufferedReader = null;
                    e2 = e12;
                } catch (Throwable th3) {
                    th = th3;
                    bufferedReader = null;
                    r4 = fileReader;
                    fileReader2 = r4;
                    if (fileReader2 != null) {
                        try {
                            fileReader2.close();
                        } catch (IOException e13) {
                            e13.printStackTrace();
                        }
                    }
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException e14) {
                            e14.printStackTrace();
                        }
                    }
                    throw th;
                }
            } catch (Throwable th4) {
                th = th4;
                r4 = z;
            }
        } catch (IOException e15) {
            e15.printStackTrace();
            z = e15;
        }
        return str;
    }

    private static boolean a() {
        return Build.VERSION.SDK_INT >= 16;
    }
}
