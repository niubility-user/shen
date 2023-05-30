package jd.wjlogin_sdk.d;

import com.jingdong.sdk.platform.business.personal.R2;
import java.util.ArrayList;
import jd.wjlogin_sdk.tlvtype.a0;
import jd.wjlogin_sdk.tlvtype.b0;
import jd.wjlogin_sdk.tlvtype.d0;
import jd.wjlogin_sdk.tlvtype.e;
import jd.wjlogin_sdk.tlvtype.f;
import jd.wjlogin_sdk.tlvtype.f0;
import jd.wjlogin_sdk.tlvtype.g;
import jd.wjlogin_sdk.tlvtype.g0;
import jd.wjlogin_sdk.tlvtype.h;
import jd.wjlogin_sdk.tlvtype.h0;
import jd.wjlogin_sdk.tlvtype.i;
import jd.wjlogin_sdk.tlvtype.j0;
import jd.wjlogin_sdk.tlvtype.k;
import jd.wjlogin_sdk.tlvtype.k0;
import jd.wjlogin_sdk.tlvtype.l;
import jd.wjlogin_sdk.tlvtype.l0;
import jd.wjlogin_sdk.tlvtype.m;
import jd.wjlogin_sdk.tlvtype.m0;
import jd.wjlogin_sdk.tlvtype.n;
import jd.wjlogin_sdk.tlvtype.n0;
import jd.wjlogin_sdk.tlvtype.o;
import jd.wjlogin_sdk.tlvtype.o0;
import jd.wjlogin_sdk.tlvtype.p;
import jd.wjlogin_sdk.tlvtype.p0;
import jd.wjlogin_sdk.tlvtype.q;
import jd.wjlogin_sdk.tlvtype.q0;
import jd.wjlogin_sdk.tlvtype.r;
import jd.wjlogin_sdk.tlvtype.r0;
import jd.wjlogin_sdk.tlvtype.s;
import jd.wjlogin_sdk.tlvtype.s0;
import jd.wjlogin_sdk.tlvtype.t;
import jd.wjlogin_sdk.tlvtype.t0;
import jd.wjlogin_sdk.tlvtype.u;
import jd.wjlogin_sdk.tlvtype.u0;
import jd.wjlogin_sdk.tlvtype.v;
import jd.wjlogin_sdk.tlvtype.w;
import jd.wjlogin_sdk.tlvtype.x;
import jd.wjlogin_sdk.tlvtype.y;
import jd.wjlogin_sdk.tlvtype.z;
import jd.wjlogin_sdk.util.ByteUtil;
import jd.wjlogin_sdk.util.c0;

/* loaded from: classes.dex */
public class a {
    private c a;
    private jd.wjlogin_sdk.util.c b;

    public a(byte[] bArr) {
        jd.wjlogin_sdk.util.c cVar = new jd.wjlogin_sdk.util.c(bArr);
        this.b = cVar;
        cVar.b();
    }

    public c a() {
        c cVar = new c();
        this.a = cVar;
        cVar.d(this.b.h());
        this.a.b(this.b.g());
        this.a.b(this.b.f());
        this.a.c(this.b.f());
        this.a.a(this.b.f());
        this.a.b(this.b.h());
        this.a.e(this.b.h());
        this.a.a(this.b.h());
        this.a.f(this.b.h());
        this.a.a(this.b.d());
        return this.a;
    }

