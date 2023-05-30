package com.jingdong.app.mall.home.floor.bottomfloat;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.app.mall.home.R;
import com.jingdong.app.mall.home.base.HomeDraweeView;
import com.jingdong.app.mall.home.floor.common.f;
import com.jingdong.app.mall.home.floor.common.g;
import com.jingdong.app.mall.home.floor.common.i.l;
import com.jingdong.app.mall.home.floor.common.i.s;
import com.jingdong.app.mall.home.floor.ctrl.e;
import com.jingdong.app.mall.home.floor.ctrl.t.i;
import com.jingdong.common.utils.LangUtils;
import com.jingdong.jdsdk.utils.FontsUtil;
import com.jingdong.sdk.jdtoast.ToastUtils;

/* loaded from: classes4.dex */
public class FrameThreeLayout extends FrameBaseLayout {
    protected static int I = 26;
    protected static int J = 44;
    protected static int K = 64;
    protected static int L = 56;
    private static final Handler M = new Handler(Looper.getMainLooper());
    private f A;
    private TextView B;
    private f C;
    private StrokeImage D;
    private f E;
    private String F;
    private com.jingdong.app.mall.home.x.b G;
    private com.jingdong.app.mall.home.x.c H;
    private f u;
    private SimpleDraweeView v;
    private f w;
    private View x;
    private f y;
    private ImageTextView z;

    /* loaded from: classes4.dex */
    public class a implements View.OnClickListener {
        a() {
            FrameThreeLayout.this = r1;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            com.jingdong.app.mall.home.floor.bottomfloat.b bVar = FrameThreeLayout.this.f9159i;
            if (bVar == null) {
                return;
            }
            bVar.c();
            FrameThreeLayout frameThreeLayout = FrameThreeLayout.this;
            frameThreeLayout.f9163m.put(frameThreeLayout.f9159i.q, Boolean.TRUE);
            boolean z = !TextUtils.isEmpty(FrameThreeLayout.this.f9159i.t);
            FrameThreeLayout frameThreeLayout2 = FrameThreeLayout.this;
            frameThreeLayout2.l(frameThreeLayout2.f9159i, "2", "0", z ? "xview" : "m");
            new com.jingdong.app.mall.home.q.a("\u5e95\u90e8\u7ea2\u5305\u96e8\u5173\u95ed", FrameThreeLayout.this.f9159i.p).b();
            FrameThreeLayout.this.g(true);
        }
    }

    /* loaded from: classes4.dex */
    public class b implements View.OnClickListener {
        b() {
            FrameThreeLayout.this = r1;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            com.jingdong.app.mall.home.floor.bottomfloat.b bVar;
            boolean z;
            if (l.k() || (bVar = FrameThreeLayout.this.f9159i) == null) {
                return;
            }
            String str = bVar.t;
            boolean z2 = !TextUtils.isEmpty(str);
            FrameThreeLayout frameThreeLayout = FrameThreeLayout.this;
            frameThreeLayout.l(frameThreeLayout.f9159i, "2", "1", z2 ? "xview" : "m");
            if (z2) {
                z = i.p().i(str, null);
            } else {
                FrameThreeLayout frameThreeLayout2 = FrameThreeLayout.this;
                if (frameThreeLayout2.f9159i.G != null) {
                    l.e(frameThreeLayout2.getContext(), FrameThreeLayout.this.f9159i.G);
                }
                z = true;
            }
            if (!z) {
                ToastUtils.showToast(FrameThreeLayout.this.getContext(), FrameThreeLayout.this.f9159i.A);
                return;
            }
            new com.jingdong.app.mall.home.q.a("\u5e95\u90e8\u7ea2\u5305\u96e8\u70b9\u51fb", FrameThreeLayout.this.f9159i.o).b();
            FrameThreeLayout frameThreeLayout3 = FrameThreeLayout.this;
            com.jingdong.app.mall.home.floor.bottomfloat.b bVar2 = frameThreeLayout3.f9159i;
            if (bVar2.f9192g) {
                frameThreeLayout3.g(true);
            } else if (bVar2.f9191f) {
                frameThreeLayout3.f9163m.put(bVar2.q, Boolean.TRUE);
                FrameThreeLayout.this.g(true);
            }
        }
    }

    /* loaded from: classes4.dex */
    class c implements e.b {
        c() {
            FrameThreeLayout.this = r1;
        }

