package com.jd.lib.un.utils;

import android.text.TextUtils;
import androidx.annotation.NonNull;
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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static boolean unZipChildFile(java.io.File r1, java.util.List<java.io.File> r2, java.util.zip.ZipFile r3, java.util.zip.ZipEntry r4, java.lang.String r5) throws java.io.IOException {
        /*
            java.io.File r0 = new java.io.File
            r0.<init>(r1, r5)
            r2.add(r0)
            boolean r1 = r4.isDirectory()
            if (r1 == 0) goto L13
            boolean r1 = createOrExistsDir(r0)
            return r1
        L13:
            boolean r1 = createOrExistsFile(r0)
            r2 = 0
            if (r1 != 0) goto L1b
            return r2
        L1b:
            r1 = 0
            java.io.BufferedInputStream r5 = new java.io.BufferedInputStream     // Catch: java.lang.Throwable -> L4b
            java.io.InputStream r3 = r3.getInputStream(r4)     // Catch: java.lang.Throwable -> L4b
            r5.<init>(r3)     // Catch: java.lang.Throwable -> L4b
            java.io.BufferedOutputStream r3 = new java.io.BufferedOutputStream     // Catch: java.lang.Throwable -> L48
            java.io.FileOutputStream r4 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L48
            r4.<init>(r0)     // Catch: java.lang.Throwable -> L48
            r3.<init>(r4)     // Catch: java.lang.Throwable -> L48
            r1 = 8192(0x2000, float:1.14794E-41)
            byte[] r1 = new byte[r1]     // Catch: java.lang.Throwable -> L46
        L33:
            int r4 = r5.read(r1)     // Catch: java.lang.Throwable -> L46
            r0 = -1
            if (r4 == r0) goto L3e
            r3.write(r1, r2, r4)     // Catch: java.lang.Throwable -> L46
            goto L33
        L3e:
            r5.close()
            r3.close()
            r1 = 1
            return r1
        L46:
            r1 = move-exception
            goto L4f
        L48:
            r2 = move-exception
            r3 = r1
            goto L4e
        L4b:
            r2 = move-exception
            r3 = r1
            r5 = r3
        L4e:
            r1 = r2
        L4f:
            if (r5 == 0) goto L54
            r5.close()
        L54:
            if (r3 == 0) goto L59
            r3.close()
        L59:
            goto L5b
        L5a:
            throw r1
        L5b:
            goto L5a
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.lib.un.utils.UnZipUtils.unZipChildFile(java.io.File, java.util.List, java.util.zip.ZipFile, java.util.zip.ZipEntry, java.lang.String):boolean");
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
