package com.huawei.hms.framework.network.grs.g;

import android.content.Context;
import android.os.SystemClock;
import android.text.TextUtils;
import com.google.common.net.HttpHeaders;
import com.huawei.hms.framework.common.Logger;
import com.huawei.hms.framework.network.grs.GrsBaseInfo;
import com.huawei.hms.framework.network.grs.h.d;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.json.JSONArray;

/* loaded from: classes12.dex */
public class c {

    /* renamed from: n */
    private static final String f1313n = "c";
    private final GrsBaseInfo a;
    private final Context b;

    /* renamed from: c */
    private final com.huawei.hms.framework.network.grs.e.a f1314c;
    private d d;

    /* renamed from: j */
    private final com.huawei.hms.framework.network.grs.g.k.c f1320j;

    /* renamed from: k */
    private com.huawei.hms.framework.network.grs.g.k.d f1321k;

    /* renamed from: e */
    private final Map<String, Future<d>> f1315e = new ConcurrentHashMap(16);

    /* renamed from: f */
    private final List<d> f1316f = new CopyOnWriteArrayList();

    /* renamed from: g */
    private final JSONArray f1317g = new JSONArray();

    /* renamed from: h */
    private final List<String> f1318h = new CopyOnWriteArrayList();

    /* renamed from: i */
    private final List<String> f1319i = new CopyOnWriteArrayList();

    /* renamed from: l */
    private String f1322l = "";

    /* renamed from: m */
    private long f1323m = 1;

    /* loaded from: classes12.dex */
    public class a implements Callable<d> {
        final /* synthetic */ ExecutorService a;
        final /* synthetic */ String b;

        /* renamed from: c */
        final /* synthetic */ com.huawei.hms.framework.network.grs.e.c f1324c;

        a(ExecutorService executorService, String str, com.huawei.hms.framework.network.grs.e.c cVar) {
            c.this = r1;
            this.a = executorService;
            this.b = str;
            this.f1324c = cVar;
        }

        @Override // java.util.concurrent.Callable
        public d call() {
            return c.this.b(this.a, this.b, this.f1324c);
        }
    }

    public c(com.huawei.hms.framework.network.grs.g.k.c cVar, com.huawei.hms.framework.network.grs.e.a aVar) {
        this.f1320j = cVar;
        this.a = cVar.b();
        this.b = cVar.a();
        this.f1314c = aVar;
        c();
        d();
    }

