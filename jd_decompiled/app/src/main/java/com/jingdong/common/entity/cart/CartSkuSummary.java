package com.jingdong.common.entity.cart;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.entity.Product;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.sdk.oklog.OKLog;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class CartSkuSummary extends CartSummary implements Parcelable {
    public static final Parcelable.Creator<CartSkuSummary> CREATOR = new Parcelable.Creator<CartSkuSummary>() { // from class: com.jingdong.common.entity.cart.CartSkuSummary.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CartSkuSummary createFromParcel(Parcel parcel) {
            return new CartSkuSummary(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CartSkuSummary[] newArray(int i2) {
            return new CartSkuSummary[i2];
        }
    };
    private static final String TAG = "CartSkuSummary";
    private long activePromotionId;
    protected String delCategory;
    protected String deliveryId;
    protected CartExtFlag extFlag;
    protected ArrayList<String> fsSkuIds;
    private ArrayList<String> giftPoolsId;
    private ArrayList<NewGiftItem> giftPoolsItems;
    protected String imageDomail;
    protected String imgUrl;
    private String isHyj;
    public String locId;
    private String promoLimitMsg;
    private String sType;
    protected ArrayList<String> serviceSkus;
    protected String skuId;
    public String skuUuid;
    public String storeId;
    private String switchId;
    public boolean useUuid;
    protected ArrayList<String> yanbaoSkus;

    public CartSkuSummary() {
    }

    @Override // com.jingdong.common.entity.cart.CartSummary, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public CartSkuSummary getCartSkuSummaryToRouter(JDJSONObject jDJSONObject) {
        if (jDJSONObject != null) {
            CartSkuSummary cartSkuSummary = new CartSkuSummary(jDJSONObject);
            if (!TextUtils.isEmpty(jDJSONObject.optString("skuId"))) {
                cartSkuSummary.setSkuId(jDJSONObject.optString("skuId"));
            } else if (!TextUtils.isEmpty(jDJSONObject.optString("sku"))) {
                cartSkuSummary.setSkuId(jDJSONObject.optString("sku"));
            }
            cartSkuSummary.setNum(jDJSONObject.optInt("num", 1));
            cartSkuSummary.locId = jDJSONObject.optString("locId");
            cartSkuSummary.switchId = jDJSONObject.optString("switchId");
            cartSkuSummary.isHyj = jDJSONObject.optString("isHyj");
            cartSkuSummary.deliveryId = jDJSONObject.optString(CartConstant.KEY_DELIVERYID);
            cartSkuSummary.extFlag = new CartExtFlag(jDJSONObject.optJSONObject(CartConstant.KEY_EXTFLAG));
            return cartSkuSummary;
        }
        return null;
    }

    public String getDelCategory() {
        return this.delCategory;
    }

    public CartExtFlag getExtFlag() {
        if (this.extFlag == null) {
            this.extFlag = new CartExtFlag();
        }
        return this.extFlag;
    }

    public ArrayList<String> getFsSkuIds() {
        if (this.fsSkuIds == null) {
            this.fsSkuIds = new ArrayList<>();
        }
        return this.fsSkuIds;
    }

    public ArrayList<NewGiftItem> getGiftPoolsItems() {
        if (this.giftPoolsItems == null) {
            this.giftPoolsItems = new ArrayList<>();
        }
        return this.giftPoolsItems;
    }

    public String getImageDomail() {
        return this.imageDomail;
    }

    public String getImgUrl() {
        String str = this.imgUrl;
        if (str == null) {
            return "";
        }
        if (str.startsWith("http://")) {
            return this.imgUrl;
        }
        if (this.imageDomail == null) {
            this.imageDomail = "";
        }
        return this.imageDomail + this.imgUrl;
    }

    public String getOriginalImgUrl() {
        String str = this.imgUrl;
        return str == null ? "" : str;
    }

    public String getPromoLimitMsg() {
        return this.promoLimitMsg;
    }

    public ArrayList<String> getServiceSkus() {
        return this.serviceSkus;
    }

    public String getSkuId() {
        String str = this.skuId;
        return str == null ? "" : str;
    }

    public String getSkuUuid() {
        return this.skuUuid;
    }

    public String getStoreId() {
        return this.storeId;
    }

    public ArrayList<String> getYanbaoSkus() {
        return this.yanbaoSkus;
    }

    public String getsType() {
        return this.sType;
    }

    public void putGiftPoolIds(String str) {
        if (this.giftPoolsId == null) {
            this.giftPoolsId = new ArrayList<>();
        }
        this.giftPoolsId.add(str);
    }

    public void putGiftPoolsItems(NewGiftItem newGiftItem) {
        if (this.giftPoolsItems == null) {
            this.giftPoolsItems = new ArrayList<>();
        }
        this.giftPoolsItems.add(newGiftItem);
    }

    public void setActivePromotionId(long j2) {
        this.activePromotionId = j2;
    }

    public void setDelCategory(String str) {
        this.delCategory = str;
    }

    public void setDeliveryId(String str) {
        this.deliveryId = str;
    }

    public void setExtFlag(CartExtFlag cartExtFlag) {
        this.extFlag = cartExtFlag;
    }

    public void setFsSkuIds(ArrayList<String> arrayList) {
        this.fsSkuIds = arrayList;
    }

    public void setGiftPoolsId(ArrayList<String> arrayList) {
        this.giftPoolsId = arrayList;
    }

    public void setGiftPoolsItems(ArrayList<NewGiftItem> arrayList) {
        this.giftPoolsItems = arrayList;
    }

    public void setImageDomail(String str) {
        this.imageDomail = str;
    }

    public void setImgUrl(String str) {
        this.imgUrl = str;
    }

    public void setIsHyj(String str) {
        this.isHyj = str;
    }

    public void setLocId(String str) {
        this.locId = str;
    }

    public void setPromoLimitMsg(String str) {
        this.promoLimitMsg = str;
    }

    public void setSkuId(String str) {
        this.skuId = str;
    }

    public void setSkuUuid(String str) {
        this.skuUuid = str;
    }

    public void setStoreId(String str) {
        this.storeId = str;
    }

    public void setSwitchId(String str) {
        this.switchId = str;
    }

    public void setsType(String str) {
        this.sType = str;
    }

    public Product toProduct() {
        Product product = new Product();
        try {
            product.setId(Long.valueOf(Long.parseLong(this.skuId)));
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e(TAG, e2);
            }
        }
        product.setNum(Integer.valueOf(this.num));
        return product;
    }

    public String toString() {
        return "CartSkuSummary [skuId=" + this.skuId + ", num=" + this.num + ", skuUuid=" + this.skuUuid + ", storeId=" + this.storeId + "]";
    }

    public JSONObject toSummaryParams() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("Id", getSkuId());
        jSONObject.put("num", String.valueOf(getNum()));
        if (!TextUtils.isEmpty(getSkuFlag())) {
            jSONObject.put(CartConstant.KEY_SKUFLAG, getSkuFlag());
        }
        if (!TextUtils.isEmpty(this.deliveryId)) {
            jSONObject.put(CartConstant.KEY_DELIVERYID, this.deliveryId);
        }
        long j2 = this.activePromotionId;
        if (j2 > 0) {
            jSONObject.put("activePromotionId", j2);
        }
        if (!TextUtils.isEmpty(this.sType)) {
            jSONObject.put("sType", this.sType);
        }
        if (!TextUtils.isEmpty(this.switchId)) {
            jSONObject.put("switchId", this.switchId);
        }
        CartExtFlag cartExtFlag = this.extFlag;
        if (cartExtFlag != null && cartExtFlag.toSummaryParams().length() > 0) {
            jSONObject.put(CartConstant.KEY_EXTFLAG, this.extFlag.toSummaryParams());
        }
        if (!TextUtils.isEmpty(this.locId)) {
            jSONObject.put("locId", this.locId);
        }
        if (!TextUtils.isEmpty(this.delCategory)) {
            jSONObject.put("delCategory", this.delCategory);
        }
        ArrayList<String> arrayList = this.fsSkuIds;
        if (arrayList != null && arrayList.size() > 0) {
            JSONArray jSONArray = new JSONArray();
            Iterator<String> it = this.fsSkuIds.iterator();
            while (it.hasNext()) {
                jSONArray.put(it.next());
            }
            jSONObject.put("fsSkuIds", jSONArray);
        }
        ArrayList<String> arrayList2 = this.giftPoolsId;
        if (arrayList2 != null && arrayList2.size() > 0) {
            JSONArray jSONArray2 = new JSONArray();
            int size = this.giftPoolsId.size();
            for (int i2 = 0; i2 < size; i2++) {
                jSONArray2.put(this.giftPoolsId.get(i2));
            }
            jSONObject.put("giftIds", jSONArray2);
        }
        ArrayList<NewGiftItem> arrayList3 = this.giftPoolsItems;
        if (arrayList3 != null && arrayList3.size() > 0) {
            JSONArray jSONArray3 = new JSONArray();
            int size2 = this.giftPoolsItems.size();
            for (int i3 = 0; i3 < size2; i3++) {
                jSONArray3.put(this.giftPoolsItems.get(i3).toSummaryParams());
            }
            jSONObject.put("giftItems", jSONArray3);
        }
        if (!TextUtils.isEmpty(this.isHyj)) {
            jSONObject.put("isHyj", this.isHyj);
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
        parcel.writeString(this.skuId);
        parcel.writeInt(this.num);
        parcel.writeString(this.deliveryId);
        parcel.writeLong(this.activePromotionId);
        parcel.writeString(this.sType);
        parcel.writeString(this.switchId);
        parcel.writeString(this.locId);
        parcel.writeString(this.delCategory);
        parcel.writeString(this.imageDomail);
        parcel.writeString(this.imgUrl);
        parcel.writeStringList(this.fsSkuIds);
        parcel.writeStringList(this.giftPoolsId);
        parcel.writeTypedList(this.giftPoolsItems);
        parcel.writeStringList(this.yanbaoSkus);
        parcel.writeStringList(this.serviceSkus);
        parcel.writeString(this.isHyj);
        parcel.writeString(this.skuUuid);
        parcel.writeString(this.storeId);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public CartSkuSummary(JDJSONObject jDJSONObject) {
        if (jDJSONObject != null) {
            setNum(jDJSONObject.optInt(CartConstant.KEY_NUM_BIG));
            setSkuId(jDJSONObject.optString("Id"));
            setSkuUuid(jDJSONObject.optString("skuUuid", ""));
            setStoreId(jDJSONObject.optString("storeId", ""));
            this.promoLimitMsg = jDJSONObject.optString(CartConstant.KEY_SKU_SUIT_PROMOLIMITMSG);
            this.useUuid = jDJSONObject.optBoolean("useUuid");
        }
    }

    public CartSkuSummary(String str, int i2) {
        this.skuId = str;
        this.num = i2;
        this.itemType = 1;
    }

    public CartSkuSummary(String str, int i2, String str2) {
        this(str, i2);
        this.storeId = str2;
    }

    public CartSkuSummary(String str, int i2, String str2, String str3) {
        this(str, i2, str2);
        this.skuUuid = str3;
    }

    public CartSkuSummary(String str, int i2, String str2, String str3, boolean z) {
        this(str, i2, str2, str3);
        this.useUuid = z;
    }

    public CartSkuSummary(String str, ArrayList<String> arrayList, Integer num) {
        this.skuId = str;
        this.num = num.intValue();
        this.itemType = 1;
        this.giftPoolsId = arrayList;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public CartSkuSummary(Parcel parcel) {
        super(parcel);
        this.skuId = parcel.readString();
        this.num = parcel.readInt();
        this.deliveryId = parcel.readString();
        this.activePromotionId = parcel.readLong();
        this.sType = parcel.readString();
        this.switchId = parcel.readString();
        this.locId = parcel.readString();
        this.delCategory = parcel.readString();
        this.imageDomail = parcel.readString();
        this.imgUrl = parcel.readString();
        this.fsSkuIds = parcel.createStringArrayList();
        this.giftPoolsId = parcel.createStringArrayList();
        this.giftPoolsItems = parcel.createTypedArrayList(NewGiftItem.CREATOR);
        this.yanbaoSkus = parcel.createStringArrayList();
        this.serviceSkus = parcel.createStringArrayList();
        this.isHyj = parcel.readString();
        this.skuUuid = parcel.readString();
        this.storeId = parcel.readString();
    }
}
