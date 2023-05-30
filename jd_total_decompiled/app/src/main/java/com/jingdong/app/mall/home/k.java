package com.jingdong.app.mall.home;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.GradientDrawable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.app.mall.home.floor.bottomfloat.BaseFloatPriority;
import com.jingdong.app.mall.home.floor.bottomfloat.BottomFloatLayout;
import com.jingdong.app.mall.home.floor.common.i.l;
import com.jingdong.app.mall.home.floor.ctrl.t.n;
import com.jingdong.app.mall.home.floor.model.entity.HomeWebFloorViewEntity;
import com.jingdong.cleanmvp.common.BaseEvent;
import com.jingdong.common.entity.JumpEntity;
import com.jingdong.common.jdmiaosha.utils.cache.Final;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.common.login.LoginEvent;
import com.jingdong.common.login.LoginUserBase;
import com.jingdong.sdk.platform.business.personal.R2;

/* loaded from: classes4.dex */
public class k implements View.OnClickListener {
    private String A;
    private String B;
    private String C;
    private String D;
    private JumpEntity E;
    private HomeWebFloorViewEntity F;

    /* renamed from: h  reason: collision with root package name */
    private RelativeLayout f10310h;

    /* renamed from: j  reason: collision with root package name */
    private SimpleDraweeView f10312j;

    /* renamed from: l  reason: collision with root package name */
    private TextView f10314l;

    /* renamed from: n  reason: collision with root package name */
    private TextView f10316n;
    private SimpleDraweeView o;
    private final View q;
    private final Context r;
    private int t;
    private BaseFloatPriority u;
    private SpannableStringBuilder v;
    private String w;
    private int x;
    private int y;
    private String z;

    /* renamed from: g  reason: collision with root package name */
    private int f10309g = 0;

    /* renamed from: i  reason: collision with root package name */
    private final com.jingdong.app.mall.home.floor.common.f f10311i = new com.jingdong.app.mall.home.floor.common.f(R2.attr.cornerRadius, 80);

    /* renamed from: k  reason: collision with root package name */
    private final com.jingdong.app.mall.home.floor.common.f f10313k = new com.jingdong.app.mall.home.floor.common.f(80, 80);

