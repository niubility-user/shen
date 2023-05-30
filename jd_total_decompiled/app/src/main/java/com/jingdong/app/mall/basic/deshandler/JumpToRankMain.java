package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.os.Bundle;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.common.deeplinkhelper.DeepLinkRankHelper;
import com.jingdong.common.jdreactFramework.JDReactConstant;
import com.jingdong.common.jdreactFramework.utils.ReactActivityUtils;
import com.jingdong.common.jump.JumpUtil;

@Des(des = JumpUtil.VALUE_DES_RANKING_PAGE)
/* loaded from: classes19.dex */
public class JumpToRankMain extends a {
    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        String string = bundle.getString("fromName");
        bundle.putString("des", JumpUtil.VALUE_DES_JDREACT_COMMON);
        bundle.putString(JDReactConstant.IntentConstant.MODULE_NAME, "JDReactRankingList");
        bundle.putString("appname", "JDReactRankingList");
        bundle.putString(DeepLinkRankHelper.RANK_ENTRY, string);
        if (!bundle.getBoolean("isFromNative", false)) {
            ReactActivityUtils.startJDReactCommonActivity(context, bundle);
        } else {
            DeepLinkRankHelper.startToRankHome(context, bundle, string);
        }
        c(context);
    }
}
