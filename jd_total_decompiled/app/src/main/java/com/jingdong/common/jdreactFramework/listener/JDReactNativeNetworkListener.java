package com.jingdong.common.jdreactFramework.listener;

import android.app.Activity;
import com.jingdong.common.jdflutter.JDFlutterCall;
import com.jingdong.common.jdflutter.JDFlutterCallResult;
import com.jingdong.common.jdreactFramework.JDCallback;
import com.jingdong.common.jdreactFramework.JDReactHelper;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.sdk.oklog.OKLog;
import java.util.HashMap;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class JDReactNativeNetworkListener implements NativeNetworkListener, JDFlutterCall {
    private static final String FETCH_DATA_ENC = "enc";
    private static final String FETCH_DATA_FASTJSON = "fastjson";
    private static final String FETCH_DATA_KEY_APPID = "customAppId";
    private static final String FETCH_DATA_KEY_CUSTOM_KOOKIE = "extraCookie";
    private static final String FETCH_DATA_KEY_EXTEND_PARAMS_JSON = "extendParams";
    private static final String FETCH_DATA_KEY_FUNCTION_ID = "function_id";
    private static final String FETCH_DATA_KEY_HOST = "host";
    private static final String FETCH_DATA_KEY_HOST_BETA = "host_beta";
    private static final String FETCH_DATA_KEY_MOVIES_PLAYTYPE = "play_type";
    private static final String FETCH_DATA_KEY_PARAMS_JSON = "params_json";
    private static final String FETCH_DATA_KEY_SECRETKEY = "customSecretKey";
    private static final String FETCH_DATA_KEY_URL = "url";
    private static final String FETCH_DATA_METHOD = "method";
    private static final String FETCH_DATA_TIME_OUT = "timeout";
    private static final String FETCH_EP_PARAMS = "epParams";
    private static final String FETCH_HEAD_KEY = "head";
    private static final String FETCH_PARAMS = "params";
    private static final String FETCH_RETRY_BUSINESS_LAYER = "needRetryNet";
    private static final String FETCH_RETRY_NETWORK_LAYER = "needRetryBus";
    private static final String FETCH_USE_HTTPS = "use_https";
    private static final String FETCH_USE_HTTP_ERR = "usehttpError";
    private static final String FUNCTION_UPDATE_REMAIN_TICKETS = "updateRemainTickets";
    public static final int HTTP_TIMEOUT = 30000;
    public static final String NETWORKCHANNEL = "com.jd.jdflutter/network";
    private static final int NORMAL_ERROR_CODE = 0;
    private static final int NORMAL_SUCCESS_CODE = 1;
    private static final String PREFS_BETA_MODE_KEY = "jdreact_beta_mode_debug";
    private static final String TAG = "JDReactNativeNetworkModule";

    private void updateRemainTickets(JSONObject jSONObject) {
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setHost(Configuration.getVirtualHost());
        httpSetting.setFunctionId(FUNCTION_UPDATE_REMAIN_TICKETS);
        httpSetting.setJsonParams(jSONObject);
        httpSetting.setNotifyUser(false);
        httpSetting.setEffect(1);
        httpSetting.setAttempts(1);
        httpSetting.setListener(new HttpGroup.OnCommonListener() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeNetworkListener.2
            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
            public void onEnd(HttpResponse httpResponse) {
                OKLog.d(JDReactNativeNetworkListener.TAG, "updateRemainTickets sucess!!!");
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
            public void onError(HttpError httpError) {
                OKLog.d(JDReactNativeNetworkListener.TAG, "updateRemainTickets failed!!!");
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnReadyListener
            public void onReady(HttpGroup.HttpSettingParams httpSettingParams) {
            }
        });
        HttpGroupUtils.getHttpGroupaAsynPool().add(httpSetting);
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Unreachable block: B:42:0x0158
        	at jadx.core.dex.visitors.blocks.BlockProcessor.checkForUnreachableBlocks(BlockProcessor.java:81)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:47)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:39)
        */
    @Override // com.jingdong.common.jdreactFramework.listener.NativeNetworkListener
    public void fetch(java.util.HashMap r27, com.jingdong.common.jdreactFramework.JDCallback r28, com.jingdong.common.jdreactFramework.JDCallback r29) {
        /*
            Method dump skipped, instructions count: 1058
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.jdreactFramework.listener.JDReactNativeNetworkListener.fetch(java.util.HashMap, com.jingdong.common.jdreactFramework.JDCallback, com.jingdong.common.jdreactFramework.JDCallback):void");
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeNetworkListener
    public void fetchWithoutCertificate(HashMap hashMap, JDCallback jDCallback, JDCallback jDCallback2) {
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeNetworkListener
    public boolean isBeta() {
        return JDReactHelper.newInstance().isBeta();
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeNetworkListener
    public boolean isBetaHost() {
        try {
            return Configuration.isBeta();
        } catch (Exception e2) {
            OKLog.e(TAG, e2);
            return false;
        }
    }

    @Override // com.jingdong.common.jdflutter.JDFlutterCall
    public void onMethodCall(String str, HashMap hashMap, final JDFlutterCallResult jDFlutterCallResult, Activity activity) {
        if (str.equals("fetch")) {
            fetch(hashMap, new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeNetworkListener.3
                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    OKLog.d(JDReactNativeNetworkListener.TAG, "invoke...success" + objArr[0].toString());
                    jDFlutterCallResult.success(objArr[0].toString());
                }
            }, new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeNetworkListener.4
                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    OKLog.d(JDReactNativeNetworkListener.TAG, "invoke...error" + objArr[0].toString());
                    jDFlutterCallResult.error("", "", "");
                }
            });
        } else if (str.equals("isBetaHost")) {
            jDFlutterCallResult.success(Boolean.valueOf(isBetaHost()));
        } else if (str.equals("fetchWithoutCertificate")) {
            fetchWithoutCertificate(hashMap, new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeNetworkListener.5
                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.success(objArr[0].toString());
                }
            }, new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeNetworkListener.6
                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.error("", "", "");
                }
            });
        }
    }
}
