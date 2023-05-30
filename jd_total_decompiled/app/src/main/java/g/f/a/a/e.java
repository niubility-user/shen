package g.f.a.a;

import com.jingdong.sdk.platform.business.personal.R2;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONObject;

/* loaded from: classes12.dex */
public class e implements g.f.a.c.d, Runnable {

    /* renamed from: g  reason: collision with root package name */
    private g.f.a.c.d f19501g;

    /* renamed from: h  reason: collision with root package name */
    private InputStream f19502h;

    /* renamed from: i  reason: collision with root package name */
    private boolean f19503i;

    /* renamed from: j  reason: collision with root package name */
    private int f19504j;

    /* renamed from: k  reason: collision with root package name */
    private OutputStream f19505k = null;

    /* renamed from: l  reason: collision with root package name */
    private int f19506l = 0;

    /* renamed from: m  reason: collision with root package name */
    private boolean f19507m = false;

    private void e() {
        h.a(this.f19501g, "RECORD.END", null, new byte[1], 0, 0);
    }

    private void f(int i2) {
        this.f19503i = true;
        h.a(this.f19501g, "RECORD.FINISH", g.c(i2), null, 0, 0);
    }

    private void h(JSONObject jSONObject) throws Exception {
        this.f19502h = d(jSONObject);
        this.f19503i = false;
        new Thread(this).start();
        g.f.a.b.b.b("mic", "mic started.....");
    }

    private void i() {
        this.f19503i = true;
    }

    @Override // g.f.a.c.d
    public void a(g.f.a.c.c cVar) {
    }

    @Override // g.f.a.c.d
    public void b(String str, String str2, byte[] bArr, int i2, int i3) {
        str.hashCode();
        if (str.equals("RECORD.STOP")) {
            i();
        } else if (str.equals("RECORD.START")) {
            try {
                h(new JSONObject(str2));
            } catch (Exception e2) {
                e2.printStackTrace();
                new HashMap();
                if (e2 instanceof UnsupportedOperationException) {
                    f(-1012);
                } else {
                    f(-1001);
                }
                i();
            }
        }
    }

    @Override // g.f.a.c.d
    public void c(String str, String str2) {
    }

    public InputStream d(JSONObject jSONObject) throws Exception {
        String optString = jSONObject.optString("AUDIO_SOURCE");
        int optInt = jSONObject.optInt("SAMPLE_RATE", R2.id.rn_redbox_report_label);
        String optString2 = jSONObject.optString("RECORD_FILE_PATH", "");
        this.f19506l = jSONObject.optInt("RECORD_TIMEOUT", 0);
        if (!optString2.equals("")) {
            this.f19505k = new FileOutputStream(optString2, true);
        }
        this.f19504j = ((optInt * 2) / 10) * 2;
        if (optString != null && !"".equals(optString)) {
            if (optString.startsWith("res://")) {
                return e.class.getResourceAsStream("/" + optString.replaceFirst("res://", "").replaceFirst("/", ""));
            } else if (!optString.startsWith("asset://") && !optString.startsWith("assets://")) {
                if (optString.startsWith("#")) {
                    Matcher matcher = Pattern.compile("^#(.*)[#.](.*?)\\(").matcher(optString);
                    if (matcher.find()) {
                        try {
                            return (InputStream) Class.forName(matcher.group(1)).getMethod(matcher.group(2), new Class[0]).invoke(null, new Object[0]);
                        } catch (Exception e2) {
                            throw new Exception("invoke " + optString + " failed", e2);
                        }
                    }
                    return null;
                }
                FileInputStream fileInputStream = new FileInputStream(optString);
                this.f19507m = true;
                return fileInputStream;
            } else {
                return e.class.getResourceAsStream("/assets/" + optString.replaceFirst("asset://", "").replaceFirst("/", ""));
            }
        }
        return new d(optInt);
    }

    public void g(g.f.a.c.d dVar) {
        this.f19501g = dVar;
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x0020, code lost:
        if (r7 <= 0) goto L92;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0022, code lost:
        g.f.a.a.h.a(r14.f19501g, "RECORD.DATA", null, r13, 0, r6);
        r7 = r14.f19505k;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x002f, code lost:
        if (r7 == null) goto L16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0031, code lost:
        r7.write(r13);
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0034, code lost:
        r3 = r3 + (r6 / 32);
        r6 = r14.f19506l;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0039, code lost:
        if (r6 <= 0) goto L20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x003b, code lost:
        if (r3 < r6) goto L20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0040, code lost:
        if (r14.f19507m == false) goto L93;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0042, code lost:
        java.lang.Thread.sleep(80);
     */
    @Override // java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void run() {
        int i2 = 0;
        loop0: while (!this.f19503i) {
            try {
                try {
                    int i3 = this.f19504j;
                    byte[] bArr = new byte[i3];
                    int i4 = 0;
                    while (true) {
                        if (i4 >= i3) {
                            break;
                        }
                        int read = this.f19502h.read(bArr, i4, i3 - i4);
                        if (read < 0) {
                            break loop0;
                        }
                        i4 += read;
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                    e();
                    InputStream inputStream = this.f19502h;
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                            this.f19502h = null;
                        } catch (IOException e3) {
                            e3.printStackTrace();
                        }
                    }
                    OutputStream outputStream = this.f19505k;
                    if (outputStream != null) {
                        try {
                            outputStream.close();
                            this.f19505k = null;
                        } catch (IOException e4) {
                            e4.printStackTrace();
                        }
                    }
                    g.f.a.b.b.b("mic", "mic stoped....");
                }
            } catch (Throwable th) {
                InputStream inputStream2 = this.f19502h;
                if (inputStream2 != null) {
                    try {
                        inputStream2.close();
                        this.f19502h = null;
                    } catch (IOException e5) {
                        e5.printStackTrace();
                    }
                }
                OutputStream outputStream2 = this.f19505k;
                if (outputStream2 != null) {
                    try {
                        outputStream2.close();
                        this.f19505k = null;
                    } catch (IOException e6) {
                        e6.printStackTrace();
                    }
                }
                g.f.a.b.b.b("mic", "mic stoped....");
                e();
                throw th;
            }
        }
        InputStream inputStream3 = this.f19502h;
        if (inputStream3 != null) {
            try {
                inputStream3.close();
                this.f19502h = null;
            } catch (IOException e7) {
                e7.printStackTrace();
            }
        }
        OutputStream outputStream3 = this.f19505k;
        if (outputStream3 != null) {
            try {
                outputStream3.close();
                this.f19505k = null;
            } catch (IOException e8) {
                e8.printStackTrace();
            }
        }
        g.f.a.b.b.b("mic", "mic stoped....");
        e();
    }
}
