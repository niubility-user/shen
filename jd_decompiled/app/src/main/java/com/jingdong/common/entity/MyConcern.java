package com.jingdong.common.entity;

import java.util.List;

/* loaded from: classes5.dex */
public class MyConcern {
    public String displayTime;
    public String groupId;
    private List<Product> productList;
    public String remainTime;

    public List<Product> getProductList() {
        return this.productList;
    }

    public void setProductList(List<Product> list) {
        this.productList = list;
    }
}
