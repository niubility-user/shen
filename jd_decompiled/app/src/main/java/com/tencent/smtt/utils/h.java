package com.tencent.smtt.utils;

import com.huawei.hms.framework.common.ExceptionCode;
import com.jd.dynamic.DYConstants;
import com.jingdong.common.jdreactFramework.utils.RSAUtils;
import java.security.KeyFactory;
import java.security.spec.X509EncodedKeySpec;
import java.util.Random;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

/* loaded from: classes9.dex */
public class h {
    private static String b = "";

    /* renamed from: c  reason: collision with root package name */
    private static byte[] f17960c;

    /* renamed from: g  reason: collision with root package name */
    private static String f17962g;
    private Cipher d;

    /* renamed from: e  reason: collision with root package name */
    private Cipher f17963e;
    protected static final char[] a = "0123456789abcdef".toCharArray();

    /* renamed from: f  reason: collision with root package name */
    private static h f17961f = null;

    private h() throws Exception {
        this.d = null;
        this.f17963e = null;
        f17962g = String.valueOf(new Random().nextInt(89999999) + ExceptionCode.CRASH_EXCEPTION) + String.valueOf(new Random().nextInt(89999999) + ExceptionCode.CRASH_EXCEPTION) + String.valueOf(new Random().nextInt(89999999) + ExceptionCode.CRASH_EXCEPTION);
        String str = "00000000";
        for (int i2 = 0; i2 < 12; i2++) {
            str = str + String.valueOf(new Random().nextInt(89999999) + ExceptionCode.CRASH_EXCEPTION);
        }
        f17960c = (str + f17962g).getBytes();
        this.d = Cipher.getInstance("RSA/ECB/NoPadding");
        this.d.init(1, KeyFactory.getInstance(RSAUtils.KEY_ALGORITHM).generatePublic(new X509EncodedKeySpec(android.util.Base64.decode((d() + e()).getBytes(), 0))));
        b = b(this.d.doFinal(f17960c));
        SecretKey generateSecret = SecretKeyFactory.getInstance("DESede").generateSecret(new DESedeKeySpec(f17962g.getBytes()));
        Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
        this.f17963e = cipher;
        cipher.init(1, generateSecret);
    }

    public static h a() {
        try {
            if (f17961f == null) {
                f17961f = new h();
            }
            return f17961f;
        } catch (Exception e2) {
            f17961f = null;
            e2.printStackTrace();
            return null;
        }
    }

    public static byte[] a(byte[] bArr, String str) throws Exception {
        SecretKey generateSecret = SecretKeyFactory.getInstance("DESede").generateSecret(new DESedeKeySpec(str.getBytes()));
        Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
        cipher.init(1, generateSecret);
        return cipher.doFinal(bArr);
    }

    public static String b(byte[] bArr) {
        char[] cArr = new char[bArr.length * 2];
        for (int i2 = 0; i2 < bArr.length; i2++) {
            int i3 = bArr[i2] & 255;
            int i4 = i2 * 2;
            char[] cArr2 = a;
            cArr[i4] = cArr2[i3 >>> 4];
            cArr[i4 + 1] = cArr2[i3 & 15];
        }
        return new String(cArr);
    }

    public static byte[] b(byte[] bArr, String str) {
        try {
            SecretKey generateSecret = SecretKeyFactory.getInstance("DESede").generateSecret(new DESedeKeySpec(str.getBytes()));
            Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
            cipher.init(2, generateSecret);
            return cipher.doFinal(bArr);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static String c() {
        return f17962g;
    }

    private String d() {
        return "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDcEQ3TCNWPBqgIiY7WQ/IqTOTTV2w8aZ/GPm68FK0";
    }

    private String e() {
        return "fAJBemZKtYR3Li46VJ+Hwnor7ZpQnblGWPFaLv5JoPqvavgB0GInuhm+T+syPs1mw0uPLWaqwvZsCfoaIvUuxy5xHJgmWARrK4/9pHyDxRlZte0PCIoR1ko5B8lVVH1X1dQIDAQAB";
    }

    public byte[] a(byte[] bArr) throws Exception {
        return this.f17963e.doFinal(bArr);
    }

    public String b() {
        return b;
    }

    public byte[] c(byte[] bArr) {
        TbsLog.i("Post3DESEncryption", "DesDecrypt deskeys is " + f17962g);
        try {
            SecretKey generateSecret = SecretKeyFactory.getInstance("DESede").generateSecret(new DESedeKeySpec(f17962g.getBytes()));
            Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
            cipher.init(2, generateSecret);
            return cipher.doFinal(bArr);
        } catch (Exception e2) {
            try {
                TbsLog.i("Post3DESEncryption", "DesDecrypt exception,  content is " + d(bArr));
            } catch (Throwable unused) {
            }
            TbsLog.i(e2);
            return null;
        }
    }

    public String d(byte[] bArr) {
        StringBuilder sb = new StringBuilder("");
        if (bArr == null || bArr.length <= 0) {
            return null;
        }
        for (int i2 = 0; i2 < bArr.length; i2++) {
            sb.append("0x");
            String hexString = Integer.toHexString(bArr[i2] & 255);
            if (hexString.length() < 2) {
                sb.append(0);
            }
            sb.append(hexString);
            if (i2 != bArr.length - 1) {
                sb.append(DYConstants.DY_REGEX_COMMA);
            }
        }
        return sb.toString();
    }
}
