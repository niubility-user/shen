package com.jingdong.app.mall.home.n.g.u;

import android.graphics.Rect;
import android.util.Pair;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.home.floor.model.entity.CategoryEntity;

/* loaded from: classes4.dex */
public abstract class c extends com.jingdong.app.mall.home.r.e.b {

    /* renamed from: j  reason: collision with root package name */
    public static int f10357j = 18;

    /* renamed from: k  reason: collision with root package name */
    public static int f10358k = 24;
    protected com.jingdong.app.mall.home.n.g.v.c a;
    private long b;

    /* renamed from: c  reason: collision with root package name */
    private int f10359c;
    private int d;

    /* renamed from: e  reason: collision with root package name */
    private Pair<Integer, Integer> f10360e;

    /* renamed from: f  reason: collision with root package name */
    private Rect f10361f;

    /* renamed from: g  reason: collision with root package name */
    private boolean f10362g;

    /* renamed from: h  reason: collision with root package name */
    protected com.jingdong.app.mall.home.n.a f10363h;

    /* renamed from: i  reason: collision with root package name */
    protected int f10364i;

    public c(JDJSONObject jDJSONObject, com.jingdong.app.mall.home.n.a aVar) {
        super(jDJSONObject);
        this.a = new com.jingdong.app.mall.home.n.g.v.c();
        this.f10361f = new Rect();
        this.f10363h = aVar;
        this.f10364i = aVar.getFloorHeight();
    }

    public boolean A() {
        return false;
    }

    public boolean B() {
        return true;
    }

    public Pair<Integer, Integer> a() {
        return this.f10360e;
    }

    public String b() {
        return null;
    }

    public long c() {
        return this.b;
    }

    public String d() {
        return null;
    }

    public com.jingdong.app.mall.home.n.g.v.c e() {
        return this.a;
    }

    public com.jingdong.app.mall.home.n.a f() {
        return this.f10363h;
    }

    public Rect g() {
        return this.f10361f;
    }

    public int getFloorHeight() {
        return this.f10364i;
    }

    public int h() {
        return this.d;
    }

    public int i() {
        return 0;
    }

    public String j() {
        return null;
    }

    public int k() {
        return this.f10359c;
    }

    public boolean l() {
        return this.f10362g;
    }

    public boolean m() {
        return false;
    }

    public boolean n() {
        return true;
    }

    protected abstract void o(com.jingdong.app.mall.home.n.g.v.c cVar);

    public void p(CategoryEntity.CaItem caItem) {
        int d = com.jingdong.app.mall.home.floor.common.d.d(f10358k);
        int d2 = com.jingdong.app.mall.home.floor.common.d.d(24 - f10357j);
        x(d2, 0, d2, 0);
        r(d);
        q();
        o(this.a);
    }

    protected abstract void q();

    protected void r(int i2) {
        s(i2, i2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void s(int i2, int i3) {
        this.f10360e = new Pair<>(Integer.valueOf(i2), Integer.valueOf(i3));
    }

    public void t(String str) {
    }

    public void u(long j2) {
        this.b = j2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void v(boolean z) {
        boolean z2 = z && this.f10363h.isFullSpan();
        this.f10362g = z2;
        if (z2) {
            x(0, 0, 0, 0);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void w(int i2) {
        this.f10361f.set(i2, i2, i2, i2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void x(int i2, int i3, int i4, int i5) {
        this.f10361f.set(i2, i3, i4, i5);
    }

    public void y(int i2) {
        this.d = i2;
    }

    public void z(int i2) {
        this.f10359c = i2;
    }
}
