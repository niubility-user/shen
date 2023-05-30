package com.jingdong.common.sample.jshopmember.entity;

import android.os.Parcel;
import android.os.Parcelable;
import com.jd.framework.json.JDJSONObject;

/* loaded from: classes6.dex */
public class Privilege2Customer implements Parcelable {
    public static final Parcelable.Creator<Privilege2Customer> CREATOR = new Parcelable.Creator<Privilege2Customer>() { // from class: com.jingdong.common.sample.jshopmember.entity.Privilege2Customer.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Privilege2Customer createFromParcel(Parcel parcel) {
            return new Privilege2Customer(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Privilege2Customer[] newArray(int i2) {
            return new Privilege2Customer[i2];
        }
    };
    public String privilegeName;
    public int privilegeType;

    public Privilege2Customer(JDJSONObject jDJSONObject) {
        this.privilegeName = jDJSONObject.optString("privilegeName");
        this.privilegeType = jDJSONObject.optInt("privilegeType");
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.privilegeName);
        parcel.writeInt(this.privilegeType);
    }

    protected Privilege2Customer(Parcel parcel) {
        this.privilegeName = parcel.readString();
        this.privilegeType = parcel.readInt();
    }
}
