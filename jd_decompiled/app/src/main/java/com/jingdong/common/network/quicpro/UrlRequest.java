package com.jingdong.common.network.quicpro;

import java.nio.ByteBuffer;
import java.util.Map;
import java.util.concurrent.Executor;

/* loaded from: classes5.dex */
public abstract class UrlRequest {
    public static final String HTTP_METHOD_GET = "Get";
    public static final String HTTP_METHOD_POST = "Post";

    /* loaded from: classes5.dex */
    public static abstract class Builder {
        public abstract Builder addHeader(String str, String str2);

        public abstract Builder allowDirectExecutor();

        public abstract UrlRequest build();

        public abstract Builder disableCache();

        public abstract Builder setConnectTimeout(int i2);

        public abstract Builder setHost(String str);

        public abstract Builder setHttpMethod(String str);

        public abstract Builder setLogLevel(String str);

        public abstract Builder setParam(Map<String, String> map);

        public abstract Builder setPath(String str);

        public abstract Builder setReadTimeout(int i2);

        public abstract Builder setServicePort(String str);

        public abstract Builder setUploadDataProvider(UploadDataProvider uploadDataProvider, Executor executor);
    }

    /* loaded from: classes5.dex */
    public static abstract class Callback {
        public void onCanceled(UrlRequest urlRequest, UrlResponseInfo urlResponseInfo) {
        }

        public abstract void onFailed(UrlRequest urlRequest, UrlResponseInfo urlResponseInfo, QuicProException quicProException);

        public abstract void onReadCompleted(UrlRequest urlRequest, UrlResponseInfo urlResponseInfo, ByteBuffer byteBuffer) throws Exception;

        public abstract void onRedirectReceived(UrlRequest urlRequest, UrlResponseInfo urlResponseInfo, String str) throws Exception;

        public abstract void onResponseStarted(UrlRequest urlRequest, UrlResponseInfo urlResponseInfo) throws Exception;

        public abstract void onSucceeded(UrlRequest urlRequest, UrlResponseInfo urlResponseInfo);
    }

    protected abstract void addHeader(String str, String str2);

    protected abstract void setConnectTimeout(int i2);

    protected abstract void setHttpMethod(String str);

    protected abstract void setParam(Map<String, String> map);

    protected abstract void setReadTimeout(int i2);

    protected abstract void setUploadDataProvider(UploadDataProvider uploadDataProvider, Executor executor);

    public abstract void start();
}
