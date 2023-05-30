package com.xiaomi.push;

import android.content.Context;
import android.content.SharedPreferences;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileLock;
import java.util.ArrayList;

/* loaded from: classes11.dex */
public class x6 {
    private static boolean a;

    /* loaded from: classes11.dex */
    public static class a implements Runnable {

        /* renamed from: g */
        private Context f19308g;

        /* renamed from: h */
        private b7 f19309h;

        public a(Context context, b7 b7Var) {
            this.f19309h = b7Var;
            this.f19308g = context;
        }

        @Override // java.lang.Runnable
        public void run() {
            x6.f(this.f19308g, this.f19309h);
        }
    }

    private static void a(Context context) {
        File file = new File(context.getFilesDir() + "/tdReadTemp");
        if (file.exists()) {
            return;
        }
        file.mkdirs();
    }

    public static void b(Context context, b7 b7Var) {
        i.b(context).g(new a(context, b7Var));
    }

    private static void c(Context context, b7 b7Var, File file, byte[] bArr) {
        String str;
        int a2;
        ArrayList arrayList = new ArrayList();
        byte[] bArr2 = new byte[4];
        BufferedInputStream bufferedInputStream = null;
        try {
            try {
                BufferedInputStream bufferedInputStream2 = new BufferedInputStream(new FileInputStream(file));
                loop0: while (true) {
                    int i2 = 0;
                    int i3 = 0;
                    while (true) {
                        try {
                            int read = bufferedInputStream2.read(bArr2);
                            if (read == -1) {
                                break loop0;
                            } else if (read == 4) {
                                a2 = c.a(bArr2);
                                if (a2 < 1 || a2 > 10240) {
                                    break loop0;
                                }
                                byte[] bArr3 = new byte[a2];
                                int read2 = bufferedInputStream2.read(bArr3);
                                if (read2 != a2) {
                                    str = "TinyData read from cache file failed cause buffer size not equal length. size:" + read2 + "__length:" + a2;
                                    break loop0;
                                }
                                byte[] b = y5.b(bArr, bArr3);
                                if (b != null && b.length != 0) {
                                    g7 g7Var = new g7();
                                    m8.e(g7Var, b);
                                    g7Var.a("item_size", String.valueOf(b.length));
                                    arrayList.add(g7Var);
                                    i2++;
                                    i3 += b.length;
                                    if (i2 >= 8 || i3 >= 10240) {
                                    }
                                }
                                g.j.a.a.a.c.D("TinyData read from cache file failed cause decrypt fail");
                            } else {
                                str = "TinyData read from cache file failed cause lengthBuffer error. size:" + read;
                                break loop0;
                            }
                        } catch (Exception e2) {
                            e = e2;
                            bufferedInputStream = bufferedInputStream2;
                            g.j.a.a.a.c.s(e);
                            u9.b(bufferedInputStream);
                            return;
                        } catch (Throwable th) {
                            th = th;
                            bufferedInputStream = bufferedInputStream2;
                            u9.b(bufferedInputStream);
                            throw th;
                        }
                    }
                    y6.c(context, b7Var, arrayList);
                    arrayList.clear();
                }
                str = "TinyData read from cache file failed cause lengthBuffer < 1 || too big. length:" + a2;
                g.j.a.a.a.c.D(str);
                y6.c(context, b7Var, arrayList);
                if (file != null && file.exists() && !file.delete()) {
                    g.j.a.a.a.c.o("TinyData delete reading temp file failed");
                }
                u9.b(bufferedInputStream2);
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Exception e3) {
            e = e3;
        }
    }

    private static void d(Context context) {
        SharedPreferences.Editor edit = context.getSharedPreferences("mipush_extra", 4).edit();
        edit.putLong("last_tiny_data_upload_timestamp", System.currentTimeMillis() / 1000);
        edit.commit();
    }

    /* JADX WARN: Removed duplicated region for block: B:158:0x00b7  */
    /* JADX WARN: Removed duplicated region for block: B:160:0x00bb  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static void f(Context context, b7 b7Var) {
        RandomAccessFile randomAccessFile;
        File file;
        if (a) {
            g.j.a.a.a.c.o("TinyData extractTinyData is running");
            return;
        }
        a = true;
        File file2 = new File(context.getFilesDir(), "tiny_data.data");
        if (!file2.exists()) {
            g.j.a.a.a.c.o("TinyData no ready file to get data.");
            return;
        }
        a(context);
        byte[] b = com.xiaomi.push.service.f1.b(context);
        FileLock fileLock = null;
        try {
            try {
                File file3 = new File(context.getFilesDir(), "tiny_data.lock");
                u9.f(file3);
                randomAccessFile = new RandomAccessFile(file3, "rw");
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
                fileLock = randomAccessFile.getChannel().lock();
                file2.renameTo(new File(context.getFilesDir() + "/tdReadTemp/tiny_data.data"));
                if (fileLock != null && fileLock.isValid()) {
                    try {
                        fileLock.release();
                    } catch (IOException e4) {
                        e = e4;
                        g.j.a.a.a.c.s(e);
                        u9.b(randomAccessFile);
                        file = new File(context.getFilesDir() + "/tdReadTemp/tiny_data.data");
                        if (file.exists()) {
                        }
                    }
                }
            } catch (Exception e5) {
                e = e5;
                g.j.a.a.a.c.s(e);
                if (fileLock != null && fileLock.isValid()) {
                    try {
                        fileLock.release();
                    } catch (IOException e6) {
                        e = e6;
                        g.j.a.a.a.c.s(e);
                        u9.b(randomAccessFile);
                        file = new File(context.getFilesDir() + "/tdReadTemp/tiny_data.data");
                        if (file.exists()) {
                        }
                    }
                }
                u9.b(randomAccessFile);
                file = new File(context.getFilesDir() + "/tdReadTemp/tiny_data.data");
                if (file.exists()) {
                }
            }
            u9.b(randomAccessFile);
            file = new File(context.getFilesDir() + "/tdReadTemp/tiny_data.data");
            if (file.exists()) {
                g.j.a.a.a.c.o("TinyData no ready file to get data.");
                return;
            }
            c(context, b7Var, file, b);
            w6.c(false);
            d(context);
            a = false;
        } catch (Throwable th2) {
            th = th2;
            if (fileLock != null && fileLock.isValid()) {
                fileLock.release();
            }
            u9.b(randomAccessFile);
            throw th;
        }
    }
}
