package tv.danmaku.ijk.media.alpha.util;

import android.text.TextUtils;
import com.jingdong.jdsdk.constant.CartConstant;
import java.io.File;
import java.text.SimpleDateFormat;

/* loaded from: classes11.dex */
public class FileUtil {
    private static final String TAG = "FileUtil";

    public static void clearVideoCache(String str) {
        String[] list;
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            File file = new File(str);
            if (file.exists() && file.isDirectory() && (list = file.list()) != null) {
                for (String str2 : list) {
                    File file2 = new File(str2);
                    if (file2.isDirectory()) {
                        file2.deleteOnExit();
                    } else if (!TextUtils.isEmpty(getExpireTime(file2.getName()))) {
                        if (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(getExpireTime(file2.getName()) + " 23:59:59").getTime() < System.currentTimeMillis()) {
                            String str3 = file2.getName() + " is expired";
                            file2.deleteOnExit();
                        }
                    }
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private static String getExpireTime(String str) {
        if (TextUtils.isEmpty(str) || !str.contains(CartConstant.KEY_YB_INFO_LINK)) {
            return null;
        }
        return str.substring(str.indexOf(95) + 1, str.length() - 4);
    }
}
