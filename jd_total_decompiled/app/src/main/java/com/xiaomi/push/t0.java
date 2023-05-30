package com.xiaomi.push;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileLock;
import java.util.Arrays;

/* loaded from: classes11.dex */
public class t0 {
    public static String a() {
        return Build.VERSION.RELEASE + "-" + Build.VERSION.INCREMENTAL;
    }

    public static String b(Context context) {
        String c2 = w0.b(context).c("sp_client_report_status", "sp_client_report_key", "");
        if (TextUtils.isEmpty(c2)) {
            String a = p0.a(20);
            w0.b(context).e("sp_client_report_status", "sp_client_report_key", a);
            return a;
        }
        return c2;
    }

    /* JADX WARN: Code restructure failed: missing block: B:60:0x00e4, code lost:
        if (r7 == null) goto L62;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static void c(Context context, String str, String str2) {
        File file;
        RandomAccessFile randomAccessFile;
        Exception e2;
        File externalFilesDir = context.getExternalFilesDir(str2);
        if (externalFilesDir != null) {
            if (!externalFilesDir.exists()) {
                externalFilesDir.mkdirs();
            }
            File externalFilesDir2 = context.getExternalFilesDir(str);
            if (externalFilesDir2 != null) {
                if (!externalFilesDir2.exists()) {
                    externalFilesDir2.mkdirs();
                    return;
                }
                File[] listFiles = externalFilesDir2.listFiles(new u0());
                if (listFiles == null || listFiles.length <= 0) {
                    return;
                }
                long currentTimeMillis = System.currentTimeMillis();
                FileLock fileLock = null;
                RandomAccessFile randomAccessFile2 = null;
                File file2 = null;
                for (File file3 : listFiles) {
                    if (file3 != null) {
                        try {
                        } catch (Exception e3) {
                            file = file2;
                            randomAccessFile = randomAccessFile2;
                            e2 = e3;
                        } catch (Throwable th) {
                            th = th;
                        }
                        if (!TextUtils.isEmpty(file3.getAbsolutePath())) {
                            file = new File(file3.getAbsolutePath() + ".lock");
                            try {
                                u9.f(file);
                                randomAccessFile = new RandomAccessFile(file, "rw");
                            } catch (Exception e4) {
                                randomAccessFile = randomAccessFile2;
                                e2 = e4;
                            } catch (Throwable th2) {
                                th = th2;
                            }
                            try {
                                try {
                                    fileLock = randomAccessFile.getChannel().lock();
                                    File file4 = new File(externalFilesDir.getAbsolutePath() + File.separator + file3.getName() + currentTimeMillis);
                                    try {
                                        u9.i(file3, file4);
                                    } catch (IOException e5) {
                                        e5.printStackTrace();
                                        file3.delete();
                                        file4.delete();
                                    }
                                    file3.delete();
                                    if (fileLock != null && fileLock.isValid()) {
                                        try {
                                            fileLock.release();
                                        } catch (IOException e6) {
                                            g.j.a.a.a.c.s(e6);
                                        }
                                    }
                                    u9.b(randomAccessFile);
                                } catch (Throwable th3) {
                                    th = th3;
                                    randomAccessFile2 = randomAccessFile;
                                    file2 = file;
                                    if (fileLock != null && fileLock.isValid()) {
                                        try {
                                            fileLock.release();
                                        } catch (IOException e7) {
                                            g.j.a.a.a.c.s(e7);
                                        }
                                    }
                                    u9.b(randomAccessFile2);
                                    if (file2 != null) {
                                        file2.delete();
                                    }
                                    throw th;
                                }
                            } catch (Exception e8) {
                                e2 = e8;
                                g.j.a.a.a.c.s(e2);
                                if (fileLock != null && fileLock.isValid()) {
                                    try {
                                        fileLock.release();
                                    } catch (IOException e9) {
                                        g.j.a.a.a.c.s(e9);
                                    }
                                }
                                u9.b(randomAccessFile);
                            }
                            file.delete();
                            randomAccessFile2 = randomAccessFile;
                            file2 = file;
                        }
                    }
                    if (fileLock != null && fileLock.isValid()) {
                        try {
                            fileLock.release();
                        } catch (IOException e10) {
                            g.j.a.a.a.c.s(e10);
                        }
                    }
                    u9.b(randomAccessFile2);
                    if (file2 != null) {
                        file2.delete();
                    }
                }
            }
        }
    }

    public static boolean d(Context context, String str) {
        File file = new File(str);
        long d = g.j.b.b.b.e(context).c().d();
        if (file.exists()) {
            try {
                if (file.length() > d) {
                    return false;
                }
            } catch (Exception e2) {
                g.j.a.a.a.c.s(e2);
                return false;
            }
        } else {
            u9.f(file);
        }
        return true;
    }

    @TargetApi(9)
    public static byte[] e(String str) {
        byte[] copyOf = Arrays.copyOf(m0.b(str), 16);
        copyOf[0] = 68;
        copyOf[15] = 84;
        return copyOf;
    }

    public static File[] f(Context context, String str) {
        File externalFilesDir = context.getExternalFilesDir(str);
        if (externalFilesDir != null) {
            return externalFilesDir.listFiles(new v0());
        }
        return null;
    }
}
