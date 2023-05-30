package com.jingdong.common.entity.cart;

import android.os.Parcel;
import android.os.Parcelable;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.jdsdk.constant.CartConstant;
import java.util.ArrayList;

/* loaded from: classes5.dex */
public class GiftPool implements Parcelable {
    public static final Parcelable.Creator<GiftPool> CREATOR = new Parcelable.Creator<GiftPool>() { // from class: com.jingdong.common.entity.cart.GiftPool.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public GiftPool createFromParcel(Parcel parcel) {
            return new GiftPool(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public GiftPool[] newArray(int i2) {
            return new GiftPool[i2];
        }
    };
    private ArrayList<CartResponseSku> gifts;
    private String id;
    private String name;

    public GiftPool() {
    }

    public static ArrayList<GiftPool> toList(JDJSONArray jDJSONArray, String str) {
        if (jDJSONArray == null) {
            return null;
        }
        int size = jDJSONArray.size();
        ArrayList<GiftPool> arrayList = new ArrayList<>(size);
        for (int i2 = 0; i2 < size; i2++) {
            JDJSONObject optJSONObject = jDJSONArray.optJSONObject(i2);
            GiftPool giftPool = new GiftPool();
            giftPool.setId(optJSONObject.optString("id"));
            giftPool.setName(optJSONObject.optString("name"));
            giftPool.setGifts(CartResponseGift.toList4Gift(optJSONObject.optJSONArray(CartConstant.KEY_GLOBAL_GIFTS), str));
            arrayList.add(giftPool);
        }
        return arrayList;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public ArrayList<CartResponseSku> getGifts() {
        return this.gifts;
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void setGifts(ArrayList<CartResponseSku> arrayList) {
        this.gifts = arrayList;
    }

    public void setId(String str) {
        this.id = str;
    }

    public void setName(String str) {
        this.name = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.id);
        parcel.writeString(this.name);
        parcel.writeTypedList(this.gifts);
    }

    protected GiftPool(Parcel parcel) {
        this.id = parcel.readString();
        this.name = parcel.readString();
        this.gifts = parcel.createTypedArrayList(CartResponseSku.CREATOR);
    }
}
