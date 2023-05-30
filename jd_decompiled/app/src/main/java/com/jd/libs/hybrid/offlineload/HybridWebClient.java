package com.jd.libs.hybrid.offlineload;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import cn.com.union.fido.common.MIMEType;
import com.google.common.net.HttpHeaders;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessMagicHeadPicInfoEntity;
import com.jd.libs.hybrid.base.HybridSettings;
import com.jd.libs.hybrid.base.HybridWebView;
import com.jd.libs.hybrid.base.util.ExceptionUtils;
import com.jd.libs.hybrid.base.util.HybridUrlUtils;
import com.jd.libs.hybrid.base.util.Log;
import com.jd.libs.hybrid.base.util.PerformanceUtils;
import com.jd.libs.hybrid.offlineload.entity.CommonFile;
import com.jd.libs.hybrid.offlineload.entity.LocalFileEntity;
import com.jd.libs.hybrid.offlineload.entity.LocalFileType;
import com.jd.libs.hybrid.offlineload.entity.OfflineFiles;
import com.jd.libs.hybrid.offlineload.temp.CommonResEngine;
import com.jd.libs.hybrid.offlineload.temp.OfflineSwitchSetting;
import com.jd.libs.hybrid.offlineload.utils.OfflineExceptionUtils;
import com.jd.libs.hybrid.offlineload.utils.OfflineFileHelper;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes16.dex */
public class HybridWebClient {
    private final String a;

    /* renamed from: c */
    private volatile int f5904c;
    private volatile List<CommonFile> d;

    /* renamed from: e */
    private volatile OfflineFiles f5905e;

    /* renamed from: f */
    private volatile OfflineFiles f5906f;

    /* renamed from: j */
    private volatile Uri f5910j;
    private volatile HybridWebView b = null;

    /* renamed from: g */
    private volatile boolean f5907g = false;

    /* renamed from: h */
    private volatile boolean f5908h = false;

    /* renamed from: i */
    private OfflineCallback f5909i = null;

    /* renamed from: k */
    private volatile boolean f5911k = false;

    /* renamed from: l */
    private volatile boolean f5912l = false;

    /* renamed from: m */
    private final AtomicBoolean f5913m = new AtomicBoolean(false);

    /* renamed from: n */
    private final AtomicBoolean f5914n = new AtomicBoolean(true);
    private final AtomicBoolean o = new AtomicBoolean(false);
    private volatile String p = null;
    private volatile String q = null;

    /* renamed from: com.jd.libs.hybrid.offlineload.HybridWebClient$2 */
    /* loaded from: classes16.dex */
    class AnonymousClass2 implements Runnable {

        /* renamed from: g */
        final /* synthetic */ HybridWebView f5917g;

        /* renamed from: h */
        final /* synthetic */ int f5918h;

        @Override // java.lang.Runnable
        public void run() {
            if (this.f5917g != null) {
                Log.d("HybridWebClient", "evaluateJavascript-> " + ("javascript:window.HYBRID_BINGO=" + this.f5918h + ";"));
                this.f5917g.evaluateJavascript("javascript:window.HYBRID_BINGO=" + this.f5918h + ";", null);
            }
        }
    }

    public HybridWebClient(String str) {
        this.a = str;
        com.jd.libs.xdog.b.b(XDogListener.class);
        if (!HybridSettings.isInited()) {
        }
    }

    private boolean a(Uri uri, Uri uri2, boolean z) {
        if (uri != null) {
            if (z) {
                return HybridUrlUtils.isRegexpMatched(uri.toString(), uri2.toString());
            }
            return HybridUrlUtils.uriMatchHostPath(uri, uri2);
        }
        return false;
    }

