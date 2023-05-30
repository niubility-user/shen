package com.android.volley.error;

import android.content.Intent;
import com.android.volley.NetworkResponse;

/* loaded from: classes.dex */
public class AuthFailureError extends VolleyError {
    private Intent mResolutionIntent;

    public AuthFailureError() {
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        return this.mResolutionIntent != null ? "User needs to (re)enter credentials." : super.getMessage();
    }

    public Intent getResolutionIntent() {
        return this.mResolutionIntent;
    }

    public AuthFailureError(Intent intent) {
        this.mResolutionIntent = intent;
    }

    public AuthFailureError(NetworkResponse networkResponse, boolean z, String str) {
        super(networkResponse, str, z);
    }

    public AuthFailureError(String str) {
        super(str, (String) null);
    }

    public AuthFailureError(String str, Exception exc) {
        super(str, exc, (String) null);
    }
}
