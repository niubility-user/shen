package com.jd.lib.babel.servicekit.model;

import java.util.ArrayList;

/* loaded from: classes13.dex */
public class CartSummary {
    public ArrayList<CartSummary> childItems;
    public int num;
    public String packId;
    public String skuId;

    public CartSummary(String str) {
        this.num = 1;
        this.skuId = str;
    }

    public void addSku(CartSummary cartSummary) {
        ArrayList<CartSummary> arrayList;
        if (cartSummary == null || (arrayList = this.childItems) == null) {
            return;
        }
        arrayList.add(cartSummary);
    }

    public CartSummary(String str, Integer num) {
        this.num = 1;
        this.childItems = new ArrayList<>();
        this.packId = str;
        this.num = num.intValue();
    }
}
