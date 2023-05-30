package com.unionpay.tsmservice.request;

import android.os.Parcel;
import android.os.Parcelable;
import com.unionpay.tsmservice.AppID;

/* loaded from: classes11.dex */
public class AppDownloadRequestParams extends RequestParams {
    public static final Parcelable.Creator<AppDownloadRequestParams> CREATOR = new Parcelable.Creator<AppDownloadRequestParams>() { // from class: com.unionpay.tsmservice.request.AppDownloadRequestParams.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final AppDownloadRequestParams createFromParcel(Parcel parcel) {
            return new AppDownloadRequestParams(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final AppDownloadRequestParams[] newArray(int i2) {
            return new AppDownloadRequestParams[i2];
        }
    };
    private AppID mAppID;
    private String mAppName;

    public AppDownloadRequestParams() {
    }

    public AppDownloadRequestParams(Parcel parcel) {
        super(parcel);
        this.mAppID = (AppID) parcel.readParcelable(AppID.class.getClassLoader());
        this.mAppName = parcel.readString();
    }

    public AppID getAppID() {
        return this.mAppID;
    }

    public String getAppName() {
        return this.mAppName;
    }

    public void setAppID(AppID appID) {
        this.mAppID = appID;
    }

    public void setAppName(String str) {
        this.mAppName = str;
    }

    @Override // com.unionpay.tsmservice.request.RequestParams, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        super.writeToParcel(parcel, i2);
        parcel.writeParcelable(this.mAppID, i2);
        parcel.writeString(this.mAppName);
    }
}
