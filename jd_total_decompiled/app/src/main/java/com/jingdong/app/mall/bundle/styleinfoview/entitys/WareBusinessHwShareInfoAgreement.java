package com.jingdong.app.mall.bundle.styleinfoview.entitys;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes3.dex */
public class WareBusinessHwShareInfoAgreement implements Parcelable {
    public static final Parcelable.Creator<WareBusinessHwShareInfoAgreement> CREATOR = new Parcelable.Creator<WareBusinessHwShareInfoAgreement>() { // from class: com.jingdong.app.mall.bundle.styleinfoview.entitys.WareBusinessHwShareInfoAgreement.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WareBusinessHwShareInfoAgreement createFromParcel(Parcel parcel) {
            return new WareBusinessHwShareInfoAgreement(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WareBusinessHwShareInfoAgreement[] newArray(int i2) {
            return new WareBusinessHwShareInfoAgreement[i2];
        }
    };
    public String content;
    public String title;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.title);
        parcel.writeString(this.content);
    }

    public WareBusinessHwShareInfoAgreement() {
    }

    private WareBusinessHwShareInfoAgreement(Parcel parcel) {
        this.title = parcel.readString();
        this.content = parcel.readString();
    }
}
