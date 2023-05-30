package com.jingdong.app.mall.home.floor.bubble;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.app.mall.home.R;
import com.jingdong.app.mall.home.base.HomeDraweeView;
import com.jingdong.app.mall.home.floor.bubble.a;
import com.jingdong.app.mall.home.floor.ctrl.f;
import com.jingdong.jdsdk.utils.DPIUtil;
import com.jingdong.jdsdk.utils.FontsUtil;
import com.jingdong.wireless.iconfont.widget.IconImageView;

/* loaded from: classes4.dex */
public class FloatBubbleLayout extends RelativeLayout {

    /* renamed from: g */
    private int f9231g;

    /* renamed from: h */
    private SimpleDraweeView f9232h;

    /* renamed from: i */
    private SimpleDraweeView f9233i;

    /* renamed from: j */
    private TextView f9234j;

    /* renamed from: k */
    private IconImageView f9235k;

    /* renamed from: l */
    private AnimatorSet f9236l;

    /* renamed from: m */
    private AnimatorSet f9237m;

    /* renamed from: n */
    private a.C0284a f9238n;
    private boolean o;
    private int p;
    private final Context q;
    private final com.jingdong.app.mall.home.floor.bubble.b r;
    private Paint s;

    /* loaded from: classes4.dex */
    public class a implements View.OnClickListener {
        a() {
            FloatBubbleLayout.this = r1;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            FloatBubbleLayout.this.h();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class b extends e {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        b() {
            super(null);
            FloatBubbleLayout.this = r1;
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            if (this.f9243g) {
                return;
            }
            FloatBubbleLayout.this.r.o();
        }

        @Override // com.jingdong.app.mall.home.floor.bubble.FloatBubbleLayout.e, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            super.onAnimationStart(animator);
            FloatBubbleLayout.this.setVisibility(0);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class c extends e {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        c() {
            super(null);
            FloatBubbleLayout.this = r1;
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            if (this.f9243g) {
                return;
            }
            FloatBubbleLayout.this.setVisibility(8);
            FloatBubbleLayout.this.r.n(false, false);
        }
    }

    /* loaded from: classes4.dex */
    public class d implements View.OnClickListener {
        d() {
            FloatBubbleLayout.this = r1;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            FloatBubbleLayout.this.i();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public static class e implements Animator.AnimatorListener {

        /* renamed from: g */
        protected boolean f9243g;

        private e() {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            this.f9243g = true;
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            this.f9243g = false;
        }

        /* synthetic */ e(a aVar) {
            this();
        }
    }

    public FloatBubbleLayout(Context context) {
        this(context, null);
    }

    private void l() {
        if (this.f9232h != null) {
            return;
        }
        int dip2px = DPIUtil.dip2px(30.0f) >> 1;
        int dip2px2 = DPIUtil.dip2px(3.0f);
        boolean z = Build.VERSION.SDK_INT >= 21;
        float[] fArr = new float[8];
        fArr[0] = z ? dip2px : dip2px2;
        fArr[1] = z ? dip2px : dip2px2;
        float f2 = dip2px;
        fArr[2] = f2;
        fArr[3] = f2;
        fArr[4] = f2;
        fArr[5] = f2;
        float f3 = dip2px2;
        fArr[6] = f3;
        fArr[7] = f3;
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setStroke(DPIUtil.dip2px(0.6f) + 1, -1711276033);
        gradientDrawable.setColor(this.f9231g);
        gradientDrawable.setCornerRadii(fArr);
        setBackgroundDrawable(gradientDrawable);
        int dip2px3 = DPIUtil.dip2px(24.0f);
        HomeDraweeView homeDraweeView = new HomeDraweeView(this.q);
        this.f9232h = homeDraweeView;
        homeDraweeView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        e(this.f9232h, dip2px3 >> 1);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(dip2px3, dip2px3);
        layoutParams.addRule(15);
        int dip2px4 = DPIUtil.dip2px(6.0f) >> 1;
        layoutParams.setMargins(dip2px4, dip2px4, dip2px4, dip2px4);
        HomeDraweeView homeDraweeView2 = new HomeDraweeView(this.q);
        this.f9233i = homeDraweeView2;
        homeDraweeView2.setAlpha(0.001f);
        addView(this.f9233i, layoutParams);
        addView(this.f9232h, layoutParams);
        TextView textView = new TextView(this.q);
        this.f9234j = textView;
        textView.setTypeface(FontsUtil.getTypeFace(this.q));
        this.f9234j.setTextColor(-1);
        this.f9234j.setId(R.id.mallfloor_item1);
        this.f9234j.setMaxLines(1);
        this.f9234j.setEllipsize(TextUtils.TruncateAt.END);
        this.f9234j.setMaxWidth(DPIUtil.dip2px(220.0f));
        this.f9234j.setGravity(16);
        this.f9234j.setTextSize(0, DPIUtil.dip2px(12.0f));
        this.f9234j.setPadding(DPIUtil.dip2px(3.0f), 0, DPIUtil.dip2px(25.0f), 0);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, DPIUtil.dip2px(24.0f));
        layoutParams2.leftMargin = DPIUtil.dip2px(30.0f);
        layoutParams2.addRule(15);
        addView(this.f9234j, layoutParams2);
        IconImageView iconImageView = new IconImageView(this.q);
        this.f9235k = iconImageView;
        iconImageView.setColor(-1);
        this.f9235k.setResCode(com.jingdong.common.R.string.jdif_common_guanbi);
        int dip2px5 = DPIUtil.dip2px(24.0f);
        int dip2px6 = (dip2px5 - DPIUtil.dip2px(9.0f)) >> 1;
        this.f9235k.setPadding(dip2px6, dip2px6, dip2px6, dip2px6);
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(dip2px5, dip2px5);
        layoutParams3.addRule(7, this.f9234j.getId());
        layoutParams3.addRule(15);
        addView(this.f9235k, layoutParams3);
        setVisibility(8);
        d(false, n());
        setOnClickListener(new a());
    }

    public void b() {
        this.r.g();
    }

    protected boolean c() {
        return true;
    }

    public void d(boolean z, boolean z2) {
        TextView textView = this.f9234j;
        if (textView != null) {
            textView.setTextColor(z2 ? -1250068 : -1);
        }
        IconImageView iconImageView = this.f9235k;
        if (iconImageView != null) {
            iconImageView.setColor(z2 ? -1250068 : -1);
            this.f9235k.setResCode(z ? com.jingdong.common.R.string.jdif_common_guanbi : com.jingdong.common.R.string.jdif_common_xiangyoujiantou);
        }
        if (this.o == z) {
            return;
        }
        this.o = z;
        IconImageView iconImageView2 = this.f9235k;
        if (iconImageView2 != null) {
            iconImageView2.setOnClickListener(z ? new d() : null);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        if (this.f9232h == null || Build.VERSION.SDK_INT < 21) {
            return;
        }
        canvas.drawCircle((r0.getLeft() + this.f9232h.getRight()) >> 1, getHeight() >> 1, DPIUtil.dip2px(24.0f) >> 1, this.s);
    }

    protected void e(SimpleDraweeView simpleDraweeView, int i2) {
    }

    protected void f(ViewGroup.LayoutParams layoutParams) {
        if (layoutParams != null) {
            layoutParams.width = -2;
            layoutParams.height = DPIUtil.dip2px(30.0f);
        }
    }

    public final void g() {
        this.r.h();
    }

    public final void h() {
        this.r.i(this.f9238n);
    }

    public final void i() {
        s(true);
        this.p |= 4;
        this.r.j(this.f9238n);
    }

    public void j(AnimatorSet animatorSet, AnimatorSet animatorSet2) {
        if (animatorSet != null) {
            this.f9236l = animatorSet;
            animatorSet.addListener(new b());
        }
        if (animatorSet2 != null) {
            this.f9237m = animatorSet2;
            animatorSet2.addListener(new c());
        }
    }

    public void k(a.C0284a c0284a, a.C0284a c0284a2) {
        this.f9238n = c0284a;
        TextView textView = this.f9234j;
        if (textView != null) {
            textView.setText(c0284a.b());
        }
        f.d(c0284a.d(), this.f9232h);
        if (c0284a2 != null) {
            f.d(c0284a2.d(), this.f9233i);
        }
    }

    public final boolean m() {
        return (this.p & 4) != 0;
    }

    public boolean n() {
        return com.jingdong.app.mall.home.state.dark.a.h();
    }

    public final boolean o() {
        return (this.p & 2) != 0;
    }

    public void p(a.C0284a c0284a) {
    }

    public void q(a.C0284a c0284a) {
    }

    public void r(a.C0284a c0284a) {
    }

    public final void s(boolean z) {
        int i2 = this.p & (-2);
        this.p = i2;
        this.p = i2 | 2;
        if (!this.r.r()) {
            u();
        }
        this.r.p(z);
    }

    @Override // android.view.View
    public final void setLayoutParams(ViewGroup.LayoutParams layoutParams) {
        f(layoutParams);
        super.setLayoutParams(layoutParams);
    }

    public final void t(String str, String str2, int i2) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.p = 0;
        l();
        this.r.q(str, str2, i2);
    }

    public void u() {
        AnimatorSet animatorSet = this.f9236l;
        if (animatorSet != null) {
            animatorSet.cancel();
        }
        AnimatorSet animatorSet2 = this.f9237m;
        if (animatorSet2 != null) {
            animatorSet2.cancel();
        }
        setVisibility(8);
    }

    public final void v() {
        if (o()) {
            this.p &= -3;
            this.r.n(false, true);
        }
    }

    public void w(int i2) {
        this.f9231g = i2;
    }

    public boolean x() {
        setVisibility(8);
        if (m() || o() || !c()) {
            return false;
        }
        this.p |= 1;
        this.f9237m.cancel();
        this.f9236l.start();
        this.r.u(this.f9238n);
        return true;
    }

    public void y() {
        AnimatorSet animatorSet = this.f9236l;
        if (animatorSet != null) {
            animatorSet.cancel();
        }
        AnimatorSet animatorSet2 = this.f9237m;
        if (animatorSet2 == null || animatorSet2.isRunning()) {
            return;
        }
        this.f9237m.start();
    }

    public FloatBubbleLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public FloatBubbleLayout(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        Paint paint = new Paint(1);
        this.s = paint;
        this.q = context;
        paint.setColor(-570425345);
        this.s.setStyle(Paint.Style.STROKE);
        this.s.setStrokeWidth(DPIUtil.dip2px(1.0f));
        this.r = new com.jingdong.app.mall.home.floor.bubble.b(this, context);
    }
}
