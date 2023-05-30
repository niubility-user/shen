package com.jd.lib.productdetail.core.entitys;

import android.text.TextUtils;
import com.jd.lib.productdetail.core.entitys.coupon.PDCouponCellEntity;
import com.jd.lib.productdetail.core.entitys.coupon.PDSskCouponEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.PromotionLayerItemEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.PromotionVipGiftEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessData;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessExpression;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessGiftPools3C;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessPromotionEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessPromotionGiftEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessPromotionPlusEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessWhiteBarEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.WarePromotionInfo;
import com.jd.lib.productdetail.core.entitys.warebusiness.WarePropertyInfo;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes15.dex */
public class PreferentialGuideEntity {
    public static final int FANS_VIP = 2;
    public static final int FOLD_NUM = 5;
    public static final int SHOP_VIP = 1;
    public List<String> bestCouponId;
    public String bgIcon;
    public String bgIconTop;
    public String couponIconText;
    public List<PDCouponCellEntity> couponInfo;
    public String desc;
    public WareBusinessExpression expression;
    public WareBusinessExpression expressionFuture;
    public FirstLevelGuideInfo firstLevelGuideInfo;
    public WareBusinessGiftPools3C giftPool3C;
    public boolean hasFinanceCoupon;
    public String iconCode;
    public String identify;
    private boolean isHitBestCouponId;
    public ArrayList<WareBusinessLabelInfoEntity> labelsInfo;
    public List<PdGuideLabelsInfoNewEneity> labelsInfoNew;
    public String moreIcon;
    public PopPlus20Info popPlus20Info;
    public WareBusinessPromotionEntity promotion;
    public SecondLevelGuideInfo secondLevelGuideInfo;
    public PDSskCouponEntity sskCouponInfo;
    public String titleImage;
    public String titleImageBg;
    public String titleRightText;
    public int vipJumpType;
    public String vipJumpUrl;
    public WareBusinessWhiteBarEntity whiteBarInfo;
    private int realLayerItemCount = 0;
    private int cuxiaoIndex = 0;
    public boolean isHidBestGuideInfo = false;
    public int businessEnum = -1;

    /* loaded from: classes15.dex */
    public static class FirstLevelGuideInfo {
        public boolean boldFlag;
        public String fLevelCode;
        public String fLevelGuideText;
        public String plus20timesText;
        public String saveMoney;
        public String textBgColor;
    }

    /* loaded from: classes15.dex */
    public static class PopPlus20Info {
        public String content;
        public String iconText;
        public String rightJumpUrl;
        public String times;
    }

