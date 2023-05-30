package com.jingdong.app.mall.home.floor.view.linefloor.widget;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.GradientDrawable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.app.mall.home.R;
import com.jingdong.app.mall.home.floor.common.f;
import com.jingdong.app.mall.home.floor.common.i.m;
import com.jingdong.app.mall.home.floor.view.b.d;
import com.jingdong.app.mall.home.floor.view.b.g.b;
import com.jingdong.app.mall.home.floor.view.widget.SecKillSkuLabelText;
import com.jingdong.app.mall.home.n.h.e;
import com.jingdong.app.mall.home.state.dark.DarkWhiteBgImageView;
import com.jingdong.common.entity.Product;
import com.jingdong.common.unification.navigationbar.NavigationConstants;
import com.jingdong.jdsdk.utils.FontsUtil;
import com.jingdong.sdk.platform.business.personal.R2;

/* loaded from: classes4.dex */
public class SecKillProductLayout2Pcs extends SecKillProductAbstractLayout {
    private int q;
    private b r;
    private d.a s;
    private a t;
    private a u;
    private a v;
    private a w;
    private float x;
    private boolean y;

    /* loaded from: classes4.dex */
    static class a extends RelativeLayout {

        /* renamed from: g */
        private Context f9935g;

        /* renamed from: h */
        private DarkWhiteBgImageView f9936h;

        /* renamed from: i */
        private SimpleDraweeView f9937i;

        /* renamed from: j */
        private RelativeLayout f9938j;

        /* renamed from: k */
        private SecKillSkuLabelText f9939k;

        /* renamed from: l */
        private LinearLayout f9940l;

        /* renamed from: m */
        private TextView f9941m;

        /* renamed from: n */
        private TextView f9942n;
        private boolean o;
        private int p;

        public a(Context context) {
            super(context);
            this.f9935g = context;
        }

        private void g(float f2) {
            if (this.o) {
                return;
            }
            RelativeLayout relativeLayout = this.f9938j;
            if (relativeLayout != null) {
                relativeLayout.setScaleX(f2);
                this.f9938j.setScaleY(f2);
            }
            LinearLayout linearLayout = this.f9940l;
            if (linearLayout != null) {
                linearLayout.setScaleX(f2);
                this.f9940l.setScaleY(f2);
            }
        }

        public int h() {
            return this.p;
        }

        public void i(float f2, float f3) {
            setAlpha(f2);
            g(f3);
        }

        private void j(TextView textView) {
            textView.setIncludeFontPadding(false);
            textView.setSingleLine();
            textView.setEllipsize(TextUtils.TruncateAt.END);
            textView.setGravity(17);
        }

        public void k(float f2) {
            if (this.o) {
                setScaleX(f2);
                setScaleY(f2);
                return;
            }
            RelativeLayout relativeLayout = this.f9938j;
            if (relativeLayout != null) {
                relativeLayout.setScaleX(f2);
                this.f9938j.setScaleY(f2);
            }
        }

