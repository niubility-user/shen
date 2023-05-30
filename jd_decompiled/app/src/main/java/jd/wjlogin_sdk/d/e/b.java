package jd.wjlogin_sdk.d.e;

import jd.wjlogin_sdk.tlvtype.a0;
import jd.wjlogin_sdk.tlvtype.d0;
import jd.wjlogin_sdk.tlvtype.e;
import jd.wjlogin_sdk.tlvtype.g;
import jd.wjlogin_sdk.tlvtype.l;
import jd.wjlogin_sdk.tlvtype.n0;
import jd.wjlogin_sdk.tlvtype.s0;
import jd.wjlogin_sdk.tlvtype.v;
import jd.wjlogin_sdk.tlvtype.x;
import jd.wjlogin_sdk.util.ByteUtil;
import jd.wjlogin_sdk.util.c0;

/* loaded from: classes11.dex */
public class b {
    private jd.wjlogin_sdk.d.c a;
    private a b;

    public b(byte[] bArr) {
        a aVar = new a(bArr);
        this.b = aVar;
        aVar.b();
    }

    public jd.wjlogin_sdk.d.c a() {
        jd.wjlogin_sdk.d.c cVar = new jd.wjlogin_sdk.d.c();
        this.a = cVar;
        cVar.c(this.b.i());
        this.a.a(this.b.h());
        this.a.b(this.b.g());
        this.a.c(this.b.g());
        this.a.a(this.b.g());
        this.a.b(this.b.i());
        this.a.e(this.b.i());
        this.a.a(this.b.i());
        this.a.f(this.b.i());
        this.a.a(this.b.d());
        return this.a;
    }

    public jd.wjlogin_sdk.tlvtype.a b() {
        jd.wjlogin_sdk.tlvtype.a aVar = new jd.wjlogin_sdk.tlvtype.a();
        if (274 == this.a.q()) {
            return a(aVar);
        }
        return b(aVar);
    }

    private jd.wjlogin_sdk.tlvtype.a b(jd.wjlogin_sdk.tlvtype.a aVar) {
        if (aVar == null) {
            aVar = new jd.wjlogin_sdk.tlvtype.a();
        }
        try {
            int l2 = this.b.l();
            int m2 = this.b.m();
            while (m2 < l2) {
                switch (this.b.i()) {
                    case 3:
                        short i2 = this.b.i();
                        x xVar = new x();
                        xVar.c(this.b.i());
                        xVar.a(this.b.i());
                        short i3 = this.b.i();
                        xVar.a(this.b.d(i3));
                        short i4 = this.b.i();
                        xVar.b(this.b.d(i4));
                        aVar.a(xVar);
                        short s = (short) (i3 + 8 + i4);
                        if (i2 <= s) {
                            break;
                        } else {
                            this.b.c(s);
                            break;
                        }
                    case 10:
                        n0 n0Var = new n0();
                        n0Var.a(jd.wjlogin_sdk.util.b.b(this.b.f()));
                        aVar.a(n0Var);
                        break;
                    case 14:
                        short i5 = this.b.i();
                        s0 s0Var = new s0();
                        s0Var.a(this.b.g());
                        s0Var.b(this.b.g());
                        if (i5 > 8) {
                            this.b.c(8);
                        }
                        aVar.a(s0Var);
                        break;
                    case 16:
                        jd.wjlogin_sdk.tlvtype.c cVar = new jd.wjlogin_sdk.tlvtype.c();
                        cVar.a(ByteUtil.parseByte2HexStr(this.b.f()));
                        aVar.a(cVar);
                        break;
                    case 17:
                        jd.wjlogin_sdk.tlvtype.d dVar = new jd.wjlogin_sdk.tlvtype.d();
                        dVar.a(this.b.f());
                        aVar.a(dVar);
                        break;
                    case 21:
                        short i6 = this.b.i();
                        e eVar = new e();
                        eVar.a(this.b.g());
                        aVar.a(eVar);
                        if (i6 <= 4) {
                            break;
                        } else {
                            this.b.c(i6 - 4);
                            break;
                        }
                    case 24:
                        g gVar = new g();
                        gVar.a(this.b.k());
                        aVar.a(gVar);
                        break;
                    case 30:
                        l lVar = new l();
                        lVar.a(this.b.k());
                        aVar.a(lVar);
                        break;
                    case 53:
                        v vVar = new v();
                        vVar.a(this.b.j());
                        aVar.a(vVar);
                        break;
                    case 55:
                        this.b.k();
                        break;
                    case 86:
                        a0 a0Var = new a0();
                        a0Var.a(this.b.k());
                        aVar.a(a0Var);
                        break;
                    case 92:
                        jd.wjlogin_sdk.tlvtype.b bVar = new jd.wjlogin_sdk.tlvtype.b();
                        short i7 = this.b.i();
                        bVar.a(this.b.g());
                        aVar.a(bVar);
                        if (i7 <= 4) {
                            break;
                        } else {
                            this.b.c(i7 - 4);
                            break;
                        }
                    case 114:
                        d0 d0Var = new d0();
                        short i8 = this.b.i();
                        d0Var.b(this.b.i());
                        d0Var.a(this.b.i());
                        aVar.a(d0Var);
                        if (i8 <= 4) {
                            break;
                        } else {
                            this.b.c(i8 - 4);
                            break;
                        }
                    default:
                        a aVar2 = this.b;
                        aVar2.c(aVar2.i());
                        break;
                }
                m2 = this.b.m();
            }
            return aVar;
        } catch (Exception unused) {
            c0.a((short) 258, "getOldproTlv_Exception");
            return aVar;
        }
    }

