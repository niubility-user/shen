package com.jingdong.common.jdreactFramework.utils;

import android.text.TextUtils;
import com.google.zxing.common.StringUtils;
import com.jingdong.common.jdreactFramework.JDReactHelper;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.zip.CRC32;
import java.util.zip.CheckedInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/* loaded from: classes5.dex */
public class ZipUtils {
    private static final String BASE_DIR = "";
    private static final int BUFFER = 1024;
    public static final String EXT = ".zip";
    private static final String PATH = File.separator;
    private static final String TAG = "HybridZipUtil";

    /* JADX WARN: Removed duplicated region for block: B:268:0x00c6 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:276:0x00bc A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static boolean copyFile(File file, File file2) {
        BufferedOutputStream bufferedOutputStream;
        BufferedInputStream bufferedInputStream;
        if (!file.exists()) {
            JLog.e("cppyFile", "file not exist:" + file.getAbsolutePath());
            return false;
        } else if (!file2.getParentFile().isDirectory() && !file2.getParentFile().mkdirs()) {
            JLog.e("cppyFile", "mkdir failed:" + file2.getParent());
            return false;
        } else {
            BufferedInputStream bufferedInputStream2 = null;
            try {
                bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
                try {
                    bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file2));
                } catch (Exception e2) {
                    e = e2;
                    bufferedOutputStream = null;
                } catch (Throwable th) {
                    th = th;
                    bufferedOutputStream = null;
                }
            } catch (Exception e3) {
                e = e3;
                bufferedOutputStream = null;
            } catch (Throwable th2) {
                th = th2;
                bufferedOutputStream = null;
            }
            try {
                byte[] bArr = new byte[4096];
                while (true) {
                    int read = bufferedInputStream.read(bArr, 0, 4096);
                    if (read == -1) {
                        break;
                    } else if (read > 0) {
                        bufferedOutputStream.write(bArr, 0, read);
                    }
                }
                bufferedOutputStream.flush();
                try {
                    bufferedInputStream.close();
                } catch (IOException e4) {
                    e4.printStackTrace();
                }
                try {
                    bufferedOutputStream.close();
                } catch (IOException e5) {
                    e5.printStackTrace();
                }
                return true;
            } catch (Exception e6) {
                e = e6;
                bufferedInputStream2 = bufferedInputStream;
                try {
                    JLog.e("copyFile", e.getMessage());
                    if (bufferedInputStream2 != null) {
                        try {
                            bufferedInputStream2.close();
                        } catch (IOException e7) {
                            e7.printStackTrace();
                        }
                    }
                    if (bufferedOutputStream != null) {
                        try {
                            bufferedOutputStream.close();
                        } catch (IOException e8) {
                            e8.printStackTrace();
                        }
                    }
                    return false;
                } catch (Throwable th3) {
                    th = th3;
                    if (bufferedInputStream2 != null) {
                        try {
                            bufferedInputStream2.close();
                        } catch (IOException e9) {
                            e9.printStackTrace();
                        }
                    }
                    if (bufferedOutputStream != null) {
                        try {
                            bufferedOutputStream.close();
                        } catch (IOException e10) {
                            e10.printStackTrace();
                        }
                    }
                    throw th;
                }
            } catch (Throwable th4) {
                th = th4;
                bufferedInputStream2 = bufferedInputStream;
                if (bufferedInputStream2 != null) {
                }
                if (bufferedOutputStream != null) {
                }
                throw th;
            }
        }
    }

    public static boolean copyFileDirect(File file, File file2) {
        if (file.isDirectory()) {
            if (file2.isDirectory() || file2.mkdirs()) {
                for (File file3 : file.listFiles()) {
                    File file4 = new File(file2, file3.getName());
                    if (file3.isFile()) {
                        if (!copyFile(file3, file4)) {
                            return false;
                        }
                    } else if (file3.isDirectory() && !copyFileDirect(file3, file4)) {
                        return false;
                    }
                }
                return true;
            }
            return false;
        }
        return false;
    }

    public static boolean copyJDFlutterFileDirect(File file, File file2) {
        if (file.isDirectory()) {
            if (file2.isDirectory() || file2.mkdirs()) {
                for (File file3 : file.listFiles()) {
                    File file4 = new File(file2, file3.getName());
                    if (file3.isFile()) {
                        if (!copyFile(file3, new File(file2, file3.getName() + "_new"))) {
                            return false;
                        }
                    } else if (file3.isDirectory() && !copyFileDirect(file3, file4)) {
                        return false;
                    }
                }
                return true;
            }
            return false;
        }
        return false;
    }

    private static void decompress(String str) throws Exception {
        decompress(new File(str));
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:147:0x0041 A[Catch: Exception -> 0x003d, TRY_LEAVE, TryCatch #0 {Exception -> 0x003d, blocks: (B:143:0x0039, B:147:0x0041), top: B:153:0x0039 }] */
    /* JADX WARN: Removed duplicated region for block: B:153:0x0039 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static void decompressFile(File file, ZipInputStream zipInputStream) throws Exception {
        FileOutputStream fileOutputStream;
        BufferedOutputStream bufferedOutputStream;
        BufferedOutputStream bufferedOutputStream2 = null;
        try {
            fileOutputStream = new FileOutputStream(file);
        } catch (Exception e2) {
            e = e2;
            bufferedOutputStream = null;
        } catch (Throwable th) {
            th = th;
            fileOutputStream = 0;
        }
        try {
            bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
        } catch (Exception e3) {
            e = e3;
            bufferedOutputStream = null;
        } catch (Throwable th2) {
            th = th2;
            if (bufferedOutputStream2 != null) {
            }
            if (fileOutputStream != 0) {
            }
            throw th;
        }
        try {
            byte[] bArr = new byte[1024];
            while (true) {
                int read = zipInputStream.read(bArr, 0, 1024);
                if (read != -1) {
                    bufferedOutputStream.write(bArr, 0, read);
                } else {
                    try {
                        bufferedOutputStream.close();
                        fileOutputStream.close();
                        return;
                    } catch (Exception e4) {
                        throw e4;
                    }
                }
            }
        } catch (Exception e5) {
            e = e5;
            bufferedOutputStream2 = fileOutputStream;
            try {
                throw e;
            } catch (Throwable th3) {
                th = th3;
                fileOutputStream = bufferedOutputStream2;
                bufferedOutputStream2 = bufferedOutputStream;
                if (bufferedOutputStream2 != null) {
                    try {
                        bufferedOutputStream2.close();
                    } catch (Exception e6) {
                        throw e6;
                    }
                }
                if (fileOutputStream != 0) {
                    fileOutputStream.close();
                }
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            bufferedOutputStream2 = bufferedOutputStream;
            if (bufferedOutputStream2 != null) {
            }
            if (fileOutputStream != 0) {
            }
            throw th;
        }
    }

    private static void fileProber(File file) {
        File parentFile = file.getParentFile();
        if (parentFile.exists()) {
            return;
        }
        fileProber(parentFile);
        parentFile.mkdir();
    }

    /* JADX WARN: Removed duplicated region for block: B:107:0x0093  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static File getRealFileName(String str, String str2) {
        String str3;
        String[] split = str2.split("/");
        File file = new File(str);
        if (split.length > 1) {
            int i2 = 0;
            while (i2 < split.length - 1) {
                String str4 = split[i2];
                try {
                    str4 = new String(str4.getBytes("8859_1"), StringUtils.GB2312);
                } catch (UnsupportedEncodingException unused) {
                }
                i2++;
                file = new File(file, str4);
            }
            if (JDReactHelper.newInstance().isDebug()) {
                JLog.d("upZipFile", "1ret = " + file);
            }
            if (!file.exists()) {
                file.mkdirs();
            }
            String str5 = split[split.length - 1];
            try {
                str3 = new String(str5.getBytes("8859_1"), StringUtils.GB2312);
                try {
                    if (JDReactHelper.newInstance().isDebug()) {
                        JLog.d("upZipFile", "substr = " + str3);
                    }
                } catch (UnsupportedEncodingException unused2) {
                    str5 = str3;
                    str3 = str5;
                    File file2 = new File(file, str3);
                    if (JDReactHelper.newInstance().isDebug()) {
                    }
                    return file2;
                }
            } catch (UnsupportedEncodingException unused3) {
            }
            File file22 = new File(file, str3);
            if (JDReactHelper.newInstance().isDebug()) {
                JLog.d("upZipFile", "2ret = " + file22);
            }
            return file22;
        }
        return file;
    }

    public static void renameFile(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        new File(str).renameTo(new File(str2));
    }

    private static void decompress(File file) throws Exception {
        decompress(file, file.getParent());
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:151:0x004a A[Catch: Exception -> 0x0046, TryCatch #6 {Exception -> 0x0046, blocks: (B:147:0x0042, B:151:0x004a, B:153:0x004f), top: B:157:0x0042 }] */
    /* JADX WARN: Removed duplicated region for block: B:153:0x004f A[Catch: Exception -> 0x0046, TRY_LEAVE, TryCatch #6 {Exception -> 0x0046, blocks: (B:147:0x0042, B:151:0x004a, B:153:0x004f), top: B:157:0x0042 }] */
    /* JADX WARN: Removed duplicated region for block: B:157:0x0042 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static void decompress(File file, File file2) throws Exception {
        CheckedInputStream checkedInputStream;
        FileInputStream fileInputStream;
        ZipInputStream zipInputStream;
        ZipInputStream zipInputStream2 = null;
        try {
            fileInputStream = new FileInputStream(file);
            try {
                checkedInputStream = new CheckedInputStream(fileInputStream, new CRC32());
                try {
                    zipInputStream = new ZipInputStream(checkedInputStream);
                } catch (Exception e2) {
                    e = e2;
                    zipInputStream = null;
                } catch (Throwable th) {
                    th = th;
                    if (zipInputStream2 != null) {
                    }
                    if (checkedInputStream != null) {
                    }
                    if (fileInputStream != 0) {
                    }
                    throw th;
                }
                try {
                    decompress(file2, zipInputStream);
                    try {
                        zipInputStream.close();
                        checkedInputStream.close();
                        fileInputStream.close();
                    } catch (Exception e3) {
                        throw e3;
                    }
                } catch (Exception e4) {
                    e = e4;
                    zipInputStream2 = fileInputStream;
                    try {
                        throw e;
                    } catch (Throwable th2) {
                        th = th2;
                        fileInputStream = zipInputStream2;
                        zipInputStream2 = zipInputStream;
                        if (zipInputStream2 != null) {
                            try {
                                zipInputStream2.close();
                            } catch (Exception e5) {
                                throw e5;
                            }
                        }
                        if (checkedInputStream != null) {
                            checkedInputStream.close();
                        }
                        if (fileInputStream != 0) {
                            fileInputStream.close();
                        }
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    zipInputStream2 = zipInputStream;
                    if (zipInputStream2 != null) {
                    }
                    if (checkedInputStream != null) {
                    }
                    if (fileInputStream != 0) {
                    }
                    throw th;
                }
            } catch (Exception e6) {
                e = e6;
                checkedInputStream = null;
                zipInputStream = null;
            } catch (Throwable th4) {
                th = th4;
                checkedInputStream = null;
            }
        } catch (Exception e7) {
            e = e7;
            checkedInputStream = null;
            zipInputStream = null;
        } catch (Throwable th5) {
            th = th5;
            checkedInputStream = null;
            fileInputStream = 0;
        }
    }

    public static void decompress(File file, String str) throws Exception {
        if (str == null || str.contains("../")) {
            return;
        }
        decompress(file, new File(str));
    }

    private static void decompress(String str, String str2) throws Exception {
        decompress(new File(str), str2);
    }

    private static void decompress(File file, ZipInputStream zipInputStream) throws Exception {
        while (true) {
            ZipEntry nextEntry = zipInputStream.getNextEntry();
            if (nextEntry == null) {
                return;
            }
            String name = nextEntry.getName();
            if (name.contains("../")) {
                return;
            }
            File file2 = new File(file.getPath() + File.separator + name);
            fileProber(file2);
            if (nextEntry.isDirectory()) {
                file2.mkdirs();
            } else {
                decompressFile(file2, zipInputStream);
            }
            zipInputStream.closeEntry();
        }
    }
}
