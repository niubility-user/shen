package com.jd.lib.productdetail.tradein.stores;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

/* loaded from: classes16.dex */
public class TradeInSelectStoreResp {
    public String code;
    public Data data;

    /* loaded from: classes16.dex */
    public static final class Data {
        public int provinceId;
        public String provinceName;
        public List<StoreTagItem> storeTags;

        /* loaded from: classes16.dex */
        public static final class StoreTagItem {
            public int selected;
            public List<StoreInfoItem> storeInfos;
            public int tagId;
            public String tagName;

            /* loaded from: classes16.dex */
            public static final class StoreInfoItem implements Parcelable {
                public static final Parcelable.Creator<StoreInfoItem> CREATOR = new Parcelable.Creator<StoreInfoItem>() { // from class: com.jd.lib.productdetail.tradein.stores.TradeInSelectStoreResp.Data.StoreTagItem.StoreInfoItem.1
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // android.os.Parcelable.Creator
                    public StoreInfoItem createFromParcel(Parcel parcel) {
                        return new StoreInfoItem(parcel);
                    }

                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // android.os.Parcelable.Creator
                    public StoreInfoItem[] newArray(int i2) {
                        return new StoreInfoItem[i2];
                    }
                };
                public String distance;
                public String openTime;
                public int selected;
                public String storeAddress;
                public int storeId;
                public String storeName;

                public StoreInfoItem() {
                }

                @Override // android.os.Parcelable
                public int describeContents() {
                    return 0;
                }

                public void readFromParcel(Parcel parcel) {
                    this.distance = parcel.readString();
                    this.openTime = parcel.readString();
                    this.selected = parcel.readInt();
                    this.storeAddress = parcel.readString();
                    this.storeId = parcel.readInt();
                    this.storeName = parcel.readString();
                }

                @Override // android.os.Parcelable
                public void writeToParcel(Parcel parcel, int i2) {
                    parcel.writeString(this.distance);
                    parcel.writeString(this.openTime);
                    parcel.writeInt(this.selected);
                    parcel.writeString(this.storeAddress);
                    parcel.writeInt(this.storeId);
                    parcel.writeString(this.storeName);
                }

                public StoreInfoItem(Parcel parcel) {
                    this.distance = parcel.readString();
                    this.openTime = parcel.readString();
                    this.selected = parcel.readInt();
                    this.storeAddress = parcel.readString();
                    this.storeId = parcel.readInt();
                    this.storeName = parcel.readString();
                }
            }
        }
    }
}
