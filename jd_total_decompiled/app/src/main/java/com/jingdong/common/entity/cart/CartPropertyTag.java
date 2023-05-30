package com.jingdong.common.entity.cart;

import android.os.Parcel;
import android.os.Parcelable;
import com.jd.framework.json.JDJSONObject;

/* loaded from: classes5.dex */
public class CartPropertyTag implements Parcelable {
    public static final Parcelable.Creator<CartPropertyTag> CREATOR = new Parcelable.Creator<CartPropertyTag>() { // from class: com.jingdong.common.entity.cart.CartPropertyTag.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CartPropertyTag createFromParcel(Parcel parcel) {
            return new CartPropertyTag(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CartPropertyTag[] newArray(int i2) {
            return new CartPropertyTag[i2];
        }
    };

    /* renamed from: c  reason: collision with root package name */
    public String f12331c;

    public CartPropertyTag(JDJSONObject jDJSONObject) {
        if (jDJSONObject == null) {
            return;
        }
        this.f12331c = jDJSONObject.optString("c");
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.f12331c);
    }

    protected CartPropertyTag(Parcel parcel) {
        this.f12331c = parcel.readString();
    }
}
