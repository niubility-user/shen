package com.sina.weibo.sdk.auth;

import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes9.dex */
public final class b {

    /* renamed from: e  reason: collision with root package name */
    private Map<String, WbAuthListener> f16077e;

    /* loaded from: classes9.dex */
    static class a {
        private static final b a = new b(0);
    }

    /* synthetic */ b(byte b) {
        this();
    }

    public static synchronized b b() {
        b bVar;
        synchronized (b.class) {
            bVar = a.a;
        }
        return bVar;
    }

    public final synchronized void a(String str, WbAuthListener wbAuthListener) {
        if (!TextUtils.isEmpty(str) && wbAuthListener != null) {
            this.f16077e.put(str, wbAuthListener);
        }
    }

    private b() {
        this.f16077e = new HashMap();
    }

    public final synchronized void b(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.f16077e.remove(str);
    }

    public final synchronized WbAuthListener a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return this.f16077e.get(str);
    }
}
