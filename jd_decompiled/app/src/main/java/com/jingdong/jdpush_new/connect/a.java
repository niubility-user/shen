package com.jingdong.jdpush_new.connect;

import android.content.Context;
import com.jingdong.jdpush_new.j.o;
import java.sql.Time;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/* loaded from: classes12.dex */
public class a implements com.jingdong.jdpush_new.j.d<com.jingdong.jdpush_new.g.b> {
    private f a;

    /* renamed from: c */
    private ScheduledFuture<?> f12783c;
    private ScheduledFuture<?> d;

    /* renamed from: e */
    private ScheduledFuture<?> f12784e;

    /* renamed from: h */
    private int f12787h = 0;
    private long b = System.currentTimeMillis();

    /* renamed from: f */
    private int f12785f = d.c().b();

    /* renamed from: g */
    private int f12786g = d.c().a();

    /* renamed from: com.jingdong.jdpush_new.connect.a$a */
    /* loaded from: classes12.dex */
    public class RunnableC0497a implements Runnable {
        RunnableC0497a() {
            a.this = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            com.jingdong.jdpush_new.j.g.c("heart timeout");
            a.this.a.f();
        }
    }

    /* loaded from: classes12.dex */
    public class b implements Runnable {
        b() {
            a.this = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            a.this.k();
        }
    }

    /* loaded from: classes12.dex */
    public class c implements Runnable {
        c() {
            a.this = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            com.jingdong.jdpush_new.j.g.c("heartIOCountDiff=" + a.this.f12787h);
            if (a.this.f12787h != 1) {
                if (a.this.f12787h >= 2) {
                    a.this.a.f();
                    return;
                }
                return;
            }
            a.this.k();
        }
    }

    public a(Context context, f fVar) {
        this.a = fVar;
        g();
    }

    private void d() {
        ScheduledFuture<?> scheduledFuture = this.f12784e;
        if (scheduledFuture != null) {
            scheduledFuture.cancel(false);
            this.f12784e = null;
        }
    }

    private void e() {
        ScheduledFuture<?> scheduledFuture = this.d;
        if (scheduledFuture != null) {
            scheduledFuture.cancel(false);
        }
    }

    private void f() {
        this.f12784e = o.b().c(new c(), this.f12786g, TimeUnit.SECONDS);
    }

    private void g() {
        this.d = o.b().c(new RunnableC0497a(), 150000L, TimeUnit.MILLISECONDS);
    }

    @Override // com.jingdong.jdpush_new.j.d
    /* renamed from: a */
    public void accept(com.jingdong.jdpush_new.g.b bVar) {
        com.jingdong.jdpush_new.j.g.a("\u6536\u5230\u5fc3\u8df3\u56de\u6267");
        this.b = System.currentTimeMillis();
        this.f12787h--;
        e();
        g();
        d();
    }

    public void h() {
        ScheduledFuture<?> scheduledFuture = this.f12783c;
        if (scheduledFuture != null) {
            scheduledFuture.cancel(false);
            this.f12783c = null;
            com.jingdong.jdpush_new.j.g.a("cancel next heart");
        }
        e();
    }

    public void i() {
        j();
        com.jingdong.jdpush_new.j.g.a("schedule next send heart time");
    }

    public void j() {
        this.f12783c = o.b().c(new b(), this.f12785f, TimeUnit.SECONDS);
    }

    public void k() {
        if (System.currentTimeMillis() - this.b > 150000) {
            com.jingdong.jdpush_new.j.g.c("heart timeout, last receive time is " + new Time(this.b));
            this.a.f();
        } else if (this.a.e()) {
            com.jingdong.jdpush_new.j.g.c("long conn is closed, give up send heart");
        } else {
            this.f12787h++;
            this.a.g(new com.jingdong.jdpush_new.g.b((short) 0, null));
            i();
            f();
        }
    }
}
