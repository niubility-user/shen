package c.t.m.g;

import android.net.wifi.ScanResult;
import android.os.Looper;
import android.os.Message;
import android.util.Base64;
import com.jd.dynamic.DYConstants;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.text.Typography;

/* loaded from: classes.dex */
public class d1 extends s implements m4, Runnable {

    /* renamed from: n  reason: collision with root package name */
    public volatile e4 f346n;
    public File o;

    /* renamed from: k  reason: collision with root package name */
    public volatile boolean f343k = false;
    public String p = "wf4_bf";
    public String q = "wf4";
    public StringBuilder r = new StringBuilder(100);

    /* renamed from: m  reason: collision with root package name */
    public o3 f345m = new o3(8192, 5);

    /* renamed from: l  reason: collision with root package name */
    public List<String> f344l = new ArrayList();

    /* loaded from: classes.dex */
    public class a implements v4 {

        /* renamed from: g  reason: collision with root package name */
        public final /* synthetic */ File f347g;

        public a(d1 d1Var, File file) {
            this.f347g = file;
        }

        @Override // c.t.m.g.v4
        public void a(String str) {
            z0.e();
        }

        @Override // c.t.m.g.v4
        public void b(String str) {
            this.f347g.delete();
            z0.e();
        }
    }

    public d1(File file) {
        this.o = file;
        if (z0.e()) {
            new StringBuilder("data dir:").append(this.o.getAbsolutePath());
        }
    }

    @Override // c.t.m.g.q0
    public int a(Looper looper) {
        g(100, 0L);
        return 0;
    }

    @Override // c.t.m.g.m4
    public byte[] a(byte[] bArr) {
        byte[] h2 = o5.h(f4.b(bArr), o5.a("fc_wf_up"));
        if (!t2.e(h2)) {
            byte[] encode = Base64.encode(h2, 2);
            if (!t2.e(encode)) {
                try {
                    return (new String(encode) + Typography.dollar).getBytes("UTF-8");
                } catch (Throwable unused) {
                }
            }
        }
        return new byte[0];
    }

    @Override // c.t.m.g.q0
    public String b() {
        return "WifiInfoPro";
    }

    @Override // c.t.m.g.q0
    public void d() {
        t.j(k());
        g(101, 0L);
    }

    /* JADX WARN: Removed duplicated region for block: B:41:0x00d7  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00fd A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00fe  */
    @Override // c.t.m.g.s
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void f(android.os.Message r15) {
        /*
            Method dump skipped, instructions count: 416
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: c.t.m.g.d1.f(android.os.Message):void");
    }

    public final String o(ScanResult scanResult) {
        this.r.setLength(0);
        try {
            StringBuilder sb = this.r;
            sb.append(scanResult.BSSID);
            sb.append(',');
            StringBuilder sb2 = this.r;
            sb2.append(Base64.encodeToString(scanResult.SSID.getBytes("UTF-8"), 2));
            sb2.append(',');
            StringBuilder sb3 = this.r;
            sb3.append(scanResult.frequency);
            sb3.append(',');
            this.r.append(Base64.encodeToString(scanResult.capabilities.getBytes("UTF-8"), 2));
        } catch (Throwable unused) {
            this.r.setLength(0);
        }
        return this.r.toString();
    }

    public void p(List<ScanResult> list) {
        if (!c() || t2.d(list)) {
            return;
        }
        Message obtainMessage = k().obtainMessage(102);
        obtainMessage.obj = list;
        h(obtainMessage, 0L);
    }

    public final void q(List<String> list) {
        if (this.f343k || t2.d(list) || t2.b(this.f346n)) {
            return;
        }
        long length = this.f346n.g().length();
        z0.e();
        if (length <= 51200) {
            StringBuilder sb = new StringBuilder(500);
            sb.append("1|");
            sb.append(list.size());
            Iterator<String> it = list.iterator();
            while (it.hasNext()) {
                sb.append('|');
                sb.append(it.next());
            }
            this.f346n.e(sb.toString());
        }
        list.clear();
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            this.f343k = true;
            if (this.f346n != null) {
                this.f346n.b();
                this.f346n = null;
            }
            File file = new File(this.o, this.q);
            if (z0.e()) {
                StringBuilder sb = new StringBuilder("upload:");
                sb.append(file.getName());
                sb.append(DYConstants.DY_REGEX_COMMA);
                sb.append(file.length());
            }
            byte[] b = f4.b(u6.f(file));
            String str = a2.f294h ? "https://testdatalbs.sparta.html5.qq.com/tr?wf4" : "https://analytics.map.qq.com/?wf4";
            if (!a2.f291e) {
                str = str.replace("https:", "http:");
            }
            a2.f297k.a(str, b, new a(this, file));
            if (c()) {
                this.f346n = new e4(new File(this.o, this.q));
                this.f346n.c(this);
            }
        } catch (Throwable unused) {
        }
        this.f343k = false;
    }
}
