package com.jd.lib.un.utils;

import android.util.Base64;
import com.jingdong.common.utils.security.JDKeyStore;
import java.io.File;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;

/* loaded from: classes16.dex */
public class UnEncryptUtils {
    private static char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    private static byte[] base64Decode(byte[] bArr) {
        return Base64.decode(bArr, 2);
    }

    public static byte[] base64Encode(byte[] bArr) {
        return Base64.encode(bArr, 2);
    }

    private static String bytes2HexString(byte[] bArr) {
        int length;
        if (bArr != null && (length = bArr.length) > 0) {
            char[] cArr = new char[length << 1];
            int i2 = 0;
            for (int i3 = 0; i3 < length; i3++) {
                int i4 = i2 + 1;
                char[] cArr2 = HEX_DIGITS;
                cArr[i2] = cArr2[(bArr[i3] >>> 4) & 15];
                i2 = i4 + 1;
                cArr[i4] = cArr2[bArr[i3] & 15];
            }
            return new String(cArr);
        }
        return "";
    }

    private static String bytes2HexStringLowercase(byte[] bArr) {
        String str = "";
        if (bArr == null) {
            return "";
        }
        for (byte b : bArr) {
            String hexString = Integer.toHexString(b & 255);
            if (hexString.length() == 1) {
                hexString = "0" + hexString;
            }
            str = str + hexString;
        }
        return str;
    }

    public static byte[] decrypt3DES(byte[] bArr, byte[] bArr2, String str, byte[] bArr3) {
        return symmetricTemplate(bArr, bArr2, "DESede", str, bArr3, false);
    }

    public static byte[] decryptAES(byte[] bArr, byte[] bArr2, String str, byte[] bArr3) {
        return symmetricTemplate(bArr, bArr2, JDKeyStore.KEY_TYPE_AES, str, bArr3, false);
    }

    public static byte[] decryptBase643DES(byte[] bArr, byte[] bArr2, String str, byte[] bArr3) {
        return decrypt3DES(base64Decode(bArr), bArr2, str, bArr3);
    }

    public static byte[] decryptBase64AES(byte[] bArr, byte[] bArr2, String str, byte[] bArr3) {
        return decryptAES(base64Decode(bArr), bArr2, str, bArr3);
    }

    public static byte[] decryptBase64DES(byte[] bArr, byte[] bArr2, String str, byte[] bArr3) {
        return decryptDES(base64Decode(bArr), bArr2, str, bArr3);
    }

    public static byte[] decryptDES(byte[] bArr, byte[] bArr2, String str, byte[] bArr3) {
        return symmetricTemplate(bArr, bArr2, "DES", str, bArr3, false);
    }

    public static byte[] decryptHexString3DES(String str, byte[] bArr, String str2, byte[] bArr2) {
        return decrypt3DES(hexString2Bytes(str), bArr, str2, bArr2);
    }

    public static byte[] decryptHexStringAES(String str, byte[] bArr, String str2, byte[] bArr2) {
        return decryptAES(hexString2Bytes(str), bArr, str2, bArr2);
    }

    public static byte[] decryptHexStringDES(String str, byte[] bArr, String str2, byte[] bArr2) {
        return decryptDES(hexString2Bytes(str), bArr, str2, bArr2);
    }

    public static byte[] encrypt3DES(byte[] bArr, byte[] bArr2, String str, byte[] bArr3) {
        return symmetricTemplate(bArr, bArr2, "DESede", str, bArr3, true);
    }

    public static byte[] encrypt3DES2Base64(byte[] bArr, byte[] bArr2, String str, byte[] bArr3) {
        return base64Encode(encrypt3DES(bArr, bArr2, str, bArr3));
    }

    public static String encrypt3DES2HexString(byte[] bArr, byte[] bArr2, String str, byte[] bArr3) {
        return bytes2HexString(encrypt3DES(bArr, bArr2, str, bArr3));
    }

    public static byte[] encryptAES(byte[] bArr, byte[] bArr2, String str, byte[] bArr3) {
        return symmetricTemplate(bArr, bArr2, JDKeyStore.KEY_TYPE_AES, str, bArr3, true);
    }

    public static byte[] encryptAES2Base64(byte[] bArr, byte[] bArr2, String str, byte[] bArr3) {
        return base64Encode(encryptAES(bArr, bArr2, str, bArr3));
    }

    public static String encryptAES2HexString(byte[] bArr, byte[] bArr2, String str, byte[] bArr3) {
        return bytes2HexString(encryptAES(bArr, bArr2, str, bArr3));
    }

    public static byte[] encryptDES(byte[] bArr, byte[] bArr2, String str, byte[] bArr3) {
        return symmetricTemplate(bArr, bArr2, "DES", str, bArr3, true);
    }

