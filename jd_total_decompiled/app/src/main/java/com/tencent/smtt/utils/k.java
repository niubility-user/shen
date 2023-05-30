package com.tencent.smtt.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.text.TextUtils;
import com.jingdong.sdk.baseinfo.BaseInfo;
import com.tencent.smtt.sdk.TbsPrivacyAccess;
import java.security.MessageDigest;
import java.util.Locale;
import java.util.Random;
import java.util.UUID;

/* loaded from: classes9.dex */
public class k {
    private static SharedPreferences a;
    private static SharedPreferences.Editor b;

    private static String a() {
        return BaseInfo.getOSFingerprint() + new Random().nextInt(2147483646);
    }

    public static String a(Context context) {
        SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences("tsui", 0);
        a = sharedPreferences;
        if (sharedPreferences != null) {
            String string = sharedPreferences.getString("tsui", "");
            if (!TextUtils.isEmpty(string)) {
                return string;
            }
        }
        StringBuilder sb = new StringBuilder();
        String a2 = a();
        String g2 = b.g(context);
        String c2 = c(context);
        String replace = b(context).replace("-", "");
        if (a2 != null && a2.length() > 0) {
            sb.append(a2);
            sb.append("|");
        }
        if (g2 != null && g2.length() > 0) {
            sb.append(g2);
            sb.append("|");
        }
        if (c2 != null && c2.length() > 0) {
            sb.append(c2);
            sb.append("|");
        }
        if (replace != null && replace.length() > 0) {
            sb.append(replace);
        }
        if (sb.length() > 0) {
            String a3 = a(sb.toString());
            if (!TextUtils.isEmpty(a3)) {
                a(context, "tsui", a3);
                return a3;
            }
        }
        String replace2 = UUID.randomUUID().toString().replace("-", "");
        a(context, "tsui", replace2);
        return replace2;
    }

    public static String a(String str) {
        try {
            String a2 = a(b(str));
            return a2.length() > 0 ? a2 : "";
        } catch (Exception e2) {
            TbsLog.w("SDKUID", "encrypt sdkuid failed, origin is " + str + "reason: " + e2);
            return "";
        }
    }

    private static String a(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        for (byte b2 : bArr) {
            String hexString = Integer.toHexString(b2 & 255);
            if (hexString.length() == 1) {
                sb.append("0");
            }
            sb.append(hexString);
        }
        return sb.toString().toUpperCase(Locale.CHINA);
    }

    private static void a(Context context, String str, String str2) {
        if (a == null) {
            a = context.getApplicationContext().getSharedPreferences("tsui", 0);
        }
        SharedPreferences.Editor edit = a.edit();
        b = edit;
        edit.putString(str, str2);
        b.commit();
    }

    private static String b(Context context) {
        try {
            String a2 = s.a(context);
            int nextInt = new Random().nextInt(2147483646);
            return new UUID(("" + nextInt + (Build.BOARD.length() % 10) + (BaseInfo.getDeviceBrand().length() % 10) + (BaseInfo.getDeviceName().length() % 10) + (Build.HARDWARE.length() % 10) + (Build.ID.length() % 10) + (a2.length() % 10) + (BaseInfo.getDeviceProductName().length() % 10) + (c(context).length() % 10)).hashCode(), c(context).hashCode()).toString();
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    private static byte[] b(String str) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA1");
            messageDigest.reset();
            messageDigest.update(str.getBytes("UTF-8"));
            return messageDigest.digest();
        } catch (Exception unused) {
            return "".getBytes();
        }
    }

    private static String c(Context context) {
        try {
            String configurePrivacy = TbsPrivacyAccess.getConfigurePrivacy(context, TbsPrivacyAccess.ConfigurablePrivacy.SERIAL, "");
            if (!TextUtils.isEmpty(configurePrivacy) && !configurePrivacy.contains("unknown")) {
                return configurePrivacy;
            }
            return a();
        } catch (Exception unused) {
            return "unknown";
        }
    }
}
