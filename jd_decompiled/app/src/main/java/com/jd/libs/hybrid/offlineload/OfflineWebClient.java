package com.jd.libs.hybrid.offlineload;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import com.google.common.net.HttpHeaders;
import com.jd.framework.json.JDJSON;
import com.jd.libs.hybrid.base.HybridSettings;
import com.jd.libs.hybrid.base.HybridWebView;
import com.jd.libs.hybrid.base.util.ExceptionUtils;
import com.jd.libs.hybrid.base.util.HybridUrlUtils;
import com.jd.libs.hybrid.base.util.Log;
import com.jd.libs.hybrid.base.util.PerformanceUtils;
import com.jd.libs.hybrid.offlineload.entity.CommonFile;
import com.jd.libs.hybrid.offlineload.entity.OfflineFiles;
import com.jd.libs.hybrid.offlineload.utils.OfflineExceptionUtils;
import com.jd.libs.hybrid.offlineload.utils.OfflineFileUtils;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

@Deprecated
/* loaded from: classes16.dex */
public class OfflineWebClient {
    private String a;

    /* renamed from: c */
    private volatile List<CommonFile> f5922c;
    private volatile OfflineFiles d;

    /* renamed from: l */
    private OnFileLoadListener f5930l;

    /* renamed from: m */
    private volatile Uri f5931m;

    /* renamed from: n */
    private volatile Uri f5932n;
    private volatile HybridWebView b = null;

    /* renamed from: e */
    private volatile boolean f5923e = false;

    /* renamed from: f */
    private volatile Handler f5924f = null;

    /* renamed from: g */
    private volatile Runnable f5925g = null;

    /* renamed from: h */
    private volatile boolean f5926h = false;

    /* renamed from: i */
    private int f5927i = HybridSettings.MAX_OFFLINE_FETCH_TIME;

    /* renamed from: j */
    private boolean f5928j = false;

    /* renamed from: k */
    private Callback f5929k = null;
    private volatile boolean o = false;
    private volatile boolean p = false;
    private final AtomicBoolean q = new AtomicBoolean(true);
    private volatile String r = null;

    /* renamed from: com.jd.libs.hybrid.offlineload.OfflineWebClient$4 */
    /* loaded from: classes16.dex */
    class AnonymousClass4 implements Runnable {

        /* renamed from: g */
        final /* synthetic */ HybridWebView f5938g;

        /* renamed from: h */
        final /* synthetic */ int f5939h;

        @Override // java.lang.Runnable
        public void run() {
            if (this.f5938g != null) {
                Log.d("OfflineWebClient", "evaluateJavascript-> " + ("javascript:window.HYBRID_BINGO=" + this.f5939h + ";"));
                this.f5938g.evaluateJavascript("javascript:window.HYBRID_BINGO=" + this.f5939h + ";", null);
            }
        }
    }

    /* loaded from: classes16.dex */
    public interface Callback {
        void beforeReload(String str);

        void onFetchPreDownloadFile(int i2, long j2, long j3);

        void onFileHitMainPage(String str, String str2, String str3);

        void onFirstOfflinePageStarted(String str, boolean z, boolean z2, String str2);
    }

    /* loaded from: classes16.dex */
    public interface OnFileLoadListener {
        void onFileLoad(@NonNull OfflineFiles offlineFiles);
    }

    public OfflineWebClient(String str) {
        if (HybridSettings.isInited()) {
            this.a = str;
        }
    }

    private boolean h(Uri uri, Uri uri2) {
        return HybridUrlUtils.uriMatchHostPath(uri, uri2);
    }

    private boolean i(Uri uri, Uri uri2, boolean z) {
        if (uri == null || uri2 == null) {
            return false;
        }
        if (z) {
            return HybridUrlUtils.isRegexpMatched(uri.toString(), uri2.toString());
        }
        return HybridUrlUtils.uriMatchHostPath(uri, uri2);
    }

