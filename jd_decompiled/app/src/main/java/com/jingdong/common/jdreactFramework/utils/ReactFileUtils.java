package com.jingdong.common.jdreactFramework.utils;

import com.jingdong.common.jdreactFramework.JDReactHelper;
import java.io.File;

/* loaded from: classes5.dex */
public class ReactFileUtils {
    private static final String TAG = "ReactFileUtils";

    public static void cleanIntermediateFile(File file) {
        String[] list;
        if (JDReactHelper.newInstance().isDebug()) {
            JLog.d(TAG, "Error , revert the backupfile is  is " + file.getAbsolutePath());
        }
        if (!file.exists() || (list = file.list()) == null || list.length <= 0) {
            return;
        }
        for (String str : list) {
            File file2 = new File(file, str);
            if (file2.isDirectory()) {
                if (JDReactHelper.newInstance().isDebug()) {
                    JLog.d(TAG, "revertBackupFiles(), revert clean the Directory " + file2.getAbsolutePath());
                }
                FileUtil.emptyDir(file2);
            } else {
                if (JDReactHelper.newInstance().isDebug()) {
                    JLog.d(TAG, "revertBackupFiles(), revert clean the file " + file2.getAbsolutePath());
                }
                file2.delete();
            }
        }
    }
}
