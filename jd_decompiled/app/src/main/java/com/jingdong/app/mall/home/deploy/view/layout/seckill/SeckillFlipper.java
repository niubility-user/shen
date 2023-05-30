package com.jingdong.app.mall.home.deploy.view.layout.seckill;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.jingdong.app.mall.home.R;
import com.jingdong.app.mall.home.deploy.view.layout.widget.IconLabel;
import com.jingdong.app.mall.home.floor.common.d;
import com.jingdong.app.mall.home.floor.common.f;
import com.jingdong.app.mall.home.floor.common.i.g;
import com.jingdong.app.mall.home.floor.view.view.JDViewFlipper;
import com.jingdong.app.mall.home.floor.view.widget.TimeFormatView;
import com.jingdong.app.mall.home.o.a.b;
import com.jingdong.app.mall.home.r.c.a;
import com.jingdong.app.mall.home.x.c;
import com.jingdong.app.mall.home.x.e;
import com.jingdong.common.entity.JumpEntity;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.utils.FontsUtil;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes4.dex */
public class SeckillFlipper extends JDViewFlipper {
    private final b A;

    /* renamed from: g  reason: collision with root package name */
    private final Handler f8954g;

    /* renamed from: h  reason: collision with root package name */
    private SeckillModel f8955h;

    /* renamed from: i  reason: collision with root package name */
    private boolean f8956i;

    /* renamed from: j  reason: collision with root package name */
    private RelativeLayout f8957j;

    /* renamed from: k  reason: collision with root package name */
    private f f8958k;

    /* renamed from: l  reason: collision with root package name */
    private IconLabel f8959l;

    /* renamed from: m  reason: collision with root package name */
    private f f8960m;

    /* renamed from: n  reason: collision with root package name */
    private RelativeLayout f8961n;
    private TextView o;
    private View p;
    private f q;
    private f r;
    private f s;
    private TimeFormatView t;
    private f u;
    private boolean v;
    private final AtomicBoolean w;
    private final AtomicBoolean x;
    private com.jingdong.app.mall.home.x.b y;
    private c z;

