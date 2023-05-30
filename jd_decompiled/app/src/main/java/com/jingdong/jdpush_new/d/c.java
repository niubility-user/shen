package com.jingdong.jdpush_new.d;

import android.content.Context;
import com.jingdong.jdsdk.JdSdk;

/* loaded from: classes12.dex */
public class c {

    /* renamed from: c  reason: collision with root package name */
    private static c f12817c;
    private com.jingdong.jdpush_new.g.c.a b = new com.jingdong.jdpush_new.g.c.a();
    private Context a = JdSdk.getInstance().getApplicationContext();

    public static c b() {
        if (f12817c == null) {
            f12817c = new c();
        }
        return f12817c;
    }

    public com.jingdong.jdpush_new.g.c.a a() {
        if (com.jingdong.jdpush_new.g.c.a.j(this.b)) {
            this.b = com.jingdong.jdpush_new.f.a.k(this.a).h(com.jingdong.jdpush_new.j.c.d(this.a));
        }
        return this.b;
    }

    public void c(com.jingdong.jdpush_new.g.c.a aVar) {
        this.b = aVar;
    }

    public void d(Context context) {
        this.a = context;
    }
}
