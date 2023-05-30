package com.jingdong.common.entity.cart;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.cart.CartConfigState;
import com.jingdong.jdsdk.constant.CartConstant;

/* loaded from: classes5.dex */
public class CartResponseSoldOffSku extends CartSummary {
    public static final Parcelable.Creator<CartResponseSoldOffSku> CREATOR = new Parcelable.Creator<CartResponseSoldOffSku>() { // from class: com.jingdong.common.entity.cart.CartResponseSoldOffSku.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CartResponseSoldOffSku createFromParcel(Parcel parcel) {
            return new CartResponseSoldOffSku(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CartResponseSoldOffSku[] newArray(int i2) {
            return new CartResponseSoldOffSku[i2];
        }
    };
    public String bottomText;
    public CartButtonInfo buttonInfo;
    public String customInfoId;
    public CartExtFlag extFlag;
    public String id;
    public int imageType;
    public String imgUrl;
    public boolean isEditChecked;
    public String jumpUrl;
    public String limitType;
    public String maskUrl;
    public String name;
    public double price;
    public CartPropertyTag propertyTags;
    public String skuUuid;
    public long specialId;
    public String storeId;
    public int urlType;
    public boolean useUuid;
    public long venderId;
    public String weight;

    public CartResponseSoldOffSku() {
        this.urlType = -1;
    }

    private void resetEditCheckType() {
        if (CartConfigState.getInstance().specProductCheckedSkuIds.contains(TextUtils.isEmpty(this.skuUuid) ? this.id : this.skuUuid)) {
            this.isEditChecked = true;
        }
    }

    @Override // com.jingdong.common.entity.cart.CartSummary, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.jingdong.common.entity.cart.CartSummary, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        super.writeToParcel(parcel, i2);
        parcel.writeString(this.id);
        parcel.writeString(this.name);
        parcel.writeString(this.imgUrl);
        parcel.writeDouble(this.price);
        parcel.writeByte(this.isEditChecked ? (byte) 1 : (byte) 0);
        parcel.writeParcelable(this.propertyTags, i2);
        parcel.writeString(this.weight);
    }

    public CartResponseSoldOffSku(JDJSONObject jDJSONObject, String str) {
        this.urlType = -1;
        if (jDJSONObject == null) {
            return;
        }
        this.id = jDJSONObject.optString("Id");
        this.name = jDJSONObject.optString("Name");
        this.price = jDJSONObject.optDouble("Price");
        String optString = jDJSONObject.optString("ImgUrl");
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(optString)) {
            this.imgUrl = str + optString;
        }
        this.limitType = jDJSONObject.optString(CartConstant.KEY_SKU_LIMITTYPE);
        this.propertyTags = new CartPropertyTag(jDJSONObject.optJSONObject("propertyTags"));
        this.weight = jDJSONObject.optString(CartConstant.KEY_CART_SOLDOFFSKU_WEIGHT);
        this.specialId = jDJSONObject.optLong("specialId");
        this.skuUuid = jDJSONObject.optString("skuUuid", "");
        this.storeId = jDJSONObject.optString("storeId", "");
        this.customInfoId = jDJSONObject.optString(CartConstant.KEY_CART_SOLDOFFSKU_CUSTOMINFOID, "");
        this.venderId = jDJSONObject.optLong("vendorId", -1L);
        this.useUuid = jDJSONObject.optBoolean("useUuid");
        resetEditCheckType();
        this.jumpUrl = jDJSONObject.optString(CartConstant.KEY_JUMPURL, "");
        this.urlType = jDJSONObject.optInt("urlType", -1);
        this.bottomText = jDJSONObject.optString("bottomText", "");
        JDJSONObject optJSONObject = jDJSONObject.optJSONObject(CartConstant.KEY_EXTFLAG);
        if (optJSONObject != null) {
            this.extFlag = new CartExtFlag(optJSONObject);
        }
        this.imageType = jDJSONObject.optInt("imageType");
        this.maskUrl = jDJSONObject.optString("maskUrl");
        JDJSONObject optJSONObject2 = jDJSONObject.optJSONObject("buttonInfo");
        if (optJSONObject2 != null) {
            this.buttonInfo = new CartButtonInfo(optJSONObject2);
        }
    }

    protected CartResponseSoldOffSku(Parcel parcel) {
        super(parcel);
        this.urlType = -1;
        this.id = parcel.readString();
        this.name = parcel.readString();
        this.imgUrl = parcel.readString();
        this.price = parcel.readDouble();
        this.isEditChecked = parcel.readByte() != 0;
        this.propertyTags = (CartPropertyTag) parcel.readParcelable(CartPropertyTag.class.getClassLoader());
        this.weight = parcel.readString();
    }
}
