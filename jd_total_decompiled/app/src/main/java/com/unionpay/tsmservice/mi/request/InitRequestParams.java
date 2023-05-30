package com.unionpay.tsmservice.mi.request;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes11.dex */
public class InitRequestParams extends RequestParams {
    public static final Parcelable.Creator<InitRequestParams> CREATOR = new Parcelable.Creator<InitRequestParams>() { // from class: com.unionpay.tsmservice.mi.request.InitRequestParams.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final InitRequestParams createFromParcel(Parcel parcel) {
            return new InitRequestParams(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final InitRequestParams[] newArray(int i2) {
            return new InitRequestParams[i2];
        }
    };
    private String mSignature;

    public InitRequestParams() {
        this.mSignature = "";
    }

    public InitRequestParams(Parcel parcel) {
        super(parcel);
        this.mSignature = "";
        this.mSignature = parcel.readString();
    }

    public String getSignature() {
        return this.mSignature;
    }

    public void setSignature(String str) {
        this.mSignature = str;
    }

    @Override // com.unionpay.tsmservice.mi.request.RequestParams, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        super.writeToParcel(parcel, i2);
        parcel.writeString(this.mSignature);
    }
}
