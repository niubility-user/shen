package m.a.c;

import com.jingdong.sdk.platform.business.personal.R2;
import java.io.IOException;
import java.util.Arrays;

/* loaded from: classes11.dex */
public class e0 {

    /* renamed from: g  reason: collision with root package name */
    protected m.a.b.j f20333g;

    /* renamed from: h  reason: collision with root package name */
    protected boolean f20334h;

    /* renamed from: i  reason: collision with root package name */
    protected byte[] f20335i;

    public e0() {
        this.f20333g = null;
        this.f20334h = false;
        this.f20335i = null;
    }

    public void b(m.a.b.h hVar) throws IOException {
        if (this.f20333g != null) {
            if (this.f20335i != null) {
                m.a.b.h hVar2 = new m.a.b.h();
                hVar2.p(this.f20333g);
                boolean z = this.f20334h;
                if (z) {
                    hVar2.g(z);
                }
                hVar2.q(this.f20335i);
                hVar.y((byte) 48, hVar2);
                return;
            }
            throw new IOException("No value to encode for the extension!");
        }
        throw new IOException("Null OID to encode for the extension!");
    }

    public m.a.b.j c() {
        return this.f20333g;
    }

    public byte[] d() {
        return this.f20335i;
    }

    public boolean e() {
        return this.f20334h;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof e0) {
            e0 e0Var = (e0) obj;
            if (this.f20334h == e0Var.f20334h && this.f20333g.equals(e0Var.f20333g)) {
                return Arrays.equals(this.f20335i, e0Var.f20335i);
            }
            return false;
        }
        return false;
    }

    public int hashCode() {
        byte[] bArr = this.f20335i;
        int i2 = 0;
        if (bArr != null) {
            int length = bArr.length;
            while (length > 0) {
                int i3 = length - 1;
                i2 += length * bArr[i3];
                length = i3;
            }
        }
        return (((i2 * 31) + this.f20333g.hashCode()) * 31) + (this.f20334h ? R2.attr.layout_constraintWidth : R2.attr.layout_editor_absoluteX);
    }

    public String toString() {
        String str = "ObjectId: " + this.f20333g.toString();
        if (this.f20334h) {
            return str + " Criticality=true\n";
        }
        return str + " Criticality=false\n";
    }

    public e0(m.a.b.i iVar) throws IOException {
        this.f20333g = null;
        this.f20334h = false;
        this.f20335i = null;
        m.a.b.g E = iVar.E();
        this.f20333g = E.k();
        m.a.b.i e2 = E.e();
        if (e2.a == 1) {
            this.f20334h = e2.j();
            this.f20335i = E.e().q();
            return;
        }
        this.f20334h = false;
        this.f20335i = e2.q();
    }

    public e0(e0 e0Var) {
        this.f20333g = null;
        this.f20334h = false;
        this.f20335i = null;
        this.f20333g = e0Var.f20333g;
        this.f20334h = e0Var.f20334h;
        this.f20335i = e0Var.f20335i;
    }
}
