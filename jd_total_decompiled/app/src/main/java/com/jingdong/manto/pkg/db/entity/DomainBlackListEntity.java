package com.jingdong.manto.pkg.db.entity;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes16.dex */
public class DomainBlackListEntity implements Parcelable {
    public static final Parcelable.Creator<DomainBlackListEntity> CREATOR = new Parcelable.Creator<DomainBlackListEntity>() { // from class: com.jingdong.manto.pkg.db.entity.DomainBlackListEntity.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DomainBlackListEntity createFromParcel(Parcel parcel) {
            return new DomainBlackListEntity(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DomainBlackListEntity[] newArray(int i2) {
            return new DomainBlackListEntity[i2];
        }
    };
    public String blackDomainList;
    public String key;

    public DomainBlackListEntity() {
        this.key = "1";
    }

    protected DomainBlackListEntity(Parcel parcel) {
        this.key = "1";
        this.key = parcel.readString();
        this.blackDomainList = parcel.readString();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public void readFromParcel(Parcel parcel) {
        this.key = parcel.readString();
        this.blackDomainList = parcel.readString();
    }

    public String toString() {
        return "DomainBlackListEntity{blackDomainList='" + this.blackDomainList + "'}";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.key);
        parcel.writeString(this.blackDomainList);
    }
}
