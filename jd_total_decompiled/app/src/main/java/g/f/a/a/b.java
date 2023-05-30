package g.f.a.a;

import android.content.Context;
import java.util.HashMap;
import org.json.JSONObject;

/* loaded from: classes12.dex */
public class b implements g.f.a.c.d {

    /* renamed from: g */
    private Context f19472g;

    /* renamed from: h */
    private g.f.a.c.c f19473h;

    /* renamed from: i */
    private c f19474i;

    /* renamed from: j */
    private e f19475j;

    /* renamed from: k */
    private k f19476k;

    /* renamed from: l */
    private boolean f19477l = false;

    public b(Context context) {
        this.f19472g = context;
    }

    public static long e(short[] sArr, int i2) {
        long j2 = 0;
        if (sArr == null) {
            return 0L;
        }
        System.currentTimeMillis();
        int min = Math.min(i2 / 2, 512);
        if (min <= 0) {
            return 0L;
        }
        for (int i3 = 0; i3 < min; i3++) {
            int i4 = i3 * 2;
            j2 += sArr[i4] * sArr[i4];
        }
        return (long) Math.sqrt(j2 / min);
    }

    private void f() {
        e eVar = this.f19475j;
        if (eVar != null) {
            eVar.g(null);
            h.a(this.f19475j, "RECORD.STOP", null, null, 0, 0);
        }
        k kVar = this.f19476k;
        if (kVar != null) {
            kVar.i(null);
            h.a(this.f19476k, "VAD.STOP", null, null, 0, 0);
        }
        c cVar = this.f19474i;
        if (cVar != null) {
            cVar.o(null);
            h.a(this.f19474i, "DETECT.CANCEL", null, null, 0, 0);
        }
    }

    private void g() {
        c cVar = this.f19474i;
        if (cVar != null) {
            h.a(cVar, "DETECT.END", null, new byte[1], 0, 0);
        }
    }

    private void h(String str) {
        i(g.f.a.c.b.SPEECH_RESULT, str, null);
        if (this.f19477l) {
            return;
        }
        f();
    }

    private void i(g.f.a.c.b bVar, String str, byte[] bArr) {
        g.f.a.c.c cVar = this.f19473h;
        if (cVar != null) {
            cVar.onEvent(bVar, str, bArr);
        }
    }

    private void j(String str) {
        i(g.f.a.c.b.SPEECH_PARITAL_RESULT, str, null);
    }

    private void k(String str) {
        if (this.f19475j == null) {
            this.f19475j = new e();
        }
        this.f19475j.g(this);
        h.a(this.f19475j, "RECORD.START", str, null, 0, 0);
        if (this.f19474i == null) {
            this.f19474i = new c(this.f19472g);
        }
        this.f19474i.o(this);
        h.a(this.f19474i, "DETECT.START", str, null, 0, 0);
        if (this.f19476k == null) {
            this.f19476k = new k(this.f19472g);
        }
        this.f19476k.i(this);
        h.a(this.f19476k, "VAD.START", str, null, 0, 0);
        try {
            this.f19477l = new JSONObject(str).optBoolean("LONG_SPEECH", false);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private void l() {
        e eVar = this.f19475j;
        if (eVar != null) {
            h.a(eVar, "RECORD.STOP", null, null, 0, 0);
        }
        c cVar = this.f19474i;
        if (cVar != null) {
            h.a(cVar, "DETECT.STOP", null, null, 0, 0);
        }
    }

    private void m() {
        e eVar = this.f19475j;
        if (eVar != null) {
            h.a(eVar, "RECORD.STOP", null, null, 0, 0);
        }
    }

    private void n(byte[] bArr) {
        HashMap hashMap = new HashMap();
        hashMap.put("volume", Integer.valueOf((int) (Math.min(5000.0f, (float) d(bArr, Math.min(bArr.length, 80))) / 50.0f)));
        i(g.f.a.c.b.SPEECH_VOLUME, new JSONObject(hashMap).toString(), null);
    }

    private void o(byte[] bArr, String str) {
        c cVar = this.f19474i;
        if (cVar != null) {
            h.a(cVar, str, null, bArr, 0, 0);
        }
    }

    private void p(byte[] bArr) {
        k kVar = this.f19476k;
        if (kVar != null) {
            h.a(kVar, "VAD.DATA", null, bArr, 0, 0);
        }
    }

    @Override // g.f.a.c.d
    public void a(g.f.a.c.c cVar) {
        this.f19473h = cVar;
    }

    @Override // g.f.a.c.d
    public void b(String str, String str2, byte[] bArr, int i2, int i3) {
        str.hashCode();
        char c2 = '\uffff';
        switch (str.hashCode()) {
            case -1902978732:
                if (str.equals("VAD.BEGIN")) {
                    c2 = 0;
                    break;
                }
                break;
            case -1899810125:
                if (str.equals("VAD.ERROR")) {
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
            case 21782078:
                if (str.equals("DETECT.FINISH")) {
                    c2 = 5;
                    break;
                }
                break;
            case 735008332:
                if (str.equals("DETECT.PARITAL")) {
                    c2 = 6;
                    break;
                }
                break;
            case 941038662:
                if (str.equals("VAD.END")) {
                    c2 = 7;
                    break;
                }
                break;
            case 1219806622:
                if (str.equals("RECORD.END")) {
                    c2 = '\b';
                    break;
                }
                break;
        }
        switch (c2) {
            case 0:
                i(g.f.a.c.b.SPEECH_BEGIN, null, null);
                return;
            case 1:
                h(str2);
                return;
            case 2:
                o(bArr, "DETECT.DATA");
                return;
            case 3:
                p(bArr);
                n(bArr);
                return;
            case 4:
                h(str2);
                return;
            case 5:
                h(str2);
                return;
            case 6:
                j(str2);
                return;
            case 7:
                o(bArr, "DETECT.END");
                i(g.f.a.c.b.SPEECH_END, null, null);
                if (this.f19477l) {
                    return;
                }
                m();
                return;
            case '\b':
                g();
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
            case -1739034828:
                if (str.equals("ASR.START")) {
                    c2 = 0;
                    break;
                }
                break;
            case 359544528:
                if (str.equals("ASR.STOP")) {
                    c2 = 1;
                    break;
                }
                break;
            case 1449254344:
                if (str.equals("ASR.CANCEL")) {
                    c2 = 2;
                    break;
                }
                break;
        }
        switch (c2) {
            case 0:
                k(str2);
                return;
            case 1:
                l();
                return;
            case 2:
                f();
                return;
            default:
                return;
        }
    }

    public long d(byte[] bArr, int i2) {
        int i3 = i2 / 2;
        short[] sArr = new short[i3];
        for (int i4 = 0; i4 < i3; i4++) {
            int i5 = i4 * 2;
            sArr[i4] = (short) ((bArr[i5 + 0] & 255) | (bArr[i5 + 1] << 8));
        }
        return e(sArr, i3);
    }
}
