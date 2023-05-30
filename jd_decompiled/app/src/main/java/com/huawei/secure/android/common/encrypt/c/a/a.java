package com.huawei.secure.android.common.encrypt.c.a;

import android.os.Build;
import android.security.keystore.KeyGenParameterSpec;
import android.text.TextUtils;
import com.huawei.secure.android.common.encrypt.d.c;
import com.huawei.secure.android.common.encrypt.d.f;
import com.jdpay.lib.crypto.AES;
import com.jingdong.common.utils.security.JDKeyStore;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;

/* loaded from: classes12.dex */
public class a {
    private static Map<String, SecretKey> a = new HashMap();

    private static SecretKey a(String str) {
        f.d("GCMKS", "load key");
        SecretKey secretKey = null;
        try {
            KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");
            keyStore.load(null);
            Key key = keyStore.getKey(str, null);
            if (key instanceof SecretKey) {
                secretKey = (SecretKey) key;
            } else {
                f.d("GCMKS", "generate key");
                KeyGenerator keyGenerator = KeyGenerator.getInstance(JDKeyStore.KEY_TYPE_AES, "AndroidKeyStore");
                keyGenerator.init(new KeyGenParameterSpec.Builder(str, 3).setBlockModes("GCM").setEncryptionPaddings("NoPadding").setKeySize(256).build());
                secretKey = keyGenerator.generateKey();
            }
        } catch (IOException e2) {
            f.c("GCMKS", "IOException : " + e2.getMessage());
        } catch (InvalidAlgorithmParameterException e3) {
            f.c("GCMKS", "InvalidAlgorithmParameterException : " + e3.getMessage());
        } catch (KeyStoreException e4) {
            f.c("GCMKS", "KeyStoreException : " + e4.getMessage());
        } catch (NoSuchAlgorithmException e5) {
            f.c("GCMKS", "NoSuchAlgorithmException : " + e5.getMessage());
        } catch (NoSuchProviderException e6) {
            f.c("GCMKS", "NoSuchProviderException : " + e6.getMessage());
        } catch (UnrecoverableKeyException e7) {
            f.c("GCMKS", "UnrecoverableKeyException : " + e7.getMessage());
        } catch (CertificateException e8) {
            f.c("GCMKS", "CertificateException : " + e8.getMessage());
        } catch (Exception e9) {
            f.c("GCMKS", "Exception: " + e9.getMessage());
        }
        a.put(str, secretKey);
        return secretKey;
    }

    private static boolean b() {
        return Build.VERSION.SDK_INT >= 23;
    }

    private static SecretKey c(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (a.get(str) == null) {
            a(str);
        }
        return a.get(str);
    }

