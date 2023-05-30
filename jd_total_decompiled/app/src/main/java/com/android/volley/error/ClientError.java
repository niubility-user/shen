package com.android.volley.error;

import com.android.volley.NetworkResponse;

/* loaded from: classes.dex */
public class ClientError extends ServerError {
    public ClientError(NetworkResponse networkResponse, boolean z, String str) {
        super(networkResponse, str, z);
    }

    public ClientError() {
    }
}
