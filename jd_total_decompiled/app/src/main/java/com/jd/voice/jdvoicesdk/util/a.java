package com.jd.voice.jdvoicesdk.util;

import android.content.Context;
import android.os.Environment;
import android.os.StatFs;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Locale;

/* loaded from: classes18.dex */
public class a {
    static {
        new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
    }

    public static boolean a() {
        return Environment.getExternalStorageState().equals("mounted");
    }

    public static String b(Context context, String str) {
        if (c()) {
            return Environment.getExternalStorageDirectory() + File.separator + "jdVoice" + str;
        }
        return context.getFilesDir() + File.separator + "jdVoice" + str;
    }

    public static boolean c() {
        if (a()) {
            StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getAbsolutePath());
            if (statFs.getAvailableBlocks() * statFs.getBlockSize() > 2097152) {
                return true;
            }
        }
        return false;
    }
}
