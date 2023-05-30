package com.huawei.hms.hatool;

import java.util.ArrayList;
import java.util.UUID;

/* loaded from: classes12.dex */
public class i1 {
    private String a;
    private String b;

    /* renamed from: c */
    private String f1380c;
    private String d;

    /* renamed from: e */
    private long f1381e;

    public i1(String str, String str2, String str3, String str4, long j2) {
        this.a = str;
        this.b = str2;
        this.f1380c = str3;
        this.d = str4;
        this.f1381e = j2;
    }

    public void a() {
        v.c("StreamEventHandler", "Begin to handle stream events...");
        b1 b1Var = new b1();
        b1Var.b(this.f1380c);
        b1Var.d(this.b);
        b1Var.a(this.d);
        b1Var.c(String.valueOf(this.f1381e));
        if ("oper".equals(this.b) && z.i(this.a, "oper")) {
            p0 a = y.a().a(this.a, this.f1381e);
            String a2 = a.a();
            Boolean valueOf = Boolean.valueOf(a.b());
            b1Var.f(a2);
            b1Var.e(String.valueOf(valueOf));
        }
        String replace = UUID.randomUUID().toString().replace("-", "");
        ArrayList arrayList = new ArrayList();
        arrayList.add(b1Var);
        new l0(this.a, this.b, q0.g(), arrayList, replace).a();
    }
}
