package com.huawei.secure.android.common.encrypt.d;

import android.text.TextUtils;
import java.io.UnsupportedEncodingException;
import java.util.Locale;

/* loaded from: classes12.dex */
public final class c {
    public static String a(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (byte b : bArr) {
            String hexString = Integer.toHexString(b & 255);
            if (hexString.length() == 1) {
                sb.append('0');
            }
            sb.append(hexString);
        }
        return sb.toString();
    }

    public static byte[] b(String str) {
        if (TextUtils.isEmpty(str)) {
            return new byte[0];
        }
        try {
            String upperCase = str.toUpperCase(Locale.ENGLISH);
            int length = upperCase.length() / 2;
            byte[] bArr = new byte[length];
            try {
                byte[] bytes = upperCase.getBytes("UTF-8");
                for (int i2 = 0; i2 < length; i2++) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("0x");
                    int i3 = i2 * 2;
                    sb.append(new String(new byte[]{bytes[i3]}, "UTF-8"));
                    bArr[i2] = (byte) (((byte) (Byte.decode(sb.toString()).byteValue() << 4)) ^ Byte.decode("0x" + new String(new byte[]{bytes[i3 + 1]}, "UTF-8")).byteValue());
                }
                return bArr;
            } catch (UnsupportedEncodingException | NumberFormatException e2) {
                f.c("HexUtil", "hex string 2 byte array exception : " + e2.getMessage());
                return new byte[0];
            }
        } catch (Throwable th) {
            f.c("HexUtil", "hex string toUpperCase exception : " + th.getMessage());
            return new byte[0];
        }
    }
}