    private jd.wjlogin_sdk.tlvtype.a a(jd.wjlogin_sdk.tlvtype.a aVar) {
        if (aVar == null) {
            aVar = new jd.wjlogin_sdk.tlvtype.a();
        }
        try {
            int l2 = this.b.l();
            int m2 = this.b.m();
            while (m2 < l2) {
                switch (this.b.i()) {
                    case 3:
                        int g2 = this.b.g();
                        x xVar = new x();
                        xVar.c(this.b.i());
                        xVar.a(this.b.i());
                        short i2 = this.b.i();
                        xVar.a(this.b.d(i2));
                        short i3 = this.b.i();
                        xVar.b(this.b.d(i3));
                        aVar.a(xVar);
                        int i4 = i2 + i3 + 8;
                        if (g2 <= i4) {
                            break;
                        } else {
                            this.b.c(g2 - i4);
                            break;
                        }
                    case 10:
                        n0 n0Var = new n0();
                        n0Var.a(jd.wjlogin_sdk.util.b.b(this.b.e()));
                        aVar.a(n0Var);
                        break;
                    case 14:
                        int g3 = this.b.g();
                        s0 s0Var = new s0();
                        s0Var.a(this.b.g());
                        s0Var.b(this.b.g());
                        aVar.a(s0Var);
                        if (g3 <= 8) {
                            break;
                        } else {
                            this.b.c(g3 - 8);
                            break;
                        }
                    case 16:
                        jd.wjlogin_sdk.tlvtype.c cVar = new jd.wjlogin_sdk.tlvtype.c();
                        cVar.a(ByteUtil.parseByte2HexStr(this.b.e()));
                        aVar.a(cVar);
                        break;
                    case 17:
                        jd.wjlogin_sdk.tlvtype.d dVar = new jd.wjlogin_sdk.tlvtype.d();
                        dVar.a(this.b.e());
                        aVar.a(dVar);
                        break;
                    case 21:
                        int g4 = this.b.g();
                        e eVar = new e();
                        eVar.a(this.b.g());
                        aVar.a(eVar);
                        if (g4 <= 4) {
                            break;
                        } else {
                            this.b.c(g4 - 4);
                            break;
                        }
                    case 24:
                        g gVar = new g();
                        gVar.a(this.b.j());
                        aVar.a(gVar);
                        break;
                    case 30:
                        l lVar = new l();
                        lVar.a(this.b.j());
                        aVar.a(lVar);
                        break;
                    case 53:
                        v vVar = new v();
                        vVar.a(this.b.j());
                        aVar.a(vVar);
                        break;
                    case 55:
                        this.b.j();
                        break;
                    case 86:
                        a0 a0Var = new a0();
                        a0Var.a(this.b.j());
                        aVar.a(a0Var);
                        break;
                    case 92:
                        jd.wjlogin_sdk.tlvtype.b bVar = new jd.wjlogin_sdk.tlvtype.b();
                        int g5 = this.b.g();
                        bVar.a(this.b.g());
                        aVar.a(bVar);
                        if (g5 <= 4) {
                            break;
                        } else {
                            this.b.c(g5 - 4);
                            break;
                        }
                    case 114:
                        d0 d0Var = new d0();
                        int g6 = this.b.g();
                        d0Var.b(this.b.i());
                        d0Var.a(this.b.i());
                        aVar.a(d0Var);
                        if (g6 <= 4) {
                            break;
                        } else {
                            this.b.c(g6 - 4);
                            break;
                        }
                    default:
                        a aVar2 = this.b;
                        aVar2.c(aVar2.g());
                        break;
                }
                m2 = this.b.m();
            }
            return aVar;
        } catch (Exception e2) {
            e2.printStackTrace();
            c0.a((short) jd.wjlogin_sdk.util.d.e0, "getNewproTlv_Exception");
            return aVar;
        }
    }
}
