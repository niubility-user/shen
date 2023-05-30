package com.jingdong.app.mall.home.floor.bottomfloat;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.widget.RelativeLayout;
import com.jingdong.app.mall.home.floor.common.f;
import com.jingdong.app.mall.home.floor.common.i.m;
import com.jingdong.app.mall.home.o.a.k;
import com.jingdong.app.mall.home.r.e.h;
import com.jingdong.cleanmvp.common.BaseEvent;
import com.jingdong.common.login.LoginEvent;
import com.jingdong.common.login.LoginUserBase;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes4.dex */
public class BottomFloatLayout extends RelativeLayout {
    public static final AtomicBoolean t = new AtomicBoolean(false);
    private static Handler u = new Handler(Looper.getMainLooper());
    private static boolean v;

    /* renamed from: g  reason: collision with root package name */
    private Context f9144g;

    /* renamed from: h  reason: collision with root package name */
    private AtomicBoolean f9145h;

    /* renamed from: i  reason: collision with root package name */
    private AtomicBoolean f9146i;

    /* renamed from: j  reason: collision with root package name */
    private AtomicBoolean f9147j;

    /* renamed from: k  reason: collision with root package name */
    private AtomicBoolean f9148k;

    /* renamed from: l  reason: collision with root package name */
    private AtomicBoolean f9149l;

    /* renamed from: m  reason: collision with root package name */
    private AtomicBoolean f9150m;

    /* renamed from: n  reason: collision with root package name */
    private BaseFloatPriority f9151n;
    private FrameBaseLayout o;
    private FrameBaseLayout p;
    private FrameBaseLayout q;
    private com.jingdong.app.mall.home.floor.bottomfloat.b r;
    private boolean s;

