package com.jingdong.app.mall.home.l;

import android.text.TextUtils;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes4.dex */
public final class b {
    private final ConcurrentHashMap<String, com.jingdong.app.mall.home.l.a> a;

    /* renamed from: com.jingdong.app.mall.home.l.b$b  reason: collision with other inner class name */
    /* loaded from: classes4.dex */
    static final class C0305b {
        static b a = new b();
    }

    public static b b() {
        return C0305b.a;
    }

    public void a(String str, com.jingdong.app.mall.home.l.a aVar) {
        this.a.put(str, aVar);
    }

    public com.jingdong.app.mall.home.l.a c(String str) {
        com.jingdong.app.mall.home.l.a aVar;
        if (TextUtils.isEmpty(str) || this.a.size() <= 0 || (aVar = this.a.get(str)) == null || !aVar.d(str)) {
            return null;
        }
        return aVar;
    }

    private b() {
        this.a = new ConcurrentHashMap<>();
    }
}
