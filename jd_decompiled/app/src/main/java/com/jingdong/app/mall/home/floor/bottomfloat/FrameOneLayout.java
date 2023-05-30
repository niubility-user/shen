package com.jingdong.app.mall.home.floor.bottomfloat;

import android.content.Context;
import android.os.SystemClock;
import android.text.SpannableString;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.app.mall.home.R;
import com.jingdong.app.mall.home.base.HomeDraweeView;
import com.jingdong.app.mall.home.floor.common.f;
import com.jingdong.app.mall.home.floor.common.i.l;
import com.jingdong.app.mall.home.floor.common.i.s;
import com.jingdong.app.mall.home.floor.ctrl.e;
import com.jingdong.app.mall.home.x.g;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes4.dex */
public class FrameOneLayout extends FrameBaseLayout {
    private com.jingdong.app.mall.home.x.c A;
    private AtomicBoolean B;
    private AtomicBoolean C;
    private AtomicBoolean D;
    private f u;
    private SimpleDraweeView v;
    private f w;
    private View x;
    private f y;
    private com.jingdong.app.mall.home.x.b z;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class a implements View.OnClickListener {
        a() {
            FrameOneLayout.this = r1;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (l.k()) {
                return;
            }
            FrameOneLayout frameOneLayout = FrameOneLayout.this;
            if (frameOneLayout.f9158h == null || frameOneLayout.q.get()) {
                return;
            }
            new com.jingdong.app.mall.home.q.a("\u5e95\u90e8\u6d6e\u5c42\u70b9\u51fb", FrameOneLayout.this.f9158h.o).b();
            FrameOneLayout frameOneLayout2 = FrameOneLayout.this;
            frameOneLayout2.l(frameOneLayout2.f9158h, "1", "1", "m");
            l.e(FrameOneLayout.this.getContext(), FrameOneLayout.this.f9158h.G);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class b implements View.OnClickListener {
        b() {
            FrameOneLayout.this = r1;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            FrameOneLayout frameOneLayout = FrameOneLayout.this;
            if (frameOneLayout.f9158h == null || frameOneLayout.q.get()) {
                return;
            }
            FrameOneLayout frameOneLayout2 = FrameOneLayout.this;
            frameOneLayout2.f9163m.put(frameOneLayout2.f9158h.q, Boolean.TRUE);
            FrameOneLayout.this.f9158h.c();
            new com.jingdong.app.mall.home.q.a("\u5e95\u90e8\u6d6e\u5c42\u5173\u95ed", FrameOneLayout.this.f9158h.p).b();
            FrameOneLayout frameOneLayout3 = FrameOneLayout.this;
            frameOneLayout3.l(frameOneLayout3.f9158h, "1", "0", "m");
            FrameOneLayout frameOneLayout4 = FrameOneLayout.this;
            frameOneLayout4.g(frameOneLayout4.x());
            FrameOneLayout.this.setTranslationX(com.jingdong.app.mall.home.floor.common.d.f9279g << 1);
        }
    }

    /* loaded from: classes4.dex */
    class c implements e.b {
        c() {
            FrameOneLayout.this = r1;
        }

        @Override // com.jingdong.app.mall.home.floor.ctrl.e.b
        public void onFailed(String str, View view) {
            FrameOneLayout.this.g(!r1.x());
        }

        @Override // com.jingdong.app.mall.home.floor.ctrl.e.b
        public void onStart(String str, View view) {
        }

        @Override // com.jingdong.app.mall.home.floor.ctrl.e.b
        public void onSuccess(String str, View view) {
            FrameOneLayout.this.p.set(true);
            FrameOneLayout.this.p();
        }
    }

    /* loaded from: classes4.dex */
    public class d extends com.jingdong.app.mall.home.x.c {
        d() {
            FrameOneLayout.this = r1;
        }

        @Override // com.jingdong.app.mall.home.x.c
        public void a() {
            FrameOneLayout.this.v(true);
        }

        @Override // com.jingdong.app.mall.home.x.c
        public void c(long j2, com.jingdong.app.mall.home.x.e eVar) {
            FrameBaseLayout frameBaseLayout;
            if (eVar == null) {
                FrameOneLayout.this.v(false);
                return;
            }
            try {
                long j3 = eVar.a;
                if (j3 > 3) {
                    long j4 = FrameOneLayout.this.f9158h.f9195j;
                    if (j4 >= 0 && j3 >= j4) {
                        return;
                    }
                }
                FrameOneLayout.this.f9161k.setTime(eVar);
                if (!FrameOneLayout.this.q.get() && eVar.a > 5) {
                    FrameOneLayout.this.C.set(true);
                    FrameOneLayout.this.p();
                }
                if (eVar.a > 3 || (frameBaseLayout = FrameOneLayout.this.f9157g) == null || !frameBaseLayout.a()) {
                    return;
                }
                FrameOneLayout.this.v(true);
            } catch (Exception e2) {
                com.jingdong.app.mall.home.o.a.f.s0(this, e2);
            }
        }
    }

    public FrameOneLayout(Context context) {
        super(context);
        this.B = new AtomicBoolean(true);
        this.C = new AtomicBoolean(false);
        this.D = new AtomicBoolean(false);
        f fVar = new f(-1, 60);
        this.u = fVar;
        RelativeLayout.LayoutParams u = fVar.u(this);
        u.addRule(12);
        setLayoutParams(u);
        HomeDraweeView homeDraweeView = new HomeDraweeView(context);
        this.v = homeDraweeView;
        homeDraweeView.setScaleType(ImageView.ScaleType.FIT_XY);
        f fVar2 = new f(-1, -1);
        this.w = fVar2;
        View view = this.v;
        addView(view, fVar2.u(view));
        this.v.setOnClickListener(new a());
        this.f9161k = new FrameTimeLayout(context);
        f fVar3 = new f(-1, -1);
        this.f9162l = fVar3;
        View view2 = this.f9161k;
        addView(view2, fVar3.u(view2));
        View view3 = new View(context);
        this.x = view3;
        view3.setBackgroundResource(R.drawable.home_bottom_banner_close);
        f fVar4 = new f(36, 36);
        this.y = fVar4;
        fVar4.E(0, 0, 18, 0);
        RelativeLayout.LayoutParams u2 = this.y.u(this.x);
        u2.addRule(11, -1);
        addView(this.x, u2);
        this.x.setOnClickListener(new b());
    }

    private void u() {
        this.u.R(-1, this.f9158h.f9193h + 26);
        this.w.E(0, 26, 0, 0);
        this.f9162l.E(0, 26, 0, 0);
        f.d(this.v, this.w, true);
        f.d(this.f9161k, this.f9162l, true);
        f.c(this, this.u);
        f.c(this.x, this.y);
    }

    public void v(boolean z) {
        if (this.D.getAndSet(true)) {
            return;
        }
        if (!x()) {
            w(z);
        } else {
            g(true);
        }
    }

    public boolean x() {
        com.jingdong.app.mall.home.floor.bottomfloat.b bVar = this.f9158h;
        return !bVar.a || this.f9159i.f9196k - bVar.f9196k < 5;
    }

    private void y() {
        this.f9161k.setText(new SpannableString(this.f9158h.u));
        this.f9161k.setFrameInfo(getContext(), this.f9158h);
    }

    private void z() {
        ViewPropertyAnimator animate = animate();
        animate.cancel();
        FrameBaseLayout frameBaseLayout = this.f9157g;
        if (frameBaseLayout != null && !frameBaseLayout.a()) {
            animate.setDuration(500L);
        }
        animate.translationY(this.u.h());
        animate.start();
    }

    public void A() {
        com.jingdong.app.mall.home.x.b bVar = this.z;
        if (bVar != null) {
            bVar.g(this.A);
        }
        if (this.q.get() && x()) {
            g(true);
            return;
        }
        this.f9161k.initDefSize();
        com.jingdong.app.mall.home.floor.bottomfloat.b bVar2 = this.f9158h;
        long j2 = bVar2.f9196k;
        long j3 = bVar2.f9197l;
        long elapsedRealtime = (j2 * 1000) - (SystemClock.elapsedRealtime() - s.f9357c);
        if (this.B.getAndSet(false)) {
            elapsedRealtime = Math.max(2L, elapsedRealtime);
        }
        if (elapsedRealtime <= 0) {
            v(false);
            return;
        }
        this.z = g.b().f(j3, elapsedRealtime);
        if (this.A == null) {
            this.A = new d();
        }
        com.jingdong.app.mall.home.x.b bVar3 = this.z;
        if (bVar3 != null) {
            bVar3.a(this.A);
        }
    }

    @Override // com.jingdong.app.mall.home.floor.bottomfloat.FrameBaseLayout
    public void d() {
        super.d();
        setTranslationY(0.0f);
        setTranslationX(com.jingdong.app.mall.home.floor.common.d.f9279g << 1);
        this.C.set(false);
        this.D.set(false);
        this.x.setVisibility(8);
        boolean a2 = this.f9158h.a();
        this.q.set(e(this.f9158h.q));
        if (a2 && !this.q.get() && !TextUtils.isEmpty(this.f9158h.r)) {
            y();
            A();
            u();
            setVisibility(0);
            e.p(this.v, this.f9158h.r, e.b, new c());
            return;
        }
        g(true);
    }

    @Override // com.jingdong.app.mall.home.floor.bottomfloat.FrameBaseLayout
    public boolean f() {
        return this.p.get() && this.C.get();
    }

    @Override // com.jingdong.app.mall.home.floor.bottomfloat.FrameBaseLayout
    public void i() {
        super.i();
        if (this.f9158h == null) {
            return;
        }
        if ((this.f9158h.f9196k * 1000) - (SystemClock.elapsedRealtime() - s.f9357c) <= 0) {
            this.q.set(true);
            setVisibility(4);
        }
    }

    @Override // com.jingdong.app.mall.home.floor.bottomfloat.FrameBaseLayout
    public void n() {
        super.n();
        if (this.p.get() && this.C.get()) {
            com.jingdong.app.mall.home.floor.bottomfloat.b bVar = this.f9158h;
            m(bVar, "1", "0", bVar.f9189c ? "1" : "0");
        }
    }

    @Override // com.jingdong.app.mall.home.floor.bottomfloat.FrameBaseLayout
    public void p() {
        if (this.q.get() || !f() || this.r.get() || getTranslationX() == 0.0f || this.s.getAndSet(true)) {
            return;
        }
        this.f9161k.showTime(this.f9158h.f9189c);
        setTranslationX(0.0f);
        b();
        if (this.o.getAndSet(false)) {
            new com.jingdong.app.mall.home.q.a("\u5e95\u90e8\u6d6e\u5c42\u66dd\u5149", true, this.f9158h.f9199n).b();
        }
        this.x.setVisibility(0);
    }

    @Override // com.jingdong.app.mall.home.floor.bottomfloat.FrameBaseLayout
    public boolean q() {
        return super.q() || getTranslationX() != 0.0f;
    }

    public void w(boolean z) {
        if (z) {
            z();
        } else {
            setVisibility(4);
        }
    }
}
