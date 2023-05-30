package m.a.c;

import java.io.IOException;

/* loaded from: classes11.dex */
public final class d {
    public static final m.a.b.j d = m.a.b.j.newInternal(new int[]{1, 3, 6, 1, 5, 5, 7, 48, 1});

    /* renamed from: e  reason: collision with root package name */
    public static final m.a.b.j f20316e = m.a.b.j.newInternal(new int[]{1, 3, 6, 1, 5, 5, 7, 48, 2});

    /* renamed from: f  reason: collision with root package name */
    public static final m.a.b.j f20317f = m.a.b.j.newInternal(new int[]{1, 3, 6, 1, 5, 5, 7, 48, 3});

    /* renamed from: g  reason: collision with root package name */
    public static final m.a.b.j f20318g = m.a.b.j.newInternal(new int[]{1, 3, 6, 1, 5, 5, 7, 48, 5});
    private int a;
    private m.a.b.j b;

    /* renamed from: c  reason: collision with root package name */
    private f0 f20319c;

    public void a(m.a.b.h hVar) throws IOException {
        m.a.b.h hVar2 = new m.a.b.h();
        hVar2.p(this.b);
        this.f20319c.a(hVar2);
        hVar.y((byte) 48, hVar2);
    }

    public f0 b() {
        return this.f20319c;
    }

    public m.a.b.j c() {
        return this.b;
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof d)) {
            return false;
        }
        d dVar = (d) obj;
        if (this == dVar) {
            return true;
        }
        return this.b.equals(dVar.c()) && this.f20319c.equals(dVar.b());
    }

    public int hashCode() {
        if (this.a == -1) {
            this.a = this.b.hashCode() + this.f20319c.hashCode();
        }
        return this.a;
    }

    public String toString() {
        String jVar;
        if (this.b.equals(f20316e)) {
            jVar = "caIssuers";
        } else if (this.b.equals(f20318g)) {
            jVar = "caRepository";
        } else if (this.b.equals(f20317f)) {
            jVar = "timeStamping";
        } else {
            jVar = this.b.equals(d) ? "ocsp" : this.b.toString();
        }
        return "\n   accessMethod: " + jVar + "\n   accessLocation: " + this.f20319c.toString();
    }
}
