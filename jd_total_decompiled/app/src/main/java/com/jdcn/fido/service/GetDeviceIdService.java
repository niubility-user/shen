package com.jdcn.fido.service;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import com.jdcn.fido.BuildConfig;
import com.jdcn.fido.constant.BasicInformation;
import com.jdcn.fido.http.HttpUrlUtil;
import com.jdcn.fido.http.HttpUtil;
import com.jdcn.fido.sdk.IFidoCallback;
import com.jdcn.fido.utils.DeviceInfo;
import com.jdcn.fido.utils.FidoServiceUtil;
import com.jdcn.fido.utils.FingerDeviceIdManger;
import com.jdcn.fido.utils.TrackerUtil;
import com.tencent.mapsdk.internal.l4;
import org.json.JSONObject;

/* loaded from: classes18.dex */
public class GetDeviceIdService {
    private static String checkInServer() {
        try {
            JSONObject jSONObject = new JSONObject();
            StringBuilder sb = new StringBuilder();
            sb.append(Build.VERSION.SDK_INT);
            jSONObject.put("version", sb.toString());
            jSONObject.put("deviceType", DeviceInfo.getModel());
            jSONObject.put("codename", Build.VERSION.CODENAME);
            jSONObject.put("incremental", Build.VERSION.INCREMENTAL);
            jSONObject.put(l4.f16791e, BuildConfig.fidoVersionName);
            return HttpUtil.httpPost(HttpUrlUtil.getRequestUrl(HttpUrlUtil.URL_VERIFYVERSION), jSONObject);
        } catch (Throwable unused) {
            return HttpUtil.FAIL_HTTP_EXCEPTION;
        }
    }

    public static void getDeviceId(Activity activity, Bundle bundle, IFidoCallback iFidoCallback) {
        int fingerStatus = FidoServiceUtil.fingerStatus(activity);
        TrackerUtil.tracker(activity, null, "", "getDeviceId_run_005");
        if (fingerStatus != 0) {
            getDeviceIdEnd(activity, fingerStatus, null, iFidoCallback);
            return;
        }
        String orGenerateDeviceId = FingerDeviceIdManger.getOrGenerateDeviceId(activity);
        TrackerUtil.tracker(activity, null, "", "getDeviceId_run_006");
        if (TextUtils.isEmpty(orGenerateDeviceId)) {
            fingerStatus = 401;
        }
        if (fingerStatus != 0) {
            getDeviceIdEnd(activity, fingerStatus, null, iFidoCallback);
            return;
        }
        String checkInServer = checkInServer();
        TrackerUtil.tracker(activity, null, "", "getDeviceId_run_007");
        checkInServer.hashCode();
        char c2 = '\uffff';
        switch (checkInServer.hashCode()) {
            case -225079175:
                if (checkInServer.equals(HttpUtil.FAIL_HTTP_EXCEPTION)) {
                    c2 = 0;
                    break;
                }
                break;
            case 48:
                if (checkInServer.equals("0")) {
                    c2 = 1;
                    break;
                }
                break;
            case 49:
                if (checkInServer.equals("1")) {
                    c2 = 2;
                    break;
                }
                break;
            case 50:
                if (checkInServer.equals("2")) {
                    c2 = 3;
                    break;
                }
                break;
            case 379436981:
                if (checkInServer.equals(HttpUtil.FAIL_ERROR_PARAM)) {
                    c2 = 4;
                    break;
                }
                break;
        }
        switch (c2) {
            case 0:
            case 4:
                getDeviceIdEnd(activity, 210, null, iFidoCallback);
                return;
            case 1:
                getDeviceIdEnd(activity, 108, null, iFidoCallback);
                return;
            case 2:
                Bundle bundle2 = new Bundle();
                bundle2.putString("deviceId", orGenerateDeviceId);
                getDeviceIdEnd(activity, 0, bundle2, iFidoCallback);
                return;
            case 3:
                getDeviceIdEnd(activity, 109, null, iFidoCallback);
                return;
            default:
                getDeviceIdEnd(activity, 210, null, iFidoCallback);
                return;
        }
    }

    private static void getDeviceIdEnd(Activity activity, final int i2, final Bundle bundle, final IFidoCallback iFidoCallback) {
        TrackerUtil.tracker(activity, null, "", "getDeviceId_end_".concat(String.valueOf(i2)));
        TrackerUtil.appendResult(BasicInformation.GET_DEVICEID_RESULT, i2);
        TrackerUtil.tracker(activity, BasicInformation.GET_DEVICEID);
        activity.runOnUiThread(new Runnable() { // from class: com.jdcn.fido.service.GetDeviceIdService.1
            @Override // java.lang.Runnable
            public final void run() {
                iFidoCallback.response(i2, bundle);
            }
        });
    }
}
