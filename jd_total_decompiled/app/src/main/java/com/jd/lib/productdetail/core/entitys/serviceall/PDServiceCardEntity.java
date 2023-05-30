package com.jd.lib.productdetail.core.entitys.serviceall;

import java.util.List;

/* loaded from: classes15.dex */
public class PDServiceCardEntity {
    public String bizCategoryCode;
    public String business_type;
    public CardConfigEntity cardConfig;
    public String cardTitle;
    public int cardType;
    public boolean fxgNewStyle;
    public List<ServiceListEntity> serviceList;
    public String singleCardServiceHighLightText;
    public String singleCardServiceHighLightTextColor;
    public String singleCardServiceText;
    public String singleCardServiceTextColor;
    public String type;

    /* loaded from: classes15.dex */
    public static class CardConfigEntity {
        public String arrow;
        public String bgImage;
        public String heartImage;
        public String icon;
        public String title;
    }

    /* loaded from: classes15.dex */
    public static class ServiceListEntity {
        public String serviceDesc;
        public String serviceDescColor;
        public String serviceHighLightText;
        public String serviceHighLightTextColor;
        public String serviceText;
        public String serviceTextColor;
    }
}
