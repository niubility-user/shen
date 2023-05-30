package com.xiaomi.push;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes11.dex */
public class n9 {
    private static volatile n9 d;
    private Context a;
    private Handler b = new Handler(Looper.getMainLooper());

    /* renamed from: c  reason: collision with root package name */
    private Map<String, Map<String, String>> f18890c = new HashMap();

    private n9(Context context) {
        this.a = context;
    }

    public static n9 b(Context context) {
        if (d == null) {
            synchronized (n9.class) {
                if (d == null) {
                    d = new n9(context);
                }
            }
        }
        return d;
    }

    private synchronized String c(String str, String str2) {
        if (this.f18890c != null && !TextUtils.isEmpty(str)) {
            if (!TextUtils.isEmpty(str2)) {
                try {
                    Map<String, String> map = this.f18890c.get(str);
                    if (map != null) {
                        return map.get(str2);
                    }
                    return "";
                } catch (Throwable unused) {
                    return "";
                }
            }
        }
        return "";
    }

    private synchronized void f(String str, String str2, String str3) {
        if (this.f18890c == null) {
            this.f18890c = new HashMap();
        }
        Map<String, String> map = this.f18890c.get(str);
        if (map == null) {
            map = new HashMap<>();
        }
        map.put(str2, str3);
        this.f18890c.put(str, map);
    }

    public synchronized String d(String str, String str2, String str3) {
        String c2 = c(str, str2);
        if (TextUtils.isEmpty(c2)) {
            return this.a.getSharedPreferences(str, 4).getString(str2, str3);
        }
        return c2;
    }

    public synchronized void e(String str, String str2, String str3) {
        f(str, str2, str3);
        this.b.post(new o9(this, str, str2, str3));
    }
}
