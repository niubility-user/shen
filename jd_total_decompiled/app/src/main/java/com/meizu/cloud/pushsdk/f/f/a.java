package com.meizu.cloud.pushsdk.f.f;

import android.content.Context;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes14.dex */
public abstract class a {

    /* renamed from: i */
    private static final String f15920i = "a";
    protected com.meizu.cloud.pushsdk.f.c.a a;
    protected c b;

    /* renamed from: c */
    protected b f15921c;
    protected final boolean d;

    /* renamed from: e */
    protected final long f15922e;

    /* renamed from: f */
    protected final int f15923f;

    /* renamed from: g */
    protected final TimeUnit f15924g;

    /* renamed from: h */
    protected final AtomicBoolean f15925h = new AtomicBoolean(true);

    /* renamed from: com.meizu.cloud.pushsdk.f.f.a$a */
    /* loaded from: classes14.dex */
    public static class C0765a {
        protected final com.meizu.cloud.pushsdk.f.c.a a;
        protected final String b;

        /* renamed from: c */
        protected final String f15926c;
        protected final Context d;

        /* renamed from: e */
        protected c f15927e = null;

        /* renamed from: f */
        protected boolean f15928f = false;

        /* renamed from: g */
        protected com.meizu.cloud.pushsdk.f.g.b f15929g = com.meizu.cloud.pushsdk.f.g.b.OFF;

        /* renamed from: h */
        protected boolean f15930h = false;

        /* renamed from: i */
        protected long f15931i = 600;

        /* renamed from: j */
        protected long f15932j = 300;

        /* renamed from: k */
        protected long f15933k = 15;

        /* renamed from: l */
        protected int f15934l = 10;

        /* renamed from: m */
        protected TimeUnit f15935m = TimeUnit.SECONDS;

        public C0765a(com.meizu.cloud.pushsdk.f.c.a aVar, String str, String str2, Context context, Class<? extends a> cls) {
            this.a = aVar;
            this.b = str;
            this.f15926c = str2;
            this.d = context;
        }

        public C0765a a(int i2) {
            this.f15934l = i2;
            return this;
        }

        public C0765a b(c cVar) {
            this.f15927e = cVar;
            return this;
        }

        public C0765a c(com.meizu.cloud.pushsdk.f.g.b bVar) {
            this.f15929g = bVar;
            return this;
        }

        public C0765a d(Boolean bool) {
            this.f15928f = bool.booleanValue();
            return this;
        }
    }

    public a(C0765a c0765a) {
        this.a = c0765a.a;
        String str = c0765a.f15926c;
        boolean z = c0765a.f15928f;
        String str2 = c0765a.b;
        this.b = c0765a.f15927e;
        com.meizu.cloud.pushsdk.f.g.b bVar = c0765a.f15929g;
        boolean z2 = c0765a.f15930h;
        this.d = z2;
        this.f15922e = c0765a.f15933k;
        int i2 = c0765a.f15934l;
        this.f15923f = i2 < 2 ? 2 : i2;
        this.f15924g = c0765a.f15935m;
        if (z2) {
            this.f15921c = new b(c0765a.f15931i, c0765a.f15932j, c0765a.f15935m, c0765a.d);
        }
        com.meizu.cloud.pushsdk.f.g.c.d(c0765a.f15929g);
        com.meizu.cloud.pushsdk.f.g.c.g(f15920i, "Tracker created successfully.", new Object[0]);
    }

    private com.meizu.cloud.pushsdk.f.b.b a(List<com.meizu.cloud.pushsdk.f.b.b> list) {
        if (this.d) {
            list.add(this.f15921c.b());
        }
        c cVar = this.b;
        if (cVar != null) {
            if (!cVar.d().isEmpty()) {
                list.add(new com.meizu.cloud.pushsdk.f.b.b("geolocation", this.b.d()));
            }
            if (!this.b.f().isEmpty()) {
                list.add(new com.meizu.cloud.pushsdk.f.b.b("mobileinfo", this.b.f()));
            }
        }
        LinkedList linkedList = new LinkedList();
        Iterator<com.meizu.cloud.pushsdk.f.b.b> it = list.iterator();
        while (it.hasNext()) {
            linkedList.add(it.next().a());
        }
        return new com.meizu.cloud.pushsdk.f.b.b("push_extra_info", linkedList);
    }

    private void c(com.meizu.cloud.pushsdk.f.b.c cVar, List<com.meizu.cloud.pushsdk.f.b.b> list, boolean z) {
        if (this.b != null) {
            cVar.c(new HashMap(this.b.a()));
            cVar.b("et", a(list).a());
        }
        com.meizu.cloud.pushsdk.f.g.c.g(f15920i, "Adding new payload to event storage: %s", cVar);
        this.a.h(cVar, z);
    }

    public com.meizu.cloud.pushsdk.f.c.a b() {
        return this.a;
    }

    public void d(com.meizu.cloud.pushsdk.f.d.b bVar, boolean z) {
        if (this.f15925h.get()) {
            c(bVar.f(), bVar.c(), z);
        }
    }

    public void e(c cVar) {
        this.b = cVar;
    }

    public void f() {
        if (this.f15925h.get()) {
            b().j();
        }
    }
}
