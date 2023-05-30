package g.f.a.a.l;

import android.content.Context;
import com.jd.ai.asr.jni.JDKwsJni;
import g.f.a.a.h;
import g.f.a.c.c;
import g.f.a.c.d;
import org.json.JSONObject;

/* loaded from: classes12.dex */
public class a implements d, Runnable {

    /* renamed from: g  reason: collision with root package name */
    private d f19522g;

    /* renamed from: h  reason: collision with root package name */
    private boolean f19523h = false;

    /* renamed from: i  reason: collision with root package name */
    private boolean f19524i = false;

    public a(Context context) {
    }

    private void d(byte[] bArr, int i2, boolean z) {
        if (this.f19523h) {
            g.f.a.b.b.b("kws decoder byte data: " + i2 + " isLast: " + z, new String[0]);
            JDKwsJni.kwsDecoder(bArr, i2, z);
            if (z) {
                e(JDKwsJni.kwsForceResult());
                j();
            }
        }
    }

    private void e(String str) {
        h.a(this.f19522g, "KWS.FINISH", str, null, 0, 0);
    }

    private void f(JSONObject jSONObject) throws Exception {
        if (JDKwsJni.kwsInit(jSONObject.optString("KWS_CONFIG"), jSONObject.optString("KWS_MODEL_1"), jSONObject.optString("KWS_MODEL_2"), jSONObject.optString("KWS_MODEL_3")) == 0) {
            this.f19524i = true;
            g.f.a.b.b.b("kws load succ", new String[0]);
            return;
        }
        g.f.a.b.b.b("kws load fail", new String[0]);
    }

    private void g() {
        if (this.f19524i) {
            JDKwsJni.kwsRelease();
        }
    }

    private void i(JSONObject jSONObject) throws Exception {
        if (!this.f19524i) {
            g.f.a.b.b.b("kws not load....", new String[0]);
            return;
        }
        JDKwsJni.kwsReset();
        this.f19523h = true;
    }

    private void j() {
        this.f19523h = false;
    }

    @Override // g.f.a.c.d
    public void a(c cVar) {
    }

    @Override // g.f.a.c.d
    public void b(String str, String str2, byte[] bArr, int i2, int i3) {
        str.hashCode();
        char c2 = '\uffff';
        switch (str.hashCode()) {
            case -865562272:
                if (str.equals("KWS.RELEASE")) {
                    c2 = 0;
                    break;
                }
                break;
            case -109009711:
                if (str.equals("KWS.DATA")) {
                    c2 = 1;
                    break;
                }
                break;
            case -108758515:
                if (str.equals("KWS.LOAD")) {
                    c2 = 2;
                    break;
                }
                break;
            case -108544727:
                if (str.equals("KWS.STOP")) {
                    c2 = 3;
                    break;
                }
                break;
            case 412126900:
                if (str.equals("KWS.END")) {
                    c2 = 4;
                    break;
                }
                break;
            case 930067451:
                if (str.equals("KWS.START")) {
                    c2 = 5;
                    break;
                }
                break;
        }
        switch (c2) {
            case 0:
                g();
                return;
            case 1:
                d(bArr, bArr.length, false);
                return;
            case 2:
                try {
                    f(new JSONObject(str2));
                    return;
                } catch (Exception e2) {
                    e2.printStackTrace();
                    return;
                }
            case 3:
                j();
                return;
            case 4:
                d(bArr, bArr.length, true);
                return;
            case 5:
                try {
                    i(new JSONObject(str2));
                    return;
                } catch (Exception e3) {
                    e3.printStackTrace();
                    return;
                }
            default:
                return;
        }
    }

    @Override // g.f.a.c.d
    public void c(String str, String str2) {
    }

    public void h(d dVar) {
        this.f19522g = dVar;
    }

    @Override // java.lang.Runnable
    public void run() {
    }
}
