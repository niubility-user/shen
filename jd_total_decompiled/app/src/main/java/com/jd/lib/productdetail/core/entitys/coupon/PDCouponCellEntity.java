package com.jd.lib.productdetail.core.entitys.coupon;

import android.graphics.Typeface;
import android.text.TextUtils;
import com.jd.lib.productdetail.core.entitys.coupon.PdCouponEntity;
import com.jd.lib.productdetail.core.utils.PDUtils;
import com.jingdong.app.mall.mylib.CouponUnit.CouponEntity;
import com.jingdong.app.mall.mylib.CouponUnit.CouponLabelEntity;
import com.jingdong.app.mall.mylib.CouponUnit.CouponUtil;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes15.dex */
public class PDCouponCellEntity extends PdCouponEntity {
    private String buttonText;
    private boolean canOverlay;
    public String couponDesc;
    public String couponId;
    private String couponTypeText;
    private String detailText;
    private String endDay;
    private String endMinute;
    private int faceType;
    private String faceValue;
    public int futureBtnStatus;
    public String futureContent;
    public boolean futureCoupon;
    public long futureStartTime;
    public String futureTimeIcon;
    public GuideUserIconMap guideUserIconMap;
    public GwcCoupon gwcCoupon;
    public String iconButtonColor;
    public boolean isBestCoupon;
    private boolean isHourCoupon;
    private boolean isLeftArrowVisi;
    private boolean isRightArrowVisi;
    private boolean ishasReceiverd;
    public String marketingIconUrl;
    public int notPlusCoupon;
    public int promoImageType;
    public String promotionIconUrl;
    private String startAndEntTime;
    private String startDay;
    private String startMinute;
    private int typeCoupon;
    private String useDesc;

    /* loaded from: classes15.dex */
    public static class GuideUserIconMap {
        public String iconTitle;
        public String jumpType;
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
            this.faceValue = "\u00a5" + this.discountStr;
            this.useDesc = "";
            if (isDongCoupon()) {
                if (isJinTieCoupon()) {
                    this.useDesc = getQuota("\u6bcf\u6ee1%1$s\u5143\u53ef\u51cf");
                } else if (isFullSubscribeCoupon()) {
                    this.useDesc = getQuota("\u6ee1%1$s\u5143\u53ef\u51cf");
                } else {
                    this.useDesc = getQuota("\u6ee1%1$s\u5143\u53ef\u51cf");
                }
            } else if (!isWhiteBarCoupon() && !isPaymentCoupon()) {
                if (isPlusJtCoupon()) {
                    if (isJinTieCoupon()) {
                        this.useDesc = getQuota("\u6bcf\u6ee1%1$s\u5143\u53ef\u51cf");
                    } else if (isFullSubscribeCoupon()) {
                        this.useDesc = getQuota("\u6ee1%1$s\u5143\u53ef\u51cf");
                    } else if (isPlusProCoupon()) {
                        this.useDesc = getQuota("\u5b9e\u4ed8\u6ee1%1$s\u5143\u53ef\u7528");
                    } else {
                        this.useDesc = getQuota("\u6ee1%1$s\u5143\u53ef\u51cf");
                    }
                }
            } else if (isDirectDiscountCoupon()) {
                this.useDesc = this.discountValueDesc;
            } else if (isFullSubscribeCoupon() && PDUtils.stringToInt(this.quotaStr) > 0 && PDUtils.stringToInt(this.discountStr) > 0) {
                this.useDesc = getDiscountQuota("\u6ee1%1$s\u5143\u51cf%2$s\u5143");
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
        } else if (isPlusJtCoupon()) {
            this.typeCoupon = 401;
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
        if (this.futureCoupon) {
            couponEntity.futureContent = this.futureContent;
            couponEntity.futureTimeIcon = this.futureTimeIcon;
            int i2 = this.futureBtnStatus;
            couponEntity.futureBtnStatus = i2;
            if (i2 == 1) {
                this.buttonText = "\u63d0\u9192\u6211";
            } else {
                this.buttonText = "\u53d6\u6d88\u63d0\u9192";
            }
        } else if (this.applicability) {
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
                this.detailText = "";
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
        couponEntity.isBrandVip = TextUtils.equals(this.userLabel, "107");
        couponEntity.isPayVip = TextUtils.equals(this.userLabel, "115");
        couponEntity.faceValue = this.faceValue;
        couponEntity.faceType = String.valueOf(this.faceType);
        if (!TextUtils.isEmpty(this.couponDesc)) {
            couponEntity.withAvailable = this.couponDesc;
        } else {
            couponEntity.withAvailable = this.useDesc;
        }
        couponEntity.iconButtonColor = this.iconButtonColor;
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
        couponEntity.canOverlay = this.canOverlay;
        couponEntity.isShowRightArrow = this.isRightArrowVisi;
        PdCouponEntity.CouponLiveResource couponLiveResource = this.couponResource;
        if (couponLiveResource != null) {
            couponEntity.haveLiveIcon = true;
            couponEntity.liveCouponText = couponLiveResource.iconTitle;
        }
        couponEntity.markingType = this.anotherType;
        couponEntity.bgImgUrl = this.iconSkinTwoUrl;
        couponEntity.priceBgImgUrl = this.iconSkinOneUrl;
        couponEntity.iconColorList = this.rightBgColorList;
        couponEntity.receiveColorList = this.buttonBgColorList;
        ArrayList arrayList = new ArrayList();
        ArrayList<PDCouponAdditionEntity> arrayList2 = this.couponIcon;
        if (arrayList2 != null && !arrayList2.isEmpty()) {
            Iterator<PDCouponAdditionEntity> it = this.couponIcon.iterator();
            while (it.hasNext()) {
                PDCouponAdditionEntity next = it.next();
                if (next != null) {
                    CouponLabelEntity couponLabelEntity = new CouponLabelEntity();
                    couponLabelEntity.iconTitle = next.iconTitle;
                    couponLabelEntity.iconUrl = next.iconUrl;
                    couponLabelEntity.iconDesc = next.iconDesc;
                    arrayList.add(couponLabelEntity);
                }
            }
        }
        if (!TextUtils.isEmpty(this.detailText)) {
            CouponLabelEntity couponLabelEntity2 = new CouponLabelEntity();
            couponLabelEntity2.iconTitle = this.detailText;
            arrayList.add(couponLabelEntity2);
        }
        if (!arrayList.isEmpty()) {
            couponEntity.labelLists = arrayList;
        }
        return couponEntity;
    }

    public boolean isBrandMember() {
        return TextUtils.equals(this.userLabel, "107");
    }
}
