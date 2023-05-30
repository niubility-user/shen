package com.jdpay.net.http;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.jdpay.lib.converter.Converter;
import com.jdpay.net.Result;
import com.jdpay.net.ResultObserver;
import com.jdpay.net.http.converter.HttpResponseConverter;

/* loaded from: classes18.dex */
public class HttpResult<DATA> extends Result<DATA> {
    protected HttpResponse response;

    public HttpResult(@NonNull ResultObserver<DATA> resultObserver) {
        super(resultObserver);
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [com.jdpay.net.Request] */
    public void onResponse(@Nullable HttpResponse httpResponse, @Nullable Throwable th) {
        this.response = httpResponse;
        this.throwable = th;
        if (httpResponse != null && this.hasProgress) {
            httpResponse.setProgressListener(this);
            httpResponse.setProgressInterval(500);
        }
        Converter<Object, DATA> converter = null;
        if (httpResponse != null) {
            converter = httpResponse.getRequest().getResponseConverter();
            HttpRequest httpRequest = (HttpRequest) httpResponse.getRequest();
            if (converter instanceof HttpResponseConverter) {
                ((HttpResponseConverter) converter).setType(httpRequest.getResultType());
            }
        }
        onHandleResult(httpResponse, converter, th);
    }
}