    private void j(final String str) {
        if (OfflineFileUtils.removeFileInUsingState(this.r) && !OfflineFileUtils.isFileInUsingState(this.r)) {
            OfflineFiles offlineFiles = this.d;
            if (OfflineFileUtils.deleteOldFiles(String.valueOf(this.r.hashCode()))) {
                StringBuilder sb = new StringBuilder();
                sb.append("[Build-in] WebView reload online page, deleted build-in-app's old files, id: ");
                sb.append(offlineFiles != null ? offlineFiles.getAppId() : "");
                Log.d("OfflineWebClient", sb.toString());
            }
        }
        if (this.b != null) {
            Runnable runnable = new Runnable() { // from class: com.jd.libs.hybrid.offlineload.OfflineWebClient.2
                {
                    OfflineWebClient.this = this;
                }

                @Override // java.lang.Runnable
                public void run() {
                    Log.d("OfflineWebClient", "Reload for real web.");
                    if (OfflineWebClient.this.f5929k != null) {
                        OfflineWebClient.this.f5929k.beforeReload(str);
                    }
                    PerformanceUtils.onWebReload(OfflineWebClient.this.b, OfflineWebClient.this.d != null ? OfflineWebClient.this.d.getAppId() : "");
                    if (OfflineWebClient.this.o) {
                        Log.d("setConfigBingoForH5, from reload");
                        OfflineWebClient offlineWebClient = OfflineWebClient.this;
                        offlineWebClient.r(offlineWebClient.b, OfflineWebClient.this.d != null ? 1 : 0, OfflineWebClient.this.f5928j ? 1 : 0);
                    }
                    if (OfflineWebClient.this.b != null) {
                        OfflineWebClient.this.b.reload();
                    }
                }
            };
            Handler handler = this.b.getView().getHandler();
            if (Looper.myLooper() == (handler != null ? handler.getLooper() : Looper.getMainLooper())) {
                runnable.run();
            } else {
                this.b.getView().post(runnable);
            }
        }
    }

    private String k(Uri uri, @NonNull Map<String, String> map) {
        boolean equalsIgnoreCase;
        List<CommonFile> list = this.f5922c;
        if (uri != null && !TextUtils.isEmpty(uri.toString()) && list != null && !list.isEmpty()) {
            for (CommonFile commonFile : list) {
                Uri parse = Uri.parse(commonFile.getUrl());
                String url = commonFile.getUrl();
                String uri2 = uri.toString();
                if (("https".equals(parse.getScheme()) || "http".equals(parse.getScheme())) && ("https".equals(uri.getScheme()) || "http".equals(uri.getScheme()))) {
                    url = url.substring(parse.getScheme().length());
                    uri2 = uri2.substring(uri.getScheme().length());
                }
                String[] split = url.split("!");
                String[] split2 = uri2.split("!");
                if (commonFile.isCanMatchImg()) {
                    equalsIgnoreCase = split[0].equalsIgnoreCase(split2[0]);
                } else {
                    equalsIgnoreCase = url.equalsIgnoreCase(uri2);
                }
                if (equalsIgnoreCase) {
                    if (commonFile.getHeaderParams() != null) {
                        map.putAll(commonFile.getHeaderParams());
                        map.put("X-Cache", "jd");
                    }
                    return commonFile.getFilePath();
                }
            }
        }
        return null;
    }

    private String l(@NonNull Uri uri, @NonNull OfflineFiles offlineFiles) {
        boolean h2 = h(this.f5931m, uri);
        boolean i2 = i(this.f5932n, uri, offlineFiles.isSsrBiz());
        if (h2 || i2) {
            if (offlineFiles.isSsrBiz()) {
                if (offlineFiles.isCanPreloadHtml() && !TextUtils.isEmpty(offlineFiles.getPreloadHtmlUrl()) && HybridUrlUtils.uriMatchHostPath(uri, Uri.parse(offlineFiles.getPreloadHtmlUrl()))) {
                    if (Log.isDebug()) {
                        Log.xLogDForDev("OfflineWebClient", "\u5f00\u542f\u5e76\u6267\u884c\u4e86\u9884\u4e0b\u8f7dhtml\uff0c\u53bb\u83b7\u53d6\u9884\u4e0b\u8f7d\u7684html\u672c\u5730\u6587\u4ef6");
                    }
                    String preloadHtmlPath = offlineFiles.getPreloadHtmlPath();
                    if (this.f5929k != null) {
                        int preloadHtmlState = offlineFiles.getPreloadHtmlState();
                        this.f5929k.onFetchPreDownloadFile(preloadHtmlState, offlineFiles.getPreloadHtmlDownloadStart(), offlineFiles.getPreloadHtmlDownloadEnd());
                        PerformanceUtils.onHtmlPreDownloadChange(this.b, offlineFiles.getAppId(), preloadHtmlState, null);
                    }
                    return preloadHtmlPath;
                }
                return null;
            }
            return offlineFiles.getHtmlFile();
        }
        return null;
    }

