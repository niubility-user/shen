package com.jingdong.app.mall.home.r.d;

import android.os.SystemClock;
import android.widget.RelativeLayout;
import com.jingdong.app.mall.home.floor.bottomfloat.BaseFloatPriority;
import com.jingdong.app.mall.home.o.a.f;
import com.jingdong.app.mall.home.widget.HomePullRefreshRecyclerView;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes4.dex */
public abstract class a {
    protected int a;

    /* renamed from: f */
    protected HomePullRefreshRecyclerView f10661f;

    /* renamed from: g */
    protected RelativeLayout f10662g;

    /* renamed from: h */
    protected BaseFloatPriority f10663h;

    /* renamed from: i */
    protected volatile com.jingdong.app.mall.home.r.d.b f10664i;
    protected final AtomicBoolean b = new AtomicBoolean(false);

    /* renamed from: c */
    protected AtomicBoolean f10659c = new AtomicBoolean(true);
    protected AtomicBoolean d = new AtomicBoolean(false);

    /* renamed from: e */
    protected AtomicInteger f10660e = new AtomicInteger();

    /* renamed from: j */
    private com.jingdong.app.mall.home.o.a.b f10665j = new C0320a();

    /* renamed from: k */
    private com.jingdong.app.mall.home.o.a.b f10666k = new b();

    /* renamed from: com.jingdong.app.mall.home.r.d.a$a */
    /* loaded from: classes4.dex */
    public class C0320a extends com.jingdong.app.mall.home.o.a.b {
        C0320a() {
            a.this = r1;
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        protected void safeRun() {
            a.this.e();
        }
    }

    /* loaded from: classes4.dex */
    public class b extends com.jingdong.app.mall.home.o.a.b {
        b() {
            a.this = r1;
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        protected void safeRun() {
            a.this.g();
        }
    }

    public void a() {
        b(0L);
        f.K0(this.b);
    }

    public void b(long j2) {
    }

    public void c(boolean z) {
    }

    public void d(boolean z) {
        HomePullRefreshRecyclerView homePullRefreshRecyclerView = this.f10661f;
        if (homePullRefreshRecyclerView == null) {
            return;
        }
        if (z) {
            homePullRefreshRecyclerView.T();
        }
        this.f10661f.s0(!z);
    }

    public void e() {
    }

    public final boolean f() {
        int elapsedRealtime = (int) ((this.f10664i.f10669c - (SystemClock.elapsedRealtime() - this.f10664i.d)) / 1000);
        if (elapsedRealtime <= 0) {
            f.E0(this.f10665j);
            return true;
        } else if (this.f10660e.get() != elapsedRealtime) {
            this.f10660e.set(elapsedRealtime);
            f.E0(this.f10666k);
            return false;
        } else {
            return false;
        }
    }

    public void g() {
    }
}
