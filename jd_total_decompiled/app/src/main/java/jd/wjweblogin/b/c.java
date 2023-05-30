package jd.wjweblogin.b;

import com.jingdong.common.entity.personal.PersonalConstants;
import com.jingdong.common.utils.security.JDKeyStore;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: classes11.dex */
public class c {
    private static final String a = "WJWebLogin.AESServerEncryptor";

    private static int a() {
        return 10;
    }

    public static byte[] a(String str) throws Throwable {
        return b(d().getBytes(), str.getBytes());
    }

    private static int b() {
        return 128;
    }

    private static byte[] b(byte[] bArr, byte[] bArr2) throws Exception {
        SecretKeySpec secretKeySpec = new SecretKeySpec(bArr, JDKeyStore.KEY_TYPE_AES);
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
        cipher.init(1, secretKeySpec, new IvParameterSpec(c().getBytes()));
        return cipher.doFinal(bArr2);
    }

    private static String c() {
        StringBuilder sb = new StringBuilder();
        sb.append("0");
        sb.append("0");
        sb.append("0");
        sb.append("0");
        sb.append("0");
        sb.append("0");
        sb.append("0");
        sb.append("0");
        sb.append("0");
        sb.append("0");
        sb.append("0");
        sb.append("0");
        sb.append("0");
        sb.append("0");
        sb.append("0");
        sb.append("0");
        if (jd.wjweblogin.d.g.b) {
            jd.wjweblogin.d.g.b("Encryptor.getIVVector = " + sb.toString());
        }
        return sb.toString();
    }

    private static String d() {
        StringBuilder sb = new StringBuilder();
        sb.append("m");
        sb.append("r");
        sb.append("m");
        sb.append("T");
        sb.append("7");
        sb.append("E");
        sb.append("E");
        sb.append("z");
        sb.append("Q");
        sb.append("o");
        sb.append(PersonalConstants.ICON_STYLE_N);
        sb.append("J");
        sb.append("T");
        sb.append("Y");
        sb.append("t");
        sb.append("R");
        if (jd.wjweblogin.d.g.b) {
            jd.wjweblogin.d.g.b("Encryptor.getKey = " + sb.toString());
        }
        return sb.toString();
    }

    public static String a(byte[] bArr) throws Throwable {
        jd.wjweblogin.d.g.b(a, "decrypt ");
        byte[] bytes = d().getBytes();
        jd.wjweblogin.d.g.b(a, "decrypt rawKey " + bytes.toString());
        String str = new String(a(bytes, bArr));
        jd.wjweblogin.d.g.b(a, "decrypt result " + str);
        return str;
    }

    private static byte[] a(byte[] bArr, byte[] bArr2) throws Exception {
        jd.wjweblogin.d.g.b(a, "decrypt(byte[] raw, byte[] encrypted) ");
        SecretKeySpec secretKeySpec = new SecretKeySpec(bArr, JDKeyStore.KEY_TYPE_AES);
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
        cipher.init(2, secretKeySpec, new IvParameterSpec(c().getBytes()));
        return cipher.doFinal(bArr2);
    }
}
