package com.huawei.hms.hatool;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/* loaded from: classes12.dex */
public final class s {
    static Map<String, l1> b = new HashMap();

    /* renamed from: c */
    private static s f1403c;
    private g1 a = new g1();

    private s() {
    }

    public static s c() {
        if (f1403c == null) {
            d();
        }
        return f1403c;
    }

    private static synchronized void d() {
        synchronized (s.class) {
            if (f1403c == null) {
                f1403c = new s();
            }
        }
    }

    public l1 a(String str) {
        return b.get(str);
    }

    public Set<String> a() {
        return b.keySet();
    }

    public void a(String str, l1 l1Var) {
        b.put(str, l1Var);
    }

    public g1 b() {
        return this.a;
    }
}
