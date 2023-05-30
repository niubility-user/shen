package com.jd.libs.hybrid.preload.okhttp;

import android.net.Uri;
import android.text.TextUtils;
import com.google.common.net.HttpHeaders;
import com.huawei.hms.framework.common.ContainerUtils;
import com.jd.framework.json.JDJSONObject;
import com.jd.libs.hybrid.base.util.Log;
import com.jd.libs.hybrid.preload.DataProvider;
import com.jingdong.common.jdmiaosha.utils.cache.Final;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/* loaded from: classes16.dex */
public class OKHttpSetting extends HttpSetting {
    private String a;
    private boolean b;

    /* renamed from: g */
    private String f6145g;

    /* renamed from: c */
    private JDJSONObject f6142c = new JDJSONObject();
    private Map<String, String> d = new HashMap();

    /* renamed from: e */
    private String f6143e = "";

    /* renamed from: f */
    private String f6144f = "";

    /* renamed from: h */
    private OkHttpClient f6146h = initOkHttpClient();

    public OKHttpSetting(String str, boolean z) {
        this.a = str.trim();
        this.b = z;
    }

    private String a(String str, Map<String, String> map) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        sb2.append(str);
        sb2.append(str.contains("?") ? ContainerUtils.FIELD_DELIMITER : "?");
        sb.append(sb2.toString());
        if (map != null && !map.isEmpty()) {
            for (String str2 : map.keySet()) {
                sb.append(str2);
                sb.append(ContainerUtils.KEY_VALUE_DELIMITER);
                sb.append(map.get(str2));
                sb.append(ContainerUtils.FIELD_DELIMITER);
            }
        }
        return sb.deleteCharAt(sb.length() - 1).toString();
    }

    private String b() {
        if (TextUtils.isEmpty(this.a)) {
            return "";
        }
        Uri parse = !TextUtils.isEmpty(this.a) ? Uri.parse(this.a) : null;
        if (parse == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(parse.getScheme() == null ? "" : parse.getScheme());
        sb.append("://");
        sb.append(parse.getHost() != null ? parse.getHost() : "");
        return sb.toString();
    }

    private Request c(String str, String str2) {
        Log.d("OKHttpSetting", "preload(JSONP/Http) finalUrl --> " + getUrl());
        Map<String, Object> hashMap = new HashMap<>();
        JDJSONObject jDJSONObject = this.f6142c;
        if (jDJSONObject != null) {
            hashMap = jDJSONObject.getInnerMap();
        }
        FormBody.Builder builder = new FormBody.Builder();
        for (String str3 : hashMap.keySet()) {
            builder.add(str3, hashMap.get(str3) != null ? hashMap.get(str3).toString() : "");
        }
        FormBody build = builder.build();
        Request.Builder builder2 = new Request.Builder();
        if (isPost()) {
            builder2.post(build);
        } else {
            builder2.get();
        }
        builder2.url(a(getRequestUrl(), getExtraParams())).removeHeader("User-Agent").addHeader("User-Agent", getUaString()).addHeader(HttpHeaders.ORIGIN, b()).addHeader("Cookie", getCookieString()).addHeader("Referer", this.a);
        Map<String, String> headerMap = getHeaderMap();
        if (headerMap != null) {
            for (String str4 : headerMap.keySet()) {
                builder2.addHeader(str4, headerMap.get(str4));
            }
        }
        Request build2 = builder2.build();
        if (Log.isDebug()) {
            Log.xLogD("OKHttpSetting", "\u9879\u76ee(id:" + str + ")\u53d1\u8d77\u63a5\u53e3\u9884\u52a0\u8f7d\u8bf7\u6c42\uff0cURL\uff1a" + this.f6143e + "\uff0cRequest\uff1a", DataProvider.getRequestLog(str2, build2.method(), build2.headers(), build2.body()));
        }
        return build2;
    }

    public JDJSONObject getBodyJson() {
        return this.f6142c;
    }

    public String getCookieString() {
        return this.f6145g;
    }

    public Map<String, String> getExtraParams() {
        return this.d;
    }

    @Override // com.jingdong.jdsdk.network.toolbox.HttpSetting
    public String getRequestUrl() {
        return TextUtils.isEmpty(this.f6143e) ? "" : this.f6143e;
    }

    public String getUaString() {
        return this.f6144f;
    }

    public OkHttpClient initOkHttpClient() {
        try {
            if (this.f6146h == null) {
                OkHttpClient.Builder builder = new OkHttpClient.Builder();
                TimeUnit timeUnit = TimeUnit.MILLISECONDS;
                return builder.connectTimeout(Final.FIVE_SECOND, timeUnit).readTimeout(3000L, timeUnit).writeTimeout(3000L, timeUnit).followRedirects(false).followSslRedirects(false).retryOnConnectionFailure(false).build();
            }
        } catch (Exception e2) {
            Log.d("OKHttpSetting", "initOkHttpClient error --> " + e2.toString());
        }
        return this.f6146h;
    }

    public void setBodyJson(JDJSONObject jDJSONObject) {
        this.f6142c = jDJSONObject;
    }

    public void setCookieString(String str) {
        if (str == null) {
            str = "";
        }
        this.f6145g = str;
    }

    public void setExtraParams(Map<String, String> map) {
        this.d = map;
    }

    public void setHttpListener(String str, String str2, final OKHttpListener oKHttpListener) {
        if (this.b) {
            Log.d("OKHttpSetting", "preload(GateWay) setHttpListener -->");
            setListener(new HttpGroup.OnAllListener(this) { // from class: com.jd.libs.hybrid.preload.okhttp.OKHttpSetting.1
                @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
                public void onEnd(HttpResponse httpResponse) {
                    Log.w("OKHttpSetting", "preload(GateWay) onEnd --> Response = " + httpResponse.getFastJsonObject());
                    oKHttpListener.onEnd(new OKHttpResponse("", httpResponse));
                }

                @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
                public void onError(HttpError httpError) {
                    Log.w("OKHttpSetting", "preload(GateWay) onError --> Response = " + httpError);
                    if (httpError.getHttpResponse() != null && httpError.getHttpResponse().getFastJsonObject() != null && !httpError.getHttpResponse().getFastJsonObject().isEmpty()) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("preload(GateWay) onEnd --> ");
                        sb.append(httpError.toString());
                        sb.append(", Response = ");
                        sb.append((httpError.getHttpResponse() == null || httpError.getHttpResponse().getFastJsonObject() == null) ? null : httpError.getHttpResponse().getFastJsonObject().toString());
                        Log.d("OKHttpSetting", sb.toString());
                        oKHttpListener.onEnd(new OKHttpResponse("", httpError.getHttpResponse()));
                        return;
                    }
                    Log.d("OKHttpSetting", "preload(GateWay) onError --> " + httpError.toString());
                    oKHttpListener.onError(new OKHttpError(0, "", httpError));
                }

                @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnProgressListener
                public void onProgress(int i2, int i3) {
                }

                @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnStartListener
                public void onStart() {
                }
            });
            if (Log.isDebug()) {
                String str3 = "\u9879\u76ee(id:" + str + ")\u53d1\u8d77\u63a5\u53e3\u9884\u52a0\u8f7d\u8bf7\u6c42\uff0cURL\uff1a" + this.f6143e + "\uff0cRequest\uff1a";
                String str4 = isPost() ? "POST" : "GET";
                Map<String, String> headerMap = getHeaderMap();
                JDJSONObject jDJSONObject = this.f6142c;
                Log.xLogD("OKHttpSetting", str3, DataProvider.getRequestLog(str2, str4, headerMap, jDJSONObject != null ? jDJSONObject.getInnerMap() : null));
            }
            HttpGroupUtils.getHttpGroupaAsynPool().add(this);
            return;
        }
        if (this.f6146h == null) {
            this.f6146h = initOkHttpClient();
        }
        if (this.f6146h == null) {
            Log.d("OKHttpSetting", "initOkHttpClient error");
            return;
        }
        Log.d("OKHttpSetting", "preload(JSONP/Http) enqueue -->");
        this.f6146h.newCall(c(str, str2)).enqueue(new Callback(this) { // from class: com.jd.libs.hybrid.preload.okhttp.OKHttpSetting.2
            @Override // okhttp3.Callback
            public void onFailure(Call call, IOException iOException) {
                Log.w("OKHttpSetting", "preload(JSONP/Http) onError --> " + iOException.toString());
                oKHttpListener.onError(new OKHttpError(0, iOException.getMessage(), null));
            }

            @Override // okhttp3.Callback
            public void onResponse(Call call, Response response) {
                try {
                    String string = response.body().string();
                    oKHttpListener.onEnd(new OKHttpResponse(string, null));
                    Log.w("OKHttpSetting", "preload(JSONP/Http) onEnd --> Response = " + string);
                } catch (Exception e2) {
                    Log.d("OKHttpSetting", "preload(JSONP/Http) onError --> exception=" + e2.toString());
                    oKHttpListener.onError(new OKHttpError(0, e2.getMessage(), null));
                }
            }
        });
    }

    @Override // com.jingdong.jdsdk.network.toolbox.HttpSetting
    public void setRequestUrl(String str) {
        this.f6143e = str;
    }

    public void setUaString(String str) {
        this.f6144f = str;
    }
}
