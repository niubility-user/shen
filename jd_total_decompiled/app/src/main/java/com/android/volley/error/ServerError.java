package com.android.volley.error;

import com.android.volley.NetworkResponse;

/* loaded from: classes.dex */
public class ServerError extends VolleyError {
    public ServerError(NetworkResponse networkResponse, String str, boolean z) {
        super(networkResponse, str, z);
    }

    public ServerError() {
    }
}
