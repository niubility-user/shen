package com.jd.framework.network.impl;

import android.content.Context;
import com.android.volley.Cache;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.jd.framework.advertise.OkHttpNetworkFetcher;
import com.jd.framework.network.JDCacheChecker;
import com.jd.framework.network.JDRequestQueue;
import com.jd.framework.network.filedown.JDFileDownloader;
import com.jd.framework.network.request.JDAdRequest;
import com.jd.framework.network.request.JDFileRequest;
import com.jd.framework.network.request.JDRequest;
import com.jd.framework.network.toolbox.JDNetworkConvertor;
import com.jd.framework.network.toolbox.JDVolley;

/* loaded from: classes13.dex */
public class JDRequestQueueDefault implements JDRequestQueue {
    private Context context;
    private final OkHttpNetworkFetcher mOkHttpNetworkFetcher = new OkHttpNetworkFetcher();
    private final RequestQueue mRequestQueue;

    public JDRequestQueueDefault(Context context) {
        this.context = context;
        this.mRequestQueue = JDVolley.newRequestQueue(context);
    }

    @Override // com.jd.framework.network.JDRequestQueue
    public <T> JDRequest<T> add(JDRequest<T> jDRequest) {
        if (jDRequest instanceof JDFileRequest) {
            JDFileDownloader.getInstance(this.context).add((JDFileRequest) jDRequest);
        } else if (jDRequest instanceof JDAdRequest) {
            this.mOkHttpNetworkFetcher.fetch(jDRequest);
        } else {
            this.mRequestQueue.add(JDNetworkConvertor.toRequest(jDRequest));
        }
        return jDRequest;
    }

    @Override // com.jd.framework.network.JDRequestQueue
    public void cancelAll() {
        this.mRequestQueue.cancelAll(new RequestQueue.RequestFilter() { // from class: com.jd.framework.network.impl.JDRequestQueueDefault.2
            @Override // com.android.volley.RequestQueue.RequestFilter
            public boolean apply(Request<?> request) {
                return true;
            }
        });
    }

    @Override // com.jd.framework.network.JDRequestQueue
    public void cancelByTag(final String str) {
        if (str != null) {
            this.mRequestQueue.cancelAll(new RequestQueue.RequestFilter() { // from class: com.jd.framework.network.impl.JDRequestQueueDefault.1
                @Override // com.android.volley.RequestQueue.RequestFilter
                public boolean apply(Request<?> request) {
                    return str.equals(request.getTag());
                }
            });
            return;
        }
        throw new IllegalArgumentException("Cannot cancelAll with a null tag");
    }

    @Override // com.jd.framework.network.JDRequestQueue
    public Cache getCache() {
        RequestQueue requestQueue = this.mRequestQueue;
        if (requestQueue == null) {
            return null;
        }
        return requestQueue.getCache();
    }

    @Override // com.jd.framework.network.JDRequestQueue
    public boolean isCacheExpired(String str) {
        Cache.Entry entry;
        Cache cache = getCache();
        if (cache == null || (entry = cache.get(str)) == null) {
            return false;
        }
        return entry.isExpired();
    }

    @Override // com.jd.framework.network.JDRequestQueue
    public void setCacheChecker(JDCacheChecker jDCacheChecker) {
        this.mRequestQueue.setCacheChecker(jDCacheChecker);
    }

    @Override // com.jd.framework.network.JDRequestQueue
    public void stop() {
        this.mRequestQueue.stop();
    }
}
