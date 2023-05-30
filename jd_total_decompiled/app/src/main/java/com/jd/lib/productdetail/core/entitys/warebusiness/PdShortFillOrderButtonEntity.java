package com.jd.lib.productdetail.core.entitys.warebusiness;

import android.os.Parcel;
import android.os.Parcelable;
import java.io.Serializable;

/* loaded from: classes15.dex */
public class PdShortFillOrderButtonEntity implements Serializable, Parcelable {
    public static final Parcelable.Creator<PdShortFillOrderButtonEntity> CREATOR = new Parcelable.Creator<PdShortFillOrderButtonEntity>() { // from class: com.jd.lib.productdetail.core.entitys.warebusiness.PdShortFillOrderButtonEntity.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PdShortFillOrderButtonEntity createFromParcel(Parcel parcel) {
            return new PdShortFillOrderButtonEntity(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PdShortFillOrderButtonEntity[] newArray(int i2) {
            return new PdShortFillOrderButtonEntity[i2];
        }
    };
    public String bgColors;
    public String buttonName;
    public int buttonTag;
    public String tapCloseView;

    public PdShortFillOrderButtonEntity() {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.buttonName);
        parcel.writeString(this.bgColors);
        parcel.writeInt(this.buttonTag);
        parcel.writeString(this.tapCloseView);
    }

    protected PdShortFillOrderButtonEntity(Parcel parcel) {
        this.buttonName = parcel.readString();
        this.bgColors = parcel.readString();
        this.buttonTag = parcel.readInt();
        this.tapCloseView = parcel.readString();
    }
}
