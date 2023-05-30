package com.jd.stat.common.relinker;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import com.jd.stat.common.relinker.b;
import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/* loaded from: classes18.dex */
public class a implements b.a {
    /* JADX WARN: Code restructure failed: missing block: B:113:?, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:114:?, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x0027, code lost:
        if (r8 != null) goto L18;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0029, code lost:
        r23.a("FATAL! Couldn't find application APK!");
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x002e, code lost:
        if (r8 == null) goto L113;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0030, code lost:
        r8.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0033, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0034, code lost:
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0035, code lost:
        r5 = r8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0038, code lost:
        r6 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x0104, code lost:
        if (r5 != null) goto L93;
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x0106, code lost:
        r5.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x010a, code lost:
        throw r0;
     */
    @Override // com.jd.stat.common.relinker.b.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void a(Context context, String[] strArr, String str, File file, c cVar) {
        ZipFile zipFile;
        int i2;
        Closeable closeable;
        InputStream inputStream;
        long a;
        ZipFile zipFile2 = null;
        try {
            ApplicationInfo applicationInfo = context.getApplicationInfo();
            int i3 = 0;
            while (true) {
                int i4 = i3 + 1;
                if (i3 >= 5) {
                    zipFile = null;
                    break;
                } else {
                    try {
                        zipFile = new ZipFile(new File(applicationInfo.sourceDir), 1);
                    } catch (IOException unused) {
                        i3 = i4;
                    }
                }
            }
            while (true) {
                int i5 = i2 + 1;
                if (i2 < 5) {
                    String str2 = null;
                    ZipEntry zipEntry = null;
                    for (String str3 : strArr) {
                        str2 = "lib" + File.separatorChar + str3 + File.separatorChar + str;
                        zipEntry = zipFile.getEntry(str2);
                        if (zipEntry != null) {
                            break;
                        }
                    }
                    if (str2 != null) {
                        cVar.a("Looking for %s in APK...", str2);
                    }
                    if (zipEntry == null) {
                        if (str2 != null) {
                            throw new MissingLibraryException(str2);
                        }
                        throw new MissingLibraryException(str);
                    }
                    cVar.a("Found %s! Extracting...", str2);
                    try {
                        if (file.exists() || file.createNewFile()) {
                            try {
                                inputStream = zipFile.getInputStream(zipEntry);
                                try {
                                    closeable = new FileOutputStream(file);
                                } catch (FileNotFoundException unused2) {
                                    closeable = null;
                                } catch (IOException unused3) {
                                    closeable = null;
                                } catch (Throwable th) {
                                    th = th;
                                    closeable = null;
                                }
                                try {
                                    a = a(inputStream, closeable);
                                    closeable.getFD().sync();
                                } catch (FileNotFoundException unused4) {
                                    a(inputStream);
                                    a(closeable);
                                    i2 = i5;
                                } catch (IOException unused5) {
                                    a(inputStream);
                                    a(closeable);
                                    i2 = i5;
                                } catch (Throwable th2) {
                                    th = th2;
                                    zipFile2 = inputStream;
                                    a(zipFile2);
                                    a(closeable);
                                    throw th;
                                }
                            } catch (FileNotFoundException unused6) {
                                inputStream = null;
                                closeable = null;
                            } catch (IOException unused7) {
                                inputStream = null;
                                closeable = null;
                            } catch (Throwable th3) {
                                th = th3;
                                closeable = null;
                            }
                            if (a != file.length()) {
                                a(inputStream);
                                a(closeable);
                            } else {
                                a(inputStream);
                                a(closeable);
                                file.setReadable(true, false);
                                file.setExecutable(true, false);
                                file.setWritable(true);
                                if (zipFile != null) {
                                    try {
                                        zipFile.close();
                                        return;
                                    } catch (IOException unused8) {
                                        return;
                                    }
                                }
                                return;
                            }
                        }
                    } catch (IOException unused9) {
                    }
                    i2 = i5;
                } else {
                    cVar.a("FATAL! Couldn't extract the library from the APK!");
                    if (zipFile != null) {
                        try {
                            zipFile.close();
                            return;
                        } catch (IOException unused10) {
                            return;
                        }
                    }
                    return;
                }
            }
        } catch (Throwable th4) {
            th = th4;
        }
    }

    private long a(InputStream inputStream, OutputStream outputStream) throws IOException {
        byte[] bArr = new byte[4096];
        long j2 = 0;
        while (true) {
            int read = inputStream.read(bArr);
            if (read == -1) {
                outputStream.flush();
                return j2;
            }
            outputStream.write(bArr, 0, read);
            j2 += read;
        }
    }

    private void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
            }
        }
    }
}
