package com.jd.lib.cashier.sdk.core.model;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes14.dex */
public class PopConfirmBtnJsonParam implements Parcelable {
    public static final Parcelable.Creator<PopConfirmBtnJsonParam> CREATOR = new Parcelable.Creator<PopConfirmBtnJsonParam>() { // from class: com.jd.lib.cashier.sdk.core.model.PopConfirmBtnJsonParam.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PopConfirmBtnJsonParam createFromParcel(Parcel parcel) {
            return new PopConfirmBtnJsonParam(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PopConfirmBtnJsonParam[] newArray(int i2) {
            return new PopConfirmBtnJsonParam[i2];
        }
    };
    public int leaveto;
    public String orderid;

    public PopConfirmBtnJsonParam() {
        this.orderid = "";
        this.leaveto = 0;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.orderid);
        parcel.writeInt(this.leaveto);
    }

    protected PopConfirmBtnJsonParam(Parcel parcel) {
        this.orderid = "";
        this.leaveto = 0;
        this.orderid = parcel.readString();
        this.leaveto = parcel.readInt();
    }
}
