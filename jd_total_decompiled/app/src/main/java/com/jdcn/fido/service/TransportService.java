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

    /* JADX WARN: Code restructure failed: missing block: B:104:0x0043, code lost:
        if (r4 == 1) goto L115;
     */
    /* JADX WARN: Code restructure failed: missing block: B:105:0x0045, code lost:
        if (r4 == 2) goto L114;
     */
    /* JADX WARN: Code restructure failed: missing block: B:106:0x0047, code lost:
        r4 = new org.json.JSONObject(r10);
     */
    /* JADX WARN: Code restructure failed: missing block: B:107:0x0056, code lost:
        if (r4.getString("response").equals("challenge") == false) goto L109;
     */
    /* JADX WARN: Code restructure failed: missing block: B:108:0x0058, code lost:
        r10 = r4.getString("challenge");
     */
    /* JADX WARN: Code restructure failed: missing block: B:109:0x005d, code lost:
        r10 = "";
     */
    /* JADX WARN: Code restructure failed: missing block: B:111:0x0062, code lost:
        if (android.text.TextUtils.isEmpty(r10) == false) goto L113;
     */
    /* JADX WARN: Code restructure failed: missing block: B:112:0x0064, code lost:
        authenticateEnd(r9, 210, null, r11);
     */
    /* JADX WARN: Code restructure failed: missing block: B:114:0x0069, code lost:
        authenticateEnd(r9, 208, null, r11);
     */
    /* JADX WARN: Code restructure failed: missing block: B:120:?, code lost:
        return r10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:121:?, code lost:
        return "";
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static String handleFinalResponse(Activity activity, String str, IFidoCallback iFidoCallback) {
        try {
            if (!TextUtils.isEmpty(str)) {
                char c2 = '\uffff';
                int hashCode = str.hashCode();
                if (hashCode != -225079175) {
                    if (hashCode != 379436981) {
                        if (hashCode == 626565632 && str.equals(HttpUtil.FAIL_CRYPTO_ERROR)) {
                            c2 = 2;
                        }
                    } else if (str.equals(HttpUtil.FAIL_ERROR_PARAM)) {
                        c2 = 0;
                    }
                } else if (str.equals(HttpUtil.FAIL_HTTP_EXCEPTION)) {
                    c2 = 1;
                }
            }
            authenticateEnd(activity, 210, null, iFidoCallback);
            return "";
        } catch (Throwable unused) {
            authenticateEnd(activity, 210, null, iFidoCallback);
            return "";
        }
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
