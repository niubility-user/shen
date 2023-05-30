package com.jingdong.common.shop;

import android.os.Parcel;
import android.os.Parcelable;
import com.jd.framework.json.JDJSONObject;

/* loaded from: classes6.dex */
public class CouponStair implements Parcelable {
    public static final Parcelable.Creator<CouponStair> CREATOR = new Parcelable.Creator<CouponStair>() { // from class: com.jingdong.common.shop.CouponStair.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CouponStair createFromParcel(Parcel parcel) {
            return new CouponStair(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CouponStair[] newArray(int i2) {
            return new CouponStair[i2];
        }
    };
    public double discount;
    public double quota;

    public CouponStair(JDJSONObject jDJSONObject) {
        this.discount = jDJSONObject.optDouble("discount");
        this.quota = jDJSONObject.optDouble("quota");
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeDouble(this.discount);
        parcel.writeDouble(this.quota);
    }

    protected CouponStair(Parcel parcel) {
        this.discount = parcel.readDouble();
        this.quota = parcel.readDouble();
    }
}
