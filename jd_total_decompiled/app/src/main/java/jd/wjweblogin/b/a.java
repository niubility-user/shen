package jd.wjweblogin.b;

import com.jdpay.lib.crypto.AES;
import com.jingdong.common.utils.security.JDKeyStore;
import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: classes11.dex */
public class a {
    private static final int a = 10;
    private static final int b = 128;

    /* renamed from: c  reason: collision with root package name */
    private static final String f20011c = "WJWebLogin.AESCipher";

    private static String a() {
        StringBuilder sb = new StringBuilder();
        sb.append("1");
        sb.append("6");
        sb.append("5");
        sb.append("3");
        sb.append("6");
        sb.append("7");
        sb.append("8");
        sb.append("1");
        sb.append("4");
        sb.append("5");
        sb.append("7");
        sb.append("1");
        sb.append("2");
        sb.append("1");
        sb.append("9");
        sb.append("1");
        if (jd.wjweblogin.d.g.b) {
            jd.wjweblogin.d.g.b("EncryptorV6.getIVVector = " + sb.toString());
        }
        return sb.toString();
    }

    private static byte[] b(byte[] bArr, byte[] bArr2) throws Exception {
        SecretKeySpec secretKeySpec = new SecretKeySpec(bArr, JDKeyStore.KEY_TYPE_AES);
        Cipher cipher = Cipher.getInstance(AES.ALGORITHM);
        cipher.init(1, secretKeySpec, new IvParameterSpec(a().getBytes()));
        return cipher.doFinal(bArr2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static byte[] a(String str, String str2) throws Throwable {
        return b(a(str), str2.getBytes());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String a(String str, byte[] bArr) throws Throwable {
        jd.wjweblogin.d.g.b(f20011c, "decrypt ");
        return new String(a(a(str), bArr));
    }

    private static byte[] a(String str) throws Exception {
        return new SecretKeySpec(SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1").generateSecret(new PBEKeySpec(str.toCharArray(), g.b().a(), 10, 128)).getEncoded(), JDKeyStore.KEY_TYPE_AES).getEncoded();
    }

    private static byte[] a(byte[] bArr, byte[] bArr2) throws Exception {
        jd.wjweblogin.d.g.b(f20011c, "decrypt(byte[] raw, byte[] encrypted) ");
        SecretKeySpec secretKeySpec = new SecretKeySpec(bArr, JDKeyStore.KEY_TYPE_AES);
        Cipher cipher = Cipher.getInstance(AES.ALGORITHM);
        cipher.init(2, secretKeySpec, new IvParameterSpec(a().getBytes()));
        return cipher.doFinal(bArr2);
    }
}
