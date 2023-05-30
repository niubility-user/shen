package com.jingdong.jdsdk.secure;

import com.jingdong.sdk.oklog.OKLog;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

/* loaded from: classes14.dex */
public class DesUtil {
    private static final String DES = "DES";
    private static final String PADDING = "DES/ECB/PKCS5Padding";
    private static final String TAG = "DesUtil";

    public static String bytesTo16HexString(byte[] bArr) {
        StringBuilder sb = new StringBuilder("");
        if (bArr == null || bArr.length <= 0) {
            return "";
        }
        for (byte b : bArr) {
            String hexString = Integer.toHexString(b & 255);
            if (hexString.length() < 2) {
                sb.append(0);
            }
            sb.append(hexString);
        }
        return sb.toString();
    }

    public static byte[] decrypt(byte[] bArr, byte[] bArr2) throws Exception {
        SecureRandom secureRandom = new SecureRandom();
        SecretKey generateSecret = SecretKeyFactory.getInstance(DES).generateSecret(new DESKeySpec(bArr2));
        Cipher cipher = Cipher.getInstance(PADDING);
        cipher.init(2, generateSecret, secureRandom);
        return cipher.doFinal(bArr);
    }

    public static byte[] encrypt(byte[] bArr, byte[] bArr2) throws Exception {
        SecureRandom secureRandom = new SecureRandom();
        SecretKey generateSecret = SecretKeyFactory.getInstance(DES).generateSecret(new DESKeySpec(bArr2));
        Cipher cipher = Cipher.getInstance(PADDING);
        cipher.init(1, generateSecret, secureRandom);
        return cipher.doFinal(bArr);
    }

    public static void main(String[] strArr) {
    }

    public static final String decrypt(String str, String str2) {
        try {
            return new String(decrypt(Base64.decode(str.getBytes()), str2.getBytes()));
        } catch (Exception e2) {
            OKLog.e("Exception -->DesUtil ", "Exception -->DesUtil");
            OKLog.e(TAG, e2);
            return null;
        }
    }

    public static final String encrypt(String str, String str2) {
        try {
            return Base64.encodeBytes(encrypt(str.getBytes("utf-8"), str2.getBytes()));
        } catch (Exception e2) {
            OKLog.e("Exception -->DesUtil ", "Exception -->DesUtil");
            OKLog.e(TAG, e2);
            return null;
        }
    }

    public static final String decrypt(String str, String str2, String str3) {
        try {
            return new String(decrypt(Base64.decode(str.getBytes()), str2.getBytes()), str3);
        } catch (Exception e2) {
            OKLog.e("Exception -->DesUtil ", "Exception -->DesUtil");
            OKLog.e(TAG, e2);
            return null;
        }
    }
}
