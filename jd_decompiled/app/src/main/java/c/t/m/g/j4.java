package c.t.m.g;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import androidx.core.content.PermissionChecker;
import c.t.m.g.c0;
import c.t.m.g.w1;
import com.huawei.hms.framework.common.ContainerUtils;
import com.jd.dynamic.DYConstants;
import com.jingdong.sdk.platform.business.personal.R2;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.text.Typography;

/* loaded from: classes.dex */
public class j4 extends b0 {

    /* renamed from: g  reason: collision with root package name */
    public static volatile j4 f491g = null;

    /* renamed from: h  reason: collision with root package name */
    public static String f492h = "";

    /* renamed from: i  reason: collision with root package name */
    public static volatile String f493i = "";

    /* renamed from: j  reason: collision with root package name */
    public static final AtomicBoolean f494j = new AtomicBoolean(false);
    public a d;

    /* renamed from: c  reason: collision with root package name */
    public SimpleDateFormat f495c = w4.c("HHmmss");

    /* renamed from: e  reason: collision with root package name */
    public boolean f496e = false;

    /* renamed from: f  reason: collision with root package name */
    public HashSet<String> f497f = new HashSet<>();

    /* loaded from: classes.dex */
    public class a extends Handler {
        public StringBuffer a;
        public long b;

        public a(Looper looper) {
            super(looper);
            this.a = new StringBuffer((int) R2.layout.text_view_without_line_height);
        }

        public void a() {
            StringBuffer stringBuffer = this.a;
            stringBuffer.append(Typography.dollar);
            stringBuffer.append(j4.this.f495c.format(new Date()) + ",LOC,shutdown");
            String stringBuffer2 = this.a.toString();
            this.a.setLength(0);
            j4.this.q(stringBuffer2, true);
            t.j(j4.this.d);
        }

        public final void b(Message message) {
            switch (message.what) {
                case 101:
                    removeMessages(101);
                    this.b = System.currentTimeMillis();
                    String stringBuffer = this.a.toString();
                    this.a.setLength(0);
                    StringBuffer stringBuffer2 = this.a;
                    stringBuffer2.append("LOC_CORE,");
                    stringBuffer2.append(j4.x());
                    if (!t2.c(stringBuffer)) {
                        this.a.append(stringBuffer);
                    }
                    j4.this.u("SYSTEM", j4.y());
                    removeMessages(102);
                    t.d(j4.this.d, 102, 120000L);
                    j4 j4Var = j4.this;
                    j4Var.u("PERMISSION", j4Var.j(y3.a()));
                    return;
                case 102:
                    removeMessages(102);
                    t.d(j4.this.d, 102, 120000L);
                    j4 j4Var2 = j4.this;
                    j4Var2.u("PERMISSION", j4Var2.j(y3.a()));
                    return;
                case 103:
                    StringBuffer stringBuffer3 = this.a;
                    stringBuffer3.append(Typography.dollar);
                    stringBuffer3.append((String) message.obj);
                    if (this.a.length() < 18432 && System.currentTimeMillis() - this.b < 180000) {
                        return;
                    }
                    j4.this.q(this.a.toString(), false);
                    this.a.setLength(0);
                    t.k(j4.this.d, 101);
                    return;
                case 104:
                    j4.this.q("", true);
                    return;
                case 105:
                    j4.this.q(this.a.toString(), false);
                    this.a.setLength(0);
                    t.k(j4.this.d, 101);
                    return;
                case 106:
                    removeMessages(106);
                    this.a.insert(0, (String) message.obj);
                    return;
                default:
                    return;
            }
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            try {
                b(message);
            } catch (Throwable unused) {
            }
        }
    }

    /* loaded from: classes.dex */
    public class b implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        public String f499g;

        /* renamed from: h  reason: collision with root package name */
        public boolean f500h;

        /* loaded from: classes.dex */
        public class a implements c0.c {
            public final /* synthetic */ byte[] a;
            public final /* synthetic */ boolean[] b;

            public a(b bVar, byte[] bArr, boolean[] zArr) {
                this.a = bArr;
                this.b = zArr;
            }

            @Override // c.t.m.g.c0.c
            public void a(String str) {
                try {
                    File file = new File(y3.a().getExternalFilesDir("data").getAbsolutePath() + "/mllc");
                    if (!file.exists()) {
                        file.mkdirs();
                    }
                    u6.e(new File(file, "mllc_" + System.currentTimeMillis() + ".enc"), this.a, true);
                } catch (Throwable unused) {
                }
                this.b[0] = false;
            }

            @Override // c.t.m.g.c0.c
            public void b(String str) {
            }
        }

        public b(String str, boolean z) {
            this.f499g = "";
            this.f500h = false;
            this.f499g = str;
            this.f500h = z;
        }

        public final void a() {
            boolean z;
            w1.a c2 = w1.c();
            try {
                File file = new File(y3.a().getExternalFilesDir("data").getAbsolutePath() + "/mllc");
                File[] listFiles = file.listFiles();
                if (t2.i(listFiles)) {
                    return;
                }
                long currentTimeMillis = System.currentTimeMillis();
                int length = listFiles.length;
                boolean z2 = false;
                long j2 = 0;
                int i2 = 0;
                while (true) {
                    if (i2 >= length) {
                        break;
                    }
                    File file2 = listFiles[i2];
                    if (c2 != w1.a.NETWORK_WIFI && j2 >= 102400) {
                        break;
                    }
                    if (b(file2, currentTimeMillis)) {
                        byte[] f2 = u6.f(file2);
                        if (t2.e(f2)) {
                            z = true;
                        } else {
                            j2 += f2.length;
                            z = c(f2, z2);
                            StringBuilder sb = new StringBuilder("upload:");
                            sb.append(file2.getName());
                            sb.append(DYConstants.DY_REGEX_COMMA);
                            sb.append(f2.length);
                            sb.append(DYConstants.DY_REGEX_COMMA);
                            sb.append(z);
                        }
                        if (!z) {
                            u6.c(file.getAbsolutePath(), 10485760L);
                            break;
                        }
                        file2.delete();
                    }
                    i2++;
                    z2 = false;
                }
                j4.this.u("NET", c2 + ",up file len:" + j2);
            } catch (Throwable unused) {
            }
        }