    /* renamed from: m  reason: collision with root package name */
    private final com.jingdong.app.mall.home.floor.common.f f10315m = new com.jingdong.app.mall.home.floor.common.f(R2.attr.badgeStyle, -1);
    private final com.jingdong.app.mall.home.floor.common.f p = new com.jingdong.app.mall.home.floor.common.f(144, 56);
    private boolean s = !TextUtils.isEmpty(LoginUserBase.getUserPin());
    private String G = "";
    private String H = "";

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class a extends com.jingdong.app.mall.home.o.a.b {
        a() {
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        protected void safeRun() {
            k.this.u.l();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class b extends BaseFloatPriority {

        /* loaded from: classes4.dex */
        class a extends com.jingdong.app.mall.home.o.a.b {
            a() {
            }

            @Override // com.jingdong.app.mall.home.o.a.b
            public void safeRun() {
                k.this.u();
                k.this.A();
            }
        }

        b(String str, int i2) {
            super(str, i2);
        }

        @Override // com.jingdong.app.mall.home.floor.bottomfloat.BaseFloatPriority
        public boolean a() {
            k.this.s = !TextUtils.isEmpty(LoginUserBase.getUserPin());
            return (k.this.s || BottomFloatLayout.t.get() || !super.a() || n.t() || i.i()) ? false : true;
        }

        @Override // com.jingdong.app.mall.home.floor.bottomfloat.BaseFloatPriority
        protected void g(int i2) {
            k.this.r();
        }

        @Override // com.jingdong.app.mall.home.floor.bottomfloat.BaseFloatPriority
        public void h() {
            com.jingdong.app.mall.home.o.a.f.E0(new a());
        }

        @Override // com.jingdong.app.mall.home.floor.bottomfloat.BaseFloatPriority
        public boolean m(int i2) {
            return 15 != i2 || n.t();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class c extends com.jingdong.app.mall.home.o.a.b {
        c() {
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        protected void safeRun() {
            k.this.u.l();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class d extends com.jingdong.app.mall.home.o.a.b {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ int f10321g;

        d(int i2) {
            this.f10321g = i2;
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        public void safeRun() {
            if (this.f10321g == 0) {
                k.this.f10311i.Q(R2.attr.cornerRadius);
                k.this.f10315m.Q(R2.attr.badgeStyle);
            } else {
                k.this.f10311i.Q(R2.attr.chipSpacingVertical);
                k.this.f10315m.Q(312);
            }
            k kVar = k.this;
            kVar.o(kVar.f10310h, 0);
            k.this.z();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class e extends com.jingdong.app.mall.home.o.a.b {
        e() {
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        public void safeRun() {
            if (!(k.this.q instanceof ViewGroup) || k.this.f10310h == null) {
                return;
            }
            ((ViewGroup) k.this.q).removeView(k.this.f10310h);
            k.this.f10310h = null;
        }
    }

    public k(View view) {
        this.q = view;
        this.r = view.getContext();
        com.jingdong.app.mall.home.o.a.f.G0(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void A() {
        RelativeLayout relativeLayout = this.f10310h;
        if (relativeLayout == null || this.s) {
            return;
        }
        com.jingdong.app.mall.home.v.c.a.a(relativeLayout);
        if (this.f10310h.getVisibility() == 8) {
            E(this.f10309g);
            if (this.q != null) {
                com.jingdong.app.mall.home.r.c.a.y("Home_UnregisteredGuideExpo", "", this.H);
            }
        }
    }

    private void B() {
        Context context = this.r;
        if (context == null) {
            return;
        }
        com.jingdong.app.mall.home.floor.ctrl.e.m(this.f10312j, this.z, context.getResources().getDrawable(R.drawable.home_login_guide_icon));
        TextView textView = this.f10314l;
        if (textView != null) {
            textView.setTextColor(this.x);
            this.f10314l.setText(this.v);
        }
        com.jingdong.app.mall.home.floor.ctrl.e.m(this.o, this.B, this.r.getResources().getDrawable(R.drawable.home_bg_corners_loginguide_button));
        TextView textView2 = this.f10316n;
        if (textView2 != null) {
            textView2.setText(this.A);
        }
    }

    private void D(String str, String str2) {
        if (!TextUtils.isEmpty(str2)) {
            int indexOf = this.w.indexOf(str);
            ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(this.y);
            this.v.setSpan(foregroundColorSpan, indexOf, str2.length() + indexOf, 17);
        }
        this.w = this.w.replace(str, str2);
    }

    private void E(int i2) {
        if (m() && !this.s) {
            com.jingdong.app.mall.home.o.a.f.E0(new d(i2));
        }
    }

    private boolean m() {
        BaseFloatPriority baseFloatPriority = this.u;
        if (baseFloatPriority == null || baseFloatPriority.a()) {
            return (com.jingdong.app.mall.home.o.a.f.f10457c && com.jingdong.app.mall.home.o.a.f.k0() && i.g() < 1) ? false : true;
        }
        this.u.b(false);
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void o(View view, int i2) {
        if (view != null) {
            view.setVisibility(i2);
        }
    }

    private RelativeLayout q() {
        RelativeLayout relativeLayout = new RelativeLayout(this.r);
        this.f10311i.F(new Rect(20, 0, 0, 20));
        RelativeLayout.LayoutParams u = this.f10311i.u(relativeLayout);
        u.addRule(2, R.id.gap);
        relativeLayout.setLayoutParams(u);
        relativeLayout.setOnClickListener(this);
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(-1308622848);
        gradientDrawable.setCornerRadius(this.f10311i.h());
        relativeLayout.setBackground(gradientDrawable);
        SimpleDraweeView simpleDraweeView = new SimpleDraweeView(this.r);
        this.f10312j = simpleDraweeView;
        simpleDraweeView.setScaleType(ImageView.ScaleType.FIT_XY);
        this.f10313k.E(10, 0, 0, 0);
        relativeLayout.addView(this.f10312j, this.f10313k.u(this.f10312j));
        com.jingdong.app.mall.home.floor.common.g gVar = new com.jingdong.app.mall.home.floor.common.g(this.r, false);
        gVar.m(24);
        gVar.l(this.x);
        gVar.h();
        gVar.c(true);
        gVar.d(16);
        gVar.i(this.v);
        gVar.q(this.r, 4099);
        this.f10314l = gVar.a();
        this.f10315m.F(new Rect(114, 0, 0, 0));
        this.f10315m.J(2, -3, 2, -3);
        relativeLayout.addView(this.f10314l, this.f10315m.u(this.f10314l));
        this.p.F(new Rect(0, 0, 12, 0));
        SimpleDraweeView simpleDraweeView2 = new SimpleDraweeView(this.r);
        this.o = simpleDraweeView2;
        simpleDraweeView2.setScaleType(ImageView.ScaleType.FIT_XY);
        RelativeLayout.LayoutParams u2 = this.p.u(this.o);
        u2.addRule(15);
        u2.addRule(11);
        relativeLayout.addView(this.o, u2);
        this.o.setImageResource(R.drawable.home_bg_corners_loginguide_button);
        com.jingdong.app.mall.home.floor.common.g gVar2 = new com.jingdong.app.mall.home.floor.common.g(this.r, false);
        gVar2.m(24);
        gVar2.d(17);
        gVar2.l(-1);
        gVar2.h();
        gVar2.i(this.A);
        TextView a2 = gVar2.a();
        this.f10316n = a2;
        RelativeLayout.LayoutParams u3 = this.p.u(a2);
        u3.addRule(15);
        u3.addRule(11);
        relativeLayout.addView(this.f10316n, u3);
        View view = this.q;
        com.jingdong.app.mall.home.o.a.f.n(view);
        ((ViewGroup) view).addView(relativeLayout, this.t);
        relativeLayout.setVisibility(8);
        return relativeLayout;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void r() {
        o(this.f10310h, 8);
    }

    private void t() {
        if (this.u == null) {
            this.u = new b("\u767b\u5f55\u5f15\u5bfc", 101);
        } else {
            com.jingdong.app.mall.home.floor.bottomfloat.a.d().a(this.u);
        }
        if (TextUtils.isEmpty(LoginUserBase.getUserPin()) && !this.u.a()) {
            com.jingdong.app.mall.home.o.a.f.F0(new c(), Final.FIVE_SECOND);
        }
        this.u.j();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void u() {
        View view = this.q;
        if (view == null) {
            return;
        }
        com.jingdong.app.mall.home.o.a.f.n(view);
        this.t = ((ViewGroup) view).indexOfChild(this.q.findViewById(R.id.home_tips)) + 1;
        if (this.f10310h == null) {
            this.f10310h = q();
        }
        B();
    }

    private void v() {
        if (this.F == null) {
            this.F = new HomeWebFloorViewEntity();
        }
        HomeWebFloorViewEntity homeWebFloorViewEntity = this.F;
        homeWebFloorViewEntity.clickEventId = "Home_UnregisteredGuideXview";
        homeWebFloorViewEntity.checkUnLogin = true;
        homeWebFloorViewEntity.srvJson = this.G;
    }

    private void w(com.jingdong.app.mall.home.r.e.h hVar) {
        String jsonString = hVar.getJsonString("firmText", "Hi\uff0c\u767b\u5f55\u83b7\u5f97\u66f4\u591a\u4f18\u60e0\u54e6\uff5e");
        String jsonString2 = hVar.getJsonString("specialText1");
        String jsonString3 = hVar.getJsonString("specialText2");
        String jsonString4 = hVar.getJsonString("specialText3");
        if (com.jingdong.app.mall.home.o.a.k.v()) {
            jsonString = jsonString.concat(com.jingdong.app.mall.home.o.a.k.q());
        }
        this.v = new SpannableStringBuilder(jsonString.replace("#1", jsonString2).replace("#2", jsonString3).replace("#3", jsonString4));
        this.w = jsonString;
        this.y = com.jingdong.app.mall.home.floor.view.b.h.a.d(hVar.getJsonString("specialTextColor"), -1);
        if (jsonString.contains("#1")) {
            D("#1", jsonString2);
        }
        if (jsonString.contains("#2")) {
            D("#2", jsonString3);
        }
        if (jsonString.contains("#3")) {
            D("#3", jsonString4);
        }
        this.x = com.jingdong.app.mall.home.floor.view.b.h.a.d(hVar.getJsonString("firmTextColor"), -1);
        this.z = hVar.getJsonString("iconImg");
        this.A = hVar.getJsonString("buttonText", "\u7acb\u5373\u767b\u5f55");
        this.B = hVar.getJsonString("buttonImg");
        this.C = hVar.getJsonString("openType");
        this.D = hVar.getJsonString("xviewJumpUrl");
        JumpEntity jump = hVar.getJump();
        this.E = jump;
        this.G = jump != null ? jump.srvJson : "";
        this.H = hVar.f();
        if (x()) {
            v();
            com.jingdong.app.mall.home.floor.ctrl.t.i.p().j(this.D, this.F);
        }
        B();
    }

    private boolean x() {
        return TextUtils.equals(this.C, "0") && !TextUtils.isEmpty(this.D);
    }

    public void C() {
        BaseFloatPriority baseFloatPriority = this.u;
        if (baseFloatPriority != null) {
            baseFloatPriority.b(false);
        }
        com.jingdong.app.mall.home.o.a.f.E0(new e());
    }

    public void F(boolean z) {
        if (this.f10309g == z || !m()) {
            return;
        }
        this.f10309g = z ? 1 : 0;
        E(z ? 1 : 0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean n() {
        BaseFloatPriority baseFloatPriority = this.u;
        return baseFloatPriority != null && baseFloatPriority.a();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (com.jingdong.app.mall.home.o.a.k.w() || l.h()) {
            return;
        }
        if (l.j(view.getContext())) {
            com.jingdong.app.mall.home.r.c.a.s("Home_UnregisteredGuide", "", this.G);
            return;
        }
        boolean z = false;
        if (x()) {
            v();
            z = com.jingdong.app.mall.home.floor.ctrl.t.i.p().i(this.D, this.F);
        }
        if (!z) {
            JumpEntity jumpEntity = this.E;
            if (jumpEntity != null && !TextUtils.isEmpty(jumpEntity.des) && !TextUtils.equals(JumpUtil.VALUE_DES_JDLOGIN, this.E.des)) {
                l.e(view.getContext(), this.E);
            } else {
                l.l(view.getContext());
            }
        }
        com.jingdong.app.mall.home.r.c.a.s("Home_UnregisteredGuide", "", this.G);
    }

    public void onEventMainThread(BaseEvent baseEvent) {
        if (baseEvent instanceof LoginEvent) {
            p(!BottomFloatLayout.t.get());
        }
    }

    void p(boolean z) {
        boolean z2 = !TextUtils.isEmpty(LoginUserBase.getUserPin());
        this.s = z2;
        if (z2) {
            C();
        } else if (z && m()) {
            u();
            A();
        } else {
            r();
        }
    }

    public void s(com.jingdong.app.mall.home.r.e.h hVar) {
        w(hVar);
        if (this.q == null || this.s) {
            return;
        }
        com.jingdong.app.mall.home.v.c.a.a(this.f10310h);
        t();
        com.jingdong.app.mall.home.o.a.f.F0(new a(), BottomFloatLayout.t.get() ? 200L : 0L);
    }

    public void y(boolean z) {
        t();
        p(z);
    }

    public void z() {
        com.jingdong.app.mall.home.floor.common.f.d(this.f10310h, this.f10311i, true);
        com.jingdong.app.mall.home.floor.common.f.c(this.f10312j, this.f10313k);
        com.jingdong.app.mall.home.floor.common.f.c(this.f10314l, this.f10315m);
        com.jingdong.app.mall.home.floor.common.f.O(this.f10314l, 24);
        com.jingdong.app.mall.home.floor.common.f.d(this.o, this.p, true);
        com.jingdong.app.mall.home.floor.common.f.d(this.f10316n, this.p, true);
        com.jingdong.app.mall.home.floor.common.f.O(this.f10316n, 24);
    }
}
