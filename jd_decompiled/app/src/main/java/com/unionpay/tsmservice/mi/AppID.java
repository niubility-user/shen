package com.unionpay.tsmservice.mi;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes11.dex */
public class AppID implements Parcelable {
    public static final Parcelable.Creator<AppID> CREATOR = new Parcelable.Creator<AppID>() { // from class: com.unionpay.tsmservice.mi.AppID.1
        @Override // android.os.Parcelable.Creator
        public final /* synthetic */ AppID createFromParcel(Parcel parcel) {
            return new AppID(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public final /* bridge */ /* synthetic */ AppID[] newArray(int i2) {
            return new AppID[i2];
        }
    };
    String a;
    String b;

    public AppID(Parcel parcel) {
        this.a = "";
        this.b = "";
        this.a = parcel.readString();
        this.b = parcel.readString();
    }

    public AppID(String str, String str2) {
        this.a = "";
        this.b = "";
        this.a = str;
        this.b = str2;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getAppAid() {
        return this.a;
    }

    public String getAppVersion() {
        return this.b;
    }

    public void setAppAid(String str) {
        this.a = str;
    }

    public void setAppVersion(String str) {
        this.b = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.a);
        parcel.writeString(this.b);
    }
}
