package com.jd.jdcache.match.impl;

import android.net.Uri;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import androidx.annotation.Keep;
import androidx.annotation.WorkerThread;
import com.jd.jdcache.JDCacheParamsProvider;
import com.jd.jdcache.JDCacheSetting;
import com.jd.jdcache.entity.JDCacheDataSource;
import com.jd.jdcache.entity.JDCacheLocalResp;
import com.jd.jdcache.entity.JDCacheLocalRespKt;
import com.jd.jdcache.match.base.JDCacheResourceMatcher;
import com.jd.jdcache.util.CancellableJob;
import com.jd.jdcache.util.CoroutineHelper;
import com.jd.jdcache.util.ICancellable;
import com.jd.jdcache.util.UrlHelper;
import com.jingdong.common.jdreactFramework.JDReactConstant;
import com.tencent.smtt.sdk.TbsReaderView;
import java.io.File;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Keep
@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\b\b\u0017\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u001f\u0010\u0012J\u0017\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0002\u00a2\u0006\u0004\b\u0005\u0010\u0006J\u0019\u0010\t\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0007\u001a\u00020\u0002H\u0016\u00a2\u0006\u0004\b\t\u0010\nJ\u0017\u0010\u000b\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0002H\u0016\u00a2\u0006\u0004\b\u000b\u0010\u0006J\u0019\u0010\u000f\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\r\u001a\u00020\fH\u0017\u00a2\u0006\u0004\b\u000f\u0010\u0010J\u000f\u0010\u0011\u001a\u00020\u0004H\u0014\u00a2\u0006\u0004\b\u0011\u0010\u0012R$\u0010\u0013\u001a\u0004\u0018\u00010\b8\u0004@\u0004X\u0084\u000e\u00a2\u0006\u0012\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\t\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u0018\u0010\u0019\u001a\u0004\u0018\u00010\u00188\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0019\u0010\u001aR\u001c\u0010\u001b\u001a\u00020\u00028\u0016@\u0016X\u0096D\u00a2\u0006\f\n\u0004\b\u001b\u0010\u001c\u001a\u0004\b\u001d\u0010\u001e\u00a8\u0006 "}, d2 = {"Lcom/jd/jdcache/match/impl/MapResourceMatcher;", "Lcom/jd/jdcache/match/base/JDCacheResourceMatcher;", "", TbsReaderView.KEY_FILE_PATH, "", "readResMapFromJsonFile", "(Ljava/lang/String;)V", "url", "Lcom/jd/jdcache/entity/JDCacheDataSource;", "getDataSource", "(Ljava/lang/String;)Lcom/jd/jdcache/entity/JDCacheDataSource;", JDReactConstant.PREPARE, "Landroid/webkit/WebResourceRequest;", "request", "Landroid/webkit/WebResourceResponse;", "match", "(Landroid/webkit/WebResourceRequest;)Landroid/webkit/WebResourceResponse;", "onDestroy", "()V", "dataSource", "Lcom/jd/jdcache/entity/JDCacheDataSource;", "()Lcom/jd/jdcache/entity/JDCacheDataSource;", "setDataSource", "(Lcom/jd/jdcache/entity/JDCacheDataSource;)V", "Lcom/jd/jdcache/util/ICancellable;", "readMapTask", "Lcom/jd/jdcache/util/ICancellable;", "name", "Ljava/lang/String;", "getName", "()Ljava/lang/String;", "<init>", "JDCache_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes13.dex */
public class MapResourceMatcher extends JDCacheResourceMatcher {
    @Nullable
    private JDCacheDataSource dataSource;
    @NotNull
    private final String name = "MapResourceMatcher";
    private ICancellable readMapTask;

    private final void readResMapFromJsonFile(String r7) {
        this.readMapTask = new CancellableJob(CoroutineHelper.launchCoroutine$default(CoroutineHelper.INSTANCE, this, null, new MapResourceMatcher$readResMapFromJsonFile$job$1(this, r7, null), 1, null));
    }

    @Nullable
    public final JDCacheDataSource getDataSource() {
        return this.dataSource;
    }

    @Override // com.jd.jdcache.match.base.JDCacheResourceMatcher
    @NotNull
    public String getName() {
        return this.name;
    }

    @Override // com.jd.jdcache.match.base.JDCacheResourceMatcher
    @WorkerThread
    @Nullable
    public WebResourceResponse match(@NotNull WebResourceRequest request) {
        JDCacheLocalResp jDCacheLocalResp;
        WebResourceResponse createResponse;
        JDCacheDataSource jDCacheDataSource = this.dataSource;
        if (jDCacheDataSource != null) {
            HashMap<String, JDCacheLocalResp> localFileMap = jDCacheDataSource.getLocalFileMap();
            if (localFileMap != null) {
                UrlHelper urlHelper = UrlHelper.INSTANCE;
                Uri url = request.getUrl();
                Intrinsics.checkExpressionValueIsNotNull(url, "request.url");
                jDCacheLocalResp = localFileMap.get(urlHelper.urlToKey(url));
            } else {
                jDCacheLocalResp = null;
            }
            if (jDCacheLocalResp == null || (createResponse = JDCacheLocalRespKt.createResponse(jDCacheLocalResp, jDCacheDataSource.getLocalFileDirDetail().getPath())) == null) {
                return null;
            }
            return createResponse(createResponse);
        }
        return null;
    }

    @Override // com.jd.jdcache.match.base.JDCacheResourceMatcher
    public void onDestroy() {
        super.onDestroy();
        ICancellable iCancellable = this.readMapTask;
        if (iCancellable != null) {
            ICancellable.DefaultImpls.cancel$default(iCancellable, null, 1, null);
        }
    }

    @Override // com.jd.jdcache.match.base.JDCacheResourceMatcher
    public void prepare(@NotNull String str) {
        JDCacheDataSource dataSource = getDataSource(str);
        this.dataSource = dataSource;
        if (dataSource != null && dataSource.getLocalFileMap() == null && dataSource.getLocalFileDirDetail().exists()) {
            readResMapFromJsonFile(dataSource.getLocalFileDirDetail().getPath() + File.separator + "resource.json");
        }
    }

    public final void setDataSource(@Nullable JDCacheDataSource jDCacheDataSource) {
        this.dataSource = jDCacheDataSource;
    }

    @Nullable
    public JDCacheDataSource getDataSource(@NotNull String url) {
        JDCacheParamsProvider paramsProvider = JDCacheSetting.INSTANCE.getParamsProvider();
        if (paramsProvider != null) {
            return paramsProvider.sourceWithUrl(url, getLoader());
        }
        return null;
    }
}
