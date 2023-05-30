package com.unionpay.tsmservice.mi.result;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes11.dex */
public class PayResultNotifyResult implements Parcelable {
    public static final Parcelable.Creator<PayResultNotifyResult> CREATOR = new Parcelable.Creator<PayResultNotifyResult>() { // from class: com.unionpay.tsmservice.mi.result.PayResultNotifyResult.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final PayResultNotifyResult createFromParcel(Parcel parcel) {
            return new PayResultNotifyResult(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final PayResultNotifyResult[] newArray(int i2) {
            return new PayResultNotifyResult[i2];
        }
    };
    private Bundle mResultData;

    public PayResultNotifyResult() {
    }

    public PayResultNotifyResult(Parcel parcel) {
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
