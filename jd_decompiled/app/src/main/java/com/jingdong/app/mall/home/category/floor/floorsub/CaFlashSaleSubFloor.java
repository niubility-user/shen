package com.jingdong.app.mall.home.category.floor.floorsub;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.facebook.drawee.drawable.ScalingUtils;
import com.jingdong.app.mall.home.R;
import com.jingdong.app.mall.home.category.floor.base.BaseCaRecycleItem;
import com.jingdong.app.mall.home.category.widget.CaProgress;
import com.jingdong.app.mall.home.floor.common.d;
import com.jingdong.app.mall.home.floor.ctrl.e;
import com.jingdong.app.mall.home.n.g.x.f;
import com.jingdong.app.mall.home.state.dark.DarkWhiteBgImageView;
import com.jingdong.jdsdk.utils.FontsUtil;
import com.jingdong.sdk.platform.business.personal.R2;
import org.jetbrains.annotations.NotNull;

/* loaded from: classes4.dex */
public class CaFlashSaleSubFloor extends BaseCaRecycleItem<f> {
    private static Drawable E = e.w();
    private com.jingdong.app.mall.home.floor.common.f A;
    private com.jingdong.app.mall.home.floor.common.f B;
    private com.jingdong.app.mall.home.floor.common.f C;
    private com.jingdong.app.mall.home.floor.common.f D;
    private DarkWhiteBgImageView q;
    private LinearLayout r;
    private TextView s;
    private TextView t;
    private CaProgress u;
    private TextView v;
    private TextView w;
    private com.jingdong.app.mall.home.floor.common.f x;
    private com.jingdong.app.mall.home.floor.common.f y;
    private com.jingdong.app.mall.home.floor.common.f z;

    public CaFlashSaleSubFloor(Context context) {
        super(context);
        setContentDescription("\u9650\u65f6\u62a2\u8d2d");
        DarkWhiteBgImageView darkWhiteBgImageView = new DarkWhiteBgImageView(context);
        this.q = darkWhiteBgImageView;
        darkWhiteBgImageView.setId(R.id.mallfloor_item1);
        this.q.setScaleType(ImageView.ScaleType.FIT_XY);
        this.q.setBackgroundColor(-328966);
        this.q.getHierarchy().setActualImageScaleType(ScalingUtils.ScaleType.FIT_XY);
        com.jingdong.app.mall.home.floor.common.f fVar = new com.jingdong.app.mall.home.floor.common.f(R2.anim.settlement_dialog_right_enter, R2.anim.settlement_dialog_right_enter);
        this.x = fVar;
        RelativeLayout.LayoutParams u = fVar.u(this.q);
        u.addRule(14);
        addView(this.q, u);
        LinearLayout linearLayout = new LinearLayout(context);
        this.r = linearLayout;
        linearLayout.setGravity(1);
        e.a(this.r, "2624");
        com.jingdong.app.mall.home.floor.common.f fVar2 = new com.jingdong.app.mall.home.floor.common.f(130, 42);
        this.C = fVar2;
        fVar2.K(new Rect(7, 0, 3, 9));
        RelativeLayout.LayoutParams u2 = this.C.u(this.r);
        u2.addRule(7, this.q.getId());
        u2.addRule(6, this.q.getId());
        addView(this.r, u2);
        TextView textView = new TextView(context);
        this.w = textView;
        textView.setGravity(17);
        this.w.setTextColor(-3393485);
        this.w.setSingleLine(true);
        this.w.getPaint().setFakeBoldText(true);
        this.w.setEllipsize(TextUtils.TruncateAt.END);
        com.jingdong.app.mall.home.floor.common.f fVar3 = new com.jingdong.app.mall.home.floor.common.f(-1, -1);
        this.D = fVar3;
        LinearLayout linearLayout2 = this.r;
        TextView textView2 = this.w;
        linearLayout2.addView(textView2, fVar3.i(textView2));
        TextView textView3 = new TextView(context);
        this.t = textView3;
        textView3.setGravity(17);
        this.t.setIncludeFontPadding(false);
        this.t.setMaxLines(1);
        this.t.setEllipsize(TextUtils.TruncateAt.END);
        this.t.setTextColor(-907508);
        this.t.setTypeface(FontsUtil.getTypeFace(context, 4099));
        com.jingdong.app.mall.home.floor.common.f fVar4 = new com.jingdong.app.mall.home.floor.common.f(-1, 34);
        this.z = fVar4;
        fVar4.F(new Rect(0, 9, 0, 0));
        RelativeLayout.LayoutParams u3 = this.z.u(this.t);
        u3.addRule(3, this.q.getId());
        addView(this.t, u3);
        TextView textView4 = new TextView(context);
        this.s = textView4;
        textView4.setGravity(17);
        this.s.setIncludeFontPadding(false);
        this.s.setMaxLines(1);
        this.s.setEllipsize(TextUtils.TruncateAt.END);
        this.s.setTypeface(FontsUtil.getTypeFace(context, 4099));
        this.s.setTextColor(-7566196);
        this.s.getPaint().setFlags(17);
        com.jingdong.app.mall.home.floor.common.f fVar5 = new com.jingdong.app.mall.home.floor.common.f(-1, 30);
        this.y = fVar5;
        fVar5.F(new Rect(0, 40, 0, 0));
        RelativeLayout.LayoutParams u4 = this.y.u(this.s);
        u4.addRule(3, this.q.getId());
        addView(this.s, u4);
        CaProgress caProgress = new CaProgress(context);
        this.u = caProgress;
        caProgress.a(R2.anim.settlement_dialog_right_enter, 10);
        this.u.f(-322794, -301974);
        this.u.c(true, -6429, -1);
        com.jingdong.app.mall.home.floor.common.f fVar6 = new com.jingdong.app.mall.home.floor.common.f(R2.anim.settlement_dialog_right_enter, 10);
        this.A = fVar6;
        fVar6.F(new Rect(0, 79, 0, 0));
        RelativeLayout.LayoutParams u5 = this.A.u(this.u);
        u5.addRule(14);
        u5.addRule(3, this.q.getId());
        addView(this.u, u5);
        TextView textView5 = new TextView(context);
        this.v = textView5;
        textView5.setGravity(17);
        this.v.setMaxLines(1);
        this.v.setEllipsize(TextUtils.TruncateAt.END);
        com.jingdong.app.mall.home.floor.common.f fVar7 = new com.jingdong.app.mall.home.floor.common.f(-1, 30);
        this.B = fVar7;
        fVar7.F(new Rect(0, 99, 0, 0));
        RelativeLayout.LayoutParams u6 = this.B.u(this.v);
        u6.addRule(3, this.q.getId());
        addView(this.v, u6);
    }

