package com.jingdong.common.entity.cart;

import com.jingdong.jdsdk.constant.CartConstant;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class CartRequestBase {
    protected ArrayList<CartPackSummary> packs;
    protected ArrayList<CartSkuSummary> skus;
    protected int tideUp;

    public CartRequestBase(ArrayList<CartSkuSummary> arrayList, ArrayList<CartPackSummary> arrayList2) {
        this.skus = new ArrayList<>();
        this.packs = new ArrayList<>();
        this.tideUp = -1;
        this.skus = arrayList;
        this.packs = arrayList2;
    }

    public ArrayList<CartPackSummary> getPacks() {
        return this.packs;
    }

    public ArrayList<CartSkuSummary> getSkus() {
        return this.skus;
    }

    public int getTideUp() {
        return this.tideUp;
    }

    public void setOperatePacks(ArrayList<CartPackSummary> arrayList) {
        this.packs = arrayList;
    }

    public void setOperateSkus(ArrayList<CartSkuSummary> arrayList) {
        this.skus = arrayList;
    }

    public void setTideUp(int i2) {
        this.tideUp = i2;
    }

    public JSONObject toParams() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        ArrayList<CartSkuSummary> skus = getSkus();
        this.skus = skus;
        if (skus != null && skus.size() > 0) {
            JSONArray jSONArray = new JSONArray();
            Iterator<CartSkuSummary> it = this.skus.iterator();
            while (it.hasNext()) {
                jSONArray.put(it.next().toSummaryParams());
            }
            jSONObject.put(CartConstant.KEY_THE_SKUS, jSONArray);
        }
        ArrayList<CartPackSummary> packs = getPacks();
        this.packs = packs;
        if (packs != null && packs.size() > 0) {
            JSONArray jSONArray2 = new JSONArray();
            Iterator<CartPackSummary> it2 = this.packs.iterator();
            while (it2.hasNext()) {
                jSONArray2.put(it2.next().toSummaryParams());
            }
            jSONObject.put(CartConstant.KEY_THE_PACKS, jSONArray2);
        }
        int i2 = this.tideUp;
        if (i2 != -1) {
            jSONObject.put(CartConstant.KEY_TIDEUP, i2);
        }
        return jSONObject;
    }

    public CartRequestBase(CartSkuSummary cartSkuSummary, CartPackSummary cartPackSummary) {
        this.skus = new ArrayList<>();
        this.packs = new ArrayList<>();
        this.tideUp = -1;
        if (cartSkuSummary != null) {
            this.skus.add(cartSkuSummary);
        }
        if (cartPackSummary != null) {
            this.packs.add(cartPackSummary);
        }
    }

    public CartRequestBase() {
        this.skus = new ArrayList<>();
        this.packs = new ArrayList<>();
        this.tideUp = -1;
    }
}
