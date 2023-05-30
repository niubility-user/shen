package com.jd.aips.common.utils;

import java.security.MessageDigest;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;

/* loaded from: classes12.dex */
public class MD5Utils {
    public static String md5_32bit(String str) {
        if (str == null) {
            str = "";
        }
        try {
            byte[] bytes = str.getBytes();
            char[] cArr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
            MessageDigest messageDigest = MessageDigest.getInstance(MessageDigestAlgorithms.MD5);
            messageDigest.update(bytes);
            byte[] digest = messageDigest.digest();
            char[] cArr2 = new char[32];
            int i2 = 0;
            for (int i3 = 0; i3 < 16; i3++) {
                byte b = digest[i3];
                int i4 = i2 + 1;
                cArr2[i2] = cArr[(b >>> 4) & 15];
                i2 = i4 + 1;
                cArr2[i4] = cArr[b & 15];
            }
            return new String(cArr2);
        } catch (Exception unused) {
            return null;
        }
    }
}
