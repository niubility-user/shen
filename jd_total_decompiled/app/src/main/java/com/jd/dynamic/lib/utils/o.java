package com.jd.dynamic.lib.utils;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

/* loaded from: classes13.dex */
public class o {
    public static File a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return new File(str);
    }

    public static List<File> b(File file, File file2, String str) {
        if (file == null || file2 == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        ZipFile zipFile = new ZipFile(file);
        Enumeration<? extends ZipEntry> entries = zipFile.entries();
        try {
            if (TextUtils.isEmpty(str)) {
                while (entries.hasMoreElements()) {
                    ZipEntry nextElement = entries.nextElement();
                    String replace = nextElement.getName().replace("\\", "/");
                    if (!replace.contains("../") && !h(file2, arrayList, zipFile, nextElement, replace)) {
                        return arrayList;
                    }
                }
            } else {
                while (entries.hasMoreElements()) {
                    ZipEntry nextElement2 = entries.nextElement();
                    String replace2 = nextElement2.getName().replace("\\", "/");
                    if (!replace2.contains("../") && replace2.contains(str) && !h(file2, arrayList, zipFile, nextElement2, replace2)) {
                        return arrayList;
                    }
                }
            }
            return arrayList;
        } finally {
            zipFile.close();
        }
    }

    public static List<File> c(String str, String str2) {
        return d(str, str2, null);
    }

    public static List<File> d(String str, String str2, String str3) {
        return b(a(str), a(str2), str3);
    }

    public static void e(InputStream inputStream, File file) {
        FileOutputStream fileOutputStream = null;
        try {
            try {
                byte[] bArr = new byte[2048];
                FileOutputStream fileOutputStream2 = new FileOutputStream(file);
                while (true) {
                    try {
                        int read = inputStream.read(bArr);
                        if (read <= -1) {
                            fileOutputStream2.close();
                            return;
                        }
                        fileOutputStream2.write(bArr, 0, read);
                    } catch (Exception e2) {
                        e = e2;
                        fileOutputStream = fileOutputStream2;
                        e.printStackTrace();
                        if (fileOutputStream != null) {
                            fileOutputStream.close();
                            return;
                        }
                        return;
                    } catch (Throwable th) {
                        th = th;
                        fileOutputStream = fileOutputStream2;
                        if (fileOutputStream != null) {
                            fileOutputStream.close();
                        }
                        throw th;
                    }
                }
            } catch (Exception e3) {
                e = e3;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static void f(InputStream inputStream, String str) {
        ZipInputStream zipInputStream = new ZipInputStream(inputStream);
        while (true) {
            ZipEntry nextEntry = zipInputStream.getNextEntry();
            if (nextEntry == null) {
                zipInputStream.close();
                return;
            }
            String replace = nextEntry.getName().replace("\\", "/");
            if (!replace.contains("../")) {
                if (nextEntry.isDirectory()) {
                    new File(str + File.separator + replace.substring(0, replace.length() - 1)).mkdirs();
                } else {
                    StringBuilder sb = new StringBuilder();
                    sb.append(str);
                    String str2 = File.separator;
                    sb.append(str2);
                    sb.append(replace);
                    t.e("FileUtils", sb.toString());
                    File file = new File(str + str2 + replace);
                    if (!file.exists()) {
                        t.e("FileUtils", "Create the file:" + str + str2 + replace);
                        file.getParentFile().mkdirs();
                        file.createNewFile();
                    }
                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                    byte[] bArr = new byte[1024];
                    while (true) {
                        int read = zipInputStream.read(bArr);
                        if (read == -1) {
                            break;
                        }
                        fileOutputStream.write(bArr, 0, read);
                        fileOutputStream.flush();
                    }
                    fileOutputStream.close();
                }
            }
        }
    }

    public static boolean g(File file) {
        return file != null && (!file.exists() ? !file.mkdirs() : !file.isDirectory());
    }

    /* JADX WARN: Removed duplicated region for block: B:110:0x0051  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x0056  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static boolean h(File file, List<File> list, ZipFile zipFile, ZipEntry zipEntry, String str) {
        BufferedOutputStream bufferedOutputStream;
        BufferedInputStream bufferedInputStream;
        Throwable th;
        File file2 = new File(file, str);
        list.add(file2);
        if (zipEntry.isDirectory()) {
            return g(file2);
        }
        if (!j(file2)) {
            return false;
        }
        try {
            bufferedInputStream = new BufferedInputStream(zipFile.getInputStream(zipEntry));
        } catch (Throwable th2) {
            th = th2;
            bufferedOutputStream = null;
            bufferedInputStream = null;
        }
        try {
            bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file2));
        } catch (Throwable th3) {
            th = th3;
            bufferedOutputStream = null;
            th = th;
            if (bufferedInputStream != null) {
            }
            if (bufferedOutputStream != null) {
            }
            throw th;
        }
        try {
            byte[] bArr = new byte[8192];
            while (true) {
                int read = bufferedInputStream.read(bArr);
                if (read == -1) {
                    bufferedInputStream.close();
                    bufferedOutputStream.close();
                    return true;
                }
                bufferedOutputStream.write(bArr, 0, read);
            }
        } catch (Throwable th4) {
            th = th4;
            if (bufferedInputStream != null) {
                bufferedInputStream.close();
            }
            if (bufferedOutputStream != null) {
                bufferedOutputStream.close();
            }
            throw th;
        }
    }

    public static String i(@NonNull String str) {
        return str.replace(".zip", "/");
    }

    public static boolean j(File file) {
        if (file == null) {
            return false;
        }
        if (file.exists()) {
            return file.isFile();
        }
        if (g(file.getParentFile())) {
            try {
                return file.createNewFile();
            } catch (IOException e2) {
                e2.printStackTrace();
                return false;
            }
        }
        return false;
    }

    public static String k(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        File file = new File(str);
        if (file.exists() && file.isDirectory()) {
            String m2 = m(str + File.separator + "version.json");
            if (TextUtils.isEmpty(m2)) {
                return null;
            }
            try {
                Map map = (Map) new Gson().fromJson(m2, new TypeToken<Map<String, String>>() { // from class: com.jd.dynamic.lib.utils.o.1
                }.getType());
                return map != null ? (String) map.get("version") : "";
            } catch (Exception e2) {
                e2.printStackTrace();
                return "";
            }
        }
        return null;
    }

    public static boolean l(File file) {
        if (file != null || file.exists()) {
            String name = file.getName();
            if (TextUtils.isEmpty(name)) {
                return false;
            }
            return name.toLowerCase().endsWith(".out");
        }
        return false;
    }

    public static String m(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return o(new File(str));
    }

    public static boolean n(File file) {
        if (file != null || file.exists()) {
            String name = file.getName();
            if (TextUtils.isEmpty(name)) {
                return false;
            }
            return name.toLowerCase().endsWith(".js");
        }
        return false;
    }

    public static String o(File file) {
        FileInputStream fileInputStream = null;
        if (file != null && file.exists() && file.isFile()) {
            StringBuilder sb = new StringBuilder();
            try {
                try {
                    try {
                        byte[] bArr = new byte[1024];
                        FileInputStream fileInputStream2 = new FileInputStream(file);
                        while (true) {
                            try {
                                int read = fileInputStream2.read(bArr);
                                if (read == -1) {
                                    break;
                                }
                                sb.append(new String(bArr, 0, read));
                            } catch (Exception e2) {
                                e = e2;
                                fileInputStream = fileInputStream2;
                                e.printStackTrace();
                                if (fileInputStream != null) {
                                    fileInputStream.close();
                                }
                                return sb.toString();
                            } catch (Throwable th) {
                                th = th;
                                fileInputStream = fileInputStream2;
                                if (fileInputStream != null) {
                                    try {
                                        fileInputStream.close();
                                    } catch (IOException e3) {
                                        e3.printStackTrace();
                                    }
                                }
                                throw th;
                            }
                        }
                        fileInputStream2.close();
                    } catch (Throwable th2) {
                        th = th2;
                    }
                } catch (Exception e4) {
                    e = e4;
                }
            } catch (IOException e5) {
                e5.printStackTrace();
            }
            return sb.toString();
        }
        return null;
    }

    public static boolean p(File file) {
        return file != null && file.exists() && file.isDirectory();
    }

    public static void q(File file) {
        if (file == null || !file.exists()) {
            return;
        }
        if (file.isFile()) {
            file.delete();
        }
        if (file.isDirectory()) {
            for (File file2 : file.listFiles()) {
                if (file2.isFile()) {
                    file2.delete();
                } else if (file2.isDirectory()) {
                    q(file2);
                }
            }
            file.delete();
        }
    }
}
