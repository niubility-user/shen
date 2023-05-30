package com.android.volley;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.text.TextUtils;
import com.jd.framework.network.JDCacheChecker;
import com.jingdong.jdsdk.network.config.RuntimeConfigHelper;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes.dex */
public class RequestQueue {
    private static final int DEFAULT_NETWORK_THREAD_POOL_SIZE = 5;
    public static final String TAG = "Volley-RequestQueue";
    private final int MAX_REQUEST_PER_SERVICE;
    private final Cache mCache;
    private CacheDispatcher mCacheDispatcher;
    private final PriorityBlockingQueue<Request<?>> mCacheQueue;
    private final Set<Request<?>> mCurrentRequests;
    private final ResponseDelivery mDelivery;
    private NetworkDispatcher[] mDispatchers;
    private final Network mNetwork;
    private final PriorityBlockingQueue<Request<?>> mNetworkQueue;
    private AtomicInteger mSequenceGenerator;
    private final Deque<Request<?>> readyAsyncCalls;
    private final Deque<Request<?>> runningAsyncCalls;

    /* loaded from: classes.dex */
    public interface RequestFilter {
        boolean apply(Request<?> request);
    }

    /* loaded from: classes.dex */
    public interface RequestFinishedListener<T> {
        void onRequestFinished(Request<T> request);
    }

    public RequestQueue(Cache cache, Network network, int i2, ResponseDelivery responseDelivery) {
        this.mSequenceGenerator = new AtomicInteger();
        this.mCurrentRequests = new HashSet();
        this.mCacheQueue = new PriorityBlockingQueue<>();
        this.mNetworkQueue = new PriorityBlockingQueue<>();
        this.MAX_REQUEST_PER_SERVICE = 2;
        this.readyAsyncCalls = new ArrayDeque();
        this.runningAsyncCalls = new ArrayDeque();
        this.mCache = cache;
        this.mNetwork = network;
        this.mDispatchers = new NetworkDispatcher[i2];
        this.mDelivery = responseDelivery;
    }

    private synchronized void finished(Request<?> request) {
        if (VolleyLog.DEBUG) {
            String str = "finished request : " + request.getServiceKey();
        }
        this.runningAsyncCalls.remove(request);
        promoteCalls();
        if (VolleyLog.DEBUG) {
            String str2 = "====running list size : " + this.runningAsyncCalls.size() + " ,ready list size" + this.readyAsyncCalls.size();
        }
    }

    private static Looper newHandlerLooperInOtherThread(String str) {
        HandlerThread handlerThread = new HandlerThread(str);
        handlerThread.start();
        return handlerThread.getLooper();
    }

    private void promoteCalls() {
        if (this.readyAsyncCalls.isEmpty()) {
            return;
        }
        Iterator<Request<?>> it = this.readyAsyncCalls.iterator();
        while (it.hasNext()) {
            Request<?> next = it.next();
            if (runningRequestPerService(next) < 2) {
                if (VolleyLog.DEBUG) {
                    String str = "promote request to running list and remove from ready list : " + next.getServiceKey();
                }
                it.remove();
                this.runningAsyncCalls.add(next);
                this.mNetworkQueue.add(next);
            }
        }
    }

    private int runningRequestPerService(Request<?> request) {
        Iterator<Request<?>> it = this.runningAsyncCalls.iterator();
        int i2 = 0;
        while (it.hasNext()) {
            if (TextUtils.equals(it.next().getServiceKey(), request.getServiceKey())) {
                i2++;
            }
        }
        return i2;
    }

    public <T> Request<T> add(Request<T> request) {
        request.setRequestQueue(this);
        synchronized (this.mCurrentRequests) {
            this.mCurrentRequests.add(request);
        }
        if (request.getSequence() == -1) {
            request.setSequence(getSequenceNumber());
        }
        request.addMarker("add-to-queue");
        if (request.shouldCache() && request.getCacheModel() != 3) {
            this.mCacheQueue.add(request);
            return request;
        }
        add2NetworkQueue(request);
        return request;
    }

    public synchronized void add2NetworkQueue(Request<?> request) {
        if (!TextUtils.isEmpty(request.getServiceKey()) && RuntimeConfigHelper.antiBlockSwitch()) {
            if (runningRequestPerService(request) < 2) {
                if (VolleyLog.DEBUG) {
                    String str = "add request to running list : " + request.getServiceKey();
                }
                this.runningAsyncCalls.add(request);
                this.mNetworkQueue.add(request);
            } else {
                if (VolleyLog.DEBUG) {
                    String str2 = "add request to ready list : " + request.getServiceKey();
                }
                this.readyAsyncCalls.add(request);
            }
        }
        this.mNetworkQueue.add(request);
    }

    public void cancelAll(RequestFilter requestFilter) {
        synchronized (this.mCurrentRequests) {
            for (Request<?> request : this.mCurrentRequests) {
                if (requestFilter.apply(request)) {
                    request.cancel();
                }
            }
        }
    }

    public void finish(Request<?> request) {
        synchronized (this.mCurrentRequests) {
            this.mCurrentRequests.remove(request);
        }
        finished(request);
    }

    public Cache getCache() {
        return this.mCache;
    }

    public int getSequenceNumber() {
        return this.mSequenceGenerator.incrementAndGet();
    }

    public void setCacheChecker(JDCacheChecker jDCacheChecker) {
        this.mNetwork.setCacheChecker(jDCacheChecker);
    }

    public void start() {
        stop();
        CacheDispatcher cacheDispatcher = new CacheDispatcher(this.mCacheQueue, this, this.mNetworkQueue, this.mCache, this.mDelivery);
        this.mCacheDispatcher = cacheDispatcher;
        cacheDispatcher.setName("Volley-CacheDispatcher");
        this.mCacheDispatcher.start();
        for (int i2 = 0; i2 < this.mDispatchers.length; i2++) {
            NetworkDispatcher networkDispatcher = new NetworkDispatcher(this.mNetworkQueue, this.mNetwork, this.mCache, this.mDelivery);
            NetworkDispatcher[] networkDispatcherArr = this.mDispatchers;
            networkDispatcherArr[i2] = networkDispatcher;
            networkDispatcherArr[i2].setName("Volley-NetworkDispatcher-" + i2);
            networkDispatcher.start();
        }
    }

    public void stop() {
        CacheDispatcher cacheDispatcher = this.mCacheDispatcher;
        if (cacheDispatcher != null) {
            cacheDispatcher.quit();
        }
        int i2 = 0;
        while (true) {
            NetworkDispatcher[] networkDispatcherArr = this.mDispatchers;
            if (i2 >= networkDispatcherArr.length) {
                return;
            }
            if (networkDispatcherArr[i2] != null) {
                networkDispatcherArr[i2].quit();
            }
            i2++;
        }
    }

    public void cancelAll(final Object obj) {
        if (obj != null) {
            cancelAll(new RequestFilter() { // from class: com.android.volley.RequestQueue.1
                {
                    RequestQueue.this = this;
                }

                @Override // com.android.volley.RequestQueue.RequestFilter
                public boolean apply(Request<?> request) {
                    return request.getTag() == obj;
                }
            });
            return;
        }
        throw new IllegalArgumentException("Cannot cancelAll with a null tag");
    }

    public RequestQueue(Cache cache, Network network, int i2) {
        this(cache, network, i2, new ExecutorDelivery(new Handler(newHandlerLooperInOtherThread("CallBack_Thread"))));
    }

    public RequestQueue(Cache cache, Network network) {
        this(cache, network, 5);
    }
}
