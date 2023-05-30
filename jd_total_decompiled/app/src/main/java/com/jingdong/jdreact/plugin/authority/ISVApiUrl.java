package com.jingdong.jdreact.plugin.authority;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.text.TextUtils;
import com.huawei.hms.framework.common.ContainerUtils;
import com.jdjr.mobilecert.MobileCertConstants;
import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import com.jingdong.common.cashiernative.CashierSdkGlobalConfig;
import com.jingdong.common.jdreactFramework.JDReactHelper;
import com.jingdong.jdreact.plugin.utils.CommonUtil;
import com.jingdong.jdreact.plugin.utils.NetUtil;
import com.tencent.smtt.sdk.ProxyConfig;
import java.io.IOException;
import java.text.Collator;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.TreeMap;
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
public class ISVApiUrl {
    private static final String PARAMETER_APP_ID = "appid";
    private static final String PARAMETER_APP_KEY = "app_key";
    private static final String PARAMETER_APP_NAME = "appName";
    private static final String PARAMETER_BODY = "body";
    private static final String PARAMETER_BUILD = "build";
    private static final String PARAMETER_CLIENT = "client";
    private static final String PARAMETER_CLIENT_IP = "clientIp";
    private static final String PARAMETER_CLIENT_VERSION = "clientVersion";
    private static final String PARAMETER_NETWORK_TYPE = "networkType";
    private static final String PARAMETER_OS_VERSION = "osVersion";
    private static final String PARAMETER_REDIRECT_URI = "redirect_uri";
    private static final String PARAMETER_SCREEN = "screen";
    private static final String PARAMETER_UUID = "uuid";
    private static final String POST_BODY_TYPE = "application/x-www-form-urlencoded;charset=utf-8";
    private static final String TAG = "ISVApiUrl";
    private String appId;
    private String appKey;
    private String appName;
    private String body;
    private String customHost;
    private String hostBeta;
    private String hostUrl;
    private String mA2;
    private String redirectUri;
    private TreeMap<String, String> sortedParamMap;
    private boolean useBetaHost;
    private boolean useCookie;
    private boolean useHttps;
    private boolean usePost;

    /* loaded from: classes13.dex */
    public interface RequestCallback {
        void onError(Call call, IOException iOException);

        void onSuccess(Call call, String str);
    }

