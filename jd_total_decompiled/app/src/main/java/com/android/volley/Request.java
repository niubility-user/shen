package com.android.volley;

import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.text.TextUtils;
import com.android.volley.Cache;
import com.android.volley.Response;
import com.android.volley.VolleyLog;
import com.android.volley.error.AuthFailureError;
import com.android.volley.error.VolleyError;
import com.jd.framework.network.request.JDRequest;
import com.jd.jdcache.util.UrlHelper;
import com.jingdong.common.utils.LangUtils;
import com.jingdong.jdsdk.network.toolbox.CacheResponseChecker;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import kotlin.text.Typography;

/* loaded from: classes.dex */
public abstract class Request<T> implements Comparable<Request<T>> {
    private static final String DEFAULT_PARAMS_ENCODING = "UTF-8";
    private static final long SLOW_REQUEST_THRESHOLD_MS = 3000;
    private CacheResponseChecker cacheResponseChecker;
    private DownGradeType downGradeType;
    private String httpDnsParam;
    private boolean isCallbackInMainThread;
    private boolean isFinalDowngrade;
    private boolean isForce2HttpFlag;
    private boolean isHttpsDown2HttpRequest;
    private boolean isUseCookies;
    private boolean isUseDialingIP;
    private boolean isUseDomainName;
    private boolean isUseOkhttpFlag;
    private Cache.Entry mCacheEntry;
    private String mCacheKey;
    private int mCacheModel;
    private long mCacheTime;
    private int mCallTimeoutMs;
    private boolean mCanceled;
    private int mConnectTimeoutMs;
    private int mDefaultTrafficStatsTag;
    private Response.ErrorListener mErrorListener;
    private final VolleyLog.MarkerLog mEventLog;
    private Map<String, String> mHeaders;
    private final int mMethod;
    private Map<String, String> mParams;
    private JDRequest.Priority mPriority;
    private int mReadTimeoutMs;
    private long mRequestBirthTime;
    private RequestQueue mRequestQueue;
    private boolean mResponseDelivered;
    private RetryPolicy mRetryPolicy;
    private Integer mSequence;
    protected String mServiceKey;
    private boolean mShouldCache;
    private boolean mShouldRetryServerErrors;
    private Object mTag;
    private String mUrl;
    private int maxRetryNum;
    private boolean needRetryOnNetworkLayer;

    /* loaded from: classes.dex */
    public enum DownGradeType {
        DownGrade2Domain,
        DownGrade2Ip,
        DownGrade2BuildInIP,
        DownGrade2HttpDnsIP,
        DownGrade2HttpDnsBackupIP,
        NoDownGrade
    }

    /* loaded from: classes.dex */
    public interface Method {
        public static final int DELETE = 3;
        public static final int DEPRECATED_GET_OR_POST = -1;
        public static final int GET = 0;
        public static final int HEAD = 4;
        public static final int OPTIONS = 5;
        public static final int PATCH = 7;
        public static final int POST = 1;
        public static final int PUT = 2;
        public static final int TRACE = 6;
    }

    @Deprecated
    public Request(String str, Response.ErrorListener errorListener) {
        this(-1, str, errorListener);
    }

    private String convertCacheModel(int i2) {
        return i2 != 0 ? i2 != 1 ? i2 != 2 ? i2 != 3 ? i2 != 4 ? "" : "READ_ASSETS" : "NET_ONLY" : "CACHE_ONLY" : "BOTH" : "AUTO";
    }

    private String convertMethod(int i2) {
        switch (i2) {
            case -1:
                return "DEPRECATED_GET_OR_POST";
            case 0:
                return "GET";
            case 1:
                return "POST";
            case 2:
                return UrlHelper.METHOD_PUT;
            case 3:
                return UrlHelper.METHOD_DELETE;
            case 4:
                return UrlHelper.METHOD_HEAD;
            case 5:
                return UrlHelper.METHOD_OPTIONS;
            case 6:
            case 7:
                return UrlHelper.METHOD_TRACE;
            default:
                return "";
        }
    }

