package g.f.a.a;

import android.content.Context;
import com.jd.ai.asr.jni.JDVadJni;
import java.io.File;
import java.text.DecimalFormat;
import java.util.LinkedList;
import org.json.JSONObject;

/* loaded from: classes12.dex */
public class k implements g.f.a.c.d {

    /* renamed from: g  reason: collision with root package name */
    public g.f.a.c.d f19514g;

    /* renamed from: j  reason: collision with root package name */
    private Context f19517j;

    /* renamed from: n  reason: collision with root package name */
    private boolean f19521n;
    private boolean o;

    /* renamed from: h  reason: collision with root package name */
    private LinkedList<byte[]> f19515h = new LinkedList<>();

    /* renamed from: i  reason: collision with root package name */
    private boolean f19516i = false;

    /* renamed from: k  reason: collision with root package name */
    private int f19518k = 160000;

    /* renamed from: l  reason: collision with root package name */
    private int f19519l = 0;

    /* renamed from: m  reason: collision with root package name */
    private boolean f19520m = false;

    public k(Context context) {
        this.f19517j = context;
    }

    private double d(double d) {
        return d;
    }

    private double e(double d) {
        if (d < 0.25d || d > 0.5d) {
            return 0.25d;
        }
        return d;
    }

    private int f(double d) {
        new DecimalFormat("#.00").format(d);
        int i2 = ((int) (d * 1000.0d)) * 2;
        if (i2 % 200 == 0) {
            return i2 / 200;
        }
        return (i2 / 200) + 1;
    }

    private void g(byte[] bArr) {
        if (!this.f19516i) {
            h.a(this.f19514g, "VAD.DATA", null, bArr, 0, 0);
        }
        if (this.f19516i && this.f19520m) {
            g.f.a.b.b.b("vad", "vad processs......");
            int vadProcess = JDVadJni.vadProcess(bArr, bArr.length);
            g.f.a.b.b.b("vad", "vad process: " + vadProcess);
            if (vadProcess == 0) {
                int i2 = this.f19519l;
                if (i2 >= this.f19518k && !this.f19521n && this.o) {
                    h.a(this.f19514g, "VAD.ERROR", g.c(-1006), null, 0, 0);
                    h();
                    return;
                }
                this.f19519l = i2 + bArr.length;
                this.f19515h.add(bArr);
                while (this.f19515h.size() > 2) {
                    this.f19515h.removeFirst();
                }
            } else if (vadProcess == 1) {
                this.f19515h.add(bArr);
                h.a(this.f19514g, "VAD.BEGIN", null, null, 0, 0);
                while (this.f19515h.size() > 0) {
                    h.a(this.f19514g, "VAD.DATA", null, this.f19515h.removeFirst(), 0, 0);
                }
            } else if (vadProcess == 2) {
                h.a(this.f19514g, "VAD.DATA", null, bArr, 0, 0);
            } else if (vadProcess != 3) {
            } else {
                h.a(this.f19514g, "VAD.END", null, bArr, 0, 0);
            }
        }
    }

    private void h() {
        this.f19515h.clear();
        if (this.f19516i && this.f19520m) {
            JDVadJni.vadRelease();
            this.f19520m = false;
        }
    }

    private void j(String str) throws Exception {
        JSONObject jSONObject = new JSONObject(str);
        String optString = jSONObject.optString("VAD_RES", String.format("%s/%s", this.f19517j.getApplicationInfo().nativeLibraryDir, "libjd.dat.so"));
        g.f.a.b.b.b("vad", "vad  res file : " + optString);
        double optDouble = jSONObject.optDouble("VAD_START_DELAY", 0.25d);
        double optDouble2 = jSONObject.optDouble("VAD_END_DELAY", 0.5d);
        this.f19518k = jSONObject.optInt("NOT_SPEECH_TIMEOUT", this.f19518k);
        this.o = jSONObject.optBoolean("VAD_START_TIMEOUT", true);
        this.f19516i = jSONObject.optBoolean("LOCAL_VAD_ENABLE", true);
        this.f19521n = jSONObject.optBoolean("LONG_SPEECH", false);
        double e2 = e(optDouble);
        d(optDouble2);
        f(e2);
        g.f.a.b.b.b("vad", "startTime: " + e2 + "  endTime: " + optDouble2);
        this.f19515h.clear();
        this.f19520m = false;
        this.f19519l = 0;
        if (this.f19516i) {
            String a = g.f.a.b.c.a(optString, this.f19517j);
            g.f.a.b.b.b("vad", "vadFile: " + a);
            if (a != null && new File(a).exists()) {
                JDVadJni.vadInit(a, (float) e2, (float) optDouble2);
                g.f.a.b.b.b("vad", "vad init .....");
                this.f19520m = true;
                return;
            }
            h.a(this.f19514g, "VAD.ERROR", g.c(-1013), null, 0, 0);
        }
    }

    @Override // g.f.a.c.d
    public void a(g.f.a.c.c cVar) {
    }

    @Override // g.f.a.c.d
    public void b(String str, String str2, byte[] bArr, int i2, int i3) {
        str.hashCode();
        char c2 = '\uffff';
        switch (str.hashCode()) {
            case -1886837491:
                if (str.equals("VAD.START")) {
                    c2 = 0;
                    break;
                }
                break;
            case -892614273:
                if (str.equals("VAD.DATA")) {
                    c2 = 1;
                    break;
                }
                break;
            case -892149289:
                if (str.equals("VAD.STOP")) {
                    c2 = 2;
                    break;
                }
                break;
        }
        switch (c2) {
            case 0:
                try {
                    j(str2);
                    return;
                } catch (Exception e2) {
                    e2.printStackTrace();
                    h();
                    h.a(this.f19514g, "VAD.ERROR", null, null, 0, 0);
                    return;
                }
            case 1:
                g(bArr);
                return;
            case 2:
                h();
                return;
            default:
                return;
        }
    }

    @Override // g.f.a.c.d
    public void c(String str, String str2) {
    }

    public void i(g.f.a.c.d dVar) {
        this.f19514g = dVar;
    }
}
