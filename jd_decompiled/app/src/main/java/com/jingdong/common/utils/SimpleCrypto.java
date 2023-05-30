package com.jingdong.common.utils;

import com.jd.jdsdk.security.AesCbcCrypto;

@Deprecated
/* loaded from: classes6.dex */
public class SimpleCrypto {
    private static final String HEX = "0123456789ABCDEF";

    private static void appendHex(StringBuffer stringBuffer, byte b) {
        stringBuffer.append(HEX.charAt((b >> 4) & 15));
        stringBuffer.append(HEX.charAt(b & 15));
    }

    @Deprecated
    public static String decrypt(String str, String str2) {
        return AesCbcCrypto.decrypt(str2, str, (byte[]) null);
    }

    public static String encrypt(String str, String str2) {
        return AesCbcCrypto.encrypt(str2, str, (byte[]) null);
    }

    public static String fromHex(String str) {
        return new String(toByte(str));
    }

    public static byte[] toByte(String str) {
        int length = str.length() / 2;
        byte[] bArr = new byte[length];
        for (int i2 = 0; i2 < length; i2++) {
            int i3 = i2 * 2;
            bArr[i2] = Integer.valueOf(str.substring(i3, i3 + 2), 16).byteValue();
        }
        return bArr;
    }

    public static String toHex(String str) {
        return toHex(str.getBytes());
    }

    public static String toHex(byte[] bArr) {
        if (bArr == null) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer(bArr.length * 2);
        for (byte b : bArr) {
            appendHex(stringBuffer, b);
        }
        return stringBuffer.toString();
    }
}
