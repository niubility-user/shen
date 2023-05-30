package com.jdcn.fido.service;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import cn.com.union.fido.FidoSDK;
import cn.com.union.fido.bean.uafclient.UAFMessage;
import cn.com.union.fido.ui.FIDOUISDK;
import cn.com.union.fido.ui.finger.FingerActivity;
import com.jdcn.fido.BuildConfig;
import com.jdcn.fido.constant.BasicInformation;
import com.jdcn.fido.http.HttpUrlUtil;
import com.jdcn.fido.http.HttpUtil;
import com.jdcn.fido.sdk.IFidoCallback;
import com.jdcn.fido.utils.Base64Util;
import com.jdcn.fido.utils.DeviceInfo;
import com.jdcn.fido.utils.FidoServiceUtil;
import com.jdcn.fido.utils.PackageUtil;
import com.jdcn.fido.utils.TrackerUtil;
import com.jdcn.fido.verification.FingerPresenter;
import com.jingdong.sdk.platform.business.personal.R2;
import com.tencent.mapsdk.internal.l4;
import org.json.JSONObject;

/* loaded from: classes18.dex */
public class RegisterService {
    private static UAFMessage getUAFMessage(Activity activity, String str, IFidoCallback iFidoCallback, boolean z) {
        UAFMessage uAFMessage = null;
        try {
            String packageName = PackageUtil.getPackageName(activity);
            if (TextUtils.isEmpty(packageName)) {
                Bundle bundle = new Bundle();
                bundle.putString("message", "packageName is empty");
                registerEnd(activity, 401, bundle, iFidoCallback);
            } else {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("userName", str);
                jSONObject.put("serialNumber", TrackerUtil.serialNumber);
                jSONObject.put(l4.f16791e, BuildConfig.fidoVersionName);
                jSONObject.put("appPackageName", packageName);
                UAFMessage handleResponse = handleResponse(HttpUtil.httpPost(HttpUrlUtil.getRequestUrl(z ? HttpUrlUtil.URL_FORCE_REGISTER : HttpUrlUtil.URL_REGISTER), jSONObject));
                if (handleResponse == null) {
                    try {
                        registerEnd(activity, 210, null, iFidoCallback);
                    } catch (Throwable unused) {
                        uAFMessage = handleResponse;
                        Bundle bundle2 = new Bundle();
                        bundle2.putString("message", "RegisterService->getUAFMessage exception");
                        registerEnd(activity, 401, bundle2, iFidoCallback);
                        return uAFMessage;
                    }
                }
                uAFMessage = handleResponse;
            }
        } catch (Throwable unused2) {
        }
        return uAFMessage;
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
        registerEnd(r9, 210, null, r11);
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x0069, code lost:
        registerEnd(r9, 208, null, r11);
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
            registerEnd(r9, r2, r3, r11)     // Catch: java.lang.Throwable -> L73
        L67:
            r1 = r10
            goto L76
        L69:
            r10 = 208(0xd0, float:2.91E-43)
            registerEnd(r9, r10, r3, r11)     // Catch: java.lang.Throwable -> L73
            goto L76
        L6f:
            registerEnd(r9, r2, r3, r11)     // Catch: java.lang.Throwable -> L73
            goto L76
        L73:
            registerEnd(r9, r2, r3, r11)
        L76:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jdcn.fido.service.RegisterService.handleFinalResponse(android.app.Activity, java.lang.String, com.jdcn.fido.sdk.IFidoCallback):java.lang.String");
    }