    @RequiresApi(api = 21)
    private WebResourceResponse b(LocalFileEntity localFileEntity) throws FileNotFoundException {
        Map<String, String> map = localFileEntity.header;
        String str = map != null ? map.get(HttpHeaders.CONTENT_TYPE) : "";
        boolean isEmpty = TextUtils.isEmpty(str);
        String str2 = MIMEType.MIME_TYPE_HTML;
        String str3 = null;
        if (!isEmpty) {
            String[] split = str.split(";");
            if (split.length > 1) {
                str = split[0];
                if (split[1].contains("charset=")) {
                    str3 = split[1].trim().replace("charset=", "");
                }
            }
            str2 = str;
        } else {
            String str4 = localFileEntity.type;
            if (str4 != null) {
                if (str4.equalsIgnoreCase("script")) {
                    str2 = "text/txt";
                } else if (localFileEntity.type.equalsIgnoreCase("stylesheet")) {
                    str2 = "text/css";
                } else if (localFileEntity.type.equalsIgnoreCase("image")) {
                    str2 = "image/*";
                } else {
                    localFileEntity.type.equalsIgnoreCase("html");
                }
            }
        }
        InputStream inputStream = localFileEntity.inputStream;
        if (inputStream == null) {
            inputStream = new FileInputStream(localFileEntity.fileName);
        }
        WebResourceResponse webResourceResponse = new WebResourceResponse(str2, str3, inputStream);
        if (localFileEntity.header == null) {
            localFileEntity.header = new HashMap(1);
        }
        localFileEntity.header.put("X-Cache", "jd");
        webResourceResponse.setResponseHeaders(localFileEntity.header);
        return webResourceResponse;
    }

    private boolean c(String str) {
        if (!OfflineFileHelper.removeFileInUsingState(str) || OfflineFileHelper.isFileInUsingState(str)) {
            return false;
        }
        return OfflineFileHelper.deleteOldFiles(String.valueOf(str.hashCode()));
    }

    private void d() {
        if (c(this.p)) {
            OfflineFiles offlineFiles = this.f5905e;
            StringBuilder sb = new StringBuilder();
            sb.append("[Web-Match] WebView reload online page, deleted old files, id: ");
            sb.append(offlineFiles != null ? offlineFiles.getAppId() : "");
            Log.d("HybridWebClient", sb.toString());
        }
        if (c(this.q)) {
            OfflineFiles offlineFiles2 = this.f5906f;
            StringBuilder sb2 = new StringBuilder();
            sb2.append("[Web-Match] WebView reload online page, deleted old [shared] files, id: ");
            sb2.append(offlineFiles2 != null ? offlineFiles2.getAppId() : "");
            Log.d("HybridWebClient", sb2.toString());
        }
        if (this.b != null) {
            p(this.b, new Runnable() { // from class: com.jd.libs.hybrid.offlineload.a
                {
                    HybridWebClient.this = this;
                }

                @Override // java.lang.Runnable
                public final void run() {
                    HybridWebClient.this.o();
                }
            });
        }
    }

    private LocalFileEntity e(@NonNull Uri uri) {
        String buildInGlobalFileName = CommonResEngine.getBuildInGlobalFileName(uri);
        if (TextUtils.isEmpty(buildInGlobalFileName)) {
            return null;
        }
        LocalFileEntity localFileEntity = new LocalFileEntity();
        localFileEntity.url = uri.toString();
        localFileEntity.fileName = buildInGlobalFileName;
        localFileEntity.configType = LocalFileType.FILE_TYPE_GLOBAL_BUILD_IN;
        return localFileEntity;
    }

    private LocalFileEntity f(Uri uri, OfflineFiles offlineFiles, boolean z) {
        if (offlineFiles == null) {
            return null;
        }
        LocalFileEntity j2 = j(uri, offlineFiles, z && offlineFiles.isCanPreloadHtml() && !TextUtils.isEmpty(offlineFiles.getPreloadHtmlUrl()));
        if (j2 != null) {
            j2.configType = LocalFileType.FILE_TYPE_PKG;
        }
        return j2;
    }

