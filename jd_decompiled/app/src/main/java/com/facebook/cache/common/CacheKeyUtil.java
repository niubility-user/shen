package com.facebook.cache.common;

import com.facebook.common.util.SecureHashUtil;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public final class CacheKeyUtil {
    public static String getFirstResourceId(CacheKey cacheKey) {
        try {
            return cacheKey instanceof MultiCacheKey ? secureHashKey(((MultiCacheKey) cacheKey).getCacheKeys().get(0)) : secureHashKey(cacheKey);
        } catch (UnsupportedEncodingException e2) {
            throw new RuntimeException(e2);
        }
    }

    public static List<String> getResourceIds(CacheKey cacheKey) {
        ArrayList arrayList;
        try {
            if (cacheKey instanceof MultiCacheKey) {
                List<CacheKey> cacheKeys = ((MultiCacheKey) cacheKey).getCacheKeys();
                arrayList = new ArrayList(cacheKeys.size());
                for (int i2 = 0; i2 < cacheKeys.size(); i2++) {
                    arrayList.add(secureHashKey(cacheKeys.get(i2)));
                }
            } else {
                arrayList = new ArrayList(1);
                arrayList.add(cacheKey.isResourceIdForDebugging() ? cacheKey.getUriString() : secureHashKey(cacheKey));
            }
            return arrayList;
        } catch (UnsupportedEncodingException e2) {
            throw new RuntimeException(e2);
        }
    }

    private static String secureHashKey(CacheKey cacheKey) {
        return SecureHashUtil.makeSHA1HashBase64(cacheKey.getUriString().getBytes("UTF-8"));
    }
}
