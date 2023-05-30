package com.jd.libs.hybrid.jdcache;

import android.content.Context;
import androidx.annotation.Keep;
import com.jd.jdcache.JDCache;
import com.jd.libs.hybrid.base.HybridSettings;
import com.jd.libs.hybrid.jdcache.delegate.NetDelegate;
import com.jd.libs.hybrid.offlineload.utils.OfflineExceptionUtils;
import com.jingdong.amon.router.annotation.AnnoConst;
import com.jingdong.common.XView2.common.XView2Constants;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Keep
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\b\u00c7\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0007\u0010\bJ\u0015\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0005\u0010\u0006\u00a8\u0006\t"}, d2 = {"Lcom/jd/libs/hybrid/jdcache/JDCacheHelper;", "", "Landroid/content/Context;", AnnoConst.Constructor_Context, "", XView2Constants.XVIEW2_ACTION_INIT, "(Landroid/content/Context;)V", "<init>", "()V", "hybrid_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes16.dex */
public final class JDCacheHelper {
    public static final JDCacheHelper INSTANCE = new JDCacheHelper();

    private JDCacheHelper() {
    }

    public final void init(@NotNull Context context) {
        try {
            JDCache jDCache = JDCache.INSTANCE;
            jDCache.setLogger(new CacheLogger());
            jDCache.init(context, HybridSettings.isDebug());
            jDCache.setGlobalParams(CacheParamsProvider.class);
            jDCache.registerService(NetDelegate.class);
        } catch (Exception e2) {
            OfflineExceptionUtils.reportError("hybrid_init", OfflineExceptionUtils.ERR_MSG_CODE, "JDCacheHelper#init", e2.getMessage());
        }
    }
}
