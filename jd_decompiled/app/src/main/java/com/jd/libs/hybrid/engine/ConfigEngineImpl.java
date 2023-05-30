package com.jd.libs.hybrid.engine;

import android.net.Uri;
import android.text.TextUtils;
import com.jd.dynamic.DYConstants;
import com.jd.libs.hybrid.HybridSDK;
import com.jd.libs.hybrid.adapter.ColorHttpAdapter;
import com.jd.libs.hybrid.base.HybridSettings;
import com.jd.libs.hybrid.base.engine.ConfigEngine;
import com.jd.libs.hybrid.base.util.Log;
import com.jd.libs.hybrid.offlineload.utils.CryptUtils;
import com.jd.libs.hybrid.offlineload.utils.OfflineFileUtils;
import com.jd.libs.xwin.http.b;
import com.jd.libs.xwin.http.c;
import com.jd.libs.xwin.http.d;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes16.dex */
public class ConfigEngineImpl implements ConfigEngine {
    private Uri a(String str, String str2) {
        return new Uri.Builder().scheme(HybridSettings.isDebug() ? "http" : "https").authority(HybridSettings.Net.getGatewayHost()).appendPath("client.action").appendQueryParameter("appid", "JDHybrid").appendQueryParameter("functionId", str).appendQueryParameter("body", str2).appendQueryParameter("t", String.valueOf(System.currentTimeMillis())).appendQueryParameter("client", "android").build();
    }

    private void b(String str, JSONObject jSONObject, final ConfigEngine.Callback<String> callback) {
        ColorHttpAdapter colorHttpAdapter = (ColorHttpAdapter) HybridSDK.getAdapter(ColorHttpAdapter.NAME);
        if (colorHttpAdapter != null) {
            Log.xLogDForDev("ConfigEngineImpl", "request hybrid config by app adapter");
            c(colorHttpAdapter, str, jSONObject, callback);
            return;
        }
        Log.xLogDForDev("ConfigEngineImpl", "request hybrid config by hybrid sdk");
        b bVar = new b(d.c(a(str, jSONObject.toString()).toString(), null, String.format("dbc9a7d2cf4040f5a%s", "000599c73d5bc27")));
        bVar.a(new b.a(this) { // from class: com.jd.libs.hybrid.engine.ConfigEngineImpl.1
            @Override // com.jd.libs.xwin.http.b.a
            public void onError(int i2, Map<String, List<String>> map, String str2) {
                ConfigEngine.Callback callback2 = callback;
                if (callback2 != null) {
                    callback2.onFail(-1, str2);
                }
            }

            @Override // com.jd.libs.xwin.http.b.a
            public void onRedirect(int i2, Map<String, List<String>> map, String str2) {
            }

            @Override // com.jd.libs.xwin.http.b.a
            public void onStart() {
            }

            @Override // com.jd.libs.xwin.http.b.a
            public void onSusses(int i2, Map<String, List<String>> map, String str2) {
                try {
                    JSONObject jSONObject2 = new JSONObject(str2);
                    if (jSONObject2.has("code") && jSONObject2.has("data")) {
                        if (jSONObject2.getInt("code") == 0) {
                            String decryptData = CryptUtils.decryptData(jSONObject2.getString("data"));
                            if (!TextUtils.isEmpty(decryptData) && !DYConstants.DY_NULL_STR.equals(decryptData)) {
                                ConfigEngine.Callback callback2 = callback;
                                if (callback2 != null) {
                                    callback2.onSuccess(decryptData);
                                    return;
                                }
                                return;
                            }
                            ConfigEngine.Callback callback3 = callback;
                            if (callback3 != null) {
                                callback3.onFail(-4, "data is null");
                                return;
                            }
                            return;
                        }
                        ConfigEngine.Callback callback4 = callback;
                        if (callback4 != null) {
                            callback4.onFail(-3, "code is not 0");
                            return;
                        }
                        return;
                    }
                    ConfigEngine.Callback callback5 = callback;
                    if (callback5 != null) {
                        callback5.onFail(-3, "response has not `data` or `code`");
                    }
                } catch (JSONException e2) {
                    ConfigEngine.Callback callback6 = callback;
                    if (callback6 != null) {
                        callback6.onFail(-2, e2.getMessage());
                    }
                }
            }
        });
        c.a(bVar);
    }

