package com.jingdong.app.mall.bundle.mobileConfig.net;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/* loaded from: classes12.dex */
public class a implements IConfigFetcher {
    ExecutorService a;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.jingdong.app.mall.bundle.mobileConfig.net.a$a  reason: collision with other inner class name */
    /* loaded from: classes12.dex */
    public class ThreadFactoryC0258a implements ThreadFactory {
        ThreadFactoryC0258a(a aVar) {
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            return new Thread(runnable, "jdmobileconfig");
        }
    }

    /* loaded from: classes12.dex */
    class b implements Runnable {
        final /* synthetic */ IConfigFetcherCallBack a;

        b(IConfigFetcherCallBack iConfigFetcherCallBack) {
            this.a = iConfigFetcherCallBack;
        }

        @Override // java.lang.Runnable
        public void run() {
            a.this.a(this.a);
        }
    }

    private String a(long j2) {
        return com.jingdong.app.mall.bundle.mobileConfig.c.b.a(com.jingdong.app.mall.bundle.mobileConfig.a.l().a(j2), "", com.jingdong.app.mall.bundle.mobileConfig.a.l().g());
    }

    private synchronized ExecutorService a() {
        if (this.a == null) {
            this.a = Executors.newSingleThreadExecutor(new ThreadFactoryC0258a(this));
        }
        return this.a;
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x00b0 A[Catch: all -> 0x00e5, Exception -> 0x00e7, LOOP:0: B:21:0x00aa->B:23:0x00b0, LOOP_END, TryCatch #1 {all -> 0x00e5, blocks: (B:3:0x000d, B:20:0x0097, B:21:0x00aa, B:23:0x00b0, B:24:0x00b4, B:26:0x00d9, B:36:0x00e8, B:38:0x00ed), top: B:49:0x000d }] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00d9 A[Catch: all -> 0x00e5, Exception -> 0x00e7, TRY_LEAVE, TryCatch #1 {all -> 0x00e5, blocks: (B:3:0x000d, B:20:0x0097, B:21:0x00aa, B:23:0x00b0, B:24:0x00b4, B:26:0x00d9, B:36:0x00e8, B:38:0x00ed), top: B:49:0x000d }] */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00b4 A[EDGE_INSN: B:53:0x00b4->B:24:0x00b4 BREAK  A[LOOP:0: B:21:0x00aa->B:23:0x00b0], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:54:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    void a(com.jingdong.app.mall.bundle.mobileConfig.net.IConfigFetcherCallBack r7) {
        /*
            Method dump skipped, instructions count: 254
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.app.mall.bundle.mobileConfig.net.a.a(com.jingdong.app.mall.bundle.mobileConfig.net.IConfigFetcherCallBack):void");
    }

    @Override // com.jingdong.app.mall.bundle.mobileConfig.net.IConfigFetcher
    public void fetch(ConfigRequestParams configRequestParams, IConfigFetcherCallBack iConfigFetcherCallBack) {
        try {
            a().execute(new b(iConfigFetcherCallBack));
        } catch (Throwable th) {
            th.printStackTrace();
            if (iConfigFetcherCallBack != null) {
                iConfigFetcherCallBack.onError(new RuntimeException(th));
            }
        }
    }
}
