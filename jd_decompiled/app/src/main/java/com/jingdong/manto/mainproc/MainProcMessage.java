package com.jingdong.manto.mainproc;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes15.dex */
public class MainProcMessage implements Parcelable {
    public static final Parcelable.Creator<MainProcMessage> CREATOR = new a();
    public String appId;
    public Bundle data;
    public String messageName;

    /* loaded from: classes15.dex */
    class a implements Parcelable.Creator<MainProcMessage> {
        a() {
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public MainProcMessage createFromParcel(Parcel parcel) {
            return new MainProcMessage(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public MainProcMessage[] newArray(int i2) {
            return new MainProcMessage[i2];
        }
    }

    public MainProcMessage() {
    }

    protected MainProcMessage(Parcel parcel) {
        this.messageName = parcel.readString();
        this.data = parcel.readBundle();
        this.appId = parcel.readString();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.messageName);
        parcel.writeBundle(this.data);
        parcel.writeString(this.appId);
    }
}
