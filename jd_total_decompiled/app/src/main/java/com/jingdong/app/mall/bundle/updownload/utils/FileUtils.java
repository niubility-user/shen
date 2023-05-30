package com.jingdong.app.mall.bundle.updownload.utils;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.jingdong.sdk.oklog.OKLog;
import java.io.File;

/* loaded from: classes3.dex */
public final class FileUtils {
    public static final String APP_DATA_DIR = "/JDIM";
    public static final String DIR_EMOJI = "/emoji";

    public static void delete(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        File file = new File(str);
        if (file.exists()) {
            file.delete();
        }
    }

    public static void deleteDirWithFile(File file) {
        if (file != null && file.exists() && file.isDirectory()) {
            for (File file2 : file.listFiles()) {
                if (file2.isFile()) {
                    file2.delete();
                } else if (file2.isDirectory()) {
                    deleteDirWithFile(file2);
                }
            }
            file.delete();
        }
    }

    public static File getEmojiCacheDir(Context context) {
        File file = new File(getNewCacheDir(context) + DIR_EMOJI + "/.nomedia");
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }

    @Nullable
    private static File getExternalCacheDir(Context context) {
        File externalCacheDir = Build.VERSION.SDK_INT >= 19 ? context.getExternalCacheDir() : context.getCacheDir();
        if (externalCacheDir != null) {
            if (!externalCacheDir.exists() || !externalCacheDir.isDirectory()) {
                externalCacheDir.mkdirs();
            }
        } else {
            externalCacheDir = context.getFilesDir();
        }
        OKLog.d("getExternalCacheDir", externalCacheDir == null ? "" : externalCacheDir.getAbsolutePath());
        return externalCacheDir;
    }

    private static String getNewCacheDir(Context context) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(getExternalCacheDir(context));
        stringBuffer.append("/");
        stringBuffer.append(APP_DATA_DIR);
        return stringBuffer.toString();
    }

    public static boolean isFileExist(String str) {
        if (str != null) {
            return new File(str).exists();
        }
        return false;
    }

    public static File newFile(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return new File(str);
    }
}
