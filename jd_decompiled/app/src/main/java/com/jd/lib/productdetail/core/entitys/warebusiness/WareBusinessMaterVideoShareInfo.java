package com.jd.lib.productdetail.core.entitys.warebusiness;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes15.dex */
public class WareBusinessMaterVideoShareInfo implements Parcelable {
    public static final Parcelable.Creator<WareBusinessMaterVideoShareInfo> CREATOR = new Parcelable.Creator<WareBusinessMaterVideoShareInfo>() { // from class: com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessMaterVideoShareInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WareBusinessMaterVideoShareInfo createFromParcel(Parcel parcel) {
            return new WareBusinessMaterVideoShareInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WareBusinessMaterVideoShareInfo[] newArray(int i2) {
            return new WareBusinessMaterVideoShareInfo[i2];
        }
    };
    public String des;
    public String microBlog;
    public String title;
    public String url;

    public WareBusinessMaterVideoShareInfo() {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.url);
        parcel.writeString(this.title);
        parcel.writeString(this.des);
        parcel.writeString(this.microBlog);
    }

    protected WareBusinessMaterVideoShareInfo(Parcel parcel) {
        this.url = parcel.readString();
        this.title = parcel.readString();
        this.des = parcel.readString();
        this.microBlog = parcel.readString();
    }
}