    private LocalFileEntity g(Uri uri, OfflineFiles offlineFiles, boolean z) {
        if (offlineFiles == null) {
            return null;
        }
        LocalFileEntity j2 = j(uri, offlineFiles, z && offlineFiles.isCanPreloadHtml() && !TextUtils.isEmpty(offlineFiles.getPreloadHtmlUrl()));
        if (j2 != null) {
            j2.configType = LocalFileType.FILE_TYPE_PKG_SHARED;
        }
        return j2;
    }

    private LocalFileEntity h(@NonNull Uri uri) {
        boolean equalsIgnoreCase;
        List<CommonFile> list = this.d;
        if (list != null && !list.isEmpty()) {
            for (CommonFile commonFile : list) {
                Uri parse = Uri.parse(commonFile.getUrl());
                String url = commonFile.getUrl();
                String uri2 = uri.toString();
                if (!TextUtils.isEmpty(parse.getScheme())) {
                    url = url.substring(parse.getScheme().length());
                }
                if (!TextUtils.isEmpty(uri.getScheme())) {
                    uri2 = uri2.substring(uri.getScheme().length());
                }
                if (!OfflineSwitchSetting.TYPE_4_PIC_COMPRESS_OFF) {
                    equalsIgnoreCase = url.split("!")[0].equalsIgnoreCase(uri2.split("!")[0]);
                } else {
                    equalsIgnoreCase = url.equalsIgnoreCase(uri2);
                }
                if (equalsIgnoreCase) {
                    LocalFileEntity localFileEntity = new LocalFileEntity();
                    localFileEntity.url = uri.toString();
                    localFileEntity.fileName = commonFile.getFilePath();
                    localFileEntity.header = commonFile.getHeaderParams();
                    localFileEntity.type = "";
                    if (!TextUtils.isEmpty(localFileEntity.fileName)) {
                        if (localFileEntity.fileName.endsWith(".js")) {
                            localFileEntity.type = "script";
                        } else if (localFileEntity.fileName.endsWith(".css")) {
                            localFileEntity.type = "stylesheet";
                        } else if (!localFileEntity.fileName.endsWith(WareBusinessMagicHeadPicInfoEntity.IMAGE_TYPE_GIF) && !localFileEntity.fileName.endsWith("png") && !localFileEntity.fileName.endsWith("jpg") && !localFileEntity.fileName.endsWith("jpeg")) {
                            if (localFileEntity.fileName.endsWith("htm") || localFileEntity.fileName.endsWith("html")) {
                                localFileEntity.type = "html";
                            }
                        } else {
                            localFileEntity.type = "image";
                        }
                    }
                    localFileEntity.configType = LocalFileType.FILE_TYPE_GLOBAL;
                    return localFileEntity;
                }
            }
        }
        return null;
    }

    @RequiresApi(api = 21)
    private String i(LocalFileEntity localFileEntity, WebResourceResponse webResourceResponse) {
        if (webResourceResponse == null) {
            return "";
        }
        try {
            String mimeType = webResourceResponse.getMimeType();
            String encoding = webResourceResponse.getEncoding();
            JSONObject jSONObject = new JSONObject();
            if (mimeType == null) {
                mimeType = "";
            }
            jSONObject.put("mimeType", mimeType);
            jSONObject.put("encoding", encoding != null ? encoding : "");
            jSONObject.put("header", webResourceResponse.getResponseHeaders());
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("response", jSONObject);
            jSONObject2.put("localFile", localFileEntity.toJson());
            jSONObject2.put("webViewId", this.f5904c);
            return jSONObject2.toString();
        } catch (Exception e2) {
            Log.e("HybridWebClient", e2);
            return "Error occurs when getting response's json string: " + e2.getMessage();
        }
    }

