package com.jingdong.common.entity.cart;

import android.os.Parcel;
import android.os.Parcelable;
import com.jd.framework.json.JDJSONObject;
import java.util.ArrayList;

/* loaded from: classes5.dex */
public class CartExtFloor implements Parcelable {
    public static final Parcelable.Creator<CartExtFloor> CREATOR = new Parcelable.Creator<CartExtFloor>() { // from class: com.jingdong.common.entity.cart.CartExtFloor.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CartExtFloor createFromParcel(Parcel parcel) {
            return new CartExtFloor(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CartExtFloor[] newArray(int i2) {
            return new CartExtFloor[i2];
        }
    };
    public ArrayList<CartElement> floor;
    public String floorType;

    public CartExtFloor() {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.floorType);
        parcel.writeList(this.floor);
    }

    public CartExtFloor(JDJSONObject jDJSONObject) {
        if (jDJSONObject == null) {
            return;
        }
        this.floorType = jDJSONObject.optString("floorType");
        this.floor = CartElement.toList(jDJSONObject.optJSONArray("floor"));
    }

    protected CartExtFloor(Parcel parcel) {
        this.floorType = parcel.readString();
        ArrayList<CartElement> arrayList = new ArrayList<>();
        this.floor = arrayList;
        parcel.readList(arrayList, CartElement.class.getClassLoader());
    }
}
