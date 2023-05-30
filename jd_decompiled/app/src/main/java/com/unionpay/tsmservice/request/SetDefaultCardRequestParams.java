package com.unionpay.tsmservice.request;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes11.dex */
public class SetDefaultCardRequestParams extends RequestParams {
    public static final Parcelable.Creator<SetDefaultCardRequestParams> CREATOR = new Parcelable.Creator<SetDefaultCardRequestParams>() { // from class: com.unionpay.tsmservice.request.SetDefaultCardRequestParams.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final SetDefaultCardRequestParams createFromParcel(Parcel parcel) {
            return new SetDefaultCardRequestParams(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final SetDefaultCardRequestParams[] newArray(int i2) {
            return new SetDefaultCardRequestParams[i2];
        }
    };
    private String mAppAID;

    public SetDefaultCardRequestParams() {
    }

    public SetDefaultCardRequestParams(Parcel parcel) {
        super(parcel);
        this.mAppAID = parcel.readString();
    }

    public SetDefaultCardRequestParams(String str) {
        this.mAppAID = str;
    }

    public String getAppAID() {
        return this.mAppAID;
    }

    public void setAppAID(String str) {
        this.mAppAID = str;
    }

    @Override // com.unionpay.tsmservice.request.RequestParams, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        super.writeToParcel(parcel, i2);
        parcel.writeString(this.mAppAID);
    }
}
