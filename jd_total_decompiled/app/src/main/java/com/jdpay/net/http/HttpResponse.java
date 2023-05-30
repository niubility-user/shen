package com.jdpay.net.http;

import androidx.annotation.NonNull;
import com.jdpay.net.Response;
import com.jdpay.net.http.HttpRequest;

/* loaded from: classes18.dex */
public abstract class HttpResponse<REQUEST extends HttpRequest> extends Response<REQUEST> {
    public HttpResponse(@NonNull REQUEST request) {
        super(request);
    }

    public abstract String getHeader(String str);

    public abstract int httpCode();

    public abstract boolean isSuccessful();
}
