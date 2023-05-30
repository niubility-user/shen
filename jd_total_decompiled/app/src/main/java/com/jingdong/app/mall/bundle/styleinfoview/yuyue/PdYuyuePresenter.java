package com.jingdong.app.mall.bundle.styleinfoview.yuyue;

import com.jingdong.app.mall.bundle.styleinfoview.R;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.ProductDetailEntity;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WareBusinessData;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WareBusinessPlusForBuyMt;
import com.jingdong.app.mall.bundle.styleinfoview.utils.PDManager;
import com.jingdong.app.mall.bundle.styleinfoview.utils.PDUtils;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.login.LoginUserBase;
import com.jingdong.common.login.LoginUserHelper;
import com.jingdong.common.utils.JDReminderNewUtils;

/* loaded from: classes3.dex */
class PdYuyuePresenter {
    PdYuyuePresenter() {
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0037  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0041  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void doYuyue(final BaseActivity baseActivity, final ProductDetailEntity productDetailEntity) {
        boolean z;
        WareBusinessData wareBusinessData = productDetailEntity.mWareBusinessData;
        if (wareBusinessData == null || wareBusinessData.yuyueInfo == null) {
            return;
        }
        if (productDetailEntity.isPlusMakeTime()) {
            WareBusinessData wareBusinessData2 = productDetailEntity.mWareBusinessData;
            WareBusinessPlusForBuyMt wareBusinessPlusForBuyMt = wareBusinessData2.makeMoreTime;
            if (wareBusinessPlusForBuyMt.makeMoreTimeFlag && ((wareBusinessPlusForBuyMt.drawMoreTimeFlag || wareBusinessPlusForBuyMt.appointMoreTimeFlag) && wareBusinessPlusForBuyMt.userFlag && wareBusinessData2.yuyueInfo.pinSkuYuYue)) {
                z = true;
                if (!z) {
                    PDUtils.showToastCenterNormal(baseActivity, baseActivity.getString(R.string.libpdstyleinfoview_lib_pd_yuyue_success));
                    return;
                }
                if (productDetailEntity.getStartTime() > 0) {
                    String str = "{\"des\":\"productDetail\",\"params\":{\"skuId\":\"" + productDetailEntity.skuId + "\"}}";
                    JDReminderNewUtils.setReminder("RESERVE", baseActivity.getString(R.string.libpdstyleinfoview_lib_pd_yuyue), "detailReserve_" + productDetailEntity.skuId, productDetailEntity.getProductDetailName(), "", productDetailEntity.getStartTime() * 1000, 0L, str, "", "");
                }
                if (productDetailEntity.mWareBusinessData.yuyueInfo.mad) {
                    if (LoginUserBase.hasLogin()) {
                        PDIsAppointProtocol pDIsAppointProtocol = new PDIsAppointProtocol(productDetailEntity.mManageKey);
                        pDIsAppointProtocol.setInputParams(productDetailEntity.skuId, "1", productDetailEntity.getYuyueShowCode());
                        baseActivity.addHttpGroupWithNPSSetting(pDIsAppointProtocol.request());
                        return;
                    }
                    LoginUserHelper.getInstance().executeLoginRunnable(baseActivity, new Runnable() { // from class: com.jingdong.app.mall.bundle.styleinfoview.yuyue.PdYuyuePresenter.1
                        @Override // java.lang.Runnable
                        public void run() {
                            PDManager.getInstances(productDetailEntity.mManageKey).post("pd_ProductDetailActivity", "pd_PDTopViewrefreshProduct");
                        }
                    });
                    return;
                }
                LoginUserHelper.getInstance().executeLoginRunnable(baseActivity, new Runnable() { // from class: com.jingdong.app.mall.bundle.styleinfoview.yuyue.PdYuyuePresenter.2
                    @Override // java.lang.Runnable
                    public void run() {
                        ProductDetailEntity productDetailEntity2 = productDetailEntity;
                        if (productDetailEntity2 == null) {
                            return;
                        }
                        PDIsAppointProtocol pDIsAppointProtocol2 = new PDIsAppointProtocol(productDetailEntity2.mManageKey);
                        ProductDetailEntity productDetailEntity3 = productDetailEntity;
                        pDIsAppointProtocol2.setInputParams(productDetailEntity3.skuId, "1", productDetailEntity3.getYuyueShowCode());
                        baseActivity.addHttpGroupWithNPSSetting(pDIsAppointProtocol2.request());
                    }
                });
                return;
            }
        }
        z = false;
        if (!z) {
        }
    }
}
