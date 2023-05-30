package com.jd.libs.hybrid.offlineload.jdcache;

import android.net.Uri;
import android.os.Build;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import androidx.annotation.Keep;
import com.jd.jdcache.JDCache;
import com.jd.jdcache.JDCacheLoader;
import com.jd.jdcache.JDCacheParamsProvider;
import com.jd.jdcache.entity.JDCacheLocalResp;
import com.jd.jdcache.entity.JDCacheLocalRespKt;
import com.jd.jdcache.match.PreReadInputStream;
import com.jd.jdcache.match.impl.PreloadHtmlMatcher;
import com.jd.jdcache.service.base.JDCacheFileRepoDelegate;
import com.jd.jdcache.util.CancellableJob;
import com.jd.jdcache.util.CoroutineHelper;
import com.jd.jdcache.util.JDCacheLog;
import com.jd.jdcache.util.UrlHelper;
import com.jd.libs.hybrid.base.util.Log;
import com.jd.libs.hybrid.offlineload.entity.LocalFileType;
import com.jd.libs.hybrid.offlineload.entity.OfflineFiles;
import com.jd.libs.hybrid.offlineload.utils.FileUtils;
import com.jd.libs.hybrid.offlineload.utils.GraySwitch;
import com.jd.libs.hybrid.offlineload.utils.OfflineExceptionUtils;
import com.jingdong.common.XView2.common.XView2Constants;
import com.jingdong.common.jdreactFramework.JDReactConstant;
import java.io.File;
import java.io.InputStream;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__BuildersKt;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.channels.ChannelKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONObject;