        public void d(b bVar, Product product, d.a aVar, int i2) {
            if (product == null || bVar == null) {
                return;
            }
            this.p = i2;
            this.o = product.msItemType == 2;
            int f0 = bVar.f0();
            f fVar = aVar.f9738c;
            RelativeLayout relativeLayout = this.f9938j;
            if (relativeLayout == null) {
                RelativeLayout relativeLayout2 = new RelativeLayout(this.f9935g);
                this.f9938j = relativeLayout2;
                relativeLayout2.setId(R.id.homefloor_child_item1);
                RelativeLayout.LayoutParams u = fVar.u(this.f9938j);
                u.addRule(14);
                addView(this.f9938j, u);
            } else {
                f.c(relativeLayout, fVar);
            }
            if (this.f9936h == null) {
                DarkWhiteBgImageView darkWhiteBgImageView = new DarkWhiteBgImageView(this.f9935g);
                this.f9936h = darkWhiteBgImageView;
                darkWhiteBgImageView.setScaleType(ImageView.ScaleType.FIT_XY);
                this.f9936h.setContentDescription("\u4eac\u4e1c\u79d2\u6740");
                this.f9936h.setId(R.id.homefloor_child_item1);
                this.f9938j.addView(this.f9936h, new RelativeLayout.LayoutParams(-1, -1));
            }
            f fVar2 = aVar.d;
            if (this.o && this.f9937i == null) {
                DarkWhiteBgImageView darkWhiteBgImageView2 = new DarkWhiteBgImageView(this.f9935g);
                this.f9937i = darkWhiteBgImageView2;
                darkWhiteBgImageView2.setScaleType(ImageView.ScaleType.FIT_XY);
                this.f9937i.setContentDescription("\u4eac\u4e1c\u79d2\u6740");
                RelativeLayout.LayoutParams u2 = fVar2.u(this.f9937i);
                u2.addRule(5, this.f9938j.getId());
                u2.addRule(3, this.f9938j.getId());
                addView(this.f9937i, u2);
            }
            SimpleDraweeView simpleDraweeView = this.f9937i;
            if (simpleDraweeView != null) {
                simpleDraweeView.setVisibility(this.o ? 0 : 8);
                f.c(this.f9937i, fVar2);
            }
            f fVar3 = aVar.f9741g;
            LinearLayout linearLayout = this.f9940l;
            if (linearLayout == null) {
                LinearLayout linearLayout2 = new LinearLayout(this.f9935g);
                this.f9940l = linearLayout2;
                linearLayout2.setOrientation(0);
                RelativeLayout.LayoutParams u3 = fVar3.u(this.f9940l);
                u3.addRule(3, this.f9936h.getId());
                u3.addRule(14);
                addView(this.f9940l, u3);
            } else {
                f.c(linearLayout, fVar3);
            }
            f fVar4 = aVar.f9739e;
            TextView textView = this.f9941m;
            if (textView == null) {
                TextView textView2 = new TextView(this.f9935g);
                this.f9941m = textView2;
                textView2.setTypeface(FontsUtil.getTypeFace(getContext()));
                this.f9940l.addView(this.f9941m, fVar4.i(this.f9941m));
                j(this.f9941m);
            } else {
                f.c(textView, fVar4);
            }
            this.f9941m.setMaxWidth(aVar.b.v());
            this.f9941m.setEllipsize(TextUtils.TruncateAt.END);
            this.f9941m.setTextSize(0, com.jingdong.app.mall.home.floor.common.d.d(28));
            this.f9941m.setTextColor(m.j(bVar.d0(), -1037525));
            String jsonString = com.jingdong.app.mall.home.r.e.b.getJsonString(product.prdObject, "finalPrice", "");
            if (f0 == 0 || TextUtils.isEmpty(jsonString)) {
                jsonString = product.getMiaoShaPrice();
            }
            this.f9941m.setText(TextUtils.isEmpty(jsonString) ? "" : bVar.e0(jsonString));
            f fVar5 = aVar.f9740f;
            fVar5.E(f0 == 1 ? 4 : 8, 0, 0, 0);
            fVar5.J(0, f0 != 1 ? 0 : 5, 0, 0);
            TextView textView3 = this.f9942n;
            if (textView3 == null) {
                TextView textView4 = new TextView(this.f9935g);
                this.f9942n = textView4;
                this.f9940l.addView(this.f9942n, fVar5.i(textView4));
                j(this.f9942n);
            } else {
                f.c(textView3, fVar5);
            }
            this.f9942n.setTextSize(0, com.jingdong.app.mall.home.floor.common.d.d(f0 == 1 ? 18 : 22));
            TextView textView5 = this.f9942n;
            textView5.setPaintFlags(textView5.getPaintFlags() & (-17));
            this.f9942n.setTextColor(f0 == 1 ? -381927 : -6316129);
            if (f0 == 1) {
                this.f9942n.setText("\u9884\u4f30\u4ef7");
            } else if (product.msItemType == 8) {
                if (TextUtils.isEmpty(product.getProvinceStockContent())) {
                    this.f9942n.setText("\u62fc\u56e2");
                } else {
                    this.f9942n.setText(product.getProvinceStockContent());
                }
            } else if (product.getIsNewGoods() == 1) {
                this.f9942n.setText(NavigationConstants.LABEL_NAME_NEW);
            } else {
                this.f9942n.setTypeface(FontsUtil.getTypeFace(getContext()));
                this.f9942n.setText(new SpannableString(com.jingdong.app.mall.home.category.floor.feedssub.a.e(product.getJdPriceNoNull())));
                this.f9942n.setPaintFlags(17);
                this.f9942n.setTextColor(-6316129);
            }
            this.f9942n.setVisibility((((this.f9941m.getPaint().measureText(this.f9941m.getText().toString()) + ((float) com.jingdong.app.mall.home.floor.common.d.d(f0 == 1 ? 4 : 8))) + this.f9942n.getPaint().measureText(this.f9942n.getText().toString())) > ((float) aVar.b.v()) ? 1 : (((this.f9941m.getPaint().measureText(this.f9941m.getText().toString()) + ((float) com.jingdong.app.mall.home.floor.common.d.d(f0 == 1 ? 4 : 8))) + this.f9942n.getPaint().measureText(this.f9942n.getText().toString())) == ((float) aVar.b.v()) ? 0 : -1)) > 0 ? 8 : 0);
            this.f9940l.setVisibility(this.o ? 8 : 0);
            k(1.0f);
            if (this.o) {
                e.h(this.f9936h, com.jingdong.app.mall.home.floor.common.d.d(8));
            } else {
                e.d(this.f9936h, com.jingdong.app.mall.home.floor.common.d.d(8));
            }
            this.f9936h.c(false);
            if (this.f9937i != null && this.o) {
                String jsonString2 = com.jingdong.app.mall.home.r.e.b.getJsonString(product.prdObject, bVar.z() ? "downImageurlW228H54" : "downImageurlW195H57", "");
                e.a(this.f9937i, com.jingdong.app.mall.home.floor.common.d.d(8));
                com.jingdong.app.mall.home.floor.ctrl.e.m(this.f9937i, jsonString2, com.jingdong.app.mall.home.floor.ctrl.e.b);
            }
            com.jingdong.app.mall.home.floor.ctrl.e.m(this.f9936h, product.getImageUrl(), com.jingdong.app.mall.home.floor.ctrl.e.b);
            f fVar6 = new f(f0 == 1 ? -1 : -2, 30);
            fVar6.K(new Rect(11, 0, 11, 0));
            SecKillSkuLabelText secKillSkuLabelText = this.f9939k;
            if (secKillSkuLabelText == null) {
                SecKillSkuLabelText secKillSkuLabelText2 = new SecKillSkuLabelText(getContext());
                this.f9939k = secKillSkuLabelText2;
                secKillSkuLabelText2.setIncludeFontPadding(false);
                this.f9939k.setMaxLines(1);
                this.f9939k.setEllipsize(TextUtils.TruncateAt.END);
                this.f9939k.setGravity(17);
                RelativeLayout.LayoutParams u4 = fVar6.u(this.f9939k);
                u4.addRule(12);
                u4.addRule(14);
                this.f9938j.addView(this.f9939k, u4);
            } else {
                f.c(secKillSkuLabelText, fVar6);
            }
            this.f9939k.setTextColor(f0 == 1 ? bVar.T() : -1);
            String jsonString3 = com.jingdong.app.mall.home.r.e.b.getJsonString(product.prdObject, "interestPoint", "");
            boolean z = (product.getTagType() == 15 && !TextUtils.isEmpty(product.getTagText())) || (f0 == 1 && !TextUtils.isEmpty(jsonString3));
            this.f9939k.setVisibility((this.o || !z) ? 8 : 0);
            if (this.o || !z) {
                return;
            }
            this.f9939k.setMaxWidth(fVar.v());
            this.f9939k.setTextSize(0, com.jingdong.app.mall.home.floor.common.d.d(f0 == 1 ? 18 : 22));
            SecKillSkuLabelText secKillSkuLabelText3 = this.f9939k;
            if (f0 != 1) {
                jsonString3 = com.jingdong.app.mall.home.o.a.f.j(6, product.getTagText());
            }
            secKillSkuLabelText3.setText(jsonString3);
            if (f0 == 1) {
                if (this.f9939k.getPaint().measureText(this.f9939k.getText().toString()) > this.f9939k.getMaxWidth() - com.jingdong.app.mall.home.floor.common.d.d(22)) {
                    this.f9939k.setVisibility(8);
                    return;
                }
                this.f9939k.h(true);
                this.f9939k.g(bVar.j0());
                e.a(this.f9939k, com.jingdong.app.mall.home.floor.common.d.d(8));
                return;
            }
            this.f9939k.h(false);
            e.a(this.f9939k, 0);
            int[] i0 = bVar.i0();
            if (i0 != null && i0.length != 0) {
                GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, com.jingdong.app.mall.home.floor.view.b.h.a.f(i0, R2.anim.slide_out_to_bottom, 255));
                gradientDrawable.setShape(0);
                gradientDrawable.setCornerRadius(com.jingdong.app.mall.home.floor.common.d.d(15));
                this.f9939k.setBackgroundDrawable(gradientDrawable);
                return;
            }
            this.f9939k.setBackgroundDrawable(null);
        }

