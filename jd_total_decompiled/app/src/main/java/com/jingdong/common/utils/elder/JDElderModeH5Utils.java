package com.jingdong.common.utils.elder;

import com.jingdong.common.web.IRouterParams;
import com.jingdong.sdk.eldermode.util.JDElderModeUtils;

/* loaded from: classes6.dex */
public class JDElderModeH5Utils {
    public static void getElderModeAsync(IRouterParams iRouterParams) {
        if (iRouterParams != null) {
            iRouterParams.onCallBack(Integer.valueOf(JDElderModeUtils.getElderMode()));
        }
    }

    public static int getElderModeSync(IRouterParams iRouterParams) {
        return JDElderModeUtils.getElderMode();
    }
}
