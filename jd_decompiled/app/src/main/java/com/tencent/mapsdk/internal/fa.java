package com.tencent.mapsdk.internal;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import com.facebook.cache.disk.DefaultDiskStorage;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.regex.Pattern;
import org.apache.commons.codec.CharEncoding;

/* loaded from: classes9.dex */
public class fa {
    private static final String a = "FileUtil";
    public static final int b = 1024;

    /* renamed from: c  reason: collision with root package name */
    public static final int f16513c = 1048576;
    public static final int d = 1073741824;

    /* renamed from: e  reason: collision with root package name */
    public static File f16514e;

    /* renamed from: f  reason: collision with root package name */
    private static final bb f16515f = new bb();

    /* loaded from: classes9.dex */
    public static class a implements FileFilter {
        public final /* synthetic */ String a;

        public a(String str) {
            this.a = str;
        }

        @Override // java.io.FileFilter
        public boolean accept(File file) {
            return Pattern.compile(this.a).matcher(file.getName()).matches();
        }
    }

    public static long a(File file, FileFilter fileFilter) {
        File[] listFiles;
        long j2 = 0;
        if (file == null || !file.exists()) {
            return 0L;
        }
        if (file.isDirectory() && (listFiles = file.listFiles(fileFilter)) != null) {
            for (File file2 : listFiles) {
                j2 += a(file2, fileFilter);
            }
        }
        if (fileFilter == null || fileFilter.accept(file)) {
            long length = file.length();
            File file3 = new File(file.getAbsolutePath() + System.currentTimeMillis());
            file.renameTo(file3);
            file3.delete();
            return length;
        }
        return j2;
    }

    public static File a(File file) {
        return a(file.getParentFile(), file.getName());
    }

    public static File a(File file, String str) {
        if (file == null || TextUtils.isEmpty(str)) {
            return null;
        }
        File file2 = new File(file, str);
        file2.mkdirs();
        return file2;
    }

