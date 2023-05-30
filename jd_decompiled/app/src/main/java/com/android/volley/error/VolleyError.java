package com.android.volley.error;

import com.android.volley.NetworkResponse;

/* loaded from: classes.dex */
public class VolleyError extends Exception {
    private boolean isDowngradeError;
    public final NetworkResponse networkResponse;
    private long networkTimeMs;
    private int statusCode;
    private String url;

    public VolleyError() {
        this.statusCode = -1;
        this.networkResponse = null;
    }

    public long getNetworkTimeMs() {
        return this.networkTimeMs;
    }

    public int getStatusCode() {
        return this.statusCode;
    }

    public String getUrl() {
        return this.url;
    }

    public boolean isDowngradeError() {
        return this.isDowngradeError;
    }

    void setNetworkTimeMs(long j2) {
        this.networkTimeMs = j2;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public VolleyError(NetworkResponse networkResponse, int i2, String str, boolean z) {
        this.statusCode = -1;
        this.networkResponse = networkResponse;
        this.url = str;
        this.isDowngradeError = z;
        this.statusCode = i2;
    }

    public VolleyError(NetworkResponse networkResponse, String str, boolean z) {
        this(networkResponse, -1, str, z);
    }

    public VolleyError(NetworkResponse networkResponse) {
        this(networkResponse, -1);
    }

    public VolleyError(NetworkResponse networkResponse, int i2) {
        this.statusCode = -1;
        this.networkResponse = networkResponse;
        this.url = null;
        this.isDowngradeError = false;
        this.statusCode = i2;
    }

    public VolleyError(String str, String str2) {
        super(str);
        this.statusCode = -1;
        this.networkResponse = null;
        this.url = str2;
        this.isDowngradeError = false;
    }

    public VolleyError(String str) {
        super(str);
        this.statusCode = -1;
        this.networkResponse = null;
        this.url = null;
        this.isDowngradeError = false;
    }

    public VolleyError(String str, Throwable th, String str2) {
        this(str, th, str2, false);
    }

    public VolleyError(String str, Throwable th, String str2, boolean z) {
        super(str, th);
        this.statusCode = -1;
        this.networkResponse = null;
        this.url = str2;
        this.isDowngradeError = z;
    }

    public VolleyError(Throwable th, NetworkResponse networkResponse, int i2, String str, boolean z) {
        super(th);
        this.statusCode = -1;
        this.networkResponse = networkResponse;
        this.statusCode = i2;
        this.isDowngradeError = z;
        this.url = str;
    }

    public VolleyError(Throwable th) {
        this(th, null, -1, null, false);
    }
}
