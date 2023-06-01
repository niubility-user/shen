package com.jd.framework.retrofit.interceptor;

import android.text.TextUtils;
import com.huawei.hms.framework.common.ContainerUtils;
import com.jingdong.jdsdk.network.JDHttpTookit;
import com.jingdong.jdsdk.network.config.InternalConfiguration;
import com.jingdong.jdsdk.network.config.RuntimeConfigHelper;
import com.jingdong.jdsdk.network.dependency.IStatInfoConfig;
import com.jingdong.jdsdk.network.toolbox.GatewaySignatureHelper;
import com.jingdong.sdk.oklog.OKLog;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;

/* loaded from: classes13.dex */
public class ParamBoxingInterceptor implements Interceptor {
    public static final String TAG = "Statistic";
    private static ConcurrentHashMap<String, String> additionalHeaders;

    // shen 增加请求参数用的
    private void addQueryParams(Request request, String str, HttpUrl.Builder builder, boolean z) {
        Map<String, String> urlParams;
        Map<String, String> urlParams2;
        /** 三方APP, 不必关注
        if (JDHttpTookit.getEngine().isThirdApp()) {
            String secretKey = JDHttpTookit.getEngine().getSecretKey();
            builder.addQueryParameter("appid", JDHttpTookit.getEngine().getAppId()).addQueryParameter("t", String.valueOf(System.currentTimeMillis()));
            Map<String, String> colorStatParamStr = JDHttpTookit.getEngine().getStatInfoConfigImpl().getColorStatParamStr(true, true, z, null, null);
            HttpUrl.Builder newBuilder = builder.build().newBuilder();
            if (colorStatParamStr == null || colorStatParamStr.isEmpty()) {
                return;
            }
            String str2 = colorStatParamStr.get(IStatInfoConfig.KEY_CLEARTEXT);
            String str3 = colorStatParamStr.get(IStatInfoConfig.KEY_ENCRYPT);
            if (TextUtils.isEmpty(str2)) {
                return;
            }
            Map<String, String> urlParams3 = getUrlParams(str2);
            if (urlParams3 != null && !urlParams3.isEmpty()) {
                for (String str4 : urlParams3.keySet()) {
                    newBuilder.addQueryParameter(str4, urlParams3.get(str4));
                }
            }
            if (TextUtils.equals(request.url().queryParameter("bef"), "1")) {
                newBuilder.removeAllQueryParameters("bef");
            }
            String signature3 = JDHttpTookit.getEngine().isNeedVerifySignature() ? GatewaySignatureHelper.signature3(newBuilder.build().toString(), str, secretKey) : "";
            if (z && !TextUtils.isEmpty(str3)) {
                Map<String, String> urlParams4 = getUrlParams(str3);
                if (urlParams4 != null && !urlParams4.isEmpty()) {
                    for (String str5 : urlParams4.keySet()) {
                        if (TextUtils.equals(str5, "ep")) {
                            builder.addEncodedQueryParameter(str5, urlParams4.get(str5));
                        } else {
                            builder.addQueryParameter(str5, urlParams4.get(str5));
                        }
                    }
                }
                if (TextUtils.isEmpty(signature3)) {
                    return;
                }
                builder.addQueryParameter("sign", signature3);
                return;
            }
            Map<String, String> urlParams5 = getUrlParams(str2);
            if (urlParams5 != null && !urlParams5.isEmpty()) {
                for (String str6 : urlParams5.keySet()) {
                    builder.addQueryParameter(str6, urlParams5.get(str6));
                }
            }
            if (TextUtils.isEmpty(signature3)) {
                return;
            }
            builder.addQueryParameter("sign", signature3);
            return;
        }*/
        String statisticReportString = JDHttpTookit.getEngine().getStatInfoConfigImpl().getStatisticReportString("", true, true, z, null, null);
        if (!TextUtils.isEmpty(statisticReportString) && (urlParams2 = getUrlParams(statisticReportString)) != null && !urlParams2.isEmpty()) {
            for (String str7 : urlParams2.keySet()) {
                if (TextUtils.equals(str7, "ep")) {
                    builder.addEncodedQueryParameter(str7, urlParams2.get(str7));
                } else {
                    builder.addQueryParameter(str7, urlParams2.get(str7));
                }
            }
        }
        String queryParameter = request.url().queryParameter("functionId");
        String property = InternalConfiguration.getProperty("client", "");
        String versionName = JDHttpTookit.getEngine().getStatInfoConfigImpl().getVersionName();
        String deviceUUID = JDHttpTookit.getEngine().getStatInfoConfigImpl().getDeviceUUID(z);
        if (OKLog.D) {
            OKLog.d(TAG, "- ..functionId -->> " + queryParameter);
            OKLog.d(TAG, "- ..body -->> " + str);
            OKLog.d(TAG, "- ..uuid -->> " + deviceUUID);
            OKLog.d(TAG, "- ..client -->> " + property);
            OKLog.d(TAG, "- ..clientVersion -->> " + versionName);
        }
        try {
            String signature = JDHttpTookit.getEngine().getSignatureHandlerImpl().signature(JDHttpTookit.getEngine().getApplicationContext(), queryParameter, str, deviceUUID, property, versionName);
            if (OKLog.D) {
                OKLog.d("Signature", "native signature sucess " + signature);
            }
            if (TextUtils.isEmpty(signature) || (urlParams = getUrlParams(signature)) == null || urlParams.isEmpty()) {
                return;
            }
            for (String str8 : urlParams.keySet()) {
                builder.addQueryParameter(str8, urlParams.get(str8));
            }
        } catch (Exception unused) {
        }
    }

