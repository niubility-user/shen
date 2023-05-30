package com.jingdong.common.entity;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class ExpandAttrEntity implements Parcelable {
    public static final Parcelable.Creator<ExpandAttrEntity> CREATOR = new Parcelable.Creator<ExpandAttrEntity>() { // from class: com.jingdong.common.entity.ExpandAttrEntity.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ExpandAttrEntity createFromParcel(Parcel parcel) {
            return new ExpandAttrEntity(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ExpandAttrEntity[] newArray(int i2) {
            return new ExpandAttrEntity[i2];
        }
    };
    public HashMap<String, String> valueMap;

    public ExpandAttrEntity() {
        this.valueMap = new HashMap<>();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeSerializable(this.valueMap);
    }

    protected ExpandAttrEntity(Parcel parcel) {
        this.valueMap = new HashMap<>();
        this.valueMap = (HashMap) parcel.readSerializable();
    }
}
