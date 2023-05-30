package com.jdpay.net.http;

import android.graphics.Bitmap;
import androidx.annotation.NonNull;
import com.google.common.net.HttpHeaders;
import com.jdpay.exception.JPOverflowExecption;
import com.jdpay.lib.converter.Converter;
import com.jdpay.lib.util.JDPayLog;
import com.jdpay.lib.util.Utils;
import com.jdpay.net.Response;
import com.jdpay.net.ResultObserver;
import com.jdpay.net.ServiceFactory;
import com.jdpay.net.converter.ResponseFileConverter;
import com.jdpay.net.http.HttpRequest;
import com.jdpay.net.http.converter.HttpResponseConverter;
import java.io.File;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: classes18.dex */
public class BaseHttpService {
    private static final int POOL_MAX = 64;
    protected ServiceFactory factory;
    protected final ExecutorService pool;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes18.dex */
    public class BuildRequestTask implements Runnable {
        final HttpRequestAdapter adapter;
        final ResultObserver observer;

        public BuildRequestTask(HttpRequestAdapter httpRequestAdapter, ResultObserver resultObserver) {
            this.adapter = httpRequestAdapter;
            this.observer = resultObserver;
        }

        @Override // java.lang.Runnable
        public void run() {
            ((HttpProvider) BaseHttpService.this.factory.getProvider()).enqueue((HttpRequest) this.adapter.request(), new HttpResult(this.observer));
        }
    }

    public BaseHttpService(@NonNull HttpProvider httpProvider) {
        this(httpProvider, 64);
    }

    public static Type getResultObserverReturnType(ResultObserver resultObserver) {
        Type[] genericInterfaces = resultObserver.getClass().getGenericInterfaces();
        if (genericInterfaces.length <= 0) {
            return Object.class;
        }
        Type type = genericInterfaces[0];
        if (type instanceof ParameterizedType) {
            Type[] actualTypeArguments = ((ParameterizedType) type).getActualTypeArguments();
            if (actualTypeArguments.length <= 0) {
                return Object.class;
            }
            type = actualTypeArguments[0];
        }
        return type;
    }

    public <T> T create(Class<T> cls) {
        return (T) ServiceFactory.create(cls, HttpRequestAdapter.class, this.factory);
    }

    public HttpRequest createImageRequest(@NonNull String str, int i2) {
        return createImageRequest(str, (Converter) Utils.newInstance(this.factory.getResponseConverter(i2)));
    }

    public HttpRequest download(@NonNull String str, @NonNull String str2, @NonNull ResultObserver<File> resultObserver) {
        HttpRequest.Builder builder = (HttpRequest.Builder) ((HttpProvider) this.factory.getProvider()).obtainBuilder();
        builder.setUrl(str).setMethod("GET").setResponseConverter((HttpRequest.Builder) new ResponseFileConverter(str2));
        File file = new File(str2);
        if (file.length() > 0) {
            String str3 = "bytes=" + file.length() + "-";
            JDPayLog.i("Range:" + str3);
            builder.addHeader(new HttpRequest.Param(HttpHeaders.RANGE, str3));
        }
        HttpRequest httpRequest = (HttpRequest) builder.build();
        enqueue(httpRequest, resultObserver);
        return httpRequest;
    }

    public <DATA> void enqueue(@NonNull HttpRequestAdapter httpRequestAdapter, @NonNull ResultObserver<DATA> resultObserver) {
        httpRequestAdapter.setResultType(getResultObserverReturnType(resultObserver));
        this.pool.execute(new BuildRequestTask(httpRequestAdapter, resultObserver));
    }

    public HttpResponse<HttpRequest> execute(@NonNull HttpRequest httpRequest, @NonNull Type type) {
        try {
            ((HttpResponseConverter) httpRequest.getResponseConverter()).setType(type);
            return (HttpResponse) getProvider().execute(httpRequest);
        } catch (Exception e2) {
            JDPayLog.e(e2);
            return null;
        }
    }

    public HttpProvider getProvider() {
        return (HttpProvider) this.factory.getProvider();
    }

    public HttpRequest obtainImage(@NonNull String str, int i2, @NonNull ResultObserver<Bitmap> resultObserver) {
        HttpRequest createImageRequest = createImageRequest(str, i2);
        enqueue(createImageRequest, resultObserver);
        return createImageRequest;
    }

    public BaseHttpService(@NonNull HttpProvider httpProvider, int i2) {
        this.factory = new ServiceFactory(httpProvider);
        this.pool = new ThreadPoolExecutor(4, i2, 60L, TimeUnit.SECONDS, new ArrayBlockingQueue(i2), new RejectedExecutionHandler() { // from class: com.jdpay.net.http.BaseHttpService.1
            @Override // java.util.concurrent.RejectedExecutionHandler
            public void rejectedExecution(Runnable runnable, ThreadPoolExecutor threadPoolExecutor) {
                try {
                    if (runnable instanceof BuildRequestTask) {
                        ((BuildRequestTask) runnable).observer.onFailure(new JPOverflowExecption("Thread pool overflow: " + threadPoolExecutor.getActiveCount()));
                    }
                    JDPayLog.i("build task reject:" + threadPoolExecutor.getActiveCount());
                } catch (Throwable th) {
                    JDPayLog.e(th);
                }
            }
        });
    }

    public HttpRequest createImageRequest(@NonNull String str, @NonNull Converter<Response, Bitmap> converter) {
        HttpRequest.Builder builder = (HttpRequest.Builder) ((HttpProvider) this.factory.getProvider()).obtainBuilder();
        builder.setUrl(str).setMethod("GET").setResponseConverter((HttpRequest.Builder) converter);
        return (HttpRequest) builder.build();
    }

    public <DATA> void enqueue(@NonNull HttpRequest httpRequest, @NonNull ResultObserver<DATA> resultObserver) {
        httpRequest.setResultType(getResultObserverReturnType(resultObserver));
        ((HttpProvider) this.factory.getProvider()).enqueue(httpRequest, new HttpResult(resultObserver));
    }

    public HttpRequest obtainImage(@NonNull String str, @NonNull Converter<Response, Bitmap> converter, @NonNull ResultObserver<Bitmap> resultObserver) {
        HttpRequest createImageRequest = createImageRequest(str, converter);
        enqueue(createImageRequest, resultObserver);
        return createImageRequest;
    }
}
