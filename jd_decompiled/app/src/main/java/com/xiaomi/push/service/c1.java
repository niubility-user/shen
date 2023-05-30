package com.xiaomi.push.service;

import com.xiaomi.push.service.b1;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes11.dex */
class c1 implements Runnable {

    /* renamed from: g  reason: collision with root package name */
    final /* synthetic */ b1 f19072g;

    /* JADX INFO: Access modifiers changed from: package-private */
    public c1(b1 b1Var) {
        this.f19072g = b1Var;
    }

    @Override // java.lang.Runnable
    public void run() {
        ConcurrentHashMap concurrentHashMap;
        try {
            concurrentHashMap = this.f19072g.d;
            Iterator it = concurrentHashMap.values().iterator();
            while (it.hasNext()) {
                ((b1.a) it.next()).run();
            }
        } catch (Exception e2) {
            g.j.a.a.a.c.o("Sync job exception :" + e2.getMessage());
        }
        this.f19072g.f19063c = false;
    }
}
