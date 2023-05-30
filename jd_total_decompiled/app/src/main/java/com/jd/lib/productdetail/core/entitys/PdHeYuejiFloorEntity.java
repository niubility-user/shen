package com.jd.lib.productdetail.core.entitys;

import java.util.List;

/* loaded from: classes15.dex */
public class PdHeYuejiFloorEntity {
    public String businessType;
    public List<Item> items;
    public String murl;

    /* loaded from: classes15.dex */
    public static class Item {
        public static final String BUSI_TYPE_XINYONGSHI = "1";
        public String busiType;
        public String name;
        public String range;
        public boolean selected;
        public String skuId;
        public String type;
    }

    public Item getCurrentItem() {
        List<Item> list = this.items;
        if (list == null || list.size() <= 0) {
            return null;
        }
        for (Item item : this.items) {
            if (item.selected) {
                return item;
            }
        }
        return null;
    }
}
