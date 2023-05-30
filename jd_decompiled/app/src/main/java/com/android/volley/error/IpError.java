package com.android.volley.error;

import com.android.volley.NetworkResponse;

/* loaded from: classes.dex */
public class IpError extends VolleyError {
    public IpError(String str, Throwable th, NetworkResponse networkResponse, int i2, boolean z) {
        super(th, networkResponse, i2, str, z);
    }
}
