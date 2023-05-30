package com.unionpay.tsmservice.mi.request;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes11.dex */
public class PayResultNotifyRequestParams extends RequestParams {
    public static final Parcelable.Creator<PayResultNotifyRequestParams> CREATOR = new Parcelable.Creator<PayResultNotifyRequestParams>() { // from class: com.unionpay.tsmservice.mi.request.PayResultNotifyRequestParams.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final PayResultNotifyRequestParams createFromParcel(Parcel parcel) {
            return new PayResultNotifyRequestParams(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final PayResultNotifyRequestParams[] newArray(int i2) {
            return new PayResultNotifyRequestParams[i2];
        }
    };
    private Bundle mParams;

    public PayResultNotifyRequestParams() {
    }

    public PayResultNotifyRequestParams(Parcel parcel) {
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
