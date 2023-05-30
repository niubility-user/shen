package com.jingdong.app.mall.bundle.styleinfoview.entitys.coupon;

import android.graphics.Typeface;
import android.text.TextUtils;
import com.jingdong.app.mall.mylib.CouponUnit.CouponEntity;
import com.jingdong.app.mall.mylib.CouponUnit.CouponUtil;

/* loaded from: classes3.dex */
public class PDCouponCellEntity extends PdCouponEntity {
    private String buttonText;
    private boolean canOverlay;
    public String couponId;
    private String couponTypeText;
    private String detailText;
    private String endDay;
    private String endMinute;
    private int faceType;
    private String faceValue;
    public GuideUserIconMap guideUserIconMap;
    public boolean isBestCoupon;
    private boolean isHourCoupon;
    private boolean isLeftArrowVisi;
    private boolean isRightArrowVisi;
    private boolean ishasReceiverd;
    public String marketingIconUrl;
    public int promoImageType;
    public String promotionIconUrl;
    private String startAndEntTime;
    private String startDay;
    private String startMinute;
    private int typeCoupon;
    private String useDesc;

    /* loaded from: classes3.dex */
    public static class GuideUserIconMap {
        public String iconTitle;
        public String jumpUrl;
    }

    private boolean isShowCouponKind() {
        int i2 = this.couponKind;
        return i2 == 1 || i2 == 2 || i2 == 3 || i2 == 4;
    }

    public CouponEntity getCommonCouponEntity(String str) {
        CouponEntity couponEntity = new CouponEntity();
        if (isDiscountCoupon()) {
            this.faceValue = this.discountValue;
            this.useDesc = this.discountValueDesc;
            if (this.isMultipleDiscount) {
                this.faceType = 2;
                this.isLeftArrowVisi = true;
            } else {
                this.faceType = 1;
                this.isLeftArrowVisi = false;
            }
        } else {
            this.faceValue = "\u00a5" + this.discount;
            this.useDesc = "";
            if (isDongCoupon()) {
                if (isJinTieCoupon()) {
                    this.useDesc = getQuota("\u6bcf\u6ee1%1$d\u5143\u53ef\u51cf");
                } else if (isFullSubscribeCoupon()) {
                    this.useDesc = getQuota("\u6ee1%1$d\u5143\u53ef\u51cf");
                } else {
                    this.useDesc = getQuota("\u6ee1%1$d\u5143\u53ef\u51cf");
                }
            }
            this.faceType = 0;
            this.isLeftArrowVisi = false;
        }
        if (isDongCoupon()) {
            this.typeCoupon = 1;
        } else if (isWhiteBarCoupon()) {
            this.typeCoupon = 3;
        } else if (isPaymentCoupon()) {
            this.typeCoupon = 3;
        } else {
            this.typeCoupon = 0;
        }
        this.couponTypeText = this.labelTxt;
        if (!TextUtils.isEmpty(this.beginTime) && !TextUtils.isEmpty(this.endTime)) {
            if (!TextUtils.isEmpty(this.beginHour) && !TextUtils.isEmpty(this.endHour)) {
                this.isHourCoupon = true;
                this.startAndEntTime = "";
                this.startDay = this.beginTime;
                this.endDay = this.endTime;
                this.startMinute = this.beginHour;
                this.endMinute = this.endHour;
            } else {
                this.isHourCoupon = false;
                this.startAndEntTime = this.beginTime + "-" + this.endTime;
            }
        } else {
            this.isHourCoupon = false;
            this.startAndEntTime = this.timeDesc;
        }
        if (this.applicability) {
            GuideUserIconMap guideUserIconMap = this.guideUserIconMap;
            this.buttonText = (guideUserIconMap == null || TextUtils.isEmpty(guideUserIconMap.iconTitle)) ? "\u70b9\u51fb\u9886\u53d6" : this.guideUserIconMap.iconTitle;
            this.ishasReceiverd = false;
        } else {
            if (TextUtils.isEmpty(str) && this.couponKind == 2) {
                this.buttonText = "";
            } else {
                this.buttonText = (!isShowCouponKind() || isFinanceCoupon()) ? "" : "\u53ef\u7528\u5546\u54c1";
            }
            this.ishasReceiverd = this.isSetTakenIcon;
        }
        couponEntity.canUse = !this.applicability;
        this.isRightArrowVisi = isHasAdditionInfo();
        this.canOverlay = false;
        this.detailText = "";
        if (isHasAdditionInfo()) {
            if (this.isOverlap) {
                this.canOverlay = true;
                this.detailText = "";
            } else if (isDiscountCoupon() && !this.isPlusCoupon) {
                this.canOverlay = false;
                this.detailText = "\u8be6\u7ec6\u4fe1\u606f";
            }
        }
        if (isWhiteBarCoupon() || isPaymentCoupon()) {
            this.canOverlay = false;
            this.detailText = this.tip;
        }
        if (isShowCountdownTime()) {
            couponEntity.countDownTimeCharSequence = CouponUtil.formatCountDownLimitTime(this.milliSecond, Typeface.DEFAULT);
        } else {
            couponEntity.countDownTimeCharSequence = null;
        }
        if (isJingTieCoupon() || isShouGouCoupon()) {
            couponEntity.titleImageUrl = this.promotionIconUrl;
        }
        couponEntity.faceValue = this.faceValue;
        couponEntity.faceType = String.valueOf(this.faceType);
        couponEntity.withAvailable = this.useDesc;
        couponEntity.withAvailableMore = this.isLeftArrowVisi;
        couponEntity.typeCoupon = this.typeCoupon;
        couponEntity.typeDescription = this.couponTypeText;
        couponEntity.scope = this.name;
        couponEntity.markingIconUrl = this.marketingIconUrl;
        couponEntity.startAndEndTime = this.startAndEntTime;
        couponEntity.isHourCoupon = this.isHourCoupon;
        couponEntity.startDay = this.startDay;
        couponEntity.endDay = this.endDay;
        couponEntity.startMinute = this.startMinute;
        couponEntity.endMinute = this.endMinute;
        couponEntity.isReceived = this.ishasReceiverd;
        couponEntity.buttonText = this.buttonText;
        couponEntity.bottomText = this.detailText;
        couponEntity.canOverlay = this.canOverlay;
        couponEntity.isShowRightArrow = this.isRightArrowVisi;
        couponEntity.markingType = this.anotherType;
        couponEntity.bgImgUrl = this.iconSkinTwoUrl;
        couponEntity.priceBgImgUrl = this.iconSkinOneUrl;
        couponEntity.iconColorList = this.rightBgColorList;
        return couponEntity;
    }
}
