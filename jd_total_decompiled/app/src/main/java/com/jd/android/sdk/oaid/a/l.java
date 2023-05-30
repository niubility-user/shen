package com.jd.android.sdk.oaid.a;

import android.content.Context;
import com.jdpay.system.SystemInfo;
import com.jingdong.common.permission.RomUtil;
import com.jingdong.sdk.baseinfo.BaseInfo;

/* loaded from: classes12.dex */
public final class l {
    private static final String a = "l";
    private static final String b = BaseInfo.getDeviceManufacture();

    /* renamed from: c  reason: collision with root package name */
    private static final String f1697c = BaseInfo.getDeviceBrand();

    private l() {
    }

    public static boolean a() {
        return b.equalsIgnoreCase("HUAWEI") || f1697c.equalsIgnoreCase("HUAWEI");
    }

    public static boolean a(Context context) {
        try {
            context.getPackageManager().getPackageInfo("com.coolpad.deviceidsupport", 0);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public static boolean b() {
        return b.equalsIgnoreCase("HONOR") || f1697c.equalsIgnoreCase("HONOR");
    }

    public static boolean c() {
        if (b.equalsIgnoreCase("OPPO")) {
            return true;
        }
        String str = f1697c;
        return str.equalsIgnoreCase("OPPO") || str.equalsIgnoreCase("REALME");
    }

    public static boolean d() {
        return b.equalsIgnoreCase("VIVO") || f1697c.equalsIgnoreCase("VIVO");
    }

    public static boolean e() {
        if (b.equalsIgnoreCase("XIAOMI")) {
            return true;
        }
        String str = f1697c;
        return str.equalsIgnoreCase("XIAOMI") || str.equalsIgnoreCase("REDMI");
    }

    public static boolean f() {
        return b.equalsIgnoreCase("BLACKSHARK") || f1697c.equalsIgnoreCase("BLACKSHARK");
    }

    public static boolean g() {
        return b.equalsIgnoreCase(SystemInfo.ROM_1JIA) || f1697c.equalsIgnoreCase(SystemInfo.ROM_1JIA);
    }

    public static boolean h() {
        return b.equalsIgnoreCase(RomUtil.ROM_SAMSUNG) || f1697c.equalsIgnoreCase(RomUtil.ROM_SAMSUNG);
    }

    public static boolean i() {
        return b.equalsIgnoreCase("MEIZU") || f1697c.equalsIgnoreCase("MEIZU") || BaseInfo.getOSName().toUpperCase().contains("FLYME");
    }

    public static boolean j() {
        if (b.equalsIgnoreCase(RomUtil.ROM_LENOVO)) {
            return true;
        }
        String str = f1697c;
        return str.equalsIgnoreCase(RomUtil.ROM_LENOVO) || str.equalsIgnoreCase("ZUK");
    }

    public static boolean k() {
        return b.equalsIgnoreCase("NUBIA") || f1697c.equalsIgnoreCase("NUBIA");
    }

    public static boolean l() {
        return b.equalsIgnoreCase("ASUS") || f1697c.equalsIgnoreCase("ASUS");
    }
}