    public ISVApiUrl() {
        this.useHttps = true;
        this.hostUrl = "";
        this.hostBeta = "";
        this.usePost = false;
        this.useCookie = false;
        this.useBetaHost = false;
        this.appName = CashierSdkGlobalConfig.CASHIER_SDK_SOURCE;
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
                sb.append(str2);
            }
        }
        return sb.toString();
    }

    private String getHost() {
        return !TextUtils.isEmpty(this.customHost) ? this.customHost : this.useBetaHost ? this.hostBeta : this.hostUrl;
    }

    public static String getIpAddress() {
        String str = null;
        try {
            Context applicationContext = JDReactHelper.newInstance().getApplicationContext();
            ConnectivityManager connectivityManager = (ConnectivityManager) applicationContext.getSystemService("connectivity");
            NetworkInfo networkInfo = NetUtil.getNetworkInfo(connectivityManager, 0);
            NetworkInfo networkInfo2 = NetUtil.getNetworkInfo(connectivityManager, 1);
            if (networkInfo != null && networkInfo.isConnected()) {
                str = getLocalIpAddress();
            } else if (networkInfo2 != null && networkInfo2.isConnected()) {
                str = JDReactHelper.newInstance().getIpAddressFromWifiInfo(applicationContext);
            } else {
                NetworkInfo activeNetworkInfo = NetUtil.getActiveNetworkInfo(connectivityManager);
                if (activeNetworkInfo != null) {
                    int type = activeNetworkInfo.getType();
                    if (type == 0) {
                        str = getLocalIpAddress();
                    } else if (type == 1) {
                        str = JDReactHelper.newInstance().getIpAddressFromWifiInfo(applicationContext);
                    }
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return str;
    }

    private static String getLocalIpAddress() {
        return JDReactHelper.newInstance().getNetAddressesForIPv4();
    }

    private Callback getRequestCallback(final RequestCallback requestCallback) {
        if (requestCallback == null) {
            return null;
        }
        return new Callback() { // from class: com.jingdong.jdreact.plugin.authority.ISVApiUrl.2
            @Override // okhttp3.Callback
            public void onFailure(Call call, IOException iOException) {
                String unused = ISVApiUrl.TAG;
                String str = "receive error, , e:" + iOException;
                requestCallback.onError(call, iOException);
            }

            @Override // okhttp3.Callback
            public void onResponse(Call call, Response response) throws IOException {
                requestCallback.onSuccess(call, (response == null || response.body() == null) ? null : response.body().string());
            }
        };
    }

    private static String longToIp(long j2) {
        return ((j2 >> 24) & 255) + OrderISVUtil.MONEY_DECIMAL + ((j2 >> 16) & 255) + OrderISVUtil.MONEY_DECIMAL + ((j2 >> 8) & 255) + OrderISVUtil.MONEY_DECIMAL + (j2 & 255);
    }

    private void prepareParams() {
        if (this.sortedParamMap == null) {
            this.sortedParamMap = new TreeMap<>(Collator.getInstance(Locale.CHINA));
        }
        this.sortedParamMap.put("appid", this.appId);
        this.sortedParamMap.put(PARAMETER_APP_KEY, this.appKey);
        this.sortedParamMap.put(PARAMETER_REDIRECT_URI, this.redirectUri);
        this.sortedParamMap.put("client", "android");
        this.sortedParamMap.put("osVersion", Build.VERSION.RELEASE);
        this.sortedParamMap.put("clientVersion", CommonUtil.getVersionName());
        this.sortedParamMap.put(PARAMETER_SCREEN, CommonUtil.getScreenHeight() + ProxyConfig.MATCH_ALL_SCHEMES + CommonUtil.getScreenWidth());
        this.sortedParamMap.put("uuid", CommonUtil.getAndroidId());
        this.sortedParamMap.put("appName", this.appName);
        this.sortedParamMap.put(PARAMETER_NETWORK_TYPE, NetUtil.getNetworkType());
        this.sortedParamMap.put(PARAMETER_CLIENT_IP, getIpAddress());
        this.sortedParamMap.put("build", String.valueOf(CommonUtil.getVersionCode()));
        if (TextUtils.isEmpty(this.body)) {
            return;
        }
        this.sortedParamMap.put("body", this.body);
    }

    public ISVApiUrl appId(String str) {
        this.appId = str;
        return this;
    }

    public ISVApiUrl appKey(String str) {
        this.appKey = str;
        return this;
    }

    public ISVApiUrl appName(String str) {
        this.appName = str;
        return this;
    }

    public ISVApiUrl beta(boolean z) {
        this.useBetaHost = z;
        return this;
    }

    public ISVApiUrl body(String str) {
        this.body = str;
        return this;
    }

    public ISVApiUrl cookie(String str) {
        this.useCookie = true;
        this.mA2 = str;
        return this;
    }

    public ISVApiUrl host(String str) {
        this.customHost = str;
        return this;
    }

    public ISVApiUrl https(boolean z) {
        this.useHttps = z;
        return this;
    }

    public ISVApiUrl post() {
        this.usePost = true;
        return this;
    }

    public ISVApiUrl redirectUri(String str) {
        this.redirectUri = str;
        return this;
    }

    public void request(RequestCallback requestCallback) {
        String sb;
        String host = getHost();
        if (this.hostBeta.equals(host)) {
            this.useHttps = false;
        }
        StringBuilder sb2 = new StringBuilder("http");
        if (this.useHttps) {
            sb2.append("s");
        }
        sb2.append("://");
        sb2.append(host);
        prepareParams();
        String generateParamString = generateParamString();
        Callback requestCallback2 = getRequestCallback(requestCallback);
        if (this.useCookie) {
            OkHttpClient build = new OkHttpClient.Builder().cookieJar(new CookieJar() { // from class: com.jingdong.jdreact.plugin.authority.ISVApiUrl.1
                @Override // okhttp3.CookieJar
                public List<Cookie> loadForRequest(HttpUrl httpUrl) {
                    ArrayList arrayList = new ArrayList();
                    arrayList.add(new Cookie.Builder().hostOnlyDomain(httpUrl.host()).name(MobileCertConstants.WSKEY).value(ISVApiUrl.this.mA2).build());
                    return arrayList;
                }

                @Override // okhttp3.CookieJar
                public void saveFromResponse(HttpUrl httpUrl, List<Cookie> list) {
                }
            }).build();
            if (this.usePost) {
                sb = sb2.toString();
            } else {
                if (!TextUtils.isEmpty(generateParamString)) {
                    sb2.append("?");
                    sb2.append(generateParamString);
                }
                sb = sb2.toString();
            }
            OKHttpUtil.sendRequest(build, sb, this.usePost, RequestBody.create(MediaType.parse(POST_BODY_TYPE), generateParamString), requestCallback2);
        } else if (this.usePost) {
            OKHttpUtil.sendPostRequest(sb2.toString(), RequestBody.create(MediaType.parse(POST_BODY_TYPE), generateParamString), requestCallback2);
        } else {
            if (!TextUtils.isEmpty(generateParamString)) {
                sb2.append("?");
                sb2.append(generateParamString);
            }
            OKHttpUtil.sendGetRequest(sb2.toString(), requestCallback2);
        }
    }

    public ISVApiUrl(String str, String str2) {
        this.useHttps = true;
        this.hostUrl = "";
        this.hostBeta = "";
        this.usePost = false;
        this.useCookie = false;
        this.useBetaHost = false;
        this.appName = CashierSdkGlobalConfig.CASHIER_SDK_SOURCE;
        this.hostUrl = str;
        this.hostBeta = str2;
    }
}
