package jd.wjlogin_sdk.c;

import java.util.ArrayList;
import java.util.List;
import jd.wjlogin_sdk.net.b;

/* loaded from: classes.dex */
public class h extends i {

    /* renamed from: g  reason: collision with root package name */
    boolean f19724g;

    /* renamed from: h  reason: collision with root package name */
    int f19725h;

    /* renamed from: i  reason: collision with root package name */
    int f19726i;

    /* renamed from: j  reason: collision with root package name */
    byte[] f19727j;

    /* renamed from: k  reason: collision with root package name */
    String f19728k;

    /* renamed from: l  reason: collision with root package name */
    boolean f19729l;

    public h(f fVar) {
        super(fVar);
        this.f19724g = true;
        this.f19725h = 15000;
        this.f19726i = 1;
        this.f19729l = true;
        this.f19728k = jd.wjlogin_sdk.util.e0.b.d(jd.wjlogin_sdk.util.e0.c.f19947i);
    }

    public h a(byte[] bArr) {
        this.f19727j = bArr;
        return this;
    }

    public h b(int i2) {
        this.f19725h = i2;
        return this;
    }

    public h a(int i2) {
        this.f19726i = i2;
        return this;
    }

    public h a(boolean z) {
        this.f19724g = z;
        return this;
    }

    @Override // jd.wjlogin_sdk.c.i
    List<e> a() {
        ArrayList arrayList = new ArrayList();
        a(arrayList);
        return arrayList;
    }

    private void a(List<e> list) {
        list.add(new j(new b.C0849b().a(this.f19728k).c(this.f19725h).a(this.f19727j).a(this.f19726i).b(this.f19729l).a(), this.d, this.f19732e));
    }
}
