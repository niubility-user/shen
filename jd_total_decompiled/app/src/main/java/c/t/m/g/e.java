package c.t.m.g;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.location.Location;
import android.net.wifi.ScanResult;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import c.t.m.g.w1;
import com.facebook.common.statfs.StatFsHelper;
import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import com.huawei.hms.utils.FileUtil;
import com.jd.dynamic.DYConstants;
import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import com.jingdong.sdk.platform.business.personal.R2;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;

/* loaded from: classes.dex */
public class e extends b0 {
    public static final String z = a2.d() + o1.a(e.class.getName(), MessageDigestAlgorithms.SHA_256).substring(0, 8);

    /* renamed from: c  reason: collision with root package name */
    public Context f356c;
    public final File d;

    /* renamed from: e  reason: collision with root package name */
    public boolean f357e;

    /* renamed from: f  reason: collision with root package name */
    public volatile Handler f358f;

    /* renamed from: g  reason: collision with root package name */
    public long f359g;

    /* renamed from: h  reason: collision with root package name */
    public long f360h;

    /* renamed from: i  reason: collision with root package name */
    public int f361i;

    /* renamed from: j  reason: collision with root package name */
    public int f362j;

    /* renamed from: k  reason: collision with root package name */
    public long f363k;

    /* renamed from: l  reason: collision with root package name */
    public long f364l;

    /* renamed from: m  reason: collision with root package name */
    public long f365m;

    /* renamed from: n  reason: collision with root package name */
    public long f366n;
    public boolean o;
    public boolean p;
    public boolean q;
    public long r;
    public volatile List<y> s;
    public volatile List<ScanResult> t;
    public volatile Location u;
    public d1 v;
    public v0 w;
    public n0 x;
    public BroadcastReceiver y;

