package com.jd.lib.productdetail.core.entitys;

/* loaded from: classes15.dex */
public class PDLocStoreEntity {
    public String changeStoreUrl;
    public LocShop locShop;

    /* loaded from: classes15.dex */
    public class LocShop {
        public String distance;
        public String locShopAddr;
        public String locShopId;
        public String locShopName;

        public LocShop() {
        }
    }

    public PDLocStoreEntity() {
    }

    public PDLocStoreEntity(String str, String str2, String str3, String str4) {
        LocShop locShop = new LocShop();
        this.locShop = locShop;
        locShop.distance = str4;
        locShop.locShopId = str;
        locShop.locShopName = str2;
        locShop.locShopAddr = str3;
    }
}
