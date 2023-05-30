package com.jingdong.app.mall.home.category.floor.floorsub;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.GradientDrawable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.app.mall.home.R;
import com.jingdong.app.mall.home.base.HomeDraweeView;
import com.jingdong.app.mall.home.base.HomeImageView;
import com.jingdong.app.mall.home.category.floor.base.BaseCaRecycleItem;
import com.jingdong.app.mall.home.floor.common.f;
import com.jingdong.app.mall.home.floor.model.entity.IconFloorEntity;
import com.jingdong.app.mall.home.floor.view.view.GradientTextView;
import com.jingdong.app.mall.home.floor.view.widget.FitTopImage;
import com.jingdong.app.mall.home.n.g.x.e;
import com.jingdong.app.mall.home.n.h.b;
import com.jingdong.app.mall.home.n.h.c;
import com.jingdong.app.mall.home.x.d;
import com.jingdong.common.entity.JumpEntity;
import com.jingdong.jdsdk.utils.FontsUtil;
import com.jingdong.sdk.platform.business.personal.R2;
import org.jetbrains.annotations.NotNull;

/* loaded from: classes4.dex */
public class CaFlashDownSubFloor extends BaseCaRecycleItem<e> implements d {
    private static GradientDrawable S = new GradientDrawable();
    private static GradientDrawable T = new GradientDrawable();
    private static GradientDrawable U = new GradientDrawable();
    private static GradientDrawable V = new GradientDrawable();
    private static GradientDrawable W = new GradientDrawable();
    private LinearLayout A;
    private TextView B;
    private LinearLayout C;
    private TextView D;
    private a[] E;
    private f F;
    private f G;
    private f H;
    private f I;
    private f J;
    private f K;
    private f L;
    private f M;
    private f N;
    private f O;
    private f P;
    private f Q;
    private f[] R;
    private RelativeLayout q;
    private RelativeLayout r;
    private SimpleDraweeView s;
    private SimpleDraweeView t;
    private GradientTextView u;
    private GradientTextView v;
    private LinearLayout w;
    private SimpleDraweeView x;
    private ImageView y;
    private com.jingdong.app.mall.home.floor.view.widget.a z;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public class a extends RelativeLayout {

        /* renamed from: g  reason: collision with root package name */
        private SimpleDraweeView f8705g;

        /* renamed from: h  reason: collision with root package name */
        private TextView f8706h;

        /* renamed from: i  reason: collision with root package name */
        private TextView f8707i;

        /* renamed from: j  reason: collision with root package name */
        private f f8708j;

        /* renamed from: k  reason: collision with root package name */
        private f f8709k;

        /* renamed from: l  reason: collision with root package name */
        private f f8710l;

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: com.jingdong.app.mall.home.category.floor.floorsub.CaFlashDownSubFloor$a$a  reason: collision with other inner class name */
        /* loaded from: classes4.dex */
        public class ViewOnClickListenerC0276a implements View.OnClickListener {

            /* renamed from: g  reason: collision with root package name */
            final /* synthetic */ e.a f8711g;

            ViewOnClickListenerC0276a(a aVar, e.a aVar2) {
                this.f8711g = aVar2;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                JumpEntity c2 = this.f8711g.c();
                if (c2 == null) {
                    return;
                }
                b.b(view.getContext(), c2, this.f8711g.d());
            }
        }

        public a(CaFlashDownSubFloor caFlashDownSubFloor, Context context) {
            super(context);
            HomeDraweeView homeDraweeView = new HomeDraweeView(context);
            this.f8705g = homeDraweeView;
            homeDraweeView.setId(R.id.mallfloor_item1);
            this.f8705g.setScaleType(ImageView.ScaleType.FIT_XY);
            f fVar = new f(134, 134);
            this.f8708j = fVar;
            fVar.F(new Rect(0, 8, 0, 0));
            RelativeLayout.LayoutParams u = this.f8708j.u(this.f8705g);
            u.addRule(14);
            addView(this.f8705g, u);
            TextView textView = new TextView(context);
            this.f8706h = textView;
            textView.setId(R.id.mallfloor_item2);
            this.f8706h.setMaxLines(1);
            this.f8706h.setEllipsize(TextUtils.TruncateAt.END);
            this.f8706h.setTextColor(-907508);
            this.f8706h.setTypeface(FontsUtil.getTypeFace(context, 4099));
            caFlashDownSubFloor.s(this.f8706h);
            this.f8706h.setGravity(17);
            f fVar2 = new f(-1, 28);
            this.f8710l = fVar2;
            fVar2.F(new Rect(0, 5, 0, 0));
            this.f8710l.K(new Rect(0, 0, 0, 0));
            RelativeLayout.LayoutParams u2 = this.f8710l.u(this.f8706h);
            u2.addRule(3, this.f8705g.getId());
            addView(this.f8706h, u2);
            TextView textView2 = new TextView(context);
            this.f8707i = textView2;
            textView2.setTextColor(-6316129);
            this.f8707i.setMaxLines(1);
            this.f8707i.setEllipsize(TextUtils.TruncateAt.END);
            this.f8707i.getPaint().setFlags(17);
            this.f8707i.setTypeface(FontsUtil.getTypeFace(context, 4099));
            caFlashDownSubFloor.s(this.f8707i);
            this.f8707i.setGravity(17);
            f fVar3 = new f(-1, 24);
            this.f8709k = fVar3;
            RelativeLayout.LayoutParams u3 = fVar3.u(this.f8707i);
            u3.addRule(3, this.f8706h.getId());
            addView(this.f8707i, u3);
        }

        public void a(e.a aVar) {
            if (aVar == null) {
                return;
            }
            setOnClickListener(new ViewOnClickListenerC0276a(this, aVar));
            com.jingdong.app.mall.home.n.h.e.d(this.f8705g, com.jingdong.app.mall.home.floor.common.d.d(12));
            f.c(this.f8705g, this.f8708j);
            f.c(this.f8706h, this.f8710l);
            f.c(this.f8707i, this.f8709k);
            this.f8706h.setTextSize(0, com.jingdong.app.mall.home.floor.common.d.d(24));
            this.f8707i.setTextSize(0, com.jingdong.app.mall.home.floor.common.d.d(16));
            com.jingdong.app.mall.home.floor.ctrl.e.m(this.f8705g, aVar.a(), com.jingdong.app.mall.home.floor.ctrl.e.w());
            this.f8706h.setText(aVar.e());
            SpannableString b = aVar.b();
            this.f8707i.setVisibility(TextUtils.isEmpty(b) ? 8 : 0);
            this.f8707i.setText(b);
        }
    }

