package com.unionpay.tsmservice.mi.request;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes11.dex */
public class GetSeIdRequestParams extends RequestParams {
    public static final Parcelable.Creator<GetSeIdRequestParams> CREATOR = new Parcelable.Creator<GetSeIdRequestParams>() { // from class: com.unionpay.tsmservice.mi.request.GetSeIdRequestParams.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final GetSeIdRequestParams createFromParcel(Parcel parcel) {
            return new GetSeIdRequestParams(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final GetSeIdRequestParams[] newArray(int i2) {
            return new GetSeIdRequestParams[i2];
        }
    };

    public GetSeIdRequestParams() {
    }

    public GetSeIdRequestParams(Parcel parcel) {
        super(parcel);
    }

    @Override // com.unionpay.tsmservice.mi.request.RequestParams, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.unionpay.tsmservice.mi.request.RequestParams, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        super.writeToParcel(parcel, i2);
    }
}
