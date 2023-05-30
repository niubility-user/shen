package com.unionpay.tsmservice.mi.result;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes11.dex */
public class GetSeIdResult implements Parcelable {
    public static final Parcelable.Creator<GetSeIdResult> CREATOR = new Parcelable.Creator<GetSeIdResult>() { // from class: com.unionpay.tsmservice.mi.result.GetSeIdResult.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final GetSeIdResult createFromParcel(Parcel parcel) {
            return new GetSeIdResult(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final GetSeIdResult[] newArray(int i2) {
            return new GetSeIdResult[i2];
        }
    };
    private String mSeId;

    public GetSeIdResult() {
    }

    public GetSeIdResult(Parcel parcel) {
        this.mSeId = parcel.readString();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getSeId() {
        return this.mSeId;
    }

    public void setSeId(String str) {
        this.mSeId = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.mSeId);
    }
}