    private void m(@NonNull OfflineFiles offlineFiles) {
        this.f5931m = !TextUtils.isEmpty(offlineFiles.getHtmlPath()) ? Uri.parse(offlineFiles.getHtmlPath()) : null;
        this.f5932n = TextUtils.isEmpty(offlineFiles.getOriginHtmlPath()) ? null : Uri.parse(offlineFiles.getOriginHtmlPath());
    }

    private boolean n(@NonNull Uri uri, @NonNull OfflineFiles offlineFiles) {
        if (offlineFiles.isSsrBiz()) {
            if (!TextUtils.isEmpty(offlineFiles.getStaticDir()) && HybridUrlUtils.isSSrUrl(uri.toString())) {
                return true;
            }
        } else if (!TextUtils.isEmpty(offlineFiles.getStaticPath()) && uri.toString().startsWith(offlineFiles.getStaticPath())) {
            return true;
        }
        return false;
    }

    /* JADX WARN: Code restructure failed: missing block: B:121:0x0072, code lost:
        r3 = r11.getMinFileVer();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized void o(boolean r9, boolean r10, com.jd.libs.hybrid.offlineload.entity.OfflineFiles r11) {
        /*
            Method dump skipped, instructions count: 727
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.libs.hybrid.offlineload.OfflineWebClient.o(boolean, boolean, com.jd.libs.hybrid.offlineload.entity.OfflineFiles):void");
    }

    private void p(String str, Map<String, String> map) {
        for (String str2 : map.keySet()) {
            if (!TextUtils.isEmpty(str2) && str2.equals(str) && !TextUtils.isEmpty(map.get(str2))) {
                map.remove(str2);
                return;
            }
        }
    }

    private void q(HybridWebView hybridWebView, Runnable runnable) {
        if (runnable == null || hybridWebView == null) {
            return;
        }
        Handler handler = hybridWebView.getView().getHandler();
        if (Looper.myLooper() == (handler != null ? handler.getLooper() : Looper.getMainLooper())) {
            runnable.run();
        } else {
            hybridWebView.getView().post(runnable);
        }
    }

    public void r(final HybridWebView hybridWebView, final int i2, final int i3) {
        q(hybridWebView, new Runnable(this) { // from class: com.jd.libs.hybrid.offlineload.OfflineWebClient.5
            @Override // java.lang.Runnable
            public void run() {
                if (hybridWebView != null) {
                    String format = String.format(Locale.getDefault(), "javascript:window.HYBRID_CONFIG=%d;window.HYBRID_BINGO=%d;", Integer.valueOf(i2), Integer.valueOf(i3));
                    Log.d("OfflineWebClient", "evaluateJavascript-> " + format);
                    hybridWebView.evaluateJavascript(format, null);
                }
            }
        });
    }

    private void s(final HybridWebView hybridWebView, final int i2) {
        q(hybridWebView, new Runnable(this) { // from class: com.jd.libs.hybrid.offlineload.OfflineWebClient.3
            @Override // java.lang.Runnable
            public void run() {
                if (hybridWebView != null) {
                    String str = "javascript:window.HYBRID_CONFIG=" + i2 + ";";
                    Log.d("OfflineWebClient", "evaluateJavascript-> " + str);
                    hybridWebView.evaluateJavascript(str, null);
                }
            }
        });
    }

    @RequiresApi(api = 21)
    private void t(OfflineWebRezResp offlineWebRezResp, Map<String, String> map) {
        if (map == null || map.isEmpty()) {
            return;
        }
        String str = map.get(HttpHeaders.CONTENT_TYPE);
        if (!TextUtils.isEmpty(str)) {
            offlineWebRezResp.setMimeType(str);
        }
        p(HttpHeaders.CONTENT_LENGTH, map);
        offlineWebRezResp.setResponseHeaders(map);
    }

    /* JADX WARN: Removed duplicated region for block: B:238:0x0167 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:244:0x0179 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:249:0x01c6 A[Catch: Exception -> 0x02b3, TryCatch #0 {Exception -> 0x02b3, blocks: (B:169:0x003b, B:171:0x0046, B:176:0x0050, B:177:0x0053, B:179:0x005a, B:193:0x00d3, B:196:0x00db, B:198:0x00e6, B:200:0x00ec, B:205:0x0100, B:236:0x0160, B:240:0x016b, B:251:0x01da, B:254:0x01fa, B:258:0x020b, B:262:0x023d, B:268:0x0249, B:269:0x0253, B:273:0x0266, B:279:0x0270, B:245:0x017b, B:247:0x01b6, B:249:0x01c6, B:250:0x01d1, B:246:0x0199, B:207:0x0105, B:211:0x0111, B:212:0x0115, B:214:0x011d, B:216:0x0125, B:218:0x012d, B:221:0x0136, B:223:0x013e, B:226:0x0147, B:229:0x0151, B:234:0x015c, B:281:0x027b, B:282:0x029b, B:180:0x0065, B:182:0x006b, B:184:0x0074, B:188:0x0085, B:187:0x0081, B:189:0x009e, B:191:0x00ca, B:174:0x004c), top: B:297:0x003b }] */
    /* JADX WARN: Removed duplicated region for block: B:254:0x01fa A[Catch: Exception -> 0x02b3, TRY_ENTER, TryCatch #0 {Exception -> 0x02b3, blocks: (B:169:0x003b, B:171:0x0046, B:176:0x0050, B:177:0x0053, B:179:0x005a, B:193:0x00d3, B:196:0x00db, B:198:0x00e6, B:200:0x00ec, B:205:0x0100, B:236:0x0160, B:240:0x016b, B:251:0x01da, B:254:0x01fa, B:258:0x020b, B:262:0x023d, B:268:0x0249, B:269:0x0253, B:273:0x0266, B:279:0x0270, B:245:0x017b, B:247:0x01b6, B:249:0x01c6, B:250:0x01d1, B:246:0x0199, B:207:0x0105, B:211:0x0111, B:212:0x0115, B:214:0x011d, B:216:0x0125, B:218:0x012d, B:221:0x0136, B:223:0x013e, B:226:0x0147, B:229:0x0151, B:234:0x015c, B:281:0x027b, B:282:0x029b, B:180:0x0065, B:182:0x006b, B:184:0x0074, B:188:0x0085, B:187:0x0081, B:189:0x009e, B:191:0x00ca, B:174:0x004c), top: B:297:0x003b }] */
    /* JADX WARN: Removed duplicated region for block: B:269:0x0253 A[Catch: Exception -> 0x02b3, TryCatch #0 {Exception -> 0x02b3, blocks: (B:169:0x003b, B:171:0x0046, B:176:0x0050, B:177:0x0053, B:179:0x005a, B:193:0x00d3, B:196:0x00db, B:198:0x00e6, B:200:0x00ec, B:205:0x0100, B:236:0x0160, B:240:0x016b, B:251:0x01da, B:254:0x01fa, B:258:0x020b, B:262:0x023d, B:268:0x0249, B:269:0x0253, B:273:0x0266, B:279:0x0270, B:245:0x017b, B:247:0x01b6, B:249:0x01c6, B:250:0x01d1, B:246:0x0199, B:207:0x0105, B:211:0x0111, B:212:0x0115, B:214:0x011d, B:216:0x0125, B:218:0x012d, B:221:0x0136, B:223:0x013e, B:226:0x0147, B:229:0x0151, B:234:0x015c, B:281:0x027b, B:282:0x029b, B:180:0x0065, B:182:0x006b, B:184:0x0074, B:188:0x0085, B:187:0x0081, B:189:0x009e, B:191:0x00ca, B:174:0x004c), top: B:297:0x003b }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private android.webkit.WebResourceResponse u(com.jd.libs.hybrid.base.HybridWebView r21, android.net.Uri r22) {
        /*
            Method dump skipped, instructions count: 726
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.libs.hybrid.offlineload.OfflineWebClient.u(com.jd.libs.hybrid.base.HybridWebView, android.net.Uri):android.webkit.WebResourceResponse");
    }

    public void destroy() {
        Log.d("OfflineWebClient", "Calling destroy for OfflineWebClient.");
        this.f5926h = true;
        this.b = null;
        this.f5923e = false;
        this.f5922c = null;
        OfflineFiles offlineFiles = this.d;
        if (OfflineFileUtils.removeFileInUsingState(this.r) && !OfflineFileUtils.isFileInUsingState(this.r) && OfflineFileUtils.deleteOldFiles(String.valueOf(this.r.hashCode()))) {
            StringBuilder sb = new StringBuilder();
            sb.append("[Build-in] WebView closing, deleted build-in-app's old files, id: ");
            sb.append(offlineFiles != null ? offlineFiles.getAppId() : "");
            Log.d("OfflineWebClient", sb.toString());
        }
        if (offlineFiles != null) {
            offlineFiles.destroy();
            this.d = null;
        }
        if (this.f5924f != null) {
            if (this.f5925g != null) {
                this.f5924f.removeCallbacks(this.f5925g);
                this.f5925g = null;
            }
            this.f5924f = null;
        }
    }

    public OfflineFiles getOfflineFiles() {
        return this.d;
    }

    public boolean hitMainPage() {
        return this.f5928j;
    }

    public void onCommonConfig(List<CommonFile> list) {
        if (this.f5926h) {
            return;
        }
        this.f5922c = list;
    }

    public void onConfig(OfflineFiles offlineFiles, boolean z) {
        if (this.f5926h) {
            return;
        }
        this.d = null;
        this.f5923e = false;
        if (offlineFiles == null) {
            Log.w("OfflineWebClient", "Offline config of entry url(" + this.a + ") CANNOT found in local database.");
            return;
        }
        this.d = offlineFiles;
        String str = "";
        if (Log.isDebug()) {
            StringBuilder sb = new StringBuilder();
            sb.append("\u79bb\u7ebf\u5305\uff1a\u6210\u529f\u627e\u5230\u79bb\u7ebf\u5305\u914d\u7f6e(id:");
            sb.append(offlineFiles.getAppId());
            sb.append(")\uff0c\u9879\u76ee\u7c7b\u578b\uff1a");
            sb.append(offlineFiles.getTypeString());
            sb.append("\uff0c\u672c\u5730\u6587\u4ef6");
            sb.append(offlineFiles.isAvailable() ? "" : "\u4e0d");
            sb.append("\u53ef\u7528\uff0cURL\uff1a");
            sb.append(this.a);
            sb.append("\uff0c\u672c\u5730\u914d\u7f6e\uff1a");
            Log.xLogD("OfflineWebClient", sb.toString(), JDJSON.toJSONString(offlineFiles));
        }
        if (offlineFiles.isAvailable()) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Offline config and local files of entry url(");
            sb2.append(this.a);
            sb2.append(") Found in ");
            sb2.append(offlineFiles.isBuildInBiz() ? "build-in " : "");
            sb2.append("local database.");
            if (Log.isDebug()) {
                str = " Info: " + JDJSON.toJSONString(offlineFiles);
            }
            sb2.append(str);
            Log.w("OfflineWebClient", sb2.toString());
            if (offlineFiles.isBuildInBiz()) {
                String fileRootPath = offlineFiles.getFileRootPath();
                this.r = fileRootPath;
                OfflineFileUtils.addFileInUsingState(fileRootPath);
            }
            if (z) {
                Log.d("OfflineWebClient", "Need to fetch the latest info from network");
                this.f5924f = new Handler(Looper.getMainLooper());
                this.f5925g = new Runnable() { // from class: com.jd.libs.hybrid.offlineload.OfflineWebClient.1
                    {
                        OfflineWebClient.this = this;
                    }

                    @Override // java.lang.Runnable
                    public void run() {
                        Log.d("OfflineWebClient", "Timer finished.");
                        OfflineWebClient.this.o(true, false, null);
                    }
                };
                Log.d("OfflineWebClient", "Start timer of " + this.f5927i + "ms.");
                this.f5923e = true;
                this.f5924f.postDelayed(this.f5925g, (long) this.f5927i);
            }
            OnFileLoadListener onFileLoadListener = this.f5930l;
            if (onFileLoadListener != null) {
                onFileLoadListener.onFileLoad(offlineFiles);
                return;
            }
            return;
        }
        StringBuilder sb3 = new StringBuilder();
        sb3.append("Offline local files is NOT available even though config of entry url(");
        sb3.append(this.a);
        sb3.append(") is found in ");
        sb3.append(offlineFiles.isBuildInBiz() ? "build-in " : "");
        sb3.append("local database.");
        if (Log.isDebug()) {
            str = " Info: " + JDJSON.toJSONString(offlineFiles);
        }
        sb3.append(str);
        Log.w("OfflineWebClient", sb3.toString());
    }

    public void onLatestConfig(OfflineFiles offlineFiles, boolean z) {
        if (this.f5926h) {
            return;
        }
        if (Log.isDebug()) {
            Log.d("OfflineWebClient", "ConfigCallback for fetching latest info of entry url(" + this.a + ") from network, Info = " + JDJSON.toJSONString(offlineFiles));
            if (offlineFiles != null) {
                Log.xLogD("OfflineWebClient", "\u6210\u529f\u83b7\u53d6\u9879\u76ee(id:" + offlineFiles.getAppId() + ")\u7684\u7ebf\u4e0a\u6700\u65b0\u914d\u7f6e\u6570\u636e\uff1a", JDJSON.toJSONString(offlineFiles));
            }
        }
        if (this.f5923e) {
            o(false, z, offlineFiles);
        }
    }

    public void onPageFinished(HybridWebView hybridWebView, String str) {
        this.p = false;
    }

    public void onPageStarted(HybridWebView hybridWebView, String str, Bitmap bitmap) {
        this.o = true;
        if (this.p) {
            return;
        }
        OfflineFiles offlineFiles = this.d;
        if (offlineFiles == null) {
            this.f5928j = false;
            return;
        }
        try {
            if ((!offlineFiles.isSsrBiz() && this.f5931m == null) || (offlineFiles.isSsrBiz() && this.f5932n == null)) {
                m(offlineFiles);
            }
            Uri parse = Uri.parse(str);
            boolean h2 = h(this.f5931m, parse);
            boolean i2 = i(this.f5932n, parse, offlineFiles.isSsrBiz());
            if (h2 || i2) {
                this.p = true;
                if (this.f5928j) {
                    Log.d("setConfigBingoForH5, from pagestart");
                    r(hybridWebView, 1, 1);
                } else {
                    Log.d("setConfigForH5, from pagestart");
                    s(hybridWebView, 1);
                }
                Callback callback = this.f5929k;
                if (callback != null) {
                    callback.onFirstOfflinePageStarted(str, true, this.f5928j, offlineFiles.getType());
                }
                PerformanceUtils.onMatchOffline(hybridWebView, offlineFiles.getAppId());
            }
        } catch (Exception e2) {
            Log.e("OfflineWebClient", e2);
            OfflineExceptionUtils.reportMatchError(OfflineExceptionUtils.ERR_MSG_CODE, "onPageStarted", offlineFiles != null ? offlineFiles.getAppId() : null, str, ExceptionUtils.getStackStringFromException(e2));
        }
    }

    public void setCallback(Callback callback) {
        this.f5929k = callback;
    }

    public void setOnFileLoadListener(OnFileLoadListener onFileLoadListener) {
        this.f5930l = onFileLoadListener;
    }

    public WebResourceResponse shouldInterceptRequest(HybridWebView hybridWebView, WebResourceRequest webResourceRequest) {
        this.b = hybridWebView;
        if (Build.VERSION.SDK_INT >= 21) {
            return u(hybridWebView, webResourceRequest.getUrl());
        }
        return null;
    }
}
