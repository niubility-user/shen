package com.jd.fireeye.b;

import android.util.Base64;
import com.jdpay.lib.crypto.AES;
import com.jingdong.common.jdreactFramework.utils.RSAUtils;
import com.jingdong.common.sample.jshop.utils.DataCompassUtils;
import com.jingdong.common.utils.security.JDKeyStore;
import java.io.IOException;
import java.net.URLEncoder;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Random;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.json.JSONObject;

/* loaded from: classes13.dex */
public class b {
    public static byte[] a(String str) throws IOException {
        return Base64.decode(str.getBytes(), 0);
    }

    public static JSONObject b(String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("appkey", com.jd.fireeye.security.a.a());
            jSONObject.put(DataCompassUtils.MODULE_TYPE_HEAD, com.jd.fireeye.security.a.b());
            jSONObject.put("info", URLEncoder.encode(a(str, com.jd.fireeye.security.a.j()), "UTF-8"));
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return jSONObject;
    }

    public static String a(byte[] bArr) {
        return new String(Base64.encode(bArr, 0));
    }

    public static String a() {
        Random random = new Random();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i2 = 0; i2 < 16; i2++) {
            stringBuffer.append("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".charAt(random.nextInt(62)));
        }
        return stringBuffer.toString();
    }

    public static byte[] a(byte[] bArr, String str, int i2) throws Exception {
        SecretKeySpec secretKeySpec = new SecretKeySpec(str.getBytes("UTF-8"), JDKeyStore.KEY_TYPE_AES);
        IvParameterSpec ivParameterSpec = new IvParameterSpec("16-Bytes--String".getBytes("UTF-8"));
        Cipher cipher = Cipher.getInstance(AES.ALGORITHM);
        cipher.init(i2, secretKeySpec, ivParameterSpec);
        return cipher.doFinal(bArr);
    }

    public static String b(String str, String str2) throws Exception {
        PublicKey generatePublic = KeyFactory.getInstance(RSAUtils.KEY_ALGORITHM).generatePublic(new X509EncodedKeySpec(a(str2)));
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(1, generatePublic);
        return a(cipher.doFinal(str.getBytes("UTF-8")));
    }

    public static String a(String str, String str2) throws Exception {
        return a(a(str.getBytes("UTF-8"), str2, 1));
    }
}
