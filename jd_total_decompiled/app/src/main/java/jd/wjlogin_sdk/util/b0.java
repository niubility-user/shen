package jd.wjlogin_sdk.util;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.net.Uri;
import android.text.TextUtils;
import com.jingdong.jdsdk.constant.CartConstant;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Date;
import jd.wjlogin_sdk.model.ErrorResult;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;

/* loaded from: classes.dex */
public class b0 {
    private static final String a = "WJLogin.Util";
    protected static char[] b = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    /* renamed from: c */
    private static String f19914c = "@w#a$q&ejuak";

    public static String a(Context context) {
        String packageName;
        Signature[] a2;
        if (context == null || (a2 = a(context, (packageName = context.getPackageName()))) == null || a2.length == 0) {
            return "";
        }
        return MD5.encrypt32(b(a2[0].toByteArray()) + CartConstant.KEY_YB_INFO_LINK + packageName);
    }

    public static boolean b(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        String[] split = str.split("-");
        return (split.length <= 1 || TextUtils.isEmpty(split[0]) || TextUtils.isEmpty(split[1])) ? false : true;
    }

    public static int c(String str) {
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        String[] split = str.split("\\.");
        int i2 = 0;
        int i3 = 0;
        for (int length = split.length > 4 ? 3 : split.length - 1; length >= 0; length--) {
            String str2 = "";
            for (int i4 = 0; i4 < split[length].length() && Character.isDigit(split[length].charAt(i4)); i4++) {
                try {
                    str2 = str2 + split[length].charAt(i4);
                } catch (Exception unused) {
                    return 0;
                }
            }
            i2 |= (Integer.parseInt(str2) & 255) << i3;
            i3 += 8;
        }
        return i2;
    }

    public static String d(String str) {
        try {
            return e(str).substring(8, 24);
        } catch (Exception e2) {
            e2.printStackTrace();
            return a();
        }
    }

    public static String e(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        try {
            String str2 = "";
            for (byte b2 : MessageDigest.getInstance(MessageDigestAlgorithms.MD5).digest(str.getBytes())) {
                String hexString = Integer.toHexString(b2 & 255);
                if (hexString.length() == 1) {
                    hexString = "0" + hexString;
                }
                str2 = str2 + hexString;
            }
            return str2;
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public static void b(Context context, String str) {
        p.b(a, "openurl = " + str);
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str));
        intent.putExtra("thirdAppPackage", context.getPackageName());
        intent.setPackage(f.f19954c);
        intent.setFlags(268435456);
        context.getApplicationContext().startActivity(intent);
    }

    private static Signature[] a(Context context, String str) {
        if (str != null && str.length() != 0) {
            try {
                PackageInfo packageInfo = context.getPackageManager().getPackageInfo(str, 64);
                if (packageInfo == null) {
                    return null;
                }
                return packageInfo.signatures;
            } catch (PackageManager.NameNotFoundException unused) {
            }
        }
        return null;
    }

    public static String c() {
        StringBuilder sb = new StringBuilder();
        try {
            SecureRandom secureRandom = new SecureRandom();
            for (int i2 = 0; i2 < 16; i2++) {
                sb.append(secureRandom.nextInt(9));
            }
            return sb.toString();
        } catch (Exception unused) {
            return "1829145172732096";
        }
    }

    private static String b(byte[] bArr) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(MessageDigestAlgorithms.MD5);
            messageDigest.update(bArr);
            return a(messageDigest.digest());
        } catch (NoSuchAlgorithmException e2) {
            e2.printStackTrace();
            return "";
        }
    }

    private static String a(byte[] bArr) {
        return a(bArr, 0, bArr.length);
    }

    private static String a(byte[] bArr, int i2, int i3) {
        StringBuffer stringBuffer = new StringBuffer(i3 * 2);
        int i4 = i3 + i2;
        while (i2 < i4) {
            char[] cArr = b;
            char c2 = cArr[(bArr[i2] & 240) >> 4];
            char c3 = cArr[bArr[i2] & 15];
            stringBuffer.append(c2);
            stringBuffer.append(c3);
            i2++;
        }
        return stringBuffer.toString();
    }

    public static String b() {
        StringBuilder sb = new StringBuilder();
        try {
            SecureRandom secureRandom = new SecureRandom();
            for (int i2 = 0; i2 < 9; i2++) {
                sb.append((char) ((secureRandom.nextInt(2) % 2 == 0 ? 65 : 97) + secureRandom.nextInt(26)));
            }
            return sb.toString();
        } catch (Exception unused) {
            return "hykebyIld";
        }
    }

    public static int a(String str) {
        return (int) (Math.abs(new Date().getTime() - new Date(Long.parseLong(str)).getTime()) / 86400000);
    }

    public static ErrorResult a(int i2, String str, Exception exc) {
        if (299 == i2) {
            return new ErrorResult(i2, str, exc);
        }
        if (exc != null) {
            p.a(a, "exception =" + exc);
        }
        return new ErrorResult(i2, LanguageToast.getToastMsg(i2), exc);
    }

    public static String a(byte[] bArr, int i2) {
        return bArr == null ? "" : new String(bArr, 0, i2);
    }

    public static String a() {
        return f19914c;
    }
}
