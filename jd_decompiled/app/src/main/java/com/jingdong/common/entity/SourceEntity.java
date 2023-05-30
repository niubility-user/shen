package com.jingdong.common.entity;

import android.os.Bundle;
import android.text.TextUtils;
import java.io.Serializable;

/* loaded from: classes5.dex */
public class SourceEntity implements Serializable {
    public static final String INTENT_EXTRA_ARG_SOURCE = "source";
    public static final String SOURCE_TYPE_BARCODE = "wareIdByBarCodeList";
    public static final String SOURCE_TYPE_CATEGORY_FILTER = "cateFilter";
    public static final String SOURCE_TYPE_CATELOGY = "catelogy";
    public static final String SOURCE_TYPE_CHANNEL = "genericChannel";
    public static final String SOURCE_TYPE_COMPELETE_ORDER_ACTIVITY = "compelete_order_activity";
    public static final String SOURCE_TYPE_CUT_FROM_PRODUCT_DETAIL = "shop_from_product_detail";
    public static final String SOURCE_TYPE_FROM_BABEL = "babel";
    public static final String SOURCE_TYPE_FROM_CATEGORY_RANKING = "ProcurementRanking_Productdetail";
    public static final String SOURCE_TYPE_FROM_COUPON_CENTER = "coupon_center";
    public static final String SOURCE_TYPE_FROM_CUTEVENT = "cutOff";
    public static final String SOURCE_TYPE_FROM_DONGDONG = "dongdong";
    public static final String SOURCE_TYPE_FROM_PHONE_ONLY = "phoneOnly";
    public static final String SOURCE_TYPE_FROM_PHOTOBUY = "photobuy";
    public static final String SOURCE_TYPE_FROM_RANKING = "ProcurementRanking_Productdetail";
    public static final String SOURCE_TYPE_FROM_TRY_CLOTHES = "try_clothes";
    public static final String SOURCE_TYPE_FROM_ZHIDEMAI = "zhidemai";
    public static final String SOURCE_TYPE_HISTORY = "history";
    public static final String SOURCE_TYPE_HOME_ACTIVITY = "listActivity";
    public static final String SOURCE_TYPE_HOME_AREA = "indexModule";
    public static final String SOURCE_TYPE_HOME_FAVORITE = "homeFavoriteList";
    public static final String SOURCE_TYPE_HOME_HISTORY = "homeHistory";
    public static final String SOURCE_TYPE_HOME_MIAOSHA = "indexMiaoShaArea";
    public static final String SOURCE_TYPE_HOME_PRODUCT = "indexRecommend";
    public static final String SOURCE_TYPE_LIKE_SIMILAR = "lookSimilar";
    public static final String SOURCE_TYPE_MYJD_FAVORITE = "favoriteList";
    public static final String SOURCE_TYPE_MYJD_GUESS = "guess";
    public static final String SOURCE_TYPE_MYJD_MESSAGEDETAIL = "messageDetail";
    public static final String SOURCE_TYPE_MYJD_ORDER = "oderMessage";
    public static final String SOURCE_TYPE_MYJD_ORDERWARES = "orderWares";
    public static final String SOURCE_TYPE_NEARBY = "nearby";
    public static final String SOURCE_TYPE_NEWSALES = "newSales";
    public static final String SOURCE_TYPE_OPEN_INTERFACE_CPS = "cps";
    public static final String SOURCE_TYPE_PACKS = "packs";
    public static final String SOURCE_TYPE_PHOTOSEARCH = "visualSearch";
    public static final String SOURCE_TYPE_PROMOTION_FROM_CATEGORY = "promotionProductListFromCategory";
    public static final String SOURCE_TYPE_PROMOTION_FROM_COLOR_SHOPPING = "color_shopping";
    public static final String SOURCE_TYPE_PROMOTION_FROM_HOME = "promotionProductListFromHome";
    public static final String SOURCE_TYPE_PROMOTION_FROM_SALES_PRODUCTS = "recommend_sales_product";
    public static final String SOURCE_TYPE_PROMOTION_FROM_SHOPPING = "recommend_guang";
    public static final String SOURCE_TYPE_PROMOTION_FROM_SLIDE_SCREEN = "promotionProductListFromSlideScreen";
    public static final String SOURCE_TYPE_PROM_HOME_FLOOR = "home_floor";
    public static final String SOURCE_TYPE_PROM_M_DESTINATION_PAGE = "m_destination_page";
    public static final String SOURCE_TYPE_PROM_RECOMMEND_CART = "Shopcart_GuessYouLike";
    public static final String SOURCE_TYPE_PROM_RECOMMEND_PRODUCTDETAIL = "recommend_productDetail";
    public static final String SOURCE_TYPE_RECOMMOND_CATEGORY = "recommend_cate";
    public static final String SOURCE_TYPE_SEARCH_FILTER = "searchFilter";
    public static final String SOURCE_TYPE_SEARCH_HOTKEYWORD = "hotKeyword";
    public static final String SOURCE_TYPE_SEARCH_TEXT = "search";
    public static final String SOURCE_TYPE_SHOPPINGCART = "shoppingCart_product";
    public static final String SOURCE_TYPE_SHOPPINGCART_PACKS = "shoppingCart_pack";
    public static final String SOURCE_TYPE_SHOP_FROM_FAVORIATE = "shop_from_favorite";
    public static final String SOURCE_TYPE_SHOP_FROM_PRODUCT_DETAIL = "shop_from_product_detail";
    public static final String SOURCE_TYPE_SHOP_FROM_RECOMMEND = "shop_from_recommend";
    public static final String SOURCE_TYPE_SHOP_FROM_SEARCH = "shop_from_search";
    public static final String SOURCE_TYPE_TOPSALES = "topSales";
    public static final String SOURCE_TYPE_UNIFIED_RECOMMEND = "unifiedRecommend";
    public static final String SOURCE_TYPE_UNKNOWN = "unknown";
    public static final String SOURCE_TYPE_WEB_SITE = "shoppingCart_webSite";
    public static final String SOURCE_TYPE_WIDGET = "widget";
    private static final long serialVersionUID = 1460425843446045448L;
    private String sourceType;
    private String sourceValue;

    public SourceEntity() {
    }

    public static SourceEntity getMDestinationSource(String str) {
        if (str == null) {
            str = "";
        }
        return new SourceEntity(SOURCE_TYPE_PROM_M_DESTINATION_PAGE, str);
    }

    public static SourceEntity getOpenAppSourceEntity(Bundle bundle) {
        String string = bundle.getString("sourceType");
        String string2 = bundle.getString("sourceValue");
        if (TextUtils.isEmpty(string) && TextUtils.isEmpty(string2)) {
            return getMDestinationSource(bundle.getString("landPageId"));
        }
        return new SourceEntity(string, string2);
    }

    public String getSourceType() {
        return TextUtils.isEmpty(this.sourceType) ? "unknown" : this.sourceType;
    }

    public String getSourceValue() {
        return TextUtils.isEmpty(this.sourceValue) ? "" : this.sourceValue;
    }

    public void setSourceType(String str) {
        this.sourceType = str;
    }

    public void setSourceValue(String str) {
        this.sourceValue = str;
    }

    public String toString() {
        return "SourceEntity [sourceType=" + this.sourceType + ", sourceValue=" + this.sourceValue + "]";
    }

    public SourceEntity(String str, String str2) {
        this.sourceType = str;
        this.sourceValue = str2;
    }
}
