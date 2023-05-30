package com.xiaomi.push;

import android.annotation.TargetApi;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.PowerManager;
import android.os.StatFs;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.jd.dynamic.DYConstants;
import com.jingdong.common.cashiernative.CashierPayChannelCode;
import com.jingdong.common.database.table.SignUpTable;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.sdk.baseinfo.BaseInfo;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/* loaded from: classes11.dex */
public class z6 {
    private static String a;
    private static String b;

    /* renamed from: c */
    private static String f19359c;
    private static final String[] d;

    /* renamed from: e */
    private static final Set<String> f19360e;

    /* renamed from: f */
    private static boolean f19361f;

    static {
        String.valueOf((char) 2);
        d = new String[]{"--", "a-", "u-", "v-", "o-", "g-", "d-"};
        HashSet hashSet = new HashSet();
        f19360e = hashSet;
        hashSet.add("com.xiaomi.xmsf");
        hashSet.add("com.xiaomi.finddevice");
        hashSet.add("com.miui.securitycenter");
        f19361f = true;
    }

    public static String A(Context context) {
        return ((TelephonyManager) context.getSystemService(SignUpTable.TB_COLUMN_PHONE)).getSimOperatorName();
    }

    @Deprecated
    private static String B(Context context) {
        return "";
    }

    private static String C(Context context) {
        String string = context.getSharedPreferences("device_info", 0).getString("default_id", null);
        if (TextUtils.isEmpty(string)) {
            String D = D(context);
            i(context, D);
            return D;
        }
        return string;
    }

    private static String D(Context context) {
        return m0.a(BaseInfo.getDeviceBrand() + CartConstant.KEY_YB_INFO_LINK + BaseInfo.getDeviceModel() + CartConstant.KEY_YB_INFO_LINK + Build.VERSION.SDK_INT + CartConstant.KEY_YB_INFO_LINK + Build.VERSION.RELEASE + CartConstant.KEY_YB_INFO_LINK + Build.VERSION.INCREMENTAL + CartConstant.KEY_YB_INFO_LINK + c() + CartConstant.KEY_YB_INFO_LINK + context.getPackageName() + CartConstant.KEY_YB_INFO_LINK + System.currentTimeMillis() + CartConstant.KEY_YB_INFO_LINK + p0.a(16));
    }

    private static double a(double d2) {
        int i2 = 1;
        while (true) {
            double d3 = i2;
            if (d3 >= d2) {
                return d3;
            }
            i2 <<= 1;
        }
    }

    private static float b(int i2) {
        float f2 = ((((((i2 + 102400) / 524288) + 1) * 512) * 1024) / 1024.0f) / 1024.0f;
        double d2 = f2;
        return d2 > 0.5d ? (float) Math.ceil(d2) : f2;
    }

    @TargetApi(17)
    public static int c() {
        Object g2 = k0.g("android.os.UserHandle", "myUserId", new Object[0]);
        if (g2 == null) {
            return -1;
        }
        return ((Integer) Integer.class.cast(g2)).intValue();
    }

    private static long d(File file) {
        StatFs statFs = new StatFs(file.getPath());
        return statFs.getBlockSizeLong() * statFs.getBlockCountLong();
    }

    public static String e() {
        return b(m()) + CashierPayChannelCode.JD_PAY_GB;
    }

    private static String f(int i2) {
        if (i2 > 0) {
            String[] strArr = d;
            if (i2 < strArr.length) {
                return strArr[i2];
            }
        }
        return d[0];
    }

