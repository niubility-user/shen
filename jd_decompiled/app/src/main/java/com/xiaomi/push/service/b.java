package com.xiaomi.push.service;

import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.push.u9;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileLock;

/* loaded from: classes11.dex */
public class b {

    /* renamed from: f  reason: collision with root package name */
    private static volatile b f19057f;
    private final Object a = new Object();
    private final Object b = new Object();

    /* renamed from: c  reason: collision with root package name */
    private volatile String f19058c;
    private volatile String d;

    /* renamed from: e  reason: collision with root package name */
    private Context f19059e;

    public b(Context context) {
        this.f19059e = context;
    }

    public static b a(Context context) {
        if (f19057f == null) {
            synchronized (b.class) {
                if (f19057f == null) {
                    f19057f = new b(context);
                }
            }
        }
        return f19057f;
    }

    private String c(Context context, String str, String str2, Object obj) {
        RandomAccessFile randomAccessFile;
        FileLock fileLock;
        File file = new File(context.getFilesDir(), str);
        FileLock fileLock2 = null;
        if (!file.exists()) {
            g.j.a.a.a.c.o("No ready file to get data from " + str);
            return null;
        }
        synchronized (obj) {
            try {
                File file2 = new File(context.getFilesDir(), str2);
                u9.f(file2);
                randomAccessFile = new RandomAccessFile(file2, "rw");
                try {
                    fileLock = randomAccessFile.getChannel().lock();
                } catch (Exception e2) {
                    e = e2;
                    fileLock = null;
                } catch (Throwable th) {
                    th = th;
                    if (fileLock2 != null) {
                        try {
                            fileLock2.release();
                        } catch (IOException e3) {
                            g.j.a.a.a.c.s(e3);
                        }
                    }
                    u9.b(randomAccessFile);
                    throw th;
                }
            } catch (Exception e4) {
                e = e4;
                randomAccessFile = null;
                fileLock = null;
            } catch (Throwable th2) {
                th = th2;
                randomAccessFile = null;
            }
            try {
                try {
                    String a = u9.a(file);
                    if (fileLock != null && fileLock.isValid()) {
                        try {
                            fileLock.release();
                        } catch (IOException e5) {
                            g.j.a.a.a.c.s(e5);
                        }
                    }
                    u9.b(randomAccessFile);
                    return a;
                } catch (Throwable th3) {
                    th = th3;
                    fileLock2 = fileLock;
                    if (fileLock2 != null && fileLock2.isValid()) {
                        fileLock2.release();
                    }
                    u9.b(randomAccessFile);
                    throw th;
                }
            } catch (Exception e6) {
                e = e6;
                g.j.a.a.a.c.s(e);
                if (fileLock != null && fileLock.isValid()) {
                    try {
                        fileLock.release();
                    } catch (IOException e7) {
                        g.j.a.a.a.c.s(e7);
                    }
                }
                u9.b(randomAccessFile);
                return null;
            }
        }
    }

    private void d(Context context, String str, String str2, String str3, Object obj) {
        RandomAccessFile randomAccessFile;
        synchronized (obj) {
            FileLock fileLock = null;
            try {
                try {
                    File file = new File(context.getFilesDir(), str3);
                    u9.f(file);
                    randomAccessFile = new RandomAccessFile(file, "rw");
                } catch (Exception e2) {
                    e = e2;
                    randomAccessFile = null;
                } catch (Throwable th) {
                    th = th;
                    randomAccessFile = null;
                    if (fileLock != null) {
                        try {
                            fileLock.release();
                        } catch (IOException e3) {
                            g.j.a.a.a.c.s(e3);
                        }
                    }
                    u9.b(randomAccessFile);
                    throw th;
                }
                try {
                    try {
                        fileLock = randomAccessFile.getChannel().lock();
                        u9.d(new File(context.getFilesDir(), str2), str);
                        if (fileLock != null && fileLock.isValid()) {
                            try {
                                fileLock.release();
                            } catch (IOException e4) {
                                g.j.a.a.a.c.s(e4);
                            }
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        if (fileLock != null && fileLock.isValid()) {
                            fileLock.release();
                        }
                        u9.b(randomAccessFile);
                        throw th;
                    }
                } catch (Exception e5) {
                    e = e5;
                    g.j.a.a.a.c.s(e);
                    if (fileLock != null && fileLock.isValid()) {
                        try {
                            fileLock.release();
                        } catch (IOException e6) {
                            g.j.a.a.a.c.s(e6);
                        }
                    }
                    u9.b(randomAccessFile);
                }
                u9.b(randomAccessFile);
            } catch (Throwable th3) {
                throw th3;
            }
        }
    }

    public String b() {
        if (TextUtils.isEmpty(this.f19058c)) {
            this.f19058c = c(this.f19059e, "mipush_region", "mipush_region.lock", this.a);
        }
        return this.f19058c;
    }

    public void e(String str, boolean z) {
        if (!TextUtils.equals(str, this.f19058c)) {
            this.f19058c = str;
        }
        if (z) {
            d(this.f19059e, str, "mipush_region", "mipush_region.lock", this.a);
        }
    }

    public String f() {
        if (TextUtils.isEmpty(this.d)) {
            this.d = c(this.f19059e, "mipush_country_code", "mipush_country_code.lock", this.b);
        }
        return this.d;
    }

    public void g(String str, boolean z) {
        if (!TextUtils.equals(str, this.d)) {
            this.d = str;
        }
        if (z) {
            d(this.f19059e, str, "mipush_country_code", "mipush_region.lock", this.a);
        }
    }
}