    private LocalFileEntity j(@NonNull Uri uri, OfflineFiles offlineFiles, boolean z) {
        LocalFileEntity localFileEntity;
        JSONObject jSONObject;
        if (offlineFiles == null) {
            return null;
        }
        if (this.f5910j == null) {
            l(offlineFiles);
        }
        if (z && a(this.f5910j, uri, offlineFiles.isRegexpUrl()) && HybridUrlUtils.uriMatchHostPath(Uri.parse(offlineFiles.getPreloadHtmlUrl()), uri)) {
            if (Log.isDebug()) {
                Log.xLogDForDev("HybridWebClient", "\u5f00\u542f\u5e76\u6267\u884c\u4e86\u9884\u4e0b\u8f7dhtml\uff0c\u53bb\u83b7\u53d6\u9884\u4e0b\u8f7d\u7684html");
            }
            Object preloadHtml = offlineFiles.getPreloadHtml();
            if (preloadHtml instanceof InputStream) {
                localFileEntity = new LocalFileEntity();
                localFileEntity.inputStream = (InputStream) preloadHtml;
                localFileEntity.funcType = 1;
            } else {
                if (preloadHtml instanceof String) {
                    String str = (String) preloadHtml;
                    if (!TextUtils.isEmpty(str)) {
                        localFileEntity = new LocalFileEntity();
                        localFileEntity.fileName = str;
                        localFileEntity.funcType = 1;
                    }
                }
                localFileEntity = null;
            }
            int preloadHtmlState = offlineFiles.getPreloadHtmlState();
            long preloadHtmlDownloadStart = offlineFiles.getPreloadHtmlDownloadStart();
            long preloadHtmlDownloadEnd = offlineFiles.getPreloadHtmlDownloadEnd();
            try {
                jSONObject = offlineFiles.getPreloadHtmlInfo();
            } catch (JSONException e2) {
                e2.printStackTrace();
                jSONObject = null;
            }
            OfflineCallback offlineCallback = this.f5909i;
            if (offlineCallback != null) {
                offlineCallback.onFetchPreDownloadFile(preloadHtmlState, preloadHtmlDownloadStart, preloadHtmlDownloadEnd, jSONObject);
            }
            PerformanceUtils.onHtmlPreDownloadChange(this.b, offlineFiles.getAppId(), preloadHtmlState, jSONObject);
            if (localFileEntity != null) {
                localFileEntity.url = uri.toString();
                localFileEntity.type = "html";
                return localFileEntity;
            }
        }
        String scheme = uri.getScheme();
        if ((TextUtils.isEmpty(scheme) || scheme.startsWith("http")) && !TextUtils.isEmpty(uri.getHost())) {
            Map<String, LocalFileEntity> localFileMap = offlineFiles.getLocalFileMap();
            if (!OfflineSwitchSetting.TYPE_4_PIC_COMPRESS_OFF && uri.toString().contains("!")) {
                uri = Uri.parse(uri.toString().split("!")[0]);
            }
            LocalFileEntity localFileEntity2 = localFileMap != null ? localFileMap.get(HybridUrlUtils.getHostPath(uri)) : null;
            if (localFileEntity2 != null && !TextUtils.isEmpty(localFileEntity2.fileName)) {
                String fileRootPath = offlineFiles.getFileRootPath();
                if (!localFileEntity2.fileName.startsWith(fileRootPath)) {
                    String str2 = File.separator;
                    if (fileRootPath.endsWith(str2)) {
                        localFileEntity2.fileName = fileRootPath + localFileEntity2.fileName;
                    } else {
                        localFileEntity2.fileName = fileRootPath + str2 + localFileEntity2.fileName;
                    }
                }
            }
            return localFileEntity2;
        }
        return null;
    }

