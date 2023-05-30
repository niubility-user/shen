package com.jingdong.jdreact.plugin.network;

import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import com.huawei.hms.framework.common.ContainerUtils;
import com.jdjr.mobilecert.MobileCertConstants;
import com.jingdong.common.jdreactFramework.JDReactHelper;
import com.jingdong.common.jdreactFramework.utils.NetConfig;
import com.jingdong.jdreact.plugin.utils.CommonUtil;
import com.jingdong.jdreact.plugin.utils.NetUtil;
import com.jingdong.sdk.baseinfo.BaseInfo;
import com.tencent.smtt.sdk.ProxyConfig;
import java.io.IOException;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.Response;

/* loaded from: classes13.dex */
public class ApiUrl {
    private static final String PARAMETER_APP_ID = "appid";
    private static final String PARAMETER_BODY = "body";
    private static final String PARAMETER_BUILD = "build";
    private static final String PARAMETER_CLIENT = "client";
    private static final String PARAMETER_CLIENT_VERSION = "clientVersion";
    private static final String PARAMETER_D_BRAND = "d_brand";
    private static final String PARAMETER_D_MODEL = "d_model";
    private static final String PARAMETER_FUNCTION_ID = "functionId";
    private static final String PARAMETER_LANG = "lang";
    private static final String PARAMETER_LOGIN_TYPE = "loginType";
    private static final String PARAMETER_LOGIN_USER = "loginUser";
    private static final String PARAMETER_NETWORK_TYPE = "networkType";
    private static final String PARAMETER_OS_VERSION = "osVersion";
    private static final String PARAMETER_SCREEN = "screen";
    private static final String PARAMETER_SIGN = "sign";
    private static final String PARAMETER_T = "t";
    private static final String PARAMETER_UUID = "uuid";
    private static final String POST_BODY_TYPE = "application/x-www-form-urlencoded;charset=utf-8";
    private static final String TAG = "ApiUrl";
    private String body;
    private String customAppId;
    private String customHost;
    private String customSecretKey;
    private String functionId;
    private int loginType;
    private String mA2;
    private String mPin;
    private TreeMap<String, String> sortedParamMap;
    private boolean useHttps = true;
    private Map<String, String> extraCookie = null;
    private boolean usePost = false;
    private boolean useCookie = false;
    private boolean useBetaHost = false;
    private Map<String, String> header = null;
    private Map<String, String> params = null;

    /* loaded from: classes13.dex */
    public interface RequestCallback {
        void onError(Call call, IOException iOException);

        void onSuccess(Call call, String str);
    }

