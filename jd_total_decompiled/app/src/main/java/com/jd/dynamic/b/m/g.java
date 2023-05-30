package com.jd.dynamic.b.m;

import com.jd.dynamic.DYConstants;
import com.jd.dynamic.b.m.c;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes13.dex */
public class g {
    private final AtomicBoolean a;
    private final Object b;

    /* renamed from: c  reason: collision with root package name */
    private final c f1815c;
    private final HashMap<e, i> d;

    /* loaded from: classes13.dex */
    private static class b {
        private static final g a = new g();
    }

    private g() {
        this.a = new AtomicBoolean(false);
        this.b = new Object();
        this.f1815c = new c("dy-storage-manager");
        this.d = new LinkedHashMap();
    }

    public static g a() {
        return b.a;
    }

    public i b(e eVar) {
        i iVar;
        synchronized (this.b) {
            iVar = this.d.get(eVar);
        }
        return iVar;
    }

    void c(e eVar, i iVar) {
        synchronized (this.b) {
            this.d.put(eVar, iVar);
        }
    }

    public void d() {
        if (this.a.get()) {
            return;
        }
        LinkedList linkedList = new LinkedList();
        linkedList.add(new c.e(System.currentTimeMillis(), 300000L));
        linkedList.add(new c.C0074c());
        linkedList.add(new c.d(DYConstants.TEMP_NAME_PREFIX));
        c(e.CLEAN_TYPE_ILLEGAL_FILE, new com.jd.dynamic.b.m.a.b(linkedList));
        c(e.CLEAN_TYPE_ILLEGAL_FILE_RECURSIVE, new com.jd.dynamic.b.m.a.c(linkedList));
        c(e.CLEAN_TYPE_BACKUP_UNZIP_FILE, new com.jd.dynamic.b.m.a.a(new c.b()));
        this.a.set(true);
    }

    public h e() {
        return this.f1815c;
    }
}
