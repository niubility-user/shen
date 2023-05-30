package com.jd.libs.hybrid.offlineload.utils;

import android.content.Context;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.jd.libs.hybrid.base.util.Log;
import com.jd.libs.hybrid.offlineload.entity.CommonEntity;
import com.jd.libs.hybrid.offlineload.entity.FileDetail;
import com.jingdong.jdsdk.constant.CartConstant;
import java.io.File;

/* loaded from: classes16.dex */
public class CommonFileUtils {
    public static final String HYBRID_COMMON_FILE_DIR;
    public static final String OLD_COMMON_FILE_DIR;

    static {
        StringBuilder sb = new StringBuilder();
        sb.append(OfflineFileUtils.HYBRID_OFFLINE_ROOT_DIR);
        String str = File.separator;
        sb.append(str);
        sb.append("common");
        HYBRID_COMMON_FILE_DIR = sb.toString();
        OLD_COMMON_FILE_DIR = OfflineFileUtils.OLD_OFFLINE_ROOT_DIR + str + ".common";
    }

    public static void deleteDownloadedFiles(Context context) {
        FileUtils.deleteFile(FileUtils.getDir(context, HYBRID_COMMON_FILE_DIR));
    }

    public static void deleteEntityFile(CommonEntity commonEntity) {
        if (commonEntity == null) {
            return;
        }
        FileDetail fileDetail = commonEntity.getFileDetail();
        String path = fileDetail != null ? fileDetail.getPath() : "";
        String parent = TextUtils.isEmpty(path) ? "" : new File(path).getParent();
        if (TextUtils.isEmpty(parent)) {
            return;
        }
        while (parent.endsWith(File.separator)) {
            parent = parent.substring(0, parent.length() - 1);
        }
        if (!parent.endsWith(HYBRID_COMMON_FILE_DIR) && !parent.endsWith(OLD_COMMON_FILE_DIR)) {
            Log.d("CommonFileUtils", String.format("delete common file(id:%s) %s [%s]", commonEntity.getId(), commonEntity.getUrl(), parent));
            FileUtils.deleteFile(parent);
            return;
        }
        Log.e("CommonFileUtils", String.format("try to delete common file(id:%s) but path is wrong. [%s]", commonEntity.getId(), parent));
        OfflineExceptionUtils.reportError("hybrid_del_forbid", "try to delete forbidden file", "CommonFileUtils#deleteEntityFile", "file path = " + parent);
    }

    public static String generateFileName(String str) {
        String fileNameFromPath = getFileNameFromPath(str);
        if (TextUtils.isEmpty(fileNameFromPath)) {
            fileNameFromPath = FileUtils.getRandomFileName();
        }
        return FileUtils.getTimestampForName() + CartConstant.KEY_YB_INFO_LINK + fileNameFromPath;
    }

    public static String getFileNameFromPath(String str) {
        int lastIndexOf;
        return (str.endsWith("/") || -1 == (lastIndexOf = str.lastIndexOf("/"))) ? "" : str.substring(lastIndexOf + 1);
    }

    public static String getSaveRelativeDir(String str) {
        return HYBRID_COMMON_FILE_DIR + File.separator + str;
    }

    @Nullable
    public static String getSourceDir(Context context, String str) {
        String str2;
        if (TextUtils.isEmpty(str)) {
            str2 = "";
        } else {
            str2 = File.separator + str;
        }
        String dir = FileUtils.getDir(context, HYBRID_COMMON_FILE_DIR);
        if (TextUtils.isEmpty(dir)) {
            return null;
        }
        return dir + str2;
    }
}
