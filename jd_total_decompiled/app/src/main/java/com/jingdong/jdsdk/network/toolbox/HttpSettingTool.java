package com.jingdong.jdsdk.network.toolbox;

import android.net.Uri;
import android.text.TextUtils;
import com.huawei.hms.framework.common.ContainerUtils;
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
    /* JADX WARN: Removed duplicated region for block: B:229:0x012c A[Catch: all -> 0x0189, TryCatch #1 {all -> 0x0189, blocks: (B:227:0x0121, B:229:0x012c, B:230:0x0133, B:232:0x0139, B:234:0x013f, B:235:0x0146, B:237:0x014c, B:238:0x0154, B:240:0x015a, B:242:0x0166, B:244:0x0172), top: B:267:0x0121 }] */
    /* JADX WARN: Removed duplicated region for block: B:237:0x014c A[Catch: all -> 0x0189, TryCatch #1 {all -> 0x0189, blocks: (B:227:0x0121, B:229:0x012c, B:230:0x0133, B:232:0x0139, B:234:0x013f, B:235:0x0146, B:237:0x014c, B:238:0x0154, B:240:0x015a, B:242:0x0166, B:244:0x0172), top: B:267:0x0121 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static void addStatQueryParam(HttpSetting httpSetting, String str, String str2) {
        String stringBuffer;
        HashMap hashMap;
        if (httpSetting.isNeedExtraStatisticParam()) {
            String statisticReportString = JDHttpTookit.getEngine().getStatInfoConfigImpl().getStatisticReportString(httpSetting.getFunctionId(), httpSetting.isNeedGlobalInitialization(), httpSetting.isNeedLoal(), httpSetting.isEnableEncryptTransmission(), httpSetting.getCustomEncryptMapParam(), str2);
            if (httpSetting.isPost()) {
                if (httpSetting.getMapParams() != null) {
                    StringBuffer stringBuffer2 = new StringBuffer();
                    String url = httpSetting.getUrl();
                    stringBuffer2.append(url);
                    if (!httpSetting.getMapParams().isEmpty()) {
                        if (!url.contains("?")) {
                            stringBuffer2.append("?");
                        }
                        for (String str3 : httpSetting.getMapParams().keySet()) {
                            String str4 = httpSetting.getMapParams().get(str3);
                            if (!"body".equalsIgnoreCase(str3)) {
                                stringBuffer2.append(str3 + ContainerUtils.KEY_VALUE_DELIMITER + str4 + ContainerUtils.FIELD_DELIMITER);
                            }
                        }
                    }
                    if (stringBuffer2.charAt(stringBuffer2.length() - 1) == '&') {
                        stringBuffer2.deleteCharAt(stringBuffer2.length() - 1);
                    }
                    stringBuffer2.append(statisticReportString);
                    if (httpSetting.isEnableEncryptTransmission() && httpSetting.isEncryptBody()) {
                        stringBuffer2.append("&bef=1");
                    }
                    httpSetting.setUrl(stringBuffer2.toString());
                    return;
                }
                return;
            }
            String mergerUrlAndParams = HttpGroup.mergerUrlAndParams(httpSetting.getUrl(), httpSetting.getMapParams());
            StringBuffer stringBuffer3 = new StringBuffer();
            stringBuffer3.append(mergerUrlAndParams);
            stringBuffer3.append(statisticReportString);
            if (httpSetting.isEnableEncryptTransmission() && httpSetting.isEncryptBody()) {
                String encryptBody = JDHttpTookit.getEngine().getStatInfoConfigImpl().encryptBody(str);
                if (!TextUtils.isEmpty(encryptBody)) {
                    String encode = URLEncoder.encode(encryptBody, "UTF-8");
                    stringBuffer3.append("&bef=1");
                    stringBuffer3.append("&body=");
                    stringBuffer3.append(encode);
                }
                hashMap = new HashMap();
                if (httpSetting.getCustomMapParam() != null) {
                    hashMap.putAll(httpSetting.getCustomMapParam());
                }
                if (!httpSetting.isEnableEncryptTransmission() && httpSetting.getCustomEncryptMapParam() != null) {
                    hashMap.putAll(httpSetting.getCustomEncryptMapParam());
                }
                if (!hashMap.isEmpty()) {
                    for (String str5 : hashMap.keySet()) {
                        if (!TextUtils.isEmpty(str5) && !TextUtils.isEmpty((CharSequence) hashMap.get(str5))) {
                            stringBuffer3.append(ContainerUtils.FIELD_DELIMITER);
                            stringBuffer3.append(str5);
                            stringBuffer3.append(ContainerUtils.KEY_VALUE_DELIMITER);
                            stringBuffer3.append(URLEncoder.encode((String) hashMap.get(str5), "UTF-8"));
                        }
                    }
                }
                stringBuffer = stringBuffer3.toString();
                if (httpSetting.getType() == 1000 && httpSetting.getType() != 6000) {
                    if (!TextUtils.isEmpty(httpSetting.getFunctionId())) {
                        httpSetting.setUrl(stringBuffer);
                        return;
                    }
                    if (!TextUtils.isEmpty(mergerUrlAndParams) && mergerUrlAndParams.endsWith("?")) {
                        mergerUrlAndParams = mergerUrlAndParams.substring(0, mergerUrlAndParams.length() - 1);
                    }
                    httpSetting.setUrl(mergerUrlAndParams);
                    return;
                }
                httpSetting.setUrl(stringBuffer);
            }
            String encode2 = URLEncoder.encode(str, "UTF-8");
            stringBuffer3.append("&body=");
            stringBuffer3.append(encode2);
            hashMap = new HashMap();
            if (httpSetting.getCustomMapParam() != null) {
            }
            if (!httpSetting.isEnableEncryptTransmission()) {
                hashMap.putAll(httpSetting.getCustomEncryptMapParam());
            }
            if (!hashMap.isEmpty()) {
            }
            stringBuffer = stringBuffer3.toString();
            if (httpSetting.getType() == 1000) {
            }
            httpSetting.setUrl(stringBuffer);
        }
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
