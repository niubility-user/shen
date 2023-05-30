package com.android.volley.error;

import com.android.volley.NetworkResponse;

/* loaded from: classes.dex */
public class HttpsError extends VolleyError {

    /* loaded from: classes.dex */
    public static class HttpsDomainError extends HttpsError {
        public HttpsDomainError(Throwable th, NetworkResponse networkResponse, int i2, String str) {
            this(th, networkResponse, i2, str, false);
        }

        public HttpsDomainError(Throwable th, NetworkResponse networkResponse, int i2, String str, boolean z) {
            super(th, networkResponse, i2, str, z);
        }
    }

    /* loaded from: classes.dex */
    public static class HttpsIPError extends HttpsError {
        public HttpsIPError(Throwable th, NetworkResponse networkResponse, int i2, String str) {
            super(th, networkResponse, i2, str, false);
        }
    }

    public HttpsError(Throwable th, NetworkResponse networkResponse, int i2, String str, boolean z) {
        super(th, networkResponse, i2, str, z);
    }
}
