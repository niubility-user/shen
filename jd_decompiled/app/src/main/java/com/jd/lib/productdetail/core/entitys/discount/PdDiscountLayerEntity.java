package com.jd.lib.productdetail.core.entitys.discount;

import android.text.TextUtils;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONObject;
import com.jd.lib.productdetail.core.entitys.warebusiness.GiftPool3C;
import com.jd.lib.productdetail.core.entitys.warebusiness.HeadPicGiftInfoEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessExpression;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes15.dex */
public class PdDiscountLayerEntity {
    public BatchJoinCouponInfo bestCouponInfo;
    public BottomTipsEntity bottomTips;
    public WareBusinessExpression expression;
    public WareBusinessExpression expressionFuture;
    public List<JDJSONObject> moreBriefPromotionPreferences;
    public List<JDJSONObject> moreDetailPreferences;
    public String moreTitle;
    public String topTitle;

    /* loaded from: classes15.dex */
    public static class BatchJoinCouponInfo {
        public List<String> bestCouponIds;
        public Integer receiveBiz;
        public List<JDJSONObject> receiveData;
    }

    /* loaded from: classes15.dex */
    public static class BottomTipsEntity {
        public String jumpUrl;
        public String tailIcon;
        public String tip;
    }

    /* loaded from: classes15.dex */
    public static class CouponContent {
        public String buttonText;
        public String rightBottomIcon;
    }

    /* loaded from: classes15.dex */
    public static class CouponOperation {
        public String btnTextAfterRemind;
        public String btnTextBeforeRemind;
        public boolean checkLogin;
        public JDJSONObject data;
        public ArrayList multiplePaymentInfoList;
        public boolean refresh;
        public Long remindTime;
        public boolean showInLayer;
        public int type;
        public String url;
    }

    /* loaded from: classes15.dex */
    public static class DetailPreferenceEntity {
        public String activityId;
        public String batchId;
        public String bizType;
        public boolean btUser;
        public String buttonText;
        public CouponContent contentNotOpen;
        public CouponContent contentNotReceived;
        public CouponContent contentReceived;
        public Long countdownTime;
        public String couponId;
        public int couponIndexInLayer = -1;
        public String dynamicTemplateId;
        public JDJSONObject extData;
        public int extType;
        public String faceValue;
        public List<String> giftImgs;
        public HeadPicGiftInfoEntity.GiftLayerInfosEntity giftInLayer;
        public ArrayList<GiftPool3C> giftPools;
        public List<IconListEntity> iconList;
        public boolean isReceived;
        public String jrBatchId;
        public String markingIconUrl;
        public boolean needOpen;
        public String openLink;
        public CouponOperation operationNotOpen;
        public CouponOperation operationNotReceived;
        public CouponOperation operationReceived;
        public PlanInfo planInfo;
        public CouponOperation promoOperation;
        public int promoTag;
        public String promotionId;
        public String scope;
        public DiscountStyleEntity style;
        public int styleType;
        public int subExtType;
        public String typeDescription;
        public String validTime;
        public String withAvailable;
    }

    /* loaded from: classes15.dex */
    public static class DiscountStyleEntity {
        public String bgImgUrl;
        public String borderColor;
        public String btnBgColor1;
        public String btnBorderColor;
        public String btnTextColor;
        public String countDownTimeColor;
        public String faceValueColor;
        public String grayBgImgUrl;
        public String grayBtnBgColor1;
        public String grayBtnBorderColor;
        public String grayBtnTextColor;
        public String grayCountDownTimeColor;
        public String grayFaceValueColor;
        public String grayScopeColor;
        public String grayTimeColor;
        public String grayTypeDescriptionColor;
        public String grayUseDetailColor;
        public String grayWithAvailableColor;
        public String scopeColor;
        public String splitLineImg;
        public String tempId;
        public String timeColor;
        public String typeDescBorderColor;
        public String typeDescriptionColor;
        public String useDetailColor;
        public String withAvailableColor;
    }

