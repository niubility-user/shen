package com.unionpay.tsmservice.mi.request;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes11.dex */
public class GetEncryptDataRequestParams extends RequestParams {
    public static final Parcelable.Creator<GetEncryptDataRequestParams> CREATOR = new Parcelable.Creator<GetEncryptDataRequestParams>() { // from class: com.unionpay.tsmservice.mi.request.GetEncryptDataRequestParams.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final GetEncryptDataRequestParams createFromParcel(Parcel parcel) {
            return new GetEncryptDataRequestParams(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final GetEncryptDataRequestParams[] newArray(int i2) {
            return new GetEncryptDataRequestParams[i2];
        }
    };
    private String mPan;
    private int mType;

    public GetEncryptDataRequestParams() {
    }

    public GetEncryptDataRequestParams(Parcel parcel) {
        super(parcel);
        this.mType = parcel.readInt();
        this.mPan = parcel.readString();
    }

    @Override // com.unionpay.tsmservice.mi.request.RequestParams, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getPan() {
        return this.mPan;
    }

    public int getType() {
        return this.mType;
    }

    public void setPan(String str) {
        this.mPan = str;
    }

    public void setType(int i2) {
        this.mType = i2;
    }

    @Override // com.unionpay.tsmservice.mi.request.RequestParams, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        super.writeToParcel(parcel, i2);
        parcel.writeInt(this.mType);
        parcel.writeString(this.mPan);
    }
}
