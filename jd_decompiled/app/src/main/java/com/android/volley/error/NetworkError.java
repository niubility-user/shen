package com.android.volley.error;

import com.android.volley.NetworkResponse;

/* loaded from: classes.dex */
public class NetworkError extends VolleyError {
    public NetworkError() {
    }

    public NetworkError(Throwable th, NetworkResponse networkResponse, int i2, String str, boolean z) {
        super(th, networkResponse, i2, str, z);
    }
}
