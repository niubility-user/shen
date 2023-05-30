package com.jingdong.jdreact.plugin.authority;

import android.text.TextUtils;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.cashiernative.CashierSdkGlobalConfig;
import com.jingdong.common.jdreactFramework.JDReactHelper;
import com.jingdong.jdreact.plugin.authority.ISVApiUrl;
import com.jingdong.jdsdk.constant.CartConstant;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import jd.wjlogin_sdk.common.WJLoginExtendProxy;
import jd.wjlogin_sdk.common.WJLoginHelper;
import jd.wjlogin_sdk.model.ClientInfo;
import okhttp3.Call;

/* loaded from: classes13.dex */
public class JDReactNativeAuthorityModule extends ReactContextBaseJavaModule {
    private static final String KEY_APP_ID = "appid";
    private static final String KEY_APP_KEY = "app_key";
    private static final String KEY_APP_NAME = "appName";
    private static final String KEY_APP_TOKEN = "appToken";
    private static final String KEY_BETA = "beta";
    private static final String KEY_CURRENT_HOST = "current_host";
    private static final String KEY_REDIRECT_URI = "redirect_uri";
    private static final String TAG = "JDReactAuthorityModule";
    ClientInfo clientInfo;
    WJLoginHelper helper;
    private static final String DIR_NAME = "reactISV";
    private static final File DOWNLOAD_PATH = JDReactHelper.newInstance().getApplicationContext().getDir(DIR_NAME, 0);
    private static WJLoginExtendProxy mLoginParamProxy = new WJLoginExtendProxy() { // from class: com.jingdong.jdreact.plugin.authority.JDReactNativeAuthorityModule.1
        @Override // jd.wjlogin_sdk.common.WJLoginExtendProxy
        public String getArea() {
            return CartConstant.KEY_YB_INFO_LINK;
        }

        @Override // jd.wjlogin_sdk.common.WJLoginExtendProxy
        public String getDeviceFinger() {
            return "";
        }

        @Override // jd.wjlogin_sdk.common.WJLoginExtendProxy
        public String getUuid() {
            try {
                TextUtils.isEmpty("");
            } catch (Throwable unused) {
            }
            return "";
        }
    };

    public JDReactNativeAuthorityModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    public static JDJSONObject strToJDJSONObject(String str) {
        try {
            return JDJSON.parseObject(str);
        } catch (Exception unused) {
            return null;
        }
    }

    @ReactMethod
    public void deleteToken(ReadableMap readableMap, Callback callback, Callback callback2) {
        if (readableMap == null || callback == null || callback2 == null) {
            return;
        }
        try {
            File file = new File(DOWNLOAD_PATH + File.separator + (readableMap.hasKey(KEY_APP_KEY) ? readableMap.getString(KEY_APP_KEY) : ""));
            if (file.exists()) {
                file.delete();
            }
            callback.invoke(new Object[0]);
        } catch (Exception e2) {
            e2.printStackTrace();
            callback2.invoke(new Object[0]);
        }
    }

    @ReactMethod
    public void getAuthorizedCode(ReadableMap readableMap, final Callback callback, final Callback callback2) {
        if (readableMap == null || callback == null || callback2 == null) {
            return;
        }
        String string = readableMap.hasKey("appid") ? readableMap.getString("appid") : "";
        String string2 = readableMap.hasKey(KEY_APP_KEY) ? readableMap.getString(KEY_APP_KEY) : "";
        String string3 = readableMap.hasKey("appName") ? readableMap.getString("appName") : CashierSdkGlobalConfig.CASHIER_SDK_SOURCE;
        String string4 = readableMap.hasKey(KEY_REDIRECT_URI) ? readableMap.getString(KEY_REDIRECT_URI) : "";
        boolean z = !readableMap.hasKey(KEY_BETA) || readableMap.getBoolean(KEY_BETA);
        WJLoginHelper wJLoginHelper = getWJLoginHelper();
        String a2 = wJLoginHelper != null ? wJLoginHelper.getA2() : "";
        String str = z ? "open-oauth-yf.jd.com/oauth2/authorizeForRN" : "open-oauth.jd.com/oauth2/oauth2/authorizeForRN";
        if (readableMap.hasKey(KEY_CURRENT_HOST)) {
            str = readableMap.getString(KEY_CURRENT_HOST);
        }
        ISVApiUrl cookie = new ISVApiUrl("open-oauth.jd.com/oauth2/oauth2/authorizeForRN", "open-oauth-yf.jd.com/oauth2/authorizeForRN").host(str).beta(z).appId(string).appKey(string2).redirectUri(string4).appName(string3).cookie(a2);
        if (cookie == null) {
            return;
        }
        try {
            try {
                cookie.request(new ISVApiUrl.RequestCallback() { // from class: com.jingdong.jdreact.plugin.authority.JDReactNativeAuthorityModule.2
                    @Override // com.jingdong.jdreact.plugin.authority.ISVApiUrl.RequestCallback
                    public void onError(Call call, IOException iOException) {
                        callback2.invoke(iOException.toString());
                    }

                    @Override // com.jingdong.jdreact.plugin.authority.ISVApiUrl.RequestCallback
                    public void onSuccess(Call call, String str2) {
                        try {
                            JDJSONObject strToJDJSONObject = JDReactNativeAuthorityModule.strToJDJSONObject(str2);
                            if (strToJDJSONObject != null) {
                                if (strToJDJSONObject.getInteger("code").intValue() == 0) {
                                    callback.invoke(strToJDJSONObject.getString("data"));
                                    return;
                                } else {
                                    callback2.invoke(strToJDJSONObject.getString("msg"));
                                    return;
                                }
                            }
                            callback2.invoke(1);
                        } catch (Exception e2) {
                            e2.toString();
                            callback2.invoke(0);
                        }
                    }
                });
            } catch (Exception e2) {
                e = e2;
                e.toString();
                callback2.invoke(0);
            }
        } catch (Exception e3) {
            e = e3;
        }
    }