    public CaFlashDownSubFloor(Context context) {
        super(context);
        this.E = new a[3];
        this.R = new f[3];
        this.q = new RelativeLayout(context);
        f fVar = new f(R2.attr.blendSrc, 200);
        this.F = fVar;
        RelativeLayout relativeLayout = this.q;
        addView(relativeLayout, fVar.u(relativeLayout));
        FitTopImage fitTopImage = new FitTopImage(context);
        this.s = fitTopImage;
        fitTopImage.setId(R.id.mallfloor_item2);
        f fVar2 = new f(R2.attr.blendSrc, 200);
        this.J = fVar2;
        RelativeLayout relativeLayout2 = this.q;
        SimpleDraweeView simpleDraweeView = this.s;
        relativeLayout2.addView(simpleDraweeView, fVar2.u(simpleDraweeView));
        View view = new View(context);
        view.setBackgroundColor(855638016);
        this.q.addView(view, new RelativeLayout.LayoutParams(-1, -1));
        W.setCornerRadius(com.jingdong.app.mall.home.floor.common.d.d(24));
        W.setColor(-1);
        LinearLayout linearLayout = new LinearLayout(context);
        this.w = linearLayout;
        linearLayout.setId(R.id.mallfloor_item1);
        this.w.setGravity(17);
        this.w.setBackgroundDrawable(W);
        f fVar3 = new f(116, 40);
        this.H = fVar3;
        fVar3.F(new Rect(23, 15, 0, 0));
        RelativeLayout relativeLayout3 = this.q;
        LinearLayout linearLayout2 = this.w;
        relativeLayout3.addView(linearLayout2, this.H.u(linearLayout2));
        this.x = new HomeDraweeView(context);
        f fVar4 = new f(76, 40);
        this.I = fVar4;
        LinearLayout linearLayout3 = this.w;
        SimpleDraweeView simpleDraweeView2 = this.x;
        linearLayout3.addView(simpleDraweeView2, fVar4.i(simpleDraweeView2));
        GradientTextView gradientTextView = new GradientTextView(context);
        this.u = gradientTextView;
        gradientTextView.setId(R.id.mallfloor_item3);
        this.u.getPaint().setFakeBoldText(true);
        this.u.setTextColor(-1);
        s(this.u);
        f fVar5 = new f(400, 50);
        this.K = fVar5;
        fVar5.F(new Rect(23, 62, 0, 0));
        this.q.addView(this.u, this.K.u(this.u));
        GradientTextView gradientTextView2 = new GradientTextView(context);
        this.v = gradientTextView2;
        gradientTextView2.setId(R.id.mallfloor_item4);
        this.v.setTextColor(-1);
        s(this.v);
        f fVar6 = new f(-2, 36);
        this.L = fVar6;
        fVar6.F(new Rect(23, 0, 0, 0));
        RelativeLayout.LayoutParams u = this.L.u(this.v);
        u.addRule(3, this.u.getId());
        this.q.addView(this.v, u);
        HomeImageView homeImageView = new HomeImageView(context);
        this.y = homeImageView;
        homeImageView.setBackgroundResource(R.drawable.home_c_icon_right_arrow);
        f fVar7 = new f(16, 16);
        this.N = fVar7;
        fVar7.F(new Rect(5, 10, 0, 0));
        RelativeLayout.LayoutParams u2 = this.N.u(this.y);
        u2.addRule(1, this.v.getId());
        u2.addRule(6, this.v.getId());
        this.q.addView(this.y, u2);
        T.setColor(IconFloorEntity.BGCOLOR_DEFAULT);
        T.setAlpha(101);
        T.setCornerRadii(new float[]{com.jingdong.app.mall.home.floor.common.d.d(17), com.jingdong.app.mall.home.floor.common.d.d(17), 0.0f, 0.0f, 0.0f, 0.0f, com.jingdong.app.mall.home.floor.common.d.d(17), com.jingdong.app.mall.home.floor.common.d.d(17)});
        LinearLayout linearLayout4 = new LinearLayout(context);
        this.A = linearLayout4;
        linearLayout4.setBackgroundDrawable(T);
        this.A.setOrientation(0);
        this.A.setGravity(16);
        f fVar8 = new f(R2.anim.settlement_dialog_right_exit, 34);
        this.P = fVar8;
        fVar8.F(new Rect(0, 123, 0, 0));
        RelativeLayout.LayoutParams u3 = this.P.u(this.A);
        u3.addRule(11);
        this.q.addView(this.A, u3);
        TextView textView = new TextView(context);
        this.B = textView;
        textView.setGravity(16);
        this.B.setTextColor(-1);
        this.B.setText("\u4ec5\u5269");
        f fVar9 = new f(46, 30);
        this.O = fVar9;
        fVar9.F(new Rect(15, 0, 10, 0));
        LinearLayout linearLayout5 = this.A;
        TextView textView2 = this.B;
        linearLayout5.addView(textView2, this.O.i(textView2));
        com.jingdong.app.mall.home.floor.view.widget.a aVar = new com.jingdong.app.mall.home.floor.view.widget.a();
        this.z = aVar;
        aVar.e(-16777216);
        this.z.m(-1);
        this.z.i(true);
        this.z.g(1);
        this.z.p("", "", "", "");
        this.z.o(FontsUtil.getTypeFace(context, 4099));
        t("00", "00", "00");
        this.t = new HomeDraweeView(context);
        f fVar10 = new f(104, 30);
        this.M = fVar10;
        RelativeLayout.LayoutParams u4 = fVar10.u(this.t);
        u4.addRule(11);
        this.A.addView(this.t, u4);
        this.t.setImageDrawable(this.z);
        q(context);
        V.setCornerRadius(com.jingdong.app.mall.home.floor.common.d.d(10));
        V.setColor(-1);
        RelativeLayout relativeLayout4 = new RelativeLayout(context);
        this.r = relativeLayout4;
        relativeLayout4.setBackgroundDrawable(V);
        f fVar11 = new f(-1, 210);
        this.G = fVar11;
        fVar11.F(new Rect(0, R2.anim.pop_left_top_in, 0, 0));
        RelativeLayout relativeLayout5 = this.r;
        addView(relativeLayout5, this.G.u(relativeLayout5));
        for (int i2 = 0; i2 < 3; i2++) {
            a aVar2 = new a(this, context);
            this.E[i2] = aVar2;
            this.R[i2] = new f(150, -1);
            this.R[i2].F(new Rect(i2 * 155, 0, 0, 0));
            this.r.addView(aVar2, this.R[i2].u(aVar2));
        }
    }

