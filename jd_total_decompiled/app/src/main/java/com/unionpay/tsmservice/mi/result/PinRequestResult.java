package com.unionpay.tsmservice.mi.result;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes11.dex */
public class PinRequestResult implements Parcelable {
    public static final Parcelable.Creator<PinRequestResult> CREATOR = new Parcelable.Creator<PinRequestResult>() { // from class: com.unionpay.tsmservice.mi.result.PinRequestResult.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final PinRequestResult createFromParcel(Parcel parcel) {
            return new PinRequestResult(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final PinRequestResult[] newArray(int i2) {
            return new PinRequestResult[i2];
        }
    };
    private Bundle mResultData;

    public PinRequestResult() {
    }

    public PinRequestResult(Parcel parcel) {
        this.mResultData = parcel.readBundle();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public Bundle getResultData() {
        return this.mResultData;
    }

    public void setResultData(Bundle bundle) {
        this.mResultData = bundle;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeBundle(this.mResultData);
    }
}
