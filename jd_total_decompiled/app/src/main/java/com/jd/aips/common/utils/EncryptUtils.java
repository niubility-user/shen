package com.jd.aips.common.utils;

import android.util.Base64;
import com.jingdong.common.jdreactFramework.utils.RSAUtils;
import com.jingdong.common.utils.security.JDKeyStore;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.Mac;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;

/* loaded from: classes12.dex */
public final class EncryptUtils {
    private static final char[] a = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    private EncryptUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    private static byte[] a(byte[] bArr, String str) {
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

    private static boolean b(String str) {
        if (str == null) {
            return true;
        }
        int length = str.length();
        for (int i2 = 0; i2 < length; i2++) {
            if (!Character.isWhitespace(str.charAt(i2))) {
                return false;
            }
        }
        return true;
    }

    public static byte[] decrypt3DES(byte[] bArr, byte[] bArr2, String str, byte[] bArr3) {
        return a(bArr, bArr2, "DESede", str, bArr3, false);
    }

    public static byte[] decryptAES(byte[] bArr, byte[] bArr2, String str, byte[] bArr3) {
        return a(bArr, bArr2, JDKeyStore.KEY_TYPE_AES, str, bArr3, false);
    }

    public static byte[] decryptBase64AES(byte[] bArr, byte[] bArr2, String str, byte[] bArr3) {
        return decryptAES(Base64.decode(bArr, 2), bArr2, str, bArr3);
    }

    public static byte[] decryptBase64DES(byte[] bArr, byte[] bArr2, String str, byte[] bArr3) {
        return decryptDES(Base64.decode(bArr, 2), bArr2, str, bArr3);
    }

    public static byte[] decryptBase64RSA(byte[] bArr, byte[] bArr2, boolean z, String str) {
        return decryptRSA(Base64.decode(bArr, 2), bArr2, z, str);
    }

    public static byte[] decryptBase64_3DES(byte[] bArr, byte[] bArr2, String str, byte[] bArr3) {
        return decrypt3DES(Base64.decode(bArr, 2), bArr2, str, bArr3);
    }

    public static byte[] decryptDES(byte[] bArr, byte[] bArr2, String str, byte[] bArr3) {
        return a(bArr, bArr2, "DES", str, bArr3, false);
    }

    public static byte[] decryptHexString3DES(String str, byte[] bArr, String str2, byte[] bArr2) {
        return decrypt3DES(a(str), bArr, str2, bArr2);
    }

    public static byte[] decryptHexStringAES(String str, byte[] bArr, String str2, byte[] bArr2) {
        return decryptAES(a(str), bArr, str2, bArr2);
    }

    public static byte[] decryptHexStringDES(String str, byte[] bArr, String str2, byte[] bArr2) {
        return decryptDES(a(str), bArr, str2, bArr2);
    }

    public static byte[] decryptHexStringRSA(String str, byte[] bArr, boolean z, String str2) {
        return decryptRSA(a(str), bArr, z, str2);
    }

    public static byte[] decryptRSA(byte[] bArr, byte[] bArr2, boolean z, String str) {
        return a(bArr, bArr2, z, str, false);
    }

    public static byte[] encrypt3DES(byte[] bArr, byte[] bArr2, String str, byte[] bArr3) {
        return a(bArr, bArr2, "DESede", str, bArr3, true);
    }

    public static byte[] encrypt3DES2Base64(byte[] bArr, byte[] bArr2, String str, byte[] bArr3) {
        return Base64.encode(encrypt3DES(bArr, bArr2, str, bArr3), 2);
    }

    public static String encrypt3DES2HexString(byte[] bArr, byte[] bArr2, String str, byte[] bArr3) {
        return a(encrypt3DES(bArr, bArr2, str, bArr3));
    }

    public static byte[] encryptAES(byte[] bArr, byte[] bArr2, String str, byte[] bArr3) {
        return a(bArr, bArr2, JDKeyStore.KEY_TYPE_AES, str, bArr3, true);
    }

    public static byte[] encryptAES2Base64(byte[] bArr, byte[] bArr2, String str, byte[] bArr3) {
        return Base64.encode(encryptAES(bArr, bArr2, str, bArr3), 2);
    }

    public static String encryptAES2HexString(byte[] bArr, byte[] bArr2, String str, byte[] bArr3) {
        return a(encryptAES(bArr, bArr2, str, bArr3));
    }

    public static byte[] encryptDES(byte[] bArr, byte[] bArr2, String str, byte[] bArr3) {
        return a(bArr, bArr2, "DES", str, bArr3, true);
    }

    public static byte[] encryptDES2Base64(byte[] bArr, byte[] bArr2, String str, byte[] bArr3) {
        return Base64.encode(encryptDES(bArr, bArr2, str, bArr3), 2);
    }

    public static String encryptDES2HexString(byte[] bArr, byte[] bArr2, String str, byte[] bArr3) {
        return a(encryptDES(bArr, bArr2, str, bArr3));
    }

    public static byte[] encryptHmacMD5(byte[] bArr, byte[] bArr2) {
        return a(bArr, bArr2, "HmacMD5");
    }

    public static String encryptHmacMD5ToString(String str, String str2) {
        return (str == null || str.length() == 0 || str2 == null || str2.length() == 0) ? "" : encryptHmacMD5ToString(str.getBytes(), str2.getBytes());
    }

    public static byte[] encryptHmacSHA1(byte[] bArr, byte[] bArr2) {
        return a(bArr, bArr2, "HmacSHA1");
    }

    public static String encryptHmacSHA1ToString(String str, String str2) {
        return (str == null || str.length() == 0 || str2 == null || str2.length() == 0) ? "" : encryptHmacSHA1ToString(str.getBytes(), str2.getBytes());
    }

    public static byte[] encryptHmacSHA224(byte[] bArr, byte[] bArr2) {
        return a(bArr, bArr2, "HmacSHA224");
    }

    public static String encryptHmacSHA224ToString(String str, String str2) {
        return (str == null || str.length() == 0 || str2 == null || str2.length() == 0) ? "" : encryptHmacSHA224ToString(str.getBytes(), str2.getBytes());
    }

    public static byte[] encryptHmacSHA256(byte[] bArr, byte[] bArr2) {
        return a(bArr, bArr2, "HmacSHA256");
    }

    public static String encryptHmacSHA256ToString(String str, String str2) {
        return (str == null || str.length() == 0 || str2 == null || str2.length() == 0) ? "" : encryptHmacSHA256ToString(str.getBytes(), str2.getBytes());
    }

    public static byte[] encryptHmacSHA384(byte[] bArr, byte[] bArr2) {
        return a(bArr, bArr2, "HmacSHA384");
    }

    public static String encryptHmacSHA384ToString(String str, String str2) {
        return (str == null || str.length() == 0 || str2 == null || str2.length() == 0) ? "" : encryptHmacSHA384ToString(str.getBytes(), str2.getBytes());
    }

    public static byte[] encryptHmacSHA512(byte[] bArr, byte[] bArr2) {
        return a(bArr, bArr2, "HmacSHA512");
    }

    public static String encryptHmacSHA512ToString(String str, String str2) {
        return (str == null || str.length() == 0 || str2 == null || str2.length() == 0) ? "" : encryptHmacSHA512ToString(str.getBytes(), str2.getBytes());
    }

    public static byte[] encryptMD2(byte[] bArr) {
        return a(bArr, MessageDigestAlgorithms.MD2);
    }

    public static String encryptMD2ToString(String str) {
        return (str == null || str.length() == 0) ? "" : encryptMD2ToString(str.getBytes());
    }

    public static byte[] encryptMD5(byte[] bArr) {
        return a(bArr, MessageDigestAlgorithms.MD5);
    }

    public static byte[] encryptMD5File(String str) {
        return encryptMD5File(b(str) ? null : new File(str));
    }

    public static String encryptMD5File2String(String str) {
        return encryptMD5File2String(b(str) ? null : new File(str));
    }

    public static String encryptMD5ToString(String str) {
        return (str == null || str.length() == 0) ? "" : encryptMD5ToString(str.getBytes());
    }

    public static byte[] encryptRSA(byte[] bArr, byte[] bArr2, boolean z, String str) {
        return a(bArr, bArr2, z, str, true);
    }

    public static byte[] encryptRSA2Base64(byte[] bArr, byte[] bArr2, boolean z, String str) {
        return Base64.encode(encryptRSA(bArr, bArr2, z, str), 2);
    }

    public static String encryptRSA2HexString(byte[] bArr, byte[] bArr2, boolean z, String str) {
        return a(encryptRSA(bArr, bArr2, z, str));
    }

    public static byte[] encryptSHA1(byte[] bArr) {
        return a(bArr, "SHA1");
    }

    public static String encryptSHA1ToString(String str) {
        return (str == null || str.length() == 0) ? "" : encryptSHA1ToString(str.getBytes());
    }

    public static byte[] encryptSHA224(byte[] bArr) {
        return a(bArr, "SHA224");
    }

    public static String encryptSHA224ToString(String str) {
        return (str == null || str.length() == 0) ? "" : encryptSHA224ToString(str.getBytes());
    }

    public static byte[] encryptSHA256(byte[] bArr) {
        return a(bArr, "SHA256");
    }

    public static String encryptSHA256ToString(String str) {
        return (str == null || str.length() == 0) ? "" : encryptSHA256ToString(str.getBytes());
    }

    public static byte[] encryptSHA384(byte[] bArr) {
        return a(bArr, "SHA384");
    }

    public static String encryptSHA384ToString(String str) {
        return (str == null || str.length() == 0) ? "" : encryptSHA384ToString(str.getBytes());
    }

    public static byte[] encryptSHA512(byte[] bArr) {
        return a(bArr, "SHA512");
    }

    public static String encryptSHA512ToString(String str) {
        return (str == null || str.length() == 0) ? "" : encryptSHA512ToString(str.getBytes());
    }

    public static String encryptHmacMD5ToString(byte[] bArr, byte[] bArr2) {
        return a(encryptHmacMD5(bArr, bArr2));
    }

    public static String encryptHmacSHA1ToString(byte[] bArr, byte[] bArr2) {
        return a(encryptHmacSHA1(bArr, bArr2));
    }

    public static String encryptHmacSHA224ToString(byte[] bArr, byte[] bArr2) {
        return a(encryptHmacSHA224(bArr, bArr2));
    }

    public static String encryptHmacSHA256ToString(byte[] bArr, byte[] bArr2) {
        return a(encryptHmacSHA256(bArr, bArr2));
    }

    public static String encryptHmacSHA384ToString(byte[] bArr, byte[] bArr2) {
        return a(encryptHmacSHA384(bArr, bArr2));
    }

    public static String encryptHmacSHA512ToString(byte[] bArr, byte[] bArr2) {
        return a(encryptHmacSHA512(bArr, bArr2));
    }

    public static String encryptMD2ToString(byte[] bArr) {
        return a(encryptMD2(bArr));
    }

    /* JADX WARN: Not initialized variable reg: 1, insn: 0x0030: MOVE (r0 I:??[OBJECT, ARRAY]) = (r1 I:??[OBJECT, ARRAY]), block:B:16:0x0030 */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0041 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static byte[] encryptMD5File(File file) {
        FileInputStream fileInputStream;
        FileInputStream fileInputStream2;
        FileInputStream fileInputStream3 = null;
        try {
            if (file == null) {
                return null;
            }
            try {
                fileInputStream2 = new FileInputStream(file);
            } catch (IOException e2) {
                e = e2;
                fileInputStream2 = null;
                e.printStackTrace();
                if (fileInputStream2 != null) {
                }
                return null;
            } catch (NoSuchAlgorithmException e3) {
                e = e3;
                fileInputStream2 = null;
                e.printStackTrace();
                if (fileInputStream2 != null) {
                }
                return null;
            } catch (Throwable th) {
                th = th;
                if (fileInputStream3 != null) {
                    try {
                        fileInputStream3.close();
                    } catch (IOException e4) {
                        e4.printStackTrace();
                    }
                }
                throw th;
            }
            try {
                DigestInputStream digestInputStream = new DigestInputStream(fileInputStream2, MessageDigest.getInstance(MessageDigestAlgorithms.MD5));
                do {
                } while (digestInputStream.read(new byte[262144]) > 0);
                byte[] digest = digestInputStream.getMessageDigest().digest();
                try {
                    fileInputStream2.close();
                } catch (IOException e5) {
                    e5.printStackTrace();
                }
                return digest;
            } catch (IOException e6) {
                e = e6;
                e.printStackTrace();
                if (fileInputStream2 != null) {
                    try {
                        fileInputStream2.close();
                    } catch (IOException e7) {
                        e7.printStackTrace();
                    }
                }
                return null;
            } catch (NoSuchAlgorithmException e8) {
                e = e8;
                e.printStackTrace();
                if (fileInputStream2 != null) {
                }
                return null;
            }
        } catch (Throwable th2) {
            th = th2;
            fileInputStream3 = fileInputStream;
        }
    }

    public static String encryptMD5File2String(File file) {
        return a(encryptMD5File(file));
    }

    public static String encryptMD5ToString(String str, String str2) {
        if (str == null && str2 == null) {
            return "";
        }
        if (str2 == null) {
            return a(encryptMD5(str.getBytes()));
        }
        if (str == null) {
            return a(encryptMD5(str2.getBytes()));
        }
        return a(encryptMD5((str + str2).getBytes()));
    }

    public static String encryptSHA1ToString(byte[] bArr) {
        return a(encryptSHA1(bArr));
    }

    public static String encryptSHA224ToString(byte[] bArr) {
        return a(encryptSHA224(bArr));
    }

    public static String encryptSHA256ToString(byte[] bArr) {
        return a(encryptSHA256(bArr));
    }

    public static String encryptSHA384ToString(byte[] bArr) {
        return a(encryptSHA384(bArr));
    }

    public static String encryptSHA512ToString(byte[] bArr) {
        return a(encryptSHA512(bArr));
    }

    private static byte[] a(byte[] bArr, byte[] bArr2, String str) {
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

    public static String encryptMD5ToString(byte[] bArr) {
        return a(encryptMD5(bArr));
    }

    public static String encryptMD5ToString(byte[] bArr, byte[] bArr2) {
        if (bArr == null && bArr2 == null) {
            return "";
        }
        if (bArr2 == null) {
            return a(encryptMD5(bArr));
        }
        if (bArr == null) {
            return a(encryptMD5(bArr2));
        }
        byte[] bArr3 = new byte[bArr.length + bArr2.length];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr3, bArr.length, bArr2.length);
        return a(encryptMD5(bArr3));
    }

    private static byte[] a(byte[] bArr, byte[] bArr2, String str, String str2, byte[] bArr3, boolean z) {
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

    private static byte[] a(byte[] bArr, byte[] bArr2, boolean z, String str, boolean z2) {
        Key generatePrivate;
        if (bArr != null && bArr.length != 0 && bArr2 != null && bArr2.length != 0) {
            try {
                if (z) {
                    generatePrivate = KeyFactory.getInstance(RSAUtils.KEY_ALGORITHM).generatePublic(new X509EncodedKeySpec(bArr2));
                } else {
                    generatePrivate = KeyFactory.getInstance(RSAUtils.KEY_ALGORITHM).generatePrivate(new PKCS8EncodedKeySpec(bArr2));
                }
                if (generatePrivate == null) {
                    return null;
                }
                Cipher cipher = Cipher.getInstance(str);
                cipher.init(z2 ? 1 : 2, generatePrivate);
                int length = bArr.length;
                int i2 = z2 ? 117 : 128;
                int i3 = length / i2;
                if (i3 > 0) {
                    byte[] bArr3 = new byte[0];
                    byte[] bArr4 = new byte[i2];
                    int i4 = 0;
                    for (int i5 = 0; i5 < i3; i5++) {
                        System.arraycopy(bArr, i4, bArr4, 0, i2);
                        bArr3 = a(bArr3, cipher.doFinal(bArr4));
                        i4 += i2;
                    }
                    if (i4 != length) {
                        int i6 = length - i4;
                        byte[] bArr5 = new byte[i6];
                        System.arraycopy(bArr, i4, bArr5, 0, i6);
                        return a(bArr3, cipher.doFinal(bArr5));
                    }
                    return bArr3;
                }
                return cipher.doFinal(bArr);
            } catch (InvalidKeyException e2) {
                e2.printStackTrace();
            } catch (NoSuchAlgorithmException e3) {
                e3.printStackTrace();
            } catch (InvalidKeySpecException e4) {
                e4.printStackTrace();
            } catch (BadPaddingException e5) {
                e5.printStackTrace();
            } catch (IllegalBlockSizeException e6) {
                e6.printStackTrace();
            } catch (NoSuchPaddingException e7) {
                e7.printStackTrace();
            }
        }
        return null;
    }

    private static byte[] a(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[bArr.length + bArr2.length];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr3, bArr.length, bArr2.length);
        return bArr3;
    }

    private static String a(byte[] bArr) {
        int length;
        if (bArr != null && (length = bArr.length) > 0) {
            char[] cArr = new char[length << 1];
            int i2 = 0;
            for (int i3 = 0; i3 < length; i3++) {
                int i4 = i2 + 1;
                char[] cArr2 = a;
                cArr[i2] = cArr2[(bArr[i3] >>> 4) & 15];
                i2 = i4 + 1;
                cArr[i4] = cArr2[bArr[i3] & 15];
            }
            return new String(cArr);
        }
        return "";
    }

    private static byte[] a(String str) {
        if (b(str)) {
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
            bArr[i2 >> 1] = (byte) ((a(charArray[i2]) << 4) | a(charArray[i2 + 1]));
        }
        return bArr;
    }

    private static int a(char c2) {
        if (c2 < '0' || c2 > '9') {
            if (c2 < 'A' || c2 > 'F') {
                throw new IllegalArgumentException();
            }
            return (c2 - 'A') + 10;
        }
        return c2 - '0';
    }
}
