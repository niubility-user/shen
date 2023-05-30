package k.a.a.f;

import java.util.List;

/* loaded from: classes11.dex */
public abstract class b extends m {
    private byte[] a;
    private k.a.a.f.o.c b;

    /* renamed from: c  reason: collision with root package name */
    private long f20241c;

    /* renamed from: e  reason: collision with root package name */
    private byte[] f20242e;

    /* renamed from: h  reason: collision with root package name */
    private int f20245h;

    /* renamed from: i  reason: collision with root package name */
    private String f20246i;

    /* renamed from: j  reason: collision with root package name */
    private boolean f20247j;

    /* renamed from: l  reason: collision with root package name */
    private boolean f20249l;

    /* renamed from: m  reason: collision with root package name */
    private l f20250m;

    /* renamed from: n  reason: collision with root package name */
    private a f20251n;
    private boolean o;
    private List<g> p;
    private boolean q;
    private long d = 0;

    /* renamed from: f  reason: collision with root package name */
    private long f20243f = 0;

    /* renamed from: g  reason: collision with root package name */
    private long f20244g = 0;

    /* renamed from: k  reason: collision with root package name */
    private k.a.a.f.o.d f20248k = k.a.a.f.o.d.NONE;

    public void A(k.a.a.f.o.d dVar) {
        this.f20248k = dVar;
    }

    public void B(List<g> list) {
        this.p = list;
    }

    public void C(int i2) {
        this.f20245h = i2;
    }

    public void D(String str) {
        this.f20246i = str;
    }

    public void E(int i2) {
    }

    public void F(boolean z) {
        this.o = z;
    }

    public void G(byte[] bArr) {
        this.a = bArr;
    }

    public void H(long j2) {
        this.f20241c = j2;
    }

    public void I(long j2) {
        this.f20244g = j2;
    }

    public void J(int i2) {
    }

    public void K(l lVar) {
        this.f20250m = lVar;
    }

    public a b() {
        return this.f20251n;
    }

    public long c() {
        return this.f20243f;
    }

    public k.a.a.f.o.c d() {
        return this.b;
    }

    public long e() {
        return this.d;
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof b)) {
            return j().equals(((b) obj).j());
        }
        return false;
    }

    public byte[] f() {
        return this.f20242e;
    }

    public k.a.a.f.o.d g() {
        return this.f20248k;
    }

    public List<g> h() {
        return this.p;
    }

    public int i() {
        return this.f20245h;
    }

    public String j() {
        return this.f20246i;
    }

    public byte[] k() {
        return this.a;
    }

    public long l() {
        return this.f20241c;
    }

    public long m() {
        return this.f20244g;
    }

    public l n() {
        return this.f20250m;
    }

    public boolean o() {
        return this.f20249l;
    }

    public boolean p() {
        return this.q;
    }

    public boolean q() {
        return this.f20247j;
    }

    public boolean r() {
        return this.o;
    }

    public void s(a aVar) {
        this.f20251n = aVar;
    }

    public void t(long j2) {
        this.f20243f = j2;
    }

    public void u(k.a.a.f.o.c cVar) {
        this.b = cVar;
    }

    public void v(long j2) {
        this.d = j2;
    }

    public void w(byte[] bArr) {
        this.f20242e = bArr;
    }

    public void x(boolean z) {
        this.f20249l = z;
    }

    public void y(boolean z) {
        this.q = z;
    }

    public void z(boolean z) {
        this.f20247j = z;
    }
}
