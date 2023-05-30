package com.hihonor.push.framework.aidl;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes12.dex */
public class DataBuffer implements Parcelable {
    public static final Parcelable.Creator<DataBuffer> CREATOR = new Parcelable.Creator<DataBuffer>() { // from class: com.hihonor.push.framework.aidl.DataBuffer.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DataBuffer createFromParcel(Parcel parcel) {
            return new DataBuffer(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DataBuffer[] newArray(int i2) {
            return new DataBuffer[i2];
        }
    };
    public Bundle body;
    public String eventType;
    public Bundle header;

    public DataBuffer(Parcel parcel) {
        this.eventType = parcel.readString();
        this.header = parcel.readBundle(Bundle.class.getClassLoader());
        this.body = parcel.readBundle(Bundle.class.getClassLoader());
    }

    public DataBuffer(String str, Bundle bundle, Bundle bundle2) {
        this.eventType = str;
        this.header = bundle;
        this.body = bundle2;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public Bundle getBody() {
        return this.body;
    }

    public String getEventType() {
        return this.eventType;
    }

    public Bundle getHeader() {
        return this.header;
    }

    public void setBody(Bundle bundle) {
        this.body = bundle;
    }

    public void setEventType(String str) {
        this.eventType = str;
    }

    public void setHeader(Bundle bundle) {
        this.header = bundle;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.eventType);
        parcel.writeBundle(this.header);
        parcel.writeBundle(this.body);
    }
}
