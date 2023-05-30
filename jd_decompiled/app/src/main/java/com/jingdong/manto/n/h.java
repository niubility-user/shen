package com.jingdong.manto.n;

import com.jingdong.manto.utils.MantoLog;
import java.util.Iterator;
import java.util.LinkedList;

/* loaded from: classes16.dex */
public class h {
    private final LinkedList<g> a = new LinkedList<>();

    public g a(String str) {
        Iterator<g> it = a().iterator();
        while (it.hasNext()) {
            g next = it.next();
            if (next.b.keySet().contains(str)) {
                return next;
            }
        }
        return null;
    }

    public LinkedList<g> a() {
        LinkedList<g> linkedList = new LinkedList<>();
        linkedList.addAll(this.a);
        return linkedList;
    }

    public void a(int i2) {
        MantoLog.d("MantoMPRecordManager----->", "finishByAppType " + i2);
        Iterator<g> it = a().iterator();
        while (it.hasNext()) {
            g next = it.next();
            for (f fVar : next.f13876c.values()) {
                if (fVar != null) {
                    MantoLog.d("MantoMPRecordManager----->", "finishByAppType finish appId " + fVar.f13873i);
                    fVar.f13871g = 101;
                    fVar.f13872h = i2;
                    fVar.g();
                }
            }
            this.a.remove(next);
            MantoLog.d("MantoMPRecordManager----->", "finishByAppId remove record " + next.a + ", left appMTRecords size " + this.a.size());
        }
    }

    public void a(f fVar) {
        String str;
        g b = b(fVar.f13875k);
        if (b != null) {
            b.a(fVar.f13873i, fVar.f13869e, fVar);
            a(b);
            str = "cacheInMain, found record by hashCode " + fVar.f13875k + ", size " + this.a.size();
        } else {
            g a = a(fVar.f13873i);
            if (a == null) {
                MantoLog.e("MantoMPRecordManager----->", "cacheInMain, not found error");
                return;
            }
            a.a = fVar.f13875k;
            a.a(fVar.f13873i, fVar.f13869e, fVar);
            a(a);
            str = "cacheInMain, found record " + a.a + " by appid " + fVar.f13873i + ", size " + this.a.size();
        }
        MantoLog.d("MantoMPRecordManager----->", str);
    }

    void a(g gVar) {
        if (this.a.contains(gVar)) {
            this.a.remove(gVar);
            this.a.addLast(gVar);
        }
    }

    public void a(String str, String str2) {
        MantoLog.d("MantoMPRecordManager----->", "finishByAppId " + str);
        Iterator<g> it = a().iterator();
        while (it.hasNext()) {
            g next = it.next();
            for (f fVar : next.f13876c.values()) {
                if (fVar != null && fVar.f13873i.equals(str)) {
                    MantoLog.d("MantoMPRecordManager----->", "finishByAppId finish appId " + fVar.f13873i);
                    fVar.f13871g = 101;
                    fVar.g();
                    this.a.remove(next);
                    MantoLog.d("MantoMPRecordManager----->", "finishByAppId remove record " + next.a + ", left appMTRecords size " + this.a.size());
                }
            }
        }
    }

    public g b() {
        MantoLog.d("MantoMPRecordManager----->", "getPreferAppRecord, appMTRecords record begin size " + this.a.size());
        if (this.a.size() >= 5) {
            g first = this.a.getFirst();
            for (f fVar : first.f13876c.values()) {
                if (fVar != null) {
                    fVar.f13871g = 101;
                    fVar.g();
                }
            }
            this.a.remove(first);
            MantoLog.d("MantoMPRecordManager----->", "getPreferAppRecord, reach max 5, release first");
        }
        g gVar = new g();
        this.a.add(gVar);
        MantoLog.d("MantoMPRecordManager----->", "getPreferAppRecord, appMTRecords record end size " + this.a.size());
        return gVar;
    }

    public g b(int i2) {
        Iterator<g> it = a().iterator();
        while (it.hasNext()) {
            g next = it.next();
            if (next.a == i2) {
                return next;
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b(f fVar) {
        MantoLog.d("MantoMPRecordManager----->", "releaseRecord " + fVar.f13873i + ", record " + fVar.f13875k);
        Iterator<g> it = a().iterator();
        while (it.hasNext()) {
            g next = it.next();
            if (next.a == fVar.f13875k) {
                next.b.remove(fVar.f13873i);
                next.f13876c.remove(fVar.f13873i);
                if (next.b.keySet().isEmpty() && this.a.contains(next)) {
                    this.a.remove(next);
                    MantoLog.d("MantoMPRecordManager----->", "releaseRecord remove record " + next.a + ", appId " + fVar.f13873i + ", left appMTRecords size " + this.a.size());
                }
            }
        }
    }

    public void b(String str) {
        MantoLog.d("MantoMPRecordManager----->", "releasePreByAppId " + str);
        Iterator<g> it = a().iterator();
        while (it.hasNext()) {
            g next = it.next();
            f a = next.a(str);
            if (a != null) {
                MantoLog.d("MantoMPRecordManager----->", "found record to release " + next.a);
                a.f13871g = 100;
                a.f13873i = str;
                a.g();
            }
        }
    }
}
