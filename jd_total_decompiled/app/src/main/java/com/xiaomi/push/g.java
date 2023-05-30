package com.xiaomi.push;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.jd.dynamic.DYConstants;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileLock;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes11.dex */
public class g {
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v0 */
    /* JADX WARN: Type inference failed for: r2v1 */
    /* JADX WARN: Type inference failed for: r2v3, types: [java.io.Closeable] */
    public static boolean a(Context context, String str, long j2) {
        RandomAccessFile randomAccessFile;
        ?? r2 = 23;
        if (Build.VERSION.SDK_INT < 23 || y4.p(context, "android.permission.WRITE_EXTERNAL_STORAGE")) {
            FileLock fileLock = null;
            try {
                try {
                    File file = new File(new File(context.getExternalFilesDir(null), "/.vdevdir/"), "lcfp.lock");
                    u9.f(file);
                    randomAccessFile = new RandomAccessFile(file, "rw");
                } catch (IOException e2) {
                    e = e2;
                    randomAccessFile = null;
                } catch (Throwable th) {
                    th = th;
                    r2 = 0;
                    if (0 != 0) {
                        try {
                            fileLock.release();
                        } catch (IOException unused) {
                        }
                    }
                    u9.b(r2);
                    throw th;
                }
                try {
                    fileLock = randomAccessFile.getChannel().lock();
                    boolean b = b(context, str, j2);
                    if (fileLock != null && fileLock.isValid()) {
                        try {
                            fileLock.release();
                        } catch (IOException unused2) {
                        }
                    }
                    u9.b(randomAccessFile);
                    return b;
                } catch (IOException e3) {
                    e = e3;
                    e.printStackTrace();
                    if (fileLock != null && fileLock.isValid()) {
                        try {
                            fileLock.release();
                        } catch (IOException unused3) {
                        }
                    }
                    u9.b(randomAccessFile);
                    return true;
                }
            } catch (Throwable th2) {
                th = th2;
                if (0 != 0 && fileLock.isValid()) {
                    fileLock.release();
                }
                u9.b(r2);
                throw th;
            }
        }
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:41:0x00d8 A[Catch: IOException -> 0x00e8, all -> 0x00fa, LOOP:0: B:39:0x00d2->B:41:0x00d8, LOOP_END, TRY_LEAVE, TryCatch #0 {IOException -> 0x00e8, blocks: (B:38:0x00ce, B:39:0x00d2, B:41:0x00d8), top: B:57:0x00ce }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static boolean b(Context context, String str, long j2) {
        BufferedReader bufferedReader;
        BufferedReader bufferedReader2;
        BufferedWriter bufferedWriter;
        Iterator it;
        File file = new File(new File(context.getExternalFilesDir(null), "/.vdevdir/"), "lcfp");
        ArrayList arrayList = new ArrayList();
        long currentTimeMillis = System.currentTimeMillis();
        String str2 = str + ":" + context.getPackageName() + DYConstants.DY_REGEX_COMMA + currentTimeMillis;
        if (file.exists()) {
            try {
                bufferedReader2 = new BufferedReader(new FileReader(file));
                while (true) {
                    try {
                        try {
                            String readLine = bufferedReader2.readLine();
                            if (readLine == null) {
                                break;
                            }
                            String[] split = readLine.split(":");
                            if (split.length == 2) {
                                if (TextUtils.equals(split[0], String.valueOf(str))) {
                                    String[] split2 = split[1].split(DYConstants.DY_REGEX_COMMA);
                                    if (split2.length == 2) {
                                        long parseLong = Long.parseLong(split2[1]);
                                        if (!TextUtils.equals(split2[0], context.getPackageName()) && ((float) Math.abs(currentTimeMillis - parseLong)) < ((float) (1000 * j2)) * 0.9f) {
                                            u9.b(bufferedReader2);
                                            return false;
                                        }
                                    }
                                } else {
                                    arrayList.add(readLine);
                                }
                            }
                        } catch (Throwable th) {
                            th = th;
                            bufferedReader = bufferedReader2;
                            u9.b(bufferedReader);
                            throw th;
                        }
                    } catch (Exception unused) {
                        arrayList.clear();
                        u9.b(bufferedReader2);
                        arrayList.add(str2);
                        bufferedWriter = new BufferedWriter(new FileWriter(file));
                        try {
                            it = arrayList.iterator();
                            while (it.hasNext()) {
                            }
                            u9.b(bufferedWriter);
                            return true;
                        } catch (Throwable th2) {
                            th = th2;
                            u9.b(bufferedWriter);
                            throw th;
                        }
                    }
                }
            } catch (Exception unused2) {
                bufferedReader2 = null;
            } catch (Throwable th3) {
                th = th3;
                bufferedReader = null;
                u9.b(bufferedReader);
                throw th;
            }
        } else if (!u9.f(file)) {
            return true;
        }
        arrayList.add(str2);
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(file));
        } catch (IOException e2) {
            e = e2;
            bufferedWriter = null;
        } catch (Throwable th4) {
            th = th4;
            bufferedWriter = null;
            u9.b(bufferedWriter);
            throw th;
        }
        try {
            it = arrayList.iterator();
            while (it.hasNext()) {
                bufferedWriter.write((String) it.next());
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
        } catch (IOException e3) {
            e = e3;
            g.j.a.a.a.c.D(e.toString());
            u9.b(bufferedWriter);
            return true;
        }
        u9.b(bufferedWriter);
        return true;
    }
}