    /* loaded from: classes15.dex */
    public static class IconListEntity {
        public String desc;
        public String detail;
        public String iconUrl;
    }

    /* loaded from: classes15.dex */
    public static class PlanInfo {
        public String laterPay;
        public int plan;
        public String planFee;
        public String rate;
    }

    private int getItemType(int i2) {
        if (i2 == 1) {
            return 1002;
        }
        if (i2 == 2) {
            return 1003;
        }
        if (i2 == 3) {
            return 1004;
        }
        if (i2 == 4) {
            return 1006;
        }
        return i2 == 5 ? 1007 : -1;
    }

    private PdDistLayerItemEntity getLayerItemEntity(JDJSONObject jDJSONObject) {
        PdDistLayerItemEntity pdDistLayerItemEntity = new PdDistLayerItemEntity();
        int itemType = getItemType(jDJSONObject.optInt("styleType"));
        pdDistLayerItemEntity.viewType = itemType;
        if (itemType != 1002) {
            pdDistLayerItemEntity.preferenceEntity = (DetailPreferenceEntity) JDJSON.parseObject(jDJSONObject.toJSONString(), DetailPreferenceEntity.class);
        } else {
            pdDistLayerItemEntity.couponObject = jDJSONObject;
            pdDistLayerItemEntity.preferenceEntity = (DetailPreferenceEntity) JDJSON.parseObject(jDJSONObject.toJSONString(), DetailPreferenceEntity.class);
        }
        return pdDistLayerItemEntity;
    }

    private boolean isHasHandPrice() {
        return (this.expression == null && this.expressionFuture == null) ? false : true;
    }

    private boolean isHasMoreBriefPromotionPreference() {
        return this.moreBriefPromotionPreferences != null;
    }

    private boolean isHasMoreDetialPreference() {
        return this.moreDetailPreferences != null;
    }

    private boolean isSelectExpression() {
        WareBusinessExpression wareBusinessExpression = this.expression;
        return wareBusinessExpression != null && wareBusinessExpression.selected;
    }

    private boolean isSelectExpressionFuture() {
        WareBusinessExpression wareBusinessExpression = this.expressionFuture;
        return wareBusinessExpression != null && wareBusinessExpression.selected;
    }

    public List<JDJSONObject> getBestCanReceiveCoupon() {
        List<JDJSONObject> list;
        ArrayList arrayList = new ArrayList();
        WareBusinessExpression wareBusinessExpression = this.expression;
        if (wareBusinessExpression != null && (list = wareBusinessExpression.bestPreferences) != null && !list.isEmpty()) {
            for (int i2 = 0; i2 < this.expression.bestPreferences.size(); i2++) {
                JDJSONObject jDJSONObject = this.expression.bestPreferences.get(i2);
                if (jDJSONObject != null && jDJSONObject.optInt("styleType") == 1 && !jDJSONObject.optBoolean("isReceived")) {
                    arrayList.add(jDJSONObject);
                }
            }
        }
        return arrayList;
    }

