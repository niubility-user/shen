package com.jd.libs.hybrid.offlineload.jdcache;

import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import androidx.annotation.Keep;
import androidx.annotation.RequiresApi;
import com.jd.jdcache.JDCacheLoader;
import com.jd.jdcache.JDCacheWebView;
import com.jd.jdcache.entity.JDCacheLocalResp;
import com.jd.jdcache.match.base.JDCacheResourceMatcher;
import com.jd.jdcache.util.JDCacheLog;
import com.jd.libs.hybrid.base.HybridSettings;
import com.jd.libs.hybrid.base.util.HybridUrlUtils;
import com.jd.libs.hybrid.base.util.Log;
import com.jd.libs.hybrid.base.util.PerformanceUtils;
import com.jd.libs.hybrid.offlineload.IPreDownloadParamGetter;
import com.jd.libs.hybrid.offlineload.OfflineCallback;
import com.jd.libs.hybrid.offlineload.OfflineLoadController;
import com.jd.libs.hybrid.offlineload.entity.CommonFile;
import com.jd.libs.hybrid.offlineload.entity.LocalFileType;
import com.jd.libs.hybrid.offlineload.entity.OfflineFiles;
import com.jd.libs.hybrid.offlineload.utils.OfflineExceptionUtils;
import com.jingdong.common.XView2.common.XView2Constants;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONObject;

