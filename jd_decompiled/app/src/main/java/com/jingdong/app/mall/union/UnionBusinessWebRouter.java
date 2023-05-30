package com.jingdong.app.mall.union;

import com.jingdong.common.utils.StringUtil;
import com.jingdong.common.web.IRouterParams;
import com.jingdong.jdsdk.mta.JDMtaUtils;

/* loaded from: classes4.dex */
public class UnionBusinessWebRouter {
    public static void getJdaAsync(IRouterParams iRouterParams) {
        iRouterParams.getRouterParam();
        String jda = JDMtaUtils.getJda();
        if (Boolean.valueOf(StringUtil.isEmpty(jda)).booleanValue()) {
            jda = "";
        }
        iRouterParams.onCallBack(jda);
    }

    public static String getJdaSync(IRouterParams iRouterParams) {
        String jda = JDMtaUtils.getJda();
        return Boolean.valueOf(StringUtil.isEmpty(jda)).booleanValue() ? "" : jda;
    }
}
