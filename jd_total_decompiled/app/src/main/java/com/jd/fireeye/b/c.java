package com.jd.fireeye.b;

/* loaded from: classes13.dex */
public class c {
    public static String a(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer();
        for (byte b : bArr) {
            stringBuffer.append(String.format("%02X", Byte.valueOf(b)));
        }
        return stringBuffer.toString();
    }

    public static String a(String str) {
        return a(str, "UTF-8");
    }

    public static String a(String str, String str2) {
        try {
            return a(str.getBytes(str2));
        } catch (Exception unused) {
            return "";
        }
    }
}
