package com.jd.lib.productdetail.core.entitys.giftshopping;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;

/* loaded from: classes15.dex */
public class PdRuleItemEntity implements Parcelable {
    public static final Parcelable.Creator<PdRuleItemEntity> CREATOR = new Parcelable.Creator<PdRuleItemEntity>() { // from class: com.jd.lib.productdetail.core.entitys.giftshopping.PdRuleItemEntity.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PdRuleItemEntity createFromParcel(Parcel parcel) {
            return new PdRuleItemEntity(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PdRuleItemEntity[] newArray(int i2) {
            return new PdRuleItemEntity[i2];
        }
    };
    public ArrayList<PdActiveRuleContent> content;
    public String titleImg;

    public PdRuleItemEntity() {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public void readToParcel(Parcel parcel) {
        this.titleImg = parcel.readString();
        this.content = parcel.createTypedArrayList(PdActiveRuleContent.CREATOR);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.titleImg);
        parcel.writeTypedList(this.content);
    }

    protected PdRuleItemEntity(Parcel parcel) {
        readToParcel(parcel);
    }
}
