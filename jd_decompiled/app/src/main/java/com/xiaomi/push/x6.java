package com.xiaomi.push;

import android.content.Context;
import android.content.SharedPreferences;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

/* loaded from: classes11.dex */
public class x6 {
    private static boolean a;

    /* JADX INFO: Access modifiers changed from: package-private */
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

    /* JADX WARN: Removed duplicated region for block: B:96:0x00b7  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x00bb  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void f(android.content.Context r11, com.xiaomi.push.b7 r12) {
        /*
            java.lang.String r0 = "/"
            java.lang.String r1 = "/tdReadTemp"
            boolean r2 = com.xiaomi.push.x6.a
            if (r2 != 0) goto Ldd
            r2 = 1
            com.xiaomi.push.x6.a = r2
            java.io.File r2 = new java.io.File
            java.io.File r3 = r11.getFilesDir()
            java.lang.String r4 = "tiny_data.data"
            r2.<init>(r3, r4)
            boolean r3 = r2.exists()
            java.lang.String r5 = "TinyData no ready file to get data."
            if (r3 != 0) goto L22
            g.j.a.a.a.c.o(r5)
            return
        L22:
            a(r11)
            byte[] r3 = com.xiaomi.push.service.f1.b(r11)
            r6 = 0
            java.io.File r7 = new java.io.File     // Catch: java.lang.Throwable -> L78 java.lang.Exception -> L7b
            java.io.File r8 = r11.getFilesDir()     // Catch: java.lang.Throwable -> L78 java.lang.Exception -> L7b
            java.lang.String r9 = "tiny_data.lock"
            r7.<init>(r8, r9)     // Catch: java.lang.Throwable -> L78 java.lang.Exception -> L7b
            com.xiaomi.push.u9.f(r7)     // Catch: java.lang.Throwable -> L78 java.lang.Exception -> L7b
            java.io.RandomAccessFile r8 = new java.io.RandomAccessFile     // Catch: java.lang.Throwable -> L78 java.lang.Exception -> L7b
            java.lang.String r9 = "rw"
            r8.<init>(r7, r9)     // Catch: java.lang.Throwable -> L78 java.lang.Exception -> L7b
            java.nio.channels.FileChannel r7 = r8.getChannel()     // Catch: java.lang.Exception -> L76 java.lang.Throwable -> Lc8
            java.nio.channels.FileLock r6 = r7.lock()     // Catch: java.lang.Exception -> L76 java.lang.Throwable -> Lc8
            java.io.File r7 = new java.io.File     // Catch: java.lang.Exception -> L76 java.lang.Throwable -> Lc8
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L76 java.lang.Throwable -> Lc8
            r9.<init>()     // Catch: java.lang.Exception -> L76 java.lang.Throwable -> Lc8
            java.io.File r10 = r11.getFilesDir()     // Catch: java.lang.Exception -> L76 java.lang.Throwable -> Lc8
            r9.append(r10)     // Catch: java.lang.Exception -> L76 java.lang.Throwable -> Lc8
            r9.append(r1)     // Catch: java.lang.Exception -> L76 java.lang.Throwable -> Lc8
            r9.append(r0)     // Catch: java.lang.Exception -> L76 java.lang.Throwable -> Lc8
            r9.append(r4)     // Catch: java.lang.Exception -> L76 java.lang.Throwable -> Lc8
            java.lang.String r9 = r9.toString()     // Catch: java.lang.Exception -> L76 java.lang.Throwable -> Lc8
            r7.<init>(r9)     // Catch: java.lang.Exception -> L76 java.lang.Throwable -> Lc8
            r2.renameTo(r7)     // Catch: java.lang.Exception -> L76 java.lang.Throwable -> Lc8
            if (r6 == 0) goto L90
            boolean r2 = r6.isValid()
            if (r2 == 0) goto L90
            r6.release()     // Catch: java.io.IOException -> L74
            goto L90
        L74:
            r2 = move-exception
            goto L8d
        L76:
            r2 = move-exception
            goto L7d
        L78:
            r11 = move-exception
            r8 = r6
            goto Lc9
        L7b:
            r2 = move-exception
            r8 = r6
        L7d:
            g.j.a.a.a.c.s(r2)     // Catch: java.lang.Throwable -> Lc8
            if (r6 == 0) goto L90
            boolean r2 = r6.isValid()
            if (r2 == 0) goto L90
            r6.release()     // Catch: java.io.IOException -> L8c
            goto L90
        L8c:
            r2 = move-exception
        L8d:
            g.j.a.a.a.c.s(r2)
        L90:
            com.xiaomi.push.u9.b(r8)
            java.io.File r2 = new java.io.File
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.io.File r7 = r11.getFilesDir()
            r6.append(r7)
            r6.append(r1)
            r6.append(r0)
            r6.append(r4)
            java.lang.String r0 = r6.toString()
            r2.<init>(r0)
            boolean r0 = r2.exists()
            if (r0 != 0) goto Lbb
            g.j.a.a.a.c.o(r5)
            return
        Lbb:
            c(r11, r12, r2, r3)
            r12 = 0
            com.xiaomi.push.w6.c(r12)
            d(r11)
            com.xiaomi.push.x6.a = r12
            return
        Lc8:
            r11 = move-exception
        Lc9:
            if (r6 == 0) goto Ld9
            boolean r12 = r6.isValid()
            if (r12 == 0) goto Ld9
            r6.release()     // Catch: java.io.IOException -> Ld5
            goto Ld9
        Ld5:
            r12 = move-exception
            g.j.a.a.a.c.s(r12)
        Ld9:
            com.xiaomi.push.u9.b(r8)
            throw r11
        Ldd:
            java.lang.String r11 = "TinyData extractTinyData is running"
            g.j.a.a.a.c.o(r11)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.x6.f(android.content.Context, com.xiaomi.push.b7):void");
    }
}
