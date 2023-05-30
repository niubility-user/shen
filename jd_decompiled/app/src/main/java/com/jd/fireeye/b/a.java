package com.jd.fireeye.b;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.text.TextUtils;
import com.jingdong.sdk.baseinfo.BaseInfo;
import java.security.MessageDigest;

/* loaded from: classes13.dex */
public class a {
    public static int a(Context context) {
        PackageInfo c2 = c(context);
        if (c2 == null) {
            return 0;
        }
        return c2.versionCode;
    }

    public static String b(Context context) {
        PackageInfo c2 = c(context);
        if (c2 == null) {
            return "";
        }
        String str = c2.versionName;
        return !TextUtils.isEmpty(str) ? str : "";
    }

    private static PackageInfo c(Context context) {
        if (context != null) {
            try {
                return context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            } catch (Exception unused) {
                return null;
            }
        }
        return null;
    }

    public static String a() {
        return BaseInfo.getAndroidVersion();
    }

    public static String a(Context context, String str) {
        Signature[] b;
        if (TextUtils.isEmpty(str)) {
            f.b("AppBaseUtil", "pkName is null");
            return "";
        } else if (context == null) {
            f.b("AppBaseUtil", "context is null");
            return "";
        } else {
            try {
                b = b(context, str);
            } catch (Throwable th) {
                th.printStackTrace();
            }
            if (b != null && b.length != 0) {
                Signature signature = b[0];
                if (signature != null) {
                    String b2 = b(signature.toByteArray());
                    if (f.a) {
                        f.b("AppBaseUtil", "signatureStr = " + b2);
                    }
                    return b2;
                }
                return "";
            }
            f.b("AppBaseUtil", "sign is null");
            return "";
        }
    }

    public static Signature[] b(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(str, 64);
            if (packageInfo == null) {
                return null;
            }
            return packageInfo.signatures;
        } catch (PackageManager.NameNotFoundException unused) {
            if (f.a) {
                f.b("AppBaseUtil", str + " not installed!");
            }
            return null;
        }
    }

    private static String b(byte[] bArr) {
        try {
            return a(MessageDigest.getInstance("SHA1").digest(bArr));
        } catch (Throwable th) {
            th.printStackTrace();
            return "";
        }
    }

    private static String a(byte[] bArr) {
        StringBuilder sb = new StringBuilder(bArr.length * 2);
        for (byte b : bArr) {
            String hexString = Integer.toHexString(b);
            int length = hexString.length();
            if (length == 1) {
                hexString = "0" + hexString;
            }
            if (length > 2) {
                hexString = hexString.substring(length - 2, length);
            }
            sb.append(hexString.toLowerCase());
        }
        return sb.toString();
    }
}
