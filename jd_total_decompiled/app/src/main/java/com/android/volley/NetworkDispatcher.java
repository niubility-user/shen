package com.android.volley;

import android.annotation.TargetApi;
import android.net.TrafficStats;
import android.os.Build;
import android.os.Process;
import com.android.volley.Request;
import com.android.volley.error.VolleyError;
import com.google.common.net.HttpHeaders;
import com.jd.framework.network.toolbox.JDNetworkStatisticTool;
import java.util.concurrent.BlockingQueue;

/* loaded from: classes.dex */
public class NetworkDispatcher extends Thread {
    private final Cache mCache;
    private final ResponseDelivery mDelivery;
    private final Network mNetwork;
    private final BlockingQueue<Request<?>> mQueue;
    private volatile boolean mQuit = false;

    public NetworkDispatcher(BlockingQueue<Request<?>> blockingQueue, Network network, Cache cache, ResponseDelivery responseDelivery) {
        this.mQueue = blockingQueue;
        this.mNetwork = network;
        this.mCache = cache;
        this.mDelivery = responseDelivery;
    }

    @TargetApi(14)
    private void addTrafficStatsTag(Request<?> request) {
        if (Build.VERSION.SDK_INT >= 14) {
            TrafficStats.setThreadStatsTag(request.getTrafficStatsTag());
        }
    }

    private void parseAndDeliverNetworkError(Request<?> request, VolleyError volleyError) {
        this.mDelivery.postError(request, request.parseNetworkError(volleyError));
    }

    public void quit() {
        this.mQuit = true;
        interrupt();
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        Request<?> take;
        Process.setThreadPriority(10);
        while (true) {
            try {
                take = this.mQueue.take();
            } catch (InterruptedException unused) {
                if (this.mQuit) {
                    return;
                }
            }
            try {
                try {
                    try {
                        take.addMarker("network-queue-take");
                        if (take.isCanceled()) {
                            this.mDelivery.postCancel(take);
                            take.finish("network-discard-cancelled");
                        } else {
                            addTrafficStatsTag(take);
                            JDNetworkStatisticTool.getInstance().incrTotalRequestCount();
                            this.mDelivery.postStart(take);
                            long currentTimeMillis = System.currentTimeMillis();
                            NetworkResponse performRequest = this.mNetwork.performRequest(take, this.mDelivery);
                            if (performRequest == null) {
                                this.mDelivery.postCancel(take);
                                take.finish("network-discard-cancelled");
                            } else {
                                take.addMarker("network-http-complete");
                                if (performRequest.notModified && take.hasHadResponseDelivered()) {
                                    take.finish("not-modified");
                                } else {
                                    if (take.getCacheTime() > 0) {
                                        performRequest.headers.put(HttpHeaders.EXPIRES, Long.toString(take.getCacheTime() + System.currentTimeMillis()));
                                    }
                                    Response<?> parseNetworkResponse = take.parseNetworkResponse(performRequest);
                                    parseNetworkResponse.setCache(false);
                                    performRequest.headers.put("X_REQUEST_RTT", String.valueOf(System.currentTimeMillis() - currentTimeMillis));
                                    parseNetworkResponse.setHeaders(performRequest.headers);
                                    take.addMarker("network-parse-complete");
                                    JDNetworkStatisticTool.getInstance().incrSucceedRequestCount();
                                    if (take.getDownGradeType() == Request.DownGradeType.DownGrade2Domain) {
                                        JDNetworkStatisticTool.getInstance().incrFinalDowngradeRequestCount();
                                    } else if (take.getDownGradeType() == Request.DownGradeType.DownGrade2BuildInIP) {
                                        JDNetworkStatisticTool.getInstance().incrDowngrade2BuildInIpRequestCount();
                                        JDNetworkStatisticTool.getInstance().incrDomain2IpDowngradRequestCount();
                                    } else if (take.getDownGradeType() == Request.DownGradeType.DownGrade2HttpDnsIP) {
                                        JDNetworkStatisticTool.getInstance().incrDowngrade2HttpDnsIpRequestCount();
                                        JDNetworkStatisticTool.getInstance().incrDomain2IpDowngradRequestCount();
                                    } else if (take.getDownGradeType() == Request.DownGradeType.DownGrade2HttpDnsBackupIP) {
                                        JDNetworkStatisticTool.getInstance().incrDowngrade2BackupIpRequestCount();
                                        JDNetworkStatisticTool.getInstance().incrDomain2IpDowngradRequestCount();
                                    }
                                    if (take.shouldCache(parseNetworkResponse.result) && parseNetworkResponse.cacheEntry != null && take.getCacheTime() != 0) {
                                        if (this.mNetwork.getCacheChecker().isResponseCache(parseNetworkResponse.result)) {
                                            this.mCache.put(take.getCacheKey(), parseNetworkResponse.cacheEntry);
                                            take.addMarker("network-cache-written");
                                        } else {
                                            take.addMarker("network-cache-not-written");
                                        }
                                    }
                                    take.markDelivered();
                                    this.mDelivery.postResponse(take, parseNetworkResponse);
                                }
                            }
                        }
                    } catch (VolleyError e2) {
                        parseAndDeliverNetworkError(take, e2);
                    }
                } catch (Exception e3) {
                    VolleyLog.e(e3, "Unhandled exception %s", e3.toString());
                    this.mDelivery.postError(take, new VolleyError(e3));
                }
            } finally {
                JDNetworkStatisticTool.getInstance().saveNetworkStatisticResult();
            }
        }
    }
}
