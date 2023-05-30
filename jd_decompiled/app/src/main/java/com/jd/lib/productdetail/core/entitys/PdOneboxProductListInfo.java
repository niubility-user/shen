package com.jd.lib.productdetail.core.entitys;

import java.util.ArrayList;
import java.util.HashSet;

/* loaded from: classes15.dex */
public class PdOneboxProductListInfo {
    public String HcCid1;
    public String HcCid2;
    public String HcCid3;
    public String code;
    public String curPage;
    public ArrayList<String> entityAttr;
    public String entityTitle;
    public FilterBar filterBar;
    public boolean hasNextPage;
    public String keyword;
    public String logid;
    public HashSet<String> mShowedSkuIds = new HashSet<>();
    public HashSet<String> mUploadedSkuIds = new HashSet<>();
    public ArrayList<MenuBar> menuBar;
    public String oneboxSource;
    public String page;
    public String pvid;
    public String realCount;
    public String topBgImg;
    public int wareCount;
    public ArrayList<WareInfo> wareInfo;

    /* loaded from: classes15.dex */
    public static class FilterBar {
        public String bodyKey;
        public String bodyValue;
        public String len;
        public String lenCkd;
        public String name;
        public String nameCkd;
        public String starIdx;
        public String starIdxCkd;
    }

    /* loaded from: classes15.dex */
    public static class MenuBar {
        public ArrayList<FilterItem> filterItems;
        public String name;
        public String sortBody;
        public ArrayList<SortChild> sortChilds;
        public String sortType;
        public String type;

        /* loaded from: classes15.dex */
        public static class FilterItem {
            public String bodyKey;
            public String bodyValue;
            public String showName;
        }

        /* loaded from: classes15.dex */
        public static class SortChild {
            public String sortBody;
        }
    }

    /* loaded from: classes15.dex */
    public static class WareInfo {
        public String adIconUrl;
        public String catId;
        public String cid1;
        public String cid2;
        public String clientClickUrl;
        public ConfigDatas configDatas;
        public ArrayList<CtmAttrbute> ctmNewStyleAttrList;
        public String entityId;
        public String exposal_url;
        public String good;
        public GoodShop goodShop;
        public String imageurl;
        public String jdPrice;
        public String mostGood;
        public String mostSold;
        public boolean needShield;
        public boolean needShieldCartAndFollow;
        public O2oStore o2oStore;
        public String oneboxSource;
        public String openAPPUrl;
        public String reviews;
        public boolean self;
        public String shopId;
        public String shopName;
        public ArrayList<SkuTimelinessTag> skuTimelinessTags;
        public String spuId;
        public ArrayList<SubPrice> subPrices;
        public String uet;
        public String wareId;
        public String wareType;
        public String wname;

        /* loaded from: classes15.dex */
        public static class ConfigDatas {
            public ArrayList<PriceUnder> priceUnder;

            /* loaded from: classes15.dex */
            public static class PriceUnder {
                public String listResCode;
                public String listShowName;
                public String squareResCode;
                public String squareShowName;
                public String trackId;
            }
        }

        /* loaded from: classes15.dex */
        public static class CtmAttrbute {
            public String tag;
            public String title;
        }

        /* loaded from: classes15.dex */
        public static class GoodShop {
            public String goodShopABTest;
            public String goodShopId;
            public String goodShopName;
            public String longResCode;
            public String resCode;
        }

        /* loaded from: classes15.dex */
        public static class O2oStore {
            public String o2oStoreDistance;
            public String o2oStoreId;
            public String o2oStoreName;
            public String o2oStoreUrl;
        }

        /* loaded from: classes15.dex */
        public static class SkuTimelinessTag {
            public String resCode;
            public String squareResCode;
            public String squareTagText;
            public String tagText;
            public String tagType;
        }

        /* loaded from: classes15.dex */
        public static class SubPrice {
            public String describe;
            public String fontSize;
            public String icon;
            public String price;
            public String priceColor;
            public String priceColorDark;
            public String type;
        }
    }

    public String toString() {
        return "PdOneboxProductListInfo{code='" + this.code + "', wareCount=" + this.wareCount + ", wareInfo=" + this.wareInfo + '}';
    }
}
