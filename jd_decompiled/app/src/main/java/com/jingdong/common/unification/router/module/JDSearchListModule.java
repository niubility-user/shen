package com.jingdong.common.unification.router.module;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.jd.framework.json.JDJSON;
import com.jd.lib.productdetail.core.protocol.PdLVBody;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.deeplinkhelper.DeepLinkCommonHelper;
import com.jingdong.common.deeplinkhelper.DeepLinkProductListHelper;
import com.jingdong.common.entity.SourceEntity;
import com.jingdong.common.search.FilterConstant;
import com.jingdong.common.search.ProductListConstant;
import com.jingdong.common.search.entity.FilterItem;
import com.jingdong.common.search.entity.SearchInfo;
import com.jingdong.common.unification.router.CallBackListener;
import com.jingdong.common.unification.router.JDRouterUtil;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.constant.JshopConst;
import com.jingdong.sdk.log.Log;
import java.io.Serializable;
import java.util.List;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class JDSearchListModule implements IJDModule {
    @Override // com.jingdong.common.unification.router.module.IJDModule
    public void show(Context context, JSONObject jSONObject, Bundle bundle, CallBackListener callBackListener) {
        CallBackListener callBackListener2;
        if (context == null || jSONObject == null) {
            if (callBackListener != null) {
                JDRouterUtil.callBackError(callBackListener, 703);
                return;
            }
            return;
        }
        try {
            Bundle bundle2 = new Bundle();
            bundle2.putString("hintword", jSONObject.optString("hintword", ""));
            bundle2.putString("realword", jSONObject.optString("realword", ""));
            bundle2.putBoolean(DeepLinkProductListHelper.HINTWORDSAMEWITHMAIN, jSONObject.optBoolean(DeepLinkProductListHelper.HINTWORDSAMEWITHMAIN, true));
            if (!TextUtils.isEmpty(jSONObject.optString("channelName", ""))) {
                SearchInfo searchInfo = new SearchInfo();
                searchInfo.channelName = jSONObject.optString("channelName", "");
                searchInfo.channelTitle = jSONObject.optString("channelTitle", "");
                searchInfo.cids = jSONObject.optString("cids", "");
                searchInfo.fields = jSONObject.optString("fields", "");
                searchInfo.supermarket = jSONObject.optString("jdSupermarket", "");
                bundle2.putSerializable("searchinfo", searchInfo);
            }
            bundle2.putBoolean(DeepLinkProductListHelper.HASHIDDENAUDIOBUTTON, jSONObject.optBoolean(DeepLinkProductListHelper.HASHIDDENAUDIOBUTTON, false));
            bundle2.putString("version", jSONObject.optString("version", ""));
            bundle2.putString(JshopConst.JSHOP_SEARCH_KEYWORD, jSONObject.optString(JshopConst.JSHOP_SEARCH_KEYWORD, ""));
            bundle2.putBoolean(FilterConstant.IS_ALLWORLD_SHOPPING, jSONObject.optBoolean(FilterConstant.IS_ALLWORLD_SHOPPING, false));
            bundle2.putBoolean("global_from_channel", jSONObject.optBoolean("global_from_channel", false));
            bundle2.putString("promId", jSONObject.optString("promId", ""));
            bundle2.putString("promType", jSONObject.optString("promType", ""));
            bundle2.putString("tipUrlLink", jSONObject.optString("tipUrlLink", ""));
            bundle2.putString("searchFilterType", jSONObject.optString("searchFilterType", ""));
            bundle2.putInt("sortKey", jSONObject.optInt("sortKey"));
            bundle2.putString(ProductListConstant.SEARCH_TO_PRODUCTLIST_TAB_KEY, jSONObject.optString(ProductListConstant.SEARCH_TO_PRODUCTLIST_TAB_KEY, ""));
            bundle2.putSerializable("source", new SourceEntity(jSONObject.optString("sourceType", ""), jSONObject.optString("sourceValue", "")));
            DeepLinkCommonHelper.startActivityDirect(context, DeepLinkCommonHelper.HOST_PRODUCT_LIST, bundle2);
            callBackListener2 = callBackListener;
            if (callBackListener2 != null) {
                try {
                    callBackListener.onComplete();
                } catch (Exception unused) {
                    if (callBackListener2 != null) {
                        callBackListener2.onError(701);
                    }
                }
            }
        } catch (Exception unused2) {
            callBackListener2 = callBackListener;
        }
    }

    public void showAuthorBookList(Context context, JSONObject jSONObject, Bundle bundle, CallBackListener callBackListener) {
        if (context == null || jSONObject == null) {
            if (callBackListener != null) {
                JDRouterUtil.callBackError(callBackListener, 703);
                return;
            }
            return;
        }
        try {
            Bundle bundle2 = new Bundle();
            String optString = jSONObject.optString("fromAuthorDetail", "");
            bundle2.putString("author", optString);
            bundle2.putString(JshopConst.JSHOP_SEARCH_KEYWORD, optString);
            bundle2.putInt("sortKey", 0);
            DeepLinkCommonHelper.startActivityDirect(context, DeepLinkCommonHelper.HOST_PRODUCT_LIST, bundle2);
            if (callBackListener != null) {
                callBackListener.onComplete();
            }
        } catch (Exception unused) {
            if (callBackListener != null) {
                callBackListener.onError(701);
            }
        }
    }

    public void showCouponList(Context context, JSONObject jSONObject, Bundle bundle, CallBackListener callBackListener) {
        if (context == null || jSONObject == null) {
            if (callBackListener != null) {
                JDRouterUtil.callBackError(callBackListener, 703);
                return;
            }
            return;
        }
        try {
            Bundle bundle2 = new Bundle();
            bundle2.putString("version", jSONObject.optString("version", ""));
            bundle2.putString("CouponbatchID", jSONObject.optString("couponbatchID", ""));
            bundle2.putString("couponfrom", jSONObject.optString("couponfrom", ""));
            bundle2.putString("tip", jSONObject.optString("tip", ""));
            bundle2.putInt(ProductListConstant.INLET, jSONObject.optInt(ProductListConstant.INLET, -1));
            bundle2.putString(CartConstant.KEY_CART_STILL_NEED, jSONObject.optString(CartConstant.KEY_CART_STILL_NEED, ""));
            bundle2.putString("skuCid2", jSONObject.optString("skuCid2", ""));
            bundle2.putSerializable("source", new SourceEntity(jSONObject.optString("sourceType", ""), jSONObject.optString("sourceValue", "")));
            String optString = jSONObject.optString("passThroughMap");
            if (!TextUtils.isEmpty(optString)) {
                bundle2.putSerializable("passThroughMap", optString);
            }
            String optString2 = jSONObject.optString("clearPassThroughMap");
            if (!TextUtils.isEmpty(optString2)) {
                bundle2.putSerializable("clearPassThroughMap", optString2);
            }
            DeepLinkCommonHelper.startActivityDirect(context, DeepLinkCommonHelper.HOST_CONVERGE_LIST, bundle2);
            if (callBackListener != null) {
                callBackListener.onComplete();
            }
        } catch (Exception unused) {
            if (callBackListener != null) {
                callBackListener.onError(701);
            }
        }
    }

    public void showCrossCategoryList(Context context, JSONObject jSONObject, Bundle bundle, CallBackListener callBackListener) {
        if (context == null || jSONObject == null) {
            if (callBackListener != null) {
                JDRouterUtil.callBackError(callBackListener, 703);
                return;
            }
            return;
        }
        try {
            Bundle bundle2 = new Bundle();
            bundle2.putString("activityId", jSONObject.optString("activityId", ""));
            bundle2.putString("tipFirst", jSONObject.optString("tipFirst", ""));
            bundle2.putString("tipSecond", jSONObject.optString("tipSecond", ""));
            bundle2.putString(ProductListConstant.INLET, jSONObject.optString(ProductListConstant.INLET, ""));
            bundle2.putSerializable("source", new SourceEntity(jSONObject.optString("sourceType", ""), jSONObject.optString("sourceValue", "")));
            DeepLinkCommonHelper.startActivityDirect(context, DeepLinkCommonHelper.HOST_CROSSCATEGORY_LIST, bundle2);
            if (callBackListener != null) {
                callBackListener.onComplete();
            }
        } catch (Exception unused) {
            if (callBackListener != null) {
                callBackListener.onError(701);
            }
        }
    }

    public void showECardList(Context context, JSONObject jSONObject, Bundle bundle, CallBackListener callBackListener) {
        if (context == null || jSONObject == null) {
            if (callBackListener != null) {
                JDRouterUtil.callBackError(callBackListener, 703);
                return;
            }
            return;
        }
        try {
            Bundle bundle2 = new Bundle();
            bundle2.putString("version", jSONObject.optString("version", ""));
            bundle2.putString("eCardID", jSONObject.optString("eCardID", ""));
            bundle2.putString("tip", jSONObject.optString("tip", ""));
            bundle2.putInt(ProductListConstant.INLET, jSONObject.optInt(ProductListConstant.INLET, -1));
            bundle2.putSerializable("source", new SourceEntity(jSONObject.optString("sourceType", ""), jSONObject.optString("sourceValue", "")));
            DeepLinkCommonHelper.startActivityDirect(context, DeepLinkCommonHelper.HOST_CONVERGE_LIST, bundle2);
            if (callBackListener != null) {
                callBackListener.onComplete();
            }
        } catch (Exception unused) {
            if (callBackListener != null) {
                callBackListener.onError(701);
            }
        }
    }

    public void showHourReachSearchPage(Context context, JSONObject jSONObject, Bundle bundle, CallBackListener callBackListener) {
        if (context == null || jSONObject == null) {
            if (callBackListener != null) {
                JDRouterUtil.callBackError(callBackListener, 703);
                return;
            }
            return;
        }
        try {
            Bundle bundle2 = new Bundle();
            bundle2.putString("hintword", jSONObject.optString("showWord", ""));
            bundle2.putString("realword", jSONObject.optString("realWord", ""));
            if (!TextUtils.isEmpty(jSONObject.optString("channelName", ""))) {
                SearchInfo searchInfo = new SearchInfo();
                searchInfo.channelName = jSONObject.optString("channelName", "");
                searchInfo.channelTitle = jSONObject.optString("channelTitle", "");
                bundle2.putSerializable("searchinfo", searchInfo);
            }
            bundle2.putString("keyword", jSONObject.optString("keyword", ""));
            bundle2.putString(PdLVBody.LONGITUDE, jSONObject.optString(PdLVBody.LONGITUDE));
            bundle2.putString(PdLVBody.LATITUDE, jSONObject.optString(PdLVBody.LATITUDE));
            bundle2.putString("caller", jSONObject.optString("caller"));
            DeepLinkCommonHelper.startActivityDirect(context, DeepLinkCommonHelper.HOST_HOURSEARCH_ACTIVITY, bundle2);
            if (callBackListener != null) {
                callBackListener.onComplete();
            }
        } catch (Exception unused) {
            if (callBackListener != null) {
                callBackListener.onError(701);
            }
        }
    }

    public void showProductList(Context context, JSONObject jSONObject, Bundle bundle, CallBackListener callBackListener) {
        CallBackListener callBackListener2;
        List parseArray;
        List parseArray2;
        if (context == null || jSONObject == null) {
            if (callBackListener != null) {
                JDRouterUtil.callBackError(callBackListener, 703);
            }
        } else if (!TextUtils.isEmpty(jSONObject.optString("activityId", ""))) {
            showPromotionList(context, jSONObject, bundle, callBackListener);
        } else if (!TextUtils.isEmpty(jSONObject.optString("couponbatchID", ""))) {
            showCouponList(context, jSONObject, bundle, callBackListener);
        } else if (!TextUtils.isEmpty(jSONObject.optString("eCardID", ""))) {
            showECardList(context, jSONObject, bundle, callBackListener);
        } else {
            try {
                Bundle bundle2 = new Bundle();
                bundle2.putString("hintword", jSONObject.optString("hintword", ""));
                bundle2.putString("realword", jSONObject.optString("realword", ""));
                bundle2.putBoolean(DeepLinkProductListHelper.HINTWORDSAMEWITHMAIN, jSONObject.optBoolean(DeepLinkProductListHelper.HINTWORDSAMEWITHMAIN, true));
                if (!TextUtils.isEmpty(jSONObject.optString("channelName", ""))) {
                    SearchInfo searchInfo = new SearchInfo();
                    searchInfo.channelName = jSONObject.optString("channelName", "");
                    searchInfo.channelTitle = jSONObject.optString("channelTitle", "");
                    searchInfo.cids = jSONObject.optString("cids", "");
                    searchInfo.fields = jSONObject.optString("fields", "");
                    searchInfo.supermarket = jSONObject.optString("jdSupermarket", "");
                    searchInfo.filterConfigKeys = jSONObject.optString("filterConfigKeys", "");
                    bundle2.putSerializable("searchinfo", searchInfo);
                }
                bundle2.putBoolean(DeepLinkProductListHelper.HASHIDDENAUDIOBUTTON, jSONObject.optBoolean(DeepLinkProductListHelper.HASHIDDENAUDIOBUTTON, false));
                bundle2.putString("version", jSONObject.optString("version", ""));
                bundle2.putString(JshopConst.JSHOP_SEARCH_KEYWORD, jSONObject.optString(JshopConst.JSHOP_SEARCH_KEYWORD, ""));
                bundle2.putBoolean(FilterConstant.IS_ALLWORLD_SHOPPING, jSONObject.optBoolean(FilterConstant.IS_ALLWORLD_SHOPPING, false));
                bundle2.putBoolean("global_from_channel", jSONObject.optBoolean("global_from_channel", false));
                bundle2.putString("promId", jSONObject.optString("promId", ""));
                bundle2.putString("promType", jSONObject.optString("promType", ""));
                bundle2.putString("tipUrlLink", jSONObject.optString("tipUrlLink", ""));
                bundle2.putString("searchFilterType", jSONObject.optString("searchFilterType", ""));
                bundle2.putInt("sortKey", jSONObject.optInt("sortKey"));
                bundle2.putString(ProductListConstant.SEARCH_TO_PRODUCTLIST_TAB_KEY, jSONObject.optString(ProductListConstant.SEARCH_TO_PRODUCTLIST_TAB_KEY, ""));
                bundle2.putString(ProductListConstant.PRODUCT_LIST_SEARCH_SOURCE, bundle2.getString(ProductListConstant.PRODUCT_LIST_SEARCH_SOURCE, ""));
                bundle2.putSerializable("source", new SourceEntity(jSONObject.optString("sourceType", ""), jSONObject.optString("sourceValue", "")));
                String optString = jSONObject.optString("bodyMaps", "");
                if (!TextUtils.isEmpty(optString) && (parseArray2 = JDJSON.parseArray(optString, FilterItem.class)) != null && !parseArray2.isEmpty()) {
                    bundle2.putSerializable("bodyMaps", (Serializable) parseArray2);
                }
                String optString2 = jSONObject.optString("clearBodyMaps", "");
                if (!TextUtils.isEmpty(optString2) && (parseArray = JDJSON.parseArray(optString2, FilterItem.class)) != null && !parseArray.isEmpty()) {
                    bundle2.putSerializable("clearBodyMaps", (Serializable) parseArray);
                }
                bundle2.putBoolean(BaseActivity.DISPOSEABLE_UNABLE_ANIM, jSONObject.optBoolean(BaseActivity.DISPOSEABLE_UNABLE_ANIM, false));
                DeepLinkCommonHelper.startActivityDirect(context, DeepLinkCommonHelper.HOST_PRODUCT_LIST, bundle2);
                callBackListener2 = callBackListener;
                if (callBackListener2 != null) {
                    try {
                        callBackListener.onComplete();
                    } catch (Exception unused) {
                        if (callBackListener2 != null) {
                            callBackListener2.onError(701);
                        }
                    }
                }
            } catch (Exception unused2) {
                callBackListener2 = callBackListener;
            }
        }
    }

    public void showPromotionList(Context context, JSONObject jSONObject, Bundle bundle, CallBackListener callBackListener) {
        if (context == null || jSONObject == null) {
            if (callBackListener != null) {
                JDRouterUtil.callBackError(callBackListener, 703);
                return;
            }
            return;
        }
        try {
            Bundle bundle2 = new Bundle();
            bundle2.putString("version", jSONObject.optString("version", ""));
            bundle2.putString("activityId", jSONObject.optString("activityId", ""));
            bundle2.putString("tip", jSONObject.optString("tip", ""));
            bundle2.putInt(ProductListConstant.INLET, jSONObject.optInt(ProductListConstant.INLET, -1));
            bundle2.putString("skuId", jSONObject.optString("skuId", ""));
            bundle2.putString(CartConstant.KEY_CART_STILL_NEED, jSONObject.optString(CartConstant.KEY_CART_STILL_NEED, ""));
            bundle2.putString("skuCid2", jSONObject.optString("skuCid2", ""));
            bundle2.putSerializable("source", new SourceEntity(jSONObject.optString("sourceType", ""), jSONObject.optString("sourceValue", "")));
            if (Log.D) {
                Log.d(JDSearchListModule.class.getName(), "jsonParam:" + jSONObject);
            }
            String optString = jSONObject.optString("passThroughMap");
            if (!TextUtils.isEmpty(optString)) {
                bundle2.putSerializable("passThroughMap", optString);
            }
            String optString2 = jSONObject.optString("clearPassThroughMap");
            if (!TextUtils.isEmpty(optString2)) {
                bundle2.putSerializable("clearPassThroughMap", optString2);
            }
            DeepLinkCommonHelper.startActivityDirect(context, DeepLinkCommonHelper.HOST_CONVERGE_LIST, bundle2);
            if (callBackListener != null) {
                callBackListener.onComplete();
            }
        } catch (Exception unused) {
            if (callBackListener != null) {
                callBackListener.onError(701);
            }
        }
    }

    public void showPublisherBookList(Context context, JSONObject jSONObject, Bundle bundle, CallBackListener callBackListener) {
        if (context == null || jSONObject == null) {
            if (callBackListener != null) {
                JDRouterUtil.callBackError(callBackListener, 703);
                return;
            }
            return;
        }
        try {
            Bundle bundle2 = new Bundle();
            String optString = jSONObject.optString("fromPublisherDetail", "");
            bundle2.putString(FilterConstant.SELECT_KEY_PUBLISHERS, optString);
            bundle2.putString(JshopConst.JSHOP_SEARCH_KEYWORD, optString);
            bundle2.putInt("sortKey", 0);
            DeepLinkCommonHelper.startActivityDirect(context, DeepLinkCommonHelper.HOST_PRODUCT_LIST, bundle2);
            if (callBackListener != null) {
                callBackListener.onComplete();
            }
        } catch (Exception unused) {
            if (callBackListener != null) {
                callBackListener.onError(701);
            }
        }
    }
}
