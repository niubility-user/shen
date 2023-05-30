package com.jd.framework.retrofit.interceptor;

import android.text.TextUtils;
import com.huawei.hms.framework.common.ContainerUtils;
import com.jingdong.jdsdk.network.toolbox.GatewaySignatureHelper;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;

/* loaded from: classes13.dex */
public class ColorSignInterceptor implements Interceptor {
    public static final String TAG = "ColorSignInterceptor";
    private String mAppId;
    private String mSecretKey;

    public ColorSignInterceptor(String str, String str2) {
        this.mAppId = str;
        this.mSecretKey = str2;
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

    private HttpUrl colorSign(Request request) {
        if (!TextUtils.isEmpty(this.mAppId) && !TextUtils.isEmpty(this.mSecretKey)) {
            return HttpUrl.parse(GatewaySignatureHelper.signature(request.url().newBuilder().addQueryParameter("appid", this.mAppId).addQueryParameter("t", String.valueOf(System.currentTimeMillis())).build().toString(), getBodyParam(request), this.mSecretKey));
        }
        return request.url();
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
            newBuilder.url(colorSign(request));
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return chain.proceed(newBuilder.build());
    }
}
