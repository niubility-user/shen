package com.jd.lib.productdetail.core.entitys.coupon;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes15.dex */
public class PdCouponEntity implements Parcelable {
    public static final int COUPON_TYPE_2 = 2;
    public static final int COUPON_TYPE_3 = 3;
    public static final int COUPON_TYPE_4 = 4;
    public static final Parcelable.Creator<PdCouponEntity> CREATOR = new Parcelable.Creator<PdCouponEntity>() { // from class: com.jd.lib.productdetail.core.entitys.coupon.PdCouponEntity.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PdCouponEntity createFromParcel(Parcel parcel) {
            return new PdCouponEntity(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PdCouponEntity[] newArray(int i2) {
            return new PdCouponEntity[i2];
        }
    };
    public int anotherType;
    public boolean applicability;
    public int batchId;
    public String beginHour;
    public String beginTime;
    public List<String> buttonBgColorList;
    public ArrayList<PDCouponAdditionEntity> couponIcon;
    public int couponKind;
    public CouponLiveResource couponResource;
    public int couponStyle;
    public int couponType;
    public String couponValue;
    public int discount;
    public String discountStr;
    public String discountText;
    public String discountValue;
    public String discountValueDesc;
    public String djCollectBillUrl;
    public String encryptedKey;
    public String endHour;
    public String endTime;
    public boolean hasFinanceCoupon;
    public String iconSkinOneUrl;
    public String iconSkinTwoUrl;
    public boolean isMultipleDiscount;
    public boolean isOverlap;
    public boolean isPlusCoupon;
    public int itemType;
    public String jrBatchId;
    public int jumpType;
    public String jumpUrl;
    public String labelTxt;
    public long milliSecond;
    public String name;
    public String pdUserLabel;
    public boolean personalCoupon;
    public int platformType;
    public String plusMemIcon;
    public int quota;
    public String quotaStr;
    public String receiveType;
    public ArrayList<String> rightBgColorList;
    public int roleId;
    public String roleIdCBC;
    public String ruleId;
    public String timeDesc;
    public String tip;
    public String userLabel;
    public boolean isSetTakenIcon = false;
    public boolean isAdditionLayoutOpened = false;

    /* loaded from: classes15.dex */
    public static class CouponLiveResource implements Parcelable {
        public static final Parcelable.Creator<CouponLiveResource> CREATOR = new Parcelable.Creator<CouponLiveResource>() { // from class: com.jd.lib.productdetail.core.entitys.coupon.PdCouponEntity.CouponLiveResource.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public CouponLiveResource createFromParcel(Parcel parcel) {
                return new CouponLiveResource(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public CouponLiveResource[] newArray(int i2) {
                return new CouponLiveResource[i2];
            }
        };
        public String anotherType;
        public String iconDesc;
        public String iconTitle;
        public String iconUrl;

        public CouponLiveResource() {
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            parcel.writeString(this.iconTitle);
            parcel.writeString(this.iconDesc);
            parcel.writeString(this.iconUrl);
            parcel.writeString(this.anotherType);
        }

        protected CouponLiveResource(Parcel parcel) {
            this.iconTitle = parcel.readString();
            this.iconDesc = parcel.readString();
            this.iconUrl = parcel.readString();
            this.anotherType = parcel.readString();
        }
    }

    public PdCouponEntity() {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String getDiscountQuota(String str) {
        return String.format(str, this.quotaStr, this.discountStr);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String getQuota(String str) {
        return String.format(str, this.quotaStr);
    }

    public boolean isDirectDiscountCoupon() {
        return this.couponStyle == 1001;
    }

    public boolean isDiscountCoupon() {
        return this.couponStyle == 3;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean isDongCoupon() {
        return this.couponType == 1;
    }

    public boolean isFansCoupon() {
        return this.anotherType == 2;
    }

    public boolean isFinanceCoupon() {
        return isWhiteBarCoupon() || isPaymentCoupon();
    }

    public boolean isFullSubscribeCoupon() {
        return this.couponStyle == 1002;
    }

    public boolean isHasAdditionInfo() {
        ArrayList<PDCouponAdditionEntity> arrayList = this.couponIcon;
        return (arrayList == null || arrayList.isEmpty()) ? false : true;
    }

    public boolean isJinTieCoupon() {
        return this.couponStyle == 28;
    }

    public boolean isJingTieCoupon() {
        return this.anotherType == 7;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean isPaymentCoupon() {
        return this.couponType == 1002;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean isPlusJtCoupon() {
        return this.couponType == 401;
    }

    public boolean isPlusProCoupon() {
        return this.couponStyle == 211;
    }

    public boolean isShouGouCoupon() {
        return this.anotherType == 8;
    }

    public boolean isShowCountdownTime() {
        return this.milliSecond > 60000;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean isWhiteBarCoupon() {
        return this.couponType == 1001;
    }

    public void readToParcel(Parcel parcel) {
        this.quota = parcel.readInt();
        this.quotaStr = parcel.readString();
        this.platformType = parcel.readInt();
        this.encryptedKey = parcel.readString();
        this.couponType = parcel.readInt();
        this.beginTime = parcel.readString();
        this.endTime = parcel.readString();
        this.beginHour = parcel.readString();
        this.endHour = parcel.readString();
        this.name = parcel.readString();
        this.applicability = parcel.readInt() != 0;
        this.discount = parcel.readInt();
        this.discountStr = parcel.readString();
        this.batchId = parcel.readInt();
        this.roleId = parcel.readInt();
        this.roleIdCBC = parcel.readString();
        this.timeDesc = parcel.readString();
        this.couponKind = parcel.readInt();
        this.itemType = parcel.readInt();
        this.isSetTakenIcon = parcel.readInt() != 0;
        this.couponIcon = parcel.createTypedArrayList(PDCouponAdditionEntity.CREATOR);
        this.couponStyle = parcel.readInt();
        this.couponValue = parcel.readString();
        this.isMultipleDiscount = parcel.readInt() != 0;
        this.discountValue = parcel.readString();
        this.discountValueDesc = parcel.readString();
        this.discountText = parcel.readString();
        this.isOverlap = parcel.readInt() != 0;
        this.isPlusCoupon = parcel.readInt() != 0;
        this.milliSecond = parcel.readLong();
        this.labelTxt = parcel.readString();
        this.anotherType = parcel.readInt();
        this.djCollectBillUrl = parcel.readString();
        this.iconSkinOneUrl = parcel.readString();
        this.iconSkinTwoUrl = parcel.readString();
        this.rightBgColorList = parcel.createStringArrayList();
        this.buttonBgColorList = parcel.createStringArrayList();
        this.couponResource = (CouponLiveResource) parcel.readParcelable(PdCouponEntity.class.getClassLoader());
        this.jumpType = parcel.readInt();
        this.jumpUrl = parcel.readString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeInt(this.quota);
        parcel.writeString(this.quotaStr);
        parcel.writeInt(this.platformType);
        parcel.writeString(this.encryptedKey);
        parcel.writeInt(this.couponType);
        parcel.writeString(this.beginTime);
        parcel.writeString(this.endTime);
        parcel.writeString(this.beginHour);
        parcel.writeString(this.endHour);
        parcel.writeString(this.name);
        parcel.writeInt(this.applicability ? 1 : 0);
        parcel.writeInt(this.discount);
        parcel.writeString(this.discountStr);
        parcel.writeInt(this.batchId);
        parcel.writeInt(this.roleId);
        parcel.writeString(this.roleIdCBC);
        parcel.writeString(this.timeDesc);
        parcel.writeInt(this.couponKind);
        parcel.writeInt(this.itemType);
        parcel.writeInt(this.isSetTakenIcon ? 1 : 0);
        parcel.writeTypedList(this.couponIcon);
        parcel.writeInt(this.couponStyle);
        parcel.writeString(this.couponValue);
        parcel.writeInt(this.isMultipleDiscount ? 1 : 0);
        parcel.writeInt(this.isPlusCoupon ? 1 : 0);
        parcel.writeString(this.discountValue);
        parcel.writeString(this.discountValueDesc);
        parcel.writeString(this.discountText);
        parcel.writeInt(this.isOverlap ? 1 : 0);
        parcel.writeLong(this.milliSecond);
        parcel.writeString(this.labelTxt);
        parcel.writeInt(this.anotherType);
        parcel.writeString(this.djCollectBillUrl);
        parcel.writeString(this.iconSkinTwoUrl);
        parcel.writeString(this.iconSkinOneUrl);
        parcel.writeStringList(this.rightBgColorList);
        parcel.writeStringList(this.buttonBgColorList);
        parcel.writeParcelable(this.couponResource, i2);
        parcel.writeInt(this.jumpType);
        parcel.writeString(this.jumpUrl);
    }

    public PdCouponEntity(Parcel parcel) {
        readToParcel(parcel);
    }
}
