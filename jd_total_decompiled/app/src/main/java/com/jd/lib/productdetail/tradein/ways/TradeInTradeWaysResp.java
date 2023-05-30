package com.jd.lib.productdetail.tradein.ways;

import android.os.Parcel;
import android.os.Parcelable;
import java.io.Serializable;
import java.util.List;

/* loaded from: classes16.dex */
public class TradeInTradeWaysResp {
    public String code;
    public Data data;

    /* loaded from: classes16.dex */
    public static final class Data {
        public List<TradeModeItem> tradeModes;

        /* loaded from: classes16.dex */
        public static final class TradeModeItem {
            public DefaultPromiseDate defaultPromiseDate;
            public String desc;
            public int selected;
            public StoreInfo storeInfo;
            public int tradeModeId;
            public String tradeModeName;

            /* loaded from: classes16.dex */
            public static final class DefaultPromiseDate implements Parcelable, Serializable {
                public static final Parcelable.Creator<DefaultPromiseDate> CREATOR = new Parcelable.Creator<DefaultPromiseDate>() { // from class: com.jd.lib.productdetail.tradein.ways.TradeInTradeWaysResp.Data.TradeModeItem.DefaultPromiseDate.1
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // android.os.Parcelable.Creator
                    public DefaultPromiseDate createFromParcel(Parcel parcel) {
                        return new DefaultPromiseDate(parcel);
                    }

                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // android.os.Parcelable.Creator
                    public DefaultPromiseDate[] newArray(int i2) {
                        return new DefaultPromiseDate[i2];
                    }
                };
                public String date;
                public String dayOfWeek;
                public String displayText;
                public String endTime;
                public String startTime;

                public DefaultPromiseDate() {
                }

                @Override // android.os.Parcelable
                public int describeContents() {
                    return 0;
                }

                public void readFromParcel(Parcel parcel) {
                    this.date = parcel.readString();
                    this.dayOfWeek = parcel.readString();
                    this.endTime = parcel.readString();
                    this.startTime = parcel.readString();
                    this.displayText = parcel.readString();
                }

                @Override // android.os.Parcelable
                public void writeToParcel(Parcel parcel, int i2) {
                    parcel.writeString(this.date);
                    parcel.writeString(this.dayOfWeek);
                    parcel.writeString(this.endTime);
                    parcel.writeString(this.startTime);
                    parcel.writeString(this.displayText);
                }

                public DefaultPromiseDate(Parcel parcel) {
                    this.date = parcel.readString();
                    this.dayOfWeek = parcel.readString();
                    this.endTime = parcel.readString();
                    this.startTime = parcel.readString();
                    this.displayText = parcel.readString();
                }
            }

            /* loaded from: classes16.dex */
            public static final class StoreInfo implements Parcelable, Serializable {
                public static final Parcelable.Creator<StoreInfo> CREATOR = new Parcelable.Creator<StoreInfo>() { // from class: com.jd.lib.productdetail.tradein.ways.TradeInTradeWaysResp.Data.TradeModeItem.StoreInfo.1
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // android.os.Parcelable.Creator
                    public StoreInfo createFromParcel(Parcel parcel) {
                        return new StoreInfo(parcel);
                    }

                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // android.os.Parcelable.Creator
                    public StoreInfo[] newArray(int i2) {
                        return new StoreInfo[i2];
                    }
                };
                public String openTime;
                public String storeAddress;
                public int storeId;
                public String storeName;

                public StoreInfo() {
                }

                @Override // android.os.Parcelable
                public int describeContents() {
                    return 0;
                }

                public void readFromParcel(Parcel parcel) {
                    this.storeId = parcel.readInt();
                    this.storeName = parcel.readString();
                    this.openTime = parcel.readString();
                    this.storeAddress = parcel.readString();
                }

                @Override // android.os.Parcelable
                public void writeToParcel(Parcel parcel, int i2) {
                    parcel.writeInt(this.storeId);
                    parcel.writeString(this.storeName);
                    parcel.writeString(this.openTime);
                    parcel.writeString(this.storeAddress);
                }

                public StoreInfo(Parcel parcel) {
                    this.storeId = parcel.readInt();
                    this.storeName = parcel.readString();
                    this.openTime = parcel.readString();
                    this.storeAddress = parcel.readString();
                }
            }
        }
    }
}
