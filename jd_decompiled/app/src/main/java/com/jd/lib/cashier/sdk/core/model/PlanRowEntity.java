package com.jd.lib.cashier.sdk.core.model;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes14.dex */
public class PlanRowEntity implements Parcelable {
    public static final Parcelable.Creator<PlanRowEntity> CREATOR = new Parcelable.Creator<PlanRowEntity>() { // from class: com.jd.lib.cashier.sdk.core.model.PlanRowEntity.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PlanRowEntity createFromParcel(Parcel parcel) {
            return new PlanRowEntity(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PlanRowEntity[] newArray(int i2) {
            return new PlanRowEntity[i2];
        }
    };
    public String column0;
    public String column1;
    public String column2;
    public TYPE type;

    /* loaded from: classes14.dex */
    public enum TYPE {
        HEADER,
        CELL,
        TAIL
    }

    public PlanRowEntity() {
        this.column0 = "";
        this.column1 = "";
        this.column2 = "";
        this.type = TYPE.CELL;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.column0);
        parcel.writeString(this.column1);
        parcel.writeString(this.column2);
    }

    protected PlanRowEntity(Parcel parcel) {
        this.column0 = "";
        this.column1 = "";
        this.column2 = "";
        this.type = TYPE.CELL;
        this.column0 = parcel.readString();
        this.column1 = parcel.readString();
        this.column2 = parcel.readString();
    }
}
