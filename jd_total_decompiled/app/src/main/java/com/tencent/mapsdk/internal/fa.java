package com.tencent.mapsdk.internal;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import com.facebook.cache.disk.DefaultDiskStorage;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
    */
    public static void a(File file, int i2) {
        RandomAccessFile randomAccessFile;
        File file2;
        String readLine;
        if (file == null || !file.exists() || i2 == -1) {
            return;
        }
        boolean z = false;
        RandomAccessFile randomAccessFile2 = null;
        try {
            try {
                randomAccessFile = new RandomAccessFile(file, "rw");
                try {
                    file2 = c(file);
                    try {
                        RandomAccessFile randomAccessFile3 = new RandomAccessFile(file2, "rw");
                        int i3 = 0;
                        while (true) {
                            try {
                                readLine = randomAccessFile.readLine();
                                if (readLine == null) {
                                    break;
                                }
                                if (i3 != i2) {
                                    randomAccessFile3.write(readLine.getBytes(CharEncoding.ISO_8859_1));
                                    randomAccessFile3.write("\r\n".getBytes());
                                } else {
                                    z = true;
                                }
                                i3++;
                            } catch (FileNotFoundException e2) {
                                e = e2;
                                randomAccessFile2 = randomAccessFile3;
                                e.printStackTrace();
                                ga.a(randomAccessFile);
                                ga.a(randomAccessFile2);
                                randomAccessFile2 = randomAccessFile2;
                            } catch (IOException e3) {
                                e = e3;
                                randomAccessFile2 = randomAccessFile3;
                                e.printStackTrace();
                                ga.a(randomAccessFile);
                                ga.a(randomAccessFile2);
                                randomAccessFile2 = randomAccessFile2;
                            } catch (Throwable th) {
                                th = th;
                                randomAccessFile2 = randomAccessFile3;
                                ga.a(randomAccessFile);
                                ga.a(randomAccessFile2);
                                if (z) {
                                    d(file);
                                    b(file2, file);
                                } else {
                                    d(file2);
                                }
                                throw th;
                            }
                        }
                        ga.a(randomAccessFile);
                        ga.a(randomAccessFile3);
                        randomAccessFile2 = readLine;
                    } catch (FileNotFoundException e4) {
                        e = e4;
                    } catch (IOException e5) {
                        e = e5;
                    }
                } catch (FileNotFoundException e6) {
                    e = e6;
                    file2 = null;
                } catch (IOException e7) {
                    e = e7;
                    file2 = null;
                } catch (Throwable th2) {
                    th = th2;
                    file2 = null;
                }
            } catch (FileNotFoundException e8) {
                e = e8;
                randomAccessFile = null;
                file2 = null;
            } catch (IOException e9) {
                e = e9;
                randomAccessFile = null;
                file2 = null;
            } catch (Throwable th3) {
                th = th3;
                randomAccessFile = null;
                file2 = null;
            }
        } catch (Throwable th4) {
            th = th4;
        }
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
    */
    public static void a(File file, int i2, String str) {
        RandomAccessFile randomAccessFile;
        File file2;
        String readLine;
        if (file == null || !file.exists() || i2 == -1 || TextUtils.isEmpty(str)) {
            return;
        }
        boolean z = false;
        RandomAccessFile randomAccessFile2 = null;
        try {
            try {
                randomAccessFile = new RandomAccessFile(file, "rw");
                try {
                    file2 = c(file);
                    try {
                        RandomAccessFile randomAccessFile3 = new RandomAccessFile(file2, "rw");
                        int i3 = 0;
                        while (true) {
                            try {
                                readLine = randomAccessFile.readLine();
                                if (readLine == null) {
                                    break;
                                }
                                if (i3 != i2) {
                                    randomAccessFile3.write(readLine.getBytes(CharEncoding.ISO_8859_1));
                                    randomAccessFile3.write("\r\n".getBytes());
                                } else {
                                    randomAccessFile3.write((str + "\r\n").getBytes());
                                    z = true;
                                }
                                i3++;
                            } catch (FileNotFoundException e2) {
                                e = e2;
                                randomAccessFile2 = randomAccessFile3;
                                e.printStackTrace();
                                ga.a(randomAccessFile);
                                ga.a(randomAccessFile2);
                                randomAccessFile2 = randomAccessFile2;
                            } catch (IOException e3) {
                                e = e3;
                                randomAccessFile2 = randomAccessFile3;
                                e.printStackTrace();
                                ga.a(randomAccessFile);
                                ga.a(randomAccessFile2);
                                randomAccessFile2 = randomAccessFile2;
                            } catch (Throwable th) {
                                th = th;
                                randomAccessFile2 = randomAccessFile3;
                                ga.a(randomAccessFile);
                                ga.a(randomAccessFile2);
                                if (z) {
                                    d(file);
                                    b(file2, file);
                                } else {
                                    d(file2);
                                }
                                throw th;
                            }
                        }
                        ga.a(randomAccessFile);
                        ga.a(randomAccessFile3);
                        randomAccessFile2 = readLine;
                    } catch (FileNotFoundException e4) {
                        e = e4;
                    } catch (IOException e5) {
                        e = e5;
                    }
                } catch (FileNotFoundException e6) {
                    e = e6;
                    file2 = null;
                } catch (IOException e7) {
                    e = e7;
                    file2 = null;
                } catch (Throwable th2) {
                    th = th2;
                    file2 = null;
                }
            } catch (FileNotFoundException e8) {
                e = e8;
                randomAccessFile = null;
                file2 = null;
            } catch (IOException e9) {
                e = e9;
                randomAccessFile = null;
                file2 = null;
            } catch (Throwable th3) {
                th = th3;
                randomAccessFile = null;
                file2 = null;
            }
        } catch (Throwable th4) {
            th = th4;
        }
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
    */
    public static boolean b(File file, byte[] bArr) {
        boolean z = false;
        if (file == null || bArr == null || !b(file)) {
            return false;
        }
        FileOutputStream fileOutputStream = null;
        try {
            try {
                try {
                    FileOutputStream fileOutputStream2 = new FileOutputStream(file);
                    try {
                        fileOutputStream2.write(bArr);
                        z = true;
                        fileOutputStream2.close();
                    } catch (FileNotFoundException e2) {
                        e = e2;
                        fileOutputStream = fileOutputStream2;
                        e.printStackTrace();
                    } catch (IOException e3) {
                        e = e3;
                        fileOutputStream = fileOutputStream2;
                        e.printStackTrace();
                        if (fileOutputStream != null) {
                            fileOutputStream.close();
                        }
                        return z;
                    } catch (Throwable th) {
                        th = th;
                        fileOutputStream = fileOutputStream2;
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (IOException e4) {
                                e4.printStackTrace();
                            }
                        }
                        throw th;
                    }
                } catch (FileNotFoundException e5) {
                    e = e5;
                } catch (IOException e6) {
                    e = e6;
                }
            } catch (IOException e7) {
                e7.printStackTrace();
            }
            return z;
        } catch (Throwable th2) {
            th = th2;
        }
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
    */
    public static byte[] h(File file) {
        Throwable th;
        IOException e2;
        ByteArrayOutputStream byteArrayOutputStream;
        FileNotFoundException e3;
        ?? r0 = 0;
        r0 = 0;
        if (file != null) {
            ?? exists = file.exists();
            try {
                if (exists != 0) {
                    try {
                        exists = new FileInputStream(file);
                        try {
                            byteArrayOutputStream = new ByteArrayOutputStream();
                        } catch (FileNotFoundException e4) {
                            e3 = e4;
                            byteArrayOutputStream = null;
                        } catch (IOException e5) {
                            e2 = e5;
                            byteArrayOutputStream = null;
                        } catch (Throwable th2) {
                            th = th2;
                            if (r0 != 0) {
                                try {
                                    r0.flush();
                                    r0.close();
                                } catch (IOException e6) {
                                    e6.printStackTrace();
                                }
                            }
                            if (exists != 0) {
                                try {
                                    exists.close();
                                } catch (IOException e7) {
                                    e7.printStackTrace();
                                }
                            }
                            throw th;
                        }
                    } catch (FileNotFoundException e8) {
                        e3 = e8;
                        byteArrayOutputStream = null;
                        exists = 0;
                    } catch (IOException e9) {
                        e2 = e9;
                        byteArrayOutputStream = null;
                        exists = 0;
                    } catch (Throwable th3) {
                        th = th3;
                        exists = 0;
                    }
                    try {
                        byte[] bArr = new byte[1024];
                        while (true) {
                            int read = exists.read(bArr);
                            if (read == -1) {
                                break;
                            }
                            byteArrayOutputStream.write(bArr, 0, read);
                        }
                        byte[] byteArray = byteArrayOutputStream.toByteArray();
                        try {
                            byteArrayOutputStream.flush();
                            byteArrayOutputStream.close();
                        } catch (IOException e10) {
                            e10.printStackTrace();
                        }
                        try {
                            exists.close();
                        } catch (IOException e11) {
                            e11.printStackTrace();
                        }
                        return byteArray;
                    } catch (FileNotFoundException e12) {
                        e3 = e12;
                        e3.printStackTrace();
                        if (byteArrayOutputStream != null) {
                            try {
                                byteArrayOutputStream.flush();
                                byteArrayOutputStream.close();
                            } catch (IOException e13) {
                                e13.printStackTrace();
                            }
                        }
                    } catch (IOException e14) {
                        e2 = e14;
                        e2.printStackTrace();
                        if (byteArrayOutputStream != null) {
                            try {
                                byteArrayOutputStream.flush();
                                byteArrayOutputStream.close();
                            } catch (IOException e15) {
                                e15.printStackTrace();
                            }
                        }
                    }
                }
            } catch (Throwable th4) {
                r0 = file;
                th = th4;
            }
        }
        return null;
        return null;
    }
}
