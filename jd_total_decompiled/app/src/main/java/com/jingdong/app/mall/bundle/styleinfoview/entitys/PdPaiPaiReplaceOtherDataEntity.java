package com.jingdong.app.mall.bundle.styleinfoview.entitys;

import java.util.ArrayList;

/* loaded from: classes3.dex */
public class PdPaiPaiReplaceOtherDataEntity {
    public String code;
    public DataBean data;
    public String page;

    /* loaded from: classes3.dex */
    public static class DataBean {
        public CatalogBean catalog;
        public MineInfoBean mineInfo;

        /* loaded from: classes3.dex */
        public static class CatalogBean {
            public ArrayList<BrandSearchVoListBean> brandSearchVoList;
            public ArrayList<CategorySearchVoListBean> categorySearchVoList;
            public int code;
            public int pageNo;
            public int pageSize;
            public ArrayList<ProductSearchVoListBean> productSearchVoList;
            public int totalNumber;
            public int totalPage;
            public int venderId;

            /* loaded from: classes3.dex */
            public static class BrandSearchVoListBean {
                public String brandCnName;
                public String imageUrl;
                public boolean isSelect;
                public int order;
                public int searchCount;
            }

            /* loaded from: classes3.dex */
            public class CategorySearchVoListBean {
                public String categoryName;
                public boolean isSelect;
                public int order;
                public int searchCount;

                public CategorySearchVoListBean() {
                }
            }

            /* loaded from: classes3.dex */
            public class ProductSearchVoListBean {
                public String brandCnName;
                public int brandId;
                public int categoryId;
                public String fullImageUrl;
                public int id;
                public boolean isSelect;
                public int oldProductId;
                public String productName;

                public ProductSearchVoListBean() {
                }
            }
        }

        /* loaded from: classes3.dex */
        private class MineInfoBean {
            private MineInfoBean() {
            }
        }
    }
}
