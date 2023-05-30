package com.android.volley.toolbox;

import android.os.SystemClock;
import android.text.TextUtils;
import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.ResponseDelivery;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyLog;
import com.android.volley.error.ServerError;
import com.android.volley.error.VolleyError;
import com.android.volley.utils.UrlUtil;
import com.google.common.net.HttpHeaders;
import com.jd.dynamic.DYConstants;
import com.jd.framework.network.JDCacheChecker;
import com.jd.framework.network.impl.JDCacheCheckerDefault;
import com.jingdong.jdsdk.network.config.RuntimeConfigHelper;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.impl.cookie.DateUtils;

/* loaded from: classes.dex */
public class BasicNetwork implements Network {
    protected static final boolean DEBUG = VolleyLog.DEBUG;
    private static int DEFAULT_POOL_SIZE = 4096;
    private static int SLOW_REQUEST_THRESHOLD_MS = 3000;
    private static final String TAG = "BasicNetwork";
    protected final ConcurrentHashMap<String, String> additionalHeaders;
    private JDCacheChecker cacheChecker;
    protected final HttpStackFactory mHttpStackFactory;
    protected final PlatformNetworkStrategy mPlatformNetworkStrategy;
    protected final PlatformNetworkStrategyV2 mPlatformNetworkStrategyV2;
    protected final ByteArrayPool mPool;

    public BasicNetwork(HttpStackFactory httpStackFactory) {
        this(httpStackFactory, new ByteArrayPool(DEFAULT_POOL_SIZE), null);
    }

    public static void addCacheHeaders(Map<String, String> map, Cache.Entry entry) {
        if (entry == null) {
            return;
        }
        String str = entry.etag;
        if (str != null) {
            map.put(HttpHeaders.IF_NONE_MATCH, str);
        }
        if (entry.lastModified > 0) {
            map.put(HttpHeaders.IF_MODIFIED_SINCE, DateUtils.formatDate(new Date(entry.lastModified)));
        }
    }

    public static void attemptRetryOnException(String str, Request<?> request, VolleyError volleyError) throws VolleyError {
        RetryPolicy retryPolicy = request.getRetryPolicy();
        int timeoutMs = request.getTimeoutMs();
        try {
            retryPolicy.retry(volleyError);
            request.addMarker(String.format("%s-retry [timeout=%s]", str, Integer.valueOf(timeoutMs)));
        } catch (VolleyError e2) {
            request.addMarker(String.format("%s-timeout-giveup [timeout=%s]", str, Integer.valueOf(timeoutMs)));
            throw e2;
        }
    }

    public static Map<String, String> convertHeaders(Header[] headerArr) {
        TreeMap treeMap = new TreeMap(String.CASE_INSENSITIVE_ORDER);
        for (int i2 = 0; i2 < headerArr.length; i2++) {
            treeMap.put(headerArr[i2].getName(), headerArr[i2].getValue());
        }
        return treeMap;
    }

    public byte[] entityToBytes(HttpEntity httpEntity) throws IOException, ServerError {
        PoolingByteArrayOutputStream poolingByteArrayOutputStream = new PoolingByteArrayOutputStream(this.mPool, (int) httpEntity.getContentLength());
        try {
            InputStream content = httpEntity.getContent();
            if (content != null) {
                byte[] buf = this.mPool.getBuf(1024);
                while (true) {
                    int read = content.read(buf);
                    if (read == -1) {
                        break;
                    }
                    poolingByteArrayOutputStream.write(buf, 0, read);
                }
                byte[] byteArray = poolingByteArrayOutputStream.toByteArray();
                try {
                    httpEntity.consumeContent();
                } catch (IOException unused) {
                    VolleyLog.v("Error occured when calling consumingContent", new Object[0]);
                }
                this.mPool.returnBuf(buf);
                poolingByteArrayOutputStream.close();
                return byteArray;
            }
            throw new ServerError();
        } catch (Throwable th) {
            try {
                httpEntity.consumeContent();
            } catch (IOException unused2) {
                VolleyLog.v("Error occured when calling consumingContent", new Object[0]);
            }
            this.mPool.returnBuf(null);
            poolingByteArrayOutputStream.close();
            throw th;
        }
    }

    public ConcurrentHashMap<String, String> getAdditionalHeaders() {
        return this.additionalHeaders;
    }

    @Override // com.android.volley.Network
    public JDCacheChecker getCacheChecker() {
        return this.cacheChecker;
    }

    public HttpStackFactory getHttpStackFactory() {
        return this.mHttpStackFactory;
    }

    protected void logError(String str, String str2, long j2) {
        VolleyLog.v("HTTP ERROR(%s) %d ms to fetch %s", str, Long.valueOf(SystemClock.elapsedRealtime() - j2), str2);
    }

    public void logSlowRequests(long j2, Request<?> request, byte[] bArr, StatusLine statusLine) {
        if (DEBUG || j2 > SLOW_REQUEST_THRESHOLD_MS) {
            Object[] objArr = new Object[6];
            objArr[0] = Integer.valueOf(request.getLogSequence());
            objArr[1] = request;
            objArr[2] = Long.valueOf(j2);
            objArr[3] = bArr != null ? Integer.valueOf(bArr.length) : DYConstants.DY_NULL_STR;
            objArr[4] = Integer.valueOf(statusLine.getStatusCode());
            objArr[5] = Integer.valueOf(request.getRetryPolicy().getCurrentRetryCount());
            VolleyLog.d("[id:%d-] HTTP response for request=<%s> [lifetime=%d], [size=%s], [rc=%d], [retryCount=%s]", objArr);
        }
    }

    @Override // com.android.volley.Network
    public NetworkResponse performRequest(Request<?> request, ResponseDelivery responseDelivery) throws VolleyError {
        String host = UrlUtil.getHost(request.getUrl());
        if (RuntimeConfigHelper.enableApiAdvancedMode()) {
            if (TextUtils.equals("api.m.jd.com", host)) {
                return this.mPlatformNetworkStrategyV2.performRequest(request, responseDelivery);
            }
            return this.mPlatformNetworkStrategy.performRequest(request, responseDelivery);
        }
        return this.mPlatformNetworkStrategy.performRequest(request, responseDelivery);
    }

    @Override // com.android.volley.Network
    public void setCacheChecker(JDCacheChecker jDCacheChecker) {
        this.cacheChecker = jDCacheChecker;
    }

    public BasicNetwork(HttpStackFactory httpStackFactory, ConcurrentHashMap<String, String> concurrentHashMap) {
        this(httpStackFactory, new ByteArrayPool(DEFAULT_POOL_SIZE), concurrentHashMap);
    }

    public BasicNetwork(HttpStackFactory httpStackFactory, ByteArrayPool byteArrayPool, ConcurrentHashMap<String, String> concurrentHashMap) {
        this.mHttpStackFactory = httpStackFactory;
        this.mPool = byteArrayPool;
        this.additionalHeaders = concurrentHashMap;
        this.cacheChecker = new JDCacheCheckerDefault();
        this.mPlatformNetworkStrategy = new ArtNetworkStrategy(this);
        this.mPlatformNetworkStrategyV2 = new APINetworkStrategy(this);
    }
}
