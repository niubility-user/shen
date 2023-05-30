package com.jingdong.common.utils;

import com.jingdong.common.deeplinkhelper.DeepLink3DProductHelper;
import com.jingdong.common.deeplinkhelper.DeepLinkTryClothesHelper;

/* loaded from: classes6.dex */
public class CleanCacheUtils {
    public static void cleanCache() {
        DeepLink3DProductHelper.cleanCache();
        DeepLinkTryClothesHelper.cleanCache(null);
    }
}
