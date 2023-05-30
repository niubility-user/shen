package com.jingdong.aura.sdk.update.b;

import android.text.TextUtils;
import com.jingdong.aura.sdk.update.AuraBundleResult;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.zip.ZipFile;

/* loaded from: classes4.dex */
public final class g {

    /* loaded from: classes4.dex */
    public static class a {
        public boolean a;
        public String b;

        public a(boolean z, String str) {
            this.a = z;
            this.b = str;
        }

        public final String toString() {
            return "BundleDownloadState{isCompleted=" + this.a + ", msg='" + this.b + "'}";
        }
    }

    public static synchronized Object a() {
        ObjectInputStream objectInputStream;
        File file;
        synchronized (g.class) {
            try {
                file = new File(com.jingdong.aura.sdk.update.a.a().f12241k, "auraUpdateObj");
            } catch (Throwable th) {
                th = th;
                objectInputStream = null;
            }
            if (!file.exists()) {
                a((Closeable) null);
                a((Closeable) null);
                return null;
            }
            objectInputStream = new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)));
            try {
                Object readObject = objectInputStream.readObject();
                a((Closeable) objectInputStream);
                a((Closeable) objectInputStream);
                return readObject;
            } catch (Throwable th2) {
                th = th2;
                th.printStackTrace();
                a((Closeable) objectInputStream);
                a((Closeable) objectInputStream);
                return null;
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0096 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v1, types: [java.io.FileInputStream] */
    /* JADX WARN: Type inference failed for: r1v2 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String a(java.lang.String r10) {
        /*
            java.lang.String r0 = "0123456789abcdef"
            r1 = 0
            java.lang.String r2 = "MD5"
            java.security.MessageDigest r2 = java.security.MessageDigest.getInstance(r2)     // Catch: java.lang.Throwable -> L6e java.io.IOException -> L70 java.io.FileNotFoundException -> L77 java.security.NoSuchAlgorithmException -> L82
            java.io.File r3 = new java.io.File     // Catch: java.lang.Throwable -> L6e java.io.IOException -> L70 java.io.FileNotFoundException -> L77 java.security.NoSuchAlgorithmException -> L82
            r3.<init>(r10)     // Catch: java.lang.Throwable -> L6e java.io.IOException -> L70 java.io.FileNotFoundException -> L77 java.security.NoSuchAlgorithmException -> L82
            boolean r10 = r3.exists()     // Catch: java.lang.Throwable -> L6e java.io.IOException -> L70 java.io.FileNotFoundException -> L77 java.security.NoSuchAlgorithmException -> L82
            if (r10 == 0) goto L6d
            boolean r10 = r3.isFile()     // Catch: java.lang.Throwable -> L6e java.io.IOException -> L70 java.io.FileNotFoundException -> L77 java.security.NoSuchAlgorithmException -> L82
            if (r10 != 0) goto L1b
            goto L6d
        L1b:
            java.io.FileInputStream r10 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L6e java.io.IOException -> L70 java.io.FileNotFoundException -> L77 java.security.NoSuchAlgorithmException -> L82
            r10.<init>(r3)     // Catch: java.lang.Throwable -> L6e java.io.IOException -> L70 java.io.FileNotFoundException -> L77 java.security.NoSuchAlgorithmException -> L82
            java.nio.channels.FileChannel r4 = r10.getChannel()     // Catch: java.io.FileNotFoundException -> L69 java.security.NoSuchAlgorithmException -> L6b java.io.IOException -> L71 java.lang.Throwable -> L92
            java.nio.channels.FileChannel$MapMode r5 = java.nio.channels.FileChannel.MapMode.READ_ONLY     // Catch: java.io.FileNotFoundException -> L69 java.security.NoSuchAlgorithmException -> L6b java.io.IOException -> L71 java.lang.Throwable -> L92
            r6 = 0
            long r8 = r3.length()     // Catch: java.io.FileNotFoundException -> L69 java.security.NoSuchAlgorithmException -> L6b java.io.IOException -> L71 java.lang.Throwable -> L92
            java.nio.MappedByteBuffer r3 = r4.map(r5, r6, r8)     // Catch: java.io.FileNotFoundException -> L69 java.security.NoSuchAlgorithmException -> L6b java.io.IOException -> L71 java.lang.Throwable -> L92
            r2.update(r3)     // Catch: java.io.FileNotFoundException -> L69 java.security.NoSuchAlgorithmException -> L6b java.io.IOException -> L71 java.lang.Throwable -> L92
            byte[] r2 = r2.digest()     // Catch: java.io.FileNotFoundException -> L69 java.security.NoSuchAlgorithmException -> L6b java.io.IOException -> L71 java.lang.Throwable -> L92
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.io.FileNotFoundException -> L69 java.security.NoSuchAlgorithmException -> L6b java.io.IOException -> L71 java.lang.Throwable -> L92
            int r4 = r2.length     // Catch: java.io.FileNotFoundException -> L69 java.security.NoSuchAlgorithmException -> L6b java.io.IOException -> L71 java.lang.Throwable -> L92
            int r4 = r4 * 2
            r3.<init>(r4)     // Catch: java.io.FileNotFoundException -> L69 java.security.NoSuchAlgorithmException -> L6b java.io.IOException -> L71 java.lang.Throwable -> L92
            int r4 = r2.length     // Catch: java.io.FileNotFoundException -> L69 java.security.NoSuchAlgorithmException -> L6b java.io.IOException -> L71 java.lang.Throwable -> L92
            r5 = 0
        L41:
            if (r5 >= r4) goto L5c
            r6 = r2[r5]     // Catch: java.io.FileNotFoundException -> L69 java.security.NoSuchAlgorithmException -> L6b java.io.IOException -> L71 java.lang.Throwable -> L92
            int r7 = r6 >> 4
            r7 = r7 & 15
            char r7 = r0.charAt(r7)     // Catch: java.io.FileNotFoundException -> L69 java.security.NoSuchAlgorithmException -> L6b java.io.IOException -> L71 java.lang.Throwable -> L92
            r3.append(r7)     // Catch: java.io.FileNotFoundException -> L69 java.security.NoSuchAlgorithmException -> L6b java.io.IOException -> L71 java.lang.Throwable -> L92
            r6 = r6 & 15
            char r6 = r0.charAt(r6)     // Catch: java.io.FileNotFoundException -> L69 java.security.NoSuchAlgorithmException -> L6b java.io.IOException -> L71 java.lang.Throwable -> L92
            r3.append(r6)     // Catch: java.io.FileNotFoundException -> L69 java.security.NoSuchAlgorithmException -> L6b java.io.IOException -> L71 java.lang.Throwable -> L92
            int r5 = r5 + 1
            goto L41
        L5c:
            java.lang.String r0 = r3.toString()     // Catch: java.io.FileNotFoundException -> L69 java.security.NoSuchAlgorithmException -> L6b java.io.IOException -> L71 java.lang.Throwable -> L92
            r10.close()     // Catch: java.lang.Exception -> L64
            goto L68
        L64:
            r10 = move-exception
            r10.printStackTrace()
        L68:
            return r0
        L69:
            r0 = move-exception
            goto L79
        L6b:
            r0 = move-exception
            goto L84
        L6d:
            return r1
        L6e:
            r0 = move-exception
            goto L94
        L70:
            r10 = r1
        L71:
            if (r10 == 0) goto L91
            r10.close()     // Catch: java.lang.Exception -> L8d
            goto L91
        L77:
            r0 = move-exception
            r10 = r1
        L79:
            r0.printStackTrace()     // Catch: java.lang.Throwable -> L92
            if (r10 == 0) goto L91
            r10.close()     // Catch: java.lang.Exception -> L8d
            goto L91
        L82:
            r0 = move-exception
            r10 = r1
        L84:
            r0.printStackTrace()     // Catch: java.lang.Throwable -> L92
            if (r10 == 0) goto L91
            r10.close()     // Catch: java.lang.Exception -> L8d
            goto L91
        L8d:
            r10 = move-exception
            r10.printStackTrace()
        L91:
            return r1
        L92:
            r0 = move-exception
            r1 = r10
        L94:
            if (r1 == 0) goto L9e
            r1.close()     // Catch: java.lang.Exception -> L9a
            goto L9e
        L9a:
            r10 = move-exception
            r10.printStackTrace()
        L9e:
            goto La0
        L9f:
            throw r0
        La0:
            goto L9f
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.aura.sdk.update.b.g.a(java.lang.String):java.lang.String");
    }

    private static void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
            }
        }
    }

    public static void a(File file) {
        if (file != null) {
            try {
                if (file.exists() && file.isDirectory()) {
                    for (File file2 : file.listFiles()) {
                        if (file2.isDirectory()) {
                            a(file2);
                        } else {
                            file2.delete();
                        }
                    }
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public static void a(ZipFile zipFile) {
        if (zipFile != null) {
            try {
                zipFile.close();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
    }

    public static boolean a(AuraBundleResult auraBundleResult) {
        String str;
        if (auraBundleResult == null) {
            str = "isCompleted: bundleResult is null";
        } else {
            File file = new File(auraBundleResult.downloadedFilePath);
            if (file.exists()) {
                String a2 = a(file.getAbsolutePath());
                c.a("FileUtil", "isCompleted: " + auraBundleResult.updateId + ",expected md5:" + auraBundleResult.md5 + ",downloaded File md5:" + a2);
                return (TextUtils.isEmpty(a2) || TextUtils.isEmpty(auraBundleResult.md5) || !a2.toLowerCase().equals(auraBundleResult.md5.toLowerCase())) ? false : true;
            }
            str = "isCompleted: downloadedFile not exist:" + auraBundleResult.updateId;
        }
        c.b("FileUtil", str);
        return false;
    }

    public static synchronized boolean a(Object obj) {
        FileOutputStream fileOutputStream;
        boolean z;
        synchronized (g.class) {
            ObjectOutputStream objectOutputStream = null;
            try {
                try {
                    File file = new File(com.jingdong.aura.sdk.update.a.a().f12241k, "auraUpdateObj");
                    file.delete();
                    if (!file.getParentFile().exists()) {
                        file.getParentFile().mkdirs();
                    }
                    fileOutputStream = new FileOutputStream(file);
                    try {
                        ObjectOutputStream objectOutputStream2 = new ObjectOutputStream(new BufferedOutputStream(fileOutputStream));
                        try {
                            objectOutputStream2.writeObject(obj);
                            objectOutputStream2.flush();
                            a((Closeable) objectOutputStream2);
                            a((Closeable) fileOutputStream);
                            z = true;
                        } catch (Throwable th) {
                            th = th;
                            objectOutputStream = objectOutputStream2;
                            th.printStackTrace();
                            a((Closeable) objectOutputStream);
                            a((Closeable) fileOutputStream);
                            z = false;
                            return z;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    fileOutputStream = null;
                }
            } catch (Throwable th4) {
                throw th4;
            }
        }
        return z;
    }
}
