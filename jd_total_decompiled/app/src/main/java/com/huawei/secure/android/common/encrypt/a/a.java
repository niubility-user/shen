package com.huawei.secure.android.common.encrypt.a;

import android.text.TextUtils;
import com.huawei.secure.android.common.encrypt.d.b;
import com.huawei.secure.android.common.encrypt.d.c;
import com.huawei.secure.android.common.encrypt.d.f;
import com.jdpay.lib.crypto.AES;
import com.jingdong.common.utils.security.JDKeyStore;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: classes12.dex */
public final class a {
    private static String a(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        try {
            return str.substring(0, 6) + str.substring(12, 16) + str.substring(26, 32) + str.substring(48);
        } catch (Exception e2) {
            f.c("CBC", "get encryptword exception : " + e2.getMessage());
            return "";
        }
    }

    private static String b(String str, String str2) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            try {
                return str2.substring(0, 6) + str.substring(0, 6) + str2.substring(6, 10) + str.substring(6, 16) + str2.substring(10, 16) + str.substring(16) + str2.substring(16);
            } catch (Exception e2) {
                f.c("CBC", "mix exception: " + e2.getMessage());
            }
        }
        return "";
    }

    private static byte[] c(String str, byte[] bArr, byte[] bArr2) {
        if (TextUtils.isEmpty(str)) {
            f.c("CBC", "encrypt 5 content is null");
            return new byte[0];
        } else if (bArr == null) {
            f.c("CBC", "encrypt 5 key is null");
            return new byte[0];
        } else if (bArr.length < 16) {
            f.c("CBC", "encrypt 5 key lengh is not right");
            return new byte[0];
        } else if (bArr2 == null) {
            f.c("CBC", "encrypt 5 iv is null");
            return new byte[0];
        } else if (bArr2.length < 16) {
            f.c("CBC", "encrypt 5 iv lengh is not right");
            return new byte[0];
        } else {
            try {
                return m(str.getBytes("UTF-8"), bArr, bArr2);
            } catch (UnsupportedEncodingException e2) {
                f.c("CBC", " cbc encrypt data error" + e2.getMessage());
                return new byte[0];
            }
        }
    }

    private static byte[] d(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[bArr.length + bArr2.length];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr3, bArr.length, bArr2.length);
        return bArr3;
    }

    private static String e(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        try {
            return str.substring(6, 12) + str.substring(16, 26) + str.substring(32, 48);
        } catch (Exception e2) {
            f.c("CBC", "getIv exception : " + e2.getMessage());
            return "";
        }
    }

    public static String f(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            f.c("CBC", "decrypt 1 content is null");
            return "";
        } else if (TextUtils.isEmpty(str2)) {
            f.c("CBC", "decrypt 1 key is null");
            return "";
        } else {
            byte[] b = c.b(str2);
            if (b.length < 16) {
                f.c("CBC", "decrypt 1 key length is not right");
                return "";
            }
            return g(str, b);
        }
    }

    public static String g(String str, byte[] bArr) {
        if (TextUtils.isEmpty(str)) {
            f.c("CBC", "decrypt 2 content is null");
            return "";
        } else if (bArr == null) {
            f.c("CBC", "decrypt 2 key is null");
            return "";
        } else if (bArr.length < 16) {
            f.c("CBC", "decrypt 2 key lengh is not right");
            return "";
        } else {
            String e2 = e(str);
            String a = a(str);
            if (TextUtils.isEmpty(e2)) {
                f.c("CBC", "decrypt 2 iv is null");
                return "";
            } else if (TextUtils.isEmpty(a)) {
                f.c("CBC", "decrypt 2 encrypt content is null");
                return "";
            } else {
                return h(a, bArr, c.b(e2));
            }
        }
    }

    public static String h(String str, byte[] bArr, byte[] bArr2) {
        if (TextUtils.isEmpty(str)) {
            f.c("CBC", "decrypt 4 content is null");
            return "";
        } else if (bArr == null) {
            f.c("CBC", "decrypt 4 key is null");
            return "";
        } else if (bArr.length < 16) {
            f.c("CBC", "decrypt 4 key lengh is not right");
            return "";
        } else if (bArr2 == null) {
            f.c("CBC", "decrypt 4 iv is null");
            return "";
        } else if (bArr2.length < 16) {
            f.c("CBC", "decrypt 4 iv lengh is not right");
            return "";
        } else {
            try {
                return new String(i(c.b(str), bArr, bArr2), "UTF-8");
            } catch (UnsupportedEncodingException e2) {
                f.c("CBC", " cbc decrypt data error" + e2.getMessage());
                return "";
            }
        }
    }

    public static byte[] i(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        if (bArr == null) {
            f.c("CBC", "decrypt 6 content is null");
            return new byte[0];
        } else if (bArr.length == 0) {
            f.c("CBC", "decrypt 6 content length is 0");
            return new byte[0];
        } else if (bArr2 == null) {
            f.c("CBC", "decrypt 6 key is null");
            return new byte[0];
        } else if (bArr2.length < 16) {
            f.c("CBC", "decrypt 6 key length is error");
            return new byte[0];
        } else if (bArr3 == null) {
            f.c("CBC", "decrypt 6 iv is null");
            return new byte[0];
        } else if (bArr3.length < 16) {
            f.c("CBC", "decrypt 6 iv length is error");
            return new byte[0];
        } else {
            SecretKeySpec secretKeySpec = new SecretKeySpec(bArr2, JDKeyStore.KEY_TYPE_AES);
            try {
                Cipher cipher = Cipher.getInstance(AES.ALGORITHM);
                cipher.init(2, secretKeySpec, new IvParameterSpec(bArr3));
                return cipher.doFinal(bArr);
            } catch (InvalidAlgorithmParameterException e2) {
                f.c("CBC", "InvalidAlgorithmParameterException: " + e2.getMessage());
                return new byte[0];
            } catch (InvalidKeyException e3) {
                f.c("CBC", "InvalidKeyException: " + e3.getMessage());
                return new byte[0];
            } catch (NoSuchAlgorithmException e4) {
                f.c("CBC", "NoSuchAlgorithmException: " + e4.getMessage());
                return new byte[0];
            } catch (BadPaddingException e5) {
                f.c("CBC", "BadPaddingException: " + e5.getMessage());
                f.c("CBC", "key is not right");
                return new byte[0];
            } catch (IllegalBlockSizeException e6) {
                f.c("CBC", "IllegalBlockSizeException: " + e6.getMessage());
                return new byte[0];
            } catch (NoSuchPaddingException e7) {
                f.c("CBC", "NoSuchPaddingException: " + e7.getMessage());
                return new byte[0];
            }
        }
    }

    public static String j(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            f.c("CBC", "encrypt 1 content is null");
            return "";
        } else if (TextUtils.isEmpty(str2)) {
            f.c("CBC", "encrypt 1 key is null");
            return "";
        } else {
            byte[] b = c.b(str2);
            if (b.length < 16) {
                f.c("CBC", "encrypt 1 key length is not right");
                return "";
            }
            return k(str, b);
        }
    }

    public static String k(String str, byte[] bArr) {
        if (TextUtils.isEmpty(str)) {
            f.c("CBC", "encrypt 2 content is null");
            return "";
        } else if (bArr == null) {
            f.c("CBC", "encrypt 2 key is null");
            return "";
        } else if (bArr.length < 16) {
            f.c("CBC", "encrypt 2 key lengh is not right");
            return "";
        } else {
            byte[] c2 = b.c(16);
            byte[] c3 = c(str, bArr, c2);
            return (c3 == null || c3.length == 0) ? "" : b(c.a(c2), c.a(c3));
        }
    }

    public static byte[] l(byte[] bArr, byte[] bArr2) {
        byte[] c2 = b.c(16);
        return d(c2, m(bArr, bArr2, c2));
    }

    public static byte[] m(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        if (bArr == null) {
            f.c("CBC", "encrypt 6 content is null");
            return new byte[0];
        } else if (bArr.length == 0) {
            f.c("CBC", "encrypt 6 content length is 0");
            return new byte[0];
        } else if (bArr2 == null) {
            f.c("CBC", "encrypt 6 key is null");
            return new byte[0];
        } else if (bArr2.length < 16) {
            f.c("CBC", "encrypt 6 key length is error");
            return new byte[0];
        } else if (bArr3 == null) {
            f.c("CBC", "encrypt 6 iv is null");
            return new byte[0];
        } else if (bArr3.length < 16) {
            f.c("CBC", "encrypt 6 iv length is error");
            return new byte[0];
        } else {
            SecretKeySpec secretKeySpec = new SecretKeySpec(bArr2, JDKeyStore.KEY_TYPE_AES);
            try {
                Cipher cipher = Cipher.getInstance(AES.ALGORITHM);
                cipher.init(1, secretKeySpec, new IvParameterSpec(bArr3));
                return cipher.doFinal(bArr);
            } catch (InvalidAlgorithmParameterException e2) {
                f.c("CBC", "InvalidAlgorithmParameterException: " + e2.getMessage());
                return new byte[0];
            } catch (InvalidKeyException e3) {
                f.c("CBC", "InvalidKeyException: " + e3.getMessage());
                return new byte[0];
            } catch (NoSuchAlgorithmException e4) {
                f.c("CBC", "NoSuchAlgorithmException: " + e4.getMessage());
                return new byte[0];
            } catch (BadPaddingException e5) {
                f.c("CBC", "BadPaddingException: " + e5.getMessage());
                return new byte[0];
            } catch (IllegalBlockSizeException e6) {
                f.c("CBC", "IllegalBlockSizeException: " + e6.getMessage());
                return new byte[0];
            } catch (NoSuchPaddingException e7) {
                f.c("CBC", "NoSuchPaddingException: " + e7.getMessage());
                return new byte[0];
            }
        }
    }
}
