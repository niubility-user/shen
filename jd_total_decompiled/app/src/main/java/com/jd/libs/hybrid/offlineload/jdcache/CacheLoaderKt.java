package com.jd.libs.hybrid.offlineload.jdcache;

import com.google.common.net.HttpHeaders;
import com.jd.jdcache.entity.JDCacheLocalResp;
import java.util.HashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\u001a\u0011\u0010\u0002\u001a\u00020\u0001*\u00020\u0000\u00a2\u0006\u0004\b\u0002\u0010\u0003\u00a8\u0006\u0004"}, d2 = {"Lcom/jd/jdcache/entity/JDCacheLocalResp;", "", "addResponseMaxAge", "(Lcom/jd/jdcache/entity/JDCacheLocalResp;)V", "offlineload_release"}, k = 2, mv = {1, 4, 0})
/* loaded from: classes16.dex */
public final class CacheLoaderKt {
    public static final void addResponseMaxAge(@NotNull JDCacheLocalResp jDCacheLocalResp) {
        boolean equals;
        equals = StringsKt__StringsJVMKt.equals("html", jDCacheLocalResp.getType(), true);
        if (equals) {
            return;
        }
        Map<String, String> map = jDCacheLocalResp.header;
        if (map == null) {
            map = new HashMap<>(2);
        }
        jDCacheLocalResp.header = map;
        if (map == null) {
            Intrinsics.throwNpe();
        }
        map.put(HttpHeaders.CACHE_CONTROL, "max-age=7200");
    }
}