    private void p() {
        if (((e) this.f8679m).x() < 1) {
            r();
            return;
        }
        com.jingdong.app.mall.home.x.a.b().d(this);
        this.A.setVisibility(0);
        this.C.setVisibility(8);
    }

    private void q(Context context) {
        U.setCornerRadius(com.jingdong.app.mall.home.floor.common.d.d(20));
        U.setAlpha(115);
        U.setColor(-16777216);
        LinearLayout linearLayout = new LinearLayout(context);
        this.C = linearLayout;
        linearLayout.setBackgroundDrawable(U);
        this.C.setGravity(5);
        this.C.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
        S.setColor(IconFloorEntity.BGCOLOR_DEFAULT);
        S.setAlpha(101);
        S.setCornerRadii(new float[]{com.jingdong.app.mall.home.floor.common.d.d(17), com.jingdong.app.mall.home.floor.common.d.d(17), 0.0f, 0.0f, 0.0f, 0.0f, com.jingdong.app.mall.home.floor.common.d.d(17), com.jingdong.app.mall.home.floor.common.d.d(17)});
        TextView textView = new TextView(context);
        this.D = textView;
        textView.setTextColor(-1);
        this.D.setGravity(17);
        this.D.setBackgroundDrawable(S);
        this.D.setText("\u6d3b\u52a8\u5df2\u7ed3\u675f");
        f fVar = new f(R2.anim.pickerview_dialog_scale_in, 34);
        this.Q = fVar;
        fVar.F(new Rect(0, 123, 0, 0));
        LinearLayout linearLayout2 = this.C;
        TextView textView2 = this.D;
        linearLayout2.addView(textView2, this.Q.i(textView2));
        this.q.addView(this.C);
    }

