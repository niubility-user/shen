package com.xiaomi.push;

import android.os.Environment;
import android.os.StatFs;
import android.text.TextUtils;
import java.io.File;

/* loaded from: classes11.dex */
public class w9 {
    public static long a() {
        File externalStorageDirectory;
        if (!c() && (externalStorageDirectory = Environment.getExternalStorageDirectory()) != null && !TextUtils.isEmpty(externalStorageDirectory.getPath())) {
            try {
                StatFs statFs = new StatFs(externalStorageDirectory.getPath());
                return statFs.getBlockSize() * (statFs.getAvailableBlocks() - 4);
            } catch (Throwable unused) {
            }
        }
        return 0L;
    }

    public static boolean b() {
        try {
            return Environment.getExternalStorageState().equals("removed");
        } catch (Exception e2) {
            g.j.a.a.a.c.s(e2);
            return true;
        }
    }

    public static boolean c() {
        try {
            return true ^ Environment.getExternalStorageState().equals("mounted");
        } catch (Exception e2) {
            String str = "check SDCard is busy: " + e2;
            return true;
        }
    }

    public static boolean d() {
        return a() <= 102400;
    }

    public static boolean e() {
        return (c() || d() || b()) ? false : true;
    }
}
