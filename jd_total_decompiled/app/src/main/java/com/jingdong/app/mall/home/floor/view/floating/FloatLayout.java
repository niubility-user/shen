package com.jingdong.app.mall.home.floor.view.floating;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Looper;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.app.mall.home.R;
import com.jingdong.app.mall.home.base.HomeDraweeView;
import com.jingdong.app.mall.home.floor.common.f;
import com.jingdong.app.mall.home.floor.common.i.l;
import com.jingdong.app.mall.home.floor.common.i.m;
import com.jingdong.app.mall.home.floor.common.i.t;
import com.jingdong.app.mall.home.r.e.h;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.app.util.image.assist.JDFailReason;
import com.jingdong.app.util.image.listener.JDSimpleImageLoadingListener;
import com.jingdong.common.entity.JumpEntity;
import com.jingdong.common.recommend.RecommendMtaUtils;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.utils.Md5Encrypt;
import com.jingdong.sdk.platform.business.personal.R2;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class FloatLayout extends RelativeLayout {
    private static JDDisplayImageOptions R;
    private static JDDisplayImageOptions S;
    private static int T;
    boolean A;
    private boolean B;
    private boolean C;
    private String D;
    private int E;
    private com.jingdong.app.mall.home.floor.view.floating.b F;
    private com.jingdong.app.mall.home.floor.view.floating.d G;
    private com.jingdong.app.mall.home.floor.view.floating.c H;
    private com.jingdong.app.mall.home.floor.view.floating.e I;
    private com.jingdong.app.mall.home.floor.view.floating.a J;
    private boolean K;
    private boolean L;
    private boolean M;
    private long N;
    private long O;
    private float P;
    private float Q;

    /* renamed from: g */
    private h f9788g;

    /* renamed from: h */
    private SimpleDraweeView f9789h;

    /* renamed from: i */
    private SimpleDraweeView f9790i;

    /* renamed from: j */
    public int f9791j;

    /* renamed from: k */
    public int f9792k;

    /* renamed from: l */
    private View.OnClickListener f9793l;

    /* renamed from: m */
    private String f9794m;

    /* renamed from: n */
    private int f9795n;
    private int o;
    private int p;
    AtomicBoolean q;
    private AtomicBoolean r;
    private AtomicBoolean s;
    private int t;
    private int u;
    boolean v;
    RelativeLayout w;
    f x;
    f y;
    f z;

    /* loaded from: classes4.dex */
    public class a implements View.OnClickListener {
        a() {
            FloatLayout.this = r1;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            String str = "";
            FloatLayout.this.B = true;
            new com.jingdong.app.mall.home.q.a("\u6d6e\u5c42Icon\u5173\u95ed", FloatLayout.this.f9788g.getJsonString("closeLog")).b();
            try {
                com.jingdong.app.mall.home.r.c.b c2 = com.jingdong.app.mall.home.r.c.b.c(FloatLayout.this.f9788g.getJump() == null ? "" : FloatLayout.this.f9788g.getJump().getSrvJson());
                c2.a("status", FloatLayout.this.J.g());
                str = c2.toString();
            } catch (Exception e2) {
                com.jingdong.app.mall.home.o.a.f.s0(this, e2);
            }
            com.jingdong.app.mall.home.r.c.a.u("Home_FloatingFloorClose", FloatLayout.this.f9788g.h(), str, RecommendMtaUtils.Home_PageId, null, FloatLayout.this.J.g());
            FloatLayout.this.y();
        }
    }

    /* loaded from: classes4.dex */
    public class b implements View.OnClickListener {
        b() {
            FloatLayout.this = r1;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            FloatLayout.this.G(SystemClock.elapsedRealtime());
        }
    }

    /* loaded from: classes4.dex */
    public class c implements View.OnClickListener {
        c() {
            FloatLayout.this = r1;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            FloatLayout.this.G(SystemClock.elapsedRealtime());
        }
    }

    /* loaded from: classes4.dex */
    public class d extends JDSimpleImageLoadingListener {
        d() {
            FloatLayout.this = r1;
        }

        @Override // com.jingdong.app.util.image.listener.JDSimpleImageLoadingListener, com.jingdong.app.util.image.listener.JDImageLoadingListener
        public void onLoadingComplete(String str, View view, Bitmap bitmap) {
            FloatLayout.this.J.o(true, FloatLayout.this.f9789h);
            FloatLayout floatLayout = FloatLayout.this;
            floatLayout.D = floatLayout.f9794m;
            FloatLayout.this.C(false);
            FloatLayout floatLayout2 = FloatLayout.this;
            if (!floatLayout2.A || floatLayout2.f9790i == null) {
                return;
            }
            FloatLayout.this.f9790i.setVisibility(0);
            com.jingdong.app.mall.home.floor.ctrl.e.f(FloatLayout.this.f9788g.getJsonString("closeImg"), FloatLayout.this.f9790i, FloatLayout.this.J.e() ? FloatLayout.S : FloatLayout.R);
        }

        @Override // com.jingdong.app.util.image.listener.JDSimpleImageLoadingListener, com.jingdong.app.util.image.listener.JDImageLoadingListener
        public void onLoadingFailed(String str, View view, JDFailReason jDFailReason) {
            FloatLayout.this.J.o(false, FloatLayout.this.f9789h);
            FloatLayout.this.I();
        }
    }

    /* loaded from: classes4.dex */
    public class e extends com.jingdong.app.mall.home.o.a.b {

        /* renamed from: g */
        final /* synthetic */ boolean f9800g;

        e(boolean z) {
            FloatLayout.this = r1;
            this.f9800g = z;
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        protected void safeRun() {
            FloatLayout floatLayout = FloatLayout.this;
            floatLayout.D(this.f9800g, floatLayout.getWidth(), FloatLayout.this.getHeight());
        }
    }

    static {
        JDDisplayImageOptions resetViewBeforeLoading = com.jingdong.app.mall.home.floor.ctrl.f.a().resetViewBeforeLoading(true);
        int i2 = R.drawable.home_float_close_btn;
        R = resetViewBeforeLoading.showImageOnFail(i2).showImageOnLoading(i2).showImageForEmptyUri(i2);
        JDDisplayImageOptions resetViewBeforeLoading2 = com.jingdong.app.mall.home.floor.ctrl.f.a().resetViewBeforeLoading(true);
        int i3 = R.drawable.home_float_top_close_btn;
        S = resetViewBeforeLoading2.showImageOnFail(i3).showImageOnLoading(i3).showImageForEmptyUri(i3);
        T = 102;
    }

    public FloatLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    private void B(boolean z) {
        this.w.animate().cancel();
        if (z || this.v) {
            this.w.setTranslationX(0.0f);
        }
        this.o = this.x.v() >> 1;
        this.p = this.x.h() >> 1;
        this.J.s(s(), this.f9788g.h(), this);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(this.x.v(), this.x.h());
        layoutParams.leftMargin = this.x.l();
        layoutParams.topMargin = this.x.n();
        this.w.setLayoutParams(layoutParams);
        if (this.w.getParent() == null) {
            addView(this.w);
        }
        if (this.f9790i == null) {
            HomeDraweeView homeDraweeView = new HomeDraweeView(getContext());
            this.f9790i = homeDraweeView;
            homeDraweeView.setContentDescription("\u5173\u95ed\u6d6e\u5c42icon");
            this.f9790i.getHierarchy().setActualImageScaleType(ScalingUtils.ScaleType.FIT_XY);
            this.f9790i.setScaleType(ImageView.ScaleType.FIT_XY);
            this.f9790i.setClickable(true);
        }
        SimpleDraweeView simpleDraweeView = this.f9790i;
        if (simpleDraweeView != null) {
            this.J.h(this.f9788g, simpleDraweeView, this.z);
            this.f9790i.setVisibility(8);
        }
        if (this.f9789h == null) {
            HomeDraweeView homeDraweeView2 = new HomeDraweeView(getContext());
            this.f9789h = homeDraweeView2;
            homeDraweeView2.getHierarchy().setActualImageScaleType(ScalingUtils.ScaleType.FIT_XY);
            this.f9789h.setScaleType(ImageView.ScaleType.FIT_XY);
            this.f9789h.setContentDescription("\u6d6e\u5c42\u6d3b\u52a8");
        }
        A(false);
        this.f9789h.setAlpha(1.0f);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(this.y.v(), this.y.h());
        layoutParams2.topMargin = this.y.n();
        int m2 = this.y.m();
        layoutParams2.rightMargin = m2;
        if (m2 == 0) {
            layoutParams2.addRule(14);
        }
        this.f9789h.setLayoutParams(layoutParams2);
        if (this.f9789h.getParent() == null) {
            this.w.addView(this.f9789h);
        }
        SimpleDraweeView simpleDraweeView2 = this.f9790i;
        if (simpleDraweeView2 != null) {
            m.b(this.w, simpleDraweeView2, this.J.e() ? -1 : 0);
        }
        setVisibility(0);
        this.D = "";
        this.s.set(false);
        com.jingdong.app.mall.home.floor.ctrl.e.q(this.f9789h, this.f9794m, false, new d());
        if (z) {
            return;
        }
        C(false);
    }

    public void D(boolean z, int i2, int i3) {
        if (z || !this.s.getAndSet(true)) {
            P(i2, i3 - com.jingdong.app.mall.home.floor.common.d.d(q(this.f9788g) << 1));
            O(this.v, true);
            setAlpha(1.0f);
        }
    }

    private boolean E(MotionEvent motionEvent) {
        RelativeLayout relativeLayout = this.w;
        if (relativeLayout == null || relativeLayout.getAlpha() < 0.5f || motionEvent == null) {
            return false;
        }
        int v = this.y.v() + this.y.m();
        float translationX = this.w.getTranslationX() + ((v - this.w.getWidth()) / 2.0f);
        float translationY = (this.w.getTranslationY() - this.w.getHeight()) + this.y.n();
        float f2 = translationX - v;
        float h2 = this.y.h() + translationY;
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        if (this.I.j(this.J)) {
            int d2 = com.jingdong.app.mall.home.floor.common.d.d(35);
            translationX -= this.q.get() ? 0.0f : this.y.m();
            if (this.q.get()) {
                float f3 = d2;
                translationY += f3;
                h2 -= f3;
            } else if (x > translationX - d2 && y < this.z.h() + translationY) {
                return false;
            }
        }
        return x > f2 && x < translationX && y > translationY && y < h2;
    }

    public void G(long j2) {
        if (j2 - this.O < 800) {
            return;
        }
        String str = "";
        if (this.q.get()) {
            if (this.J.b()) {
                N(false);
                try {
                    com.jingdong.app.mall.home.r.c.b c2 = com.jingdong.app.mall.home.r.c.b.c(this.f9788g.getJump() == null ? "" : this.f9788g.getJump().getSrvJson());
                    c2.a("isclose", this.A ? "1" : "0");
                    c2.a("status", "A");
                    str = c2.toString();
                } catch (Exception e2) {
                    com.jingdong.app.mall.home.o.a.f.s0(this, e2);
                }
                com.jingdong.app.mall.home.r.c.a.u("Home_FloatingFloor", this.f9788g.h(), str, RecommendMtaUtils.Home_PageId, null, (this.A ? "1" : "0").concat(CartConstant.KEY_YB_INFO_LINK).concat("A"));
                return;
            }
            return;
        }
        this.J.p();
        this.O = j2;
        new com.jingdong.app.mall.home.q.a("\u6d6e\u5c42Icon\u70b9\u51fb", this.f9788g.getJsonString("clkLog")).b();
        l.e(getContext(), this.f9788g.getJump());
        try {
            com.jingdong.app.mall.home.r.c.b c3 = com.jingdong.app.mall.home.r.c.b.c(this.f9788g.getJump() == null ? "" : this.f9788g.getJump().getSrvJson());
            c3.a("isclose", this.A ? "1" : "0");
            c3.a("status", this.J.g());
            str = c3.toString();
        } catch (Exception e3) {
            com.jingdong.app.mall.home.o.a.f.s0(this, e3);
        }
        com.jingdong.app.mall.home.r.c.a.u("Home_FloatingFloor", this.f9788g.h(), str, RecommendMtaUtils.Home_PageId, null, (this.A ? "1" : "0").concat(CartConstant.KEY_YB_INFO_LINK).concat(this.J.g()));
    }

    public void I() {
        this.D = "";
        RelativeLayout relativeLayout = this.w;
        if (relativeLayout != null) {
            relativeLayout.setTranslationX(0.0f);
        }
        SimpleDraweeView simpleDraweeView = this.f9790i;
        if (simpleDraweeView != null) {
            simpleDraweeView.setVisibility(8);
        }
    }

    private void P(float f2, float f3) {
        Q(f2, f3, true);
    }

    private void Q(float f2, float f3, boolean z) {
        if (this.w == null) {
            return;
        }
        int width = getWidth();
        int height = getHeight();
        if (width > 0 && height > 0) {
            float m2 = this.J.m(f2, this.x.v(), width);
            float x = x(f3, this.x.h(), height);
            if (z) {
                this.w.setTranslationX(m2);
            }
            this.w.setTranslationY(x);
            this.E = width;
            return;
        }
        y();
    }

    private boolean R(String str) {
        Object[] split;
        try {
            split = TextUtils.split(com.jingdong.app.mall.home.o.a.f.u("home_float_show_times", ""), "##");
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return ((split.length <= 1 || !str.equals(split[0])) ? 0 : com.jingdong.app.mall.home.o.a.f.t0(split[1], 0)) < this.f9795n;
    }

    private void m() {
        if (!this.L || this.f9788g == null || this.M) {
            return;
        }
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (elapsedRealtime - this.N > 180) {
            return;
        }
        G(elapsedRealtime);
    }

    private void n(String str) {
        try {
            Object[] split = TextUtils.split(com.jingdong.app.mall.home.o.a.f.u("home_float_show_times", ""), "##");
            int i2 = 1;
            if (split.length > 1 && str.equals(split[0])) {
                i2 = 1 + com.jingdong.app.mall.home.o.a.f.t0(split[1], 0);
            }
            com.jingdong.app.mall.home.o.a.f.x0("home_float_show_times", str.concat("##").concat(i2 + ""));
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private void o() {
        boolean a2 = com.jingdong.app.mall.home.v.a.a(getContext());
        this.K = a2;
        SimpleDraweeView simpleDraweeView = this.f9789h;
        if (simpleDraweeView != null) {
            simpleDraweeView.setOnClickListener(a2 ? new b() : null);
        }
        SimpleDraweeView v = this.I.v();
        if (v != null) {
            v.setOnClickListener(this.K ? new c() : null);
        }
    }

    private int q(h hVar) {
        if (hVar == null) {
            return T;
        }
        int jsonInt = hVar.getJsonInt("floatIconPosition", T);
        return jsonInt <= 0 ? T : jsonInt;
    }

    private String w(h hVar) {
        if (hVar == null) {
            return "";
        }
        String jsonString = hVar.getJsonString("moduleId");
        JumpEntity jump = hVar.getJump();
        if (jump == null) {
            return jsonString;
        }
        String str = jsonString + jump.des;
        if (TextUtils.isEmpty(jump.params)) {
            return str;
        }
        try {
            JSONObject jSONObject = new JSONObject(jump.params);
            return jSONObject.has("url") ? str.concat(jSONObject.getString("url")) : str;
        } catch (Exception e2) {
            e2.printStackTrace();
            return str;
        }
    }

    private float x(float f2, float f3, float f4) {
        return Math.min(Math.max(f2, f3), f4);
    }

    public void A(boolean z) {
        if (this.f9790i == null) {
            return;
        }
        boolean j2 = this.I.j(this.J);
        this.f9789h.setClickable((j2 && z) ? false : true);
        this.f9790i.setOnClickListener((j2 && z) ? null : this.f9793l);
    }

    public void C(boolean z) {
        if (this.J.k()) {
            int width = getWidth();
            int height = getHeight();
            if (width > 0 && height > 0 && Looper.myLooper() == Looper.getMainLooper()) {
                D(z, width, height);
            } else {
                postDelayed(new e(z), 50L);
            }
        }
    }

    public void F() {
        if (this.w == null) {
            return;
        }
        float v = this.x.v();
        if (this.w.getTranslationX() - this.o > (getWidth() >> 1)) {
            v = getWidth();
        }
        ViewPropertyAnimator animate = this.w.animate();
        if (Build.VERSION.SDK_INT >= 19) {
            animate.setUpdateListener(null);
        }
        animate.setDuration(160L).translationX(v);
    }

    public void H() {
        setVisibility(0);
        if (this.f9789h != null) {
            o();
        }
    }

    public void J(int i2) {
        if (this.G.j(this.J)) {
            N(this.v);
        }
        this.o = this.x.v() >> 1;
        this.p = this.x.h() >> 1;
        f.c(this.w, this.x);
        f.c(this.f9789h, this.y);
    }

    public void K(boolean z) {
        if (!this.C || this.r.get() == z) {
            return;
        }
        this.J.q(this, this.u, this.r.get());
        this.r.set(z);
    }

    public void L(int i2) {
        if (!this.C || this.F.j(this.J) || this.B) {
            return;
        }
        this.J.q(this, i2, this.r.get());
        this.u = i2;
        if (!this.J.c()) {
            this.t = this.u;
        } else if (Math.abs(this.t - i2) > com.jingdong.app.mall.home.floor.common.d.d(100)) {
            boolean z = true;
            boolean z2 = i2 > this.t;
            if (!this.v && !z2) {
                z = false;
            }
            N(z);
        }
    }

    public void M() {
        this.C = false;
    }

    public void N(boolean z) {
        O(z, false);
    }

    void O(boolean z, boolean z2) {
        this.t = this.u;
        if (this.F.j(this.J) || this.q.get() == z) {
            return;
        }
        this.q.set(z);
        this.J.t(this, getWidth(), z2);
    }

    /* JADX WARN: Code restructure failed: missing block: B:85:0x0017, code lost:
        if (r0 != 3) goto L106;
     */
    @Override // android.view.ViewGroup, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (this.K) {
            return super.dispatchTouchEvent(motionEvent);
        }
        int action = motionEvent.getAction();
        if (action != 0) {
            if (action != 1) {
                if (action == 2) {
                    if (this.L && this.w != null && this.J.k()) {
                        float x = motionEvent.getX();
                        float y = motionEvent.getY();
                        if (this.M && !this.q.get()) {
                            P(x + this.o, y + this.p);
                        } else {
                            this.M = Math.abs(x - this.P) > ((float) com.jingdong.app.mall.home.floor.common.d.d(8)) || Math.abs(y - this.Q) > ((float) com.jingdong.app.mall.home.floor.common.d.d(8));
                        }
                    }
                }
            }
            m();
            this.J.n(this);
            this.L = false;
        } else {
            this.t = this.u;
            this.M = false;
            this.L = E(motionEvent);
            this.N = SystemClock.elapsedRealtime();
            this.P = motionEvent.getX();
            this.Q = motionEvent.getY();
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    @Override // android.widget.RelativeLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        super.onLayout(z, i2, i3, i4, i5);
        if (z && this.E == i4 && getVisibility() == 0 && this.w != null && this.J.k()) {
            Q(this.w.getTranslationX(), this.w.getTranslationY(), false);
        }
    }

    public void p() {
        if (this.C) {
            return;
        }
        y();
    }

    public SimpleDraweeView r() {
        return this.f9790i;
    }

    public String s() {
        h hVar = this.f9788g;
        return hVar == null ? "" : hVar.getJsonString("expoLog");
    }

    public com.jingdong.app.mall.home.r.c.b t() {
        try {
            String f2 = this.f9788g.f();
            if (TextUtils.isEmpty(f2)) {
                return null;
            }
            return com.jingdong.app.mall.home.r.c.b.c(f2);
        } catch (Exception e2) {
            com.jingdong.app.mall.home.o.a.f.s0(this, e2);
            return null;
        }
    }

    public RelativeLayout u() {
        return this.w;
    }

    public SimpleDraweeView v() {
        return this.f9789h;
    }

    public void y() {
        setVisibility(8);
        RelativeLayout relativeLayout = this.w;
        if (relativeLayout != null) {
            removeView(relativeLayout);
            this.w.setTranslationX(0.0f);
        }
    }

    public void z(h hVar, t tVar) {
        if (hVar != null && !TextUtils.isEmpty(hVar.y) && !this.B) {
            com.jingdong.app.mall.home.v.c.a.a(this);
            this.q.set(false);
            this.t = 0;
            this.f9788g = hVar;
            this.f9795n = hVar.getJsonInt("floatIconDisplay", 2);
            this.f9794m = hVar.y;
            this.A = "1".equals(hVar.getJsonString("closeSwitch"));
            String jsonString = hVar.getJsonString("displayUIStyle", "");
            this.f9791j = hVar.getJsonInt("floatIconWidth", 120);
            this.f9792k = hVar.getJsonInt("floatIconHeight", 120);
            this.J.r(this);
            this.v = false;
            this.r.set(false);
            boolean z = t.FLOAT_NEW == tVar;
            boolean equals = "3".equals(jsonString);
            this.A = this.A || z;
            this.J = z ? this.I : this.F;
            if (!"1".equals(jsonString) && !equals) {
                if ("2".equals(jsonString)) {
                    this.J = z ? this.I : this.H;
                }
            } else {
                this.J = z ? this.I : this.G;
                this.v = equals;
            }
            if (this.J.l(jsonString)) {
                com.jingdong.app.mall.home.o.a.d.j();
            }
            setAlpha(this.v ? 0.0f : 1.0f);
            String md5 = Md5Encrypt.md5(w(hVar));
            this.J.i(hVar, this);
            if (this.J.d(md5) && R(md5)) {
                this.C = true;
                n(md5);
                B(!TextUtils.equals(this.D, this.f9794m));
                ArrayList<String> arrayList = new ArrayList<>();
                arrayList.add(hVar.C);
                com.jingdong.app.mall.home.r.c.a.i().e(true, arrayList, null);
                o();
                return;
            }
            y();
            return;
        }
        y();
    }

    public FloatLayout(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.f9793l = new a();
        this.f9795n = 2;
        this.q = new AtomicBoolean(false);
        this.r = new AtomicBoolean(false);
        this.s = new AtomicBoolean(false);
        this.x = new f(100, R2.anim.lib_cashier_sdk_pop_out_animation_bottom);
        this.y = new f(100, 100);
        this.z = new f(82, 42);
        this.F = new com.jingdong.app.mall.home.floor.view.floating.b();
        this.G = new com.jingdong.app.mall.home.floor.view.floating.d();
        this.H = new com.jingdong.app.mall.home.floor.view.floating.c();
        this.I = new com.jingdong.app.mall.home.floor.view.floating.e();
        this.J = this.F;
        this.L = false;
        this.M = false;
        this.w = new RelativeLayout(context);
    }
}
