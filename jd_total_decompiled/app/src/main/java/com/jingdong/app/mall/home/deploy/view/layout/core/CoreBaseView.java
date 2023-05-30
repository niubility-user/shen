package com.jingdong.app.mall.home.deploy.view.layout.core;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.widget.RelativeLayout;
import com.jd.dynamic.DYConstants;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.home.deploy.view.base.BaseModel;
import com.jingdong.app.mall.home.deploy.view.base.BaseView;
import com.jingdong.app.mall.home.floor.common.i.g;
import com.jingdong.app.mall.home.floor.view.b.h.a;
import com.jingdong.app.mall.home.floor.view.widget.FitTopImage;
import com.jingdong.app.mall.home.n.h.e;
import com.jingdong.app.mall.home.r.e.f;

/* loaded from: classes4.dex */
public abstract class CoreBaseView extends BaseView {

    /* renamed from: l */
    private GradientDrawable f8917l;

    /* renamed from: m */
    private String f8918m;

    /* renamed from: n */
    protected final FitTopImage f8919n;
    protected int o;

    public CoreBaseView(Context context) {
        super(context);
        this.o = -1;
        FitTopImage fitTopImage = new FitTopImage(context);
        this.f8919n = fitTopImage;
        addView(fitTopImage, new RelativeLayout.LayoutParams(-1, -1));
    }

    @Override // com.jingdong.app.mall.home.deploy.view.base.BaseView
    public void b(BaseModel baseModel) {
        int[] e2;
        f e3 = baseModel.e();
        if (e3 != null && !baseModel.s()) {
            this.f8919n.setVisibility(0);
            JDJSONObject jsonObject = e3.getJsonObject("bgInfo");
            if (jsonObject != null) {
                this.f8918m = jsonObject.getString("img");
                e2 = a.e(jsonObject.getString("color"), this.o);
            } else {
                this.f8918m = e3.getJsonString("bgImg");
                e2 = a.e(e3.getJsonString(DYConstants.DY_BG_COLOR), this.o);
            }
            int[] iArr = new int[2];
            a.h(e2, iArr);
            this.f8917l = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, iArr);
            int s = s();
            float[] r = r(s);
            if (r == null) {
                e.d(this.f8919n, s);
                this.f8917l.setCornerRadius(s);
            } else {
                this.f8917l.setCornerRadii(r);
            }
        } else {
            this.f8919n.setVisibility(8);
        }
        super.b(baseModel);
    }

    @Override // com.jingdong.app.mall.home.deploy.view.base.BaseView
    public void k() {
    }

    @Override // com.jingdong.app.mall.home.deploy.view.base.BaseView
    public void m() {
        com.jingdong.app.mall.home.floor.ctrl.e.m(this.f8919n, this.f8918m, this.f8917l);
    }

    @Override // com.jingdong.app.mall.home.deploy.view.base.BaseView
    public void q() {
        super.q();
    }

    protected float[] r(int i2) {
        return null;
    }

    public int s() {
        return g.d();
    }
}
