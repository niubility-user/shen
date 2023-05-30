package com.jingdong.app.mall.bundle.updownload;

import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;

/* loaded from: classes3.dex */
public class HttpClient {
    private static final String TAG = "HttpClient";
    private static volatile HttpClient sInstance;
    private OkHttpClient mClient;

    private HttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        this.mClient = builder.connectTimeout(10000L, timeUnit).readTimeout(10000L, timeUnit).writeTimeout(10000L, timeUnit).retryOnConnectionFailure(true).build();
    }

    public static HttpClient getInstance() {
        if (sInstance == null) {
            synchronized (HttpClient.class) {
                if (sInstance == null) {
                    sInstance = new HttpClient();
                }
            }
        }
        return sInstance;
    }

    public OkHttpClient client() {
        return this.mClient;
    }
}
