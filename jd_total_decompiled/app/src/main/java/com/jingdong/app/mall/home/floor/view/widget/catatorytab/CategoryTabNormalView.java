package com.jingdong.app.mall.home.floor.view.widget.catatorytab;

import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.app.mall.home.R;
import com.jingdong.app.mall.home.base.HomeDraweeView;
import com.jingdong.app.mall.home.floor.common.d;
import com.jingdong.app.mall.home.floor.common.f;
import com.jingdong.app.mall.home.floor.ctrl.e;
import com.jingdong.app.mall.home.floor.model.entity.CategoryEntity;

/* loaded from: classes4.dex */
public class CategoryTabNormalView extends CategoryTabItemBaseView {

    /* renamed from: j  reason: collision with root package name */
    protected TextView f10251j;

    /* renamed from: k  reason: collision with root package name */
    protected SimpleDraweeView f10252k;

    /* renamed from: l  reason: collision with root package name */
    private f f10253l;

    /* renamed from: m  reason: collision with root package name */
    private f f10254m;

    /* renamed from: n  reason: collision with root package name */
    protected e.b f10255n;
    private int o;

    /* loaded from: classes4.dex */
    class a implements e.b {
        a() {
        }

        @Override // com.jingdong.app.mall.home.floor.ctrl.e.b
        public void onFailed(String str, View view) {
            CategoryTabNormalView.this.k();
        }

        @Override // com.jingdong.app.mall.home.floor.ctrl.e.b
        public void onStart(String str, View view) {
            CategoryTabNormalView.this.l();
        }

        @Override // com.jingdong.app.mall.home.floor.ctrl.e.b
        public void onSuccess(String str, View view) {
            CategoryTabNormalView.this.j();
        }
    }

    public CategoryTabNormalView(Context context) {
        super(context);
        this.f10255n = new a();
    }

    @Override // com.jingdong.app.mall.home.floor.view.widget.catatorytab.CategoryTabItemBaseView
    public void a() {
        this.f10251j.setText(this.f10246g.isSelect() ? this.f10246g.getWords2() : this.f10246g.getWords1());
        this.f10251j.getPaint().setFakeBoldText(this.f10246g.isSelect());
        this.f10251j.setTextSize(0, d.d(this.f10246g.isSelect() ? this.f10246g.mCategoryEntity.getSelectSize() : this.f10246g.mCategoryEntity.getUnSelectSize()));
        this.f10251j.setTextColor(this.f10246g.isSelect() ? this.f10246g.mCategoryEntity.getSelectColor() : this.f10246g.mCategoryEntity.getUnSelectColor());
        f.d(this.f10251j, this.f10253l, true);
        if (this.o != 0) {
            e.p(this.f10252k, this.f10246g.isSelect() ? this.f10246g.getSelectImg() : this.f10246g.getUnSelectImg1(), e.b, this.f10255n);
        }
    }

    @Override // com.jingdong.app.mall.home.floor.view.widget.catatorytab.CategoryTabItemBaseView
    public void c(CategoryEntity.CaItem caItem, int i2) {
        int imgType = caItem.getImgType();
        this.o = imgType;
        int i3 = imgType != 1 ? imgType != 2 ? imgType != 3 ? -2 : 160 : 126 : 96;
        int max = Math.max(12, this.f10248i);
        if (this.f10251j == null) {
            TextView textView = new TextView(getContext());
            this.f10251j = textView;
            textView.setMaxLines(1);
            this.f10251j.setId(R.id.mallfloor_item1);
            this.f10251j.setGravity(17);
            f fVar = new f(-2, -1);
            this.f10253l = fVar;
            fVar.K(new Rect(max, 0, max, 0));
            RelativeLayout.LayoutParams u = this.f10253l.u(this.f10251j);
            u.addRule(13);
            addView(this.f10251j, u);
        } else {
            this.f10253l.K(new Rect(max, 0, max, 0));
            f.d(this.f10251j, this.f10253l, true);
        }
        if (this.f10252k == null) {
            HomeDraweeView homeDraweeView = new HomeDraweeView(getContext());
            this.f10252k = homeDraweeView;
            homeDraweeView.setScaleType(ImageView.ScaleType.FIT_XY);
            f fVar2 = new f(i3, 44);
            this.f10254m = fVar2;
            fVar2.K(new Rect(4, 0, 4, 0));
            RelativeLayout.LayoutParams u2 = this.f10254m.u(this.f10252k);
            u2.addRule(13);
            addView(this.f10252k, u2);
        } else {
            this.f10254m.Q(i3);
            f.d(this.f10252k, this.f10254m, true);
        }
        this.f10251j.setText(caItem.getTabName());
        if (!this.f10246g.isImageType()) {
            this.f10252k.setVisibility(4);
        } else {
            this.f10252k.setVisibility(0);
        }
        this.f10251j.setVisibility(0);
    }

    @Override // com.jingdong.app.mall.home.floor.view.widget.catatorytab.CategoryTabItemBaseView
    public void h() {
    }

    @Override // com.jingdong.app.mall.home.floor.view.widget.catatorytab.CategoryTabItemBaseView
    public void i() {
    }

    public void j() {
        g(this.f10251j, 4);
    }

    public void k() {
        g(this.f10251j, 0);
    }

    public void l() {
        g(this.f10251j, 0);
    }
}
