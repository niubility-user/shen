package com.jingdong.common.jdpay;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes5.dex */
public class JDPayFreeRequest implements Parcelable {
    public static final Parcelable.Creator<JDPayFreeRequest> CREATOR = new Parcelable.Creator<JDPayFreeRequest>() { // from class: com.jingdong.common.jdpay.JDPayFreeRequest.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public JDPayFreeRequest createFromParcel(Parcel parcel) {
            return new JDPayFreeRequest(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public JDPayFreeRequest[] newArray(int i2) {
            return new JDPayFreeRequest[i2];
        }
    };
    private String data;
    private String type;

    protected JDPayFreeRequest(Parcel parcel) {
        this.type = parcel.readString();
        this.data = parcel.readString();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getData() {
        return this.data;
    }

    public String getType() {
        return this.type;
    }

    public void setData(String str) {
        this.data = str;
    }

    public void setType(String str) {
        this.type = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.type);
        parcel.writeString(this.data);
    }
}
