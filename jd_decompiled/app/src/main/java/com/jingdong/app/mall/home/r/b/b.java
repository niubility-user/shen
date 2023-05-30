package com.jingdong.app.mall.home.r.b;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.jingdong.app.mall.home.JDHomeFragment;
import com.jingdong.app.mall.home.floor.bottomfloat.BaseFloatPriority;
import com.jingdong.app.mall.home.o.a.f;
import com.jingdong.common.XView2.XView2Manager;
import com.jingdong.common.XView2.common.IXView2LayerObserver;
import com.jingdong.common.XView2.common.XView2LayerObservableManager;

/* loaded from: classes4.dex */
public class b {

    /* renamed from: i */
    private static final String f10621i = "b";

    /* renamed from: j */
    private static final Handler f10622j = new Handler(Looper.getMainLooper());
    private final com.jingdong.app.mall.home.r.b.c a;
    private BaseFloatPriority b;

    /* renamed from: c */
    private int f10623c;
    private boolean d;

    /* renamed from: e */
    private boolean f10624e;

    /* renamed from: f */
    com.jingdong.app.mall.home.r.b.a f10625f;

    /* renamed from: g */
    b f10626g;

    /* renamed from: h */
    IXView2LayerObserver f10627h = new a();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class a extends IXView2LayerObserver {
        private boolean a = false;

        /* renamed from: com.jingdong.app.mall.home.r.b.b$a$a */
        /* loaded from: classes4.dex */
        class C0317a extends com.jingdong.app.mall.home.o.a.b {
            C0317a() {
                a.this = r1;
            }

            @Override // com.jingdong.app.mall.home.o.a.b
            protected void safeRun() {
                b.this.f(true);
            }
        }

        a() {
            b.this = r1;
        }

        @Override // com.jingdong.common.XView2.common.IXView2LayerObserver
        public void clickClose() {
            if (this.a) {
                return;
            }
            this.a = true;
            b.this.j();
        }

        @Override // com.jingdong.common.XView2.common.IXView2LayerObserver
        public void jumpClose() {
        }

        @Override // com.jingdong.common.XView2.common.IXView2LayerObserver
        public void layerAnimateEnd() {
        }

        @Override // com.jingdong.common.XView2.common.IXView2LayerObserver
        public void layerRelease() {
            b.this.f(false);
        }

        @Override // com.jingdong.common.XView2.common.IXView2LayerObserver
        public void layerShowError() {
            b.this.f(true);
        }

        @Override // com.jingdong.common.XView2.common.IXView2LayerObserver
        public void layerShowSuccess() {
            b.this.f10625f.l();
            long g2 = b.this.f10625f.g();
            if (g2 > 0) {
                b.f10622j.postDelayed(new C0317a(), g2);
            }
        }

        @Override // com.jingdong.common.XView2.common.IXView2LayerObserver
        public void onClick(String str, int i2, String str2) {
            if (i2 == 1) {
                clickClose();
                return;
            }
            com.jingdong.app.mall.home.r.c.b c2 = com.jingdong.app.mall.home.r.c.b.c(str2);
            boolean z = !TextUtils.isEmpty(c2.optString("url"));
            if (i2 != 3) {
            }
            if (z) {
                b.this.f10625f.k(c2);
                if (b.this.f10625f.b()) {
                    b.this.f(true);
                }
            }
        }
    }

    /* renamed from: com.jingdong.app.mall.home.r.b.b$b */
    /* loaded from: classes4.dex */
    public class C0318b extends com.jingdong.app.mall.home.o.a.b {
        C0318b() {
            b.this = r1;
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        protected void safeRun() {
            b.this.t();
        }
    }

    /* loaded from: classes4.dex */
    public class c extends BaseFloatPriority {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        c(String str, int i2) {
            super(str, i2);
            b.this = r1;
        }

        @Override // com.jingdong.app.mall.home.floor.bottomfloat.BaseFloatPriority
        protected void g(int i2) {
            b.this.o();
        }

        @Override // com.jingdong.app.mall.home.floor.bottomfloat.BaseFloatPriority
        public void h() {
            b.this.p();
        }
    }

    public b(com.jingdong.app.mall.home.r.b.a aVar, com.jingdong.app.mall.home.r.b.c cVar) {
        this.f10625f = aVar;
        this.a = cVar;
    }

