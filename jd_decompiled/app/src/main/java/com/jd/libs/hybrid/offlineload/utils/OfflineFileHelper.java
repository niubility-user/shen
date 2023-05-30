package com.jd.libs.hybrid.offlineload.utils;

import android.content.Context;
import android.text.TextUtils;
import android.util.Pair;
import androidx.annotation.Nullable;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONObject;
import com.jd.libs.hybrid.base.HybridSettings;
import com.jd.libs.hybrid.base.util.Log;
import com.jd.libs.hybrid.base.util.PreferenceUtils;
import com.jd.libs.hybrid.offlineload.entity.FileDetail;
import com.jd.libs.hybrid.offlineload.entity.Module;
import com.jd.libs.hybrid.offlineload.entity.OfflineModule;
import com.jingdong.jdsdk.constant.CartConstant;
import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes16.dex */
public class OfflineFileHelper {
    public static final String HYBRID_OFFLINE_DOWNLOAD_ZIP_DIR;
    public static final String HYBRID_OFFLINE_FILE_DIR;
    public static final String HYBRID_OFFLINE_FILE_TEMP_DIR;
    public static final String HYBRID_OFFLINE_ROOT_DIR = "hybrid2";
    private static final String a;
    private static final String b;

    /* renamed from: c */
    private static final String f6120c;
    private static volatile JDJSONObject d;

    /* renamed from: e */
    private static volatile Map<String, Integer> f6121e;

    static {
        StringBuilder sb = new StringBuilder();
        sb.append(HYBRID_OFFLINE_ROOT_DIR);
        String str = File.separator;
        sb.append(str);
        sb.append("file");
        String sb2 = sb.toString();
        HYBRID_OFFLINE_FILE_DIR = sb2;
        HYBRID_OFFLINE_DOWNLOAD_ZIP_DIR = HYBRID_OFFLINE_ROOT_DIR + str + "zip";
        HYBRID_OFFLINE_FILE_TEMP_DIR = sb2 + str + "temp";
        String str2 = HYBRID_OFFLINE_ROOT_DIR + str + "test";
        a = str2;
        b = str2 + str + "file";
        f6120c = str2 + str + "zip";
    }

    private static void a() {
        if (d == null) {
            try {
                d = JDJSON.parseObject(PreferenceUtils.getString(HybridSettings.getAppContext(), "toDelete", null));
            } catch (Exception e2) {
                if (Log.isDebug()) {
                    Log.e("OfflineFileHelper", e2);
                }
            }
        }
        if (d == null) {
            d = new JDJSONObject();
        }
    }

    public static void addFileInUsingState(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        synchronized ("toDelete") {
            if (f6121e == null) {
                f6121e = new HashMap();
            }
            Integer num = f6121e.get(str);
            f6121e.put(str, Integer.valueOf(num != null ? 1 + num.intValue() : 1));
            if (Log.isDebug()) {
                Log.d("OfflineFileHelper", "File in using, path = " + str);
            }
        }
    }

    public static void addOldFilesToBeDeleted(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        synchronized ("toDelete") {
            a();
            d.put(str + "_file", (Object) str2);
            d.put(str + "_zip", (Object) str3);
            PreferenceUtils.putString(HybridSettings.getAppContext(), "toDelete", d.toJSONString());
        }
    }

    public static boolean checkDirHasFiles(String str) {
        String[] list;
        if (TextUtils.isEmpty(str)) {
            return true;
        }
        String dir = FileUtils.getDir(HybridSettings.getAppContext(), false, str);
        if (TextUtils.isEmpty(dir)) {
            return true;
        }
        try {
            File file = new File(dir);
            if ((file.isDirectory() && file.exists()) && (list = file.list()) != null) {
                if (list.length > 0) {
                    return true;
                }
            }
            return false;
        } catch (Exception e2) {
            if (Log.isDebug()) {
                Log.e("OfflineFileHelper", e2);
            }
            return true;
        }
    }

    public static String combinePath(String str, String str2) {
        String str3;
        if (TextUtils.isEmpty(str)) {
            return str2;
        }
        if (TextUtils.isEmpty(str2)) {
            str3 = "";
        } else {
            str3 = File.separator + str2;
        }
        return str + str3;
    }

    public static <T extends OfflineModule> File copyBuildInZipFromAsset(T t, String str) {
        if (t == null || TextUtils.isEmpty(str)) {
            return null;
        }
        Context appContext = HybridSettings.getAppContext();
        String url = t.getFileInfo() != null ? t.getFileInfo().getUrl() : "";
        String fileNameFromPath = TextUtils.isEmpty(url) ? null : getFileNameFromPath(url);
        if (Log.isDebug()) {
            Log.d("OfflineFileHelper", "copyBuildInZipFromAsset: fileName = " + fileNameFromPath);
        }
        if (TextUtils.isEmpty(fileNameFromPath)) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(OfflineFileUtils.HYBRID_OFFLINE_ROOT_DIR);
        String str2 = File.separator;
        sb.append(str2);
        sb.append(fileNameFromPath);
        String sb2 = sb.toString();
        File file = new File(str + str2 + fileNameFromPath + CartConstant.KEY_YB_INFO_LINK + FileUtils.getTimestampForName());
        if (FileUtils.copyAssetFile(appContext, sb2, file)) {
            return file;
        }
        return null;
    }

