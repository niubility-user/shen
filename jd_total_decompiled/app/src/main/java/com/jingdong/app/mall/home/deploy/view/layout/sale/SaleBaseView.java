package com.jingdong.app.mall.home.deploy.view.layout.sale;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jd.dynamic.DYConstants;
import com.jingdong.app.mall.home.base.HomeDraweeView;
import com.jingdong.app.mall.home.deploy.view.base.BaseModel;
import com.jingdong.app.mall.home.deploy.view.base.BaseView;
import com.jingdong.app.mall.home.floor.common.i.g;
import com.jingdong.app.mall.home.floor.ctrl.e;
import com.jingdong.app.mall.home.floor.view.b.h.a;
import com.jingdong.app.mall.home.r.e.f;

/* loaded from: classes4.dex */
public abstract class SaleBaseView extends BaseView {

    /* renamed from: l  reason: collision with root package name */
    private String f8947l;

    /* renamed from: m  reason: collision with root package name */
    private GradientDrawable f8948m;

    /* renamed from: n  reason: collision with root package name */
    private final SimpleDraweeView f8949n;

    public SaleBaseView(Context context) {
        super(context);
        HomeDraweeView homeDraweeView = new HomeDraweeView(context);
        this.f8949n = homeDraweeView;
        homeDraweeView.setScaleType(ImageView.ScaleType.FIT_XY);
        addView(homeDraweeView, new RelativeLayout.LayoutParams(-1, -1));
    }

    @Override // com.jingdong.app.mall.home.deploy.view.base.BaseView
    public void b(BaseModel baseModel) {
        f e2 = baseModel.e();
        if (e2 != null) {
            this.f8947l = e2.getJsonString("bgImg");
            int[] iArr = new int[2];
            a.h(a.e(e2.getJsonString(DYConstants.DY_BG_COLOR), -1), iArr);
            GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, iArr);
            this.f8948m = gradientDrawable;
            gradientDrawable.setCornerRadius(r());
        }
        super.b(baseModel);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.home.deploy.view.base.BaseView
    public void k() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.home.deploy.view.base.BaseView
    public void m() {
        e.m(this.f8949n, this.f8947l, this.f8948m);
        com.jingdong.app.mall.home.n.h.e.d(this.f8949n, r());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.home.deploy.view.base.BaseView
    public void q() {
        super.q();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int r() {
        return g.d();
    }
}
