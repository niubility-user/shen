package com.jingdong.common.entity.cart.subcart;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes5.dex */
public class SubCartNumParam implements Parcelable {
    public static final Parcelable.Creator<SubCartNumParam> CREATOR = new Parcelable.Creator<SubCartNumParam>() { // from class: com.jingdong.common.entity.cart.subcart.SubCartNumParam.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SubCartNumParam createFromParcel(Parcel parcel) {
            return new SubCartNumParam(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SubCartNumParam[] newArray(int i2) {
            return new SubCartNumParam[i2];
        }
    };
    public int ecardNum;
    public int homeWishListNum;
    public int sevenFreshNum;

    public SubCartNumParam() {
        this.sevenFreshNum = -1;
        this.ecardNum = -1;
        this.homeWishListNum = -1;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeInt(this.sevenFreshNum);
        parcel.writeInt(this.ecardNum);
        parcel.writeInt(this.homeWishListNum);
    }

    protected SubCartNumParam(Parcel parcel) {
        this.sevenFreshNum = -1;
        this.ecardNum = -1;
        this.homeWishListNum = -1;
        this.sevenFreshNum = parcel.readInt();
        this.ecardNum = parcel.readInt();
        this.homeWishListNum = parcel.readInt();
    }
}
