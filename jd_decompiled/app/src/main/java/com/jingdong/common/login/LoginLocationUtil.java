package com.jingdong.common.login;

import com.jingdong.common.lbs.jdlocation.JDLocation;
import com.jingdong.common.lbs.jdlocation.JDLocationCache;
import com.jingdong.common.lbs.jdlocation.JDLocationCacheOption;

/* loaded from: classes.dex */
public class LoginLocationUtil {
    private static final String BUSSINESS_ID = "26ba26b59fcf391fb4c9868d9c6a25df";
    private static JDLocationCacheOption cacheOption;

    public static JDLocation getCacheLocation() {
        return JDLocationCache.getInstance().getLocation(getCacheOption());
    }

    private static JDLocationCacheOption getCacheOption() {
        JDLocationCacheOption jDLocationCacheOption = cacheOption;
        if (jDLocationCacheOption != null) {
            return jDLocationCacheOption;
        }
        JDLocationCacheOption jDLocationCacheOption2 = new JDLocationCacheOption();
        cacheOption = jDLocationCacheOption2;
        jDLocationCacheOption2.setBusinessId(BUSSINESS_ID);
        return cacheOption;
    }
}
