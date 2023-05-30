package com.jd.lib.productdetail.core.entitys.warebusiness;

import com.jd.lib.productdetail.core.entitys.warebusiness.PdStrengThenPriceEntity;
import java.util.List;

/* loaded from: classes15.dex */
public class PbPriceEntityV12 {
    public String flag;
    public PreferentialDataModel preferentialDataModel;
    public PriceModel priceMainModel;
    public PriceModel priceSecondModel;

    /* loaded from: classes15.dex */
    public static class IntervalPriceModel {
        public String hitType;
        public String jdMaxPrice;
        public String jdMinPrice;
        public String priceColor;
    }

    /* loaded from: classes15.dex */
    public static class PreferentialDataModel {
        public String arrowImgUrl;
        public String textColor;
        public List<String> textList;
    }

    /* loaded from: classes15.dex */
    public static class PriceCommonModel {
        public String hitType;
        public String price;
        public String priceColor;
        public String priceIconCode;
        public String priceText;
        public String priceTextColor;
        public int useTextType;
    }

    /* loaded from: classes15.dex */
    public static class PriceModel {
        public IntervalPriceModel intervalPriceModel;
        public PriceCommonModel priceCommonModel;
        public int type;
        public PdStrengThenPriceEntity.StrengThenDataEntity.DiscountlineEntity wareDiscountPriceInfo;
    }
}
