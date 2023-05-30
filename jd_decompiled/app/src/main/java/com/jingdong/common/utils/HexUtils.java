package com.jingdong.common.utils;

import org.apache.commons.codec.CharEncoding;

/* loaded from: classes6.dex */
public class HexUtils {
    public static String bytes2HexString(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer();
        for (byte b : bArr) {
            stringBuffer.append(String.format("%02X", Byte.valueOf(b)));
        }
        return stringBuffer.toString();
    }

    public static String hexGBK2String(String str) {
        return hexString2String(str, "GBK", "UTF-8");
    }

    public static byte[] hexString2Bytes(String str) {
        int length = str.length() / 2;
        byte[] bArr = new byte[length];
        for (int i2 = 0; i2 < length; i2++) {
            int i3 = i2 * 2;
            bArr[i2] = Integer.valueOf(str.substring(i3, i3 + 2), 16).byteValue();
        }
        return bArr;
    }

    public static String hexString2String(String str, String str2, String str3) {
        byte[] hexString2Bytes = hexString2Bytes(str);
        try {
            if (str2.equals(str3)) {
                return new String(hexString2Bytes, str2);
            }
            return new String(new String(hexString2Bytes, str2).getBytes(), str3);
        } catch (Exception unused) {
            return "";
        }
    }

    public static String hexUTF82String(String str) {
        return hexString2String(str, "UTF-8", "UTF-8");
    }

    public static String hexUnicode2String(String str) {
        return hexString2String(str, "Unicode", "UTF-8");
    }

    public static String string2HexGBK(String str) {
        return string2HexString(str, "GBK");
    }

    public static String string2HexString(String str, String str2) {
        try {
            return bytes2HexString(str.getBytes(str2));
        } catch (Exception unused) {
            return "";
        }
    }

    public static String string2HexUTF16LE(String str) {
        return string2HexString(str, CharEncoding.UTF_16LE);
    }

    public static String string2HexUTF8(String str) {
        return string2HexString(str, "UTF-8");
    }

    public static String string2HexUnicode(String str) {
        return string2HexString(str, "Unicode");
    }
}
