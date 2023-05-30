package com.jd.libs.hybrid.requestpreload.network;

import com.jd.aips.verify.tracker.VerifyTracker;
import com.jd.libs.hybrid.requestpreload.entity.CacheItem;
import com.jingdong.jdsdk.a.a;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u0000 (2\u00020\u0001:\u0002)(B\u0007\u00a2\u0006\u0004\b'\u0010\u001cJ)\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u00022\b\u0010\u0005\u001a\u0004\u0018\u00010\u0002H\u0002\u00a2\u0006\u0004\b\u0006\u0010\u0007J/\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u00022\b\u0010\u0005\u001a\u0004\u0018\u00010\u00022\u0006\u0010\t\u001a\u00020\b\u00a2\u0006\u0004\b\u000b\u0010\fJ/\u0010\u000f\u001a\u00020\n2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u00022\b\u0010\u0005\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u000e\u001a\u00020\r\u00a2\u0006\u0004\b\u000f\u0010\u0010J)\u0010\u0011\u001a\u0004\u0018\u00010\r2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u00022\b\u0010\u0005\u001a\u0004\u0018\u00010\u0002\u00a2\u0006\u0004\b\u0011\u0010\u0012J)\u0010\u0013\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u00022\b\u0010\u0005\u001a\u0004\u0018\u00010\u0002\u00a2\u0006\u0004\b\u0013\u0010\u0014J/\u0010\u0017\u001a\u00020\n2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u00022\b\u0010\u0005\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0016\u001a\u00020\u0015\u00a2\u0006\u0004\b\u0017\u0010\u0018J)\u0010\u0019\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u00022\b\u0010\u0005\u001a\u0004\u0018\u00010\u0002\u00a2\u0006\u0004\b\u0019\u0010\u001aJ\r\u0010\u001b\u001a\u00020\n\u00a2\u0006\u0004\b\u001b\u0010\u001cJ\u0015\u0010\u001e\u001a\u00020\n2\u0006\u0010\u001d\u001a\u00020\u0002\u00a2\u0006\u0004\b\u001e\u0010\u001fR$\u0010\"\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0015\u0018\u00010 8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0006\u0010!R$\u0010$\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\r\u0018\u00010 8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b#\u0010!R$\u0010&\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\b\u0018\u00010 8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b%\u0010!\u00a8\u0006*"}, d2 = {"Lcom/jd/libs/hybrid/requestpreload/network/RequestCacheManager;", "", "", "appId", "requestId", VerifyTracker.KEY_TIMESTAMP, a.a, "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;", "Lcom/jd/libs/hybrid/requestpreload/network/RequestCacheManager$Callback;", "callback", "", "jsRequestData", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/jd/libs/hybrid/requestpreload/network/RequestCacheManager$Callback;)V", "", "status", "setStatus", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;I)V", "getStatus", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/Integer;", "getCallback", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lcom/jd/libs/hybrid/requestpreload/network/RequestCacheManager$Callback;", "Lcom/jd/libs/hybrid/requestpreload/entity/CacheItem;", "data", "putResult", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/jd/libs/hybrid/requestpreload/entity/CacheItem;)V", "getResult", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lcom/jd/libs/hybrid/requestpreload/entity/CacheItem;", "destroyAll", "()V", "idOrTimestamp", "destroy", "(Ljava/lang/String;)V", "Ljava/util/concurrent/ConcurrentHashMap;", "Ljava/util/concurrent/ConcurrentHashMap;", "cacheMap", "b", "statusMap", "c", "callbackMap", "<init>", "Companion", "Callback", "request-preload_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes16.dex */
public final class RequestCacheManager {
    public static final int STATUS_CANCEL = -4;
    public static final int STATUS_END = 1;
    public static final int STATUS_FAIL = 2;
    public static final int STATUS_NULL = -2;
    public static final int STATUS_PARAMS_ERROR = -3;
    public static final int STATUS_REQUEST = 0;

    /* renamed from: a  reason: from kotlin metadata */
    private ConcurrentHashMap<String, CacheItem> cacheMap;

    /* renamed from: b  reason: from kotlin metadata */
    private ConcurrentHashMap<String, Integer> statusMap;

    /* renamed from: c  reason: collision with root package name and from kotlin metadata */
    private ConcurrentHashMap<String, Callback> callbackMap;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J!\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H&\u00a2\u0006\u0004\b\u0007\u0010\b\u00a8\u0006\t"}, d2 = {"Lcom/jd/libs/hybrid/requestpreload/network/RequestCacheManager$Callback;", "", "", "code", "Lcom/jd/libs/hybrid/requestpreload/entity/CacheItem;", "cacheItem", "", "onResult", "(ILcom/jd/libs/hybrid/requestpreload/entity/CacheItem;)V", "request-preload_release"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes16.dex */
    public interface Callback {
        void onResult(int code, @Nullable CacheItem cacheItem);
    }

    private final String a(String appId, String requestId, String timestamp) {
        if (timestamp == null) {
            return appId + '_' + requestId;
        }
        return appId + '_' + requestId + '_' + timestamp;
    }

    public final void destroy(@NotNull String idOrTimestamp) {
        boolean endsWith$default;
        boolean endsWith$default2;
        ConcurrentHashMap<String, CacheItem> concurrentHashMap = this.cacheMap;
        if (concurrentHashMap != null) {
            if (concurrentHashMap == null) {
                Intrinsics.throwNpe();
            }
            if (concurrentHashMap.containsKey(idOrTimestamp)) {
                ConcurrentHashMap<String, CacheItem> concurrentHashMap2 = this.cacheMap;
                if (concurrentHashMap2 == null) {
                    Intrinsics.throwNpe();
                }
                concurrentHashMap2.remove(idOrTimestamp);
            } else {
                ConcurrentHashMap<String, CacheItem> concurrentHashMap3 = this.cacheMap;
                if (concurrentHashMap3 == null) {
                    Intrinsics.throwNpe();
                }
                for (Map.Entry<String, CacheItem> entry : concurrentHashMap3.entrySet()) {
                    endsWith$default2 = StringsKt__StringsJVMKt.endsWith$default(entry.getKey(), '_' + idOrTimestamp, false, 2, null);
                    if (endsWith$default2) {
                        ConcurrentHashMap<String, CacheItem> concurrentHashMap4 = this.cacheMap;
                        if (concurrentHashMap4 == null) {
                            Intrinsics.throwNpe();
                        }
                        concurrentHashMap4.remove(entry.getKey());
                    }
                }
            }
        }
        ConcurrentHashMap<String, Integer> concurrentHashMap5 = this.statusMap;
        if (concurrentHashMap5 != null) {
            if (concurrentHashMap5 == null) {
                Intrinsics.throwNpe();
            }
            if (concurrentHashMap5.containsKey(idOrTimestamp)) {
                ConcurrentHashMap<String, Integer> concurrentHashMap6 = this.statusMap;
                if (concurrentHashMap6 == null) {
                    Intrinsics.throwNpe();
                }
                concurrentHashMap6.remove(idOrTimestamp);
                return;
            }
            ConcurrentHashMap<String, Integer> concurrentHashMap7 = this.statusMap;
            if (concurrentHashMap7 == null) {
                Intrinsics.throwNpe();
            }
            for (Map.Entry<String, Integer> entry2 : concurrentHashMap7.entrySet()) {
                endsWith$default = StringsKt__StringsJVMKt.endsWith$default(entry2.getKey(), '_' + idOrTimestamp, false, 2, null);
                if (endsWith$default) {
                    ConcurrentHashMap<String, Integer> concurrentHashMap8 = this.statusMap;
                    if (concurrentHashMap8 == null) {
                        Intrinsics.throwNpe();
                    }
                    concurrentHashMap8.remove(entry2.getKey());
                }
            }
        }
    }

    public final void destroyAll() {
        ConcurrentHashMap<String, CacheItem> concurrentHashMap = this.cacheMap;
        if (concurrentHashMap != null) {
            if (concurrentHashMap == null) {
                Intrinsics.throwNpe();
            }
            concurrentHashMap.clear();
            this.cacheMap = null;
        }
        ConcurrentHashMap<String, Integer> concurrentHashMap2 = this.statusMap;
        if (concurrentHashMap2 != null) {
            if (concurrentHashMap2 == null) {
                Intrinsics.throwNpe();
            }
            concurrentHashMap2.clear();
            this.statusMap = null;
        }
    }

    @Nullable
    public final Callback getCallback(@NotNull String appId, @NotNull String requestId, @Nullable String timestamp) {
        String a = a(appId, requestId, timestamp);
        ConcurrentHashMap<String, Callback> concurrentHashMap = this.callbackMap;
        if (concurrentHashMap != null) {
            if (concurrentHashMap == null) {
                Intrinsics.throwNpe();
            }
            return concurrentHashMap.get(a);
        }
        return null;
    }

    @Nullable
    public final CacheItem getResult(@NotNull String appId, @NotNull String requestId, @Nullable String timestamp) {
        String a = a(appId, requestId, timestamp);
        ConcurrentHashMap<String, CacheItem> concurrentHashMap = this.cacheMap;
        if (concurrentHashMap != null) {
            if (concurrentHashMap == null) {
                Intrinsics.throwNpe();
            }
            return concurrentHashMap.get(a);
        }
        return null;
    }

    @Nullable
    public final Integer getStatus(@NotNull String appId, @NotNull String requestId, @Nullable String timestamp) {
        String a = a(appId, requestId, timestamp);
        ConcurrentHashMap<String, Integer> concurrentHashMap = this.statusMap;
        if (concurrentHashMap != null) {
            if (concurrentHashMap == null) {
                Intrinsics.throwNpe();
            }
            return concurrentHashMap.get(a);
        }
        return -2;
    }

    public final void jsRequestData(@NotNull String appId, @NotNull String requestId, @Nullable String timestamp, @NotNull Callback callback) {
        String a = a(appId, requestId, timestamp);
        Integer status = getStatus(appId, requestId, timestamp);
        if (status != null && status.intValue() == 1) {
            callback.onResult(200, getResult(appId, requestId, timestamp));
            destroy(a);
        } else if (status != null && status.intValue() == 2) {
            callback.onResult(-1, null);
            destroy(a);
        } else if (status != null && status.intValue() == -3) {
            callback.onResult(-3, null);
            destroy(a);
        } else if (status != null && status.intValue() == -4) {
            callback.onResult(-1, null);
            destroy(a);
        } else if (status != null && status.intValue() == 0) {
            if (this.callbackMap == null) {
                this.callbackMap = new ConcurrentHashMap<>();
            }
            ConcurrentHashMap<String, Callback> concurrentHashMap = this.callbackMap;
            if (concurrentHashMap == null) {
                Intrinsics.throwNpe();
            }
            concurrentHashMap.put(a, callback);
        } else {
            callback.onResult(-2, null);
        }
    }

    public final void putResult(@NotNull String appId, @NotNull String requestId, @Nullable String timestamp, @NotNull CacheItem data) {
        String a = a(appId, requestId, timestamp);
        if (this.cacheMap == null) {
            this.cacheMap = new ConcurrentHashMap<>();
        }
        ConcurrentHashMap<String, CacheItem> concurrentHashMap = this.cacheMap;
        if (concurrentHashMap == null) {
            Intrinsics.throwNpe();
        }
        concurrentHashMap.put(a, data);
    }

    public final void setStatus(@NotNull String appId, @NotNull String requestId, @Nullable String timestamp, int status) {
        String a = a(appId, requestId, timestamp);
        if (this.statusMap == null) {
            this.statusMap = new ConcurrentHashMap<>();
        }
        ConcurrentHashMap<String, Integer> concurrentHashMap = this.statusMap;
        if (concurrentHashMap == null) {
            Intrinsics.throwNpe();
        }
        concurrentHashMap.put(a, Integer.valueOf(status));
    }
}
