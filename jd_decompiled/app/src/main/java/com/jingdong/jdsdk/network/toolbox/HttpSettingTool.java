package com.jingdong.jdsdk.network.toolbox;

import android.net.Uri;
import android.text.TextUtils;
import com.jingdong.common.network.quicpro.UrlRequest;
import com.jingdong.jdsdk.network.JDHttpTookit;
import com.jingdong.jdsdk.network.config.InternalConfiguration;
import com.jingdong.jdsdk.network.utils.ParamEncodeUtil;
import com.jingdong.jdsdk.utils.NetUtils;
import com.jingdong.sdk.oklog.OKLog;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes14.dex */
public class HttpSettingTool {
    private static String TAG = "HttpSettingTool";
    public static boolean isNetPromptAgree;
    private static int connectTimeout = Integer.parseInt(InternalConfiguration.getProperty("connectTimeout"));
    private static int connectTimeoutFor2G = Integer.parseInt(InternalConfiguration.getProperty("connectTimeoutFor2G"));
    private static int connectTimeoutForWIFI = Integer.parseInt(InternalConfiguration.getProperty("connectTimeoutForWIFI"));
    private static int readTimeout = Integer.parseInt(InternalConfiguration.getProperty("readTimeout"));
    private static int readTimeoutForWIFI = Integer.parseInt(InternalConfiguration.getProperty("readTimeoutForWIFI"));
    private static int attempts = Integer.parseInt(InternalConfiguration.getProperty("attempts"));
    private static int attemptsTime = Integer.parseInt(InternalConfiguration.getProperty("attemptsTime"));

