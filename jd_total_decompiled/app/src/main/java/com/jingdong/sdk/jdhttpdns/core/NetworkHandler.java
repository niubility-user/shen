package com.jingdong.sdk.jdhttpdns.core;

import android.text.TextUtils;
import com.jingdong.b.a.a;
import com.jingdong.sdk.jdhttpdns.pojo.HttpDnsEvent;
import com.jingdong.sdk.jdhttpdns.pojo.IpModel;
import com.jingdong.sdk.jdhttpdns.utils.DNSLog;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import okhttp3.CacheControl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class NetworkHandler {
    private static final int CONNECT_TIMEOUT = 10000;
    private static final int READ_TIMEOUT = 15000;
    private OkHttpClient mOkHttpClient;

    public NetworkHandler() {
        this.mOkHttpClient = null;
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        this.mOkHttpClient = builder.connectTimeout(10000L, timeUnit).readTimeout(10000L, timeUnit).addInterceptor(new a(DNSLog.D)).retryOnConnectionFailure(false).build();
    }

    List<IpModel> parseResult(Request request, String str) throws Exception {
        if (!TextUtils.isEmpty(str)) {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.getInt("code") == 0) {
                JSONObject optJSONObject = jSONObject.optJSONObject("data");
                if (request != null) {
                    if (request.isPreload()) {
                        return IpModel.preloadParse(optJSONObject);
                    }
                    return IpModel.parse(optJSONObject);
                }
            }
        }
        return new ArrayList();
    }

    String performRequest(Request request) throws Exception {
        String url = request.getUrl();
        HashMap<String, String> header = request.getHeader();
        DNSLog.d("HttpDns Okhttp Request Url : " + url);
        Request.Builder url2 = new Request.Builder().url(url);
        for (String str : header.keySet()) {
            url2.addHeader(str, header.get(str));
            DNSLog.d(str + " : " + header.get(str));
        }
        Response execute = this.mOkHttpClient.newCall(url2.cacheControl(CacheControl.FORCE_NETWORK).build()).execute();
        if (execute.isSuccessful()) {
            String string = execute.body().string();
            DNSLog.d("Httpdns Response :" + string);
            return string;
        }
        throw new Exception("response code : " + execute.code() + " , errMsg : " + execute.message());
    }

    public List<IpModel> requests(Request request) throws Exception {
        while (true) {
            try {
                return parseResult(request, performRequest(request));
            } catch (Exception e2) {
                e2.printStackTrace();
                if (!request.isFinalDowngrade()) {
                    if (request.getInternalResolveListener() != null) {
                        request.getInternalResolveListener().onFailure(new HttpDnsEvent(request.getLastRequestUrlStr(), e2, false), false);
                    }
                } else {
                    if (request.getInternalResolveListener() != null) {
                        request.getInternalResolveListener().onFailure(new HttpDnsEvent(request.getLastRequestUrlStr(), e2, false), true);
                    }
                    throw e2;
                }
            }
        }
    }
}
