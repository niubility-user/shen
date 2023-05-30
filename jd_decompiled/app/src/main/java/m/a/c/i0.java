package m.a.c;

import java.io.IOException;

/* loaded from: classes11.dex */
public class i0 {
    private f0 a;
    private int b;

    /* renamed from: c  reason: collision with root package name */
    private int f20357c;
    private int d;

    public void a(m.a.b.h hVar) throws IOException {
        m.a.b.h hVar2 = new m.a.b.h();
        this.a.a(hVar2);
        if (this.b != 0) {
            m.a.b.h hVar3 = new m.a.b.h();
            hVar3.k(this.b);
            hVar2.A(m.a.b.i.b(Byte.MIN_VALUE, false, (byte) 0), hVar3);
        }
        if (this.f20357c != -1) {
            m.a.b.h hVar4 = new m.a.b.h();
            hVar4.k(this.f20357c);
            hVar2.A(m.a.b.i.b(Byte.MIN_VALUE, false, (byte) 1), hVar4);
        }
        hVar.y((byte) 48, hVar2);
    }

    public boolean equals(Object obj) {
        if (obj instanceof i0) {
            i0 i0Var = (i0) obj;
            f0 f0Var = this.a;
            if (f0Var == null) {
                if (i0Var.a != null) {
                    return false;
                }
            } else if (!f0Var.equals(i0Var.a)) {
                return false;
            }
            return this.b == i0Var.b && this.f20357c == i0Var.f20357c;
        }
        return false;
    }

    public int hashCode() {
        if (this.d == -1) {
            this.d = 17;
            f0 f0Var = this.a;
            if (f0Var != null) {
                this.d = (17 * 37) + f0Var.hashCode();
            }
            int i2 = this.b;
            if (i2 != 0) {
                this.d = (this.d * 37) + i2;
            }
            int i3 = this.f20357c;
            if (i3 != -1) {
                this.d = (this.d * 37) + i3;
            }
        }
        return this.d;
    }

    public String toString() {
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append("\n   GeneralSubtree: [\n    GeneralName: ");
        f0 f0Var = this.a;
        sb.append(f0Var == null ? "" : f0Var.toString());
        sb.append("\n    Minimum: ");
        sb.append(this.b);
        String sb2 = sb.toString();
        if (this.f20357c == -1) {
            str = sb2 + "\t    Maximum: undefined";
        } else {
            str = sb2 + "\t    Maximum: " + this.f20357c;
        }
        return str + "    ]\n";
    }
}
