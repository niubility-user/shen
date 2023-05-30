package com.facebook.common.util;

import android.util.Base64;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import jd.wjlogin_sdk.util.ReplyCode;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;

/* loaded from: classes.dex */
public class SecureHashUtil {
    private static final int BUFFER_SIZE = 4096;
    static final byte[] HEX_CHAR_TABLE = {48, ReplyCode.reply0x31, ReplyCode.reply0x32, 51, ReplyCode.reply0x34, ReplyCode.reply0x35, ReplyCode.reply0x36, ReplyCode.reply0x37, ReplyCode.reply0x38, ReplyCode.reply0x39, 97, 98, 99, ReplyCode.reply0x64, 101, 102};

    public static String convertToHex(byte[] bArr) {
        StringBuilder sb = new StringBuilder(bArr.length);
        for (byte b : bArr) {
            int i2 = b & 255;
            byte[] bArr2 = HEX_CHAR_TABLE;
            sb.append((char) bArr2[i2 >>> 4]);
            sb.append((char) bArr2[i2 & 15]);
        }
        return sb.toString();
    }

    private static String makeHash(InputStream inputStream, String str) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(str);
            byte[] bArr = new byte[4096];
            while (true) {
                int read = inputStream.read(bArr);
                if (read <= 0) {
                    return convertToHex(messageDigest.digest());
                }
                messageDigest.update(bArr, 0, read);
            }
        } catch (UnsupportedEncodingException e2) {
            throw new RuntimeException(e2);
        } catch (NoSuchAlgorithmException e3) {
            throw new RuntimeException(e3);
        }
    }

    private static String makeHash(byte[] bArr, String str) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(str);
            messageDigest.update(bArr, 0, bArr.length);
            return convertToHex(messageDigest.digest());
        } catch (UnsupportedEncodingException e2) {
            throw new RuntimeException(e2);
        } catch (NoSuchAlgorithmException e3) {
            throw new RuntimeException(e3);
        }
    }

    public static String makeMD5Hash(InputStream inputStream) {
        return makeHash(inputStream, MessageDigestAlgorithms.MD5);
    }

    public static String makeMD5Hash(String str) {
        try {
            return makeMD5Hash(str.getBytes("utf-8"));
        } catch (UnsupportedEncodingException e2) {
            throw new RuntimeException(e2);
        }
    }

    public static String makeMD5Hash(byte[] bArr) {
        return makeHash(bArr, MessageDigestAlgorithms.MD5);
    }

    public static String makeSHA1Hash(String str) {
        try {
            return makeSHA1Hash(str.getBytes("utf-8"));
        } catch (UnsupportedEncodingException e2) {
            throw new RuntimeException(e2);
        }
    }

    public static String makeSHA1Hash(byte[] bArr) {
        return makeHash(bArr, MessageDigestAlgorithms.SHA_1);
    }

    public static String makeSHA1HashBase64(byte[] bArr) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(MessageDigestAlgorithms.SHA_1);
            messageDigest.update(bArr, 0, bArr.length);
            return Base64.encodeToString(messageDigest.digest(), 11);
        } catch (NoSuchAlgorithmException e2) {
            throw new RuntimeException(e2);
        }
    }

    public static String makeSHA256Hash(byte[] bArr) {
        return makeHash(bArr, MessageDigestAlgorithms.SHA_256);
    }
}
