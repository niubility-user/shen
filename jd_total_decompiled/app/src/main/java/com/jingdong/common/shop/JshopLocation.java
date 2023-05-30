package com.jingdong.common.shop;

import com.jingdong.common.lbs.jdlocation.JDLocation;
import com.jingdong.common.lbs.jdlocation.JDLocationCache;
import com.jingdong.common.lbs.jdlocation.JDLocationCacheOption;

/* loaded from: classes6.dex */
public class JshopLocation {
    private static final String BID = "ac475ecb37e28de4f680669dc352dd81";

    public static JDLocation getLocation() {
        JDLocationCacheOption jDLocationCacheOption = new JDLocationCacheOption();
        jDLocationCacheOption.setBusinessId(BID);
        return JDLocationCache.getInstance().getLocation(jDLocationCacheOption);
    }
}