    public static byte[] encryptDES2Base64(byte[] bArr, byte[] bArr2, String str, byte[] bArr3) {
        return base64Encode(encryptDES(bArr, bArr2, str, bArr3));
    }

    public static String encryptDES2HexString(byte[] bArr, byte[] bArr2, String str, byte[] bArr3) {
        return bytes2HexString(encryptDES(bArr, bArr2, str, bArr3));
    }

    public static byte[] encryptHmacMD5(byte[] bArr, byte[] bArr2) {
        return hmacTemplate(bArr, bArr2, "HmacMD5");
    }

    public static String encryptHmacMD5ToString(String str, String str2) {
        return (str == null || str.length() == 0 || str2 == null || str2.length() == 0) ? "" : encryptHmacMD5ToString(str.getBytes(), str2.getBytes());
    }

    public static byte[] encryptHmacSHA1(byte[] bArr, byte[] bArr2) {
        return hmacTemplate(bArr, bArr2, "HmacSHA1");
    }

    public static String encryptHmacSHA1ToString(String str, String str2, boolean z) {
        return (str == null || str.length() == 0 || str2 == null || str2.length() == 0) ? "" : encryptHmacSHA1ToString(str.getBytes(), str2.getBytes(), z);
    }

    public static byte[] encryptHmacSHA224(byte[] bArr, byte[] bArr2) {
        return hmacTemplate(bArr, bArr2, "HmacSHA224");
    }

    public static String encryptHmacSHA224ToString(String str, String str2) {
        return (str == null || str.length() == 0 || str2 == null || str2.length() == 0) ? "" : encryptHmacSHA224ToString(str.getBytes(), str2.getBytes());
    }

    public static byte[] encryptHmacSHA256(byte[] bArr, byte[] bArr2) {
        return hmacTemplate(bArr, bArr2, "HmacSHA256");
    }

    public static String encryptHmacSHA256ToString(String str, String str2) {
        return (str == null || str.length() == 0 || str2 == null || str2.length() == 0) ? "" : encryptHmacSHA256ToString(str.getBytes(), str2.getBytes());
    }

    public static byte[] encryptHmacSHA384(byte[] bArr, byte[] bArr2) {
        return hmacTemplate(bArr, bArr2, "HmacSHA384");
    }

    public static String encryptHmacSHA384ToString(String str, String str2) {
        return (str == null || str.length() == 0 || str2 == null || str2.length() == 0) ? "" : encryptHmacSHA384ToString(str.getBytes(), str2.getBytes());
    }

    public static byte[] encryptHmacSHA512(byte[] bArr, byte[] bArr2) {
        return hmacTemplate(bArr, bArr2, "HmacSHA512");
    }

    public static String encryptHmacSHA512ToString(String str, String str2) {
        return (str == null || str.length() == 0 || str2 == null || str2.length() == 0) ? "" : encryptHmacSHA512ToString(str.getBytes(), str2.getBytes());
    }

    public static byte[] encryptMD2(byte[] bArr) {
        return hashTemplate(bArr, MessageDigestAlgorithms.MD2);
    }

    public static String encryptMD2ToString(byte[] bArr) {
        return bytes2HexString(encryptMD2(bArr));
    }

    public static byte[] encryptMD5(byte[] bArr) {
        return hashTemplate(bArr, MessageDigestAlgorithms.MD5);
    }

    public static byte[] encryptMD5File(String str) {
        return encryptMD5File(UnStringUtils.isSpace(str) ? null : new File(str));
    }

    public static String encryptMD5File2String(String str) {
        return encryptMD5File2String(UnStringUtils.isSpace(str) ? null : new File(str));
    }

    public static String encryptMD5ToString(String str) {
        return (str == null || str.length() == 0) ? "" : encryptMD5ToString(str.getBytes());
    }

    public static byte[] encryptSHA1(byte[] bArr) {
        return hashTemplate(bArr, "SHA1");
    }

    public static String encryptSHA1ToString(String str) {
        return (str == null || str.length() == 0) ? "" : encryptSHA1ToString(str.getBytes());
    }

    private static byte[] encryptSHA224(byte[] bArr) {
        return hashTemplate(bArr, "SHA224");
    }

    private static String encryptSHA224ToString(String str) {
        return (str == null || str.length() == 0) ? "" : encryptSHA224ToString(str.getBytes());
    }

    public static byte[] encryptSHA256(byte[] bArr) {
        return hashTemplate(bArr, "SHA256");
    }

    public static String encryptSHA256ToString(String str) {
        return (str == null || str.length() == 0) ? "" : encryptSHA256ToString(str.getBytes());
    }

    public static byte[] encryptSHA384(byte[] bArr) {
        return hashTemplate(bArr, "SHA384");
    }

