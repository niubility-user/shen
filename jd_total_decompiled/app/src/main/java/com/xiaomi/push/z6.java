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

    /* JADX WARN: Removed duplicated region for block: B:131:0x00ab A[Catch: all -> 0x00cb, TryCatch #0 {, blocks: (B:98:0x0003, B:100:0x0007, B:103:0x0015, B:104:0x001a, B:106:0x001f, B:111:0x002f, B:113:0x0035, B:116:0x003c, B:118:0x004a, B:129:0x0094, B:131:0x00ab, B:132:0x00ae, B:119:0x005b, B:121:0x0069, B:124:0x0074, B:127:0x007c, B:128:0x0082, B:133:0x00c7), top: B:139:0x0003 }] */
    /* JADX WARN: Removed duplicated region for block: B:132:0x00ae A[Catch: all -> 0x00cb, TryCatch #0 {, blocks: (B:98:0x0003, B:100:0x0007, B:103:0x0015, B:104:0x001a, B:106:0x001f, B:111:0x002f, B:113:0x0035, B:116:0x003c, B:118:0x004a, B:129:0x0094, B:131:0x00ab, B:132:0x00ae, B:119:0x005b, B:121:0x0069, B:124:0x0074, B:127:0x007c, B:128:0x0082, B:133:0x00c7), top: B:139:0x0003 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static synchronized String h(Context context, boolean z) {
        String str;
        synchronized (z6.class) {
            if (b == null) {
                String o = o(context);
                String str2 = "";
                if (!a8.t()) {
                    str2 = z ? s(context) : B(context);
                }
                String g2 = g(context);
                int i2 = 1;
                if (!(Build.VERSION.SDK_INT < 26) && q(str2) && q(g2)) {
                    String e2 = z.a(context).e();
                    if (TextUtils.isEmpty(e2)) {
                        String a2 = z.a(context).a();
                        if (!TextUtils.isEmpty(a2) && !a2.startsWith("00000000-0000-0000-0000-000000000000")) {
                            i2 = 4;
                            o = a2;
                        } else if (TextUtils.isEmpty(o)) {
                            o = C(context);
                            i2 = 6;
                        } else {
                            i2 = 5;
                        }
                    } else {
                        o = e2 + o;
                        i2 = 2;
                    }
                    g.j.a.a.a.c.y("devid rule select:" + i2);
                    if (i2 != 3) {
                        b = o;
                    } else {
                        b = f(i2) + p0.k(o);
                    }
                }
                o = str2 + o + g2;
                g.j.a.a.a.c.y("devid rule select:" + i2);
                if (i2 != 3) {
                }
            }
            str = b;
        }
        return str;
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
