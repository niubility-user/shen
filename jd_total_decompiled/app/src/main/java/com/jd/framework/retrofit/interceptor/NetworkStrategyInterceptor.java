package com.jd.framework.retrofit.interceptor;

import com.jd.framework.retrofit.ExceptionReporter;
import com.jingdong.common.network.IpModel;
import com.jingdong.jdsdk.network.JDHttpTookit;
import com.jingdong.sdk.oklog.OKLog;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/* loaded from: classes13.dex */
public class NetworkStrategyInterceptor implements Interceptor {
    protected static AtomicInteger httpIdCounter = new AtomicInteger(0);

    private Request buildHttpDnsRequest(Request request) {
        String httpUrl = request.url().toString();
        String host = request.url().host();
        IpModel ipModelByHost = JDHttpTookit.getEngine().getHttpDnsControllerImpl().getIpModelByHost(host, false);
        if (ipModelByHost != null) {
            return request.newBuilder().addHeader("host", host).url(httpUrl.replaceFirst(host, ipModelByHost.getMaster())).build();
        }
        return request;
    }

    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request request = chain.request();
        Request buildHttpDnsRequest = buildHttpDnsRequest(request);
        int incrementAndGet = httpIdCounter.incrementAndGet();
        Response response = null;
        boolean z = false;
        while (true) {
            try {
                response = chain.proceed(buildHttpDnsRequest);
                if (!response.isSuccessful()) {
                    throw new Exception("http code exception");
                    break;
                }
                return response;
            } catch (Throwable th) {
                if (!z) {
                    ExceptionReporter.reportNetworkError(buildHttpDnsRequest, response, th, incrementAndGet, z);
                    z = true;
                    if (OKLog.D) {
                        OKLog.d(LoggingInterceptor.TAG, "start downgrade...");
                    }
                    buildHttpDnsRequest = request;
                } else {
                    ExceptionReporter.reportNetworkError(buildHttpDnsRequest, response, th, incrementAndGet, z);
                    throw new IOException(th);
                }
            }
        }
    }
}
