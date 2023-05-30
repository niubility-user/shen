package com.huawei.hms.hatool;

/* loaded from: classes12.dex */
public class l1 {
    private s0 a;
    private s0 b;

    /* renamed from: c  reason: collision with root package name */
    private s0 f1396c;
    private s0 d;

    public l1(String str) {
    }

    public s0 a() {
        return this.f1396c;
    }

    public s0 a(String str) {
        if (str.equals("oper")) {
            return c();
        }
        if (str.equals("maint")) {
            return b();
        }
        if (str.equals("diffprivacy")) {
            return a();
        }
        if (str.equals("preins")) {
            return d();
        }
        v.f("hmsSdk", "HiAnalyticsInstData.getConfig(type): wrong type: " + str);
        return null;
    }

    public void a(s0 s0Var) {
        this.a = s0Var;
    }

    public s0 b() {
        return this.a;
    }

    public void b(s0 s0Var) {
        this.b = s0Var;
    }

    public s0 c() {
        return this.b;
    }

    public s0 d() {
        return this.d;
    }
}
