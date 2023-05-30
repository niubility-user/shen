package com.jd.lib.productdetail.core.base.ui;

import com.jd.lib.productdetail.core.entitys.PDAddressEntity;
import com.jd.lib.productdetail.core.entitys.ecard.PDECardCartNumEntity;
import com.jd.lib.productdetail.core.entitys.prescription.PDPrescriptionListCount;

/* loaded from: classes15.dex */
public class NullProductDetailUI implements IProductDetailUI {
    @Override // com.jd.lib.productdetail.core.base.ui.IProductDetailUI
    public void changeAddress(PDAddressEntity pDAddressEntity) {
    }

    @Override // com.jd.lib.productdetail.core.base.ui.IProductDetailUI
    public void hideAddressSelectView() {
    }

    @Override // com.jingdong.cleanmvp.presenter.IBaseUI
    public void hideProgress() {
    }

    @Override // com.jingdong.cleanmvp.presenter.IBaseUI
    public boolean isRetain() {
        return false;
    }

    @Override // com.jd.lib.productdetail.core.base.ui.IProductDetailUI
    public void showArchivedPage(boolean z) {
    }

    @Override // com.jd.lib.productdetail.core.base.ui.IProductDetailUI
    public void showDataError(boolean z, boolean z2) {
    }

    @Override // com.jd.lib.productdetail.core.base.ui.IProductDetailUI
    public void showDataError(boolean z, boolean z2, String str) {
    }

    @Override // com.jd.lib.productdetail.core.base.ui.IProductDetailUI
    public void showECardListCount(PDECardCartNumEntity pDECardCartNumEntity) {
    }

    @Override // com.jd.lib.productdetail.core.base.ui.IProductDetailUI
    public void showPrescriptionListCount(PDPrescriptionListCount pDPrescriptionListCount) {
    }

    @Override // com.jd.lib.productdetail.core.base.ui.IProductDetailUI
    public void showPrintBagCount(int i2) {
    }

    @Override // com.jingdong.cleanmvp.presenter.IBaseUI
    public void showProgress() {
    }

    @Override // com.jd.lib.productdetail.core.base.ui.IProductDetailUI
    public void showSkuDyinfo() {
    }

    @Override // com.jingdong.cleanmvp.presenter.IBaseUI
    public void showToast(String str) {
    }

    @Override // com.jd.lib.productdetail.core.base.ui.IProductDetailUI
    public void updateDJCartNums(int i2) {
    }
}