    public static String encryptSHA384ToString(String str) {
        return (str == null || str.length() == 0) ? "" : encryptSHA384ToString(str.getBytes());
    }

    public static byte[] encryptSHA512(byte[] bArr) {
        return hashTemplate(bArr, "SHA512");
    }

    public static String encryptSHA512ToString(String str) {
        return (str == null || str.length() == 0) ? "" : encryptSHA512ToString(str.getBytes());
    }

    private static byte[] hashTemplate(byte[] bArr, String str) {
        if (bArr != null && bArr.length > 0) {
            try {
                MessageDigest messageDigest = MessageDigest.getInstance(str);
                messageDigest.update(bArr);
                return messageDigest.digest();
            } catch (NoSuchAlgorithmException e2) {
                e2.printStackTrace();
            }
        }
        return null;
    }

    private static int hex2Dec(char c2) {
        if (c2 < '0' || c2 > '9') {
            if (c2 < 'A' || c2 > 'F') {
                throw new IllegalArgumentException();
            }
            return (c2 - 'A') + 10;
        }
        return c2 - '0';
    }

    public static byte[] hexString2Bytes(String str) {
        if (UnStringUtils.isSpace(str)) {
            return null;
        }
        int length = str.length();
        if (length % 2 != 0) {
            str = "0" + str;
            length++;
        }
        char[] charArray = str.toUpperCase().toCharArray();
        byte[] bArr = new byte[length >> 1];
        for (int i2 = 0; i2 < length; i2 += 2) {
            bArr[i2 >> 1] = (byte) ((hex2Dec(charArray[i2]) << 4) | hex2Dec(charArray[i2 + 1]));
        }
        return bArr;
    }

    private static byte[] hmacTemplate(byte[] bArr, byte[] bArr2, String str) {
        if (bArr != null && bArr.length != 0 && bArr2 != null && bArr2.length != 0) {
            try {
                SecretKeySpec secretKeySpec = new SecretKeySpec(bArr2, str);
                Mac mac = Mac.getInstance(str);
                mac.init(secretKeySpec);
                return mac.doFinal(bArr);
            } catch (InvalidKeyException | NoSuchAlgorithmException e2) {
                e2.printStackTrace();
            }
        }
        return null;
    }

