package com.jd.lib.productdetail.tradein.ways;

import android.os.Parcel;
import android.os.Parcelable;
import com.jd.lib.productdetail.tradein.ways.TradeInTradeWaysResp;

/* loaded from: classes16.dex */
public class TradeInSelectWishTimeOrStoreRet implements Parcelable {
    public static final Parcelable.Creator<TradeInSelectWishTimeOrStoreRet> CREATOR = new Parcelable.Creator<TradeInSelectWishTimeOrStoreRet>() { // from class: com.jd.lib.productdetail.tradein.ways.TradeInSelectWishTimeOrStoreRet.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public TradeInSelectWishTimeOrStoreRet createFromParcel(Parcel parcel) {
            return new TradeInSelectWishTimeOrStoreRet(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public TradeInSelectWishTimeOrStoreRet[] newArray(int i2) {
            return new TradeInSelectWishTimeOrStoreRet[i2];
        }
    };
    public TradeInTradeWaysResp.Data.TradeModeItem.DefaultPromiseDate date;
    public TradeInTradeWaysResp.Data.TradeModeItem.StoreInfo store;
    public int tradeModeId;

    public TradeInSelectWishTimeOrStoreRet() {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public void readFromParcel(Parcel parcel) {
        this.date = (TradeInTradeWaysResp.Data.TradeModeItem.DefaultPromiseDate) parcel.readParcelable(TradeInTradeWaysResp.Data.TradeModeItem.DefaultPromiseDate.class.getClassLoader());
        this.store = (TradeInTradeWaysResp.Data.TradeModeItem.StoreInfo) parcel.readParcelable(TradeInTradeWaysResp.Data.TradeModeItem.StoreInfo.class.getClassLoader());
        this.tradeModeId = parcel.readInt();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeParcelable(this.date, i2);
        parcel.writeParcelable(this.store, i2);
        parcel.writeInt(this.tradeModeId);
    }

    public TradeInSelectWishTimeOrStoreRet(Parcel parcel) {
        this.date = (TradeInTradeWaysResp.Data.TradeModeItem.DefaultPromiseDate) parcel.readParcelable(TradeInTradeWaysResp.Data.TradeModeItem.DefaultPromiseDate.class.getClassLoader());
        this.store = (TradeInTradeWaysResp.Data.TradeModeItem.StoreInfo) parcel.readParcelable(TradeInTradeWaysResp.Data.TradeModeItem.StoreInfo.class.getClassLoader());
        this.tradeModeId = parcel.readInt();
    }
}
