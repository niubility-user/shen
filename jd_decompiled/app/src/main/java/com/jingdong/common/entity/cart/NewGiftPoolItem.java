package com.jingdong.common.entity.cart;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;

/* loaded from: classes5.dex */
public class NewGiftPoolItem implements Parcelable {
    public static final Parcelable.Creator<NewGiftPoolItem> CREATOR = new Parcelable.Creator<NewGiftPoolItem>() { // from class: com.jingdong.common.entity.cart.NewGiftPoolItem.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public NewGiftPoolItem createFromParcel(Parcel parcel) {
            return new NewGiftPoolItem(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public NewGiftPoolItem[] newArray(int i2) {
            return new NewGiftPoolItem[i2];
        }
    };
    public ArrayList<NewGiftItem> gifts;
    public String id;
    public String name;

    public NewGiftPoolItem() {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public ArrayList<NewGiftItem> getGifts() {
        return this.gifts;
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void setGifts(ArrayList<NewGiftItem> arrayList) {
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

    protected NewGiftPoolItem(Parcel parcel) {
        this.id = parcel.readString();
        this.name = parcel.readString();
        this.gifts = parcel.createTypedArrayList(NewGiftItem.CREATOR);
    }
}
