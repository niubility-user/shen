package com.jingdong.common.entity.cart;

import android.os.Parcel;
import android.os.Parcelable;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.jdsdk.constant.CartConstant;
import java.util.ArrayList;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class CartElement implements Parcelable {
    public static final Parcelable.Creator<CartElement> CREATOR = new Parcelable.Creator<CartElement>() { // from class: com.jingdong.common.entity.cart.CartElement.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CartElement createFromParcel(Parcel parcel) {
            return new CartElement(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CartElement[] newArray(int i2) {
            return new CartElement[i2];
        }
    };
    public int actionType;
    public String appUrl;
    public String btnUnitNo;
    public int elemType;
    protected CartExtFlag extFlag;
    public String imgUrl;
    public int isBg;
    public String jumpUrl;
    public int length;
    public String text;
    public int width;

    public CartElement() {
    }

    public static ArrayList<CartElement> toList(JDJSONArray jDJSONArray) {
        if (jDJSONArray == null || jDJSONArray.size() == 0) {
            return null;
        }
        int size = jDJSONArray.size();
        ArrayList<CartElement> arrayList = new ArrayList<>(size);
        for (int i2 = 0; i2 < size; i2++) {
            JDJSONObject optJSONObject = jDJSONArray.optJSONObject(i2);
            if (optJSONObject != null) {
                CartElement cartElement = new CartElement();
                cartElement.elemType = optJSONObject.optInt(CartConstant.KEY_ELEMTYPE, -1);
                cartElement.text = optJSONObject.optString("text");
                cartElement.jumpUrl = optJSONObject.optString(CartConstant.KEY_JUMPURL);
                cartElement.appUrl = optJSONObject.optString(CartConstant.KEY_APPURL);
                cartElement.btnUnitNo = optJSONObject.optString(CartConstant.KEY_BTNUNITNO);
                cartElement.imgUrl = optJSONObject.optString("imgUrl");
                cartElement.width = optJSONObject.optInt("width");
                cartElement.length = optJSONObject.optInt(CartConstant.KEY_LENGTH);
                cartElement.actionType = optJSONObject.optInt("actionType");
                cartElement.isBg = optJSONObject.optInt(CartConstant.KEY_ISBG);
                JDJSONObject optJSONObject2 = optJSONObject.optJSONObject(CartConstant.KEY_EXTFLAG);
                if (optJSONObject2 != null) {
                    cartElement.extFlag = new CartExtFlag(optJSONObject2);
                }
                arrayList.add(cartElement);
            }
        }
        return arrayList;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public CartExtFlag getExtFlag() {
        if (this.extFlag == null) {
            this.extFlag = new CartExtFlag();
        }
        return this.extFlag;
    }

    public String getSinkUserAbtest() {
        HashMap<String, String> hashMap;
        CartExtFlag cartExtFlag = this.extFlag;
        if (cartExtFlag == null || (hashMap = cartExtFlag.extMap) == null) {
            return null;
        }
        return hashMap.get(CartConstant.KEY_SINKUSERABTEST);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeInt(this.elemType);
        parcel.writeString(this.text);
        parcel.writeString(this.jumpUrl);
        parcel.writeString(this.appUrl);
        parcel.writeString(this.btnUnitNo);
        parcel.writeString(this.imgUrl);
        parcel.writeInt(this.width);
        parcel.writeInt(this.length);
        parcel.writeInt(this.actionType);
        parcel.writeInt(this.isBg);
    }

    protected CartElement(Parcel parcel) {
        this.elemType = parcel.readInt();
        this.text = parcel.readString();
        this.jumpUrl = parcel.readString();
        this.appUrl = parcel.readString();
        this.btnUnitNo = parcel.readString();
        this.imgUrl = parcel.readString();
        this.width = parcel.readInt();
        this.length = parcel.readInt();
        this.actionType = parcel.readInt();
        this.isBg = parcel.readInt();
    }
}
