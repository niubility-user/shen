package com.xiaomi.push;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.xiaomi.push.i;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileLock;

/* loaded from: classes11.dex */
public abstract class x2 extends i.a {

    /* renamed from: g  reason: collision with root package name */
    protected int f19303g;

    /* renamed from: h  reason: collision with root package name */
    protected Context f19304h;

    public x2(Context context, int i2) {
        this.f19303g = i2;
        this.f19304h = context;
    }

    public static void d(Context context, k7 k7Var) {
        q2 a = r2.b().a();
        String a2 = a == null ? "" : a.a();
        if (TextUtils.isEmpty(a2) || TextUtils.isEmpty(k7Var.a())) {
            return;
        }
        e(context, k7Var, a2);
    }

    private static void e(Context context, k7 k7Var, String str) {
        BufferedOutputStream bufferedOutputStream;
        RandomAccessFile randomAccessFile;
        byte[] d = t2.d(str, m8.f(k7Var));
        if (d == null || d.length == 0) {
            return;
        }
        synchronized (u2.a) {
            FileLock fileLock = null;
            try {
                try {
                    File file = new File(context.getExternalFilesDir(null), "push_cdata.lock");
                    u9.f(file);
                    randomAccessFile = new RandomAccessFile(file, "rw");
                    try {
                        FileLock lock = randomAccessFile.getChannel().lock();
                        try {
                            File file2 = new File(context.getExternalFilesDir(null), "push_cdata.data");
                            bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file2, true));
                            try {
                                bufferedOutputStream.write(c.b(d.length));
                                bufferedOutputStream.write(d);
                                bufferedOutputStream.flush();
                                file2.setLastModified(0L);
                                if (lock != null && lock.isValid()) {
                                    try {
                                        lock.release();
                                    } catch (IOException unused) {
                                    }
                                }
                                u9.b(bufferedOutputStream);
                            } catch (IOException e2) {
                                e = e2;
                                fileLock = lock;
                                try {
                                    e.printStackTrace();
                                    if (fileLock != null && fileLock.isValid()) {
                                        try {
                                            fileLock.release();
                                        } catch (IOException unused2) {
                                        }
                                    }
                                    u9.b(bufferedOutputStream);
                                    u9.b(randomAccessFile);
                                } catch (Throwable th) {
                                    th = th;
                                    if (fileLock != null && fileLock.isValid()) {
                                        try {
                                            fileLock.release();
                                        } catch (IOException unused3) {
                                        }
                                    }
                                    u9.b(bufferedOutputStream);
                                    u9.b(randomAccessFile);
                                    throw th;
                                }
                            } catch (Throwable th2) {
                                th = th2;
                                fileLock = lock;
                                if (fileLock != null) {
                                    fileLock.release();
                                }
                                u9.b(bufferedOutputStream);
                                u9.b(randomAccessFile);
                                throw th;
                            }
                        } catch (IOException e3) {
                            e = e3;
                            bufferedOutputStream = null;
                        } catch (Throwable th3) {
                            th = th3;
                            bufferedOutputStream = null;
                        }
                    } catch (IOException e4) {
                        e = e4;
                        bufferedOutputStream = null;
                    } catch (Throwable th4) {
                        th = th4;
                        bufferedOutputStream = null;
                    }
                } catch (IOException e5) {
                    e = e5;
                    bufferedOutputStream = null;
                    randomAccessFile = null;
                } catch (Throwable th5) {
                    th = th5;
                    bufferedOutputStream = null;
                    randomAccessFile = null;
                }
                u9.b(randomAccessFile);
            } catch (Throwable th6) {
                throw th6;
            }
        }
    }

    private String i() {
        return "dc_job_result_time_" + b();
    }

    private String k() {
        return "dc_job_result_" + b();
    }

    public abstract e7 c();

    protected boolean f() {
        return t2.b(this.f19304h, String.valueOf(b()), this.f19303g);
    }

    public abstract String g();

    protected boolean h() {
        return true;
    }

    protected boolean j() {
        return false;
    }

    @Override // java.lang.Runnable
    public void run() {
        String g2 = g();
        if (TextUtils.isEmpty(g2)) {
            return;
        }
        if (f()) {
            g.j.a.a.a.c.o("DC run job mutual: " + b());
            return;
        }
        q2 a = r2.b().a();
        String a2 = a == null ? "" : a.a();
        if (!TextUtils.isEmpty(a2) && h()) {
            if (j()) {
                SharedPreferences sharedPreferences = this.f19304h.getSharedPreferences("mipush_extra", 0);
                if (p0.b(g2).equals(sharedPreferences.getString(k(), null))) {
                    long j2 = sharedPreferences.getLong(i(), 0L);
                    int a3 = com.xiaomi.push.service.b0.d(this.f19304h).a(h7.DCJobUploadRepeatedInterval.a(), 604800);
                    if ((System.currentTimeMillis() - j2) / 1000 < this.f19303g) {
                        return;
                    }
                    if ((System.currentTimeMillis() - j2) / 1000 < a3) {
                        g2 = "same_" + j2;
                    }
                }
            }
            k7 k7Var = new k7();
            k7Var.a(g2);
            k7Var.a(System.currentTimeMillis());
            k7Var.a(c());
            e(this.f19304h, k7Var, a2);
        }
    }
}
