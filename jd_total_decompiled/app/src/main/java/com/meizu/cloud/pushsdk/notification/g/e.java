package com.meizu.cloud.pushsdk.notification.g;

import android.os.SystemClock;
import com.meizu.cloud.pushinternal.DebugLogger;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

/* loaded from: classes14.dex */
public class e {
    private final File a;
    private final File b;

    /* renamed from: c  reason: collision with root package name */
    private final String f15997c;

    public e(String str, String str2) {
        File file = new File(str);
        this.a = file;
        File file2 = new File(str2);
        this.b = file2;
        this.f15997c = file2.getAbsolutePath();
        DebugLogger.i("ZipExtractTask", "Extract mInput file = " + file.toString());
        DebugLogger.i("ZipExtractTask", "Extract mOutput file = " + file2.toString());
    }

    private int a(InputStream inputStream, OutputStream outputStream) {
        StringBuilder sb;
        byte[] bArr = new byte[8192];
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream, 8192);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream, 8192);
        int i2 = 0;
        while (true) {
            try {
                try {
                    int read = bufferedInputStream.read(bArr, 0, 8192);
                    if (read == -1) {
                        break;
                    }
                    bufferedOutputStream.write(bArr, 0, read);
                    i2 += read;
                } catch (IOException e2) {
                    DebugLogger.e("ZipExtractTask", "Extracted IOException:" + e2.toString());
                    try {
                        bufferedOutputStream.close();
                    } catch (IOException e3) {
                        DebugLogger.e("ZipExtractTask", "out.close() IOException e=" + e3.toString());
                    }
                    try {
                        bufferedInputStream.close();
                    } catch (IOException e4) {
                        e = e4;
                        sb = new StringBuilder();
                        sb.append("in.close() IOException e=");
                        sb.append(e.toString());
                        DebugLogger.e("ZipExtractTask", sb.toString());
                        return i2;
                    }
                }
            } catch (Throwable th) {
                try {
                    bufferedOutputStream.close();
                } catch (IOException e5) {
                    DebugLogger.e("ZipExtractTask", "out.close() IOException e=" + e5.toString());
                }
                try {
                    bufferedInputStream.close();
                } catch (IOException e6) {
                    DebugLogger.e("ZipExtractTask", "in.close() IOException e=" + e6.toString());
                }
                throw th;
            }
        }
        bufferedOutputStream.flush();
        try {
            bufferedOutputStream.close();
        } catch (IOException e7) {
            DebugLogger.e("ZipExtractTask", "out.close() IOException e=" + e7.toString());
        }
        try {
            bufferedInputStream.close();
        } catch (IOException e8) {
            e = e8;
            sb = new StringBuilder();
            sb.append("in.close() IOException e=");
            sb.append(e.toString());
            DebugLogger.e("ZipExtractTask", sb.toString());
            return i2;
        }
        return i2;
    }

    private void b() {
        StringBuilder sb;
        String str;
        File file = this.a;
        if (file == null || !file.exists()) {
            return;
        }
        if (this.a.delete()) {
            sb = new StringBuilder();
            str = "Delete file:";
        } else {
            sb = new StringBuilder();
            str = "Can't delete file:";
        }
        sb.append(str);
        sb.append(this.a.toString());
        sb.append(" after extracted.");
        DebugLogger.i("ZipExtractTask", sb.toString());
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x0047, code lost:
        if (r13 == null) goto L20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0049, code lost:
        r7 = r13.split("/")[0];
        com.meizu.cloud.pushinternal.DebugLogger.i("ZipExtractTask", "Extract temp directory=" + r18.b + "/" + r7);
     */
    /* JADX WARN: Removed duplicated region for block: B:76:0x01e0  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private long d() {
        long j2;
        String str;
        Throwable th;
        ZipFile zipFile;
        IOException iOException;
        StringBuilder sb;
        StringBuilder sb2;
        long currentThreadTimeMillis = SystemClock.currentThreadTimeMillis();
        ZipFile zipFile2 = null;
        String str2 = null;
        ZipFile zipFile3 = null;
        ZipFile zipFile4 = null;
        boolean z = false;
        long j3 = 0;
        try {
            try {
                zipFile = new ZipFile(this.a);
                try {
                    Enumeration<? extends ZipEntry> entries = zipFile.entries();
                    while (entries.hasMoreElements()) {
                        ZipEntry nextElement = entries.nextElement();
                        if (!nextElement.isDirectory()) {
                            String name = nextElement.getName();
                            if (name != null && name.contains("../")) {
                                throw new Exception("Unsafe zip file");
                            }
                            if (name != null) {
                                File file = new File(this.b, name);
                                if (!file.getParentFile().exists()) {
                                    if (file.getParentFile().mkdirs()) {
                                        sb2 = new StringBuilder();
                                        sb2.append("Make Destination directory=");
                                        sb2.append(file.getParentFile().getAbsolutePath());
                                    } else {
                                        sb2 = new StringBuilder();
                                        sb2.append("Can't make destination directory=");
                                        sb2.append(file.getParentFile().getAbsolutePath());
                                    }
                                    DebugLogger.i("ZipExtractTask", sb2.toString());
                                }
                                j3 += a(zipFile.getInputStream(nextElement), r13);
                                new FileOutputStream(file).close();
                            }
                        }
                    }
                    String str3 = this.b + "/" + str2;
                    if (!this.f15997c.equals(str3)) {
                        a.a(str3, this.f15997c);
                        z = true;
                    }
                    try {
                        zipFile.close();
                    } catch (IOException e2) {
                        DebugLogger.e("ZipExtractTask", "Extracted IOException:" + e2.toString());
                    }
                } catch (ZipException e3) {
                    e = e3;
                    str = null;
                    zipFile3 = zipFile;
                    j2 = 0;
                    DebugLogger.e("ZipExtractTask", "ZipException :" + e.toString());
                    if (zipFile3 != null) {
                        try {
                            zipFile3.close();
                        } catch (IOException e4) {
                            iOException = e4;
                            sb = new StringBuilder();
                            sb.append("Extracted IOException:");
                            sb.append(iOException.toString());
                            DebugLogger.e("ZipExtractTask", sb.toString());
                            str2 = str;
                            j3 = j2;
                            DebugLogger.i("ZipExtractTask", "Extract file " + this.a + ", UseTime =" + (SystemClock.currentThreadTimeMillis() - currentThreadTimeMillis));
                            if (z) {
                            }
                            b();
                            return j3;
                        }
                    }
                    str2 = str;
                    j3 = j2;
                    DebugLogger.i("ZipExtractTask", "Extract file " + this.a + ", UseTime =" + (SystemClock.currentThreadTimeMillis() - currentThreadTimeMillis));
                    if (z) {
                    }
                    b();
                    return j3;
                } catch (IOException e5) {
                    e = e5;
                    str = null;
                    zipFile4 = zipFile;
                    j2 = 0;
                    DebugLogger.e("ZipExtractTask", "Extracted IOException:" + e.toString());
                    if (zipFile4 != null) {
                        try {
                            zipFile4.close();
                        } catch (IOException e6) {
                            iOException = e6;
                            sb = new StringBuilder();
                            sb.append("Extracted IOException:");
                            sb.append(iOException.toString());
                            DebugLogger.e("ZipExtractTask", sb.toString());
                            str2 = str;
                            j3 = j2;
                            DebugLogger.i("ZipExtractTask", "Extract file " + this.a + ", UseTime =" + (SystemClock.currentThreadTimeMillis() - currentThreadTimeMillis));
                            if (z) {
                            }
                            b();
                            return j3;
                        }
                    }
                    str2 = str;
                    j3 = j2;
                    DebugLogger.i("ZipExtractTask", "Extract file " + this.a + ", UseTime =" + (SystemClock.currentThreadTimeMillis() - currentThreadTimeMillis));
                    if (z) {
                    }
                    b();
                    return j3;
                } catch (Exception e7) {
                    e = e7;
                    str = null;
                    zipFile2 = zipFile;
                    j2 = 0;
                    DebugLogger.e("ZipExtractTask", "Extracted Exception " + e.toString());
                    if (zipFile2 != null) {
                        try {
                            zipFile2.close();
                        } catch (IOException e8) {
                            iOException = e8;
                            sb = new StringBuilder();
                            sb.append("Extracted IOException:");
                            sb.append(iOException.toString());
                            DebugLogger.e("ZipExtractTask", sb.toString());
                            str2 = str;
                            j3 = j2;
                            DebugLogger.i("ZipExtractTask", "Extract file " + this.a + ", UseTime =" + (SystemClock.currentThreadTimeMillis() - currentThreadTimeMillis));
                            if (z) {
                            }
                            b();
                            return j3;
                        }
                    }
                    str2 = str;
                    j3 = j2;
                    DebugLogger.i("ZipExtractTask", "Extract file " + this.a + ", UseTime =" + (SystemClock.currentThreadTimeMillis() - currentThreadTimeMillis));
                    if (z) {
                    }
                    b();
                    return j3;
                } catch (Throwable th2) {
                    th = th2;
                    if (zipFile != null) {
                        try {
                            zipFile.close();
                        } catch (IOException e9) {
                            DebugLogger.e("ZipExtractTask", "Extracted IOException:" + e9.toString());
                        }
                    }
                    throw th;
                }
            } catch (ZipException e10) {
                e = e10;
                j2 = 0;
                str = null;
            } catch (IOException e11) {
                e = e11;
                j2 = 0;
                str = null;
            } catch (Exception e12) {
                e = e12;
                j2 = 0;
                str = null;
            }
            DebugLogger.i("ZipExtractTask", "Extract file " + this.a + ", UseTime =" + (SystemClock.currentThreadTimeMillis() - currentThreadTimeMillis));
            if (z) {
                a.b(this.b + "/" + str2);
            }
            b();
            return j3;
        } catch (Throwable th3) {
            th = th3;
            zipFile = null;
        }
    }

    public boolean c() {
        return d() > 0;
    }
}
