package com.jd.lib.productdetail.core.entitys;

import java.util.List;

/* loaded from: classes15.dex */
public class MatchPurchaseEntity {
    public List<MatchPurchaseEntityItem> mpList;
    public String mpTitle;
    public String subTitle;

    /* loaded from: classes15.dex */
    public static class MatchPurchaseEntityItem {
        public String iconCode;
        public String imgUrl;
        public String matchBrief;
        public String matchId;
        public String matchTitle;
        public String priceText;
        public int pv;
        public String rate;
    }
}