    private String generateParamString() {
        TreeMap<String, String> treeMap = this.sortedParamMap;
        if (treeMap == null || treeMap.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (String str : this.sortedParamMap.keySet()) {
            String str2 = this.sortedParamMap.get(str);
            if (!TextUtils.isEmpty(str2)) {
                if (sb.length() != 0) {
                    sb.append(ContainerUtils.FIELD_DELIMITER);
                }
                sb.append(str);
                sb.append(ContainerUtils.KEY_VALUE_DELIMITER);
                sb.append(Uri.encode(str2));
            }
        }
        return sb.toString();
    }

    private String generateSign() {
        String sb;
        TreeMap<String, String> treeMap = this.sortedParamMap;
        if (treeMap == null || treeMap.isEmpty()) {
            return "";
        }
        StringBuilder sb2 = new StringBuilder();
        if (!TextUtils.isEmpty(this.customSecretKey)) {
            TreeSet treeSet = new TreeSet();
            Iterator<String> it = this.sortedParamMap.keySet().iterator();
            while (it.hasNext()) {
                treeSet.add(it.next());
            }
            Iterator it2 = treeSet.iterator();
            while (it2.hasNext()) {
                String str = this.sortedParamMap.get(it2.next().toString());
                if (!TextUtils.isEmpty(str)) {
                    sb2.append(str);
                    sb2.append(ContainerUtils.FIELD_DELIMITER);
                }
            }
            sb = sb2.toString();
            if (sb.endsWith(ContainerUtils.FIELD_DELIMITER) && sb.length() > 1) {
                sb = sb.substring(0, sb.length() - 1);
            }
        } else {
            Iterator<String> it3 = this.sortedParamMap.keySet().iterator();
            while (it3.hasNext()) {
                String str2 = this.sortedParamMap.get(it3.next());
                if (!TextUtils.isEmpty(str2)) {
                    if (sb2.length() != 0) {
                        sb2.append(ContainerUtils.FIELD_DELIMITER);
                    }
                    sb2.append(str2);
                }
            }
            sb = sb2.toString();
        }
        if (!TextUtils.isEmpty(this.customSecretKey)) {
            return SignTool.HMACSHA256(sb, this.customSecretKey);
        }
        return SignTool.HMACSHA256(sb, NetConfig.sSecretKey);
    }

    private String getHost() {
        return !TextUtils.isEmpty(this.customHost) ? this.customHost : this.useBetaHost ? "beta-api.m.jd.com" : "api.m.jd.com";
    }

    private Callback getRequestCallback(final RequestCallback requestCallback) {
        if (requestCallback == null) {
            return null;
        }
        return new Callback() { // from class: com.jingdong.jdreact.plugin.network.ApiUrl.2
            @Override // okhttp3.Callback
            public void onFailure(Call call, IOException iOException) {
                LogUtil.e(ApiUrl.TAG, "receive error, functionId:" + ApiUrl.this.functionId + ", e:" + iOException);
                requestCallback.onError(call, iOException);
            }

            @Override // okhttp3.Callback
            public void onResponse(Call call, Response response) throws IOException {
                String string = (response == null || response.body() == null) ? null : response.body().string();
                LogUtil.i(ApiUrl.TAG, "receive response, functionId:" + ApiUrl.this.functionId + ", data:" + string);
                requestCallback.onSuccess(call, string);
            }
        };
    }

    private void prepareParams() {
        if (this.sortedParamMap == null) {
            this.sortedParamMap = new TreeMap<>(Collator.getInstance(Locale.CHINA));
        }
        if (!TextUtils.isEmpty(this.customAppId)) {
            this.sortedParamMap.put("appid", this.customAppId);
        } else {
            this.sortedParamMap.put("appid", NetConfig.sAppId);
        }
        this.sortedParamMap.put("functionId", this.functionId);
        this.sortedParamMap.put("client", NetConfig.getClient());
        this.sortedParamMap.put("clientVersion", CommonUtil.getVersionName());
        this.sortedParamMap.put("uuid", Config.getUUID());
        if (!TextUtils.isEmpty(Config.getPIN())) {
            this.sortedParamMap.put(PARAMETER_LOGIN_USER, Config.getPIN());
        }
        this.sortedParamMap.put("osVersion", Build.VERSION.RELEASE);
        this.sortedParamMap.put(PARAMETER_T, String.valueOf(System.currentTimeMillis()));
        this.sortedParamMap.put("build", String.valueOf(CommonUtil.getVersionCode()));
        this.sortedParamMap.put("d_brand", BaseInfo.getDeviceBrand());
        this.sortedParamMap.put("d_model", BaseInfo.getDeviceModel());
        this.sortedParamMap.put(PARAMETER_SCREEN, CommonUtil.getScreenHeight() + ProxyConfig.MATCH_ALL_SCHEMES + CommonUtil.getScreenWidth());
        this.sortedParamMap.put(PARAMETER_LANG, Locale.getDefault().getLanguage());
        this.sortedParamMap.put(PARAMETER_NETWORK_TYPE, TextUtils.isEmpty(NetUtil.getNetworkType()) ? "" : NetUtil.getNetworkType());
        Map<String, String> map = this.params;
        if (map != null) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                this.sortedParamMap.put(entry.getKey(), entry.getValue());
            }
        }
        if (!TextUtils.isEmpty(this.body)) {
            this.sortedParamMap.put("body", this.body);
        }
        if (NetConfig.sLoginType != 0) {
            this.sortedParamMap.put(PARAMETER_LOGIN_TYPE, this.loginType + "");
        }
        this.sortedParamMap.put(PARAMETER_SIGN, generateSign());
    }

    public ApiUrl beta(boolean z) {
        this.useBetaHost = z;
        return this;
    }

    public ApiUrl body(String str) {
        this.body = str;
        return this;
    }

    public ApiUrl cookie(String str, String str2) {
        this.useCookie = true;
        this.mPin = str;
        this.mA2 = str2;
        return this;
    }

    public ApiUrl functionId(String str) {
        this.functionId = str;
        return this;
    }

    public ApiUrl host(String str) {
        this.customHost = str;
        return this;
    }

    public ApiUrl https(boolean z) {
        this.useHttps = z;
        return this;
    }

    public ApiUrl loginType(int i2) {
        if (i2 == 0) {
            return this;
        }
        this.loginType = i2;
        return this;
    }

    public ApiUrl post() {
        this.usePost = true;
        return this;
    }

    public void request(RequestCallback requestCallback) {
        if (NetConfig.sBeta) {
            this.useBetaHost = true;
        }
        String host = getHost();
        if ("beta-api.m.jd.com".equals(host)) {
            this.useHttps = false;
        }
        if (JDReactHelper.newInstance().useHttp()) {
            this.useHttps = false;
        }
        StringBuilder sb = new StringBuilder("http");
        if (this.useHttps) {
            sb.append("s");
        }
        sb.append("://");
        sb.append(host);
        sb.append("/");
        if (NetConfig.isUseFunctionIDAsPath()) {
            sb.append(JDReactHelper.newInstance().getNativeVerionAPI());
        } else {
            sb.append("api");
        }
        prepareParams();
        String generateParamString = generateParamString();
        Callback requestCallback2 = getRequestCallback(requestCallback);
        if (this.useCookie) {
            OkHttpClient build = new OkHttpClient.Builder().hostnameVerifier(OKHttpUtil.VERIFY_HOST).cookieJar(new CookieJar() { // from class: com.jingdong.jdreact.plugin.network.ApiUrl.1
                @Override // okhttp3.CookieJar
                public List<Cookie> loadForRequest(HttpUrl httpUrl) {
                    ArrayList arrayList = new ArrayList();
                    Cookie.Builder builder = new Cookie.Builder();
                    builder.hostOnlyDomain(httpUrl.host());
                    switch (ApiUrl.this.loginType) {
                        case 1:
                            builder.name("wq_auth_token").value(ApiUrl.this.mA2);
                            break;
                        case 2:
                            builder.name("pt_pin").value(ApiUrl.this.mPin + ";pt_key=" + ApiUrl.this.mA2);
                            break;
                        case 3:
                            builder.name("thor").value(ApiUrl.this.mA2);
                            break;
                        case 4:
                            builder.name("pin").value(ApiUrl.this.mPin + ";wskey=" + ApiUrl.this.mA2);
                            break;
                        case 5:
                            builder.name("fx_key").value(ApiUrl.this.mA2);
                            break;
                        case 6:
                            builder.name(MobileCertConstants.WSKEY).value(ApiUrl.this.mA2);
                            break;
                        case 7:
                            builder.name("ticket").value(ApiUrl.this.mA2);
                            break;
                        case 8:
                            builder.name("fx_app_key").value(ApiUrl.this.mA2);
                            break;
                        default:
                            if (ApiUrl.this.extraCookie != null) {
                                for (Map.Entry entry : ApiUrl.this.extraCookie.entrySet()) {
                                    builder.name((String) entry.getKey()).value((String) entry.getValue());
                                    arrayList.add(builder.build());
                                }
                                break;
                            } else {
                                builder.name("pin").value(ApiUrl.this.mPin + ";wskey=" + ApiUrl.this.mA2);
                                break;
                            }
                    }
                    if (ApiUrl.this.extraCookie == null) {
                        arrayList.add(builder.build());
                    }
                    return arrayList;
                }

                @Override // okhttp3.CookieJar
                public void saveFromResponse(HttpUrl httpUrl, List<Cookie> list) {
                }
            }).build();
            RequestBody create = RequestBody.create(MediaType.parse(POST_BODY_TYPE), generateParamString);
            if (!this.usePost && !TextUtils.isEmpty(generateParamString)) {
                sb.append("?");
                sb.append(generateParamString);
            }
            String sb2 = sb.toString();
            Map<String, String> map = this.header;
            if (map != null) {
                OKHttpUtil.sendRequest(build, sb2, this.usePost, map, create, requestCallback2);
            } else {
                OKHttpUtil.sendRequest(build, sb2, this.usePost, create, requestCallback2);
            }
        } else if (this.usePost) {
            String sb3 = sb.toString();
            RequestBody create2 = RequestBody.create(MediaType.parse(POST_BODY_TYPE), generateParamString);
            Map<String, String> map2 = this.header;
            if (map2 != null) {
                OKHttpUtil.sendPostRequest(sb3, create2, map2, requestCallback2);
            } else {
                OKHttpUtil.sendPostRequest(sb3, create2, requestCallback2);
            }
        } else {
            if (!TextUtils.isEmpty(generateParamString)) {
                sb.append("?");
                sb.append(generateParamString);
            }
            String sb4 = sb.toString();
            Map<String, String> map3 = this.header;
            if (map3 != null) {
                OKHttpUtil.sendGetRequest(sb4, map3, requestCallback2);
            } else {
                OKHttpUtil.sendGetRequest(sb4, requestCallback2);
            }
        }
    }

    public ApiUrl setAppId(String str) {
        this.customAppId = str;
        return this;
    }

    public ApiUrl setCustomCookie(Map<String, String> map) {
        this.extraCookie = map;
        return this;
    }

    public ApiUrl setHeaderMap(Map<String, String> map) {
        this.header = map;
        return this;
    }

    public ApiUrl setParamsMap(Map<String, String> map) {
        this.params = map;
        return this;
    }

    public ApiUrl setSecretKey(String str) {
        this.customSecretKey = str;
        return this;
    }
}
