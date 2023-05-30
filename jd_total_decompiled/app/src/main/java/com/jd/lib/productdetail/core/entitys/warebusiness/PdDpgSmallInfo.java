package com.jd.lib.productdetail.core.entitys.warebusiness;

import java.io.Serializable;
import java.util.ArrayList;

/* loaded from: classes15.dex */
public class PdDpgSmallInfo implements Serializable {
    public PdTitleInfo info;
    public ArrayList<PdDpgItemInfo> items;

    /* loaded from: classes15.dex */
    public static class PdDpgItemInfo implements Serializable {
        public String color;
        public boolean isValid;
        public String itemName;
        public String itemUrl;
        public String price;
        public String skuId;
    }

    /* loaded from: classes15.dex */
    public static class PdTitleInfo implements Serializable {
        public String joint;
        public String matchAdWord;
        public String matchId;
        public String matchPrice;
        public String matchTitle;
    }
}
