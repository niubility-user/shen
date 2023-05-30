package com.jingdong.common.web;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.SystemClock;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.sdk.oklog.OKLog;
import java.io.File;
import java.io.FilenameFilter;

/* loaded from: classes6.dex */
public class Web64BitFixer {
    private static final String TAG = "Web64BitFixer";

    public static boolean deleteCacheFilesFor64() {
        File[] listFiles;
        Context applicationContext = JdSdk.getInstance().getApplicationContext();
        if (applicationContext == null) {
            return false;
        }
        long elapsedRealtime = SystemClock.elapsedRealtime();
        try {
            SharedPreferences sharedPreferences = applicationContext.getSharedPreferences("WebViewChromiumPrefs", 0);
            if (sharedPreferences != null) {
                sharedPreferences.edit().clear().apply();
                if (OKLog.D) {
                    OKLog.d(TAG, "Clear webview's WebViewChromiumPrefs.");
                }
            }
            String parent = applicationContext.getDir("", 0).getParent();
            File file = new File(parent);
            if (OKLog.D) {
                OKLog.d(TAG, "App's data path [" + parent + "]");
            }
            if (file.exists() && file.isDirectory() && (listFiles = file.listFiles(new FilenameFilter() { // from class: com.jingdong.common.web.Web64BitFixer.1
                @Override // java.io.FilenameFilter
                public boolean accept(File file2, String str) {
                    return str != null && (str.startsWith("app_webview") || str.startsWith("app_x5webview"));
                }
            })) != null && listFiles.length > 0) {
                for (File file2 : listFiles) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(file2.getPath());
                    String str = File.separator;
                    sb.append(str);
                    sb.append("GPUCache");
                    File file3 = new File(sb.toString());
                    if (file3.exists()) {
                        if (OKLog.D) {
                            OKLog.d(TAG, "Try to delete [" + file3.getPath() + "]");
                        }
                        deleteRecursive(file3);
                    }
                    File file4 = new File(file2.getPath() + str + "Default" + str + "GPUCache");
                    if (file4.exists()) {
                        if (OKLog.D) {
                            OKLog.d(TAG, "Try to delete [" + file4.getPath() + "]");
                        }
                        deleteRecursive(file4);
                    }
                }
            }
            if (OKLog.D) {
                OKLog.d(TAG, "Time elapsed " + (SystemClock.elapsedRealtime() - elapsedRealtime) + "ms");
                return true;
            }
            return true;
        } catch (Throwable th) {
            if (OKLog.E) {
                OKLog.e(TAG, th);
            }
            return false;
        }
    }

    private static void deleteRecursive(File file) {
        if (file == null) {
            return;
        }
        if (file.isDirectory()) {
            File[] listFiles = file.listFiles();
            if (listFiles == null) {
                return;
            }
            for (File file2 : listFiles) {
                deleteRecursive(file2);
            }
        }
        boolean delete = file.delete();
        if (OKLog.D) {
            OKLog.d(TAG, "Delete webview's gpu cache [" + delete + "] : fileName: " + file);
        }
    }
}