    public static void deleteAllFiles() {
        deleteBizFiles();
        deleteTestFiles();
    }

    public static synchronized void deleteAllOldFiles() {
        synchronized (OfflineFileHelper.class) {
            synchronized ("toDelete") {
                a();
                if (d.isEmpty()) {
                    return;
                }
                Iterator<String> it = d.keySet().iterator();
                while (it.hasNext()) {
                    String string = d.getString(it.next());
                    if (Log.isDebug()) {
                        Log.d("OfflineFileHelper", "Delete old files, path = " + string);
                    }
                    FileUtils.deleteFile(string);
                }
                d = new JDJSONObject();
                PreferenceUtils.putString(HybridSettings.getAppContext(), "toDelete", null);
            }
        }
    }

    public static void deleteBizFiles() {
        FileUtils.deleteFile(FileUtils.getDir(HybridSettings.getAppContext(), false, HYBRID_OFFLINE_FILE_DIR));
        FileUtils.deleteFile(FileUtils.getDir(HybridSettings.getAppContext(), false, HYBRID_OFFLINE_DOWNLOAD_ZIP_DIR));
        deleteTempFiles();
    }

    public static void deleteEntityFile(Module module) {
        deleteUnzipFile(module);
        deleteZipFile(module);
    }

    public static void deleteEntityOldFile(Module module) {
        deleteOldUnzipFile(module);
        deleteOldZipFile(module);
    }

    public static synchronized boolean deleteOldFiles(String str) {
        synchronized (OfflineFileHelper.class) {
            if (TextUtils.isEmpty(str)) {
                return false;
            }
            synchronized ("toDelete") {
                a();
                if (d.isEmpty()) {
                    return false;
                }
                String str2 = (String) d.remove(str + "_file");
                String str3 = (String) d.remove(str + "_zip");
                PreferenceUtils.putString(HybridSettings.getAppContext(), "toDelete", d.toJSONString());
                if (Log.isDebug()) {
                    Log.d("OfflineFileHelper", "Delete old files, path = " + str2);
                }
                FileUtils.deleteFile(str2);
                if (Log.isDebug()) {
                    Log.d("OfflineFileHelper", "Delete old files, path = " + str3);
                }
                FileUtils.deleteFile(str3);
                return !TextUtils.isEmpty(str2);
            }
        }
    }

    public static void deleteOldUnzipFile(Module module) {
        FileDetail oldUnzipFile = module.getOldUnzipFile();
        String path = oldUnzipFile != null ? oldUnzipFile.getPath() : null;
        if (!TextUtils.isEmpty(path)) {
            if (Log.isDebug()) {
                Log.d("OfflineFileHelper", String.format("delete id(%s)'s old unzip files: %s", module.getAppid(), path));
            }
            FileUtils.deleteFile(path);
        }
        module.setOldUnzipFile(null);
    }

    public static void deleteOldZipFile(Module module) {
        if (module.getOldZipFile() != null) {
            if (Log.isDebug()) {
                Log.d("OfflineFileHelper", String.format("delete id(%s)'s old zip file: %s", module.getAppid(), module.getOldZipFile().getPath()));
            }
            FileUtils.deleteFile(module.getOldZipFile().getPath());
        }
        module.setOldZipFile(null);
    }

    public static void deleteTempFiles() {
        FileUtils.deleteFile(FileUtils.getDir(HybridSettings.getAppContext(), false, HYBRID_OFFLINE_FILE_TEMP_DIR));
    }

    public static void deleteTestFiles() {
        FileUtils.deleteFile(FileUtils.getDir(HybridSettings.getAppContext(), false, a));
    }

    public static void deleteUnzipFile(Module module) {
        FileDetail unzipFile = module.getUnzipFile();
        String path = unzipFile != null ? unzipFile.getPath() : null;
        if (!TextUtils.isEmpty(path)) {
            if (Log.isDebug()) {
                Log.d("OfflineFileHelper", String.format("delete id(%s)'s unzip files: %s", module.getAppid(), path));
            }
            FileUtils.deleteFile(path);
        }
        module.setAvailable(false);
        module.setUnzipFile(null);
        module.setLocalFileListJson(null);
    }

