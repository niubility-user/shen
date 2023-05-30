package com.jd.libs.hybrid.jdcache;

import androidx.annotation.Keep;
import com.jd.jdcache.JDCacheParamsProvider;
import com.jd.lib.babel.servicekit.iservice.BabelLoginCallback;
import com.jd.libs.hybrid.HybridSDK;
import com.jd.libs.hybrid.base.HybridBase;
import com.jd.libs.hybrid.base.util.Log;
import com.jd.libs.hybrid.offlineload.utils.OfflineFileHelper;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Keep
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000b\n\u0002\b\n\b\u0007\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0011\u0010\u0012J\u001b\u0010\u0004\u001a\u0004\u0018\u00010\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u0016\u00a2\u0006\u0004\b\u0004\u0010\u0005J\u0019\u0010\u0006\u001a\u00020\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u0016\u00a2\u0006\u0004\b\u0006\u0010\u0005J)\u0010\n\u001a\u00020\t2\b\u0010\u0003\u001a\u0004\u0018\u00010\u00022\u000e\u0010\b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0007H\u0016\u00a2\u0006\u0004\b\n\u0010\u000bJ\u000f\u0010\f\u001a\u00020\tH\u0016\u00a2\u0006\u0004\b\f\u0010\rR\u0018\u0010\u0010\u001a\u0004\u0018\u00010\u00028V@\u0016X\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\u000e\u0010\u000f\u00a8\u0006\u0013"}, d2 = {"Lcom/jd/libs/hybrid/jdcache/CacheParamsProvider;", "Lcom/jd/jdcache/JDCacheParamsProvider;", "", "url", "getUserAgent", "(Ljava/lang/String;)Ljava/lang/String;", "getCookie", "", BabelLoginCallback.KEY_COOKIES, "", "saveCookie", "(Ljava/lang/String;Ljava/util/List;)Z", "showLog", "()Z", "getCacheDir", "()Ljava/lang/String;", "cacheDir", "<init>", "()V", "hybrid_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes16.dex */
public final class CacheParamsProvider extends JDCacheParamsProvider {
    @Override // com.jd.jdcache.JDCacheParamsProvider
    @Nullable
    public String getCacheDir() {
        return OfflineFileHelper.getSourceDir(null);
    }

    @Override // com.jd.jdcache.JDCacheParamsProvider
    @NotNull
    public String getCookie(@Nullable String url) {
        String cookieString = HybridBase.getInstance().getCookieString(url);
        Intrinsics.checkExpressionValueIsNotNull(cookieString, "HybridBase.getInstance().getCookieString(url)");
        return cookieString;
    }

    @Override // com.jd.jdcache.JDCacheParamsProvider
    @Nullable
    public String getUserAgent(@Nullable String url) {
        return HybridSDK.getSetting("userAgent");
    }

    @Override // com.jd.jdcache.JDCacheParamsProvider
    public boolean saveCookie(@Nullable String url, @NotNull List<String> cookies) {
        return HybridBase.getInstance().saveCookieString(url, cookies);
    }

    @Override // com.jd.jdcache.JDCacheParamsProvider
    public boolean showLog() {
        return Log.isDebug();
    }
}
