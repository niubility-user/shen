package com.jd.phc;

import android.content.Context;
import android.text.TextUtils;
import android.util.Base64;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class f {
    private static f b = null;

    /* renamed from: c  reason: collision with root package name */
    static byte[] f6855c = null;
    static String d = null;

    /* renamed from: e  reason: collision with root package name */
    static int f6856e = -1;

    /* renamed from: f  reason: collision with root package name */
    private static String f6857f;
    private Context a;

    private f(Context context) {
        this.a = context.getApplicationContext();
    }

    public static f a(Context context) {
        if (b == null) {
            synchronized (f.class) {
                if (b == null) {
                    b = new f(context);
                }
            }
        }
        return b;
    }

    public static boolean b() {
        byte[] bArr = f6855c;
        return bArr == null || bArr.length == 0;
    }

    private String c() throws JSONException, UnsupportedEncodingException, com.jd.phc.i.d.a {
        String a = g.a(this.a);
        if (!TextUtils.isEmpty(a)) {
            d = Base64.encodeToString(PHCNativeLoader.f().d(a), 2);
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("did", a);
            jSONObject.put("skey", f6857f);
            jSONObject.put("ciphertype", 0);
            jSONObject.put("version", c.a());
            jSONObject.put("ts", System.currentTimeMillis());
            if (a.a) {
                com.jd.phc.i.b.a("PHCManager", "device_info = " + jSONObject.toString());
            }
            byte[] g2 = PHCNativeLoader.f().g("-----BEGIN PUBLIC KEY-----\nMIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAz3oqi/nn8hYjzV+KS3o/\nNIMOCZlmyMgxQgor/c3B3S5YtGqZVWsTkhcF2OnuHRB5GJaSIdyihiCrYLzwgiUq\nU1NKUuws6VphK04VILgYVeR7VuED9rumuWiH0iFiCRuI7QaUnjR+peVhUnsRU6vz\nedtKfwZ7IhYLFk4rYWlQJP3A52pfEPL1wsz3HMchjY56MS/BPvT6wvNRyVuQ60c3\nLa8NOffcnwVRfve1vUJT1XHI5LHrNs0CRIKZWMQ03tQI3h11d8uMUfJWHYJ0IoeS\nLGqTuzC/KtQet5so/XHeEAVMerwwyTujjXUh4CNjawB+cr8C4jNEu0hWg4xW2mVl\nLwIDAQAB\n-----END PUBLIC KEY-----", jSONObject.toString().getBytes("UTF-8"));
            if (g2 != null && g2.length != 0) {
                if (a.a) {
                    com.jd.phc.i.b.a("PHCManager", "enc bytes length = " + g2.length);
                }
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("dsecret_req_cipher", Base64.encodeToString(g2, 2));
                jSONObject2.put("cipher", 0);
                if (a.a) {
                    com.jd.phc.i.b.a("PHCManager", "secret post json = " + jSONObject2.toString());
                }
                return jSONObject2.toString();
            }
            throw new com.jd.phc.i.d.a(com.jd.phc.i.d.d.D_ENCRYPT_ERROR);
        }
        throw new com.jd.phc.i.d.a(com.jd.phc.i.d.d.EID_ERROR);
    }

    public void d() throws JSONException, com.jd.phc.i.d.a, IOException {
        boolean b2;
        try {
            if (com.jd.phc.i.e.b.d(this.a)) {
                f6857f = PHCNativeLoader.f().e();
                String b3 = com.jd.phc.i.e.b.b(h.b, c());
                if (a.a) {
                    com.jd.phc.i.b.a("PHCManager", "response" + b3);
                }
                if (!TextUtils.isEmpty(b3)) {
                    JSONObject jSONObject = new JSONObject(b3);
                    int optInt = jSONObject.optInt("status_code");
                    if (optInt == 0) {
                        f6856e = jSONObject.optInt("ridx");
                        String optString = jSONObject.optString("dsecret_resp_cipher");
                        int i2 = jSONObject.getInt("ciphertype");
                        byte[] decode = Base64.decode(f6857f, 0);
                        if (!TextUtils.isEmpty(optString)) {
                            f6855c = PHCNativeLoader.f().a(decode, i2, Base64.decode(optString, 2));
                            if (!b()) {
                                if (a.a) {
                                    StringBuilder sb = new StringBuilder();
                                    for (byte b4 : f6855c) {
                                        sb.append(String.format("%02x", Integer.valueOf(b4 & 255)));
                                    }
                                    com.jd.phc.i.b.a("PHCManager", "DSecret = " + sb.toString());
                                }
                            } else {
                                throw new com.jd.phc.i.d.a(com.jd.phc.i.d.d.D_DECRYPT_ERROR);
                            }
                        }
                    } else {
                        throw new com.jd.phc.i.d.f(optInt, b3);
                    }
                }
                if (b2) {
                    return;
                }
                return;
            }
            throw new com.jd.phc.i.d.a(com.jd.phc.i.d.d.NO_CONNECT_ERROR);
        } finally {
            if (b()) {
                com.jd.phc.i.b.a("PHCManager", "Dsecret empty, use backup ");
                d = "JM9F1ywUPwflvMIpYPok0tt5k9kW4ArJEU3lfLhxBqw=";
                f6855c = Base64.decode("BHqxx7mF6ozKsygZ3HUozSM7rED0qHts6lZNeFRdOls=", 2);
            }
        }
    }
}