    public static boolean deleteUsedFile(String str) {
        if (!removeFileInUsingState(str) || isFileInUsingState(str)) {
            return false;
        }
        return deleteOldFiles(String.valueOf(str.hashCode()));
    }

    public static void deleteZipFile(Module module) {
        if (module.getZipFile() != null) {
            if (Log.isDebug()) {
                Log.d("OfflineFileHelper", String.format("delete id(%s)'s zip file: %s", module.getAppid(), module.getZipFile().getPath()));
            }
            FileUtils.deleteFile(module.getZipFile().getPath());
        }
        module.setZipFile(null);
    }

    public static String generateFileName(String str) {
        String fileNameFromPath = getFileNameFromPath(str);
        if (TextUtils.isEmpty(fileNameFromPath)) {
            fileNameFromPath = FileUtils.getRandomFileName();
        }
        return fileNameFromPath + CartConstant.KEY_YB_INFO_LINK + FileUtils.getTimestampForName();
    }

    public static String generateSaveDirName(String str) {
        return str + CartConstant.KEY_YB_INFO_LINK + FileUtils.getTimestampForName();
    }

    public static Pair<Long, String> getBuildInConfigVerFromAsset() {
        long j2 = 0;
        String str = null;
        try {
            String[] list = HybridSettings.getAppContext().getAssets().list(OfflineFileUtils.HYBRID_OFFLINE_ROOT_DIR);
            if (list != null && list.length > 0) {
                long j3 = 0;
                for (String str2 : list) {
                    try {
                        if (str2.endsWith("_build-in-config.json")) {
                            String[] split = str2.split("_build-in-config.json");
                            if (split.length > 0) {
                                long parseLong = (TextUtils.isEmpty(split[0]) || !TextUtils.isDigitsOnly(split[0])) ? 0L : Long.parseLong(split[0]);
                                if (parseLong > j3) {
                                    str = str2;
                                    j3 = parseLong;
                                }
                            }
                        }
                    } catch (Throwable th) {
                        th = th;
                        j2 = j3;
                        Log.e("FileUtils", th);
                        return new Pair<>(Long.valueOf(j2), str);
                    }
                }
                j2 = j3;
            }
        } catch (Throwable th2) {
            th = th2;
        }
        return new Pair<>(Long.valueOf(j2), str);
    }

    public static String getBuildInConfigsFromAsset(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return CryptUtils.decryptData(FileUtils.getStringFromAsset(HybridSettings.getAppContext(), OfflineFileUtils.HYBRID_OFFLINE_ROOT_DIR + File.separator + str));
    }

    public static String getFileNameFromPath(String str) {
        int lastIndexOf;
        return (str.endsWith("/") || -1 == (lastIndexOf = str.lastIndexOf("/"))) ? "" : str.substring(lastIndexOf + 1);
    }

    @Nullable
    public static String getSourceDir(String str) {
        String str2;
        if (TextUtils.isEmpty(str)) {
            str2 = "";
        } else {
            str2 = File.separator + str;
        }
        String dir = FileUtils.getDir(HybridSettings.getAppContext(), HYBRID_OFFLINE_FILE_DIR);
        if (TextUtils.isEmpty(dir)) {
            return null;
        }
        return dir + str2;
    }

    @Nullable
    public static String getTestSourceDir(String str) {
        String str2;
        if (TextUtils.isEmpty(str)) {
            str2 = "";
        } else {
            str2 = File.separator + str;
        }
        String dir = FileUtils.getDir(HybridSettings.getAppContext(), b);
        if (TextUtils.isEmpty(dir)) {
            return null;
        }
        return dir + str2;
    }

    public static String getTestZipRelativeDir() {
        return f6120c;
    }

    @Nullable
    public static String getZipDir() {
        return FileUtils.getDir(HybridSettings.getAppContext(), HYBRID_OFFLINE_DOWNLOAD_ZIP_DIR);
    }

    public static String getZipRelativeDir() {
        return HYBRID_OFFLINE_DOWNLOAD_ZIP_DIR;
    }

    public static boolean isFileInUsingState(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        synchronized ("toDelete") {
            if (f6121e != null && !f6121e.isEmpty()) {
                return f6121e.containsKey(str);
            }
            return false;
        }
    }

    public static boolean removeFileInUsingState(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        synchronized ("toDelete") {
            if (f6121e != null && !f6121e.isEmpty()) {
                if (f6121e.containsKey(str)) {
                    if (Log.isDebug()) {
                        Log.d("OfflineFileHelper", "File using state removed, path = " + str);
                    }
                    Integer num = f6121e.get(str);
                    int intValue = num != null ? num.intValue() - 1 : 0;
                    if (intValue > 0) {
                        f6121e.put(str, Integer.valueOf(intValue));
                    } else {
                        f6121e.remove(str);
                    }
                    return true;
                }
                return false;
            }
            return false;
        }
    }
}
