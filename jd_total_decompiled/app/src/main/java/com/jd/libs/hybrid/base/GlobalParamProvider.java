package com.jd.libs.hybrid.base;

import androidx.annotation.Keep;
import java.util.List;

@Keep
/* loaded from: classes16.dex */
public abstract class GlobalParamProvider {
    public static IGlobalParamProvider sGlobalParamProvider;

    @Deprecated
    /* loaded from: classes16.dex */
    public interface IGlobalParamProvider {
        String getCookieString(String str);

        String getUserAgent(String str);

        void saveCookieString(String str, List<String> list);
    }

    @Deprecated
    public static void setGlobalParamProvider(IGlobalParamProvider iGlobalParamProvider) {
        sGlobalParamProvider = iGlobalParamProvider;
    }
}
