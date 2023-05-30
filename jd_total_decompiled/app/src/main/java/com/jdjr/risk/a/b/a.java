package com.jdjr.risk.a.b;

import android.text.TextUtils;
import android.util.Base64;
import java.security.MessageDigest;

/* loaded from: classes18.dex */
public class a {
    public static String a(String str) {
        StringBuffer stringBuffer = new StringBuffer();
        try {
            if (!TextUtils.isEmpty(str)) {
                String e2 = e(str);
                if (!TextUtils.isEmpty(e2)) {
                    String c2 = c(str);
                    if (!TextUtils.isEmpty(c2)) {
                        stringBuffer.append(c2);
                        stringBuffer.insert(3, e2, 0, 32);
                        stringBuffer.insert(3, e2, 32, 64);
                    }
                }
            }
        } catch (Exception unused) {
        }
        return stringBuffer.toString();
    }

    private static String a(StringBuffer stringBuffer) {
        StringBuffer stringBuffer2 = new StringBuffer();
        try {
            stringBuffer2.append(stringBuffer.substring(3, 35));
            stringBuffer.delete(3, 35);
            stringBuffer2.insert(0, stringBuffer.substring(3, 35));
            stringBuffer.delete(3, 35);
        } catch (Exception unused) {
        }
        return stringBuffer2.toString();
    }

    public static String b(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return "DEVICEID_VERIFICATION_FALSE";
            }
            StringBuffer stringBuffer = new StringBuffer(str);
            String a = a(stringBuffer);
            if (TextUtils.isEmpty(a)) {
                return "DEVICEID_VERIFICATION_FALSE";
            }
            String d = d(stringBuffer.toString());
            return !TextUtils.isEmpty(d) ? a.equals(e(d)) ? d : "DEVICEID_VERIFICATION_FALSE" : "DEVICEID_VERIFICATION_FALSE";
        } catch (Exception unused) {
            return "DEVICEID_VERIFICATION_FALSE";
        }
    }

    public static String c(String str) {
        try {
            return new String(Base64.encode(str.getBytes(), 9));
        } catch (Exception unused) {
            return "";
        }
    }

    public static String d(String str) {
        try {
            return new String(Base64.decode(str.getBytes(), 9));
        } catch (Exception unused) {
            return "";
        }
    }

    public static String e(String str) {
        StringBuffer stringBuffer = new StringBuffer();
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA256");
            messageDigest.update(str.getBytes());
            byte[] digest = messageDigest.digest();
            if (digest != null && digest.length > 0) {
                for (byte b : digest) {
                    int i2 = b & 255;
                    if (i2 < 16) {
                        stringBuffer.append("0");
                    }
                    stringBuffer.append(Integer.toHexString(i2));
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return stringBuffer.toString();
    }
}
