package com.unionpay.tsmservice.request;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes11.dex */
public class GetSeAppListRequestParams extends RequestParams {
    public static final Parcelable.Creator<GetSeAppListRequestParams> CREATOR = new Parcelable.Creator<GetSeAppListRequestParams>() { // from class: com.unionpay.tsmservice.request.GetSeAppListRequestParams.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final GetSeAppListRequestParams createFromParcel(Parcel parcel) {
            return new GetSeAppListRequestParams(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final GetSeAppListRequestParams[] newArray(int i2) {
            return new GetSeAppListRequestParams[i2];
        }
    };

    public GetSeAppListRequestParams() {
    }

    public GetSeAppListRequestParams(Parcel parcel) {
        super(parcel);
    }

    @Override // com.unionpay.tsmservice.request.RequestParams, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.unionpay.tsmservice.request.RequestParams, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        super.writeToParcel(parcel, i2);
    }
}
