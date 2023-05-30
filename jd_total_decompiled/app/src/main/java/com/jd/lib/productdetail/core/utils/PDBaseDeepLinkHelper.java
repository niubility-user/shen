package com.jd.lib.productdetail.core.utils;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.jd.framework.json.JDJSONObject;
import com.jd.lib.productdetail.core.entitys.warebusiness.AddCartParamsEntity;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.MBaseKeyNames;
import com.jingdong.common.cart.CartCommonUtil;
import com.jingdong.common.controller.ShoppingBaseController;
import com.jingdong.common.deeplinkhelper.DeepLinkCommonHelper;
import com.jingdong.common.deeplinkhelper.DeepLinkCommuneHelper;
import com.jingdong.common.deeplinkhelper.DeepLinkFavouritesHelper;
import com.jingdong.common.deeplinkhelper.DeepLinkFillOrderHelper;
import com.jingdong.common.deeplinkhelper.DeepLinkProductListHelper;
import com.jingdong.common.deeplinkhelper.DeepLinkRankHelper;
import com.jingdong.common.deeplinkhelper.imhelper.DDParameterBuilder;
import com.jingdong.common.deeplinkhelper.imhelper.DeeplinkDongDongHelper;
import com.jingdong.common.deeplinkhelper.imhelper.DeeplinkJimiHelper;
import com.jingdong.common.deeplinkhelper.imhelper.JimiParameterBuilder;
import com.jingdong.common.entity.ProductToJsNowBuyEntity;
import com.jingdong.common.entity.ShareInfo;
import com.jingdong.common.entity.cart.CartResponse;
import com.jingdong.common.entity.cart.CartSkuSummary;
import com.jingdong.common.entity.cart.NewGiftItem;
import com.jingdong.common.entity.cart.SubmitOrderProductInfo;
import com.jingdong.common.entity.cart.methodEntity.CartAddFullEntity;
import com.jingdong.common.entity.cart.methodEntity.CartAddUniformEntity;
import com.jingdong.common.entity.settlement.FillOrder;
import com.jingdong.common.jdreactFramework.JDReactConstant;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.common.recommend.RecommendMtaUtils;
import com.jingdong.common.search.ProductListConstant;
import com.jingdong.common.unification.router.CallBackListener;
import com.jingdong.common.unification.router.JDRouter;
import com.jingdong.common.unification.router.JDRouterMtaUtil;
import com.jingdong.common.unification.router.JDRouterUrlBuilder;
import com.jingdong.common.unification.router.JDRouterUtil;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.constant.JshopConst;
import com.jingdong.jdsdk.utils.SerializableContainer;
import com.jingdong.jdsdk.utils.URLParamMap;
import com.jingdong.sdk.oklog.OKLog;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public abstract class PDBaseDeepLinkHelper {
    private static final String TAG = "PDBaseDeepLinkHelper";

    public static void addCart(ArrayList<CartSkuSummary> arrayList, final BaseActivity baseActivity, final PdAddShopCartCallback pdAddShopCartCallback) {
        CartAddUniformEntity cartAddUniformEntity = new CartAddUniformEntity();
        cartAddUniformEntity.myActivity = baseActivity;
        cartAddUniformEntity.skuList = arrayList;
        cartAddUniformEntity.isNoResponse = true;
        cartAddUniformEntity.isNotify = false;
        cartAddUniformEntity.isEffect = true;
        cartAddUniformEntity.listener = new ShoppingBaseController.ShoppingSingleListener() { // from class: com.jd.lib.productdetail.core.utils.PDBaseDeepLinkHelper.3
            @Override // com.jingdong.common.controller.ShoppingBaseController.ShoppingSingleListener, com.jingdong.common.controller.ShoppingBaseController.ShoppingListener
            public void onEnd(CartResponse cartResponse) {
                super.onEnd(cartResponse);
                PDBaseDeepLinkHelper.handleShoppingCartResult(baseActivity, cartResponse);
                PdAddShopCartCallback pdAddShopCartCallback2 = pdAddShopCartCallback;
                if (pdAddShopCartCallback2 != null) {
                    pdAddShopCartCallback2.addShopCartCallback(true);
                }
            }

            @Override // com.jingdong.common.controller.ShoppingBaseController.ShoppingSingleListener, com.jingdong.common.controller.ShoppingBaseController.ShoppingListener
            public void onError(String str) {
                super.onError(str);
                PdAddShopCartCallback pdAddShopCartCallback2 = pdAddShopCartCallback;
                if (pdAddShopCartCallback2 != null) {
                    pdAddShopCartCallback2.addShopCartCallback(false);
                }
            }

            @Override // com.jingdong.common.controller.ShoppingBaseController.ShoppingSingleListener, com.jingdong.common.controller.ShoppingBaseController.ShoppingListener
            public void onReady() {
                super.onReady();
            }
        };
        ShoppingBaseController.addCartUniform(cartAddUniformEntity);
    }

    public static void goToMWithUrlShareInfo(Context context, String str, ShareInfo shareInfo) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        URLParamMap uRLParamMap = new URLParamMap();
        uRLParamMap.put(RemoteMessageConst.TO, str);
        Bundle bundle = new Bundle();
        SerializableContainer serializableContainer = new SerializableContainer();
        serializableContainer.setMap(uRLParamMap);
        bundle.putSerializable("urlParamMap", serializableContainer);
        bundle.putString("urlAction", RemoteMessageConst.TO);
        bundle.putBoolean(MBaseKeyNames.IS_USE_RIGHT_BUTTON, false);
        bundle.putBoolean(MBaseKeyNames.IS_NEED_SHARE, true);
        bundle.putParcelable("shareInfo", shareInfo);
        DeepLinkCommonHelper.startWebActivity(context, bundle, true);
    }

    public static void goToRankHotListActivity(Context context, String str, String str2, String str3, String str4, String str5, String str6) {
        if (Log.D) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("RankingListActivity\u5165\u53e3\u53c2\u6570---------->\nskuId:");
            stringBuffer.append(str);
            stringBuffer.append("\ncateId:");
            stringBuffer.append(str2);
            stringBuffer.append("\ncateList:");
            stringBuffer.append(str3);
            stringBuffer.append("\ncateName:");
            stringBuffer.append(str4);
            stringBuffer.append("\nfrom:");
            stringBuffer.append(str5);
            stringBuffer.append("\nrankId:");
            stringBuffer.append(str6);
            Log.d("RankingController", stringBuffer.toString());
        }
        DeepLinkRankHelper.startRankHotListActivity(context, str, str2, str3, str4, str5, str6);
    }

    public static void goToRankHotOpenApp(Context context, String str, String str2) {
        StringBuilder sb = new StringBuilder();
        sb.append("openapp.jdmobile://virtual?params=");
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("category", "jump");
            jSONObject.put("des", JumpUtil.VALUE_DES_JDREACT_COMMON);
            jSONObject.put(JDReactConstant.IntentConstant.MODULE_NAME, "JDReactRankingList");
            jSONObject.put("appname", "JDReactRankingList");
            jSONObject.put("ishidden", true);
            jSONObject.put("fromName", DeepLinkRankHelper.PRODUCTDETAIL);
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("rankId", DeepLinkRankHelper.RANK_MAIN_FLOOR_TYPE_HOT);
            jSONObject2.put("categoryIds", str);
            jSONObject2.put("isLandingPage", true);
            jSONObject2.put("skuId", str2);
            jSONObject.put("param", jSONObject2);
            sb.append(jSONObject);
            if (OKLog.D) {
                OKLog.d("OPENAPPTAG", "urlStrBuff:" + ((Object) sb));
            }
            OpenAppUtils.openAppForInner(context, sb.toString());
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    public static void gotoMWithUrl(Context context, String str) {
        gotoMWithUrl(context, null, str);
    }

    public static void handleShoppingCartResult(BaseActivity baseActivity, CartResponse cartResponse) {
        CartAddFullEntity cartAddFullEntity = new CartAddFullEntity();
        cartAddFullEntity.activity = baseActivity;
        cartAddFullEntity.cartResponse = cartResponse;
        cartAddFullEntity.isHandleSuccess = true;
        cartAddFullEntity.isHandleFull = true;
        cartAddFullEntity.isHandleElse = true;
        cartAddFullEntity.isShowSuccessToast = true;
        cartAddFullEntity.source = "shangxiang";
        CartCommonUtil.cartFull(cartAddFullEntity);
    }

    public static void startBuyAskWihtDeepLink(Context context, String str) {
        Bundle bundle = new Bundle();
        bundle.putString(DeepLinkCommuneHelper.PRODUCT_ID, str);
        bundle.putString("sourceType", "detail");
        DeepLinkCommuneHelper.startCommuneList(context, bundle);
    }

    public static void startBuyAskWithRouter(final Context context, final String str, String str2) {
        if (JDRouterUtil.isRouterJump()) {
            final String build = new JDRouterUrlBuilder("JDCommuneModule").putStringParam("subDes", DeepLinkCommuneHelper.HOST_COMMUNE_LIST).putStringParam("sku", str).putStringParam("biData", str2).putStringParam("sourceType", "detail").build();
            JDRouter.build(context, build).callBackListener(new CallBackListener() { // from class: com.jd.lib.productdetail.core.utils.PDBaseDeepLinkHelper.2
                @Override // com.jingdong.common.unification.router.CallBackListener
                public void onComplete() {
                }

                @Override // com.jingdong.common.unification.router.CallBackListener
                public void onError(int i2) {
                    JDRouterMtaUtil.routerErrorMta(context, RecommendMtaUtils.Productdetail_MainPage, build + CartConstant.KEY_YB_INFO_LINK + i2);
                    PDBaseDeepLinkHelper.startBuyAskWihtDeepLink(context, str);
                }
            }).open();
            JDRouterMtaUtil.routerEnterMta(context, RecommendMtaUtils.Productdetail_MainPage, "JDCommuneModule");
            return;
        }
        startBuyAskWihtDeepLink(context, str);
    }

    public static void startDongDong(Context context, String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        startDongDong(context, str, str2, str3, str4, str5, str6, null, str7);
    }

    public static void startFavouritesActivity(BaseActivity baseActivity, String str, int i2) {
        Bundle bundle = new Bundle();
        bundle.putString("title", str);
        bundle.putInt(DeepLinkFavouritesHelper.CURRENT_TAB, i2);
        DeepLinkFavouritesHelper.startFavouritesActivity(baseActivity, bundle);
    }

    public static void startFillOrder(Context context, String str, int i2, String str2, ArrayList<String> arrayList, ArrayList<String> arrayList2, FillOrder fillOrder, int i3, Bundle bundle) {
        DeepLinkFillOrderHelper.startFillOrder(context, str, i2, str2, arrayList, arrayList2, fillOrder, 2, i3, bundle);
    }

    public static void startFillOrderWithRouter(Context context, String str, String str2, int i2, String str3, ArrayList<String> arrayList, ArrayList<NewGiftItem> arrayList2, ArrayList<String> arrayList3, ArrayList<String> arrayList4, FillOrder fillOrder, int i3, String str4, List<ProductToJsNowBuyEntity> list, String str5) {
        startFillOrderWithRouter(context, str, str2, i2, str3, arrayList, arrayList2, arrayList3, arrayList4, fillOrder, i3, str4, list, str5, new Bundle());
    }

    public static void startGiftShoppingActivity(Context context, String str, ArrayList<String> arrayList) {
        Bundle bundle = new Bundle();
        bundle.putString("selectedDeliveryId", str);
        bundle.putStringArrayList("selected3cGiftPoolsId", arrayList);
        DeepLinkCommonHelper.startActivityDirect(context, DeepLinkCommonHelper.HOST_GIFT_SHOPPING_ACTIVITY, bundle);
    }

    public static void startImageActivity(Context context, int i2, int i3, ArrayList<String> arrayList) {
        startImageActivity(context, i2, i3, arrayList, null);
    }

    public static void startJDMobileChannelActivity(Context context, String str) {
        Bundle bundle = new Bundle();
        bundle.putString("skuId", str);
        DeepLinkCommonHelper.startActivityDirect(context, DeepLinkCommonHelper.HOST_JDMOBILE_CHANNEL_ACTIVITY, bundle);
    }

    public static void startJimi(Context context, String str, String str2, String str3) {
        JimiParameterBuilder generateWithPin = JimiParameterBuilder.generateWithPin();
        if (generateWithPin == null) {
            return;
        }
        generateWithPin.addSource(str).addSkuID(str2).addAction(JimiParameterBuilder.ACTION_BROADCAST_START_PLUGIN_JIMI);
        if (str3 != null) {
            generateWithPin.addVenderID(str3);
        }
        DeeplinkJimiHelper.getInstance().startJimiActivity(context, generateWithPin.getAllParameters());
    }

    public static void startProductListActivity(Context context, String str, String str2) {
        Bundle bundle = new Bundle();
        bundle.putString(str, str2);
        bundle.putString(JshopConst.JSHOP_SEARCH_KEYWORD, str2);
        bundle.putInt("sortKey", -2);
        bundle.putInt("com.360buy:navigationDisplayFlag", -1);
        DeepLinkCommonHelper.startActivityDirect(context, DeepLinkCommonHelper.HOST_PRODUCT_LIST, bundle);
    }

    public static void startReturnCouponDeepLink(Context context, String str) {
        Bundle bundle = new Bundle();
        bundle.putString("rebateId", str);
        bundle.putInt(ProductListConstant.INLET, 0);
        DeepLinkCommonHelper.startActivityDirect(context, DeepLinkCommonHelper.HOST_CONVERGE_LIST, bundle);
    }

    public static void startSearchActivity(BaseActivity baseActivity) {
        DeepLinkCommonHelper.startActivityDirect(baseActivity, DeepLinkCommonHelper.HOST_SEARCH_ACTIVITY, null);
    }

    public static void gotoMWithUrl(Context context, String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        URLParamMap uRLParamMap = new URLParamMap();
        uRLParamMap.put(RemoteMessageConst.TO, str2);
        Bundle bundle = new Bundle();
        SerializableContainer serializableContainer = new SerializableContainer();
        serializableContainer.setMap(uRLParamMap);
        bundle.putSerializable("urlParamMap", serializableContainer);
        bundle.putString("urlAction", RemoteMessageConst.TO);
        if (!TextUtils.isEmpty(str)) {
            bundle.putString("locUrl", str);
        }
        DeepLinkCommonHelper.startWebActivity(context, bundle, true);
    }

    public static void startDongDong(Context context, String str, String str2, String str3, String str4, String str5, String str6) {
        startDongDong(context, str, str2, str3, str4, str5, str6, null, null);
    }

    public static void startFillOrder(Context context, String str, int i2, int i3, int i4, String str2, int i5, long j2, String str3, int i6, Bundle bundle) {
        DeepLinkFillOrderHelper.startFillOrder(context, str, 0, "", 0, "", "", null, null, i2, i3, i4, str2, i5, j2, str3, FillOrder.ISREGULARBUY, 2, i6, bundle);
    }

    public static void startImageActivity(Context context, int i2, int i3, ArrayList<String> arrayList, ArrayList<String> arrayList2) {
        Bundle bundle = new Bundle();
        bundle.putInt("position", i2);
        bundle.putInt("clickTimes", i3);
        bundle.putBoolean("isFromPD", true);
        bundle.putStringArrayList("image_show_list_url", arrayList);
        if (arrayList2 != null) {
            bundle.putStringArrayList("image_3d_show_list_url", arrayList2);
        }
        DeepLinkCommonHelper.startActivityDirect(context, DeepLinkCommonHelper.HOST_IMAGE_ACTIVITY, bundle);
    }

    public static void startDongDong(Context context, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8) {
        DDParameterBuilder generateWithPin = DDParameterBuilder.generateWithPin();
        if (generateWithPin == null) {
            return;
        }
        generateWithPin.addFrom(DDParameterBuilder.ACTION_BROADCAST_PRODUCT_ASK).addSkuID(str).addProductName(str2).addProductPrice(str3).addProductImageUrl(str4).addVenderID(str7).addEntry(str6).addBbtf(str8);
        DeeplinkDongDongHelper.getInstance().startDongDong(context, generateWithPin.getBundle());
    }

    public static void startFillOrder(Context context, String str, int i2, String str2, int i3, String str3, String str4, ArrayList<String> arrayList, ArrayList<String> arrayList2, FillOrder fillOrder, int i4, Bundle bundle) {
        DeepLinkFillOrderHelper.startFillOrder(context, str, i2, str2, i3, str3, str4, arrayList, arrayList2, 0, 0, 0, "", 0, 0L, "", fillOrder, 2, i4, bundle);
    }

    public static void startFillOrderWithRouter(final Context context, String str, final String str2, final int i2, final String str3, final ArrayList<String> arrayList, ArrayList<NewGiftItem> arrayList2, final ArrayList<String> arrayList3, ArrayList<String> arrayList4, final FillOrder fillOrder, final int i3, String str4, List<ProductToJsNowBuyEntity> list, String str5, final Bundle bundle) {
        bundle.putString(DeepLinkFillOrderHelper.LIVE_SOURCE_PT_KEY, str4);
        if (arrayList2 != null && !arrayList2.isEmpty()) {
            bundle.putParcelableArrayList("newGiftItems", arrayList2);
        }
        if (!TextUtils.isEmpty(str5)) {
            bundle.putString(SubmitOrderProductInfo.KEY_ADDITIONALDATA_HASSELECTEDOTC, str5);
            bundle.putString("otcMergeSwitch", "1");
        }
        if (!TextUtils.isEmpty(str)) {
            bundle.putString(CartConstant.KEY_EXTFLAG, str);
        }
        if (JDRouterUtil.isRouterJump()) {
            DeepLinkFillOrderHelper.Builder put = DeepLinkFillOrderHelper.Builder.create().put("wareId", str2).put("wareNum", Integer.valueOf(i2)).put(CartConstant.KEY_DELIVERYID, str3).put("skuSource", Integer.valueOf(i3)).put(DeepLinkFillOrderHelper.LIVE_SOURCE_PT_KEY, str4).put("orderType", Integer.valueOf(DeepLinkFillOrderHelper.getOrderType(fillOrder))).put("sourceType", 2);
            if (!TextUtils.isEmpty(str)) {
                put.put(CartConstant.KEY_EXTFLAG, str);
            }
            if (!TextUtils.isEmpty(str5)) {
                put.put(SubmitOrderProductInfo.KEY_ADDITIONALDATA_HASSELECTEDOTC, str5);
                put.put("otcMergeSwitch", "1");
            }
            final String url = put.toURL();
            JDRouter.build(context, url).extraObject("3cGiftPoolsId", arrayList).extraObject("newGiftItems", arrayList2).extraObject("ybList", arrayList3).extraObject("jdHomeServiceList", arrayList4).extraObject("atmosphereList", list).extraObject("isPopupOrder", Boolean.valueOf(bundle.getBoolean("isPopupOrder", false))).extraObject("layerType", bundle.getString("layerType")).extraObject("popUpOrderColorData", bundle.getString("popUpOrderColorData")).callBackListener(new CallBackListener() { // from class: com.jd.lib.productdetail.core.utils.PDBaseDeepLinkHelper.1
                @Override // com.jingdong.common.unification.router.CallBackListener
                public void onComplete() {
                }

                @Override // com.jingdong.common.unification.router.CallBackListener
                public void onError(int i4) {
                    JDRouterMtaUtil.routerErrorMta(context, RecommendMtaUtils.Productdetail_MainPage, url + CartConstant.KEY_YB_INFO_LINK + i4);
                    DeepLinkFillOrderHelper.startFillOrder(context, str2, i2, str3, arrayList, arrayList3, fillOrder, 2, i3, bundle);
                }
            }).open();
            JDRouterMtaUtil.routerEnterMta(context, RecommendMtaUtils.Productdetail_MainPage, "JDCheckoutModule");
            return;
        }
        DeepLinkFillOrderHelper.startFillOrder(context, str2, i2, str3, arrayList, arrayList3, fillOrder, 2, i3, bundle);
    }

    public static void startProductListActivity(String str, String str2, Context context, String str3, String str4, String str5, String str6, String str7, String str8) {
        Bundle bundle = new Bundle();
        bundle.putString("activityId", str3);
        bundle.putString("skuId", str4);
        bundle.putString("tip", str5);
        bundle.putInt(ProductListConstant.INLET, 0);
        bundle.putString("skuCid2", str7);
        bundle.putString("skuCid3", str8);
        JDJSONObject jDJSONObject = new JDJSONObject();
        jDJSONObject.put("entry_sku", (Object) str4);
        jDJSONObject.put("entry_cid3", (Object) str8);
        jDJSONObject.put("entry_cid2", (Object) str7);
        jDJSONObject.put("entry_cid1", (Object) str6);
        jDJSONObject.put("entry_shop", (Object) str);
        jDJSONObject.put("entry_brand", (Object) str2);
        bundle.putString("passThroughMap", jDJSONObject.toJSONString());
        DeepLinkCommonHelper.startActivityDirect(context, DeepLinkCommonHelper.HOST_CONVERGE_LIST, bundle);
    }

    public static void addCart(ArrayList<CartSkuSummary> arrayList, final BaseActivity baseActivity, final PdAddShopCartCallback pdAddShopCartCallback, AddCartParamsEntity addCartParamsEntity) {
        CartAddUniformEntity cartAddUniformEntity = new CartAddUniformEntity();
        cartAddUniformEntity.myActivity = baseActivity;
        cartAddUniformEntity.skuList = arrayList;
        cartAddUniformEntity.isNoResponse = true;
        cartAddUniformEntity.isNotify = false;
        cartAddUniformEntity.isEffect = true;
        if (addCartParamsEntity != null && !TextUtils.isEmpty(addCartParamsEntity.businessName)) {
            cartAddUniformEntity.businessName = addCartParamsEntity.businessName;
        }
        cartAddUniformEntity.listener = new ShoppingBaseController.ShoppingSingleListener() { // from class: com.jd.lib.productdetail.core.utils.PDBaseDeepLinkHelper.4
            @Override // com.jingdong.common.controller.ShoppingBaseController.ShoppingSingleListener, com.jingdong.common.controller.ShoppingBaseController.ShoppingListener
            public void onEnd(CartResponse cartResponse) {
                super.onEnd(cartResponse);
                PDBaseDeepLinkHelper.handleShoppingCartResult(baseActivity, cartResponse);
                PdAddShopCartCallback pdAddShopCartCallback2 = pdAddShopCartCallback;
                if (pdAddShopCartCallback2 != null) {
                    pdAddShopCartCallback2.addShopCartCallback(true);
                }
            }

            @Override // com.jingdong.common.controller.ShoppingBaseController.ShoppingSingleListener, com.jingdong.common.controller.ShoppingBaseController.ShoppingListener
            public void onError(String str) {
                super.onError(str);
                PdAddShopCartCallback pdAddShopCartCallback2 = pdAddShopCartCallback;
                if (pdAddShopCartCallback2 != null) {
                    pdAddShopCartCallback2.addShopCartCallback(false);
                }
            }

            @Override // com.jingdong.common.controller.ShoppingBaseController.ShoppingSingleListener, com.jingdong.common.controller.ShoppingBaseController.ShoppingListener
            public void onReady() {
                super.onReady();
            }
        };
        ShoppingBaseController.addCartUniform(cartAddUniformEntity);
    }

    public static void startProductListActivity(Context context, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11) {
        Bundle bundle = new Bundle();
        bundle.putString("activityId", str);
        bundle.putString("skuId", str2);
        bundle.putString("tip", str3);
        bundle.putInt(ProductListConstant.INLET, 0);
        bundle.putString(DeepLinkCommuneHelper.PRODUCT_IMAGE, str4);
        bundle.putString("productTitle", str5);
        bundle.putString("productPrice", str6);
        bundle.putString("skuCid2", str8);
        bundle.putString("skuCid3", str9);
        JDJSONObject jDJSONObject = new JDJSONObject();
        jDJSONObject.put("entry_sku", (Object) str2);
        jDJSONObject.put("entry_cid3", (Object) str9);
        jDJSONObject.put("entry_cid2", (Object) str8);
        jDJSONObject.put("entry_cid1", (Object) str7);
        jDJSONObject.put("entry_shop", (Object) str10);
        jDJSONObject.put("entry_brand", (Object) str11);
        bundle.putString("passThroughMap", jDJSONObject.toJSONString());
        DeepLinkProductListHelper.startPurchaseListActivity(context, bundle);
    }

    public static void startProductListActivity(Context context, int i2, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8) {
        Bundle bundle = new Bundle();
        bundle.putString("skuId", str6);
        bundle.putInt(ProductListConstant.INLET, i2);
        bundle.putString("tip", str);
        bundle.putString("CouponbatchID", str2);
        bundle.putString("skuCid2", str4);
        bundle.putString("skuCid3", str5);
        JDJSONObject jDJSONObject = new JDJSONObject();
        jDJSONObject.put("entry_sku", (Object) str6);
        jDJSONObject.put("entry_cid3", (Object) str5);
        jDJSONObject.put("entry_cid2", (Object) str4);
        jDJSONObject.put("entry_cid1", (Object) str3);
        jDJSONObject.put("entry_shop", (Object) str7);
        jDJSONObject.put("entry_brand", (Object) str8);
        bundle.putString("passThroughMap", jDJSONObject.toJSONString());
        DeepLinkCommonHelper.startActivityDirect(context, DeepLinkCommonHelper.HOST_CONVERGE_LIST, bundle);
    }
}