    public static String d(String str, String str2) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            try {
                return new String(e(str, c.b(str2)), "UTF-8");
            } catch (UnsupportedEncodingException e2) {
                f.c("GCMKS", "decrypt: UnsupportedEncodingException : " + e2.getMessage());
                return "";
            }
        }
        f.c("GCMKS", "alias or encrypt content is null");
        return "";
    }

    public static byte[] e(String str, byte[] bArr) {
        byte[] bArr2 = new byte[0];
        if (!TextUtils.isEmpty(str) && bArr != null) {
            if (!b()) {
                f.c("GCMKS", "sdk version is too low");
                return bArr2;
            } else if (bArr.length <= 12) {
                f.c("GCMKS", "Decrypt source data is invalid.");
                return bArr2;
            } else {
                return f(c(str), bArr);
            }
        }
        f.c("GCMKS", "alias or encrypt content is null");
        return bArr2;
    }

    public static byte[] f(SecretKey secretKey, byte[] bArr) {
        byte[] bArr2 = new byte[0];
        if (secretKey == null) {
            f.c("GCMKS", "Decrypt secret key is null");
            return bArr2;
        } else if (bArr == null) {
            f.c("GCMKS", "content is null");
            return bArr2;
        } else if (!b()) {
            f.c("GCMKS", "sdk version is too low");
            return bArr2;
        } else if (bArr.length <= 12) {
            f.c("GCMKS", "Decrypt source data is invalid.");
            return bArr2;
        } else {
            byte[] copyOf = Arrays.copyOf(bArr, 12);
            try {
                Cipher cipher = Cipher.getInstance(AES.ALGORITHM_STANDARD);
                cipher.init(2, secretKey, new GCMParameterSpec(128, copyOf));
                return cipher.doFinal(bArr, 12, bArr.length - 12);
            } catch (InvalidAlgorithmParameterException e2) {
                f.c("GCMKS", "InvalidAlgorithmParameterException : " + e2.getMessage());
                return bArr2;
            } catch (InvalidKeyException e3) {
                f.c("GCMKS", "InvalidKeyException : " + e3.getMessage());
                return bArr2;
            } catch (NoSuchAlgorithmException e4) {
                f.c("GCMKS", "NoSuchAlgorithmException : " + e4.getMessage());
                return bArr2;
            } catch (BadPaddingException e5) {
                f.c("GCMKS", "BadPaddingException : " + e5.getMessage());
                return bArr2;
            } catch (IllegalBlockSizeException e6) {
                f.c("GCMKS", "IllegalBlockSizeException : " + e6.getMessage());
                return bArr2;
            } catch (NoSuchPaddingException e7) {
                f.c("GCMKS", "NoSuchPaddingException : " + e7.getMessage());
                return bArr2;
            } catch (Exception e8) {
                f.c("GCMKS", "Exception: " + e8.getMessage());
                return bArr2;
            }
        }
    }

    public static String g(String str, String str2) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            try {
                return c.a(h(str, str2.getBytes("UTF-8")));
            } catch (UnsupportedEncodingException e2) {
                f.c("GCMKS", "encrypt: UnsupportedEncodingException : " + e2.getMessage());
                return "";
            }
        }
        f.c("GCMKS", "alias or encrypt content is null");
        return "";
    }

    public static byte[] h(String str, byte[] bArr) {
        byte[] bArr2 = new byte[0];
        if (!TextUtils.isEmpty(str) && bArr != null) {
            if (!b()) {
                f.c("GCMKS", "sdk version is too low");
                return bArr2;
            }
            return i(c(str), bArr);
        }
        f.c("GCMKS", "alias or encrypt content is null");
        return bArr2;
    }

    public static byte[] i(SecretKey secretKey, byte[] bArr) {
        byte[] bArr2 = new byte[0];
        if (bArr == null) {
            f.c("GCMKS", "content is null");
            return bArr2;
        } else if (secretKey == null) {
            f.c("GCMKS", "secret key is null");
            return bArr2;
        } else if (!b()) {
            f.c("GCMKS", "sdk version is too low");
            return bArr2;
        } else {
            try {
                Cipher cipher = Cipher.getInstance(AES.ALGORITHM_STANDARD);
                cipher.init(1, secretKey);
                byte[] doFinal = cipher.doFinal(bArr);
                byte[] iv = cipher.getIV();
                if (iv != null && iv.length == 12) {
                    byte[] copyOf = Arrays.copyOf(iv, iv.length + doFinal.length);
                    System.arraycopy(doFinal, 0, copyOf, iv.length, doFinal.length);
                    return copyOf;
                }
                f.c("GCMKS", "IV is invalid.");
                return bArr2;
            } catch (InvalidKeyException e2) {
                f.c("GCMKS", "InvalidKeyException : " + e2.getMessage());
                return bArr2;
            } catch (NoSuchAlgorithmException e3) {
                f.c("GCMKS", "NoSuchAlgorithmException : " + e3.getMessage());
                return bArr2;
            } catch (BadPaddingException e4) {
                f.c("GCMKS", "BadPaddingException : " + e4.getMessage());
                return bArr2;
            } catch (IllegalBlockSizeException e5) {
                f.c("GCMKS", "IllegalBlockSizeException : " + e5.getMessage());
                return bArr2;
            } catch (NoSuchPaddingException e6) {
                f.c("GCMKS", "NoSuchPaddingException : " + e6.getMessage());
                return bArr2;
            } catch (Exception e7) {
                f.c("GCMKS", "Exception: " + e7.getMessage());
                return bArr2;
            }
        }
    }
}
