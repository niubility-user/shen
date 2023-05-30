package com.jingdong.manto.utils;

import android.text.TextUtils;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/* loaded from: classes16.dex */
public final class f0 {
    private static File a(String str) {
        File file = new File(str);
        File parentFile = file.getParentFile();
        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }
        if (!file.exists()) {
            file.createNewFile();
        }
        return file;
    }

    /* JADX WARN: Code restructure failed: missing block: B:60:0x00fd, code lost:
        if (r4 != null) goto L79;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00f4  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0103  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x010e A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r6v0 */
    /* JADX WARN: Type inference failed for: r6v1, types: [java.io.OutputStream] */
    /* JADX WARN: Type inference failed for: r6v2 */
    /* JADX WARN: Type inference failed for: r6v4 */
    /* JADX WARN: Type inference failed for: r8v0 */
    /* JADX WARN: Type inference failed for: r8v1 */
    /* JADX WARN: Type inference failed for: r8v13, types: [java.io.OutputStream, java.io.FileOutputStream] */
    /* JADX WARN: Type inference failed for: r8v14 */
    /* JADX WARN: Type inference failed for: r8v2, types: [java.io.OutputStream] */
    /* JADX WARN: Type inference failed for: r8v3, types: [java.io.OutputStream] */
    /* JADX WARN: Type inference failed for: r8v4 */
    /* JADX WARN: Type inference failed for: r8v5 */
    /* JADX WARN: Type inference failed for: r8v6 */
    /* JADX WARN: Type inference failed for: r8v7 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static List<File> a(File file, String str, boolean z) {
        ZipFile zipFile;
        ?? r8;
        InputStream inputStream;
        byte[] bArr = new byte[4096];
        ArrayList arrayList = new ArrayList();
        if (file.exists() && file.getName().endsWith(".zip")) {
            InputStream inputStream2 = null;
            try {
                zipFile = new ZipFile(file);
                try {
                    Enumeration<? extends ZipEntry> entries = zipFile.entries();
                    ?? r6 = 0;
                    inputStream = null;
                    while (entries.hasMoreElements()) {
                        try {
                            ZipEntry nextElement = entries.nextElement();
                            String name = nextElement.getName();
                            if (!TextUtils.isEmpty(name) && name.contains("../")) {
                                MantoLog.e("zipFile", "it's a bad zip files!");
                                if (z) {
                                    file.delete();
                                }
                                t.a(inputStream);
                                t.a((OutputStream) r6);
                                try {
                                    zipFile.close();
                                } catch (IOException unused) {
                                }
                                return null;
                            }
                            String str2 = str + File.separator + name;
                            if (nextElement.isDirectory()) {
                                new File(str2).mkdirs();
                            } else {
                                inputStream = zipFile.getInputStream(nextElement);
                                arrayList.add(a(str2));
                                r8 = new FileOutputStream(str2);
                                while (true) {
                                    try {
                                        int read = inputStream.read(bArr);
                                        if (read <= 0) {
                                            break;
                                        }
                                        r8.write(bArr, 0, read);
                                    } catch (IOException e2) {
                                        e = e2;
                                        inputStream2 = inputStream;
                                        r8 = r8;
                                        try {
                                            MantoLog.e("zipFile", "filename:" + file.getName());
                                            MantoLog.e("zipFile", "unzip err: " + e);
                                            if (z) {
                                            }
                                            t.a(inputStream2);
                                            t.a((OutputStream) r8);
                                        } catch (Throwable th) {
                                            th = th;
                                            if (z) {
                                                file.delete();
                                            }
                                            t.a(inputStream2);
                                            t.a((OutputStream) r8);
                                            if (zipFile != null) {
                                                try {
                                                    zipFile.close();
                                                } catch (IOException unused2) {
                                                }
                                            }
                                            throw th;
                                        }
                                    } catch (Throwable th2) {
                                        th = th2;
                                        inputStream2 = inputStream;
                                        if (z) {
                                        }
                                        t.a(inputStream2);
                                        t.a((OutputStream) r8);
                                        if (zipFile != null) {
                                        }
                                        throw th;
                                    }
                                }
                                r6 = r8;
                            }
                        } catch (IOException e3) {
                            e = e3;
                            inputStream2 = r6;
                            r8 = inputStream2;
                            inputStream2 = inputStream;
                            r8 = r8;
                            MantoLog.e("zipFile", "filename:" + file.getName());
                            MantoLog.e("zipFile", "unzip err: " + e);
                            if (z) {
                                file.delete();
                            }
                            t.a(inputStream2);
                            t.a((OutputStream) r8);
                        } catch (Throwable th3) {
                            th = th3;
                            inputStream2 = r6;
                            r8 = inputStream2;
                            inputStream2 = inputStream;
                            if (z) {
                            }
                            t.a(inputStream2);
                            t.a((OutputStream) r8);
                            if (zipFile != null) {
                            }
                            throw th;
                        }
                    }
                    if (z) {
                        file.delete();
                    }
                    t.a(inputStream);
                    t.a((OutputStream) r6);
                } catch (IOException e4) {
                    e = e4;
                    inputStream = null;
                } catch (Throwable th4) {
                    th = th4;
                    inputStream = null;
                }
            } catch (IOException e5) {
                e = e5;
                zipFile = null;
                r8 = 0;
            } catch (Throwable th5) {
                th = th5;
                zipFile = null;
                r8 = 0;
            }
            try {
                zipFile.close();
            } catch (IOException unused3) {
            }
        }
        return arrayList;
    }

    public static long b(String str) {
        long j2 = 0;
        try {
            Enumeration<? extends ZipEntry> entries = new ZipFile(str).entries();
            while (entries.hasMoreElements()) {
                j2 += entries.nextElement().getSize();
            }
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        return j2;
    }
}