    /* loaded from: classes.dex */
    public class a extends BroadcastReceiver {
        public a() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null || !"android.net.conn.CONNECTIVITY_CHANGE".equals(intent.getAction())) {
                return;
            }
            try {
                boolean booleanExtra = intent.getBooleanExtra("noConnectivity", false);
                if (z0.e()) {
                    StringBuilder sb = new StringBuilder("intent:");
                    sb.append(intent);
                    sb.append(DYConstants.DY_REGEX_COMMA);
                }
                if (booleanExtra) {
                    return;
                }
                t.d(e.this.f358f, 107, 2000L);
                if (e.this.w != null) {
                    e.this.w.w(2000L);
                }
            } catch (Throwable unused) {
            }
        }
    }

    /* loaded from: classes.dex */
    public class b implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        public final /* synthetic */ File f367g;

        /* renamed from: h  reason: collision with root package name */
        public final /* synthetic */ String f368h;

        /* loaded from: classes.dex */
        public class a implements v4 {
            public a() {
            }

            @Override // c.t.m.g.v4
            public void a(String str) {
            }

            @Override // c.t.m.g.v4
            public void b(String str) {
                b.this.f367g.delete();
                if (z0.e()) {
                    StringBuilder sb = new StringBuilder("upload ");
                    sb.append(b.this.f367g.getName());
                    sb.append(" succeed, then delete.");
                }
            }
        }

        public b(e eVar, File file, String str) {
            this.f367g = file;
            this.f368h = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                byte[] f2 = u6.f(this.f367g);
                if (!t2.e(f2)) {
                    a2.f297k.a(this.f368h, f2, new a());
                    return;
                }
                this.f367g.delete();
                if (z0.e()) {
                    StringBuilder sb = new StringBuilder("file ");
                    sb.append(this.f367g.getName());
                    sb.append(" is empty, then delete.");
                }
            } catch (Throwable unused) {
                if (z0.e()) {
                    new StringBuilder("upload error,url=").append(this.f368h);
                }
            }
        }
    }

    /* loaded from: classes.dex */
    public class c extends Handler {
        public File a;
        public BufferedOutputStream b;

        /* renamed from: c  reason: collision with root package name */
        public StringBuffer f370c;
        public String d;

        /* renamed from: e  reason: collision with root package name */
        public long f371e;

        public c(Looper looper) {
            super(looper);
            this.d = "";
            this.f371e = 0L;
        }

        public final void a() {
            File file = this.a;
            if (file == null || !file.exists() || this.b == null || !e.z.equals(this.a.getName())) {
                File j2 = j();
                this.a = j2;
                try {
                    boolean exists = j2.exists();
                    this.b = new BufferedOutputStream(new FileOutputStream(this.a, true), 1024);
                    if (exists) {
                        return;
                    }
                    r3.h("LocationSDK", "log_fc_create", Long.valueOf(System.currentTimeMillis()));
                } catch (Throwable unused) {
                }
            }
        }

        public final void b(int i2) {
            String str;
            File file;
            a();
            if (!t2.d(e.this.s)) {
                long j2 = ((y) e.this.s.get(0)).f760e;
                r1 = this.f371e != j2;
                this.f371e = j2;
            }
            if (i2 == 102) {
                str = b2.a(a2.f295i, e.this.u, null, e.this.s, r1);
            } else {
                if (i2 == 101) {
                    List list = e.this.t;
                    if (!t2.d(list)) {
                        str = b2.a(a2.f295i, e.this.u, list, e.this.s, r1);
                    }
                }
                str = "";
            }
            if (this.b == null || TextUtils.isEmpty(str) || str.length() < 25) {
                return;
            }
            String b = o1.b(str.substring(22).getBytes(), MessageDigestAlgorithms.SHA_256);
            if (this.d.equals(b)) {
                return;
            }
            this.d = b;
            if (this.f370c == null) {
                this.f370c = new StringBuffer(e.this.f362j);
            }
            StringBuffer stringBuffer = this.f370c;
            stringBuffer.append(str);
            stringBuffer.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
            if (this.f370c.length() > e.this.f362j || ((file = this.a) != null && file.length() == 0)) {
                l();
                if (this.a.length() > i()) {
                    t.k(e.this.f358f, 106);
                }
            }
            if (z0.e()) {
                StringBuilder sb = new StringBuilder("write:");
                sb.append(str.substring(0, 60));
                sb.append("***,len=");
                sb.append(str.length());
            }
        }

        public final void c(int i2, File file) {
            try {
                if (file.isFile()) {
                    File file2 = new File(file.getAbsolutePath() + OrderISVUtil.MONEY_DECIMAL + System.currentTimeMillis() + ".enc");
                    if (i2 == 4) {
                        byte[] f2 = u6.f(file);
                        if (!t2.e(f2)) {
                            byte[] b = f4.b(f2);
                            if (!t2.e(b)) {
                                FileOutputStream fileOutputStream = new FileOutputStream(file2, true);
                                fileOutputStream.write(b);
                                fileOutputStream.close();
                                file.delete();
                            }
                        }
                    } else {
                        file.renameTo(file2);
                    }
                    if (z0.e()) {
                        StringBuilder sb = new StringBuilder("rename:");
                        sb.append(file.getName());
                        sb.append(" to ");
                        sb.append(file2.getName());
                    }
                }
            } catch (Throwable unused) {
                if (z0.e()) {
                    StringBuilder sb2 = new StringBuilder("rename:");
                    sb2.append(file.getName());
                    sb2.append(" error.");
                }
            }
        }

        public final void d(long j2, long j3) {
            File file = null;
            File[] listFiles = e.this.d == null ? null : e.this.d.listFiles();
            if (listFiles == null || listFiles.length == 0) {
                return;
            }
            long currentTimeMillis = System.currentTimeMillis();
            long j4 = 0;
            for (File file2 : listFiles) {
                if (file2.exists() && file2.isFile() && !e.z.equals(file2.getName())) {
                    if (currentTimeMillis - file2.lastModified() > j2 || file2.length() == 0) {
                        if (z0.e()) {
                            StringBuilder sb = new StringBuilder("delete expired file:");
                            sb.append(file2.getName());
                            sb.append(",len:");
                            sb.append(file2.length());
                        }
                        file2.delete();
                    } else {
                        String name = file2.getName();
                        if (currentTimeMillis - file2.lastModified() <= 172800000 || name.endsWith(".enc") || !name.startsWith(a2.d())) {
                            j4 += file2.length();
                            if (file == null || file.lastModified() > file2.lastModified()) {
                                file = file2;
                            }
                        } else {
                            c(a2.a(name), file2);
                        }
                    }
                }
            }
            if (j4 < j3 || file == null) {
                return;
            }
            if (z0.e()) {
                StringBuilder sb2 = new StringBuilder("too big folder size:");
                sb2.append(j4);
                sb2.append(", delete ");
                sb2.append(file.getName());
                sb2.append(",size:");
                sb2.append(file.length());
            }
            file.delete();
        }

        public final void e(Message message) {
            File file;
            int i2 = message.what;
            switch (i2) {
                case 101:
                case 102:
                    try {
                        b(i2);
                        return;
                    } catch (Throwable unused) {
                        return;
                    }
                case 103:
                    if (e.this.A()) {
                        String absolutePath = e.this.d.getAbsolutePath();
                        if (g(absolutePath)) {
                            return;
                        }
                        g(absolutePath.replaceAll("f_c", "d_c"));
                        return;
                    }
                    return;
                case 104:
                    h();
                    return;
                case 105:
                    try {
                        l();
                        StringBuffer stringBuffer = this.f370c;
                        if (stringBuffer != null) {
                            stringBuffer.setLength(0);
                        }
                    } catch (Throwable unused2) {
                    }
                    this.a = null;
                    u6.b(this.b);
                    d(e.this.f366n, e.this.f363k);
                    removeCallbacksAndMessages(null);
                    return;
                case 106:
                    a();
                    if (e.this.d == null || (file = this.a) == null || !file.exists()) {
                        return;
                    }
                    l();
                    long longValue = ((Long) r3.d("LocationSDK", "log_fc_create", 0L)).longValue();
                    long currentTimeMillis = System.currentTimeMillis();
                    if (z0.e()) {
                        StringBuilder sb = new StringBuilder("desFileLen=");
                        sb.append(this.a.length());
                        sb.append(",maxFileSize=");
                        sb.append(i());
                        sb.append(",curT=");
                        sb.append(currentTimeMillis);
                        sb.append(",createT=");
                        sb.append(longValue);
                        sb.append(",maxTimeRename:");
                        sb.append(e.this.f365m);
                    }
                    if (this.a.length() > i() || currentTimeMillis - longValue > e.this.f365m) {
                        k();
                        d(e.this.f366n, e.this.f363k);
                        if (a2.f292f) {
                            sendEmptyMessage(107);
                            return;
                        }
                        return;
                    }
                    return;
                case 107:
                    removeMessages(107);
                    e eVar = e.this;
                    eVar.o(eVar.f358f);
                    t.d(e.this.f358f, 107, e.this.f360h);
                    return;
                default:
                    return;
            }
        }

        public final boolean f(long j2) {
            try {
                SharedPreferences b = r3.b("LocationSDK");
                SharedPreferences.Editor edit = b.edit();
                String string = b.getString("log_up_fc_date", "");
                long j3 = b.getLong("log_up_fc_size", 0L);
                String format = w4.c("yyyyMMdd").format(new Date());
                if (!format.equals(string)) {
                    edit.putString("log_up_fc_date", format);
                } else if (j3 > e.this.f364l) {
                    return false;
                } else {
                    j2 += j3;
                }
                edit.putLong("log_up_fc_size", j2);
                edit.apply();
                return true;
            } catch (Throwable unused) {
                return true;
            }
        }

        public final boolean g(String str) {
            File[] fileArr = null;
            File file = TextUtils.isEmpty(str) ? null : new File(str);
            if (file != null && file.exists() && file.isDirectory()) {
                fileArr = file.listFiles();
            }
            if (fileArr == null || fileArr.length == 0) {
                if (fileArr != null && str.endsWith("d_c")) {
                    file.delete();
                }
                return false;
            }
            long currentTimeMillis = System.currentTimeMillis();
            int i2 = e.this.f361i;
            int i3 = 0;
            while (true) {
                boolean z = true;
                if (i3 >= fileArr.length || i2 <= 0) {
                    break;
                }
                File file2 = fileArr[i3];
                String name = (file2 != null && file2.exists() && file2.isFile()) ? file2.getName() : "";
                if (name.startsWith("dc") || name.startsWith("fc")) {
                    if (!str.endsWith("d_c") || (file2.length() != 0 && currentTimeMillis - file2.lastModified() <= e.this.f366n)) {
                        boolean z2 = (name.startsWith(a2.d()) && name.endsWith(".enc")) || (name.startsWith("fc2") || name.startsWith("fc3"));
                        if (z2) {
                            z = z2;
                        } else {
                            if (z0.e()) {
                                StringBuilder sb = new StringBuilder();
                                sb.append(str);
                                sb.append(",has no ");
                                sb.append(a2.d());
                                sb.append("***.enc files!!!");
                            }
                            if (!name.startsWith("dc") || System.currentTimeMillis() - file2.lastModified() <= 172800000) {
                                z = false;
                            }
                        }
                        if (z && f(file2.length())) {
                            if (z0.e()) {
                                StringBuilder sb2 = new StringBuilder("upload:");
                                sb2.append(file2.getName());
                                sb2.append(",len=");
                                sb2.append(file2.length());
                            }
                            int a = a2.a(name);
                            if (a > 0) {
                                String b = a2.b(a);
                                if (!TextUtils.isEmpty(b)) {
                                    if (!a2.f291e) {
                                        b = b.replace("https:", "http:");
                                    }
                                    e.this.r(file2, b);
                                    i2--;
                                }
                            }
                        }
                    } else {
                        file2.delete();
                    }
                }
                i3++;
            }
            return i2 != e.this.f361i;
        }

        public final void h() {
            try {
                BufferedOutputStream bufferedOutputStream = this.b;
                if (bufferedOutputStream != null) {
                    bufferedOutputStream.flush();
                }
            } catch (Throwable unused) {
                this.a = null;
                u6.b(this.b);
            }
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            try {
                if (z0.e()) {
                    new StringBuilder("handleMessage:").append(message.what);
                }
                e(message);
            } catch (Throwable unused) {
                z0.e();
            }
        }

        public final long i() {
            long j2 = a2.f292f ? 51200L : 512000L;
            return e.this.f359g <= j2 ? e.this.f359g : j2;
        }

        public final File j() {
            File file = e.this.d;
            if (!file.exists()) {
                file.mkdirs();
            }
            return new File(file, e.z);
        }

        public final void k() {
            h();
            File file = this.a;
            if (file == null || file.length() < 1024) {
                return;
            }
            u6.b(this.b);
            this.b = null;
            c(4, this.a);
            this.a = null;
            r3.h("LocationSDK", "log_fc_create", 0L);
        }

        public final void l() {
            StringBuffer stringBuffer = this.f370c;
            if (stringBuffer == null || stringBuffer.length() == 0 || this.b == null) {
                return;
            }
            byte[] b = p2.b(this.f370c.toString());
            if (z0.e()) {
                StringBuilder sb = new StringBuilder("write buf to file:buf:");
                sb.append(this.f370c.length());
                sb.append(",enc:");
                sb.append(b == null ? 0 : b.length);
            }
            this.f370c.setLength(0);
            if (b == null || b.length == 0) {
                return;
            }
            try {
                this.b.write(b);
                this.b.write(36);
                this.b.flush();
            } catch (Throwable unused) {
                this.a = null;
                u6.b(this.b);
            }
        }
    }

    public e(Context context, File file) {
        this.f356c = null;
        this.f359g = 102400L;
        this.f360h = 3600000L;
        this.f361i = 1;
        this.f362j = R2.styleable.ylayout_yg_paddingTop;
        this.f363k = StatFsHelper.DEFAULT_DISK_RED_LEVEL_IN_BYTES;
        this.f364l = 10485760L;
        this.f365m = 259200000L;
        this.f366n = 2592000000L;
        this.o = true;
        this.p = false;
        this.q = false;
        this.r = 0L;
        this.y = new a();
        this.f356c = context;
        this.d = file;
        this.f357e = false;
    }

    public e(Context context, String str) {
        this(context, new File(str + "/f_c"));
    }

    public final boolean A() {
        return this.f357e;
    }

    public final void E() {
        this.s = null;
        this.t = null;
        this.u = null;
        this.r = 0L;
    }

    @Override // c.t.m.g.i0
    public String a() {
        return "DC_Pro";
    }

    @Override // c.t.m.g.i0
    public void d() {
        try {
            this.f356c.unregisterReceiver(this.y);
        } catch (Throwable unused) {
        }
        d1 d1Var = this.v;
        if (d1Var != null) {
            d1Var.e(100L);
            this.v = null;
        }
        v0 v0Var = this.w;
        if (v0Var != null) {
            v0Var.e(100L);
            this.w = null;
        }
        n0 n0Var = this.x;
        if (n0Var != null) {
            n0Var.m();
            this.x = null;
        }
        if (A()) {
            t.k(this.f358f, 104);
            t.k(this.f358f, 106);
            this.r = 0L;
            t.k(this.f358f, 107);
            t.d(this.f358f, 105, 200L);
            this.f358f = null;
        }
    }

    @Override // c.t.m.g.b0
    public int h(Looper looper) {
        E();
        File file = this.d;
        this.f357e = file != null && (file.exists() || this.d.mkdirs());
        if (z0.e()) {
            new StringBuilder("startup! prepared:").append(this.f357e);
        }
        if (this.f357e) {
            this.f358f = new c(looper);
            this.r = System.currentTimeMillis() - 40000;
            t.d(this.f358f, 107, 300000L);
            try {
                this.f356c.registerReceiver(this.y, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
            } catch (Throwable unused) {
                z0.e();
            }
        }
        if (this.f357e && this.o) {
            d1 d1Var = new d1(this.d);
            this.v = d1Var;
            d1Var.i(looper);
        }
        if (this.p) {
            v0 v0Var = new v0(this.d);
            this.w = v0Var;
            v0Var.i(looper);
        }
        if (this.q) {
            n0 n0Var = new n0();
            this.x = n0Var;
            n0Var.i(looper);
        }
        z0.d("FC", "systemInfo," + z3.a() + DYConstants.DY_REGEX_COMMA + z3.p());
        StringBuilder sb = new StringBuilder("start,");
        boolean z2 = this.o;
        f2.a(z2);
        sb.append(z2 ? 1 : 0);
        sb.append(DYConstants.DY_REGEX_COMMA);
        boolean z3 = this.p;
        f2.a(z3);
        sb.append(z3 ? 1 : 0);
        sb.append(DYConstants.DY_REGEX_COMMA);
        boolean z4 = this.q;
        f2.a(z4);
        sb.append(z4 ? 1 : 0);
        z0.d("FC", sb.toString());
        return 0;
    }

    public final long i(long j2, long j3, long j4) {
        return Math.max(j3, Math.min(j2, j4));
    }

    public void k(int i2, long j2, Object obj) {
        v0 v0Var;
        if ((i2 == 1 || i2 == 2) && (v0Var = this.w) != null) {
            v0Var.q(i2, j2, obj);
        }
    }

    public void l(int i2, Location location) {
        v0 v0Var = this.w;
        if (v0Var != null) {
            v0Var.r(i2, location);
        }
        n0 n0Var = this.x;
        if (n0Var != null) {
            n0Var.o(i2, location);
        }
    }

    public void m(long j2, int i2, double d, double d2, double d3) {
        v0 v0Var = this.w;
        if (v0Var != null) {
            v0Var.s(j2, i2, d, d2, d3);
        }
    }

    public synchronized void n(Location location, List<ScanResult> list, List<y> list2) {
        long currentTimeMillis = System.currentTimeMillis();
        if (location == null || currentTimeMillis - location.getTime() > 10000) {
            return;
        }
        this.u = location;
        this.t = list;
        this.s = list2;
        if (A()) {
            if (list == null) {
                if (!t2.d(list2)) {
                    t.k(this.f358f, 102);
                }
            } else if (!t2.d(list)) {
                t.k(this.f358f, 101);
            }
        }
    }

    @SuppressLint({"MissingPermission"})
    public final void o(Handler handler) {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.r < 60000) {
            return;
        }
        t.k(handler, 106);
        try {
            w1.a c2 = w1.c();
            boolean z2 = true;
            if (c2 == w1.a.NETWORK_NONE) {
                z2 = false;
            } else if (c2 == w1.a.NETWORK_MOBILE) {
                boolean z3 = a2.f292f;
                if (!z3 && a2.f293g) {
                    long longValue = ((Long) r3.d("LocationSDK", "log_fc_up_in_m", Long.valueOf(currentTimeMillis))).longValue();
                    if (currentTimeMillis - longValue > 86400000) {
                        r3.h("LocationSDK", "log_fc_up_in_m", Long.valueOf(currentTimeMillis));
                        if (z0.e()) {
                            StringBuilder sb = new StringBuilder("upload in mobile once today. lastUpT=");
                            sb.append(longValue);
                            sb.append(",curT=");
                            sb.append(currentTimeMillis);
                        }
                    }
                }
                z2 = z3;
            }
            if (z0.e()) {
                StringBuilder sb2 = new StringBuilder("network status:");
                sb2.append(c2);
                sb2.append(",isUpload:");
                sb2.append(z2);
            }
            if (z2 && A()) {
                t.k(handler, 103);
                this.r = currentTimeMillis;
                if (z0.e()) {
                    new StringBuilder("send upload msg, last upload time:").append(this.r);
                }
            }
        } catch (Throwable unused) {
        }
    }

    public final void r(File file, String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        d.d("th_loc_task_t_consume", new b(this, file, str));
    }

    public void s(String str, String str2) {
        if ("D_UP_INTERVAL".equals(str)) {
            this.f360h = Math.max(900000L, Long.parseLong(str2));
        } else if ("D_UP_USE_HTTPS".equals(str)) {
            a2.f291e = Boolean.parseBoolean(str2);
        } else if ("D_MAX_1F_SIZE".equals(str)) {
            this.f359g = i(Long.parseLong(str2), FileUtil.LOCAL_REPORT_FILE_MAX_SIZE, 512000L);
        } else if ("D_NUM_UP".equals(str)) {
            this.f361i = (int) i(Long.parseLong(str2), 1L, 5L);
        } else if ("D_MAX_BUF_WF".equals(str)) {
            this.f362j = (int) i(Long.parseLong(str2), 5120L, 51200L);
        } else if ("D_MAX_FOLDER_SIZE".equals(str)) {
            this.f363k = i(Long.parseLong(str2), 10485760L, 209715200L);
        } else if ("D_MAX_SIZE_UP_1DAY".equals(str)) {
            this.f364l = Math.max(Long.parseLong(str2), 0L);
        } else if ("D_MAX_DAY_RENAME".equals(str)) {
            this.f365m = i(Long.parseLong(str2), 1L, 5L) * 24 * 60 * 60 * 1000;
        } else if ("D_MAX_DAY_DELETE".equals(str)) {
            this.f366n = i(Long.parseLong(str2), 1L, 30L) * 24 * 60 * 60 * 1000;
        } else if ("D_UP_WF_INFO".equals(str)) {
            this.o = Boolean.parseBoolean(str2);
        } else if ("D_UP_U_TRACK_INFO".equals(str)) {
            this.p = Boolean.parseBoolean(str2);
        } else if ("D_UP_GPS_FOR_NAVI".equals(str)) {
            this.q = Boolean.parseBoolean(str2);
        }
    }

    public void t(List<ScanResult> list) {
        d1 d1Var = this.v;
        if (d1Var != null) {
            d1Var.p(list);
        }
    }
}
