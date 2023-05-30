package com.jingdong.jdma.common.utils;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;

/* loaded from: classes12.dex */
public final class a {
    public static File a(@NonNull String str) {
        File file = null;
        try {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            File file2 = new File(str);
            try {
                if (file2.exists()) {
                    return file2;
                }
                if (file2.isDirectory()) {
                    file2.mkdirs();
                } else {
                    File parentFile = file2.getParentFile();
                    if (parentFile != null && !parentFile.exists()) {
                        parentFile.mkdirs();
                    }
                    try {
                        file2.createNewFile();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                        return null;
                    }
                }
                return file2;
            } catch (Throwable th) {
                th = th;
                file = file2;
                th.printStackTrace();
                return file;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static void b(@NonNull String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            File file = new File(str);
            if (file.exists()) {
                if (file.isDirectory()) {
                    for (File file2 : file.listFiles()) {
                        b(file2.getAbsolutePath());
                    }
                    file.delete();
                    return;
                }
                file.delete();
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static boolean c(@NonNull String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            return new File(str).exists();
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public static void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }
}
