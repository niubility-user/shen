package com.jingdong.manto.p.h;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes16.dex */
public final class a {
    private static AtomicInteger b = new AtomicInteger(1);
    public HashMap<String, c> a;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes16.dex */
    public static class b {
        private static a a = new a();
    }

    private a() {
        this.a = new HashMap<>();
    }

    public static int a() {
        return b.incrementAndGet();
    }

    public static a b() {
        return b.a;
    }

    public final c a(String str) {
        if (this.a.containsKey(str)) {
            return this.a.get(str);
        }
        return null;
    }

    public final void a(String str, com.jingdong.manto.p.h.b bVar) {
        c a = a(str);
        if (a != null) {
            a.a.remove(bVar);
        }
    }
}
