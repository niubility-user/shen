package com.jingdong.app.mall.home.pullrefresh;

import android.animation.Animator;
import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.jingdong.app.mall.home.R;
import com.jingdong.app.mall.home.floor.animation.lottie.LottieAnimationViewCatchDraw;
import com.jingdong.app.mall.home.floor.common.d;
import com.jingdong.app.mall.home.floor.common.f;
import com.jingdong.app.mall.home.o.a.k;
import com.jingdong.app.mall.home.o.a.l;
import com.jingdong.app.mall.home.pullrefresh.BaseVerticalRefresh;
import com.jingdong.corelib.utils.Log;

/* loaded from: classes4.dex */
public class JDBaseLoadingView extends BaseLoadingView {
    private static boolean A = l.p();

    /* renamed from: h */
    private boolean f10566h;

    /* renamed from: i */
    private boolean f10567i;

    /* renamed from: j */
    protected String f10568j;

    /* renamed from: k */
    protected int f10569k;

    /* renamed from: l */
    protected int f10570l;

    /* renamed from: m */
    private boolean f10571m;

    /* renamed from: n */
    protected RelativeLayout f10572n;
    protected RelativeLayout o;
    protected RelativeLayout p;
    protected LottieAnimationViewCatchDraw q;
    protected LottieAnimationViewCatchDraw r;
    protected TextView s;
    protected f t;
    protected f u;
    protected f v;
    protected CharSequence w;
    protected CharSequence x;
    protected CharSequence y;
    protected CharSequence z;

