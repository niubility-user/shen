package com.jingdong.app.mall.mylib.CouponUnit;

import android.text.TextUtils;
import java.util.List;

/* loaded from: classes4.dex */
public class CouponEntity {
    public String bgImgUrl;
    public String bottomText;
    public String buttonText;
    public String couponBgColorNum;
    public String endDay;
    public String endMinute;
    public String faceValue;
    public int futureBtnStatus;
    public String futureContent;
    public String futureTimeIcon;
    public boolean haveLiveIcon;
    public String iconButtonColor;
    public List<String> iconColorList;
    public String id;
    public boolean isBrandVip;
    public boolean isOpenDetail;
    public boolean isPayVip;
    public List<CouponLabelEntity> labelLists;
    public String liveCouponText;
    public String markingIconUrl;
    public int markingType;
    public String priceBgImgUrl;
    public List<String> receiveColorList;
    public String scope;
    public String startAndEndTime;
    public String startDay;
    public String startMinute;
    private String tipContent;
    public String titleImageUrl;
    public String typeDescription;
    public String withAvailable;
    public boolean withAvailableMore;
    public int typeCoupon = -1;
    public boolean isShowRightArrow = false;
    public boolean isHourCoupon = false;
    public boolean canShare = false;
    public boolean canSell = false;
    public boolean isBaiTiao = false;
    public boolean canOverlay = false;
    public String faceType = "0";
    public boolean isReceived = false;
    public boolean canUse = false;
    public CharSequence countDownTimeCharSequence = null;

    public String getFaceValue() {
        return TextUtils.isEmpty(this.faceValue) ? "" : this.faceValue;
    }

    public String getFaceValueNoRmbFlag() {
        return (!TextUtils.isEmpty(this.faceValue) && this.faceValue.length() > 2) ? this.faceValue.substring(1) : "";
    }

    public String getId() {
        return this.id;
    }

    public String getScope() {
        return this.scope;
    }

    public String getTipContent() {
        return this.tipContent;
    }

    public int getTypeCoupon() {
        return this.typeCoupon;
    }

    public String getTypeDescription() {
        return this.typeDescription;
    }

    public boolean isCanSell() {
        return this.canSell;
    }

    public boolean isCanShare() {
        return this.canShare;
    }

    public void setCanSell(boolean z) {
        this.canSell = z;
    }

    public void setCanShare(boolean z) {
        this.canShare = z;
    }

    public void setId(String str) {
        this.id = str;
    }

    public void setTipContent(String str) {
        this.tipContent = str;
    }

    public void setTypeDescription(String str) {
        this.typeDescription = str;
    }
}
