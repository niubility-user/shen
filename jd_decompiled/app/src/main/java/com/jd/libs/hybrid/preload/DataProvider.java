package com.jd.libs.hybrid.preload;

import android.content.Context;
import android.net.Uri;
import android.os.SystemClock;
import android.text.TextUtils;
import com.google.common.net.HttpHeaders;
import com.huawei.hms.framework.common.ContainerUtils;
import com.jd.framework.json.JDJSONObject;
import com.jd.libs.hybrid.HybridSDK;
import com.jd.libs.hybrid.base.GlobalParamProvider;
import com.jd.libs.hybrid.base.HybridBase;
import com.jd.libs.hybrid.base.HybridSettings;
import com.jd.libs.hybrid.base.util.Log;
import com.jd.libs.hybrid.preload.IPreloadParamGetter;
import com.jd.libs.hybrid.preload.PreloadController;
import com.jd.libs.hybrid.preload.entity.PreloadInfoEntity;
import com.jd.libs.hybrid.preload.okhttp.OKHttpError;
import com.jd.libs.hybrid.preload.okhttp.OKHttpListener;
import com.jd.libs.hybrid.preload.okhttp.OKHttpResponse;
import com.jd.libs.hybrid.preload.okhttp.OKHttpSetting;
import com.jd.libs.xdog.b;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.manto.sdk.api.IMantoServerRequester;
import com.jingdong.sdk.jdhttpdns.config.HttpDnsConfig;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes16.dex */
public class DataProvider {
    public static final int CODE_PRELOAD_NET_FAIL = -1;
    public static final int CODE_PRELOAD_NULL = -2;
    public static final int CODE_PRELOAD_SUCCESS = 200;
    public static final String KEY_PRELOAD_UA = "webPreloadUa";
    private static ExtraParamsGetter d;

    /* renamed from: e  reason: collision with root package name */
    private static DataProvider f6124e;
    private Map<String, Callback> a;
    private RequestHolder b;

    /* renamed from: c  reason: collision with root package name */
    private Map<String, Map<String, String>> f6125c = new ConcurrentHashMap();

    /* loaded from: classes16.dex */
    public interface Callback {
        void onResult(int i2, String str);
    }