    public static void addCustomQueryParam(HttpSetting httpSetting) {
        if (OKLog.D) {
            OKLog.d(TAG, "id:" + httpSetting.getId() + "- ..addCustomQueryParam -->> \u5373\u5c06\u62fc\u63a5\u4e1a\u52a1Query\u53c2\u6570");
        }
        if (httpSetting.getQueryParam() != null && httpSetting.getQueryParam().size() > 0) {
            if (OKLog.D) {
                OKLog.d(TAG, "id:" + httpSetting.getId() + "- ..addCustomQueryParam -->> " + httpSetting.getQueryParam());
            }
            try {
                HashMap<String, String> queryParam = httpSetting.getQueryParam();
                String url = httpSetting.getUrl();
                Uri parse = Uri.parse(url);
                for (Map.Entry<String, String> entry : queryParam.entrySet()) {
                    String key = entry.getKey();
                    String value = entry.getValue();
                    String queryParameter = parse.getQueryParameter(key);
                    if (!TextUtils.isEmpty(queryParameter)) {
                        String format = String.format("%s=%s", key, URLEncoder.encode(queryParameter, "UTF-8"));
                        String format2 = String.format("%s=%s", key, value);
                        if (OKLog.D) {
                            OKLog.d(TAG, "id:" + httpSetting.getId() + "- ..addCustomQueryParam -->> " + format + " \u66ff\u6362\u4e3a " + format2);
                        }
                        url = url.replace(format, format2);
                    } else {
                        url = url + String.format("&%s=%s", key, value);
                    }
                }
                if (OKLog.D) {
                    OKLog.d(TAG, "id:" + httpSetting.getId() + "- ..addCustomQueryParam -->> \u751f\u6210url " + url);
                }
                httpSetting.setUrl(url);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        } else if (OKLog.D) {
            OKLog.d(TAG, "id:" + httpSetting.getId() + "- ..addCustomQueryParam -->> \u7528\u6237\u672a\u4f20\u9012Query\u53c2\u6570");
        }
    }

    public static void addGuardVerifyLmtCode(HttpSetting httpSetting) {
        try {
            if (!JDHttpTookit.getEngine().getGuardVerifyPlugin().onLineSwitchOpen() || TextUtils.isEmpty(httpSetting.getHost())) {
                return;
            }
            if (httpSetting.getHost().contains("api.m.jd.com") || httpSetting.getHost().contains("api.m.jd.care")) {
                httpSetting.putMapParams("lmt", JDHttpTookit.getEngine().getGuardVerifyPlugin().getLmtValue());
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* JADX WARN: Can't wrap try/catch for region: R(15:36|(1:37)|(1:91)(11:41|(1:43)|46|47|(1:49)|50|(1:54)|55|(3:57|(4:60|(3:65|66|67)|68|58)|71)|73|(2:88|89)(2:77|(2:79|80)(4:81|(1:85)|86|87)))|44|46|47|(0)|50|(2:52|54)|55|(0)|73|(1:75)|88|89) */
    /* JADX WARN: Removed duplicated region for block: B:134:0x012c A[Catch: all -> 0x0189, TryCatch #1 {all -> 0x0189, blocks: (B:132:0x0121, B:134:0x012c, B:135:0x0133, B:137:0x0139, B:139:0x013f, B:140:0x0146, B:142:0x014c, B:143:0x0154, B:145:0x015a, B:147:0x0166, B:149:0x0172), top: B:172:0x0121 }] */
    /* JADX WARN: Removed duplicated region for block: B:142:0x014c A[Catch: all -> 0x0189, TryCatch #1 {all -> 0x0189, blocks: (B:132:0x0121, B:134:0x012c, B:135:0x0133, B:137:0x0139, B:139:0x013f, B:140:0x0146, B:142:0x014c, B:143:0x0154, B:145:0x015a, B:147:0x0166, B:149:0x0172), top: B:172:0x0121 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void addStatQueryParam(com.jingdong.jdsdk.network.toolbox.HttpSetting r8, java.lang.String r9, java.lang.String r10) {
        /*
            Method dump skipped, instructions count: 460
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.jdsdk.network.toolbox.HttpSettingTool.addStatQueryParam(com.jingdong.jdsdk.network.toolbox.HttpSetting, java.lang.String, java.lang.String):void");
    }

    public static void doSignUsingJdGuard(HttpSetting httpSetting, String str) {
        URI uri;
        if (TextUtils.isEmpty(httpSetting.getFunctionId()) || JDHttpTookit.getEngine().getJdGuardPlugin() == null || !JDHttpTookit.getEngine().getJdGuardPlugin().isEnable() || !JDHttpTookit.getEngine().getJdGuardPlugin().isInWhiteList(httpSetting.getFunctionId())) {
            return;
        }
        String str2 = httpSetting.isPost() ? UrlRequest.HTTP_METHOD_POST : UrlRequest.HTTP_METHOD_GET;
        boolean isPost = httpSetting.isPost();
        Map<String, String> map = null;
        String str3 = isPost ? ParamEncodeUtil.DEFAULT_CONTENT_TYPE : null;
        try {
            uri = new URI(httpSetting.getUrl());
        } catch (URISyntaxException unused) {
            uri = null;
        }
        try {
            map = JDHttpTookit.getEngine().getJdGuardPlugin().genSign(uri, !TextUtils.isEmpty(str) ? str.getBytes() : null, str3, str2, isPost);
        } catch (Throwable unused2) {
        }
        if (map == null || map.isEmpty()) {
            return;
        }
        Map<String, String> headerMap = httpSetting.getHeaderMap();
        if (headerMap == null || headerMap.isEmpty()) {
            headerMap = new HashMap<>();
        }
        headerMap.putAll(map);
        httpSetting.setHeaderMap(headerMap);
    }

    public static String getBodyParam(HttpSetting httpSetting) {
        String str;
        if (httpSetting.getMapParams() == null || httpSetting.getMapParams().isEmpty() || !httpSetting.getMapParams().containsKey("body")) {
            str = null;
        } else {
            str = httpSetting.getMapParams().get("body");
            if (!httpSetting.isPost()) {
                try {
                    str = URLDecoder.decode(str, HttpSetting.charset);
                } catch (Throwable unused) {
                }
            }
        }
        if (TextUtils.isEmpty(str)) {
            str = httpSetting.getJsonParamsString();
        }
        if (TextUtils.equals("{}", str) && httpSetting.getMapParams() != null && httpSetting.getMapParams().containsKey("body")) {
            String str2 = httpSetting.getMapParams().get("body");
            if (!TextUtils.equals(str2, "%7B%7D")) {
                str = str2;
            }
        }
        if (OKLog.D) {
            OKLog.d(TAG, "id:" + httpSetting.getId() + "- ..body -->> " + str);
        }
        if (httpSetting.getMapParams() == null || !httpSetting.getMapParams().containsKey("body")) {
            httpSetting.putMapParams("body", str);
        }
        return str;
    }

    public static HttpSetting setupBaseParams(HttpRequest httpRequest) {
        String str;
        HttpSetting httpSetting = httpRequest.getHttpSetting();
        if (OKLog.I && httpSetting.getFunctionId() != null) {
            OKLog.i(TAG, "id:" + httpSetting.getId() + "- functionId -->> " + httpSetting.getFunctionId());
        }
        if (OKLog.I && httpSetting.getUrl() != null) {
            OKLog.i(TAG, "id:" + httpSetting.getId() + "- url -->> " + httpSetting.getUrl());
        }
        String property = InternalConfiguration.getProperty("host");
        if (httpSetting.getHost() == null) {
            httpSetting.setHost(property);
        }
        if (httpSetting.getFunctionId() != null) {
            httpSetting.putMapParams("functionId", httpSetting.getFunctionId());
            String jsonParamsString = httpSetting.getJsonParamsString();
            if (OKLog.D) {
                OKLog.i(TAG, "id:" + httpSetting.getId() + "- body -->> " + jsonParamsString);
            }
            if (httpSetting.getMapParams() == null || !httpSetting.getMapParams().containsKey("body")) {
                httpSetting.putMapParams("body", jsonParamsString);
            }
        }
        if (TextUtils.isEmpty(httpSetting.getUrl())) {
            if (TextUtils.isEmpty(httpSetting.getUrlPath())) {
                if (!JDHttpTookit.getEngine().isThirdApp()) {
                    str = "https://" + httpSetting.getHost() + "/client.action";
                } else {
                    str = "https://" + httpSetting.getHost() + "/api";
                }
            } else {
                str = "https://" + httpSetting.getHost() + "/" + httpSetting.getUrlPath();
            }
            httpSetting.setUrl(str);
        } else {
            try {
                httpSetting.setHost(new URL(httpSetting.getUrl()).getHost());
            } catch (MalformedURLException unused) {
                if (OKLog.E) {
                    OKLog.e(TAG, "MalformedURLException:" + httpSetting.getUrl());
                }
            }
        }
        if (httpSetting.getAttempts() == 0) {
            httpSetting.setAttempts(attempts);
        }
        if (httpSetting.getAttemptsTime() == 0) {
            httpSetting.setAttemptsTime(attemptsTime);
        }
        if (httpSetting.getConnectTimeout() == 0) {
            httpSetting.setConnectTimeout(connectTimeout);
        }
        if (httpSetting.getReadTimeout() == 0) {
            if (NetUtils.isWifi()) {
                httpSetting.setReadTimeout(readTimeoutForWIFI);
            } else {
                httpSetting.setReadTimeout(readTimeout);
            }
        }
        if (httpSetting.getType() == 5000 || httpSetting.getType() == 500) {
            httpSetting.setPost(false);
        }
        if (httpSetting.getType() == 5000) {
            httpSetting.setLocalFileCache(true);
            httpSetting.setLocalFileCacheTime(2592000000L);
        }
        if (httpSetting.isForeverCache()) {
            httpSetting.setLocalFileCacheTime(3153600000000L);
            httpSetting.setLocalFileCache(true);
        }
        return httpSetting;
    }

    public static void setupParams(HttpRequest httpRequest) {
        if (!JDHttpTookit.getEngine().isThirdApp() && !httpRequest.getHttpSetting().shouldColorSign()) {
            ParamBuilderForJDMall.setupParams(httpRequest);
        } else {
            ParamBuilderForThirdApp.setupParams(httpRequest);
        }
    }
}
