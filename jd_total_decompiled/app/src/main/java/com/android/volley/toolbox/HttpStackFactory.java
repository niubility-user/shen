package com.android.volley.toolbox;

import com.android.volley.VolleyLog;

/* loaded from: classes.dex */
public class HttpStackFactory {
    public static final String TAG = "HttpStackFactory";
    private HttpStack okHttpStack = new OkHttpStack();
    private HttpStack hurlStack = new HurlStack();

    public synchronized HttpStack getHttpStack(boolean z) {
        if (z) {
            boolean z2 = VolleyLog.DEBUG;
            return this.okHttpStack;
        }
        boolean z3 = VolleyLog.DEBUG;
        return this.hurlStack;
    }
}
