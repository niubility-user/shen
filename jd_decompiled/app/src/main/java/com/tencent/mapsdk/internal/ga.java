package com.tencent.mapsdk.internal;

import android.graphics.Bitmap;
import android.text.TextUtils;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Stack;

/* loaded from: classes9.dex */
public class ga {
    private static final int a = 4096;

    public static int a(byte[] bArr, File file, boolean z) {
        if (bArr != null && bArr.length != 0 && file != null && !file.isDirectory()) {
            FileOutputStream fileOutputStream = null;
            try {
                if (!file.exists()) {
                    file.createNewFile();
                }
                FileOutputStream fileOutputStream2 = new FileOutputStream(file, z);
                try {
                    fileOutputStream2.write(bArr);
                    fileOutputStream2.flush();
                    int length = bArr.length;
                    a(fileOutputStream2);
                    return length;
                } catch (IOException unused) {
                    fileOutputStream = fileOutputStream2;
                    a(fileOutputStream);
                    return 0;
                } catch (Throwable th) {
                    th = th;
                    fileOutputStream = fileOutputStream2;
                    a(fileOutputStream);
                    throw th;
                }
            } catch (IOException unused2) {
            } catch (Throwable th2) {
                th = th2;
            }
        }
        return 0;
    }

    public static int a(byte[] bArr, String str, boolean z) {
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        return a(bArr, new File(str), z);
    }

    public static final long a(InputStream inputStream, OutputStream outputStream) {
        if (inputStream != null && outputStream != null) {
            try {
                byte[] bArr = new byte[4096];
                long j2 = 0;
                while (true) {
                    int read = inputStream.read(bArr);
                    if (read <= 0) {
                        return j2;
                    }
                    outputStream.write(bArr, 0, read);
                    j2 += read;
                }
            } catch (IOException unused) {
            }
        }
        return -1L;
    }

    public static final InputStream a(File file) {
        if (file == null) {
            return null;
        }
        try {
            if (file.exists() && file.isFile() && file.canRead()) {
                return new FileInputStream(file);
            }
        } catch (IOException unused) {
        }
        return null;
    }

    public static final void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
            }
        }
    }

    public static void a(byte[] bArr, OutputStream outputStream) {
        if (bArr == null || bArr.length == 0 || outputStream == null) {
            return;
        }
        try {
            outputStream.write(bArr);
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    public static void a(Bitmap... bitmapArr) {
        if (bitmapArr != null) {
            for (Bitmap bitmap : bitmapArr) {
                if (bitmap != null && !bitmap.isRecycled()) {
                    bitmap.recycle();
                }
            }
        }
    }

    public static boolean a(InputStream inputStream) {
        if (inputStream != null) {
            try {
                return inputStream.available() > 0;
            } catch (IOException e2) {
                e2.printStackTrace();
                return false;
            }
        }
        return false;
    }

    public static boolean a(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        Stack stack = new Stack();
        stack.push(str);
        while (!stack.isEmpty()) {
            File file = new File((String) stack.peek());
            if (file.exists()) {
                if (file.isDirectory()) {
                    File[] listFiles = file.listFiles();
                    if (listFiles != null) {
                        if (listFiles.length != 0) {
                            for (File file2 : listFiles) {
                                if (file2.isDirectory()) {
                                    stack.push(file2.getAbsolutePath());
                                } else {
                                    file2.delete();
                                }
                            }
                        }
                    }
                }
                file.delete();
            }
            stack.pop();
        }
        return true;
    }

    public static boolean a(String str, String str2) {
        File file = new File(str);
        if (file.exists()) {
            return file.renameTo(new File(str2));
        }
        return false;
    }

    public static byte[] a(InputStream inputStream, int i2) {
        ByteArrayOutputStream byteArrayOutputStream;
        if (inputStream == null) {
            return null;
        }
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
        } catch (Throwable th) {
            th = th;
            byteArrayOutputStream = null;
        }
        try {
            byte[] bArr = new byte[i2];
            do {
                int read = inputStream.read(bArr, 0, i2);
                if (read == -1) {
                    break;
                }
                byteArrayOutputStream.write(bArr, 0, read);
            } while (byteArrayOutputStream.size() < i2);
            byteArrayOutputStream.flush();
            return byteArrayOutputStream.toByteArray();
        } catch (Throwable th2) {
            th = th2;
            try {
                th.printStackTrace();
                return null;
            } finally {
                a(byteArrayOutputStream);
            }
        }
    }

    public static boolean b(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            File file = new File(str);
            if (file.exists() && file.isDirectory()) {
                return true;
            }
            if (file.exists() && file.isFile()) {
                file.delete();
            }
            return file.mkdirs();
        } catch (Exception unused) {
            return false;
        }
    }

    public static byte[] b(InputStream inputStream) {
        ByteArrayOutputStream byteArrayOutputStream;
        if (inputStream == null) {
            return null;
        }
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
        } catch (Throwable th) {
            th = th;
            byteArrayOutputStream = null;
        }
        try {
            byte[] bArr = new byte[4096];
            while (true) {
                int read = inputStream.read(bArr, 0, 4096);
                if (read == -1) {
                    byteArrayOutputStream.flush();
                    return byteArrayOutputStream.toByteArray();
                }
                byteArrayOutputStream.write(bArr, 0, read);
            }
        } catch (Throwable th2) {
            th = th2;
            try {
                th.printStackTrace();
                return null;
            } finally {
                a(byteArrayOutputStream);
            }
        }
    }

    public static final InputStream c(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            return a(new File(str));
        } catch (Exception unused) {
            return null;
        }
    }
}
