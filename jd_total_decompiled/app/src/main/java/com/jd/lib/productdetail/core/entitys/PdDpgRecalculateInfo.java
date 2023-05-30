package com.jd.lib.productdetail.core.entitys;

import java.util.List;

/* loaded from: classes15.dex */
public class PdDpgRecalculateInfo {
    public int code;
    public DetailBean data;

    /* loaded from: classes15.dex */
    public static class DetailBean {
        public String discount;
        public int itemNum;
        public String matchPrice;
        public RequestParam param;

        /* loaded from: classes15.dex */
        public static class RequestParam {
            public String function;
            public String matchId;
            public List<SkuInfoParam> skuInfoParamList;
        }

        /* loaded from: classes15.dex */
        public static class SkuInfoParam {
            public int num;
            public String skuId;
            public boolean valid;
        }
    }
}
