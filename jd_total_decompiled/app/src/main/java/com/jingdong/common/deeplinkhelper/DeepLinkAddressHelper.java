package com.jingdong.common.deeplinkhelper;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.address.AddressConstant;
import com.jingdong.common.unification.router.builder.RouterEntry;
import com.jingdong.common.web.IRouterParams;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.auraSetting.AuraBundleInfos;
import com.jingdong.jdsdk.constant.JshopConst;
import com.jingdong.sdk.deeplink.DeepLinkUri;
import com.jingdong.sdk.deeplink.a;
import com.jingdong.sdk.deeplink.b;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class DeepLinkAddressHelper {
    public static final String ACTIVITY_REQUEST_CODE = "requestCode";
    public static final String ACTIVITY_TITLE = "title";
    public static final String COME_FROM_EMPTY_ADDRESS = "empty_address";
    public static final String INTENT_EXTAS_ADDRESS_TAG_LIST = "addressTagList";
    public static final String INTENT_EXTAS_PAGE_TYPE = "PageType";
    public static final String INTENT_EXTAS_SELECTED_ADDRESS_TAG = "selectedAddressTag";
    public static final String INTENT_EXTAS_SHOW_LOCATION = "show_location";
    public static final int PAGE_TYPE_NEW = 1;
    public static final int PAGE_TYPE_SAVE = 2;
    public static RouterEntry routerEntry;
    public static IRouterParams routerParams;

    private static boolean checkDeepLinkHost(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        a d = b.a().d(new DeepLinkUri.Builder().scheme("jingdong").host(str).toString());
        return (d == null || TextUtils.isEmpty(d.b())) ? false : true;
    }

    public static String getSourceMapJsonString(String str, boolean z) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(AddressConstant.ADDRESS_LIST_SOURCE_TAG_KEY, str);
            jSONObject.put(AddressConstant.ADDRESS_LIST_PLUS_KEY, z);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return jSONObject.toString();
    }

    public static void startAddressListDynamic(Context context, Bundle bundle, boolean z, int i2) {
        String str = (bundle == null || !bundle.getBoolean(AddressConstant.INTENT_EXTRAS_ADDRESS_LIST_LAYER_FLAG, false)) ? DeepLinkCommonHelper.HOST_ADDRESSLIST_ACTIVITY : DeepLinkCommonHelper.HOST_POPUP_ADDRESSLIST_ACTIVITY;
        if (checkDeepLinkHost(str) && DeepLinkSwitch.getInstance().isSwitchOpen(AuraBundleInfos.getSwitchMaskFromBundleId(29))) {
            if (z) {
                DeepLinkCommonHelper.startActivityForResult((Activity) context, str, bundle, i2);
            } else {
                DeepLinkCommonHelper.startActivityDirect(context, str, bundle);
            }
        }
    }

    public static void startAddressListFormApplets(Context context, JDJSONObject jDJSONObject, RouterEntry routerEntry2) {
        if (Log.D) {
            StringBuilder sb = new StringBuilder();
            sb.append("jsonParam=");
            sb.append(jDJSONObject == null ? "jsonParam is null" : jDJSONObject.toString());
            Log.d("startAddressListFormApplets", sb.toString());
        }
        if (Log.D) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("context=");
            sb2.append(context == null ? "context is null" : "context is not null");
            Log.d("startAddressListFormApplets", sb2.toString());
        }
        if (Log.D) {
            StringBuilder sb3 = new StringBuilder();
            sb3.append("routerEntry=");
            sb3.append(routerEntry == null ? "routerEntry is null" : "routerEntry is not null");
            Log.d("startAddressListFormApplets", sb3.toString());
        }
        if (context == null || jDJSONObject == null || routerEntry2 == null) {
            return;
        }
        routerEntry = routerEntry2;
        Bundle bundle = new Bundle();
        String optString = jDJSONObject.optString("bussinessType");
        JDJSONObject optJSONObject = jDJSONObject.optJSONObject("sourceMap");
        String optString2 = jDJSONObject.optString(JshopConst.JSKEY_COUPON_PLATFORM);
        bundle.putString(AddressConstant.ADDRESS_LIST_BUSSINESS_TYPE_KEY, optString);
        if (optJSONObject != null) {
            bundle.putString(AddressConstant.ADDRESS_LIST_SOURCE_MAP_KEY, optJSONObject.toString());
        }
        bundle.putString(AddressConstant.ADDRESS_LIST_PLATFORM_TYPE_KEY, optString2);
        startAddressListDynamic(context, bundle, false, 0);
    }

    public static void startAddressListFormH5(IRouterParams iRouterParams) {
        if (iRouterParams == null) {
            return;
        }
        if (iRouterParams.getContext() != null && !TextUtils.isEmpty(iRouterParams.getRouterParam())) {
            routerParams = iRouterParams;
            Bundle bundle = new Bundle();
            try {
                if (Log.D) {
                    Log.d("startAddressListFormH5", iRouterParams.getRouterParam());
                }
                JSONObject jSONObject = new JSONObject(iRouterParams.getRouterParam());
                String optString = jSONObject.optString("bussinessType");
                JSONObject optJSONObject = jSONObject.optJSONObject("sourceMap");
                String optString2 = jSONObject.optString(JshopConst.JSKEY_COUPON_PLATFORM);
                bundle.putString(AddressConstant.ADDRESS_LIST_BUSSINESS_TYPE_KEY, optString);
                if (optJSONObject != null) {
                    bundle.putString(AddressConstant.ADDRESS_LIST_SOURCE_MAP_KEY, optJSONObject.toString());
                }
                bundle.putString(AddressConstant.ADDRESS_LIST_PLATFORM_TYPE_KEY, optString2);
                if (Log.D) {
                    Log.d("startAddressListFormH5", "bussinessType=" + optString + " sourceMap=" + optJSONObject.toString() + " platformType=" + optString2);
                }
            } catch (JSONException e2) {
                if (Log.D) {
                    e2.printStackTrace();
                }
            }
            startAddressListDynamic(iRouterParams.getContext(), bundle, false, 0);
            return;
        }
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.put("status", -1);
            jSONObject2.put("msg", "error params");
        } catch (JSONException e3) {
            e3.printStackTrace();
        }
        iRouterParams.onCallBack(jSONObject2.toString());
    }

    public static void startEditNewEasyBuyAddressDynamic(Context context, Bundle bundle, boolean z, int i2) {
        if (checkDeepLinkHost(DeepLinkCommonHelper.HOST_EDITNEWEASYBUYADDRESS_ACTIVITY) && DeepLinkSwitch.getInstance().isSwitchOpen(AuraBundleInfos.getSwitchMaskFromBundleId(29))) {
            if (z) {
                DeepLinkCommonHelper.startActivityForResult((Activity) context, DeepLinkCommonHelper.HOST_EDITNEWEASYBUYADDRESS_ACTIVITY, bundle, i2);
            } else {
                DeepLinkCommonHelper.startActivityDirect(context, DeepLinkCommonHelper.HOST_EDITNEWEASYBUYADDRESS_ACTIVITY, bundle);
            }
        }
    }

    public static void startEditOrderAddressDynamic(Context context, Bundle bundle, boolean z, int i2) {
        String str = (bundle == null || !bundle.getBoolean(AddressConstant.INTENT_EXTRAS_ADDRESS_LAYER_FLAG, false)) ? DeepLinkCommonHelper.HOST_EDITORDERADDRESS_ACTIVITY : DeepLinkCommonHelper.HOST_POPUP_EDITORDERADDRESS_ACTIVITY;
        if (checkDeepLinkHost(str) && DeepLinkSwitch.getInstance().isSwitchOpen(AuraBundleInfos.getSwitchMaskFromBundleId(29))) {
            if (z) {
                DeepLinkCommonHelper.startActivityForResult((Activity) context, str, bundle, i2);
            } else {
                DeepLinkCommonHelper.startActivityDirect(context, str, bundle);
            }
        }
    }

    public static void startEditOrderAddressListFloorDynamic(Context context) {
        startEditOrderAddressListFloorDynamic(context, null, false, 0);
    }

    public static void startNewEasyAddressListDynamic(Context context, Bundle bundle) {
        startNewEasyAddressListDynamic(context, bundle, false, 0);
    }

    public static void startEditOrderAddressListFloorDynamic(Context context, Bundle bundle, boolean z, int i2) {
        startAddressListDynamic(context, bundle, z, i2);
    }

    public static void startNewEasyAddressListDynamic(Context context, Bundle bundle, boolean z, int i2) {
        startAddressListDynamic(context, bundle, z, i2);
    }
}
