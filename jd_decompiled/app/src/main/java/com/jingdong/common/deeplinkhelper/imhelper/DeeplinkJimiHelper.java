package com.jingdong.common.deeplinkhelper.imhelper;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.airbnb.deeplinkdispatch.DeepLink;
import com.jingdong.common.deeplinkhelper.DeepLinkSwitch;
import com.jingdong.jdsdk.auraSetting.AuraBundleInfos;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.sdk.deeplink.DeepLinkDispatch;
import com.jingdong.sdk.deeplink.DeepLinkUri;
import com.jingdong.sdk.oklog.OKLog;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class DeeplinkJimiHelper {
    private static final String HOST_DONDDONG_ACTIVITY = "icsactivityshadow";
    private static final String TAG = "DeeplinkJimiHelper";
    private static DeeplinkJimiHelper mInstance;

    private DeeplinkJimiHelper() {
    }

    private Bundle convertJimiBundle2IcsBundle(Bundle bundle) {
        String string = bundle.getString("pin");
        String string2 = bundle.getString("source");
        String string3 = bundle.getString("pid");
        if (TextUtils.isEmpty(string3)) {
            string3 = bundle.getString("sku");
        }
        String string4 = bundle.getString("venderID");
        String string5 = bundle.getString("skillID");
        if (TextUtils.isEmpty(string) || TextUtils.isEmpty(string2)) {
            return null;
        }
        Bundle bundle2 = new Bundle();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("source", string2);
            jSONObject.put("from", DDParameterBuilder.ACTION_BROADCAST_START_IM_CUSTOMER_SERVICE_MANAGER);
            jSONObject.put("pin", string);
            String str = "sdk_jd";
            if (!TextUtils.isEmpty(string2) && (string2.equals("m_sdk_product") || string2.equals(JimiParameterBuilder.SOURCE_MOBILE_PRODUCT) || string2.equals("mobile_product") || string2.equals(JimiParameterBuilder.SOURCE_MOBILE_KANJIA) || string2.equals("jdapp_m_product_kanjia"))) {
                str = "sdk_item";
            }
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("entry", str);
            if (!TextUtils.isEmpty(string3)) {
                jSONObject2.put("pid", string3);
            }
            if (!TextUtils.isEmpty(string4)) {
                jSONObject2.put("venderId", string4);
            }
            if (!TextUtils.isEmpty(string5)) {
                jSONObject2.put("skillId", string5);
            }
            jSONObject.put("body", jSONObject2);
            bundle2.putBoolean(DeepLink.IS_DEEP_LINK, true);
            bundle2.putString("action", jSONObject.toString());
        } catch (Exception e2) {
            OKLog.e(TAG, e2);
        }
        return bundle2;
    }

    public static synchronized DeeplinkJimiHelper getInstance() {
        DeeplinkJimiHelper deeplinkJimiHelper;
        synchronized (DeeplinkJimiHelper.class) {
            if (mInstance == null) {
                mInstance = new DeeplinkJimiHelper();
            }
            deeplinkJimiHelper = mInstance;
        }
        return deeplinkJimiHelper;
    }

    public void startJimiActivity(Context context, Bundle bundle) {
        if (DeepLinkSwitch.getInstance().isSwitchOpen(AuraBundleInfos.getSwitchMaskFromBundleId(53))) {
            JDMtaUtils.sendCommonData(context, "start_dongdong", "aura", "DeeplinkDongDongHelper.startDongDong", (Object) null, bundle.toString(), "com.jd.lib.icssdk.ActivityShadow", "");
            DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host(HOST_DONDDONG_ACTIVITY).toString(), convertJimiBundle2IcsBundle(bundle), 268435456);
        }
    }
}
