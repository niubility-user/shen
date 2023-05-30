package com.jd.lib.productdetail.core.entitys.coupon;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes15.dex */
public class PDCouponAdditionEntity implements Parcelable {
    public static final Parcelable.Creator<PDCouponAdditionEntity> CREATOR = new Parcelable.Creator<PDCouponAdditionEntity>() { // from class: com.jd.lib.productdetail.core.entitys.coupon.PDCouponAdditionEntity.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PDCouponAdditionEntity createFromParcel(Parcel parcel) {
            return new PDCouponAdditionEntity(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PDCouponAdditionEntity[] newArray(int i2) {
            return new PDCouponAdditionEntity[i2];
        }
    };
    public String iconDesc;
    public String iconTitle;
    public String iconUrl;

    public PDCouponAdditionEntity() {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.iconUrl);
        parcel.writeString(this.iconTitle);
        parcel.writeString(this.iconDesc);
    }

    protected PDCouponAdditionEntity(Parcel parcel) {
        this.iconUrl = parcel.readString();
        this.iconTitle = parcel.readString();
        this.iconDesc = parcel.readString();
    }
}
