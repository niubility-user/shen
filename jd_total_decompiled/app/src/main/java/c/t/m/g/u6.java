package c.t.m.g;

import android.content.Context;
import android.os.Environment;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/* loaded from: classes.dex */
public class u6 {
    public static File a;

    public static File a(Context context, String str) {
        File file;
        try {
            file = a;
        } catch (Exception unused) {
        }
        if (file != null) {
            return file;
        }
        if ("mounted".equals(Environment.getExternalStorageState())) {
            File externalFilesDir = context.getExternalFilesDir(str);
            a = externalFilesDir;
            return externalFilesDir;
        }
        File filesDir = context.getApplicationContext().getFilesDir();
        a = filesDir;
        return filesDir;
    }

    @Deprecated
    public static void b(Closeable closeable) {
        j0.a(closeable);
    }

    public static void c(String str, long j2) {
        if (t2.c(str)) {
            return;
        }
        long j3 = 0;
        File file = null;
        try {
            for (File file2 : new File(str).listFiles()) {
                if (file == null || file.lastModified() > file2.lastModified()) {
                    file = file2;
                }
                j3 += file2.length();
            }
            if (j3 < j2 || file == null) {
                return;
            }
            if (z0.e()) {
                StringBuilder sb = new StringBuilder("delete file ");
                sb.append(file.getName());
                sb.append(":");
                sb.append(file.length());
                sb.append(",sumS:");
                sb.append(j3);
                sb.append(",maxS:");
                sb.append(j2);
            }
            file.delete();
        } catch (Throwable unused) {
        }
    }

    public static boolean d(File file) {
        if (file == null || !file.exists()) {
            return false;
        }
        if (file.isDirectory()) {
            for (File file2 : file.listFiles()) {
                if (!d(file2)) {
                    return false;
                }
            }
        }
        return file.delete();
    }

    public static boolean e(File file, byte[] bArr, boolean z) {
        FileOutputStream fileOutputStream;
        try {
            fileOutputStream = new FileOutputStream(file, z);
            try {
                fileOutputStream.write(bArr);
                j0.a(fileOutputStream);
                return true;
            } catch (Throwable unused) {
                j0.a(fileOutputStream);
                return false;
            }
        } catch (Throwable unused2) {
            fileOutputStream = null;
        }
    }

    public static byte[] f(File file) {
        BufferedInputStream bufferedInputStream;
        byte[] bArr;
        if (!file.exists() || file.length() == 0) {
            return e2.a;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] c2 = y0.a().c(2048);
        try {
            bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
            while (true) {
                try {
                    int read = bufferedInputStream.read(c2);
                    if (read == -1) {
                        break;
                    }
                    byteArrayOutputStream.write(c2, 0, read);
                } catch (Throwable unused) {
                    try {
                        bArr = e2.a;
                        return bArr;
                    } finally {
                        y0.a().b(c2);
                        j0.a(bufferedInputStream);
                        j0.a(byteArrayOutputStream);
                    }
                }
            }
            bArr = byteArrayOutputStream.toByteArray();
        } catch (Throwable unused2) {
            bufferedInputStream = null;
        }
        return bArr;
    }
}
