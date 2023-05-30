package com.tencent.open.log;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.text.TextUtils;
import com.tencent.tauth.Tencent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/* loaded from: classes9.dex */
public class a extends Tracer implements Handler.Callback {
    private b a;
    private FileWriter b;

    /* renamed from: c */
    private File f17671c;
    private char[] d;

    /* renamed from: e */
    private volatile f f17672e;

    /* renamed from: f */
    private volatile f f17673f;

    /* renamed from: g */
    private volatile f f17674g;

    /* renamed from: h */
    private volatile f f17675h;

    /* renamed from: i */
    private volatile boolean f17676i;

    /* renamed from: j */
    private HandlerThread f17677j;

    /* renamed from: k */
    private Handler f17678k;

    public a(b bVar) {
        this(c.b, true, g.a, bVar);
    }

    private void f() {
        if (Thread.currentThread() == this.f17677j && !this.f17676i) {
            this.f17676i = true;
            i();
            try {
                try {
                    this.f17675h.a(g(), this.d);
                } catch (IOException e2) {
                    SLog.e("FileTracer", "flushBuffer exception", e2);
                }
                this.f17676i = false;
            } finally {
                this.f17675h.b();
            }
        }
    }

    private Writer g() {
        File a = c().a();
        if (a != null && ((a != null && !a.equals(this.f17671c)) || (this.b == null && a != null))) {
            this.f17671c = a;
            h();
            try {
                this.b = new FileWriter(this.f17671c, true);
            } catch (IOException unused) {
                this.b = null;
                SLog.e(SLog.TAG, "-->obtainFileWriter() app specific file permission denied");
            }
            a(a);
        }
        return this.b;
    }

    private void h() {
        try {
            FileWriter fileWriter = this.b;
            if (fileWriter != null) {
                fileWriter.flush();
                this.b.close();
            }
        } catch (IOException e2) {
            SLog.e(SLog.TAG, "-->closeAppSpecificFileWriter() exception:", e2);
        }
    }

    private void i() {
        synchronized (this) {
            if (this.f17674g == this.f17672e) {
                this.f17674g = this.f17673f;
                this.f17675h = this.f17672e;
            } else {
                this.f17674g = this.f17672e;
                this.f17675h = this.f17673f;
            }
        }
    }

    public void a() {
        if (this.f17678k.hasMessages(1024)) {
            this.f17678k.removeMessages(1024);
        }
        this.f17678k.sendEmptyMessage(1024);
    }

    public void b() {
        h();
        this.f17677j.quit();
    }

    public b c() {
        return this.a;
    }

    @Override // com.tencent.open.log.Tracer
    protected void doTrace(int i2, Thread thread, long j2, String str, String str2, Throwable th) {
        a(e().a(i2, thread, j2, str, str2, th));
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message message) {
        if (message.what != 1024) {
            return true;
        }
        f();
        return true;
    }

    public a(int i2, boolean z, g gVar, b bVar) {
        super(i2, z, gVar);
        this.f17676i = false;
        a(bVar);
        this.f17672e = new f();
        this.f17673f = new f();
        this.f17674g = this.f17672e;
        this.f17675h = this.f17673f;
        this.d = new char[bVar.c()];
        HandlerThread handlerThread = new HandlerThread(bVar.b(), bVar.d());
        this.f17677j = handlerThread;
        if (handlerThread != null) {
            handlerThread.start();
        }
        if (!this.f17677j.isAlive() || this.f17677j.getLooper() == null) {
            return;
        }
        this.f17678k = new Handler(this.f17677j.getLooper(), this);
    }

    private boolean b(File file) {
        if (file == null) {
            return false;
        }
        String name = file.getName();
        SLog.d("FileTracer", "name=" + name);
        return !TextUtils.isEmpty(name) && name.length() == 47 && name.startsWith("com.tencent.mobileqq_connectSdk.") && name.endsWith(".log");
    }

    private void a(String str) {
        this.f17674g.a(str);
        if (this.f17674g.a() >= c().c()) {
            a();
        }
    }

    private void a(File file) {
        File[] listFiles;
        File parentFile = file.getParentFile();
        if (parentFile == null || !parentFile.exists() || !parentFile.isDirectory() || (listFiles = parentFile.listFiles()) == null) {
            return;
        }
        for (File file2 : listFiles) {
            if (b(file2)) {
                String name = file2.getName();
                if (b.a(System.currentTimeMillis() - (Tencent.USE_ONE_HOUR ? 3600000L : 259200000L)).compareTo(name.substring(32, 43)) > 0) {
                    SLog.d("FileTracer", "delete name=" + name + ", success=" + file2.delete());
                }
            }
        }
    }

    public void a(b bVar) {
        this.a = bVar;
    }
}
