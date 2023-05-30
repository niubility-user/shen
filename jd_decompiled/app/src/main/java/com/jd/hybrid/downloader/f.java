package com.jd.hybrid.downloader;

import android.content.Context;
import com.jd.framework.network.filedown.JDFileService;
import com.jd.libs.hybrid.base.util.Log;
import com.jingdong.common.utils.LangUtils;
import java.io.File;

/* loaded from: classes13.dex */
public class f {
    private static void a(String str, File file) {
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            Runtime.getRuntime().exec("chmod " + str + LangUtils.SINGLE_SPACE + file);
            if (Log.isDebug()) {
                Log.d("FileService", "change mode file : " + file.getAbsolutePath() + " with mode : " + str);
            }
        } catch (Exception e2) {
            if (Log.isDebug()) {
                e2.printStackTrace();
                Log.d("FileService", " -->> chModFile mode:" + str + " file:" + file + " error:" + e2.getMessage());
            }
        }
    }

    public static File b(Context context, boolean z, String str, String str2) throws Exception {
        File filesDir = context.getFilesDir();
        if (str == null) {
            str = "";
        }
        File file = new File(filesDir, str);
        if (z && !file.exists()) {
            if (file.mkdirs()) {
                a(JDFileService.FILE_DIR_MODE_FOR_INTERNAL, file);
            } else {
                throw new IllegalStateException("Unable to create directory: " + file.getAbsolutePath());
            }
        }
        return new File(file, str2);
    }
}
