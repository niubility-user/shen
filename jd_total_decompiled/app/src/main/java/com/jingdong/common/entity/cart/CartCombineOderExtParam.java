package com.jingdong.common.entity.cart;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes5.dex */
public class CartCombineOderExtParam implements Parcelable {
    public static final Parcelable.Creator<CartCombineOderExtParam> CREATOR = new Parcelable.Creator<CartCombineOderExtParam>() { // from class: com.jingdong.common.entity.cart.CartCombineOderExtParam.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CartCombineOderExtParam createFromParcel(Parcel parcel) {
            return new CartCombineOderExtParam(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CartCombineOderExtParam[] newArray(int i2) {
            return new CartCombineOderExtParam[i2];
        }
    };
    public boolean isNewStyle;
    public boolean isSortOpen;
    public String returnText;
    public String user;
    public String venderId;
    public int venderType;

    public CartCombineOderExtParam() {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.user);
        parcel.writeString(this.returnText);
        parcel.writeInt(this.venderType);
        parcel.writeString(this.venderId);
        parcel.writeByte(this.isNewStyle ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.isSortOpen ? (byte) 1 : (byte) 0);
    }

    protected CartCombineOderExtParam(Parcel parcel) {
        this.user = parcel.readString();
        this.returnText = parcel.readString();
        this.venderType = parcel.readInt();
        this.venderId = parcel.readString();
        this.isNewStyle = parcel.readByte() != 0;
        this.isSortOpen = parcel.readByte() != 0;
    }
}
