package com.jingdong.app.mall.bundle.styleinfoview.entitys.caro2o;

import android.text.TextUtils;

/* loaded from: classes3.dex */
public class PDCarShopEntity {
    public static final String HEALTHY_ONLINE = "2";
    public static final String HEALTHY_o2o = "1";
    public String bundlingOrderId;
    public String carIcon;
    public String carText;
    public String colorSize;
    public int count;
    public String discount;
    public String fix;
    public String fixstyle;
    public String install;
    public boolean isInShopService;
    public String jHealthHentaiEnum;
    public boolean linkage;
    public String mLink;
    public String nofix;
    public String price;
    public String serviceInfoId;
    public String serviceSku;
    public String shopIcon;
    public String storeId;
    public String toBuyUrl;

    public boolean isSelectCarShop() {
        return !TextUtils.isEmpty(this.bundlingOrderId);
    }
}
