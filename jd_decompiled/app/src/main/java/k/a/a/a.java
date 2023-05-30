package k.a.a;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import k.a.a.e.a.g;
import k.a.a.f.h;
import k.a.a.f.n;
import k.a.a.f.o.e;
import k.a.a.g.a;
import k.a.a.h.d;
import k.a.a.h.e;
import k.a.a.i.c;

/* loaded from: classes11.dex */
public class a {
    private File a;
    private n b;

    /* renamed from: c  reason: collision with root package name */
    private boolean f20180c;
    private k.a.a.g.a d;

    /* renamed from: e  reason: collision with root package name */
    private boolean f20181e;

    /* renamed from: f  reason: collision with root package name */
    private char[] f20182f;

    /* renamed from: g  reason: collision with root package name */
    private Charset f20183g;

    /* renamed from: h  reason: collision with root package name */
    private ThreadFactory f20184h;

    /* renamed from: i  reason: collision with root package name */
    private ExecutorService f20185i;

    public a(String str) {
        this(new File(str), null);
    }

    private d.a a() {
        if (this.f20181e) {
            if (this.f20184h == null) {
                this.f20184h = Executors.defaultThreadFactory();
            }
            this.f20185i = Executors.newSingleThreadExecutor(this.f20184h);
        }
        return new d.a(this.f20185i, this.f20181e, this.d);
    }

    private void b() {
        n nVar = new n();
        this.b = nVar;
        nVar.n(this.a);
    }

    private RandomAccessFile e() throws IOException {
        if (c.j(this.a)) {
            g gVar = new g(this.a, e.READ.getValue(), c.d(this.a));
            gVar.g();
            return gVar;
        }
        return new RandomAccessFile(this.a, e.READ.getValue());
    }

    private void g() throws k.a.a.c.a {
        if (this.b != null) {
            return;
        }
        if (!this.a.exists()) {
            b();
        } else if (this.a.canRead()) {
            try {
                RandomAccessFile e2 = e();
                try {
                    n g2 = new k.a.a.d.a().g(e2, this.f20183g);
                    this.b = g2;
                    g2.n(this.a);
                    if (e2 != null) {
                        e2.close();
                    }
                } catch (Throwable th) {
                    try {
                        throw th;
                    } catch (Throwable th2) {
                        if (e2 != null) {
                            try {
                                e2.close();
                            } catch (Throwable unused) {
                            }
                        }
                        throw th2;
                    }
                }
            } catch (k.a.a.c.a e3) {
                throw e3;
            } catch (IOException e4) {
                throw new k.a.a.c.a(e4);
            }
        } else {
            throw new k.a.a.c.a("no read access for the input zip file");
        }
    }

    public void c(String str) throws k.a.a.c.a {
        if (k.a.a.i.g.f(str)) {
            if (k.a.a.i.g.b(new File(str))) {
                if (this.b == null) {
                    g();
                }
                if (this.b != null) {
                    if (this.d.d() != a.b.BUSY) {
                        new k.a.a.h.e(this.b, this.f20182f, a()).b(new e.a(str, this.f20183g));
                        return;
                    }
                    throw new k.a.a.c.a("invalid operation - Zip4j is in busy state");
                }
                throw new k.a.a.c.a("Internal error occurred when extracting zip file");
            }
            throw new k.a.a.c.a("invalid output path");
        }
        throw new k.a.a.c.a("output path is null or invalid");
    }

    public File d() {
        return this.a;
    }

    public boolean f() throws k.a.a.c.a {
        if (this.b == null) {
            g();
            if (this.b == null) {
                throw new k.a.a.c.a("Zip Model is null");
            }
        }
        if (this.b.a() != null && this.b.a().a() != null) {
            Iterator<h> it = this.b.a().a().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                h next = it.next();
                if (next != null && next.q()) {
                    this.f20180c = true;
                    break;
                }
            }
            return this.f20180c;
        }
        throw new k.a.a.c.a("invalid zip file");
    }

    public void h(char[] cArr) {
        this.f20182f = cArr;
    }

    public String toString() {
        return this.a.toString();
    }

    public a(File file) {
        this(file, null);
    }

    public a(File file, char[] cArr) {
        this.f20183g = k.a.a.i.d.b;
        this.a = file;
        this.f20182f = cArr;
        this.f20181e = false;
        this.d = new k.a.a.g.a();
    }
}
