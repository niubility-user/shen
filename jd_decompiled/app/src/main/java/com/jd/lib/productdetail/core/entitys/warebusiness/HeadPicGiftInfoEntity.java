package com.jd.lib.productdetail.core.entitys.warebusiness;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes15.dex */
public class HeadPicGiftInfoEntity implements Parcelable {
    public static final Parcelable.Creator<HeadPicGiftInfoEntity> CREATOR = new Parcelable.Creator<HeadPicGiftInfoEntity>() { // from class: com.jd.lib.productdetail.core.entitys.warebusiness.HeadPicGiftInfoEntity.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public HeadPicGiftInfoEntity createFromParcel(Parcel parcel) {
            return new HeadPicGiftInfoEntity(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public HeadPicGiftInfoEntity[] newArray(int i2) {
            return new HeadPicGiftInfoEntity[i2];
        }
    };
    public String backGiftImg;
    public String downText;
    public List<GiftLayerInfosEntity> giftLayerInfos;
    public ArrayList<GiftsEntity> giftPicShowList;
    public int index;
    public String titleOne;
    public String titleTwo;

    /* loaded from: classes15.dex */
    public static class GiftLayerInfosEntity {
        public String arrow;
        public String businessType;
        public String giftAllName;
        public List<GiftsEntity> gifts;
        public String jumpName;
        public String jumplink;
        public String skuType;
        public String title;
        public String titleColor;
        public String titleIcon;
    }

    /* loaded from: classes15.dex */
    public static class GiftsEntity implements Parcelable {
        public static final Parcelable.Creator<GiftsEntity> CREATOR = new Parcelable.Creator<GiftsEntity>() { // from class: com.jd.lib.productdetail.core.entitys.warebusiness.HeadPicGiftInfoEntity.GiftsEntity.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public GiftsEntity createFromParcel(Parcel parcel) {
                return new GiftsEntity(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public GiftsEntity[] newArray(int i2) {
                return new GiftsEntity[i2];
            }
        };
        public String desc;
        public String img;
        public String num;
        public String sku;

        public GiftsEntity() {
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            parcel.writeString(this.desc);
            parcel.writeString(this.img);
            parcel.writeString(this.num);
            parcel.writeString(this.sku);
        }

        protected GiftsEntity(Parcel parcel) {
            this.desc = parcel.readString();
            this.img = parcel.readString();
            this.num = parcel.readString();
            this.sku = parcel.readString();
        }
    }

    public HeadPicGiftInfoEntity() {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.downText);
        parcel.writeTypedList(this.giftPicShowList);
        parcel.writeString(this.titleOne);
        parcel.writeString(this.titleTwo);
        parcel.writeString(this.backGiftImg);
        parcel.writeInt(this.index);
    }

    protected HeadPicGiftInfoEntity(Parcel parcel) {
        this.downText = parcel.readString();
        this.giftPicShowList = parcel.createTypedArrayList(GiftsEntity.CREATOR);
        this.titleOne = parcel.readString();
        this.titleTwo = parcel.readString();
        this.backGiftImg = parcel.readString();
        this.index = parcel.readInt();
    }
}
