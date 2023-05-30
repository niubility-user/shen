package com.jd.stat.network;

import java.util.HashSet;
import java.util.Iterator;

/* loaded from: classes18.dex */
public class g {
    private static final HashSet<Class<?>> a = new HashSet<>();
    private static final HashSet<Class<?>> b = new HashSet<>();

    public static boolean a(Exception exc, int i2, d dVar) {
        if (i2 >= dVar.b() || dVar.c()) {
            return false;
        }
        return a(a, exc) || !a(b, exc);
    }

    protected static boolean a(HashSet<Class<?>> hashSet, Throwable th) {
        Iterator<Class<?>> it = hashSet.iterator();
        while (it.hasNext()) {
            if (it.next().isInstance(th)) {
                return true;
            }
        }
        return false;
    }
}
