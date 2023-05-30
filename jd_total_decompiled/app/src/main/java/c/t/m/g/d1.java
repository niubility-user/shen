package c.t.m.g;

import android.net.wifi.ScanResult;
import android.os.Looper;
import android.os.Message;
import android.util.Base64;
import c.t.m.g.w1;
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
    */
    public void f(Message message) {
        w1.a c2;
        if (this.o == null) {
            return;
        }
        boolean z = false;
        switch (message.what) {
            case 100:
                this.f346n = new e4(new File(this.o, this.q));
                this.f346n.c(this);
                byte[] f2 = u6.f(new File(this.o, this.p));
                long longValue = ((Long) r3.d("LocationSDK", "log_fc_wf_bf_create_t_ms", 0L)).longValue();
                long currentTimeMillis = System.currentTimeMillis();
                if (currentTimeMillis - longValue >= 2592000000L || t2.e(f2)) {
                    r3.h("LocationSDK", "log_fc_wf_bf_create_t_ms", Long.valueOf(currentTimeMillis));
                } else {
                    this.f345m.b(f2);
                }
                g(103, 300000L);
                u6.e(new File(this.o, this.p), this.f345m.c(), false);
                g(104, 300000L);
                return;
            case 101:
                q(this.f344l);
                u6.e(new File(this.o, this.p), this.f345m.c(), false);
                if (this.f346n != null) {
                    this.f346n.b();
                    this.f346n = null;
                }
                t.b(k(), 103);
                if (message.what != 101 && c()) {
                    g(103, 1800000L);
                }
                long currentTimeMillis2 = System.currentTimeMillis();
                long longValue2 = ((Long) r3.d("LocationSDK", "log_fc_wf_up_t_ms", 0L)).longValue();
                c2 = w1.c();
                if (c2 != w1.a.NETWORK_WIFI || (c2 == w1.a.NETWORK_MOBILE && (a2.f292f || a2.f293g))) {
                    z = true;
                }
                if (z0.e()) {
                    StringBuilder sb = new StringBuilder("lastUpT:");
                    sb.append(longValue2);
                    sb.append(",deltaT:");
                    sb.append(currentTimeMillis2 - longValue2);
                    sb.append(",network status:");
                    sb.append(c2);
                    sb.append(",isUpload:");
                    sb.append(z);
                }
                if (z) {
                    return;
                }
                int i2 = (longValue2 > 0L ? 1 : (longValue2 == 0L ? 0 : -1));
                if (i2 != 0 && currentTimeMillis2 - longValue2 >= 86400000) {
                    d.d("th_loc_task_t_consume", this);
                    r3.h("LocationSDK", "log_fc_wf_up_t_ms", Long.valueOf(currentTimeMillis2));
                    return;
                } else if (i2 == 0) {
                    r3.h("LocationSDK", "log_fc_wf_up_t_ms", Long.valueOf(currentTimeMillis2));
                    return;
                } else {
                    return;
                }
            case 102:
                for (ScanResult scanResult : (List) message.obj) {
                    if (!this.f345m.d(scanResult.BSSID)) {
                        this.f345m.a(scanResult.BSSID);
                        this.f344l.add(o(scanResult));
                    }
                }
                if (z0.e()) {
                    new StringBuilder("wf list size:").append(this.f344l.size());
                }
                if (this.f344l.size() >= 30) {
                    q(this.f344l);
                    return;
                }
                return;
            case 103:
                t.b(k(), 103);
                if (message.what != 101) {
                    g(103, 1800000L);
                    break;
                }
                long currentTimeMillis22 = System.currentTimeMillis();
                long longValue22 = ((Long) r3.d("LocationSDK", "log_fc_wf_up_t_ms", 0L)).longValue();
                c2 = w1.c();
                if (c2 != w1.a.NETWORK_WIFI) {
                    break;
                }
                z = true;
                if (z0.e()) {
                }
                if (z) {
                }
                break;
            case 104:
                u6.e(new File(this.o, this.p), this.f345m.c(), false);
                g(104, 300000L);
                return;
            default:
                return;
        }
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