    private static UAFMessage handleResponse(String str) {
        try {
            if (TextUtils.isEmpty(str) || str.equals(HttpUtil.FAIL_ERROR_PARAM) || str.equals(HttpUtil.FAIL_HTTP_EXCEPTION)) {
                return null;
            }
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.getString("response").equals("fidoreg")) {
                String decodeBase64String = Base64Util.decodeBase64String(jSONObject.getString("fidoreg"));
                UAFMessage uAFMessage = new UAFMessage();
                try {
                    uAFMessage.uafProtocolMessage = decodeBase64String;
                } catch (Throwable unused) {
                }
                return uAFMessage;
            }
            return null;
        } catch (Throwable unused2) {
            return null;
        }
    }

    private static void multiUser(Activity activity, Bundle bundle, IFidoCallback iFidoCallback) {
        try {
            FingerPresenter fingerPresenter = new FingerPresenter();
            FIDOUISDK.setFidoPresenter(fingerPresenter);
            Intent intent = new Intent();
            intent.setClass(activity, FingerActivity.class);
            synchronized (fingerPresenter) {
                activity.startActivityForResult(intent, R2.drawable.author_has_followed_img);
                while (fingerPresenter.getFingerModel().getResponseCoded() == -1) {
                    fingerPresenter.wait();
                }
            }
            int responseCoded = fingerPresenter.getFingerModel().getResponseCoded();
            if (responseCoded == 0) {
                processMultiUser(activity, bundle, iFidoCallback);
            } else {
                registerEnd(activity, responseCoded, null, iFidoCallback);
            }
        } catch (InterruptedException unused) {
            activity.finishActivity(R2.drawable.author_has_followed_img);
            registerEnd(activity, 206, null, iFidoCallback);
        } finally {
            FIDOUISDK.setFidoPresenter(null);
        }
    }

    private static void processMultiUser(Activity activity, Bundle bundle, IFidoCallback iFidoCallback) {
        try {
            String packageName = PackageUtil.getPackageName(activity);
            if (packageName == null) {
                Bundle bundle2 = new Bundle();
                bundle2.putString("message", "packageName is empty");
                registerEnd(activity, 401, bundle2, iFidoCallback);
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
            jSONObject.put("userName", string5);
            jSONObject.put("appPackageName", packageName);
            jSONObject.put("serialNumber", TrackerUtil.serialNumber);
            String handleFinalResponse = handleFinalResponse(activity, HttpUtil.httpPostEncode(activity, HttpUrlUtil.getRequestUrl(HttpUrlUtil.URL_REGISTER3), jSONObject), iFidoCallback);
            if (TextUtils.isEmpty(handleFinalResponse)) {
                return;
            }
            Bundle bundle3 = new Bundle();
            bundle3.putString("challenge", handleFinalResponse);
            bundle3.putString("deviceId", string5);
            bundle3.putString("appId", packageName);
            registerEnd(activity, 0, bundle3, iFidoCallback);
        } catch (Throwable unused) {
            Bundle bundle4 = new Bundle();
            bundle4.putString("message", "register->processMultiUser");
            registerEnd(activity, 401, bundle4, iFidoCallback);
        }
    }

    private static void processUAFRequest(Activity activity, Bundle bundle, UAFMessage uAFMessage, IFidoCallback iFidoCallback) {
        UAFMessage uAFMessage2 = new UAFMessage();
        FidoSDK fidoSDK = FidoSDK.getInstance(activity);
        short sendUAFMessage = fidoSDK.sendUAFMessage(uAFMessage, uAFMessage2, null, null);
        fidoSDK.release();
        if (sendUAFMessage == 0) {
            processUAFResponse(activity, bundle, uAFMessage2, iFidoCallback);
        } else if (sendUAFMessage != 5) {
            registerEnd(activity, Short.valueOf(FidoServiceUtil.getStatusCode(sendUAFMessage)).shortValue(), null, iFidoCallback);
        } else {
            TrackerUtil.append(BasicInformation.SCENE_REG_MULTI);
            multiUser(activity, bundle, iFidoCallback);
        }
    }

    private static void processUAFResponse(Activity activity, Bundle bundle, UAFMessage uAFMessage, IFidoCallback iFidoCallback) {
        try {
            String packageName = PackageUtil.getPackageName(activity);
            if (packageName == null) {
                Bundle bundle2 = new Bundle();
                bundle2.putString("message", "packageName is empty");
                registerEnd(activity, 401, bundle2, iFidoCallback);
                return;
            }
            String str = uAFMessage.uafProtocolMessage;
            if (TextUtils.isEmpty(str)) {
                Bundle bundle3 = new Bundle();
                bundle3.putString("message", "uafResponse is empty");
                registerEnd(activity, 401, bundle3, iFidoCallback);
                return;
            }
            String string = bundle.containsKey("pin") ? bundle.getString("pin") : "";
            String string2 = bundle.containsKey("A2") ? bundle.getString("A2") : "";
            String string3 = bundle.containsKey("eytPin") ? bundle.getString("eytPin") : "";
            String string4 = bundle.containsKey("visa") ? bundle.getString("visa") : "";
            String string5 = bundle.getString("userName");
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("pin", string);
            jSONObject.put("a2", string2);
            jSONObject.put("eytPin", string3);
            jSONObject.put("visa", string4);
            jSONObject.put("resp", str);
            jSONObject.put("userName", string5);
            jSONObject.put("appPackageName", packageName);
            jSONObject.put("serialNumber", TrackerUtil.serialNumber);
            jSONObject.put("deviceInfo", DeviceInfo.getDeviceInfo(activity));
            String handleFinalResponse = handleFinalResponse(activity, HttpUtil.httpPostEncode(activity, HttpUrlUtil.getRequestUrl(HttpUrlUtil.URL_REGISTER2), jSONObject), iFidoCallback);
            if (TextUtils.isEmpty(handleFinalResponse)) {
                return;
            }
            Bundle bundle4 = new Bundle();
            bundle4.putString("challenge", handleFinalResponse);
            bundle4.putString("deviceId", string5);
            bundle4.putString("appId", packageName);
            registerEnd(activity, 0, bundle4, iFidoCallback);
        } catch (Throwable unused) {
            Bundle bundle5 = new Bundle();
            bundle5.putString("message", "register->processUAFResponse");
            registerEnd(activity, 401, bundle5, iFidoCallback);
        }
    }

    public static void regist(Activity activity, Bundle bundle, IFidoCallback iFidoCallback, boolean z) {
        String userName = FidoServiceUtil.getUserName(activity, bundle);
        TrackerUtil.tracker(activity, null, "", "regist_run_003");
        bundle.putString("userName", userName);
        FidoServiceUtil.handleBundle(bundle, iFidoCallback);
        UAFMessage uAFMessage = getUAFMessage(activity, userName, iFidoCallback, z);
        TrackerUtil.tracker(activity, null, "", "regist_run_004");
        if (uAFMessage != null) {
            if (TextUtils.equals(Thread.currentThread().getName(), BasicInformation.THREAD_NAME)) {
                registerEnd(activity, 206, null, iFidoCallback);
                return;
            }
            TrackerUtil.tracker(activity, null, "", "regist_run_005");
            processUAFRequest(activity, bundle, uAFMessage, iFidoCallback);
        }
    }

    private static void registerEnd(Activity activity, final int i2, final Bundle bundle, final IFidoCallback iFidoCallback) {
        TrackerUtil.tracker(activity, null, "", "regist_end_".concat(String.valueOf(i2)));
        FIDOUISDK.paramsReset();
        TrackerUtil.appendResult(BasicInformation.SCENE_REG_RESULT, i2);
        TrackerUtil.tracker(activity, BasicInformation.SCENE_REG);
        activity.runOnUiThread(new Runnable() { // from class: com.jdcn.fido.service.RegisterService.1
            @Override // java.lang.Runnable
            public final void run() {
                iFidoCallback.response(i2, bundle);
            }
        });
    }
}