    @RequiresApi(api = 21)
    private LocalFileEntity k(Uri uri, WebResourceRequest webResourceRequest) {
        OfflineFiles offlineFiles = this.f5905e;
        OfflineFiles offlineFiles2 = this.f5906f;
        if (offlineFiles == null && offlineFiles2 == null) {
            return null;
        }
        if (offlineFiles2 != null || offlineFiles.isAvailable()) {
            if (offlineFiles != null || offlineFiles2.isAvailable()) {
                if (offlineFiles == null || offlineFiles2 == null || offlineFiles.isAvailable() || offlineFiles2.isAvailable()) {
                    Map<String, LocalFileEntity> localFileMap = offlineFiles != null ? offlineFiles.getLocalFileMap() : null;
                    Map<String, LocalFileEntity> localFileMap2 = offlineFiles2 != null ? offlineFiles2.getLocalFileMap() : null;
                    if ((localFileMap != null && !localFileMap.isEmpty()) || (localFileMap2 != null && !localFileMap2.isEmpty())) {
                        boolean isForMainFrame = webResourceRequest.isForMainFrame();
                        if (isForMainFrame) {
                            this.f5914n.set(true);
                        }
                        LocalFileEntity f2 = f(uri, offlineFiles, isForMainFrame);
                        if (f2 == null) {
                            f2 = g(uri, offlineFiles2, isForMainFrame);
                        }
                        if (f2 == null) {
                            f2 = h(uri);
                        }
                        return f2 == null ? e(uri) : f2;
                    }
                    if (Log.isDebug()) {
                        Log.e("HybridWebClient", "[Web-Match] Error: Offline files are available but file map is empty.");
                    }
                    return null;
                }
                return null;
            }
            return null;
        }
        return null;
    }