        public final boolean b(File file, long j2) {
            if (file == null || !file.exists()) {
                return false;
            }
            if (j2 - file.lastModified() >= 2592000000L) {
                file.delete();
                return false;
            }
            return true;
        }

        public final boolean c(byte[] bArr, boolean z) {
            try {
                if (t2.e(bArr)) {
                    return true;
                }
                byte[] b = z ? s4.b(bArr, "0PEq^X$sjtWqEqa2$dg4TG2PT^4dFEep") : bArr;
                if (t2.e(b)) {
                    return true;
                }
                j4.this.u("NET", "[src,enc]=[" + bArr.length + DYConstants.DY_REGEX_COMMA + b.length + "],type=" + w1.a());
                boolean[] zArr = {true};
                c0.c("https://analytics.map.qq.com/tr?mllc", b, new a(this, b, zArr));
                return zArr[0];
            } catch (Throwable unused) {
                return false;
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            if (!t2.c(this.f499g)) {
                try {
                    c(this.f499g.getBytes("utf-8"), true);
                } catch (Exception unused) {
                }
            }
            if (!this.f500h || j4.f494j.get()) {
                return;
            }
            j4.f494j.set(true);
            a();
            j4.f494j.set(false);
        }
    }

    public static void o(String str) {
        f492h = str;
    }

    public static void p(String str, String str2) {
        w().u(str, str2);
    }

    public static j4 w() {
        if (f491g == null) {
            synchronized (j4.class) {
                if (f491g == null) {
                    f491g = new j4();
                }
            }
        }
        return f491g;
    }

    public static String x() {
        StringBuilder sb = new StringBuilder();
        sb.append(w4.c("yyyyMMdd-HHmmss").format(new Date()));
        sb.append(',');
        sb.append(z3.m());
        sb.append(',');
        sb.append(z3.o());
        sb.append(',');
        sb.append(',');
        sb.append(t2.c(f493i) ? "" : f493i);
        sb.append(',');
        sb.append(z3.n());
        sb.append(',');
        sb.append(z3.a());
        return sb.toString();
    }

    public static String y() {
        return z3.f() + ',' + f492h + ',' + z3.m() + ",," + Build.VERSION.SDK_INT + ',' + w1.a() + ',' + z3.r() + DYConstants.DY_REGEX_COMMA + z3.a();
    }

    @Override // c.t.m.g.i0
    public String a() {
        return "MllcPro";
    }

    @Override // c.t.m.g.i0
    public void d() {
        a aVar = this.d;
        if (aVar != null) {
            aVar.a();
            this.d = null;
            d.b("th_loc_extra");
        }
    }

    @Override // c.t.m.g.b0
    public int h(Looper looper) {
        new StringBuilder("startup:isUp->").append(this.f496e);
        if (this.f496e) {
            a aVar = new a(d.e("th_loc_extra").getLooper());
            this.d = aVar;
            t.k(aVar, 101);
        }
        this.f497f.clear();
        return 0;
    }

    public final String j(Context context) {
        String[] strArr = {"android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_WIFI_STATE", "android.permission.CHANGE_WIFI_STATE", "android.permission.ACCESS_NETWORK_STATE", "android.permission.CHANGE_NETWORK_STATE", "android.permission.READ_PHONE_STATE"};
        int i2 = context.getApplicationInfo().targetSdkVersion;
        int i3 = 0;
        for (int i4 = 0; i4 < 7; i4++) {
            try {
                if (!s(context, strArr[i4], i2)) {
                    i3 |= 1 << i4;
                }
            } catch (Throwable unused) {
                i3 = -1;
            }
        }
        return Build.VERSION.SDK_INT + ContainerUtils.FIELD_DELIMITER + i2 + ContainerUtils.FIELD_DELIMITER + i3 + ContainerUtils.FIELD_DELIMITER + b4.c().d(context);
    }

    public void l(long j2) {
        a aVar;
        if (!b() || (aVar = this.d) == null) {
            return;
        }
        t.d(aVar, 105, j2);
    }

    public final void q(String str, boolean z) {
        d.d("th_loc_task_t_consume", new b(str, z));
    }

    public void r(boolean z) {
        this.f496e = z;
    }

    public final boolean s(Context context, String str, int i2) {
        if (Build.VERSION.SDK_INT >= 23) {
            return i2 < 23 ? PermissionChecker.checkSelfPermission(context, str) == 0 : context.checkSelfPermission(str) == 0;
        }
        return true;
    }

    public final void u(String str, String str2) {
        a aVar;
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(DYConstants.DY_REGEX_AT);
        sb.append(str2);
        if (!b() || (aVar = this.d) == null) {
            return;
        }
        Message obtainMessage = aVar.obtainMessage(103);
        obtainMessage.obj = this.f495c.format(new Date()) + DYConstants.DY_REGEX_COMMA + str + DYConstants.DY_REGEX_COMMA + str2;
        obtainMessage.sendToTarget();
    }
}
