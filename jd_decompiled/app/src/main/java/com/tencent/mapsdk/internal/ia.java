package com.tencent.mapsdk.internal;

import android.text.TextUtils;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.InflaterInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/* loaded from: classes9.dex */
public class ia {
    private static final int a = 49152;

    public static void a(File file, String str) {
        a(file, str, (FilenameFilter) null);
    }

    public static void a(File file, String str, FilenameFilter filenameFilter) {
        File file2 = new File(str);
        if (!file2.exists()) {
            file2.mkdirs();
        }
        ZipFile zipFile = new ZipFile(file);
        byte[] bArr = new byte[a];
        Enumeration<? extends ZipEntry> entries = zipFile.entries();
        while (entries.hasMoreElements()) {
            ZipEntry nextElement = entries.nextElement();
            String name = nextElement.getName();
            if (name == null || (!name.contains("../") && !name.contains(".."))) {
                if (filenameFilter == null || filenameFilter.accept(file2, nextElement.getName())) {
                    if (nextElement.isDirectory()) {
                        new File(str + File.separator + nextElement.getName()).mkdir();
                    } else {
                        InputStream inputStream = zipFile.getInputStream(nextElement);
                        File file3 = new File(str + File.separator + nextElement.getName());
                        if (!file3.exists()) {
                            File parentFile = file3.getParentFile();
                            if (!parentFile.exists()) {
                                parentFile.mkdirs();
                            }
                            file3.createNewFile();
                        }
                        FileOutputStream fileOutputStream = new FileOutputStream(file3);
                        while (true) {
                            int read = inputStream.read(bArr);
                            if (read <= 0) {
                                break;
                            }
                            fileOutputStream.write(bArr, 0, read);
                        }
                        fileOutputStream.flush();
                        inputStream.close();
                        fileOutputStream.close();
                    }
                }
            }
        }
        zipFile.close();
    }

    private static void a(File file, ZipOutputStream zipOutputStream, String str) {
        String str2;
        if (file == null || !file.exists()) {
            return;
        }
        if (file.isDirectory()) {
            File[] listFiles = file.listFiles();
            if (listFiles != null) {
                for (File file2 : listFiles) {
                    if (!file2.isDirectory()) {
                        str2 = str;
                    } else if (TextUtils.isEmpty(str)) {
                        str2 = file2.getName();
                    } else {
                        str2 = str + File.separator + file2.getName();
                    }
                    a(file2, zipOutputStream, str2);
                }
                return;
            }
            return;
        }
        byte[] bArr = new byte[a];
        FileInputStream fileInputStream = new FileInputStream(file);
        zipOutputStream.setLevel(9);
        zipOutputStream.setMethod(8);
        zipOutputStream.putNextEntry(new ZipEntry(TextUtils.isEmpty(str) ? file.getName() : str + File.separator + file.getName()));
        while (true) {
            int read = fileInputStream.read(bArr);
            if (read == -1) {
                zipOutputStream.closeEntry();
                fileInputStream.close();
                return;
            }
            zipOutputStream.write(bArr, 0, read);
        }
    }

    public static void a(InputStream inputStream, File file) {
        if (!file.exists()) {
            file.mkdirs();
        }
        ZipInputStream zipInputStream = new ZipInputStream(inputStream);
        byte[] bArr = new byte[a];
        while (true) {
            ZipEntry nextEntry = zipInputStream.getNextEntry();
            while (nextEntry != null) {
                String name = nextEntry.getName();
                if (name == null || (!name.contains("../") && !name.contains(".."))) {
                    if (nextEntry.isDirectory()) {
                        new File(file + File.separator + nextEntry.getName().substring(0, r2.length() - 1)).mkdir();
                    } else {
                        File file2 = new File(file, File.separator + nextEntry.getName());
                        if (!file2.exists()) {
                            File parentFile = file2.getParentFile();
                            if (!parentFile.exists()) {
                                parentFile.mkdirs();
                            }
                            file2.createNewFile();
                        }
                        FileOutputStream fileOutputStream = new FileOutputStream(file2);
                        while (true) {
                            int read = zipInputStream.read(bArr);
                            if (read <= 0) {
                                break;
                            }
                            fileOutputStream.write(bArr, 0, read);
                        }
                        fileOutputStream.flush();
                        fileOutputStream.close();
                    }
                }
            }
            inputStream.close();
            zipInputStream.close();
            return;
        }
    }

