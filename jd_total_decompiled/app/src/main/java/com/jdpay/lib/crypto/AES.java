package com.jdpay.lib.crypto;

import android.text.TextUtils;
import android.util.Base64;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.jdpay.lib.util.JDPayLog;
import com.jingdong.common.utils.security.JDKeyStore;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: classes18.dex */
public class AES {
    public static final String ALGORITHM = "AES/CBC/PKCS5Padding";
    public static final String ALGORITHM_STANDARD = "AES/GCM/NoPadding";

    @Nullable
    public static byte[] decrypt(String str, byte[] bArr, byte[] bArr2) {
        try {
            Cipher cipher = Cipher.getInstance(str);
            cipher.init(2, new SecretKeySpec(bArr, JDKeyStore.KEY_TYPE_AES));
            return cipher.doFinal(bArr2);
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    @Nullable
    public static String decryptToString(@NonNull String str, @NonNull String str2) {
        return decryptToString(str, str2, ALGORITHM);
    }

    @Nullable
    public static byte[] encrypt(String str, byte[] bArr, byte[] bArr2) {
        try {
            Cipher cipher = Cipher.getInstance(str);
            cipher.init(1, new SecretKeySpec(bArr, JDKeyStore.KEY_TYPE_AES));
            return cipher.doFinal(bArr2);
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    @Nullable
    public static String encryptToBase64(@NonNull String str, @NonNull String str2) {
        return encryptToBase64(str, str2, ALGORITHM);
    }

    @Nullable
    public static byte[] generateKey(int i2) {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance(JDKeyStore.KEY_TYPE_AES);
            keyGenerator.init(i2);
            return keyGenerator.generateKey().getEncoded();
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    @Nullable
    public static String decryptToString(@NonNull String str, @NonNull String str2, @Nullable String str3) {
        try {
            byte[] decrypt = decrypt(str.getBytes("UTF-8"), Base64.decode(str2, 0), str3, null);
            if (decrypt == null || decrypt.length <= 0) {
                return null;
            }
            return new String(decrypt, "UTF-8");
        } catch (Exception e2) {
            JDPayLog.e(e2);
            return null;
        }
    }

    @Nullable
    public static String encryptToBase64(@NonNull String str, @NonNull String str2, @Nullable String str3) {
        try {
            byte[] encrypt = encrypt(str.getBytes("UTF-8"), str2.getBytes("UTF-8"), str3, null);
            if (encrypt == null || encrypt.length <= 0) {
                return null;
            }
            return Base64.encodeToString(encrypt, 0);
        } catch (Exception e2) {
            JDPayLog.e(e2);
            return null;
        }
    }

    @Nullable
    public static byte[] decrypt(@NonNull String str, @NonNull String str2, @Nullable String str3) {
        try {
            return decrypt(str.getBytes("UTF-8"), Base64.decode(str2, 0), str3, null);
        } catch (Exception e2) {
            JDPayLog.e(e2);
            return null;
        }
    }

    @Nullable
    public static byte[] encrypt(@NonNull byte[] bArr, @NonNull byte[] bArr2) {
        return encrypt(bArr, bArr2, ALGORITHM, null);
    }

    @Nullable
    public static byte[] encrypt(@NonNull byte[] bArr, @NonNull byte[] bArr2, @Nullable String str, @Nullable SecureRandom secureRandom) {
        if (TextUtils.isEmpty(str)) {
            str = ALGORITHM;
        }
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(bArr, str);
            Cipher cipher = Cipher.getInstance(str);
            cipher.init(1, secretKeySpec, new IvParameterSpec(new byte[cipher.getBlockSize()]), secureRandom);
            return cipher.doFinal(bArr2);
        } catch (Exception e2) {
            JDPayLog.e(e2);
            return null;
        }
    }

    @Nullable
    public static byte[] decrypt(@NonNull byte[] bArr, @NonNull byte[] bArr2, @Nullable String str, @Nullable SecureRandom secureRandom) {
        if (TextUtils.isEmpty(str)) {
            str = ALGORITHM;
        }
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(bArr, str);
            Cipher cipher = Cipher.getInstance(str);
            cipher.init(2, secretKeySpec, new IvParameterSpec(new byte[cipher.getBlockSize()]), secureRandom);
            return cipher.doFinal(bArr2);
        } catch (Exception e2) {
            JDPayLog.e(e2);
            return null;
        }
    }
}
