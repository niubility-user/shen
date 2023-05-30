package com.jd.libs.hybrid.offlineload.utils;

import android.content.Context;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.jd.dynamic.DYConstants;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jd.libs.hybrid.base.HybridSettings;
import com.jd.libs.hybrid.base.util.Log;
import com.jd.libs.hybrid.base.util.PreferenceUtils;
import com.jd.libs.hybrid.offlineload.entity.BuildInOfflineEntity;
import com.jd.libs.hybrid.offlineload.entity.OfflineEntity;
import com.jingdong.jdsdk.constant.CartConstant;
import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes16.dex */
public class OfflineFileUtils {
    public static final String HYBRID_OFFLINE_BUILDIN_DIR;
    public static final String HYBRID_OFFLINE_BUILDIN_FILE_DIR;
    public static final String HYBRID_OFFLINE_BUILDIN_ZIP_DIR;
    public static final String HYBRID_OFFLINE_DOWNLOAD_ZIP_DIR;
    public static final String HYBRID_OFFLINE_FILE_DIR;
    public static final String HYBRID_OFFLINE_FILE_TEMP_DIR;
    public static final String HYBRID_OFFLINE_ROOT_DIR = "hybrid";
    public static final String OLD_OFFLINE_BUILDIN_DIR;
    public static final String OLD_OFFLINE_BUILDIN_FILE_DIR;
    public static final String OLD_OFFLINE_BUILDIN_ZIP_DIR;
    public static final String OLD_OFFLINE_DOWNLOAD_ZIP_DIR;
    public static final String OLD_OFFLINE_FILE_DIR;
    public static final String OLD_OFFLINE_FILE_TEMP_DIR;
    public static final String OLD_OFFLINE_ROOT_DIR;
    private static final String a;
    private static final String b;

    /* renamed from: c  reason: collision with root package name */
    private static final String f6122c;
    private static volatile JDJSONObject d;