    /* JADX WARN: Removed duplicated region for block: B:121:0x009a A[LOOP:0: B:91:0x0006->B:121:0x009a, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:131:0x0092 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private d a(ExecutorService executorService, List<String> list, String str, com.huawei.hms.framework.network.grs.e.c cVar) {
        d dVar;
        d dVar2 = null;
        int i2 = 0;
        while (true) {
            if (i2 >= list.size()) {
                break;
            }
            String str2 = list.get(i2);
            boolean z = true;
            if (!TextUtils.isEmpty(str2)) {
                Future<d> submit = executorService.submit(new com.huawei.hms.framework.network.grs.g.a(str2, i2, this, this.b, str, this.a, cVar).g());
                this.f1315e.put(str2, submit);
                try {
                    dVar = submit.get(this.f1323m, TimeUnit.SECONDS);
                } catch (InterruptedException e2) {
                    e = e2;
                } catch (CancellationException unused) {
                } catch (ExecutionException e3) {
                    e = e3;
                } catch (TimeoutException unused2) {
                }
                if (dVar != null) {
                    try {
                    } catch (InterruptedException e4) {
                        e = e4;
                        dVar2 = dVar;
                        Logger.w(f1313n, "the current thread was interrupted while waiting", e);
                        if (!z) {
                        }
                    } catch (CancellationException unused3) {
                        dVar2 = dVar;
                        Logger.i(f1313n, "{requestServer} the computation was cancelled");
                        if (!z) {
                        }
                    } catch (ExecutionException e5) {
                        e = e5;
                        dVar2 = dVar;
                        Logger.w(f1313n, "the computation threw an ExecutionException", e);
                        z = false;
                        if (!z) {
                        }
                    } catch (TimeoutException unused4) {
                        dVar2 = dVar;
                        Logger.w(f1313n, "the wait timed out");
                        z = false;
                        if (!z) {
                        }
                    }
                    if (dVar.o() || dVar.m()) {
                        Logger.i(f1313n, "grs request return body is not null and is OK.");
                        dVar2 = dVar;
                        if (!z) {
                            Logger.v(f1313n, "needBreak is true so need break current circulation");
                            break;
                        }
                        i2++;
                    }
                }
                dVar2 = dVar;
            }
            z = false;
            if (!z) {
            }
        }
        return b(dVar2);
    }

    private void a(String str, String str2) {
        StringBuilder sb = new StringBuilder();
        sb.append(str2);
        sb.append(str);
        String grsReqParamJoint = this.a.getGrsReqParamJoint(false, false, e(), this.b);
        if (!TextUtils.isEmpty(grsReqParamJoint)) {
            sb.append("?");
            sb.append(grsReqParamJoint);
        }
        this.f1319i.add(sb.toString());
    }

    private d b(d dVar) {
        String str;
        String str2;
        for (Map.Entry<String, Future<d>> entry : this.f1315e.entrySet()) {
            if (dVar != null && (dVar.o() || dVar.m())) {
                break;
            }
            try {
                dVar = entry.getValue().get(40000L, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e2) {
                e = e2;
                str = f1313n;
                str2 = "{checkResponse} when check result, find InterruptedException, check others";
                Logger.w(str, str2, e);
            } catch (CancellationException unused) {
                Logger.i(f1313n, "{checkResponse} when check result, find CancellationException, check others");
            } catch (ExecutionException e3) {
                e = e3;
                str = f1313n;
                str2 = "{checkResponse} when check result, find ExecutionException, check others";
                Logger.w(str, str2, e);
            } catch (TimeoutException unused2) {
                Logger.w(f1313n, "{checkResponse} when check result, find TimeoutException, cancel current request task");
                if (!entry.getValue().isCancelled()) {
                    entry.getValue().cancel(true);
                }
            }
        }
        return dVar;
    }

    public d b(ExecutorService executorService, String str, com.huawei.hms.framework.network.grs.e.c cVar) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        d a2 = a(executorService, this.f1319i, str, cVar);
        int b = a2 == null ? 0 : a2.b();
        String str2 = f1313n;
        Logger.v(str2, "use 2.0 interface return http's code is\uff1a{%s}", Integer.valueOf(b));
        if (b == 404 || b == 401) {
            if (TextUtils.isEmpty(e()) && TextUtils.isEmpty(this.a.getAppName())) {
                Logger.i(str2, "request grs server use 1.0 API must set appName,please check.");
                return null;
            }
            this.f1315e.clear();
            Logger.i(str2, "this env has not deploy new interface,so use old interface.");
            a2 = a(executorService, this.f1318h, str, cVar);
        }
        e.a(new ArrayList(this.f1316f), SystemClock.elapsedRealtime() - elapsedRealtime, this.f1317g, this.b);
        this.f1316f.clear();
        return a2;
    }

    private void b(String str, String str2) {
        if (TextUtils.isEmpty(this.a.getAppName()) && TextUtils.isEmpty(e())) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(str2);
        Locale locale = Locale.ROOT;
        Object[] objArr = new Object[1];
        objArr[0] = TextUtils.isEmpty(e()) ? this.a.getAppName() : e();
        sb.append(String.format(locale, str, objArr));
        String grsReqParamJoint = this.a.getGrsReqParamJoint(false, false, "1.0", this.b);
        if (!TextUtils.isEmpty(grsReqParamJoint)) {
            sb.append("?");
            sb.append(grsReqParamJoint);
        }
        this.f1318h.add(sb.toString());
    }

    private void c() {
        com.huawei.hms.framework.network.grs.g.k.d a2 = com.huawei.hms.framework.network.grs.g.j.a.a(this.b);
        if (a2 == null) {
            Logger.w(f1313n, "g*s***_se****er_conf*** maybe has a big error");
            return;
        }
        a(a2);
        List<String> a3 = a2.a();
        if (a3 == null || a3.size() <= 0) {
            Logger.v(f1313n, "maybe grs_base_url config with [],please check.");
        } else if (a3.size() > 10) {
            throw new IllegalArgumentException("grs_base_url's count is larger than MAX value 10");
        } else {
            String c2 = a2.c();
            String b = a2.b();
            if (a3.size() > 0) {
                for (String str : a3) {
                    if (str.startsWith("https://")) {
                        b(c2, str);
                        a(b, str);
                    } else {
                        Logger.w(f1313n, "grs server just support https scheme url,please check.");
                    }
                }
            }
            Logger.v(f1313n, "request to GRS server url is{%s} and {%s}", this.f1318h, this.f1319i);
        }
    }

    private void d() {
        String grsParasKey = this.a.getGrsParasKey(true, true, this.b);
        this.f1322l = this.f1314c.a().a(grsParasKey + HttpHeaders.ETAG, "");
    }

    private String e() {
        com.huawei.hms.framework.network.grs.f.b a2 = com.huawei.hms.framework.network.grs.f.b.a(this.b.getPackageName(), this.a);
        com.huawei.hms.framework.network.grs.local.model.a a3 = a2 != null ? a2.a() : null;
        if (a3 != null) {
            String b = a3.b();
            Logger.v(f1313n, "get appName from local assets is{%s}", b);
            return b;
        }
        return "";
    }

    public d a(ExecutorService executorService, String str, com.huawei.hms.framework.network.grs.e.c cVar) {
        String str2;
        String str3;
        if (this.f1318h.isEmpty() && this.f1319i.isEmpty()) {
            return null;
        }
        try {
            return (d) executorService.submit(new a(executorService, str, cVar)).get(b() != null ? r0.d() : 10, TimeUnit.SECONDS);
        } catch (InterruptedException e2) {
            e = e2;
            str2 = f1313n;
            str3 = "{submitExcutorTaskWithTimeout} the current thread was interrupted while waiting";
            Logger.w(str2, str3, e);
            return null;
        } catch (CancellationException unused) {
            Logger.i(f1313n, "{submitExcutorTaskWithTimeout} the computation was cancelled");
            return null;
        } catch (ExecutionException e3) {
            e = e3;
            str2 = f1313n;
            str3 = "{submitExcutorTaskWithTimeout} the computation threw an ExecutionException";
            Logger.w(str2, str3, e);
            return null;
        } catch (TimeoutException unused2) {
            Logger.w(f1313n, "{submitExcutorTaskWithTimeout} the wait timed out");
            return null;
        } catch (Exception e4) {
            e = e4;
            str2 = f1313n;
            str3 = "{submitExcutorTaskWithTimeout} catch Exception";
            Logger.w(str2, str3, e);
            return null;
        }
    }

    public String a() {
        return this.f1322l;
    }

    public synchronized void a(d dVar) {
        this.f1316f.add(dVar);
        d dVar2 = this.d;
        if (dVar2 != null && (dVar2.o() || this.d.m())) {
            Logger.v(f1313n, "grsResponseResult is ok");
        } else if (dVar.n()) {
            Logger.i(f1313n, "GRS server open 503 limiting strategy.");
            com.huawei.hms.framework.network.grs.h.d.a(this.a.getGrsParasKey(true, true, this.b), new d.a(dVar.k(), SystemClock.elapsedRealtime()));
        } else {
            if (dVar.m()) {
                Logger.i(f1313n, "GRS server open 304 Not Modified.");
            }
            if (!dVar.o() && !dVar.m()) {
                Logger.v(f1313n, "grsResponseResult has exception so need return");
                return;
            }
            this.d = dVar;
            this.f1314c.a(this.a, dVar, this.b, this.f1320j);
            com.huawei.hms.framework.network.grs.f.b.a(this.b, this.a);
            for (Map.Entry<String, Future<d>> entry : this.f1315e.entrySet()) {
                if (!entry.getKey().equals(dVar.l()) && !entry.getValue().isCancelled()) {
                    Logger.i(f1313n, "future cancel");
                    entry.getValue().cancel(true);
                }
            }
        }
    }

    public void a(com.huawei.hms.framework.network.grs.g.k.d dVar) {
        this.f1321k = dVar;
    }

    public com.huawei.hms.framework.network.grs.g.k.d b() {
        return this.f1321k;
    }
}
