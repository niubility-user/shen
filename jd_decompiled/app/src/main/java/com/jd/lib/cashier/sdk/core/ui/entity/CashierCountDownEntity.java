package com.jd.lib.cashier.sdk.core.ui.entity;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes14.dex */
public class CashierCountDownEntity implements Parcelable {
    public static final Parcelable.Creator<CashierCountDownEntity> CREATOR = new Parcelable.Creator<CashierCountDownEntity>() { // from class: com.jd.lib.cashier.sdk.core.ui.entity.CashierCountDownEntity.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CashierCountDownEntity createFromParcel(Parcel parcel) {
            return new CashierCountDownEntity(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CashierCountDownEntity[] newArray(int i2) {
            return new CashierCountDownEntity[i2];
        }
    };
    public String btnText;
    public String btnUrl;
    public String message;
    public String title;

    public CashierCountDownEntity() {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.title);
        parcel.writeString(this.message);
        parcel.writeString(this.btnText);
        parcel.writeString(this.btnUrl);
    }

    protected CashierCountDownEntity(Parcel parcel) {
        this.title = parcel.readString();
        this.message = parcel.readString();
        this.btnText = parcel.readString();
        this.btnUrl = parcel.readString();
    }
}
