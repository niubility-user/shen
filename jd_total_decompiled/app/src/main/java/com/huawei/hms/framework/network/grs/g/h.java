package com.huawei.hms.framework.network.grs.g;

import com.huawei.hms.framework.common.ExecutorsUtils;
import com.huawei.hms.framework.common.Logger;
import com.huawei.hms.framework.common.NetworkUtil;
import com.huawei.hms.framework.network.grs.h.d;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

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

    /* JADX WARN: Code restructure failed: missing block: B:92:0x0069, code lost:
        if (r2.a() != false) goto L94;
     */
    /* JADX WARN: Code restructure failed: missing block: B:95:0x006d, code lost:
        return null;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public d a(com.huawei.hms.framework.network.grs.g.k.c cVar, String str, com.huawei.hms.framework.network.grs.e.c cVar2) {
        Future<d> submit;
        String str2;
        String str3;
        Logger.d("RequestController", "request to server with service name is: " + str);
        String grsParasKey = cVar.b().getGrsParasKey(true, true, cVar.a());
        Logger.v("RequestController", "request spUrlKey: " + grsParasKey);
        synchronized (this.f1339c) {
            if (!NetworkUtil.isNetworkAvailable(cVar.a())) {
                return null;
            }
            d.a a2 = com.huawei.hms.framework.network.grs.h.d.a(grsParasKey);
            com.huawei.hms.framework.network.grs.g.k.b bVar = this.b.get(grsParasKey);
            try {
                if (bVar != null && bVar.b()) {
                    submit = bVar.a();
                    return submit.get();
                }
                return submit.get();
            } catch (InterruptedException e2) {
                e = e2;
                str2 = "RequestController";
                str3 = "when check result, find InterruptedException, check others";
                Logger.w(str2, str3, e);
                return null;
            } catch (CancellationException e3) {
                e = e3;
                str2 = "RequestController";
                str3 = "when check result, find CancellationException, check others";
                Logger.w(str2, str3, e);
                return null;
            } catch (ExecutionException e4) {
                e = e4;
                str2 = "RequestController";
                str3 = "when check result, find ExecutionException, check others";
                Logger.w(str2, str3, e);
                return null;
            }
            Logger.d("RequestController", "hitGrsRequestBean == null or request block is released.");
            submit = this.a.submit(new a(cVar, str, cVar2));
            this.b.put(grsParasKey, new com.huawei.hms.framework.network.grs.g.k.b(submit));
        }
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