    /* loaded from: classes4.dex */
    public class a implements Animator.AnimatorListener {
        a() {
            JDBaseLoadingView.this = r1;
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            if (Log.D) {
                Log.d("JDBaseLoadingView", "\u4e0b\u62c9\u52a8\u753b\u7ed3\u675f \u5f00\u542f\u5237\u65b0\u52a8\u753b ");
            }
            LottieAnimationViewCatchDraw lottieAnimationViewCatchDraw = JDBaseLoadingView.this.q;
            if (lottieAnimationViewCatchDraw != null) {
                lottieAnimationViewCatchDraw.cancelAnimation();
                JDBaseLoadingView.this.q.setVisibility(8);
            }
            LottieAnimationViewCatchDraw lottieAnimationViewCatchDraw2 = JDBaseLoadingView.this.r;
            if (lottieAnimationViewCatchDraw2 != null) {
                lottieAnimationViewCatchDraw2.setVisibility(0);
                try {
                    JDBaseLoadingView.this.r.setProgress(0.0f);
                    JDBaseLoadingView.this.r.playAnimation();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
        }
    }

    /* loaded from: classes4.dex */
    public static /* synthetic */ class b {
        static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[BaseVerticalRefresh.g.values().length];
            a = iArr;
            try {
                iArr[BaseVerticalRefresh.g.PULL_FROM_END.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[BaseVerticalRefresh.g.PULL_FROM_START.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public JDBaseLoadingView(Context context, BaseVerticalRefresh.g gVar) {
        super(context);
        this.f10566h = true;
        this.f10567i = false;
        this.f10569k = -1;
        this.f10570l = -1;
        this.f10571m = true;
        this.f10543g = gVar;
        p(context);
        try {
            q();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        o();
        k();
    }

    private void q() {
        if (r()) {
            String o = k.o("refresh/refresh_start_white.json");
            String o2 = k.o("refresh/refresh_loading_white.json");
            if (TextUtils.isEmpty(o) || TextUtils.isEmpty(o2)) {
                return;
            }
            boolean isValid = this.r.isValid(o2);
            if (this.q.isValid(o) && isValid) {
                this.r.setLottieJson(o2, "REFRESH_LOTTIE_HOME");
                this.r.loop(true);
                this.r.setVisibility(8);
                this.q.setLottieJson(o, "PULL_LOTTIE_HOME");
                this.q.setProgress(0.0f);
                this.q.setVisibility(0);
                this.q.addAnimatorListener(new a());
                return;
            }
            this.r.setVisibility(8);
            this.q.setVisibility(8);
        }
    }

    @Override // com.jingdong.app.mall.home.pullrefresh.BaseLoadingView
    public int a() {
        RelativeLayout relativeLayout;
        if (b.a[this.f10543g.ordinal()] == 2 && (relativeLayout = this.o) != null) {
            return relativeLayout.getHeight();
        }
        return d.d(80);
    }

    @Override // com.jingdong.app.mall.home.pullrefresh.BaseLoadingView
    public void b(float f2) {
        this.f10567i = false;
    }

    @Override // com.jingdong.app.mall.home.pullrefresh.BaseLoadingView
    public void c(float f2) {
    }

    @Override // com.jingdong.app.mall.home.pullrefresh.BaseLoadingView
    public void d(float f2, boolean z, boolean z2) {
    }

    @Override // com.jingdong.app.mall.home.pullrefresh.BaseLoadingView
    public void e(int i2) {
    }

    @Override // com.jingdong.app.mall.home.pullrefresh.BaseLoadingView
    public void f(int i2, int i3) {
        if (r()) {
            if (this.f10571m && i3 > 0) {
                this.f10571m = false;
                try {
                    this.q.cancelAnimation();
                    this.q.setProgress(0.0f);
                } catch (Exception unused) {
                }
                this.q.setVisibility(0);
            }
            if (i3 == 0) {
                this.f10566h = true;
                this.f10571m = true;
                try {
                    this.q.cancelAnimation();
                    this.r.cancelAnimation();
                } catch (Exception unused2) {
                }
                this.r.setVisibility(8);
            }
            try {
                if (i3 < d.d(35) || this.q.isAnimating() || !this.f10566h) {
                    return;
                }
                this.q.setProgress(0.0f);
                this.q.playAnimation();
                this.f10566h = false;
            } catch (Exception unused3) {
            }
        }
    }

    @Override // com.jingdong.app.mall.home.pullrefresh.BaseLoadingView
    public void g() {
        this.f10567i = false;
        u(this.w);
    }

    @Override // com.jingdong.app.mall.home.pullrefresh.BaseLoadingView
    public void h() {
        Log.d("JDBaseLoadingView", "refresh complete");
        this.f10567i = true;
        if (!s()) {
            u(this.z);
        }
        if (r()) {
            try {
                this.q.cancelAnimation();
                this.r.cancelAnimation();
            } catch (Exception unused) {
            }
        }
    }

    @Override // com.jingdong.app.mall.home.pullrefresh.BaseLoadingView
    public synchronized boolean i(boolean z) {
        u(this.x);
        t(d.d(22));
        return false;
    }

    @Override // com.jingdong.app.mall.home.pullrefresh.BaseLoadingView
    public void j() {
        this.f10567i = false;
        u(this.y);
    }

    @Override // com.jingdong.app.mall.home.pullrefresh.BaseLoadingView
    public void k() {
        if (!this.f10567i && r()) {
            this.q.setVisibility(0);
            this.r.setVisibility(8);
            try {
                this.q.cancelAnimation();
                this.r.cancelAnimation();
            } catch (Exception unused) {
                this.f10567i = false;
            }
        }
        this.f10567i = false;
    }

    @Override // com.jingdong.app.mall.home.pullrefresh.BaseLoadingView
    public void l(int i2) {
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        if (layoutParams != null) {
            layoutParams.height = i2;
            requestLayout();
        }
    }

    @Override // com.jingdong.app.mall.home.pullrefresh.BaseLoadingView
    public void n(BaseVerticalRefresh.l lVar) {
    }

    protected void o() {
        if (b.a[this.f10543g.ordinal()] != 1) {
            this.w = getResources().getString(R.string.pull_to_refresh_header_hint_home_pull_refresh_800);
            this.x = getResources().getString(R.string.pull_to_refresh_header_hint_home_loading_800);
            this.y = getResources().getString(R.string.pull_to_refresh_header_hint_home_release_refresh_800);
            this.z = getResources().getString(R.string.pull_to_refresh_header_hint_home_complete_800);
            return;
        }
        this.w = getResources().getString(com.jingdong.common.R.string.pull_to_refresh_header_hint_normal2);
        this.x = getResources().getString(com.jingdong.common.R.string.pull_to_refresh_header_hint_loading);
        this.y = getResources().getString(com.jingdong.common.R.string.pull_to_refresh_header_hint_ready);
    }

    public void p(Context context) {
        if (context == null) {
            return;
        }
        setBackgroundColor(0);
        RelativeLayout relativeLayout = new RelativeLayout(context);
        this.f10572n = relativeLayout;
        relativeLayout.setBackgroundColor(0);
        addView(this.f10572n, new FrameLayout.LayoutParams(-1, -1));
        this.o = new RelativeLayout(context);
        RelativeLayout.LayoutParams u = new f(-1, -2).u(this.o);
        u.addRule(12);
        this.f10572n.addView(this.o, u);
        RelativeLayout relativeLayout2 = new RelativeLayout(context);
        this.p = relativeLayout2;
        relativeLayout2.setId(R.id.home_pull_anim);
        f fVar = new f(-1, 60);
        this.t = fVar;
        fVar.F(new Rect(0, 96, 0, 0));
        RelativeLayout relativeLayout3 = this.o;
        RelativeLayout relativeLayout4 = this.p;
        relativeLayout3.addView(relativeLayout4, this.t.u(relativeLayout4));
        if (Build.VERSION.SDK_INT >= 21) {
            this.q = new LottieAnimationViewCatchDraw(context);
            f fVar2 = new f(84, -1);
            this.u = fVar2;
            RelativeLayout.LayoutParams u2 = fVar2.u(this.q);
            u2.addRule(14);
            this.p.addView(this.q, u2);
            LottieAnimationViewCatchDraw lottieAnimationViewCatchDraw = new LottieAnimationViewCatchDraw(context);
            this.r = lottieAnimationViewCatchDraw;
            lottieAnimationViewCatchDraw.setVisibility(8);
            f fVar3 = new f(84, -1);
            this.v = fVar3;
            RelativeLayout.LayoutParams u3 = fVar3.u(this.r);
            u3.addRule(14);
            this.p.addView(this.r, u3);
        }
        TextView textView = new TextView(context);
        this.s = textView;
        textView.setTextColor(this.f10570l);
        this.s.setTextSize(0, d.d(22));
        RelativeLayout.LayoutParams u4 = new f(-2, -2).u(this.s);
        u4.addRule(3, this.p.getId());
        u4.addRule(14);
        this.o.addView(this.s, u4);
    }

    public boolean r() {
        return (A || Build.VERSION.SDK_INT < 21 || this.q == null || this.r == null) ? false : true;
    }

    protected boolean s() {
        return false;
    }

    public void t(int i2) {
        TextView textView = this.s;
        if (textView != null) {
            textView.setTextSize(0, i2);
        }
    }

    public void u(CharSequence charSequence) {
        TextView textView = this.s;
        if (textView == null || TextUtils.equals(charSequence, textView.getText())) {
            return;
        }
        this.s.setText(charSequence);
        com.jingdong.app.mall.home.a.s.g(TextUtils.equals(charSequence, this.w) || TextUtils.equals(charSequence, this.z));
    }
}
