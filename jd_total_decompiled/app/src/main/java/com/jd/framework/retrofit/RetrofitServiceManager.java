package com.jd.framework.retrofit;

import android.text.TextUtils;
import com.android.volley.VolleyLog;
import com.jd.framework.retrofit.convertor.JdJsonConverterFactory;
import com.jd.framework.retrofit.interceptor.LoggingInterceptor;
import com.jd.framework.retrofit.interceptor.NetworkStrategyInterceptor;
import com.jd.framework.retrofit.interceptor.ParamBoxingInterceptor;
import com.jingdong.b.a.a;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

/* loaded from: classes13.dex */
public final class RetrofitServiceManager {
    private static final int CONNECT_TIMEOUT = 10000;
    private static final int READ_TIMEOUT = 15000;
    public static final String TAG = "RetrofitServiceManager";
    public ConcurrentHashMap<String, Retrofit> cachedRetrofitMap;
    public OkHttpClient okHttpClient;

    /* loaded from: classes13.dex */
    private static class SingletonHolder {
        private static final RetrofitServiceManager INSTANCE = new RetrofitServiceManager();

        private SingletonHolder() {
        }
    }

    public static RetrofitServiceManager getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public Retrofit getRetrofit(String str) {
        if (!TextUtils.isEmpty(str)) {
            String format = String.format("%s%s", str.endsWith(".care") ? "http://" : "https://", str);
            if (this.cachedRetrofitMap.containsKey(format)) {
                return this.cachedRetrofitMap.get(format);
            }
            Retrofit build = new Retrofit.Builder().client(this.okHttpClient).baseUrl(format).addConverterFactory(JdJsonConverterFactory.create()).addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build();
            this.cachedRetrofitMap.put(format, build);
            return build;
        }
        throw new IllegalArgumentException("host shouldn't be null");
    }

    public <T> T getService(Class<T> cls) {
        return (T) getRetrofit("api.m.jd.com").create(cls);
    }

    public void init() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        this.okHttpClient = builder.connectTimeout(10000L, timeUnit).readTimeout(15000L, timeUnit).addInterceptor(new ParamBoxingInterceptor()).addInterceptor(new NetworkStrategyInterceptor()).addInterceptor(new a(VolleyLog.DEBUG)).addInterceptor(LoggingInterceptor.getLoggingInterceptor()).build();
        this.cachedRetrofitMap = new ConcurrentHashMap<>();
    }

    private RetrofitServiceManager() {
        this.cachedRetrofitMap = null;
        init();
    }

    public <T> T getService(String str, Class<T> cls) {
        return (T) getRetrofit(str).create(cls);
    }
}
