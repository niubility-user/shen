package com.jingdong.common.entity.cart;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import java.util.ArrayList;

/* loaded from: classes5.dex */
public class CartPromotion implements Parcelable {
    public static final Parcelable.Creator<CartPromotion> CREATOR = new Parcelable.Creator<CartPromotion>() { // from class: com.jingdong.common.entity.cart.CartPromotion.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CartPromotion createFromParcel(Parcel parcel) {
            return new CartPromotion(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CartPromotion[] newArray(int i2) {
            return new CartPromotion[i2];
        }
    };
    public static final String KEY_CHECKTYPE = "checkType";
    public static final String KEY_DISCOUNT = "discount";
    public static final String KEY_ID = "id";
    public static final String KEY_JBEANPROMOPRICE = "jBeanPromoPrice";
    public static final String KEY_NEEDJBEANNUM = "needJBeanNum";
    public static final String KEY_OPTIMAL_NOTICE = "optimalNotice";
    public static final String KEY_PRICE = "price";
    public static final String KEY_PRICECOLOR = "priceColor";
    public static final String KEY_PRICEICON = "priceIcon";
    public static final String KEY_PRICEWITHOUTHELP = "priceWithoutHelp";
    public static final String KEY_TITLE = "title";
    public static final String KEY_TYPE = "type";
    public static final String KEY_UNUSABLE = "unusable";
    public static final int TYPE_JBEAN = 0;
    public int checkType;
    public String discount;
    public long id;
    public String jBeanPromoPrice;
    public int needJBeanNum;
    public String optimalNotice;
    public String price;
    public String priceColor;
    public String priceIcon;
    public String priceWithoutHelp;
    public String title;
    public int type;
    public Boolean unusable;

    private CartPromotion(JDJSONObject jDJSONObject) {
        if (jDJSONObject == null) {
            return;
        }
        this.id = jDJSONObject.optLong("id");
        this.title = jDJSONObject.optString("title");
        this.type = jDJSONObject.optInt("type");
        this.checkType = jDJSONObject.optInt("checkType");
        this.price = jDJSONObject.optString("price");
        this.priceIcon = jDJSONObject.optString(KEY_PRICEICON);
        this.priceColor = jDJSONObject.optString(KEY_PRICECOLOR);
        this.priceWithoutHelp = jDJSONObject.optString(KEY_PRICEWITHOUTHELP);
        this.unusable = Boolean.valueOf(jDJSONObject.optBoolean(KEY_UNUSABLE));
        this.optimalNotice = jDJSONObject.optString(KEY_OPTIMAL_NOTICE);
    }

    public static ArrayList<CartPromotion> toList(JDJSONArray jDJSONArray) {
        if (jDJSONArray == null || jDJSONArray.size() == 0) {
            return null;
        }
        int size = jDJSONArray.size();
        ArrayList<CartPromotion> arrayList = new ArrayList<>(size);
        for (int i2 = 0; i2 < size; i2++) {
            JDJSONObject optJSONObject = jDJSONArray.optJSONObject(i2);
            if (optJSONObject != null) {
                arrayList.add(new CartPromotion(optJSONObject));
            }
        }
        return arrayList;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeLong(this.id);
        parcel.writeString(this.title);
        parcel.writeInt(this.type);
        parcel.writeInt(this.checkType);
        parcel.writeInt(this.needJBeanNum);
        parcel.writeString(this.discount);
        parcel.writeString(this.jBeanPromoPrice);
        parcel.writeString(this.price);
        parcel.writeString(this.priceIcon);
        parcel.writeString(this.priceColor);
        parcel.writeString(this.priceWithoutHelp);
        if (Build.VERSION.SDK_INT >= 29) {
            parcel.writeBoolean(this.unusable.booleanValue());
        } else {
            parcel.writeValue(this.unusable);
        }
    }

    public CartPromotion(JDJSONObject jDJSONObject, int i2) {
        this(jDJSONObject);
        if (jDJSONObject != null && i2 == 0) {
            this.needJBeanNum = jDJSONObject.optInt(KEY_NEEDJBEANNUM, 0);
            this.discount = jDJSONObject.optString("discount", "");
            this.jBeanPromoPrice = jDJSONObject.optString(KEY_JBEANPROMOPRICE, "");
        }
    }

    protected CartPromotion(Parcel parcel) {
        this.id = parcel.readLong();
        this.title = parcel.readString();
        this.type = parcel.readInt();
        this.checkType = parcel.readInt();
        this.needJBeanNum = parcel.readInt();
        this.discount = parcel.readString();
        this.jBeanPromoPrice = parcel.readString();
        this.price = parcel.readString();
        this.priceIcon = parcel.readString();
        this.priceColor = parcel.readString();
        this.priceWithoutHelp = parcel.readString();
        if (Build.VERSION.SDK_INT >= 29) {
            this.unusable = Boolean.valueOf(parcel.readBoolean());
        } else {
            this.unusable = (Boolean) parcel.readValue(Boolean.class.getClassLoader());
        }
    }
}
