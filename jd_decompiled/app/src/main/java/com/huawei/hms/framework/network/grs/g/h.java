package com.huawei.hms.framework.network.grs.g;

import com.huawei.hms.framework.common.ExecutorsUtils;
import com.huawei.hms.framework.common.Logger;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;

/* loaded from: classes12.dex */
public class h {
    private final ExecutorService a = ExecutorsUtils.newCachedThreadPool("GRS_RequestController-Task");
    private final Map<String, com.huawei.hms.framework.network.grs.g.k.b> b = new ConcurrentHashMap(16);

    /* renamed from: c */
    private final Object f1339c = new Object();
    private com.huawei.hms.framework.network.grs.e.a d;

    /* loaded from: classes12.dex */
    public class a implements Callable<d> {
        final /* synthetic */ com.huawei.hms.framework.network.grs.g.k.c a;
        final /* synthetic */ String b;

        /* renamed from: c */
        final /* synthetic */ com.huawei.hms.framework.network.grs.e.c f1340c;

        a(com.huawei.hms.framework.network.grs.g.k.c cVar, String str, com.huawei.hms.framework.network.grs.e.c cVar2) {
            h.this = r1;
            this.a = cVar;
            this.b = str;
            this.f1340c = cVar2;
        }

        @Override // java.util.concurrent.Callable
        public d call() {
            return new c(this.a, h.this.d).a(h.this.a, this.b, this.f1340c);
        }
    }

    /* loaded from: classes12.dex */
    public class b implements Runnable {
        final /* synthetic */ com.huawei.hms.framework.network.grs.g.k.c a;
        final /* synthetic */ String b;

        /* renamed from: c */
        final /* synthetic */ com.huawei.hms.framework.network.grs.e.c f1341c;
        final /* synthetic */ com.huawei.hms.framework.network.grs.b d;

        b(com.huawei.hms.framework.network.grs.g.k.c cVar, String str, com.huawei.hms.framework.network.grs.e.c cVar2, com.huawei.hms.framework.network.grs.b bVar) {
            h.this = r1;
            this.a = cVar;
            this.b = str;
            this.f1341c = cVar2;
            this.d = bVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            h hVar = h.this;
            hVar.a(hVar.a(this.a, this.b, this.f1341c), this.d);
        }
    }

