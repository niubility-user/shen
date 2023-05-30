package com.android.volley;

import com.android.volley.error.VolleyError;

/* loaded from: classes.dex */
public interface RetryPolicy {
    int getCurrentRetryCount();

    int getCurrentTimeout();

    void retry(VolleyError volleyError) throws VolleyError;
}
