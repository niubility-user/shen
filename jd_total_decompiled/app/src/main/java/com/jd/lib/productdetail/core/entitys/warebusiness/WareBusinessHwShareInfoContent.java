package com.jd.lib.productdetail.core.entitys.warebusiness;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes15.dex */
public class WareBusinessHwShareInfoContent implements Parcelable {
    public static final Parcelable.Creator<WareBusinessHwShareInfoContent> CREATOR = new Parcelable.Creator<WareBusinessHwShareInfoContent>() { // from class: com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessHwShareInfoContent.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WareBusinessHwShareInfoContent createFromParcel(Parcel parcel) {
            return new WareBusinessHwShareInfoContent(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WareBusinessHwShareInfoContent[] newArray(int i2) {
            return new WareBusinessHwShareInfoContent[i2];
        }
    };
    public String icon;
    public String text;
    public String title;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.title);
        parcel.writeString(this.icon);
        parcel.writeString(this.text);
    }

    public WareBusinessHwShareInfoContent() {
    }

    private WareBusinessHwShareInfoContent(Parcel parcel) {
        this.title = parcel.readString();
        this.icon = parcel.readString();
        this.text = parcel.readString();
    }
}
