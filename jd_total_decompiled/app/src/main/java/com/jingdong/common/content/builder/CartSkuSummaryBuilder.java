package com.jingdong.common.content.builder;

import com.jingdong.common.entity.cart.CartSkuSummary;
import java.util.Map;

/* loaded from: classes5.dex */
public class CartSkuSummaryBuilder {
    private CartSkuSummary sku;

    private CartSkuSummaryBuilder(String str) {
        this.sku = new CartSkuSummary(str, 1);
    }

    public static CartSkuSummaryBuilder create(String str) {
        return new CartSkuSummaryBuilder(str);
    }

    public CartSkuSummary build() {
        return this.sku;
    }

    public CartSkuSummaryBuilder setExtMap(Map<String, String> map) {
        if (map != null && !map.isEmpty()) {
            this.sku.getExtFlag().getExtMap().putAll(map);
        }
        return this;
    }

    public CartSkuSummaryBuilder setStoreId(String str) {
        this.sku.setStoreId(str);
        return this;
    }
}
