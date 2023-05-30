package com.jd.framework.network;

import com.android.volley.Cache;
import com.jd.framework.network.request.JDRequest;

/* loaded from: classes13.dex */
public interface JDRequestQueue {
    <T> JDRequest<T> add(JDRequest<T> jDRequest);

    void cancelAll();

    void cancelByTag(String str);

    Cache getCache();

    boolean isCacheExpired(String str);

    void setCacheChecker(JDCacheChecker jDCacheChecker);

    void stop();
}
