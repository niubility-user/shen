package com.jd.jdsec.a.k;

import android.content.Context;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Vibrator;
import com.jingdong.sdk.baseinfo.BaseInfo;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes13.dex */
public class d {
    private static long a;
    private static long b;

    public static Long a() {
        List asList = Arrays.asList("com.tencent.mm", "com.tencent.mobileqq", "com.eg.android.AlipayGphone", "com.taobao.taobao", "com.yztc.studio.plugin", "com.soft.apk008v", "com.qxq.shenqi", "com.igrimace.nzt", "com.bigsing.changer", "org.wuji", "com.linghang520.jlipnet", "com.deruhai.guangzi", "com.qi.earthnutproxy", "com.flight.ribald.protein", "com.miui.miuibbs", "com.uwish.app");
        for (int i2 = 0; i2 < asList.size(); i2++) {
            try {
                if (BaseInfo.isPkgInstalled((String) asList.get(i2))) {
                    b |= 1 << i2;
                }
            } catch (Exception unused) {
            }
        }
        return Long.valueOf(b);
    }

    public static boolean b(Context context) {
        double intExtra = context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED")).getIntExtra("temperature", 0);
        Double.isNaN(intExtra);
        double d = intExtra / 10.0d;
        return d == 0.0d || d > 100.0d;
    }

    private static boolean c(PackageManager packageManager) {
        return packageManager.hasSystemFeature("android.hardware.camera.autofocus");
    }

    private static boolean d(NetworkInfo networkInfo) {
        if (networkInfo == null) {
            return false;
        }
        return (System.getProperty("http.proxyHost") == null || System.getProperty("http.proxyPort") == null) ? false : true;
    }

    public static Long e(Context context) {
        try {
            if (b(context)) {
                a |= 1;
            }
            PackageManager packageManager = context.getPackageManager();
            if (f(packageManager)) {
                a |= 2;
            }
            if (h(packageManager)) {
                a |= 4;
            }
            if (i(context)) {
                a |= 8;
            }
            if (c(packageManager)) {
                a |= 16;
            }
            com.jd.jdsec.a.l.b.e("JDSec.risklabel.vpnIsInUse", String.valueOf(com.jd.jdsec.a.g.O(context)));
            com.jd.jdsec.a.l.b.e("JDSec.risklabel.isAppUsingProxy", String.valueOf(g(context)));
            if (g(context) && !com.jd.jdsec.a.g.O(context)) {
                a |= 32;
            }
        } catch (Exception unused) {
        }
        return Long.valueOf(a);
    }

    public static boolean f(PackageManager packageManager) {
        return packageManager.hasSystemFeature("android.hardware.sensor.compass");
    }

    public static boolean g(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (connectivityManager != null) {
            try {
                return d(connectivityManager.getNetworkInfo(connectivityManager.getActiveNetwork()));
            } catch (Exception unused) {
                return false;
            }
        }
        return false;
    }

    public static boolean h(PackageManager packageManager) {
        return packageManager.hasSystemFeature("android.hardware.usb.host");
    }

    private static boolean i(Context context) {
        Vibrator vibrator = (Vibrator) context.getSystemService("vibrator");
        return vibrator != null && vibrator.hasVibrator();
    }
}
