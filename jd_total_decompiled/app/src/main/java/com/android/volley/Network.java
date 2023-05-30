package com.android.volley;

import com.android.volley.error.VolleyError;
import com.jd.framework.network.JDCacheChecker;

/* loaded from: classes.dex */
public interface Network {
    JDCacheChecker getCacheChecker();

    NetworkResponse performRequest(Request<?> request, ResponseDelivery responseDelivery) throws VolleyError;

    void setCacheChecker(JDCacheChecker jDCacheChecker);
}
