package com.jd.framework.retrofit.interceptor;

import com.jingdong.sdk.oklog.OKLog;
import okhttp3.Interceptor;
import okhttp3.logging.HttpLoggingInterceptor;

/* loaded from: classes13.dex */
public class LoggingInterceptor implements HttpLoggingInterceptor.Logger {
    public static final String TAG = "LoggingInterceptor";

    public static final Interceptor getLoggingInterceptor() {
        if (OKLog.D) {
            return new HttpLoggingInterceptor(new LoggingInterceptor()).setLevel(HttpLoggingInterceptor.Level.BODY);
        }
        return new HttpLoggingInterceptor(new LoggingInterceptor()).setLevel(HttpLoggingInterceptor.Level.NONE);
    }

    @Override // okhttp3.logging.HttpLoggingInterceptor.Logger
    public void log(String str) {
        if (OKLog.D) {
            OKLog.d(TAG, str);
        }
    }
}
