package com.jingdong.app.mall.home;

import com.jingdong.common.utils.FireEyeRearLinkUtils;

/* loaded from: classes4.dex */
public class d {
    public static boolean a() {
        return false;
    }

    public static void b(String str, boolean z) {
        FireEyeRearLinkUtils.requestBenefit(str, z);
    }

    public static void c(boolean z) {
        JDHomeRouter.setShowTopTab(z);
    }
}
