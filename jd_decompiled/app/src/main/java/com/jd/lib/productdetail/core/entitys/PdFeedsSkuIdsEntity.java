package com.jd.lib.productdetail.core.entitys;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;

/* loaded from: classes15.dex */
public class PdFeedsSkuIdsEntity {
    public int code;
    public ArrayList<PdSkuEntity> data;
    public int feedCode;
    public Others others;

    /* loaded from: classes15.dex */
    public static class Others {
        public String touchstone_expids;
    }

    /* loaded from: classes15.dex */
    public static class PdSkuEntity implements Parcelable {
        public static final Parcelable.Creator<PdSkuEntity> CREATOR = new Parcelable.Creator<PdSkuEntity>() { // from class: com.jd.lib.productdetail.core.entitys.PdFeedsSkuIdsEntity.PdSkuEntity.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public PdSkuEntity createFromParcel(Parcel parcel) {
                return new PdSkuEntity(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public PdSkuEntity[] newArray(int i2) {
                return new PdSkuEntity[i2];
            }
        };
        public String broker_info;
        public String main_sku;
        public String rec_broker;
        public String skuId;

        public PdSkuEntity() {
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            parcel.writeString(this.broker_info);
            parcel.writeString(this.main_sku);
            parcel.writeString(this.rec_broker);
            parcel.writeString(this.skuId);
        }

        protected PdSkuEntity(Parcel parcel) {
            this.broker_info = parcel.readString();
            this.main_sku = parcel.readString();
            this.rec_broker = parcel.readString();
            this.skuId = parcel.readString();
        }
    }
}