    public static boolean a(File[] fileArr, File file, String str) {
        boolean z = false;
        if (fileArr != null && fileArr.length > 0) {
            File a2 = fa.a(file, str);
            for (File file2 : fileArr) {
                try {
                    fa.a(file2, a2);
                } catch (Throwable th) {
                    fa.e(a2);
                    throw th;
                }
            }
            try {
                c(a2, file.getAbsolutePath());
                fa.e(a2);
                z = true;
            } catch (Exception e2) {
                e2.printStackTrace();
                fa.e(a2);
            }
        }
        return z;
    }

    public static byte[] a(InputStream inputStream) {
        GZIPInputStream gZIPInputStream = null;
        if (inputStream != null) {
            try {
                try {
                    GZIPInputStream gZIPInputStream2 = new GZIPInputStream(inputStream);
                    try {
                        byte[] b = ga.b(gZIPInputStream2);
                        ga.a((Closeable) gZIPInputStream2);
                        return b;
                    } catch (IOException e2) {
                        e = e2;
                        gZIPInputStream = gZIPInputStream2;
                        e.printStackTrace();
                        ga.a((Closeable) gZIPInputStream);
                        return new byte[0];
                    } catch (Throwable th) {
                        th = th;
                        gZIPInputStream = gZIPInputStream2;
                        ga.a((Closeable) gZIPInputStream);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (IOException e3) {
                e = e3;
            }
        }
        ga.a((Closeable) gZIPInputStream);
        return new byte[0];
    }

    public static byte[] a(byte[] bArr) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DeflaterOutputStream deflaterOutputStream = new DeflaterOutputStream(byteArrayOutputStream);
        try {
            deflaterOutputStream.write(bArr, 0, bArr.length);
            deflaterOutputStream.finish();
            deflaterOutputStream.flush();
            deflaterOutputStream.close();
            return byteArrayOutputStream.toByteArray();
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static byte[] a(byte[] bArr, int i2, int i3) {
        int read;
        InflaterInputStream inflaterInputStream = new InflaterInputStream(new ByteArrayInputStream(bArr, i2, i3));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr2 = new byte[1024];
        do {
            try {
                try {
                    read = inflaterInputStream.read(bArr2);
                    if (read > 0) {
                        byteArrayOutputStream.write(bArr2, 0, read);
                    }
                } catch (IOException unused) {
                    return null;
                }
            } catch (IOException unused2) {
                inflaterInputStream.close();
                return null;
            }
        } while (read > 0);
        inflaterInputStream.close();
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        try {
            byteArrayOutputStream.close();
        } catch (IOException unused3) {
        }
        return byteArray;
    }

    public static void b(File file, String str) {
        try {
            a(new FileInputStream(file), new File(str));
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static byte[] b(byte[] bArr) {
        return a(bArr, 0, bArr.length);
    }

    public static File c(File file, String str) {
        if (file == null || !file.exists()) {
            return null;
        }
        File file2 = new File(str, file.getName() + ".zip");
        FileOutputStream fileOutputStream = new FileOutputStream(file2);
        ZipOutputStream zipOutputStream = new ZipOutputStream(fileOutputStream);
        try {
            if (file.isDirectory()) {
                File[] listFiles = file.listFiles();
                if (listFiles != null) {
                    for (File file3 : listFiles) {
                        a(file3, zipOutputStream, file.getName());
                    }
                }
            } else {
                a(file, zipOutputStream, (String) null);
            }
            fileOutputStream.flush();
            zipOutputStream.finish();
            return file2;
        } finally {
            fileOutputStream.close();
            zipOutputStream.close();
        }
    }

    public static byte[] c(byte[] bArr) {
        return a(new ByteArrayInputStream(bArr));
    }
}