    public void a(d dVar, com.huawei.hms.framework.network.grs.b bVar) {
        if (bVar != null) {
            if (dVar == null) {
                Logger.v("RequestController", "GrsResponse is null");
                bVar.a();
                return;
            }
            Logger.v("RequestController", "GrsResponse is not null");
            bVar.a(dVar);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:54:0x0069, code lost:
        if (r2.a() != false) goto L56;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x006d, code lost:
        return null;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.huawei.hms.framework.network.grs.g.d a(com.huawei.hms.framework.network.grs.g.k.c r7, java.lang.String r8, com.huawei.hms.framework.network.grs.e.c r9) {
        /*
            r6 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "request to server with service name is: "
            r0.append(r1)
            r0.append(r8)
            java.lang.String r0 = r0.toString()
            java.lang.String r1 = "RequestController"
            com.huawei.hms.framework.common.Logger.d(r1, r0)
            com.huawei.hms.framework.network.grs.GrsBaseInfo r0 = r7.b()
            android.content.Context r1 = r7.a()
            r2 = 1
            java.lang.String r0 = r0.getGrsParasKey(r2, r2, r1)
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "request spUrlKey: "
            r1.append(r2)
            r1.append(r0)
            java.lang.String r1 = r1.toString()
            java.lang.String r2 = "RequestController"
            com.huawei.hms.framework.common.Logger.v(r2, r1)
            java.lang.Object r1 = r6.f1339c
            monitor-enter(r1)
            android.content.Context r2 = r7.a()     // Catch: java.lang.Throwable -> Laa
            boolean r2 = com.huawei.hms.framework.common.NetworkUtil.isNetworkAvailable(r2)     // Catch: java.lang.Throwable -> Laa
            r3 = 0
            if (r2 != 0) goto L49
            monitor-exit(r1)     // Catch: java.lang.Throwable -> Laa
            return r3
        L49:
            com.huawei.hms.framework.network.grs.h.d$a r2 = com.huawei.hms.framework.network.grs.h.d.a(r0)     // Catch: java.lang.Throwable -> Laa
            java.util.Map<java.lang.String, com.huawei.hms.framework.network.grs.g.k.b> r4 = r6.b     // Catch: java.lang.Throwable -> Laa
            java.lang.Object r4 = r4.get(r0)     // Catch: java.lang.Throwable -> Laa
            com.huawei.hms.framework.network.grs.g.k.b r4 = (com.huawei.hms.framework.network.grs.g.k.b) r4     // Catch: java.lang.Throwable -> Laa
            if (r4 == 0) goto L63
            boolean r5 = r4.b()     // Catch: java.lang.Throwable -> Laa
            if (r5 != 0) goto L5e
            goto L63
        L5e:
            java.util.concurrent.Future r7 = r4.a()     // Catch: java.lang.Throwable -> Laa
            goto L8a
        L63:
            if (r2 == 0) goto L6e
            boolean r2 = r2.a()     // Catch: java.lang.Throwable -> Laa
            if (r2 != 0) goto L6c
            goto L6e
        L6c:
            monitor-exit(r1)     // Catch: java.lang.Throwable -> Laa
            return r3
        L6e:
            java.lang.String r2 = "RequestController"
            java.lang.String r4 = "hitGrsRequestBean == null or request block is released."
            com.huawei.hms.framework.common.Logger.d(r2, r4)     // Catch: java.lang.Throwable -> Laa
            java.util.concurrent.ExecutorService r2 = r6.a     // Catch: java.lang.Throwable -> Laa
            com.huawei.hms.framework.network.grs.g.h$a r4 = new com.huawei.hms.framework.network.grs.g.h$a     // Catch: java.lang.Throwable -> Laa
            r4.<init>(r7, r8, r9)     // Catch: java.lang.Throwable -> Laa
            java.util.concurrent.Future r7 = r2.submit(r4)     // Catch: java.lang.Throwable -> Laa
            java.util.Map<java.lang.String, com.huawei.hms.framework.network.grs.g.k.b> r8 = r6.b     // Catch: java.lang.Throwable -> Laa
            com.huawei.hms.framework.network.grs.g.k.b r9 = new com.huawei.hms.framework.network.grs.g.k.b     // Catch: java.lang.Throwable -> Laa
            r9.<init>(r7)     // Catch: java.lang.Throwable -> Laa
            r8.put(r0, r9)     // Catch: java.lang.Throwable -> Laa
        L8a:
            monitor-exit(r1)     // Catch: java.lang.Throwable -> Laa
            java.lang.Object r7 = r7.get()     // Catch: java.lang.InterruptedException -> L92 java.util.concurrent.ExecutionException -> L99 java.util.concurrent.CancellationException -> La0
            com.huawei.hms.framework.network.grs.g.d r7 = (com.huawei.hms.framework.network.grs.g.d) r7     // Catch: java.lang.InterruptedException -> L92 java.util.concurrent.ExecutionException -> L99 java.util.concurrent.CancellationException -> La0
            return r7
        L92:
            r7 = move-exception
            java.lang.String r8 = "RequestController"
            java.lang.String r9 = "when check result, find InterruptedException, check others"
            goto La6
        L99:
            r7 = move-exception
            java.lang.String r8 = "RequestController"
            java.lang.String r9 = "when check result, find ExecutionException, check others"
            goto La6
        La0:
            r7 = move-exception
            java.lang.String r8 = "RequestController"
            java.lang.String r9 = "when check result, find CancellationException, check others"
        La6:
            com.huawei.hms.framework.common.Logger.w(r8, r9, r7)
            return r3
        Laa:
            r7 = move-exception
            monitor-exit(r1)     // Catch: java.lang.Throwable -> Laa
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.framework.network.grs.g.h.a(com.huawei.hms.framework.network.grs.g.k.c, java.lang.String, com.huawei.hms.framework.network.grs.e.c):com.huawei.hms.framework.network.grs.g.d");
    }

    public void a(com.huawei.hms.framework.network.grs.e.a aVar) {
        this.d = aVar;
    }

    public void a(com.huawei.hms.framework.network.grs.g.k.c cVar, com.huawei.hms.framework.network.grs.b bVar, String str, com.huawei.hms.framework.network.grs.e.c cVar2) {
        this.a.execute(new b(cVar, str, cVar2, bVar));
    }

    public void a(String str) {
        synchronized (this.f1339c) {
            this.b.remove(str);
        }
    }
}
