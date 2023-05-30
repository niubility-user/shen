package com.jingdong.app.mall.bundle.smile.utils;

import com.jingdong.sdk.oklog.OKLog;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;

/* loaded from: classes3.dex */
public class Md5EncryptUtil {
    private static final String TAG = "Md5EncryptUtil";
    protected static char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    protected static MessageDigest messagedigest;

    static {
        messagedigest = null;
        try {
            messagedigest = MessageDigest.getInstance(MessageDigestAlgorithms.MD5);
        } catch (NoSuchAlgorithmException e2) {
            System.err.println(Md5EncryptUtil.class.getName() + "\u521d\u59cb\u5316\u5931\u8d25\uff0cMessageDigest\u4e0d\u652f\u6301MD5Util\u3002");
            e2.printStackTrace();
        }
    }

    private static void appendHexPair(byte b, StringBuffer stringBuffer) {
        char[] cArr = hexDigits;
        char c2 = cArr[(b & 240) >> 4];
        char c3 = cArr[b & 15];
        stringBuffer.append(c2);
        stringBuffer.append(c3);
    }

    private static String bufferToHex(byte[] bArr) {
        return bufferToHex(bArr, 0, bArr.length);
    }

    public static boolean checkPassword(String str, String str2) {
        return str.equals(str2);
    }

    public static String getFileMD5String(File file) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(file);
        byte[] bArr = new byte[4096];
        while (true) {
            try {
                try {
                    int read = fileInputStream.read(bArr);
                    if (read <= 0) {
                        break;
                    }
                    messagedigest.update(bArr, 0, read);
                } catch (Exception e2) {
                    OKLog.e(TAG, e2.getMessage());
                }
            } finally {
                fileInputStream.close();
            }
        }
        return bufferToHex(messagedigest.digest());
    }

    public static String getMD5String(String str) {
        return getMD5String(str.getBytes());
    }

    public static void main(String[] strArr) throws IOException {
        long currentTimeMillis = System.currentTimeMillis();
        String fileMD5String = getFileMD5String(new File("H:/12345.txt"));
        String fileMD5String2 = getFileMD5String(new File("H:/12345(2).txt"));
        String fileMD5String3 = getFileMD5String(new File("H:/12345(3).txt"));
        String fileMD5String4 = getFileMD5String(new File("H:/1.zip"));
        String fileMD5String5 = getFileMD5String(new File("H:/2.zip"));
        OKLog.d(TAG, "MD5:" + fileMD5String);
        OKLog.d(TAG, "MD5:" + fileMD5String2);
        OKLog.d(TAG, "MD5:" + fileMD5String3);
        OKLog.d(TAG, "MD5:" + fileMD5String4);
        OKLog.d(TAG, "MD5:" + fileMD5String5);
        OKLog.d(TAG, "\u4e24\u4e2a\u6587\u4ef6\u540d\u4e0d\u540c\uff0c\u5185\u540c\u76f8\u540c" + checkPassword(fileMD5String, fileMD5String2));
        OKLog.d(TAG, "\u6587\u4ef6\u540d\u4e0d\u540c\uff0c\u5185\u5bb9\u4e0d\u540c" + checkPassword(fileMD5String, fileMD5String3));
        OKLog.d(TAG, "\u6d4b\u8bd5\u538b\u7f29\u5305,\u5185\u5bb9\u4e0d\u540c" + checkPassword(fileMD5String4, fileMD5String5));
        OKLog.d(TAG, "md5:" + fileMD5String + " time:" + ((System.currentTimeMillis() - currentTimeMillis) / 1000) + "s");
    }

    private static String bufferToHex(byte[] bArr, int i2, int i3) {
        StringBuffer stringBuffer = new StringBuffer(i3 * 2);
        int i4 = i3 + i2;
        while (i2 < i4) {
            appendHexPair(bArr[i2], stringBuffer);
            i2++;
        }
        return stringBuffer.toString();
    }

    public static String getMD5String(byte[] bArr) {
        messagedigest.update(bArr);
        return bufferToHex(messagedigest.digest());
    }
}