    /* loaded from: classes4.dex */
    class a extends FrameOneLayout {
        a(Context context) {
            super(context);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.jingdong.app.mall.home.floor.bottomfloat.FrameBaseLayout
        public void g(boolean z) {
            super.g(z);
            BottomFloatLayout.this.f9149l.set(true);
            BottomFloatLayout.this.u(z, false);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.jingdong.app.mall.home.floor.bottomfloat.FrameOneLayout
        public void w(boolean z) {
            super.w(z);
            if (BottomFloatLayout.this.f9151n != null) {
                BottomFloatLayout.this.f9151n.j();
            }
            BottomFloatLayout.this.f9149l.set(false);
            BottomFloatLayout.this.f9148k.set(true);
            if (BottomFloatLayout.this.f9147j.get() || BottomFloatLayout.this.f9146i.get()) {
                return;
            }
            BottomFloatLayout.this.B();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class b extends BaseFloatPriority {
        b(String str, int i2, boolean z) {
            super(str, i2, z);
        }

        @Override // com.jingdong.app.mall.home.floor.bottomfloat.BaseFloatPriority
        protected void g(int i2) {
            BottomFloatLayout.this.u(16 == i2, true);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.jingdong.app.mall.home.floor.bottomfloat.BaseFloatPriority
        public void h() {
            BottomFloatLayout bottomFloatLayout = BottomFloatLayout.this;
            bottomFloatLayout.y(bottomFloatLayout.f9147j.get());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class c extends com.jingdong.app.mall.home.o.a.b {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ h f9153g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ com.jingdong.app.mall.home.floor.bottomfloat.b f9154h;

        /* renamed from: i  reason: collision with root package name */
        final /* synthetic */ com.jingdong.app.mall.home.floor.bottomfloat.b f9155i;

        c(h hVar, com.jingdong.app.mall.home.floor.bottomfloat.b bVar, com.jingdong.app.mall.home.floor.bottomfloat.b bVar2) {
            this.f9153g = hVar;
            this.f9154h = bVar;
            this.f9155i = bVar2;
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        protected void safeRun() {
            if (BottomFloatLayout.this.f9150m.get()) {
                return;
            }
            BottomFloatLayout.this.f9150m.set(true);
            BottomFloatLayout.this.p(this.f9153g, this.f9154h, this.f9155i);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class d extends FrameThreeLayout {
        d(Context context) {
            super(context);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.jingdong.app.mall.home.floor.bottomfloat.FrameBaseLayout
        public void g(boolean z) {
            super.g(z);
            BottomFloatLayout.this.u(z, true);
        }
    }

    public BottomFloatLayout(Context context) {
        super(context);
        this.f9145h = new AtomicBoolean(false);
        this.f9146i = new AtomicBoolean(false);
        this.f9147j = new AtomicBoolean(false);
        this.f9148k = new AtomicBoolean(false);
        this.f9149l = new AtomicBoolean(false);
        this.f9150m = new AtomicBoolean(false);
        this.f9144g = context;
        setClipChildren(false);
        RelativeLayout.LayoutParams u2 = new f(-1, -2).u(this);
        u2.addRule(12);
        setLayoutParams(u2);
        a aVar = new a(context);
        this.o = aVar;
        addView(aVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void B() {
        if (this.p == null || !m()) {
            return;
        }
        this.f9148k.set(false);
        this.p.p();
    }

    private void C(boolean z, FrameBaseLayout... frameBaseLayoutArr) {
        for (FrameBaseLayout frameBaseLayout : frameBaseLayoutArr) {
            if (frameBaseLayout != null) {
                frameBaseLayout.k(z);
            }
        }
    }

    private boolean l(com.jingdong.app.mall.home.floor.bottomfloat.b bVar, com.jingdong.app.mall.home.floor.bottomfloat.b bVar2) {
        if (this.p == null || this.q == null) {
            return true;
        }
        if (bVar == null) {
            bVar = new com.jingdong.app.mall.home.floor.bottomfloat.b(false);
        }
        if (bVar2 == null) {
            bVar2 = new com.jingdong.app.mall.home.floor.bottomfloat.b(false);
        }
        return (this.o.e(bVar.q) || this.q.e(bVar2.q)) ? false : true;
    }

    private boolean m() {
        if (this.f9151n.a() && this.f9150m.get()) {
            k(0);
            this.f9151n.l();
            return true;
        }
        k(4);
        this.f9151n.j();
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void p(h hVar, com.jingdong.app.mall.home.floor.bottomfloat.b bVar, com.jingdong.app.mall.home.floor.bottomfloat.b bVar2) {
        if (bVar2 != null) {
            q(hVar, bVar, bVar2);
            r(hVar, bVar, bVar2);
        } else {
            com.jingdong.app.mall.home.n.h.c.l(true, this.p, this.q);
        }
        this.o.c(this, hVar, bVar, bVar2);
        this.o.o(this.p);
        m();
    }

    private void q(h hVar, com.jingdong.app.mall.home.floor.bottomfloat.b bVar, com.jingdong.app.mall.home.floor.bottomfloat.b bVar2) {
        if (this.q == null) {
            d dVar = new d(this.f9144g);
            this.q = dVar;
            addView(dVar);
        }
        this.q.c(this, hVar, bVar, bVar2);
    }

    private void r(h hVar, com.jingdong.app.mall.home.floor.bottomfloat.b bVar, com.jingdong.app.mall.home.floor.bottomfloat.b bVar2) {
        if (this.p == null) {
            FrameTwoLayout frameTwoLayout = new FrameTwoLayout(this.f9144g);
            this.p = frameTwoLayout;
            m.b(this, frameTwoLayout, 0);
        }
        this.p.o(this.q);
        this.p.c(this, hVar, bVar, bVar2);
    }

    private boolean s() {
        boolean isEmpty = TextUtils.isEmpty(LoginUserBase.getUserPin());
        return (isEmpty && !this.s) || (!isEmpty && this.s);
    }

    public static void t() {
        v = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void u(boolean z, boolean z2) {
        if (z || z2) {
            k(4);
        }
        if (z) {
            v();
        }
    }

    private void v() {
        t.set(false);
        m.K(this);
        com.jingdong.app.mall.home.o.a.f.H0(this);
        BaseFloatPriority baseFloatPriority = this.f9151n;
        if (baseFloatPriority != null) {
            baseFloatPriority.b(true);
        }
        FrameBaseLayout frameBaseLayout = this.o;
        if (frameBaseLayout != null) {
            frameBaseLayout.j();
        }
        FrameBaseLayout frameBaseLayout2 = this.p;
        if (frameBaseLayout2 != null) {
            frameBaseLayout2.j();
        }
        FrameBaseLayout frameBaseLayout3 = this.q;
        if (frameBaseLayout3 != null) {
            frameBaseLayout3.j();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void y(boolean z) {
        this.f9147j.set(z);
        if (!this.f9146i.get() && this.f9145h.get() && !z) {
            if (this.f9151n.a()) {
                boolean m2 = m();
                if (this.f9148k.get() && m2) {
                    B();
                    return;
                }
                return;
            }
            return;
        }
        k(4);
    }

    public void A() {
        this.f9145h.set(false);
    }

    public void k(int i2) {
        if (getVisibility() != i2) {
            setVisibility(i2);
            C(i2 == 0, this.o, this.q);
        }
    }

    public void n() {
        t.set(this.f9145h.get());
        if (this.f9145h.get()) {
            return;
        }
        v();
    }

    public boolean o(h hVar, com.jingdong.app.mall.home.r.e.d dVar) {
        int size;
        k(4);
        com.jingdong.app.mall.home.v.c.a.a(this);
        com.jingdong.app.mall.home.floor.bottomfloat.b bVar = null;
        u.removeCallbacksAndMessages(null);
        this.s = com.jingdong.app.mall.home.floor.bottomfloat.b.d(dVar);
        boolean z = false;
        if (!s() || k.w()) {
            com.jingdong.app.mall.home.o.a.f.G0(this);
            this.f9151n = new b("\u5e95\u90e8\u6d6e\u5c42", 17, false);
            this.f9149l.set(false);
            this.f9148k.set(false);
            ArrayList<com.jingdong.app.mall.home.r.e.f> arrayList = dVar.f10682c;
            if (arrayList != null && (size = arrayList.size()) > 0) {
                com.jingdong.app.mall.home.floor.bottomfloat.b bVar2 = new com.jingdong.app.mall.home.floor.bottomfloat.b(dVar, arrayList.get(0));
                this.r = bVar2;
                boolean z2 = bVar2.a;
                boolean a2 = bVar2.a();
                if (z2 && size > 1) {
                    bVar = new com.jingdong.app.mall.home.floor.bottomfloat.b(dVar, arrayList.get(1));
                    a2 = bVar.a() && bVar.e(this.q);
                }
                if (!a2 || (z2 && bVar == null)) {
                    return false;
                }
                this.f9145h.set(true);
                if (!this.f9150m.get() && v) {
                    z = true;
                }
                boolean l2 = l(this.r, bVar);
                if (!z) {
                    this.f9150m.set(true);
                    p(hVar, this.r, bVar);
                } else {
                    u.postDelayed(new c(hVar, this.r, bVar), 2400L);
                }
                return l2;
            }
            return false;
        }
        return false;
    }

    public void onEventMainThread(BaseEvent baseEvent) {
        if ((baseEvent instanceof LoginEvent) && s()) {
            u(true, true);
        }
    }

    public void w() {
        this.f9146i.set(true);
        if (this.f9145h.get()) {
            FrameBaseLayout frameBaseLayout = this.o;
            if (frameBaseLayout != null) {
                frameBaseLayout.h();
            }
            FrameBaseLayout frameBaseLayout2 = this.q;
            if (frameBaseLayout2 != null) {
                frameBaseLayout2.h();
            }
            y(this.f9147j.get());
        }
    }

    public void x() {
        this.f9146i.set(false);
        if (this.f9145h.get()) {
            FrameBaseLayout frameBaseLayout = this.o;
            if (frameBaseLayout != null) {
                frameBaseLayout.i();
            }
            FrameBaseLayout frameBaseLayout2 = this.q;
            if (frameBaseLayout2 != null) {
                frameBaseLayout2.i();
            }
            y(this.f9147j.get());
        }
    }

    public void z(boolean z, boolean z2) {
        y(z || z2);
    }
}
