package com.tencent.smtt.utils;

import android.content.Context;
import android.os.Build;
import android.os.Looper;
import android.text.TextUtils;
import com.tencent.smtt.sdk.QbSdk;
import java.io.File;
import java.io.FileFilter;

/* loaded from: classes9.dex */
public class n {
    public static boolean a(Context context) {
        if (Looper.getMainLooper() != Looper.myLooper()) {
            return b(context);
        }
        return true;
    }

    private static boolean a(File file) {
        try {
            return !e.b(file);
        } catch (Throwable th) {
            String str = "isOatFileBroken exception: " + th;
            return false;
        }
    }

    public static boolean b(Context context) {
        int i2;
        File c2;
        try {
            i2 = Build.VERSION.SDK_INT;
        } catch (Throwable th) {
            th.printStackTrace();
        }
        if (i2 < 21 || i2 > 25 || (c2 = c(context)) == null) {
            return true;
        }
        for (File file : c2.listFiles(new FileFilter() { // from class: com.tencent.smtt.utils.n.1
            @Override // java.io.FileFilter
            public boolean accept(File file2) {
                String name = file2.getName();
                return !TextUtils.isEmpty(name) && name.endsWith(".dex");
            }
        })) {
            if (file.isFile() && file.exists()) {
                if (a(file)) {
                    TbsLog.w("TbsCheckUtils", "" + file + " is invalid --> check failed!");
                    file.delete();
                    return false;
                }
                TbsLog.i("TbsCheckUtils", "" + file + " #4 check success!");
            }
        }
        TbsLog.i("TbsCheckUtils", "checkTbsValidity -->#5 check ok!");
        return true;
    }

    private static File c(Context context) {
        File file = new File(QbSdk.getTbsFolderDir(context), "core_share");
        if (file.isDirectory() && file.exists()) {
            return file;
        }
        return null;
    }
}
