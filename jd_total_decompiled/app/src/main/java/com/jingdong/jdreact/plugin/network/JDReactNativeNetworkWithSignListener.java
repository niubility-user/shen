package com.jingdong.jdreact.plugin.network;

import android.text.TextUtils;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.jdreactFramework.JDCallback;
import com.jingdong.common.jdreactFramework.listener.NativeNetworkWithSignListener;
import com.jingdong.common.jdreactFramework.utils.JLog;
import com.jingdong.common.jdreactFramework.utils.NetConfig;
import com.jingdong.jdreact.plugin.network.ApiUrl;
import com.jingdong.manto.sdk.api.IMantoServerRequester;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.HashSet;
import okhttp3.Call;

/* loaded from: classes13.dex */
public class JDReactNativeNetworkWithSignListener implements NativeNetworkWithSignListener {
    private static final String FETCH_DATA_KEY_APPID = "customAppId";
    private static final String FETCH_DATA_KEY_BETA_HOST = "beta_host";
    private static final String FETCH_DATA_KEY_BETA_HOST_BACKUP = "host_beta";
    private static final String FETCH_DATA_KEY_CUSTOM_KOOKIE = "extraCookie";
    private static final String FETCH_DATA_KEY_EXTEND_PARAMS_JSON = "extendParams";
    private static final String FETCH_DATA_KEY_FUNCTION_ID = "function_id";
    private static final String FETCH_DATA_KEY_HOST = "host";
    private static final String FETCH_DATA_KEY_PARAMS_JSON = "params_json";
    private static final String FETCH_DATA_KEY_SECRETKEY = "customSecretKey";
    private static final String FETCH_DATA_METHOD = "method";
    private static final String FETCH_HEAD_KEY = "head";
    private static final String FETCH_PARAMS_KEY = "params";
    private static final String FETCH_USE_HTTPS_KEY = "use_https";
    private static final String TAG = "JDReactNetworkWithSign";
    private LoginHelper loginHelper;

