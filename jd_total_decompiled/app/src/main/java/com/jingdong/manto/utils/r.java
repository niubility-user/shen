package com.jingdong.manto.utils;

import android.text.TextUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/* loaded from: classes16.dex */
public final class r {
    public static void a(InputStream inputStream) {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (Throwable unused) {
            }
        }
    }

    public static void a(OutputStream outputStream) {
        if (outputStream != null) {
            try {
                outputStream.close();
            } catch (Throwable unused) {
            }
        }
    }

    public static boolean a(String str) {
        return str != null && new File(str).exists();
    }

    public static boolean a(String str, String str2) {
        Throwable th;
        FileInputStream fileInputStream;
        FileOutputStream fileOutputStream;
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            if (str.equals(str2)) {
                return true;
            }
            FileOutputStream fileOutputStream2 = null;
            try {
                fileInputStream = new FileInputStream(str);
                try {
                    File file = new File(str2);
                    if (!file.exists()) {
                        file.getParentFile().mkdirs();
                    }
                    fileOutputStream = new FileOutputStream(str2);
                } catch (Exception unused) {
                } catch (Throwable th2) {
                    th = th2;
                }
                try {
                    byte[] bArr = new byte[16384];
                    while (true) {
                        int read = fileInputStream.read(bArr);
                        if (read == -1) {
                            a(fileInputStream);
                            a(fileOutputStream);
                            return true;
                        }
                        fileOutputStream.write(bArr, 0, read);
                    }
                } catch (Exception unused2) {
                    fileOutputStream2 = fileOutputStream;
                    a(fileInputStream);
                    a(fileOutputStream2);
                    return false;
                } catch (Throwable th3) {
                    th = th3;
                    fileOutputStream2 = fileOutputStream;
                    a(fileInputStream);
                    a(fileOutputStream2);
                    throw th;
                }
            } catch (Exception unused3) {
                fileInputStream = null;
            } catch (Throwable th4) {
                th = th4;
                fileInputStream = null;
            }
        }
        return false;
    }

    public static boolean a(String str, String str2, boolean z) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        File file = new File(str);
        if (file.exists()) {
            File file2 = new File(str2);
            if (file.isFile()) {
                if (file2.isFile() || !file2.exists()) {
                    a(str, str2);
                    if (z) {
                        file.delete();
                        return true;
                    }
                    return true;
                }
                return false;
            } else if (file.isDirectory()) {
                if (!file2.exists()) {
                    file2.mkdir();
                }
                if (file2.isDirectory()) {
                    String[] list = file.list();
                    for (int i2 = 0; i2 < list.length; i2++) {
                        a(str + "/" + list[i2], str2 + "/" + list[i2], z);
                    }
                    return true;
                }
                return false;
            } else {
                return true;
            }
        }
        return false;
    }
}
