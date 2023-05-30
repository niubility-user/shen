package com.jd.aips.common.network;

import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;

/* loaded from: classes12.dex */
public final class OkHttpClientProvider {
    public static final int DEFAULT_TIMEOUT = 30;
    private static volatile OkHttpClientProvider b;
    private final OkHttpClient a = a();

    private OkHttpClientProvider() {
    }

    private OkHttpClient a() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        TimeUnit timeUnit = TimeUnit.SECONDS;
        return builder.connectTimeout(30L, timeUnit).readTimeout(30L, timeUnit).writeTimeout(30L, timeUnit).retryOnConnectionFailure(true).build();
    }

    public static OkHttpClientProvider getInstance() {
        if (b == null) {
            synchronized (OkHttpClientProvider.class) {
                if (b == null) {
                    b = new OkHttpClientProvider();
                }
            }
        }
        return b;
    }

    public OkHttpClient provideOkHttpClient() {
        return provideOkHttpClient(30);
    }

    public OkHttpClient provideOkHttpClient(int i2) {
        if (i2 == 30) {
            return this.a;
        }
        OkHttpClient.Builder newBuilder = this.a.newBuilder();
        long j2 = i2;
        TimeUnit timeUnit = TimeUnit.SECONDS;
        return newBuilder.connectTimeout(j2, timeUnit).readTimeout(j2, timeUnit).writeTimeout(j2, timeUnit).build();
    }
}
