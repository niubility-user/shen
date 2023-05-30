package com.huawei.hms.hatool;

import android.content.Context;

/* loaded from: classes12.dex */
public class b {
    s0 a;
    s0 b;

    /* renamed from: c  reason: collision with root package name */
    Context f1351c;
    String d;

    public b(Context context) {
        if (context != null) {
            this.f1351c = context.getApplicationContext();
        }
        this.a = new s0();
        this.b = new s0();
    }

    public b a(int i2, String str) {
        s0 s0Var;
        v.c("hmsSdk", "Builder.setCollectURL(int type,String collectURL) is execute.TYPE : " + i2);
        if (!p1.b(str)) {
            str = "";
        }
        if (i2 == 0) {
            s0Var = this.a;
        } else if (i2 != 1) {
            v.f("hmsSdk", "Builder.setCollectURL(int type,String collectURL): invalid type!");
            return this;
        } else {
            s0Var = this.b;
        }
        s0Var.b(str);
        return this;
    }

    public b a(String str) {
        v.c("hmsSdk", "Builder.setAppID is execute");
        this.d = str;
        return this;
    }

    @Deprecated
    public b a(boolean z) {
        v.c("hmsSdk", "Builder.setEnableImei(boolean isReportAndroidImei) is execute.");
        this.a.j().a(z);
        this.b.j().a(z);
        return this;
    }

    public void a() {
        if (this.f1351c == null) {
            v.b("hmsSdk", "analyticsConf create(): context is null,create failed!");
            return;
        }
        v.c("hmsSdk", "Builder.create() is execute.");
        z0 z0Var = new z0("_hms_config_tag");
        z0Var.b(new s0(this.a));
        z0Var.a(new s0(this.b));
        m.a().a(this.f1351c);
        g0.a().a(this.f1351c);
        q.c().a(z0Var);
        m.a().a(this.d);
    }

    @Deprecated
    public b b(boolean z) {
        v.c("hmsSdk", "Builder.setEnableSN(boolean isReportSN) is execute.");
        this.a.j().b(z);
        this.b.j().b(z);
        return this;
    }

    @Deprecated
    public b c(boolean z) {
        v.c("hmsSdk", "Builder.setEnableUDID(boolean isReportUDID) is execute.");
        this.a.j().c(z);
        this.b.j().c(z);
        return this;
    }
}
