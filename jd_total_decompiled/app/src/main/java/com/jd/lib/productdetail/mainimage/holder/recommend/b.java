package com.jd.lib.productdetail.mainimage.holder.recommend;

import android.view.View;
import com.jd.lib.productdetail.mainimage.old.h0;
import com.jd.lib.productdetail.mainimage.presenter.PdMainImagePresenter;
import com.jingdong.common.BaseActivity;

/* loaded from: classes15.dex */
public class b implements View.OnClickListener {

    /* renamed from: g */
    public final /* synthetic */ PdMImageRecommendNewView f4931g;

    public b(PdMImageRecommendNewView pdMImageRecommendNewView) {
        this.f4931g = pdMImageRecommendNewView;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        h0 h0Var;
        PdMImageRecommendNewView pdMImageRecommendNewView = this.f4931g;
        PdMainImagePresenter pdMainImagePresenter = pdMImageRecommendNewView.p;
        if (pdMainImagePresenter == null || (h0Var = pdMainImagePresenter.mTopImageRecommendContainer) == null) {
            return;
        }
        h0Var.b((BaseActivity) pdMImageRecommendNewView.f4908h, pdMImageRecommendNewView.v, pdMImageRecommendNewView.w, pdMImageRecommendNewView.x, pdMImageRecommendNewView.y, pdMImageRecommendNewView.z);
    }
}
