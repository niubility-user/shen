package com.jdpay.net.http.okhttp;

import android.util.SparseArray;
import androidx.annotation.NonNull;
import com.jdpay.lib.converter.Converter;
import com.jdpay.lib.util.JDPayLog;
import com.jdpay.net.JPNetworkException;
import com.jdpay.net.http.HttpProvider;
import com.jdpay.net.http.HttpResponse;
import com.jdpay.net.http.HttpResult;
import com.jdpay.net.http.okhttp.OkhttpRequest;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.ResponseBody;

/* loaded from: classes18.dex */
public class OkhttpProvider implements HttpProvider<OkhttpRequest, Converter<HttpResponse, ?>, OkhttpRequest.OkhttpBuilder, HttpResult> {
    protected final OkHttpClient client;
    private final SparseArray<OkhttpRequest> requests = new SparseArray<>();

    /* loaded from: classes18.dex */
    public class CallbackImp implements Callback {
        final OkhttpRequest request;
        final HttpResult result;

        @Override // okhttp3.Callback
        public void onFailure(Call call, IOException iOException) {
            OkhttpProvider.this.removeRequest(this.request.getId());
            this.result.onResponse(null, iOException);
        }

        @Override // okhttp3.Callback
        public void onResponse(Call call, Response response) {
            OkhttpProvider.this.removeRequest(this.request.getId());
            OkhttpResponse okhttpResponse = new OkhttpResponse(this.request, response);
            ResponseBody body = response.body();
            long contentLength = body == null ? 0L : body.contentLength();
            JDPayLog.i("Http Status:" + okhttpResponse.httpCode() + " isSucessful:" + okhttpResponse.isSuccessful() + " Length:" + contentLength);
            if (okhttpResponse.isSuccessful()) {
                this.result.onResponse(okhttpResponse, null);
            } else {
                this.result.onResponse(null, new JPNetworkException(okhttpResponse));
            }
            okhttpResponse.close();
        }

        private CallbackImp(@NonNull OkhttpRequest okhttpRequest, @NonNull HttpResult httpResult) {
            OkhttpProvider.this = r1;
            this.request = okhttpRequest;
            this.result = httpResult;
        }
    }

    public OkhttpProvider() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        TimeUnit timeUnit = TimeUnit.SECONDS;
        this.client = builder.connectTimeout(10L, timeUnit).readTimeout(10L, timeUnit).writeTimeout(10L, timeUnit).build();
    }

    public void removeRequest(int i2) {
        synchronized (this.requests) {
            this.requests.remove(i2);
        }
    }

    @Override // com.jdpay.net.Provider
    public OkhttpRequest cancel(int i2) {
        OkhttpRequest okhttpRequest;
        synchronized (this.requests) {
            okhttpRequest = this.requests.get(i2);
            if (okhttpRequest != null) {
                okhttpRequest.cancel();
                this.requests.remove(i2);
            }
        }
        return okhttpRequest;
    }

    @Override // com.jdpay.net.Provider
    public void enqueue(@NonNull OkhttpRequest okhttpRequest, @NonNull HttpResult httpResult) {
        okhttpRequest.call = this.client.newCall(okhttpRequest.rawRequest);
        synchronized (this.requests) {
            this.requests.put(okhttpRequest.getId(), okhttpRequest);
        }
        try {
            okhttpRequest.call.enqueue(new CallbackImp(okhttpRequest, httpResult));
        } catch (Throwable th) {
            httpResult.onResponse(null, th);
        }
    }

    @Override // com.jdpay.net.Provider
    public HttpResponse<OkhttpRequest> execute(@NonNull OkhttpRequest okhttpRequest) throws IOException {
        okhttpRequest.call = this.client.newCall(okhttpRequest.rawRequest);
        synchronized (this.requests) {
            this.requests.put(okhttpRequest.getId(), okhttpRequest);
        }
        try {
            OkhttpResponse okhttpResponse = new OkhttpResponse(okhttpRequest, okhttpRequest.call.execute());
            synchronized (this.requests) {
                this.requests.remove(okhttpRequest.getId());
            }
            return okhttpResponse;
        } catch (Throwable th) {
            synchronized (this.requests) {
                this.requests.remove(okhttpRequest.getId());
                throw th;
            }
        }
    }

    @Override // com.jdpay.net.Provider
    public OkhttpRequest.OkhttpBuilder obtainBuilder() {
        return new OkhttpRequest.OkhttpBuilder();
    }

    public OkhttpProvider(OkHttpClient okHttpClient) {
        this.client = okHttpClient;
    }
}
