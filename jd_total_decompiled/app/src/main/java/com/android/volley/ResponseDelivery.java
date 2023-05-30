package com.android.volley;

import com.android.volley.error.VolleyError;

/* loaded from: classes.dex */
public interface ResponseDelivery {
    void postCancel(Request<?> request);

    void postError(Request<?> request, VolleyError volleyError);

    void postOtherError(Request<?> request, VolleyError volleyError);

    void postResponse(Request<?> request, Response<?> response);

    void postResponse(Request<?> request, Response<?> response, Runnable runnable);

    void postStart(Request<?> request);
}
