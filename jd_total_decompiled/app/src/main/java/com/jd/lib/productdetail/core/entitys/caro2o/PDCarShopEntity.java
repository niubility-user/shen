package com.jd.lib.productdetail.core.entitys.caro2o;

import android.text.TextUtils;

/* loaded from: classes15.dex */
public class PDCarShopEntity {
    public static final String HEALTHY_ONLINE = "2";
    public static final String HEALTHY_o2o = "1";
    public String anchorText;
    public String bundlingOrderId;
    public String carIcon;
    public boolean carNewFlag;
    public String carText;
    public String colorSize;
    public int count;
    public boolean deliveryShopSelected;
    public String discount;
    public String fix;
    public String fixstyle;
    public String freeload;
    public boolean hasHave;
    public String install;
    public String installText;
    public String introduceurl;
    public boolean isInShopService = true;
    public boolean isSelectAgreement;
    public String jHealthHentaiEnum;
    public boolean linkage;
    public String mLink;
    public String noInstallPrice;
    public String nofix;
    public String price;
    public PDCarRecLocShopEntity recLocShop;
    public boolean recShopFlag;
    public String sendStatus;
    public String sendText;
    public String sendText1;
    public String sendText2;
    public String sendUrl;
    public String serviceInfoId;
    public String serviceNumLink;
    public String serviceSku;
    public String shopIcon;
    public String storeId;
    public String tipsIcon;
    public String toBuyUrl;
    public String validDesc;

    public boolean isSelectAgreement() {
        return this.isSelectAgreement || TextUtils.equals(this.sendStatus, "2");
    }

    public boolean isSelectCarShop() {
        return !TextUtils.isEmpty(this.bundlingOrderId);
    }
}
