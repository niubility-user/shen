package com.jd.lib.cashier.sdk.core.model;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes14.dex */
public class ExitRetainOptionEntity implements Parcelable {
    public static final Parcelable.Creator<ExitRetainOptionEntity> CREATOR = new Parcelable.Creator<ExitRetainOptionEntity>() { // from class: com.jd.lib.cashier.sdk.core.model.ExitRetainOptionEntity.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ExitRetainOptionEntity createFromParcel(Parcel parcel) {
            return new ExitRetainOptionEntity(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ExitRetainOptionEntity[] newArray(int i2) {
            return new ExitRetainOptionEntity[i2];
        }
    };
    public String btnEventParam;
    public String btnText;
    public boolean selected;

    public ExitRetainOptionEntity() {
        this.btnText = "";
        this.btnEventParam = "";
        this.selected = false;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.btnText);
        parcel.writeString(this.btnEventParam);
        parcel.writeByte(this.selected ? (byte) 1 : (byte) 0);
    }

    protected ExitRetainOptionEntity(Parcel parcel) {
        this.btnText = "";
        this.btnEventParam = "";
        this.selected = false;
        this.btnText = parcel.readString();
        this.btnEventParam = parcel.readString();
        this.selected = parcel.readByte() != 0;
    }
}
