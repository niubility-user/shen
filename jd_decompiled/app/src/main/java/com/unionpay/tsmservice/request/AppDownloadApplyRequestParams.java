package com.unionpay.tsmservice.request;

import android.os.Parcel;
import android.os.Parcelable;
import com.unionpay.tsmservice.AppID;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes11.dex */
public class AppDownloadApplyRequestParams extends RequestParams {
    public static final Parcelable.Creator<AppDownloadApplyRequestParams> CREATOR = new Parcelable.Creator<AppDownloadApplyRequestParams>() { // from class: com.unionpay.tsmservice.request.AppDownloadApplyRequestParams.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final AppDownloadApplyRequestParams createFromParcel(Parcel parcel) {
            return new AppDownloadApplyRequestParams(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final AppDownloadApplyRequestParams[] newArray(int i2) {
            return new AppDownloadApplyRequestParams[i2];
        }
    };
    private AppID mAppID;
    private HashMap<String, String> mParams;

    public AppDownloadApplyRequestParams() {
    }

    public AppDownloadApplyRequestParams(Parcel parcel) {
        super(parcel);
        this.mAppID = (AppID) parcel.readParcelable(AppID.class.getClassLoader());
        this.mParams = parcel.readHashMap(HashMap.class.getClassLoader());
    }

    public AppID getAppID() {
        return this.mAppID;
    }

    public Map<String, String> getParams() {
        return this.mParams;
    }

    public void setAppID(AppID appID) {
        this.mAppID = appID;
    }

    public void setParams(HashMap<String, String> hashMap) {
        this.mParams = hashMap;
    }

    @Override // com.unionpay.tsmservice.request.RequestParams, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        super.writeToParcel(parcel, i2);
        parcel.writeParcelable(this.mAppID, i2);
        parcel.writeMap(this.mParams);
    }
}