    public void f(boolean z) {
        if (this.d) {
            return;
        }
        this.f10624e = true;
        if (z) {
            f.r0(f10621i, "\u6267\u884cApi\uff1a autoClose closeLayer");
            this.f10625f.c();
        }
        if (i()) {
            g();
            return;
        }
        BaseFloatPriority baseFloatPriority = this.b;
        if (baseFloatPriority != null) {
            baseFloatPriority.b(true);
        }
    }

    private boolean i() {
        return (this.f10626g == null || !this.f10624e || this.d) ? false : true;
    }

    public void j() {
        this.f10624e = true;
        this.f10625f.j();
        if (i()) {
            g();
            return;
        }
        BaseFloatPriority baseFloatPriority = this.b;
        if (baseFloatPriority != null) {
            baseFloatPriority.b(true);
        }
    }

    public void o() {
        this.f10625f.n(false);
    }

    public void t() {
        if (!XView2Manager.getInstance().getXViewCanPopStatus(this.f10625f.d())) {
            f.r0(f10621i, "\u672a\u51c6\u5907\u597d\u8d44\u6e90\uff1a " + this.f10625f.d());
            int i2 = this.f10623c;
            if (i2 > 5) {
                f(false);
                return;
            }
            this.f10623c = i2 + 1;
            g();
        } else if (this.b != null) {
        } else {
            int i3 = this.f10625f.a;
            if (i3 == 19) {
                com.jingdong.app.mall.home.floor.bottomfloat.a.d().c(20);
            } else if (i3 == 20) {
                com.jingdong.app.mall.home.floor.bottomfloat.a.d().c(19);
            }
            f.r0(f10621i, "\u5f00\u59cb\u52a0\u8f7d\u52a0\u8f7d\uff1a " + this.f10625f.d());
            XView2LayerObservableManager.getManager().registerXView2Observer(this.f10627h, this.f10625f.f());
            c cVar = new c("\u5e95\u90e8XView".concat(this.f10625f.e()), this.f10625f.a);
            this.b = cVar;
            cVar.j();
            this.b.l();
        }
    }

    public void g() {
        Handler handler = f10622j;
        handler.removeCallbacks(null);
        if (i()) {
            this.f10626g.g();
        } else if (JDHomeFragment.O0()) {
            if (!this.f10625f.a()) {
                f(true);
            } else if (!this.f10625f.h() || this.a.f10637g.get()) {
                long q = this.f10623c > 0 ? 1000L : this.f10625f.q();
                if (q > 0) {
                    f.r0(f10621i, "\u5ef6\u65f6\u7b49\u5f85\u52a0\u8f7d\uff1a " + q);
                    handler.postDelayed(new C0318b(), q);
                    return;
                }
                t();
            }
        }
    }

    public boolean h() {
        return this.f10625f.a() && this.f10625f.q() > 0;
    }

    public void k() {
        if (i()) {
            this.f10626g.k();
        } else if (this.f10625f.m()) {
            f(true);
        } else {
            o();
        }
    }

    public void l(boolean z) {
        if (i()) {
            this.f10626g.l(z);
        } else if (!this.f10625f.a()) {
            f(true);
        } else if (z) {
        } else {
            if (!this.f10625f.i()) {
                g();
            } else {
                p();
            }
        }
    }

    public void m() {
        if (i()) {
            this.f10626g.m();
        }
    }

    public void n() {
        if (i()) {
            this.f10626g.n();
        }
    }

    public void p() {
        if (i()) {
            this.f10626g.p();
        } else if (JDHomeFragment.O0()) {
            if (this.b == null) {
                g();
            } else if (!this.f10625f.a()) {
                f(true);
            } else {
                boolean z = !this.a.g() && (this.b.a() && JDHomeFragment.O0());
                if (z && !this.f10625f.i()) {
                    f.r0(f10621i, "\u6267\u884cApi\uff1a showLayer");
                    this.f10625f.p();
                    return;
                }
                f.r0(f10621i, "\u6267\u884cApi\uff1a setLayerVisible" + z);
                this.f10625f.n(z);
            }
        }
    }

    public void q() {
        if (i()) {
            this.f10626g.q();
        }
        this.f10624e = true;
        this.d = true;
        this.f10625f.c();
    }

    public void r() {
        if (i()) {
            this.f10626g.r();
        } else if (this.d || !this.f10625f.h()) {
        } else {
            g();
        }
    }

    public void s(b bVar) {
        this.f10626g = bVar;
    }
}
