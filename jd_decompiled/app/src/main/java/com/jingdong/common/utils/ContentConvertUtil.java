package com.jingdong.common.utils;

/* loaded from: classes6.dex */
public class ContentConvertUtil {
    public static byte[] toByteArray(String str) {
        int length = str.length() / 2;
        byte[] bArr = new byte[length];
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            int i4 = i3 + 1;
            bArr[i2] = (byte) ((Character.digit(str.charAt(i3), 16) << 4) | Character.digit(str.charAt(i4), 16));
            i2++;
            i3 = i4 + 1;
        }
        return bArr;
    }
}
