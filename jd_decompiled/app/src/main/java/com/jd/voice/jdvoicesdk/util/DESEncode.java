package com.jd.voice.jdvoicesdk.util;

import java.util.Random;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

/* loaded from: classes18.dex */
public class DESEncode {
    private static final String DES = "DES";
    private static final String ENCRYPT_ALGORITHM = "DES/CBC/PKCS5Padding";
    private static final char[] bcdLookup = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    public static String byte2hex(byte[] bArr) {
        String str = "";
        for (byte b : bArr) {
            String hexString = Integer.toHexString(b & 255);
            str = hexString.length() == 1 ? String.valueOf(str) + "0" + hexString : String.valueOf(str) + hexString;
        }
        return str.toUpperCase();
    }

    public static final String bytesToHexStr(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer(bArr.length * 2);
        for (int i2 = 0; i2 < bArr.length; i2++) {
            char[] cArr = bcdLookup;
            stringBuffer.append(cArr[(bArr[i2] >>> 4) & 15]);
            stringBuffer.append(cArr[bArr[i2] & 15]);
        }
        return stringBuffer.toString();
    }

    public static byte[] decrypt(byte[] bArr, byte[] bArr2) throws Exception {
        IvParameterSpec ivParameterSpec = new IvParameterSpec(bArr2);
        SecretKey generateSecret = SecretKeyFactory.getInstance(DES).generateSecret(new DESKeySpec(bArr2));
        Cipher cipher = Cipher.getInstance(ENCRYPT_ALGORITHM);
        cipher.init(2, generateSecret, ivParameterSpec);
        return cipher.doFinal(bArr);
    }

    public static byte[] encrypt(byte[] bArr, byte[] bArr2) throws Exception {
        IvParameterSpec ivParameterSpec = new IvParameterSpec(bArr2);
        SecretKey generateSecret = SecretKeyFactory.getInstance(DES).generateSecret(new DESKeySpec(bArr2));
        Cipher cipher = Cipher.getInstance(ENCRYPT_ALGORITHM);
        cipher.init(1, generateSecret, ivParameterSpec);
        return cipher.doFinal(bArr);
    }

    public static String getRandomNumber() {
        Random random = new Random();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i2 = 0; i2 < 8; i2++) {
            stringBuffer.append(random.nextInt(9));
        }
        return stringBuffer.toString();
    }

    public static byte[] hex2byte(byte[] bArr) {
        if (bArr.length % 2 == 0) {
            byte[] bArr2 = new byte[bArr.length / 2];
            for (int i2 = 0; i2 < bArr.length; i2 += 2) {
                bArr2[i2 / 2] = (byte) Integer.parseInt(new String(bArr, i2, 2), 16);
            }
            return bArr2;
        }
        throw new IllegalArgumentException("\u957f\u5ea6\u4e0d\u662f\u5076\u6570");
    }

    public static final byte[] hexStrToBytes(String str) {
        int length = str.length() / 2;
        byte[] bArr = new byte[length];
        for (int i2 = 0; i2 < length; i2++) {
            int i3 = i2 * 2;
            bArr[i2] = (byte) Integer.parseInt(str.substring(i3, i3 + 2), 16);
        }
        return bArr;
    }
}
