package com.jingdong.common.xbridge;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import androidx.core.app.NotificationCompat;
import androidx.core.content.FileProvider;
import com.huawei.hms.actions.SearchIntents;
import com.jd.dynamic.base.interfaces.IExceptionHandler;
import com.jd.framework.network.filedown.JDFileService;
import com.jd.libs.hybrid.base.util.Log;
import com.jd.libs.hybrid.base.util.SharedPreferenceUtils;
import com.jd.xbridge.base.IBridgeCallback;
import com.jd.xbridge.base.IBridgePlugin;
import com.jd.xbridge.base.IBridgeWebView;
import com.jingdong.common.BaseFrameUtil;
import com.jingdong.common.frame.IMyActivity;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.network.toolbox.FileGuider;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpRequest;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.tencent.smtt.sdk.TbsReaderView;
import java.io.File;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes12.dex */
public class WebViewDownloadPlugin implements IBridgePlugin, com.jd.xbridge.base.a {
    private SharedPreferences downloadPreference;
    private BroadcastReceiver installedReceiver;
    private HttpRequest myHttpRequest;
    private final String TAG = "WebViewDownloadPlugin";
    private int progress = 1;
    private boolean downloadFinished = false;
    private boolean registerDownloadReceiver = false;

    /* JADX INFO: Access modifiers changed from: private */
    public String checkApkFilePath(String str) {
        String string = this.downloadPreference.getString(str, "");
        if (TextUtils.isEmpty(string)) {
            return "";
        }
        try {
            JSONObject jSONObject = new JSONObject(string);
            return jSONObject.has(TbsReaderView.KEY_FILE_PATH) ? jSONObject.getString(TbsReaderView.KEY_FILE_PATH) : "";
        } catch (JSONException e2) {
            e2.printStackTrace();
            return "";
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void deleteApkFile(String str) {
        if (str != null) {
            File file = new File(str);
            if (file.exists()) {
                file.delete();
            }
        }
    }

    private void downloadApk(final String str, final String str2, final IBridgeCallback iBridgeCallback) {
        if (!needDownLoad(str, str2)) {
            openSystemInstallView(str, iBridgeCallback);
            return;
        }
        final FileGuider fileGuider = new FileGuider();
        fileGuider.setSpace(1);
        fileGuider.setChildDirName("WebViewDownloadPlugin");
        fileGuider.setImmutable(false);
        fileGuider.setFileName(str + ".apk");
        fileGuider.setMode(1);
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setUrl(str2);
        httpSetting.setCacheMode(2);
        httpSetting.setType(500);
        httpSetting.setSavePath(fileGuider);
        httpSetting.setBreakpointTransmission(false);
        httpSetting.setAttempts(1);
        httpSetting.setListener(new HttpGroup.OnAllAndPauseListener() { // from class: com.jingdong.common.xbridge.WebViewDownloadPlugin.2
            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
            public void onEnd(HttpResponse httpResponse) {
                if (Log.isDebug()) {
                    Log.xLogD("WebViewDownloadPlugin", "download finished...");
                }
                iBridgeCallback.onSuccess(WebViewDownloadPlugin.this.getCallbackData(0, 0));
                WebViewDownloadPlugin.this.save(str, str2, JDFileService.getFilePath(fileGuider.getSpace(), JdSdk.getInstance().getApplicationContext(), fileGuider.getChildDirName(), "", fileGuider.getFileName()).getAbsolutePath());
                WebViewDownloadPlugin.this.downloadFinished = true;
                WebViewDownloadPlugin.this.progress = 1;
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
            public void onError(HttpError httpError) {
                if (Log.isDebug()) {
                    Log.d("WebViewDownloadPlugin", "download error...");
                }
                iBridgeCallback.onError("-1");
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnPauseListener
            public void onPause() {
                if (Log.isDebug()) {
                    Log.d("WebViewDownloadPlugin", "download onPause...");
                }
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnProgressListener
            public void onProgress(int i2, int i3) {
                WebViewDownloadPlugin.this.progressCallBack(i2, i3, iBridgeCallback);
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnStartListener
            public void onStart() {
                if (Log.isDebug()) {
                    Log.d("WebViewDownloadPlugin", "download start...");
                }
                iBridgeCallback.onSuccess(WebViewDownloadPlugin.this.getCallbackData(1, 0));
            }
        });
        this.myHttpRequest = HttpGroupUtils.getHttpGroupaAsynPool().add(httpSetting);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public JSONObject getCallbackData(int i2, int i3) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("condition", i2);
            if (i3 > 0) {
                jSONObject.put(NotificationCompat.CATEGORY_PROGRESS, i3);
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return jSONObject;
    }

    private Uri getUriForFile(File file, Intent intent) {
        Activity thisActivity = BaseFrameUtil.getInstance().getCurrentMyActivity().getThisActivity();
        if (thisActivity == null || file == null) {
            return null;
        }
        if (Build.VERSION.SDK_INT > 23) {
            Uri uriForFile = FileProvider.getUriForFile(thisActivity, thisActivity.getPackageName() + ".fileProviderRootPath", file);
            if (intent != null) {
                intent.addFlags(3);
                return uriForFile;
            }
            return uriForFile;
        }
        return Uri.fromFile(file);
    }

    private boolean needDownLoad(String str, String str2) {
        if (this.downloadPreference != null && !TextUtils.isEmpty(str)) {
            String string = this.downloadPreference.getString(str, "");
            if (!TextUtils.isEmpty(string)) {
                try {
                    JSONObject jSONObject = new JSONObject(string);
                    if (jSONObject.has(TbsReaderView.KEY_FILE_PATH) && jSONObject.has("url")) {
                        return !str2.equals(jSONObject.getString("url"));
                    }
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
            }
        }
        return true;
    }

    private void openSystemInstallView(String str, IBridgeCallback iBridgeCallback) {
        IMyActivity currentMyActivity = BaseFrameUtil.getInstance().getCurrentMyActivity();
        if (currentMyActivity == null) {
            iBridgeCallback.onError("error: myActivity is null");
            return;
        }
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setFlags(268435456);
        String checkApkFilePath = checkApkFilePath(str);
        if (TextUtils.isEmpty(checkApkFilePath)) {
            iBridgeCallback.onError("filePath is null, install failed, please download first");
            return;
        }
        intent.setDataAndType(getUriForFile(new File(checkApkFilePath), intent), "application/vnd.android.package-archive");
        currentMyActivity.startActivityNoException(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void progressCallBack(int i2, int i3, IBridgeCallback iBridgeCallback) {
        double d = i3;
        double d2 = i2;
        Double.isNaN(d);
        Double.isNaN(d2);
        if ((d / d2) * 10.0d > this.progress) {
            if (Log.isDebug()) {
                Log.d("WebViewDownloadPlugin", (this.progress * 10) + "%");
            }
            iBridgeCallback.onSuccess(getCallbackData(2, this.progress * 10));
            this.progress++;
        }
    }

    private void registerDownloadReceiver(final String str) {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.PACKAGE_ADDED");
        intentFilter.addAction("android.intent.action.PACKAGE_REPLACED");
        intentFilter.addDataScheme("package");
        this.installedReceiver = new BroadcastReceiver() { // from class: com.jingdong.common.xbridge.WebViewDownloadPlugin.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                if (intent.getAction().equals("android.intent.action.PACKAGE_ADDED") || intent.getAction().equals("android.intent.action.PACKAGE_REPLACED")) {
                    if (Log.isDebug()) {
                        Log.xLogD("WebViewDownloadPlugin", "\u5e94\u7528\u5b89\u88c5\u5b8c\u6210");
                    }
                    String checkApkFilePath = WebViewDownloadPlugin.this.checkApkFilePath(str);
                    if (TextUtils.isEmpty(checkApkFilePath)) {
                        return;
                    }
                    WebViewDownloadPlugin.this.deleteApkFile(checkApkFilePath);
                    SharedPreferenceUtils.commitString(WebViewDownloadPlugin.this.downloadPreference, str, null);
                }
            }
        };
        JdSdk.getInstance().getApplicationContext().registerReceiver(this.installedReceiver, intentFilter);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void save(String str, String str2, String str3) {
        JSONObject jSONObject = new JSONObject();
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3)) {
            return;
        }
        try {
            jSONObject.put("url", str2);
            jSONObject.put(TbsReaderView.KEY_FILE_PATH, str3);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        SharedPreferenceUtils.commitString(this.downloadPreference, str, jSONObject.toString());
    }

    public void cancelDownload(IBridgeCallback iBridgeCallback) {
        HttpRequest httpRequest = this.myHttpRequest;
        if (httpRequest != null) {
            httpRequest.stop();
            this.progress = 1;
            iBridgeCallback.onSuccess("cancel success");
            return;
        }
        iBridgeCallback.onError("cancel failed, no request is on process");
    }

    @Override // com.jd.xbridge.base.a
    public void destroy() {
        HttpRequest httpRequest;
        if (!this.downloadFinished && (httpRequest = this.myHttpRequest) != null) {
            httpRequest.stop();
        }
        if (this.installedReceiver == null || !this.registerDownloadReceiver) {
            return;
        }
        JdSdk.getInstance().getApplicationContext().unregisterReceiver(this.installedReceiver);
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x005e  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x007b  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0084  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x008d  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0098  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00a5 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:54:0x00a6  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x00bb  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x00c1  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x00c5  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00cb  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00d7  */
    @Override // com.jd.xbridge.base.IBridgePlugin
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public boolean execute(IBridgeWebView iBridgeWebView, String str, String str2, IBridgeCallback iBridgeCallback) {
        String str3;
        String str4;
        char c2;
        JSONObject jSONObject;
        String str5 = null;
        try {
            jSONObject = new JSONObject(str2);
            str4 = jSONObject.has("url") ? jSONObject.getString("url") : null;
        } catch (JSONException e2) {
            e = e2;
            str3 = null;
        }
        try {
            if (jSONObject.has("source")) {
                str5 = jSONObject.getString("source");
            }
        } catch (JSONException e3) {
            str3 = str4;
            e = e3;
            e.printStackTrace();
            str4 = str3;
            if (iBridgeWebView != null) {
                if (!this.registerDownloadReceiver) {
                    registerDownloadReceiver(str5);
                    this.registerDownloadReceiver = true;
                }
                if (this.downloadPreference == null) {
                }
            }
            str.hashCode();
            c2 = '\uffff';
            switch (str.hashCode()) {
                case -1367724422:
                    break;
                case 107944136:
                    break;
                case 1427818632:
                    break;
                case 1957569947:
                    break;
            }
            switch (c2) {
            }
        }
        if (iBridgeWebView != null && iBridgeWebView.getView() != null && iBridgeWebView.getView().getContext() != null) {
            if (!this.registerDownloadReceiver && (IExceptionHandler.DynamicExceptionData.TYPE_DOWNLOAD.equals(str) || "install".equals(str))) {
                registerDownloadReceiver(str5);
                this.registerDownloadReceiver = true;
            }
            if (this.downloadPreference == null) {
                this.downloadPreference = SharedPreferenceUtils.createPreference(iBridgeWebView.getView().getContext(), "JDWebViewDownload");
            }
        }
        str.hashCode();
        c2 = '\uffff';
        switch (str.hashCode()) {
            case -1367724422:
                if (str.equals("cancel")) {
                    c2 = 0;
                    break;
                }
                break;
            case 107944136:
                if (str.equals(SearchIntents.EXTRA_QUERY)) {
                    c2 = 1;
                    break;
                }
                break;
            case 1427818632:
                if (str.equals(IExceptionHandler.DynamicExceptionData.TYPE_DOWNLOAD)) {
                    c2 = 2;
                    break;
                }
                break;
            case 1957569947:
                if (str.equals("install")) {
                    c2 = 3;
                    break;
                }
                break;
        }
        switch (c2) {
            case 0:
                cancelDownload(iBridgeCallback);
                return true;
            case 1:
                iBridgeCallback.onSuccess(Boolean.valueOf(needDownLoad(str5, str4)));
                return true;
            case 2:
                if (!TextUtils.isEmpty(str5) && !TextUtils.isEmpty(str4)) {
                    downloadApk(str5, str4, iBridgeCallback);
                    return true;
                }
                iBridgeCallback.onError("action: download, params error");
                if (TextUtils.isEmpty(str5)) {
                    openSystemInstallView(str5, iBridgeCallback);
                    return true;
                }
                iBridgeCallback.onError("action: install, params error, source is null");
                cancelDownload(iBridgeCallback);
                return true;
            case 3:
                if (TextUtils.isEmpty(str5)) {
                }
                break;
            default:
                return false;
        }
    }
}
