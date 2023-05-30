package com.unionpay.tsmservice.mi.result;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes11.dex */
public class GetEncryptDataResult implements Parcelable {
    public static final Parcelable.Creator<GetEncryptDataResult> CREATOR = new Parcelable.Creator<GetEncryptDataResult>() { // from class: com.unionpay.tsmservice.mi.result.GetEncryptDataResult.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final GetEncryptDataResult createFromParcel(Parcel parcel) {
            return new GetEncryptDataResult(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final GetEncryptDataResult[] newArray(int i2) {
            return new GetEncryptDataResult[i2];
        }
    };
    private String mData;

    public GetEncryptDataResult() {
    }

    public GetEncryptDataResult(Parcel parcel) {
        this.mData = parcel.readString();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getData() {
        return this.mData;
    }

    public void setData(String str) {
        this.mData = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.mData);
    }
}
