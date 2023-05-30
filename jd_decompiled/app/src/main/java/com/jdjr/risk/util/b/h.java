package com.jdjr.risk.util.b;

import android.text.TextUtils;

/* loaded from: classes18.dex */
public class h {
    public static String a(String str, String str2, String str3) {
        if (str != null) {
            try {
                if (TextUtils.isEmpty(str)) {
                    return "";
                }
                int parseLong = (int) (Long.parseLong(str2) % 255);
                byte[] bytes = str.getBytes(str3);
                int length = bytes.length - 13;
                byte[] bArr = new byte[length];
                byte[] bArr2 = new byte[8];
                System.arraycopy(bytes, 5, bArr, 0, length);
                System.arraycopy(bytes, bytes.length - 8, bArr2, 0, 8);
                return "jdd01" + b.a(a.a(bArr), parseLong, str3) + new String(bArr2, str3);
            } catch (Exception unused) {
                return "";
            }
        }
        return "";
    }

    public static String b(String str, String str2, String str3) {
        if (str != null) {
            try {
                if (TextUtils.isEmpty(str)) {
                    return "";
                }
                byte[] bytes = str.getBytes(str3);
                byte[] bArr = new byte[8];
                int length = bytes.length - 10;
                byte[] bArr2 = new byte[length];
                System.arraycopy(bytes, 0, bArr, 0, 8);
                System.arraycopy(bytes, 10, bArr2, 0, length);
                return new String(bArr, str3) + "81" + b.a(a.a(bArr2), (int) (Long.parseLong(str2) % 255), str3);
            } catch (Exception unused) {
                return "";
            }
        }
        return "";
    }
}
