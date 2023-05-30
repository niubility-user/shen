package com.jingdong.app.mall.home.pulltorefresh;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.os.Build;
import android.text.TextUtils;
import android.util.Pair;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.jingdong.app.mall.home.R;
import com.jingdong.app.mall.home.floor.common.i.m;
import com.jingdong.app.mall.home.floor.ctrl.guide.PullXViewGuideLayout;
import com.jingdong.app.mall.home.floor.ctrl.h;
import com.jingdong.app.mall.home.floor.ctrl.t.i;
import com.jingdong.app.mall.home.floor.ctrl.t.j;
import com.jingdong.app.mall.home.floor.ctrl.t.p;
import com.jingdong.app.mall.home.o.a.f;
import com.jingdong.app.mall.home.pullrefresh.BaseVerticalRefresh;
import com.jingdong.app.mall.home.pullrefresh.JDBaseLoadingView;
import com.jingdong.app.mall.home.widget.HomeTopBgView;
import com.jingdong.common.entity.JumpEntity;

/* loaded from: classes4.dex */
public class JDHomeBaseLoadingView extends JDBaseLoadingView {
    private static int O = 32;
    public static int P = 10;
    private static Pair<String, Bitmap> Q;
    protected JumpEntity B;
    protected String C;
    protected String D;
    protected boolean E;
    private float F;
    private com.jingdong.app.mall.home.pulltorefresh.b G;
    private BaseVerticalRefresh.l H;
    protected int I;
    private boolean J;
    protected HomeTopBgView K;
    protected PullXViewGuideLayout L;
    ValueAnimator M;
    AccelerateInterpolator N;

    /* loaded from: classes4.dex */
    public class a implements ValueAnimator.AnimatorUpdateListener {
        a() {
            JDHomeBaseLoadingView.this = r1;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            JDHomeBaseLoadingView.this.setAlpha(((Float) valueAnimator.getAnimatedValue()).floatValue());
        }
    }

