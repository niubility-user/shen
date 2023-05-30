package com.jingdong.common.network.quicpro;

import android.text.TextUtils;
import com.jingdong.common.network.quicpro.UrlRequest;
import com.jingdong.jdsdk.network.utils.ParamEncodeUtil;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.Executor;
import kotlin.text.Typography;

/* loaded from: classes5.dex */
public class QuicProRequest extends UrlRequest {
    private static final String DEFAULT_PARAMS_ENCODING = "UTF-8";
    public static final String TAG = "QuicPro";
    private String host;
    private UrlRequest.Callback mCallback;
    private int mConnectTimeout;
    private Executor mExecutor;
    private String mLogLevel;
    private String mMethod;
    private Map<String, String> mParams;
    private int mReadTimeout;
    private QuicProUrlResponseInfo mResponseInfo;
    private String mUrl;
    private String path;
    private String servicePort;
    private HeadersList mRequestHeaders = new HeadersList();
    private QPEventListener mInternalEventListener = new InternalEventListener();
    private int totalRecvData = 0;
    private ByteArrayOutputStream mOutputStream = new ByteArrayOutputStream();

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public static final class HeadersList extends ArrayList<Map.Entry<String, String>> {
        private HeadersList() {
        }
    }

    /* loaded from: classes5.dex */
    class InternalEventListener implements QPEventListener {
        InternalEventListener() {
        }

        @Override // com.jingdong.common.network.quicpro.QPEventListener
        public void onClosed() {
        }

        @Override // com.jingdong.common.network.quicpro.QPEventListener
        public void onFailure() {
            UrlRequest.Callback callback = QuicProRequest.this.mCallback;
            QuicProRequest quicProRequest = QuicProRequest.this;
            callback.onFailed(quicProRequest, quicProRequest.mResponseInfo, new QuicProException("QuicProException statusCode : " + QuicProRequest.this.mResponseInfo.mStatusCode, new IOException()));
        }