    private byte[] encodeParameters(Map<String, String> map, String str, boolean z) {
        StringBuilder sb = new StringBuilder();
        if (z) {
            try {
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    sb.append(URLEncoder.encode(entry.getKey(), str));
                    sb.append('=');
                    sb.append(URLEncoder.encode(entry.getValue(), str));
                    sb.append(Typography.amp);
                }
                return sb.toString().getBytes(str);
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
        return sb.toString().getBytes();
    }

    private static int findDefaultTrafficStatsTag(String str) {
        Uri parse;
        String host;
        if (TextUtils.isEmpty(str) || (parse = Uri.parse(str)) == null || (host = parse.getHost()) == null) {
            return 0;
        }
        return host.hashCode();
    }

    public void addHeader(String str, String str2) {
        if (this.mHeaders == Collections.EMPTY_MAP) {
            this.mHeaders = new HashMap();
        }
        this.mHeaders.put(str, str2);
    }

    public void addMarker(String str) {
        if (VolleyLog.MarkerLog.ENABLED) {
            this.mEventLog.add(str, Thread.currentThread().getId(), getLogSequence());
        } else if (this.mRequestBirthTime == 0) {
            this.mRequestBirthTime = SystemClock.elapsedRealtime();
        }
    }

    public void cancel() {
        this.mCanceled = true;
    }

    @Override // java.lang.Comparable
    public /* bridge */ /* synthetic */ int compareTo(Object obj) {
        return compareTo((Request) ((Request) obj));
    }

    public synchronized void deliverCancel() {
        Response.ErrorListener errorListener = this.mErrorListener;
        if (errorListener != null) {
            errorListener.onCancel();
        }
    }

    public synchronized void deliverError(VolleyError volleyError) {
        Response.ErrorListener errorListener = this.mErrorListener;
        if (errorListener != null) {
            errorListener.onErrorResponse(volleyError);
        }
    }

    public abstract void deliverResponse(Response<T> response);

    public synchronized void deliverStart() {
        Response.ErrorListener errorListener = this.mErrorListener;
        if (errorListener != null) {
            errorListener.onStart();
        }
    }

    public void finish(final String str) {
        RequestQueue requestQueue = this.mRequestQueue;
        if (requestQueue != null) {
            requestQueue.finish(this);
            onFinish();
        }
        if (VolleyLog.MarkerLog.ENABLED) {
            final long id = Thread.currentThread().getId();
            if (Looper.myLooper() != Looper.getMainLooper()) {
                new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.android.volley.Request.1
                    {
                        Request.this = this;
                    }

                    @Override // java.lang.Runnable
                    public void run() {
                        Request.this.mEventLog.add(str, id, Request.this.getLogSequence());
                        Request.this.mEventLog.finish(toString());
                    }
                });
                return;
            }
            this.mEventLog.add(str, id, getLogSequence());
            this.mEventLog.finish(toString());
            return;
        }
        long elapsedRealtime = SystemClock.elapsedRealtime() - this.mRequestBirthTime;
        if (elapsedRealtime >= SLOW_REQUEST_THRESHOLD_MS) {
            VolleyLog.d("%d ms: %s", Long.valueOf(elapsedRealtime), toString());
        }
    }

    public byte[] getBody() throws AuthFailureError {
        Map<String, String> params = getParams();
        if (params == null || params.size() <= 0) {
            return null;
        }
        return encodeParameters(params, getParamsEncoding(), true);
    }

    public String getBodyContentType() {
        return "application/x-www-form-urlencoded; charset=" + getParamsEncoding();
    }

    public Cache.Entry getCacheEntry() {
        return this.mCacheEntry;
    }

    public String getCacheKey() {
        if (!TextUtils.isEmpty(this.mCacheKey)) {
            return this.mCacheKey;
        }
        return getUrl();
    }

    public int getCacheModel() {
        return this.mCacheModel;
    }

    public long getCacheTime() {
        return this.mCacheTime;
    }

    public int getCallTimeoutMs() {
        return this.mCallTimeoutMs;
    }

    public int getConnectTimeoutMs() {
        int i2 = this.mConnectTimeoutMs;
        if (i2 <= 0) {
            return 10000;
        }
        return i2;
    }

    public DownGradeType getDownGradeType() {
        return this.downGradeType;
    }

    public Response.ErrorListener getErrorListener() {
        return this.mErrorListener;
    }

    public Map<String, String> getHeaders() throws AuthFailureError {
        return this.mHeaders;
    }

    public final int getLogSequence() {
        Integer num = this.mSequence;
        if (num == null) {
            return -1;
        }
        return num.intValue();
    }

    public int getMaxRetryNum() {
        return this.maxRetryNum;
    }

    public int getMethod() {
        return this.mMethod;
    }

    public Map<String, String> getParams() throws AuthFailureError {
        Map<String, String> map = this.mParams;
        if (map != null) {
            return map;
        }
        return null;
    }

    protected String getParamsEncoding() {
        return "UTF-8";
    }

    @Deprecated
    public byte[] getPostBody() throws AuthFailureError {
        Map<String, String> postParams = getPostParams();
        if (postParams == null || postParams.size() <= 0) {
            return null;
        }
        return encodeParameters(postParams, getPostParamsEncoding(), true);
    }

    @Deprecated
    public String getPostBodyContentType() {
        return getBodyContentType();
    }

    @Deprecated
    protected Map<String, String> getPostParams() throws AuthFailureError {
        return getParams();
    }

    @Deprecated
    protected String getPostParamsEncoding() {
        return getParamsEncoding();
    }

    public JDRequest.Priority getPriority() {
        return this.mPriority;
    }

    public int getReadTimeoutMs() {
        int i2 = this.mReadTimeoutMs;
        if (i2 <= 0) {
            return 10000;
        }
        return i2;
    }

    public RetryPolicy getRetryPolicy() {
        return this.mRetryPolicy;
    }

    public final int getSequence() {
        Integer num = this.mSequence;
        if (num != null) {
            return num.intValue();
        }
        throw new IllegalStateException("getSequence called before setSequence");
    }

    public String getServiceKey() {
        return this.mServiceKey;
    }

    public Object getTag() {
        return this.mTag;
    }

    public final int getTimeoutMs() {
        return this.mRetryPolicy.getCurrentTimeout();
    }

    public int getTrafficStatsTag() {
        return this.mDefaultTrafficStatsTag;
    }

    public String getUrl() {
        return this.mUrl;
    }

    public boolean hasHadResponseDelivered() {
        return this.mResponseDelivered;
    }

    public boolean isCallbackInMainThread() {
        return this.isCallbackInMainThread;
    }

    public boolean isCanceled() {
        return this.mCanceled;
    }

    public boolean isFinalDowngrade() {
        return this.isFinalDowngrade;
    }

    public boolean isForce2HttpFlag() {
        return this.isForce2HttpFlag;
    }

    public boolean isHttpsDown2HttpRequest() {
        return this.isHttpsDown2HttpRequest;
    }

    public boolean isUseCookies() {
        return this.isUseCookies;
    }

    public boolean isUseDialingIP() {
        return this.isUseDialingIP;
    }

    public boolean isUseDomainName() {
        return this.isUseDomainName;
    }

    public boolean isUseOkhttp() {
        return this.isUseOkhttpFlag;
    }

    public void markDelivered() {
        this.mResponseDelivered = true;
    }

    public boolean needRetryOnNetworkLayer() {
        return this.needRetryOnNetworkLayer;
    }

    public synchronized void onFinish() {
        this.mErrorListener = null;
    }

    public VolleyError parseNetworkError(VolleyError volleyError) {
        return volleyError;
    }

    public abstract Response<T> parseNetworkResponse(NetworkResponse networkResponse);

    /* JADX WARN: Multi-variable type inference failed */
    public Request<?> setCacheEntry(Cache.Entry entry) {
        this.mCacheEntry = entry;
        return this;
    }

    public void setCacheKey(String str) {
        this.mCacheKey = str;
    }

    public void setCacheModel(int i2) {
        this.mCacheModel = i2;
    }

    public void setCacheResponseChecker(CacheResponseChecker cacheResponseChecker) {
        this.cacheResponseChecker = cacheResponseChecker;
    }

    public void setCacheTime(long j2) {
        this.mCacheTime = j2;
    }

    public void setCallTimeoutMs(int i2) {
        this.mCallTimeoutMs = i2;
    }

    public void setCallbackInMainThread(boolean z) {
        this.isCallbackInMainThread = z;
    }

    public void setConnectTimeoutMs(int i2) {
        this.mConnectTimeoutMs = i2;
    }

    public void setDownGradeType(DownGradeType downGradeType) {
        this.downGradeType = downGradeType;
    }

    public void setForce2HttpFlag(boolean z) {
        this.isForce2HttpFlag = z;
    }

    public void setHeaders(Map<String, String> map) {
        this.mHeaders = map;
    }

    public void setIsFinalDowngradeFlag(boolean z) {
        this.isFinalDowngrade = z;
    }

    public void setIsHttpsDown2HttpRequest(boolean z) {
        this.isHttpsDown2HttpRequest = z;
    }

    public void setMaxRetryNum(int i2) {
        this.maxRetryNum = i2;
    }

    public void setNeedRetryOnNetworkLayer(boolean z) {
        this.needRetryOnNetworkLayer = z;
    }

    public void setParams(Map<String, String> map) {
        this.mParams = map;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public Request<?> setPriority(JDRequest.Priority priority) {
        this.mPriority = priority;
        return this;
    }

    public void setReadTimeoutMs(int i2) {
        this.mReadTimeoutMs = i2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public Request<?> setRequestQueue(RequestQueue requestQueue) {
        this.mRequestQueue = requestQueue;
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public Request<?> setRetryPolicy(RetryPolicy retryPolicy) {
        this.mRetryPolicy = retryPolicy;
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final Request<?> setSequence(int i2) {
        this.mSequence = Integer.valueOf(i2);
        return this;
    }

    public void setServiceKey(String str) {
        this.mServiceKey = str;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final Request<?> setShouldCache(boolean z) {
        this.mShouldCache = z;
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final Request<?> setShouldRetryServerErrors(boolean z) {
        this.mShouldRetryServerErrors = z;
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public Request<?> setTag(Object obj) {
        this.mTag = obj;
        return this;
    }

    public void setUrl(String str) {
        this.mUrl = str;
    }

    public void setUseCookies(boolean z) {
        this.isUseCookies = z;
    }

    public void setUseDialingIP(boolean z) {
        this.isUseDialingIP = z;
    }

    public void setUseDomainName(boolean z) {
        this.isUseDomainName = z;
    }

    public void setUseOkhttpFlag(boolean z) {
        this.isUseOkhttpFlag = z;
    }

    public final boolean shouldCache() {
        return this.mShouldCache;
    }

    public final boolean shouldRetryServerErrors() {
        return this.mShouldRetryServerErrors;
    }

    public String toString() {
        String str = "0x" + Integer.toHexString(getTrafficStatsTag());
        byte[] bArr = null;
        try {
            Map<String, String> params = getParams();
            if (params != null && params.size() > 0) {
                bArr = encodeParameters(params, getParamsEncoding(), false);
            }
        } catch (Exception unused) {
        }
        StringBuilder sb = new StringBuilder();
        sb.append(this.mCanceled ? "[X] " : "[ ] ");
        sb.append("[ ");
        sb.append(this.mTag);
        sb.append(" ] [ ");
        sb.append(convertMethod(this.mMethod));
        sb.append(" ] [ ");
        sb.append(convertCacheModel(this.mCacheModel));
        sb.append(" ] [ ");
        sb.append(getUrl());
        sb.append(" ] [ ");
        sb.append(bArr != null ? new String(bArr) : "");
        sb.append(" ] ");
        sb.append(str);
        sb.append(LangUtils.SINGLE_SPACE);
        sb.append(getPriority());
        sb.append(LangUtils.SINGLE_SPACE);
        sb.append(this.mSequence);
        return sb.toString();
    }

    public Request(int i2, String str, Response.ErrorListener errorListener) {
        this.mEventLog = VolleyLog.MarkerLog.ENABLED ? new VolleyLog.MarkerLog() : null;
        this.mShouldCache = true;
        this.mCanceled = false;
        this.mResponseDelivered = false;
        this.mRequestBirthTime = 0L;
        this.mShouldRetryServerErrors = false;
        this.needRetryOnNetworkLayer = true;
        this.mCacheEntry = null;
        this.mPriority = JDRequest.Priority.NORMAL;
        this.mHeaders = Collections.emptyMap();
        this.isUseCookies = true;
        this.mCacheTime = -1L;
        this.isUseOkhttpFlag = true;
        this.isForce2HttpFlag = false;
        this.isUseDialingIP = false;
        this.downGradeType = DownGradeType.NoDownGrade;
        this.isFinalDowngrade = false;
        this.isHttpsDown2HttpRequest = false;
        this.mMethod = i2;
        this.mUrl = str;
        this.mErrorListener = errorListener;
        setRetryPolicy(new DefaultRetryPolicy());
        this.mDefaultTrafficStatsTag = findDefaultTrafficStatsTag(str);
    }

    public int compareTo(Request<T> request) {
        JDRequest.Priority priority = getPriority();
        JDRequest.Priority priority2 = request.getPriority();
        return priority == priority2 ? this.mSequence.intValue() - request.mSequence.intValue() : priority2.ordinal() - priority.ordinal();
    }

    public final boolean shouldCache(Object obj) {
        CacheResponseChecker cacheResponseChecker;
        if (obj != null && (cacheResponseChecker = this.cacheResponseChecker) != null) {
            return cacheResponseChecker.shouldCache(obj);
        }
        return this.mShouldCache;
    }
}
