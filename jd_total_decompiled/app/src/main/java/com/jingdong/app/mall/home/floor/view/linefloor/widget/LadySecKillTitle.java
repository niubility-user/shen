package com.jingdong.app.mall.home.floor.view.linefloor.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.app.mall.home.R;
import com.jingdong.app.mall.home.base.HomeDraweeView;
import com.jingdong.app.mall.home.floor.common.f;
import com.jingdong.app.mall.home.floor.common.g;
import com.jingdong.app.mall.home.floor.common.i.l;
import com.jingdong.app.mall.home.floor.common.i.m;
import com.jingdong.app.mall.home.floor.ctrl.e;
import com.jingdong.app.mall.home.floor.view.view.GradientTextView;
import com.jingdong.app.mall.home.floor.view.view.JDViewFlipper;
import com.jingdong.app.mall.home.floor.view.widget.TimeFormatView;
import com.jingdong.app.util.image.assist.JDFailReason;
import com.jingdong.app.util.image.listener.JDSimpleImageLoadingListener;
import com.jingdong.common.entity.JumpEntity;
import com.jingdong.jdsdk.utils.FontsUtil;
import com.jingdong.sdk.platform.business.personal.R2;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes4.dex */
public class LadySecKillTitle extends RelativeLayout {
    private com.jingdong.app.mall.home.o.a.b A;

    /* renamed from: g  reason: collision with root package name */
    private GradientTextView f9882g;

    /* renamed from: h  reason: collision with root package name */
    private SimpleDraweeView f9883h;

    /* renamed from: i  reason: collision with root package name */
    private f f9884i;

    /* renamed from: j  reason: collision with root package name */
    private JDViewFlipper f9885j;

    /* renamed from: k  reason: collision with root package name */
    private f f9886k;

    /* renamed from: l  reason: collision with root package name */
    private LabelLayout f9887l;

    /* renamed from: m  reason: collision with root package name */
    private f f9888m;

    /* renamed from: n  reason: collision with root package name */
    private LinearLayout f9889n;
    private f o;
    private RelativeLayout p;
    private f q;
    private TextView r;
    private SimpleDraweeView s;
    private TimeFormatView t;
    private com.jingdong.app.mall.home.floor.view.b.g.b u;
    private Handler v;
    private AtomicBoolean w;
    private int x;
    private int[] y;
    private boolean z;

