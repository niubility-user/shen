package c.t.m.g;

import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.sdk.platform.business.personal.R2;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

/* loaded from: classes.dex */
public class e4 {
    public byte[] a;
    public File b;

    /* renamed from: c  reason: collision with root package name */
    public FileOutputStream f390c;
    public BufferedOutputStream d;

    /* renamed from: e  reason: collision with root package name */
    public StringBuilder f391e;

    /* renamed from: f  reason: collision with root package name */
    public m4 f392f;

    /* renamed from: g  reason: collision with root package name */
    public String f393g;

    /* renamed from: h  reason: collision with root package name */
    public int f394h;

    /* renamed from: i  reason: collision with root package name */
    public boolean f395i;

    /* renamed from: j  reason: collision with root package name */
    public long f396j;

    /* renamed from: k  reason: collision with root package name */
    public String f397k;

    /* renamed from: l  reason: collision with root package name */
    public boolean f398l;

    /* renamed from: m  reason: collision with root package name */
    public boolean f399m;

    /* renamed from: n  reason: collision with root package name */
    public int f400n;
    public int o;

    /* loaded from: classes.dex */
    public class a implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        public final /* synthetic */ String f401g;

        public a(String str) {
            this.f401g = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                StringBuilder sb = new StringBuilder();
                String str = this.f401g;
                sb.append(str.substring(0, str.length() - e4.this.f397k.length()));
                sb.append(".gzip");
                f4.a(new File(this.f401g), new File(sb.toString()), true);
            } catch (Throwable unused) {
            }
        }
    }

    public e4(File file) {
        this(file, R2.dimen.HugerTextSize);
    }

    public e4(File file, int i2) {
        this.a = new byte[0];
        this.f393g = "";
        this.f394h = 0;
        this.f395i = false;
        this.f396j = Long.MAX_VALUE;
        this.f397k = "";
        this.f398l = false;
        this.f399m = false;
        this.f400n = 1;
        this.o = 0;
        d(file, i2);
    }

    public void b() {
        synchronized (this.a) {
            if (this.d == null) {
                return;
            }
            f(this.f391e.toString().getBytes("UTF-8"));
            this.f391e.setLength(0);
            if (z0.e()) {
                StringBuilder sb = new StringBuilder();
                sb.append(this.b.getAbsolutePath());
                sb.append(" close(). length=");
                sb.append(this.b.length());
            }
            this.d.close();
            this.f390c.close();
            if (this.f395i && this.f398l) {
                h();
            }
            this.f400n = 1;
            this.d = null;
            this.f390c = null;
        }
    }

    public void c(m4 m4Var) {
        synchronized (this.a) {
            this.f392f = m4Var;
        }
    }

    public final void d(File file, int i2) {
        this.b = file;
        File parentFile = file == null ? null : file.getParentFile();
        if ((parentFile == null || !parentFile.exists()) && !parentFile.mkdirs()) {
            return;
        }
        this.f393g = file.getAbsolutePath();
        this.f394h = i2;
        if (z0.e()) {
            StringBuilder sb = new StringBuilder("create file:");
            sb.append(file.getAbsolutePath());
            sb.append(",bufSize:");
            sb.append(i2);
        }
        this.f391e = new StringBuilder(i2);
        this.f390c = new FileOutputStream(file, true);
        this.d = new BufferedOutputStream(this.f390c, R2.dimen.HugerTextSize);
    }

    public void e(String str) {
        synchronized (this.a) {
            StringBuilder sb = this.f391e;
            if (sb != null) {
                sb.append(str);
                if (this.f391e.length() >= this.f394h) {
                    f(this.f391e.toString().getBytes("UTF-8"));
                    this.f391e.setLength(0);
                }
            }
        }
    }

    public void f(byte[] bArr) {
        synchronized (this.a) {
            if (this.d == null) {
                return;
            }
            m4 m4Var = this.f392f;
            this.d.write(m4Var == null ? bArr : m4Var.a(bArr));
            if (this.f395i) {
                int length = this.o + bArr.length;
                this.o = length;
                if (length >= 5120) {
                    this.o = 0;
                    File g2 = g();
                    if ((g2 == null ? 0L : g2.length()) >= this.f396j) {
                        this.d.close();
                        this.f390c.close();
                        h();
                        d(new File(this.f393g), this.f394h);
                    }
                }
            }
        }
    }

    public File g() {
        File file;
        synchronized (this.a) {
            file = this.b;
        }
        return file;
    }

    public final void h() {
        File file = new File(this.f393g + CartConstant.KEY_YB_INFO_LINK + this.f400n + this.f397k);
        while (file.exists()) {
            this.f400n++;
            file = new File(this.f393g + CartConstant.KEY_YB_INFO_LINK + this.f400n + this.f397k);
        }
        boolean renameTo = this.b.renameTo(file);
        if (z0.e()) {
            StringBuilder sb = new StringBuilder("rename ");
            sb.append(this.b.getName());
            sb.append(" to ");
            sb.append(file.getName());
            sb.append(": ");
            sb.append(renameTo);
        }
        String absolutePath = file.getAbsolutePath();
        if (this.f399m && !t2.c(absolutePath)) {
            z0.e();
            new Thread(new a(absolutePath), "th_loc_tmp").start();
        }
        this.f400n++;
    }
}
