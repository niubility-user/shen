package g.f.a.a.m;

import android.content.Context;
import com.jd.ai.asr.jni.JDWakeupJni;
import g.f.a.a.e;
import g.f.a.a.h;
import g.f.a.c.b;
import g.f.a.c.c;
import g.f.a.c.d;
import org.json.JSONObject;

/* loaded from: classes12.dex */
public class a implements d {

    /* renamed from: g  reason: collision with root package name */
    private c f19530g;

    /* renamed from: h  reason: collision with root package name */
    private e f19531h;

    /* renamed from: i  reason: collision with root package name */
    private boolean f19532i;

    public a(Context context) {
    }

    private void d(b bVar, String str, byte[] bArr) {
        c cVar = this.f19530g;
        if (cVar != null) {
            cVar.onEvent(bVar, str, bArr);
        }
    }

    private void e(String str) throws Exception {
        if (this.f19531h == null) {
            this.f19531h = new e();
        }
        this.f19531h.g(this);
        h.a(this.f19531h, "RECORD.START", str, null, 0, 0);
        JSONObject jSONObject = new JSONObject(str);
        String optString = jSONObject.optString("WAKEUP_FILE");
        jSONObject.optDouble("WAKEUP_THRESSHOLD");
        g.f.a.b.b.b("wakeup ", optString);
        if (JDWakeupJni.wakeupInit("/sdcard/xiao3dong1xiao3dong1_v0001.bin") == -1) {
            g.f.a.b.b.b("wakeup", "wakeup init fail..");
        } else {
            this.f19532i = true;
        }
    }

    private void f() {
        e eVar = this.f19531h;
        if (eVar != null) {
            h.a(eVar, "RECORD.STOP", null, null, 0, 0);
        }
        JDWakeupJni.destory_wakeup();
    }

    private void g(byte[] bArr, boolean z) {
        if (this.f19532i) {
            int Detect_wakeup = JDWakeupJni.Detect_wakeup(bArr, bArr.length, 0.01d, 0);
            g.f.a.b.b.b("wakeup", "wakeup ret: " + Detect_wakeup);
            if (Detect_wakeup == 1) {
                d(b.WAKEUP_SUCC, null, bArr);
            }
        }
    }

    @Override // g.f.a.c.d
    public void a(c cVar) {
        this.f19530g = cVar;
    }

    @Override // g.f.a.c.d
    public void b(String str, String str2, byte[] bArr, int i2, int i3) {
        char c2;
        int hashCode = str.hashCode();
        if (hashCode == -840742105) {
            if (str.equals("RECORD.DATA")) {
                c2 = 0;
            }
            c2 = '\uffff';
        } else if (hashCode != -434833200) {
            if (hashCode == 1219806622 && str.equals("RECORD.END")) {
                c2 = 2;
            }
            c2 = '\uffff';
        } else {
            if (str.equals("RECORD.FINISH")) {
                c2 = 1;
            }
            c2 = '\uffff';
        }
        if (c2 == 0) {
            g(bArr, false);
        } else if (c2 != 1) {
        } else {
            g(bArr, true);
        }
    }

    @Override // g.f.a.c.d
    public void c(String str, String str2) {
        str.hashCode();
        if (!str.equals("WAKEUP_START")) {
            if (str.equals("WAKEUP_STOP")) {
                f();
                return;
            }
            return;
        }
        try {
            e(str2);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
