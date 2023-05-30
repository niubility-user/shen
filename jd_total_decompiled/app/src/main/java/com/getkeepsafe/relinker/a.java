package com.getkeepsafe.relinker;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import com.getkeepsafe.relinker.c;
import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/* loaded from: classes12.dex */
public class a implements c.a {

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.getkeepsafe.relinker.a$a  reason: collision with other inner class name */
    /* loaded from: classes12.dex */
    public static class C0026a {
        public ZipFile a;
        public ZipEntry b;

        public C0026a(ZipFile zipFile, ZipEntry zipEntry) {
            this.a = zipFile;
            this.b = zipEntry;
        }
    }

    private void b(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
            }
        }
    }

    private long c(InputStream inputStream, OutputStream outputStream) throws IOException {
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

    private C0026a d(Context context, String[] strArr, String str, d dVar) {
        String[] f2 = f(context);
        int length = f2.length;
        char c2 = 0;
        int i2 = 0;
        while (true) {
            ZipFile zipFile = null;
            if (i2 >= length) {
                return null;
            }
            String str2 = f2[i2];
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
                        int length2 = strArr.length;
                        int i7 = 0;
                        while (i7 < length2) {
                            String str3 = "lib" + File.separatorChar + strArr[i7] + File.separatorChar + str;
                            Object[] objArr = new Object[2];
                            objArr[c2] = str3;
                            objArr[1] = str2;
                            dVar.i("Looking for %s in APK %s...", objArr);
                            ZipEntry entry = zipFile.getEntry(str3);
                            if (entry != null) {
                                return new C0026a(zipFile, entry);
                            }
                            i7++;
                            c2 = 0;
                        }
                        i5 = i6;
                        c2 = 0;
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
            c2 = 0;
        }
    }

    private String[] e(Context context, String str) {
        Pattern compile = Pattern.compile("lib" + File.separatorChar + "([^\\" + File.separatorChar + "]*)" + File.separatorChar + str);
        HashSet hashSet = new HashSet();
        for (String str2 : f(context)) {
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

    private String[] f(Context context) {
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

    @Override // com.getkeepsafe.relinker.c.a
    public void a(Context context, String[] strArr, String str, File file, d dVar) {
        String[] strArr2;
        Closeable closeable;
        InputStream inputStream;
        long c2;
        C0026a c0026a = null;
        Closeable closeable2 = null;
        try {
            C0026a d = d(context, strArr, str, dVar);
            try {
                if (d == null) {
                    try {
                        strArr2 = e(context, str);
                    } catch (Exception e2) {
                        strArr2 = new String[]{e2.toString()};
                    }
                    throw new b(str, strArr, strArr2);
                }
                int i2 = 0;
                while (true) {
                    int i3 = i2 + 1;
                    if (i2 < 5) {
                        dVar.i("Found %s! Extracting...", str);
                        try {
                            if (file.exists() || file.createNewFile()) {
                                try {
                                    inputStream = d.a.getInputStream(d.b);
                                    try {
                                        closeable = new FileOutputStream(file);
                                        try {
                                            c2 = c(inputStream, closeable);
                                            closeable.getFD().sync();
                                        } catch (FileNotFoundException unused) {
                                            b(inputStream);
                                            b(closeable);
                                            i2 = i3;
                                        } catch (IOException unused2) {
                                            b(inputStream);
                                            b(closeable);
                                            i2 = i3;
                                        } catch (Throwable th) {
                                            th = th;
                                            closeable2 = inputStream;
                                            b(closeable2);
                                            b(closeable);
                                            throw th;
                                        }
                                    } catch (FileNotFoundException unused3) {
                                        closeable = null;
                                    } catch (IOException unused4) {
                                        closeable = null;
                                    } catch (Throwable th2) {
                                        th = th2;
                                        closeable = null;
                                    }
                                } catch (FileNotFoundException unused5) {
                                    inputStream = null;
                                    closeable = null;
                                } catch (IOException unused6) {
                                    inputStream = null;
                                    closeable = null;
                                } catch (Throwable th3) {
                                    th = th3;
                                    closeable = null;
                                }
                                if (c2 != file.length()) {
                                    b(inputStream);
                                    b(closeable);
                                } else {
                                    b(inputStream);
                                    b(closeable);
                                    file.setReadable(true, false);
                                    file.setExecutable(true, false);
                                    file.setWritable(true);
                                    if (d != null) {
                                        try {
                                            ZipFile zipFile = d.a;
                                            if (zipFile != null) {
                                                zipFile.close();
                                                return;
                                            }
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
                        i2 = i3;
                    } else {
                        dVar.h("FATAL! Couldn't extract the library from the APK!");
                        if (d != null) {
                            try {
                                ZipFile zipFile2 = d.a;
                                if (zipFile2 != null) {
                                    zipFile2.close();
                                    return;
                                }
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
                c0026a = d;
                if (c0026a != null) {
                    try {
                        ZipFile zipFile3 = c0026a.a;
                        if (zipFile3 != null) {
                            zipFile3.close();
                        }
                    } catch (IOException unused10) {
                    }
                }
                throw th;
            }
        } catch (Throwable th5) {
            th = th5;
        }
    }
}
