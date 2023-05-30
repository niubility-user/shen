package com.cmic.sso.sdk.e;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import com.jingdong.common.database.table.SignUpTable;
import com.jingdong.common.jdmiaosha.utils.cache.JDNetCacheManager;
import com.jingdong.sdk.baseinfo.BaseInfo;
import java.lang.reflect.Method;

/* loaded from: classes.dex */
public class m {
    private static final String a = BaseInfo.getDeviceBrand();
    private static final String b = BaseInfo.getDeviceModel();

    /* renamed from: c */
    private static final String f1039c = "android" + Build.VERSION.RELEASE;
    private static final boolean d;

    /* renamed from: e */
    private static final String f1040e;

    static {
        d = Build.VERSION.SDK_INT <= 28;
        f1040e = BaseInfo.getDeviceManufacture();
    }

    public static int a(Context context, boolean z, com.cmic.sso.sdk.a aVar) {
        ConnectivityManager connectivityManager;
        NetworkInfo activeNetworkInfo;
        try {
            connectivityManager = (ConnectivityManager) context.getApplicationContext().getSystemService("connectivity");
            activeNetworkInfo = connectivityManager != null ? connectivityManager.getActiveNetworkInfo() : null;
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        if (activeNetworkInfo != null && activeNetworkInfo.isAvailable()) {
            int type = activeNetworkInfo.getType();
            if (type != 1) {
                if (type == 0) {
                    c.b("TelephonyUtils", "\u6d41\u91cf");
                    return 1;
                }
                return 0;
            }
            c.b("TelephonyUtils", "WIFI");
            boolean a2 = g.a(context, "android.permission.CHANGE_NETWORK_STATE");
            StringBuilder sb = new StringBuilder();
            sb.append("CHANGE_NETWORK_STATE=");
            sb.append(a2);
            c.a("TelephonyUtils", sb.toString());
            if (a2 && z && a(connectivityManager, context, aVar)) {
                c.b("TelephonyUtils", "\u6d41\u91cf\u6570\u636e WIFI \u540c\u5f00");
                return 3;
            }
            return 2;
        }
        return 0;
    }

    public static String b() {
        return b;
    }

    public static String c() {
        return f1039c;
    }

    public static boolean d() {
        return d;
    }

    public static boolean e() {
        String str = f1040e;
        c.a(JDNetCacheManager.BRAND_BIZKEY, str);
        return "HUAWEI".equalsIgnoreCase(str);
    }

    private static boolean a(ConnectivityManager connectivityManager, Context context, com.cmic.sso.sdk.a aVar) {
        try {
            Method declaredMethod = ConnectivityManager.class.getDeclaredMethod("getMobileDataEnabled", new Class[0]);
            declaredMethod.setAccessible(true);
            boolean booleanValue = ((Boolean) declaredMethod.invoke(connectivityManager, new Object[0])).booleanValue();
            StringBuilder sb = new StringBuilder();
            sb.append("data is on ---------");
            sb.append(booleanValue);
            c.b("TelephonyUtils", sb.toString());
            if (Build.VERSION.SDK_INT >= 26) {
                TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(SignUpTable.TB_COLUMN_PHONE);
                if (telephonyManager != null && g.a(context, "android.permission.ACCESS_NETWORK_STATE")) {
                    if (telephonyManager.createForSubscriptionId(SubscriptionManager.getDefaultSubscriptionId()).isDataEnabled()) {
                        aVar.a("networkTypeByAPI", "1");
                    } else {
                        aVar.a("networkTypeByAPI", "0");
                    }
                }
            } else {
                aVar.a("networkTypeByAPI", "-1");
            }
            return booleanValue;
        } catch (Exception unused) {
            c.a("TelephonyUtils", "isMobileEnabled ----\u53cd\u5c04\u51fa\u9519-----");
            return false;
        }
    }

    public static String a() {
        return a;
    }

    public static boolean a(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(SignUpTable.TB_COLUMN_PHONE);
        return telephonyManager == null || 1 != telephonyManager.getSimState();
    }
}
