package com.unionpay.tsmservice.mi.request;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes11.dex */
public class PinRequestRequestParams extends RequestParams {
    public static final Parcelable.Creator<PinRequestRequestParams> CREATOR = new Parcelable.Creator<PinRequestRequestParams>() { // from class: com.unionpay.tsmservice.mi.request.PinRequestRequestParams.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final PinRequestRequestParams createFromParcel(Parcel parcel) {
            return new PinRequestRequestParams(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final PinRequestRequestParams[] newArray(int i2) {
            return new PinRequestRequestParams[i2];
        }
    };
    private Bundle mParams;

    public PinRequestRequestParams() {
    }

    public PinRequestRequestParams(Parcel parcel) {
        super(parcel);
        this.mParams = parcel.readBundle();
    }

    @Override // com.unionpay.tsmservice.mi.request.RequestParams, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public Bundle getParams() {
        return this.mParams;
    }

    public void setParams(Bundle bundle) {
        this.mParams = bundle;
    }

    @Override // com.unionpay.tsmservice.mi.request.RequestParams, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        super.writeToParcel(parcel, i2);
        parcel.writeBundle(this.mParams);
    }
}
