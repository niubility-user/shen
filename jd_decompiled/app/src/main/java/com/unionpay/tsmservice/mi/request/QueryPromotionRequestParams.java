package com.unionpay.tsmservice.mi.request;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes11.dex */
public class QueryPromotionRequestParams extends RequestParams {
    public static final Parcelable.Creator<QueryPromotionRequestParams> CREATOR = new Parcelable.Creator<QueryPromotionRequestParams>() { // from class: com.unionpay.tsmservice.mi.request.QueryPromotionRequestParams.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final QueryPromotionRequestParams createFromParcel(Parcel parcel) {
            return new QueryPromotionRequestParams(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final QueryPromotionRequestParams[] newArray(int i2) {
            return new QueryPromotionRequestParams[i2];
        }
    };
    private Bundle mParams;

    public QueryPromotionRequestParams() {
    }

    public QueryPromotionRequestParams(Parcel parcel) {
        super(parcel);
        this.mParams = parcel.readBundle();
    }

    @Override // com.unionpay.tsmservice.mi.request.RequestParams, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public Bundle getParams() {
        return this.mParams;
    }

    public void setParams(Bundle bundle) {
        this.mParams = bundle;
    }

    @Override // com.unionpay.tsmservice.mi.request.RequestParams, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        super.writeToParcel(parcel, i2);
        parcel.writeBundle(this.mParams);
    }
}
