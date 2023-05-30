package com.unionpay.tsmservice.mi.request;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes11.dex */
public class CancelPayRequestParams extends RequestParams {
    public static final Parcelable.Creator<CancelPayRequestParams> CREATOR = new Parcelable.Creator<CancelPayRequestParams>() { // from class: com.unionpay.tsmservice.mi.request.CancelPayRequestParams.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final CancelPayRequestParams createFromParcel(Parcel parcel) {
            return new CancelPayRequestParams(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final CancelPayRequestParams[] newArray(int i2) {
            return new CancelPayRequestParams[i2];
        }
    };
    private Bundle mParams;

    public CancelPayRequestParams() {
    }

    public CancelPayRequestParams(Parcel parcel) {
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
