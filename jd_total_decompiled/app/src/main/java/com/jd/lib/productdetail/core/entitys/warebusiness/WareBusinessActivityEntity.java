package com.jd.lib.productdetail.core.entitys.warebusiness;

import java.util.List;

/* loaded from: classes15.dex */
public class WareBusinessActivityEntity {
    public BigSale bigSale;
    public List<BigSale> bizActs;

    /* loaded from: classes15.dex */
    public static class BigSale {
        public String bizKey;
        public CustomParam customParam;
        public String desc;
        public String descBounce;
        public String descPrice;
        public String icon;
        public JumpParam jumpParam;
        public int jumpType;
        public boolean mustLogin;
        public String skuSource;
        public boolean truthBigSale;
        public String url;
        public YjhxNewStyle yjhxNewStyle;
    }

    /* loaded from: classes15.dex */
    public static class CustomParam {
        public String colorText;
    }

    /* loaded from: classes15.dex */
    public static class JumpParam {
        public String comefrom;
        public String skuId;
    }

    /* loaded from: classes15.dex */
    public static class YjhxNewStyle {
        public String hxPrice;
        public String hxText;
        public String imgPath;
        public String jprice;
        public String jpriceText;
    }
}
