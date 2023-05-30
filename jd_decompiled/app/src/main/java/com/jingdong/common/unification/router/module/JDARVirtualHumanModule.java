package com.jingdong.common.unification.router.module;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import com.jingdong.common.deeplinkhelper.DeepLinkArVrPublicInterfaceHelper;
import com.jingdong.common.unification.router.CallBackListener;
import com.jingdong.common.unification.router.JDRouterUtil;
import com.jingdong.corelib.utils.Log;
import com.jingdong.sdk.deeplink.DeepLinkDispatch;
import com.jingdong.sdk.deeplink.DeepLinkUri;
import com.jingdong.sdk.jdhttpdns.config.HttpDnsConfig;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class JDARVirtualHumanModule implements IJDModule {
    private void bundleToJson(Bundle bundle, JSONObject jSONObject) {
        if (bundle == null || jSONObject == null) {
            return;
        }
        for (String str : bundle.keySet()) {
            try {
                jSONObject.put(str, bundle.get(str));
            } catch (JSONException unused) {
            }
        }
    }

    private void jsonToBundle(JSONObject jSONObject, Bundle bundle) {
        if (jSONObject == null || bundle == null) {
            return;
        }
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            bundle.putString(next, jSONObject.optString(next));
        }
    }

    public void cleanCache(Context context, JSONObject jSONObject, Bundle bundle, CallBackListener callBackListener) {
        if (context == null) {
            JDRouterUtil.callBackError(callBackListener, 703);
            return;
        }
        Bundle bundle2 = new Bundle();
        bundle2.putString("className", "com.jd.lib.virtualhuman.CacheManager");
        bundle2.putString("methodName", "cleanCache");
        bundle2.putString(DeepLinkArVrPublicInterfaceHelper.BUNDLE_KEY_METHOD_PARAMS, "");
        DeepLinkArVrPublicInterfaceHelper.execMethod(context, bundle2, false);
    }

    public void exportInterface(Context context, JSONObject jSONObject, Bundle bundle, CallBackListener callBackListener) {
        if (context != null && (context instanceof Activity)) {
            DeepLinkUri.Builder host = new DeepLinkUri.Builder().scheme("jingdong").host("virtualhumanactivity");
            if (jSONObject != null) {
                try {
                    Iterator<String> keys = jSONObject.keys();
                    while (keys.hasNext()) {
                        String next = keys.next();
                        if (bundle != null) {
                            bundle.putString(next, jSONObject.optString(next));
                        }
                    }
                } catch (Error e2) {
                    Log.e("virtualhuman", "showVirtualHuman()e=" + e2.toString());
                    JDRouterUtil.callBackError(callBackListener, 704);
                    return;
                }
            }
            DeepLinkDispatch.startActivityDirect((Activity) context, host.toString(), bundle);
            JDRouterUtil.callBackComplete(callBackListener);
            return;
        }
        JDRouterUtil.callBackError(callBackListener, 703);
    }

    public void preload(Context context, JSONObject jSONObject, Bundle bundle, CallBackListener callBackListener) {
        if (context == null) {
            JDRouterUtil.callBackError(callBackListener, 703);
            return;
        }
        Bundle bundle2 = new Bundle();
        bundle2.putString("className", "com.jd.lib.virtualhuman.CacheManager");
        bundle2.putString("methodName", HttpDnsConfig.PREDOWNLOAD_PARAMS);
        try {
            String string = jSONObject.getString("tempId");
            String string2 = jSONObject.getString("channel");
            JSONArray jSONArray = new JSONArray();
            jSONArray.put(string);
            jSONArray.put(string2);
            bundle2.putString(DeepLinkArVrPublicInterfaceHelper.BUNDLE_KEY_METHOD_PARAMS, jSONArray.toString());
            DeepLinkArVrPublicInterfaceHelper.execMethod(context, bundle2, true);
            JDRouterUtil.callBackComplete(callBackListener);
        } catch (Exception e2) {
            Log.e("virtualhuman", "preload()e=" + e2.toString());
            JDRouterUtil.callBackError(callBackListener, 704);
        }
    }

    @Override // com.jingdong.common.unification.router.module.IJDModule
    public void show(Context context, JSONObject jSONObject, Bundle bundle, CallBackListener callBackListener) {
        Log.d("virtualhuman", "show():jsonParam = " + jSONObject);
    }
}
