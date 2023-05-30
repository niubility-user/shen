package com.jdjr.risk.jdcn.common.network.httpclient;

import com.jdjr.risk.jdcn.common.network.httpclient.JDCNHttpCaller;
import com.jdjr.risk.jdcn.common.utils.JDCNLogUtils;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes18.dex */
public class b extends JDCNHttpCaller {

    /* renamed from: c  reason: collision with root package name */
    private static volatile b f7491c;
    ConcurrentHashMap<Integer, com.jdjr.risk.jdcn.common.network.httpclient.a> a = new ConcurrentHashMap<>();
    private ThreadPoolExecutor b;

    /* loaded from: classes18.dex */
    public static class a implements ThreadFactory {
        private static final AtomicInteger a = new AtomicInteger(1);
        private final ThreadGroup b;

        /* renamed from: c  reason: collision with root package name */
        private final AtomicInteger f7492c = new AtomicInteger(1);
        private final String d;

        a() {
            ThreadGroup threadGroup;
            SecurityManager securityManager = System.getSecurityManager();
            if (securityManager != null) {
                threadGroup = securityManager.getThreadGroup();
            } else {
                threadGroup = Thread.currentThread().getThreadGroup();
            }
            this.b = threadGroup;
            this.d = "jdcn_net-" + a.getAndIncrement() + "-thread-";
        }

        @Override // java.util.concurrent.ThreadFactory
        public final Thread newThread(Runnable runnable) {
            Thread thread = new Thread(this.b, runnable, this.d + this.f7492c.getAndIncrement(), 0L);
            if (thread.isDaemon()) {
                thread.setDaemon(false);
            }
            if (thread.getPriority() != 5) {
                thread.setPriority(5);
            }
            return thread;
        }
    }

    private b() {
        b();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static b a() {
        if (f7491c == null) {
            synchronized (b.class) {
                if (f7491c == null) {
                    f7491c = new b();
                }
            }
        }
        return f7491c;
    }

    private void b() {
        this.b = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 10L, TimeUnit.SECONDS, new SynchronousQueue(), new a());
    }

    @Override // com.jdjr.risk.jdcn.common.network.httpclient.JDCNHttpCaller
    public void cancalAllRequest() {
        this.b.shutdownNow();
    }

    @Override // com.jdjr.risk.jdcn.common.network.httpclient.JDCNHttpCaller
    public void cancelRequest(int i2) {
        com.jdjr.risk.jdcn.common.network.httpclient.a remove = this.a.remove(Integer.valueOf(i2));
        JDCNLogUtils.d("gggl", "OriginalJDCNHttpCaller cancelRequest requestId = ".concat(String.valueOf(i2)));
        if (remove != null) {
            remove.d = true;
            remove.b = null;
            JDCNLogUtils.d("gggl", "handler != null cancelRequest requestId = ".concat(String.valueOf(i2)));
        }
    }

    @Override // com.jdjr.risk.jdcn.common.network.httpclient.JDCNHttpCaller
    public int startRequest(JDCNHttpCaller.NetworkRequest networkRequest, INetworkCallback iNetworkCallback) {
        int generateId = generateId(networkRequest.getUrl());
        if (this.b.isShutdown()) {
            b();
        }
        com.jdjr.risk.jdcn.common.network.httpclient.a aVar = new com.jdjr.risk.jdcn.common.network.httpclient.a(networkRequest, iNetworkCallback, this, generateId);
        this.a.put(Integer.valueOf(generateId), aVar);
        try {
            this.b.execute(aVar);
        } catch (Throwable th) {
            iNetworkCallback.networkError(generateId, -5, th.getMessage());
        }
        return generateId;
    }

    @Override // com.jdjr.risk.jdcn.common.network.httpclient.JDCNHttpCaller
    public JDCNHttpResponse startRequestSync(JDCNHttpCaller.NetworkRequest networkRequest) {
        return new com.jdjr.risk.jdcn.common.network.httpclient.a(networkRequest, null, this, 0).a(this.b);
    }
}
