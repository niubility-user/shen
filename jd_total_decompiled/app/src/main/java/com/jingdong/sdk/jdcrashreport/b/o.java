package com.jingdong.sdk.jdcrashreport.b;

import android.os.Environment;
import android.text.TextUtils;
import java.io.File;

/* loaded from: classes7.dex */
public class o {
    static boolean a() {
        try {
            return Environment.getExternalStorageState().equals("mounted");
        } catch (Throwable th) {
            r.g("FileUtils", th);
            return false;
        }
    }

    public static boolean b(File file) {
        return file == null || !file.exists() || (file.isFile() && file.delete());
    }

    public static boolean c(String str) {
        if (TextUtils.isEmpty(str)) {
            return true;
        }
        File file = new File(str);
        if (file.exists()) {
            return file.isFile() && file.delete();
        }
        return true;
    }

    public static File d(String str) {
        File externalFilesDir;
        if (com.jingdong.sdk.jdcrashreport.d.I() == null || !a() || (externalFilesDir = com.jingdong.sdk.jdcrashreport.d.I().getExternalFilesDir(null)) == null) {
            return null;
        }
        if (!TextUtils.isEmpty(str)) {
            externalFilesDir = new File(externalFilesDir, File.separator + str);
        }
        if ((externalFilesDir.exists() && externalFilesDir.isDirectory()) || externalFilesDir.mkdirs()) {
            return externalFilesDir;
        }
        return null;
    }
}
