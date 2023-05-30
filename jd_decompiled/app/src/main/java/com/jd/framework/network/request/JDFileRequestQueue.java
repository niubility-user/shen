package com.jd.framework.network.request;

import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes13.dex */
public class JDFileRequestQueue {
    private AtomicInteger mSequenceGenerator = new AtomicInteger();
    private final PriorityBlockingQueue<JDFileRequest> mRequestQueue = new PriorityBlockingQueue<>();

    public JDFileRequest add(JDFileRequest jDFileRequest) {
        if (jDFileRequest.getSequence() == -1) {
            jDFileRequest.setSequence(getSequenceNumber());
        }
        this.mRequestQueue.add(jDFileRequest);
        return jDFileRequest;
    }

    public int getQueueSize() {
        return this.mRequestQueue.size();
    }

    public int getSequenceNumber() {
        return this.mSequenceGenerator.incrementAndGet();
    }

    public JDFileRequest take() throws InterruptedException {
        return this.mRequestQueue.take();
    }
}
