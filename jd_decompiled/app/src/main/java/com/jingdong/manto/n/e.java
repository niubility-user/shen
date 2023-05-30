package com.jingdong.manto.n;

import com.jingdong.manto.preload.MantoMPReceiver0;
import com.jingdong.manto.preload.MantoMPReceiver1;
import com.jingdong.manto.preload.MantoMPReceiver2;
import com.jingdong.manto.preload.MantoMPReceiver3;
import com.jingdong.manto.preload.MantoMPReceiver4;
import com.jingdong.manto.ui.MantoActivity0;
import com.jingdong.manto.ui.MantoActivity1;
import com.jingdong.manto.ui.MantoActivity2;
import com.jingdong.manto.ui.MantoActivity3;
import com.jingdong.manto.ui.MantoActivity4;
import java.util.Iterator;
import java.util.LinkedList;

/* loaded from: classes16.dex */
public class e {
    private final LinkedList<d> a;

    public e() {
        LinkedList<d> linkedList = new LinkedList<>();
        this.a = linkedList;
        linkedList.add(new d(MantoActivity0.class, MantoMPReceiver0.class));
        linkedList.add(new d(MantoActivity1.class, MantoMPReceiver1.class));
        linkedList.add(new d(MantoActivity2.class, MantoMPReceiver2.class));
        linkedList.add(new d(MantoActivity3.class, MantoMPReceiver3.class));
        linkedList.add(new d(MantoActivity4.class, MantoMPReceiver4.class));
    }

    private LinkedList<d> a() {
        LinkedList<d> linkedList = new LinkedList<>();
        linkedList.addAll(this.a);
        return linkedList;
    }

    public d a(String str) {
        Iterator<d> it = a().iterator();
        while (it.hasNext()) {
            d next = it.next();
            if (next.d.keySet().contains(str)) {
                return next;
            }
        }
        return null;
    }

    public void a(int i2) {
        Iterator<d> it = a().iterator();
        while (it.hasNext()) {
            d next = it.next();
            for (f fVar : next.f13867e.values()) {
                if (fVar != null) {
                    fVar.f13871g = 101;
                    fVar.f13872h = i2;
                    fVar.g();
                }
            }
            next.d.clear();
            next.f13867e.clear();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(d dVar) {
        if (this.a.contains(dVar)) {
            this.a.remove(dVar);
            this.a.addLast(dVar);
        }
    }

    public void a(f fVar) {
        d b = b(fVar.f13870f);
        if (b != null) {
            b.a(fVar.f13873i, fVar.f13869e, fVar);
            a(b);
        }
    }

    public void a(String str, String str2) {
        Iterator<d> it = a().iterator();
        while (it.hasNext()) {
            d next = it.next();
            boolean z = false;
            for (f fVar : next.f13867e.values()) {
                if (fVar != null && fVar.f13873i.equals(str)) {
                    z = true;
                    fVar.f13871g = 101;
                    fVar.g();
                }
            }
            if (z) {
                next.d.clear();
                next.f13867e.clear();
            }
        }
    }

    public d b() {
        d dVar;
        int i2 = 0;
        while (true) {
            if (i2 >= this.a.size()) {
                dVar = null;
                break;
            } else if (this.a.get(i2).d.keySet().isEmpty()) {
                dVar = this.a.get(i2);
                break;
            } else {
                i2++;
            }
        }
        return dVar == null ? this.a.getFirst() : dVar;
    }

    public d b(String str) {
        Iterator<d> it = a().iterator();
        while (it.hasNext()) {
            d next = it.next();
            if (next.a.equals(str)) {
                return next;
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b(f fVar) {
        Iterator<d> it = a().iterator();
        while (it.hasNext()) {
            d next = it.next();
            if (next.a.equals(fVar.f13870f)) {
                next.d.remove(fVar.f13873i);
                next.f13867e.remove(fVar.f13873i);
                if (next.d.keySet().isEmpty() && this.a.contains(next)) {
                    this.a.remove(next);
                    this.a.addFirst(next);
                }
            }
        }
    }

    public void c() {
        d dVar;
        int i2 = 0;
        while (true) {
            if (i2 >= this.a.size()) {
                dVar = null;
                break;
            } else if (this.a.get(i2).d.keySet().isEmpty()) {
                dVar = this.a.get(i2);
                break;
            } else {
                i2++;
            }
        }
        if (dVar != null) {
            dVar.a();
        }
    }

    public void c(String str) {
        Iterator<d> it = a().iterator();
        while (it.hasNext()) {
            f a = it.next().a(str);
            if (a != null) {
                a.f13871g = 100;
                a.f13873i = str;
                a.g();
            }
        }
    }
}
