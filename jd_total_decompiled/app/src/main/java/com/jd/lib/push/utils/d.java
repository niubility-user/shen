package com.jd.lib.push.utils;

import android.text.TextUtils;
import com.alibaba.fastjson.util.TypeUtils;
import com.jd.framework.json.JDJSON;
import com.jingdong.app.mall.bundle.mobileConfig.JDMobileConfig;
import com.jingdong.jdpush_new.j.g;

/* loaded from: classes16.dex */
public class d {
    public static String a() {
        String config = JDMobileConfig.getInstance().getConfig("JDPush", "config", "dtConfig", "{}");
        g.a("\u83b7\u53d6\u79fb\u52a8\u914d\u7f6e\u4e0b\u53d1dtConfig " + config);
        return config;
    }

    private static int b(String str, int i2) {
        try {
            String config = JDMobileConfig.getInstance().getConfig("JDPush", "switch", str, i2 + "");
            g.a(str + "\uff1a " + config);
            return TypeUtils.castToInt(config).intValue();
        } catch (Throwable th) {
            g.g(th);
            return i2;
        }
    }

    public static String c() {
        try {
            return JDJSON.parseObject(a()).optString("invalidJDDT", "");
        } catch (Throwable th) {
            g.g(th);
            return "";
        }
    }

    public static String d() {
        String config = JDMobileConfig.getInstance().getConfig("JDPush", "config", "longConnConfig", "{}");
        g.a("\u83b7\u53d6\u79fb\u52a8\u914d\u7f6e\u4e0b\u53d1longConnConfig " + config);
        return config;
    }

    public static String e() {
        String config = JDMobileConfig.getInstance().getConfig("JDPush", "config", "pushConfig", "{}");
        g.a("\u83b7\u53d6\u79fb\u52a8\u914d\u7f6e\u4e0b\u53d1config " + config);
        return config;
    }

    public static int f() {
        return b("serviceScheme", 0);
    }

    private static boolean g(String str, String str2) {
        String config = JDMobileConfig.getInstance().getConfig("JDPush", "switch", str, str2);
        g.a("PushMobileConfig - " + str + "\uff1a " + config);
        return TextUtils.equals(config, "1");
    }

    public static boolean h() {
        return b("hmsChannelAlsoUseHonorClearBadge", 0) == 1;
    }

    public static boolean i() {
        return b("honorChannelAlsoUseHmsClearBadge", 0) == 1;
    }

    public static boolean j() {
        return b("honorUseHmsChannel", 1) == 1;
    }

    public static boolean k() {
        return b("alwaysUseBind", 0) == 1;
    }

    public static boolean l() {
        return g("ua", "0");
    }

    public static boolean m() {
        return b("jumpDirectly", 0) == 1;
    }

    public static boolean n() {
        return b("setBadgeInSubThread", 1) == 1;
    }
}
