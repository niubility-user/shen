package com.jd.lib.productdetail.mainimage.holder.recommend;

import com.jd.lib.productdetail.mainimage.old.PdMPartsRecommendView;
import com.jd.lib.productdetail.mainimage.presenter.PdMainImagePresenter;
import com.jingdong.common.unification.dialog.UnBottomDialog;

/* loaded from: classes15.dex */
public class d implements PdMPartsRecommendView.a {
    public final /* synthetic */ PdMImageRecommendNewView a;

    public d(PdMImageRecommendNewView pdMImageRecommendNewView) {
        this.a = pdMImageRecommendNewView;
    }

    @Override // com.jd.lib.productdetail.mainimage.old.PdMPartsRecommendView.a
    public void a() {
        UnBottomDialog unBottomDialog;
        PdMainImagePresenter pdMainImagePresenter = this.a.p;
        if (pdMainImagePresenter == null || (unBottomDialog = pdMainImagePresenter.mLayerDialog) == null) {
            return;
        }
        unBottomDialog.dismiss();
    }
}
