package com.jd.lib.un.utils;

import android.content.Context;
import android.os.Environment;
import java.io.File;

/* loaded from: classes16.dex */
public class UnCleanUtils {
    public static boolean cleanExternalCache(Context context) {
        return "mounted".equals(Environment.getExternalStorageState()) && deleteFilesInDir(context.getApplicationContext().getExternalCacheDir());
    }

    public static boolean cleanInternalCache(Context context) {
        return deleteFilesInDir(context.getApplicationContext().getCacheDir());
    }

    public static boolean cleanInternalDbByName(Context context, String str) {
        return context.getApplicationContext().deleteDatabase(str);
    }

    public static boolean cleanInternalDbs(Context context) {
        return deleteFilesInDir(new File(context.getApplicationContext().getFilesDir().getParent(), "databases"));
    }

    public static boolean cleanInternalFiles(Context context) {
        return deleteFilesInDir(context.getApplicationContext().getFilesDir());
    }

    public static boolean cleanInternalSp(Context context) {
        return deleteFilesInDir(new File(context.getApplicationContext().getFilesDir().getParent(), "shared_prefs"));
    }

    private static boolean deleteDir(File file) {
        if (file == null) {
            return false;
        }
        if (file.exists()) {
            if (file.isDirectory()) {
                File[] listFiles = file.listFiles();
                if (listFiles != null && listFiles.length != 0) {
                    for (File file2 : listFiles) {
                        if (file2.isFile()) {
                            if (!file2.delete()) {
                                return false;
                            }
                        } else if (file2.isDirectory() && !deleteDir(file2)) {
                            return false;
                        }
                    }
                }
                return file.delete();
            }
            return false;
        }
        return true;
    }

    private static boolean deleteFilesInDir(File file) {
        if (file == null) {
            return false;
        }
        if (file.exists()) {
            if (file.isDirectory()) {
                File[] listFiles = file.listFiles();
                if (listFiles != null && listFiles.length != 0) {
                    for (File file2 : listFiles) {
                        if (file2.isFile()) {
                            if (!file2.delete()) {
                                return false;
                            }
                        } else if (file2.isDirectory() && !deleteDir(file2)) {
                            return false;
                        }
                    }
                }
                return true;
            }
            return false;
        }
        return true;
    }

    private static File getFileByPath(String str) {
        if (UnStringUtils.isSpace(str)) {
            return null;
        }
        return new File(str);
    }

    public static boolean deleteFilesInDir(String str) {
        return deleteFilesInDir(getFileByPath(str));
    }
}
