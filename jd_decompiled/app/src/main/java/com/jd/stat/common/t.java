package com.jd.stat.common;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.text.TextUtils;
import android.util.Base64;
import com.jingdong.sdk.baseinfo.BaseInfo;

@SuppressLint({"MissingPermission"})
/* loaded from: classes18.dex */
public class t {
    private static volatile String a;
    private static String b;

    public static boolean a() {
        com.jd.stat.common.b.b.b("JDMob.Security.UniqueId", "called isSameUniqueId");
        if (TextUtils.isEmpty(a)) {
            a(com.jd.stat.security.c.a);
        }
        String b2 = b(com.jd.stat.security.c.a);
        boolean equals = TextUtils.equals(a, b2);
        com.jd.stat.common.b.b.b("JDMob.Security.UniqueId", "cachedUniqueIdInSDK = " + a + ", getUniqueIdRealTimeInSDK = " + b2 + ", isSame = " + equals);
        if (!equals) {
            a = b2;
            com.jd.stat.common.b.b.b("JDMob.Security.UniqueId", "cachedUniqueIdInSDK assigned " + b2 + " in isSameUniqueId");
            if (com.jd.stat.common.b.g.b(b2)) {
                com.jd.stat.common.b.b.b("JDMob.Security.UniqueId", "put new id:" + b2);
                com.jd.stat.common.b.f.a("cpa_ududud_new", Base64.encodeToString(b2.getBytes(), 2));
            }
        }
        return equals;
    }

    public static String b(Context context) {
        com.jd.stat.common.b.b.b("JDMob.Security.UniqueId", "called getUniqueIdRealTimeInSDK");
        StringBuilder sb = new StringBuilder();
        sb.append("");
        sb.append("-");
        if (!TextUtils.isEmpty("")) {
            sb.append("".replace(":", ""));
        }
        sb.append("-");
        sb.append(BaseInfo.getAndroidId());
        sb.append("-");
        sb.append(q.a());
        return sb.toString();
    }

    public static String c(Context context) {
        com.jd.stat.common.b.b.b("JDMob.Security.UniqueId", "called getJDMallFormatUniqueIdInSDK");
        if (!TextUtils.isEmpty(b) && b.length() > 2) {
            return b;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("");
        sb.append("-");
        if (!TextUtils.isEmpty("")) {
            sb.append("".replace(":", ""));
        }
        String sb2 = sb.toString();
        b = sb2;
        return sb2;
    }

    public static String d(Context context) {
        try {
            if (l.b(context)) {
                BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
                String address = defaultAdapter != null ? defaultAdapter.getAddress() : "";
                if (TextUtils.isEmpty(address) || TextUtils.equals("02:00:00:00:00:00", address)) {
                    address = BaseInfo.getAndroidIdWithAOPBySecure(context.getContentResolver(), "bluetooth_address");
                }
                return TextUtils.isEmpty(address) ? "" : address;
            }
            return "";
        } catch (Exception unused) {
            return "";
        }
    }

    private static String e(Context context) {
        com.jd.stat.common.b.b.b("JDMob.Security.UniqueId", "called getJDKey");
        if (!TextUtils.isEmpty(a)) {
            com.jd.stat.common.b.b.b("JDMob.Security.UniqueId", "cachedUniqueIdInSDK is not empty. " + a);
            return a;
        }
        String b2 = com.jd.stat.common.b.f.b("cpa_ududud_new", "");
        if (!TextUtils.isEmpty(b2)) {
            a = new String(Base64.decode(b2.getBytes(), 2));
            com.jd.stat.common.b.b.b("JDMob.Security.UniqueId", "cachedUniqueIdInSDK assigned new value in  getJDKey" + a);
            return a;
        }
        String b3 = b(context);
        if (!TextUtils.isEmpty(b3)) {
            a = b3;
            com.jd.stat.common.b.b.b("JDMob.Security.UniqueId", "cachedUniqueIdInSDK assigned new value in  getJDKey2:" + b3);
            com.jd.stat.common.b.f.a("cpa_ududud_new", Base64.encodeToString(b3.getBytes(), 2));
            com.jd.stat.common.b.b.b("JDMob.Security.UniqueId", "put newid in getJDKey" + b3);
        }
        return b3;
    }

    public static String b() {
        com.jd.stat.common.b.b.b("JDMob.Security.UniqueId", "called getEncryptJDkey");
        try {
            String a2 = a(com.jd.stat.security.c.a);
            if (TextUtils.isEmpty(a2)) {
                return "";
            }
            return "jidwhzqalpk" + Base64.encodeToString(a2.getBytes(), 2);
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public static String a(Context context) {
        com.jd.stat.common.b.b.b("JDMob.Security.UniqueId", "called getUniqueIdWithCacheInSDK");
        String e2 = e(context);
        return e2 == null ? "" : e2;
    }
}
