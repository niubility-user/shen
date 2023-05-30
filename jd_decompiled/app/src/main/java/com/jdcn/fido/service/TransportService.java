package com.jdcn.fido.service;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import cn.com.union.fido.FidoSDK;
import cn.com.union.fido.bean.uafclient.UAFMessage;
import cn.com.union.fido.ui.FIDOUISDK;
import com.jd.dynamic.DYConstants;
import com.jd.lib.productdetail.core.protocol.PdExceptionReportProtocol;
import com.jdcn.fido.BuildConfig;
import com.jdcn.fido.constant.BasicInformation;
import com.jdcn.fido.http.HttpUrlUtil;
import com.jdcn.fido.http.HttpUtil;
import com.jdcn.fido.sdk.IFidoCallback;
import com.jdcn.fido.utils.Base64Util;
import com.jdcn.fido.utils.FidoServiceUtil;
import com.jdcn.fido.utils.FingerDeviceIdManger;
import com.jdcn.fido.utils.PackageUtil;
import com.jdcn.fido.utils.TrackerUtil;
import com.tencent.mapsdk.internal.l4;
import org.json.JSONObject;

/* loaded from: classes18.dex */
public class TransportService {
    private static void authenticateEnd(Activity activity, final int i2, final Bundle bundle, final IFidoCallback iFidoCallback) {
        TrackerUtil.tracker(activity, null, "", "transport_end_".concat(String.valueOf(i2)));
        FIDOUISDK.paramsReset();
        TrackerUtil.appendResult(BasicInformation.SCENE_TRANS_RESULT, i2);
        TrackerUtil.tracker(activity, BasicInformation.SCENE_TRANS);
        activity.runOnUiThread(new Runnable() { // from class: com.jdcn.fido.service.TransportService.1
            @Override // java.lang.Runnable
            public final void run() {
                iFidoCallback.response(i2, bundle);
            }
        });
    }

    private static UAFMessage getUAFMessage(Activity activity, String str, IFidoCallback iFidoCallback) {
        UAFMessage uAFMessage = null;
        try {
            String packageName = PackageUtil.getPackageName(activity);
            if (TextUtils.isEmpty(packageName)) {
                Bundle bundle = new Bundle();
                bundle.putString("message", "packageName is empty");
                authenticateEnd(activity, 401, bundle, iFidoCallback);
            } else {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("appPackageName", packageName);
                jSONObject.put("userName", str);
                jSONObject.put("transaction", DYConstants.DY_TRUE);
                jSONObject.put("stepup", DYConstants.DY_TRUE);
                jSONObject.put("serialNumber", TrackerUtil.serialNumber);
                jSONObject.put(l4.f16791e, BuildConfig.fidoVersionName);
                UAFMessage handleResponse = handleResponse(HttpUtil.httpPost(HttpUrlUtil.getRequestUrl(HttpUrlUtil.URL_TRANSPORT), jSONObject));
                if (handleResponse == null) {
                    try {
                        authenticateEnd(activity, 210, null, iFidoCallback);
                    } catch (Throwable unused) {
                        uAFMessage = handleResponse;
                        Bundle bundle2 = new Bundle();
                        bundle2.putString("message", "TransportService->transport->getUAFMessage exception");
                        authenticateEnd(activity, 401, bundle2, iFidoCallback);
                        return uAFMessage;
                    }
                }
                uAFMessage = handleResponse;
            }
        } catch (Throwable unused2) {
        }
        return uAFMessage;
    }

    private static void gotoRegister(Activity activity, Bundle bundle, IFidoCallback iFidoCallback) {
        RegisterService.regist(activity, bundle, iFidoCallback, true);
    }

