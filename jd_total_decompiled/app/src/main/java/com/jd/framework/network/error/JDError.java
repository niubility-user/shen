package com.jd.framework.network.error;

import com.android.volley.NetworkResponse;
import com.android.volley.error.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.jd.framework.network.JDNetworkResponse;

/* loaded from: classes.dex */
public class JDError extends Exception {
    protected boolean isDowngradeError;
    public JDNetworkResponse networkResponse;
    private int statusCode;
    private String url;

    public JDError() {
        this.isDowngradeError = false;
        this.statusCode = 0;
        this.networkResponse = null;
    }

    public JDNetworkResponse convert2JDNetworkResponse(VolleyError volleyError) {
        NetworkResponse networkResponse;
        String str;
        if (volleyError == null || (networkResponse = volleyError.networkResponse) == null) {
            return null;
        }
        String str2 = "";
        try {
            if (networkResponse.data != null) {
                if (networkResponse.headers != null) {
                    str = new String(networkResponse.data, HttpHeaderParser.parseCharset(networkResponse.headers, "utf-8"));
                } else {
                    str = new String(networkResponse.data, "utf-8");
                }
                str2 = str;
            }
        } catch (Throwable unused) {
        }
        return new JDNetworkResponse(networkResponse.statusCode, networkResponse.headers, str2);
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

    public void setUrl(String str) {
        this.url = str;
    }

    public JDError(JDNetworkResponse jDNetworkResponse) {
        this.isDowngradeError = false;
        this.statusCode = 0;
        this.networkResponse = jDNetworkResponse;
    }

    public JDError(String str) {
        super(str);
        this.isDowngradeError = false;
        this.statusCode = 0;
        this.networkResponse = null;
    }

    public JDError(VolleyError volleyError) {
        super(volleyError.getCause());
        this.isDowngradeError = false;
        this.statusCode = 0;
        this.networkResponse = convert2JDNetworkResponse(volleyError);
        this.statusCode = volleyError.getStatusCode();
        this.isDowngradeError = volleyError.isDowngradeError();
        this.url = volleyError.getUrl();
    }

    public JDError(String str, Throwable th) {
        super(str, th);
        this.isDowngradeError = false;
        this.statusCode = 0;
        this.networkResponse = null;
    }

    public JDError(Throwable th, String str) {
        super(th);
        this.isDowngradeError = false;
        this.statusCode = 0;
        this.networkResponse = null;
        this.url = str;
    }

    public JDError(Throwable th) {
        super(th);
        this.isDowngradeError = false;
        this.statusCode = 0;
        this.networkResponse = null;
        this.url = null;
    }
}
