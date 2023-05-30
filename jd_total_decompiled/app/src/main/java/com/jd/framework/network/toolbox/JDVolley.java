package com.jd.framework.network.toolbox;

import android.content.Context;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HttpStackFactory;
import com.google.common.net.HttpHeaders;
import com.jingdong.jdsdk.network.config.RuntimeConfigHelper;
import java.io.File;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes13.dex */
public class JDVolley {
    private static final String DEFAULT_CACHE_DIR = "jd_volley";
    private static final int JD_DISK_USAGE_BYTES = 5242880;
    private static ConcurrentHashMap<String, String> additionalHeaders;

    public static RequestQueue addToRequestQueue(Context context, Request request) {
        RequestQueue newRequestQueue = newRequestQueue(context);
        newRequestQueue.add(request);
        return newRequestQueue;
    }

    private static synchronized ConcurrentHashMap getAdditionalHeaders() {
        ConcurrentHashMap<String, String> concurrentHashMap;
        synchronized (JDVolley.class) {
            if (additionalHeaders == null) {
                ConcurrentHashMap<String, String> concurrentHashMap2 = new ConcurrentHashMap<>();
                concurrentHashMap2.put("Connection", "keep-alive");
                if (RuntimeConfigHelper.enableBrCompress()) {
                    concurrentHashMap2.put(HttpHeaders.ACCEPT_ENCODING, "br,gzip,deflate");
                } else {
                    concurrentHashMap2.put(HttpHeaders.ACCEPT_ENCODING, "gzip,deflate");
                }
                concurrentHashMap2.put("Charset", "UTF-8");
                additionalHeaders = concurrentHashMap2;
            }
            concurrentHashMap = additionalHeaders;
        }
        return concurrentHashMap;
    }

    public static RequestQueue newRequestQueue(Context context, HttpStackFactory httpStackFactory) {
        File file = new File(context.getCacheDir(), DEFAULT_CACHE_DIR);
        if (httpStackFactory == null) {
            httpStackFactory = new HttpStackFactory();
        }
        RequestQueue requestQueue = new RequestQueue(new DiskBasedCache(file, JD_DISK_USAGE_BYTES), new BasicNetwork(httpStackFactory, getAdditionalHeaders()));
        requestQueue.start();
        return requestQueue;
    }

    public static synchronized void updateAdditionalHeaders(boolean z) {
        synchronized (JDVolley.class) {
            getAdditionalHeaders().put("Connection", z ? "Keep-Alive" : "close");
        }
    }

    public static RequestQueue newRequestQueue(Context context) {
        return newRequestQueue(context, null);
    }
}
