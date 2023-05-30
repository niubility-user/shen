package com.unionpay.tsmservice.request;

import android.os.Parcel;
import android.os.Parcelable;
import com.unionpay.tsmservice.AppID;

/* loaded from: classes11.dex */
public class AppDataUpdateRequestParams extends RequestParams {
    public static final Parcelable.Creator<AppDataUpdateRequestParams> CREATOR = new Parcelable.Creator<AppDataUpdateRequestParams>() { // from class: com.unionpay.tsmservice.request.AppDataUpdateRequestParams.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final AppDataUpdateRequestParams createFromParcel(Parcel parcel) {
            return new AppDataUpdateRequestParams(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final AppDataUpdateRequestParams[] newArray(int i2) {
            return new AppDataUpdateRequestParams[i2];
        }
    };
    private AppID mAppID;

    public AppDataUpdateRequestParams() {
    }

    public AppDataUpdateRequestParams(Parcel parcel) {
        super(parcel);
        this.mAppID = (AppID) parcel.readParcelable(AppID.class.getClassLoader());
    }

    public AppID getAppID() {
        return this.mAppID;
    }

    public void setAppID(AppID appID) {
        this.mAppID = appID;
    }

    @Override // com.unionpay.tsmservice.request.RequestParams, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        super.writeToParcel(parcel, i2);
        parcel.writeParcelable(this.mAppID, i2);
    }
}
