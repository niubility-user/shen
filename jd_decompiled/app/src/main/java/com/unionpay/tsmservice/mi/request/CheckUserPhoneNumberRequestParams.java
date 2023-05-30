package com.unionpay.tsmservice.mi.request;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes11.dex */
public class CheckUserPhoneNumberRequestParams extends RequestParams {
    public static final Parcelable.Creator<CheckUserPhoneNumberRequestParams> CREATOR = new Parcelable.Creator<CheckUserPhoneNumberRequestParams>() { // from class: com.unionpay.tsmservice.mi.request.CheckUserPhoneNumberRequestParams.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final CheckUserPhoneNumberRequestParams createFromParcel(Parcel parcel) {
            return new CheckUserPhoneNumberRequestParams(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final CheckUserPhoneNumberRequestParams[] newArray(int i2) {
            return new CheckUserPhoneNumberRequestParams[i2];
        }
    };
    private String phoneNumber;

    public CheckUserPhoneNumberRequestParams() {
    }

    protected CheckUserPhoneNumberRequestParams(Parcel parcel) {
        super(parcel);
        this.phoneNumber = parcel.readString();
    }

    @Override // com.unionpay.tsmservice.mi.request.RequestParams, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String str) {
        this.phoneNumber = str;
    }

    @Override // com.unionpay.tsmservice.mi.request.RequestParams, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        super.writeToParcel(parcel, i2);
        parcel.writeString(this.phoneNumber);
    }
}
