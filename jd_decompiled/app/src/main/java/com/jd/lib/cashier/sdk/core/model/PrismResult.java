package com.jd.lib.cashier.sdk.core.model;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes14.dex */
public class PrismResult implements Parcelable, ICheckNullObj {
    public static final Parcelable.Creator<PrismResult> CREATOR = new Parcelable.Creator<PrismResult>() { // from class: com.jd.lib.cashier.sdk.core.model.PrismResult.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PrismResult createFromParcel(Parcel parcel) {
            return new PrismResult(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PrismResult[] newArray(int i2) {
            return new PrismResult[i2];
        }
    };
    public String touchstone_expids;

    public PrismResult() {
        this.touchstone_expids = "";
    }

    @Override // com.jd.lib.cashier.sdk.core.model.ICheckNullObj
    public void checkNullObjAndInit() {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.touchstone_expids);
    }

    protected PrismResult(Parcel parcel) {
        this.touchstone_expids = "";
        this.touchstone_expids = parcel.readString();
    }
}
