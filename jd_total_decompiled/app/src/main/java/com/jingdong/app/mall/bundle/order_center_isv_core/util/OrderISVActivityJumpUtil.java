package com.jingdong.app.mall.bundle.order_center_isv_core.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.bundle.order_center_isv_core.bean.OrderISVRefundProgressFlutterOpenAppBean;
import com.jingdong.app.mall.bundle.order_center_isv_core.param.OrderDetailISVJumpParam;
import com.jingdong.app.mall.bundle.order_center_isv_core.param.OrderISVRedirectProtocol;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.MBaseKeyNames;
import com.jingdong.common.deeplinkhelper.DeepLinkCommonHelper;
import com.jingdong.common.deeplinkhelper.DeepLinkEvaluateCenterHelper;
import com.jingdong.common.deeplinkhelper.DeepLinkFillOrderHelper;
import com.jingdong.common.deeplinkhelper.DeepLinkOrderCenterHelper;
import com.jingdong.common.deeplinkhelper.DeeplinkProductDetailHelper;
import com.jingdong.common.entity.ShareInfo;
import com.jingdong.common.entity.SourceEntity;
import com.jingdong.common.impl.UtilFactory;
import com.jingdong.common.jdreactFramework.JDReactAuraHelper;
import com.jingdong.common.jump.OpenAppConstant;
import com.jingdong.common.jump.OpenAppJumpBuilder;
import com.jingdong.common.messagecenter.NotificationMessageSummary;
import com.jingdong.common.protocol.parse.IJsonParse;
import com.jingdong.common.unification.router.CallBackListener;
import com.jingdong.common.unification.router.JDRouter;
import com.jingdong.common.unification.router.JDRouterMtaUtil;
import com.jingdong.common.unification.router.JDRouterUrlBuilder;
import com.jingdong.common.unification.router.JDRouterUtil;
import com.jingdong.common.utils.JumpMessageActivityUtil;
import com.jingdong.common.utils.pay.PayCallbackListener;
import com.jingdong.common.utils.pay.PayUtils;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.constant.JshopConst;
import com.jingdong.jdsdk.utils.JSONObjectProxy;
import com.jingdong.jdsdk.utils.SerializableContainer;
import com.jingdong.jdsdk.utils.URLParamMap;
import com.tencent.smtt.sdk.WebView;
import java.net.URLEncoder;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class OrderISVActivityJumpUtil {
    public static final String INVALID_IM_VENDER = "-1";
    public static final String ROUTER_MODULE_CASHIER = "JDCashierModule";
    public static final String ROUTER_MODULE_COMMENTS = "JDShareOrderModule";
    public static final String ROUTER_MODULE_ORDER = "JDOrderModule";
    private static final String TAG = "StartActivityUtils";

    public static void goToEvaluateEdit(Context context, String str, String str2, String str3, int i2) {
        Bundle bundle = new Bundle();
        bundle.putString("orderId", str);
        bundle.putString("wareId", str2);
        bundle.putInt("business", 0);
        bundle.putString("commentType", str3);
        DeepLinkEvaluateCenterHelper.startEvaluateEditForResult(context, bundle, i2);
    }

    public static void goToEvaluateEditByRouter(final Context context, final String str, final String str2, final String str3, final int i2) {
        if (JDRouterUtil.isRouterJump()) {
            final String build = new JDRouterUrlBuilder().setModuleName(ROUTER_MODULE_COMMENTS).setMethodName("showShareOrder").putStringParam("orderId", str).putStringParam("wareId", str2).putIntParam("business", 0).putStringParam("commentType", str3).build();
            JDRouter.build(context, build).callBackListener(new CallBackListener() { // from class: com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVActivityJumpUtil.2
                @Override // com.jingdong.common.unification.router.CallBackListener
                public void onComplete() {
                }

                @Override // com.jingdong.common.unification.router.CallBackListener
                public void onError(int i3) {
                    Context context2 = context;
                    JDRouterMtaUtil.routerErrorMta(context2, context2.getClass().getSimpleName(), build + CartConstant.KEY_YB_INFO_LINK + String.valueOf(i3));
                    OrderISVActivityJumpUtil.goToEvaluateEdit(context, str, str2, str3, i2);
                }
            }).requestCode(i2).open();
            return;
        }
        goToEvaluateEdit(context, str, str2, str3, i2);
    }

    public static void gotoLocalOrderDetail(final Context context, final String str, final String str2, final String str3) {
        if (JDRouterUtil.isRouterJump()) {
            JDRouterUrlBuilder jDRouterUrlBuilder = new JDRouterUrlBuilder();
            jDRouterUrlBuilder.setModuleName(ROUTER_MODULE_ORDER).setMethodName("showDetail").putStringParam("orderId", str).putBooleanParam("isNew", true);
            final String build = jDRouterUrlBuilder.build();
            JDRouter.build(context, build).callBackListener(new CallBackListener() { // from class: com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVActivityJumpUtil.8
                @Override // com.jingdong.common.unification.router.CallBackListener
                public void onComplete() {
                }

                @Override // com.jingdong.common.unification.router.CallBackListener
                public void onError(int i2) {
                    Context context2 = context;
                    JDRouterMtaUtil.routerErrorMta(context2, context2.getClass().getSimpleName(), build + CartConstant.KEY_YB_INFO_LINK + String.valueOf(i2));
                    OrderISVActivityJumpUtil.oldJumpToOrderDetail(context, str, str2, str3);
                }
            }).requestCode(0).open();
            JDRouterMtaUtil.routerEnterMta(context, context.getClass().getSimpleName(), "JDOrderModule_showDetail");
            return;
        }
        oldJumpToOrderDetail(context, str, str2, str3);
    }

    public static void gotoProductDetailPage(Context context, String str) {
        long pareStr2Long = OrderISVUtil.pareStr2Long(str);
        if (pareStr2Long != -1) {
            DeeplinkProductDetailHelper.startProductDetail(context, pareStr2Long, (String) null, (String) null, (String) null, (SourceEntity) null);
        }
    }

    public static void gotoVirtualOrderDetail(Context context, String str, int i2, String str2) {
        if (TextUtils.isEmpty(str) || i2 <= 0) {
            return;
        }
        try {
            JSONObjectProxy jSONObjectProxy = TextUtils.isEmpty(str2) ? null : new JSONObjectProxy(new JSONObject(str2));
            if (context == null || !(context instanceof BaseActivity)) {
                return;
            }
            OrderISVStartVirtualOrderDetail.jumpToActivity((BaseActivity) context, str, i2, jSONObjectProxy);
        } catch (Exception e2) {
            if (Log.E) {
                e2.printStackTrace();
            }
        }
    }

    public static void jumpToCancelProgress(Context context, String str, String str2) {
        Bundle bundle = new Bundle();
        bundle.putString("orderId", str);
        bundle.putString("fromPage", str2);
        DeepLinkOrderCenterHelper.startCancelProgress(context, bundle);
    }

    public static void jumpToCancelProgressByRouter(final Context context, final String str, final String str2, final int i2) {
        if (context == null) {
            return;
        }
        if (JDRouterUtil.isRouterJump()) {
            if (Log.D) {
                Log.d(TAG, "jumpToCancelProgressByRouter -->> ");
            }
            final String build = new JDRouterUrlBuilder().setModuleName(ROUTER_MODULE_ORDER).setMethodName("showRefund").putStringParam("orderId", str).putStringParam("fromPage", str2).build();
            JDRouter.build(context, build).callBackListener(new CallBackListener() { // from class: com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVActivityJumpUtil.5
                @Override // com.jingdong.common.unification.router.CallBackListener
                public void onComplete() {
                }

                @Override // com.jingdong.common.unification.router.CallBackListener
                public void onError(int i3) {
                    Context context2 = context;
                    JDRouterMtaUtil.routerErrorMta(context2, context2.getClass().getSimpleName(), build + CartConstant.KEY_YB_INFO_LINK + String.valueOf(i3));
                    OrderISVActivityJumpUtil.jumpToCancelProgress(context, str, str2, i2);
                }
            }).requestCode(i2).open();
            JDRouterMtaUtil.routerEnterMta(context, context.getClass().getSimpleName(), "JDOrderModule_showRefund");
            return;
        }
        jumpToCancelProgress(context, str, str2, i2);
    }

    public static void jumpToCashier(Context context, String str, String str2, String str3, String str4, String str5) {
        try {
            JDJSONObject generatePayJDJsonObject = PayUtils.generatePayJDJsonObject(str, str2, str3, str4, "", "", str5);
            if (context instanceof BaseActivity) {
                PayUtils.doPay((BaseActivity) context, generatePayJDJsonObject, (PayCallbackListener) null);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static void jumpToCashierByRouter(final Context context, final String str, final String str2, final String str3, final String str4, final String str5) {
        if (JDRouterUtil.isRouterJump()) {
            final String build = new JDRouterUrlBuilder().setModuleName(ROUTER_MODULE_CASHIER).setMethodName("show").putStringParam("orderId", str).putStringParam("orderTypeCode", str2).putStringParam("orderType", str3).putStringParam("payablePrice", str4).putStringParam("paySourceId", str5).build();
            JDRouter.build(context, build).callBackListener(new CallBackListener() { // from class: com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVActivityJumpUtil.4
                @Override // com.jingdong.common.unification.router.CallBackListener
                public void onComplete() {
                }

                @Override // com.jingdong.common.unification.router.CallBackListener
                public void onError(int i2) {
                    Context context2 = context;
                    JDRouterMtaUtil.routerErrorMta(context2, context2.getClass().getSimpleName(), build + CartConstant.KEY_YB_INFO_LINK + String.valueOf(i2));
                    OrderISVActivityJumpUtil.jumpToCashier(context, str, str2, str3, str4, str5);
                }
            }).open();
            JDRouterMtaUtil.routerEnterMta(context, context.getClass().getSimpleName(), ROUTER_MODULE_CASHIER);
            return;
        }
        jumpToCashier(context, str, str2, str3, str4, str5);
    }

    public static void jumpToCommentsShareForResult(Context context, String str, int i2) {
        Bundle bundle = new Bundle();
        bundle.putString("orderId", str);
        bundle.putInt("business", 0);
        DeepLinkEvaluateCenterHelper.startEvaluateUniteForResult(context, bundle, i2);
    }

    public static void jumpToCommentsShareForResultByRouter(final Context context, final String str, final int i2) {
        if (JDRouterUtil.isRouterJump()) {
            final String build = new JDRouterUrlBuilder().setModuleName(ROUTER_MODULE_COMMENTS).setMethodName("showCommentAndShare").putStringParam("orderId", str).putIntParam("business", 0).build();
            JDRouter.build(context, build).callBackListener(new CallBackListener() { // from class: com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVActivityJumpUtil.1
                @Override // com.jingdong.common.unification.router.CallBackListener
                public void onComplete() {
                }

                @Override // com.jingdong.common.unification.router.CallBackListener
                public void onError(int i3) {
                    Context context2 = context;
                    JDRouterMtaUtil.routerErrorMta(context2, context2.getClass().getSimpleName(), build + CartConstant.KEY_YB_INFO_LINK + String.valueOf(i3));
                    OrderISVActivityJumpUtil.jumpToCommentsShareForResult(context, str, i2);
                }
            }).requestCode(i2).open();
            return;
        }
        jumpToCommentsShareForResult(context, str, i2);
    }

    public static void jumpToCompleteOrderListActivity(Context context, String str) {
        if (context == null || TextUtils.isEmpty(str)) {
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString("orderId", str);
        DeepLinkFillOrderHelper.startCompleteOrderListActivity(context, bundle);
    }

    public static void jumpToDeliveryHomeWeb(Context context, String str, String str2, Runnable runnable) {
        if (runnable != null) {
            runnable.run();
        }
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
        bundle.putBoolean(MBaseKeyNames.IS_SHOW_MORE_BTN, false);
        bundle.putBoolean(MBaseKeyNames.IS_NEED_SHARE, true);
        bundle.putBoolean(MBaseKeyNames.KEY_ALWAYS_TRANSPARENT_STATUSBAR, false);
        ShareInfo shareInfo = new ShareInfo();
        shareInfo.setUrl(str2);
        bundle.putParcelable("shareInfo", shareInfo);
        DeepLinkCommonHelper.startWebActivity(context, bundle, true);
    }

    public static void jumpToDial(Context context, String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            Intent intent = new Intent();
            intent.setAction("android.intent.action.DIAL");
            intent.setData(Uri.parse(WebView.SCHEME_TEL + URLEncoder.encode(str, "UTF-8")));
            context.startActivity(intent);
        } catch (Exception unused) {
        }
    }

    public static void jumpToEvaluateCenter(Context context, String str) {
        Bundle bundle = new Bundle();
        bundle.putString("orderId", str);
        DeepLinkEvaluateCenterHelper.startEvaluateUniteForResult(context, bundle, 1102);
    }

    public static void jumpToEvaluateEdit(Context context, String str, String str2) {
        Bundle bundle = new Bundle();
        bundle.putString("orderId", str);
        bundle.putString("wareId", str2);
        bundle.putInt("business", 0);
        DeepLinkEvaluateCenterHelper.startEvaluateEdit(context, bundle);
    }

    public static void jumpToEvaluateEditByRouter(final Context context, final String str, final String str2) {
        if (JDRouterUtil.isRouterJump()) {
            final String build = new JDRouterUrlBuilder().setModuleName(ROUTER_MODULE_COMMENTS).setMethodName("showShareOrder").putStringParam("orderId", str).putStringParam("wareId", str2).putIntParam("business", 0).build();
            JDRouter.build(context, build).callBackListener(new CallBackListener() { // from class: com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVActivityJumpUtil.3
                @Override // com.jingdong.common.unification.router.CallBackListener
                public void onComplete() {
                }

                @Override // com.jingdong.common.unification.router.CallBackListener
                public void onError(int i2) {
                    Context context2 = context;
                    JDRouterMtaUtil.routerErrorMta(context2, context2.getClass().getSimpleName(), build + CartConstant.KEY_YB_INFO_LINK + String.valueOf(i2));
                    OrderISVActivityJumpUtil.jumpToEvaluateEdit(context, str, str2);
                }
            }).open();
            return;
        }
        jumpToEvaluateEdit(context, str, str2);
    }

    public static boolean jumpToFlutterRefundProgressByOpenApp(Context context, String str, boolean z, String str2, String str3, String str4) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        OrderISVRefundProgressFlutterOpenAppBean orderISVRefundProgressFlutterOpenAppBean = new OrderISVRefundProgressFlutterOpenAppBean();
        OrderISVRefundProgressFlutterOpenAppBean.ParamBean paramBean = new OrderISVRefundProgressFlutterOpenAppBean.ParamBean();
        paramBean.orderId = str;
        paramBean.fromPage = str2;
        if (!TextUtils.isEmpty(str3)) {
            paramBean.adjustType = str3;
        }
        if (!TextUtils.isEmpty(str4)) {
            paramBean.refId = str4;
        }
        orderISVRefundProgressFlutterOpenAppBean.param = paramBean;
        IJsonParse jsonParser = UtilFactory.getInstance().getJsonParser();
        if (jsonParser != null) {
            jumpWithOpenApp(context, "openapp.jdmobile://virtual?params=" + jsonParser.toJsonString(orderISVRefundProgressFlutterOpenAppBean));
            return true;
        }
        return false;
    }

    public static void jumpToInstallationTrace(Context context, String str, String str2) {
        Bundle bundle = new Bundle();
        bundle.putString("orderId", str);
        bundle.putString("fromPage", str2);
        DeepLinkOrderCenterHelper.startInstallTrace(context, bundle);
    }

    public static void jumpToInstallationTraceByRouter(final Context context, final String str, final String str2) {
        if (JDRouterUtil.isRouterJump()) {
            if (Log.D) {
                Log.d(TAG, "jumpToInstallationTraceByRouter -->> ");
            }
            final String build = new JDRouterUrlBuilder().setModuleName(ROUTER_MODULE_ORDER).setMethodName("showInstall").putStringParam("orderId", str).putStringParam("fromPage", str2).build();
            JDRouter.build(context, build).callBackListener(new CallBackListener() { // from class: com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVActivityJumpUtil.7
                @Override // com.jingdong.common.unification.router.CallBackListener
                public void onComplete() {
                }

                @Override // com.jingdong.common.unification.router.CallBackListener
                public void onError(int i2) {
                    Context context2 = context;
                    JDRouterMtaUtil.routerErrorMta(context2, context2.getClass().getSimpleName(), build + CartConstant.KEY_YB_INFO_LINK + String.valueOf(i2));
                    OrderISVActivityJumpUtil.jumpToInstallationTrace(context, str, str2);
                }
            }).open();
            JDRouterMtaUtil.routerEnterMta(context, context.getClass().getSimpleName(), "JDOrderModule_showInstall");
            return;
        }
        jumpToInstallationTrace(context, str, str2);
    }

    public static void jumpToOrderDetail(Context context, OrderDetailISVJumpParam orderDetailISVJumpParam) {
        OrderISVRedirectProtocol orderISVRedirectProtocol;
        if (orderDetailISVJumpParam == null || (orderISVRedirectProtocol = orderDetailISVJumpParam.newOrderISVRedirectProtocol) == null) {
            return;
        }
        String str = orderISVRedirectProtocol.type;
        if ("M".equals(str)) {
            startWebActivity(context, orderDetailISVJumpParam.newOrderISVRedirectProtocol.url);
        } else if ("Native".equals(str)) {
            if (orderDetailISVJumpParam.isVirtualOrder) {
                if (orderDetailISVJumpParam.canGoToVirtualOrder) {
                    gotoVirtualOrderDetail(context, orderDetailISVJumpParam.orderId, orderDetailISVJumpParam.orderType, orderDetailISVJumpParam.newOrderISVRedirectProtocol.param);
                    return;
                }
                return;
            }
            gotoLocalOrderDetail(context, orderDetailISVJumpParam.orderId, orderDetailISVJumpParam.mTestId, orderDetailISVJumpParam.mFromPage);
        } else if ("openApp".equals(str)) {
            jumpWithOpenApp(context, orderDetailISVJumpParam.newOrderISVRedirectProtocol.url);
        }
    }

    public static void jumpToShopActivity(Context context, String str, String str2) {
        Bundle bundle = new Bundle();
        bundle.putString(JshopConst.INTENT_BRAND_JSON, "{\"venderId\":" + str + ",\"shopId\":" + str2 + "}");
        StringBuilder sb = new StringBuilder();
        sb.append("");
        sb.append(str);
        bundle.putSerializable("source", new SourceEntity("Orderdetail_Shopid", sb.toString()));
        DeepLinkCommonHelper.startShopActivity(context, bundle, true);
    }

    public static void jumpToShoppingCart(Context context) {
        DeepLinkCommonHelper.startShoppingCartActivity(context, null, true);
    }

    public static void jumpWithOpenApp(Context context, String str) {
        if (TextUtils.isEmpty(str) || context == null) {
            return;
        }
        Uri parse = Uri.parse(str);
        if (Log.D) {
            Log.d("jumpWithOpenApp", "uri -->> " + parse.toString());
        }
        if (parse.getScheme() == null) {
            return;
        }
        if (parse.getScheme().contains("http")) {
            startWebActivity(context, parse.toString());
        } else if (!parse.getScheme().contains(OpenAppConstant.SCHEME_OPENAPP_1) && !parse.getScheme().contains("openapp.jdmobile")) {
            if (parse.getScheme().toLowerCase().contains("openapp.jdmobile")) {
                new OpenAppJumpBuilder.Builder(parse).build().jump(context);
            }
        } else {
            new OpenAppJumpBuilder.Builder(parse).build().jump(context);
        }
    }

    public static void oldJumpToOrderDetail(Context context, String str, String str2, String str3) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putBoolean("isNew", true);
        bundle.putString("function", str3);
        bundle.putString(NotificationMessageSummary.TEST_ID, str2);
        bundle.putString("orderId", str);
        if (context instanceof BaseActivity) {
            DeepLinkOrderCenterHelper.startOrderDetailForResult((BaseActivity) context, bundle, 0);
        }
    }

    public static void startMessageCenter(Context context) {
        JumpMessageActivityUtil.jumpToMessageCenter(context);
    }

    public static void startMovieActivity(Context context, Bundle bundle) {
        Intent intent = new Intent();
        intent.putExtras(bundle);
        JDReactAuraHelper.getInstance().launchMovieHomeActivity(context, intent);
    }

    public static void startProductDetailActivity(Context context, Bundle bundle, SourceEntity sourceEntity) {
        if (context == null || bundle == null) {
            return;
        }
        if (sourceEntity != null) {
            bundle.putSerializable("source", sourceEntity);
        }
        if (!TextUtils.isEmpty(bundle.getString("clickUrl"))) {
            bundle.putString("targetUrl", bundle.getString("clickUrl"));
        }
        DeeplinkProductDetailHelper.startProductDetail(context, bundle);
    }

    public static void startQbActivity(Context context, Bundle bundle) {
        DeepLinkCommonHelper.startActivityDirect(context, DeepLinkCommonHelper.HOST_QBGAMEORDERLIST_ACTIVITY, bundle);
    }

    public static void startWebActivity(Context context, String str) {
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
        bundle.putBoolean(MBaseKeyNames.IS_SHOW_MORE_BTN, false);
        DeepLinkCommonHelper.startWebActivity(context, bundle, true);
    }

    public static void jumpToCancelProgress(Context context, String str, String str2, int i2) {
        Bundle bundle = new Bundle();
        bundle.putString("orderId", str);
        bundle.putString("fromPage", str2);
        if (context instanceof BaseActivity) {
            DeepLinkOrderCenterHelper.startCancelProgressForResult((BaseActivity) context, bundle, i2);
        }
    }

    public static void jumpToCancelProgressByRouter(final Context context, final String str, final String str2) {
        if (JDRouterUtil.isRouterJump()) {
            if (Log.D) {
                Log.d(TAG, "jumpToCancelProgressByRouter -->> ");
            }
            final String build = new JDRouterUrlBuilder().setModuleName(ROUTER_MODULE_ORDER).setMethodName("showRefund").putStringParam("orderId", str).putStringParam("fromPage", str2).build();
            JDRouter.build(context, build).callBackListener(new CallBackListener() { // from class: com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVActivityJumpUtil.6
                @Override // com.jingdong.common.unification.router.CallBackListener
                public void onComplete() {
                }

                @Override // com.jingdong.common.unification.router.CallBackListener
                public void onError(int i2) {
                    Context context2 = context;
                    JDRouterMtaUtil.routerErrorMta(context2, context2.getClass().getSimpleName(), build + CartConstant.KEY_YB_INFO_LINK + String.valueOf(i2));
                    OrderISVActivityJumpUtil.jumpToCancelProgress(context, str, str2);
                }
            }).open();
            JDRouterMtaUtil.routerEnterMta(context, context.getClass().getSimpleName(), "JDOrderModule_showRefund");
            return;
        }
        jumpToCancelProgress(context, str, str2);
    }
}
