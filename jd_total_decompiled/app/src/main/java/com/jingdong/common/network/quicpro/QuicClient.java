package com.jingdong.common.network.quicpro;

import com.android.volley.VolleyLog;
import com.jingdong.common.network.quicpro.UrlRequest;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/* loaded from: classes5.dex */
public class QuicClient {
    public static final String TAG = "Quic-Pro";
    private Executor mExecutor = Executors.newFixedThreadPool(5);

    /* loaded from: classes5.dex */
    public static class MyCallback extends UrlRequest.Callback {
        private final CountDownLatch latch = new CountDownLatch(1);
        public UrlResponseInfo mUrlResponseInfo;

        public UrlResponseInfo getUrlResponseInfo() {
            try {
                this.latch.await();
            } catch (InterruptedException e2) {
                e2.printStackTrace();
            }
            return this.mUrlResponseInfo;
        }

        @Override // com.jingdong.common.network.quicpro.UrlRequest.Callback
        public void onFailed(UrlRequest urlRequest, UrlResponseInfo urlResponseInfo, QuicProException quicProException) {
            this.mUrlResponseInfo = urlResponseInfo;
            this.latch.countDown();
        }

        @Override // com.jingdong.common.network.quicpro.UrlRequest.Callback
        public void onReadCompleted(UrlRequest urlRequest, UrlResponseInfo urlResponseInfo, ByteBuffer byteBuffer) throws Exception {
            String str = "response body string is " + new String(urlResponseInfo.getResponseContent());
        }

        @Override // com.jingdong.common.network.quicpro.UrlRequest.Callback
        public void onRedirectReceived(UrlRequest urlRequest, UrlResponseInfo urlResponseInfo, String str) throws Exception {
        }

        @Override // com.jingdong.common.network.quicpro.UrlRequest.Callback
        public void onResponseStarted(UrlRequest urlRequest, UrlResponseInfo urlResponseInfo) throws Exception {
            String str = "Response statusCode is " + urlResponseInfo.getHttpStatusCode();
            String str2 = "Response Header are " + urlResponseInfo.getAllHeaders();
        }

        @Override // com.jingdong.common.network.quicpro.UrlRequest.Callback
        public void onSucceeded(UrlRequest urlRequest, UrlResponseInfo urlResponseInfo) {
            this.mUrlResponseInfo = urlResponseInfo;
            this.latch.countDown();
        }
    }

    public void performRequest(String str, Map<String, String> map, int i2, Map<String, String> map2, int i3, int i4) throws IOException {
        MyCallback myCallback = new MyCallback();
        UrlRequest.Builder newUrlRequestBuilder = QuicProEngine.getInstance().newUrlRequestBuilder(str, myCallback, this.mExecutor);
        newUrlRequestBuilder.setServicePort("quicpro.jd.com:20000");
        newUrlRequestBuilder.setConnectTimeout(i3);
        newUrlRequestBuilder.setReadTimeout(i4);
        if (map != null && !map.isEmpty()) {
            for (String str2 : map.keySet()) {
                newUrlRequestBuilder.addHeader(str2, map.get(str2));
                if (VolleyLog.DEBUG) {
                    String str3 = str2 + ":" + map.get(str2);
                }
            }
        }
        if (i2 == 0) {
            newUrlRequestBuilder.setHttpMethod(UrlRequest.HTTP_METHOD_GET);
        } else if (i2 == 1) {
            newUrlRequestBuilder.setHttpMethod(UrlRequest.HTTP_METHOD_POST);
            newUrlRequestBuilder.setParam(map2);
        }
        newUrlRequestBuilder.build().start();
        UrlResponseInfo urlResponseInfo = myCallback.getUrlResponseInfo();
        if (urlResponseInfo.getAllHeaders() != null) {
            String str4 = urlResponseInfo.getAllHeaders().get("negotiatedProtocol");
            if (VolleyLog.DEBUG) {
                String str5 = "Protocol Version : " + str4;
            }
        }
        if (urlResponseInfo.getHttpStatusCode() != -1) {
            String str6 = new String(urlResponseInfo.getResponseContent());
            long contentLength = urlResponseInfo.getContentLength();
            if (VolleyLog.DEBUG) {
                String str7 = "response result : " + str6;
                String str8 = "response content length : " + contentLength;
                return;
            }
            return;
        }
        throw new IOException("Could not retrieve response code from Quic Pro connection.");
    }
}
