package com.jdjr.risk.device.c;

import android.content.Context;
import android.text.TextUtils;
import java.io.File;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.util.UUID;

/* loaded from: classes18.dex */
public class v {
    private static String a = "";

    public static synchronized String a(Context context) {
        String str;
        synchronized (v.class) {
            if (TextUtils.isEmpty(a)) {
                File file = new File(context.getFilesDir(), "MYINSTALLATION");
                if (!file.exists()) {
                    b(file);
                }
                a = a(file);
            }
            str = a;
        }
        return str;
    }

    private static String a(File file) {
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
        byte[] bArr = new byte[(int) randomAccessFile.length()];
        randomAccessFile.readFully(bArr);
        randomAccessFile.close();
        return new String(bArr);
    }

    private static void b(File file) {
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(UUID.randomUUID().toString().getBytes());
        fileOutputStream.close();
    }
}