        @Override // com.jingdong.app.mall.home.floor.ctrl.e.b
        public void onFailed(String str, View view) {
            FrameThreeLayout.this.p.set(false);
            FrameThreeLayout.this.g(true);
        }

        @Override // com.jingdong.app.mall.home.floor.ctrl.e.b
        public void onStart(String str, View view) {
        }

        @Override // com.jingdong.app.mall.home.floor.ctrl.e.b
        public void onSuccess(String str, View view) {
            FrameThreeLayout.this.p.set(true);
        }
    }

    /* loaded from: classes4.dex */
    class d extends com.jingdong.app.mall.home.o.a.b {
        d() {
            FrameThreeLayout.this = r1;
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        protected void safeRun() {
            FrameThreeLayout.this.g(true);
        }
    }

    /* loaded from: classes4.dex */
    public class e extends com.jingdong.app.mall.home.x.c {
        e() {
            FrameThreeLayout.this = r1;
        }

        @Override // com.jingdong.app.mall.home.x.c
        public void a() {
        }

        @Override // com.jingdong.app.mall.home.x.c
        protected void c(long j2, com.jingdong.app.mall.home.x.e eVar) {
            if (eVar == null) {
                return;
            }
            try {
                FrameThreeLayout.this.f9161k.setTime(eVar);
            } catch (Exception e2) {
                com.jingdong.app.mall.home.o.a.f.s0(this, e2);
            }
        }
    }

    public FrameThreeLayout(Context context) {
        super(context);
        f fVar = new f(-1, 140);
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
        this.f9161k = new FrameTimeLayout(context);
        f fVar3 = new f(-1, -1);
        this.f9162l = fVar3;
        RelativeLayout.LayoutParams u2 = fVar3.u(this.f9161k);
        u2.addRule(12);
        addView(this.f9161k, u2);
        View view2 = new View(context);
        this.x = view2;
        view2.setBackgroundResource(R.drawable.home_bottom_banner_close);
        f fVar4 = new f(36, 36);
        this.y = fVar4;
        fVar4.E(0, 0, 18, 0);
        RelativeLayout.LayoutParams u3 = this.y.u(this.x);
        u3.addRule(11, -1);
        addView(this.x, u3);
        this.x.setOnClickListener(new a());
        this.v.setOnClickListener(new b());
        this.z = new ImageTextView(context);
        f fVar5 = new f(106, J);
        this.A = fVar5;
        RelativeLayout.LayoutParams u4 = fVar5.u(this.z);
        u4.addRule(11);
        addView(this.z, u4);
        g gVar = new g(context, false);
        gVar.c(true);
        gVar.d(16);
        gVar.h();
        this.B = gVar.a();
        f fVar6 = new f(-2, -2);
        this.C = fVar6;
        addView(this.B, fVar6.u(this.B));
        StrokeImage strokeImage = new StrokeImage(context, com.jingdong.app.mall.home.floor.common.d.d(2), -1644826);
        this.D = strokeImage;
        strokeImage.b(com.jingdong.app.mall.home.floor.common.d.d(8));
        int i2 = K;
        this.E = new f(i2, i2);
        this.D.setBackgroundColor(-1);
        this.D.setScaleType(ImageView.ScaleType.FIT_XY);
        View view3 = this.D;
        addView(view3, this.E.u(view3));
    }

    private void A() {
        this.f9161k.initFrameThree(this.f9159i);
        this.f9161k.setText(t());
        this.f9161k.setTextSize(u());
        this.f9161k.setDayTextSize(s());
        this.f9161k.setTimeSize(v());
        this.f9161k.setType(w());
        this.f9161k.setTypeface(this.f9159i.C == 3 ? FontsUtil.getTypeFace(getContext()) : Typeface.DEFAULT);
        this.f9161k.setFrameInfo(getContext(), this.f9159i);
    }

    private int s() {
        int d2 = com.jingdong.app.mall.home.floor.common.d.d(24);
        com.jingdong.app.mall.home.floor.bottomfloat.b bVar = this.f9159i;
        if (bVar == null) {
            return d2;
        }
        int i2 = bVar.C;
        if (i2 == 1 || i2 == 2) {
            return com.jingdong.app.mall.home.floor.common.d.d(bVar.B != 0 ? 20 : 24);
        }
        return d2;
    }

