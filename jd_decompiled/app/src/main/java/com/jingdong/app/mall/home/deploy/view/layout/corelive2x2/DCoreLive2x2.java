package com.jingdong.app.mall.home.deploy.view.layout.corelive2x2;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.jingdong.app.mall.home.JDHomeFragment;
import com.jingdong.app.mall.home.R;
import com.jingdong.app.mall.home.deploy.view.base.BaseModel;
import com.jingdong.app.mall.home.deploy.view.layout.core.CoreBaseView;
import com.jingdong.app.mall.home.deploy.view.layout.widget.IconImageText;
import com.jingdong.app.mall.home.deploy.view.layout.widget.SkuLayout;
import com.jingdong.app.mall.home.floor.common.f;
import com.jingdong.app.mall.home.floor.common.g;
import com.jingdong.app.mall.home.floor.view.view.GradientTextView;
import com.jingdong.app.mall.home.p.b.d.a;
import com.jingdong.app.mall.home.p.b.d.b;
import com.jingdong.app.mall.home.p.b.d.c;
import com.jingdong.sdk.platform.business.personal.R2;

/* loaded from: classes4.dex */
public class DCoreLive2x2 extends CoreBaseView implements b {
    private f A;
    private LiveLottie B;
    private f C;
    private a D;
    private DCoreLive2x2Model p;
    private View q;
    private IconImageText r;
    private f s;
    private TextView t;
    private f u;
    private GradientTextView v;
    private f w;
    private SkuLayout x;
    private SkuLayout y;
    private f z;

    public DCoreLive2x2(Context context) {
        super(context);
        View view = new View(context);
        this.q = view;
        addView(view, new RelativeLayout.LayoutParams(-1, -1));
        IconImageText iconImageText = new IconImageText(context);
        this.r = iconImageText;
        iconImageText.setId(R.id.homefloor_child_item1);
        f fVar = new f(-2, 54);
        this.s = fVar;
        fVar.J(16, 0, 0, 0);
        View view2 = this.r;
        addView(view2, this.s.u(view2));
        g gVar = new g(context, false);
        gVar.l(-1);
        gVar.h();
        gVar.d(17);
        TextView a = gVar.a();
        this.t = a;
        a.setBackgroundResource(R.drawable.home_live_label_bg);
        f fVar2 = new f(100, 28);
        this.u = fVar2;
        fVar2.J(24, 0, 0, 0);
        this.u.E(8, 12, 0, 0);
        RelativeLayout.LayoutParams u = this.u.u(this.t);
        u.addRule(1, this.r.getId());
        addView(this.t, u);
        g gVar2 = new g(context, true);
        gVar2.h();
        gVar2.d(16);
        this.v = gVar2.b();
        f fVar3 = new f(-2, 116);
        this.w = fVar3;
        fVar3.J(12, 0, 0, 0);
        View view3 = this.v;
        addView(view3, this.w.u(view3));
        this.x = new SkuLayout(context);
        f fVar4 = new f(126, 126);
        this.z = fVar4;
        fVar4.E(12, 0, 0, 12);
        RelativeLayout.LayoutParams u2 = this.z.u(this.x);
        u2.addRule(12);
        addView(this.x, u2);
        this.y = new SkuLayout(context);
        f fVar5 = new f(126, 126);
        this.A = fVar5;
        fVar5.E(0, 0, 76, 12);
        RelativeLayout.LayoutParams u3 = this.A.u(this.y);
        u3.addRule(11);
        u3.addRule(12);
        addView(this.y, u3);
        this.B = new LiveLottie(getContext());
        f fVar6 = new f(R2.anim.pickerview_dialog_scale_in, R2.anim.pickerview_dialog_scale_in);
        this.C = fVar6;
        fVar6.E(0, 0, 12, 12);
        RelativeLayout.LayoutParams u4 = this.C.u(this.B);
        u4.addRule(11);
        u4.addRule(12);
        addView(this.B, u4);
    }

    @Override // com.jingdong.app.mall.home.p.b.d.b
    public void a(View view) {
        if (JDHomeFragment.O0()) {
            a aVar = new a(view, this);
            this.D = aVar;
            view.setAnimation(aVar);
        }
    }

    @Override // com.jingdong.app.mall.home.deploy.view.base.BaseView
    public void i(BaseModel baseModel, int i2) {
        super.i(baseModel, i2);
        baseModel.t();
        this.s.R(-2, 54);
        this.w.R(-2, 116);
        this.z.R(126, 126);
        this.A.R(126, 126);
        this.A.E(0, 0, 76, 12);
        this.C.R(R2.anim.pickerview_dialog_scale_in, R2.anim.pickerview_dialog_scale_in);
        this.C.E(0, 0, 12, 12);
        q();
    }

    @Override // com.jingdong.app.mall.home.deploy.view.base.BaseView
    protected boolean j(BaseModel baseModel) {
        com.jingdong.app.mall.home.o.a.f.n(baseModel);
        this.p = (DCoreLive2x2Model) baseModel;
        return baseModel != null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.home.deploy.view.layout.core.CoreBaseView, com.jingdong.app.mall.home.deploy.view.base.BaseView
    public void m() {
        super.m();
        GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, new int[]{2130706432, 335544320});
        gradientDrawable.setCornerRadius(s());
        this.q.setBackgroundDrawable(gradientDrawable);
        g.o(this.v, 20);
        g.o(this.t, 20);
        this.r.i(this.p.A0());
        String V = this.p.V();
        this.t.setVisibility(TextUtils.isEmpty(V) ? 8 : 0);
        this.t.setText(com.jingdong.app.mall.home.o.a.f.j(3, V));
        this.v.setText(this.p.e0());
        this.v.setTextGradient(GradientTextView.GradientType.LeftToRight, this.p.d0());
        this.x.b(this.p.y0());
        this.y.b(this.p.z0());
        if (TextUtils.equals(this.p.f("showPop"), "1")) {
            this.B.k();
        } else {
            this.B.h();
        }
        setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.app.mall.home.deploy.view.layout.corelive2x2.DCoreLive2x2.1
            {
                DCoreLive2x2.this = this;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                DCoreLive2x2.this.p.v0(DCoreLive2x2.this, 0, true);
                c.g().f(DCoreLive2x2.this.p, DCoreLive2x2.this);
            }
        });
        this.x.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.app.mall.home.deploy.view.layout.corelive2x2.DCoreLive2x2.2
            {
                DCoreLive2x2.this = this;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                DCoreLive2x2.this.p.v0(DCoreLive2x2.this.x, 0, true);
                c.g().f(DCoreLive2x2.this.p, DCoreLive2x2.this);
            }
        });
        this.y.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.app.mall.home.deploy.view.layout.corelive2x2.DCoreLive2x2.3
            {
                DCoreLive2x2.this = this;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                DCoreLive2x2.this.p.v0(DCoreLive2x2.this.y, 1, true);
                c.g().f(DCoreLive2x2.this.p, DCoreLive2x2.this);
            }
        });
        setContentDescription(this.p.m0());
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        a aVar = this.D;
        if (aVar != null) {
            aVar.a();
            this.D = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.home.deploy.view.layout.core.CoreBaseView, com.jingdong.app.mall.home.deploy.view.base.BaseView
    public void q() {
        super.q();
        f.c(this.r, this.s);
        f.c(this.t, this.u);
        f.c(this.v, this.w);
        f.c(this.x, this.z);
        f.c(this.y, this.A);
        f.c(this.B, this.C);
    }
}