    /* JADX WARN: Code restructure failed: missing block: B:63:0x0043, code lost:
        if (r4 == 1) goto L74;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x0045, code lost:
        if (r4 == 2) goto L73;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x0047, code lost:
        r4 = new org.json.JSONObject(r10);
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x0056, code lost:
        if (r4.getString("response").equals("challenge") == false) goto L68;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x0058, code lost:
        r10 = r4.getString("challenge");
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x005d, code lost:
        r10 = "";
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x0062, code lost:
        if (android.text.TextUtils.isEmpty(r10) == false) goto L72;
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x0064, code lost:
        authenticateEnd(r9, 210, null, r11);
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x0069, code lost:
        authenticateEnd(r9, 208, null, r11);
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:?, code lost:
        return r10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:80:?, code lost:
        return "";
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.lang.String handleFinalResponse(android.app.Activity r9, java.lang.String r10, com.jdcn.fido.sdk.IFidoCallback r11) {
        /*
            java.lang.String r0 = "challenge"
            java.lang.String r1 = ""
            r2 = 210(0xd2, float:2.94E-43)
            r3 = 0
            boolean r4 = android.text.TextUtils.isEmpty(r10)     // Catch: java.lang.Throwable -> L73
            if (r4 != 0) goto L6f
            r4 = -1
            int r5 = r10.hashCode()     // Catch: java.lang.Throwable -> L73
            r6 = -225079175(0xfffffffff2959079, float:-5.924854E30)
            r7 = 2
            r8 = 1
            if (r5 == r6) goto L38
            r6 = 379436981(0x169dbfb5, float:2.5485702E-25)
            if (r5 == r6) goto L2e
            r6 = 626565632(0x2558a200, float:1.8789901E-16)
            if (r5 == r6) goto L24
            goto L41
        L24:
            java.lang.String r5 = "FAIL_ENCODE_ERROR"
            boolean r5 = r10.equals(r5)     // Catch: java.lang.Throwable -> L73
            if (r5 == 0) goto L41
            r4 = 2
            goto L41
        L2e:
            java.lang.String r5 = "FAIL_ERROR_PARAM"
            boolean r5 = r10.equals(r5)     // Catch: java.lang.Throwable -> L73
            if (r5 == 0) goto L41
            r4 = 0
            goto L41
        L38:
            java.lang.String r5 = "FAIL_HTTP_EXCEPTION"
            boolean r5 = r10.equals(r5)     // Catch: java.lang.Throwable -> L73
            if (r5 == 0) goto L41
            r4 = 1
        L41:
            if (r4 == 0) goto L6f
            if (r4 == r8) goto L6f
            if (r4 == r7) goto L69
            org.json.JSONObject r4 = new org.json.JSONObject     // Catch: java.lang.Throwable -> L73
            r4.<init>(r10)     // Catch: java.lang.Throwable -> L73
            java.lang.String r10 = "response"
            java.lang.String r10 = r4.getString(r10)     // Catch: java.lang.Throwable -> L73
            boolean r10 = r10.equals(r0)     // Catch: java.lang.Throwable -> L73
            if (r10 == 0) goto L5d
            java.lang.String r10 = r4.getString(r0)     // Catch: java.lang.Throwable -> L73
            goto L5e
        L5d:
            r10 = r1
        L5e:
            boolean r0 = android.text.TextUtils.isEmpty(r10)     // Catch: java.lang.Throwable -> L73
            if (r0 == 0) goto L67
            authenticateEnd(r9, r2, r3, r11)     // Catch: java.lang.Throwable -> L73
        L67:
            r1 = r10
            goto L76
        L69:
            r10 = 208(0xd0, float:2.91E-43)
            authenticateEnd(r9, r10, r3, r11)     // Catch: java.lang.Throwable -> L73
            goto L76
        L6f:
            authenticateEnd(r9, r2, r3, r11)     // Catch: java.lang.Throwable -> L73
            goto L76
        L73:
            authenticateEnd(r9, r2, r3, r11)
        L76:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jdcn.fido.service.TransportService.handleFinalResponse(android.app.Activity, java.lang.String, com.jdcn.fido.sdk.IFidoCallback):java.lang.String");
    }

    private static UAFMessage handleResponse(String str) {
        try {
            if (TextUtils.isEmpty(str) || str.equals(HttpUtil.FAIL_ERROR_PARAM) || str.equals(HttpUtil.FAIL_HTTP_EXCEPTION)) {
                return null;
            }
            JSONObject jSONObject = new JSONObject(str);
            String string = jSONObject.getString("response");
            UAFMessage uAFMessage = new UAFMessage();
            if (string.equals("fidoauth")) {
                uAFMessage.uafProtocolMessage = Base64Util.decodeBase64String(jSONObject.getString("fidoauth"));
            } else {
                uAFMessage.additionalData = jSONObject.getString("error");
            }
            return uAFMessage;
        } catch (Throwable unused) {
            return null;
        }
    }

    private static void processUAFRequest(Activity activity, Bundle bundle, UAFMessage uAFMessage, IFidoCallback iFidoCallback) {
        if (!TextUtils.isEmpty(uAFMessage.additionalData)) {
            if ("1500".equals(uAFMessage.additionalData)) {
                TrackerUtil.append(BasicInformation.SCENE_TRANS_TO_REG_1500);
                gotoRegister(activity, bundle, iFidoCallback);
                return;
            } else if (PdExceptionReportProtocol.RequestParams.ERROR_CODE_INVALID_FLOORS.equals(uAFMessage.additionalData)) {
                updateDeviceId(activity, bundle, iFidoCallback);
                return;
            } else {
                authenticateEnd(activity, 210, null, iFidoCallback);
                return;
            }
        }
        UAFMessage uAFMessage2 = new UAFMessage();
        FidoSDK fidoSDK = FidoSDK.getInstance(activity);
        short sendUAFMessage = fidoSDK.sendUAFMessage(uAFMessage, uAFMessage2, null, null);
        fidoSDK.release();
        if (sendUAFMessage == 0) {
            processUAFResponse(activity, bundle, uAFMessage2, iFidoCallback);
        } else if (sendUAFMessage == 8) {
            TrackerUtil.append(BasicInformation.SCENE_TRANS_TO_REG_AUTHEN);
            gotoRegister(activity, bundle, iFidoCallback);
        } else if (sendUAFMessage != 18) {
            authenticateEnd(activity, Short.valueOf(FidoServiceUtil.getStatusCode(sendUAFMessage)).shortValue(), null, iFidoCallback);
        } else {
            TrackerUtil.append(BasicInformation.SCENE_TRANS_TO_REG_FINGER);
            gotoRegister(activity, bundle, iFidoCallback);
        }
    }

    private static void processUAFResponse(Activity activity, Bundle bundle, UAFMessage uAFMessage, IFidoCallback iFidoCallback) {
        try {
            String packageName = PackageUtil.getPackageName(activity);
            if (packageName == null) {
                Bundle bundle2 = new Bundle();
                bundle2.putString("message", "packageName is empty");
                authenticateEnd(activity, 401, bundle2, iFidoCallback);
                return;
            }
            String str = uAFMessage.uafProtocolMessage;
            if (TextUtils.isEmpty(str)) {
                Bundle bundle3 = new Bundle();
                bundle3.putString("message", "uafResponse is empty");
                authenticateEnd(activity, 401, bundle3, iFidoCallback);
                return;
            }
            JSONObject jSONObject = new JSONObject();
            String string = bundle.containsKey("pin") ? bundle.getString("pin") : "";
            String string2 = bundle.containsKey("A2") ? bundle.getString("A2") : "";
            String string3 = bundle.containsKey("eytPin") ? bundle.getString("eytPin") : "";
            String string4 = bundle.containsKey("visa") ? bundle.getString("visa") : "";
            String string5 = bundle.getString("userName");
            jSONObject.put("pin", string);
            jSONObject.put("a2", string2);
            jSONObject.put("eytPin", string3);
            jSONObject.put("visa", string4);
            jSONObject.put("resp", str);
            jSONObject.put("userName", string5);
            jSONObject.put("appPackageName", packageName);
            jSONObject.put("appPackageName", packageName);
            jSONObject.put("serialNumber", TrackerUtil.serialNumber);
            String handleFinalResponse = handleFinalResponse(activity, HttpUtil.httpPostEncode(activity, HttpUrlUtil.getRequestUrl(HttpUrlUtil.URL_TRANSPORT2), jSONObject), iFidoCallback);
            if (TextUtils.isEmpty(handleFinalResponse)) {
                return;
            }
            Bundle bundle4 = new Bundle();
            bundle4.putString("challenge", handleFinalResponse);
            bundle4.putString("deviceId", string5);
            bundle4.putString("appId", packageName);
            authenticateEnd(activity, 0, bundle4, iFidoCallback);
        } catch (Throwable unused) {
            Bundle bundle5 = new Bundle();
            bundle5.putString("message", "TransportService->transport->processUAFResponse exception");
            authenticateEnd(activity, 401, bundle5, iFidoCallback);
        }
    }

    public static void transport(Activity activity, Bundle bundle, IFidoCallback iFidoCallback) {
        String userName = FidoServiceUtil.getUserName(activity, bundle);
        TrackerUtil.tracker(activity, null, "", "transport_run_003");
        bundle.putString("userName", userName);
        FidoServiceUtil.handleBundle(bundle, iFidoCallback);
        int hasKeyEntitByUserName = FidoServiceUtil.hasKeyEntitByUserName(activity, userName);
        TrackerUtil.tracker(activity, null, "", "transport_run_004");
        if (hasKeyEntitByUserName == -1) {
            authenticateEnd(activity, 401, null, iFidoCallback);
        } else if (hasKeyEntitByUserName == 0) {
            TrackerUtil.tracker(activity, null, "", "transport_run_005");
            TrackerUtil.append(BasicInformation.SCENE_TRANS_TO_REG_KEY);
            gotoRegister(activity, bundle, iFidoCallback);
        } else {
            TrackerUtil.tracker(activity, null, "", "transport_run_006");
            UAFMessage uAFMessage = getUAFMessage(activity, userName, iFidoCallback);
            if (uAFMessage != null) {
                TrackerUtil.tracker(activity, null, "", "transport_run_007");
                if (TextUtils.equals(Thread.currentThread().getName(), BasicInformation.THREAD_NAME)) {
                    authenticateEnd(activity, 206, null, iFidoCallback);
                } else {
                    processUAFRequest(activity, bundle, uAFMessage, iFidoCallback);
                }
            }
        }
    }

    private static void updateDeviceId(Activity activity, Bundle bundle, IFidoCallback iFidoCallback) {
        if (!TextUtils.isEmpty(FingerDeviceIdManger.updateDeviceId(activity))) {
            TrackerUtil.append(BasicInformation.SCENE_TRANS_TO_REG_40005);
            gotoRegister(activity, bundle, iFidoCallback);
            return;
        }
        Bundle bundle2 = new Bundle();
        bundle2.putString("message", "deviceId == null");
        authenticateEnd(activity, 401, bundle2, iFidoCallback);
    }
}
