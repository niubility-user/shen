package com.unionpay.tsmservice.request;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes11.dex */
public class OnlinePaymentVerifyRequestParams extends RequestParams {
    public static final Parcelable.Creator<OnlinePaymentVerifyRequestParams> CREATOR = new Parcelable.Creator<OnlinePaymentVerifyRequestParams>() { // from class: com.unionpay.tsmservice.request.OnlinePaymentVerifyRequestParams.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final OnlinePaymentVerifyRequestParams createFromParcel(Parcel parcel) {
            return new OnlinePaymentVerifyRequestParams(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final OnlinePaymentVerifyRequestParams[] newArray(int i2) {
            return new OnlinePaymentVerifyRequestParams[i2];
        }
    };
    private String mAId;
    private String mOrderNumber;
    private Bundle mResource;

    public OnlinePaymentVerifyRequestParams() {
    }

    public OnlinePaymentVerifyRequestParams(Parcel parcel) {
        super(parcel);
        this.mResource = parcel.readBundle();
        this.mOrderNumber = parcel.readString();
        this.mAId = parcel.readString();
    }

    @Override // com.unionpay.tsmservice.request.RequestParams, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getAId() {
        return this.mAId;
    }

    public String getOrderNumber() {
        return this.mOrderNumber;
    }

    public Bundle getResource() {
        return this.mResource;
    }

    public void setAId(String str) {
        this.mAId = str;
    }

    public void setOrderNumber(String str) {
        this.mOrderNumber = str;
    }

    public void setResource(Bundle bundle) {
        this.mResource = bundle;
    }

    @Override // com.unionpay.tsmservice.request.RequestParams, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        super.writeToParcel(parcel, i2);
        parcel.writeBundle(this.mResource);
        parcel.writeString(this.mOrderNumber);
        parcel.writeString(this.mAId);
    }
}
