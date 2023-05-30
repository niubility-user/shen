package com.android.volley;

import com.android.volley.Cache;
import com.android.volley.error.VolleyError;
import java.util.Map;

/* loaded from: classes.dex */
public class Response<T> {
    public final Cache.Entry cacheEntry;
    public final VolleyError error;
    private Map<String, String> headers;
    public boolean intermediate;
    private boolean isCache;
    public final T result;
    public int statusCode;

    /* loaded from: classes.dex */
    public interface ErrorListener {
        void onCancel();

        void onErrorResponse(VolleyError volleyError);

        void onStart();
    }

    /* loaded from: classes.dex */
    public interface Listener<T> {
        void onResponse(Response<T> response);
    }

    private Response(int i2, T t, Cache.Entry entry) {
        this.intermediate = false;
        this.result = t;
        this.cacheEntry = entry;
        this.error = null;
        this.statusCode = i2;
    }

    public static <T> Response<T> error(VolleyError volleyError) {
        return new Response<>(volleyError);
    }

    public static <T> Response<T> success(int i2, T t, Cache.Entry entry) {
        return new Response<>(i2, t, entry);
    }

    public Map<String, String> getHeaders() {
        return this.headers;
    }

    public boolean isCache() {
        return this.isCache;
    }

    public boolean isSuccess() {
        return this.error == null;
    }

    public void setCache(boolean z) {
        this.isCache = z;
    }

    public void setHeaders(Map<String, String> map) {
        this.headers = map;
    }

    private Response(VolleyError volleyError) {
        this.intermediate = false;
        this.result = null;
        this.cacheEntry = null;
        this.error = volleyError;
    }
}