    private SpannableString t() {
        com.jingdong.app.mall.home.floor.bottomfloat.b bVar = this.f9159i;
        if (bVar == null) {
            return null;
        }
        if (bVar.C == 3 && !TextUtils.isEmpty(bVar.v)) {
            StringBuilder sb = new StringBuilder();
            if (!TextUtils.isEmpty(this.f9159i.v)) {
                sb.append(this.f9159i.v);
                sb.append(LangUtils.SINGLE_SPACE);
            }
            sb.append(this.f9159i.u);
            SpannableString spannableString = new SpannableString(sb);
            spannableString.setSpan(new ForegroundColorSpan(this.f9159i.f9194i), 0, this.f9159i.v.length(), 17);
            spannableString.setSpan(new StyleSpan(1), 0, this.f9159i.v.length(), 17);
            return spannableString;
        } else if (this.f9159i.B == 1) {
            return new SpannableString(this.f9159i.y);
        } else {
            return new SpannableString(this.f9159i.u);
        }
    }

    private int u() {
        com.jingdong.app.mall.home.floor.bottomfloat.b bVar = this.f9159i;
        if (bVar == null) {
            return 26;
        }
        int i2 = bVar.C;
        if (i2 == 1 || i2 == 2) {
            if (bVar.B != 0) {
                return 20;
            }
        } else if (i2 != 3) {
            return 26;
        }
        return 28;
    }

    private int v() {
        com.jingdong.app.mall.home.floor.bottomfloat.b bVar = this.f9159i;
        if (bVar == null) {
            return 28;
        }
        int i2 = bVar.C;
        return ((i2 == 1 || i2 == 2) && bVar.B != 0) ? 20 : 28;
    }

    private int w() {
        com.jingdong.app.mall.home.floor.bottomfloat.b bVar = this.f9159i;
        if (bVar == null) {
            return 0;
        }
        int i2 = bVar.C;
        if (i2 == 1 || i2 == 2) {
            return bVar.B == 0 ? 1 : 2;
        }
        return 0;
    }

    private void x() {
        com.jingdong.app.mall.home.floor.bottomfloat.b bVar = this.f9159i;
        if (bVar != null && bVar.C == 3 && !TextUtils.isEmpty(bVar.w) && !TextUtils.isEmpty(this.f9159i.x)) {
            this.z.setVisibility(0);
            ImageTextView imageTextView = this.z;
            com.jingdong.app.mall.home.floor.bottomfloat.b bVar2 = this.f9159i;
            imageTextView.a(bVar2.x, bVar2.w, 3);
            this.z.c(16, 0, 0, 0);
            this.z.b(-1, 20, false);
            return;
        }
        this.z.setVisibility(8);
    }

    private void y() {
        com.jingdong.app.mall.home.floor.bottomfloat.b bVar = this.f9159i;
        if (bVar != null && bVar.b() && !TextUtils.isEmpty(this.f9159i.z)) {
            this.D.setVisibility(0);
            com.jingdong.app.mall.home.floor.ctrl.e.u(this.D, this.f9159i.z);
            return;
        }
        this.D.setVisibility(8);
    }

    private void z() {
        com.jingdong.app.mall.home.floor.bottomfloat.b bVar = this.f9159i;
        if (bVar != null && bVar.b()) {
            com.jingdong.app.mall.home.floor.bottomfloat.b bVar2 = this.f9159i;
            if (bVar2.B == 1 && !TextUtils.isEmpty(bVar2.u)) {
                this.B.setVisibility(0);
                g.o(this.B, 24);
                this.B.setTextColor(this.f9159i.E);
                this.B.setText(this.f9159i.u);
                return;
            }
        }
        this.B.setVisibility(8);
    }

    public void B(long j2) {
        com.jingdong.app.mall.home.x.b bVar = this.G;
        if (bVar != null) {
            bVar.g(this.H);
        }
        if (this.q.get()) {
            g(true);
            return;
        }
        long j3 = this.f9158h.f9197l;
        if (j2 > 0) {
            this.G = com.jingdong.app.mall.home.x.g.b().f(j3, j2);
            if (this.H == null) {
                this.H = new e();
            }
            com.jingdong.app.mall.home.x.b bVar2 = this.G;
            if (bVar2 != null) {
                bVar2.a(this.H);
            }
        }
    }

