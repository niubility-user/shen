package com.jd.aips.tracker.util;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/* loaded from: classes12.dex */
public class UemsPersistentUtil {
    public static boolean a(String str) {
        File file = new File(str);
        if (file.exists()) {
            try {
                return file.delete();
            } catch (Exception unused) {
                return false;
            }
        }
        return true;
    }

    public static byte[] b(String str) {
        BufferedInputStream bufferedInputStream;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        BufferedInputStream bufferedInputStream2 = null;
        try {
            bufferedInputStream = new BufferedInputStream(new FileInputStream(new File(str)));
        } catch (Exception e2) {
            e = e2;
            bufferedInputStream = null;
        } catch (Throwable th) {
            th = th;
            try {
                byteArrayOutputStream.close();
                bufferedInputStream2.close();
            } catch (IOException e3) {
                e3.printStackTrace();
            }
            throw th;
        }
        try {
            try {
                byte[] bArr = new byte[8192];
                while (true) {
                    int read = bufferedInputStream.read(bArr);
                    if (read == -1) {
                        break;
                    }
                    byteArrayOutputStream.write(bArr, 0, read);
                    byteArrayOutputStream.flush();
                }
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                try {
                    byteArrayOutputStream.close();
                    bufferedInputStream.close();
                } catch (IOException e4) {
                    e4.printStackTrace();
                }
                return byteArray;
            } catch (Throwable th2) {
                th = th2;
                bufferedInputStream2 = bufferedInputStream;
                byteArrayOutputStream.close();
                bufferedInputStream2.close();
                throw th;
            }
        } catch (Exception e5) {
            e = e5;
            e.printStackTrace();
            try {
                byteArrayOutputStream.close();
                bufferedInputStream.close();
            } catch (IOException e6) {
                e6.printStackTrace();
            }
            return null;
        }
    }
}