    public JDReactNativeNetworkWithSignListener(LoginHelper loginHelper) {
        this(loginHelper, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x0246  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x027d  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x01a4 A[Catch: Exception -> 0x0280, TRY_LEAVE, TryCatch #0 {Exception -> 0x0280, blocks: (B:81:0x019e, B:83:0x01a4, B:85:0x01aa, B:89:0x01b5, B:91:0x01bb, B:102:0x0248, B:104:0x024d, B:105:0x0250, B:107:0x0256, B:108:0x0259, B:110:0x025f, B:112:0x0266, B:113:0x026b, B:92:0x01db, B:88:0x01b2, B:93:0x01f7, B:95:0x01fd, B:97:0x0203, B:98:0x021f, B:99:0x0237), top: B:127:0x019e, inners: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:95:0x01fd A[Catch: Exception -> 0x0280, TryCatch #0 {Exception -> 0x0280, blocks: (B:81:0x019e, B:83:0x01a4, B:85:0x01aa, B:89:0x01b5, B:91:0x01bb, B:102:0x0248, B:104:0x024d, B:105:0x0250, B:107:0x0256, B:108:0x0259, B:110:0x025f, B:112:0x0266, B:113:0x026b, B:92:0x01db, B:88:0x01b2, B:93:0x01f7, B:95:0x01fd, B:97:0x0203, B:98:0x021f, B:99:0x0237), top: B:127:0x019e, inners: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0237 A[Catch: Exception -> 0x0280, TryCatch #0 {Exception -> 0x0280, blocks: (B:81:0x019e, B:83:0x01a4, B:85:0x01aa, B:89:0x01b5, B:91:0x01bb, B:102:0x0248, B:104:0x024d, B:105:0x0250, B:107:0x0256, B:108:0x0259, B:110:0x025f, B:112:0x0266, B:113:0x026b, B:92:0x01db, B:88:0x01b2, B:93:0x01f7, B:95:0x01fd, B:97:0x0203, B:98:0x021f, B:99:0x0237), top: B:127:0x019e, inners: #1 }] */
    @Override // com.jingdong.common.jdreactFramework.listener.NativeNetworkWithSignListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void fetch(HashMap hashMap, final JDCallback jDCallback, final JDCallback jDCallback2) {
        String str;
        String str2;
        HashMap hashMap2;
        String str3;
        String str4;
        boolean equals;
        ApiUrl apiUrl;
        JLog.d(TAG, "invoke fetch method. data = " + hashMap + "\uff0c okCB = " + jDCallback + ", errorCB = " + jDCallback2);
        if (hashMap != null && jDCallback != null && jDCallback2 != null) {
            String str5 = (String) hashMap.get("function_id");
            String str6 = "";
            String str7 = hashMap.containsKey("host") ? (String) hashMap.get("host") : "";
            if (NetConfig.sBeta) {
                str7 = hashMap.containsKey(FETCH_DATA_KEY_BETA_HOST) ? (String) hashMap.get(FETCH_DATA_KEY_BETA_HOST) : "";
                if (TextUtils.isEmpty(str7)) {
                    str7 = hashMap.containsKey(FETCH_DATA_KEY_BETA_HOST_BACKUP) ? (String) hashMap.get(FETCH_DATA_KEY_BETA_HOST_BACKUP) : "";
                }
            }
            HashMap hashMap3 = hashMap.containsKey("head") ? (HashMap) hashMap.get("head") : null;
            HashMap hashMap4 = hashMap.containsKey("params") ? (HashMap) hashMap.get("params") : null;
            String str8 = hashMap.containsKey("method") ? (String) hashMap.get("method") : IMantoServerRequester.POST;
            HashMap hashMap5 = hashMap.containsKey(FETCH_DATA_KEY_EXTEND_PARAMS_JSON) ? (HashMap) hashMap.get(FETCH_DATA_KEY_EXTEND_PARAMS_JSON) : null;
            if (hashMap5 != null && hashMap5.containsKey(FETCH_DATA_KEY_APPID)) {
                str = (String) hashMap5.get(FETCH_DATA_KEY_APPID);
            } else {
                str = hashMap.containsKey(FETCH_DATA_KEY_APPID) ? (String) hashMap.get(FETCH_DATA_KEY_APPID) : null;
            }
            if (hashMap5 != null && hashMap5.containsKey(FETCH_DATA_KEY_SECRETKEY)) {
                str2 = (String) hashMap5.get(FETCH_DATA_KEY_SECRETKEY);
            } else {
                str2 = hashMap.containsKey(FETCH_DATA_KEY_SECRETKEY) ? (String) hashMap.get(FETCH_DATA_KEY_SECRETKEY) : null;
            }
            if (hashMap5 != null && hashMap5.containsKey(FETCH_DATA_KEY_CUSTOM_KOOKIE)) {
                hashMap2 = (HashMap) hashMap5.get(FETCH_DATA_KEY_CUSTOM_KOOKIE);
            } else {
                hashMap2 = hashMap.containsKey(FETCH_DATA_KEY_CUSTOM_KOOKIE) ? (HashMap) hashMap.get(FETCH_DATA_KEY_CUSTOM_KOOKIE) : null;
            }
            LoginHelper loginHelper = this.loginHelper;
            if (loginHelper != null) {
                str6 = loginHelper.getPin();
                str3 = this.loginHelper.getA2();
            } else {
                str3 = "";
            }
            String str9 = (String) hashMap.get(FETCH_DATA_KEY_PARAMS_JSON);
            HashMap hashMap6 = hashMap2;
            try {
                if (hashMap.containsKey(FETCH_USE_HTTPS_KEY)) {
                    try {
                        str4 = str2;
                    } catch (Exception unused) {
                        str4 = str2;
                    }
                    try {
                        equals = hashMap.get(FETCH_USE_HTTPS_KEY).equals("1");
                    } catch (Exception unused2) {
                        JLog.d(TAG, "use_https = " + hashMap.get(FETCH_USE_HTTPS_KEY));
                        equals = true;
                        JLog.d(TAG, "functionId = " + str5 + ", loginType = " + NetConfig.sLoginType + ", host = " + str7 + ", params_json = " + str9 + ", use_https = " + equals);
                        if (TextUtils.isEmpty(str6)) {
                        }
                        if (TextUtils.isEmpty(str5)) {
                        }
                        if (apiUrl != null) {
                        }
                    }
                    JLog.d(TAG, "functionId = " + str5 + ", loginType = " + NetConfig.sLoginType + ", host = " + str7 + ", params_json = " + str9 + ", use_https = " + equals);
                    if (TextUtils.isEmpty(str6) && !TextUtils.isEmpty(str3)) {
                        try {
                            str6 = URLEncoder.encode(str6, "UTF-8");
                        } catch (UnsupportedEncodingException e2) {
                            e2.printStackTrace();
                        }
                        if (IMantoServerRequester.POST.equals(str8)) {
                            apiUrl = new ApiUrl().host(str7).functionId(str5).loginType(NetConfig.sLoginType).post().cookie(str6, str3).body(str9);
                        } else {
                            apiUrl = new ApiUrl().host(str7).functionId(str5).loginType(NetConfig.sLoginType).cookie(str6, str3).body(str9);
                        }
                    } else if (TextUtils.isEmpty(str5)) {
                        jDCallback2.invoke(1);
                        apiUrl = null;
                    } else if (IMantoServerRequester.POST.equals(str8)) {
                        apiUrl = new ApiUrl().host(str7).functionId(str5).loginType(NetConfig.sLoginType).post().body(str9);
                    } else {
                        apiUrl = new ApiUrl().host(str7).functionId(str5).loginType(NetConfig.sLoginType).body(str9);
                    }
                    if (apiUrl != null) {
                        if (hashMap3 != null) {
                            apiUrl.setHeaderMap(hashMap3);
                        }
                        if (hashMap4 != null) {
                            apiUrl.setParamsMap(hashMap4);
                        }
                        if (!TextUtils.isEmpty(str)) {
                            apiUrl.setAppId(str);
                        }
                        if (!TextUtils.isEmpty(str4)) {
                            apiUrl.setSecretKey(str4);
                        }
                        if (hashMap6 != null) {
                            apiUrl.setCustomCookie(hashMap6);
                        }
                        apiUrl.https(equals);
                        try {
                            apiUrl.request(new ApiUrl.RequestCallback() { // from class: com.jingdong.jdreact.plugin.network.JDReactNativeNetworkWithSignListener.1
                                @Override // com.jingdong.jdreact.plugin.network.ApiUrl.RequestCallback
                                public void onError(Call call, IOException iOException) {
                                    JLog.d(JDReactNativeNetworkWithSignListener.TAG, "Http onError");
                                    jDCallback2.invoke(iOException.toString());
                                }

                                @Override // com.jingdong.jdreact.plugin.network.ApiUrl.RequestCallback
                                public void onSuccess(Call call, String str10) {
                                    try {
                                        JDJSONObject strToJDJSONObject = OKHttpJDReactHttpSetting.strToJDJSONObject(str10);
                                        if (strToJDJSONObject != null) {
                                            jDCallback.invoke(strToJDJSONObject.toString());
                                        } else {
                                            jDCallback2.invoke(1);
                                        }
                                    } catch (Exception e3) {
                                        JLog.e(JDReactNativeNetworkWithSignListener.TAG, e3.toString());
                                        jDCallback2.invoke(0);
                                    }
                                }
                            });
                            return;
                        } catch (Exception e3) {
                            e = e3;
                            JLog.e(TAG, e.toString());
                            jDCallback2.invoke(0);
                            return;
                        }
                    }
                    return;
                }
                str4 = str2;
                if (TextUtils.isEmpty(str6)) {
                }
                if (TextUtils.isEmpty(str5)) {
                }
                if (apiUrl != null) {
                }
            } catch (Exception e4) {
                e = e4;
            }
            equals = true;
            JLog.d(TAG, "functionId = " + str5 + ", loginType = " + NetConfig.sLoginType + ", host = " + str7 + ", params_json = " + str9 + ", use_https = " + equals);
        } else {
            JLog.e(TAG, "parameters are invalid!!");
        }
    }

    public JDReactNativeNetworkWithSignListener(LoginHelper loginHelper, HashSet<String> hashSet) {
        this.loginHelper = loginHelper;
        if (hashSet != null) {
            OKHttpUtil.setCustomVerifyHost(hashSet);
        }
    }
}
