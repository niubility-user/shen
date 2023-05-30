package com.jingdong.manto.p.g;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes16.dex */
public final class a {
    private static AtomicInteger b = new AtomicInteger(1);

    /* renamed from: c  reason: collision with root package name */
    public static a f13934c;
    public HashMap<String, c> a = new HashMap<>();

    private a() {
    }

    public static int a() {
        return b.incrementAndGet();
    }

    public static a b() {
        if (f13934c == null) {
            synchronized (a.class) {
                if (f13934c == null) {
                    f13934c = new a();
                }
            }
        }
        return f13934c;
    }

    public final c a(String str) {
        if (this.a.containsKey(str)) {
            return this.a.get(str);
        }
        return null;
    }

    public final void b(String str) {
        this.a.remove(str);
    }
}
