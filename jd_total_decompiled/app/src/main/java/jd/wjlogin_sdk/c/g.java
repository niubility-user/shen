package jd.wjlogin_sdk.c;

import java.util.ArrayList;
import java.util.List;
import jd.wjlogin_sdk.common.DevelopType;
import jd.wjlogin_sdk.net.b;

/* loaded from: classes.dex */
public class g extends i {

    /* renamed from: g  reason: collision with root package name */
    boolean f19716g;

    /* renamed from: h  reason: collision with root package name */
    int f19717h;

    /* renamed from: i  reason: collision with root package name */
    int f19718i;

    /* renamed from: j  reason: collision with root package name */
    byte[] f19719j;

    /* renamed from: k  reason: collision with root package name */
    String f19720k;

    /* renamed from: l  reason: collision with root package name */
    String f19721l;

    /* renamed from: m  reason: collision with root package name */
    boolean f19722m;

    /* renamed from: n  reason: collision with root package name */
    boolean f19723n;

    public g(f fVar) {
        super(fVar);
        this.f19716g = true;
        this.f19717h = 15000;
        this.f19723n = false;
        this.f19718i = 1;
        this.f19722m = true;
        this.f19720k = jd.wjlogin_sdk.util.e0.b.f(jd.wjlogin_sdk.util.e0.c.f19947i);
        this.f19721l = jd.wjlogin_sdk.util.e0.b.e(jd.wjlogin_sdk.util.e0.c.B);
    }

    public g a(boolean z) {
        this.f19723n = z;
        return this;
    }

    public g b(boolean z) {
        this.f19716g = z;
        return this;
    }

    public g a(byte[] bArr) {
        this.f19719j = bArr;
        return this;
    }

    public g b(String str) {
        this.f19720k = str;
        return this;
    }

    public g a(int i2) {
        this.f19718i = i2;
        return this;
    }

    public g b(int i2) {
        this.f19717h = i2;
        return this;
    }

    @Override // jd.wjlogin_sdk.c.i
    List<e> a() {
        ArrayList arrayList = new ArrayList();
        a(arrayList);
        return arrayList;
    }

    private void a(List<e> list) {
        if (this.f19716g && jd.wjlogin_sdk.config.a.c().l() && 1 != DevelopType.getDebugModel()) {
            list.add(new k(new b.C0849b().a(this.f19721l).c(this.f19717h).a(this.f19719j).a(this.f19718i).b(this.f19722m).d(2).a(this.f19723n).a(), this.d, this.f19732e));
        }
        list.add(new j(new b.C0849b().a(this.f19720k).c(this.f19717h).a(this.f19719j).a(this.f19718i).b(this.f19722m).a(this.f19723n).a(), this.d, this.f19732e));
    }
}
