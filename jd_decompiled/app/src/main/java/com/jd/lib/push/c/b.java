package com.jd.lib.push.c;

/* loaded from: classes16.dex */
public class b {
    private com.jd.lib.push.c.a a;

    /* loaded from: classes16.dex */
    public static class a {
        private static b a = new b();
    }

    public static b b() {
        return a.a;
    }

    public synchronized com.jd.lib.push.c.a a() {
        if (this.a == null) {
            int a2 = com.jingdong.jdpush_new.j.l.a();
            com.jingdong.jdpush_new.j.g.h("device type is " + a2);
            this.a = c.a(a2);
        }
        return this.a;
    }

    public void c() {
        a().f();
    }
}
