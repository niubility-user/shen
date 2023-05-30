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
import cn.com.union.fido.common.MIMEType;
import com.google.common.net.HttpHeaders;
import com.jd.framework.json.JDJSON;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessMagicHeadPicInfoEntity;
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
import com.tencent.smtt.sdk.ProxyConfig;
import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
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

    /* JADX WARN: Code restructure failed: missing block: B:215:0x0072, code lost:
        r3 = r11.getMinFileVer();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public synchronized void o(boolean z, boolean z2, OfflineFiles offlineFiles) {
        String str;
        if (this.f5923e) {
            this.f5923e = false;
            if (this.f5924f != null) {
                if (this.f5925g != null) {
                    Log.d("OfflineWebClient", "Cancel timer.");
                    this.f5924f.removeCallbacks(this.f5925g);
                    this.f5925g = null;
                }
                this.f5924f = null;
            }
            OfflineFiles offlineFiles2 = this.d;
            if (offlineFiles2 == null) {
                return;
            }
            if (!offlineFiles2.isBuildInBiz()) {
                if (offlineFiles != null) {
                    boolean z3 = true;
                    if (offlineFiles2.getFileVersion() != offlineFiles.getFileVersion()) {
                        Log.w("OfflineWebClient", "Latest info differs with cache, load URL for web on network.");
                        if (Log.isDebug()) {
                            Log.xLogD("OfflineWebClient", "\u9879\u76ee(id:" + offlineFiles.getAppId() + ")\u7ebf\u4e0a\u5b58\u5728\u66f4\u65b0\u79bb\u7ebf\u6587\u4ef6\u7248\u672c" + offlineFiles.getFileVersion() + "\uff0c\u5c06\u91cd\u65b0\u52a0\u8f7d\u7ebf\u4e0aH5\u3002");
                        }
                    } else if (offlineFiles.isAvailable()) {
                        z3 = false;
                    } else {
                        Log.w("OfflineWebClient", "Fail to fetch latest info, load URL for web on network.");
                        if (Log.isDebug()) {
                            Log.xLogD("OfflineWebClient", "\u83b7\u53d6\u9879\u76ee(id:" + offlineFiles2.getAppId() + ")\u7684\u6700\u65b0\u914d\u7f6e\u6570\u636e\u5931\u8d25\uff0c\u5c06\u91cd\u65b0\u52a0\u8f7d\u7ebf\u4e0aH5\u3002");
                        }
                    }
                    if (z3) {
                        this.f5931m = null;
                        this.f5932n = null;
                        offlineFiles2.destroy();
                        this.f5928j = false;
                        this.d = offlineFiles;
                        j(offlineFiles == null ? "0" : offlineFiles.getType());
                    } else {
                        Log.xLogD("OfflineWebClient", "\u9879\u76ee(id:" + offlineFiles2.getAppId() + ")\u7ebf\u4e0a\u79bb\u7ebf\u6587\u4ef6\u7248\u672c\u4e0e\u672c\u5730\u7248\u672c\u4e00\u81f4(v:" + offlineFiles2.getFileVersion() + ")\uff0c\u7ee7\u7eed\u4f7f\u7528\u672c\u5730\u79bb\u7ebf\u5305\u3002");
                        Log.d("OfflineWebClient", "Latest info's version is same with cache's, do nothing.");
                    }
                } else {
                    if (z) {
                        Log.w("OfflineWebClient", "Fetch info timeout, load URL for real web.");
                        if (Log.isDebug()) {
                            Log.xLogD("OfflineWebClient", "\u83b7\u53d6\u9879\u76ee(id:" + offlineFiles2.getAppId() + ")\u7684\u6700\u65b0\u914d\u7f6e\u6570\u636e\u8d85\u65f6\uff0c\u5c06\u91cd\u65b0\u52a0\u8f7d\u7ebf\u4e0aH5\u3002");
                        }
                    } else {
                        Log.w("OfflineWebClient", "Has no config on server, load URL for real web.");
                        if (Log.isDebug()) {
                            Log.xLogD("OfflineWebClient", "\u83b7\u53d6\u9879\u76ee(id:" + offlineFiles2.getAppId() + ")\u7684\u6700\u65b0\u914d\u7f6e\u6570\u636e\u5931\u8d25\uff0c\u5c06\u91cd\u65b0\u52a0\u8f7d\u7ebf\u4e0aH5\u3002");
                        }
                    }
                    this.f5931m = null;
                    this.f5932n = null;
                    if (offlineFiles2 != null) {
                        offlineFiles2.destroy();
                    }
                    this.f5928j = false;
                    this.d = null;
                    j("0");
                }
                return;
            }
            if (!z && offlineFiles != null && offlineFiles.isAvailable()) {
                Log.xLogD("OfflineWebClient", "\u9879\u76ee(id:" + offlineFiles2.getAppId() + ")\u7ebf\u4e0a\u79bb\u7ebf\u6587\u4ef6\u7248\u672c\u4e0e\u672c\u5730\u7248\u672c\u4e00\u81f4(v:" + offlineFiles2.getFileVersion() + ")\uff0c\u7ee7\u7eed\u4f7f\u7528\u672c\u5730\u79bb\u7ebf\u5305\u3002");
                Log.d("OfflineWebClient", "[Build-in] Latest file's version is same with local's, do nothing.");
            }
            String minFileVer = offlineFiles2.getMinFileVer();
            int parseInt = (TextUtils.isEmpty(minFileVer) || !TextUtils.isDigitsOnly(minFileVer)) ? 0 : Integer.parseInt(minFileVer);
            Log.d("OfflineWebClient", "[Build-in] Local file version = " + offlineFiles2.getFileVersion() + ", minimum version requirement = " + minFileVer);
            StringBuilder sb = new StringBuilder();
            sb.append("\u9879\u76ee(id:");
            sb.append(offlineFiles2.getAppId());
            sb.append(")");
            String sb2 = sb.toString();
            if (z) {
                str = sb2 + "\u83b7\u53d6\u5230\u7ebf\u4e0a\u914d\u7f6e\u8d85\u65f6";
            } else if (z2) {
                str = sb2 + "\u83b7\u53d6\u6700\u65b0\u6570\u636e\u5931\u8d25";
            } else {
                str = sb2 + "\u7ebf\u4e0a\u5b58\u5728\u66f4\u65b0\u79bb\u7ebf\u6587\u4ef6\u7248\u672c";
                if (offlineFiles != null) {
                    str = str + offlineFiles.getFileVersion();
                }
            }
            String str2 = str + "\uff0c\u672c\u5730\u7248\u672c" + offlineFiles2.getFileVersion() + "\uff0c\u6700\u4f4e\u8981\u6c42\u7248\u672c" + minFileVer;
            if (offlineFiles2.getFileVersion() < parseInt) {
                Log.w("OfflineWebClient", "[Build-in] Fetch timeout or latest info differs with cache, local file's version < min version, so reload online web page.");
                if (Log.isDebug()) {
                    Log.xLogD("OfflineWebClient", str2 + "\u3002\u4e0d\u7b26\u5408\u6700\u4f4e\u7248\u672c\u8981\u6c42\uff0c\u91cd\u65b0\u52a0\u8f7d\u7ebf\u4e0aH5\u3002");
                }
                this.f5931m = null;
                this.f5932n = null;
                offlineFiles2.destroy();
                this.f5928j = false;
                this.d = offlineFiles;
                j(offlineFiles == null ? "0" : offlineFiles.getType());
            } else {
                Log.w("OfflineWebClient", "[Build-in] Fetch timeout or latest info differs with cache, local file's version >= min version, so stay using local files.");
                if (Log.isDebug()) {
                    Log.xLogD("OfflineWebClient", str2 + "\u3002\u7b26\u5408\u6700\u4f4e\u7248\u672c\u8981\u6c42\uff0c\u7ee7\u7eed\u4f7f\u7528\u672c\u5730\u79bb\u7ebf\u5305\u3002");
                }
            }
        }
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

    /* JADX WARN: Removed duplicated region for block: B:388:0x0167 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:394:0x0179 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:399:0x01c6 A[Catch: Exception -> 0x02b3, TryCatch #0 {Exception -> 0x02b3, blocks: (B:319:0x003b, B:321:0x0046, B:326:0x0050, B:327:0x0053, B:329:0x005a, B:343:0x00d3, B:346:0x00db, B:348:0x00e6, B:350:0x00ec, B:355:0x0100, B:386:0x0160, B:390:0x016b, B:401:0x01da, B:404:0x01fa, B:408:0x020b, B:412:0x023d, B:418:0x0249, B:419:0x0253, B:423:0x0266, B:429:0x0270, B:395:0x017b, B:397:0x01b6, B:399:0x01c6, B:400:0x01d1, B:396:0x0199, B:357:0x0105, B:361:0x0111, B:362:0x0115, B:364:0x011d, B:366:0x0125, B:368:0x012d, B:371:0x0136, B:373:0x013e, B:376:0x0147, B:379:0x0151, B:384:0x015c, B:431:0x027b, B:432:0x029b, B:330:0x0065, B:332:0x006b, B:334:0x0074, B:338:0x0085, B:337:0x0081, B:339:0x009e, B:341:0x00ca, B:324:0x004c), top: B:447:0x003b }] */
    /* JADX WARN: Removed duplicated region for block: B:404:0x01fa A[Catch: Exception -> 0x02b3, TRY_ENTER, TryCatch #0 {Exception -> 0x02b3, blocks: (B:319:0x003b, B:321:0x0046, B:326:0x0050, B:327:0x0053, B:329:0x005a, B:343:0x00d3, B:346:0x00db, B:348:0x00e6, B:350:0x00ec, B:355:0x0100, B:386:0x0160, B:390:0x016b, B:401:0x01da, B:404:0x01fa, B:408:0x020b, B:412:0x023d, B:418:0x0249, B:419:0x0253, B:423:0x0266, B:429:0x0270, B:395:0x017b, B:397:0x01b6, B:399:0x01c6, B:400:0x01d1, B:396:0x0199, B:357:0x0105, B:361:0x0111, B:362:0x0115, B:364:0x011d, B:366:0x0125, B:368:0x012d, B:371:0x0136, B:373:0x013e, B:376:0x0147, B:379:0x0151, B:384:0x015c, B:431:0x027b, B:432:0x029b, B:330:0x0065, B:332:0x006b, B:334:0x0074, B:338:0x0085, B:337:0x0081, B:339:0x009e, B:341:0x00ca, B:324:0x004c), top: B:447:0x003b }] */
    /* JADX WARN: Removed duplicated region for block: B:419:0x0253 A[Catch: Exception -> 0x02b3, TryCatch #0 {Exception -> 0x02b3, blocks: (B:319:0x003b, B:321:0x0046, B:326:0x0050, B:327:0x0053, B:329:0x005a, B:343:0x00d3, B:346:0x00db, B:348:0x00e6, B:350:0x00ec, B:355:0x0100, B:386:0x0160, B:390:0x016b, B:401:0x01da, B:404:0x01fa, B:408:0x020b, B:412:0x023d, B:418:0x0249, B:419:0x0253, B:423:0x0266, B:429:0x0270, B:395:0x017b, B:397:0x01b6, B:399:0x01c6, B:400:0x01d1, B:396:0x0199, B:357:0x0105, B:361:0x0111, B:362:0x0115, B:364:0x011d, B:366:0x0125, B:368:0x012d, B:371:0x0136, B:373:0x013e, B:376:0x0147, B:379:0x0151, B:384:0x015c, B:431:0x027b, B:432:0x029b, B:330:0x0065, B:332:0x006b, B:334:0x0074, B:338:0x0085, B:337:0x0081, B:339:0x009e, B:341:0x00ca, B:324:0x004c), top: B:447:0x003b }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private WebResourceResponse u(HybridWebView hybridWebView, Uri uri) {
        boolean z;
        boolean z2;
        boolean z3;
        String str;
        String str2;
        Callback callback;
        OfflineFiles offlineFiles = this.d;
        if (!HybridSettings.isInited() || uri == null || offlineFiles == null) {
            return null;
        }
        if (TextUtils.isEmpty(offlineFiles.isSsrBiz() ? offlineFiles.getStaticDir() : offlineFiles.getHtmlPath())) {
            this.f5928j = false;
            this.f5931m = null;
            this.f5932n = null;
            return null;
        } else if (!offlineFiles.isAvailable()) {
            this.f5928j = false;
            return null;
        } else {
            try {
                boolean isSsrBiz = offlineFiles.isSsrBiz();
                HashMap hashMap = new HashMap();
                if ((!isSsrBiz && this.f5931m == null) || (isSsrBiz && this.f5932n == null)) {
                    m(offlineFiles);
                }
                String l2 = l(uri, offlineFiles);
                if (l2 != null) {
                    this.q.set(false);
                    z = false;
                    z2 = false;
                    z3 = true;
                } else {
                    if (n(uri, offlineFiles)) {
                        hashMap.put("cache-control", "max-age=7200");
                        if (isSsrBiz) {
                            l2 = offlineFiles.getStaticDir() + File.separator + (TextUtils.isEmpty(uri.getLastPathSegment()) ? "" : uri.getLastPathSegment());
                        } else {
                            l2 = offlineFiles.getStaticDir() + File.separator + uri.toString().substring(offlineFiles.getStaticPath().length());
                        }
                        z = true;
                        z2 = false;
                    } else {
                        l2 = k(uri, hashMap);
                        z = false;
                        z2 = true;
                    }
                    z3 = false;
                }
                if (!TextUtils.isEmpty(l2)) {
                    File file = new File(l2);
                    if (file.exists() && file.isFile()) {
                        if (l2.endsWith(".js")) {
                            str = "text/txt";
                            if (!z2) {
                                hashMap.put("access-control-allow-origin", ProxyConfig.MATCH_ALL_SCHEMES);
                            }
                        } else if (!l2.endsWith(".css")) {
                            if (!l2.endsWith(WareBusinessMagicHeadPicInfoEntity.IMAGE_TYPE_GIF) && !l2.endsWith("png") && !l2.endsWith("jpg") && !l2.endsWith("jpeg")) {
                                if (!l2.endsWith("htm") && !l2.endsWith("html") && l2.endsWith("ttf")) {
                                    if (!z2) {
                                        hashMap.put("access-control-allow-origin", ProxyConfig.MATCH_ALL_SCHEMES);
                                    }
                                    str = MIMEType.MIME_TYPE_HTML;
                                    str2 = null;
                                    FileInputStream fileInputStream = new FileInputStream(l2);
                                    if (!z3 || (isSsrBiz && z && this.q.getAndSet(false))) {
                                        if (!isSsrBiz && z) {
                                            Log.w("OfflineWebClient", "Use local offline ssr file for first ssr resource (url: " + uri + "), local file[" + l2 + "])");
                                        } else {
                                            Log.w("OfflineWebClient", "Use local offline file for main resource(url: " + uri + "), local file[" + l2 + "])");
                                        }
                                        this.f5928j = true;
                                        Log.d("setConfigBingoForH5, from intercept");
                                        r(this.b, 1, 1);
                                        callback = this.f5929k;
                                        if (callback != null) {
                                            callback.onFileHitMainPage(uri.toString(), l2, offlineFiles.getType());
                                        }
                                        PerformanceUtils.onOfflineBingo(hybridWebView, offlineFiles.getAppId());
                                    }
                                    OfflineWebRezResp offlineWebRezResp = new OfflineWebRezResp(str, str2, fileInputStream, l2, z3);
                                    t(offlineWebRezResp, hashMap);
                                    String str3 = "main";
                                    String str4 = "SSR";
                                    if (Log.isDebug()) {
                                        StringBuilder sb = new StringBuilder();
                                        sb.append("\u4f7f\u7528\u672c\u5730\u79bb\u7ebf\u6587\u4ef6\u66ff\u6362\u7ebf\u4e0a\u8d44\u6e90\uff0c\u5c5e\u4e8e");
                                        sb.append(z2 ? "\u516c\u5171\u8d44\u6e90" : "\u9879\u76ee\u5185\u8d44\u6e90");
                                        sb.append("\uff0c\u539f\u7ebf\u4e0aURL\uff1a");
                                        sb.append(uri);
                                        Log.xLogD("OfflineWebClient", sb.toString());
                                        Object[] objArr = new Object[7];
                                        objArr[0] = uri;
                                        objArr[1] = l2;
                                        objArr[2] = offlineWebRezResp.getMimeType();
                                        objArr[3] = offlineWebRezResp.getEncoding();
                                        objArr[4] = offlineWebRezResp.getResponseHeaders();
                                        if (!isSsrBiz) {
                                            str4 = "Normal";
                                        }
                                        objArr[5] = str4;
                                        if (z2) {
                                            str3 = "common";
                                        } else if (!z3) {
                                            str3 = "static";
                                        }
                                        objArr[6] = str3;
                                        Log.d("OfflineWebClient", String.format("=\n===============================================\nAssembling local response for url[%s], file[%s], mimeType = %s, encoding = %s, header = %s, type: %s, %s resource.\n===============================================", objArr));
                                    } else {
                                        Object[] objArr2 = new Object[5];
                                        objArr2[0] = uri;
                                        objArr2[1] = l2;
                                        objArr2[2] = offlineWebRezResp.getMimeType();
                                        if (!isSsrBiz) {
                                            str4 = "Normal";
                                        }
                                        objArr2[3] = str4;
                                        if (z2) {
                                            str3 = "common";
                                        } else if (!z3) {
                                            str3 = "static";
                                        }
                                        objArr2[4] = str3;
                                        Log.d("OfflineWebClient", String.format("Assembling local response for url[%s], file[%s], mimeType = %s, type: %s, %s resource.", objArr2));
                                    }
                                    return offlineWebRezResp;
                                }
                                str = MIMEType.MIME_TYPE_HTML;
                            }
                            str = "image/*";
                            if (!z2) {
                                hashMap.put("access-control-allow-origin", ProxyConfig.MATCH_ALL_SCHEMES);
                            }
                            str2 = null;
                            FileInputStream fileInputStream2 = new FileInputStream(l2);
                            if (!z3) {
                            }
                            if (!isSsrBiz) {
                            }
                            Log.w("OfflineWebClient", "Use local offline file for main resource(url: " + uri + "), local file[" + l2 + "])");
                            this.f5928j = true;
                            Log.d("setConfigBingoForH5, from intercept");
                            r(this.b, 1, 1);
                            callback = this.f5929k;
                            if (callback != null) {
                            }
                            PerformanceUtils.onOfflineBingo(hybridWebView, offlineFiles.getAppId());
                            OfflineWebRezResp offlineWebRezResp2 = new OfflineWebRezResp(str, str2, fileInputStream2, l2, z3);
                            t(offlineWebRezResp2, hashMap);
                            String str32 = "main";
                            String str42 = "SSR";
                            if (Log.isDebug()) {
                            }
                            return offlineWebRezResp2;
                        } else {
                            str = "text/css";
                            if (!z2) {
                                hashMap.put("access-control-allow-origin", ProxyConfig.MATCH_ALL_SCHEMES);
                            }
                        }
                        str2 = "utf-8";
                        FileInputStream fileInputStream22 = new FileInputStream(l2);
                        if (!z3) {
                        }
                        if (!isSsrBiz) {
                        }
                        Log.w("OfflineWebClient", "Use local offline file for main resource(url: " + uri + "), local file[" + l2 + "])");
                        this.f5928j = true;
                        Log.d("setConfigBingoForH5, from intercept");
                        r(this.b, 1, 1);
                        callback = this.f5929k;
                        if (callback != null) {
                        }
                        PerformanceUtils.onOfflineBingo(hybridWebView, offlineFiles.getAppId());
                        OfflineWebRezResp offlineWebRezResp22 = new OfflineWebRezResp(str, str2, fileInputStream22, l2, z3);
                        t(offlineWebRezResp22, hashMap);
                        String str322 = "main";
                        String str422 = "SSR";
                        if (Log.isDebug()) {
                        }
                        return offlineWebRezResp22;
                    }
                    Log.d("OfflineWebClient", "Offline local file does NOT exist for url[" + uri + "], File path [" + l2 + "]");
                    return null;
                }
                Log.d("OfflineWebClient", "Offline local file's path does NOT be found for url[" + uri + "]");
                return null;
            } catch (Exception e2) {
                Log.e("OfflineWebClient", e2);
                OfflineExceptionUtils.reportMatchError(OfflineExceptionUtils.ERR_MSG_CODE, "shouldInterceptRequest", offlineFiles != null ? offlineFiles.getAppId() : null, uri != null ? uri.toString() : null, ExceptionUtils.getStackStringFromException(e2));
                return null;
            }
        }
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
