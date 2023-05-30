package jd.wjlogin_sdk.relinker;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import jd.wjlogin_sdk.relinker.b;

/* loaded from: classes.dex */
public class a implements b.a {
    private static final int a = 5;
    private static final int b = 4096;

    /* JADX WARN: Code restructure failed: missing block: B:115:?, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:116:?, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x0027, code lost:
        if (r8 != null) goto L18;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0029, code lost:
        r23.a("FATAL! Couldn't find application APK!");
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x002e, code lost:
        if (r8 == null) goto L115;
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
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0037, code lost:
        r6 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x00fa, code lost:
        r5 = r8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x00fd, code lost:
        if (r5 != null) goto L98;
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x00ff, code lost:
        r5.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:77:0x0103, code lost:
        throw r0;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r12v5 */
    @Override // jd.wjlogin_sdk.relinker.b.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void a(Context context, String[] strArr, String str, File file, c cVar) {
        ZipFile zipFile;
        int i2;
        InputStream inputStream;
        FileOutputStream fileOutputStream;
        Closeable closeable;
        long a2;
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
                            } catch (FileNotFoundException unused2) {
                                inputStream = null;
                            } catch (IOException unused3) {
                                inputStream = null;
                            } catch (Throwable th) {
                                th = th;
                                inputStream = null;
                            }
                            try {
                                fileOutputStream = new FileOutputStream(file);
                            } catch (FileNotFoundException unused4) {
                                fileOutputStream = 0;
                                a(inputStream);
                                closeable = fileOutputStream;
                                a(closeable);
                                i2 = i5;
                            } catch (IOException unused5) {
                                fileOutputStream = 0;
                                a(inputStream);
                                closeable = fileOutputStream;
                                a(closeable);
                                i2 = i5;
                            } catch (Throwable th2) {
                                th = th2;
                            }
                            try {
                                a2 = a(inputStream, fileOutputStream);
                                fileOutputStream.getFD().sync();
                            } catch (FileNotFoundException | IOException unused6) {
                                a(inputStream);
                                closeable = fileOutputStream;
                                a(closeable);
                                i2 = i5;
                            } catch (Throwable th3) {
                                th = th3;
                                zipFile2 = fileOutputStream;
                                a(inputStream);
                                a(zipFile2);
                                throw th;
                            }
                            if (a2 != file.length()) {
                                a(inputStream);
                                closeable = fileOutputStream;
                                a(closeable);
                            } else {
                                a(inputStream);
                                a(fileOutputStream);
                                file.setReadable(true, false);
                                file.setExecutable(true, false);
                                file.setWritable(true);
                                if (zipFile != null) {
                                    try {
                                        zipFile.close();
                                        return;
                                    } catch (IOException unused7) {
                                        return;
                                    }
                                }
                                return;
                            }
                        }
                    } catch (IOException unused8) {
                    }
                    i2 = i5;
                } else {
                    cVar.a("FATAL! Couldn't extract the library from the APK!");
                    if (zipFile != null) {
                        try {
                            zipFile.close();
                            return;
                        } catch (IOException unused9) {
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