    public jd.wjlogin_sdk.tlvtype.a b() {
        jd.wjlogin_sdk.tlvtype.a aVar = new jd.wjlogin_sdk.tlvtype.a();
        try {
            int j2 = this.b.j();
            int k2 = this.b.k();
            ArrayList arrayList = null;
            while (k2 < j2) {
                switch (this.b.h()) {
                    case 3:
                        short h2 = this.b.h();
                        x xVar = new x();
                        xVar.c(this.b.h());
                        xVar.a(this.b.h());
                        short h3 = this.b.h();
                        xVar.a(this.b.f(h3));
                        short h4 = this.b.h();
                        xVar.b(this.b.f(h4));
                        short s = (short) (h3 + 8 + h4);
                        if (h2 > s) {
                            this.b.c(h2 - s);
                        }
                        aVar.a(xVar);
                        break;
                    case 10:
                        n0 n0Var = new n0();
                        n0Var.a(jd.wjlogin_sdk.util.b.b(this.b.e()));
                        aVar.a(n0Var);
                        break;
                    case 14:
                        short h5 = this.b.h();
                        s0 s0Var = new s0();
                        s0Var.a(this.b.f());
                        s0Var.b(this.b.f());
                        aVar.a(s0Var);
                        if (h5 <= 8) {
                            break;
                        } else {
                            this.b.c(h5 - 8);
                            break;
                        }
                    case 15:
                        short h6 = this.b.h();
                        u0 u0Var = new u0();
                        short h7 = this.b.h();
                        u0Var.a(this.b.f(h7));
                        short h8 = this.b.h();
                        u0Var.a(this.b.c((int) h8));
                        aVar.a(u0Var);
                        short s2 = (short) (h7 + h8 + 4);
                        if (h6 <= s2) {
                            break;
                        } else {
                            this.b.c(h6 - s2);
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
                        short h9 = this.b.h();
                        e eVar = new e();
                        eVar.a(this.b.f());
                        aVar.a(eVar);
                        if (h9 <= 4) {
                            break;
                        } else {
                            this.b.c(h9 - 4);
                            break;
                        }
                    case 23:
                        f fVar = new f();
                        fVar.a(this.b.i());
                        aVar.a(fVar);
                        break;
                    case 24:
                        g gVar = new g();
                        gVar.a(this.b.i());
                        aVar.a(gVar);
                        break;
                    case 25:
                        h hVar = new h();
                        hVar.a(this.b.i());
                        aVar.a(hVar);
                        break;
                    case 27:
                        short h10 = this.b.h();
                        i iVar = new i();
                        iVar.a(this.b.f());
                        aVar.a(iVar);
                        if (h10 <= 4) {
                            break;
                        } else {
                            this.b.c(h10 - 4);
                            break;
                        }
                    case 29:
                        short h11 = this.b.h();
                        k kVar = new k();
                        short h12 = this.b.h();
                        kVar.a(this.b.f(h12));
                        kVar.a(this.b.f());
                        short h13 = this.b.h();
                        kVar.c(this.b.f(h13));
                        short h14 = this.b.h();
                        kVar.b(this.b.f(h14));
                        short h15 = this.b.h();
                        kVar.d(this.b.f(h15));
                        kVar.a(this.b.d());
                        aVar.a(kVar);
                        short s3 = (short) (h12 + h13 + h14 + h15 + 13);
                        if (h11 <= s3) {
                            break;
                        } else {
                            this.b.c(h11 - s3);
                            break;
                        }
                    case 30:
                        l lVar = new l();
                        lVar.a(this.b.i());
                        aVar.a(lVar);
                        break;
                    case 34:
                        short h16 = this.b.h();
                        m mVar = new m();
                        mVar.a(this.b.d());
                        mVar.a(this.b.f());
                        aVar.a(mVar);
                        if (h16 <= 5) {
                            break;
                        } else {
                            this.b.c(h16 - 5);
                            break;
                        }
                    case 35:
                        n nVar = new n();
                        nVar.a(this.b.e());
                        aVar.a(nVar);
                        break;
                    case 36:
                        o oVar = new o();
                        oVar.a(this.b.i());
                        aVar.a(oVar);
                        break;
                    case 38:
                        p pVar = new p();
                        pVar.a(this.b.i());
                        aVar.a(pVar);
                        break;
                    case 41:
                        q qVar = new q();
                        qVar.a(this.b.i());
                        aVar.a(qVar);
                        break;
                    case 46:
                        r rVar = new r();
                        rVar.a(this.b.i());
                        aVar.a(rVar);
                        break;
                    case 49:
                        s sVar = new s();
                        sVar.a(this.b.i());
                        aVar.a(sVar);
                        break;
                    case 50:
                        t tVar = new t();
                        tVar.a(this.b.i());
                        aVar.a(tVar);
                        break;
                    case 51:
                        u uVar = new u();
                        uVar.a(this.b.i());
                        aVar.a(uVar);
                        break;
                    case 53:
                        v vVar = new v();
                        vVar.a(this.b.i());
                        aVar.a(vVar);
                        break;
                    case 55:
                        w wVar = new w();
                        wVar.a(this.b.i());
                        aVar.a(wVar);
                        break;
                    case 77:
                        y yVar = new y();
                        yVar.a(this.b.i());
                        aVar.a(yVar);
                        break;
                    case 78:
                        z zVar = new z();
                        zVar.a(this.b.i());
                        aVar.a(zVar);
                        break;
                    case 86:
                        a0 a0Var = new a0();
                        a0Var.a(this.b.i());
                        aVar.a(a0Var);
                        break;
                    case 92:
                        jd.wjlogin_sdk.tlvtype.b bVar = new jd.wjlogin_sdk.tlvtype.b();
                        short h17 = this.b.h();
                        bVar.a(this.b.f());
                        aVar.a(bVar);
                        if (h17 <= 4) {
                            break;
                        } else {
                            this.b.c(h17 - 4);
                            break;
                        }
                    case 93:
                        b0 b0Var = new b0();
                        b0Var.a(this.b.i());
                        aVar.a(b0Var);
                        break;
                    case 114:
                        d0 d0Var = new d0();
                        short h18 = this.b.h();
                        d0Var.b(this.b.h());
                        d0Var.a(this.b.h());
                        aVar.a(d0Var);
                        if (h18 <= 4) {
                            break;
                        } else {
                            this.b.c(h18 - 4);
                            break;
                        }
                    case 116:
                        f0 f0Var = new f0();
                        short h19 = this.b.h();
                        f0Var.a(this.b.h());
                        aVar.a(f0Var);
                        if (h19 <= 2) {
                            break;
                        } else {
                            this.b.c(h19 - 2);
                            break;
                        }
                    case 119:
                        g0 g0Var = new g0();
                        g0Var.a(this.b.i());
                        aVar.a(g0Var);
                        break;
                    case 120:
                        if (arrayList == null) {
                            arrayList = new ArrayList();
                        }
                        short h20 = this.b.h();
                        h0 h0Var = new h0();
                        short h21 = this.b.h();
                        h0Var.b(this.b.f(h21));
                        short h22 = this.b.h();
                        h0Var.a(this.b.f(h22));
                        short h23 = this.b.h();
                        h0Var.c(this.b.f(h23));
                        short h24 = this.b.h();
                        h0Var.e(this.b.f(h24));
                        short h25 = this.b.h();
                        h0Var.d(this.b.f(h25));
                        arrayList.add(h0Var);
                        short s4 = (short) (h21 + h22 + h23 + h24 + h25 + 10);
                        if (h20 <= s4) {
                            break;
                        } else {
                            this.b.c(h20 - s4);
                            break;
                        }
                    case 125:
                        j0 j0Var = new j0();
                        j0Var.a(this.b.i());
                        aVar.a(j0Var);
                        break;
                    case 132:
                        k0 k0Var = new k0();
                        k0Var.a(this.b.i());
                        aVar.a(k0Var);
                        break;
                    case 134:
                        l0 l0Var = new l0();
                        l0Var.a(this.b.i());
                        aVar.a(l0Var);
                        break;
                    case 152:
                        m0 m0Var = new m0();
                        m0Var.a(this.b.i());
                        aVar.a(m0Var);
                        break;
                    case 170:
                        o0 o0Var = new o0();
                        o0Var.a(this.b.e());
                        aVar.a(o0Var);
                        break;
                    case R2.anim.pop_right_bottom_out /* 171 */:
                        p0 p0Var = new p0();
                        p0Var.a(this.b.e());
                        aVar.a(p0Var);
                        break;
                    case R2.anim.pop_right_top_in /* 172 */:
                        q0 q0Var = new q0();
                        this.b.h();
                        q0Var.a(this.b.f());
                        aVar.a(q0Var);
                        break;
                    case 231:
                        r0 r0Var = new r0();
                        r0Var.a(this.b.i());
                        aVar.a(r0Var);
                        break;
                    case 238:
                        t0 t0Var = new t0();
                        t0Var.a(this.b.i());
                        aVar.a(t0Var);
                        break;
                    default:
                        this.b.c((int) this.b.h());
                        break;
                }
                k2 = this.b.k();
            }
            if (arrayList != null) {
                aVar.a(arrayList);
            }
            return aVar;
        } catch (Exception e2) {
            e2.printStackTrace();
            c0.a((short) 258, "getTLV_Exception");
            return aVar;
        }
    }
}