    /* loaded from: classes15.dex */
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
            int i3 = this.cuxiaoIndex + 1;
            this.cuxiaoIndex = i3;
            promotionLayerItemEntity.cuxiaoIndex = i3;
            arrayList.add(promotionLayerItemEntity);
        }
        PromotionLayerItemEntity promotionLayerItemEntity2 = new PromotionLayerItemEntity();
        promotionLayerItemEntity2.foldList = new ArrayList<>();
        int i4 = 0;
        while (i4 < promotionVipGiftEntity.gift.size()) {
            if (i4 < 5) {
                PromotionLayerItemEntity promotionLayerItemEntity3 = new PromotionLayerItemEntity();
                promotionLayerItemEntity3.mPromotionEntity = promotionVipGiftEntity.gift.get(i4);
                promotionLayerItemEntity3.type = 16;
                promotionLayerItemEntity3.isTag = i4 == 0 && !promotionVipGiftEntity.needGuide;
                int i5 = this.cuxiaoIndex + 1;
                this.cuxiaoIndex = i5;
                promotionLayerItemEntity3.cuxiaoIndex = i5;
                arrayList.add(promotionLayerItemEntity3);
            } else {
                PromotionLayerItemEntity promotionLayerItemEntity4 = new PromotionLayerItemEntity();
                promotionLayerItemEntity4.mPromotionEntity = promotionVipGiftEntity.gift.get(i4);
                promotionLayerItemEntity4.type = 16;
                promotionLayerItemEntity4.isTag = false;
                promotionLayerItemEntity2.foldList.add(promotionLayerItemEntity4);
            }
            i4++;
        }
        if (promotionLayerItemEntity2.foldList.size() > 0) {
            promotionLayerItemEntity2.isFold = true;
            promotionLayerItemEntity2.type = 17;
            promotionLayerItemEntity2.foldText = promotionVipGiftEntity.gift.get(0) != null ? promotionVipGiftEntity.gift.get(0).getText() : "";
            int i6 = this.cuxiaoIndex + 1;
            this.cuxiaoIndex = i6;
            promotionLayerItemEntity2.cuxiaoIndex = i6;
            arrayList.add(promotionLayerItemEntity2);
        }
    }

    private void addSpotPreGiftInfoData(ArrayList<PromotionLayerItemEntity> arrayList, PromotionVipGiftEntity promotionVipGiftEntity) {
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
            int i2 = this.cuxiaoIndex + 1;
            this.cuxiaoIndex = i2;
            promotionLayerItemEntity.cuxiaoIndex = i2;
            arrayList.add(promotionLayerItemEntity);
        }
        PromotionLayerItemEntity promotionLayerItemEntity2 = new PromotionLayerItemEntity();
        promotionLayerItemEntity2.foldList = new ArrayList<>();
        int i3 = 0;
        while (i3 < promotionVipGiftEntity.gift.size()) {
            if (i3 < 5) {
                PromotionLayerItemEntity promotionLayerItemEntity3 = new PromotionLayerItemEntity();
                promotionLayerItemEntity3.mPromotionEntity = promotionVipGiftEntity.gift.get(i3);
                promotionLayerItemEntity3.type = 16;
                promotionLayerItemEntity3.isTag = i3 == 0 && !promotionVipGiftEntity.needGuide;
                int i4 = this.cuxiaoIndex + 1;
                this.cuxiaoIndex = i4;
                promotionLayerItemEntity3.cuxiaoIndex = i4;
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
            int i5 = this.cuxiaoIndex + 1;
            this.cuxiaoIndex = i5;
            promotionLayerItemEntity2.cuxiaoIndex = i5;
            arrayList.add(promotionLayerItemEntity2);
        }
    }

    private ArrayList<PromotionLayerItemEntity> dealPromotionData(WareBusinessData wareBusinessData) {
        WarePropertyInfo warePropertyInfo;
        WarePromotionInfo warePromotionInfo;
        List<WareBusinessPromotionGiftEntity> list;
        ArrayList<PromotionLayerItemEntity> arrayList = new ArrayList<>();
        boolean z = false;
        this.cuxiaoIndex = 0;
        WareBusinessPromotionEntity wareBusinessPromotionEntity = this.promotion;
        if (wareBusinessPromotionEntity != null) {
            List<WareBusinessPromotionGiftEntity> list2 = wareBusinessPromotionEntity.bestProList;
            if (list2 != null && list2.size() > 0) {
                for (WareBusinessPromotionGiftEntity wareBusinessPromotionGiftEntity : this.promotion.bestProList) {
                    if (wareBusinessPromotionGiftEntity != null) {
                        PromotionLayerItemEntity promotionLayerItemEntity = new PromotionLayerItemEntity();
                        promotionLayerItemEntity.mPromotionEntity = wareBusinessPromotionGiftEntity;
                        promotionLayerItemEntity.type = 3;
                        promotionLayerItemEntity.valueDown = wareBusinessPromotionGiftEntity.valueDown;
                        promotionLayerItemEntity.bgColor = wareBusinessPromotionGiftEntity.bgColor;
                        promotionLayerItemEntity.remainderTime = wareBusinessPromotionGiftEntity.remainderTime;
                        promotionLayerItemEntity.bigSale = wareBusinessPromotionGiftEntity.bigSale;
                        if (!this.isHidBestGuideInfo) {
                            promotionLayerItemEntity.sheng = this.businessEnum;
                        } else {
                            promotionLayerItemEntity.sheng = -1;
                        }
                        promotionLayerItemEntity.iconCode = wareBusinessPromotionGiftEntity.iconCode;
                        promotionLayerItemEntity.isTag = true;
                        int i2 = this.cuxiaoIndex + 1;
                        this.cuxiaoIndex = i2;
                        promotionLayerItemEntity.cuxiaoIndex = i2;
                        arrayList.add(promotionLayerItemEntity);
                    }
                }
            }
            List<WareBusinessPromotionGiftEntity> list3 = this.promotion.activity;
            if (list3 != null && !list3.isEmpty()) {
                for (WareBusinessPromotionGiftEntity wareBusinessPromotionGiftEntity2 : this.promotion.activity) {
                    if (wareBusinessPromotionGiftEntity2 != null && !TextUtils.equals(wareBusinessPromotionGiftEntity2.businessType, "9")) {
                        List<String> list4 = wareBusinessPromotionGiftEntity2.valueList;
                        if (list4 != null && list4.size() > 0) {
                            int i3 = 0;
                            while (i3 < wareBusinessPromotionGiftEntity2.valueList.size()) {
                                PromotionLayerItemEntity promotionLayerItemEntity2 = new PromotionLayerItemEntity();
                                WareBusinessPromotionGiftEntity wareBusinessPromotionGiftEntity3 = new WareBusinessPromotionGiftEntity();
                                promotionLayerItemEntity2.mPromotionEntity = wareBusinessPromotionGiftEntity3;
                                wareBusinessPromotionGiftEntity3.setValue(wareBusinessPromotionGiftEntity2.valueList.get(i3));
                                promotionLayerItemEntity2.type = 3;
                                promotionLayerItemEntity2.iconCode = wareBusinessPromotionGiftEntity2.iconCode;
                                promotionLayerItemEntity2.valueList = wareBusinessPromotionGiftEntity2.valueList;
                                promotionLayerItemEntity2.isTag = i3 == 0;
                                promotionLayerItemEntity2.isValueListFirst = i3 == 0;
                                promotionLayerItemEntity2.mPromotionEntity.setText(wareBusinessPromotionGiftEntity2.getText());
                                int i4 = this.cuxiaoIndex + 1;
                                this.cuxiaoIndex = i4;
                                promotionLayerItemEntity2.cuxiaoIndex = i4;
                                arrayList.add(promotionLayerItemEntity2);
                                i3++;
                            }
                        } else {
                            PromotionLayerItemEntity promotionLayerItemEntity3 = new PromotionLayerItemEntity();
                            promotionLayerItemEntity3.mPromotionEntity = wareBusinessPromotionGiftEntity2;
                            promotionLayerItemEntity3.type = 3;
                            promotionLayerItemEntity3.iconCode = wareBusinessPromotionGiftEntity2.iconCode;
                            promotionLayerItemEntity3.valueDown = wareBusinessPromotionGiftEntity2.valueDown;
                            promotionLayerItemEntity3.bgColor = wareBusinessPromotionGiftEntity2.bgColor;
                            promotionLayerItemEntity3.remainderTime = wareBusinessPromotionGiftEntity2.remainderTime;
                            promotionLayerItemEntity3.bigSale = wareBusinessPromotionGiftEntity2.bigSale;
                            promotionLayerItemEntity3.isTag = true;
                            if (wareBusinessPromotionGiftEntity2.getTag() == 1) {
                                promotionLayerItemEntity3.vipJumpType = this.vipJumpType;
                                promotionLayerItemEntity3.vipJumpUrl = this.vipJumpUrl;
                            }
                            int i5 = this.cuxiaoIndex + 1;
                            this.cuxiaoIndex = i5;
                            promotionLayerItemEntity3.cuxiaoIndex = i5;
                            arrayList.add(promotionLayerItemEntity3);
                        }
                    }
                }
            }
            PopPlus20Info popPlus20Info = this.popPlus20Info;
            if (popPlus20Info != null && !TextUtils.isEmpty(popPlus20Info.iconText) && !TextUtils.isEmpty(this.popPlus20Info.content)) {
                PromotionLayerItemEntity promotionLayerItemEntity4 = new PromotionLayerItemEntity();
                WareBusinessPromotionGiftEntity wareBusinessPromotionGiftEntity4 = new WareBusinessPromotionGiftEntity();
                wareBusinessPromotionGiftEntity4.setProChannel(2);
                wareBusinessPromotionGiftEntity4.setLink(this.popPlus20Info.rightJumpUrl);
                wareBusinessPromotionGiftEntity4.setText(this.popPlus20Info.iconText);
                wareBusinessPromotionGiftEntity4.setValue(this.popPlus20Info.content);
                wareBusinessPromotionGiftEntity4.businessType = "9";
                promotionLayerItemEntity4.mPromotionEntity = wareBusinessPromotionGiftEntity4;
                promotionLayerItemEntity4.type = 3;
                promotionLayerItemEntity4.isTag = true;
                promotionLayerItemEntity4.plusTimes = this.popPlus20Info.times;
                int i6 = this.cuxiaoIndex + 1;
                this.cuxiaoIndex = i6;
                promotionLayerItemEntity4.cuxiaoIndex = i6;
                arrayList.add(promotionLayerItemEntity4);
            }
            List<WareBusinessPromotionGiftEntity> list5 = this.promotion.attach;
            if (list5 != null && !list5.isEmpty()) {
                int i7 = 0;
                while (i7 < this.promotion.attach.size()) {
                    PromotionLayerItemEntity promotionLayerItemEntity5 = new PromotionLayerItemEntity();
                    promotionLayerItemEntity5.mPromotionEntity = this.promotion.attach.get(i7);
                    promotionLayerItemEntity5.type = 4;
                    promotionLayerItemEntity5.iconCode = this.iconCode;
                    promotionLayerItemEntity5.isTag = i7 == 0;
                    int i8 = this.cuxiaoIndex + 1;
                    this.cuxiaoIndex = i8;
                    promotionLayerItemEntity5.cuxiaoIndex = i8;
                    arrayList.add(promotionLayerItemEntity5);
                    i7++;
                }
            }
            WareBusinessPromotionPlusEntity wareBusinessPromotionPlusEntity = this.promotion.plusGiftInfo;
            if (wareBusinessPromotionPlusEntity != null && (list = wareBusinessPromotionPlusEntity.plusGift) != null && !list.isEmpty()) {
                int i9 = 0;
                while (i9 < this.promotion.plusGiftInfo.plusGift.size()) {
                    PromotionLayerItemEntity promotionLayerItemEntity6 = new PromotionLayerItemEntity();
                    promotionLayerItemEntity6.mPromotionEntity = this.promotion.plusGiftInfo.plusGift.get(i9);
                    promotionLayerItemEntity6.type = 5;
                    promotionLayerItemEntity6.iconCode = this.iconCode;
                    promotionLayerItemEntity6.isTag = i9 == 0;
                    int i10 = this.cuxiaoIndex + 1;
                    this.cuxiaoIndex = i10;
                    promotionLayerItemEntity6.cuxiaoIndex = i10;
                    arrayList.add(promotionLayerItemEntity6);
                    i9++;
                }
                if (!TextUtils.isEmpty(this.promotion.plusGiftInfo.plusBottom)) {
                    WareBusinessPromotionGiftEntity wareBusinessPromotionGiftEntity5 = new WareBusinessPromotionGiftEntity();
                    wareBusinessPromotionGiftEntity5.setText(this.promotion.plusGiftInfo.plusBottom);
                    wareBusinessPromotionGiftEntity5.setLink(this.promotion.plusGiftInfo.plusBottomLink);
                    wareBusinessPromotionGiftEntity5.setValue("plusBottom");
                    PromotionLayerItemEntity promotionLayerItemEntity7 = new PromotionLayerItemEntity();
                    promotionLayerItemEntity7.mPromotionEntity = wareBusinessPromotionGiftEntity5;
                    promotionLayerItemEntity7.type = 5;
                    promotionLayerItemEntity7.iconCode = this.promotion.plusGiftInfo.plusColour;
                    promotionLayerItemEntity7.isTag = false;
                    int i11 = this.cuxiaoIndex + 1;
                    this.cuxiaoIndex = i11;
                    promotionLayerItemEntity7.cuxiaoIndex = i11;
                    arrayList.add(promotionLayerItemEntity7);
                }
            }
            addFanGiftData(arrayList, this.promotion.memberGiftInfo, 1);
            addFanGiftData(arrayList, this.promotion.fsGiftInfo, 2);
            List<WareBusinessPromotionGiftEntity> list6 = this.promotion.gift;
            if (list6 != null && !list6.isEmpty()) {
                for (int i12 = 0; i12 < this.promotion.gift.size(); i12++) {
                    PromotionLayerItemEntity promotionLayerItemEntity8 = new PromotionLayerItemEntity();
                    promotionLayerItemEntity8.mPromotionEntity = this.promotion.gift.get(i12);
                    promotionLayerItemEntity8.type = 6;
                    promotionLayerItemEntity8.iconCode = this.iconCode;
                    if (i12 == 0) {
                        promotionLayerItemEntity8.isTag = true;
                    } else {
                        promotionLayerItemEntity8.isTag = !TextUtils.equals(this.promotion.gift.get(i12 - 1).getText(), this.promotion.gift.get(i12).getText());
                    }
                    int i13 = this.cuxiaoIndex + 1;
                    this.cuxiaoIndex = i13;
                    promotionLayerItemEntity8.cuxiaoIndex = i13;
                    arrayList.add(promotionLayerItemEntity8);
                }
            }
            addSpotPreGiftInfoData(arrayList, this.promotion.preSaleGiftInfo);
            addSpotPreGiftInfoData(arrayList, this.promotion.spotGiftInfo);
            if (wareBusinessData != null && (warePromotionInfo = wareBusinessData.promotionInfo) != null && warePromotionInfo.giftPool3C != null) {
                PromotionLayerItemEntity promotionLayerItemEntity9 = new PromotionLayerItemEntity();
                promotionLayerItemEntity9.mWareBusinessGiftPools3C = wareBusinessData.promotionInfo.giftPool3C;
                promotionLayerItemEntity9.type = 7;
                promotionLayerItemEntity9.iconCode = this.iconCode;
                int i14 = this.cuxiaoIndex + 1;
                this.cuxiaoIndex = i14;
                promotionLayerItemEntity9.cuxiaoIndex = i14;
                arrayList.add(promotionLayerItemEntity9);
            }
            if (wareBusinessData != null && (warePropertyInfo = wareBusinessData.property) != null && warePropertyInfo.suitABTest == 0 && this.promotion.hasSuit()) {
                z = true;
            }
            if (z) {
                PromotionLayerItemEntity promotionLayerItemEntity10 = new PromotionLayerItemEntity();
                promotionLayerItemEntity10.mPdSuitEntry = wareBusinessData.suit;
                promotionLayerItemEntity10.type = 8;
                promotionLayerItemEntity10.iconCode = this.iconCode;
                int i15 = this.cuxiaoIndex + 1;
                this.cuxiaoIndex = i15;
                promotionLayerItemEntity10.cuxiaoIndex = i15;
                arrayList.add(promotionLayerItemEntity10);
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
        return dealData(wareBusinessData, true);
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

    public ArrayList<PromotionLayerItemEntity> dealData(WareBusinessData wareBusinessData, boolean z) {
        boolean z2;
        WarePropertyInfo warePropertyInfo;
        WareBusinessExpression wareBusinessExpression;
        WareBusinessExpression wareBusinessExpression2;
        boolean z3 = false;
        this.realLayerItemCount = 0;
        ArrayList<PromotionLayerItemEntity> arrayList = new ArrayList<>();
        if (wareBusinessData == null) {
            return arrayList;
        }
        if (!z || (((wareBusinessExpression = this.expression) == null || !wareBusinessExpression.isValid()) && ((wareBusinessExpression2 = this.expressionFuture) == null || !wareBusinessExpression2.isValid()))) {
            z2 = false;
        } else {
            PromotionLayerItemEntity promotionLayerItemEntity = new PromotionLayerItemEntity();
            promotionLayerItemEntity.type = 18;
            WareBusinessExpression wareBusinessExpression3 = this.expression;
            if (wareBusinessExpression3 != null && wareBusinessExpression3.isValid()) {
                promotionLayerItemEntity.expression = this.expression;
            }
            WareBusinessExpression wareBusinessExpression4 = this.expressionFuture;
            if (wareBusinessExpression4 != null && wareBusinessExpression4.isValid()) {
                promotionLayerItemEntity.expressionFuture = this.expressionFuture;
            }
            arrayList.add(promotionLayerItemEntity);
            this.realLayerItemCount++;
            z2 = true;
        }
        WareBusinessWhiteBarEntity wareBusinessWhiteBarEntity = this.whiteBarInfo;
        if (wareBusinessWhiteBarEntity != null && !TextUtils.isEmpty(wareBusinessWhiteBarEntity.marketingText) && this.whiteBarInfo.ava && (warePropertyInfo = wareBusinessData.property) != null && warePropertyInfo.cartFlag) {
            z3 = true;
        }
        if (z3) {
            PromotionLayerItemEntity promotionLayerItemEntity2 = new PromotionLayerItemEntity();
            promotionLayerItemEntity2.type = 9;
            promotionLayerItemEntity2.mWhiteBarInfoEntity = this.whiteBarInfo;
            arrayList.add(promotionLayerItemEntity2);
            PromotionLayerItemEntity promotionLayerItemEntity3 = new PromotionLayerItemEntity();
            promotionLayerItemEntity3.mWhiteBarInfoEntity = this.whiteBarInfo;
            promotionLayerItemEntity3.type = 1;
            arrayList.add(promotionLayerItemEntity3);
            this.realLayerItemCount++;
        }
        if (!z2 && this.secondLevelGuideInfo != null && !this.isHidBestGuideInfo) {
            PromotionLayerItemEntity promotionLayerItemEntity4 = new PromotionLayerItemEntity();
            promotionLayerItemEntity4.type = 13;
            promotionLayerItemEntity4.secondLevelGuideInfo = this.secondLevelGuideInfo;
            promotionLayerItemEntity4.sheng = this.businessEnum;
            this.realLayerItemCount++;
            arrayList.add(promotionLayerItemEntity4);
        }
        ArrayList<PromotionLayerItemEntity> dealPromotionData = dealPromotionData(wareBusinessData);
        if (dealPromotionData != null && !dealPromotionData.isEmpty()) {
            PromotionLayerItemEntity promotionLayerItemEntity5 = new PromotionLayerItemEntity();
            promotionLayerItemEntity5.type = 10;
            promotionLayerItemEntity5.subTitle = getPromotionTips(this.promotion);
            this.realLayerItemCount += dealPromotionData.size();
            arrayList.add(promotionLayerItemEntity5);
            arrayList.addAll(dealPromotionData);
        }
        List<PDCouponCellEntity> list = this.couponInfo;
        if (list != null && !list.isEmpty()) {
            ArrayList arrayList2 = new ArrayList();
            ArrayList arrayList3 = new ArrayList();
            for (PDCouponCellEntity pDCouponCellEntity : this.couponInfo) {
                pDCouponCellEntity.promoImageType = this.businessEnum;
                PromotionLayerItemEntity promotionLayerItemEntity6 = new PromotionLayerItemEntity();
                promotionLayerItemEntity6.mPdCouponCellEntity = pDCouponCellEntity;
                promotionLayerItemEntity6.isHidBestGuideInfo = this.isHidBestGuideInfo;
                promotionLayerItemEntity6.type = 2;
                if (pDCouponCellEntity.applicability) {
                    arrayList2.add(promotionLayerItemEntity6);
                } else {
                    arrayList3.add(promotionLayerItemEntity6);
                }
            }
            if (!arrayList2.isEmpty()) {
                this.realLayerItemCount += arrayList2.size();
                PromotionLayerItemEntity promotionLayerItemEntity7 = new PromotionLayerItemEntity();
                promotionLayerItemEntity7.type = 11;
                arrayList.add(promotionLayerItemEntity7);
                if (this.sskCouponInfo != null) {
                    PromotionLayerItemEntity promotionLayerItemEntity8 = new PromotionLayerItemEntity();
                    promotionLayerItemEntity8.type = 19;
                    promotionLayerItemEntity8.mPdSskCouponEntity = this.sskCouponInfo;
                    arrayList.add(promotionLayerItemEntity8);
                    this.realLayerItemCount++;
                }
                arrayList.addAll(arrayList2);
            } else if (this.sskCouponInfo != null) {
                PromotionLayerItemEntity promotionLayerItemEntity9 = new PromotionLayerItemEntity();
                promotionLayerItemEntity9.type = 11;
                arrayList.add(promotionLayerItemEntity9);
                PromotionLayerItemEntity promotionLayerItemEntity10 = new PromotionLayerItemEntity();
                promotionLayerItemEntity10.type = 19;
                promotionLayerItemEntity10.mPdSskCouponEntity = this.sskCouponInfo;
                arrayList.add(promotionLayerItemEntity10);
                this.realLayerItemCount++;
            }
            if (!arrayList3.isEmpty()) {
                this.realLayerItemCount += arrayList3.size();
                PromotionLayerItemEntity promotionLayerItemEntity11 = new PromotionLayerItemEntity();
                promotionLayerItemEntity11.type = 12;
                arrayList.add(promotionLayerItemEntity11);
                arrayList.addAll(arrayList3);
            }
        } else if (this.sskCouponInfo != null) {
            PromotionLayerItemEntity promotionLayerItemEntity12 = new PromotionLayerItemEntity();
            promotionLayerItemEntity12.type = 11;
            arrayList.add(promotionLayerItemEntity12);
            PromotionLayerItemEntity promotionLayerItemEntity13 = new PromotionLayerItemEntity();
            promotionLayerItemEntity13.type = 19;
            promotionLayerItemEntity13.mPdSskCouponEntity = this.sskCouponInfo;
            arrayList.add(promotionLayerItemEntity13);
            this.realLayerItemCount++;
        }
        if (this.secondLevelGuideInfo != null && !this.isHidBestGuideInfo) {
            PromotionLayerItemEntity promotionLayerItemEntity14 = new PromotionLayerItemEntity();
            promotionLayerItemEntity14.type = 14;
            SecondLevelGuideInfo secondLevelGuideInfo = this.secondLevelGuideInfo;
            promotionLayerItemEntity14.title = secondLevelGuideInfo.sLevelGuideText2;
            promotionLayerItemEntity14.jumpUrl = secondLevelGuideInfo.sLevelJumpUrl;
            promotionLayerItemEntity14.xiiconCode = secondLevelGuideInfo.sLevelIconCode;
            arrayList.add(promotionLayerItemEntity14);
        }
        Iterator<PromotionLayerItemEntity> it = arrayList.iterator();
        while (it.hasNext()) {
            it.next().mNewDsjStyle = z2;
        }
        return arrayList;
    }
}
