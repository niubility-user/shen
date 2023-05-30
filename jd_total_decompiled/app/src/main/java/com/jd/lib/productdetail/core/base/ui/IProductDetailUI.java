package com.jd.lib.productdetail.core.base.ui;

import com.jd.lib.productdetail.core.entitys.PDAddressEntity;
import com.jd.lib.productdetail.core.entitys.ecard.PDECardCartNumEntity;
import com.jd.lib.productdetail.core.entitys.prescription.PDPrescriptionListCount;
import com.jingdong.cleanmvp.presenter.IBaseUI;

/* loaded from: classes15.dex */
public interface IProductDetailUI extends IBaseUI {
    void changeAddress(PDAddressEntity pDAddressEntity);

    void hideAddressSelectView();

    void showArchivedPage(boolean z);

    void showDataError(boolean z, boolean z2);

    void showDataError(boolean z, boolean z2, String str);

    void showECardListCount(PDECardCartNumEntity pDECardCartNumEntity);

    void showPrescriptionListCount(PDPrescriptionListCount pDPrescriptionListCount);

    void showPrintBagCount(int i2);

    void showSkuDyinfo();

    void updateDJCartNums(int i2);
}
