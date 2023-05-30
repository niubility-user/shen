package com.jingdong.common.entity.cart;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.jingdong.jdsdk.constant.CartConstant;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class CartCouponSummary extends CartSkuSummary implements Parcelable {
    public static final Parcelable.Creator<CartCouponSummary> CREATOR = new Parcelable.Creator<CartCouponSummary>() { // from class: com.jingdong.common.entity.cart.CartCouponSummary.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CartCouponSummary createFromParcel(Parcel parcel) {
            return new CartCouponSummary(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CartCouponSummary[] newArray(int i2) {
            return new CartCouponSummary[i2];
        }
    };
    protected int cid;
    protected Map<String, String> fields;
    protected Integer overseaPurchase;
    protected String pid;
    protected long subVendorType;
    protected int type;
    protected int vendorId;

    public CartCouponSummary(String str, int i2) {
        super(str, 1);
        this.cid = i2;
    }

    @Override // com.jingdong.common.entity.cart.CartSkuSummary, com.jingdong.common.entity.cart.CartSummary, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public int getCid() {
        return this.cid;
    }

    public Map<String, String> getFields() {
        return this.fields;
    }

    public Integer getOverseaPurchase() {
        return this.overseaPurchase;
    }

    public String getPid() {
        return this.pid;
    }

    public long getSubVendorType() {
        return this.subVendorType;
    }

    public int getType() {
        return this.type;
    }

    public int getVendorId() {
        return this.vendorId;
    }

    public void setCid(int i2) {
        this.cid = i2;
    }

    public void setFields(Map<String, String> map) {
        this.fields = map;
    }

    public void setOverseaPurchase(Integer num) {
        this.overseaPurchase = num;
    }

    public void setPid(String str) {
        this.pid = str;
    }

    public void setSubVendorType(long j2) {
        this.subVendorType = j2;
    }

    public void setType(int i2) {
        this.type = i2;
    }

    public void setVendorId(int i2) {
        this.vendorId = i2;
    }

    @Override // com.jingdong.common.entity.cart.CartSkuSummary
    public String toString() {
        return "CartCouponSummary{cid=" + this.cid + ", pid='" + this.pid + "', overseaPurchase=" + this.overseaPurchase + '}';
    }

    @Override // com.jingdong.common.entity.cart.CartSkuSummary
    public JSONObject toSummaryParams() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("Id", getSkuId());
        jSONObject.put("cid", getCid());
        jSONObject.put("type", getType());
        jSONObject.put("vendorId", getVendorId());
        if (getSubVendorType() != 0) {
            jSONObject.put("subVendorType", getSubVendorType());
        }
        if (!TextUtils.isEmpty(this.storeId)) {
            jSONObject.put("storeId", this.storeId);
        }
        if (!TextUtils.isEmpty(this.skuUuid)) {
            jSONObject.put("skuUuid", this.skuUuid);
        }
        boolean z = this.useUuid;
        if (z) {
            jSONObject.put("useUuid", z);
        }
        if (getPid() != null) {
            jSONObject.put("pid", getPid());
        }
        if (getOverseaPurchase() != null) {
            jSONObject.put("overseaPurchase", getOverseaPurchase());
        }
        Map<String, String> map = this.fields;
        if (map != null && map.entrySet().size() > 0) {
            JSONObject jSONObject2 = new JSONObject();
            for (Map.Entry<String, String> entry : this.fields.entrySet()) {
                jSONObject2.put(entry.getKey(), entry.getValue());
            }
            jSONObject.put("fields", jSONObject2);
        }
        CartExtFlag cartExtFlag = this.extFlag;
        if (cartExtFlag != null && cartExtFlag.toSummaryParams().length() > 0) {
            jSONObject.put(CartConstant.KEY_EXTFLAG, cartExtFlag.toSummaryParams());
        }
        return jSONObject;
    }

    @Override // com.jingdong.common.entity.cart.CartSkuSummary, com.jingdong.common.entity.cart.CartSummary, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        super.writeToParcel(parcel, i2);
        parcel.writeInt(this.cid);
        parcel.writeInt(this.vendorId);
        parcel.writeLong(this.subVendorType);
        parcel.writeString(this.pid);
        parcel.writeValue(this.overseaPurchase);
    }

    public CartCouponSummary(String str, int i2, int i3) {
        super(str, 1);
        this.cid = i2;
        this.type = i3;
    }

    public CartCouponSummary(String str, int i2, int i3, String str2) {
        super(str, 1, str2);
        this.cid = i2;
        this.type = i3;
    }

    public CartCouponSummary(String str, int i2, int i3, String str2, String str3, boolean z) {
        super(str, 1, str2, str3, z);
        this.cid = i2;
        this.type = i3;
    }

    public CartCouponSummary(String str, int i2, String str2) {
        super(str, 1);
        this.cid = i2;
        this.pid = str2;
    }

    public CartCouponSummary(String str, int i2, Integer num) {
        super(str, 1);
        this.cid = i2;
        this.overseaPurchase = num;
    }

    public CartCouponSummary(String str, int i2, String str2, Integer num) {
        super(str, 1);
        this.cid = i2;
        this.pid = str2;
        this.overseaPurchase = num;
    }

    protected CartCouponSummary(Parcel parcel) {
        super(parcel);
        this.cid = parcel.readInt();
        this.vendorId = parcel.readInt();
        this.subVendorType = parcel.readLong();
        this.pid = parcel.readString();
        this.overseaPurchase = (Integer) parcel.readValue(Integer.class.getClassLoader());
    }
}
