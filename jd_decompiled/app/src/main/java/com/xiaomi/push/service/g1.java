package com.xiaomi.push.service;

import android.content.Context;
import com.xiaomi.push.g7;
import com.xiaomi.push.u9;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileLock;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public class g1 implements Runnable {

    /* renamed from: g  reason: collision with root package name */
    final /* synthetic */ Context f19086g;

    /* renamed from: h  reason: collision with root package name */
    final /* synthetic */ g7 f19087h;

    /* JADX INFO: Access modifiers changed from: package-private */
    public g1(Context context, g7 g7Var) {
        this.f19086g = context;
        this.f19087h = g7Var;
    }

    @Override // java.lang.Runnable
    public void run() {
        RandomAccessFile randomAccessFile;
        synchronized (f1.a) {
            FileLock fileLock = null;
            try {
                try {
                    File file = new File(this.f19086g.getFilesDir(), "tiny_data.lock");
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
                        f1.e(this.f19086g, this.f19087h);
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
}
