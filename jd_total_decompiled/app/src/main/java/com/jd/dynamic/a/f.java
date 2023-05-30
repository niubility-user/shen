package com.jd.dynamic.a;

/* loaded from: classes13.dex */
public class f extends i implements d {

    /* renamed from: g */
    private d f1710g;

    public f(long j2, long j3) {
        super(j2, j3);
        this.f1710g = new b(j2, j3, -1L);
        o();
    }

    public static f r(long j2, long j3) {
        return new f(j2, j3);
    }

    @Override // com.jd.dynamic.a.d
    public Object a(Object... objArr) {
        if (d()) {
            return this.f1710g.a(objArr);
        }
        return null;
    }
}
