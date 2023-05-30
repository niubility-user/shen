package com.jingdong.c.b;

import android.text.TextUtils;
import com.unionpay.tsmservice.mi.data.Constant;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes10.dex */
public final class i {
    public static final String[] b = {"imei", Constant.KEY_MAC, "randomUUID"};

    /* renamed from: c */
    private static volatile i f12305c;
    public ConcurrentHashMap<String, String> a = new ConcurrentHashMap<>();

    public static i a() {
        if (f12305c != null) {
            return f12305c;
        }
        throw new IllegalStateException("memoryCache doesn't initialize yet.");
    }

    public static synchronized void c(b bVar) {
        synchronized (i.class) {
            if (f12305c == null) {
                f12305c = new i();
                if (bVar.b() != null) {
                    k.a(bVar.b());
                    for (String str : b) {
                        String str2 = "";
                        if (!TextUtils.isEmpty(str)) {
                            String string = k.a.getString(a.d(k.b, str), null);
                            if (!TextUtils.isEmpty(string)) {
                                String b2 = a.b(k.b, string);
                                if (!TextUtils.isEmpty(b2)) {
                                    String str3 = "The cached " + str + " is: " + b2;
                                    str2 = b2;
                                }
                            }
                        }
                        f12305c.a.put(str, str2);
                    }
                }
            }
            k.b("loggable", h.a);
            k.b("wifi_mac_readable", bVar.f());
            k.b("imei_readable", bVar.e());
            k.b("file_cache_enabled", bVar.d());
        }
    }

    public final String b(String str) {
        return this.a.get(str);
    }

    public final void d(String str, String str2) {
        if (TextUtils.isEmpty(str2) || TextUtils.isEmpty(str)) {
            return;
        }
        this.a.put(str, str2);
        if ("androidId".equals(str) || k.a == null || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        k.a.edit().putString(a.d(k.b, str), a.d(k.b, str2)).apply();
    }
}
