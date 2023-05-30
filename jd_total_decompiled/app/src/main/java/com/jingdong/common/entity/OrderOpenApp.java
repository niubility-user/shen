package com.jingdong.common.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

/* loaded from: classes5.dex */
public class OrderOpenApp implements Parcelable {
    public static final Parcelable.Creator<OrderOpenApp> CREATOR = new Parcelable.Creator<OrderOpenApp>() { // from class: com.jingdong.common.entity.OrderOpenApp.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public OrderOpenApp createFromParcel(Parcel parcel) {
            return new OrderOpenApp(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public OrderOpenApp[] newArray(int i2) {
            return new OrderOpenApp[i2];
        }
    };
    public String cartParams;
    public String category;
    public String des;
    public String easyBuy;
    public String eventID;
    public String fromCart;
    public String is170;
    public String isYYS;
    public String mType;
    public String orderType;
    public String recommendGoodsWareIds;
    public String shopCartSelectedNum;
    public String statisParams;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean getIs170() {
        return !TextUtils.isEmpty(this.is170) && this.is170.equals("1");
    }

    public boolean getIsYYS() {
        return !TextUtils.isEmpty(this.isYYS) && this.isYYS.equals("1");
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.category);
        parcel.writeString(this.des);
        parcel.writeString(this.recommendGoodsWareIds);
        parcel.writeString(this.shopCartSelectedNum);
        parcel.writeString(this.orderType);
        parcel.writeString(this.is170);
        parcel.writeString(this.isYYS);
        parcel.writeString(this.easyBuy);
        parcel.writeString(this.fromCart);
        parcel.writeString(this.mType);
        parcel.writeString(this.eventID);
        parcel.writeString(this.cartParams);
        parcel.writeString(this.statisParams);
    }

    public OrderOpenApp() {
    }

    private OrderOpenApp(Parcel parcel) {
        this.category = parcel.readString();
        this.des = parcel.readString();
        this.recommendGoodsWareIds = parcel.readString();
        this.shopCartSelectedNum = parcel.readString();
        this.orderType = parcel.readString();
        this.is170 = parcel.readString();
        this.isYYS = parcel.readString();
        this.easyBuy = parcel.readString();
        this.fromCart = parcel.readString();
        this.mType = parcel.readString();
        this.eventID = parcel.readString();
        this.cartParams = parcel.readString();
        this.statisParams = parcel.readString();
    }
}
