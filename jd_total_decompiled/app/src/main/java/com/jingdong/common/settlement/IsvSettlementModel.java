package com.jingdong.common.settlement;

import android.os.Parcel;
import android.os.Parcelable;
import java.io.Serializable;

/* loaded from: classes6.dex */
public class IsvSettlementModel implements Serializable, Parcelable {
    public static final Parcelable.Creator<IsvSettlementModel> CREATOR = new Parcelable.Creator<IsvSettlementModel>() { // from class: com.jingdong.common.settlement.IsvSettlementModel.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public IsvSettlementModel createFromParcel(Parcel parcel) {
            return new IsvSettlementModel(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public IsvSettlementModel[] newArray(int i2) {
            return new IsvSettlementModel[i2];
        }
    };
    public String flag;
    public String showMoreId;

    public IsvSettlementModel() {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.flag);
        parcel.writeString(this.showMoreId);
    }

    protected IsvSettlementModel(Parcel parcel) {
        this.flag = parcel.readString();
        this.showMoreId = parcel.readString();
    }
}
