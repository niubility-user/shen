package com.jingdong.aura.a.b.k;

import java.util.ArrayList;
import java.util.List;
import l.b.a.f;

/* loaded from: classes4.dex */
class c {
    private static List<l.b.a.e> a = new ArrayList();
    private static List<l.b.a.e> b = new ArrayList();

    /* renamed from: c  reason: collision with root package name */
    private static List<f> f12121c = new ArrayList();

    static {
        new ArrayList();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a(l.b.a.e eVar) {
        b.add(eVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void b(l.b.a.e eVar) {
        a.add(eVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a(f fVar) {
        f12121c.add(fVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a(int i2, l.b.a.d dVar) {
        if (a.isEmpty() && b.isEmpty()) {
            return;
        }
        l.b.a.a aVar = new l.b.a.a(i2, dVar);
        List<l.b.a.e> list = a;
        for (l.b.a.e eVar : (l.b.a.e[]) list.toArray(new l.b.a.e[list.size()])) {
            eVar.bundleChanged(aVar);
        }
        if (b.isEmpty()) {
            return;
        }
        List<l.b.a.e> list2 = b;
        for (l.b.a.e eVar2 : (l.b.a.e[]) list2.toArray(new l.b.a.e[list2.size()])) {
            eVar2.bundleChanged(aVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a(int i2, l.b.a.d dVar, Throwable th) {
        if (f12121c.isEmpty()) {
            return;
        }
        l.b.a.c cVar = new l.b.a.c(i2, dVar, th);
        List<f> list = f12121c;
        for (f fVar : (f[]) list.toArray(new f[list.size()])) {
            fVar.a(cVar);
        }
    }
}
