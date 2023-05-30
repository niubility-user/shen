package com.jingdong.app.mall.bundle.styleinfoview.entitys;

import java.util.List;

/* loaded from: classes3.dex */
public class MatchPurchaseEntity {
    public List<MatchPurchaseEntityItem> mpList;
    public String mpTitle;

    /* loaded from: classes3.dex */
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
