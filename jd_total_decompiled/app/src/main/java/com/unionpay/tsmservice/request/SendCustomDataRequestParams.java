package com.unionpay.tsmservice.request;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes11.dex */
public class SendCustomDataRequestParams extends RequestParams {
    public static final Parcelable.Creator<SendCustomDataRequestParams> CREATOR = new Parcelable.Creator<SendCustomDataRequestParams>() { // from class: com.unionpay.tsmservice.request.SendCustomDataRequestParams.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final SendCustomDataRequestParams createFromParcel(Parcel parcel) {
            return new SendCustomDataRequestParams(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final SendCustomDataRequestParams[] newArray(int i2) {
            return new SendCustomDataRequestParams[i2];
        }
    };
    private Bundle mParams;

    public SendCustomDataRequestParams() {
    }

    public SendCustomDataRequestParams(Parcel parcel) {
        super(parcel);
        this.mParams = parcel.readBundle();
    }

    @Override // com.unionpay.tsmservice.request.RequestParams, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public Bundle getParams() {
        return this.mParams;
    }

    public void setParams(Bundle bundle) {
        this.mParams = bundle;
    }

    @Override // com.unionpay.tsmservice.request.RequestParams, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        super.writeToParcel(parcel, i2);
        parcel.writeBundle(this.mParams);
    }
}
