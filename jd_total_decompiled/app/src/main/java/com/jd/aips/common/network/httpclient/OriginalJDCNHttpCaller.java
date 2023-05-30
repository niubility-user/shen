package com.jd.aips.common.network.httpclient;

import com.jd.aips.common.network.httpclient.JDCNHttpCaller;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes12.dex */
public class OriginalJDCNHttpCaller extends JDCNHttpCaller {

    /* renamed from: c  reason: collision with root package name */
    private static volatile OriginalJDCNHttpCaller f1589c;
    private ThreadPoolExecutor a;
    private ConcurrentHashMap<Integer, OriginalHttpHandler> b = new ConcurrentHashMap<>();

    /* loaded from: classes12.dex */
    public static class NetThreadFactory implements ThreadFactory {
        private static final AtomicInteger d = new AtomicInteger(1);
        private final ThreadGroup a;
        private final AtomicInteger b = new AtomicInteger(1);

        /* renamed from: c  reason: collision with root package name */
        private final String f1590c;

        NetThreadFactory() {
            ThreadGroup threadGroup;
            SecurityManager securityManager = System.getSecurityManager();
            if (securityManager != null) {
                threadGroup = securityManager.getThreadGroup();
            } else {
                threadGroup = Thread.currentThread().getThreadGroup();
            }
            this.a = threadGroup;
            this.f1590c = "jdcn_net-" + d.getAndIncrement() + "-thread-";
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(this.a, runnable, this.f1590c + this.b.getAndIncrement(), 0L);
            if (thread.isDaemon()) {
                thread.setDaemon(false);
            }
            if (thread.getPriority() != 5) {
                thread.setPriority(5);
            }
            return thread;
        }
    }

    private OriginalJDCNHttpCaller() {
        b();
    }

    private void b() {
        this.a = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 10L, TimeUnit.SECONDS, new SynchronousQueue(), new NetThreadFactory());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(int i2) {
        this.b.remove(Integer.valueOf(i2));
    }

    @Override // com.jd.aips.common.network.httpclient.JDCNHttpCaller
    public void cancalAllRequest() {
        this.a.shutdownNow();
    }

    @Override // com.jd.aips.common.network.httpclient.JDCNHttpCaller
    public void cancelRequest(int i2) {
        OriginalHttpHandler remove = this.b.remove(Integer.valueOf(i2));
        String str = "OriginalJDCNHttpCaller cancelRequest requestId = " + i2;
        if (remove != null) {
            remove.a();
            String str2 = "handler != null cancelRequest requestId = " + i2;
        }
    }

    @Override // com.jd.aips.common.network.httpclient.JDCNHttpCaller
    public int startRequest(JDCNHttpCaller.NetworkRequest networkRequest, INetworkCallback iNetworkCallback) {
        networkRequest.getUrl();
        int nextInt = new Random().nextInt(10000) + 90000;
        if (this.a.isShutdown()) {
            b();
        }
        OriginalHttpHandler originalHttpHandler = new OriginalHttpHandler(networkRequest, iNetworkCallback, this, nextInt);
        this.b.put(Integer.valueOf(nextInt), originalHttpHandler);
        try {
            this.a.execute(originalHttpHandler);
        } catch (Throwable th) {
            iNetworkCallback.networkError(nextInt, -5, th.getMessage());
        }
        return nextInt;
    }

    @Override // com.jd.aips.common.network.httpclient.JDCNHttpCaller
    public JDCNHttpResponse startRequestSync(JDCNHttpCaller.NetworkRequest networkRequest) {
        return new OriginalHttpHandler(networkRequest, null, this, 0).a(this.a);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static OriginalJDCNHttpCaller a() {
        if (f1589c == null) {
            synchronized (OriginalJDCNHttpCaller.class) {
                if (f1589c == null) {
                    f1589c = new OriginalJDCNHttpCaller();
                }
            }
        }
        return f1589c;
    }
}