    public SeckillFlipper(Context context) {
        super(context);
        this.f8954g = new Handler(Looper.getMainLooper());
        this.w = new AtomicBoolean(false);
        this.x = new AtomicBoolean(false);
        this.A = new b() { // from class: com.jingdong.app.mall.home.deploy.view.layout.seckill.SeckillFlipper.1
            @Override // com.jingdong.app.mall.home.o.a.b
            protected void safeRun() {
                if (SeckillFlipper.this.f() <= 0) {
                    SeckillFlipper.this.m();
                    return;
                }
                SeckillFlipper.this.j();
                SeckillFlipper.this.showNext();
                SeckillFlipper.this.r();
            }
        };
        setInAnimation(getContext(), R.anim.home_in_animation_bottom);
        setOutAnimation(getContext(), R.anim.home_out_animation_top);
        this.f8957j = new RelativeLayout(getContext());
        f fVar = new f(-1, -1);
        this.f8958k = fVar;
        RelativeLayout relativeLayout = this.f8957j;
        relativeLayout.setLayoutParams(fVar.g(relativeLayout));
        g();
        addView(this.f8957j);
        this.f8961n = new RelativeLayout(getContext());
        f fVar2 = new f(-1, -1);
        this.r = fVar2;
        RelativeLayout relativeLayout2 = this.f8961n;
        relativeLayout2.setLayoutParams(fVar2.g(relativeLayout2));
        h();
        addView(this.f8961n);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int f() {
        int H0 = this.f8955h.H0();
        if (this.f8955h.W0() || !this.f8956i) {
            return 0;
        }
        return H0;
    }

    private void g() {
        this.f8960m = new f(-2, 28);
        IconLabel iconLabel = new IconLabel(getContext());
        this.f8959l = iconLabel;
        RelativeLayout.LayoutParams u = this.f8960m.u(iconLabel);
        u.addRule(15);
        this.f8957j.addView(this.f8959l, u);
    }

    private void h() {
        this.p = new View(getContext());
        f fVar = new f(-1, 28);
        this.q = fVar;
        RelativeLayout.LayoutParams u = fVar.u(this.p);
        u.addRule(15);
        this.p.setBackgroundDrawable(getResources().getDrawable(R.drawable.home_seckill_card_time));
        this.f8961n.addView(this.p, u);
        this.s = new f(84, -1);
        Typeface typeFace = FontsUtil.getTypeFace(getContext());
        TextView textView = new TextView(getContext());
        this.o = textView;
        textView.setIncludeFontPadding(false);
        this.o.setSingleLine();
        this.o.setEllipsize(TextUtils.TruncateAt.END);
        this.o.setTypeface(typeFace);
        RelativeLayout relativeLayout = this.f8961n;
        TextView textView2 = this.o;
        relativeLayout.addView(textView2, this.s.u(textView2));
        this.o.setTextColor(-1);
        this.o.setGravity(17);
        f fVar2 = new f(98, -1);
        this.u = fVar2;
        fVar2.F(new Rect(90, 0, 0, 0));
        TimeFormatView timeFormatView = new TimeFormatView(getContext());
        this.t = timeFormatView;
        timeFormatView.n(typeFace);
        RelativeLayout.LayoutParams u2 = this.u.u(this.t);
        u2.addRule(15);
        this.f8961n.addView(this.t, u2);
        this.t.k(-381927);
        this.t.i(-381927);
        this.t.e(0);
    }

    private void k(long j2) {
        Animation inAnimation = getInAnimation();
        if (inAnimation != null) {
            inAnimation.setDuration(j2);
        }
        Animation outAnimation = getOutAnimation();
        if (outAnimation != null) {
            outAnimation.setDuration(j2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m() {
        s();
        if (this.f8955h.W0()) {
            o();
        } else {
            n();
        }
    }

    private void n() {
        if (getCurrentView() == this.f8957j) {
            showNext();
        }
    }

    private void o() {
        j();
        if (getCurrentView() != this.f8957j) {
            showNext();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void r() {
        if (this.v) {
            return;
        }
        this.f8954g.removeCallbacksAndMessages(null);
        k(400L);
        this.f8954g.postDelayed(this.A, this.f8955h.L0());
    }

    public void e(SeckillModel seckillModel, int i2) {
        this.w.set(false);
        this.f8955h = seckillModel;
        this.f8956i = true;
        this.o.setTextSize(0, d.d(22));
        this.o.setText(seckillModel.O0());
        this.t.l(d.d(22));
        this.t.h(this.u.v() / 3);
        this.t.g(d.d(28));
        this.f8959l.h(seckillModel.D0(), seckillModel.l().v(), i2);
        f.c(this.o, this.s);
        f.c(this.t, this.u);
        f.c(this.f8957j, this.f8958k);
        f.c(this.f8959l, this.f8960m);
        f.c(this.f8961n, this.r);
        f.c(this.p, this.q);
        p();
        m();
        boolean z = g.k() && !this.f8955h.W0();
        if (!seckillModel.X0() && !z) {
            this.x.set(false);
            setVisibility(0);
            return;
        }
        this.x.set(true);
        setVisibility(8);
        s();
    }

    public void i(SeckillModel seckillModel, e eVar) {
        int C0 = seckillModel.C0();
        int i2 = C0 / 60;
        int i3 = C0 % 60;
        if (seckillModel.E0() == null && eVar.d == 0 && eVar.f11088e == i2 && eVar.f11089f == i3) {
            seckillModel.T0(true);
        }
    }

    public void j() {
        JumpEntity G0;
        if (this.f8955h == null || this.w.getAndSet(true) || (G0 = this.f8955h.G0()) == null) {
            return;
        }
        a.y("Home_SeckillOperaWordExpo", "", G0.getSrvJson());
    }

    public void l(View.OnClickListener onClickListener) {
        RelativeLayout relativeLayout = this.f8957j;
        if (relativeLayout != null) {
            relativeLayout.setOnClickListener(onClickListener);
        }
    }

    public void p() {
        if (g.k()) {
            com.jingdong.app.mall.home.x.b bVar = this.y;
            if (bVar != null) {
                bVar.g(this.z);
            }
            com.jingdong.app.mall.home.n.h.c.k(true, this.f8961n);
            return;
        }
        com.jingdong.app.mall.home.n.h.c.k(this.f8955h.W0(), this.f8961n);
        long abs = (Math.abs(this.f8955h.N0()) * 1000) - this.f8955h.M0();
        if (abs <= 0) {
            t("00", "00", "00");
            this.f8955h.c1();
            return;
        }
        try {
            this.y = com.jingdong.app.mall.home.x.g.b().f(this.f8955h.F0(), abs);
            if (this.z == null) {
                this.z = new c() { // from class: com.jingdong.app.mall.home.deploy.view.layout.seckill.SeckillFlipper.2
                    @Override // com.jingdong.app.mall.home.x.c
                    public void a() {
                        SeckillFlipper.this.t("00", "00", "00");
                        SeckillFlipper.this.f8955h.c1();
                    }

                    @Override // com.jingdong.app.mall.home.x.c
                    public void c(long j2, e eVar) {
                        if (eVar == null) {
                            return;
                        }
                        try {
                            String a = eVar.a();
                            String b = eVar.b();
                            String c2 = eVar.c();
                            SeckillFlipper seckillFlipper = SeckillFlipper.this;
                            if (a.length() <= 1) {
                                a = "0" + a;
                            }
                            if (b.length() <= 1) {
                                b = "0" + b;
                            }
                            if (c2.length() <= 1) {
                                c2 = "0" + c2;
                            }
                            seckillFlipper.t(a, b, c2);
                            SeckillFlipper seckillFlipper2 = SeckillFlipper.this;
                            seckillFlipper2.i(seckillFlipper2.f8955h, eVar);
                        } catch (Exception e2) {
                            if (Log.D) {
                                e2.printStackTrace();
                            }
                        }
                    }
                };
            }
            com.jingdong.app.mall.home.x.b bVar2 = this.y;
            if (bVar2 != null) {
                bVar2.a(this.z);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void q() {
        if (this.x.get()) {
            return;
        }
        if (f() <= 0) {
            m();
        } else if (this.v) {
            this.v = false;
            r();
        }
    }

    public void s() {
        this.v = true;
        this.f8954g.removeCallbacksAndMessages(null);
    }

    public void t(String str, String str2, String str3) {
        this.t.m(str, str2, str3);
    }
}
