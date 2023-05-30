package com.jd.libs.hybrid.offlineload.jdcache;

import androidx.annotation.Keep;
import com.jd.libs.hybrid.offlineload.entity.LocalFileType;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Keep
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\t\b\u0007\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u000e\u0010\u000fR\u001c\u0010\u0003\u001a\u00020\u00028\u0016@\u0016X\u0096\u0004\u00a2\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006R\u001c\u0010\b\u001a\u00020\u00078\u0016@\u0016X\u0096D\u00a2\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\n\u0010\u000bR\u001c\u0010\f\u001a\u00020\u00078\u0016@\u0016X\u0096D\u00a2\u0006\f\n\u0004\b\f\u0010\t\u001a\u0004\b\r\u0010\u000b\u00a8\u0006\u0010"}, d2 = {"Lcom/jd/libs/hybrid/offlineload/jdcache/SharedPackageMatcher;", "Lcom/jd/libs/hybrid/offlineload/jdcache/PackageMatcher;", "Lcom/jd/libs/hybrid/offlineload/entity/LocalFileType;", "fromType", "Lcom/jd/libs/hybrid/offlineload/entity/LocalFileType;", "getFromType", "()Lcom/jd/libs/hybrid/offlineload/entity/LocalFileType;", "", "name", "Ljava/lang/String;", "getName", "()Ljava/lang/String;", "logPrefix", "getLogPrefix", "<init>", "()V", "offlineload_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes16.dex */
public final class SharedPackageMatcher extends PackageMatcher {
    @NotNull
    private final String name = "SharedPackageMatcher";
    @NotNull
    private final String logPrefix = "\u516c\u5171\u79bb\u7ebf\u5305";
    @NotNull
    private final LocalFileType fromType = LocalFileType.FILE_TYPE_PKG_SHARED;

    @Override // com.jd.libs.hybrid.offlineload.jdcache.PackageMatcher
    @NotNull
    public LocalFileType getFromType() {
        return this.fromType;
    }

    @Override // com.jd.libs.hybrid.offlineload.jdcache.PackageMatcher
    @NotNull
    public String getLogPrefix() {
        return this.logPrefix;
    }

    @Override // com.jd.jdcache.match.impl.MapResourceMatcher, com.jd.jdcache.match.base.JDCacheResourceMatcher
    @NotNull
    public String getName() {
        return this.name;
    }
}
