package com.jingdong.app.mall.home.deploy.view.layout.year;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.widget.RelativeLayout;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.home.deploy.view.base.BaseModel;
import com.jingdong.app.mall.home.deploy.view.base.BaseView;
import com.jingdong.app.mall.home.floor.common.i.g;
import com.jingdong.app.mall.home.floor.ctrl.e;
import com.jingdong.app.mall.home.floor.view.b.h.a;
import com.jingdong.app.mall.home.floor.view.widget.FitTopImage;
import com.jingdong.app.mall.home.r.e.f;

/* loaded from: classes4.dex */
public abstract class YearBaseView extends BaseView {

    /* renamed from: l */
    private GradientDrawable f9098l;

    /* renamed from: m */
    private String f9099m;

    /* renamed from: n */
    private final FitTopImage f9100n;
    protected int[] o;

    public YearBaseView(Context context) {
        super(context);
        this.o = new int[]{-1, -1};
        FitTopImage fitTopImage = new FitTopImage(context);
        this.f9100n = fitTopImage;
        addView(fitTopImage, new RelativeLayout.LayoutParams(-1, -1));
    }

    @Override // com.jingdong.app.mall.home.deploy.view.base.BaseView
    public void b(BaseModel baseModel) {
        f e2 = baseModel.e();
        if (e2 != null) {
            JDJSONObject jsonObject = e2.getJsonObject("bgInfo");
            int[] iArr = new int[2];
            int[] iArr2 = this.o;
            if (jsonObject != null) {
                this.f9099m = jsonObject.getString("img");
                iArr2 = a.e(jsonObject.getString("color"), this.o);
            }
            a.h(iArr2, iArr);
            GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, iArr);
            this.f9098l = gradientDrawable;
            gradientDrawable.setCornerRadius(r());
        }
        super.b(baseModel);
    }

    @Override // com.jingdong.app.mall.home.deploy.view.base.BaseView
    public void k() {
    }

    @Override // com.jingdong.app.mall.home.deploy.view.base.BaseView
    public void m() {
        e.m(this.f9100n, this.f9099m, this.f9098l);
        com.jingdong.app.mall.home.n.h.e.d(this.f9100n, r());
    }

    @Override // com.jingdong.app.mall.home.deploy.view.base.BaseView
    public void q() {
        super.q();
    }

    protected int r() {
        return g.d();
    }
}
