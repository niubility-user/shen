package com.jingdong.app.mall.bundle.styleinfoview.entitys;

import android.text.TextUtils;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.coupon.PDCouponCellEntity;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.PromotionLayerItemEntity;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.PromotionVipGiftEntity;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WareBusinessData;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WareBusinessGiftPools3C;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WareBusinessPromotionEntity;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WareBusinessPromotionGiftEntity;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WareBusinessPromotionPlusEntity;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WareBusinessWhiteBarEntity;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WarePromotionInfo;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WarePropertyInfo;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public class PreferentialGuideEntity {
    public static final int FANS_VIP = 2;
    public static final int FOLD_NUM = 5;
    public static final int SHOP_VIP = 1;
    public List<String> bestCouponId;
    public String bgIcon;
    public String bgIconTop;
    public String couponIconText;
    public List<PDCouponCellEntity> couponInfo;
    public FirstLevelGuideInfo firstLevelGuideInfo;
    public WareBusinessGiftPools3C giftPool3C;
    public boolean hasFinanceCoupon;
    public String iconCode;
    private boolean isHitBestCouponId;
    public PopPlus20Info popPlus20Info;
    public WareBusinessPromotionEntity promotion;
    public SecondLevelGuideInfo secondLevelGuideInfo;
    public int vipJumpType;
    public String vipJumpUrl;
    public WareBusinessWhiteBarEntity whiteBarInfo;
    private int realLayerItemCount = 0;
    public boolean isHidBestGuideInfo = false;
    public int businessEnum = -1;

    /* loaded from: classes3.dex */
    public static class FirstLevelGuideInfo {
        public boolean boldFlag;
        public String fLevelCode;
        public String fLevelGuideText;
        public String plus20timesText;
        public String saveMoney;
        public String textBgColor;
    }

    /* loaded from: classes3.dex */
    public static class PopPlus20Info {
        public String content;
        public String iconText;
        public String rightJumpUrl;
        public String times;
    }

    /* loaded from: classes3.dex */
    public static class SecondLevelGuideInfo {
        public boolean isOverWidth;
        public String jdPrice;
        public String sLevelCode;
        public String sLevelGuideText0;
        public String sLevelGuideText1;
        public String sLevelGuideText2;
        public String sLevelIconCode;
        public String sLevelJumpUrl;
        public String saveMoney;
    }

    private void addFanGiftData(ArrayList<PromotionLayerItemEntity> arrayList, PromotionVipGiftEntity promotionVipGiftEntity, int i2) {
        List<WareBusinessPromotionGiftEntity> list;
        if (promotionVipGiftEntity == null || (list = promotionVipGiftEntity.gift) == null || list.isEmpty()) {
            return;
        }
        if (promotionVipGiftEntity.needGuide) {
            PromotionLayerItemEntity promotionLayerItemEntity = new PromotionLayerItemEntity();
            promotionLayerItemEntity.type = 15;
            promotionLayerItemEntity.iconCode = promotionVipGiftEntity.gift.get(0) != null ? promotionVipGiftEntity.gift.get(0).iconCode : "";
            promotionLayerItemEntity.title = promotionVipGiftEntity.leftText;
            promotionLayerItemEntity.subTitle = promotionVipGiftEntity.top;
            promotionLayerItemEntity.vipJumpUrl = promotionVipGiftEntity.link;
            promotionLayerItemEntity.vipJumpType = promotionVipGiftEntity.jumpType;
            promotionLayerItemEntity.rightText = promotionVipGiftEntity.rightText;
            promotionLayerItemEntity.vipType = i2;
            arrayList.add(promotionLayerItemEntity);
        }
        PromotionLayerItemEntity promotionLayerItemEntity2 = new PromotionLayerItemEntity();
        promotionLayerItemEntity2.foldList = new ArrayList<>();
        int i3 = 0;
        while (true) {
            if (i3 >= promotionVipGiftEntity.gift.size()) {
                break;
            }
            if (i3 < 5) {
                PromotionLayerItemEntity promotionLayerItemEntity3 = new PromotionLayerItemEntity();
                promotionLayerItemEntity3.mPromotionEntity = promotionVipGiftEntity.gift.get(i3);
                promotionLayerItemEntity3.type = 16;
                promotionLayerItemEntity3.isTag = i3 == 0 && !promotionVipGiftEntity.needGuide;
                arrayList.add(promotionLayerItemEntity3);
            } else {
                PromotionLayerItemEntity promotionLayerItemEntity4 = new PromotionLayerItemEntity();
                promotionLayerItemEntity4.mPromotionEntity = promotionVipGiftEntity.gift.get(i3);
                promotionLayerItemEntity4.type = 16;
                promotionLayerItemEntity4.isTag = false;
                promotionLayerItemEntity2.foldList.add(promotionLayerItemEntity4);
            }
            i3++;
        }
        if (promotionLayerItemEntity2.foldList.size() > 0) {
            promotionLayerItemEntity2.isFold = true;
            promotionLayerItemEntity2.type = 17;
            promotionLayerItemEntity2.foldText = promotionVipGiftEntity.gift.get(0) != null ? promotionVipGiftEntity.gift.get(0).getText() : "";
            arrayList.add(promotionLayerItemEntity2);
        }
    }

    private ArrayList<PromotionLayerItemEntity> dealPromotionData(WareBusinessData wareBusinessData) {
        WarePropertyInfo warePropertyInfo;
        WarePromotionInfo warePromotionInfo;
        List<WareBusinessPromotionGiftEntity> list;
        ArrayList<PromotionLayerItemEntity> arrayList = new ArrayList<>();
        WareBusinessPromotionEntity wareBusinessPromotionEntity = this.promotion;
        if (wareBusinessPromotionEntity != null) {
            List<WareBusinessPromotionGiftEntity> list2 = wareBusinessPromotionEntity.bestProList;
            if (list2 != null && list2.size() > 0) {
                for (WareBusinessPromotionGiftEntity wareBusinessPromotionGiftEntity : this.promotion.bestProList) {
                    if (wareBusinessPromotionGiftEntity != null) {
                        PromotionLayerItemEntity promotionLayerItemEntity = new PromotionLayerItemEntity();
                        promotionLayerItemEntity.mPromotionEntity = wareBusinessPromotionGiftEntity;
                        promotionLayerItemEntity.type = 3;
                        if (!this.isHidBestGuideInfo) {
                            promotionLayerItemEntity.sheng = this.businessEnum;
                        } else {
                            promotionLayerItemEntity.sheng = -1;
                        }
                        promotionLayerItemEntity.iconCode = wareBusinessPromotionGiftEntity.iconCode;
                        promotionLayerItemEntity.isTag = true;
                        arrayList.add(promotionLayerItemEntity);
                    }
                }
            }
            List<WareBusinessPromotionGiftEntity> list3 = this.promotion.activity;
            if (list3 != null && !list3.isEmpty()) {
                for (WareBusinessPromotionGiftEntity wareBusinessPromotionGiftEntity2 : this.promotion.activity) {
                    if (wareBusinessPromotionGiftEntity2 != null && !TextUtils.equals(wareBusinessPromotionGiftEntity2.businessType, "9")) {
                        PromotionLayerItemEntity promotionLayerItemEntity2 = new PromotionLayerItemEntity();
                        promotionLayerItemEntity2.mPromotionEntity = wareBusinessPromotionGiftEntity2;
                        promotionLayerItemEntity2.type = 3;
                        promotionLayerItemEntity2.iconCode = wareBusinessPromotionGiftEntity2.iconCode;
                        promotionLayerItemEntity2.isTag = true;
                        if (wareBusinessPromotionGiftEntity2.getTag() == 1) {
                            promotionLayerItemEntity2.vipJumpType = this.vipJumpType;
                            promotionLayerItemEntity2.vipJumpUrl = this.vipJumpUrl;
                        }
                        arrayList.add(promotionLayerItemEntity2);
                    }
                }
            }
            PopPlus20Info popPlus20Info = this.popPlus20Info;
            if (popPlus20Info != null && !TextUtils.isEmpty(popPlus20Info.iconText) && !TextUtils.isEmpty(this.popPlus20Info.content)) {
                PromotionLayerItemEntity promotionLayerItemEntity3 = new PromotionLayerItemEntity();
                WareBusinessPromotionGiftEntity wareBusinessPromotionGiftEntity3 = new WareBusinessPromotionGiftEntity();
                wareBusinessPromotionGiftEntity3.setProChannel(2);
                wareBusinessPromotionGiftEntity3.setLink(this.popPlus20Info.rightJumpUrl);
                wareBusinessPromotionGiftEntity3.setText(this.popPlus20Info.iconText);
                wareBusinessPromotionGiftEntity3.setValue(this.popPlus20Info.content);
                wareBusinessPromotionGiftEntity3.businessType = "9";
                promotionLayerItemEntity3.mPromotionEntity = wareBusinessPromotionGiftEntity3;
                promotionLayerItemEntity3.type = 3;
                promotionLayerItemEntity3.isTag = true;
                promotionLayerItemEntity3.plusTimes = this.popPlus20Info.times;
                arrayList.add(promotionLayerItemEntity3);
            }
            List<WareBusinessPromotionGiftEntity> list4 = this.promotion.attach;
            if (list4 != null && !list4.isEmpty()) {
                int i2 = 0;
                while (i2 < this.promotion.attach.size()) {
                    PromotionLayerItemEntity promotionLayerItemEntity4 = new PromotionLayerItemEntity();
                    promotionLayerItemEntity4.mPromotionEntity = this.promotion.attach.get(i2);
                    promotionLayerItemEntity4.type = 4;
                    promotionLayerItemEntity4.iconCode = this.iconCode;
                    promotionLayerItemEntity4.isTag = i2 == 0;
                    arrayList.add(promotionLayerItemEntity4);
                    i2++;
                }
            }
            WareBusinessPromotionPlusEntity wareBusinessPromotionPlusEntity = this.promotion.plusGiftInfo;
            if (wareBusinessPromotionPlusEntity != null && (list = wareBusinessPromotionPlusEntity.plusGift) != null && !list.isEmpty()) {
                int i3 = 0;
                while (i3 < this.promotion.plusGiftInfo.plusGift.size()) {
                    PromotionLayerItemEntity promotionLayerItemEntity5 = new PromotionLayerItemEntity();
                    promotionLayerItemEntity5.mPromotionEntity = this.promotion.plusGiftInfo.plusGift.get(i3);
                    promotionLayerItemEntity5.type = 5;
                    promotionLayerItemEntity5.iconCode = this.iconCode;
                    promotionLayerItemEntity5.isTag = i3 == 0;
                    arrayList.add(promotionLayerItemEntity5);
                    i3++;
                }
                if (!TextUtils.isEmpty(this.promotion.plusGiftInfo.plusBottom)) {
                    WareBusinessPromotionGiftEntity wareBusinessPromotionGiftEntity4 = new WareBusinessPromotionGiftEntity();
                    wareBusinessPromotionGiftEntity4.setText(this.promotion.plusGiftInfo.plusBottom);
                    wareBusinessPromotionGiftEntity4.setLink(this.promotion.plusGiftInfo.plusBottomLink);
                    wareBusinessPromotionGiftEntity4.setValue("plusBottom");
                    PromotionLayerItemEntity promotionLayerItemEntity6 = new PromotionLayerItemEntity();
                    promotionLayerItemEntity6.mPromotionEntity = wareBusinessPromotionGiftEntity4;
                    promotionLayerItemEntity6.type = 5;
                    promotionLayerItemEntity6.iconCode = this.promotion.plusGiftInfo.plusColour;
                    promotionLayerItemEntity6.isTag = false;
                    arrayList.add(promotionLayerItemEntity6);
                }
            }
            addFanGiftData(arrayList, this.promotion.memberGiftInfo, 1);
            addFanGiftData(arrayList, this.promotion.fsGiftInfo, 2);
            List<WareBusinessPromotionGiftEntity> list5 = this.promotion.gift;
            if (list5 != null && !list5.isEmpty()) {
                for (int i4 = 0; i4 < this.promotion.gift.size(); i4++) {
                    PromotionLayerItemEntity promotionLayerItemEntity7 = new PromotionLayerItemEntity();
                    promotionLayerItemEntity7.mPromotionEntity = this.promotion.gift.get(i4);
                    promotionLayerItemEntity7.type = 6;
                    promotionLayerItemEntity7.iconCode = this.iconCode;
                    if (i4 == 0) {
                        promotionLayerItemEntity7.isTag = true;
                    } else {
                        promotionLayerItemEntity7.isTag = !TextUtils.equals(this.promotion.gift.get(i4 - 1).getText(), this.promotion.gift.get(i4).getText());
                    }
                    arrayList.add(promotionLayerItemEntity7);
                }
            }
            if (wareBusinessData != null && (warePromotionInfo = wareBusinessData.promotionInfo) != null && warePromotionInfo.giftPool3C != null) {
                PromotionLayerItemEntity promotionLayerItemEntity8 = new PromotionLayerItemEntity();
                promotionLayerItemEntity8.mWareBusinessGiftPools3C = wareBusinessData.promotionInfo.giftPool3C;
                promotionLayerItemEntity8.type = 7;
                promotionLayerItemEntity8.iconCode = this.iconCode;
                arrayList.add(promotionLayerItemEntity8);
            }
            if (wareBusinessData != null && (warePropertyInfo = wareBusinessData.property) != null && warePropertyInfo.suitABTest == 0 && this.promotion.hasSuit()) {
                PromotionLayerItemEntity promotionLayerItemEntity9 = new PromotionLayerItemEntity();
                promotionLayerItemEntity9.mPdSuitEntry = wareBusinessData.suit;
                promotionLayerItemEntity9.type = 8;
                promotionLayerItemEntity9.iconCode = this.iconCode;
                arrayList.add(promotionLayerItemEntity9);
            }
        }
        return arrayList;
    }

    private String getPromotionTips(WareBusinessPromotionEntity wareBusinessPromotionEntity) {
        List<String> list;
        if (wareBusinessPromotionEntity == null || (list = wareBusinessPromotionEntity.tips) == null || list.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < wareBusinessPromotionEntity.tips.size(); i2++) {
            sb.append(wareBusinessPromotionEntity.tips.get(i2));
        }
        return sb.toString();
    }

    private boolean isHidPromotionTips() {
        WareBusinessPromotionEntity wareBusinessPromotionEntity;
        List<WareBusinessPromotionGiftEntity> list;
        List<String> list2 = this.bestCouponId;
        boolean z = (list2 == null || list2.size() == 0) && ((wareBusinessPromotionEntity = this.promotion) == null || (list = wareBusinessPromotionEntity.bestProList) == null || list.size() == 0);
        this.isHidBestGuideInfo = z;
        return z;
    }

    public ArrayList<PromotionLayerItemEntity> dealData(WareBusinessData wareBusinessData) {
        WarePropertyInfo warePropertyInfo;
        boolean z = false;
        this.realLayerItemCount = 0;
        ArrayList<PromotionLayerItemEntity> arrayList = new ArrayList<>();
        if (wareBusinessData == null) {
            return arrayList;
        }
        WareBusinessWhiteBarEntity wareBusinessWhiteBarEntity = this.whiteBarInfo;
        if (wareBusinessWhiteBarEntity != null && !TextUtils.isEmpty(wareBusinessWhiteBarEntity.marketingText) && this.whiteBarInfo.ava && (warePropertyInfo = wareBusinessData.property) != null && warePropertyInfo.cartFlag) {
            z = true;
        }
        if (z) {
            PromotionLayerItemEntity promotionLayerItemEntity = new PromotionLayerItemEntity();
            promotionLayerItemEntity.type = 9;
            promotionLayerItemEntity.mWhiteBarInfoEntity = this.whiteBarInfo;
            arrayList.add(promotionLayerItemEntity);
            PromotionLayerItemEntity promotionLayerItemEntity2 = new PromotionLayerItemEntity();
            promotionLayerItemEntity2.mWhiteBarInfoEntity = this.whiteBarInfo;
            promotionLayerItemEntity2.type = 1;
            arrayList.add(promotionLayerItemEntity2);
            this.realLayerItemCount++;
        }
        if (this.secondLevelGuideInfo != null && !this.isHidBestGuideInfo) {
            PromotionLayerItemEntity promotionLayerItemEntity3 = new PromotionLayerItemEntity();
            promotionLayerItemEntity3.type = 13;
            promotionLayerItemEntity3.secondLevelGuideInfo = this.secondLevelGuideInfo;
            promotionLayerItemEntity3.sheng = this.businessEnum;
            this.realLayerItemCount++;
            arrayList.add(promotionLayerItemEntity3);
        }
        ArrayList<PromotionLayerItemEntity> dealPromotionData = dealPromotionData(wareBusinessData);
        if (dealPromotionData != null && !dealPromotionData.isEmpty()) {
            PromotionLayerItemEntity promotionLayerItemEntity4 = new PromotionLayerItemEntity();
            promotionLayerItemEntity4.type = 10;
            promotionLayerItemEntity4.subTitle = getPromotionTips(this.promotion);
            this.realLayerItemCount += dealPromotionData.size();
            arrayList.add(promotionLayerItemEntity4);
            arrayList.addAll(dealPromotionData);
        }
        List<PDCouponCellEntity> list = this.couponInfo;
        if (list != null && !list.isEmpty()) {
            ArrayList arrayList2 = new ArrayList();
            ArrayList arrayList3 = new ArrayList();
            for (PDCouponCellEntity pDCouponCellEntity : this.couponInfo) {
                pDCouponCellEntity.promoImageType = this.businessEnum;
                PromotionLayerItemEntity promotionLayerItemEntity5 = new PromotionLayerItemEntity();
                promotionLayerItemEntity5.mPdCouponCellEntity = pDCouponCellEntity;
                promotionLayerItemEntity5.isHidBestGuideInfo = this.isHidBestGuideInfo;
                promotionLayerItemEntity5.type = 2;
                if (pDCouponCellEntity.applicability) {
                    arrayList2.add(promotionLayerItemEntity5);
                } else {
                    arrayList3.add(promotionLayerItemEntity5);
                }
            }
            if (!arrayList2.isEmpty()) {
                this.realLayerItemCount += arrayList2.size();
                PromotionLayerItemEntity promotionLayerItemEntity6 = new PromotionLayerItemEntity();
                promotionLayerItemEntity6.type = 11;
                arrayList.add(promotionLayerItemEntity6);
                arrayList.addAll(arrayList2);
            }
            if (!arrayList3.isEmpty()) {
                this.realLayerItemCount += arrayList3.size();
                PromotionLayerItemEntity promotionLayerItemEntity7 = new PromotionLayerItemEntity();
                promotionLayerItemEntity7.type = 12;
                arrayList.add(promotionLayerItemEntity7);
                arrayList.addAll(arrayList3);
            }
        }
        if (this.secondLevelGuideInfo != null && !this.isHidBestGuideInfo) {
            PromotionLayerItemEntity promotionLayerItemEntity8 = new PromotionLayerItemEntity();
            promotionLayerItemEntity8.type = 14;
            SecondLevelGuideInfo secondLevelGuideInfo = this.secondLevelGuideInfo;
            promotionLayerItemEntity8.title = secondLevelGuideInfo.sLevelGuideText2;
            promotionLayerItemEntity8.jumpUrl = secondLevelGuideInfo.sLevelJumpUrl;
            promotionLayerItemEntity8.xiiconCode = secondLevelGuideInfo.sLevelIconCode;
            arrayList.add(promotionLayerItemEntity8);
        }
        return arrayList;
    }

    public boolean filterBestCouponId() {
        List<String> list;
        int i2;
        isHidPromotionTips();
        if (this.isHitBestCouponId && (list = this.bestCouponId) != null && list.size() > 0) {
            List<PDCouponCellEntity> list2 = this.couponInfo;
            if (list2 == null || list2.size() <= 0) {
                i2 = 0;
            } else {
                i2 = 0;
                for (int i3 = 0; i3 < this.bestCouponId.size(); i3++) {
                    int i4 = 0;
                    while (true) {
                        if (i4 >= this.couponInfo.size()) {
                            break;
                        }
                        PDCouponCellEntity pDCouponCellEntity = this.couponInfo.get(i4);
                        if (pDCouponCellEntity != null && TextUtils.equals(this.bestCouponId.get(i3), pDCouponCellEntity.couponId)) {
                            i2++;
                            break;
                        }
                        i4++;
                    }
                }
            }
            this.isHidBestGuideInfo = i2 != this.bestCouponId.size();
        }
        return this.isHidBestGuideInfo;
    }

    public int getRealLayerItemCount() {
        return this.realLayerItemCount;
    }

    public void resetHitBestGuide() {
        this.isHitBestCouponId = false;
    }

    public void sortFloorCoupon() {
        List<PDCouponCellEntity> list;
        if (isHidPromotionTips()) {
            return;
        }
        List<String> list2 = this.bestCouponId;
        if (!(list2 != null && list2.size() > 0) || (list = this.couponInfo) == null || list.size() <= 0) {
            return;
        }
        ArrayList arrayList = new ArrayList(this.couponInfo.size());
        ArrayList arrayList2 = new ArrayList(this.couponInfo.size());
        for (int i2 = 0; i2 < this.couponInfo.size(); i2++) {
            PDCouponCellEntity pDCouponCellEntity = this.couponInfo.get(i2);
            int i3 = 0;
            while (true) {
                if (i3 >= this.bestCouponId.size()) {
                    break;
                }
                String str = this.bestCouponId.get(i3);
                if (pDCouponCellEntity != null && TextUtils.equals(str, pDCouponCellEntity.couponId)) {
                    this.isHitBestCouponId = true;
                    pDCouponCellEntity.isBestCoupon = true;
                    break;
                }
                i3++;
            }
            if (pDCouponCellEntity != null && pDCouponCellEntity.isBestCoupon) {
                arrayList.add(pDCouponCellEntity);
            } else {
                arrayList2.add(pDCouponCellEntity);
            }
        }
        if (!this.isHitBestCouponId) {
            this.isHidBestGuideInfo = true;
        }
        arrayList.addAll(arrayList2);
        this.couponInfo = arrayList;
    }
}