@Keep
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u009c\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0007\u0018\u0000 a2\u00020\u0001:\u0001aB\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u00a2\u0006\u0004\b`\u0010\u0006J\u0017\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0002\u00a2\u0006\u0004\b\u0005\u0010\u0006J\u0019\u0010\n\u001a\u0004\u0018\u00010\t2\u0006\u0010\b\u001a\u00020\u0007H\u0002\u00a2\u0006\u0004\b\n\u0010\u000bJ\u0017\u0010\u000e\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\fH\u0002\u00a2\u0006\u0004\b\u000e\u0010\u000fJ)\u0010\u0015\u001a\u00020\u00132\b\u0010\u0011\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0012\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\u0013H\u0002\u00a2\u0006\u0004\b\u0015\u0010\u0016J\u000f\u0010\u0017\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0017\u0010\u0018J#\u0010\u001c\u001a\u00020\u00022\b\u0010\u001a\u001a\u0004\u0018\u00010\u00192\b\u0010\u001b\u001a\u0004\u0018\u00010\tH\u0003\u00a2\u0006\u0004\b\u001c\u0010\u001dJ\u0017\u0010 \u001a\u00020\u00042\u0006\u0010\u001f\u001a\u00020\u001eH\u0002\u00a2\u0006\u0004\b \u0010!J?\u0010'\u001a\u00020\u00042\n\b\u0002\u0010#\u001a\u0004\u0018\u00010\"2\n\b\u0002\u0010$\u001a\u0004\u0018\u00010\"2\n\b\u0002\u0010%\u001a\u0004\u0018\u00010\"2\n\b\u0002\u0010&\u001a\u0004\u0018\u00010\"H\u0002\u00a2\u0006\u0004\b'\u0010(J\u000f\u0010)\u001a\u00020\u0004H\u0014\u00a2\u0006\u0004\b)\u0010\u0018J\r\u0010*\u001a\u00020\u0004\u00a2\u0006\u0004\b*\u0010\u0018J\u000f\u0010+\u001a\u0004\u0018\u00010\f\u00a2\u0006\u0004\b+\u0010,J\u000f\u0010-\u001a\u0004\u0018\u00010\f\u00a2\u0006\u0004\b-\u0010,J\u0017\u00100\u001a\u00020\u00042\u0006\u0010/\u001a\u00020.H\u0014\u00a2\u0006\u0004\b0\u00101J\u0019\u00102\u001a\u0004\u0018\u00010\t2\u0006\u0010\b\u001a\u00020\u0007H\u0016\u00a2\u0006\u0004\b2\u0010\u000bJ\u0017\u00103\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016\u00a2\u0006\u0004\b3\u0010\u0006J\u0017\u00104\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016\u00a2\u0006\u0004\b4\u0010\u0006R\u0018\u00105\u001a\u0004\u0018\u00010\u00108\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b5\u00106R$\u00108\u001a\u0004\u0018\u0001078\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b8\u00109\u001a\u0004\b:\u0010;\"\u0004\b<\u0010=R\u0016\u0010>\u001a\u00020\u00138\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b>\u0010?R\u0016\u0010@\u001a\u00020\u00138\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b@\u0010?R\u0016\u0010B\u001a\u00020A8\u0002@\u0002X\u0082.\u00a2\u0006\u0006\n\u0004\bB\u0010CR\u0016\u0010E\u001a\u00020D8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bE\u0010FR$\u0010H\u001a\u0004\u0018\u00010G8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\bH\u0010I\u001a\u0004\bJ\u0010K\"\u0004\bL\u0010MR\u0016\u0010O\u001a\u00020N8\u0002@\u0002X\u0082.\u00a2\u0006\u0006\n\u0004\bO\u0010PR\u0016\u0010Q\u001a\u00020D8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bQ\u0010FR\u0016\u0010R\u001a\u00020D8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bR\u0010FR\u0016\u0010S\u001a\u00020D8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bS\u0010FR\u0016\u0010T\u001a\u00020D8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bT\u0010FR\u0016\u0010V\u001a\u00020U8\u0002@\u0002X\u0082.\u00a2\u0006\u0006\n\u0004\bV\u0010WR\u0016\u0010Y\u001a\u00020X8\u0002@\u0002X\u0082.\u00a2\u0006\u0006\n\u0004\bY\u0010ZR\u0016\u0010[\u001a\u00020D8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b[\u0010FR\u0016\u0010]\u001a\u00020\\8\u0002@\u0002X\u0082.\u00a2\u0006\u0006\n\u0004\b]\u0010^R\u0016\u0010_\u001a\u00020D8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b_\u0010F\u00a8\u0006b"}, d2 = {"Lcom/jd/libs/hybrid/offlineload/jdcache/CacheLoader;", "Lcom/jd/jdcache/JDCacheLoader;", "", "url", "", "startPreloadHtml", "(Ljava/lang/String;)V", "Landroid/webkit/WebResourceRequest;", "request", "Landroid/webkit/WebResourceResponse;", "getRespFromMatch", "(Landroid/webkit/WebResourceRequest;)Landroid/webkit/WebResourceResponse;", "Lcom/jd/libs/hybrid/offlineload/entity/OfflineFiles;", "offlineFiles", "getOfflineUri", "(Lcom/jd/libs/hybrid/offlineload/entity/OfflineFiles;)V", "Landroid/net/Uri;", "originHtmlPath", "uri", "", "regexpUrl", "checkIsOfflineMainFrame", "(Landroid/net/Uri;Landroid/net/Uri;Z)Z", "doReload", "()V", "Lcom/jd/jdcache/entity/JDCacheLocalResp;", "localFile", "response", "getJsonStringFromResp", "(Lcom/jd/jdcache/entity/JDCacheLocalResp;Landroid/webkit/WebResourceResponse;)Ljava/lang/String;", "Ljava/lang/Runnable;", "runnable", "runForWebView", "(Ljava/lang/Runnable;)V", "", "config", "bingo", "comConfig", "comBingo", "setConfigBingoForH5", "(Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;)V", "prepareMatchers", "searchConfig", "getBizOfflineFiles", "()Lcom/jd/libs/hybrid/offlineload/entity/OfflineFiles;", "getSharedOfflineFiles", "Landroid/os/Message;", "msg", "handleMessageData", "(Landroid/os/Message;)V", "onRequest", "onPageStarted", "onPageFinished", "offlineHtmlUri", "Landroid/net/Uri;", "Lcom/jd/libs/hybrid/offlineload/IPreDownloadParamGetter$PreDownloadParamGetter;", "preDownloadGetter", "Lcom/jd/libs/hybrid/offlineload/IPreDownloadParamGetter$PreDownloadParamGetter;", "getPreDownloadGetter", "()Lcom/jd/libs/hybrid/offlineload/IPreDownloadParamGetter$PreDownloadParamGetter;", "setPreDownloadGetter", "(Lcom/jd/libs/hybrid/offlineload/IPreDownloadParamGetter$PreDownloadParamGetter;)V", "offlinePageStarted", "Z", "pageStartedOnce", "Lcom/jd/libs/hybrid/offlineload/jdcache/SharedPackageMatcher;", "sharedPackageMatcher", "Lcom/jd/libs/hybrid/offlineload/jdcache/SharedPackageMatcher;", "Ljava/util/concurrent/atomic/AtomicBoolean;", "sharedFileChecked", "Ljava/util/concurrent/atomic/AtomicBoolean;", "Lcom/jd/libs/hybrid/offlineload/OfflineCallback;", "callback", "Lcom/jd/libs/hybrid/offlineload/OfflineCallback;", "getCallback", "()Lcom/jd/libs/hybrid/offlineload/OfflineCallback;", "setCallback", "(Lcom/jd/libs/hybrid/offlineload/OfflineCallback;)V", "Lcom/jd/libs/hybrid/offlineload/jdcache/GlobalBuildInResourceMatcher;", "globalBuildInMatcher", "Lcom/jd/libs/hybrid/offlineload/jdcache/GlobalBuildInResourceMatcher;", "sharedFirstHit", "bizFileChecked", "startMatchOnce", "reloadOnline", "Lcom/jd/libs/hybrid/offlineload/jdcache/BizPackageMatcher;", "bizPackageMatcher", "Lcom/jd/libs/hybrid/offlineload/jdcache/BizPackageMatcher;", "Lcom/jd/libs/hybrid/offlineload/jdcache/PreloadMatcher;", "preloadMatcher", "Lcom/jd/libs/hybrid/offlineload/jdcache/PreloadMatcher;", "triedPreloadHtml", "Lcom/jd/libs/hybrid/offlineload/jdcache/GlobalResourceMatcher;", "globalMatcher", "Lcom/jd/libs/hybrid/offlineload/jdcache/GlobalResourceMatcher;", "bizFirstHit", "<init>", "Companion", "offlineload_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes16.dex */
public final class CacheLoader extends JDCacheLoader {
    public static final int ACTION_RELOAD = 1;
    public static final int STATE_FETCH_PREDOWNLOAD_HTML = 257;
    private static final String TAG = "CacheLoader";
    private final AtomicBoolean bizFileChecked;
    private final AtomicBoolean bizFirstHit;
    private BizPackageMatcher bizPackageMatcher;
    @Nullable
    private OfflineCallback callback;
    private GlobalBuildInResourceMatcher globalBuildInMatcher;
    private GlobalResourceMatcher globalMatcher;
    private volatile Uri offlineHtmlUri;
    private boolean offlinePageStarted;
    private boolean pageStartedOnce;
    @Nullable
    private IPreDownloadParamGetter.PreDownloadParamGetter preDownloadGetter;
    private PreloadMatcher preloadMatcher;
    private final AtomicBoolean reloadOnline;
    private final AtomicBoolean sharedFileChecked;
    private final AtomicBoolean sharedFirstHit;
    private SharedPackageMatcher sharedPackageMatcher;
    private final AtomicBoolean startMatchOnce;
    private final AtomicBoolean triedPreloadHtml;

    @Metadata(bv = {1, 0, 3}, d1 = {}, d2 = {}, k = 3, mv = {1, 4, 0})
    /* loaded from: classes16.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[LocalFileType.values().length];
            $EnumSwitchMapping$0 = iArr;
            iArr[LocalFileType.FILE_TYPE_PKG.ordinal()] = 1;
            iArr[LocalFileType.FILE_TYPE_PKG_SHARED.ordinal()] = 2;
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public CacheLoader(@org.jetbrains.annotations.NotNull java.lang.String r13) {
        /*
            r12 = this;
            r0 = 5
            com.jd.jdcache.match.base.JDCacheResourceMatcher[] r0 = new com.jd.jdcache.match.base.JDCacheResourceMatcher[r0]
            com.jd.libs.hybrid.offlineload.jdcache.PreloadMatcher r1 = new com.jd.libs.hybrid.offlineload.jdcache.PreloadMatcher
            r1.<init>()
            r2 = 0
            r0[r2] = r1
            com.jd.libs.hybrid.offlineload.jdcache.BizPackageMatcher r1 = new com.jd.libs.hybrid.offlineload.jdcache.BizPackageMatcher
            r1.<init>()
            r3 = 1
            r0[r3] = r1
            com.jd.libs.hybrid.offlineload.jdcache.SharedPackageMatcher r1 = new com.jd.libs.hybrid.offlineload.jdcache.SharedPackageMatcher
            r1.<init>()
            r4 = 2
            r0[r4] = r1
            com.jd.libs.hybrid.offlineload.jdcache.GlobalResourceMatcher r1 = new com.jd.libs.hybrid.offlineload.jdcache.GlobalResourceMatcher
            r1.<init>()
            r4 = 3
            r0[r4] = r1
            com.jd.libs.hybrid.offlineload.jdcache.GlobalBuildInResourceMatcher r1 = new com.jd.libs.hybrid.offlineload.jdcache.GlobalBuildInResourceMatcher
            r1.<init>()
            r4 = 4
            r0[r4] = r1
            java.util.List r8 = kotlin.collections.CollectionsKt.listOf(r0)
            r7 = 0
            r9 = 0
            r10 = 10
            r11 = 0
            r5 = r12
            r6 = r13
            r5.<init>(r6, r7, r8, r9, r10, r11)
            java.util.concurrent.atomic.AtomicBoolean r13 = new java.util.concurrent.atomic.AtomicBoolean
            r13.<init>(r2)
            r12.bizFileChecked = r13
            java.util.concurrent.atomic.AtomicBoolean r13 = new java.util.concurrent.atomic.AtomicBoolean
            r13.<init>(r2)
            r12.sharedFileChecked = r13
            java.util.concurrent.atomic.AtomicBoolean r13 = new java.util.concurrent.atomic.AtomicBoolean
            r13.<init>(r2)
            r12.triedPreloadHtml = r13
            java.util.concurrent.atomic.AtomicBoolean r13 = new java.util.concurrent.atomic.AtomicBoolean
            r13.<init>(r2)
            r12.startMatchOnce = r13
            java.util.concurrent.atomic.AtomicBoolean r13 = new java.util.concurrent.atomic.AtomicBoolean
            r13.<init>(r3)
            r12.bizFirstHit = r13
            java.util.concurrent.atomic.AtomicBoolean r13 = new java.util.concurrent.atomic.AtomicBoolean
            r13.<init>(r3)
            r12.sharedFirstHit = r13
            java.util.concurrent.atomic.AtomicBoolean r13 = new java.util.concurrent.atomic.AtomicBoolean
            r13.<init>(r2)
            r12.reloadOnline = r13
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.libs.hybrid.offlineload.jdcache.CacheLoader.<init>(java.lang.String):void");
    }

    public static final /* synthetic */ BizPackageMatcher access$getBizPackageMatcher$p(CacheLoader cacheLoader) {
        BizPackageMatcher bizPackageMatcher = cacheLoader.bizPackageMatcher;
        if (bizPackageMatcher == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bizPackageMatcher");
        }
        return bizPackageMatcher;
    }

    public static final /* synthetic */ GlobalResourceMatcher access$getGlobalMatcher$p(CacheLoader cacheLoader) {
        GlobalResourceMatcher globalResourceMatcher = cacheLoader.globalMatcher;
        if (globalResourceMatcher == null) {
            Intrinsics.throwUninitializedPropertyAccessException("globalMatcher");
        }
        return globalResourceMatcher;
    }

    public static final /* synthetic */ SharedPackageMatcher access$getSharedPackageMatcher$p(CacheLoader cacheLoader) {
        SharedPackageMatcher sharedPackageMatcher = cacheLoader.sharedPackageMatcher;
        if (sharedPackageMatcher == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sharedPackageMatcher");
        }
        return sharedPackageMatcher;
    }

    private final boolean checkIsOfflineMainFrame(Uri originHtmlPath, Uri uri, boolean regexpUrl) {
        if (originHtmlPath != null) {
            if (regexpUrl) {
                return HybridUrlUtils.isRegexpMatched(originHtmlPath.toString(), uri.toString());
            }
            return HybridUrlUtils.uriMatchHostPath(originHtmlPath, uri);
        }
        return false;
    }

    private final void doReload() {
        this.reloadOnline.set(true);
        runForWebView(new Runnable() { // from class: com.jd.libs.hybrid.offlineload.jdcache.CacheLoader$doReload$1
            {
                CacheLoader.this = this;
            }

            @Override // java.lang.Runnable
            public final void run() {
                String str;
                boolean z;
                AtomicBoolean atomicBoolean;
                JDCacheWebView view;
                JDCacheLog jDCacheLog = JDCacheLog.INSTANCE;
                if (jDCacheLog.getCanLog()) {
                    jDCacheLog.d("CacheLoader", "Reload online web.");
                }
                OfflineCallback callback = CacheLoader.this.getCallback();
                if (callback != null) {
                    callback.beforeReload();
                }
                OfflineFiles offlineFiles = CacheLoader.access$getBizPackageMatcher$p(CacheLoader.this).getOfflineFiles();
                if (offlineFiles == null) {
                    offlineFiles = CacheLoader.access$getSharedPackageMatcher$p(CacheLoader.this).getOfflineFiles();
                }
                JDCacheWebView view2 = CacheLoader.this.getView();
                if (offlineFiles == null || (str = offlineFiles.getAppId()) == null) {
                    str = "";
                }
                PerformanceUtils.onWebReload(view2, str);
                z = CacheLoader.this.pageStartedOnce;
                if (z) {
                    if (jDCacheLog.getCanLog()) {
                        jDCacheLog.d("CacheLoader", "setConfigBingoForH5, from reload");
                    }
                    CacheLoader.setConfigBingoForH5$default(CacheLoader.this, Integer.valueOf(offlineFiles != null ? 1 : 0), Integer.valueOf(CacheLoader.access$getBizPackageMatcher$p(CacheLoader.this).getFileHit().get() ? 1 : 0), null, null, 12, null);
                }
                atomicBoolean = CacheLoader.this.startMatchOnce;
                if (!atomicBoolean.get() || (view = CacheLoader.this.getView()) == null) {
                    return;
                }
                view.reload();
            }
        });
    }

    @RequiresApi(api = 21)
    private final String getJsonStringFromResp(JDCacheLocalResp localFile, WebResourceResponse response) {
        String str;
        if (response == null) {
            return "";
        }
        try {
            String mimeType = response.getMimeType();
            String encoding = response.getEncoding();
            JSONObject jSONObject = new JSONObject();
            if (mimeType == null) {
                mimeType = "";
            }
            jSONObject.put("mimeType", mimeType);
            jSONObject.put("encoding", encoding != null ? encoding : "");
            jSONObject.put("header", response.getResponseHeaders());
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("response", jSONObject);
            if (localFile != null) {
                jSONObject2.put("localFile", localFile.toJson());
            }
            jSONObject2.put("webViewId", getViewId());
            str = jSONObject2.toString();
        } catch (Exception e2) {
            JDCacheLog jDCacheLog = JDCacheLog.INSTANCE;
            if (jDCacheLog.getCanLog()) {
                jDCacheLog.e(TAG, e2);
            }
            str = "Error occurs when getting response's json string: " + e2.getMessage();
        }
        return str;
    }

    private final void getOfflineUri(OfflineFiles offlineFiles) {
        String originHtmlPath = offlineFiles.getOriginHtmlPath();
        this.offlineHtmlUri = !TextUtils.isEmpty(originHtmlPath) ? Uri.parse(originHtmlPath) : null;
    }

    private final WebResourceResponse getRespFromMatch(WebResourceRequest request) {
        List<JDCacheResourceMatcher> matcherList = getMatcherList();
        if (matcherList != null) {
            Iterator<T> it = matcherList.iterator();
            while (it.hasNext()) {
                WebResourceResponse match = ((JDCacheResourceMatcher) it.next()).match(request);
                if (match != null) {
                    return match;
                }
            }
            return null;
        }
        return null;
    }

    private final void runForWebView(Runnable runnable) {
        Handler handler;
        JDCacheWebView view = getView();
        if (view != null) {
            View view2 = view.getView();
            if (view2 == null || (handler = view2.getHandler()) == null) {
                handler = new Handler(Looper.getMainLooper());
            }
            if (Intrinsics.areEqual(Looper.myLooper(), handler.getLooper())) {
                runnable.run();
            } else {
                handler.post(runnable);
            }
        }
    }

    private final void setConfigBingoForH5(final Integer config, final Integer bingo, final Integer comConfig, final Integer comBingo) {
        runForWebView(new Runnable() { // from class: com.jd.libs.hybrid.offlineload.jdcache.CacheLoader$setConfigBingoForH5$1
            {
                CacheLoader.this = this;
            }

            @Override // java.lang.Runnable
            public final void run() {
                JDCacheWebView view = CacheLoader.this.getView();
                if (view != null) {
                    String str = "javascript:";
                    if (config != null) {
                        str = "javascript:window.HYBRID_CONFIG=" + config + ';';
                    }
                    if (bingo != null) {
                        str = str + "window.HYBRID_BINGO=" + bingo + ';';
                    }
                    if (comConfig != null) {
                        str = str + "window.HYBRID_C_CONFIG=" + comConfig + ';';
                    }
                    if (comBingo != null) {
                        str = str + "window.HYBRID_C_BINGO=" + comBingo + ';';
                    }
                    JDCacheLog jDCacheLog = JDCacheLog.INSTANCE;
                    if (jDCacheLog.getCanLog()) {
                        jDCacheLog.d("CacheLoader", "evaluateJavascript-> " + str);
                    }
                    view.evaluateJavascript(str, null);
                }
            }
        });
    }

    public static /* synthetic */ void setConfigBingoForH5$default(CacheLoader cacheLoader, Integer num, Integer num2, Integer num3, Integer num4, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            num = null;
        }
        if ((i2 & 2) != 0) {
            num2 = null;
        }
        if ((i2 & 4) != 0) {
            num3 = null;
        }
        if ((i2 & 8) != 0) {
            num4 = null;
        }
        cacheLoader.setConfigBingoForH5(num, num2, num3, num4);
    }

    public final void startPreloadHtml(String url) {
        if (this.triedPreloadHtml.compareAndSet(false, true)) {
            BizPackageMatcher bizPackageMatcher = this.bizPackageMatcher;
            if (bizPackageMatcher == null) {
                Intrinsics.throwUninitializedPropertyAccessException("bizPackageMatcher");
            }
            OfflineFiles offlineFiles = bizPackageMatcher.getOfflineFiles();
            if (offlineFiles == null) {
                SharedPackageMatcher sharedPackageMatcher = this.sharedPackageMatcher;
                if (sharedPackageMatcher == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("sharedPackageMatcher");
                }
                offlineFiles = sharedPackageMatcher.getOfflineFiles();
            }
            if (offlineFiles == null || !offlineFiles.isCanPreloadHtml()) {
                return;
            }
            JDCacheLog jDCacheLog = JDCacheLog.INSTANCE;
            if (jDCacheLog.getCanLog()) {
                jDCacheLog.d(TAG, "pre-download html is enable.");
                Log.xLogDForDev(TAG, "\u914d\u7f6e\u5f00\u542f\u4e86\u9884\u4e0b\u8f7dHTML");
            }
            if (!offlineFiles.isAvailable()) {
                if (jDCacheLog.getCanLog()) {
                    jDCacheLog.d(TAG, "pre-download html canceled because offline files are not ready.");
                    Log.xLogDForDev(TAG, "\u4e0d\u8fdb\u884c\u9884\u4e0b\u8f7dHTML\uff0c\u56e0\u4e3a\u79bb\u7ebf\u6587\u4ef6\u672a\u4e0b\u8f7d\u89e3\u538b\u5b8c\u6210");
                    return;
                }
                return;
            }
            IPreDownloadParamGetter.PreDownloadParamGetter preDownloadParamGetter = this.preDownloadGetter;
            if (preDownloadParamGetter != null) {
                url = preDownloadParamGetter.getDownloadUrl(offlineFiles, url);
            }
            if (url == null || url.length() == 0) {
                if (jDCacheLog.getCanLog()) {
                    jDCacheLog.d(TAG, "pre-download html canceled by outside.");
                    Log.xLogDForDev(TAG, "\u4e1a\u52a1\u5c42\u63a7\u5236\u4e0d\u8fdb\u884c\u9884\u4e0b\u8f7dHTML");
                    return;
                }
                return;
            }
            PreloadMatcher preloadMatcher = this.preloadMatcher;
            if (preloadMatcher == null) {
                Intrinsics.throwUninitializedPropertyAccessException("preloadMatcher");
            }
            preloadMatcher.startPreload(offlineFiles, url);
        }
    }

    @Nullable
    public final OfflineFiles getBizOfflineFiles() {
        if (getEnable()) {
            BizPackageMatcher bizPackageMatcher = this.bizPackageMatcher;
            if (bizPackageMatcher == null) {
                Intrinsics.throwUninitializedPropertyAccessException("bizPackageMatcher");
            }
            return bizPackageMatcher.getOfflineFiles();
        }
        return null;
    }

    @Nullable
    public final OfflineCallback getCallback() {
        return this.callback;
    }

    @Nullable
    public final IPreDownloadParamGetter.PreDownloadParamGetter getPreDownloadGetter() {
        return this.preDownloadGetter;
    }

    @Nullable
    public final OfflineFiles getSharedOfflineFiles() {
        if (getEnable()) {
            SharedPackageMatcher sharedPackageMatcher = this.sharedPackageMatcher;
            if (sharedPackageMatcher == null) {
                Intrinsics.throwUninitializedPropertyAccessException("sharedPackageMatcher");
            }
            return sharedPackageMatcher.getOfflineFiles();
        }
        return null;
    }

    @Override // com.jd.jdcache.JDCacheLoader
    public void handleMessageData(@NotNull Message msg) {
        if (getEnable()) {
            int i2 = msg.what;
            if (i2 == 1) {
                doReload();
            } else if (i2 != 257) {
            } else {
                Object obj = msg.obj;
                JSONObject jSONObject = obj instanceof JSONObject ? (JSONObject) obj : null;
                int optInt = jSONObject != null ? jSONObject.optInt(XView2Constants.STATE) : 0;
                OfflineCallback offlineCallback = this.callback;
                if (offlineCallback != null) {
                    offlineCallback.onFetchPreDownloadFile(optInt, jSONObject != null ? jSONObject.optLong("start") : 0L, jSONObject != null ? jSONObject.optLong("end") : 0L, null);
                }
                PerformanceUtils.onHtmlPreDownloadChange(getView(), jSONObject != null ? jSONObject.optString("id") : null, optInt, null);
            }
        }
    }

    @Override // com.jd.jdcache.JDCacheLoader
    public void onPageFinished(@NotNull String url) {
        if (getEnable()) {
            this.offlinePageStarted = false;
        }
    }

    @Override // com.jd.jdcache.JDCacheLoader
    public void onPageStarted(@NotNull String url) {
        if (getEnable()) {
            int i2 = 1;
            this.pageStartedOnce = true;
            if (this.offlinePageStarted) {
                return;
            }
            BizPackageMatcher bizPackageMatcher = this.bizPackageMatcher;
            if (bizPackageMatcher == null) {
                Intrinsics.throwUninitializedPropertyAccessException("bizPackageMatcher");
            }
            OfflineFiles offlineFiles = bizPackageMatcher.getOfflineFiles();
            if (offlineFiles == null) {
                SharedPackageMatcher sharedPackageMatcher = this.sharedPackageMatcher;
                if (sharedPackageMatcher == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("sharedPackageMatcher");
                }
                offlineFiles = sharedPackageMatcher.getOfflineFiles();
            }
            if (offlineFiles != null) {
                try {
                    if (this.offlineHtmlUri == null) {
                        getOfflineUri(offlineFiles);
                    }
                    Uri uri = Uri.parse(url);
                    Uri uri2 = this.offlineHtmlUri;
                    Intrinsics.checkExpressionValueIsNotNull(uri, "uri");
                    if (checkIsOfflineMainFrame(uri2, uri, offlineFiles.isRegexpUrl())) {
                        this.offlinePageStarted = true;
                        BizPackageMatcher bizPackageMatcher2 = this.bizPackageMatcher;
                        if (bizPackageMatcher2 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("bizPackageMatcher");
                        }
                        OfflineFiles offlineFiles2 = bizPackageMatcher2.getOfflineFiles();
                        if (offlineFiles2 != null) {
                            JDCacheLog jDCacheLog = JDCacheLog.INSTANCE;
                            if (jDCacheLog.getCanLog()) {
                                jDCacheLog.d("setConfigBingoForH5 biz, from pageStart");
                            }
                            BizPackageMatcher bizPackageMatcher3 = this.bizPackageMatcher;
                            if (bizPackageMatcher3 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("bizPackageMatcher");
                            }
                            setConfigBingoForH5$default(this, 1, Integer.valueOf(bizPackageMatcher3.getFileHit().get() ? 1 : 0), null, null, 12, null);
                            PerformanceUtils.onMatchOffline(getView(), offlineFiles2.getAppId());
                        }
                        SharedPackageMatcher sharedPackageMatcher2 = this.sharedPackageMatcher;
                        if (sharedPackageMatcher2 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("sharedPackageMatcher");
                        }
                        OfflineFiles offlineFiles3 = sharedPackageMatcher2.getOfflineFiles();
                        if (offlineFiles3 != null) {
                            JDCacheLog jDCacheLog2 = JDCacheLog.INSTANCE;
                            if (jDCacheLog2.getCanLog()) {
                                jDCacheLog2.d("setConfigBingoForH5 shared, from pageStart");
                            }
                            SharedPackageMatcher sharedPackageMatcher3 = this.sharedPackageMatcher;
                            if (sharedPackageMatcher3 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("sharedPackageMatcher");
                            }
                            if (!sharedPackageMatcher3.getFileHit().get()) {
                                i2 = 0;
                            }
                            setConfigBingoForH5$default(this, null, null, 1, Integer.valueOf(i2), 3, null);
                            PerformanceUtils.onMatchShared(getView(), offlineFiles3.getAppId());
                        }
                        OfflineCallback offlineCallback = this.callback;
                        if (offlineCallback != null) {
                            offlineCallback.onOfflinePageStarted(url);
                        }
                    }
                } catch (Exception e2) {
                    JDCacheLog jDCacheLog3 = JDCacheLog.INSTANCE;
                    if (jDCacheLog3.getCanLog()) {
                        jDCacheLog3.e(TAG, e2);
                    }
                    OfflineExceptionUtils.reportMatchError(OfflineExceptionUtils.ERR_MSG_CODE, "CacheLoader#onPageStarted", offlineFiles.getAppId(), url, e2);
                }
            }
        }
    }

    /* JADX WARN: Not initialized variable reg: 5, insn: 0x0175: MOVE (r4 I:??[OBJECT, ARRAY]) = (r5 I:??[OBJECT, ARRAY]), block:B:278:0x0175 */
    /* JADX WARN: Removed duplicated region for block: B:219:0x0075  */
    /* JADX WARN: Removed duplicated region for block: B:222:0x0085 A[Catch: Exception -> 0x00be, TRY_ENTER, TryCatch #2 {Exception -> 0x00be, blocks: (B:222:0x0085, B:224:0x008d, B:230:0x00c6, B:241:0x0100, B:243:0x0104, B:244:0x0107, B:246:0x010d, B:248:0x0113, B:249:0x0116, B:250:0x011b, B:252:0x0121, B:253:0x0124, B:261:0x0139, B:263:0x0145, B:264:0x0148, B:272:0x0166), top: B:356:0x0083 }] */
    /* JADX WARN: Removed duplicated region for block: B:228:0x00c2 A[Catch: Exception -> 0x0373, TRY_ENTER, TRY_LEAVE, TryCatch #4 {Exception -> 0x0373, blocks: (B:220:0x007f, B:228:0x00c2, B:233:0x00cf, B:237:0x00dc, B:270:0x015a, B:273:0x0169, B:236:0x00d7, B:232:0x00ca), top: B:358:0x007f }] */
    /* JADX WARN: Removed duplicated region for block: B:283:0x0182 A[Catch: Exception -> 0x036f, TRY_ENTER, TryCatch #0 {Exception -> 0x036f, blocks: (B:283:0x0182, B:285:0x018a, B:287:0x0190, B:289:0x0198, B:291:0x01a0, B:300:0x01d9, B:302:0x0203, B:303:0x0206, B:305:0x020c, B:307:0x0212, B:310:0x021d, B:312:0x023b, B:313:0x023e, B:315:0x0244, B:317:0x024a, B:318:0x024d, B:320:0x0251, B:322:0x025b, B:324:0x0261, B:325:0x026c, B:328:0x0278, B:330:0x02e3, B:332:0x02e9, B:333:0x032f, B:335:0x0347, B:337:0x034d, B:293:0x01a8, B:295:0x01ac, B:296:0x01bf, B:298:0x01c3), top: B:353:0x0180 }] */
    /* JADX WARN: Removed duplicated region for block: B:287:0x0190 A[Catch: Exception -> 0x036f, TryCatch #0 {Exception -> 0x036f, blocks: (B:283:0x0182, B:285:0x018a, B:287:0x0190, B:289:0x0198, B:291:0x01a0, B:300:0x01d9, B:302:0x0203, B:303:0x0206, B:305:0x020c, B:307:0x0212, B:310:0x021d, B:312:0x023b, B:313:0x023e, B:315:0x0244, B:317:0x024a, B:318:0x024d, B:320:0x0251, B:322:0x025b, B:324:0x0261, B:325:0x026c, B:328:0x0278, B:330:0x02e3, B:332:0x02e9, B:333:0x032f, B:335:0x0347, B:337:0x034d, B:293:0x01a8, B:295:0x01ac, B:296:0x01bf, B:298:0x01c3), top: B:353:0x0180 }] */
    /* JADX WARN: Removed duplicated region for block: B:345:0x037d  */
    /* JADX WARN: Removed duplicated region for block: B:347:0x0382  */
    /* JADX WARN: Removed duplicated region for block: B:348:0x0387  */
    @Override // com.jd.jdcache.JDCacheLoader
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public android.webkit.WebResourceResponse onRequest(@org.jetbrains.annotations.NotNull android.webkit.WebResourceRequest r23) {
        /*
            Method dump skipped, instructions count: 919
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.libs.hybrid.offlineload.jdcache.CacheLoader.onRequest(android.webkit.WebResourceRequest):android.webkit.WebResourceResponse");
    }

    @Override // com.jd.jdcache.JDCacheLoader
    protected void prepareMatchers() {
        List<JDCacheResourceMatcher> matcherList;
        if (getEnable() && (matcherList = getMatcherList()) != null) {
            for (JDCacheResourceMatcher jDCacheResourceMatcher : matcherList) {
                jDCacheResourceMatcher.setLoader(this);
                jDCacheResourceMatcher.prepare(getUrl());
                if (jDCacheResourceMatcher instanceof PreloadMatcher) {
                    this.preloadMatcher = (PreloadMatcher) jDCacheResourceMatcher;
                } else if (jDCacheResourceMatcher instanceof BizPackageMatcher) {
                    this.bizPackageMatcher = (BizPackageMatcher) jDCacheResourceMatcher;
                } else if (jDCacheResourceMatcher instanceof SharedPackageMatcher) {
                    this.sharedPackageMatcher = (SharedPackageMatcher) jDCacheResourceMatcher;
                } else if (jDCacheResourceMatcher instanceof GlobalResourceMatcher) {
                    this.globalMatcher = (GlobalResourceMatcher) jDCacheResourceMatcher;
                } else if (jDCacheResourceMatcher instanceof GlobalBuildInResourceMatcher) {
                    this.globalBuildInMatcher = (GlobalBuildInResourceMatcher) jDCacheResourceMatcher;
                }
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void searchConfig() {
        OfflineLoadController offlineLoadController = OfflineLoadController.getInstance(HybridSettings.getAppContext());
        offlineLoadController.getOfflineFiles(getUrl(), new OfflineLoadController.NetConfigCallback<OfflineFiles>() { // from class: com.jd.libs.hybrid.offlineload.jdcache.CacheLoader$searchConfig$1
            {
                CacheLoader.this = this;
            }

            @Override // com.jd.libs.hybrid.offlineload.OfflineLoadController.ConfigCallback
            public void onFilesAvailable() {
                CacheLoader.access$getBizPackageMatcher$p(CacheLoader.this).onFilesAvailable();
                CacheLoader cacheLoader = CacheLoader.this;
                cacheLoader.startPreloadHtml(cacheLoader.getUrl());
            }

            @Override // com.jd.libs.hybrid.offlineload.OfflineLoadController.ConfigCallback
            public void onCacheCallback(@Nullable OfflineFiles files, boolean needCheck) {
                AtomicBoolean atomicBoolean;
                AtomicBoolean atomicBoolean2;
                CacheLoader.access$getBizPackageMatcher$p(CacheLoader.this).onCacheConfig(files);
                if (files == null) {
                    atomicBoolean = CacheLoader.this.bizFileChecked;
                    atomicBoolean.set(true);
                    atomicBoolean2 = CacheLoader.this.sharedFileChecked;
                    if (atomicBoolean2.get()) {
                        CacheLoader cacheLoader = CacheLoader.this;
                        cacheLoader.startPreloadHtml(cacheLoader.getUrl());
                    }
                }
            }

            @Override // com.jd.libs.hybrid.offlineload.OfflineLoadController.NetConfigCallback
            public void onNetworkCallback(@Nullable OfflineFiles files, boolean error, boolean reload) {
                CacheLoader.access$getBizPackageMatcher$p(CacheLoader.this).onLatestConfig(files, reload);
            }
        });
        offlineLoadController.getSharedOfflineFiles(getUrl(), new OfflineLoadController.ConfigCallback<OfflineFiles>() { // from class: com.jd.libs.hybrid.offlineload.jdcache.CacheLoader$searchConfig$2
            {
                CacheLoader.this = this;
            }

            @Override // com.jd.libs.hybrid.offlineload.OfflineLoadController.ConfigCallback
            public void onFilesAvailable() {
                AtomicBoolean atomicBoolean;
                AtomicBoolean atomicBoolean2;
                CacheLoader.access$getSharedPackageMatcher$p(CacheLoader.this).onFilesAvailable();
                atomicBoolean = CacheLoader.this.sharedFileChecked;
                atomicBoolean.set(true);
                atomicBoolean2 = CacheLoader.this.bizFileChecked;
                if (atomicBoolean2.get()) {
                    CacheLoader cacheLoader = CacheLoader.this;
                    cacheLoader.startPreloadHtml(cacheLoader.getUrl());
                }
            }

            @Override // com.jd.libs.hybrid.offlineload.OfflineLoadController.ConfigCallback
            public void onCacheCallback(@Nullable OfflineFiles files, boolean needCheck) {
                CacheLoader.access$getSharedPackageMatcher$p(CacheLoader.this).onCacheConfig(files);
            }
        });
        offlineLoadController.getCommonOfflineFiles(new OfflineLoadController.ConfigCallback<List<? extends CommonFile>>() { // from class: com.jd.libs.hybrid.offlineload.jdcache.CacheLoader$searchConfig$3
            {
                CacheLoader.this = this;
            }

            @Override // com.jd.libs.hybrid.offlineload.OfflineLoadController.ConfigCallback
            public void onFilesAvailable() {
            }

            @Override // com.jd.libs.hybrid.offlineload.OfflineLoadController.ConfigCallback
            public void onCacheCallback(@Nullable List<? extends CommonFile> files, boolean needCheck) {
                CacheLoader.access$getGlobalMatcher$p(CacheLoader.this).onConfig(files);
            }
        });
    }

    public final void setCallback(@Nullable OfflineCallback offlineCallback) {
        this.callback = offlineCallback;
    }

    public final void setPreDownloadGetter(@Nullable IPreDownloadParamGetter.PreDownloadParamGetter preDownloadParamGetter) {
        this.preDownloadGetter = preDownloadParamGetter;
    }
}