    @Override // com.jingdong.app.mall.home.category.floor.base.BaseCaRecycleItem
    /* renamed from: n  reason: merged with bridge method [inline-methods] */
    public void e(@NotNull f fVar) {
        com.jingdong.app.mall.home.floor.common.f.c(this.q, this.x);
        com.jingdong.app.mall.home.floor.common.f.c(this.t, this.z);
        com.jingdong.app.mall.home.floor.common.f.c(this.s, this.y);
        com.jingdong.app.mall.home.floor.common.f.c(this.u, this.A);
        com.jingdong.app.mall.home.floor.common.f.c(this.v, this.B);
        com.jingdong.app.mall.home.floor.common.f.c(this.r, this.C);
        com.jingdong.app.mall.home.floor.common.f.c(this.w, this.D);
        com.jingdong.app.mall.home.n.h.e.h(this.r, d.d(12));
        this.t.setTextSize(0, d.d(28));
        this.s.setTextSize(0, d.d(20));
        this.w.setTextSize(0, d.d(20));
        this.v.setTextSize(0, d.d(20));
        e.m(this.q, fVar.i(), E);
        this.t.setText(fVar.x());
        this.s.setText(fVar.u());
        this.v.setText(fVar.w());
        this.v.setTextColor(-7566196);
        String y = fVar.y();
        this.r.setVisibility(TextUtils.isEmpty(y) ? 8 : 0);
        this.w.setText(y);
        if (fVar.B()) {
            this.u.h(fVar.v() / 100.0f, 1000L);
        } else {
            this.u.e(fVar.v() / 100.0f);
        }
        com.jingdong.app.mall.home.n.h.e.d(this.q, d.d(16));
        com.jingdong.app.mall.home.n.h.e.h(this.r, d.d(10));
    }
}
