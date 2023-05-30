package com.jd.libs.hybrid.offlineload.jdcache;

import android.net.Uri;
import android.os.Build;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import androidx.annotation.Keep;
import com.jd.jdcache.JDCacheLoader;
import com.jd.jdcache.entity.JDCacheDataSource;
import com.jd.jdcache.entity.JDCacheLocalResp;
import com.jd.jdcache.entity.JDCacheLocalRespKt;
import com.jd.jdcache.match.impl.MapResourceMatcher;
import com.jd.jdcache.util.JDCacheLog;
import com.jd.jdcache.util.UrlHelper;
import com.jd.libs.hybrid.base.util.Log;
import com.jd.libs.hybrid.offlineload.XDogListener;
import com.jd.libs.hybrid.offlineload.entity.LocalFileType;
import com.jd.libs.hybrid.offlineload.entity.OfflineFiles;
import com.jd.libs.hybrid.offlineload.temp.OfflineSwitchSetting;
import com.jd.libs.hybrid.offlineload.utils.OfflineExceptionUtils;
import com.jd.libs.hybrid.offlineload.utils.OfflineFileHelper;
import com.jingdong.common.jdreactFramework.JDReactConstant;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Keep
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\t\b'\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b3\u0010\u000fJ\u0017\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0003\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0005\u0010\u0006J\u0017\u0010\b\u001a\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u0002H\u0016\u00a2\u0006\u0004\b\b\u0010\tJ\u0017\u0010\f\u001a\u00020\u00072\b\u0010\u000b\u001a\u0004\u0018\u00010\n\u00a2\u0006\u0004\b\f\u0010\rJ\r\u0010\u000e\u001a\u00020\u0007\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u001f\u0010\u0013\u001a\u00020\u00072\b\u0010\u0010\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0012\u001a\u00020\u0011\u00a2\u0006\u0004\b\u0013\u0010\u0014J\u0019\u0010\u0018\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u0016\u001a\u00020\u0015H\u0016\u00a2\u0006\u0004\b\u0018\u0010\u0019J\u000f\u0010\u001a\u001a\u00020\u0007H\u0014\u00a2\u0006\u0004\b\u001a\u0010\u000fR*\u0010\u001d\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001b8\u0006@DX\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u001d\u0010\u001e\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R.\u0010#\u001a\u0004\u0018\u00010\n2\b\u0010\u001c\u001a\u0004\u0018\u00010\n8\u0006@DX\u0086\u000e\u00a2\u0006\u0012\n\u0004\b#\u0010$\u001a\u0004\b%\u0010&\"\u0004\b'\u0010\rR\u0016\u0010*\u001a\u00020\u00028&@&X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b(\u0010)R\u0016\u0010.\u001a\u00020+8&@&X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b,\u0010-R$\u0010/\u001a\u0004\u0018\u00010\u00028\u0004@\u0004X\u0084\u000e\u00a2\u0006\u0012\n\u0004\b/\u00100\u001a\u0004\b1\u0010)\"\u0004\b2\u0010\t\u00a8\u00064"}, d2 = {"Lcom/jd/libs/hybrid/offlineload/jdcache/PackageMatcher;", "Lcom/jd/jdcache/match/impl/MapResourceMatcher;", "", "url", "Lcom/jd/jdcache/entity/JDCacheDataSource;", "getDataSource", "(Ljava/lang/String;)Lcom/jd/jdcache/entity/JDCacheDataSource;", "", JDReactConstant.PREPARE, "(Ljava/lang/String;)V", "Lcom/jd/libs/hybrid/offlineload/entity/OfflineFiles;", "files", "onCacheConfig", "(Lcom/jd/libs/hybrid/offlineload/entity/OfflineFiles;)V", "onFilesAvailable", "()V", "newFiles", "", "reload", "onLatestConfig", "(Lcom/jd/libs/hybrid/offlineload/entity/OfflineFiles;Z)V", "Landroid/webkit/WebResourceRequest;", "request", "Landroid/webkit/WebResourceResponse;", "match", "(Landroid/webkit/WebResourceRequest;)Landroid/webkit/WebResourceResponse;", "onDestroy", "Ljava/util/concurrent/atomic/AtomicBoolean;", "<set-?>", "fileHit", "Ljava/util/concurrent/atomic/AtomicBoolean;", "getFileHit", "()Ljava/util/concurrent/atomic/AtomicBoolean;", "setFileHit", "(Ljava/util/concurrent/atomic/AtomicBoolean;)V", "offlineFiles", "Lcom/jd/libs/hybrid/offlineload/entity/OfflineFiles;", "getOfflineFiles", "()Lcom/jd/libs/hybrid/offlineload/entity/OfflineFiles;", "setOfflineFiles", "getLogPrefix", "()Ljava/lang/String;", "logPrefix", "Lcom/jd/libs/hybrid/offlineload/entity/LocalFileType;", "getFromType", "()Lcom/jd/libs/hybrid/offlineload/entity/LocalFileType;", "fromType", "targetUrl", "Ljava/lang/String;", "getTargetUrl", "setTargetUrl", "<init>", "offlineload_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes16.dex */
public abstract class PackageMatcher extends MapResourceMatcher {
    @NotNull
    private AtomicBoolean fileHit = new AtomicBoolean(false);
    @Nullable
    private OfflineFiles offlineFiles;
    @Nullable
    private String targetUrl;

    @Override // com.jd.jdcache.match.impl.MapResourceMatcher
    @Nullable
    public final JDCacheDataSource getDataSource(@NotNull String url) {
        return null;
    }

    @NotNull
    public final AtomicBoolean getFileHit() {
        return this.fileHit;
    }

    @NotNull
    public abstract LocalFileType getFromType();

    @NotNull
    public abstract String getLogPrefix();

    @Nullable
    public final OfflineFiles getOfflineFiles() {
        return this.offlineFiles;
    }

    @Nullable
    protected final String getTargetUrl() {
        return this.targetUrl;
    }

    @Override // com.jd.jdcache.match.impl.MapResourceMatcher, com.jd.jdcache.match.base.JDCacheResourceMatcher
    @Nullable
    public WebResourceResponse match(@NotNull WebResourceRequest request) {
        LocalResourceResponse localResourceResponse;
        boolean contains$default;
        List split$default;
        if (Build.VERSION.SDK_INT < 21) {
            return null;
        }
        if (request.isForMainFrame()) {
            this.fileHit.set(false);
        }
        Uri url = request.getUrl();
        if (url != null) {
            try {
                OfflineFiles offlineFiles = this.offlineFiles;
                if (offlineFiles != null && offlineFiles.isAvailable()) {
                    JDCacheDataSource dataSource = getDataSource();
                    if ((dataSource != null ? dataSource.getLocalFileMap() : null) == null) {
                        JDCacheLog jDCacheLog = JDCacheLog.INSTANCE;
                        if (jDCacheLog.getCanLog()) {
                            jDCacheLog.e(getName(), getLogPrefix() + " [Web-Match] Error: Offline files are available but file map is empty.");
                        }
                        return null;
                    }
                    if (!OfflineSwitchSetting.TYPE_4_PIC_COMPRESS_OFF) {
                        String uri = url.toString();
                        Intrinsics.checkExpressionValueIsNotNull(uri, "uri.toString()");
                        contains$default = StringsKt__StringsKt.contains$default((CharSequence) uri, (CharSequence) "!", false, 2, (Object) null);
                        if (contains$default) {
                            split$default = StringsKt__StringsKt.split$default((CharSequence) uri, new String[]{"!"}, false, 0, 6, (Object) null);
                            String str = (String) split$default.get(0);
                            if (str.length() > 0) {
                                Uri parse = Uri.parse(str);
                                Intrinsics.checkExpressionValueIsNotNull(parse, "Uri.parse(rawUrl)");
                                url = parse;
                            }
                        }
                    }
                    HashMap<String, JDCacheLocalResp> localFileMap = dataSource.getLocalFileMap();
                    JDCacheLocalResp jDCacheLocalResp = localFileMap != null ? localFileMap.get(UrlHelper.INSTANCE.urlToKey(url)) : null;
                    if (jDCacheLocalResp == null) {
                        return null;
                    }
                    CacheLoaderKt.addResponseMaxAge(jDCacheLocalResp);
                    WebResourceResponse createResponse = JDCacheLocalRespKt.createResponse(jDCacheLocalResp, dataSource.getLocalFileDirDetail().getPath());
                    if (createResponse != null) {
                        localResourceResponse = new LocalResourceResponse(createResponse);
                        localResourceResponse.setFromType(getFromType());
                        localResourceResponse.setLocalFile(jDCacheLocalResp);
                    } else {
                        localResourceResponse = null;
                    }
                    if (localResourceResponse == null) {
                        JDCacheLog jDCacheLog2 = JDCacheLog.INSTANCE;
                        if (jDCacheLog2.getCanLog()) {
                            jDCacheLog2.e(getName(), getLogPrefix() + " [Web-Match] Offline local file does NOT exist for url[" + url + "], File path [" + jDCacheLocalResp.getFilename() + ']');
                        }
                        return null;
                    }
                    this.fileHit.set(true);
                    return localResourceResponse;
                }
                return null;
            } catch (Exception e2) {
                JDCacheLog jDCacheLog3 = JDCacheLog.INSTANCE;
                if (jDCacheLog3.getCanLog()) {
                    jDCacheLog3.e(getName(), e2);
                }
                OfflineExceptionUtils.reportMatchError(OfflineExceptionUtils.ERR_MSG_CODE, "PkgMatcher#match", "", url.toString(), e2);
            }
        }
        return null;
    }

    public final void onCacheConfig(@Nullable OfflineFiles files) {
        if (getDestroyed().get()) {
            return;
        }
        this.offlineFiles = null;
        setDataSource(null);
        if (files == null) {
            JDCacheLog jDCacheLog = JDCacheLog.INSTANCE;
            if (jDCacheLog.getCanLog()) {
                jDCacheLog.w(getName(), getLogPrefix() + " [Web-Match] Config of url(" + this.targetUrl + ") CANNOT found.");
                return;
            }
            return;
        }
        this.offlineFiles = files;
        JDCacheLog jDCacheLog2 = JDCacheLog.INSTANCE;
        if (jDCacheLog2.getCanLog()) {
            String offlineFiles = files.toString();
            Intrinsics.checkExpressionValueIsNotNull(offlineFiles, "files.toString()");
            jDCacheLog2.w(getName(), getLogPrefix() + " [Web-Match] Found a config of url(" + this.targetUrl + "). Local files info: " + offlineFiles);
            Log.xLogD(getName(), getLogPrefix() + "\uff1a\u6210\u529f\u627e\u5230\u79bb\u7ebf\u5305\u914d\u7f6e(id:" + files.getAppId() + ")\uff0cURL\uff1a" + this.targetUrl + "\uff0c\u672c\u5730\u914d\u7f6e\uff1a", offlineFiles);
        }
    }

    @Override // com.jd.jdcache.match.impl.MapResourceMatcher, com.jd.jdcache.match.base.JDCacheResourceMatcher
    protected void onDestroy() {
        try {
            super.onDestroy();
            OfflineFiles offlineFiles = this.offlineFiles;
            if (offlineFiles != null) {
                OfflineFileHelper.deleteUsedFile(offlineFiles.getFileRootPath());
                offlineFiles.destroy();
            }
        } catch (Exception e2) {
            JDCacheLog jDCacheLog = JDCacheLog.INSTANCE;
            if (jDCacheLog.getCanLog()) {
                jDCacheLog.e(getName(), e2);
            }
            OfflineFiles offlineFiles2 = this.offlineFiles;
            OfflineExceptionUtils.reportMatchError(OfflineExceptionUtils.ERR_MSG_CODE, "PkgMatcher#onDestroy", offlineFiles2 != null ? offlineFiles2.getAppId() : null, (String) null, e2);
        }
        this.offlineFiles = null;
    }

    public final void onFilesAvailable() {
        OfflineFiles offlineFiles;
        if (getDestroyed().get() || (offlineFiles = this.offlineFiles) == null) {
            return;
        }
        if (offlineFiles.isAvailable()) {
            try {
                setDataSource(DataSourceHelper.INSTANCE.convertToDataSource(offlineFiles));
                JDCacheLog jDCacheLog = JDCacheLog.INSTANCE;
                if (jDCacheLog.getCanLog()) {
                    String offlineFiles2 = offlineFiles.toString();
                    Intrinsics.checkExpressionValueIsNotNull(offlineFiles2, "files.toString()");
                    jDCacheLog.w(getName(), getLogPrefix() + " [Web-Match] Id(" + offlineFiles.getAppId() + ")'s offline local files are ready. Info: " + offlineFiles2);
                    Log.xLogD(getName(), getLogPrefix() + "\uff1a\u79bb\u7ebf\u5305(id:" + offlineFiles.getAppId() + ")\u7684\u79bb\u7ebf\u6587\u4ef6\u5df2\u53ef\u7528\uff0c\u672c\u5730\u914d\u7f6e\uff1a", offlineFiles2);
                    XDogListener.xLocalFileListStr(offlineFiles);
                }
                OfflineFileHelper.addFileInUsingState(offlineFiles.getFileRootPath());
                return;
            } catch (Exception e2) {
                JDCacheLog jDCacheLog2 = JDCacheLog.INSTANCE;
                if (jDCacheLog2.getCanLog()) {
                    jDCacheLog2.e(getName(), e2);
                }
                OfflineExceptionUtils.reportMatchError(OfflineExceptionUtils.ERR_MSG_CODE, "PkgMatcher#onFilesAvailable", offlineFiles.getAppId(), (String) null, e2);
                return;
            }
        }
        JDCacheLog jDCacheLog3 = JDCacheLog.INSTANCE;
        if (jDCacheLog3.getCanLog()) {
            String offlineFiles3 = offlineFiles.toString();
            Intrinsics.checkExpressionValueIsNotNull(offlineFiles3, "files.toString()");
            jDCacheLog3.w(getName(), getLogPrefix() + " [Web-Match] Id(" + offlineFiles.getAppId() + ")'s offline local files are NOT ready yet. Info: " + offlineFiles3);
            Log.xLogD(getName(), getLogPrefix() + "\uff1a\u79bb\u7ebf\u5305(id:" + offlineFiles.getAppId() + ")\u7684\u79bb\u7ebf\u6587\u4ef6\u6682\u672a\u53ef\u7528\uff0c\u672c\u5730\u914d\u7f6e\uff1a", offlineFiles3);
        }
    }

    public final void onLatestConfig(@Nullable OfflineFiles newFiles, boolean reload) {
        if (getDestroyed().get()) {
            return;
        }
        OfflineFiles offlineFiles = this.offlineFiles;
        JDCacheLog jDCacheLog = JDCacheLog.INSTANCE;
        if (jDCacheLog.getCanLog()) {
            StringBuilder sb = new StringBuilder();
            sb.append(getLogPrefix());
            sb.append(" [Web-Match] ConfigCallback for fetching latest (id:");
            sb.append(offlineFiles != null ? offlineFiles.getAppId() : null);
            sb.append(")");
            String sb2 = sb.toString();
            if (newFiles == null) {
                jDCacheLog.d(getName(), sb2 + ", no new local file info");
            } else {
                jDCacheLog.d(getName(), sb2 + ", new local file info = " + newFiles);
            }
        }
        if (reload) {
            try {
                this.fileHit.set(false);
                setDataSource(null);
                this.offlineFiles = newFiles;
                if (offlineFiles != null) {
                    offlineFiles.destroy();
                }
                if (OfflineFileHelper.deleteUsedFile(offlineFiles != null ? offlineFiles.getFileRootPath() : null) && jDCacheLog.getCanLog()) {
                    String name = getName();
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append(getLogPrefix());
                    sb3.append(" [Web-Match] WebView reload online page, deleted old files,");
                    sb3.append(" id: ");
                    sb3.append(offlineFiles != null ? offlineFiles.getAppId() : "");
                    jDCacheLog.d(name, sb3.toString());
                }
                JDCacheLoader loader = getLoader();
                if (loader != null) {
                    loader.sendMessageData(1, null);
                }
            } catch (Exception e2) {
                JDCacheLog jDCacheLog2 = JDCacheLog.INSTANCE;
                if (jDCacheLog2.getCanLog()) {
                    jDCacheLog2.e(getName(), e2);
                }
                OfflineFiles offlineFiles2 = this.offlineFiles;
                OfflineExceptionUtils.reportMatchError(OfflineExceptionUtils.ERR_MSG_CODE, "PkgMatcher#onLatestConfig", offlineFiles2 != null ? offlineFiles2.getAppId() : null, (String) null, e2);
            }
        }
    }

    @Override // com.jd.jdcache.match.impl.MapResourceMatcher, com.jd.jdcache.match.base.JDCacheResourceMatcher
    public void prepare(@NotNull String url) {
        this.targetUrl = url;
    }

    protected final void setFileHit(@NotNull AtomicBoolean atomicBoolean) {
        this.fileHit = atomicBoolean;
    }

    protected final void setOfflineFiles(@Nullable OfflineFiles offlineFiles) {
        this.offlineFiles = offlineFiles;
    }

    protected final void setTargetUrl(@Nullable String str) {
        this.targetUrl = str;
    }
}