@Keep
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\n\b\u0007\u0018\u0000 &2\u00020\u0001:\u0001&B\u0007\u00a2\u0006\u0004\b%\u0010\u0019J\u0017\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016\u00a2\u0006\u0004\b\u0005\u0010\u0006J\u001f\u0010\t\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00072\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u00a2\u0006\u0004\b\t\u0010\nJ/\u0010\r\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00022\u0016\u0010\f\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u000bH\u0014\u00a2\u0006\u0004\b\r\u0010\u000eJ/\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00022\u0016\u0010\f\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u000bH\u0014\u00a2\u0006\u0004\b\u000f\u0010\u000eJ\u0019\u0010\u0013\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0011\u001a\u00020\u0010H\u0016\u00a2\u0006\u0004\b\u0013\u0010\u0014J\u0011\u0010\u0016\u001a\u0004\u0018\u00010\u0015H\u0014\u00a2\u0006\u0004\b\u0016\u0010\u0017J\u000f\u0010\u0018\u001a\u00020\u0004H\u0014\u00a2\u0006\u0004\b\u0018\u0010\u0019R\u0016\u0010\u001b\u001a\u00020\u001a8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u001b\u0010\u001cR\u0016\u0010\u001e\u001a\u00020\u001d8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u001e\u0010\u001fR\u0016\u0010 \u001a\u00020\u001d8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b \u0010\u001fR(\u0010\b\u001a\u0004\u0018\u00010\u00072\b\u0010!\u001a\u0004\u0018\u00010\u00078\u0006@BX\u0086\u000e\u00a2\u0006\f\n\u0004\b\b\u0010\"\u001a\u0004\b#\u0010$\u00a8\u0006'"}, d2 = {"Lcom/jd/libs/hybrid/offlineload/jdcache/PreloadMatcher;", "Lcom/jd/jdcache/match/impl/PreloadHtmlMatcher;", "", "url", "", JDReactConstant.PREPARE, "(Ljava/lang/String;)V", "Lcom/jd/libs/hybrid/offlineload/entity/OfflineFiles;", "offlineFiles", "startPreload", "(Lcom/jd/libs/hybrid/offlineload/entity/OfflineFiles;Ljava/lang/String;)V", "", "header", "downloadHtmlStream", "(Ljava/lang/String;Ljava/util/Map;)V", "downloadHtmlFile", "Landroid/webkit/WebResourceRequest;", "request", "Landroid/webkit/WebResourceResponse;", "match", "(Landroid/webkit/WebResourceRequest;)Landroid/webkit/WebResourceResponse;", "Lcom/jd/jdcache/entity/JDCacheLocalResp;", "geDownloadLocalResp", "()Lcom/jd/jdcache/entity/JDCacheLocalResp;", "onDestroy", "()V", "", XView2Constants.STATE, "I", "", "end", "J", "start", "<set-?>", "Lcom/jd/libs/hybrid/offlineload/entity/OfflineFiles;", "getOfflineFiles", "()Lcom/jd/libs/hybrid/offlineload/entity/OfflineFiles;", "<init>", "Companion", "offlineload_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes16.dex */
public final class PreloadMatcher extends PreloadHtmlMatcher {
    @Deprecated

    /* renamed from: Companion  reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int STATE_DEFAULT = 0;
    public static final int STATE_FAIL = -1;
    public static final int STATE_SUCCESS = 200;
    public static final int STATE_TIMEOUT = -2;
    private long end;
    @Nullable
    private OfflineFiles offlineFiles;
    private long start;
    private int state;

    /* JADX INFO: Access modifiers changed from: private */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\b\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\b\u0010\tR\u0016\u0010\u0003\u001a\u00020\u00028\u0006@\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u0003\u0010\u0004R\u0016\u0010\u0005\u001a\u00020\u00028\u0006@\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u0005\u0010\u0004R\u0016\u0010\u0006\u001a\u00020\u00028\u0006@\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u0006\u0010\u0004R\u0016\u0010\u0007\u001a\u00020\u00028\u0006@\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u0007\u0010\u0004\u00a8\u0006\n"}, d2 = {"Lcom/jd/libs/hybrid/offlineload/jdcache/PreloadMatcher$Companion;", "", "", "STATE_DEFAULT", "I", "STATE_FAIL", "STATE_SUCCESS", "STATE_TIMEOUT", "<init>", "()V", "offlineload_release"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes16.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Override // com.jd.jdcache.match.impl.PreloadHtmlMatcher
    protected void downloadHtmlFile(@NotNull String url, @Nullable Map<String, String> header) {
        String str;
        JDCacheParamsProvider globalParams = JDCache.INSTANCE.getGlobalParams();
        String userAgent = globalParams != null ? globalParams.getUserAgent(url) : null;
        if (userAgent == null || userAgent.length() == 0) {
            JDCacheLog jDCacheLog = JDCacheLog.INSTANCE;
            if (jDCacheLog.getCanLog()) {
                Log.xLogEForDev(getName(), "\u5f00\u542f\u4e86\u9884\u4e0b\u8f7dhtml\u529f\u80fd\uff0c\u4f46\u672a\u83b7\u53d6\u5230UserAgent\u4fe1\u606f\uff0c\u65e0\u6cd5\u9884\u4e0b\u8f7d\u3002");
                jDCacheLog.e(getName(), "Cannot pre-download html cause ua is null!");
            }
            OfflineFiles offlineFiles = this.offlineFiles;
            OfflineExceptionUtils.reportDownloadError(OfflineExceptionUtils.ERR_MSG_CODE, "PreloadMatcher#preloadHtml-ua", offlineFiles != null ? offlineFiles.getAppId() : null, url, "Cannot pre-download html cause ua is null!");
            return;
        }
        try {
            Uri parse = Uri.parse(url);
            Intrinsics.checkExpressionValueIsNotNull(parse, "Uri.parse(url)");
            str = parse.getLastPathSegment();
        } catch (Exception e2) {
            JDCacheLog jDCacheLog2 = JDCacheLog.INSTANCE;
            if (jDCacheLog2.getCanLog()) {
                jDCacheLog2.e(getName(), e2);
            }
            str = null;
        }
        if (str == null || str.length() == 0) {
            str = FileUtils.getRandomFileName();
        }
        StringBuilder sb = new StringBuilder();
        sb.append("temp");
        String str2 = File.separator;
        sb.append(str2);
        sb.append(System.currentTimeMillis());
        sb.append(str2);
        sb.append(str);
        String sb2 = sb.toString();
        setDownloadUrl(url);
        JDCacheParamsProvider globalParams2 = JDCache.INSTANCE.getGlobalParams();
        String cookie = globalParams2 != null ? globalParams2.getCookie(url) : null;
        JDCacheLog jDCacheLog3 = JDCacheLog.INSTANCE;
        if (jDCacheLog3.getCanLog()) {
            String name = getName();
            StringBuilder sb3 = new StringBuilder();
            sb3.append("\u9879\u76ee(id:");
            OfflineFiles offlineFiles2 = this.offlineFiles;
            sb3.append(offlineFiles2 != null ? offlineFiles2.getAppId() : null);
            sb3.append(")\u5f00\u542f\u4e86HTML\u9884\u4e0b\u8f7d\u529f\u80fd\uff0c\u5f00\u59cb\u4e0b\u8f7d\u6587\u4ef6\u3002");
            Log.xLogD(name, sb3.toString());
            jDCacheLog3.d(getName(), "Preload html is enable, try to download html file = " + url + ",\n ua = " + userAgent + "\n cookie = " + cookie);
        }
        Job launchCoroutine$default = CoroutineHelper.launchCoroutine$default(CoroutineHelper.INSTANCE, this, null, new PreloadMatcher$downloadHtmlFile$job$1(this, header, userAgent, cookie, url, sb2, null), 1, null);
        setWaitingChannel(ChannelKt.Channel(-1));
        setDownloadTask(new CancellableJob(launchCoroutine$default));
    }

    @Override // com.jd.jdcache.match.impl.PreloadHtmlMatcher
    protected void downloadHtmlStream(@NotNull String url, @Nullable Map<String, String> header) {
        JDCache jDCache = JDCache.INSTANCE;
        JDCacheParamsProvider globalParams = jDCache.getGlobalParams();
        String userAgent = globalParams != null ? globalParams.getUserAgent(url) : null;
        if (userAgent == null || userAgent.length() == 0) {
            JDCacheLog jDCacheLog = JDCacheLog.INSTANCE;
            if (jDCacheLog.getCanLog()) {
                Log.xLogEForDev(getName(), "\u5f00\u542f\u4e86\u9884\u4e0b\u8f7dhtml\u529f\u80fd\uff0c\u4f46\u672a\u83b7\u53d6\u5230UserAgent\u4fe1\u606f\uff0c\u65e0\u6cd5\u9884\u4e0b\u8f7d\u3002");
                jDCacheLog.e(getName(), "Cannot pre-download html cause ua is null!");
            }
            OfflineFiles offlineFiles = this.offlineFiles;
            OfflineExceptionUtils.reportDownloadError(OfflineExceptionUtils.ERR_MSG_CODE, "PreloadMatcher#preloadHtml-ua", offlineFiles != null ? offlineFiles.getAppId() : null, url, "Cannot pre-download html cause ua is null!");
            return;
        }
        setDownloadUrl(url);
        JDCacheParamsProvider globalParams2 = jDCache.getGlobalParams();
        String cookie = globalParams2 != null ? globalParams2.getCookie(url) : null;
        JDCacheLog jDCacheLog2 = JDCacheLog.INSTANCE;
        if (jDCacheLog2.getCanLog()) {
            String name = getName();
            StringBuilder sb = new StringBuilder();
            sb.append("\u9879\u76ee(id:");
            OfflineFiles offlineFiles2 = this.offlineFiles;
            sb.append(offlineFiles2 != null ? offlineFiles2.getAppId() : null);
            sb.append(")\u5f00\u542f\u4e86HTML\u9884\u4e0b\u8f7d\u529f\u80fd\uff0c\u5f00\u59cb\u9884\u94fe\u63a5\u3002");
            Log.xLogD(name, sb.toString());
            jDCacheLog2.d(getName(), "Preload html is enable, try to download html stream = " + url + ",\n ua = " + userAgent + "\n cookie = " + cookie);
        }
        Job launchCoroutine$default = CoroutineHelper.launchCoroutine$default(CoroutineHelper.INSTANCE, this, null, new PreloadMatcher$downloadHtmlStream$job$1(this, header, userAgent, cookie, url, null), 1, null);
        setWaitingChannel(ChannelKt.Channel(-1));
        setDownloadTask(new CancellableJob(launchCoroutine$default));
    }

    @Override // com.jd.jdcache.match.impl.PreloadHtmlMatcher
    @Nullable
    protected JDCacheLocalResp geDownloadLocalResp() {
        Object runBlocking$default;
        if (getWaitingChannel() != null) {
            runBlocking$default = BuildersKt__BuildersKt.runBlocking$default(null, new PreloadMatcher$geDownloadLocalResp$$inlined$let$lambda$1(null, this), 1, null);
            return (JDCacheLocalResp) runBlocking$default;
        }
        return null;
    }

    @Nullable
    public final OfflineFiles getOfflineFiles() {
        return this.offlineFiles;
    }

    @Override // com.jd.jdcache.match.impl.PreloadHtmlMatcher, com.jd.jdcache.match.base.JDCacheResourceMatcher
    @Nullable
    public WebResourceResponse match(@NotNull WebResourceRequest request) {
        Uri parse;
        WebResourceResponse createResponse;
        InputStream fileStream;
        JDCacheLoader loader = getLoader();
        if (loader == null || !loader.getPreloadHtml() || Build.VERSION.SDK_INT < 21 || getDestroyed().get() || !request.isForMainFrame()) {
            return null;
        }
        if (getDownloadUrl() != null) {
            try {
                parse = Uri.parse(getDownloadUrl());
            } catch (Exception unused) {
            }
            if (parse == null && UrlHelper.INSTANCE.matchHostPath(request.getUrl(), parse)) {
                setDownloadUrl(null);
                JDCacheLog jDCacheLog = JDCacheLog.INSTANCE;
                if (jDCacheLog.getCanLog()) {
                    Log.xLogDForDev(getName(), "\u5f00\u542f\u5e76\u6267\u884c\u4e86\u9884\u4e0b\u8f7dhtml\uff0c\u53bb\u83b7\u53d6\u9884\u4e0b\u8f7d\u7684html");
                }
                setLocalResp(geDownloadLocalResp());
                if (getDestroyed().get()) {
                    return null;
                }
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(XView2Constants.STATE, this.state);
                jSONObject.put("start", this.start);
                jSONObject.put("end", this.end);
                OfflineFiles offlineFiles = this.offlineFiles;
                jSONObject.put("id", offlineFiles != null ? offlineFiles.getAppId() : null);
                JDCacheLoader loader2 = getLoader();
                if (loader2 != null) {
                    loader2.sendMessageData(257, jSONObject);
                }
                JDCacheLocalResp localResp = getLocalResp();
                if (localResp != null && (fileStream = localResp.getFileStream()) != null && (fileStream instanceof PreReadInputStream)) {
                    ((PreReadInputStream) fileStream).finishPreRead();
                }
                if (jDCacheLog.getCanLog() && getLocalResp() != null) {
                    jDCacheLog.d(getName(), "Received pre-download html file. " + getLocalResp());
                }
                JDCacheLocalResp localResp2 = getLocalResp();
                if (localResp2 == null || (createResponse = JDCacheLocalRespKt.createResponse(localResp2, null)) == null) {
                    return null;
                }
                LocalResourceResponse localResourceResponse = new LocalResourceResponse(createResponse);
                localResourceResponse.setFromType(LocalFileType.FILE_TYPE_HTML_PRELOAD);
                localResourceResponse.setLocalFile(getLocalResp());
                return localResourceResponse;
            }
        }
        parse = null;
        return parse == null ? null : null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jd.jdcache.match.impl.PreloadHtmlMatcher, com.jd.jdcache.match.base.JDCacheResourceMatcher
    public void onDestroy() {
        String filename;
        String parent;
        JDCacheFileRepoDelegate fileRepo;
        try {
            JDCacheLocalResp localResp = getLocalResp();
            if (localResp != null && (filename = localResp.getFilename()) != null && (parent = new File(filename).getParent()) != null && (fileRepo = getFileRepo()) != null) {
                fileRepo.deleteFile(parent);
            }
            super.onDestroy();
            OfflineFiles offlineFiles = this.offlineFiles;
            if (offlineFiles != null) {
                offlineFiles.destroy();
            }
            this.offlineFiles = null;
        } catch (Exception e2) {
            JDCacheLog jDCacheLog = JDCacheLog.INSTANCE;
            if (jDCacheLog.getCanLog()) {
                jDCacheLog.e(getName(), e2);
            }
            OfflineFiles offlineFiles2 = this.offlineFiles;
            OfflineExceptionUtils.reportMatchError(OfflineExceptionUtils.ERR_MSG_CODE, "PreloadMatcher#onDestroy", offlineFiles2 != null ? offlineFiles2.getAppId() : null, (String) null, e2);
        }
    }

    @Override // com.jd.jdcache.match.impl.PreloadHtmlMatcher, com.jd.jdcache.match.base.JDCacheResourceMatcher
    public void prepare(@NotNull String url) {
    }

    public final void startPreload(@NotNull OfflineFiles offlineFiles, @Nullable String url) {
        Map<String, String> mutableMapOf;
        JDCacheLoader loader = getLoader();
        if (loader == null || !loader.getPreloadHtml()) {
            return;
        }
        this.offlineFiles = offlineFiles;
        if (url == null || url.length() == 0) {
            return;
        }
        mutableMapOf = MapsKt__MapsKt.mutableMapOf(TuplesKt.to("Request-From", "jdhybrid-htmlDownload"));
        try {
            if (GraySwitch.fileForPreloadHtml) {
                downloadHtmlFile(url, mutableMapOf);
            } else {
                downloadHtmlStream(url, mutableMapOf);
            }
        } catch (Exception e2) {
            JDCacheLog jDCacheLog = JDCacheLog.INSTANCE;
            if (jDCacheLog.getCanLog()) {
                jDCacheLog.e(getName(), e2);
            }
            OfflineExceptionUtils.reportDownloadError(OfflineExceptionUtils.ERR_MSG_CODE, "PreloadMatcher#startPreload", offlineFiles.getAppId(), url, e2);
        }
    }
}
