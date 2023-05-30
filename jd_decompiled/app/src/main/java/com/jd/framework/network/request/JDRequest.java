package com.jd.framework.network.request;

import android.text.TextUtils;
import com.jd.framework.network.JDResponseListener;
import com.jingdong.jdsdk.network.toolbox.CacheResponseChecker;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes13.dex */
public abstract class JDRequest<T> {
    private static final String DEFAULT_PARAMS_ENCODING = "UTF-8";
    private static AtomicInteger mTagGenerator = new AtomicInteger();
    private CacheResponseChecker cacheResponseChecker;
    private Map<String, String> header;
    private boolean isCallbackInMainThread;
    private boolean isForce2HttpFlag;
    private boolean isUseCookies;
    private boolean isUseDomainName;
    private boolean isUseOkhttpFlag;
    private String mCacheKey;
    private int mCacheModel;
    private long mCacheTime;
    private int mCallTimeoutMs;
    private int mConnectTimeoutMs;
    private String mHttpDnsParam;
    private int mMaxNumRetries;
    private final int mMethod;
    protected Map<String, String> mParams;
    protected String mPostBody;
    protected Priority mPriority;
    private int mReadTimeoutMs;
    protected JDResponseListener<T> mResponseListener;
    protected int mSequence;
    private String mServiceKey;
    private String mTag;
    private String mUrl;
    private boolean needRetryOnNetworkLayer;

    /* loaded from: classes13.dex */
    public interface CacheModel {
        public static final int AUTO = 0;
        public static final int BOTH = 1;
        public static final int CACHE_ONLY = 2;
        public static final int NET_ONLY = 3;
        public static final int READ_ASSETS = 4;
    }

    /* loaded from: classes13.dex */
    public interface Method {
        public static final int DELETE = 3;
        public static final int GET = 0;
        public static final int POST = 1;
        public static final int PUT = 2;
    }

    /* loaded from: classes13.dex */
    public enum Priority {
        LOW,
        NORMAL,
        HIGH,
        IMMEDIATE
    }

    public JDRequest(String str) {
        this(0, str);
    }

    public String getCacheKey() {
        return this.mCacheKey;
    }

    public int getCacheModel() {
        return this.mCacheModel;
    }

    public CacheResponseChecker getCacheResponseChecker() {
        return this.cacheResponseChecker;
    }

    public long getCacheTime() {
        return this.mCacheTime;
    }

    public int getCallTimeoutMs() {
        return this.mCallTimeoutMs;
    }

    public int getConnectTimeoutMs() {
        return this.mConnectTimeoutMs;
    }

    public Map<String, String> getHeader() {
        return this.header;
    }

    public String getHost() {
        if (TextUtils.isEmpty(this.mUrl)) {
            return "";
        }
        try {
            return new URL(this.mUrl).getHost();
        } catch (MalformedURLException e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public int getMaxNumRetries() {
        return this.mMaxNumRetries;
    }

    public int getMethod() {
        return this.mMethod;
    }

    public Map<String, String> getParams() {
        return this.mParams;
    }

    public String getPostBody() {
        return this.mPostBody;
    }

    public Priority getPriority() {
        return this.mPriority;
    }

    public int getReadTimeoutMs() {
        return this.mReadTimeoutMs;
    }

    public JDResponseListener<T> getResponseListener() {
        return this.mResponseListener;
    }

    public int getSequence() {
        return this.mSequence;
    }

    public String getServiceKey() {
        return this.mServiceKey;
    }

    public String getTag() {
        return this.mTag;
    }

    public String getUrl() {
        return this.mUrl;
    }

    public boolean getUseOkhttpFlag() {
        return this.isUseOkhttpFlag;
    }

    public boolean isCallbackInMainThread() {
        return this.isCallbackInMainThread;
    }

    public boolean isForce2HttpFlag() {
        return this.isForce2HttpFlag;
    }

    public boolean isUseCookies() {
        return this.isUseCookies;
    }

    public boolean isUseDomainName() {
        return this.isUseDomainName;
    }

    public boolean needRetryOnNetworkLayer() {
        return this.needRetryOnNetworkLayer;
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

    public void setForce2HttpFlag(boolean z) {
        this.isForce2HttpFlag = z;
    }

    public void setHeader(Map<String, String> map) {
        this.header = map;
    }

    public void setMaxNumRetries(int i2) {
        this.mMaxNumRetries = i2;
    }

    public void setNeedRetryOnNetworkLayer(boolean z) {
        this.needRetryOnNetworkLayer = z;
    }

    public void setParams(Map<String, String> map) {
        this.mParams = map;
    }

    public void setPriority(Priority priority) {
        this.mPriority = priority;
    }

    public void setReadTimeoutMs(int i2) {
        this.mReadTimeoutMs = i2;
    }

    public void setResponseListener(JDResponseListener<T> jDResponseListener) {
        this.mResponseListener = jDResponseListener;
    }

    public void setSequence(int i2) {
        this.mSequence = i2;
    }

    public void setServiceKey(String str) {
        this.mServiceKey = str;
    }

    public void setTag(String str) {
        this.mTag = str;
    }

    public void setUrl(String str) {
        this.mUrl = str;
    }

    public void setUseCookies(boolean z) {
        this.isUseCookies = z;
    }

    public void setUseDomainName(boolean z) {
        this.isUseDomainName = z;
    }

    public void setUseOkhttpFlag(boolean z) {
        this.isUseOkhttpFlag = z;
    }

    public JDRequest(int i2, String str) {
        this.mTag = "tag-" + mTagGenerator.incrementAndGet();
        this.mSequence = -1;
        this.mCacheModel = 0;
        this.mCacheTime = 0L;
        this.mPriority = Priority.NORMAL;
        this.isUseCookies = true;
        this.isUseOkhttpFlag = false;
        this.isForce2HttpFlag = false;
        this.needRetryOnNetworkLayer = true;
        this.mMethod = i2;
        this.mUrl = str;
    }

    public JDRequest(int i2, String str, Map<String, String> map) {
        this.mTag = "tag-" + mTagGenerator.incrementAndGet();
        this.mSequence = -1;
        this.mCacheModel = 0;
        this.mCacheTime = 0L;
        this.mPriority = Priority.NORMAL;
        this.isUseCookies = true;
        this.isUseOkhttpFlag = false;
        this.isForce2HttpFlag = false;
        this.needRetryOnNetworkLayer = true;
        this.mMethod = i2;
        this.mUrl = str;
        this.mParams = map;
    }

    public JDRequest(int i2, String str, String str2) {
        this.mTag = "tag-" + mTagGenerator.incrementAndGet();
        this.mSequence = -1;
        this.mCacheModel = 0;
        this.mCacheTime = 0L;
        this.mPriority = Priority.NORMAL;
        this.isUseCookies = true;
        this.isUseOkhttpFlag = false;
        this.isForce2HttpFlag = false;
        this.needRetryOnNetworkLayer = true;
        this.mMethod = i2;
        this.mUrl = str;
        this.mPostBody = str2;
    }
}
