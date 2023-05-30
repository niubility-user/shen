package com.jingdong.sdk.jdhttpdns.core;

import com.jingdong.sdk.jdhttpdns.listener.IResolveListener;
import com.jingdong.sdk.jdhttpdns.pojo.FailEvent;
import com.jingdong.sdk.jdhttpdns.utils.StatisticTool;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes7.dex */
public class WorkRunnable implements Runnable {
    private static AtomicInteger failureCount = new AtomicInteger(0);
    private HttpDnsImpl httpDnsImpl;
    private IResolveListener listener;
    private Request request;
    private String uniqueId;

    public WorkRunnable(HttpDnsImpl httpDnsImpl, String str, IResolveListener iResolveListener, boolean z) {
        this.httpDnsImpl = httpDnsImpl;
        this.uniqueId = httpDnsImpl.uniqueId(str);
        Request request = new Request(str, new InternalResolveListener());
        this.request = request;
        request.setIsCdnParam(z);
        this.listener = iResolveListener;
    }

    public static int getFailureCount() {
        return failureCount.get();
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            try {
                StatisticTool.incrementTotalCountAndGet();
                this.httpDnsImpl.getMemoryCache().updataCache(this.httpDnsImpl.getNetworkHelper().requests(this.request));
                failureCount.set(0);
                StatisticTool.incrementSuccessCountAndGet();
                if (this.request.isFinalDowngrade()) {
                    StatisticTool.incrementDomainSuccessCountAndGet();
                }
                IResolveListener iResolveListener = this.listener;
                if (iResolveListener != null) {
                    iResolveListener.onSuccess();
                }
            } catch (Exception e2) {
                failureCount.incrementAndGet();
                IResolveListener iResolveListener2 = this.listener;
                if (iResolveListener2 != null) {
                    iResolveListener2.onFailure(new FailEvent(this.request.getLastRequestUrlStr(), e2));
                }
            }
        } finally {
            this.httpDnsImpl.remove(this.uniqueId);
        }
    }
}
