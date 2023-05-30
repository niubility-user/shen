package com.huawei.hms.framework.network.grs;

import android.content.Context;
import android.text.TextUtils;
import com.google.common.net.HttpHeaders;
import com.huawei.hms.framework.common.ExecutorsUtils;
import com.huawei.hms.framework.common.Logger;
import com.huawei.hms.framework.common.StringUtils;
import com.huawei.hms.framework.network.grs.g.h;
import com.huawei.hms.framework.network.grs.g.i;
import com.jingdong.common.jdmiaosha.utils.cache.Final;
import com.jingdong.jdsdk.constant.CartConstant;
import com.tencent.mapsdk.internal.i2;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes12.dex */
public class c {

    /* renamed from: i  reason: collision with root package name */
    private static final String f1288i = "c";

    /* renamed from: j  reason: collision with root package name */
    private static final ExecutorService f1289j = ExecutorsUtils.newSingleThreadExecutor("GRS_GrsClient-Init");

    /* renamed from: k  reason: collision with root package name */
    private static AtomicInteger f1290k = new AtomicInteger(0);
    private GrsBaseInfo a;
    private Context b;

    /* renamed from: c  reason: collision with root package name */
    private h f1291c;
    private com.huawei.hms.framework.network.grs.e.a d;

    /* renamed from: e  reason: collision with root package name */
    private com.huawei.hms.framework.network.grs.e.c f1292e;

    /* renamed from: f  reason: collision with root package name */
    private com.huawei.hms.framework.network.grs.e.c f1293f;

    /* renamed from: g  reason: collision with root package name */
    private com.huawei.hms.framework.network.grs.a f1294g;

    /* renamed from: h  reason: collision with root package name */
    private FutureTask<Boolean> f1295h;

    /* loaded from: classes12.dex */
    class a implements Callable<Boolean> {
        final /* synthetic */ Context a;
        final /* synthetic */ GrsBaseInfo b;

