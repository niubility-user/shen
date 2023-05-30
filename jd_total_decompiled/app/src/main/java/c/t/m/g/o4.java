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
import com.jingdong.sdk.baseinfo.BaseInfo;
import com.jingdong.sdk.platform.business.personal.R2;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.text.Typography;

/* loaded from: classes.dex */
public class o4 extends b0 {

    /* renamed from: g */
    public static volatile o4 f572g = null;

    /* renamed from: h */
    public static String f573h = "";

    /* renamed from: i */
    public static volatile String f574i = "";

    /* renamed from: j */
    public static final AtomicBoolean f575j = new AtomicBoolean(false);
    public a d;

    /* renamed from: c */
    public SimpleDateFormat f576c = w4.c("HHmmss");

    /* renamed from: e */
    public boolean f577e = false;

    /* renamed from: f */
    public final ConcurrentHashMap<String, Integer> f578f = new ConcurrentHashMap<>();

    /* loaded from: classes.dex */
    public class a extends Handler {
        public StringBuffer a;
        public long b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(Looper looper) {
            super(looper);
            o4.this = r1;
            this.a = new StringBuffer((int) R2.layout.text_view_without_line_height);
        }

        public void a() {
            StringBuffer stringBuffer = this.a;
            stringBuffer.append(Typography.dollar);
            stringBuffer.append(o4.this.f576c.format(new Date()) + ",LOC,shutdown");
            String stringBuffer2 = this.a.toString();
            this.a.setLength(0);
            o4.this.p(stringBuffer2, true);
            t.j(o4.this.d);
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
                    stringBuffer2.append(o4.w());
                    if (!t2.c(stringBuffer)) {
                        this.a.append(stringBuffer);
                    }
                    o4.this.t("SYSTEM", o4.x());
                    removeMessages(102);
                    t.d(o4.this.d, 102, 120000L);
                    o4 o4Var = o4.this;
                    o4Var.t("PERMISSION", o4Var.j(y3.a()));
                    return;
                case 102:
                    removeMessages(102);
                    t.d(o4.this.d, 102, 120000L);
                    o4 o4Var2 = o4.this;
                    o4Var2.t("PERMISSION", o4Var2.j(y3.a()));
                    return;
                case 103:
                    StringBuffer stringBuffer3 = this.a;
                    stringBuffer3.append(Typography.dollar);
                    stringBuffer3.append((String) message.obj);
                    if (this.a.length() < 18432 && System.currentTimeMillis() - this.b < 180000) {
                        return;
                    }
                    o4.this.p(this.a.toString(), false);
                    this.a.setLength(0);
                    t.k(o4.this.d, 101);
                    return;
                case 104:
                    o4.this.p("", true);
                    return;
                case 105:
                    o4.this.p(this.a.toString(), false);
                    this.a.setLength(0);
                    t.k(o4.this.d, 101);
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

        /* renamed from: g */
        public byte[] f580g;

        /* renamed from: h */
        public boolean f581h;

        /* loaded from: classes.dex */
        public class a implements c0.c {
            public final /* synthetic */ boolean a;
            public final /* synthetic */ byte[] b;

            /* renamed from: c */
            public final /* synthetic */ boolean[] f583c;

            public a(b bVar, boolean z, byte[] bArr, boolean[] zArr) {
                this.a = z;
                this.b = bArr;
                this.f583c = zArr;
            }

            @Override // c.t.m.g.c0.c
            public void a(String str) {
                try {
                    File file = new File(y3.a().getExternalFilesDir("data").getAbsolutePath() + "/mllc");
                    if (!file.exists()) {
                        file.mkdirs();
                    }
                    StringBuilder sb = new StringBuilder("mllc_");
                    sb.append(System.currentTimeMillis());
                    sb.append(this.a ? ".enc2" : ".enc");
                    u6.e(new File(file, sb.toString()), this.b, true);
                } catch (Throwable unused) {
                }
                this.f583c[0] = false;
            }

            @Override // c.t.m.g.c0.c
            public void b(String str) {
            }
        }

        public b(byte[] bArr, boolean z) {
            o4.this = r1;
            this.f581h = false;
            this.f580g = bArr;
            this.f581h = z;
        }

        public final void a() {
            long j2;
            boolean z;
            w1.a c2 = w1.c();
            long j3 = c2 != w1.a.NETWORK_WIFI ? 102400L : 2097152L;
            try {
                File file = new File(y3.a().getExternalFilesDir("data").getAbsolutePath() + "/mllc");
                File[] listFiles = file.listFiles();
                if (t2.i(listFiles)) {
                    return;
                }
                long currentTimeMillis = System.currentTimeMillis();
                int length = listFiles.length;
                long j4 = 0;
                int i2 = 0;
                while (true) {
                    if (i2 >= length) {
                        break;
                    }
                    File file2 = listFiles[i2];
                    if (j4 >= j3) {
                        break;
                    }
                    if (b(file2, currentTimeMillis)) {
                        byte[] f2 = u6.f(file2);
                        String name = file2.getName();
                        file2.delete();
                        if (t2.e(f2)) {
                            j2 = j3;
                            z = true;
                        } else {
                            j2 = j3;
                            j4 += f2.length;
                            z = c(f2, false, name.endsWith(".enc2"));
                            StringBuilder sb = new StringBuilder("upload:");
                            sb.append(name);
                            sb.append(DYConstants.DY_REGEX_COMMA);
                            sb.append(f2.length);
                            sb.append(DYConstants.DY_REGEX_COMMA);
                            sb.append(z);
                        }
                        if (!z) {
                            u6.c(file.getAbsolutePath(), 10485760L);
                            break;
                        }
                    } else {
                        j2 = j3;
                    }
                    i2++;
                    j3 = j2;
                }
                o4.this.t("NET", c2 + ",up file len:" + j4);
            } catch (Throwable unused) {
            }
        }

        public final boolean b(File file, long j2) {
            if (file == null || !file.exists()) {
                return false;
            }
            if (j2 - file.lastModified() >= 2592000000L) {
                u6.d(file);
                return false;
            }
            return true;
        }

        public final boolean c(byte[] bArr, boolean z, boolean z2) {
            try {
                if (t2.e(bArr)) {
                    return true;
                }
                byte[] c2 = z ? f6.c(bArr, true) : bArr;
                if (t2.e(c2)) {
                    return true;
                }
                o4.this.t("NET", "[src,enc]=[" + bArr.length + DYConstants.DY_REGEX_COMMA + c2.length + "],type=" + w1.a());
                boolean[] zArr = {true};
                c0.c(z2 ? "https://cs.map.qq.com/atta?type=1&multi=0" : "https://analytics.map.qq.com/tr?mllc", c2, new a(this, z2, c2, zArr));
                return zArr[0];
            } catch (Throwable unused) {
                return false;
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            if (!t2.e(this.f580g)) {
                c(this.f580g, true, true);
            }
            if (!this.f581h || o4.f575j.get()) {
                return;
            }
            o4.f575j.set(true);
            a();
            o4.f575j.set(false);
        }
    }

    public static void n(String str) {
        f573h = str;
    }

    public static final void o(String str, String str2) {
        v().t(str, str2);
    }

    public static o4 v() {
        if (f572g == null) {
            synchronized (o4.class) {
                if (f572g == null) {
                    f572g = new o4();
                }
            }
        }
        return f572g;
    }

    public static String w() {
        StringBuilder sb = new StringBuilder();
        sb.append(w4.c("yyyyMMdd-HHmmss").format(new Date()));
        sb.append(',');
        sb.append(',');
        sb.append(z3.o());
        sb.append(',');
        sb.append(',');
        sb.append(z3.p());
        sb.append(',');
        sb.append(t2.c(f574i) ? "" : f574i);
        sb.append(',');
        sb.append(z3.n());
        sb.append(',');
        sb.append(z3.a());
        sb.append(',');
        sb.append("");
        sb.append(',');
        sb.append("");
        return sb.toString();
    }

    public static String x() {
        return z3.f() + ',' + f573h + ',' + z3.p() + ',' + BaseInfo.getDeviceBrand() + "_," + Build.VERSION.SDK_INT + ',' + w1.a() + ',' + z3.r() + DYConstants.DY_REGEX_COMMA + z3.a();
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
        new StringBuilder("startup:isUp->").append(this.f577e);
        if (this.f577e) {
            a aVar = new a(d.e("th_loc_extra").getLooper());
            this.d = aVar;
            t.k(aVar, 101);
        }
        this.f578f.clear();
        return 0;
    }

    public final String j(Context context) {
        String[] strArr = {"android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_WIFI_STATE", "android.permission.CHANGE_WIFI_STATE", "android.permission.ACCESS_NETWORK_STATE", "android.permission.CHANGE_NETWORK_STATE", "android.permission.READ_PHONE_STATE"};
        int i2 = context.getApplicationInfo().targetSdkVersion;
        int i3 = 0;
        for (int i4 = 0; i4 < 7; i4++) {
            try {
                if (!r(context, strArr[i4], i2)) {
                    i3 |= 1 << i4;
                }
            } catch (Throwable unused) {
                i3 = -1;
            }
        }
        return Build.VERSION.SDK_INT + ContainerUtils.FIELD_DELIMITER + i2 + ContainerUtils.FIELD_DELIMITER + i3 + ContainerUtils.FIELD_DELIMITER + p6.b().a(context);
    }

    public final void p(String str, boolean z) {
        try {
            d.d("th_loc_task_t_consume", new b(str.getBytes("UTF-8"), z));
        } catch (Throwable unused) {
        }
    }

    public void q(boolean z) {
        this.f577e = z;
    }

    public final boolean r(Context context, String str, int i2) {
        if (Build.VERSION.SDK_INT >= 23) {
            return i2 < 23 ? PermissionChecker.checkSelfPermission(context, str) == 0 : context.checkSelfPermission(str) == 0;
        }
        return true;
    }

    public final void t(String str, String str2) {
        a aVar;
        if (!b() || (aVar = this.d) == null) {
            return;
        }
        Message obtainMessage = aVar.obtainMessage(103);
        obtainMessage.obj = this.f576c.format(new Date()) + DYConstants.DY_REGEX_COMMA + str + DYConstants.DY_REGEX_COMMA + str2;
        obtainMessage.sendToTarget();
    }
}
