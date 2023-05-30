package com.jd.lib.productdetail.core.entitys.warebusiness;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;

/* loaded from: classes15.dex */
public class GiftPool3C implements Parcelable {
    public static final Parcelable.Creator<GiftPool3C> CREATOR = new Parcelable.Creator<GiftPool3C>() { // from class: com.jd.lib.productdetail.core.entitys.warebusiness.GiftPool3C.1
        @Override // android.os.Parcelable.Creator
        public GiftPool3C createFromParcel(Parcel parcel) {
            return new GiftPool3C(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public GiftPool3C[] newArray(int i2) {
            return new GiftPool3C[i2];
        }
    };
    private ArrayList<Gift3C> gifts;
    private String id;
    private String name;

    public GiftPool3C() {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public ArrayList<Gift3C> getGifts() {
        return this.gifts;
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void setGifts(ArrayList<Gift3C> arrayList) {
        this.gifts = arrayList;
    }

    public void setId(String str) {
        this.id = str;
    }

    public void setName(String str) {
        this.name = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.id);
        parcel.writeString(this.name);
        parcel.writeTypedList(this.gifts);
    }

    protected GiftPool3C(Parcel parcel) {
        this.id = parcel.readString();
        this.name = parcel.readString();
        this.gifts = parcel.createTypedArrayList(Gift3C.CREATOR);
    }
}
