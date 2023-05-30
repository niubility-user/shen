package com.jd.libs.hybrid.offlineload.jdcache;

import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import androidx.annotation.Keep;
import com.jd.jdcache.entity.JDCacheLocalResp;
import com.jd.jdcache.match.base.JDCacheResourceMatcher;
import com.jd.libs.hybrid.offlineload.entity.LocalFileType;
import com.jd.libs.hybrid.offlineload.temp.CommonResEngine;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Keep
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0007\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0011\u0010\u0012J\u0019\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0002\u00a2\u0006\u0004\b\u0005\u0010\u0006J\u0019\u0010\n\u001a\u0004\u0018\u00010\t2\u0006\u0010\b\u001a\u00020\u0007H\u0016\u00a2\u0006\u0004\b\n\u0010\u000bR\u001c\u0010\r\u001a\u00020\f8\u0016@\u0016X\u0096D\u00a2\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010\u00a8\u0006\u0013"}, d2 = {"Lcom/jd/libs/hybrid/offlineload/jdcache/GlobalBuildInResourceMatcher;", "Lcom/jd/jdcache/match/base/JDCacheResourceMatcher;", "Landroid/net/Uri;", "loadedUri", "Lcom/jd/jdcache/entity/JDCacheLocalResp;", "getBuildInGlobalFile", "(Landroid/net/Uri;)Lcom/jd/jdcache/entity/JDCacheLocalResp;", "Landroid/webkit/WebResourceRequest;", "request", "Landroid/webkit/WebResourceResponse;", "match", "(Landroid/webkit/WebResourceRequest;)Landroid/webkit/WebResourceResponse;", "", "name", "Ljava/lang/String;", "getName", "()Ljava/lang/String;", "<init>", "()V", "offlineload_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes16.dex */
public final class GlobalBuildInResourceMatcher extends JDCacheResourceMatcher {
    @NotNull
    private final String name = "GlobalBuildInMatcher";

    private final JDCacheLocalResp getBuildInGlobalFile(Uri loadedUri) {
        String buildInGlobalFileName = CommonResEngine.getBuildInGlobalFileName(loadedUri);
        if (TextUtils.isEmpty(buildInGlobalFileName)) {
            return null;
        }
        String uri = loadedUri.toString();
        Intrinsics.checkExpressionValueIsNotNull(uri, "loadedUri.toString()");
        JDCacheLocalResp jDCacheLocalResp = new JDCacheLocalResp(uri, "", null, null, null, false, 60, null);
        jDCacheLocalResp.setFilename(buildInGlobalFileName);
        return jDCacheLocalResp;
    }

    @Override // com.jd.jdcache.match.base.JDCacheResourceMatcher
    @NotNull
    public String getName() {
        return this.name;
    }

    @Override // com.jd.jdcache.match.base.JDCacheResourceMatcher
    @Nullable
    public WebResourceResponse match(@NotNull WebResourceRequest request) {
        WebResourceResponse it;
        if (!getDestroyed().get() && Build.VERSION.SDK_INT >= 21) {
            Uri url = request.getUrl();
            Intrinsics.checkExpressionValueIsNotNull(url, "request.url");
            JDCacheLocalResp buildInGlobalFile = getBuildInGlobalFile(url);
            if (buildInGlobalFile == null || (it = CommonResEngine.getResponse(request.getUrl(), buildInGlobalFile.getFilename())) == null) {
                return null;
            }
            Intrinsics.checkExpressionValueIsNotNull(it, "it");
            LocalResourceResponse localResourceResponse = new LocalResourceResponse(it);
            localResourceResponse.setFromType(LocalFileType.FILE_TYPE_GLOBAL_BUILD_IN);
            localResourceResponse.setLocalFile(buildInGlobalFile);
            return localResourceResponse;
        }
        return null;
    }
}
