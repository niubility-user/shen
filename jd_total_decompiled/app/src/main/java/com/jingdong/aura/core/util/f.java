package com.jingdong.aura.core.util;

import com.jd.dynamic.base.interfaces.IExceptionHandler;
import java.io.File;

/* loaded from: classes4.dex */
public class f {
    private static com.jingdong.aura.core.util.l.b a = com.jingdong.aura.core.util.l.c.a("PathUtils");

    public static void a(File file) {
        if (file == null) {
            return;
        }
        File[] listFiles = file.listFiles();
        if (listFiles != null) {
            for (int i2 = 0; i2 < listFiles.length; i2++) {
                if (listFiles[i2].isDirectory()) {
                    a(listFiles[i2]);
                } else {
                    listFiles[i2].delete();
                }
            }
        }
        file.delete();
    }

    private static void b(File file) {
        File[] listFiles = file.listFiles();
        if (listFiles == null) {
            a.c("listFiles is null!");
            return;
        }
        for (int i2 = 0; i2 < listFiles.length; i2++) {
            if (listFiles[i2].isDirectory()) {
                b(listFiles[i2]);
            } else if (listFiles[i2].getName().startsWith("libcom") && listFiles[i2].getName().endsWith(".so")) {
                listFiles[i2].delete();
            }
        }
    }

    public static void a() {
        try {
            File file = new File(d.a(), File.separatorChar + IExceptionHandler.DynamicExceptionData.TYPE_STORAGE);
            b(file);
            File file2 = new File(file, "oat");
            if (file2.exists()) {
                a(file2);
            }
        } catch (Throwable unused) {
        }
    }
}
