package com.jd.lib.productdetail.mainimage.old;

import android.view.View;
import com.jd.lib.productdetail.core.entitys.PreferentialRecommendTabItemEntity;

/* loaded from: classes15.dex */
public class f0 implements View.OnClickListener {

    /* renamed from: g  reason: collision with root package name */
    public final /* synthetic */ PdMPartsRecommendView f5152g;

    public f0(PdMPartsRecommendView pdMPartsRecommendView) {
        this.f5152g = pdMPartsRecommendView;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        PreferentialRecommendTabItemEntity preferentialRecommendTabItemEntity = new PreferentialRecommendTabItemEntity();
        PdMPartsRecommendView pdMPartsRecommendView = this.f5152g;
        preferentialRecommendTabItemEntity.tabId = pdMPartsRecommendView.z;
        pdMPartsRecommendView.c(preferentialRecommendTabItemEntity);
    }
}
