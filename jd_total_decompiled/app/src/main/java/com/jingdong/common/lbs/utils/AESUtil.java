package com.jingdong.common.lbs.utils;

import android.util.Base64;
import com.jingdong.common.utils.security.JDKeyStore;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: classes.dex */
public class AESUtil {
    public static String byte2HexStr(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer();
        for (byte b : bArr) {
            String hexString = Integer.toHexString(b & 255);
            if (hexString.length() == 1) {
                hexString = "0".concat(String.valueOf(hexString));
            }
            stringBuffer.append(hexString.toUpperCase());
        }
        return stringBuffer.toString();
    }

    public static String decrypt(String str, String str2) {
        try {
            byte[] decode = Base64.decode(str.getBytes(), 11);
            SecretKeySpec secretKeySpec = new SecretKeySpec(hexStr2Byte(str2), JDKeyStore.KEY_TYPE_AES);
            IvParameterSpec ivParameterSpec = new IvParameterSpec("\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000".getBytes());
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(2, secretKeySpec, ivParameterSpec);
            return new String(cipher.doFinal(decode));
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static String decryptWithVersion(String str, String str2, String str3) {
        try {
            String[] split = str3.split("\\.");
            byte[] bArr = new byte[16];
            if (split.length > 2) {
                bArr[13] = (byte) Integer.parseInt(split[0]);
                bArr[14] = (byte) Integer.parseInt(split[1]);
                bArr[15] = (byte) Integer.parseInt(split[2]);
            }
            byte[] decode = Base64.decode(str.getBytes(), 11);
            SecretKeySpec secretKeySpec = new SecretKeySpec(Base64.decode(str2.getBytes(), 11), JDKeyStore.KEY_TYPE_AES);
            IvParameterSpec ivParameterSpec = new IvParameterSpec(bArr);
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(2, secretKeySpec, ivParameterSpec);
            return new String(cipher.doFinal(decode));
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static String encrypt(String str, String str2) {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(hexStr2Byte(str2), JDKeyStore.KEY_TYPE_AES);
            IvParameterSpec ivParameterSpec = new IvParameterSpec("\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000".getBytes());
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(1, secretKeySpec, ivParameterSpec);
            return new String(Base64.encode(cipher.doFinal(str.getBytes("UTF-8")), 11));
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static String encryptWithVersion(String str, String str2, String str3) {
        try {
            String[] split = str3.split("\\.");
            byte[] bArr = new byte[16];
            if (split.length > 2) {
                bArr[13] = (byte) Integer.parseInt(split[0]);
                bArr[14] = (byte) Integer.parseInt(split[1]);
                bArr[15] = (byte) Integer.parseInt(split[2]);
            }
            SecretKeySpec secretKeySpec = new SecretKeySpec(Base64.decode(str2.getBytes(), 11), JDKeyStore.KEY_TYPE_AES);
            IvParameterSpec ivParameterSpec = new IvParameterSpec(bArr);
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(1, secretKeySpec, ivParameterSpec);
            return new String(Base64.encode(cipher.doFinal(str.getBytes("UTF-8")), 11));
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static String getAESKey() {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance(JDKeyStore.KEY_TYPE_AES);
            keyGenerator.init(128);
            return byte2HexStr(keyGenerator.generateKey().getEncoded());
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static byte[] hexStr2Byte(String str) {
        if (str.length() <= 0) {
            return null;
        }
        byte[] bArr = new byte[str.length() / 2];
        for (int i2 = 0; i2 < str.length() / 2; i2++) {
            int i3 = i2 * 2;
            int i4 = i3 + 1;
            bArr[i2] = (byte) ((Integer.parseInt(str.substring(i3, i4), 16) * 16) + Integer.parseInt(str.substring(i4, i3 + 2), 16));
        }
        return bArr;
    }
}
