package com.jd.lib.un.utils;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

/* loaded from: classes16.dex */
public class UnZipUtils {
    private static final int BUFFER_LEN = 8192;
    private static final String TAG = "FileUtils";
    public static final String ZIP_SUFFIX = ".zip";

    private UnZipUtils() {
        throw new UnsupportedOperationException("\u4e0d\u652f\u6301\u53cd\u5c04\u5bf9\u8c61");
    }

    public static boolean createOrExistsDir(String str) {
        return createOrExistsDir(getFileByPath(str));
    }

    public static boolean createOrExistsFile(String str) {
        return createOrExistsFile(getFileByPath(str));
    }

    public static String createUnZipDir(@NonNull String str) {
        return str.replace(".zip", "/");
    }

    public static void deleteFile(File file) {
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
                    deleteFile(file2);
                }
            }
            file.delete();
        }
    }

    public static boolean dirIsExists(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        File file = new File(str);
        return file.exists() && file.isDirectory();
    }

    public static File getFileByPath(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return new File(str);
    }

    public static File[] getFilesByZipDir(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        File file = new File(str);
        if (file.exists() && file.isDirectory()) {
            return file.listFiles();
        }
        return null;
    }

    public static String getParentDir(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        File file = new File(str);
        if (file.exists()) {
            return file.getParent();
        }
        return null;
    }

    public static String readTxt(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return readTxt(new File(str));
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x0051  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0056  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static boolean unZipChildFile(File file, List<File> list, ZipFile zipFile, ZipEntry zipEntry, String str) throws IOException {
        BufferedOutputStream bufferedOutputStream;
        BufferedInputStream bufferedInputStream;
        Throwable th;
        File file2 = new File(file, str);
        list.add(file2);
        if (zipEntry.isDirectory()) {
            return createOrExistsDir(file2);
        }
        if (!createOrExistsFile(file2)) {
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
                if (read != -1) {
                    bufferedOutputStream.write(bArr, 0, read);
                } else {
                    bufferedInputStream.close();
                    bufferedOutputStream.close();
                    return true;
                }
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

    public static List<File> unZipFile(String str, String str2) throws IOException {
        return unZipFileByKeyword(str, str2, (String) null);
    }

    public static List<File> unZipFileByKeyword(String str, String str2, String str3) throws IOException {
        return unZipFileByKeyword(getFileByPath(str), getFileByPath(str2), str3);
    }

    public static void unZipFileStream(InputStream inputStream, String str) throws IOException {
        ZipInputStream zipInputStream = new ZipInputStream(inputStream);
        while (true) {
            ZipEntry nextEntry = zipInputStream.getNextEntry();
            if (nextEntry != null) {
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
                        UnLog.e(TAG, sb.toString());
                        File file = new File(str + str2 + replace);
                        if (!file.exists()) {
                            UnLog.e(TAG, "Create the file:" + str + str2 + replace);
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
            } else {
                zipInputStream.close();
                return;
            }
        }
    }

    public static void writeBytesToFile(InputStream inputStream, File file) throws IOException {
        FileOutputStream fileOutputStream = null;
        try {
            try {
                byte[] bArr = new byte[2048];
                FileOutputStream fileOutputStream2 = new FileOutputStream(file);
                while (true) {
                    try {
                        int read = inputStream.read(bArr);
                        if (read > -1) {
                            fileOutputStream2.write(bArr, 0, read);
                        } else {
                            fileOutputStream2.close();
                            return;
                        }
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

    public String[] getPathsByZipDir(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        File file = new File(str);
        if (file.exists() && file.isDirectory()) {
            return file.list();
        }
        return null;
    }

    public static boolean createOrExistsDir(File file) {
        return file != null && (!file.exists() ? !file.mkdirs() : !file.isDirectory());
    }

    public static boolean createOrExistsFile(File file) {
        if (file == null) {
            return false;
        }
        if (file.exists()) {
            return file.isFile();
        }
        if (createOrExistsDir(file.getParentFile())) {
            try {
                return file.createNewFile();
            } catch (IOException e2) {
                e2.printStackTrace();
                return false;
            }
        }
        return false;
    }

    public static List<File> unZipFile(File file, File file2) throws IOException {
        return unZipFileByKeyword(file, file2, (String) null);
    }

    public static List<File> unZipFileByKeyword(File file, File file2, String str) throws IOException {
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
                    if (!replace.contains("../") && !unZipChildFile(file2, arrayList, zipFile, nextElement, replace)) {
                        return arrayList;
                    }
                }
            } else {
                while (entries.hasMoreElements()) {
                    ZipEntry nextElement2 = entries.nextElement();
                    String replace2 = nextElement2.getName().replace("\\", "/");
                    if (!replace2.contains("../") && replace2.contains(str) && !unZipChildFile(file2, arrayList, zipFile, nextElement2, replace2)) {
                        return arrayList;
                    }
                }
            }
            return arrayList;
        } finally {
            zipFile.close();
        }
    }

    public static String readTxt(File file) {
        BufferedReader bufferedReader = null;
        if (file == null || !file.exists() || !file.isFile()) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        try {
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        try {
            try {
                BufferedReader bufferedReader2 = new BufferedReader(new FileReader(file));
                while (true) {
                    try {
                        String readLine = bufferedReader2.readLine();
                        if (readLine == null) {
                            break;
                        }
                        sb.append(readLine);
                    } catch (Exception e3) {
                        e = e3;
                        bufferedReader = bufferedReader2;
                        e.printStackTrace();
                        if (bufferedReader != null) {
                            bufferedReader.close();
                        }
                        return sb.toString();
                    } catch (Throwable th) {
                        th = th;
                        bufferedReader = bufferedReader2;
                        if (bufferedReader != null) {
                            try {
                                bufferedReader.close();
                            } catch (IOException e4) {
                                e4.printStackTrace();
                            }
                        }
                        throw th;
                    }
                }
                bufferedReader2.close();
            } catch (Exception e5) {
                e = e5;
            }
            return sb.toString();
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static boolean dirIsExists(File file) {
        return file != null && file.exists() && file.isDirectory();
    }
}
