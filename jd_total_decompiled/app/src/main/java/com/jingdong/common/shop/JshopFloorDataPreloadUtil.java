package com.jingdong.common.shop;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import com.jd.dynamic.DYConstants;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONObject;
import com.jd.libs.hybrid.HybridSDK;
import com.jingdong.common.entity.AddressGlobal;
import com.jingdong.common.entity.SourceEntity;
import com.jingdong.common.jdreactFramework.JDReactAuraHelper;
import com.jingdong.common.jdreactFramework.utils.AbstractJDReactInitialHelper;
import com.jingdong.common.jump.JumpNetDataProvider;
import com.jingdong.common.lbs.jdlocation.JDLocation;
import com.jingdong.common.utils.ABTestUtils;
import com.jingdong.common.utils.AddressUtil;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.constant.JshopConst;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.sdk.jdtoast.ToastUtils;
import com.jingdong.sdk.oklog.OKLog;
import com.jingdong.sdk.utils.DPIUtil;
import com.jingdong.union.common.config.UnionConstants;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes6.dex */
public class JshopFloorDataPreloadUtil {
    public static final String HAS_REQUEST_IN_DEEP_LINK = "hasRequestedInDeepLink";
    private static final String PRE_LOAD_FUNCTION = "getShopHomeFloorInfo";

    private static String getFunctionFlowRefer(Bundle bundle) {
        boolean containsKey = bundle.containsKey("source");
        String str = DYConstants.DY_NULL_STR;
        if (containsKey) {
            try {
                SourceEntity sourceEntity = (SourceEntity) bundle.getSerializable("source");
                if (sourceEntity != null) {
                    String sourceType = sourceEntity.getSourceType();
                    String sourceValue = sourceEntity.getSourceValue();
                    if (TextUtils.isEmpty(sourceType)) {
                        sourceType = DYConstants.DY_NULL_STR;
                    }
                    if (TextUtils.isEmpty(sourceValue)) {
                        sourceValue = DYConstants.DY_NULL_STR;
                    }
                    return sourceType + CartConstant.KEY_YB_INFO_LINK + sourceValue;
                }
            } catch (Exception unused) {
            }
        }
        if (bundle.containsKey("sourceInfo")) {
            try {
                JDJSONObject parseObject = JDJSON.parseObject(bundle.getString("sourceInfo"));
                if (parseObject != null) {
                    String optString = parseObject.optString("entrance");
                    String optString2 = parseObject.optString("moduleId");
                    if (TextUtils.isEmpty(optString)) {
                        optString = DYConstants.DY_NULL_STR;
                    }
                    if (!TextUtils.isEmpty(optString2)) {
                        str = optString2;
                    }
                    return optString + CartConstant.KEY_YB_INFO_LINK + str;
                }
                return "";
            } catch (Exception unused2) {
                return "";
            }
        }
        return "";
    }

    private static String getFunctionFlowSourceRpc(Bundle bundle) {
        if (bundle.containsKey("source")) {
            try {
                SourceEntity sourceEntity = (SourceEntity) bundle.getSerializable("source");
                if (sourceEntity != null) {
                    return sourceEntity.getSourceType();
                }
            } catch (Exception unused) {
            }
        }
        if (bundle.containsKey("sourceInfo")) {
            try {
                JDJSONObject parseObject = JDJSON.parseObject(bundle.getString("sourceInfo"));
                return parseObject != null ? parseObject.optString("entrance") : "";
            } catch (Exception unused2) {
                return "";
            }
        }
        return "";
    }

