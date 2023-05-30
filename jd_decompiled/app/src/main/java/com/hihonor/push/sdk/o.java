package com.hihonor.push.sdk;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Base64;
import com.jdpay.lib.crypto.AES;
import com.jingdong.common.utils.security.JDKeyStore;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: classes12.dex */
public class o {
    public static volatile z a;
    public static final o b = new o();

    public final void a(Context context) {
        if (a == null) {
            a = new z(context, "push");
        }
    }

    public synchronized void b(Context context, String str) {
        byte[] bArr;
        byte[] bArr2;
        a(context);
        if (TextUtils.isEmpty(str)) {
            a.a("key_push_token");
        } else {
            String f2 = i.f(context, context.getPackageName());
            byte[] j2 = i.j("EA23F5B8C7577CDC744ABD1C6D7E143D5123F8F282BF4E7853C1EC86BD2EDD22");
            byte[] j3 = i.j(f2);
            try {
                bArr = new byte[32];
                new SecureRandom().nextBytes(bArr);
            } catch (Exception unused) {
                bArr = new byte[0];
            }
            i.k(j2, -4);
            byte[] l2 = i.l(j2, j3);
            i.k(l2, 6);
            String encodeToString = Base64.encodeToString(i.l(l2, bArr), 0);
            boolean b2 = a.b("key_aes_gcm", encodeToString);
            byte[] decode = Base64.decode(encodeToString, 0);
            String str2 = "";
            if (!TextUtils.isEmpty(str) && decode != null && decode.length >= 16) {
                try {
                    try {
                        bArr2 = new byte[12];
                        new SecureRandom().nextBytes(bArr2);
                    } catch (Exception unused2) {
                        bArr2 = new byte[0];
                    }
                    byte[] bytes = str.getBytes(StandardCharsets.UTF_8);
                    SecretKeySpec secretKeySpec = new SecretKeySpec(decode, JDKeyStore.KEY_TYPE_AES);
                    Cipher cipher = Cipher.getInstance(AES.ALGORITHM_STANDARD);
                    cipher.init(1, secretKeySpec, new GCMParameterSpec(128, bArr2));
                    byte[] doFinal = cipher.doFinal(bytes);
                    if (doFinal != null && doFinal.length != 0) {
                        str2 = i.g(bArr2) + i.g(doFinal);
                    }
                } catch (GeneralSecurityException e2) {
                    String str3 = "GCM encrypt data error" + e2.getMessage();
                }
            }
            if (b2 && !TextUtils.isEmpty(str2)) {
                a.b("key_push_token", str2);
            }
        }
    }

    public synchronized String c(Context context) {
        String str;
        a(context);
        str = "";
        SharedPreferences sharedPreferences = a.a;
        boolean z = true;
        if (sharedPreferences != null && sharedPreferences.contains("key_push_token")) {
            SharedPreferences sharedPreferences2 = a.a;
            if (sharedPreferences2 == null || !sharedPreferences2.contains("key_aes_gcm")) {
                z = false;
            }
            if (z) {
                SharedPreferences sharedPreferences3 = a.a;
                String string = sharedPreferences3 != null ? sharedPreferences3.getString("key_push_token", "") : "";
                SharedPreferences sharedPreferences4 = a.a;
                byte[] decode = Base64.decode(sharedPreferences4 != null ? sharedPreferences4.getString("key_aes_gcm", "") : "", 0);
                String str2 = "";
                if (!TextUtils.isEmpty(string) && decode != null && decode.length >= 16) {
                    try {
                        SecretKeySpec secretKeySpec = new SecretKeySpec(decode, JDKeyStore.KEY_TYPE_AES);
                        Cipher cipher = Cipher.getInstance(AES.ALGORITHM_STANDARD);
                        String substring = string.substring(0, 24);
                        String substring2 = string.substring(24);
                        if (!TextUtils.isEmpty(substring) && !TextUtils.isEmpty(substring2)) {
                            cipher.init(2, secretKeySpec, new GCMParameterSpec(128, i.j(substring)));
                            str2 = new String(cipher.doFinal(i.j(substring2)), StandardCharsets.UTF_8);
                        }
                    } catch (Exception e2) {
                        String str3 = "GCM decrypt data exception: " + e2.getMessage();
                    }
                }
                if (TextUtils.isEmpty(str2)) {
                    a.a("key_aes_gcm");
                    a.a("key_push_token");
                } else {
                    str = str2;
                }
            } else {
                a.a("key_push_token");
            }
        }
        return str;
    }
}
