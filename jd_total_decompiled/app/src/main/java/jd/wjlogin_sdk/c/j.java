package jd.wjlogin_sdk.c;

import jd.wjlogin_sdk.net.NetworkException;

/* loaded from: classes11.dex */
public class j extends a {
    public j(jd.wjlogin_sdk.net.c cVar, short s, short s2) {
        super(cVar, s, s2);
        this.a = new b();
    }

    @Override // jd.wjlogin_sdk.c.e
    public int b() {
        try {
            this.f19713c = this.b.a(this);
            this.d = e();
            return ((Integer) this.f19713c.first).intValue();
        } catch (Throwable th) {
            th.printStackTrace();
            return th instanceof NetworkException ? a(th) : jd.wjweblogin.d.c.f20052g;
        }
    }

    @Override // jd.wjlogin_sdk.c.e
    public String c() {
        return "V1Executor";
    }
}
