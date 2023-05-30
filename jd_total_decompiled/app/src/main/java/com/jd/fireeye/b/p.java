package com.jd.fireeye.b;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.text.TextUtils;
import android.util.Base64;
import com.jingdong.sdk.baseinfo.BaseInfo;

/* loaded from: classes13.dex */
public class p {
    private static volatile String a;

    public static String a() {
        return "123456789";
    }

    public static String a(Context context) {
        try {
            return BaseInfo.getAndroidId();
        } catch (Exception unused) {
            return "";
        }
    }

    public static String b(Context context) {
        try {
            if (k.a(context)) {
                BluetoothAdapter.getDefaultAdapter();
                String bluetoothMac = BaseInfo.getBluetoothMac(context);
                return TextUtils.isEmpty(bluetoothMac) ? "" : bluetoothMac;
            }
            return "";
        } catch (Exception unused) {
            return "";
        }
    }

    private static String c(Context context) {
        if (!TextUtils.isEmpty(a)) {
            return a;
        }
        String a2 = o.a("cpa_ududud_new", "");
        if (!TextUtils.isEmpty(a2)) {
            String str = new String(Base64.decode(a2.getBytes(), 2));
            if (q.d(str)) {
                a = str;
                return a;
            }
        }
        String d = d(context);
        if (q.d(d)) {
            a = d;
            try {
                if (q.c(d)) {
                    o.b("cpa_ududud_new", Base64.encodeToString(d.getBytes(), 2));
                }
            } catch (Exception unused) {
            }
        }
        f.b("JDMob.Security.UniqueId", "init cachedUniqueIdInSDK = " + d + ",cachedUniqueIdInSDK = " + a);
        return d;
    }

    public static String d(Context context) {
        StringBuilder sb = new StringBuilder();
        sb.append("-");
        String f2 = f(context);
        if (!TextUtils.isEmpty(f2)) {
            sb.append(f2.replace(":", ""));
        }
        sb.append("-");
        sb.append(a(context));
        return sb.toString();
    }

    public static String e(Context context) {
        String c2 = c(context);
        return c2 == null ? "" : c2;
    }

    public static String f(Context context) {
        return "";
    }
}