    public ArrayList<PdDistLayerItemEntity> spliceListData() {
        WareBusinessExpression wareBusinessExpression;
        WareBusinessExpression wareBusinessExpression2;
        ArrayList<PdDistLayerItemEntity> arrayList = new ArrayList<>();
        if (isHasHandPrice()) {
            if (!isSelectExpression() && !isSelectExpressionFuture() && (wareBusinessExpression2 = this.expression) != null) {
                wareBusinessExpression2.selected = true;
            }
            WareBusinessExpression wareBusinessExpression3 = this.expression;
            if (wareBusinessExpression3 == null && (wareBusinessExpression = this.expressionFuture) != null) {
                wareBusinessExpression.selected = true;
            }
            if (wareBusinessExpression3 != null && this.expressionFuture == null) {
                wareBusinessExpression3.selected = true;
            }
            if (isSelectExpression()) {
                PdDistLayerItemEntity pdDistLayerItemEntity = new PdDistLayerItemEntity();
                pdDistLayerItemEntity.viewType = 1001;
                pdDistLayerItemEntity.expression = this.expression;
                arrayList.add(pdDistLayerItemEntity);
                List<JDJSONObject> list = this.expression.bestPreferences;
                if (list != null && !list.isEmpty()) {
                    for (int i2 = 0; i2 < this.expression.bestPreferences.size(); i2++) {
                        JDJSONObject jDJSONObject = this.expression.bestPreferences.get(i2);
                        if (jDJSONObject != null) {
                            arrayList.add(getLayerItemEntity(jDJSONObject));
                        }
                    }
                }
            } else if (isSelectExpressionFuture()) {
                PdDistLayerItemEntity pdDistLayerItemEntity2 = new PdDistLayerItemEntity();
                pdDistLayerItemEntity2.viewType = 1001;
                pdDistLayerItemEntity2.expression = this.expressionFuture;
                arrayList.add(pdDistLayerItemEntity2);
                List<JDJSONObject> list2 = this.expressionFuture.bestPreferences;
                if (list2 != null && !list2.isEmpty()) {
                    for (int i3 = 0; i3 < this.expressionFuture.bestPreferences.size(); i3++) {
                        JDJSONObject jDJSONObject2 = this.expressionFuture.bestPreferences.get(i3);
                        if (jDJSONObject2 != null) {
                            arrayList.add(getLayerItemEntity(jDJSONObject2));
                        }
                    }
                }
            }
        }
        if (isHasHandPrice() && ((isHasMoreDetialPreference() || isHasMoreBriefPromotionPreference()) && !TextUtils.isEmpty(this.moreTitle))) {
            PdDistLayerItemEntity pdDistLayerItemEntity3 = new PdDistLayerItemEntity();
            pdDistLayerItemEntity3.viewType = 1005;
            pdDistLayerItemEntity3.moreTitle = this.moreTitle;
            arrayList.add(pdDistLayerItemEntity3);
        }
        if (isHasMoreDetialPreference()) {
            for (int i4 = 0; i4 < this.moreDetailPreferences.size(); i4++) {
                JDJSONObject jDJSONObject3 = this.moreDetailPreferences.get(i4);
                if (jDJSONObject3 != null) {
                    arrayList.add(getLayerItemEntity(jDJSONObject3));
                }
            }
        }
        if (isHasMoreBriefPromotionPreference()) {
            for (int i5 = 0; i5 < this.moreBriefPromotionPreferences.size(); i5++) {
                JDJSONObject jDJSONObject4 = this.moreBriefPromotionPreferences.get(i5);
                if (jDJSONObject4 != null) {
                    PdDistLayerItemEntity pdDistLayerItemEntity4 = new PdDistLayerItemEntity();
                    if (i5 == 0) {
                        pdDistLayerItemEntity4.isFirstbriefPromotion = true;
                    }
                    if (i5 == this.moreBriefPromotionPreferences.size() - 1) {
                        pdDistLayerItemEntity4.isLastbriefPromotion = true;
                    }
                    pdDistLayerItemEntity4.viewType = getItemType(jDJSONObject4.optInt("styleType"));
                    pdDistLayerItemEntity4.preferenceEntity = (DetailPreferenceEntity) JDJSON.parseObject(jDJSONObject4.toJSONString(), DetailPreferenceEntity.class);
                    arrayList.add(pdDistLayerItemEntity4);
                }
            }
        }
        if (this.bottomTips != null) {
            PdDistLayerItemEntity pdDistLayerItemEntity5 = new PdDistLayerItemEntity();
            pdDistLayerItemEntity5.viewType = 1008;
            pdDistLayerItemEntity5.bottomTips = this.bottomTips;
            arrayList.add(pdDistLayerItemEntity5);
        }
        PdDistLayerItemEntity pdDistLayerItemEntity6 = new PdDistLayerItemEntity();
        pdDistLayerItemEntity6.viewType = 1009;
        arrayList.add(pdDistLayerItemEntity6);
        return arrayList;
    }
}
