package com.jingdong.common.network.quicpro;

import android.text.TextUtils;
import android.util.Pair;
import com.jingdong.common.network.quicpro.UrlRequest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.Executor;

/* loaded from: classes5.dex */
public class UrlRequestBuilderImpl extends UrlRequest.Builder {
    private static final String ACCEPT_ENCODING = "Accept-Encoding";
    private static final String TAG = "UrlRequestBuilderImpl";
    private final UrlRequest.Callback mCallback;
    private int mConnectTimeout;
    private boolean mDisableCache;
    private boolean mDisableConnectionMigration;
    private final Executor mExecutor;
    private String mHost;
    private String mLogLevel;
    private String mMethod;
    private Map<String, String> mParams;
    private String mPath;
    private int mReadTimeout;
    private Collection<Object> mRequestAnnotations;
    private final ArrayList<Pair<String, String>> mRequestHeaders = new ArrayList<>();
    private String mServicePort;
    private UploadDataProvider mUploadDataProvider;
    private Executor mUploadDataProviderExecutor;
    private final String mUrl;

    public UrlRequestBuilderImpl(String str, UrlRequest.Callback callback, Executor executor) {
        if (str == null) {
            throw new NullPointerException("URL is required.");
        }
        if (callback != null) {
            this.mUrl = str;
            this.mCallback = callback;
            this.mExecutor = executor;
            return;
        }
        throw new NullPointerException("Callback is required.");
    }

    @Override // com.jingdong.common.network.quicpro.UrlRequest.Builder
    public UrlRequest.Builder addHeader(String str, String str2) {
        if (str != null) {
            if (str2 != null) {
                this.mRequestHeaders.add(Pair.create(str, str2));
                return this;
            }
            throw new NullPointerException("Invalid header value.");
        }
        throw new NullPointerException("Invalid header name.");
    }

    @Override // com.jingdong.common.network.quicpro.UrlRequest.Builder
    public UrlRequest.Builder allowDirectExecutor() {
        return this;
    }

    @Override // com.jingdong.common.network.quicpro.UrlRequest.Builder
    public UrlRequest build() {
        if (!TextUtils.isEmpty(this.mServicePort)) {
            QuicProRequest quicProRequest = new QuicProRequest(this.mUrl, this.mServicePort, this.mCallback, this.mExecutor);
            quicProRequest.setParam(this.mParams);
            String str = this.mMethod;
            if (str != null) {
                quicProRequest.setHttpMethod(str);
            }
            if (!TextUtils.isEmpty(this.mLogLevel)) {
                quicProRequest.setLogLevel(this.mLogLevel);
            }
            Iterator<Pair<String, String>> it = this.mRequestHeaders.iterator();
            while (it.hasNext()) {
                Pair<String, String> next = it.next();
                quicProRequest.addHeader((String) next.first, (String) next.second);
            }
            UploadDataProvider uploadDataProvider = this.mUploadDataProvider;
            if (uploadDataProvider != null) {
                quicProRequest.setUploadDataProvider(uploadDataProvider, this.mUploadDataProviderExecutor);
            }
            int i2 = this.mReadTimeout;
            if (i2 > 0) {
                quicProRequest.setReadTimeout(i2);
            }
            int i3 = this.mConnectTimeout;
            if (i3 > 0) {
                quicProRequest.setConnectTimeout(i3);
            }
            return quicProRequest;
        }
        throw new IllegalArgumentException("servicePort \u90fd\u4e0d\u80fd\u4e3a\u7a7a");
    }

    @Override // com.jingdong.common.network.quicpro.UrlRequest.Builder
    public UrlRequest.Builder disableCache() {
        this.mDisableCache = true;
        return this;
    }

    @Override // com.jingdong.common.network.quicpro.UrlRequest.Builder
    public UrlRequest.Builder setConnectTimeout(int i2) {
        this.mConnectTimeout = i2;
        return this;
    }

    @Override // com.jingdong.common.network.quicpro.UrlRequest.Builder
    public UrlRequest.Builder setHost(String str) {
        this.mHost = str;
        return this;
    }

    @Override // com.jingdong.common.network.quicpro.UrlRequest.Builder
    public UrlRequest.Builder setHttpMethod(String str) {
        if (str != null) {
            this.mMethod = str;
            return this;
        }
        throw new NullPointerException("Method is required.");
    }

    @Override // com.jingdong.common.network.quicpro.UrlRequest.Builder
    public UrlRequest.Builder setLogLevel(String str) {
        this.mLogLevel = str;
        return this;
    }

    @Override // com.jingdong.common.network.quicpro.UrlRequest.Builder
    public UrlRequest.Builder setParam(Map<String, String> map) {
        this.mParams = map;
        return this;
    }

    @Override // com.jingdong.common.network.quicpro.UrlRequest.Builder
    public UrlRequest.Builder setPath(String str) {
        this.mPath = str;
        return this;
    }

    @Override // com.jingdong.common.network.quicpro.UrlRequest.Builder
    public UrlRequest.Builder setReadTimeout(int i2) {
        this.mReadTimeout = i2;
        return this;
    }

    @Override // com.jingdong.common.network.quicpro.UrlRequest.Builder
    public UrlRequest.Builder setServicePort(String str) {
        this.mServicePort = str;
        return this;
    }

    @Override // com.jingdong.common.network.quicpro.UrlRequest.Builder
    public UrlRequest.Builder setUploadDataProvider(UploadDataProvider uploadDataProvider, Executor executor) {
        if (uploadDataProvider != null) {
            if (executor != null) {
                if (this.mMethod == null) {
                    this.mMethod = "POST";
                }
                this.mUploadDataProvider = uploadDataProvider;
                this.mUploadDataProviderExecutor = executor;
                return this;
            }
            throw new NullPointerException("Invalid UploadDataProvider Executor.");
        }
        throw new NullPointerException("Invalid UploadDataProvider.");
    }
}
