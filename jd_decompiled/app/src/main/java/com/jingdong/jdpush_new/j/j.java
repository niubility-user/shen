package com.jingdong.jdpush_new.j;

import android.text.TextUtils;
import android.util.Base64;
import com.jingdong.common.jdreactFramework.utils.RSAUtils;
import java.io.ByteArrayOutputStream;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;

/* loaded from: classes12.dex */
public class j {
    public static String a = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDJnWxi66oGdbxFd7ODH6B+Rj4l\nqLomHA49H8dDRAmjp4AYc+uhGZRcek4Ts//x2SVH4qe/qwvo6OsLhVVktcEntRwm\n2nUkJI79huyRmJJX3CMYT+Bu7mqpwPUypY6OyH9oWTSqWMsCchzk2SIdUmYeRqrU\n917J2yEWi4D0hyV+4QIDAQAB";

    public static byte[] a(byte[] bArr, String str) throws Exception {
        byte[] doFinal;
        PublicKey generatePublic = KeyFactory.getInstance(RSAUtils.KEY_ALGORITHM).generatePublic(new X509EncodedKeySpec(Base64.decode(str, 0)));
        Cipher cipher = Cipher.getInstance("RSA/None/PKCS1Padding");
        cipher.init(2, generatePublic);
        int length = bArr.length;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i2 = 0;
        int i3 = 0;
        while (true) {
            int i4 = length - i2;
            if (i4 > 0) {
                if (i4 > 128) {
                    doFinal = cipher.doFinal(bArr, i2, 128);
                } else {
                    doFinal = cipher.doFinal(bArr, i2, i4);
                }
                byteArrayOutputStream.write(doFinal, 0, doFinal.length);
                i3++;
                i2 = i3 * 128;
            } else {
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                byteArrayOutputStream.close();
                return byteArray;
            }
        }
    }

    public static String b(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        try {
            String str2 = "";
            for (byte b : MessageDigest.getInstance(MessageDigestAlgorithms.MD5).digest(str.getBytes())) {
                String hexString = Integer.toHexString(b & 255);
                if (hexString.length() == 1) {
                    hexString = "0" + hexString;
                }
                str2 = str2 + hexString;
            }
            return str2;
        } catch (NoSuchAlgorithmException e2) {
            g.g(e2);
            return "";
        }
    }
}
