package com.jdpay.net.http.okhttp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.jdpay.net.http.HttpResponse;
import java.io.IOException;
import java.io.InputStream;
import okhttp3.Response;
import okhttp3.ResponseBody;

/* loaded from: classes18.dex */
public class OkhttpResponse extends HttpResponse<OkhttpRequest> {
    final Response rawResponse;

    public OkhttpResponse(@NonNull OkhttpRequest okhttpRequest, @NonNull Response response) {
        super(okhttpRequest);
        this.rawResponse = response;
    }

    @Override // com.jdpay.net.Response
    public void close() {
        super.close();
        try {
            this.rawResponse.close();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // com.jdpay.net.http.HttpResponse
    public String getHeader(String str) {
        return this.rawResponse.header(str);
    }

    @Override // com.jdpay.net.Response
    @Nullable
    public InputStream getRawInputStream() {
        ResponseBody body = this.rawResponse.body();
        if (body == null) {
            return null;
        }
        return body.byteStream();
    }

    @Override // com.jdpay.net.Response
    @Nullable
    public String getString() {
        ResponseBody body = this.rawResponse.body();
        if (body == null) {
            return null;
        }
        try {
            return body.string();
        } catch (IOException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    @Override // com.jdpay.net.http.HttpResponse
    public int httpCode() {
        return this.rawResponse.code();
    }

    @Override // com.jdpay.net.http.HttpResponse
    public boolean isSuccessful() {
        return this.rawResponse.isSuccessful();
    }
}