    private void l(@NonNull OfflineFiles offlineFiles) {
        String originHtmlPath = offlineFiles.getOriginHtmlPath();
        this.f5910j = !TextUtils.isEmpty(originHtmlPath) ? Uri.parse(originHtmlPath) : null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:200:0x00dd A[Catch: Exception -> 0x0283, TRY_ENTER, TRY_LEAVE, TryCatch #5 {Exception -> 0x0283, blocks: (B:146:0x0029, B:149:0x0037, B:151:0x004f, B:153:0x0055, B:168:0x0075, B:170:0x007b, B:173:0x0089, B:206:0x00ed, B:177:0x0094, B:180:0x00a5, B:182:0x00a9, B:185:0x00b9, B:187:0x00bf, B:190:0x00c8, B:192:0x00ce, B:200:0x00dd, B:154:0x0058, B:156:0x005c, B:157:0x005f, B:159:0x0063, B:163:0x006a, B:165:0x006e, B:166:0x0071), top: B:267:0x0029 }] */
    /* JADX WARN: Removed duplicated region for block: B:202:0x00e2  */
    @androidx.annotation.RequiresApi(api = 21)
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private android.webkit.WebResourceResponse m(com.jd.libs.hybrid.base.HybridWebView r20, android.webkit.WebResourceRequest r21) {
        /*
            Method dump skipped, instructions count: 666
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.libs.hybrid.offlineload.HybridWebClient.m(com.jd.libs.hybrid.base.HybridWebView, android.webkit.WebResourceRequest):android.webkit.WebResourceResponse");
    }

    /* renamed from: n */
    public /* synthetic */ void o() {
        Log.d("HybridWebClient", "Reload online web.");
        OfflineCallback offlineCallback = this.f5909i;
        if (offlineCallback != null) {
            offlineCallback.beforeReload();
        }
        OfflineFiles offlineFiles = this.f5905e;
        if (offlineFiles == null) {
            offlineFiles = this.f5906f;
        }
        PerformanceUtils.onWebReload(this.b, offlineFiles != null ? offlineFiles.getAppId() : "");
        if (this.f5911k) {
            Log.d("setConfigBingoForH5, from reload");
            q(this.b, offlineFiles != null ? 1 : 0, this.f5908h ? 1 : 0);
        }
        if (this.b == null || !this.f5913m.get()) {
            return;
        }
        this.b.reload();
    }

    private void p(HybridWebView hybridWebView, Runnable runnable) {
        if (runnable == null || hybridWebView == null) {
            return;
        }
        View view = hybridWebView.getView();
        Handler handler = view != null ? view.getHandler() : null;
        if (handler == null) {
            handler = new Handler(Looper.getMainLooper());
        }
        if (Looper.myLooper() == handler.getLooper()) {
            runnable.run();
        } else {
            handler.post(runnable);
        }
    }

    private void q(final HybridWebView hybridWebView, final int i2, final int i3) {
        p(hybridWebView, new Runnable(this) { // from class: com.jd.libs.hybrid.offlineload.HybridWebClient.3
            @Override // java.lang.Runnable
            public void run() {
                if (hybridWebView != null) {
                    String format = String.format(Locale.getDefault(), "javascript:window.HYBRID_CONFIG=%d;window.HYBRID_BINGO=%d;", Integer.valueOf(i2), Integer.valueOf(i3));
                    Log.d("HybridWebClient", "evaluateJavascript-> " + format);
                    hybridWebView.evaluateJavascript(format, null);
                }
            }
        });
    }

    private void r(final HybridWebView hybridWebView, final int i2) {
        p(hybridWebView, new Runnable(this) { // from class: com.jd.libs.hybrid.offlineload.HybridWebClient.1
            @Override // java.lang.Runnable
            public void run() {
                if (hybridWebView != null) {
                    String str = "javascript:window.HYBRID_CONFIG=" + i2 + ";";
                    Log.d("HybridWebClient", "evaluateJavascript-> " + str);
                    hybridWebView.evaluateJavascript(str, null);
                }
            }
        });
    }

    public void destroy() {
        Log.d("HybridWebClient", "Calling destroy for HybridWebClient.");
        this.f5907g = true;
        this.b = null;
        this.d = null;
        OfflineFiles offlineFiles = this.f5905e;
        if (c(this.p)) {
            StringBuilder sb = new StringBuilder();
            sb.append("[Web-Match] WebView closing, deleted old files, id: ");
            sb.append(offlineFiles != null ? offlineFiles.getAppId() : "");
            Log.d("HybridWebClient", sb.toString());
        }
        if (offlineFiles != null) {
            offlineFiles.destroy();
            this.f5905e = null;
        }
        OfflineFiles offlineFiles2 = this.f5906f;
        if (c(this.q)) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("[Web-Match] WebView closing, deleted old [shared] files, id: ");
            sb2.append(offlineFiles != null ? offlineFiles.getAppId() : "");
            Log.d("HybridWebClient", sb2.toString());
        }
        if (offlineFiles2 != null) {
            offlineFiles2.destroy();
            this.f5906f = null;
        }
    }

    public OfflineFiles getOfflineFiles() {
        return this.f5905e;
    }

    public OfflineFiles getSharedFiles() {
        return this.f5906f;
    }

    public void onCommonConfig(List<CommonFile> list) {
        if (this.f5907g) {
            return;
        }
        this.d = list;
    }

    public void onConfig(OfflineFiles offlineFiles) {
        if (this.f5907g) {
            return;
        }
        this.f5905e = null;
        if (offlineFiles == null) {
            Log.w("HybridWebClient", "[Web-Match] Config of url(" + this.a + ") CANNOT found.");
            return;
        }
        this.f5905e = offlineFiles;
        if (Log.isDebug()) {
            String offlineFiles2 = offlineFiles.toString();
            Log.w("HybridWebClient", "[Web-Match] Found a config of url(" + this.a + "). Local files info: " + offlineFiles2);
            Log.xLogD("HybridWebClient", "\u79bb\u7ebf\u5305\uff1a\u6210\u529f\u627e\u5230\u79bb\u7ebf\u5305\u914d\u7f6e(id:" + offlineFiles.getAppId() + ")\uff0cURL\uff1a" + this.a + "\uff0c\u672c\u5730\u914d\u7f6e\uff1a", offlineFiles2);
        }
    }

    public void onFilesAvailable() {
        OfflineFiles offlineFiles;
        if (this.f5907g || (offlineFiles = this.f5905e) == null) {
            return;
        }
        if (offlineFiles.isAvailable()) {
            if (Log.isDebug()) {
                String offlineFiles2 = offlineFiles.toString();
                Log.w("HybridWebClient", "[Web-Match] Id(" + offlineFiles.getAppId() + ")'s offline local files are ready. Info: " + offlineFiles2);
                StringBuilder sb = new StringBuilder();
                sb.append("\u79bb\u7ebf\u5305\uff1a\u79bb\u7ebf\u5305(id:");
                sb.append(offlineFiles.getAppId());
                sb.append(")\u7684\u79bb\u7ebf\u6587\u4ef6\u5df2\u53ef\u7528\uff0c\u672c\u5730\u914d\u7f6e\uff1a");
                Log.xLogD("HybridWebClient", sb.toString(), offlineFiles2);
                XDogListener.xLocalFileListStr(offlineFiles);
            }
            String fileRootPath = offlineFiles.getFileRootPath();
            this.p = fileRootPath;
            OfflineFileHelper.addFileInUsingState(fileRootPath);
        } else if (Log.isDebug()) {
            String offlineFiles3 = offlineFiles.toString();
            Log.w("HybridWebClient", "[Web-Match] Id(" + offlineFiles.getAppId() + ")'s offline local files are NOT ready yet. Info: " + offlineFiles3);
            StringBuilder sb2 = new StringBuilder();
            sb2.append("\u79bb\u7ebf\u5305\uff1a\u79bb\u7ebf\u5305(id:");
            sb2.append(offlineFiles.getAppId());
            sb2.append(")\u7684\u79bb\u7ebf\u6587\u4ef6\u6682\u672a\u53ef\u7528\uff0c\u672c\u5730\u914d\u7f6e\uff1a");
            Log.xLogD("HybridWebClient", sb2.toString(), offlineFiles3);
        }
    }

    public void onLatestConfig(OfflineFiles offlineFiles, boolean z, boolean z2) {
        if (this.o.getAndSet(true) || this.f5907g) {
            return;
        }
        OfflineFiles offlineFiles2 = this.f5905e;
        if (Log.isDebug()) {
            String str = "[Web-Match] ConfigCallback for fetching latest (id:" + offlineFiles2.getAppId() + ")";
            if (offlineFiles == null) {
                Log.d("HybridWebClient", str + ", no new local file info");
            } else {
                Log.d("HybridWebClient", str + ", new local file info = " + offlineFiles);
            }
        }
        if (z2) {
            this.f5910j = null;
            this.f5908h = false;
            this.d = null;
            if (this.f5906f != null) {
                this.f5906f.setAvailable(false);
            }
            this.f5905e = offlineFiles;
            if (offlineFiles2 != null) {
                offlineFiles2.destroy();
            }
            d();
        }
    }

    public void onPageFinished(HybridWebView hybridWebView, String str) {
        this.f5912l = false;
    }

    public void onPageStarted(HybridWebView hybridWebView, String str, Bitmap bitmap) {
        this.f5911k = true;
        if (this.f5912l) {
            return;
        }
        OfflineFiles offlineFiles = this.f5905e;
        if (offlineFiles == null) {
            offlineFiles = this.f5906f;
        }
        if (offlineFiles == null) {
            return;
        }
        try {
            if (this.f5910j == null) {
                l(offlineFiles);
            }
            if (a(this.f5910j, Uri.parse(str), offlineFiles.isRegexpUrl())) {
                this.f5912l = true;
                if (this.f5908h) {
                    Log.d("setConfigBingoForH5, from pagestart");
                    q(this.b, 1, 1);
                } else {
                    Log.d("setConfigForH5, from pagestart");
                    r(this.b, 1);
                }
                OfflineCallback offlineCallback = this.f5909i;
                if (offlineCallback != null) {
                    offlineCallback.onOfflinePageStarted(str);
                }
                PerformanceUtils.onMatchOffline(this.b, offlineFiles.getAppId());
            }
        } catch (Exception e2) {
            Log.e("HybridWebClient", e2);
            OfflineExceptionUtils.reportMatchError(OfflineExceptionUtils.ERR_MSG_CODE, "onPageStarted", offlineFiles.getAppId(), str, ExceptionUtils.getStackStringFromException(e2));
        }
    }

    public void onSharedConfig(OfflineFiles offlineFiles) {
        if (this.f5907g) {
            return;
        }
        this.f5906f = null;
        if (offlineFiles == null) {
            Log.w("HybridWebClient", "[Web-Match](Shared) Config of url(" + this.a + ") CANNOT found.");
            return;
        }
        this.f5906f = offlineFiles;
        if (Log.isDebug()) {
            String offlineFiles2 = offlineFiles.toString();
            Log.w("HybridWebClient", "[Web-Match](Shared) Found a config of url(" + this.a + "). Local files info: " + offlineFiles2);
            Log.xLogD("HybridWebClient", "[\u516c\u5171]\u79bb\u7ebf\u5305\uff1a\u6210\u529f\u627e\u5230[\u516c\u5171]\u79bb\u7ebf\u5305\u914d\u7f6e(id:" + offlineFiles.getAppId() + ")\uff0cURL\uff1a" + this.a + "\uff0c\u672c\u5730\u914d\u7f6e\uff1a", offlineFiles2);
        }
    }

    public void onSharedFilesAvailable() {
        OfflineFiles offlineFiles;
        if (this.f5907g || (offlineFiles = this.f5906f) == null) {
            return;
        }
        if (offlineFiles.isAvailable()) {
            if (Log.isDebug()) {
                String offlineFiles2 = offlineFiles.toString();
                Log.w("HybridWebClient", "[Web-Match](Shared) Id(" + offlineFiles.getAppId() + ")'s offline local files are ready. Info: " + offlineFiles2);
                StringBuilder sb = new StringBuilder();
                sb.append("[\u516c\u5171]\u79bb\u7ebf\u5305\uff1a\u79bb\u7ebf\u5305(id:");
                sb.append(offlineFiles.getAppId());
                sb.append(")\u7684\u79bb\u7ebf\u6587\u4ef6\u5df2\u53ef\u7528\uff0c\u672c\u5730\u914d\u7f6e\uff1a");
                Log.xLogD("HybridWebClient", sb.toString(), offlineFiles2);
                XDogListener.xLocalFileListStr(offlineFiles);
            }
            String fileRootPath = offlineFiles.getFileRootPath();
            this.q = fileRootPath;
            OfflineFileHelper.addFileInUsingState(fileRootPath);
        } else if (Log.isDebug()) {
            String offlineFiles3 = offlineFiles.toString();
            Log.w("HybridWebClient", "[Web-Match](Shared) Id(" + offlineFiles.getAppId() + ")'s offline local files are NOT ready yet. Info: " + offlineFiles3);
            StringBuilder sb2 = new StringBuilder();
            sb2.append("[\u516c\u5171]\u79bb\u7ebf\u5305\uff1a\u79bb\u7ebf\u5305(id:");
            sb2.append(offlineFiles.getAppId());
            sb2.append(")\u7684\u79bb\u7ebf\u6587\u4ef6\u6682\u672a\u53ef\u7528\uff0c\u672c\u5730\u914d\u7f6e\uff1a");
            Log.xLogD("HybridWebClient", sb2.toString(), offlineFiles3);
        }
    }

    public void setCallback(OfflineCallback offlineCallback) {
        this.f5909i = offlineCallback;
    }

    @RequiresApi(api = 21)
    @CallSuper
    public WebResourceResponse shouldInterceptRequest(HybridWebView hybridWebView, WebResourceRequest webResourceRequest) {
        this.f5913m.set(true);
        if (webResourceRequest.isForMainFrame()) {
            this.f5908h = false;
        }
        WebResourceResponse m2 = m(hybridWebView, webResourceRequest);
        if (m2 == null && Log.isDebug()) {
            Log.xLogDForDev("HybridWebClient", "\u672a\u627e\u5230\u53ef\u66ff\u6362\u7ebf\u4e0a\u8d44\u6e90\u7684\u672c\u5730\u79bb\u7ebf\u6587\u4ef6\uff0c\u7ebf\u4e0aURL\uff1a" + webResourceRequest.getUrl());
        }
        return m2;
    }
}
