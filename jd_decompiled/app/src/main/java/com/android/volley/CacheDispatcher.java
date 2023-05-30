package com.android.volley;

import android.os.Process;
import com.android.volley.Cache;
import com.android.volley.error.CacheMissError;
import java.util.concurrent.BlockingQueue;

/* loaded from: classes.dex */
public class CacheDispatcher extends Thread {
    private static final boolean DEBUG = VolleyLog.DEBUG;
    private final Cache mCache;
    private final BlockingQueue<Request<?>> mCacheQueue;
    private final ResponseDelivery mDelivery;
    private final BlockingQueue<Request<?>> mNetworkQueue;
    private volatile boolean mQuit = false;
    private final RequestQueue mRequestQueue;

    public CacheDispatcher(BlockingQueue<Request<?>> blockingQueue, RequestQueue requestQueue, BlockingQueue<Request<?>> blockingQueue2, Cache cache, ResponseDelivery responseDelivery) {
        this.mCacheQueue = blockingQueue;
        this.mNetworkQueue = blockingQueue2;
        this.mRequestQueue = requestQueue;
        this.mCache = cache;
        this.mDelivery = responseDelivery;
    }

    public void quit() {
        this.mQuit = true;
        interrupt();
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        if (DEBUG) {
            VolleyLog.v("start new dispatcher", new Object[0]);
        }
        Process.setThreadPriority(10);
        this.mCache.initialize();
        while (true) {
            try {
                final Request<?> take = this.mCacheQueue.take();
                take.addMarker("cache-queue-take");
                if (take.isCanceled()) {
                    this.mDelivery.postCancel(take);
                    take.finish("cache-discard-canceled");
                } else {
                    Cache.Entry entry = this.mCache.get(take.getCacheKey());
                    int cacheModel = take.getCacheModel();
                    if (entry == null) {
                        take.addMarker("cache-miss");
                        if (cacheModel != 2) {
                            this.mRequestQueue.add2NetworkQueue(take);
                        } else {
                            this.mDelivery.postResponse(take, Response.error(new CacheMissError("cache-miss")));
                        }
                    } else if (entry.isExpired()) {
                        take.addMarker("cache-hit-expired");
                        take.setCacheEntry(entry);
                        if (cacheModel != 2) {
                            this.mRequestQueue.add2NetworkQueue(take);
                        } else {
                            this.mDelivery.postResponse(take, Response.error(new CacheMissError("cache-miss")));
                        }
                    } else {
                        take.addMarker("cache-hit");
                        Response<?> parseNetworkResponse = take.parseNetworkResponse(new NetworkResponse(entry.data, entry.responseHeaders));
                        parseNetworkResponse.setCache(true);
                        parseNetworkResponse.setHeaders(entry.responseHeaders);
                        take.addMarker("cache-hit-parsed");
                        if (cacheModel != 2 && (cacheModel != 0 || entry.refreshNeeded())) {
                            take.addMarker("cache-hit-refresh-needed");
                            take.setCacheEntry(entry);
                            parseNetworkResponse.intermediate = true;
                            this.mDelivery.postResponse(take, parseNetworkResponse, new Runnable() { // from class: com.android.volley.CacheDispatcher.1
                                @Override // java.lang.Runnable
                                public void run() {
                                    try {
                                        CacheDispatcher.this.mRequestQueue.add2NetworkQueue(take);
                                    } catch (Exception unused) {
                                    }
                                }
                            });
                        }
                        this.mDelivery.postResponse(take, parseNetworkResponse);
                    }
                }
            } catch (InterruptedException unused) {
                if (this.mQuit) {
                    return;
                }
            }
        }
    }
}
