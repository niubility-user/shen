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
import com.jd.framework.network.filedown.JDFileService;
import com.jd.libs.hybrid.base.util.Log;
import com.jd.libs.hybrid.base.util.SharedPreferenceUtils;
import com.jd.xbridge.base.IBridgeCallback;
import com.jd.xbridge.base.IBridgePlugin;
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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean execute(com.jd.xbridge.base.IBridgeWebView r8, java.lang.String r9, java.lang.String r10, com.jd.xbridge.base.IBridgeCallback r11) {
        /*
            r7 = this;
            java.lang.String r0 = "source"
            java.lang.String r1 = "url"
            r2 = 0
            org.json.JSONObject r3 = new org.json.JSONObject     // Catch: org.json.JSONException -> L28
            r3.<init>(r10)     // Catch: org.json.JSONException -> L28
            boolean r10 = r3.has(r1)     // Catch: org.json.JSONException -> L28
            if (r10 == 0) goto L16
            java.lang.String r10 = r3.getString(r1)     // Catch: org.json.JSONException -> L28
            goto L17
        L16:
            r10 = r2
        L17:
            boolean r1 = r3.has(r0)     // Catch: org.json.JSONException -> L23
            if (r1 == 0) goto L2e
            java.lang.String r0 = r3.getString(r0)     // Catch: org.json.JSONException -> L23
            r2 = r0
            goto L2e
        L23:
            r0 = move-exception
            r6 = r0
            r0 = r10
            r10 = r6
            goto L2a
        L28:
            r10 = move-exception
            r0 = r2
        L2a:
            r10.printStackTrace()
            r10 = r0
        L2e:
            java.lang.String r0 = "install"
            java.lang.String r1 = "download"
            r3 = 1
            if (r8 == 0) goto L6e
            android.view.View r4 = r8.getView()
            if (r4 == 0) goto L6e
            android.view.View r4 = r8.getView()
            android.content.Context r4 = r4.getContext()
            if (r4 == 0) goto L6e
            boolean r4 = r7.registerDownloadReceiver
            if (r4 != 0) goto L5a
            boolean r4 = r1.equals(r9)
            if (r4 != 0) goto L55
            boolean r4 = r0.equals(r9)
            if (r4 == 0) goto L5a
        L55:
            r7.registerDownloadReceiver(r2)
            r7.registerDownloadReceiver = r3
        L5a:
            android.content.SharedPreferences r4 = r7.downloadPreference
            if (r4 != 0) goto L6e
            android.view.View r8 = r8.getView()
            android.content.Context r8 = r8.getContext()
            java.lang.String r4 = "JDWebViewDownload"
            android.content.SharedPreferences r8 = com.jd.libs.hybrid.base.util.SharedPreferenceUtils.createPreference(r8, r4)
            r7.downloadPreference = r8
        L6e:
            r9.hashCode()
            r8 = -1
            int r4 = r9.hashCode()
            r5 = 0
            switch(r4) {
                case -1367724422: goto L98;
                case 107944136: goto L8d;
                case 1427818632: goto L84;
                case 1957569947: goto L7b;
                default: goto L7a;
            }
        L7a:
            goto La2
        L7b:
            boolean r9 = r9.equals(r0)
            if (r9 != 0) goto L82
            goto La2
        L82:
            r8 = 3
            goto La2
        L84:
            boolean r9 = r9.equals(r1)
            if (r9 != 0) goto L8b
            goto La2
        L8b:
            r8 = 2
            goto La2
        L8d:
            java.lang.String r0 = "query"
            boolean r9 = r9.equals(r0)
            if (r9 != 0) goto L96
            goto La2
        L96:
            r8 = 1
            goto La2
        L98:
            java.lang.String r0 = "cancel"
            boolean r9 = r9.equals(r0)
            if (r9 != 0) goto La1
            goto La2
        La1:
            r8 = 0
        La2:
            switch(r8) {
                case 0: goto Ld7;
                case 1: goto Lcb;
                case 2: goto La6;
                case 3: goto Lbb;
                default: goto La5;
            }
        La5:
            return r5
        La6:
            boolean r8 = android.text.TextUtils.isEmpty(r2)
            if (r8 != 0) goto Lb6
            boolean r8 = android.text.TextUtils.isEmpty(r10)
            if (r8 != 0) goto Lb6
            r7.downloadApk(r2, r10, r11)
            return r3
        Lb6:
            java.lang.String r8 = "action: download, params error"
            r11.onError(r8)
        Lbb:
            boolean r8 = android.text.TextUtils.isEmpty(r2)
            if (r8 != 0) goto Lc5
            r7.openSystemInstallView(r2, r11)
            return r3
        Lc5:
            java.lang.String r8 = "action: install, params error, source is null"
            r11.onError(r8)
            goto Ld7
        Lcb:
            boolean r8 = r7.needDownLoad(r2, r10)
            java.lang.Boolean r8 = java.lang.Boolean.valueOf(r8)
            r11.onSuccess(r8)
            return r3
        Ld7:
            r7.cancelDownload(r11)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.xbridge.WebViewDownloadPlugin.execute(com.jd.xbridge.base.IBridgeWebView, java.lang.String, java.lang.String, com.jd.xbridge.base.IBridgeCallback):boolean");
    }
}
