package m.a.c;

import com.jd.dynamic.DYConstants;
import java.io.IOException;

/* loaded from: classes11.dex */
public class c0 implements g0 {

    /* renamed from: g  reason: collision with root package name */
    private String f20309g;

    /* renamed from: h  reason: collision with root package name */
    private String f20310h;

    /* renamed from: i  reason: collision with root package name */
    private int f20311i = -1;

    public c0(m.a.b.i iVar) throws IOException {
        this.f20309g = null;
        this.f20310h = null;
        m.a.b.i[] m2 = new m.a.b.g(iVar.D()).m(2);
        int length = m2.length;
        if (length < 1 || length > 2) {
            throw new IOException("Invalid encoding of EDIPartyName");
        }
        for (int i2 = 0; i2 < length; i2++) {
            m.a.b.i iVar2 = m2[i2];
            if (iVar2.z((byte) 0) && !iVar2.w()) {
                if (this.f20309g == null) {
                    iVar2 = iVar2.f20295c.e();
                    this.f20309g = iVar2.f();
                } else {
                    throw new IOException("Duplicate nameAssigner found in EDIPartyName");
                }
            }
            if (iVar2.z((byte) 1) && !iVar2.w()) {
                if (this.f20310h == null) {
                    this.f20310h = iVar2.f20295c.e().f();
                } else {
                    throw new IOException("Duplicate partyName found in EDIPartyName");
                }
            }
        }
    }

    @Override // m.a.c.g0
    public int a(g0 g0Var) throws UnsupportedOperationException {
        if (g0Var != null && g0Var.getType() == 5) {
            throw new UnsupportedOperationException("Narrowing, widening, and matching of names not supported for EDIPartyName");
        }
        return -1;
    }

    @Override // m.a.c.g0
    public void b(m.a.b.h hVar) throws IOException {
        m.a.b.h hVar2 = new m.a.b.h();
        m.a.b.h hVar3 = new m.a.b.h();
        if (this.f20309g != null) {
            m.a.b.h hVar4 = new m.a.b.h();
            hVar4.t(this.f20309g);
            hVar2.y(m.a.b.i.b(Byte.MIN_VALUE, false, (byte) 0), hVar4);
        }
        String str = this.f20310h;
        if (str != null) {
            hVar3.t(str);
            hVar2.y(m.a.b.i.b(Byte.MIN_VALUE, false, (byte) 1), hVar3);
            hVar.y((byte) 48, hVar2);
            return;
        }
        throw new IOException("Cannot have null partyName");
    }

    public boolean equals(Object obj) {
        if (obj instanceof c0) {
            c0 c0Var = (c0) obj;
            String str = c0Var.f20309g;
            String str2 = this.f20309g;
            if (str2 == null) {
                if (str != null) {
                    return false;
                }
            } else if (!str2.equals(str)) {
                return false;
            }
            String str3 = c0Var.f20310h;
            String str4 = this.f20310h;
            return str4 == null ? str3 == null : str4.equals(str3);
        }
        return false;
    }

    @Override // m.a.c.g0
    public int getType() {
        return 5;
    }

    public int hashCode() {
        if (this.f20311i == -1) {
            int hashCode = this.f20310h.hashCode() + 37;
            this.f20311i = hashCode;
            String str = this.f20309g;
            if (str != null) {
                this.f20311i = (hashCode * 37) + str.hashCode();
            }
        }
        return this.f20311i;
    }

    public String toString() {
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append("EDIPartyName: ");
        if (this.f20309g == null) {
            str = "";
        } else {
            str = "  nameAssigner = " + this.f20309g + DYConstants.DY_REGEX_COMMA;
        }
        sb.append(str);
        sb.append("  partyName = ");
        sb.append(this.f20310h);
        return sb.toString();
    }
}