    private DataProvider() {
        if (f6124e != null) {
            throw new IllegalStateException("instance is not null !");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(String str, final PreloadInfoEntity preloadInfoEntity, IPreloadParamGetter.PreloadParamGetter preloadParamGetter, final String str2) {
        HashMap hashMap;
        Iterator it;
        String str3;
        Iterator<String> it2;
        String str4 = "DataProvider";
        if (preloadParamGetter != null && !preloadParamGetter.allowPreloadData(preloadInfoEntity)) {
            if (Log.isDebug()) {
                Log.d("DataProvider", "preLoad canceled by outside.");
                Log.xLogDForDev("DataProvider", "\u4e1a\u52a1\u5c42\u63a7\u5236\u4e0d\u8fdb\u884c\u63a5\u53e3\u9884\u52a0\u8f7d");
            }
        } else if (preloadInfoEntity != null) {
            if ("0".equals(preloadInfoEntity.getRequestType()) || "1".equals(preloadInfoEntity.getRequestType()) || "2".equals(preloadInfoEntity.getRequestType())) {
                if ("1".equals(preloadInfoEntity.getRequestType()) && TextUtils.isEmpty(preloadInfoEntity.getRequestUrl())) {
                    return;
                }
                if ("0".equals(preloadInfoEntity.getRequestType()) && TextUtils.isEmpty(preloadInfoEntity.getFunctionId())) {
                    return;
                }
                long elapsedRealtime = SystemClock.elapsedRealtime();
                if (this.b == null) {
                    this.b = new RequestHolder();
                }
                final String str5 = preloadInfoEntity.getAppid() + CartConstant.KEY_YB_INFO_LINK + str2;
                this.b.onRequest(str5);
                OKHttpSetting oKHttpSetting = new OKHttpSetting(str, "0".equals(preloadInfoEntity.getRequestType()));
                if (HybridSettings.Net.isUseHttp()) {
                    oKHttpSetting.setUseHttps(false);
                }
                oKHttpSetting.setCookieString(HybridBase.getInstance().getCookieString(str));
                if (!"0".equals(preloadInfoEntity.getRequestType())) {
                    String setting = HybridBase.getInstance().getSetting("userAgent");
                    if (TextUtils.isEmpty(setting)) {
                        GlobalParamProvider.IGlobalParamProvider iGlobalParamProvider = GlobalParamProvider.sGlobalParamProvider;
                        setting = iGlobalParamProvider != null ? iGlobalParamProvider.getUserAgent(str) : null;
                    }
                    Log.d("DataProvider", "preLoad userAgent -->" + setting);
                    if (!TextUtils.isEmpty(setting)) {
                        oKHttpSetting.setUaString(setting);
                        if (preloadInfoEntity.getRequestUrl() != null) {
                            oKHttpSetting.setUrl(preloadInfoEntity.getRequestUrl());
                        }
                    } else {
                        this.b.removeDirectly(str5);
                        Log.e("DataProvider", "preload onError --> This is Non-GateWay request, but UserAgent is null/empty.");
                        if (Log.isDebug()) {
                            Log.xLogEForDev("DataProvider", "\u6b64\u975e\u7f51\u5173\u7c7b\u578b\u7684\u9884\u52a0\u8f7d\u8bf7\u6c42\u4e2d\uff0cUserAgent\u4e3a\u7a7a\uff0c\u653e\u5f03\u9884\u52a0\u8f7d\u3002");
                            return;
                        }
                        return;
                    }
                }
                Uri parse = !TextUtils.isEmpty(preloadInfoEntity.getRequestUrl()) ? Uri.parse(preloadInfoEntity.getRequestUrl()) : null;
                Map<String, String> hashMap2 = new HashMap<>();
                if (parse != null && !TextUtils.isEmpty(parse.getHost())) {
                    oKHttpSetting.setHost(parse.getHost());
                } else {
                    oKHttpSetting.setHost(HybridSettings.Net.getGatewayHost());
                }
                if (!TextUtils.isEmpty(preloadInfoEntity.getRequestUrl())) {
                    hashMap2 = f(hashMap2, preloadInfoEntity.getRequestUrl());
                }
                Map<String, String> headerMap = oKHttpSetting.getHeaderMap();
                if (headerMap == null || headerMap.size() == 0) {
                    headerMap = new HashMap<>();
                    oKHttpSetting.setHeaderMap(headerMap);
                }
                headerMap.put("Request-From", "jdhybrid-preFetch");
                if (preloadInfoEntity.getHeader() != null || CustomParamProvider.getPreloadHeader() != null) {
                    if (preloadInfoEntity.getHeader() != null) {
                        headerMap.putAll(preloadInfoEntity.getHeader());
                    }
                    if (CustomParamProvider.getPreloadHeader() != null) {
                        headerMap.putAll(CustomParamProvider.getPreloadHeader());
                    }
                }
                if (d == null || preloadInfoEntity.getExtraKeys() == null || preloadInfoEntity.getExtraKeys().isEmpty()) {
                    hashMap = null;
                } else {
                    hashMap = new HashMap();
                    Iterator<String> it3 = preloadInfoEntity.getExtraKeys().iterator();
                    while (it3.hasNext()) {
                        String next = it3.next();
                        if (TextUtils.isEmpty(next)) {
                            it2 = it3;
                        } else {
                            it2 = it3;
                            hashMap.put(next, d.getValueByKey(next));
                        }
                        it3 = it2;
                    }
                }
                if (!"0".equals(preloadInfoEntity.getRequestType()) && preloadInfoEntity.getMappings() != null && !preloadInfoEntity.getMappings().isEmpty()) {
                    hashMap = new HashMap();
                    Iterator<String> it4 = preloadInfoEntity.getMappings().keySet().iterator();
                    while (it4.hasNext()) {
                        String next2 = it4.next();
                        Iterator<String> it5 = it4;
                        String str6 = preloadInfoEntity.getMappings().get(next2);
                        if (!TextUtils.isEmpty(str6)) {
                            hashMap.put(str6, CustomParamProvider.getValueByDefine(next2));
                        }
                        it4 = it5;
                    }
                }
                Map<String, String> fetchParams = preloadParamGetter != null ? preloadParamGetter.getFetchParams() : null;
                if (preloadInfoEntity.getUrlParamsState() == 1 && !TextUtils.isEmpty(str)) {
                    fetchParams = f(fetchParams, str);
                }
                HashMap hashMap3 = new HashMap();
                JDJSONObject jDJSONObject = new JDJSONObject();
                if (hashMap != null && !hashMap.isEmpty()) {
                    hashMap3.putAll(hashMap);
                    jDJSONObject.putAll(hashMap);
                }
                if (hashMap2 != null && !hashMap2.isEmpty()) {
                    hashMap3.putAll(hashMap2);
                }
                if (preloadInfoEntity.getParams() != null && !preloadInfoEntity.getParams().isEmpty()) {
                    hashMap3.putAll(preloadInfoEntity.getParams());
                }
                if (preloadInfoEntity.getBody() != null && !preloadInfoEntity.getBody().isEmpty()) {
                    jDJSONObject.putAll(preloadInfoEntity.getBody());
                }
                if (fetchParams != null && !fetchParams.isEmpty()) {
                    hashMap3.putAll(fetchParams);
                    jDJSONObject.putAll(fetchParams);
                }
                hashMap3.put("area", CustomParamProvider.getValueByDefine("area"));
                hashMap3.put("t", String.valueOf(System.currentTimeMillis()));
                JDJSONObject jDJSONObject2 = new JDJSONObject();
                jDJSONObject2.put(HybridSDK.LNG, (Object) CustomParamProvider.getValueByDefine(HybridSDK.LNG));
                jDJSONObject2.put("lat", (Object) CustomParamProvider.getValueByDefine("lat"));
                jDJSONObject.put("geo", (Object) jDJSONObject2);
                hashMap3.put("body", jDJSONObject.toJSONString());
                String url = oKHttpSetting.getUrl();
                Map<String, String> hashMap4 = new HashMap<>();
                Iterator it6 = hashMap3.entrySet().iterator();
                while (it6.hasNext()) {
                    Map.Entry entry = (Map.Entry) it6.next();
                    if (preloadInfoEntity == null || TextUtils.isEmpty((CharSequence) entry.getKey())) {
                        it = it6;
                        str3 = str4;
                    } else {
                        it = it6;
                        str3 = str4;
                        oKHttpSetting.putQueryParam((String) entry.getKey(), (String) entry.getValue());
                        if (hashMap2 != null && hashMap2.containsKey(entry.getKey())) {
                            url = replace(url, (String) entry.getKey(), (String) entry.getValue());
                        } else {
                            hashMap4.put(entry.getKey(), entry.getValue());
                        }
                    }
                    it6 = it;
                    str4 = str3;
                }
                String str7 = str4;
                oKHttpSetting.setExtraParams(hashMap4);
                oKHttpSetting.setRequestUrl(url);
                if (!jDJSONObject.isEmpty()) {
                    oKHttpSetting.putJsonParam(jDJSONObject);
                    oKHttpSetting.setBodyJson(jDJSONObject);
                }
                oKHttpSetting.setPost(!IMantoServerRequester.GET.equalsIgnoreCase(preloadInfoEntity.getMethod()));
                if ("0".equals(preloadInfoEntity.getRequestType())) {
                    oKHttpSetting.setFunctionId(preloadInfoEntity.getFunctionId());
                }
                oKHttpSetting.setUseFastJsonParser(true);
                oKHttpSetting.setEffect(0);
                oKHttpSetting.setBusinessLayerCheckSwitch(false);
                Log.d("\u63a5\u53e3\u9884\u52a0\u8f7d\u67e5\u8be2\u8017\u65f6\uff1a" + (SystemClock.elapsedRealtime() - elapsedRealtime));
                if (HybridSettings.Net.getDataProvider() != null) {
                    h(preloadInfoEntity, str, oKHttpSetting, str5, str2);
                } else {
                    oKHttpSetting.setHttpListener(preloadInfoEntity.getAppid(), preloadInfoEntity.getRequestTypeStr(), new OKHttpListener() { // from class: com.jd.libs.hybrid.preload.DataProvider.2
                        @Override // com.jd.libs.hybrid.preload.okhttp.OKHttpListener
                        public void onEnd(OKHttpResponse oKHttpResponse) {
                            String string;
                            if (oKHttpResponse != null) {
                                HttpResponse httpResponse = oKHttpResponse.getmHttpResponse();
                                if (httpResponse != null && httpResponse.getFastJsonObject() != null) {
                                    string = httpResponse.getFastJsonObject().toJSONString();
                                } else {
                                    string = oKHttpResponse.getString();
                                }
                                if (DataProvider.this.b != null) {
                                    DataProvider.this.b.onEnd(str5, string);
                                }
                                if (Log.isDebug()) {
                                    Log.xLogD("DataProvider", "\u9879\u76ee(id:" + preloadInfoEntity.getAppid() + ")\u63a5\u53e3\u9884\u52a0\u8f7d\u8bf7\u6c42\u6210\u529f\uff0c\u7ed3\u679c\uff1a", string);
                                }
                                DataProvider.this.g(str5, str2);
                            }
                        }

                        @Override // com.jd.libs.hybrid.preload.okhttp.OKHttpListener
                        public void onError(OKHttpError oKHttpError) {
                            String str8;
                            if (DataProvider.this.b != null) {
                                DataProvider.this.b.onError(str5);
                            }
                            if (Log.isDebug()) {
                                StringBuilder sb = new StringBuilder();
                                sb.append("\u9879\u76ee(id:");
                                sb.append(preloadInfoEntity.getAppid());
                                sb.append(")\u63a5\u53e3\u9884\u52a0\u8f7d\u8bf7\u6c42\u5931\u8d25");
                                if (oKHttpError != null) {
                                    str8 = "\u539f\u56e0\uff1a" + oKHttpError.toString();
                                } else {
                                    str8 = "";
                                }
                                sb.append(str8);
                                Log.xLogE("DataProvider", sb.toString());
                            }
                            DataProvider.this.g(str5, str2);
                        }
                    });
                }
                Log.d("\u63a5\u53e3\u9884\u52a0\u8f7d\u67e5\u8be2\u8017\u65f6\uff1a" + (SystemClock.elapsedRealtime() - elapsedRealtime));
                Object[] objArr = new Object[7];
                objArr[0] = "0".equals(preloadInfoEntity.getRequestType()) ? "GateWay" : "1".equals(preloadInfoEntity.getRequestType()) ? "Jsonp" : "CommonHttp";
                objArr[1] = oKHttpSetting.getHost();
                objArr[2] = oKHttpSetting.getFunctionId();
                objArr[3] = oKHttpSetting.isPost() ? "POST" : "GET";
                objArr[4] = oKHttpSetting.getHeaderMap().toString();
                objArr[5] = oKHttpSetting.getJsonParamsString();
                objArr[6] = oKHttpSetting.getBodyJson();
                Log.w(str7, String.format("Preload data: type = %s, host = %s, functionId = %s, method = %s, header = %s, param = %s, body = %s", objArr));
            }
        }
    }

    private Map<String, String> e(String str) {
        if (TextUtils.isEmpty(str)) {
            return new ConcurrentHashMap();
        }
        Map<String, String> map = this.f6125c.get(str);
        if (map == null) {
            ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
            this.f6125c.put(str, concurrentHashMap);
            return concurrentHashMap;
        }
        return map;
    }

    private Map<String, String> f(Map<String, String> map, String str) {
        List<String> queryParameters;
        if (TextUtils.isEmpty(str)) {
            return new HashMap();
        }
        try {
            Uri parse = Uri.parse(str);
            Set<String> queryParameterNames = parse.getQueryParameterNames();
            if (!queryParameterNames.isEmpty()) {
                if (map == null) {
                    map = new HashMap<>(queryParameterNames.size());
                }
                for (String str2 : queryParameterNames) {
                    if (!map.containsKey(str2) && (queryParameters = parse.getQueryParameters(str2)) != null && !queryParameters.isEmpty()) {
                        map.put(str2, queryParameters.get(queryParameters.size() - 1));
                    }
                }
            }
            Log.d("DataProvider", "uri getUriQueryMap -->" + map);
            return map;
        } catch (Exception e2) {
            Log.e("DataProvider", "uri getUriQueryMap error -->" + e2.toString());
            return new HashMap();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g(String str, String str2) {
        Callback callback;
        e(str2).put("prlend", String.valueOf(System.currentTimeMillis()));
        b.k(HttpDnsConfig.PREDOWNLOAD_PARAMS, "prlend", String.valueOf(System.currentTimeMillis()));
        Map<String, Callback> map = this.a;
        if (map == null || (callback = map.get(str)) == null) {
            return;
        }
        RequestHolder requestHolder = this.b;
        if (requestHolder == null) {
            callback.onResult(-2, null);
            return;
        }
        int status = requestHolder.getStatus(str);
        if (status == 1) {
            callback.onResult(200, this.b.getResult(str));
        } else if (status == 2) {
            callback.onResult(-1, null);
        } else {
            callback.onResult(-2, null);
        }
        this.b.clear(str);
    }

    public static DataProvider getInstance() {
        if (f6124e == null) {
            synchronized (DataProvider.class) {
                if (f6124e == null) {
                    f6124e = new DataProvider();
                }
            }
        }
        return f6124e;
    }

    public static String getPreloadStatusStr(int i2) {
        return 200 == i2 ? "\u8bf7\u6c42\u6210\u529f" : -1 == i2 ? "\u7f51\u7edc\u5931\u8d25" : -2 == i2 ? "\u672a\u914d\u7f6e\u9884\u52a0\u8f7d" : "";
    }

    public static JDJSONObject getRequestLog(String str, String str2, Object obj, Object obj2) {
        JDJSONObject jDJSONObject = new JDJSONObject();
        jDJSONObject.put("type", (Object) str);
        jDJSONObject.put("method", (Object) str2);
        jDJSONObject.put("header", (Object) (obj != null ? obj.toString() : ""));
        jDJSONObject.put("body", (Object) (obj2 != null ? obj2.toString() : ""));
        return jDJSONObject;
    }

    private void h(final PreloadInfoEntity preloadInfoEntity, String str, OKHttpSetting oKHttpSetting, final String str2, final String str3) {
        Map<String, String> headerMap = oKHttpSetting.getHeaderMap();
        headerMap.put("User-Agent", oKHttpSetting.getUaString());
        headerMap.put(HttpHeaders.ORIGIN, str);
        headerMap.put("Cookie", oKHttpSetting.getCookieString());
        headerMap.put("Referer", str);
        JDJSONObject bodyJson = oKHttpSetting.getBodyJson();
        HybridSettings.Net.DataProviderSettings dataProvider = HybridSettings.Net.getDataProvider();
        if (Log.isDebug()) {
            Log.xLogD("DataProvider", "\u9879\u76ee(id:" + preloadInfoEntity.getAppid() + ")\u53d1\u8d77\u63a5\u53e3\u9884\u52a0\u8f7d\u8bf7\u6c42\uff0c\u8bf7\u6c42URL\uff1a" + str + "\uff0cRequest\uff1a", getRequestLog(preloadInfoEntity.getRequestTypeStr(), oKHttpSetting.isPost() ? "POST" : "GET", headerMap, bodyJson != null ? bodyJson.getInnerMap() : null));
        }
        dataProvider.onPreloadRequest(oKHttpSetting.getRequestUrl(), headerMap, oKHttpSetting.getMapParams(), bodyJson != null ? bodyJson.getInnerMap() : null, oKHttpSetting.isPost(), new HybridSettings.Net.PreloadCallback() { // from class: com.jd.libs.hybrid.preload.DataProvider.3
            @Override // com.jd.libs.hybrid.base.HybridSettings.Net.PreloadCallback
            public void onFail(int i2, String str4) {
                if (Log.isDebug()) {
                    Log.xLogE("DataProvider", "\u9879\u76ee(id:" + preloadInfoEntity.getAppid() + ")\u63a5\u53e3\u9884\u52a0\u8f7d\u8bf7\u6c42\u5931\u8d25\uff0c\u539f\u56e0\uff1acode=" + i2 + ", msg=" + str4);
                }
                if (DataProvider.this.b != null) {
                    DataProvider.this.b.onError(str2);
                }
                DataProvider.this.g(str2, str3);
            }

            @Override // com.jd.libs.hybrid.base.HybridSettings.Net.PreloadCallback
            public void onSuccess(Map<String, String> map, int i2, String str4) {
                if (str4 != null) {
                    if (Log.isDebug()) {
                        Log.xLogD("DataProvider", "\u9879\u76ee(id:" + preloadInfoEntity.getAppid() + ")\u63a5\u53e3\u9884\u52a0\u8f7d\u8bf7\u6c42\u6210\u529f\uff0c\u7ed3\u679c\uff1a", str4);
                    }
                    if (DataProvider.this.b != null) {
                        DataProvider.this.b.onEnd(str2, str4);
                    }
                    DataProvider.this.g(str2, str3);
                }
            }
        });
    }

    public static String replace(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return str;
        }
        return str.replaceAll("(" + str2 + "=[^&]*)", str2 + ContainerUtils.KEY_VALUE_DELIMITER + str3);
    }

    @Deprecated
    public static void setExtraParamsGetter(ExtraParamsGetter extraParamsGetter) {
        d = extraParamsGetter;
    }

    public synchronized void destroy(String str) {
        Log.d("DataProvider", "Calling preload destroy for timestamp = " + str);
        RequestHolder requestHolder = this.b;
        if (requestHolder != null) {
            requestHolder.clear(str);
        }
        Map<String, Callback> map = this.a;
        if (map != null) {
            String str2 = null;
            for (String str3 : map.keySet()) {
                if (str3.endsWith(str)) {
                    str2 = str3;
                }
            }
            if (str2 != null) {
                this.a.remove(str2);
            }
        }
        optPerformanceInfo(str);
    }

    public synchronized void jsRequestData(String str, Callback callback) {
        RequestHolder requestHolder = this.b;
        if (requestHolder == null) {
            callback.onResult(-2, null);
            return;
        }
        int status = requestHolder.getStatus(str);
        if (status == 1) {
            callback.onResult(200, this.b.getResult(str));
            this.b.clear(str);
        } else if (status == 2) {
            callback.onResult(-1, null);
            this.b.clear(str);
        } else if (status == 0) {
            if (this.a == null) {
                this.a = new HashMap();
            }
            this.a.put(str, callback);
        } else {
            callback.onResult(-2, null);
        }
    }

    public Map<String, String> optPerformanceInfo(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return this.f6125c.remove(str);
    }

    public synchronized void preLoad(Context context, final String str, final IPreloadParamGetter.PreloadParamGetter preloadParamGetter, final String str2) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if (!TextUtils.isEmpty(str2)) {
            e(str2).put("prlstart", String.valueOf(System.currentTimeMillis()));
            b.k(HttpDnsConfig.PREDOWNLOAD_PARAMS, "prlstart", String.valueOf(System.currentTimeMillis()));
        }
        new PreloadController(context).getEntityByUrl(str, new PreloadController.Callback() { // from class: com.jd.libs.hybrid.preload.DataProvider.1
            @Override // com.jd.libs.hybrid.preload.PreloadController.Callback
            public void onCallback(PreloadInfoEntity preloadInfoEntity) {
                DataProvider.this.d(str, preloadInfoEntity, preloadParamGetter, str2);
            }
        });
    }
}
