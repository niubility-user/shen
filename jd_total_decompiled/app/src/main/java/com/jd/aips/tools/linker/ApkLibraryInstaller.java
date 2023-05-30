package com.jd.aips.tools.linker;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import com.jd.aips.tools.linker.SafeLinker;
import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/* loaded from: classes12.dex */
public class ApkLibraryInstaller implements SafeLinker.LibraryInstaller {

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public static class ZipFileInZipEntry {
        public ZipEntry zipEntry;
        public ZipFile zipFile;

        public ZipFileInZipEntry(ZipFile zipFile, ZipEntry zipEntry) {
            this.zipFile = zipFile;
            this.zipEntry = zipEntry;
        }
    }

    private String[] a(Context context) {
        String[] strArr;
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        if (Build.VERSION.SDK_INT < 21 || (strArr = applicationInfo.splitSourceDirs) == null || strArr.length == 0) {
            return new String[]{applicationInfo.sourceDir};
        }
        String[] strArr2 = new String[strArr.length + 1];
        strArr2[0] = applicationInfo.sourceDir;
        System.arraycopy(strArr, 0, strArr2, 1, strArr.length);
        return strArr2;
    }

    @Override // com.jd.aips.tools.linker.SafeLinker.LibraryInstaller
    @SuppressLint({"SetWorldReadable"})
    public void installLibrary(Context context, String[] strArr, String str, File file, SafeLinkerInstance safeLinkerInstance) {
        String[] strArr2;
        Throwable th;
        InputStream inputStream;
        FileOutputStream fileOutputStream;
        long j2;
        ZipFileInZipEntry zipFileInZipEntry = null;
        r13 = null;
        Closeable closeable = null;
        try {
            ZipFileInZipEntry a = a(context, strArr, str);
            try {
                if (a == null) {
                    try {
                        strArr2 = a(context, str);
                    } catch (Exception e2) {
                        strArr2 = new String[]{e2.toString()};
                    }
                    throw new MissingLibraryException(str, strArr, strArr2);
                }
                int i2 = 0;
                while (true) {
                    int i3 = i2 + 1;
                    if (i2 < 5) {
                        try {
                            if (file.exists() || file.createNewFile()) {
                                try {
                                    inputStream = a.zipFile.getInputStream(a.zipEntry);
                                } catch (FileNotFoundException | IOException unused) {
                                    inputStream = null;
                                } catch (Throwable th2) {
                                    th = th2;
                                    inputStream = null;
                                }
                                try {
                                    fileOutputStream = new FileOutputStream(file);
                                    j2 = 0;
                                    try {
                                        byte[] bArr = new byte[4096];
                                        while (true) {
                                            int read = inputStream.read(bArr);
                                            if (read == -1) {
                                                break;
                                            }
                                            fileOutputStream.write(bArr, 0, read);
                                            j2 += read;
                                        }
                                        fileOutputStream.flush();
                                        fileOutputStream.getFD().sync();
                                    } catch (FileNotFoundException | IOException unused2) {
                                        a(inputStream);
                                        a(fileOutputStream);
                                        i2 = i3;
                                    } catch (Throwable th3) {
                                        th = th3;
                                        closeable = fileOutputStream;
                                        a(inputStream);
                                        a(closeable);
                                        throw th;
                                    }
                                } catch (FileNotFoundException | IOException unused3) {
                                    fileOutputStream = null;
                                    a(inputStream);
                                    a(fileOutputStream);
                                    i2 = i3;
                                } catch (Throwable th4) {
                                    th = th4;
                                }
                                if (j2 != file.length()) {
                                    a(inputStream);
                                    a(fileOutputStream);
                                } else {
                                    a(inputStream);
                                    a(fileOutputStream);
                                    file.setReadable(true, false);
                                    file.setExecutable(true, false);
                                    file.setWritable(true);
                                    try {
                                        ZipFile zipFile = a.zipFile;
                                        if (zipFile != null) {
                                            zipFile.close();
                                            return;
                                        }
                                        return;
                                    } catch (IOException unused4) {
                                        return;
                                    }
                                }
                            }
                        } catch (IOException unused5) {
                        }
                        i2 = i3;
                    } else {
                        try {
                            ZipFile zipFile2 = a.zipFile;
                            if (zipFile2 != null) {
                                zipFile2.close();
                                return;
                            }
                            return;
                        } catch (IOException unused6) {
                            return;
                        }
                    }
                }
            } catch (Throwable th5) {
                th = th5;
                zipFileInZipEntry = a;
                if (zipFileInZipEntry != null) {
                    try {
                        ZipFile zipFile3 = zipFileInZipEntry.zipFile;
                        if (zipFile3 != null) {
                            zipFile3.close();
                        }
                    } catch (IOException unused7) {
                    }
                }
                throw th;
            }
        } catch (Throwable th6) {
            th = th6;
        }
    }

    private ZipFileInZipEntry a(Context context, String[] strArr, String str) {
        String[] a = a(context);
        int length = a.length;
        int i2 = 0;
        while (true) {
            ZipFile zipFile = null;
            if (i2 >= length) {
                return null;
            }
            String str2 = a[i2];
            int i3 = 0;
            while (true) {
                int i4 = i3 + 1;
                if (i3 >= 5) {
                    break;
                }
                try {
                    zipFile = new ZipFile(new File(str2), 1);
                    break;
                } catch (IOException unused) {
                    i3 = i4;
                }
            }
            if (zipFile != null) {
                int i5 = 0;
                while (true) {
                    int i6 = i5 + 1;
                    if (i5 < 5) {
                        for (String str3 : strArr) {
                            ZipEntry entry = zipFile.getEntry("lib" + File.separatorChar + str3 + File.separatorChar + str);
                            if (entry != null) {
                                return new ZipFileInZipEntry(zipFile, entry);
                            }
                        }
                        i5 = i6;
                    } else {
                        try {
                            zipFile.close();
                            break;
                        } catch (IOException unused2) {
                        }
                    }
                }
            }
            i2++;
        }
    }

    private String[] a(Context context, String str) {
        Pattern compile = Pattern.compile("lib" + File.separatorChar + "([^\\" + File.separatorChar + "]*)" + File.separatorChar + str);
        HashSet hashSet = new HashSet();
        for (String str2 : a(context)) {
            try {
                Enumeration<? extends ZipEntry> entries = new ZipFile(new File(str2), 1).entries();
                while (entries.hasMoreElements()) {
                    Matcher matcher = compile.matcher(entries.nextElement().getName());
                    if (matcher.matches()) {
                        hashSet.add(matcher.group(1));
                    }
                }
            } catch (IOException unused) {
            }
        }
        return (String[]) hashSet.toArray(new String[hashSet.size()]);
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
