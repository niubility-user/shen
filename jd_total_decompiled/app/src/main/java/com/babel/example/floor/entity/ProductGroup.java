package com.babel.example.floor.entity;

import java.util.List;

/* loaded from: classes.dex */
public class ProductGroup {
    public String groupId;
    public List<ProductModel> productList;

    public boolean hasData() {
        List<ProductModel> list = this.productList;
        return list != null && list.size() > 0;
    }
}
