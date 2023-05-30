package com.jd.lib.productdetail.core.entitys.warebusiness;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes15.dex */
public class WareBusinessMasterVideoExtInfo implements Parcelable {
    public static final Parcelable.Creator<WareBusinessMasterVideoExtInfo> CREATOR = new Parcelable.Creator<WareBusinessMasterVideoExtInfo>() { // from class: com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessMasterVideoExtInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WareBusinessMasterVideoExtInfo createFromParcel(Parcel parcel) {
            return new WareBusinessMasterVideoExtInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WareBusinessMasterVideoExtInfo[] newArray(int i2) {
            return new WareBusinessMasterVideoExtInfo[i2];
        }
    };
    public int trailerDuration;
    public String trailerImgUrl;
    public String trailerPlayUrl;

    public WareBusinessMasterVideoExtInfo() {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeInt(this.trailerDuration);
        parcel.writeString(this.trailerImgUrl);
        parcel.writeString(this.trailerPlayUrl);
    }

    protected WareBusinessMasterVideoExtInfo(Parcel parcel) {
        this.trailerDuration = parcel.readInt();
        this.trailerImgUrl = parcel.readString();
        this.trailerPlayUrl = parcel.readString();
    }
}
