package com.jd.lib.productdetail.core.entitys.warebusiness;

/* loaded from: classes15.dex */
public class WareBusinessDepositAddCart {
    public String addModel;
    public BuyType buyType;

    /* loaded from: classes15.dex */
    public static class BuyType {
        public static final int BUY_TYPE_XH = 1;
        public static final int BUY_TYPE_YS = 2;
        public boolean enable = true;
        public BuyTypeItem left;
        public BuyTypeItem right;
        public String title;

        /* loaded from: classes15.dex */
        public static class BuyTypeItem {
            public String name;
            public boolean selected;
            public int type;
        }

        public BuyTypeItem getCurrent() {
            BuyTypeItem buyTypeItem;
            BuyTypeItem buyTypeItem2;
            if (this.enable && (buyTypeItem = this.left) != null && (buyTypeItem2 = this.right) != null) {
                if (buyTypeItem.selected) {
                    return buyTypeItem;
                }
                if (buyTypeItem2.selected) {
                    return buyTypeItem2;
                }
            }
            return null;
        }
    }
}