    private void c(ColorHttpAdapter colorHttpAdapter, String str, JSONObject jSONObject, final ConfigEngine.Callback<String> callback) {
        colorHttpAdapter.request(str, "GET", jSONObject, new ColorHttpAdapter.Callback(this) { // from class: com.jd.libs.hybrid.engine.ConfigEngineImpl.2
            @Override // com.jd.libs.hybrid.base.engine.ConfigEngine.Callback
            public void onFail(int i2, String str2) {
                ConfigEngine.Callback callback2 = callback;
                if (callback2 != null) {
                    callback2.onFail(i2, str2);
                }
            }

            @Override // com.jd.libs.hybrid.base.engine.ConfigEngine.Callback
            public void onSuccess(String str2) {
                try {
                    JSONObject jSONObject2 = new JSONObject(str2);
                    if (jSONObject2.has("code") && jSONObject2.has("data")) {
                        if (jSONObject2.getInt("code") == 0) {
                            String decryptData = CryptUtils.decryptData(jSONObject2.getString("data"));
                            if (!TextUtils.isEmpty(decryptData) && !DYConstants.DY_NULL_STR.equals(decryptData)) {
                                ConfigEngine.Callback callback2 = callback;
                                if (callback2 != null) {
                                    callback2.onSuccess(decryptData);
                                    return;
                                }
                                return;
                            }
                            ConfigEngine.Callback callback3 = callback;
                            if (callback3 != null) {
                                callback3.onFail(-4, "data is null");
                                return;
                            }
                            return;
                        }
                        ConfigEngine.Callback callback4 = callback;
                        if (callback4 != null) {
                            callback4.onFail(-3, "code is not 0");
                            return;
                        }
                        return;
                    }
                    ConfigEngine.Callback callback5 = callback;
                    if (callback5 != null) {
                        callback5.onFail(-3, "response has not `data` or `code`");
                    }
                } catch (JSONException e2) {
                    ConfigEngine.Callback callback6 = callback;
                    if (callback6 != null) {
                        callback6.onFail(-2, e2.getMessage());
                    }
                }
            }
        });
    }

    private String d() {
        Log.d("request hybrid config for web page");
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("uuid", HybridSDK.getSetting("uuid"));
            jSONObject.put(HybridSDK.OS_VERSION, HybridSDK.getSetting(HybridSDK.OS_VERSION));
            jSONObject.put(HybridSDK.APP_VERSION, HybridSDK.getSetting(HybridSDK.APP_VERSION));
            jSONObject.put(HybridSDK.APP_VERSION_CODE, HybridSDK.getSetting(HybridSDK.APP_VERSION_CODE));
            jSONObject.put(HybridSDK.D_MODEL, HybridSDK.getSetting(HybridSDK.D_MODEL));
            jSONObject.put(HybridSDK.D_BRAND, HybridSDK.getSetting(HybridSDK.D_BRAND));
            jSONObject.put("pin", HybridSDK.getSetting("pin"));
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return CryptUtils.encodeData(jSONObject.toString());
    }

    @Override // com.jd.libs.hybrid.base.engine.ConfigEngine
    public void getAllConfig(ConfigEngine.Callback<String> callback) {
        Log.d("request hybrid config for app");
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("api-version", "1.7.0");
            jSONObject.put("app-key", HybridSettings.getAppKey());
            jSONObject.put("app-type", "android");
            jSONObject.put("d_info", d());
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        b(OfflineFileUtils.HYBRID_OFFLINE_ROOT_DIR, jSONObject, callback);
    }

    @Override // com.jd.libs.hybrid.base.engine.ConfigEngine
    public void getConfigById(String str, ConfigEngine.Callback<String> callback) {
        Log.d("request hybrid config for web page");
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("id", str);
            jSONObject.put("api-version", "1.7.0");
            jSONObject.put("app-key", HybridSettings.getAppKey());
            jSONObject.put("app-type", "android");
            jSONObject.put("d_info", d());
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        b("hybridResourceItem", jSONObject, callback);
    }

    @Override // com.jd.libs.hybrid.base.engine.ConfigEngine
    public void getDebugConfig(String str, ConfigEngine.Callback<String> callback) {
        Log.d("request hybrid config for app");
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("id", str);
            jSONObject.put("app-key", HybridSettings.getAppKey());
            jSONObject.put("app-type", "android");
            jSONObject.put("d_info", d());
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        b("hybridInline", jSONObject, callback);
    }
}
