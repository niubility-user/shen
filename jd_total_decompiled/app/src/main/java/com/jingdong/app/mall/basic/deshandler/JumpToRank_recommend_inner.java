package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.os.Bundle;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.common.deeplinkhelper.DeepLinkRankHelper;
import com.jingdong.common.jdreactFramework.JDReactConstant;
import com.jingdong.common.jdreactFramework.utils.ReactActivityUtils;
import com.jingdong.common.jump.JumpUtil;

@Des(des = JumpUtil.VALUES_DES_RANK_RECOMMEND_INNER)
/* loaded from: classes19.dex */
public class JumpToRank_recommend_inner extends a {
    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        boolean z = bundle.getBoolean("isFromNative", false);
        String string = bundle.getString("contentId");
        String string2 = bundle.getString("from");
        if (!z) {
            Bundle bundle2 = new Bundle();
            bundle2.putString("category", "jump");
            bundle2.putString("des", JumpUtil.VALUE_DES_JDREACT_COMMON);
            bundle2.putString(JDReactConstant.IntentConstant.MODULE_NAME, "JDReactRankingList");
            bundle2.putString("appname", "JDReactRankingList");
            bundle2.putBoolean("ishidden", true);
            bundle2.putString("fromName", string2);
            bundle2.putString("contentId", string);
            ReactActivityUtils.startJDReactCommonActivity(context, bundle2);
        } else {
            DeepLinkRankHelper.startRankRecommendInnerPageActivity(context, string, string2);
        }
        c(context);
    }
}
