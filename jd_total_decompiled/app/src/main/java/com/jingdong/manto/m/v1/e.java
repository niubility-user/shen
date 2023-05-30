package com.jingdong.manto.m.v1;

import android.util.SparseArray;
import com.jingdong.manto.h;
import com.jingdong.manto.jsengine.IMantoWebViewJS;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes15.dex */
public class e {
    private final AtomicInteger a = new AtomicInteger(0);
    public final SparseArray<a> b = new SparseArray<>();

    /* renamed from: c  reason: collision with root package name */
    private h f13810c;

    public e(h hVar) {
        this.f13810c = hVar;
    }

    public int a(String str) {
        h hVar = this.f13810c;
        if (hVar == null) {
            return -100;
        }
        IMantoWebViewJS g2 = hVar.g();
        if (g2 instanceof com.jingdong.manto.jsengine.c) {
            return -101;
        }
        a bVar = g2 instanceof com.jingdong.manto.jsengine.e ? new b(this.f13810c.h()) : new f(this.f13810c.h());
        bVar.start();
        h hVar2 = this.f13810c;
        c cVar = new c(hVar2);
        int a = bVar.a(cVar, str, hVar2.j());
        if (a != 0) {
            bVar.a();
            return a;
        }
        int addAndGet = this.a.addAndGet(1);
        this.b.put(addAndGet, bVar);
        cVar.b = addAndGet;
        return addAndGet;
    }

    public void a() {
        if (this.b == null) {
            return;
        }
        for (int i2 = 0; i2 < this.b.size(); i2++) {
            a aVar = this.b.get(i2);
            if (aVar != null) {
                aVar.a();
            }
        }
        this.b.clear();
    }

    public void a(int i2) {
        a aVar = this.b.get(i2);
        if (aVar != null) {
            aVar.a();
            this.b.remove(i2);
        }
    }

    public void a(int i2, String str) {
        a aVar = this.b.get(i2);
        if (aVar != null) {
            aVar.a(str);
        }
    }

    public void b(int i2, String str) {
        a aVar = this.b.get(i2);
        if (aVar != null) {
            aVar.b(str);
        }
    }
}