    /* loaded from: classes4.dex */
    class a implements View.OnClickListener {
        a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (LadySecKillTitle.this.u == null) {
                return;
            }
            LadySecKillTitle.this.u.t0(0, view.getContext(), false);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class b implements View.OnClickListener {
        b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (LadySecKillTitle.this.u == null) {
                return;
            }
            JumpEntity Z = LadySecKillTitle.this.u.Z();
            if (Z != null) {
                com.jingdong.app.mall.home.r.c.a.s("Home_SeckillOperaWord", "", Z.getSrvJson());
            }
            if (Z == null) {
                LadySecKillTitle.this.u.t0(0, view.getContext(), false);
            } else {
                l.e(view.getContext(), Z);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class c extends JDSimpleImageLoadingListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ com.jingdong.app.mall.home.floor.view.b.g.b f9892g;

        c(com.jingdong.app.mall.home.floor.view.b.g.b bVar) {
            this.f9892g = bVar;
        }

        @Override // com.jingdong.app.util.image.listener.JDSimpleImageLoadingListener, com.jingdong.app.util.image.listener.JDImageLoadingListener
        public void onLoadingCancelled(String str, View view) {
        }

        @Override // com.jingdong.app.util.image.listener.JDSimpleImageLoadingListener, com.jingdong.app.util.image.listener.JDImageLoadingListener
        public void onLoadingComplete(String str, View view, Bitmap bitmap) {
            if (this.f9892g.C() && LadySecKillTitle.this.f9883h.getVisibility() == 0) {
                LadySecKillTitle.this.f9882g.setVisibility(4);
                LadySecKillTitle.this.f9882g.setMinWidth(LadySecKillTitle.this.f9884i.v());
                LadySecKillTitle.this.f9882g.setMaxWidth(LadySecKillTitle.this.f9884i.v());
            }
        }

        @Override // com.jingdong.app.util.image.listener.JDSimpleImageLoadingListener, com.jingdong.app.util.image.listener.JDImageLoadingListener
        public void onLoadingFailed(String str, View view, JDFailReason jDFailReason) {
            LadySecKillTitle.this.f9882g.setVisibility(0);
            LadySecKillTitle.this.f9882g.setMinWidth(0);
            LadySecKillTitle.this.f9882g.setMaxWidth(com.jingdong.app.mall.home.floor.common.d.f9279g);
            LadySecKillTitle.this.f9883h.setVisibility(8);
        }
    }

    /* loaded from: classes4.dex */
    class d extends com.jingdong.app.mall.home.o.a.b {
        d() {
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        protected void safeRun() {
            int c0 = LadySecKillTitle.this.u.c0();
            if (c0 <= 0 || LadySecKillTitle.this.u.r0() || !LadySecKillTitle.this.z) {
                return;
            }
            LadySecKillTitle.this.t();
            LadySecKillTitle.this.f9885j.showNext();
            LadySecKillTitle.i(LadySecKillTitle.this);
            String str = "playCount==" + c0 + "---flipperCount==" + LadySecKillTitle.this.x;
            if (LadySecKillTitle.this.x >= (c0 * 2) - 1) {
                LadySecKillTitle.this.u.v0(c0);
                LadySecKillTitle.this.z = false;
                return;
            }
            LadySecKillTitle.this.v.postDelayed(this, 3000L);
        }
    }

    public LadySecKillTitle(Context context) {
        super(context);
        this.v = new Handler(Looper.getMainLooper());
        this.w = new AtomicBoolean(false);
        this.x = 0;
        this.y = new int[]{-580315, -768442, -20831};
        this.A = new d();
        g gVar = new g(context, true);
        gVar.m(30);
        gVar.f(1);
        gVar.l(-15066598);
        gVar.j(true);
        GradientTextView b2 = gVar.b();
        this.f9882g = b2;
        b2.setId(R.id.homefloor_child_item1);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(15);
        addView(this.f9882g, layoutParams);
        JDViewFlipper jDViewFlipper = new JDViewFlipper(getContext());
        this.f9885j = jDViewFlipper;
        jDViewFlipper.setOnClickListener(new a());
        this.f9885j.setInAnimation(getContext(), R.anim.home_in_animation_bottom);
        this.f9885j.setOutAnimation(getContext(), R.anim.home_out_animation_top);
        f fVar = new f(-2, 39);
        this.f9886k = fVar;
        RelativeLayout.LayoutParams u = fVar.u(this.f9885j);
        u.addRule(15);
        u.addRule(1, this.f9882g.getId());
        addView(this.f9885j, u);
        s();
    }

    static /* synthetic */ int i(LadySecKillTitle ladySecKillTitle) {
        int i2 = ladySecKillTitle.x;
        ladySecKillTitle.x = i2 + 1;
        return i2;
    }

    private int l(com.jingdong.app.mall.home.floor.view.b.g.b bVar) {
        this.f9882g.setText(com.jingdong.app.mall.home.o.a.f.j(4, bVar.q0()));
        this.f9882g.setTextGradient(GradientTextView.GradientType.LeftToRight, bVar.k0());
        this.f9882g.getPaint().setFakeBoldText(bVar.x(0));
        int s = bVar.s(0);
        g.o(this.f9882g, s);
        return s;
    }

    private void m(com.jingdong.app.mall.home.floor.view.b.g.b bVar) {
        int c0 = bVar.c0();
        boolean r0 = bVar.r0();
        if (c0 <= 0 && !r0) {
            this.f9885j.removeView(this.f9889n);
            return;
        }
        if (this.f9885j.indexOfChild(this.f9889n) < 0) {
            m.b(this.f9885j, this.f9889n, -1);
        }
        String a0 = bVar.a0();
        if (TextUtils.isEmpty(a0)) {
            LabelLayout labelLayout = this.f9887l;
            if (labelLayout != null) {
                labelLayout.setVisibility(8);
                return;
            }
            return;
        }
        String j2 = com.jingdong.app.mall.home.o.a.f.j(6, a0);
        int d2 = com.jingdong.app.mall.home.floor.common.d.d(12);
        if (bVar.E()) {
            this.f9888m = new f(-2, 32);
        } else {
            this.f9888m = new f(R2.anim.popup_center_enter, 32);
        }
        LabelLayout labelLayout2 = this.f9887l;
        if (labelLayout2 == null) {
            LabelLayout labelLayout3 = new LabelLayout(getContext(), false, false);
            this.f9887l = labelLayout3;
            this.f9889n.addView(this.f9887l, this.f9888m.i(labelLayout3));
        } else {
            f.c(labelLayout2, this.f9888m);
        }
        this.f9887l.s = bVar.E();
        this.f9887l.t(bVar.E() ? bVar.o0() : this.y);
        this.f9887l.q(bVar.n0());
        this.f9887l.w(j2, bVar.E() ? 22 : 24);
        this.f9887l.f9881n.setGravity(17);
        this.f9887l.setPadding(d2, 0, bVar.E() ? com.jingdong.app.mall.home.floor.common.d.d(24) : d2, 0);
        t();
        if (this.f9885j.getCurrentView() != this.f9889n) {
            this.f9885j.showNext();
        }
    }

    private void n(com.jingdong.app.mall.home.floor.view.b.g.b bVar) {
        if (com.jingdong.app.mall.home.floor.common.i.g.k() && !bVar.r0()) {
            this.f9885j.setVisibility(8);
            return;
        }
        this.f9885j.setVisibility(0);
        o(bVar);
        m(bVar);
    }

    private void o(com.jingdong.app.mall.home.floor.view.b.g.b bVar) {
        String X;
        boolean E = bVar.E();
        this.x = 0;
        f fVar = new f(E ? 69 : 30, -1);
        Typeface typeFace = FontsUtil.getTypeFace(getContext());
        TextView textView = this.r;
        if (textView == null) {
            TextView textView2 = new TextView(getContext());
            this.r = textView2;
            textView2.setIncludeFontPadding(false);
            this.r.setSingleLine();
            this.r.setEllipsize(TextUtils.TruncateAt.END);
            this.r.setTypeface(typeFace);
            RelativeLayout relativeLayout = this.p;
            TextView textView3 = this.r;
            relativeLayout.addView(textView3, fVar.u(textView3));
        } else {
            f.c(textView, fVar);
        }
        this.r.setTextColor(E ? -1 : -55513);
        this.r.setTextSize(0, com.jingdong.app.mall.home.floor.common.d.d(E ? 18 : 26));
        TextView textView4 = this.r;
        if (E) {
            X = bVar.X() + "\u70b9\u573a";
        } else {
            X = bVar.X();
        }
        textView4.setText(X);
        this.r.setGravity(E ? 17 : 8388659);
        if (E) {
            SimpleDraweeView simpleDraweeView = this.s;
            if (simpleDraweeView != null) {
                simpleDraweeView.setVisibility(8);
            }
        } else {
            f fVar2 = new f(39, 36);
            fVar2.E(25, 0, 0, 0);
            SimpleDraweeView simpleDraweeView2 = this.s;
            if (simpleDraweeView2 == null) {
                HomeDraweeView homeDraweeView = new HomeDraweeView(getContext());
                this.s = homeDraweeView;
                homeDraweeView.setScaleType(ImageView.ScaleType.FIT_XY);
                RelativeLayout relativeLayout2 = this.p;
                SimpleDraweeView simpleDraweeView3 = this.s;
                relativeLayout2.addView(simpleDraweeView3, fVar2.u(simpleDraweeView3));
            } else {
                f.c(simpleDraweeView2, fVar2);
            }
            this.s.setImageResource(R.drawable.home_seckill_name);
            this.s.setVisibility(0);
        }
        f fVar3 = new f(E ? 95 : 112, -1);
        fVar3.F(new Rect(E ? 73 : 74, 0, 0, 0));
        TimeFormatView timeFormatView = this.t;
        if (timeFormatView == null) {
            TimeFormatView timeFormatView2 = new TimeFormatView(getContext());
            this.t = timeFormatView2;
            timeFormatView2.n(typeFace);
            RelativeLayout.LayoutParams u = fVar3.u(this.t);
            u.addRule(15);
            this.p.addView(this.t, u);
        } else {
            f.c(timeFormatView, fVar3);
        }
        this.t.k(E ? -381927 : -1);
        this.t.i(E ? -381927 : -380642);
        this.t.e(E ? 16736594 : -40622);
        this.t.h(com.jingdong.app.mall.home.floor.common.d.d(30));
        this.t.g(com.jingdong.app.mall.home.floor.common.d.d(32));
        this.t.l(com.jingdong.app.mall.home.floor.common.d.d(22));
        bVar.N0(this);
        this.p.setBackgroundDrawable(E ? getContext().getResources().getDrawable(R.drawable.home_seckill_time_bg) : null);
        v();
    }

    private void p(com.jingdong.app.mall.home.floor.view.b.g.b bVar) {
        String l0 = bVar.l0();
        int m0 = bVar.m0();
        this.f9882g.setMinWidth(0);
        this.f9882g.setMaxWidth(com.jingdong.app.mall.home.floor.common.d.f9279g);
        this.f9882g.setVisibility(0);
        if (!TextUtils.isEmpty(l0) && bVar.C() && m0 > 0) {
            int min = Math.min(Math.max(64, m0), 200);
            if (this.f9883h == null) {
                HomeDraweeView homeDraweeView = new HomeDraweeView(getContext());
                this.f9883h = homeDraweeView;
                homeDraweeView.setScaleType(ImageView.ScaleType.FIT_XY);
                f fVar = new f(min, 32);
                this.f9884i = fVar;
                RelativeLayout.LayoutParams u = fVar.u(this.f9883h);
                u.addRule(15);
                addView(this.f9883h, u);
            } else {
                this.f9884i.Q(min);
                f.c(this.f9883h, this.f9884i);
            }
            this.f9883h.setVisibility(0);
            e.j(l0, this.f9883h, e.f9402h, false, new c(bVar), null);
            return;
        }
        SimpleDraweeView simpleDraweeView = this.f9883h;
        if (simpleDraweeView != null) {
            simpleDraweeView.setVisibility(8);
        }
    }

    private LinearLayout q() {
        this.f9889n = new LinearLayout(getContext());
        f fVar = new f(-1, 36);
        this.o = fVar;
        LinearLayout linearLayout = this.f9889n;
        linearLayout.setLayoutParams(fVar.i(linearLayout));
        this.f9889n.setGravity(17);
        this.f9889n.setOnClickListener(new b());
        return this.f9889n;
    }

    private RelativeLayout r() {
        this.p = new RelativeLayout(getContext());
        f fVar = new f(-1, -1);
        this.q = fVar;
        RelativeLayout relativeLayout = this.p;
        relativeLayout.setLayoutParams(fVar.u(relativeLayout));
        return this.p;
    }

    private void s() {
        this.f9885j.addView(q());
        this.f9885j.addView(r());
    }

    public void k(com.jingdong.app.mall.home.floor.view.b.g.b bVar) {
        this.u = bVar;
        this.z = true;
        this.w.set(false);
        int l2 = l(bVar);
        p(bVar);
        n(bVar);
        int i2 = 42 - l2;
        if (l2 > 32) {
            i2 = 4;
        }
        this.f9886k.E(i2, 0, 0, 0);
        this.f9889n.setGravity(bVar.E() ? 16 : 17);
        this.q.R(bVar.E() ? R2.anim.settlement_dialog_right_exit : -1, bVar.E() ? 32 : -1);
        this.q.F(new Rect(0, bVar.E() ? 3 : 0, 0, 0));
        f.d(this.f9885j, this.f9886k, true);
        f.c(this.f9889n, this.o);
        f.c(this.p, this.q);
    }

    public void t() {
        JumpEntity Z;
        if (this.u == null || this.w.getAndSet(true) || (Z = this.u.Z()) == null) {
            return;
        }
        com.jingdong.app.mall.home.r.c.a.y("Home_SeckillOperaWordExpo", "", Z.getSrvJson());
    }

    public void u() {
        this.v.removeCallbacks(this.A);
        this.v.postDelayed(this.A, 1000L);
    }

    public void v() {
        this.v.removeCallbacks(this.A);
    }

    public void w(String str, String str2, String str3) {
        this.t.m(str, str2, str3);
    }

    public void x(boolean z) {
        com.jingdong.app.mall.home.floor.view.b.g.b bVar = this.u;
        if (bVar != null) {
            com.jingdong.app.mall.home.n.h.c.k(z || bVar.r0(), this.p);
        }
    }
}
