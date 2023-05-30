package com.jingdong.app.mall.home.category.floor.floorsub;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.app.mall.home.R;
import com.jingdong.app.mall.home.base.HomeDraweeView;
import com.jingdong.app.mall.home.category.floor.base.BaseCaRecycleItem;
import com.jingdong.app.mall.home.floor.common.d;
import com.jingdong.app.mall.home.floor.common.f;
import com.jingdong.app.mall.home.floor.common.g;
import com.jingdong.app.mall.home.floor.ctrl.e;
import com.jingdong.app.mall.home.floor.view.view.GradientTextView;
import com.jingdong.app.mall.home.n.g.x.m;
import com.jingdong.app.mall.home.n.h.c;
import org.jetbrains.annotations.NotNull;

/* loaded from: classes4.dex */
public class CaRankingMergeSubFloor extends BaseCaRecycleItem<m> {
    private static int A = -15066598;
    private static Drawable B = e.w();
    private SimpleDraweeView q;
    private f r;
    private SimpleDraweeView s;
    private f t;
    private TextView u;
    private f v;
    private GradientTextView w;
    private f x;
    private SimpleDraweeView y;
    private f z;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class a implements e.b {
        a() {
        }

        @Override // com.jingdong.app.mall.home.floor.ctrl.e.b
        public void onFailed(String str, View view) {
            c.k(false, CaRankingMergeSubFloor.this.y);
        }

        @Override // com.jingdong.app.mall.home.floor.ctrl.e.b
        public void onStart(String str, View view) {
        }

        @Override // com.jingdong.app.mall.home.floor.ctrl.e.b
        public void onSuccess(String str, View view) {
            c.k(true, CaRankingMergeSubFloor.this.y);
        }
    }

    public CaRankingMergeSubFloor(Context context) {
        super(context);
        HomeDraweeView homeDraweeView = new HomeDraweeView(context);
        this.q = homeDraweeView;
        homeDraweeView.setScaleType(ImageView.ScaleType.FIT_XY);
        this.q.setId(R.id.home_category_floor_item_bg);
        f fVar = new f(228, 276);
        this.r = fVar;
        RelativeLayout.LayoutParams u = fVar.u(this.q);
        u.addRule(14);
        addView(this.q, u);
        HomeDraweeView homeDraweeView2 = new HomeDraweeView(context);
        this.s = homeDraweeView2;
        homeDraweeView2.setScaleType(ImageView.ScaleType.FIT_XY);
        f fVar2 = new f(144, 144);
        this.t = fVar2;
        fVar2.F(new Rect(0, 0, 0, 76));
        RelativeLayout.LayoutParams u2 = this.t.u(this.s);
        u2.addRule(8, this.q.getId());
        u2.addRule(14);
        addView(this.s, u2);
        TextView textView = new TextView(context);
        this.u = textView;
        textView.setGravity(17);
        this.u.setSingleLine(true);
        this.u.setEllipsize(TextUtils.TruncateAt.END);
        this.u.getPaint().setFakeBoldText(true);
        f.O(this.u, 24);
        this.u.setTextColor(A);
        this.u.setMaxWidth(d.d(228));
        f fVar3 = new f(-2, -2);
        this.v = fVar3;
        fVar3.F(new Rect(20, 0, 20, 32));
        RelativeLayout.LayoutParams u3 = this.v.u(this.u);
        u3.addRule(14);
        u3.addRule(8, this.q.getId());
        addView(this.u, u3);
        g gVar = new g(context, true);
        gVar.f(1);
        gVar.d(17);
        GradientTextView b = gVar.b();
        this.w = b;
        b.setEllipsize(TextUtils.TruncateAt.END);
        f.O(this.w, 20);
        this.w.setMaxWidth(d.d(228));
        f fVar4 = new f(-2, -2);
        this.x = fVar4;
        fVar4.F(new Rect(20, 0, 20, 6));
        RelativeLayout.LayoutParams u4 = this.x.u(this.w);
        u4.addRule(14);
        u4.addRule(8, this.q.getId());
        addView(this.w, u4);
        HomeDraweeView homeDraweeView3 = new HomeDraweeView(context);
        this.y = homeDraweeView3;
        homeDraweeView3.setScaleType(ImageView.ScaleType.FIT_XY);
        f fVar5 = new f(130, 32);
        this.z = fVar5;
        fVar5.F(new Rect(0, 16, 0, 0));
        RelativeLayout.LayoutParams u5 = this.z.u(this.y);
        u5.addRule(14);
        addView(this.y, u5);
        this.y.setImageResource(R.drawable.home_category_rank_default_name);
    }

    @Override // com.jingdong.app.mall.home.category.floor.base.BaseCaRecycleItem
    /* renamed from: o  reason: merged with bridge method [inline-methods] */
    public void e(@NotNull m mVar) {
        this.u.setText(mVar.j());
        this.w.setText(mVar.w());
        this.w.setTextGradient(GradientTextView.GradientType.LeftToRight, mVar.v());
        e.m(this.s, mVar.i(), B);
        e.p(this.q, mVar.u(), e.f9398c, new a());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.home.category.floor.base.BaseCaRecycleItem
    /* renamed from: p  reason: merged with bridge method [inline-methods] */
    public void f(m mVar) {
        super.f(mVar);
        f.O(this.u, 24);
        f.O(this.w, 20);
        com.jingdong.app.mall.home.n.h.e.d(this.q, d.d(20));
        com.jingdong.app.mall.home.n.h.e.d(this.s, d.d(12));
        f.c(this.q, this.r);
        f.c(this.s, this.t);
        f.c(this.u, this.v);
        f.c(this.w, this.x);
        f.c(this.y, this.z);
    }
}