        a(Context context, GrsBaseInfo grsBaseInfo) {
            this.a = context;
            this.b = grsBaseInfo;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.concurrent.Callable
        public Boolean call() {
            c.this.f1291c = new h();
            c.this.f1292e = new com.huawei.hms.framework.network.grs.e.c(this.a, GrsApp.getInstance().getBrand(CartConstant.KEY_YB_INFO_LINK) + "share_pre_grs_conf_");
            c.this.f1293f = new com.huawei.hms.framework.network.grs.e.c(this.a, GrsApp.getInstance().getBrand(CartConstant.KEY_YB_INFO_LINK) + "share_pre_grs_services_");
            c cVar = c.this;
            cVar.d = new com.huawei.hms.framework.network.grs.e.a(cVar.f1292e, c.this.f1293f, c.this.f1291c);
            c cVar2 = c.this;
            cVar2.f1294g = new com.huawei.hms.framework.network.grs.a(cVar2.a, c.this.d, c.this.f1291c, c.this.f1293f);
            if (c.f1290k.incrementAndGet() <= 2 || com.huawei.hms.framework.network.grs.f.b.a(this.a.getPackageName(), c.this.a) == null) {
                new com.huawei.hms.framework.network.grs.f.b(this.a, this.b, true).a(this.b);
            }
            String c2 = new com.huawei.hms.framework.network.grs.g.k.c(this.b, this.a).c();
            Logger.v(c.f1288i, "scan serviceSet is: " + c2);
            String a = c.this.f1293f.a(i2.d, "");
            String a2 = i.a(a, c2);
            if (!TextUtils.isEmpty(a2)) {
                c.this.f1293f.b(i2.d, a2);
                Logger.i(c.f1288i, "postList is:" + StringUtils.anonymizeMessage(a2));
                Logger.i(c.f1288i, "currentServices:" + StringUtils.anonymizeMessage(a));
                if (!a2.equals(a)) {
                    c.this.f1291c.a(c.this.a.getGrsParasKey(true, true, this.a));
                    c.this.f1291c.a(new com.huawei.hms.framework.network.grs.g.k.c(this.b, this.a), (String) null, c.this.f1293f);
                }
            }
            c cVar3 = c.this;
            cVar3.a(cVar3.f1292e.a());
            c.this.d.b(this.b, this.a);
            return Boolean.TRUE;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public c(Context context, GrsBaseInfo grsBaseInfo) {
        this.f1295h = null;
        this.b = context.getApplicationContext() != null ? context.getApplicationContext() : context;
        a(grsBaseInfo);
        GrsBaseInfo grsBaseInfo2 = this.a;
        FutureTask<Boolean> futureTask = new FutureTask<>(new a(this.b, grsBaseInfo2));
        this.f1295h = futureTask;
        f1289j.execute(futureTask);
        Logger.i(f1288i, "GrsClient Instance is init, GRS SDK version: %s, GrsBaseInfoParam: app_name=%s, reg_country=%s, ser_country=%s, issue_country=%s", com.huawei.hms.framework.network.grs.h.a.a(), grsBaseInfo2.getAppName(), grsBaseInfo.getRegCountry(), grsBaseInfo.getSerCountry(), grsBaseInfo.getIssueCountry());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public c(GrsBaseInfo grsBaseInfo) {
        this.f1295h = null;
        a(grsBaseInfo);
    }

    private void a(GrsBaseInfo grsBaseInfo) {
        try {
            this.a = grsBaseInfo.m16clone();
        } catch (CloneNotSupportedException e2) {
            Logger.w(f1288i, "GrsClient catch CloneNotSupportedException", e2);
            this.a = grsBaseInfo.copy();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Map<String, ?> map) {
        if (map == null || map.isEmpty()) {
            Logger.v(f1288i, "sp's content is empty.");
            return;
        }
        for (String str : map.keySet()) {
            if (str.endsWith("time")) {
                String a2 = this.f1292e.a(str, "");
                long j2 = 0;
                if (!TextUtils.isEmpty(a2) && a2.matches("\\d+")) {
                    try {
                        j2 = Long.parseLong(a2);
                    } catch (NumberFormatException e2) {
                        Logger.w(f1288i, "convert expire time from String to Long catch NumberFormatException.", e2);
                    }
                }
                if (!a(j2)) {
                    Logger.i(f1288i, "init interface auto clear some invalid sp's data.");
                    String substring = str.substring(0, str.length() - 4);
                    String str2 = substring + HttpHeaders.ETAG;
                    this.f1292e.a(substring);
                    this.f1292e.a(str);
                    this.f1292e.a(str2);
                }
            }
        }
    }

    private boolean a(long j2) {
        return System.currentTimeMillis() - j2 <= Final.SEV_DAY;
    }

    private boolean e() {
        String str;
        String str2;
        FutureTask<Boolean> futureTask = this.f1295h;
        if (futureTask == null) {
            return false;
        }
        try {
            return futureTask.get(8L, TimeUnit.SECONDS).booleanValue();
        } catch (InterruptedException e2) {
            e = e2;
            str = f1288i;
            str2 = "init compute task interrupted.";
            Logger.w(str, str2, e);
            return false;
        } catch (CancellationException unused) {
            Logger.i(f1288i, "init compute task canceled.");
            return false;
        } catch (ExecutionException e3) {
            e = e3;
            str = f1288i;
            str2 = "init compute task failed.";
            Logger.w(str, str2, e);
            return false;
        } catch (TimeoutException unused2) {
            Logger.w(f1288i, "init compute task timed out");
            return false;
        } catch (Exception e4) {
            e = e4;
            str = f1288i;
            str2 = "init compute task occur unknown Exception";
            Logger.w(str, str2, e);
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String a(String str, String str2) {
        if (this.a == null || str == null || str2 == null) {
            Logger.w(f1288i, "invalid para!");
            return null;
        } else if (e()) {
            return this.f1294g.a(str, str2, this.b);
        } else {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Map<String, String> a(String str) {
        if (this.a != null && str != null) {
            return e() ? this.f1294g.a(str, this.b) : new HashMap();
        }
        Logger.w(f1288i, "invalid para!");
        return new HashMap();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a() {
        if (e()) {
            String grsParasKey = this.a.getGrsParasKey(true, true, this.b);
            this.f1292e.a(grsParasKey);
            this.f1292e.a(grsParasKey + "time");
            this.f1292e.a(grsParasKey + HttpHeaders.ETAG);
            this.f1291c.a(grsParasKey);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(String str, IQueryUrlsCallBack iQueryUrlsCallBack) {
        if (iQueryUrlsCallBack == null) {
            Logger.w(f1288i, "IQueryUrlsCallBack is must not null for process continue.");
        } else if (this.a == null || str == null) {
            iQueryUrlsCallBack.onCallBackFail(-6);
        } else if (e()) {
            this.f1294g.a(str, iQueryUrlsCallBack, this.b);
        } else {
            Logger.i(f1288i, "grs init task has not completed.");
            iQueryUrlsCallBack.onCallBackFail(-7);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(String str, String str2, IQueryUrlCallBack iQueryUrlCallBack) {
        if (iQueryUrlCallBack == null) {
            Logger.w(f1288i, "IQueryUrlCallBack is must not null for process continue.");
        } else if (this.a == null || str == null || str2 == null) {
            iQueryUrlCallBack.onCallBackFail(-6);
        } else if (e()) {
            this.f1294g.a(str, str2, iQueryUrlCallBack, this.b);
        } else {
            Logger.i(f1288i, "grs init task has not completed.");
            iQueryUrlCallBack.onCallBackFail(-7);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean a(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && c.class == obj.getClass() && (obj instanceof c)) {
            return this.a.compare(((c) obj).a);
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean b() {
        GrsBaseInfo grsBaseInfo;
        Context context;
        if (!e() || (grsBaseInfo = this.a) == null || (context = this.b) == null) {
            return false;
        }
        this.d.a(grsBaseInfo, context);
        return true;
    }
}
