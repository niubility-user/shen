package com.unionpay.tsmservice.request;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes11.dex */
public class PreDownloadRequestParams extends RequestParams {
    public static final Parcelable.Creator<PreDownloadRequestParams> CREATOR = new Parcelable.Creator<PreDownloadRequestParams>() { // from class: com.unionpay.tsmservice.request.PreDownloadRequestParams.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final PreDownloadRequestParams createFromParcel(Parcel parcel) {
            return new PreDownloadRequestParams(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final PreDownloadRequestParams[] newArray(int i2) {
            return new PreDownloadRequestParams[i2];
        }
    };
    private Bundle mParams;

    public PreDownloadRequestParams() {
    }

    public PreDownloadRequestParams(Parcel parcel) {
        super(parcel);
        this.mParams = parcel.readBundle();
    }

    public Bundle getParams() {
        return this.mParams;
    }

    public void setParams(Bundle bundle) {
        this.mParams = bundle;
    }

    @Override // com.unionpay.tsmservice.request.RequestParams, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        super.writeToParcel(parcel, i2);
        parcel.writeBundle(this.mParams);
    }
}
