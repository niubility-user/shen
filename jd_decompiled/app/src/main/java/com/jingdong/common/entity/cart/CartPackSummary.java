package com.jingdong.common.entity.cart;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.entity.Pack;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.sdk.oklog.OKLog;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class CartPackSummary extends CartSummary implements Parcelable {
    public static final Parcelable.Creator<CartPackSummary> CREATOR = new Parcelable.Creator<CartPackSummary>() { // from class: com.jingdong.common.entity.cart.CartPackSummary.1
        @Override // android.os.Parcelable.Creator
        public CartPackSummary createFromParcel(Parcel parcel) {
            return new CartPackSummary(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public CartPackSummary[] newArray(int i2) {
            return new CartPackSummary[i2];
        }
    };
    private static final String TAG = "CartPackSummary";
    protected ArrayList<? super CartSummary> childItems;
    protected CartExtFlag extFlag;
    protected ArrayList<? super CartSkuSummary> gifts;
    protected String packId;
    public int parseType;
    protected String sType;
    public String skuUuid;
    public String storeId;
    public boolean useUuid;
    protected String ybPackId;

    public CartPackSummary(String str, Integer num, String str2) {
        this(str, num);
        this.sType = str2;
    }

    public void addGift(CartSkuSummary cartSkuSummary) {
        if (cartSkuSummary != null) {
            this.gifts.add(cartSkuSummary);
        }
    }

    public void addSku(CartSummary cartSummary) {
        if (cartSummary != null) {
            this.childItems.add(cartSummary);
        }
    }

    @Override // com.jingdong.common.entity.cart.CartSummary, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public CartPackSummary getCartPackSummaryToRouter(JDJSONObject jDJSONObject) {
        if (jDJSONObject != null) {
            CartPackSummary cartPackSummary = new CartPackSummary(jDJSONObject);
            cartPackSummary.packId = jDJSONObject.optString("packId");
            cartPackSummary.num = jDJSONObject.optInt("num", 0);
            cartPackSummary.sType = jDJSONObject.optString("sType");
            cartPackSummary.skuUuid = jDJSONObject.optString("skuUuid");
            cartPackSummary.storeId = jDJSONObject.optString("storeId");
            cartPackSummary.useUuid = jDJSONObject.optBoolean("useUuid", false);
            cartPackSummary.ybPackId = jDJSONObject.optString(CartConstant.KEY_YB_PACK_ID);
            cartPackSummary.extFlag = new CartExtFlag(jDJSONObject.optJSONObject(CartConstant.KEY_EXTFLAG));
            return cartPackSummary;
        }
        return null;
    }

    public ArrayList<? super CartSummary> getChildItems() {
        return this.childItems;
    }

    public CartExtFlag getExtFlag() {
        if (this.extFlag == null) {
            this.extFlag = new CartExtFlag();
        }
        return this.extFlag;
    }

    public ArrayList<? super CartSkuSummary> getGifts() {
        return this.gifts;
    }

    public String getPackId() {
        String str = this.packId;
        return str == null ? "" : str;
    }

    public CartSummary getSku(String str) {
        ArrayList<? super CartSummary> arrayList = this.childItems;
        if (arrayList == null || arrayList.size() <= 0) {
            return null;
        }
        int size = this.childItems.size();
        for (int i2 = 0; i2 < size; i2++) {
            CartSummary cartSummary = this.childItems.get(i2);
            if (cartSummary instanceof CartSkuSummary) {
                CartSkuSummary cartSkuSummary = (CartSkuSummary) cartSummary;
                if (cartSkuSummary.getSkuId().equals(str)) {
                    return cartSkuSummary;
                }
            } else {
                CartPackSummary cartPackSummary = (CartPackSummary) cartSummary;
                if (cartPackSummary.getPackId().equals(str)) {
                    return cartPackSummary;
                }
            }
        }
        return null;
    }

    public String getYbPackId() {
        String str = this.ybPackId;
        return str == null ? "" : str;
    }

    public String getsType() {
        String str = this.sType;
        return str == null ? "" : str;
    }

    public void removeSku(String str) {
        ArrayList<? super CartSummary> arrayList = this.childItems;
        if (arrayList == null || arrayList.size() <= 0) {
            return;
        }
        int size = this.childItems.size();
        int i2 = 0;
        while (true) {
            if (i2 >= size) {
                i2 = -1;
                break;
            }
            CartSummary cartSummary = this.childItems.get(i2);
            if (cartSummary instanceof CartSkuSummary) {
                if (((CartSkuSummary) cartSummary).getSkuId().equals(str)) {
                    break;
                }
                i2++;
            } else if (((CartPackSummary) cartSummary).getPackId().equals(str)) {
                break;
            } else {
                i2++;
            }
        }
        if (i2 != -1) {
            this.childItems.remove(i2);
        }
    }

    public void setChildItems(ArrayList<? super CartSummary> arrayList) {
        this.childItems = arrayList;
    }

    public void setExtFlag(CartExtFlag cartExtFlag) {
        this.extFlag = cartExtFlag;
    }

    public void setGifts(ArrayList<? super CartSkuSummary> arrayList) {
        this.gifts = arrayList;
    }

    public void setPackId(String str) {
        this.packId = str;
    }

    public void setYbPackId(String str) {
        this.ybPackId = str;
    }

    public void setsType(String str) {
        this.sType = str;
    }

    public Pack toPack() {
        Pack pack = new Pack();
        try {
            pack.setId(Long.valueOf(Long.parseLong(this.packId)));
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e(TAG, e2);
            }
        }
        pack.setNum(Integer.valueOf(this.num));
        return pack;
    }

    public JSONObject toSummaryParams() throws JSONException {
        JSONArray jSONArray;
        ArrayList<? super CartSummary> arrayList;
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("Id", getPackId());
        if (!TextUtils.isEmpty(getYbPackId())) {
            jSONObject.put(CartConstant.KEY_YB_PACK_ID, getYbPackId());
        }
        jSONObject.put("num", getNum());
        if (!TextUtils.isEmpty(getSkuFlag())) {
            jSONObject.put(CartConstant.KEY_SKUFLAG, getSkuFlag());
        }
        CartExtFlag cartExtFlag = this.extFlag;
        if (cartExtFlag != null && cartExtFlag.toSummaryParams().length() > 0) {
            jSONObject.put(CartConstant.KEY_EXTFLAG, this.extFlag.toSummaryParams());
        }
        if (!"".equals(getsType())) {
            if (TextUtils.equals(getsType(), "16") && (arrayList = this.childItems) != null && arrayList.size() > 0) {
                jSONObject.put("sType", "13");
            } else {
                jSONObject.put("sType", getsType());
            }
        }
        JSONArray jSONArray2 = null;
        if (this.parseType == 0) {
            ArrayList<? super CartSkuSummary> arrayList2 = this.gifts;
            if (arrayList2 != null && arrayList2.size() > 0) {
                jSONArray2 = new JSONArray();
                Iterator<? super CartSkuSummary> it = this.gifts.iterator();
                while (it.hasNext()) {
                    jSONArray2.put(it.next().toSummaryParams());
                }
            }
            ArrayList<? super CartSummary> arrayList3 = this.childItems;
            if (arrayList3 != null && arrayList3.size() > 0) {
                if (jSONArray2 == null) {
                    jSONArray2 = new JSONArray();
                }
                Iterator<? super CartSummary> it2 = this.childItems.iterator();
                while (it2.hasNext()) {
                    CartSummary next = it2.next();
                    if (next instanceof CartSkuSummary) {
                        jSONArray2.put(((CartSkuSummary) next).toSummaryParams());
                    } else {
                        CartPackSummary cartPackSummary = (CartPackSummary) next;
                        CartSkuSummary cartSkuSummary = new CartSkuSummary(cartPackSummary.getPackId(), cartPackSummary.getNum(), cartPackSummary.storeId, cartPackSummary.skuUuid, cartPackSummary.useUuid);
                        if (this.itemType == 12) {
                            cartSkuSummary.setsType(CartConstant.SUIT_TYPE_COMMON_PACK_FULL_GIFT);
                        } else {
                            cartSkuSummary.setsType("24");
                        }
                        jSONArray2.put(cartSkuSummary.toSummaryParams());
                    }
                }
            }
            if (jSONArray2 != null) {
                jSONObject.put(CartConstant.KEY_THE_SKUS, jSONArray2);
            }
        } else {
            ArrayList<? super CartSkuSummary> arrayList4 = this.gifts;
            if (arrayList4 == null || arrayList4.size() <= 0) {
                jSONArray = null;
            } else {
                jSONArray = new JSONArray();
                Iterator<? super CartSkuSummary> it3 = this.gifts.iterator();
                while (it3.hasNext()) {
                    jSONArray.put(it3.next().toSummaryParams());
                }
            }
            if (jSONArray != null) {
                jSONObject.put(CartConstant.KEY_THE_GIFTS, jSONArray);
            }
            ArrayList<? super CartSummary> arrayList5 = this.childItems;
            if (arrayList5 != null && arrayList5.size() > 0) {
                jSONArray2 = new JSONArray();
                Iterator<? super CartSummary> it4 = this.childItems.iterator();
                while (it4.hasNext()) {
                    CartSummary next2 = it4.next();
                    if (next2 instanceof CartSkuSummary) {
                        jSONArray2.put(((CartSkuSummary) next2).toSummaryParams());
                    } else {
                        CartPackSummary cartPackSummary2 = (CartPackSummary) next2;
                        CartSkuSummary cartSkuSummary2 = new CartSkuSummary(cartPackSummary2.getPackId(), cartPackSummary2.getNum(), cartPackSummary2.storeId, cartPackSummary2.skuUuid, cartPackSummary2.useUuid);
                        if (this.itemType == 12) {
                            cartSkuSummary2.setsType(CartConstant.SUIT_TYPE_COMMON_PACK_FULL_GIFT);
                        } else {
                            cartSkuSummary2.setsType("24");
                        }
                        jSONArray2.put(cartSkuSummary2.toSummaryParams());
                    }
                }
            }
            if (jSONArray2 != null) {
                jSONObject.put(CartConstant.KEY_THE_SKUS, jSONArray2);
            }
        }
        if (!TextUtils.isEmpty(this.skuUuid)) {
            jSONObject.put("skuUuid", this.skuUuid);
        }
        if (!TextUtils.isEmpty(this.storeId)) {
            jSONObject.put("storeId", this.storeId);
        }
        boolean z = this.useUuid;
        if (z) {
            jSONObject.put("useUuid", z);
        }
        return jSONObject;
    }

    @Override // com.jingdong.common.entity.cart.CartSummary, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        super.writeToParcel(parcel, i2);
        parcel.writeString(this.packId);
        parcel.writeInt(this.num);
        parcel.writeString(this.ybPackId);
        parcel.writeList(this.gifts);
        parcel.writeList(this.childItems);
        parcel.writeString(this.sType);
    }

    public CartPackSummary(String str, Integer num, String str2, String str3, String str4, boolean z) {
        this(str, num);
        this.sType = str2;
        this.skuUuid = str3;
        this.storeId = str4;
        this.useUuid = z;
    }

    public CartPackSummary(String str, String str2, Integer num, String str3) {
        this(str, num);
        this.ybPackId = str2;
        this.sType = str3;
    }

    public CartPackSummary(String str, Integer num, ArrayList<CartSkuSummary> arrayList, String str2) {
        this(str, num);
        this.gifts = arrayList;
        this.sType = str2;
    }

    public CartPackSummary(JDJSONObject jDJSONObject) {
        this.parseType = 0;
        this.gifts = new ArrayList<>();
        this.childItems = new ArrayList<>();
        this.sType = "";
    }

    public CartPackSummary(String str, Integer num) {
        this.parseType = 0;
        this.gifts = new ArrayList<>();
        this.childItems = new ArrayList<>();
        this.sType = "";
        this.packId = str;
        this.num = num.intValue();
    }

    public CartPackSummary(Parcel parcel) {
        super(parcel);
        this.parseType = 0;
        this.gifts = new ArrayList<>();
        this.childItems = new ArrayList<>();
        this.sType = "";
        this.packId = parcel.readString();
        this.num = parcel.readInt();
        this.ybPackId = parcel.readString();
        ArrayList<? super CartSkuSummary> arrayList = new ArrayList<>();
        this.gifts = arrayList;
        parcel.readList(arrayList, CartSkuSummary.class.getClassLoader());
        ArrayList<? super CartSummary> arrayList2 = new ArrayList<>();
        this.childItems = arrayList2;
        parcel.readList(arrayList2, CartSummary.class.getClassLoader());
        this.sType = parcel.readString();
    }
}
