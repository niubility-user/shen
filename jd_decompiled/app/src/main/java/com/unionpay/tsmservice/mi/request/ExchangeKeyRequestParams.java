package com.unionpay.tsmservice.mi.request;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes11.dex */
public class ExchangeKeyRequestParams extends RequestParams {
    public static final Parcelable.Creator<ExchangeKeyRequestParams> CREATOR = new Parcelable.Creator<ExchangeKeyRequestParams>() { // from class: com.unionpay.tsmservice.mi.request.ExchangeKeyRequestParams.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final ExchangeKeyRequestParams createFromParcel(Parcel parcel) {
            return new ExchangeKeyRequestParams(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final ExchangeKeyRequestParams[] newArray(int i2) {
            return new ExchangeKeyRequestParams[i2];
        }
    };
    private String mTempKey;
    private int mType;

    public ExchangeKeyRequestParams() {
    }

    public ExchangeKeyRequestParams(Parcel parcel) {
        super(parcel);
        this.mType = parcel.readInt();
        this.mTempKey = parcel.readString();
    }

    public String getTempKey() {
        return this.mTempKey;
    }

    public int getType() {
        return this.mType;
    }

    public void setTempKey(String str) {
        this.mTempKey = str;
    }

    public void setType(int i2) {
        this.mType = i2;
    }

    @Override // com.unionpay.tsmservice.mi.request.RequestParams, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        super.writeToParcel(parcel, i2);
        parcel.writeInt(this.mType);
        parcel.writeString(this.mTempKey);
    }
}