    @Override // com.jingdong.app.mall.home.floor.bottomfloat.FrameBaseLayout
    public void d() {
        super.d();
        this.x.setVisibility(8);
        this.q.set(e(this.f9159i.q));
        M.removeCallbacksAndMessages(null);
        setTranslationX(com.jingdong.app.mall.home.floor.common.d.f9279g << 1);
        if (this.f9159i.a() && !this.q.get() && !TextUtils.isEmpty(this.f9159i.r)) {
            A();
            if (!TextUtils.isEmpty(this.f9159i.t) && !TextUtils.equals(this.f9159i.t, this.F)) {
                this.F = this.f9159i.t;
                i.p().j(this.F, null);
            }
            z();
            y();
            x();
            r();
            com.jingdong.app.mall.home.floor.ctrl.e.p(this.v, this.f9159i.r, com.jingdong.app.mall.home.floor.ctrl.e.b, new c());
            return;
        }
        g(true);
    }

    @Override // com.jingdong.app.mall.home.floor.bottomfloat.FrameBaseLayout
    public boolean f() {
        return this.p.get() || this.r.get();
    }

    @Override // com.jingdong.app.mall.home.floor.bottomfloat.FrameBaseLayout
    public void h() {
        super.h();
        if (this.f9159i != null && this.s.get() && this.f9159i.f9192g) {
            g(true);
        }
    }

    @Override // com.jingdong.app.mall.home.floor.bottomfloat.FrameBaseLayout
    public void i() {
        super.i();
        if (this.f9159i == null) {
            return;
        }
        long elapsedRealtime = (this.f9159i.f9196k * 1000) - (SystemClock.elapsedRealtime() - s.f9357c);
        if (this.r.get() || elapsedRealtime <= 3000) {
            setTranslationX(com.jingdong.app.mall.home.floor.common.d.f9279g << 1);
            g(true);
        }
    }

    @Override // com.jingdong.app.mall.home.floor.bottomfloat.FrameBaseLayout
    public void j() {
        super.j();
        if (this.f9159i.d) {
            i.p().h();
        }
    }

    @Override // com.jingdong.app.mall.home.floor.bottomfloat.FrameBaseLayout
    public void n() {
        super.n();
        com.jingdong.app.mall.home.floor.bottomfloat.b bVar = this.f9159i;
        m(bVar, "2", bVar.f9190e ? "1" : "0", "0");
    }

    @Override // com.jingdong.app.mall.home.floor.bottomfloat.FrameBaseLayout
    public void p() {
        long elapsedRealtime = (this.f9159i.f9196k * 1000) - (SystemClock.elapsedRealtime() - s.f9357c);
        if (!this.r.get() && elapsedRealtime >= 5000) {
            if (this.s.getAndSet(true)) {
                return;
            }
            this.x.setVisibility(0);
            this.f9161k.showTime(this.f9159i.f9189c);
            B(elapsedRealtime);
            M.postDelayed(new d(), elapsedRealtime);
            setTranslationX(0.0f);
            b();
            if (this.o.getAndSet(false)) {
                new com.jingdong.app.mall.home.q.a("\u5e95\u90e8\u7ea2\u5305\u96e8\u66dd\u5149", true, this.f9159i.f9199n).b();
            }
            com.jingdong.app.mall.home.floor.ctrl.e.f(this.f9159i.r, this.v, com.jingdong.app.mall.home.floor.ctrl.e.f9402h);
            return;
        }
        g(true);
    }

    @Override // com.jingdong.app.mall.home.floor.bottomfloat.FrameBaseLayout
    public boolean q() {
        return super.q() || getTranslationX() > 0.0f;
    }

    protected void r() {
        if (this.f9159i.b() && this.f9159i.B == 1) {
            f fVar = new f(-1, -2);
            this.f9162l = fVar;
            fVar.E(0, 0, 0, (this.f9158h.f9193h - L) / 2);
        } else {
            f fVar2 = new f(-1, -1);
            this.f9162l = fVar2;
            fVar2.E(0, I, 0, 0);
        }
        this.u.R(-1, this.f9158h.f9193h + I);
        this.w.E(0, I, 0, 0);
        this.A.E(0, ((this.f9158h.f9193h - J) / 2) + I, 40, 0);
        this.E.E(56, ((this.f9158h.f9193h - K) / 2) + I, 0, 0);
        this.C.E(this.f9159i.F, (((this.f9158h.f9193h - L) / 2) + I) - 3, 170, 0);
        f.d(this.v, this.w, true);
        f.d(this.f9161k, this.f9162l, true);
        f.c(this, this.u);
        f.c(this.x, this.y);
        f.d(this.z, this.A, true);
        f.d(this.D, this.E, true);
        f.d(this.B, this.C, true);
    }
}
