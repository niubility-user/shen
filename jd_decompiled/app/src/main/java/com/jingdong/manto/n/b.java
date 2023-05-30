package com.jingdong.manto.n;

import java.util.Iterator;
import java.util.LinkedList;

/* loaded from: classes16.dex */
public class b {
    private final LinkedList<a> a = new LinkedList<>();

    public a a(int i2) {
        Iterator<a> it = a().iterator();
        while (it.hasNext()) {
            a next = it.next();
            if (next.a == i2) {
                return next;
            }
        }
        return null;
    }

    public LinkedList<a> a() {
        LinkedList<a> linkedList = new LinkedList<>();
        linkedList.addAll(this.a);
        return linkedList;
    }

    void a(a aVar) {
        if (this.a.contains(aVar)) {
            this.a.remove(aVar);
            this.a.addLast(aVar);
        }
    }

    public void a(f fVar) {
        a a = a(fVar.f13875k);
        if (a != null) {
            a.a(fVar.f13873i, fVar.f13869e, fVar);
            a(a);
            return;
        }
        a b = b();
        b.a = fVar.f13875k;
        b.a(fVar.f13873i, fVar.f13869e, fVar);
    }

    public a b() {
        a aVar = new a();
        this.a.add(aVar);
        return aVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b(f fVar) {
        Iterator<a> it = a().iterator();
        while (it.hasNext()) {
            a next = it.next();
            if (next.a == fVar.f13875k) {
                next.b.remove(fVar.f13873i);
                next.f13864c.remove(fVar.f13873i);
                if (next.b.keySet().isEmpty() && this.a.contains(next)) {
                    this.a.remove(next);
                }
            }
        }
    }
}
