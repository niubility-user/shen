package com.meizu.cloud.pushsdk.f.f;

import android.content.Context;
import com.meizu.cloud.pushsdk.f.g.e;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes14.dex */
public class b {

    /* renamed from: j  reason: collision with root package name */
    private static final String f15936j = "b";
    private String a;
    private String b;

    /* renamed from: c  reason: collision with root package name */
    private String f15937c;
    private int d;

    /* renamed from: e  reason: collision with root package name */
    private final AtomicBoolean f15938e = new AtomicBoolean(false);

    /* renamed from: f  reason: collision with root package name */
    private long f15939f;

    /* renamed from: g  reason: collision with root package name */
    private final long f15940g;

    /* renamed from: h  reason: collision with root package name */
    private final long f15941h;

    /* renamed from: i  reason: collision with root package name */
    private final Context f15942i;

    public b(long j2, long j3, TimeUnit timeUnit, Context context) {
        this.b = null;
        this.d = 0;
        this.f15940g = timeUnit.toMillis(j2);
        this.f15941h = timeUnit.toMillis(j3);
        this.f15942i = context;
        Map c2 = c();
        if (c2 != null) {
            try {
                String obj = c2.get("userId").toString();
                String obj2 = c2.get("sessionId").toString();
                int intValue = ((Integer) c2.get("sessionIndex")).intValue();
                this.a = obj;
                this.d = intValue;
                this.b = obj2;
            } catch (Exception e2) {
                com.meizu.cloud.pushsdk.f.g.c.f(f15936j, "Exception occurred retrieving session info from file: %s", e2.getMessage());
            }
            g();
            f();
            com.meizu.cloud.pushsdk.f.g.c.g(f15936j, "Tracker Session Object created.", new Object[0]);
        }
        this.a = e.c();
        g();
        f();
        com.meizu.cloud.pushsdk.f.g.c.g(f15936j, "Tracker Session Object created.", new Object[0]);
    }

    private Map c() {
        return com.meizu.cloud.pushsdk.f.g.a.a("snowplow_session_vars", this.f15942i);
    }

    private boolean e() {
        return com.meizu.cloud.pushsdk.f.g.a.b("snowplow_session_vars", d(), this.f15942i);
    }

    private void f() {
        this.f15939f = System.currentTimeMillis();
    }

    private void g() {
        this.f15937c = this.b;
        this.b = e.c();
        this.d++;
        String str = f15936j;
        com.meizu.cloud.pushsdk.f.g.c.e(str, "Session information is updated:", new Object[0]);
        com.meizu.cloud.pushsdk.f.g.c.e(str, " + Session ID: %s", this.b);
        com.meizu.cloud.pushsdk.f.g.c.e(str, " + Previous Session ID: %s", this.f15937c);
        com.meizu.cloud.pushsdk.f.g.c.e(str, " + Session Index: %s", Integer.valueOf(this.d));
        e();
    }

    public void a() {
        com.meizu.cloud.pushsdk.f.g.c.e(f15936j, "Checking and updating session information.", new Object[0]);
        if (e.f(this.f15939f, System.currentTimeMillis(), this.f15938e.get() ? this.f15941h : this.f15940g)) {
            return;
        }
        g();
        f();
    }

    public com.meizu.cloud.pushsdk.f.b.b b() {
        com.meizu.cloud.pushsdk.f.g.c.g(f15936j, "Getting session context...", new Object[0]);
        f();
        return new com.meizu.cloud.pushsdk.f.b.b("client_session", d());
    }

    public Map d() {
        HashMap hashMap = new HashMap(8);
        hashMap.put("userId", this.a);
        hashMap.put("sessionId", this.b);
        hashMap.put("previousSessionId", this.f15937c);
        hashMap.put("sessionIndex", Integer.valueOf(this.d));
        hashMap.put("storageMechanism", "SQLITE");
        return hashMap;
    }
}
