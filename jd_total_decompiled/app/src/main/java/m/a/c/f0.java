package m.a.c;

import java.io.IOException;

/* loaded from: classes11.dex */
public class f0 {
    private g0 a;

    public f0(m.a.b.i iVar) throws IOException {
        this(iVar, false);
    }

    public void a(m.a.b.h hVar) throws IOException {
        m.a.b.h hVar2 = new m.a.b.h();
        this.a.b(hVar2);
        int type = this.a.getType();
        if (type == 0 || type == 3 || type == 5) {
            hVar.A(m.a.b.i.b(Byte.MIN_VALUE, true, (byte) type), hVar2);
        } else if (type == 4) {
            hVar.y(m.a.b.i.b(Byte.MIN_VALUE, true, (byte) type), hVar2);
        } else {
            hVar.A(m.a.b.i.b(Byte.MIN_VALUE, false, (byte) type), hVar2);
        }
    }

    public g0 b() {
        return this.a;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof f0) {
            try {
                return this.a.a(((f0) obj).a) == 0;
            } catch (UnsupportedOperationException unused) {
                return false;
            }
        }
        return false;
    }

    public int hashCode() {
        return this.a.hashCode();
    }

    public String toString() {
        return this.a.toString();
    }

    public f0(m.a.b.i iVar, boolean z) throws IOException {
        this.a = null;
        short s = (byte) (iVar.a & 31);
        switch (s) {
            case 0:
                if (iVar.y() && iVar.w()) {
                    iVar.C((byte) 48);
                    this.a = new r0(iVar);
                    return;
                }
                throw new IOException("Invalid encoding of Other-Name");
            case 1:
                if (iVar.y() && !iVar.w()) {
                    iVar.C((byte) 22);
                    this.a = new y0(iVar);
                    return;
                }
                throw new IOException("Invalid encoding of RFC822 name");
            case 2:
                if (iVar.y() && !iVar.w()) {
                    iVar.C((byte) 22);
                    this.a = new a0(iVar);
                    return;
                }
                throw new IOException("Invalid encoding of DNS name");
            case 3:
            default:
                throw new IOException("Unrecognized GeneralName tag, (" + ((int) s) + ")");
            case 4:
                if (iVar.y() && iVar.w()) {
                    this.a = new f1(iVar.k());
                    return;
                }
                throw new IOException("Invalid encoding of Directory name");
            case 5:
                if (iVar.y() && iVar.w()) {
                    iVar.C((byte) 48);
                    this.a = new c0(iVar);
                    return;
                }
                throw new IOException("Invalid encoding of EDI name");
            case 6:
                if (iVar.y() && !iVar.w()) {
                    iVar.C((byte) 22);
                    this.a = z ? c1.g(iVar) : new c1(iVar);
                    return;
                }
                throw new IOException("Invalid encoding of URI");
            case 7:
                if (iVar.y() && !iVar.w()) {
                    iVar.C((byte) 4);
                    this.a = new k0(iVar);
                    return;
                }
                throw new IOException("Invalid encoding of IP address");
            case 8:
                if (iVar.y() && !iVar.w()) {
                    iVar.C((byte) 6);
                    this.a = new q0(iVar);
                    return;
                }
                throw new IOException("Invalid encoding of OID name");
        }
    }
}
