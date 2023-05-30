package com.jingdong.manto.message;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes15.dex */
public class MantoAcrossBundleData implements Parcelable {
    public static String BUNDLE_DATA_NAME = "bundle_data_name";
    public static final Parcelable.Creator<MantoAcrossBundleData> CREATOR = new a();
    private String appId;
    private Bundle bundle;

    /* loaded from: classes15.dex */
    class a implements Parcelable.Creator<MantoAcrossBundleData> {
        a() {
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public MantoAcrossBundleData createFromParcel(Parcel parcel) {
            return new MantoAcrossBundleData(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public MantoAcrossBundleData[] newArray(int i2) {
            return new MantoAcrossBundleData[i2];
        }
    }

    public MantoAcrossBundleData() {
        this.appId = "";
    }

    protected MantoAcrossBundleData(Parcel parcel) {
        this.appId = "";
        this.bundle = parcel.readBundle();
        this.appId = parcel.readString();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getAppId() {
        return this.appId;
    }

    public Bundle getBundle() {
        return this.bundle;
    }

    public MantoAcrossBundleData setAppId(String str) {
        this.appId = str;
        return this;
    }

    public MantoAcrossBundleData setBundle(Bundle bundle) {
        this.bundle = bundle;
        return this;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeBundle(this.bundle);
        parcel.writeString(this.appId);
    }
}
