package com.jingdong.common.entity.settlement.global;

import android.os.Parcel;
import android.os.Parcelable;
import java.io.Serializable;

/* loaded from: classes5.dex */
public class GlobalCouponInfos implements Parcelable, Serializable {
    public static final Parcelable.Creator<GlobalCouponInfos> CREATOR = new Parcelable.Creator<GlobalCouponInfos>() { // from class: com.jingdong.common.entity.settlement.global.GlobalCouponInfos.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public GlobalCouponInfos createFromParcel(Parcel parcel) {
            return new GlobalCouponInfos(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public GlobalCouponInfos[] newArray(int i2) {
            return new GlobalCouponInfos[i2];
        }
    };
    public int availableNum;
    public String couponSign;
    public int totalNum;
    public int usedNum;

    public GlobalCouponInfos() {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeInt(this.totalNum);
        parcel.writeInt(this.availableNum);
        parcel.writeInt(this.usedNum);
        parcel.writeString(this.couponSign);
    }

    protected GlobalCouponInfos(Parcel parcel) {
        this.totalNum = parcel.readInt();
        this.availableNum = parcel.readInt();
        this.usedNum = parcel.readInt();
        this.couponSign = parcel.readString();
    }
}