        public void e(int i2) {
            if (i2 >= 200 && i2 < 400) {
                setAlpha(((float) (i2 - 200)) / 200.0f);
            }
            if (i2 >= 100 && i2 < 400) {
                g(((((float) (i2 - 100)) * 0.15f) / 300.0f) + 0.85f);
            }
            if (i2 < 400 || i2 >= 450) {
                return;
            }
            setAlpha(1.0f);
            g(1.0f);
        }

        public void f(int i2) {
            if (i2 >= 100 && i2 < 300) {
                setAlpha((300 - i2) / 200.0f);
            }
            if (i2 < 300) {
                g(1.0f - ((i2 * 0.15f) / 300));
            }
            if (i2 < 300 || i2 >= 350) {
                return;
            }
            setAlpha(0.0f);
            g(0.85f);
        }
    }

    public SecKillProductLayout2Pcs(Context context, SecKillBottomProductView secKillBottomProductView) {
        super(context, secKillBottomProductView);
    }

    @Override // com.jingdong.app.mall.home.floor.view.b.f.g
    public int a() {
        return this.q;
    }

    @Override // com.jingdong.app.mall.home.floor.view.b.f.g
    public void e(int i2) {
        b bVar;
        if (i2 == 0 && (bVar = this.r) != null) {
            com.jingdong.app.mall.home.floor.view.b.f.d.a(bVar.R(this.q), this.r.Q());
        }
        this.s.a = true;
    }

    @Override // com.jingdong.app.mall.home.floor.view.b.f.g
    public boolean f() {
        return this.y;
    }

    @Override // com.jingdong.app.mall.home.floor.view.b.f.g
    public boolean g() {
        return this.s.a;
    }

    @Override // com.jingdong.app.mall.home.floor.view.b.f.g
    public void h(float f2) {
        a aVar = this.v;
        if (aVar != null) {
            aVar.k(f2);
        }
    }

    @Override // com.jingdong.app.mall.home.floor.view.b.f.g
    public boolean isVisible() {
        return m.H(this, com.jingdong.app.mall.home.a.f8560i, com.jingdong.app.mall.home.a.f8562k, 100, true);
    }

    @Override // com.jingdong.app.mall.home.floor.view.b.f.g
    public void onEnd(boolean z) {
        a aVar = this.v;
        if (aVar != null) {
            aVar.k(1.0f);
        }
        if (z) {
            this.p.o();
        }
    }

    @Override // com.jingdong.app.mall.home.floor.view.linefloor.widget.SecKillProductAbstractLayout
    public void q(d.a aVar, b bVar, int i2) {
        n();
        this.q = i2;
        this.r = bVar;
        this.s = aVar;
        Product U = bVar.U(i2);
        int i3 = i2 + 2;
        Product U2 = bVar.U(i3);
        if (U == null) {
            return;
        }
        f fVar = new f(-2, -1);
        if (this.u == null) {
            a aVar2 = new a(this.o);
            this.u = aVar2;
            RelativeLayout.LayoutParams u = fVar.u(aVar2);
            u.addRule(14);
            addView(this.u, u);
        }
        this.u.i(0.0f, 1.0f);
        this.w = this.u;
        if (this.t == null) {
            a aVar3 = new a(this.o);
            this.t = aVar3;
            RelativeLayout.LayoutParams u2 = fVar.u(aVar3);
            u2.addRule(14);
            addView(this.t, u2);
        }
        this.t.i(1.0f, 1.0f);
        a aVar4 = this.t;
        this.v = aVar4;
        aVar4.d(bVar, U, aVar, i2);
        this.u.d(bVar, U2, aVar, i3);
        if (bVar.v()) {
            return;
        }
        this.y = com.jingdong.app.mall.home.floor.view.b.f.d.f(this.r.R(this.q), this.r.Q());
        com.jingdong.app.mall.home.floor.view.b.f.e.j().e(this);
    }

    @Override // com.jingdong.app.mall.home.floor.view.linefloor.widget.SecKillProductAbstractLayout
    public int r() {
        return (this.x < 250.0f ? this.v : this.w).h();
    }

    @Override // com.jingdong.app.mall.home.floor.view.linefloor.widget.SecKillProductAbstractLayout
    public void t() {
        a aVar = this.v;
        a aVar2 = this.w;
        this.v = aVar2;
        this.w = aVar;
        this.x = 0.0f;
        if (aVar2 != null) {
            aVar2.i(1.0f, 1.0f);
        }
        a aVar3 = this.w;
        if (aVar3 != null) {
            aVar3.i(0.0f, 0.85f);
        }
    }

    @Override // com.jingdong.app.mall.home.floor.view.linefloor.widget.SecKillProductAbstractLayout
    public void u() {
        a aVar = this.v;
        if (aVar != null) {
            aVar.i(1.0f, 1.0f);
        }
        a aVar2 = this.w;
        if (aVar2 != null) {
            aVar2.i(0.0f, 0.85f);
        }
        this.x = 0.0f;
    }

    @Override // com.jingdong.app.mall.home.floor.view.linefloor.widget.SecKillProductAbstractLayout
    public void v(int i2) {
        this.x = i2;
        a aVar = this.v;
        if (aVar != null) {
            aVar.f(i2);
        }
        a aVar2 = this.w;
        if (aVar2 != null) {
            aVar2.e(i2);
        }
    }
}