    private static Map<String, Object> getParam(Context context, Bundle bundle) {
        Bundle bundle2;
        HashMap hashMap = new HashMap();
        boolean z = bundle.containsKey("templateId") && bundle.containsKey(JshopConst.JSHOP_DESIGNER_ID);
        if (z) {
            hashMap.put(JshopConst.JSHOP_DESIGNER_ID, bundle.getString(JshopConst.JSHOP_DESIGNER_ID, ""));
            hashMap.put("templateId", bundle.getString("templateId", ""));
        }
        if (!z) {
            String shopId = JshopEnterParam.getShopId(bundle);
            String venderId = JshopEnterParam.getVenderId(bundle);
            if (!TextUtils.isEmpty(shopId)) {
                hashMap.put("shopId", shopId);
            }
            if (!TextUtils.isEmpty(venderId)) {
                hashMap.put("venderId", venderId);
            }
            if (TextUtils.isEmpty(shopId) && TextUtils.isEmpty(venderId)) {
                ExceptionReporter.reportExceptionToBugly(new Exception("\u8fdb\u5165\u5e97\u94fa\u672a\u6309\u89c4\u8303\u4f20\u53c2, \u5165\u53c2\u6ca1\u6709\"shopId\"\u6216\"venderId\""));
                if (OKLog.D) {
                    ToastUtils.showToastInCenter(context, "\u8fdb\u5165\u5e97\u94fa\u672a\u6309\u89c4\u8303\u4f20\u53c2, \u5165\u53c2\u6ca1\u6709\"shopId\"\u6216\"venderId\"", 1);
                    return null;
                }
                return null;
            }
        }
        if (bundle.containsKey("categoryInfo") && (bundle2 = bundle.getBundle("categoryInfo")) != null) {
            String string = bundle2.getString("categoryId", "");
            String string2 = bundle2.getString("categoryName", "");
            String string3 = bundle2.getString("sku", "");
            String string4 = bundle2.getString(ABTestUtils.KEY_BASE_ARCH_CONFIG_NAMESPACE, "");
            if (!TextUtils.isEmpty(string) && string.length() < 7 && !TextUtils.isEmpty(string3)) {
                hashMap.put("sku", string3);
            }
            if (!TextUtils.isEmpty(string)) {
                hashMap.put("cid", string);
            }
            if (!TextUtils.isEmpty(string2)) {
                hashMap.put("cname", string2);
            }
            if (!TextUtils.isEmpty(string4)) {
                hashMap.put("wareAbTest", string4);
            }
        }
        if (!JDReactAuraHelper.getInstance().getModuleAvailability("JDReactShopTemplate")) {
            hashMap.put("clientControl", "0");
        }
        hashMap.put("source", "app-shop");
        if (bundle.containsKey(JshopConst.KEY_TEST_ID)) {
            hashMap.put("abtest", bundle.getString(JshopConst.KEY_TEST_ID, ""));
        }
        AddressGlobal addressGlobal = AddressUtil.getAddressGlobal();
        if (addressGlobal != null) {
            hashMap.put("lngWs", addressGlobal.getLongitude());
            hashMap.put("latWs", addressGlobal.getLatitude());
        }
        JDLocation location = JshopLocation.getLocation();
        hashMap.put(HybridSDK.LNG, String.valueOf(location.getLng()));
        hashMap.put("lat", String.valueOf(location.getLat()));
        String functionFlowRefer = getFunctionFlowRefer(bundle);
        if (!TextUtils.isEmpty(functionFlowRefer)) {
            hashMap.put(UnionConstants.BUNDLE_REFER, functionFlowRefer);
        }
        String functionFlowSourceRpc = getFunctionFlowSourceRpc(bundle);
        if (!TextUtils.isEmpty(functionFlowSourceRpc)) {
            hashMap.put("sourceRpc", functionFlowSourceRpc);
        }
        hashMap.put("navigationAbTest", "1");
        if (context instanceof Activity) {
            Activity activity = (Activity) context;
            hashMap.put("displayWidth", Integer.valueOf(DPIUtil.getAppWidth(activity) - DPIUtil.dip2px(activity, 24.0f)));
        }
        if (bundle.containsKey("projectId")) {
            hashMap.put("projectId", bundle.getString("projectId", ""));
        }
        if (bundle.containsKey("pageId")) {
            hashMap.put("pageId", bundle.getString("pageId", ""));
        }
        if (bundle.containsKey("previewType")) {
            hashMap.put("previewType", bundle.getString("previewType", ""));
        }
        hashMap.put("RNVersion", AbstractJDReactInitialHelper.getJDReactFrameworkVersion());
        String string5 = bundle.getString("homeFloorExt", "");
        if (!TextUtils.isEmpty(string5)) {
            hashMap.put("extend", string5);
        }
        return hashMap;
    }

    public static void handlePreload(Context context, Bundle bundle) {
        Map<String, Object> param;
        if (needPreload(bundle) && (param = getParam(context, bundle)) != null && JumpNetDataProvider.getInstance().request(PRE_LOAD_FUNCTION, param, new boolean[0])) {
            bundle.putBoolean(HAS_REQUEST_IN_DEEP_LINK, true);
        }
    }

    private static boolean needPreload(Bundle bundle) {
        return Build.VERSION.SDK_INT >= 21 && !JshopEnterParam.isClose(bundle) && TextUtils.equals("home", JshopEnterParam.getJumpTab(bundle));
    }
}
