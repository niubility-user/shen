package com.jdcn.fido.service;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import com.jdcn.fido.constant.BasicInformation;
import com.jdcn.fido.http.HttpUrlUtil;
import com.jdcn.fido.http.HttpUtil;
import com.jdcn.fido.sdk.IFidoCallback;
import com.jdcn.fido.utils.FidoServiceUtil;
import com.jdcn.fido.utils.PackageUtil;
import com.jdcn.fido.utils.TrackerUtil;
import org.json.JSONObject;

/* loaded from: classes18.dex */
public class UnregisterService {
    private static void handleResponse(Activity activity, String str, String str2, IFidoCallback iFidoCallback) {
        try {
            if (TextUtils.isEmpty(str2)) {
                unregisterEnd(activity, 210, null, iFidoCallback);
                return;
            }
            JSONObject jSONObject = new JSONObject(str2);
            if (!"FINGER_FIDO".equals(jSONObject.getString("payWay"))) {
                unregisterEnd(activity, 210, null, iFidoCallback);
            } else if (!"1".equals(jSONObject.getString("code"))) {
                unregisterEnd(activity, 210, null, iFidoCallback);
            } else {
                new Bundle().putString("deviceId", str);
                unregisterEnd(activity, 0, null, iFidoCallback);
            }
        } catch (Throwable unused) {
            unregisterEnd(activity, 210, null, iFidoCallback);
        }
    }

    public static void unregister(Activity activity, Bundle bundle, IFidoCallback iFidoCallback) {
        String userName = FidoServiceUtil.getUserName(activity, bundle);
        try {
            String packageName = PackageUtil.getPackageName(activity);
            if (TextUtils.isEmpty(packageName)) {
                Bundle bundle2 = new Bundle();
                bundle2.putString("message", "packageName is empty");
                unregisterEnd(activity, 401, bundle2, iFidoCallback);
                return;
            }
            JSONObject jSONObject = new JSONObject();
            String string = bundle.containsKey("pin") ? bundle.getString("pin") : "";
            String string2 = bundle.containsKey("A2") ? bundle.getString("A2") : "";
            String string3 = bundle.containsKey("eytPin") ? bundle.getString("eytPin") : "";
            String string4 = bundle.containsKey("visa") ? bundle.getString("visa") : "";
            jSONObject.put("pin", string);
            jSONObject.put("a2", string2);
            jSONObject.put("eytPin", string3);
            jSONObject.put("visa", string4);
            jSONObject.put("appId", packageName);
            jSONObject.put("client", "1");
            jSONObject.put("deviceId", userName);
            handleResponse(activity, userName, HttpUtil.httpPost(HttpUrlUtil.getRequestUrl(HttpUrlUtil.URL_UNREGISTER), jSONObject), iFidoCallback);
        } catch (Throwable unused) {
            Bundle bundle3 = new Bundle();
            bundle3.putString("message", "unregister->getUAFMessage exception");
            unregisterEnd(activity, 401, bundle3, iFidoCallback);
        }
    }

    private static void unregisterEnd(Activity activity, final int i2, final Bundle bundle, final IFidoCallback iFidoCallback) {
        TrackerUtil.appendResult(BasicInformation.SCENE_TRANS_RESULT, i2);
        TrackerUtil.tracker(activity, BasicInformation.SCENE_UNREG);
        activity.runOnUiThread(new Runnable() { // from class: com.jdcn.fido.service.UnregisterService.1
            @Override // java.lang.Runnable
            public final void run() {
                iFidoCallback.response(i2, bundle);
            }
        });
    }
}
