package com.jd.libs.hybrid.jdcache.delegate;

import androidx.annotation.Keep;
import com.jd.jdcache.service.base.JDCacheNetDelegate;
import com.jd.jdcache.service.base.NetState;
import com.jd.jdcache.service.impl.net.NetConnection;
import com.jd.jdcache.util.JDCacheLog;
import com.jd.libs.hybrid.base.HybridBase;
import com.jd.libs.hybrid.offlineload.utils.FileUtils;
import com.jd.libs.hybrid.offlineload.utils.OfflineFileHelper;
import java.io.File;
import java.io.InputStream;
import java.util.Map;
import kotlin.Metadata;
import kotlin.NotImplementedError;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Keep
@Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\b\u0007\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u001a\u0010\u001bJy\u0010\u000f\u001a\u0012\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\r\u0018\u00010\f2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u00022\u0016\u0010\u0006\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u00052\b\u0010\u0007\u001a\u0004\u0018\u00010\u00022\b\u0010\b\u001a\u0004\u0018\u00010\u00022\u0014\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u00052\u0006\u0010\u000b\u001a\u00020\nH\u0016\u00a2\u0006\u0004\b\u000f\u0010\u0010Ja\u0010\u0013\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\r\u0018\u00010\f2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0011\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u00022\u0016\u0010\u0006\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u00052\b\u0010\u0007\u001a\u0004\u0018\u00010\u00022\b\u0010\b\u001a\u0004\u0018\u00010\u0002H\u0016\u00a2\u0006\u0004\b\u0013\u0010\u0014Jw\u0010\u0015\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\r\u0018\u00010\f2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u00022\u0016\u0010\u0006\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u00052\b\u0010\u0007\u001a\u0004\u0018\u00010\u00022\b\u0010\b\u001a\u0004\u0018\u00010\u00022\u0014\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u00052\u0006\u0010\u000b\u001a\u00020\nH\u0016\u00a2\u0006\u0004\b\u0015\u0010\u0010R\u001c\u0010\u0016\u001a\u00020\u00028\u0016@\u0016X\u0096D\u00a2\u0006\f\n\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0018\u0010\u0019\u00a8\u0006\u001c"}, d2 = {"Lcom/jd/libs/hybrid/jdcache/delegate/NetDelegate;", "Lcom/jd/jdcache/service/base/JDCacheNetDelegate;", "", "url", "method", "", "header", "userAgent", "cookie", "body", "", "followRedirect", "Lkotlinx/coroutines/flow/Flow;", "Lcom/jd/jdcache/service/base/NetState;", "Ljava/io/InputStream;", "connectFlow", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;Z)Lkotlinx/coroutines/flow/Flow;", "savePath", "Ljava/io/File;", "downloadFlow", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;Ljava/lang/String;Ljava/lang/String;)Lkotlinx/coroutines/flow/Flow;", "requestFlow", "name", "Ljava/lang/String;", "getName", "()Ljava/lang/String;", "<init>", "()V", "hybrid_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes16.dex */
public final class NetDelegate extends JDCacheNetDelegate {
    @NotNull
    private final String name = "HybridNetDelegate";

    @Override // com.jd.jdcache.service.base.JDCacheNetDelegate
    @Nullable
    public Flow<NetState<InputStream>> connectFlow(@NotNull String url, @NotNull String method, @Nullable Map<String, String> header, @Nullable String userAgent, @Nullable String cookie, @Nullable Map<String, String> body, boolean followRedirect) {
        if (Intrinsics.areEqual("1", HybridBase.getInstance().getSetting(HybridBase.SWITCH_DOWNLOAD_ADAPTER))) {
            return FlowKt.callbackFlow(new NetDelegate$connectFlow$1(this, url, header, null));
        }
        return new NetConnection().connectFlow(url, method, header, userAgent, cookie, body, followRedirect);
    }

    @Override // com.jd.jdcache.service.base.JDCacheNetDelegate
    @Nullable
    public Flow<NetState<File>> downloadFlow(@NotNull String url, @NotNull String savePath, @NotNull String method, @Nullable Map<String, String> header, @Nullable String userAgent, @Nullable String cookie) {
        if (Intrinsics.areEqual("1", HybridBase.getInstance().getSetting(HybridBase.SWITCH_DOWNLOAD_ADAPTER))) {
            if (url.length() == 0) {
                JDCacheLog jDCacheLog = JDCacheLog.INSTANCE;
                if (jDCacheLog.getCanLog()) {
                    jDCacheLog.e(getName(), "Cannot download file, because url is empty.");
                }
                return null;
            }
            if (savePath.length() == 0) {
                JDCacheLog jDCacheLog2 = JDCacheLog.INSTANCE;
                if (jDCacheLog2.getCanLog()) {
                    jDCacheLog2.e(getName(), "Cannot download file[" + url + "], because savePath is empty.");
                }
                return null;
            }
            return FlowKt.callbackFlow(new NetDelegate$downloadFlow$3(this, url, header, OfflineFileHelper.HYBRID_OFFLINE_FILE_TEMP_DIR + File.separator + "netDelegate", FileUtils.getTimestampForName(), savePath, null));
        }
        return new NetConnection().downloadFlow(url, savePath, method, header, userAgent, cookie);
    }

    @Override // com.jd.jdcache.service.base.AbstractDelegate
    @NotNull
    public String getName() {
        return this.name;
    }

    @Override // com.jd.jdcache.service.base.JDCacheNetDelegate
    @Nullable
    public Flow<NetState<String>> requestFlow(@NotNull String url, @NotNull String method, @Nullable Map<String, String> header, @Nullable String userAgent, @Nullable String cookie, @Nullable Map<String, String> body, boolean followRedirect) {
        throw new NotImplementedError("An operation is not implemented: Not yet implemented");
    }
}