    @Deprecated
    public static String g(Context context) {
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:84:0x00ab A[Catch: all -> 0x00cb, TryCatch #0 {, blocks: (B:51:0x0003, B:53:0x0007, B:56:0x0015, B:57:0x001a, B:59:0x001f, B:64:0x002f, B:66:0x0035, B:69:0x003c, B:71:0x004a, B:82:0x0094, B:84:0x00ab, B:85:0x00ae, B:72:0x005b, B:74:0x0069, B:77:0x0074, B:80:0x007c, B:81:0x0082, B:86:0x00c7), top: B:92:0x0003 }] */
    /* JADX WARN: Removed duplicated region for block: B:85:0x00ae A[Catch: all -> 0x00cb, TryCatch #0 {, blocks: (B:51:0x0003, B:53:0x0007, B:56:0x0015, B:57:0x001a, B:59:0x001f, B:64:0x002f, B:66:0x0035, B:69:0x003c, B:71:0x004a, B:82:0x0094, B:84:0x00ab, B:85:0x00ae, B:72:0x005b, B:74:0x0069, B:77:0x0074, B:80:0x007c, B:81:0x0082, B:86:0x00c7), top: B:92:0x0003 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static synchronized java.lang.String h(android.content.Context r6, boolean r7) {
        /*
            java.lang.Class<com.xiaomi.push.z6> r0 = com.xiaomi.push.z6.class
            monitor-enter(r0)
            java.lang.String r1 = com.xiaomi.push.z6.b     // Catch: java.lang.Throwable -> Lcb
            if (r1 != 0) goto Lc7
            java.lang.String r1 = o(r6)     // Catch: java.lang.Throwable -> Lcb
            java.lang.String r2 = ""
            boolean r3 = com.xiaomi.push.a8.t()     // Catch: java.lang.Throwable -> Lcb
            if (r3 != 0) goto L1f
            if (r7 == 0) goto L1a
            java.lang.String r7 = s(r6)     // Catch: java.lang.Throwable -> Lcb
            goto L1e
        L1a:
            java.lang.String r7 = B(r6)     // Catch: java.lang.Throwable -> Lcb
        L1e:
            r2 = r7
        L1f:
            java.lang.String r7 = g(r6)     // Catch: java.lang.Throwable -> Lcb
            int r3 = android.os.Build.VERSION.SDK_INT     // Catch: java.lang.Throwable -> Lcb
            r4 = 26
            r5 = 1
            if (r3 >= r4) goto L2c
            r3 = 1
            goto L2d
        L2c:
            r3 = 0
        L2d:
            if (r3 != 0) goto L82
            boolean r3 = q(r2)     // Catch: java.lang.Throwable -> Lcb
            if (r3 == 0) goto L82
            boolean r3 = q(r7)     // Catch: java.lang.Throwable -> Lcb
            if (r3 != 0) goto L3c
            goto L82
        L3c:
            com.xiaomi.push.z r7 = com.xiaomi.push.z.a(r6)     // Catch: java.lang.Throwable -> Lcb
            java.lang.String r7 = r7.e()     // Catch: java.lang.Throwable -> Lcb
            boolean r2 = android.text.TextUtils.isEmpty(r7)     // Catch: java.lang.Throwable -> Lcb
            if (r2 != 0) goto L5b
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Lcb
            r6.<init>()     // Catch: java.lang.Throwable -> Lcb
            r6.append(r7)     // Catch: java.lang.Throwable -> Lcb
            r6.append(r1)     // Catch: java.lang.Throwable -> Lcb
            java.lang.String r1 = r6.toString()     // Catch: java.lang.Throwable -> Lcb
            r5 = 2
            goto L94
        L5b:
            com.xiaomi.push.z r7 = com.xiaomi.push.z.a(r6)     // Catch: java.lang.Throwable -> Lcb
            java.lang.String r7 = r7.a()     // Catch: java.lang.Throwable -> Lcb
            boolean r2 = android.text.TextUtils.isEmpty(r7)     // Catch: java.lang.Throwable -> Lcb
            if (r2 != 0) goto L74
            java.lang.String r2 = "00000000-0000-0000-0000-000000000000"
            boolean r2 = r7.startsWith(r2)     // Catch: java.lang.Throwable -> Lcb
            if (r2 != 0) goto L74
            r5 = 4
            r1 = r7
            goto L94
        L74:
            boolean r7 = android.text.TextUtils.isEmpty(r1)     // Catch: java.lang.Throwable -> Lcb
            if (r7 != 0) goto L7c
            r5 = 5
            goto L94
        L7c:
            java.lang.String r1 = C(r6)     // Catch: java.lang.Throwable -> Lcb
            r5 = 6
            goto L94
        L82:
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Lcb
            r6.<init>()     // Catch: java.lang.Throwable -> Lcb
            r6.append(r2)     // Catch: java.lang.Throwable -> Lcb
            r6.append(r1)     // Catch: java.lang.Throwable -> Lcb
            r6.append(r7)     // Catch: java.lang.Throwable -> Lcb
            java.lang.String r1 = r6.toString()     // Catch: java.lang.Throwable -> Lcb
        L94:
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Lcb
            r6.<init>()     // Catch: java.lang.Throwable -> Lcb
            java.lang.String r7 = "devid rule select:"
            r6.append(r7)     // Catch: java.lang.Throwable -> Lcb
            r6.append(r5)     // Catch: java.lang.Throwable -> Lcb
            java.lang.String r6 = r6.toString()     // Catch: java.lang.Throwable -> Lcb
            g.j.a.a.a.c.y(r6)     // Catch: java.lang.Throwable -> Lcb
            r6 = 3
            if (r5 != r6) goto Lae
            com.xiaomi.push.z6.b = r1     // Catch: java.lang.Throwable -> Lcb
            goto Lc7
        Lae:
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Lcb
            r6.<init>()     // Catch: java.lang.Throwable -> Lcb
            java.lang.String r7 = f(r5)     // Catch: java.lang.Throwable -> Lcb
            r6.append(r7)     // Catch: java.lang.Throwable -> Lcb
            java.lang.String r7 = com.xiaomi.push.p0.k(r1)     // Catch: java.lang.Throwable -> Lcb
            r6.append(r7)     // Catch: java.lang.Throwable -> Lcb
            java.lang.String r6 = r6.toString()     // Catch: java.lang.Throwable -> Lcb
            com.xiaomi.push.z6.b = r6     // Catch: java.lang.Throwable -> Lcb
        Lc7:
            java.lang.String r6 = com.xiaomi.push.z6.b     // Catch: java.lang.Throwable -> Lcb
            monitor-exit(r0)
            return r6
        Lcb:
            r6 = move-exception
            monitor-exit(r0)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.z6.h(android.content.Context, boolean):java.lang.String");
    }

    private static void i(Context context, String str) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("device_info", 0);
        if (TextUtils.isEmpty(sharedPreferences.getString("default_id", null))) {
            sharedPreferences.edit().putString("default_id", str).apply();
        } else {
            g.j.a.a.a.c.o("default_id exist,do not change it.");
        }
    }

    public static boolean j(Context context) {
        Intent registerReceiver = context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        if (registerReceiver != null) {
            int intExtra = registerReceiver.getIntExtra("status", -1);
            return intExtra == 2 || intExtra == 5;
        }
        return false;
    }

    public static boolean k(Context context, String str) {
        ApplicationInfo applicationInfo;
        PackageInfo packageInfo = (PackageInfo) k0.e(context.getPackageManager(), "getPackageInfoAsUser", str, 0, 999);
        return packageInfo == null || (applicationInfo = packageInfo.applicationInfo) == null || (applicationInfo.flags & 8388608) != 8388608;
    }

    public static boolean l(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        int i2 = 0;
        while (true) {
            String[] strArr = d;
            if (i2 >= strArr.length) {
                return false;
            }
            if (str.startsWith(strArr[i2])) {
                return true;
            }
            i2++;
        }
    }

    public static int m() {
        BufferedReader bufferedReader;
        Throwable th;
        String[] split;
        if (new File("/proc/meminfo").exists()) {
            BufferedReader bufferedReader2 = null;
            try {
                try {
                    bufferedReader = new BufferedReader(new FileReader("/proc/meminfo"), 8192);
                } catch (Exception unused) {
                } catch (Throwable th2) {
                    bufferedReader = null;
                    th = th2;
                }
                try {
                    String readLine = bufferedReader.readLine();
                    if (!TextUtils.isEmpty(readLine) && (split = readLine.split("\\s+")) != null && split.length >= 2 && TextUtils.isDigitsOnly(split[1])) {
                        Integer.parseInt(split[1]);
                    }
                    bufferedReader.close();
                } catch (Exception unused2) {
                    bufferedReader2 = bufferedReader;
                    if (bufferedReader2 != null) {
                        bufferedReader2.close();
                    }
                    return 0;
                } catch (Throwable th3) {
                    th = th3;
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException unused3) {
                        }
                    }
                    throw th;
                }
            } catch (IOException unused4) {
            }
        }
        return 0;
    }

    public static String n() {
        double d2 = d(Environment.getDataDirectory());
        Double.isNaN(d2);
        return a(((d2 / 1024.0d) / 1024.0d) / 1024.0d) + CashierPayChannelCode.JD_PAY_GB;
    }

    public static String o(Context context) {
        String str = a;
        if (str == null && f19361f) {
            boolean t = t(context);
            f19361f = t;
            if (t) {
                try {
                    a = BaseInfo.getAndroidIdWithAOPBySecure(context.getContentResolver(), "android_id");
                } catch (Throwable th) {
                    g.j.a.a.a.c.o("failure to get androidId: " + th);
                }
                return a;
            }
            return null;
        }
        return str;
    }

    public static boolean p(Context context) {
        PowerManager powerManager = (PowerManager) context.getSystemService("power");
        return powerManager == null || powerManager.isScreenOn();
    }

    private static boolean q(String str) {
        if (str == null) {
            return true;
        }
        String trim = str.trim();
        return trim.length() == 0 || trim.equalsIgnoreCase(DYConstants.DY_NULL_STR) || trim.equalsIgnoreCase("unknown");
    }

    public static String r() {
        return m() + "KB";
    }

    @Deprecated
    public static String s(Context context) {
        return null;
    }

    private static boolean t(Context context) {
        if ("com.xiaomi.xmsf".equals(context.getPackageName())) {
            return true;
        }
        Intent intent = new Intent();
        ComponentName componentName = new ComponentName(context.getPackageName(), "com.xiaomi.push.service.XMPushService");
        intent.setComponent(componentName);
        try {
            Bundle bundle = context.getPackageManager().getServiceInfo(componentName, 128).metaData;
            if (bundle != null) {
                String string = bundle.getString("supportGetAndroidID");
                if (TextUtils.isEmpty(string)) {
                    return true;
                }
                return Boolean.parseBoolean(string);
            }
        } catch (Exception unused) {
        }
        return true;
    }

    public static String u() {
        return (d(Environment.getDataDirectory()) / 1024) + "KB";
    }

    @Deprecated
    public static String v(Context context) {
        return null;
    }

    @Deprecated
    public static String w(Context context) {
        return null;
    }

    @Deprecated
    public static String x(Context context) {
        return "";
    }

    public static synchronized String y(Context context) {
        synchronized (z6.class) {
            String str = f19359c;
            if (str != null) {
                return str;
            }
            String k2 = p0.k(o(context) + g(context));
            f19359c = k2;
            return k2;
        }
    }

    public static synchronized String z(Context context) {
        String k2;
        synchronized (z6.class) {
            k2 = p0.k(o(context) + ((String) null));
        }
        return k2;
    }
}