    public ClientInfo getClientInfo() {
        if (this.clientInfo == null) {
            this.clientInfo = new ClientInfo();
        }
        return this.clientInfo;
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return "JDReactNativeAuthorityModule";
    }

    @ReactMethod
    public void getToken(ReadableMap readableMap, Callback callback, Callback callback2) {
        if (readableMap == null || callback == null) {
            if (callback2 != null) {
                callback2.invoke(new Object[0]);
                return;
            }
            return;
        }
        BufferedReader bufferedReader = null;
        try {
            try {
                try {
                    String str = DOWNLOAD_PATH + File.separator + (readableMap.hasKey(KEY_APP_KEY) ? readableMap.getString(KEY_APP_KEY) : "");
                    if (new File(str).exists()) {
                        BufferedReader bufferedReader2 = new BufferedReader(new FileReader(str));
                        try {
                            callback.invoke(bufferedReader2.readLine());
                            bufferedReader = bufferedReader2;
                        } catch (Exception e2) {
                            e = e2;
                            bufferedReader = bufferedReader2;
                            e.printStackTrace();
                            callback2.invoke(new Object[0]);
                            if (bufferedReader != null) {
                                bufferedReader.close();
                            }
                            return;
                        } catch (Throwable th) {
                            th = th;
                            bufferedReader = bufferedReader2;
                            if (bufferedReader != null) {
                                try {
                                    bufferedReader.close();
                                } catch (IOException e3) {
                                    e3.printStackTrace();
                                }
                            }
                            throw th;
                        }
                    } else {
                        callback2.invoke(new Object[0]);
                    }
                } catch (Exception e4) {
                    e = e4;
                }
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (IOException e5) {
            e5.printStackTrace();
        }
    }

    public WJLoginHelper getWJLoginHelper() {
        if (this.helper == null) {
            WJLoginHelper createInstance = WJLoginHelper.createInstance(JDReactHelper.newInstance().getApplication(), getClientInfo());
            this.helper = createInstance;
            createInstance.setWJLoginExtendProxy(mLoginParamProxy);
        }
        return this.helper;
    }

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:51:0x00a9 -> B:66:0x00ac). Please submit an issue!!! */
    @ReactMethod
    public void saveToken(ReadableMap readableMap, Callback callback, Callback callback2) {
        FileOutputStream fileOutputStream;
        if (readableMap == null || callback == null || callback2 == null) {
            return;
        }
        String string = readableMap.hasKey(KEY_APP_TOKEN) ? readableMap.getString(KEY_APP_TOKEN) : "";
        BufferedOutputStream bufferedOutputStream = null;
        try {
            try {
                File file = new File(DOWNLOAD_PATH + File.separator + (readableMap.hasKey(KEY_APP_KEY) ? readableMap.getString(KEY_APP_KEY) : ""));
                if (file.getParentFile() != null && !file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                }
                if (!file.exists()) {
                    file.createNewFile();
                }
                fileOutputStream = new FileOutputStream(file);
                try {
                    try {
                        BufferedOutputStream bufferedOutputStream2 = new BufferedOutputStream(fileOutputStream);
                        try {
                            bufferedOutputStream2.write(string.getBytes());
                            callback.invoke(new Object[0]);
                            try {
                                bufferedOutputStream2.close();
                            } catch (IOException e2) {
                                e2.printStackTrace();
                            }
                            fileOutputStream.close();
                        } catch (Exception e3) {
                            e = e3;
                            bufferedOutputStream = bufferedOutputStream2;
                            e.printStackTrace();
                            callback2.invoke(new Object[0]);
                            if (bufferedOutputStream != null) {
                                try {
                                    bufferedOutputStream.close();
                                } catch (IOException e4) {
                                    e4.printStackTrace();
                                }
                            }
                            if (fileOutputStream != null) {
                                fileOutputStream.close();
                            }
                        } catch (Throwable th) {
                            th = th;
                            bufferedOutputStream = bufferedOutputStream2;
                            if (bufferedOutputStream != null) {
                                try {
                                    bufferedOutputStream.close();
                                } catch (IOException e5) {
                                    e5.printStackTrace();
                                }
                            }
                            if (fileOutputStream != null) {
                                try {
                                    fileOutputStream.close();
                                } catch (IOException e6) {
                                    e6.printStackTrace();
                                }
                            }
                            throw th;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                    }
                } catch (Exception e7) {
                    e = e7;
                }
            } catch (Exception e8) {
                e = e8;
                fileOutputStream = null;
            } catch (Throwable th3) {
                th = th3;
                fileOutputStream = null;
            }
        } catch (IOException e9) {
            e9.printStackTrace();
        }
    }
}
