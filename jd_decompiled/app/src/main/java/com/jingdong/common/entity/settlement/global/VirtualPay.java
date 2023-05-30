package com.jingdong.common.entity.settlement.global;

import android.os.Parcel;
import android.os.Parcelable;
import java.io.Serializable;

/* loaded from: classes5.dex */
public class VirtualPay implements Parcelable, Serializable {
    public static final Parcelable.Creator<VirtualPay> CREATOR = new Parcelable.Creator<VirtualPay>() { // from class: com.jingdong.common.entity.settlement.global.VirtualPay.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public VirtualPay createFromParcel(Parcel parcel) {
            return new VirtualPay(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public VirtualPay[] newArray(int i2) {
            return new VirtualPay[i2];
        }
    };
    public String inputPasswordExplain;
    public boolean isOpenPaymentPassword;
    public boolean isShortPwd;
    public boolean needRemark;

    public VirtualPay() {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeByte(this.needRemark ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.isOpenPaymentPassword ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.isShortPwd ? (byte) 1 : (byte) 0);
        parcel.writeString(this.inputPasswordExplain);
    }

    protected VirtualPay(Parcel parcel) {
        this.needRemark = parcel.readByte() != 0;
        this.isOpenPaymentPassword = parcel.readByte() != 0;
        this.isShortPwd = parcel.readByte() != 0;
        this.inputPasswordExplain = parcel.readString();
    }
}
