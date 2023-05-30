package com.jingdong.manto.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes16.dex */
public class s {
    public static String a(String str, long j2, long j3) {
        InputStreamReader inputStreamReader;
        File file;
        StringBuffer stringBuffer = new StringBuffer();
        try {
            file = new File(str);
            inputStreamReader = new InputStreamReader(new FileInputStream(file));
        } catch (Throwable unused) {
            inputStreamReader = null;
        }
        if (j2 >= file.length()) {
            t.a(inputStreamReader);
            return "";
        }
        if (j2 < 0) {
            j2 = 0;
        }
        inputStreamReader.skip(j2);
        if (j3 + j2 > file.length()) {
            j3 = file.length() - j2;
        }
        long j4 = j3 / 1024;
        Long.signum(j4);
        long j5 = j3 - (1024 * j4);
        char[] cArr = new char[1024];
        for (int i2 = 0; i2 < j4; i2++) {
            if (-1 != inputStreamReader.read(cArr, 0, 1024)) {
                stringBuffer.append(String.valueOf(cArr));
            }
        }
        if (j5 > 0) {
            int i3 = (int) j5;
            if (-1 != inputStreamReader.read(cArr, 0, i3)) {
                stringBuffer.append(String.valueOf(cArr, 0, i3));
            }
        }
        t.a(inputStreamReader);
        return stringBuffer.toString();
    }

    public static String a(String str, String str2, long j2, long j3) {
        InputStreamReader inputStreamReader;
        File file;
        StringBuffer stringBuffer = new StringBuffer();
        try {
            file = new File(str);
            inputStreamReader = new InputStreamReader(new FileInputStream(file), str2);
        } catch (Throwable unused) {
            inputStreamReader = null;
        }
        if (j2 >= file.length()) {
            t.a(inputStreamReader);
            return "";
        }
        if (j2 < 0) {
            j2 = 0;
        }
        inputStreamReader.skip(j2);
        if (j3 + j2 > file.length()) {
            j3 = file.length() - j2;
        }
        long j4 = j3 / 1024;
        Long.signum(j4);
        long j5 = j3 - (1024 * j4);
        char[] cArr = new char[1024];
        for (int i2 = 0; i2 < j4; i2++) {
            if (-1 != inputStreamReader.read(cArr, 0, 1024)) {
                stringBuffer.append(String.valueOf(cArr));
            }
        }
        if (j5 > 0) {
            int i3 = (int) j5;
            if (-1 != inputStreamReader.read(cArr, 0, i3)) {
                stringBuffer.append(String.valueOf(cArr, 0, i3));
            }
        }
        t.a(inputStreamReader);
        return stringBuffer.toString();
    }

    public static List<File> a(File file, boolean z) {
        ArrayList arrayList = new ArrayList();
        for (File file2 : file.listFiles()) {
            if (!file2.isDirectory()) {
                arrayList.add(file2);
            }
            if (file2.isDirectory() && z) {
                arrayList.addAll(a(file2, true));
            }
        }
        return arrayList;
    }

    private static void a(String str) {
        int length = str.length();
        for (int i2 = 0; i2 < length; i2++) {
            if (str.charAt(i2) == 0) {
                throw new IllegalArgumentException("Null byte present in file/path name. There are no known legitimate use cases for such data, but several injection attacks may use it");
            }
        }
    }

    public static boolean a(File file) {
        File[] listFiles;
        if (file == null || !file.exists()) {
            return false;
        }
        if (file.isFile()) {
            file.delete();
            return true;
        } else if (!file.isDirectory() || (listFiles = file.listFiles()) == null) {
            return true;
        } else {
            for (File file2 : listFiles) {
                a(file2);
            }
            return true;
        }
    }

    public static boolean a(InputStream inputStream, String str) {
        return a(inputStream, str, false);
    }

