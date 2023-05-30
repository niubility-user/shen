package com.jingdong.common.entity;

import android.os.Parcel;
import android.os.Parcelable;
import java.io.Serializable;

/* loaded from: classes5.dex */
public class ProductToJsNowBuyEntity implements Serializable, Parcelable {
    public static final Parcelable.Creator<ProductToJsNowBuyEntity> CREATOR = new Parcelable.Creator<ProductToJsNowBuyEntity>() { // from class: com.jingdong.common.entity.ProductToJsNowBuyEntity.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ProductToJsNowBuyEntity createFromParcel(Parcel parcel) {
            return new ProductToJsNowBuyEntity(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ProductToJsNowBuyEntity[] newArray(int i2) {
            return new ProductToJsNowBuyEntity[i2];
        }
    };
    private static final long serialVersionUID = 6063409384802725660L;
    public String necessaryKey;
    public String showMsg;
    public int type;

    public ProductToJsNowBuyEntity() {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeInt(this.type);
        parcel.writeString(this.necessaryKey);
        parcel.writeString(this.showMsg);
    }

    protected ProductToJsNowBuyEntity(Parcel parcel) {
        this.type = parcel.readInt();
        this.necessaryKey = parcel.readString();
        this.showMsg = parcel.readString();
    }
}
