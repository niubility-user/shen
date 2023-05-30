package com.jingdong.jdsdk.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;

/* loaded from: classes.dex */
public class Md5Encrypt {
    private static final char[] DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public static char[] encodeHex(byte[] bArr) {
        int length = bArr.length;
        char[] cArr = new char[length << 1];
        int i2 = 0;
        for (int i3 = 0; i3 < length; i3++) {
            int i4 = i2 + 1;
            char[] cArr2 = DIGITS;
            cArr[i2] = cArr2[(bArr[i3] & 240) >>> 4];
            i2 = i4 + 1;
            cArr[i4] = cArr2[bArr[i3] & 15];
        }
        return cArr;
    }

    public static String md5(String str) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(MessageDigestAlgorithms.MD5);
            try {
                messageDigest.update(str.getBytes("utf-8"));
                return new String(encodeHex(messageDigest.digest()));
            } catch (UnsupportedEncodingException unused) {
                throw new IllegalStateException("System doesn't support your  EncodingException.");
            }
        } catch (NoSuchAlgorithmException unused2) {
            throw new IllegalStateException("System doesn't support MD5 algorithm.");
        }
    }
}
