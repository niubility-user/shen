package c.t.m.g;

import com.jingdong.sdk.baseinfo.BaseInfo;

/* loaded from: classes.dex */
public class x {
    public String a;
    public String b;

    /* renamed from: c  reason: collision with root package name */
    public String f738c;

    public x(String str, String str2, String str3) {
        this.a = str2;
        this.b = str;
        this.f738c = str3;
    }

    public String a() {
        return b(this.f738c);
    }

    public final String b(String str) {
        return str == null ? "" : str;
    }

    public String c() {
        return b(this.b);
    }

    public String d() {
        return b(this.a);
    }

    public String e() {
        return b(BaseInfo.getDeviceManufacture());
    }

    public String f() {
        return b(z3.k());
    }

    public String g() {
        return "1.7.6";
    }
}
