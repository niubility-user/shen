package com.jingdong.common.entity.cart;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.jdsdk.constant.CartConstant;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class CartExtFlag implements Parcelable {
    public static final Parcelable.Creator<CartExtFlag> CREATOR = new Parcelable.Creator<CartExtFlag>() { // from class: com.jingdong.common.entity.cart.CartExtFlag.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CartExtFlag createFromParcel(Parcel parcel) {
            return new CartExtFlag(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CartExtFlag[] newArray(int i2) {
            return new CartExtFlag[i2];
        }
    };
    public String buyLimit;
    public String cartPbn;
    public String cartPn;
    public String cartPns;
    public String cartPnt;
    public String dist;
    public String extJsonParam;
    public HashMap<String, String> extMap;
    public String jps;
    public String ob;
    public String pt;
    public int service;
    public String shopId;
    public int suitType;
    public String unusable;

    public CartExtFlag() {
        this.extMap = new HashMap<>();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public HashMap<String, String> getExtMap() {
        return this.extMap;
    }

    public void setExtMap(HashMap<String, String> hashMap) {
        this.extMap = hashMap;
    }

    public JSONObject toSummaryParams() throws JSONException {
        JSONObject jSONObject;
        if (TextUtils.isEmpty(this.extJsonParam)) {
            jSONObject = new JSONObject();
        } else {
            jSONObject = new JSONObject(this.extJsonParam);
        }
        if (!TextUtils.isEmpty(this.dist)) {
            jSONObject.put(CartConstant.KEY_DIST, this.dist);
        }
        int i2 = this.service;
        if (i2 != 0) {
            jSONObject.put("service", i2);
        }
        int i3 = this.suitType;
        if (i3 != 0) {
            jSONObject.put("suitType", i3);
        }
        if (!TextUtils.isEmpty(this.pt)) {
            jSONObject.put("pt", this.pt);
        }
        if (!TextUtils.isEmpty(this.ob)) {
            jSONObject.put(CartConstant.KEY_OB, this.ob);
        }
        if (!TextUtils.isEmpty(this.jps)) {
            jSONObject.put(CartConstant.KEY_CART_JPS, this.jps);
        }
        HashMap<String, String> hashMap = this.extMap;
        if (hashMap != null && !hashMap.isEmpty()) {
            for (Map.Entry<String, String> entry : this.extMap.entrySet()) {
                if (!TextUtils.isEmpty(entry.getKey()) && !TextUtils.isEmpty(entry.getValue())) {
                    jSONObject.put(entry.getKey(), entry.getValue());
                }
            }
        }
        return jSONObject;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeMap(this.extMap);
        parcel.writeString(this.extJsonParam);
        parcel.writeString(this.dist);
        parcel.writeInt(this.service);
        parcel.writeInt(this.suitType);
        parcel.writeString(this.pt);
        parcel.writeString(this.ob);
    }

    public CartExtFlag(JDJSONObject jDJSONObject) {
        this.extMap = new HashMap<>();
        if (jDJSONObject == null) {
            return;
        }
        this.dist = jDJSONObject.optString(CartConstant.KEY_DIST);
        this.service = jDJSONObject.optInt("service");
        this.suitType = jDJSONObject.optInt("suitType");
        this.pt = jDJSONObject.optString("pt");
        this.ob = jDJSONObject.optString(CartConstant.KEY_OB);
        this.cartPnt = jDJSONObject.optString("cart_pnt");
        this.cartPns = jDJSONObject.optString("cart_pns");
        this.cartPn = jDJSONObject.optString("cart_pn");
        this.shopId = jDJSONObject.optString("shopId");
        this.buyLimit = jDJSONObject.optString("buyLimit");
        this.unusable = jDJSONObject.optString(CartPromotion.KEY_UNUSABLE);
        this.cartPbn = jDJSONObject.optString("cart_pbn");
        this.extJsonParam = jDJSONObject.optString("extJsonParam");
        for (String str : jDJSONObject.keySet()) {
            if (this.extMap != null && !TextUtils.isEmpty(str)) {
                this.extMap.put(str, jDJSONObject.optString(str, ""));
            }
        }
    }

    protected CartExtFlag(Parcel parcel) {
        this.extMap = new HashMap<>();
        this.extMap = parcel.readHashMap(HashMap.class.getClassLoader());
        this.dist = parcel.readString();
        this.service = parcel.readInt();
        this.suitType = parcel.readInt();
        this.pt = parcel.readString();
        this.ob = parcel.readString();
        this.extJsonParam = parcel.readString();
    }
}
