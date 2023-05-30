package com.jingdong.app.mall.home.floor.view.linefloor.floor;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewParent;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.app.mall.home.base.HomeDraweeView;
import com.jingdong.app.mall.home.floor.common.f;
import com.jingdong.app.mall.home.floor.common.i.m;
import com.jingdong.app.mall.home.floor.ctrl.j;
import com.jingdong.app.mall.home.floor.ctrl.l;
import com.jingdong.app.mall.home.floor.view.baseui.BaseMallFloor;
import com.jingdong.app.mall.home.floor.view.linefloor.base.BaseLineLayout;
import com.jingdong.sdk.platform.business.personal.R2;

/* loaded from: classes4.dex */
public class Line1Split2CardView extends BaseLineLayout<com.jingdong.app.mall.home.floor.view.b.g.e> implements j {
    private SimpleDraweeView A;
    private AnimatorSet B;
    private com.jingdong.app.mall.home.floor.view.b.g.e C;
    private boolean D;
    private Drawable E;
    private f F;
    private boolean s;
    private boolean t;
    private boolean u;
    private SimpleDraweeView v;
    private TextView w;
    private TextView x;
    private RelativeLayout y;
    private SimpleDraweeView z;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class a implements View.OnClickListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ com.jingdong.app.mall.home.floor.view.b.g.e f9866g;

