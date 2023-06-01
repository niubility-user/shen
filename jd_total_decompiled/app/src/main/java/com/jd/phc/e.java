package com.jd.phc;

import android.content.Context;
import android.text.TextUtils;
import android.util.Base64;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class e {
    public static c d = c.Official;

    /* renamed from: e */
    private static e f6851e;
    private Context a;
    private String b;

    /* renamed from: c */
    boolean f6852c = false;

    /* loaded from: classes.dex */
    public interface a {
        void a(String str, String str2);
    }

    /* loaded from: classes.dex */
    public enum b {
        AES_CBC_PKCS5Padding(0),
        AES_CTR_NoPadding(1),
        RC4(2),
        RC4_CRC32CHECKSUM(4),
        MODIFIED_BASE64(5);
        
        private int value;

        b(int i2) {
            this.value = i2;
        }

        public int value() {
            return this.value;
        }
    }

    /* loaded from: classes.dex */
    public enum c {
        Pre,
        Official
    }

    private e(Context context) {
        this.a = context.getApplicationContext();
        this.b = context.getPackageName();
    }

    public static e c(Context context) {
        if (f6851e == null) {
            synchronized (e.class) {
                if (f6851e == null) {
                    f6851e = new e(context);
                }
            }
        }
        return f6851e;
    }

    public static e d(Context context, String str, boolean z) {
        if (f6851e == null) {
            synchronized (e.class) {
                if (f6851e == null) {
                    f6851e = new e(context);
                }
            }
        }
        return f6851e;
    }

    public Map<String, String> a(String str) throws IllegalArgumentException {
        if (!TextUtils.isEmpty(str)) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                long optLong = jSONObject.optLong("ts");
                jSONObject.optString("version");
                int optInt = jSONObject.optInt("ciphertype");
                String optString = jSONObject.optString("cipher");
                String optString2 = jSONObject.optString("hdid");
                String optString3 = jSONObject.optString("appname");
                int optInt2 = jSONObject.optInt("ridx");
                byte[] bArr = f.f6855c;
                if (optInt2 == -1) {
                    bArr = Base64.decode("BHqxx7mF6ozKsygZ3HUozSM7rED0qHts6lZNeFRdOls=", 2);
                } else if (bArr == null) {
                    return null;
                }
                ByteBuffer order = ByteBuffer.allocate(8).order(ByteOrder.BIG_ENDIAN);
                order.putLong(optLong);
                new d();
                JSONObject jSONObject2 = new JSONObject(optString);
                HashMap hashMap = new HashMap();
                Iterator<String> keys = jSONObject2.keys();
                if (optInt == b.MODIFIED_BASE64.value()) {
                    while (keys.hasNext()) {
                        String next = keys.next();
                        String str2 = new String(d.a(jSONObject2.optString(next)));
                        com.jd.phc.i.b.a("PHCEngine", "Decoded is " + str2);
                        hashMap.put(next, str2);
                    }
                } else {
                    byte[] c2 = PHCNativeLoader.f().c(bArr, optString2, optString3, order.array());
                    while (keys.hasNext()) {
                        String next2 = keys.next();
                        hashMap.put(next2, new String(PHCNativeLoader.f().a(c2, optInt, Base64.decode(jSONObject2.optString(next2), 2)), Charset.forName("UTF-8")));
                    }
                }
                return hashMap;
            } catch (Exception e2) {
                if (com.jd.phc.a.a) {
                    e2.printStackTrace();
                }
                return null;
            } catch (Throwable th) {
                if (com.jd.phc.a.a) {
                    th.printStackTrace();
                }
                return null;
            }
        }
        throw new IllegalArgumentException("plaintext can not null;");
    }

    public String b(Map<String, String> map, b bVar) throws IllegalArgumentException {
        if (map != null && !map.isEmpty() && bVar != null) {
            new d();
            try {
                long currentTimeMillis = System.currentTimeMillis();
                ByteBuffer order = ByteBuffer.allocate(8).order(ByteOrder.BIG_ENDIAN);
                order.putLong(currentTimeMillis);
                int i2 = f.f6856e;
                byte[] bArr = f.f6855c;
                String str = f.d;
                if (bArr == null) {
                    bArr = Base64.decode("BHqxx7mF6ozKsygZ3HUozSM7rED0qHts6lZNeFRdOls=", 2);
                    str = "JM9F1ywUPwflvMIpYPok0tt5k9kW4ArJEU3lfLhxBqw=";
                    i2 = -1;
                }
                JSONObject jSONObject = new JSONObject();
                com.jd.phc.i.b.a("PHCEngine", "brian cipher type is :" + bVar.value());
                if (bVar.value() == b.MODIFIED_BASE64.value()) {
                    for (String str2 : map.keySet()) {
                        String str3 = map.get(str2);
                        String b2 = d.b(str3.getBytes());
                        com.jd.phc.i.b.a("PHCEngine", "brian Encoded str of " + str3 + " is :" + b2);
                        jSONObject.put(str2, b2);
                    }
                } else {
                    byte[] c2 = PHCNativeLoader.f().c(bArr, str, this.b, order.array());
                    for (String str4 : map.keySet()) {
                        jSONObject.put(str4, Base64.encodeToString(PHCNativeLoader.f().b(c2, bVar.value(), map.get(str4).getBytes()), 2));
                    }
                }
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("hdid", str);
                jSONObject2.put("ts", currentTimeMillis);
                jSONObject2.put("ridx", i2);
                jSONObject2.put("cipher", jSONObject); // 加密函数
                jSONObject2.put("ciphertype", bVar.value());
                jSONObject2.put("version", com.jd.phc.c.a());
                jSONObject2.put("appname", this.b);
                return jSONObject2.toString();
            } catch (Exception e2) {
                if (com.jd.phc.a.a) {
                    e2.printStackTrace();
                    return "";
                }
                return "";
            } catch (Throwable th) {
                if (com.jd.phc.a.a) {
                    th.printStackTrace();
                    return "";
                }
                return "";
            }
        }
        throw new IllegalArgumentException("plaintext can not null;");
    }

    public void e(a aVar) {
        f(c.Official, aVar);
    }

    public void f(c cVar, a aVar) {
        d = cVar;
        com.jd.phc.i.b.a("PHCEngine", "In init :" + b.MODIFIED_BASE64.value() + f.b());
        if (aVar != null) {
            if (f.b() || !this.f6852c) {
                new com.jd.phc.b(this.a, aVar).execute((Object[]) null);
            }
        }
    }

    public void g(boolean z) {
        com.jd.phc.a.a(z);
    }
}