    public static void adjustBodyParam(Request request, String str, Request.Builder builder, HttpUrl.Builder builder2, boolean z) {
        FormBody build;
        if (TextUtils.isEmpty(str)) {
            str = "{}";
        }
        if (TextUtils.equals("GET", request.method())) {
            if (TextUtils.isEmpty(request.url().queryParameter("body"))) {
                builder2.addQueryParameter("body", str);
                return;
            }
            return;
        }
        if (TextUtils.equals(request.url().queryParameter("bef"), "1") && z) {
            build = new FormBody.Builder().add("body", JDHttpTookit.getEngine().getStatInfoConfigImpl().encryptBody(str)).build();
        } else {
            build = new FormBody.Builder().add("body", str).build();
            builder2.removeAllQueryParameters("bef");
        }
        builder.post(build);
    }

    public static String bodyToString(RequestBody requestBody) {
        try {
            Buffer buffer = new Buffer();
            if (requestBody != null) {
                requestBody.writeTo(buffer);
                return buffer.readUtf8();
            }
            return "";
        } catch (IOException unused) {
            return "{}";
        }
    }

    private static synchronized ConcurrentHashMap getAdditionalHeaders() {
        ConcurrentHashMap<String, String> concurrentHashMap;
        synchronized (ParamBoxingInterceptor.class) {
            if (additionalHeaders == null) {
                ConcurrentHashMap<String, String> concurrentHashMap2 = new ConcurrentHashMap<>();
                concurrentHashMap2.put("Connection", "keep-alive");
                concurrentHashMap2.put("Charset", "UTF-8");
                additionalHeaders = concurrentHashMap2;
            }
            concurrentHashMap = additionalHeaders;
        }
        return concurrentHashMap;
    }

    public static String getBodyParam(Request request) {
        Map<String, String> urlParams;
        if (TextUtils.equals("GET", request.method())) {
            String queryParameter = request.url().queryParameter("body");
            return !TextUtils.isEmpty(queryParameter) ? queryParameter : "{}";
        }
        String bodyToString = bodyToString(request.body());
        if (TextUtils.isEmpty(bodyToString) || (urlParams = getUrlParams(bodyToString)) == null || !urlParams.containsKey("body")) {
            return "{}";
        }
        try {
            return URLDecoder.decode(urlParams.get("body"), "UTF-8");
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
            return "{}";
        }
    }

    public static Map<String, String> getUrlParams(String str) {
        HashMap hashMap = new HashMap(0);
        if (TextUtils.isEmpty(str)) {
            return hashMap;
        }
        for (String str2 : str.split(ContainerUtils.FIELD_DELIMITER)) {
            String[] split = str2.split(ContainerUtils.KEY_VALUE_DELIMITER);
            if (split.length == 2) {
                hashMap.put(split[0], split[1]);
            }
        }
        return hashMap;
    }

    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request request = chain.request();
        Request.Builder newBuilder = request.newBuilder();
        try {
            Headers.Builder newBuilder2 = request.headers().newBuilder();
            HashMap hashMap = new HashMap();
            hashMap.putAll(getAdditionalHeaders());
            boolean enableEncryptTransmission = RuntimeConfigHelper.enableEncryptTransmission(request.url().host());
            hashMap.putAll(JDHttpTookit.getEngine().getStatInfoConfigImpl().getUniformHeaderField(true, enableEncryptTransmission));
            for (Map.Entry entry : hashMap.entrySet()) {
                newBuilder2.add((String) entry.getKey(), (String) entry.getValue());
            }
            newBuilder.headers(newBuilder2.build());
            HttpUrl.Builder newBuilder3 = request.url().newBuilder();
            String bodyParam = getBodyParam(request);
            addQueryParams(request, bodyParam, newBuilder3, enableEncryptTransmission);
            adjustBodyParam(request, bodyParam, newBuilder, newBuilder3, enableEncryptTransmission);
            newBuilder.url(newBuilder3.build());
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return chain.proceed(newBuilder.build());
    }
}
