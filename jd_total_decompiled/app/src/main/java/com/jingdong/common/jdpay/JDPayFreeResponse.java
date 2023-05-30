package com.jingdong.common.jdpay;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes5.dex */
public class JDPayFreeResponse implements Parcelable {
    public static final Parcelable.Creator<JDPayFreeResponse> CREATOR = new Parcelable.Creator<JDPayFreeResponse>() { // from class: com.jingdong.common.jdpay.JDPayFreeResponse.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public JDPayFreeResponse createFromParcel(Parcel parcel) {
            return new JDPayFreeResponse(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public JDPayFreeResponse[] newArray(int i2) {
            return new JDPayFreeResponse[i2];
        }
    };
    private String result;
    private String type;

    public JDPayFreeResponse() {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getResult() {
        return this.result;
    }

    public String getType() {
        return this.type;
    }

    public void setResult(String str) {
        this.result = str;
    }

    public void setType(String str) {
        this.type = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.type);
        parcel.writeString(this.result);
    }

    protected JDPayFreeResponse(Parcel parcel) {
        this.type = parcel.readString();
        this.result = parcel.readString();
    }
}
