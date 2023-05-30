package com.jd.lib.productdetail.core.entitys.warebusiness;

import android.text.TextUtils;
import com.jd.lib.productdetail.core.entitys.coupon.PDCouponCellEntity;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes15.dex */
public class WareBusinessBigPlusJumpPlayer {
    public String bottomJumpUrl;
    public String bottomTitle;
    public List<PDCouponCellEntity> couponInfo;
    public int timeState;
    public String topBgImg;
    public String topTitle;
    public String topTitleImg;

    public ArrayList<PromotionLayerItemEntity> dealData(WareBusinessData wareBusinessData) {
        ArrayList<PromotionLayerItemEntity> arrayList = new ArrayList<>();
        List<PDCouponCellEntity> list = this.couponInfo;
        if (list != null && !list.isEmpty()) {
            new ArrayList();
            new ArrayList();
            Iterator<PDCouponCellEntity> it = this.couponInfo.iterator();
            while (it.hasNext()) {
                PromotionLayerItemEntity promotionLayerItemEntity = new PromotionLayerItemEntity();
                promotionLayerItemEntity.mPdCouponCellEntity = it.next();
                promotionLayerItemEntity.isHidBestGuideInfo = true;
                promotionLayerItemEntity.type = 2;
                arrayList.add(promotionLayerItemEntity);
            }
        }
        return arrayList;
    }

    public boolean isValid() {
        List<PDCouponCellEntity> list;
        return (TextUtils.isEmpty(this.topTitle) || (list = this.couponInfo) == null || list.size() <= 0 || TextUtils.isEmpty(this.bottomTitle) || TextUtils.isEmpty(this.bottomJumpUrl)) ? false : true;
    }
}
