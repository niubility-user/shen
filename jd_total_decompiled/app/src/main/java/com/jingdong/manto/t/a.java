package com.jingdong.manto.t;

import android.os.Build;
import android.system.Os;
import com.jingdong.manto.utils.MantoLog;
import java.io.File;
import java.io.IOException;

/* loaded from: classes16.dex */
public class a {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean a(String str, String str2) {
        File file = new File(str2);
        if (file.getParentFile() != null && !file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e2) {
                MantoLog.e("file", "error", e2);
            }
        }
        if (Build.VERSION.SDK_INT >= 21) {
            try {
                Os.rename(str, str2);
                MantoLog.d("file", "rename works");
                return true;
            } catch (Throwable th) {
                MantoLog.e("file", "rename error", th);
            }
        }
        return new File(str).renameTo(new File(str2));
    }
}
