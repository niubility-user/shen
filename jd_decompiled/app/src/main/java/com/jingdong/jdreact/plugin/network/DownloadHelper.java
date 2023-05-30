package com.jingdong.jdreact.plugin.network;

import android.text.TextUtils;
import com.jingdong.common.jdreactFramework.JDReactConstant;
import com.jingdong.jdreact.plugin.network.OKHttpUtil;
import java.io.File;

/* loaded from: classes13.dex */
public class DownloadHelper {
    private static String getSavePath(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(JDReactConstant.ReactDownloadPath.getAbsolutePath());
        String str2 = File.separator;
        sb.append(str2);
        sb.append("download_save");
        sb.append(str2);
        sb.append(str);
        return sb.toString();
    }

    public static void startDownload(String str, String str2, OKHttpUtil.DownloadListener downloadListener) {
        if (TextUtils.isEmpty(str2) || TextUtils.isEmpty(str)) {
            return;
        }
        OKHttpUtil.download(str, getSavePath(str2), downloadListener);
    }
}
