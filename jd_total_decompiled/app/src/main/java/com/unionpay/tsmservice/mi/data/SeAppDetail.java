package com.unionpay.tsmservice.mi.data;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes11.dex */
public class SeAppDetail implements Parcelable {
    public static final Parcelable.Creator<SeAppDetail> CREATOR = new Parcelable.Creator<SeAppDetail>() { // from class: com.unionpay.tsmservice.mi.data.SeAppDetail.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final SeAppDetail createFromParcel(Parcel parcel) {
            return new SeAppDetail(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final SeAppDetail[] newArray(int i2) {
            return new SeAppDetail[i2];
        }
    };
    private Bundle mDetail;

    public SeAppDetail() {
    }

    public SeAppDetail(Parcel parcel) {
        this.mDetail = parcel.readBundle();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public Bundle getDetail() {
        return this.mDetail;
    }

    public void setDetail(Bundle bundle) {
        this.mDetail = bundle;
    }

    public String toString() {
        if (this.mDetail == null) {
            return "AppDetail [mDetail=null]";
        }
        String str = "AppDetail [mDetail=Bundle(";
        for (String str2 : this.mDetail.keySet()) {
            str = str + str2 + ":" + this.mDetail.get(str2) + ";";
        }
        return str + ")]";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeBundle(this.mDetail);
    }
}