    public static String a(Context context) {
        if (context == null) {
            return "";
        }
        try {
            String packageName = context.getPackageName();
            if (packageName != null && packageName.length() != 0) {
                return packageName.length() > 255 ? packageName.substring(0, 254) : packageName;
            }
            return "";
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:22:0x0045, code lost:
        if (r1 != false) goto L55;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x0070, code lost:
        if (r1 == false) goto L56;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x007f, code lost:
        if (r1 == false) goto L56;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x0081, code lost:
        d(r7);
        b(r4, r7);
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x0088, code lost:
        d(r4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x008b, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:?, code lost:
        return;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void a(java.io.File r7, int r8) {
        /*
            java.lang.String r0 = "rw"
            if (r7 == 0) goto L9f
            boolean r1 = r7.exists()
            if (r1 == 0) goto L9f
            r1 = -1
            if (r8 != r1) goto Lf
            goto L9f
        Lf:
            r1 = 0
            r2 = 0
            java.io.RandomAccessFile r3 = new java.io.RandomAccessFile     // Catch: java.lang.Throwable -> L60 java.io.IOException -> L64 java.io.FileNotFoundException -> L73
            r3.<init>(r7, r0)     // Catch: java.lang.Throwable -> L60 java.io.IOException -> L64 java.io.FileNotFoundException -> L73
            java.io.File r4 = c(r7)     // Catch: java.lang.Throwable -> L57 java.io.IOException -> L5a java.io.FileNotFoundException -> L5d
            java.io.RandomAccessFile r5 = new java.io.RandomAccessFile     // Catch: java.lang.Throwable -> L51 java.io.IOException -> L53 java.io.FileNotFoundException -> L55
            r5.<init>(r4, r0)     // Catch: java.lang.Throwable -> L51 java.io.IOException -> L53 java.io.FileNotFoundException -> L55
            r0 = 0
        L20:
            java.lang.String r2 = r3.readLine()     // Catch: java.lang.Throwable -> L48 java.io.IOException -> L4b java.io.FileNotFoundException -> L4e
            if (r2 == 0) goto L3f
            if (r0 == r8) goto L3b
            java.lang.String r6 = "ISO-8859-1"
            byte[] r2 = r2.getBytes(r6)     // Catch: java.lang.Throwable -> L48 java.io.IOException -> L4b java.io.FileNotFoundException -> L4e
            r5.write(r2)     // Catch: java.lang.Throwable -> L48 java.io.IOException -> L4b java.io.FileNotFoundException -> L4e
            java.lang.String r2 = "\r\n"
            byte[] r2 = r2.getBytes()     // Catch: java.lang.Throwable -> L48 java.io.IOException -> L4b java.io.FileNotFoundException -> L4e
            r5.write(r2)     // Catch: java.lang.Throwable -> L48 java.io.IOException -> L4b java.io.FileNotFoundException -> L4e
            goto L3c
        L3b:
            r1 = 1
        L3c:
            int r0 = r0 + 1
            goto L20
        L3f:
            com.tencent.mapsdk.internal.ga.a(r3)
            com.tencent.mapsdk.internal.ga.a(r5)
            if (r1 == 0) goto L88
            goto L81
        L48:
            r8 = move-exception
            r2 = r5
            goto L8c
        L4b:
            r8 = move-exception
            r2 = r5
            goto L67
        L4e:
            r8 = move-exception
            r2 = r5
            goto L76
        L51:
            r8 = move-exception
            goto L8c
        L53:
            r8 = move-exception
            goto L67
        L55:
            r8 = move-exception
            goto L76
        L57:
            r8 = move-exception
            r4 = r2
            goto L8c
        L5a:
            r8 = move-exception
            r4 = r2
            goto L67
        L5d:
            r8 = move-exception
            r4 = r2
            goto L76
        L60:
            r8 = move-exception
            r3 = r2
            r4 = r3
            goto L8c
        L64:
            r8 = move-exception
            r3 = r2
            r4 = r3
        L67:
            r8.printStackTrace()     // Catch: java.lang.Throwable -> L51
            com.tencent.mapsdk.internal.ga.a(r3)
            com.tencent.mapsdk.internal.ga.a(r2)
            if (r1 == 0) goto L88
            goto L81
        L73:
            r8 = move-exception
            r3 = r2
            r4 = r3
        L76:
            r8.printStackTrace()     // Catch: java.lang.Throwable -> L51
            com.tencent.mapsdk.internal.ga.a(r3)
            com.tencent.mapsdk.internal.ga.a(r2)
            if (r1 == 0) goto L88
        L81:
            d(r7)
            b(r4, r7)
            goto L8b
        L88:
            d(r4)
        L8b:
            return
        L8c:
            com.tencent.mapsdk.internal.ga.a(r3)
            com.tencent.mapsdk.internal.ga.a(r2)
            if (r1 == 0) goto L9b
            d(r7)
            b(r4, r7)
            goto L9e
        L9b:
            d(r4)
        L9e:
            throw r8
        L9f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.mapsdk.internal.fa.a(java.io.File, int):void");
    }

    /* JADX WARN: Code restructure failed: missing block: B:25:0x0061, code lost:
        if (r1 != false) goto L58;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x008c, code lost:
        if (r1 == false) goto L59;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x009b, code lost:
        if (r1 == false) goto L59;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x009d, code lost:
        d(r8);
        b(r4, r8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x00a4, code lost:
        d(r4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x00a7, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:?, code lost:
        return;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void a(java.io.File r8, int r9, java.lang.String r10) {
        /*
            java.lang.String r0 = "rw"
            if (r8 == 0) goto Lbb
            boolean r1 = r8.exists()
            if (r1 == 0) goto Lbb
            r1 = -1
            if (r9 == r1) goto Lbb
            boolean r1 = android.text.TextUtils.isEmpty(r10)
            if (r1 == 0) goto L15
            goto Lbb
        L15:
            r1 = 0
            r2 = 0
            java.io.RandomAccessFile r3 = new java.io.RandomAccessFile     // Catch: java.lang.Throwable -> L7c java.io.IOException -> L80 java.io.FileNotFoundException -> L8f
            r3.<init>(r8, r0)     // Catch: java.lang.Throwable -> L7c java.io.IOException -> L80 java.io.FileNotFoundException -> L8f
            java.io.File r4 = c(r8)     // Catch: java.lang.Throwable -> L73 java.io.IOException -> L76 java.io.FileNotFoundException -> L79
            java.io.RandomAccessFile r5 = new java.io.RandomAccessFile     // Catch: java.lang.Throwable -> L6d java.io.IOException -> L6f java.io.FileNotFoundException -> L71
            r5.<init>(r4, r0)     // Catch: java.lang.Throwable -> L6d java.io.IOException -> L6f java.io.FileNotFoundException -> L71
            r0 = 0
        L26:
            java.lang.String r2 = r3.readLine()     // Catch: java.lang.Throwable -> L64 java.io.IOException -> L67 java.io.FileNotFoundException -> L6a
            if (r2 == 0) goto L5b
            java.lang.String r6 = "\r\n"
            if (r0 == r9) goto L41
            java.lang.String r7 = "ISO-8859-1"
            byte[] r2 = r2.getBytes(r7)     // Catch: java.lang.Throwable -> L64 java.io.IOException -> L67 java.io.FileNotFoundException -> L6a
            r5.write(r2)     // Catch: java.lang.Throwable -> L64 java.io.IOException -> L67 java.io.FileNotFoundException -> L6a
            byte[] r2 = r6.getBytes()     // Catch: java.lang.Throwable -> L64 java.io.IOException -> L67 java.io.FileNotFoundException -> L6a
            r5.write(r2)     // Catch: java.lang.Throwable -> L64 java.io.IOException -> L67 java.io.FileNotFoundException -> L6a
            goto L58
        L41:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L64 java.io.IOException -> L67 java.io.FileNotFoundException -> L6a
            r2.<init>()     // Catch: java.lang.Throwable -> L64 java.io.IOException -> L67 java.io.FileNotFoundException -> L6a
            r2.append(r10)     // Catch: java.lang.Throwable -> L64 java.io.IOException -> L67 java.io.FileNotFoundException -> L6a
            r2.append(r6)     // Catch: java.lang.Throwable -> L64 java.io.IOException -> L67 java.io.FileNotFoundException -> L6a
            java.lang.String r2 = r2.toString()     // Catch: java.lang.Throwable -> L64 java.io.IOException -> L67 java.io.FileNotFoundException -> L6a
            byte[] r2 = r2.getBytes()     // Catch: java.lang.Throwable -> L64 java.io.IOException -> L67 java.io.FileNotFoundException -> L6a
            r5.write(r2)     // Catch: java.lang.Throwable -> L64 java.io.IOException -> L67 java.io.FileNotFoundException -> L6a
            r1 = 1
        L58:
            int r0 = r0 + 1
            goto L26
        L5b:
            com.tencent.mapsdk.internal.ga.a(r3)
            com.tencent.mapsdk.internal.ga.a(r5)
            if (r1 == 0) goto La4
            goto L9d
        L64:
            r9 = move-exception
            r2 = r5
            goto La8
        L67:
            r9 = move-exception
            r2 = r5
            goto L83
        L6a:
            r9 = move-exception
            r2 = r5
            goto L92
        L6d:
            r9 = move-exception
            goto La8
        L6f:
            r9 = move-exception
            goto L83
        L71:
            r9 = move-exception
            goto L92
        L73:
            r9 = move-exception
            r4 = r2
            goto La8
        L76:
            r9 = move-exception
            r4 = r2
            goto L83
        L79:
            r9 = move-exception
            r4 = r2
            goto L92
        L7c:
            r9 = move-exception
            r3 = r2
            r4 = r3
            goto La8
        L80:
            r9 = move-exception
            r3 = r2
            r4 = r3
        L83:
            r9.printStackTrace()     // Catch: java.lang.Throwable -> L6d
            com.tencent.mapsdk.internal.ga.a(r3)
            com.tencent.mapsdk.internal.ga.a(r2)
            if (r1 == 0) goto La4
            goto L9d
        L8f:
            r9 = move-exception
            r3 = r2
            r4 = r3
        L92:
            r9.printStackTrace()     // Catch: java.lang.Throwable -> L6d
            com.tencent.mapsdk.internal.ga.a(r3)
            com.tencent.mapsdk.internal.ga.a(r2)
            if (r1 == 0) goto La4
        L9d:
            d(r8)
            b(r4, r8)
            goto La7
        La4:
            d(r4)
        La7:
            return
        La8:
            com.tencent.mapsdk.internal.ga.a(r3)
            com.tencent.mapsdk.internal.ga.a(r2)
            if (r1 == 0) goto Lb7
            d(r8)
            b(r4, r8)
            goto Lba
        Lb7:
            d(r4)
        Lba:
            throw r9
        Lbb:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.mapsdk.internal.fa.a(java.io.File, int, java.lang.String):void");
    }

    public static void a(File file, File file2) {
        if (!file2.exists()) {
            a(file2.getParentFile(), file2.getName());
        }
        if (file.isDirectory()) {
            File[] listFiles = file.listFiles();
            if (listFiles != null) {
                for (File file3 : listFiles) {
                    a(file3, new File(file2, file.getName()));
                }
                return;
            }
            return;
        }
        File file4 = new File(file2, file.getName());
        if (Build.VERSION.SDK_INT < 26) {
            b(file4, h(file));
            return;
        }
        try {
            Files.copy(file.toPath(), file4.toPath(), new CopyOption[0]);
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    public static void a(File file, byte[] bArr) {
        if (file == null || bArr == null || bArr.length == 0) {
            return;
        }
        if (!file.exists()) {
            b(file);
        }
        RandomAccessFile randomAccessFile = null;
        try {
            try {
                RandomAccessFile randomAccessFile2 = new RandomAccessFile(file, "rw");
                try {
                    randomAccessFile2.seek(randomAccessFile2.length());
                    randomAccessFile2.write(bArr);
                    ga.a(randomAccessFile2);
                } catch (FileNotFoundException e2) {
                    e = e2;
                    randomAccessFile = randomAccessFile2;
                    e.printStackTrace();
                    ga.a(randomAccessFile);
                } catch (IOException e3) {
                    e = e3;
                    randomAccessFile = randomAccessFile2;
                    e.printStackTrace();
                    ga.a(randomAccessFile);
                } catch (Throwable th) {
                    th = th;
                    randomAccessFile = randomAccessFile2;
                    ga.a(randomAccessFile);
                    throw th;
                }
            } catch (FileNotFoundException e4) {
                e = e4;
            } catch (IOException e5) {
                e = e5;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static boolean a(String str) {
        return b(new File(str));
    }

    public static boolean a(String str, String str2) {
        File file = new File(str);
        File file2 = new File(str2);
        if (file2.exists()) {
            e(file2);
        }
        return file.renameTo(new File(str2));
    }

    public static File b(File file, String str) {
        File file2 = null;
        if (file != null && !TextUtils.isEmpty(str)) {
            int lastIndexOf = str.lastIndexOf(File.separator);
            if (lastIndexOf != -1) {
                String substring = str.substring(lastIndexOf + 1);
                File file3 = new File(file, str.substring(0, lastIndexOf));
                str = substring;
                file = file3;
            }
            if (!file.exists() && !file.mkdirs()) {
                if (Log.isLoggable(a, 6)) {
                    String str2 = "create file failed with mkdirs failed:" + file.getAbsolutePath();
                }
                return null;
            }
            file2 = new File(file, str);
            try {
                if (!file2.exists() && !file2.createNewFile() && Log.isLoggable(a, 6)) {
                    String str3 = "create file failed:" + file2.getAbsolutePath();
                }
            } catch (IOException unused) {
                Log.isLoggable(a, 6);
            }
        }
        return file2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v2 */
    /* JADX WARN: Type inference failed for: r1v3 */
    /* JADX WARN: Type inference failed for: r1v4 */
    /* JADX WARN: Type inference failed for: r1v5 */
    /* JADX WARN: Type inference failed for: r1v6 */
    /* JADX WARN: Type inference failed for: r1v7, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r1v8, types: [java.io.Closeable, java.io.BufferedReader] */
    public static String b(File file, int i2) {
        Closeable closeable = null;
        if (file != null && file.exists()) {
            ?? r1 = -1;
            try {
                if (i2 != -1) {
                    try {
                        r1 = new BufferedReader(new FileReader(file));
                        int i3 = 0;
                        while (true) {
                            try {
                                String readLine = r1.readLine();
                                if (readLine == null) {
                                    break;
                                } else if (i3 == i2) {
                                    ga.a((Closeable) r1);
                                    return readLine;
                                } else {
                                    i3++;
                                }
                            } catch (FileNotFoundException e2) {
                                e = e2;
                                e.printStackTrace();
                                ga.a((Closeable) r1);
                                return null;
                            } catch (IOException e3) {
                                e = e3;
                                e.printStackTrace();
                                ga.a((Closeable) r1);
                                return null;
                            }
                        }
                    } catch (FileNotFoundException e4) {
                        e = e4;
                        r1 = 0;
                    } catch (IOException e5) {
                        e = e5;
                        r1 = 0;
                    } catch (Throwable th) {
                        th = th;
                        ga.a(closeable);
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                closeable = r1;
            }
        }
        return null;
    }

    public static String b(String str) {
        if (str == null) {
            return "";
        }
        String trim = str.trim();
        if (trim.contains("../")) {
            trim = trim.replaceAll("\\.\\./", "");
        }
        while (trim.startsWith(File.separator)) {
            trim = trim.substring(1);
        }
        while (trim.endsWith(File.separator)) {
            trim = trim.substring(0, trim.length() - 1);
        }
        return trim;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v1 */
    /* JADX WARN: Type inference failed for: r3v3 */
    /* JADX WARN: Type inference failed for: r3v4 */
    /* JADX WARN: Type inference failed for: r3v5, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r3v6, types: [java.io.RandomAccessFile] */
    public static void b(File file, int i2, String str) {
        Throwable th;
        RandomAccessFile randomAccessFile;
        Closeable closeable;
        IOException e2;
        FileNotFoundException e3;
        ?? r3;
        if (file == null || !file.exists() || i2 == -1) {
            return;
        }
        File file2 = null;
        try {
            try {
                randomAccessFile = new RandomAccessFile(file, "rw");
            } catch (FileNotFoundException e4) {
                e3 = e4;
                file = null;
                randomAccessFile = null;
            } catch (IOException e5) {
                e2 = e5;
                file = null;
                randomAccessFile = null;
            } catch (Throwable th2) {
                th = th2;
                randomAccessFile = null;
                closeable = null;
                ga.a(randomAccessFile);
                ga.a(closeable);
                d(file2);
                throw th;
            }
            try {
                file = c(file);
                try {
                    r3 = new RandomAccessFile(file, "rw");
                    int i3 = 0;
                    long j2 = 0;
                    while (true) {
                        try {
                            String readLine = randomAccessFile.readLine();
                            if (readLine == null) {
                                break;
                            }
                            if (i3 == i2) {
                                j2 = randomAccessFile.getFilePointer() - "\r\n".getBytes().length;
                            } else if (i3 > i2) {
                                r3.write(readLine.getBytes(CharEncoding.ISO_8859_1));
                            }
                            i3++;
                        } catch (FileNotFoundException e6) {
                            e3 = e6;
                            file2 = r3;
                            e3.printStackTrace();
                            r3 = file2;
                            ga.a(randomAccessFile);
                            ga.a((Closeable) r3);
                            d(file);
                        } catch (IOException e7) {
                            e2 = e7;
                            file2 = r3;
                            e2.printStackTrace();
                            r3 = file2;
                            ga.a(randomAccessFile);
                            ga.a((Closeable) r3);
                            d(file);
                        } catch (Throwable th3) {
                            th = th3;
                            file2 = file;
                            closeable = r3;
                            ga.a(randomAccessFile);
                            ga.a(closeable);
                            d(file2);
                            throw th;
                        }
                    }
                    randomAccessFile.seek(j2);
                    randomAccessFile.write((str + "\r\n").getBytes());
                    r3.seek(0L);
                    while (true) {
                        String readLine2 = r3.readLine();
                        if (readLine2 == null) {
                            break;
                        }
                        randomAccessFile.write(readLine2.getBytes(CharEncoding.ISO_8859_1));
                    }
                } catch (FileNotFoundException e8) {
                    e3 = e8;
                } catch (IOException e9) {
                    e2 = e9;
                }
            } catch (FileNotFoundException e10) {
                e3 = e10;
                file = null;
            } catch (IOException e11) {
                e2 = e11;
                file = null;
            } catch (Throwable th4) {
                th = th4;
                file = null;
                r3 = 0;
                file2 = file;
                closeable = r3;
                ga.a(randomAccessFile);
                ga.a(closeable);
                d(file2);
                throw th;
            }
            ga.a(randomAccessFile);
            ga.a((Closeable) r3);
            d(file);
        } catch (Throwable th5) {
            th = th5;
        }
    }

    public static boolean b(File file) {
        if (file == null) {
            return false;
        }
        if (file.exists()) {
            return true;
        }
        File parentFile = file.getParentFile();
        if (parentFile == null) {
            return false;
        }
        if (parentFile.exists() || parentFile.mkdirs()) {
            try {
                return file.createNewFile();
            } catch (IOException e2) {
                e2.printStackTrace();
                return false;
            }
        }
        return false;
    }

    public static boolean b(File file, File file2) {
        if (file == null || !file.exists()) {
            return false;
        }
        d(file2);
        return file.renameTo(file2);
    }

    /* JADX WARN: Code restructure failed: missing block: B:29:0x0031, code lost:
        if (r1 == null) goto L34;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean b(java.io.File r3, byte[] r4) {
        /*
            r0 = 0
            if (r3 == 0) goto L47
            if (r4 != 0) goto L6
            goto L47
        L6:
            boolean r1 = b(r3)
            if (r1 != 0) goto Ld
            return r0
        Ld:
            r1 = 0
            java.io.FileOutputStream r2 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L24 java.io.IOException -> L26 java.io.FileNotFoundException -> L2d
            r2.<init>(r3)     // Catch: java.lang.Throwable -> L24 java.io.IOException -> L26 java.io.FileNotFoundException -> L2d
            r2.write(r4)     // Catch: java.lang.Throwable -> L1b java.io.IOException -> L1e java.io.FileNotFoundException -> L21
            r0 = 1
            r2.close()     // Catch: java.io.IOException -> L37
            goto L3b
        L1b:
            r3 = move-exception
            r1 = r2
            goto L3c
        L1e:
            r3 = move-exception
            r1 = r2
            goto L27
        L21:
            r3 = move-exception
            r1 = r2
            goto L2e
        L24:
            r3 = move-exception
            goto L3c
        L26:
            r3 = move-exception
        L27:
            r3.printStackTrace()     // Catch: java.lang.Throwable -> L24
            if (r1 == 0) goto L3b
            goto L33
        L2d:
            r3 = move-exception
        L2e:
            r3.printStackTrace()     // Catch: java.lang.Throwable -> L24
            if (r1 == 0) goto L3b
        L33:
            r1.close()     // Catch: java.io.IOException -> L37
            goto L3b
        L37:
            r3 = move-exception
            r3.printStackTrace()
        L3b:
            return r0
        L3c:
            if (r1 == 0) goto L46
            r1.close()     // Catch: java.io.IOException -> L42
            goto L46
        L42:
            r4 = move-exception
            r4.printStackTrace()
        L46:
            throw r3
        L47:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.mapsdk.internal.fa.b(java.io.File, byte[]):boolean");
    }

    public static File[] b(File file, FileFilter fileFilter) {
        File[] fileArr = new File[0];
        return (file != null && file.isDirectory()) ? file.listFiles(fileFilter) : fileArr;
    }

    public static File c(File file) {
        if (file != null) {
            File parentFile = file.getParentFile();
            StringBuilder sb = new StringBuilder();
            sb.append(f16515f.a(System.currentTimeMillis() + ""));
            sb.append(DefaultDiskStorage.FileType.TEMP);
            return b(parentFile, sb.toString());
        }
        return null;
    }

    public static File[] c(File file, String str) {
        return b(file, new a(str));
    }

    public static int d(File file, String str) {
        BufferedReader bufferedReader;
        if (file == null || !file.exists()) {
            return -1;
        }
        BufferedReader bufferedReader2 = null;
        try {
            try {
                bufferedReader = new BufferedReader(new FileReader(file));
                int i2 = 0;
                while (true) {
                    try {
                        String readLine = bufferedReader.readLine();
                        if (readLine == null) {
                            break;
                        } else if (readLine.startsWith(str)) {
                            ga.a(bufferedReader);
                            return i2;
                        } else {
                            i2++;
                        }
                    } catch (FileNotFoundException e2) {
                        e = e2;
                        bufferedReader2 = bufferedReader;
                        e.printStackTrace();
                        bufferedReader = bufferedReader2;
                        ga.a(bufferedReader);
                        return -1;
                    } catch (IOException e3) {
                        e = e3;
                        bufferedReader2 = bufferedReader;
                        e.printStackTrace();
                        bufferedReader = bufferedReader2;
                        ga.a(bufferedReader);
                        return -1;
                    } catch (Throwable th) {
                        th = th;
                        bufferedReader2 = bufferedReader;
                        ga.a(bufferedReader2);
                        throw th;
                    }
                }
            } catch (FileNotFoundException e4) {
                e = e4;
            } catch (IOException e5) {
                e = e5;
            }
            ga.a(bufferedReader);
            return -1;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static boolean d(File file) {
        if (file == null || !file.exists()) {
            return false;
        }
        Stack stack = new Stack();
        stack.push(file);
        boolean z = false;
        while (!stack.isEmpty()) {
            File file2 = (File) stack.peek();
            if (!file2.isFile()) {
                if (file2.isDirectory()) {
                    File[] listFiles = file2.listFiles();
                    if (listFiles != null && listFiles.length != 0) {
                        for (File file3 : listFiles) {
                            stack.push(file3);
                        }
                    }
                }
            }
            z = file2.delete();
            stack.pop();
        }
        return z;
    }

    public static long e(File file) {
        return a(file, (FileFilter) null);
    }

    public static void e(File file, String str) {
        if (file == null || TextUtils.isEmpty(str)) {
            return;
        }
        if (!file.exists()) {
            b(file);
        }
        RandomAccessFile randomAccessFile = null;
        try {
            try {
                RandomAccessFile randomAccessFile2 = new RandomAccessFile(file, "rw");
                try {
                    randomAccessFile2.seek(randomAccessFile2.length());
                    randomAccessFile2.write((str + "\r\n").getBytes());
                    ga.a(randomAccessFile2);
                } catch (FileNotFoundException e2) {
                    e = e2;
                    randomAccessFile = randomAccessFile2;
                    e.printStackTrace();
                    ga.a(randomAccessFile);
                } catch (IOException e3) {
                    e = e3;
                    randomAccessFile = randomAccessFile2;
                    e.printStackTrace();
                    ga.a(randomAccessFile);
                } catch (Throwable th) {
                    th = th;
                    randomAccessFile = randomAccessFile2;
                    ga.a(randomAccessFile);
                    throw th;
                }
            } catch (FileNotFoundException e4) {
                e = e4;
            } catch (IOException e5) {
                e = e5;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static boolean f(File file) {
        if (file != null && file.exists() && file.isFile()) {
            return file.delete();
        }
        return false;
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [boolean] */
    public static List<String> g(File file) {
        BufferedReader bufferedReader;
        Closeable closeable = null;
        if (file != null) {
            ?? exists = file.exists();
            try {
                if (exists != 0) {
                    try {
                        bufferedReader = new BufferedReader(new FileReader(file));
                    } catch (FileNotFoundException e2) {
                        e = e2;
                        bufferedReader = null;
                    } catch (IOException e3) {
                        e = e3;
                        bufferedReader = null;
                    } catch (Throwable th) {
                        th = th;
                        ga.a(closeable);
                        throw th;
                    }
                    try {
                        ArrayList arrayList = new ArrayList();
                        while (true) {
                            String readLine = bufferedReader.readLine();
                            if (readLine == null) {
                                ga.a(bufferedReader);
                                return arrayList;
                            }
                            arrayList.add(readLine);
                        }
                    } catch (FileNotFoundException e4) {
                        e = e4;
                        e.printStackTrace();
                        ga.a(bufferedReader);
                        return null;
                    } catch (IOException e5) {
                        e = e5;
                        e.printStackTrace();
                        ga.a(bufferedReader);
                        return null;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                closeable = exists;
            }
        }
        return null;
    }

    /* JADX WARN: Code restructure failed: missing block: B:44:0x0062, code lost:
        if (r1 == 0) goto L59;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x0079, code lost:
        if (r1 == 0) goto L59;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x007b, code lost:
        r1.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x007f, code lost:
        r6 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x0080, code lost:
        r6.printStackTrace();
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0 */
    /* JADX WARN: Type inference failed for: r0v13 */
    /* JADX WARN: Type inference failed for: r0v2 */
    /* JADX WARN: Type inference failed for: r0v3, types: [java.io.ByteArrayOutputStream] */
    /* JADX WARN: Type inference failed for: r1v0, types: [boolean] */
    /* JADX WARN: Type inference failed for: r1v1, types: [java.io.FileInputStream] */
    /* JADX WARN: Type inference failed for: r1v2 */
    /* JADX WARN: Type inference failed for: r1v3 */
    /* JADX WARN: Type inference failed for: r1v4 */
    /* JADX WARN: Type inference failed for: r1v5 */
    /* JADX WARN: Type inference failed for: r1v6, types: [java.io.FileInputStream] */
    /* JADX WARN: Type inference failed for: r1v7 */
    /* JADX WARN: Type inference failed for: r1v8, types: [java.io.FileInputStream] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static byte[] h(java.io.File r6) {
        /*
            r0 = 0
            if (r6 == 0) goto La0
            boolean r1 = r6.exists()
            if (r1 != 0) goto Lb
            goto La0
        Lb:
            java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L4b java.io.IOException -> L4e java.io.FileNotFoundException -> L65
            r1.<init>(r6)     // Catch: java.lang.Throwable -> L4b java.io.IOException -> L4e java.io.FileNotFoundException -> L65
            java.io.ByteArrayOutputStream r6 = new java.io.ByteArrayOutputStream     // Catch: java.lang.Throwable -> L41 java.io.IOException -> L43 java.io.FileNotFoundException -> L47
            r6.<init>()     // Catch: java.lang.Throwable -> L41 java.io.IOException -> L43 java.io.FileNotFoundException -> L47
            r2 = 1024(0x400, float:1.435E-42)
            byte[] r2 = new byte[r2]     // Catch: java.io.IOException -> L3d java.io.FileNotFoundException -> L3f java.lang.Throwable -> L84
        L19:
            int r3 = r1.read(r2)     // Catch: java.io.IOException -> L3d java.io.FileNotFoundException -> L3f java.lang.Throwable -> L84
            r4 = -1
            if (r3 == r4) goto L25
            r4 = 0
            r6.write(r2, r4, r3)     // Catch: java.io.IOException -> L3d java.io.FileNotFoundException -> L3f java.lang.Throwable -> L84
            goto L19
        L25:
            byte[] r0 = r6.toByteArray()     // Catch: java.io.IOException -> L3d java.io.FileNotFoundException -> L3f java.lang.Throwable -> L84
            r6.flush()     // Catch: java.io.IOException -> L30
            r6.close()     // Catch: java.io.IOException -> L30
            goto L34
        L30:
            r6 = move-exception
            r6.printStackTrace()
        L34:
            r1.close()     // Catch: java.io.IOException -> L38
            goto L3c
        L38:
            r6 = move-exception
            r6.printStackTrace()
        L3c:
            return r0
        L3d:
            r2 = move-exception
            goto L52
        L3f:
            r2 = move-exception
            goto L69
        L41:
            r6 = move-exception
            goto L88
        L43:
            r6 = move-exception
            r2 = r6
            r6 = r0
            goto L52
        L47:
            r6 = move-exception
            r2 = r6
            r6 = r0
            goto L69
        L4b:
            r6 = move-exception
            r1 = r0
            goto L88
        L4e:
            r6 = move-exception
            r2 = r6
            r6 = r0
            r1 = r6
        L52:
            r2.printStackTrace()     // Catch: java.lang.Throwable -> L84
            if (r6 == 0) goto L62
            r6.flush()     // Catch: java.io.IOException -> L5e
            r6.close()     // Catch: java.io.IOException -> L5e
            goto L62
        L5e:
            r6 = move-exception
            r6.printStackTrace()
        L62:
            if (r1 == 0) goto L83
            goto L7b
        L65:
            r6 = move-exception
            r2 = r6
            r6 = r0
            r1 = r6
        L69:
            r2.printStackTrace()     // Catch: java.lang.Throwable -> L84
            if (r6 == 0) goto L79
            r6.flush()     // Catch: java.io.IOException -> L75
            r6.close()     // Catch: java.io.IOException -> L75
            goto L79
        L75:
            r6 = move-exception
            r6.printStackTrace()
        L79:
            if (r1 == 0) goto L83
        L7b:
            r1.close()     // Catch: java.io.IOException -> L7f
            goto L83
        L7f:
            r6 = move-exception
            r6.printStackTrace()
        L83:
            return r0
        L84:
            r0 = move-exception
            r5 = r0
            r0 = r6
            r6 = r5
        L88:
            if (r0 == 0) goto L95
            r0.flush()     // Catch: java.io.IOException -> L91
            r0.close()     // Catch: java.io.IOException -> L91
            goto L95
        L91:
            r0 = move-exception
            r0.printStackTrace()
        L95:
            if (r1 == 0) goto L9f
            r1.close()     // Catch: java.io.IOException -> L9b
            goto L9f
        L9b:
            r0 = move-exception
            r0.printStackTrace()
        L9f:
            throw r6
        La0:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.mapsdk.internal.fa.h(java.io.File):byte[]");
    }
}
