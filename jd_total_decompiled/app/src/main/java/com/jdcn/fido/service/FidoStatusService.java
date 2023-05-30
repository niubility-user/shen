package com.jdcn.fido.service;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import com.jdcn.fido.BuildConfig;
import com.jdcn.fido.constant.BasicInformation;
import com.jdcn.fido.http.HttpUrlUtil;
import com.jdcn.fido.http.HttpUtil;
import com.jdcn.fido.sdk.IFidoCallback;
import com.jdcn.fido.utils.DeviceInfo;
import com.jdcn.fido.utils.FidoServiceUtil;
import com.jdcn.fido.utils.FingerDeviceIdManger;
import com.jdcn.fido.utils.PackageUtil;
import com.jdcn.fido.utils.TrackerUtil;
import com.meizu.cloud.pushsdk.notification.model.BrightRemindSetting;
import com.tencent.mapsdk.internal.l4;
import org.json.JSONObject;

/* loaded from: classes18.dex */
public class FidoStatusService {
    public static void getOpenState(Activity activity, Bundle bundle, IFidoCallback iFidoCallback) {
        int i2;
        int fingerStatus = FidoServiceUtil.fingerStatus(activity);
        if (fingerStatus != 0) {
            getStatusEnd(activity, fingerStatus, null, iFidoCallback);
            return;
        }
        FingerDeviceIdManger.getOrGenerateDeviceId(activity);
        String statusInServer = getStatusInServer(activity, bundle);
        statusInServer.hashCode();
        char c2 = '\uffff';
        switch (statusInServer.hashCode()) {
            case -1946444285:
                if (statusInServer.equals("STATUS_NOT_OPEN")) {
                    c2 = 0;
                    break;
                }
                break;
            case -225079175:
                if (statusInServer.equals(HttpUtil.FAIL_HTTP_EXCEPTION)) {
                    c2 = 1;
                    break;
                }
                break;
            case 379436981:
                if (statusInServer.equals(HttpUtil.FAIL_ERROR_PARAM)) {
                    c2 = 2;
                    break;
                }
                break;
            case 1291172471:
                if (statusInServer.equals("STATUS_OPEN")) {
                    c2 = 3;
                    break;
                }
                break;
            case 1304499120:
                if (statusInServer.equals("STATUS_UN_AVAILABLE")) {
                    c2 = 4;
                    break;
                }
                break;
            case 1360449355:
                if (statusInServer.equals("STATUS_CLOSE")) {
                    c2 = 5;
                    break;
                }
                break;
        }
        switch (c2) {
            case 0:
                i2 = 602;
                break;
            case 1:
            case 2:
                getStatusEnd(activity, 210, null, iFidoCallback);
                return;
            case 3:
                i2 = 601;
                break;
            case 4:
                i2 = 600;
                break;
            case 5:
                i2 = 603;
                break;
            default:
                getStatusEnd(activity, 210, null, iFidoCallback);
                return;
        }
        getStatusEnd(activity, i2, null, iFidoCallback);
    }

    private static void getStatusEnd(Activity activity, final int i2, final Bundle bundle, final IFidoCallback iFidoCallback) {
        TrackerUtil.appendResult(BasicInformation.GET_OPENSTATE_RESULT, i2);
        TrackerUtil.tracker(activity, BasicInformation.GET_OPENSTATE);
        activity.runOnUiThread(new Runnable() { // from class: com.jdcn.fido.service.FidoStatusService.1
            @Override // java.lang.Runnable
            public final void run() {
                iFidoCallback.response(i2, bundle);
            }
        });
    }

    private static String getStatusInServer(Activity activity, Bundle bundle) {
        try {
            String packageName = PackageUtil.getPackageName(activity);
            String string = bundle.containsKey("pin") ? bundle.getString("pin") : "";
            String string2 = bundle.containsKey("A2") ? bundle.getString("A2") : "";
            String string3 = bundle.containsKey("eytPin") ? bundle.getString("eytPin") : "";
            String string4 = bundle.containsKey("visa") ? bundle.getString("visa") : "";
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("pin", string);
            jSONObject.put("a2", string2);
            jSONObject.put("eytPin", string3);
            jSONObject.put("visa", string4);
            jSONObject.put("appId", packageName);
            jSONObject.put("client", "1");
            JSONObject jSONObject2 = new JSONObject();
            StringBuilder sb = new StringBuilder();
            sb.append(Build.VERSION.SDK_INT);
            jSONObject2.put("version", sb.toString());
            jSONObject2.put("deviceType", DeviceInfo.getModel());
            jSONObject2.put("codename", Build.VERSION.CODENAME);
            jSONObject2.put("incremental", Build.VERSION.INCREMENTAL);
            jSONObject2.put(l4.f16791e, BuildConfig.fidoVersionName);
            jSONObject.put(BrightRemindSetting.BRIGHT_REMIND, jSONObject2);
            JSONObject jSONObject3 = new JSONObject(HttpUtil.httpPost(HttpUrlUtil.getRequestUrl(HttpUrlUtil.URL_STATUS), jSONObject));
            return "FINGER_FIDO".equals(jSONObject3.getString("payWay")) ? jSONObject3.getString("status") : HttpUtil.FAIL_HTTP_EXCEPTION;
        } catch (Throwable unused) {
            return HttpUtil.FAIL_HTTP_EXCEPTION;
        }
    }
}
