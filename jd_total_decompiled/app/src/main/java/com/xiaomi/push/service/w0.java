package com.xiaomi.push.service;

import android.text.TextUtils;
import android.util.Base64;
import com.jingdong.common.jdreactFramework.utils.RSAUtils;
import java.io.ByteArrayOutputStream;
import java.security.KeyFactory;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher;
import jd.wjlogin_sdk.util.ReplyCode;

/* loaded from: classes11.dex */
public class w0 {
    private static final byte[] a;
    private static RSAPublicKey b;

    static {
        byte[] bArr = {48, -127, -97, 48, 13, 6, 9, 42, ReplyCode.reply0x86, 72, ReplyCode.reply0x86, -9, 13, 1, 1, 1, 5, 0, 3, -127, ReplyCode.reply0x8d, 0, 48, -127, ReplyCode.reply0x89, 2, -127, -127, 0, -109, -38, ReplyCode.reply0x8e, 26, -72, 78, 16, 70, ReplyCode.reply0xa6, 113, -30, ReplyCode.reply0x24, 85, -3, -43, ReplyCode.reply0x7b, 61, -98, 4, -16, 67, 19, ReplyCode.reply0xa6, -73, -5, ReplyCode.reply0xa7, ReplyCode.reply0x24, 44, -27, 59, ReplyCode.reply0x85, 72, -73, ReplyCode.reply0xd0, ReplyCode.reply0x31, 13, 16, ReplyCode.reply0x32, -27, ReplyCode.reply0xae, 18, -28, 84, 0, -41, 16, 69, -39, 7, 82, ReplyCode.reply0x38, ReplyCode.reply0x4f, -37, ReplyCode.reply0x28, 85, 107, 98, ReplyCode.reply0x21, ReplyCode.reply0x7b, ReplyCode.reply0xde, ReplyCode.reply0xcf, 111, -11, ReplyCode.reply0x31, 28, 117, -74, 114, ReplyCode.reply0x86, -29, ReplyCode.reply0xac, 82, 22, ReplyCode.reply0x86, 42, -40, ReplyCode.reply0xb1, 18, ReplyCode.reply0x8c, -42, 101, -70, 44, 11, 62, ReplyCode.reply0xcf, -3, -22, -2, 66, 90, ReplyCode.reply0x8c, ReplyCode.reply0xb5, -99, ReplyCode.reply0x22, 121, 69, 10, ReplyCode.reply0xaf, ReplyCode.reply0xc7, 89, -23, -36, -60, ReplyCode.reply0xaf, 67, ReplyCode.reply0x8e, 10, ReplyCode.reply0x4f, ReplyCode.reply0x64, 29, 47, -24, 110, -66, -7, 87, 16, ReplyCode.reply0x83, ReplyCode.reply0xa5, -43, -103, 67, -20, ReplyCode.reply0x29, 117, -37, -11, 2, 3, 1, 0, 1};
        a = bArr;
        try {
            b = (RSAPublicKey) KeyFactory.getInstance(RSAUtils.KEY_ALGORITHM).generatePublic(new X509EncodedKeySpec(bArr));
        } catch (Throwable unused) {
            g.j.a.a.a.c.D("rsa key pair init failure!!!");
        }
    }

    public static String a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(1, b);
            return Base64.encodeToString(b(cipher, 1, str.getBytes("UTF-8"), b.getModulus().bitLength()), 2);
        } catch (Throwable unused) {
            return null;
        }
    }

    private static byte[] b(Cipher cipher, int i2, byte[] bArr, int i3) {
        if (cipher == null || bArr == null) {
            return null;
        }
        int i4 = i3 / 8;
        if (i2 != 2) {
            i4 -= 11;
        }
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            int i5 = 0;
            int i6 = 0;
            while (bArr.length > i5) {
                byte[] doFinal = bArr.length - i5 > i4 ? cipher.doFinal(bArr, i5, i4) : cipher.doFinal(bArr, i5, bArr.length - i5);
                byteArrayOutputStream.write(doFinal, 0, doFinal.length);
                i6++;
                i5 = i6 * i4;
            }
            return byteArrayOutputStream.toByteArray();
        } catch (Exception e2) {
            throw new RuntimeException(e2);
        }
    }
}