    /* renamed from: e  reason: collision with root package name */
    private static volatile Map<String, Integer> f6123e;

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
        String str2 = HYBRID_OFFLINE_ROOT_DIR + str + "buildIn";
        HYBRID_OFFLINE_BUILDIN_DIR = str2;
        HYBRID_OFFLINE_BUILDIN_FILE_DIR = str2 + str + "file";
        HYBRID_OFFLINE_BUILDIN_ZIP_DIR = str2 + str + "zip";
        String str3 = HYBRID_OFFLINE_ROOT_DIR + str + "test";
        a = str3;
        b = str3 + str + "file";
        f6122c = str3 + str + "zip";
        String str4 = HYBRID_OFFLINE_ROOT_DIR + str + ".preload";
        OLD_OFFLINE_ROOT_DIR = str4;
        String str5 = str4 + str + ".file";
        OLD_OFFLINE_FILE_DIR = str5;
        OLD_OFFLINE_DOWNLOAD_ZIP_DIR = str4 + str + ".zip";
        OLD_OFFLINE_FILE_TEMP_DIR = str5 + str + DYConstants.TEMP_NAME_PREFIX;
        String str6 = str4 + str + ".buildIn";
        OLD_OFFLINE_BUILDIN_DIR = str6;
        OLD_OFFLINE_BUILDIN_FILE_DIR = str6 + str + ".file";
        OLD_OFFLINE_BUILDIN_ZIP_DIR = str6 + str + ".zip";
    }

    private static void a() {
        if (d == null) {
            try {
                d = JDJSON.parseObject(PreferenceUtils.getString(HybridSettings.getAppContext(), "toDelete", null));
            } catch (Exception e2) {
                Log.e("OfflineFileUtils", e2);
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
            if (f6123e == null) {
                f6123e = new HashMap();
            }
            Integer num = f6123e.get(str);
            f6123e.put(str, Integer.valueOf(num != null ? 1 + num.intValue() : 1));
            Log.d("OfflineFileUtils", "File in using, path = " + str);
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
        String dir = FileUtils.getDir(HybridSettings.getAppContext(), str);
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
            Log.e("OfflineFileUtils", e2);
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

    public static <T extends BuildInOfflineEntity> File copyBuildInZipFromAsset(Context context, T t) {
        if (t == null) {
            return null;
        }
        String url = t.getFileInfo() != null ? t.getFileInfo().getUrl() : "";
        String fileNameFromPath = TextUtils.isEmpty(url) ? null : getFileNameFromPath(url);
        Log.d("OfflineFileUtils", "copyBuildInZipFromAsset: fileName = " + fileNameFromPath);
        if (TextUtils.isEmpty(fileNameFromPath)) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(HYBRID_OFFLINE_ROOT_DIR);
        String str = File.separator;
        sb.append(str);
        sb.append(fileNameFromPath);
        String sb2 = sb.toString();
        File file = new File(getBuildInZipDir(context) + str + fileNameFromPath + CartConstant.KEY_YB_INFO_LINK + FileUtils.getTimestampForName());
        if (FileUtils.copyAssetFile(context, sb2, file)) {
            return file;
        }
        return null;
    }

    public static void deleteAllFiles(Context context) {
        deleteDownloadedFiles(context);
        deleteBuildInFiles(context);
        deleteTestFiles(context);
    }

    public static synchronized void deleteAllOldFiles() {
        synchronized (OfflineFileUtils.class) {
            synchronized ("toDelete") {
                a();
                if (d.isEmpty()) {
                    return;
                }
                Iterator<String> it = d.keySet().iterator();
                while (it.hasNext()) {
                    String string = d.getString(it.next());
                    Log.d("OfflineFileUtils", "Delete old files, path = " + string);
                    FileUtils.deleteFile(string);
                }
                d = new JDJSONObject();
                PreferenceUtils.putString(HybridSettings.getAppContext(), "toDelete", null);
            }
        }
    }

    public static void deleteBuildInFiles(Context context) {
        FileUtils.deleteFile(FileUtils.getDir(context, HYBRID_OFFLINE_BUILDIN_DIR));
    }

    public static void deleteDownloadedFiles(Context context) {
        FileUtils.deleteFile(FileUtils.getDir(context, HYBRID_OFFLINE_FILE_DIR));
        FileUtils.deleteFile(FileUtils.getDir(context, HYBRID_OFFLINE_DOWNLOAD_ZIP_DIR));
        deleteTempFiles(context);
    }

    public static void deleteEntityFile(OfflineEntity offlineEntity) {
        deleteUnzipFile(offlineEntity);
        deleteZipFile(offlineEntity);
        offlineEntity.setAvailable(false);
        offlineEntity.setFileRootPath(null);
        offlineEntity.setZipFile(null);
        offlineEntity.setDocumentFile(null);
        offlineEntity.setSourceFile(null);
    }

    public static void deleteEntityOldFile(OfflineEntity offlineEntity) {
        FileUtils.deleteFile(offlineEntity.getOldZipFile() != null ? offlineEntity.getOldZipFile().getPath() : null);
        deleteOldUnzipFile(offlineEntity);
        offlineEntity.setOldFileRootPath(null);
        offlineEntity.setOldZipFile(null);
    }

    public static synchronized boolean deleteOldFiles(String str) {
        synchronized (OfflineFileUtils.class) {
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
                Log.d("OfflineFileUtils", "Delete old files, path = " + str2);
                FileUtils.deleteFile(str2);
                Log.d("OfflineFileUtils", "Delete old files, path = " + str3);
                FileUtils.deleteFile(str3);
                return !TextUtils.isEmpty(str2);
            }
        }
    }

    public static void deleteOldUnzipFile(OfflineEntity offlineEntity) {
        String oldFileRootPath = offlineEntity.getOldFileRootPath();
        if (TextUtils.isEmpty(oldFileRootPath)) {
            return;
        }
        Log.d("OfflineFileUtils", String.format("delete id(%s)'s old unzip files: %s", offlineEntity.getAppid(), oldFileRootPath));
        FileUtils.deleteFile(oldFileRootPath);
    }

    public static void deleteTempFiles(Context context) {
        FileUtils.deleteFile(FileUtils.getDir(context, HYBRID_OFFLINE_FILE_TEMP_DIR));
    }

    public static void deleteTestFiles(Context context) {
        FileUtils.deleteFile(FileUtils.getDir(context, a));
    }

    public static void deleteUnzipFile(OfflineEntity offlineEntity) {
        String fileRootPath = offlineEntity.getFileRootPath();
        if (TextUtils.isEmpty(fileRootPath)) {
            return;
        }
        Log.d("OfflineFileUtils", String.format("delete id(%s)'s unzip files: %s", offlineEntity.getAppid(), fileRootPath));
        FileUtils.deleteFile(fileRootPath);
    }

    public static void deleteZipFile(OfflineEntity offlineEntity) {
        if (offlineEntity.getZipFile() != null) {
            Log.d("OfflineFileUtils", String.format("delete id(%s)'s old zip file: %s", offlineEntity.getAppid(), offlineEntity.getZipFile().getPath()));
            FileUtils.deleteFile(offlineEntity.getZipFile().getPath());
        }
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

    public static void getBuildInConfigFromAsset(Context context, Map<String, String> map) {
        if (map == null) {
            return;
        }
        String str = null;
        try {
            String[] list = context.getAssets().list(HYBRID_OFFLINE_ROOT_DIR);
            if (list != null && list.length > 0) {
                long j2 = 0;
                for (String str2 : list) {
                    if (str2.endsWith("_build-in-config.json")) {
                        String[] split = str2.split("_build-in-config.json");
                        if (split.length > 0) {
                            long parseLong = (TextUtils.isEmpty(split[0]) || !TextUtils.isDigitsOnly(split[0])) ? 0L : Long.parseLong(split[0]);
                            if (parseLong > j2) {
                                str = str2;
                                j2 = parseLong;
                            }
                        }
                    }
                }
            }
        } catch (Throwable th) {
            Log.e("OfflineFileUtils", th);
        }
        if (TextUtils.isEmpty(str)) {
            return;
        }
        String decryptData = CryptUtils.decryptData(FileUtils.getStringFromAsset(context, HYBRID_OFFLINE_ROOT_DIR + File.separator + str));
        if (TextUtils.isEmpty(decryptData)) {
            return;
        }
        JDJSONArray parseArray = JDJSON.parseArray(decryptData);
        for (int i2 = 0; parseArray != null && i2 < parseArray.size(); i2++) {
            JDJSONObject optJSONObject = parseArray.optJSONObject(i2);
            if (optJSONObject != null && optJSONObject.optInt("type", 1) != 4) {
                String jSONString = optJSONObject.toJSONString();
                Log.d("OfflineFileUtils", "fetch build-in config: " + jSONString);
                map.put(String.valueOf(jSONString.hashCode()), optJSONObject.toJSONString());
            }
        }
    }

    @Nullable
    public static String getBuildInSourceDir(Context context, String str) {
        String str2;
        if (TextUtils.isEmpty(str)) {
            str2 = "";
        } else {
            str2 = File.separator + str;
        }
        String dir = FileUtils.getDir(context, HYBRID_OFFLINE_BUILDIN_FILE_DIR);
        if (TextUtils.isEmpty(dir)) {
            return null;
        }
        return dir + str2;
    }

    @Nullable
    public static String getBuildInZipDir(Context context) {
        return FileUtils.getDir(context, HYBRID_OFFLINE_BUILDIN_ZIP_DIR);
    }

    public static String getBuildInZipRelativeDir() {
        return HYBRID_OFFLINE_BUILDIN_ZIP_DIR;
    }

    public static String getFileNameFromPath(String str) {
        int lastIndexOf;
        return (str.endsWith("/") || -1 == (lastIndexOf = str.lastIndexOf("/"))) ? "" : str.substring(lastIndexOf + 1);
    }

    @Nullable
    public static String getSourceDir(Context context, String str) {
        String str2;
        if (TextUtils.isEmpty(str)) {
            str2 = "";
        } else {
            str2 = File.separator + str;
        }
        String dir = FileUtils.getDir(context, HYBRID_OFFLINE_FILE_DIR);
        if (TextUtils.isEmpty(dir)) {
            return null;
        }
        return dir + str2;
    }

    @Nullable
    public static String getTestSourceDir(Context context, String str, String str2) {
        String str3;
        if (TextUtils.isEmpty(str2)) {
            str3 = "";
        } else {
            str3 = File.separator + str2;
        }
        String dir = FileUtils.getDir(context, b);
        if (TextUtils.isEmpty(dir)) {
            return null;
        }
        return dir + File.separator + str + str3;
    }

    public static String getTestZipRelativeDir() {
        return f6122c;
    }

    @Nullable
    public static String getZipDir(Context context) {
        return FileUtils.getDir(context, HYBRID_OFFLINE_DOWNLOAD_ZIP_DIR);
    }

    public static String getZipRelativeDir() {
        return HYBRID_OFFLINE_DOWNLOAD_ZIP_DIR;
    }

    public static boolean isFileInUsingState(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        synchronized ("toDelete") {
            if (f6123e != null && !f6123e.isEmpty()) {
                return f6123e.containsKey(str);
            }
            return false;
        }
    }

    public static boolean isOldFileDir(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return str.contains(OLD_OFFLINE_ROOT_DIR);
    }

    public static boolean removeFileInUsingState(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        synchronized ("toDelete") {
            if (f6123e != null && !f6123e.isEmpty()) {
                if (f6123e.containsKey(str)) {
                    Log.d("OfflineFileUtils", "File using state removed, path = " + str);
                    Integer num = f6123e.get(str);
                    int intValue = num != null ? num.intValue() - 1 : 0;
                    if (intValue > 0) {
                        f6123e.put(str, Integer.valueOf(intValue));
                    } else {
                        f6123e.remove(str);
                    }
                    return true;
                }
                return false;
            }
            return false;
        }
    }
}
