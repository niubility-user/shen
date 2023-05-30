package com.jingdong.app.mall.bundle.styleinfoview.utils;

import android.text.TextUtils;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.PDCarGiftEntity;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.ProductDetailEntity;
import java.util.ArrayList;

/* loaded from: classes3.dex */
public class PDCarO2oGiftUtils {
    public static void setCarGift(ProductDetailEntity productDetailEntity, PDCarGiftEntity pDCarGiftEntity) {
        ArrayList<String> arrayList;
        PDCarGiftEntity pDCarGiftEntity2 = productDetailEntity.mCarGiftEntity;
        if (pDCarGiftEntity2 != null && !TextUtils.isEmpty(pDCarGiftEntity2.giftSkuId) && (arrayList = productDetailEntity.giftPoolIdsSelect) != null && arrayList.contains(productDetailEntity.mCarGiftEntity.giftSkuId)) {
            productDetailEntity.giftPoolIdsSelect.remove(productDetailEntity.mCarGiftEntity.giftSkuId);
        }
        productDetailEntity.mCarGiftEntity = null;
        if (pDCarGiftEntity == null || TextUtils.isEmpty(pDCarGiftEntity.giftSkuId)) {
            return;
        }
        productDetailEntity.mCarGiftEntity = pDCarGiftEntity;
        ArrayList<String> arrayList2 = productDetailEntity.giftPoolIdsSelect;
        if (arrayList2 == null || arrayList2.contains(pDCarGiftEntity.giftSkuId)) {
            return;
        }
        productDetailEntity.giftPoolIdsSelect.add(productDetailEntity.mCarGiftEntity.giftSkuId);
    }
}