        a(com.jingdong.app.mall.home.floor.view.b.g.e eVar) {
            this.f9866g = eVar;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.f9866g.Y(((BaseLineLayout) Line1Split2CardView.this).o, 0);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class b implements View.OnClickListener {
        b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            int i2 = Line1Split2CardView.this.z.getAlpha() >= Line1Split2CardView.this.A.getAlpha() ? 1 : 2;
            if (Line1Split2CardView.this.C != null) {
                Line1Split2CardView.this.C.Y(((BaseLineLayout) Line1Split2CardView.this).o, i2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class c implements View.OnClickListener {
        c() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (Line1Split2CardView.this.C != null) {
                Line1Split2CardView.this.C.Y(((BaseLineLayout) Line1Split2CardView.this).o, 1);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class d implements View.OnClickListener {
        d() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (Line1Split2CardView.this.C != null) {
                Line1Split2CardView.this.C.Y(((BaseLineLayout) Line1Split2CardView.this).o, 2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class e extends AnimatorListenerAdapter {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ View f9871g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ View f9872h;

        e(View view, View view2) {
            this.f9871g = view;
            this.f9872h = view2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.f9871g.setVisibility(8);
            if (this.f9872h.getVisibility() != 0) {
                Line1Split2CardView.this.O(this.f9872h, 0, 1.0f);
            }
            Line1Split2CardView.this.N();
        }
    }

    public Line1Split2CardView(Context context, com.jingdong.app.mall.home.floor.view.b.a aVar) {
        super(context, aVar);
        this.E = new ColorDrawable(Color.parseColor("#E6E6E6"));
        this.F = new f(R2.attr.adSize, -1);
    }

    private void G(com.jingdong.app.mall.home.floor.view.b.g.e eVar) {
        SimpleDraweeView simpleDraweeView = this.v;
        if (simpleDraweeView == null) {
            HomeDraweeView homeDraweeView = new HomeDraweeView(this.o);
            this.v = homeDraweeView;
            homeDraweeView.setScaleType(ImageView.ScaleType.FIT_XY);
            SimpleDraweeView simpleDraweeView2 = this.v;
            addView(simpleDraweeView2, this.F.u(simpleDraweeView2));
        } else {
            f.c(simpleDraweeView, this.F);
        }
        com.jingdong.app.mall.home.floor.ctrl.e.m(this.v, eVar.N(), this.E);
    }

    private void H(com.jingdong.app.mall.home.floor.view.b.g.e eVar) {
        f fVar = new f(144, 144);
        fVar.E(0, 0, this.u ? 31 : 24, 0);
        RelativeLayout relativeLayout = this.y;
        if (relativeLayout == null) {
            RelativeLayout relativeLayout2 = new RelativeLayout(this.o);
            this.y = relativeLayout2;
            relativeLayout2.setBackgroundColor(-1);
            RelativeLayout.LayoutParams u = fVar.u(this.y);
            u.addRule(15);
            u.addRule(11);
            addView(this.y, u);
        } else {
            f.c(relativeLayout, fVar);
        }
        this.y.setVisibility(this.t ? 0 : 8);
        com.jingdong.app.mall.home.n.h.e.d(this.y, com.jingdong.app.mall.home.floor.common.d.d(16));
        f fVar2 = new f(-1, -1);
        if (this.A == null) {
            HomeDraweeView homeDraweeView = new HomeDraweeView(this.o);
            this.A = homeDraweeView;
            homeDraweeView.setScaleType(ImageView.ScaleType.FIT_XY);
            RelativeLayout relativeLayout3 = this.y;
            SimpleDraweeView simpleDraweeView = this.A;
            relativeLayout3.addView(simpleDraweeView, fVar2.u(simpleDraweeView));
        }
        com.jingdong.app.mall.home.n.h.e.d(this.A, com.jingdong.app.mall.home.floor.common.d.d(16));
        if (this.z == null) {
            HomeDraweeView homeDraweeView2 = new HomeDraweeView(this.o);
            this.z = homeDraweeView2;
            homeDraweeView2.setScaleType(ImageView.ScaleType.FIT_XY);
            RelativeLayout relativeLayout4 = this.y;
            SimpleDraweeView simpleDraweeView2 = this.z;
            relativeLayout4.addView(simpleDraweeView2, fVar2.u(simpleDraweeView2));
        }
        com.jingdong.app.mall.home.n.h.e.d(this.z, com.jingdong.app.mall.home.floor.common.d.d(16));
        com.jingdong.app.mall.home.floor.ctrl.e.m(this.z, eVar.R(), com.jingdong.app.mall.home.floor.ctrl.e.b);
        com.jingdong.app.mall.home.floor.ctrl.e.m(this.A, eVar.S(), com.jingdong.app.mall.home.floor.ctrl.e.b);
        if (!TextUtils.isEmpty(this.C.S())) {
            O(this.A, 0, this.D ? 1.0f : 0.0f);
            O(this.z, 0, this.D ? 0.0f : 1.0f);
        } else {
            O(this.A, 8, 0.0f);
            O(this.z, 0, 1.0f);
        }
        this.y.setOnClickListener(new b());
        l.g().b(this);
    }

    private void I(com.jingdong.app.mall.home.floor.view.b.g.e eVar) {
        f fVar = new f(-2, 78);
        fVar.E(0, 0, this.u ? 32 : 24, 0);
        RelativeLayout relativeLayout = this.y;
        if (relativeLayout == null) {
            RelativeLayout relativeLayout2 = new RelativeLayout(this.o);
            this.y = relativeLayout2;
            relativeLayout2.setBackgroundColor(0);
            RelativeLayout.LayoutParams u = fVar.u(this.y);
            u.addRule(15);
            u.addRule(11);
            addView(this.y, u);
        } else {
            f.c(relativeLayout, fVar);
        }
        this.y.setVisibility(this.t ? 0 : 8);
        com.jingdong.app.mall.home.n.h.e.d(this.y, 0);
        f fVar2 = new f(78, 78);
        SimpleDraweeView simpleDraweeView = this.z;
        if (simpleDraweeView == null) {
            HomeDraweeView homeDraweeView = new HomeDraweeView(this.o);
            this.z = homeDraweeView;
            homeDraweeView.setScaleType(ImageView.ScaleType.FIT_XY);
            this.z.setBackgroundColor(-1);
            RelativeLayout relativeLayout3 = this.y;
            SimpleDraweeView simpleDraweeView2 = this.z;
            relativeLayout3.addView(simpleDraweeView2, fVar2.u(simpleDraweeView2));
        } else {
            f.c(simpleDraweeView, fVar2);
        }
        com.jingdong.app.mall.home.n.h.e.d(this.z, com.jingdong.app.mall.home.floor.common.d.d(8));
        fVar2.E(84, 0, 0, 0);
        SimpleDraweeView simpleDraweeView3 = this.A;
        if (simpleDraweeView3 == null) {
            HomeDraweeView homeDraweeView2 = new HomeDraweeView(this.o);
            this.A = homeDraweeView2;
            homeDraweeView2.setScaleType(ImageView.ScaleType.FIT_XY);
            this.A.setBackgroundColor(-1);
            RelativeLayout relativeLayout4 = this.y;
            SimpleDraweeView simpleDraweeView4 = this.A;
            relativeLayout4.addView(simpleDraweeView4, fVar2.u(simpleDraweeView4));
        } else {
            f.c(simpleDraweeView3, fVar2);
        }
        com.jingdong.app.mall.home.n.h.e.d(this.A, com.jingdong.app.mall.home.floor.common.d.d(8));
        com.jingdong.app.mall.home.floor.ctrl.e.u(this.z, eVar.R());
        com.jingdong.app.mall.home.floor.ctrl.e.u(this.A, eVar.S());
        this.z.setOnClickListener(new c());
        this.A.setOnClickListener(new d());
    }

    private void J(com.jingdong.app.mall.home.floor.view.b.g.e eVar) {
        f fVar;
        f fVar2;
        if (this.s) {
            fVar = new f(130, 40);
            fVar.J(0, 0, 0, 0);
            fVar.E(this.u ? 24 : 32, 19, 0, 0);
        } else {
            fVar = new f(-2, 108);
            fVar.J(this.u ? 23 : 30, 0, 0, 0);
            fVar.E(0, 0, 0, 0);
        }
        TextView textView = this.w;
        if (textView == null) {
            TextView textView2 = new TextView(this.o);
            this.w = textView2;
            P(textView2);
            if (this.s) {
                this.w.setGravity(17);
            }
            TextView textView3 = this.w;
            addView(textView3, fVar.u(textView3));
        } else {
            f.c(textView, fVar);
        }
        this.w.setTextSize(0, com.jingdong.app.mall.home.floor.common.d.d(this.s ? 26 : 28));
        this.w.getPaint().setFakeBoldText(true);
        this.w.setTextColor(m.j(eVar.P(), -1));
        if (this.s) {
            this.w.setText(com.jingdong.app.mall.home.o.a.f.j(5, eVar.Q()));
        } else {
            TextView textView4 = this.w;
            textView4.setText(com.jingdong.app.mall.home.o.a.f.l(textView4, com.jingdong.app.mall.home.floor.common.d.d(R2.anim.miaosha_dropdown_in), eVar.Q()));
        }
        this.w.setVisibility((!this.t || TextUtils.isEmpty(eVar.Q())) ? 8 : 0);
        if (this.s) {
            fVar2 = new f(130, 34);
            fVar2.J(0, 0, 0, 0);
            fVar2.E(this.u ? 24 : 32, 59, 0, 0);
        } else {
            fVar2 = new f(-2, R2.anim.settlement_dialog_right_enter);
            fVar2.J(this.u ? 23 : 30, 0, 0, 0);
            fVar2.E(0, 0, 0, 0);
        }
        TextView textView5 = this.x;
        if (textView5 == null) {
            TextView textView6 = new TextView(this.o);
            this.x = textView6;
            P(textView6);
            if (this.s) {
                this.x.setGravity(17);
            }
            TextView textView7 = this.x;
            addView(textView7, fVar2.u(textView7));
        } else {
            f.c(textView5, fVar2);
        }
        this.x.setTextSize(0, com.jingdong.app.mall.home.floor.common.d.d(this.s ? 18 : 24));
        this.x.setTextColor(m.j(eVar.T(), -1));
        if (this.s) {
            this.x.setText(com.jingdong.app.mall.home.o.a.f.j(6, eVar.U()));
        } else {
            TextView textView8 = this.x;
            textView8.setText(com.jingdong.app.mall.home.o.a.f.l(textView8, com.jingdong.app.mall.home.floor.common.d.d(160), eVar.U()));
        }
        this.x.setVisibility((!this.t || TextUtils.isEmpty(eVar.U())) ? 8 : 0);
    }

    private void K() {
        TextView textView = this.w;
        if (textView != null) {
            textView.setVisibility(8);
        }
        TextView textView2 = this.x;
        if (textView2 != null) {
            textView2.setVisibility(8);
        }
        RelativeLayout relativeLayout = this.y;
        if (relativeLayout != null) {
            relativeLayout.setVisibility(8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void N() {
        l.g().h();
        this.B = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void O(View view, int i2, float f2) {
        if (view != null) {
            view.setVisibility(i2);
            view.setScaleX(f2);
            view.setScaleY(f2);
            view.setAlpha(f2);
        }
    }

    private void P(TextView textView) {
        textView.setIncludeFontPadding(false);
        textView.setSingleLine();
        textView.setGravity(16);
    }

    private void Q(View view, View view2) {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view2, "ScaleX", 1.0f, 0.3f);
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(view2, "ScaleY", 1.0f, 0.3f);
        ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(view2, "alpha", 1.0f, 0.0f);
        ObjectAnimator ofFloat4 = ObjectAnimator.ofFloat(view, "ScaleX", 0.3f, 1.0f);
        ObjectAnimator ofFloat5 = ObjectAnimator.ofFloat(view, "ScaleY", 0.3f, 1.0f);
        ObjectAnimator ofFloat6 = ObjectAnimator.ofFloat(view, "alpha", 0.0f, 1.0f);
        AnimatorSet animatorSet = new AnimatorSet();
        this.B = animatorSet;
        animatorSet.playTogether(ofFloat, ofFloat2, ofFloat3, ofFloat4, ofFloat5, ofFloat6);
        this.B.setDuration(1600L);
        this.B.addListener(new e(view2, view));
        this.B.start();
    }

    public boolean L() {
        ViewParent parent = getParent();
        if (parent != null) {
            ViewParent parent2 = parent.getParent();
            if ((parent2 instanceof BaseMallFloor) && !((BaseMallFloor) parent2).isFloorRecycle()) {
                return m.I(this, com.jingdong.app.mall.home.a.f8560i, com.jingdong.app.mall.home.a.f8562k, false);
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.home.floor.view.linefloor.base.BaseLineLayout
    /* renamed from: M  reason: merged with bridge method [inline-methods] */
    public void v(@NonNull com.jingdong.app.mall.home.floor.view.b.g.e eVar, int i2) {
        this.t = eVar.X();
        this.u = i2 == 0;
        this.D = false;
        this.C = eVar;
        this.s = eVar.W();
        R();
        G(eVar);
        if (this.t) {
            J(eVar);
            if (this.s) {
                I(eVar);
            } else {
                H(eVar);
            }
        } else {
            K();
        }
        setOnClickListener(new a(eVar));
    }

    public boolean R() {
        AnimatorSet animatorSet = this.B;
        if (animatorSet == null || !animatorSet.isRunning()) {
            return false;
        }
        this.B.end();
        return true;
    }

    @Override // com.jingdong.app.mall.home.floor.ctrl.j
    public boolean canPlayAnimation() {
        return hasSkuAnimation() && L() && !isAnimationDisplay();
    }

    @Override // com.jingdong.app.mall.home.floor.ctrl.j
    public boolean displayAnimation() {
        if (canPlayAnimation()) {
            this.z.setVisibility(0);
            this.A.setVisibility(0);
            if (!this.D) {
                Q(this.A, this.z);
            } else {
                Q(this.z, this.A);
            }
            this.D = !this.D;
            return true;
        }
        return false;
    }

    @Override // com.jingdong.app.mall.home.floor.ctrl.j
    public String getFloorId() {
        return this.C.getFloorId();
    }

    @Override // com.jingdong.app.mall.home.floor.ctrl.j
    public String getSkuAnimationId() {
        return this.C.O();
    }

    @Override // com.jingdong.app.mall.home.floor.ctrl.j
    public boolean hasSkuAnimation() {
        return this.C.X() && !TextUtils.isEmpty(this.C.R()) && !TextUtils.isEmpty(this.C.S()) && this.C.V();
    }

    @Override // com.jingdong.app.mall.home.floor.ctrl.j
    public boolean isAnimationDisplay() {
        AnimatorSet animatorSet = this.B;
        return animatorSet != null && animatorSet.isRunning();
    }
}