    /* loaded from: classes4.dex */
    public class b extends com.jingdong.app.mall.home.o.a.b {
        b() {
            JDHomeBaseLoadingView.this = r1;
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        public void safeRun() {
            JDHomeBaseLoadingView.this.M(null);
            JDHomeBaseLoadingView jDHomeBaseLoadingView = JDHomeBaseLoadingView.this;
            ((JDBaseLoadingView) jDHomeBaseLoadingView).f10570l = ((JDBaseLoadingView) jDHomeBaseLoadingView).f10569k;
            JDHomeBaseLoadingView.this.H(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class c extends com.jingdong.app.mall.home.t.a {
        final /* synthetic */ JumpEntity a;
        final /* synthetic */ String b;

        c(JumpEntity jumpEntity, String str) {
            JDHomeBaseLoadingView.this = r1;
            this.a = jumpEntity;
            this.b = str;
        }

        @Override // com.jingdong.app.mall.home.t.a
        public void onBitmapWithUiNull(Bitmap bitmap) {
            if (bitmap != null && !bitmap.isRecycled()) {
                JDHomeBaseLoadingView.this.I(this.a, bitmap);
                Pair unused = JDHomeBaseLoadingView.Q = new Pair(this.b, bitmap);
                return;
            }
            JDHomeBaseLoadingView.this.U();
        }
    }

    /* loaded from: classes4.dex */
    public class d extends com.jingdong.app.mall.home.o.a.b {

        /* renamed from: g */
        final /* synthetic */ JumpEntity f10577g;

        /* renamed from: h */
        final /* synthetic */ Bitmap f10578h;

        d(JumpEntity jumpEntity, Bitmap bitmap) {
            JDHomeBaseLoadingView.this = r1;
            this.f10577g = jumpEntity;
            this.f10578h = bitmap;
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        public void safeRun() {
            JDHomeBaseLoadingView jDHomeBaseLoadingView = JDHomeBaseLoadingView.this;
            if (jDHomeBaseLoadingView.K == null) {
                jDHomeBaseLoadingView.D();
            }
            JDHomeBaseLoadingView.this.P();
            JDHomeBaseLoadingView jDHomeBaseLoadingView2 = JDHomeBaseLoadingView.this;
            jDHomeBaseLoadingView2.B = this.f10577g;
            jDHomeBaseLoadingView2.M(this.f10578h);
            JDHomeBaseLoadingView.this.O(false);
        }
    }

    public JDHomeBaseLoadingView(Context context, BaseVerticalRefresh.g gVar) {
        super(context, gVar);
        this.B = null;
        this.E = false;
        this.J = true;
        this.K = null;
        this.M = new ValueAnimator();
        this.N = new AccelerateInterpolator();
        E();
        this.y = getResources().getString(R.string.pull_to_refresh_header_hint_home_release_refresh_800);
    }

    private void C() {
        O(!s());
    }

    private void E() {
        this.M.addUpdateListener(new a());
        setAlpha(0.0f);
    }

    private void G() {
        int d2 = com.jingdong.app.mall.home.floor.common.d.d(O);
        RelativeLayout relativeLayout = this.o;
        if (relativeLayout == null || relativeLayout.getPaddingBottom() == d2) {
            return;
        }
        this.o.setPadding(0, 0, 0, d2);
    }

    public void H(boolean z) {
        TextView textView = this.s;
        if (textView != null) {
            textView.setTextColor(z ? this.f10569k : this.f10570l);
        }
    }

    public void I(JumpEntity jumpEntity, Bitmap bitmap) {
        if (bitmap != null && !bitmap.isRecycled() && this.f10543g == BaseVerticalRefresh.g.PULL_FROM_START) {
            f.E0(new d(jumpEntity, bitmap));
        } else {
            U();
        }
    }

    private void J(int i2) {
        if (this.G == null) {
            this.G = new com.jingdong.app.mall.home.pulltorefresh.b();
        }
        this.G.g(this, this.F, i2);
        this.G.i();
    }

    public void O(boolean z) {
        RelativeLayout relativeLayout = this.p;
        if (relativeLayout != null) {
            relativeLayout.setVisibility(z ? 0 : 4);
        }
        O = h.H(z);
        G();
    }

    private void T(float f2, long j2) {
        if (f2 == 0.0f && this.M.isRunning()) {
            return;
        }
        float alpha = getAlpha();
        if (Math.abs(f2 - alpha) != 0.0f && j2 > 0) {
            if (Build.VERSION.SDK_INT >= 22) {
                this.M.setInterpolator(this.N);
            }
            this.M.setDuration(((float) j2) * r2);
            this.M.setFloatValues(alpha, f2);
            this.M.start();
            return;
        }
        setAlpha(f2);
    }

    public void B() {
        t(com.jingdong.app.mall.home.floor.common.d.d(22));
        com.jingdong.app.mall.home.floor.common.f.c(this.p, this.t);
        if (r()) {
            com.jingdong.app.mall.home.floor.common.f.c(this.q, this.u);
            com.jingdong.app.mall.home.floor.common.f.c(this.r, this.v);
        }
        G();
    }

    protected void D() {
    }

    public void F(boolean z) {
        this.J = z;
    }

    public void K(int i2) {
        this.I = i2;
    }

    public void L(int i2) {
        int d2 = com.jingdong.app.mall.home.floor.common.d.d(this.E ? 120 : 70);
        float f2 = (d2 - (i2 > d2 ? 0 : d2 - i2)) / d2;
        if (!this.J) {
            f2 = 0.0f;
        }
        BaseVerticalRefresh.l lVar = BaseVerticalRefresh.l.MANUAL_REFRESHING;
        BaseVerticalRefresh.l lVar2 = this.H;
        if (lVar == lVar2 || BaseVerticalRefresh.l.REFRESHING == lVar2) {
            f2 = 1.0f;
        }
        int d3 = com.jingdong.app.mall.home.floor.common.d.d(130);
        if (BaseVerticalRefresh.l.RESET == this.H && i2 < d3) {
            T(0.0f, 240L);
            return;
        }
        this.M.cancel();
        setAlpha(f2);
    }

    protected void M(Bitmap bitmap) {
        try {
            this.E = false;
            HomeTopBgView homeTopBgView = this.K;
            Bitmap bitmap2 = null;
            if (homeTopBgView != null) {
                Bitmap c2 = homeTopBgView.c();
                if (bitmap != null) {
                    N(bitmap);
                    this.E = true;
                } else {
                    this.K.g(null, null, -1);
                }
                this.K.invalidate();
                bitmap2 = c2;
            }
            if (bitmap2 != null && bitmap2 != bitmap && !bitmap2.isRecycled()) {
                bitmap2.recycle();
            }
            if (this.L == null || !this.E || this.B == null) {
                return;
            }
            i.p().n(1).f();
        } catch (Exception unused) {
        }
    }

    public void N(Bitmap bitmap) {
        HomeTopBgView homeTopBgView = this.K;
        if (homeTopBgView == null) {
            return;
        }
        ViewGroup.LayoutParams layoutParams = homeTopBgView.getLayoutParams();
        f.n(layoutParams);
        FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) layoutParams;
        int u = m.u();
        if (layoutParams2 != null) {
            layoutParams2.width = -1;
            layoutParams2.height = u;
            layoutParams2.topMargin = (-u) + h.Q();
            this.K.setLayoutParams(layoutParams2);
        }
        Matrix matrix = new Matrix();
        int width = bitmap.getWidth();
        float f2 = com.jingdong.app.mall.home.floor.common.d.f9279g / width;
        matrix.setTranslate(0.0f, -((bitmap.getHeight() * f2) - u));
        matrix.preScale(f2, f2);
        this.K.g(bitmap, matrix, com.jingdong.app.mall.home.floor.common.d.d(P));
    }

    protected void P() {
        this.w = getResources().getString(R.string.pull_to_refresh_header_hint_home_loading);
        this.y = getResources().getString(R.string.pull_to_refresh_header_hint_home_releaseloading);
        this.C = getResources().getString(R.string.pull_to_refresh_header_hint_home_pullmore);
        this.D = getResources().getString(R.string.pull_to_refresh_header_hint_home_pullmore_releaseloading);
        H(false);
    }

    public void Q(int i2) {
        this.f10569k = i2;
        if (TextUtils.isEmpty(this.f10568j)) {
            H(true);
        }
    }

    public boolean R(String str) {
        this.f10568j = str;
        this.f10570l = this.f10569k;
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        int[] iArr = {this.f10569k};
        boolean k2 = m.k(str, iArr);
        if (k2) {
            this.f10570l = iArr[0];
        }
        return k2;
    }

    public void S(String str, JumpEntity jumpEntity) {
        if (!TextUtils.isEmpty(str)) {
            Pair<String, Bitmap> pair = Q;
            if (pair != null && TextUtils.equals(str, (CharSequence) pair.first) && !((Bitmap) Q.second).isRecycled()) {
                I(jumpEntity, (Bitmap) Q.second);
                return;
            } else {
                com.jingdong.app.mall.home.floor.ctrl.f.i(str, new c(jumpEntity, str));
                return;
            }
        }
        U();
    }

    protected void U() {
        this.B = null;
        if (this.E) {
            f.E0(new b());
        }
    }

    @Override // com.jingdong.app.mall.home.pullrefresh.JDBaseLoadingView, com.jingdong.app.mall.home.pullrefresh.BaseLoadingView
    public int a() {
        RelativeLayout relativeLayout;
        if (this.f10543g == BaseVerticalRefresh.g.PULL_FROM_START && (relativeLayout = this.o) != null) {
            return relativeLayout.getHeight();
        }
        return super.a();
    }

    @Override // com.jingdong.app.mall.home.pullrefresh.JDBaseLoadingView, com.jingdong.app.mall.home.pullrefresh.BaseLoadingView
    public void d(float f2, boolean z, boolean z2) {
        this.F = f2;
        t(com.jingdong.app.mall.home.floor.common.d.d(22));
        if (z && s()) {
            u(!z2 ? this.C : this.D);
        } else {
            u(f2 < ((float) a()) ? this.w : this.y);
        }
    }

    @Override // com.jingdong.app.mall.home.pullrefresh.JDBaseLoadingView, com.jingdong.app.mall.home.pullrefresh.BaseLoadingView
    public void f(int i2, int i3) {
        int i4 = this.I;
        int i5 = i3 < i4 ? 0 : i3 - i4;
        L(i5);
        super.f(i2, i5);
    }

    @Override // com.jingdong.app.mall.home.pullrefresh.JDBaseLoadingView, com.jingdong.app.mall.home.pullrefresh.BaseLoadingView
    public void g() {
        super.g();
        C();
    }

    @Override // com.jingdong.app.mall.home.pullrefresh.JDBaseLoadingView, com.jingdong.app.mall.home.pullrefresh.BaseLoadingView
    public synchronized boolean i(boolean z) {
        TextView textView;
        C();
        if (s() && this.B != null && (textView = this.s) != null && textView.getText() != null && !z && this.s.getText().toString().equals(this.D)) {
            j n2 = i.p().n(1);
            if (n2 instanceof p) {
                p pVar = (p) n2;
                if (pVar.j() != null && pVar.r()) {
                    if (pVar.j().animationTime >= 200) {
                        J(pVar.j().animationTime);
                    } else {
                        pVar.b();
                    }
                    return true;
                }
                return true;
            }
            return false;
        }
        super.i(z);
        return false;
    }

    @Override // com.jingdong.app.mall.home.pullrefresh.JDBaseLoadingView, com.jingdong.app.mall.home.pullrefresh.BaseLoadingView
    public void j() {
        super.j();
    }

    @Override // com.jingdong.app.mall.home.pullrefresh.JDBaseLoadingView, com.jingdong.app.mall.home.pullrefresh.BaseLoadingView
    public void k() {
        com.jingdong.app.mall.home.pulltorefresh.b bVar = this.G;
        if (bVar != null) {
            bVar.f();
        }
        if (!s()) {
            super.k();
        }
        t(com.jingdong.app.mall.home.floor.common.d.d(22));
    }

    @Override // com.jingdong.app.mall.home.pullrefresh.JDBaseLoadingView, com.jingdong.app.mall.home.pullrefresh.BaseLoadingView
    public void n(BaseVerticalRefresh.l lVar) {
        super.n(lVar);
        if (BaseVerticalRefresh.l.RESET != lVar) {
            this.J = true;
        }
        this.H = lVar;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.home.pullrefresh.JDBaseLoadingView
    public void p(Context context) {
        super.p(context);
        RelativeLayout relativeLayout = this.f10572n;
        if (relativeLayout != null) {
            relativeLayout.setBackgroundColor(0);
        }
        setBackgroundColor(0);
        G();
        this.f10570l = this.f10569k;
        H(true);
    }

    @Override // com.jingdong.app.mall.home.pullrefresh.JDBaseLoadingView
    protected boolean s() {
        return false;
    }

    @Override // android.view.View
    public void setAlpha(float f2) {
        super.setAlpha(f2);
        HomeTopBgView homeTopBgView = this.K;
        if (homeTopBgView != null) {
            homeTopBgView.setAlpha(f2);
        }
    }

    @Override // android.view.View
    public void setVisibility(int i2) {
        if (getVisibility() == i2) {
            return;
        }
        if (getVisibility() != 0) {
            super.setVisibility(0);
        }
        if (i2 != 0) {
            T(0.0f, 240L);
        } else {
            this.M.cancel();
        }
    }
}
