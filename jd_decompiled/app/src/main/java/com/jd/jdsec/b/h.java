package com.jd.jdsec.b;

import java.util.HashSet;
import java.util.Iterator;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes13.dex */
public class h {
    private static final HashSet<Class<?>> a = new HashSet<>();
    private static final HashSet<Class<?>> b = new HashSet<>();

    public static boolean a(Exception exc, int i2, e eVar) {
        return i2 < eVar.o() && !eVar.t() && (b(a, exc) || !b(b, exc));
    }

    protected static boolean b(HashSet<Class<?>> hashSet, Throwable th) {
        Iterator<Class<?>> it = hashSet.iterator();
        while (it.hasNext()) {
            if (it.next().isInstance(th)) {
                return true;
            }
        }
        return false;
    }
}