        @Override // com.jingdong.common.network.quicpro.QPEventListener
        public void onRecvBody(ByteBuffer byteBuffer) {
            byte[] bArr = new byte[byteBuffer.limit()];
            byteBuffer.get(bArr);
            QuicProRequest.this.mResponseInfo.mResponseContent = bArr;
            try {
                UrlRequest.Callback callback = QuicProRequest.this.mCallback;
                QuicProRequest quicProRequest = QuicProRequest.this;
                callback.onReadCompleted(quicProRequest, quicProRequest.mResponseInfo, byteBuffer);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

        @Override // com.jingdong.common.network.quicpro.QPEventListener
        public void onRecvData(ByteBuffer byteBuffer) {
            QuicProRequest.access$312(QuicProRequest.this, byteBuffer.remaining());
            byte[] bArr = new byte[byteBuffer.limit()];
            byteBuffer.get(bArr);
            try {
                QuicProRequest.this.mOutputStream.write(bArr);
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }

        @Override // com.jingdong.common.network.quicpro.QPEventListener
        public void onRecvHeader(int i2, int i3, HashMap<String, String> hashMap) {
            try {
                QuicProRequest.this.mResponseInfo.mHeaderMap = hashMap;
                QuicProRequest.this.mResponseInfo.mContentLength = i3;
                QuicProRequest.this.mResponseInfo.mStatusCode = i2;
                UrlRequest.Callback callback = QuicProRequest.this.mCallback;
                QuicProRequest quicProRequest = QuicProRequest.this;
                callback.onResponseStarted(quicProRequest, quicProRequest.mResponseInfo);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

        @Override // com.jingdong.common.network.quicpro.QPEventListener
        public void onSucceeded() {
            if (QuicProRequest.this.mOutputStream.size() > 0) {
                QuicProRequest.this.mResponseInfo.mResponseContent = QuicProRequest.this.mOutputStream.toByteArray();
                try {
                    UrlRequest.Callback callback = QuicProRequest.this.mCallback;
                    QuicProRequest quicProRequest = QuicProRequest.this;
                    callback.onReadCompleted(quicProRequest, quicProRequest.mResponseInfo, null);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            UrlRequest.Callback callback2 = QuicProRequest.this.mCallback;
            QuicProRequest quicProRequest2 = QuicProRequest.this;
            callback2.onSucceeded(quicProRequest2, quicProRequest2.mResponseInfo);
        }
    }

    public QuicProRequest(String str, String str2, UrlRequest.Callback callback, Executor executor) {
        if (str == null) {
            throw new NullPointerException("url is required");
        }
        if (callback == null) {
            throw new NullPointerException("Listener is required");
        }
        if (executor != null) {
            this.mUrl = str;
            this.mCallback = callback;
            this.mExecutor = executor;
            this.servicePort = str2;
            this.mResponseInfo = new QuicProUrlResponseInfo();
            return;
        }
        throw new NullPointerException("Executor is required");
    }

    static /* synthetic */ int access$312(QuicProRequest quicProRequest, int i2) {
        int i3 = quicProRequest.totalRecvData + i2;
        quicProRequest.totalRecvData = i3;
        return i3;
    }

    private String encodeParameters(Map<String, String> map, String str, boolean z) {
        StringBuilder sb = new StringBuilder();
        if (z) {
            try {
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    sb.append(URLEncoder.encode(entry.getKey(), str));
                    sb.append('=');
                    sb.append(URLEncoder.encode(entry.getValue(), str));
                    sb.append(Typography.amp);
                }
                return sb.toString();
            } catch (UnsupportedEncodingException e2) {
                throw new RuntimeException("Encoding not supported: " + str, e2);
            }
        }
        for (Map.Entry<String, String> entry2 : map.entrySet()) {
            sb.append(entry2.getKey());
            sb.append('=');
            sb.append(entry2.getValue());
            sb.append(Typography.amp);
        }
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.common.network.quicpro.UrlRequest
    public void addHeader(String str, String str2) {
        if (str == null) {
            throw new NullPointerException("Invalid header name.");
        }
        if (str2 != null) {
            this.mRequestHeaders.add(new AbstractMap.SimpleImmutableEntry(str, str2));
            return;
        }
        throw new NullPointerException("Invalid header value.");
    }

    public String encodeStr(String str) {
        try {
            return URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
            return str;
        }
    }

    public String getBody() {
        Map<String, String> params = getParams();
        if (params == null || params.size() <= 0) {
            return null;
        }
        return encodeParameters(params, getParamsEncoding(), true);
    }

    public String getBodyContentType() {
        return "application/x-www-form-urlencoded; charset=" + getParamsEncoding();
    }

    protected Map<String, String> getParams() {
        Map<String, String> map = this.mParams;
        if (map != null) {
            return map;
        }
        return null;
    }

    protected String getParamsEncoding() {
        return "UTF-8";
    }

    @Override // com.jingdong.common.network.quicpro.UrlRequest
    public void setConnectTimeout(int i2) {
        this.mConnectTimeout = i2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.common.network.quicpro.UrlRequest
    public void setHttpMethod(String str) {
        if (str != null) {
            this.mMethod = str;
            return;
        }
        throw new NullPointerException("Method is required.");
    }

    public void setLogLevel(String str) {
        this.mLogLevel = str;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.common.network.quicpro.UrlRequest
    public void setParam(Map<String, String> map) {
        this.mParams = map;
    }

    @Override // com.jingdong.common.network.quicpro.UrlRequest
    public void setReadTimeout(int i2) {
        this.mReadTimeout = i2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.common.network.quicpro.UrlRequest
    public void setUploadDataProvider(UploadDataProvider uploadDataProvider, Executor executor) {
    }

    @Override // com.jingdong.common.network.quicpro.UrlRequest
    public void start() {
        if (TextUtils.isEmpty(this.mLogLevel)) {
            this.mLogLevel = "debug";
        }
        HashMap hashMap = new HashMap();
        Iterator<Map.Entry<String, String>> it = this.mRequestHeaders.iterator();
        while (it.hasNext()) {
            Map.Entry<String, String> next = it.next();
            String str = "header entry key : " + next.getKey() + " , value : " + next.getValue();
            hashMap.put(next.getKey().toLowerCase(), next.getValue());
        }
        hashMap.put("content-type", ParamEncodeUtil.DEFAULT_CONTENT_TYPE);
        if (TextUtils.isEmpty(this.mUrl)) {
            return;
        }
        if (TextUtils.equals(this.mMethod, UrlRequest.HTTP_METHOD_GET)) {
            QuicProEngine.getInstance().getQuicProJni().HttpUrlGet(this.mUrl, this.servicePort, hashMap, this.mInternalEventListener, this.mConnectTimeout, this.mReadTimeout);
        } else if (TextUtils.equals(this.mMethod, UrlRequest.HTTP_METHOD_POST)) {
            String body = getBody();
            if (!TextUtils.isEmpty(body)) {
                String str2 = "Post param : " + body;
                QuicProEngine.getInstance().getQuicProJni().HttpUrlPost(this.mUrl, this.servicePort, hashMap, body, this.mInternalEventListener, this.mConnectTimeout, this.mReadTimeout);
                return;
            }
            throw new IllegalArgumentException("post body \u4f53\u53c2\u6570\u4e0d\u80fd\u4e3a\u7a7a");
        }
    }

    public QuicProRequest(String str, String str2, String str3, UrlRequest.Callback callback, Executor executor) {
        if (callback == null) {
            throw new NullPointerException("Listener is required");
        }
        if (executor != null) {
            this.mCallback = callback;
            this.mExecutor = executor;
            this.host = str;
            this.path = str2;
            this.servicePort = str3;
            this.mResponseInfo = new QuicProUrlResponseInfo();
            return;
        }
        throw new NullPointerException("Executor is required");
    }
}
