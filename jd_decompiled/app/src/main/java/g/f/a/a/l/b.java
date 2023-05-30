package g.f.a.a.l;

import android.content.Context;
import g.f.a.a.e;
import g.f.a.a.h;
import g.f.a.a.k;
import g.f.a.c.c;
import g.f.a.c.d;

/* loaded from: classes12.dex */
public class b implements d {

    /* renamed from: g */
    private Context f19525g;

    /* renamed from: h */
    private c f19526h;

    /* renamed from: i */
    private e f19527i;

    /* renamed from: j */
    private k f19528j;

    /* renamed from: k */
    private a f19529k;

    public b(Context context) {
        this.f19525g = context;
    }

    private void d(String str) {
        f(g.f.a.c.b.SPEECh_KWS_RESULT, str, null);
        k();
    }

    private void e(byte[] bArr) {
        a aVar = this.f19529k;
        if (aVar != null) {
            h.a(aVar, "KWS.END", null, bArr, 0, 0);
        }
    }

    private void f(g.f.a.c.b bVar, String str, byte[] bArr) {
        c cVar = this.f19526h;
        if (cVar != null) {
            cVar.onEvent(bVar, str, bArr);
        }
    }

    private void g(String str) {
        if (this.f19529k == null) {
            a aVar = new a(this.f19525g);
            this.f19529k = aVar;
            aVar.h(this);
        }
        h.a(this.f19529k, "KWS.LOAD", str, null, 0, 0);
    }

    private void h() {
        a aVar = this.f19529k;
        if (aVar != null) {
            h.a(aVar, "KWS.RELEASE", null, null, 0, 0);
        }
    }

    private void i(String str) {
        if (this.f19527i == null) {
            this.f19527i = new e();
        }
        this.f19527i.g(this);
        h.a(this.f19527i, "RECORD.START", str, null, 0, 0);
        if (this.f19529k == null) {
            this.f19529k = new a(this.f19525g);
        }
        this.f19529k.h(this);
        h.a(this.f19529k, "KWS.START", str, null, 0, 0);
        if (this.f19528j == null) {
            this.f19528j = new k(this.f19525g);
        }
        this.f19528j.i(this);
        h.a(this.f19528j, "VAD.START", str, null, 0, 0);
    }

    private void j() {
        e eVar = this.f19527i;
        if (eVar != null) {
            h.a(eVar, "RECORD.STOP", null, null, 0, 0);
        }
        a aVar = this.f19529k;
        if (aVar != null) {
            h.a(aVar, "KWS.STOP", null, null, 0, 0);
        }
    }

    private void k() {
        e eVar = this.f19527i;
        if (eVar != null) {
            h.a(eVar, "RECORD.STOP", null, null, 0, 0);
        }
    }

    private void l(byte[] bArr, String str) {
        a aVar = this.f19529k;
        if (aVar != null) {
            h.a(aVar, str, null, bArr, 0, 0);
        }
    }

    private void m(byte[] bArr) {
        k kVar = this.f19528j;
        if (kVar != null) {
            h.a(kVar, "VAD.DATA", null, bArr, 0, 0);
        }
    }

    @Override // g.f.a.c.d
    public void a(c cVar) {
        this.f19526h = cVar;
    }

    @Override // g.f.a.c.d
    public void b(String str, String str2, byte[] bArr, int i2, int i3) {
        str.hashCode();
        char c2 = '\uffff';
        switch (str.hashCode()) {
            case -1899810125:
                if (str.equals("VAD.ERROR")) {
                    c2 = 0;
                    break;
                }
                break;
            case -1614639110:
                if (str.equals("KWS.FINISH")) {
                    c2 = 1;
                    break;
                }
                break;
            case -892614273:
                if (str.equals("VAD.DATA")) {
                    c2 = 2;
                    break;
                }
                break;
            case -840742105:
                if (str.equals("RECORD.DATA")) {
                    c2 = 3;
                    break;
                }
                break;
            case -434833200:
                if (str.equals("RECORD.FINISH")) {
                    c2 = 4;
                    break;
                }
                break;
            case 941038662:
                if (str.equals("VAD.END")) {
                    c2 = 5;
                    break;
                }
                break;
            case 1219806622:
                if (str.equals("RECORD.END")) {
                    c2 = 6;
                    break;
                }
                break;
        }
        switch (c2) {
            case 0:
                d(str2);
                j();
                return;
            case 1:
                f(g.f.a.c.b.SPEECh_KWS_RESULT, str2, bArr);
                return;
            case 2:
                l(bArr, "KWS.DATA");
                return;
            case 3:
                m(bArr);
                return;
            case 4:
                d(str2);
                return;
            case 5:
                l(bArr, "KWS.END");
                k();
                return;
            case 6:
                e(bArr);
                return;
            default:
                return;
        }
    }

    @Override // g.f.a.c.d
    public void c(String str, String str2) {
        str.hashCode();
        char c2 = '\uffff';
        switch (str.hashCode()) {
            case -865562272:
                if (str.equals("KWS.RELEASE")) {
                    c2 = 0;
                    break;
                }
                break;
            case -108758515:
                if (str.equals("KWS.LOAD")) {
                    c2 = 1;
                    break;
                }
                break;
            case -108544727:
                if (str.equals("KWS.STOP")) {
                    c2 = 2;
                    break;
                }
                break;
            case 930067451:
                if (str.equals("KWS.START")) {
                    c2 = 3;
                    break;
                }
                break;
        }
        switch (c2) {
            case 0:
                h();
                return;
            case 1:
                g(str2);
                return;
            case 2:
                j();
                return;
            case 3:
                i(str2);
                return;
            default:
                return;
        }
    }
}
