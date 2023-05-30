package com.android.volley.error;

import com.android.volley.NetworkResponse;

/* loaded from: classes.dex */
public class JsonExceptionError extends VolleyError {
    private boolean isParseError;

    public JsonExceptionError(String str, Throwable th, boolean z) {
        this(str, th, null, -1, z, false);
    }

    public boolean isParseError() {
        return this.isParseError;
    }

    public JsonExceptionError(String str, Throwable th, NetworkResponse networkResponse, int i2, boolean z, boolean z2) {
        super(th, networkResponse, i2, str, z);
        this.isParseError = z2;
    }
}
