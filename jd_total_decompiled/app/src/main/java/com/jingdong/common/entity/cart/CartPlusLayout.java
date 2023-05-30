package com.jingdong.common.entity.cart;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.constant.JshopConst;
import java.util.ArrayList;

/* loaded from: classes5.dex */
public class CartPlusLayout implements Parcelable {
    public static final Parcelable.Creator<CartPlusLayout> CREATOR = new Parcelable.Creator<CartPlusLayout>() { // from class: com.jingdong.common.entity.cart.CartPlusLayout.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CartPlusLayout createFromParcel(Parcel parcel) {
            return new CartPlusLayout(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CartPlusLayout[] newArray(int i2) {
            return new CartPlusLayout[i2];
        }
    };
    public String id;
    public String imgUrl;
    public String jdPrice;
    public String name;
    public String plusPrice;
    public CartPropertyTag propertyTags;
    public String savePrice;

    public CartPlusLayout(JDJSONObject jDJSONObject, String str) {
        if (jDJSONObject == null) {
            return;
        }
        this.id = jDJSONObject.optString("id");
        this.name = jDJSONObject.optString("name");
        setImgUrl(jDJSONObject.optString("imgUrl"), str);
        this.jdPrice = jDJSONObject.optString(JshopConst.JSKEY_PRODUCT_JDPRICE);
        this.plusPrice = jDJSONObject.optString(CartConstant.KEY_PLUSPRICE);
        this.savePrice = jDJSONObject.optString("savePrice");
        this.propertyTags = new CartPropertyTag(jDJSONObject.optJSONObject("propertyTags"));
    }

    public static ArrayList<CartPlusLayout> toList(JDJSONArray jDJSONArray, String str) {
        if (jDJSONArray == null) {
            return null;
        }
        int size = jDJSONArray.size();
        ArrayList<CartPlusLayout> arrayList = new ArrayList<>(size);
        for (int i2 = 0; i2 < size; i2++) {
            JDJSONObject optJSONObject = jDJSONArray.optJSONObject(i2);
            if (optJSONObject != null) {
                arrayList.add(new CartPlusLayout(optJSONObject, str));
            }
        }
        return arrayList;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public void setImgUrl(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            this.imgUrl = "";
        } else if (!str.startsWith("http://") && !str.startsWith("https://")) {
            if (str2 == null) {
                str2 = "";
            }
            this.imgUrl = str2 + str;
        } else {
            this.imgUrl = str;
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.id);
        parcel.writeString(this.name);
        parcel.writeString(this.imgUrl);
        parcel.writeString(this.jdPrice);
        parcel.writeString(this.plusPrice);
        parcel.writeString(this.savePrice);
        parcel.writeParcelable(this.propertyTags, i2);
    }

    protected CartPlusLayout(Parcel parcel) {
        this.id = parcel.readString();
        this.name = parcel.readString();
        this.imgUrl = parcel.readString();
        this.jdPrice = parcel.readString();
        this.plusPrice = parcel.readString();
        this.savePrice = parcel.readString();
        this.propertyTags = (CartPropertyTag) parcel.readParcelable(CartPropertyTag.class.getClassLoader());
    }
}