    private void r() {
        com.jingdong.app.mall.home.x.a.b().i(this);
        this.C.setVisibility(0);
        this.A.setVisibility(4);
        this.r.bringToFront();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void s(TextView textView) {
        textView.setGravity(16);
        textView.setMaxLines(1);
        textView.setEllipsize(TextUtils.TruncateAt.END);
    }

    private void t(String str, String str2, String str3) {
        com.jingdong.app.mall.home.floor.view.widget.a aVar = this.z;
        if (aVar != null) {
            aVar.j(str);
            this.z.k(str2);
            this.z.l(str3);
            this.z.invalidateSelf();
        }
    }

    @Override // com.jingdong.app.mall.home.x.d
    public void a() {
        long x = ((e) this.f8679m).x();
        if (x < 1) {
            r();
            return;
        }
        String[] h2 = com.jingdong.app.mall.home.x.a.h(x * 1000);
        t(h2[0], h2[1], h2[2]);
    }

    @Override // com.jingdong.app.mall.home.category.floor.base.BaseCaRecycleItem
    protected boolean i() {
        return this.C.getVisibility() == 0;
    }

    @Override // com.jingdong.app.mall.home.category.floor.base.BaseCaRecycleItem
    /* renamed from: o  reason: merged with bridge method [inline-methods] */
    public void e(@NotNull e eVar) {
        p();
        int d = com.jingdong.app.mall.home.floor.common.d.d(com.jingdong.app.mall.home.o.a.f.M0() ? 24 : 0);
        int d2 = com.jingdong.app.mall.home.floor.common.d.d(12);
        float f2 = d;
        W.setCornerRadius(f2);
        V.setCornerRadius(d2);
        U.setCornerRadius(f2);
        S.setCornerRadii(new float[]{f2, f2, 0.0f, 0.0f, 0.0f, 0.0f, f2, f2});
        T.setCornerRadii(new float[]{f2, f2, 0.0f, 0.0f, 0.0f, 0.0f, f2, f2});
        this.z.h(com.jingdong.app.mall.home.floor.common.d.d(26));
        this.z.f(com.jingdong.app.mall.home.floor.common.d.d(26));
        this.z.n(com.jingdong.app.mall.home.floor.common.d.d(18));
        this.z.q(com.jingdong.app.mall.home.floor.common.d.d(18));
        f.c(this.q, this.F);
        f.c(this.w, this.H);
        f.c(this.x, this.I);
        f.c(this.t, this.M);
        f.c(this.r, this.G);
        f.c(this.u, this.K);
        this.v.setMaxWidth(com.jingdong.app.mall.home.floor.common.d.d(230));
        f.c(this.v, this.L);
        f.c(this.s, this.J);
        f.c(this.y, this.N);
        f.c(this.A, this.P);
        f.c(this.B, this.O);
        f.c(this.D, this.Q);
        f.c(this.E[0], this.R[0]);
        f.c(this.E[1], this.R[1]);
        f.c(this.E[2], this.R[2]);
        this.E[0].a(eVar.w(0));
        this.E[1].a(eVar.w(1));
        this.E[2].a(eVar.w(2));
        this.u.setTextSize(0, com.jingdong.app.mall.home.floor.common.d.d(32));
        this.u.setText(eVar.z());
        this.v.setTextSize(0, com.jingdong.app.mall.home.floor.common.d.d(22));
        String y = eVar.y();
        this.v.setText(y);
        c.l(TextUtils.isEmpty(y), this.y);
        this.B.setTextSize(0, com.jingdong.app.mall.home.floor.common.d.d(22));
        this.D.setTextSize(0, com.jingdong.app.mall.home.floor.common.d.d(22));
        com.jingdong.app.mall.home.n.h.e.d(this.q, com.jingdong.app.mall.home.floor.common.d.d(24));
        com.jingdong.app.mall.home.floor.ctrl.e.m(this.s, eVar.v(), com.jingdong.app.mall.home.floor.ctrl.e.w());
        String u = eVar.u();
        c.l(TextUtils.isEmpty(u), this.w);
        com.jingdong.app.mall.home.floor.ctrl.e.m(this.x, u, com.jingdong.app.mall.home.floor.ctrl.e.w());
    }
}