    private static byte[] symmetricTemplate(byte[] bArr, byte[] bArr2, String str, String str2, byte[] bArr3, boolean z) {
        if (bArr != null && bArr.length != 0 && bArr2 != null && bArr2.length != 0) {
            try {
                SecretKeySpec secretKeySpec = new SecretKeySpec(bArr2, str);
                Cipher cipher = Cipher.getInstance(str2);
                int i2 = 1;
                if (bArr3 != null && bArr3.length != 0) {
                    IvParameterSpec ivParameterSpec = new IvParameterSpec(bArr3);
                    if (!z) {
                        i2 = 2;
                    }
                    cipher.init(i2, secretKeySpec, ivParameterSpec);
                    return cipher.doFinal(bArr);
                }
                i2 = 2;
                cipher.init(i2, secretKeySpec);
                return cipher.doFinal(bArr);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        return null;
    }

    public static String encryptMD2ToString(String str) {
        return (str == null || str.length() == 0) ? "" : encryptMD2ToString(str.getBytes());
    }

    public static String encryptHmacMD5ToString(byte[] bArr, byte[] bArr2) {
        return bytes2HexString(encryptHmacMD5(bArr, bArr2));
    }

    public static String encryptHmacSHA1ToString(byte[] bArr, byte[] bArr2, boolean z) {
        if (z) {
            return bytes2HexStringLowercase(encryptHmacSHA1(bArr, bArr2));
        }
        return bytes2HexString(encryptHmacSHA1(bArr, bArr2));
    }

    public static String encryptHmacSHA224ToString(byte[] bArr, byte[] bArr2) {
        return bytes2HexString(encryptHmacSHA224(bArr, bArr2));
    }

    public static String encryptHmacSHA256ToString(byte[] bArr, byte[] bArr2) {
        return bytes2HexString(encryptHmacSHA256(bArr, bArr2));
    }

    public static String encryptHmacSHA384ToString(byte[] bArr, byte[] bArr2) {
        return bytes2HexString(encryptHmacSHA384(bArr, bArr2));
    }

    public static String encryptHmacSHA512ToString(byte[] bArr, byte[] bArr2) {
        return bytes2HexString(encryptHmacSHA512(bArr, bArr2));
    }

    /* JADX WARN: Not initialized variable reg: 2, insn: 0x005b: MOVE (r0 I:??[OBJECT, ARRAY]) = (r2 I:??[OBJECT, ARRAY]), block:B:44:0x005b */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0052 A[Catch: IOException -> 0x004e, TRY_LEAVE, TryCatch #10 {IOException -> 0x004e, blocks: (B:35:0x004a, B:39:0x0052), top: B:61:0x004a }] */
    /* JADX WARN: Removed duplicated region for block: B:61:0x004a A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static byte[] encryptMD5File(java.io.File r4) {
        /*
            r0 = 0
            if (r4 != 0) goto L4
            return r0
        L4:
            java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L3d java.io.IOException -> L40 java.security.NoSuchAlgorithmException -> L42
            r1.<init>(r4)     // Catch: java.lang.Throwable -> L3d java.io.IOException -> L40 java.security.NoSuchAlgorithmException -> L42
            java.lang.String r4 = "MD5"
            java.security.MessageDigest r4 = java.security.MessageDigest.getInstance(r4)     // Catch: java.lang.Throwable -> L36 java.io.IOException -> L38 java.security.NoSuchAlgorithmException -> L3a
            java.security.DigestInputStream r2 = new java.security.DigestInputStream     // Catch: java.lang.Throwable -> L36 java.io.IOException -> L38 java.security.NoSuchAlgorithmException -> L3a
            r2.<init>(r1, r4)     // Catch: java.lang.Throwable -> L36 java.io.IOException -> L38 java.security.NoSuchAlgorithmException -> L3a
            r4 = 262144(0x40000, float:3.67342E-40)
            byte[] r4 = new byte[r4]     // Catch: java.io.IOException -> L32 java.security.NoSuchAlgorithmException -> L34 java.lang.Throwable -> L5a
        L18:
            int r3 = r2.read(r4)     // Catch: java.io.IOException -> L32 java.security.NoSuchAlgorithmException -> L34 java.lang.Throwable -> L5a
            if (r3 > 0) goto L18
            java.security.MessageDigest r4 = r2.getMessageDigest()     // Catch: java.io.IOException -> L32 java.security.NoSuchAlgorithmException -> L34 java.lang.Throwable -> L5a
            byte[] r4 = r4.digest()     // Catch: java.io.IOException -> L32 java.security.NoSuchAlgorithmException -> L34 java.lang.Throwable -> L5a
            r2.close()     // Catch: java.io.IOException -> L2d
            r1.close()     // Catch: java.io.IOException -> L2d
            goto L31
        L2d:
            r0 = move-exception
            r0.printStackTrace()
        L31:
            return r4
        L32:
            r4 = move-exception
            goto L45
        L34:
            r4 = move-exception
            goto L45
        L36:
            r4 = move-exception
            goto L5c
        L38:
            r4 = move-exception
            goto L3b
        L3a:
            r4 = move-exception
        L3b:
            r2 = r0
            goto L45
        L3d:
            r4 = move-exception
            r1 = r0
            goto L5c
        L40:
            r4 = move-exception
            goto L43
        L42:
            r4 = move-exception
        L43:
            r1 = r0
            r2 = r1
        L45:
            r4.printStackTrace()     // Catch: java.lang.Throwable -> L5a
            if (r2 == 0) goto L50
            r2.close()     // Catch: java.io.IOException -> L4e
            goto L50
        L4e:
            r4 = move-exception
            goto L56
        L50:
            if (r1 == 0) goto L59
            r1.close()     // Catch: java.io.IOException -> L4e
            goto L59
        L56:
            r4.printStackTrace()
        L59:
            return r0
        L5a:
            r4 = move-exception
            r0 = r2
        L5c:
            if (r0 == 0) goto L64
            r0.close()     // Catch: java.io.IOException -> L62
            goto L64
        L62:
            r0 = move-exception
            goto L6a
        L64:
            if (r1 == 0) goto L6d
            r1.close()     // Catch: java.io.IOException -> L62
            goto L6d
        L6a:
            r0.printStackTrace()
        L6d:
            goto L6f
        L6e:
            throw r4
        L6f:
            goto L6e
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.lib.un.utils.UnEncryptUtils.encryptMD5File(java.io.File):byte[]");
    }

    public static String encryptMD5File2String(File file) {
        return bytes2HexString(encryptMD5File(file));
    }

    public static String encryptMD5ToString(byte[] bArr) {
        return bytes2HexString(encryptMD5(bArr));
    }

    public static String encryptSHA1ToString(byte[] bArr) {
        return bytes2HexString(encryptSHA1(bArr));
    }

    private static String encryptSHA224ToString(byte[] bArr) {
        return bytes2HexString(encryptSHA224(bArr));
    }

    public static String encryptSHA256ToString(byte[] bArr) {
        return bytes2HexString(encryptSHA256(bArr));
    }

    public static String encryptSHA384ToString(byte[] bArr) {
        return bytes2HexString(encryptSHA384(bArr));
    }

    public static String encryptSHA512ToString(byte[] bArr) {
        return bytes2HexString(encryptSHA512(bArr));
    }
}
