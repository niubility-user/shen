package com.huawei.hms.framework.network.grs.g;

import android.content.Context;
import android.net.Uri;
import com.huawei.hms.framework.network.grs.GrsBaseInfo;
import java.util.concurrent.Callable;

/* loaded from: classes12.dex */
public class a {
    protected d a;
    private final String b;

    /* renamed from: c */
    private final c f1306c;
    private final int d;

    /* renamed from: e */
    private final Context f1307e;

    /* renamed from: f */
    private final String f1308f;

    /* renamed from: g */
    private final GrsBaseInfo f1309g;

    /* renamed from: h */
    private final com.huawei.hms.framework.network.grs.e.c f1310h;

    /* renamed from: com.huawei.hms.framework.network.grs.g.a$a */
    /* loaded from: classes12.dex */
    public enum EnumC0062a {
        GRSPOST,
        GRSGET,
        GRSDEFAULT
    }

    public a(String str, int i2, c cVar, Context context, String str2, GrsBaseInfo grsBaseInfo, com.huawei.hms.framework.network.grs.e.c cVar2) {
        this.b = str;
        this.f1306c = cVar;
        this.d = i2;
        this.f1307e = context;
        this.f1308f = str2;
        this.f1309g = grsBaseInfo;
        this.f1310h = cVar2;
    }

    private String a(String str) {
        return Uri.parse(str).getPath();
    }

    private EnumC0062a h() {
        if (this.b.isEmpty()) {
            return EnumC0062a.GRSDEFAULT;
        }
        String a = a(this.b);
        return a.contains("1.0") ? EnumC0062a.GRSGET : a.contains("2.0") ? EnumC0062a.GRSPOST : EnumC0062a.GRSDEFAULT;
    }

    public Context a() {
        return this.f1307e;
    }

    public c b() {
        return this.f1306c;
    }

    public String c() {
        return this.b;
    }

    public int d() {
        return this.d;
    }

    public String e() {
        return this.f1308f;
    }

    public com.huawei.hms.framework.network.grs.e.c f() {
        return this.f1310h;
    }

    public Callable<d> g() {
        if (EnumC0062a.GRSDEFAULT.equals(h())) {
            return null;
        }
        return EnumC0062a.GRSGET.equals(h()) ? new f(this.b, this.d, this.f1306c, this.f1307e, this.f1308f, this.f1309g) : new g(this.b, this.d, this.f1306c, this.f1307e, this.f1308f, this.f1309g, this.f1310h);
    }
}
