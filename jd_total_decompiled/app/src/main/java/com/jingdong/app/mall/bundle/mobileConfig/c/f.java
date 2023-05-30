package com.jingdong.app.mall.bundle.mobileConfig.c;

import android.text.TextUtils;
import com.jd.android.sdk.coreinfo.CoreInfo;
import com.jd.wireless.sdk.intelligent.assistant.audio.record.Constant;
import com.jingdong.common.utils.LangUtils;
import jd.wjlogin_sdk.util.ReplyCode;

/* loaded from: classes12.dex */
public class f {
    private static byte[] a = {102, ReplyCode.reply0x37, 62, 63, ReplyCode.reply0x34, ReplyCode.reply0x36, 4, 63, 125, 102, 63, ReplyCode.reply0x35, 58, ReplyCode.reply0x29, ReplyCode.reply0x39, 4, 63, 125, 102, 62, 43, ReplyCode.reply0x22, 15, 48, ReplyCode.reply0x29, ReplyCode.reply0x34, 44, 47, 62, ReplyCode.reply0x35, 125, 63, 18, ReplyCode.reply0x29, 62, ReplyCode.reply0x28, 46, 121, 97, 121, 63, 18, 43, 43, 58, 121, 32, 102, ReplyCode.reply0x22, 63, ReplyCode.reply0x34, ReplyCode.reply0x39, 125, 102, ReplyCode.reply0x35, ReplyCode.reply0x34, ReplyCode.reply0x32, ReplyCode.reply0x28, ReplyCode.reply0x29, 62, 13, ReplyCode.reply0x28, ReplyCode.reply0x34, 125, 102, 63, ReplyCode.reply0x37, ReplyCode.reply0x32, 46, ReplyCode.reply0x39, 125, 102, 63, ReplyCode.reply0x32, 46, 46, 125, 102, ReplyCode.reply0x35, ReplyCode.reply0x34, ReplyCode.reply0x32, ReplyCode.reply0x28, ReplyCode.reply0x29, 62, 13, 47, ReplyCode.reply0x35, 62, ReplyCode.reply0x32, ReplyCode.reply0x37, ReplyCode.reply0x38, 125, 63, ReplyCode.reply0x32, ReplyCode.reply0x34, ReplyCode.reply0x29, 63, ReplyCode.reply0x35, 58, 102, 47, ReplyCode.reply0x35, 62, ReplyCode.reply0x32, ReplyCode.reply0x37, ReplyCode.reply0x38, 125, 102, 47, 125, Constant.MAX_DURATION_DEFAULT, ReplyCode.reply0x32, 61, ReplyCode.reply0x35, ReplyCode.reply0x34, 24, ReplyCode.reply0x38, ReplyCode.reply0x32, ReplyCode.reply0x28, 58, ReplyCode.reply0x39, 102, 63, 18, ReplyCode.reply0x35, ReplyCode.reply0x34, ReplyCode.reply0x32, 47, ReplyCode.reply0x38, ReplyCode.reply0x35, 46, 61, 125, 102, 63, ReplyCode.reply0x32, 43, 43, 58, ReplyCode.reply0x64};
    private static String b;

    /* renamed from: c  reason: collision with root package name */
    private static String f8237c;
    private static String d;

    /* renamed from: e  reason: collision with root package name */
    static String f8238e;

    public static String a() {
        return a(0, 7);
    }

    private static String a(int i2, int i3) {
        l();
        if (TextUtils.isEmpty(f8238e)) {
            return "";
        }
        try {
            return f8238e.substring(i2, i3);
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public static String b() {
        return a(87, 103);
    }

    public static String c() {
        return !TextUtils.isEmpty(f8237c) ? f8237c : CoreInfo.Device.getBrand();
    }

    public static String d() {
        return f8238e.substring(122, 131);
    }

    public static String e() {
        return a(69, 76);
    }

    public static String f() {
        return a(33, 63);
    }

    public static String g() {
        if (TextUtils.isEmpty(b)) {
            try {
                String model = CoreInfo.Device.getModel();
                if (model != null && model.length() > 0) {
                    if (model.length() > 25) {
                        model = model.substring(0, 24);
                    }
                    return model.replaceAll(LangUtils.SINGLE_SPACE, "");
                }
                return "";
            } catch (Exception unused) {
                return "";
            }
        }
        return b;
    }

    public static String h() {
        return a(131, 140);
    }

    public static String i() {
        return a(109, 122);
    }

    public static String j() {
        if (TextUtils.isEmpty(d)) {
            String networkType = CoreInfo.Device.getNetworkType(com.jingdong.app.mall.bundle.mobileConfig.a.l().f());
            return TextUtils.isEmpty(networkType) ? "UNKNOWN" : networkType;
        }
        return d;
    }

    public static String k() {
        return a(76, 87);
    }

    static String l() {
        if (TextUtils.isEmpty(f8238e)) {
            String a2 = c.a(a);
            f8238e = a2;
            return a2;
        }
        return f8238e;
    }

    public static String m() {
        return a(103, 109);
    }

    public static String n() {
        return a(63, 69);
    }

    public static String o() {
        return a(7, 33);
    }
}
