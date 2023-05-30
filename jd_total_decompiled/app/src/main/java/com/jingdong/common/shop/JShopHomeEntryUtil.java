package com.jingdong.common.shop;

import android.content.Context;
import android.os.Bundle;
import com.jd.framework.json.JDJSON;
import com.jingdong.common.entity.SourceEntity;
import com.jingdong.common.shop.JShopHomeIntentBean;
import com.jingdong.jdsdk.constant.JshopConst;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class JShopHomeEntryUtil {
    public static final String JSHOP_HOME_TAB_ALL_PRODUCT = "allProduct";
    public static final String JSHOP_HOME_TAB_DYNAMIC = "dynamic";
    public static final String JSHOP_HOME_TAB_HOME = "home";
    public static final String JSHOP_HOME_TAB_NEW = "new";
    public static final String JSHOP_HOME_TAB_PROMOTION = "promotion";

    public static Bundle getBundle(Context context, String str, String str2, String str3, String str4, SourceEntity sourceEntity) {
        JShopHomeIntentBean jShopHomeIntentBean = new JShopHomeIntentBean();
        jShopHomeIntentBean.shopId = str;
        jShopHomeIntentBean.shopName = str3;
        jShopHomeIntentBean.venderId = str2;
        jShopHomeIntentBean.targetPage = str4;
        return getBundle(context, sourceEntity, jShopHomeIntentBean);
    }

    public static Bundle getBundleWithRecommendProduct(Context context, String str, String str2, String str3, String str4, String str5, String str6, SourceEntity sourceEntity) {
        return getBundleWithRecommendProduct(context, str, str2, str3, str4, str5, str6, "", sourceEntity);
    }

    public static Bundle getBundleWithSortSkus(Context context, String str, String str2, String str3, String str4, List<String> list, SourceEntity sourceEntity) {
        JShopHomeIntentBean jShopHomeIntentBean = new JShopHomeIntentBean();
        jShopHomeIntentBean.shopId = str;
        jShopHomeIntentBean.shopName = str3;
        jShopHomeIntentBean.venderId = str2;
        jShopHomeIntentBean.targetPage = "allProduct";
        JShopHomeIntentBean.AllProductSort allProductSort = new JShopHomeIntentBean.AllProductSort();
        allProductSort.sku = str4;
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(list);
        if (arrayList.contains(str4)) {
            arrayList.remove(str4);
        }
        allProductSort.skuArray = arrayList;
        jShopHomeIntentBean.setAllProductSort(allProductSort);
        return getBundle(context, sourceEntity, jShopHomeIntentBean);
    }

    private static JSONObject getJSON(JShopHomeIntentBean jShopHomeIntentBean) {
        JSONObject jSONObject = new JSONObject();
        if (jShopHomeIntentBean != null) {
            try {
                return new JSONObject(JDJSON.toJSONString(jShopHomeIntentBean));
            } catch (Exception unused) {
                return jSONObject;
            }
        }
        return jSONObject;
    }

    public static Bundle getBundleWithRecommendProduct(Context context, String str, String str2, String str3, String str4, String str5, String str6, String str7, SourceEntity sourceEntity) {
        JShopHomeIntentBean jShopHomeIntentBean = new JShopHomeIntentBean();
        jShopHomeIntentBean.shopId = str;
        jShopHomeIntentBean.shopName = str3;
        jShopHomeIntentBean.venderId = str2;
        jShopHomeIntentBean.targetPage = "home";
        JShopHomeIntentBean.FloatProduct floatProduct = new JShopHomeIntentBean.FloatProduct();
        floatProduct.cateId = str5;
        floatProduct.cateName = str6;
        floatProduct.sku = str4;
        floatProduct.abTest = str7;
        jShopHomeIntentBean.setFloatProduct(floatProduct);
        return getBundle(context, sourceEntity, jShopHomeIntentBean);
    }

    private static Bundle getBundle(Context context, SourceEntity sourceEntity, JShopHomeIntentBean jShopHomeIntentBean) {
        JSONObject json = getJSON(jShopHomeIntentBean);
        Bundle bundle = new Bundle();
        bundle.putSerializable("source", sourceEntity);
        bundle.putString(JshopConst.INTENT_BRAND_JSON, json.toString());
        return bundle;
    }
}
