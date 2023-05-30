package com.jdjr.risk.util.a;

import android.os.Environment;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;

/* loaded from: classes18.dex */
public class b {
    public static final String a = Environment.getExternalStorageDirectory().getAbsolutePath() + "/";

    public static String a(File file) {
        BufferedInputStream bufferedInputStream;
        Throwable th;
        ByteArrayOutputStream byteArrayOutputStream;
        byte[] byteArray;
        BufferedInputStream bufferedInputStream2 = null;
        if (file == null || !file.exists()) {
            return null;
        }
        try {
            bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
            try {
                byteArrayOutputStream = new ByteArrayOutputStream(1024);
            } catch (IOException unused) {
                byteArrayOutputStream = null;
            } catch (Throwable th2) {
                th = th2;
                byteArrayOutputStream = null;
            }
            try {
                byte[] bArr = new byte[1024];
                while (true) {
                    int read = bufferedInputStream.read(bArr);
                    if (read == -1) {
                        break;
                    }
                    byteArrayOutputStream.write(bArr, 0, read);
                }
                byteArray = byteArrayOutputStream.toByteArray();
            } catch (IOException unused2) {
                bufferedInputStream2 = bufferedInputStream;
                a(bufferedInputStream2);
                a(byteArrayOutputStream);
                return "";
            } catch (Throwable th3) {
                th = th3;
                a(bufferedInputStream);
                a(byteArrayOutputStream);
                throw th;
            }
        } catch (IOException unused3) {
            byteArrayOutputStream = null;
        } catch (Throwable th4) {
            bufferedInputStream = null;
            th = th4;
            byteArrayOutputStream = null;
        }
        if (byteArray == null) {
            a(bufferedInputStream);
            a(byteArrayOutputStream);
            return "";
        }
        String str = new String(byteArray, Charset.forName("UTF-8"));
        a(bufferedInputStream);
        a(byteArrayOutputStream);
        return str;
    }

    public static void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
            }
        }
    }
}
