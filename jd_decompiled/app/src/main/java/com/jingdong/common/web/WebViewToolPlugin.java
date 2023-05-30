package com.jingdong.common.web;

import android.content.Context;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.jd.xbridge.base.IBridgeCallback;
import com.jd.xbridge.base.IBridgePlugin;
import com.jd.xbridge.base.IBridgeWebView;
import com.jingdong.corelib.utils.Log;
import java.io.File;
import java.io.FilenameFilter;

/* loaded from: classes6.dex */
public class WebViewToolPlugin implements IBridgePlugin {
    private final String TAG = "WebViewToolPlugin";

    public void deleteFile(File file) {
        if (file != null && file.exists()) {
            if (file.isDirectory()) {
                File[] listFiles = file.listFiles();
                if (listFiles == null) {
                    return;
                }
                for (File file2 : listFiles) {
                    deleteFile(file2);
                }
            }
            file.delete();
        }
    }

    @Override // com.jd.xbridge.base.IBridgePlugin
    public boolean execute(@Nullable final IBridgeWebView iBridgeWebView, @Nullable String str, @Nullable String str2, @Nullable final IBridgeCallback iBridgeCallback) {
        Log.d("WebViewToolPlugin", "action:" + str + "  params:" + str2);
        str.hashCode();
        if (str.equals("clearCache")) {
            if (iBridgeWebView == null || iBridgeWebView.getView() == null) {
                return true;
            }
            iBridgeWebView.getView().post(new Runnable() { // from class: com.jingdong.common.web.WebViewToolPlugin.1
                @Override // java.lang.Runnable
                public void run() {
                    File[] listFiles;
                    try {
                        Context context = iBridgeWebView.getView().getContext();
                        if (context != null) {
                            String parent = context.getDir("", 0).getParent();
                            if (parent == null || TextUtils.isEmpty(parent)) {
                                return;
                            }
                            File file = new File(parent);
                            if (!file.exists() || !file.isDirectory() || (listFiles = file.listFiles(new FilenameFilter() { // from class: com.jingdong.common.web.WebViewToolPlugin.1.1
                                @Override // java.io.FilenameFilter
                                public boolean accept(File file2, String str3) {
                                    return str3 != null && (str3.startsWith("app_webview") || str3.startsWith("app_x5webview"));
                                }
                            })) == null || listFiles.length <= 0) {
                                return;
                            }
                            for (File file2 : listFiles) {
                                File file3 = new File(file2.getPath());
                                WebViewToolPlugin.this.deleteFile(file3);
                                IBridgeCallback iBridgeCallback2 = iBridgeCallback;
                                if (iBridgeCallback2 != null) {
                                    iBridgeCallback2.onSuccess("\u5220\u9664\u6210\u529f\uff1a" + file3.getPath());
                                }
                            }
                        }
                    } catch (Exception unused) {
                        IBridgeCallback iBridgeCallback3 = iBridgeCallback;
                        if (iBridgeCallback3 != null) {
                            iBridgeCallback3.onError("\u6267\u884c\u5f02\u5e38");
                        }
                    }
                }
            });
            return true;
        }
        return false;
    }
}
