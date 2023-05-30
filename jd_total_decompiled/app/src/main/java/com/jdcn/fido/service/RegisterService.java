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
        registerEnd(r9, 210, null, r11);
     */
    /* JADX WARN: Code restructure failed: missing block: B:114:0x0069, code lost:
        registerEnd(r9, 208, null, r11);
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
            registerEnd(activity, 210, null, iFidoCallback);
            return "";
        } catch (Throwable unused) {
            registerEnd(activity, 210, null, iFidoCallback);
            return "";
        }
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
