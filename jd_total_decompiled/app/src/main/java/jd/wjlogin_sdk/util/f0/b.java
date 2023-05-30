package jd.wjlogin_sdk.util.f0;

import com.jingdong.common.utils.security.JDKeyStore;
import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import jd.wjlogin_sdk.b.g;

/* loaded from: classes.dex */
public class b {
    private static final int a = 10;
    private static final int b = 128;

    /* renamed from: c  reason: collision with root package name */
    private static byte[] f19964c;

    public static String a(String str, String str2) {
        try {
            return new String(a(a(str), b(str2)));
        } catch (Throwable unused) {
            return "";
        }
    }

    public static byte[] b(String str) {
        int length = str.length() / 2;
        byte[] bArr = new byte[length];
        for (int i2 = 0; i2 < length; i2++) {
            int i3 = i2 * 2;
            bArr[i2] = Integer.valueOf(str.substring(i3, i3 + 2), 16).byteValue();
        }
        return bArr;
    }

    private static byte[] a(String str) throws Exception {
        byte[] bArr = f19964c;
        if (bArr != null) {
            return bArr;
        }
        byte[] encoded = new SecretKeySpec(SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1").generateSecret(new PBEKeySpec(str.toCharArray(), g.b().a(), 10, 128)).getEncoded(), JDKeyStore.KEY_TYPE_AES).getEncoded();
        f19964c = encoded;
        return encoded;
    }

    private static byte[] a(byte[] bArr, byte[] bArr2) throws Exception {
        SecretKeySpec secretKeySpec = new SecretKeySpec(bArr, JDKeyStore.KEY_TYPE_AES);
        Cipher cipher = Cipher.getInstance(JDKeyStore.KEY_TYPE_AES);
        cipher.init(2, secretKeySpec);
        return cipher.doFinal(bArr2);
    }
}
