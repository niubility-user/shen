package com.jingdong.common.search.entity;

import android.os.Parcel;
import android.os.Parcelable;
import com.jingdong.common.baseRecycleAdapter.entity.MultiItemEntity;

/* loaded from: classes6.dex */
public class CategoryTabItem implements Parcelable, MultiItemEntity {
    public static final Parcelable.Creator<CategoryTabItem> CREATOR = new Parcelable.Creator<CategoryTabItem>() { // from class: com.jingdong.common.search.entity.CategoryTabItem.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CategoryTabItem createFromParcel(Parcel parcel) {
            return new CategoryTabItem(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CategoryTabItem[] newArray(int i2) {
            return new CategoryTabItem[i2];
        }
    };
    public boolean isCheck;
    public boolean isExpo;
    public String keyword;
    public String landCid1;
    public String landCid2;
    public String landCid3;
    public String name;
    public String title;

    public CategoryTabItem() {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.jingdong.common.baseRecycleAdapter.entity.MultiItemEntity
    public int getItemType() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.landCid1);
        parcel.writeString(this.landCid2);
        parcel.writeString(this.landCid3);
        parcel.writeString(this.name);
        parcel.writeString(this.title);
        parcel.writeString(this.keyword);
        parcel.writeByte(this.isCheck ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.isExpo ? (byte) 1 : (byte) 0);
    }

    public CategoryTabItem(String str, String str2, String str3, String str4, String str5, String str6, boolean z) {
        this.landCid1 = str;
        this.landCid2 = str2;
        this.landCid3 = str3;
        this.name = str4;
        this.title = str5;
        this.keyword = str6;
        this.isCheck = z;
    }

    protected CategoryTabItem(Parcel parcel) {
        this.landCid1 = parcel.readString();
        this.landCid2 = parcel.readString();
        this.landCid3 = parcel.readString();
        this.name = parcel.readString();
        this.title = parcel.readString();
        this.keyword = parcel.readString();
        this.isCheck = parcel.readByte() != 0;
        this.isExpo = parcel.readByte() != 0;
    }
}