    public static boolean a(InputStream inputStream, String str, boolean z) {
        if (inputStream == null) {
            return false;
        }
        try {
            File file = new File(str);
            if (file.getParentFile() != null && !file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            if (!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(str, z);
            try {
                byte[] bArr = new byte[10240];
                while (true) {
                    int read = inputStream.read(bArr);
                    if (read == -1) {
                        break;
                    }
                    fileOutputStream.write(bArr, 0, read);
                }
            } catch (IOException unused) {
                t.a(inputStream);
                t.a(fileOutputStream);
                return false;
            } catch (Throwable unused2) {
            }
            t.a(inputStream);
            t.a(fileOutputStream);
            return true;
        } catch (IOException e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public static boolean a(String str, String str2) {
        if (!MantoStringUtils.isEmpty(str) && !MantoStringUtils.isEmpty(str2)) {
            if (str.equals(str2)) {
                return true;
            }
            try {
                File file = new File(str2);
                if (file.getParentFile() != null && !file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                }
                if (!file.exists()) {
                    file.createNewFile();
                }
                FileInputStream fileInputStream = new FileInputStream(str);
                FileOutputStream fileOutputStream = new FileOutputStream(str2);
                try {
                    byte[] bArr = new byte[10240];
                    while (true) {
                        int read = fileInputStream.read(bArr);
                        if (read == -1) {
                            break;
                        }
                        fileOutputStream.write(bArr, 0, read);
                    }
                } catch (IOException unused) {
                    t.a(fileInputStream);
                    t.a(fileOutputStream);
                    return false;
                } catch (Throwable unused2) {
                }
                t.a(fileInputStream);
                t.a(fileOutputStream);
                return true;
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
        return false;
    }

    public static String b(String str, long j2, long j3) {
        try {
            File file = new File(str);
            FileInputStream fileInputStream = new FileInputStream(file);
            if (j2 >= file.length()) {
                return "";
            }
            if (j2 < 0) {
                j2 = 0;
            }
            if (j3 + j2 > file.length()) {
                j3 = file.length() - j2;
            }
            fileInputStream.skip(j2);
            long j4 = j3 / 1024;
            Long.signum(j4);
            long j5 = j3 - (1024 * j4);
            byte[] bArr = new byte[(int) j3];
            int i2 = 0;
            for (int i3 = 0; i3 < j4; i3++) {
                int read = fileInputStream.read(bArr, i2, 1024);
                if (-1 != read) {
                    i2 += read;
                }
            }
            if (j5 > 0) {
                fileInputStream.read(bArr, i2, (int) j5);
            }
            return com.jingdong.manto.j.a.b(bArr);
        } catch (Throwable th) {
            th.printStackTrace();
            return "";
        }
    }

    public static boolean b(File file) {
        File[] listFiles;
        if (file == null || !file.exists()) {
            return false;
        }
        if (file.isFile()) {
            file.delete();
        } else if (file.isDirectory() && (listFiles = file.listFiles()) != null) {
            for (File file2 : listFiles) {
                b(file2);
            }
        }
        file.delete();
        return true;
    }

    public static boolean b(String str) {
        try {
            File file = new File(str);
            if (file.exists()) {
                return true;
            }
            file.mkdirs();
            File file2 = new File(str, ".nomedia");
            if (file2.exists()) {
                return true;
            }
            file2.createNewFile();
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public static boolean c(String str) {
        return b(new File(str));
    }

    public static boolean d(String str) {
        return str != null && new File(str).exists();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:74:0x00c3 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:78:0x00cd A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r6v0 */
    /* JADX WARN: Type inference failed for: r6v1, types: [java.io.FileInputStream] */
    /* JADX WARN: Type inference failed for: r6v2 */
    /* JADX WARN: Type inference failed for: r6v3 */
    /* JADX WARN: Type inference failed for: r6v4, types: [java.io.FileInputStream] */
    /* JADX WARN: Type inference failed for: r6v7, types: [java.io.FileInputStream] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String e(java.lang.String r14) {
        /*
            Method dump skipped, instructions count: 216
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.manto.utils.s.e(java.lang.String):java.lang.String");
    }

    public static String f(String str) {
        if (str == null) {
            return null;
        }
        a(str);
        return str.substring(Math.max(str.lastIndexOf(47), str.lastIndexOf(92)) + 1);
    }
}
