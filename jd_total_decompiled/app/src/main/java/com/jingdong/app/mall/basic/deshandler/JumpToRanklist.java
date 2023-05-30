package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.common.deeplinkhelper.DeepLinkRankHelper;
import com.jingdong.common.jdreactFramework.JDReactConstant;
import com.jingdong.common.jdreactFramework.utils.ReactActivityUtils;
import com.jingdong.common.jump.JumpUtil;

@Des(des = JumpUtil.VALUE_DES_RANKLIST)
/* loaded from: classes19.dex */
public class JumpToRanklist extends a {
    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        boolean z = bundle.getBoolean("isFromNative", false);
        String string = bundle.getString("categoryIds");
        String string2 = bundle.getString("rankId");
        String string3 = bundle.getString("from");
        String string4 = bundle.getString("cateName");
        if (!z) {
            Bundle bundle2 = new Bundle();
            bundle2.putString("category", "jump");
            bundle2.putString("des", JumpUtil.VALUE_DES_JDREACT_COMMON);
            bundle2.putString(JDReactConstant.IntentConstant.MODULE_NAME, "JDReactRankingList");
            bundle2.putString("appname", "JDReactRankingList");
            bundle2.putBoolean("ishidden", true);
            bundle2.putString("fromName", string3);
            bundle2.putString("skuId", "");
            bundle2.putString(DeepLinkRankHelper.CATE_ID, "");
            bundle2.putString("categoryName", string4);
            bundle2.putString("categoryIds", string);
            bundle2.putBoolean("isLandingPage", true);
            if (!DeepLinkRankHelper.RANK_MAIN_FLOOR_TYPE_ADD_CART.equals(string2) && !DeepLinkRankHelper.RANK_MAIN_FLOOR_TYPE_ADD_CART_TOP.equals(string2) && !DeepLinkRankHelper.RANK_MAIN_FLOOR_TYPE_HOT.equals(string2) && !DeepLinkRankHelper.RANK_MAIN_ID_BOOK_NEW.equals(string2) && !DeepLinkRankHelper.RANK_MAIN_ID_BOOK_HOT.equals(string2) && !DeepLinkRankHelper.RANK_MAIN_ID_MUSIC_HOT.equals(string2) && !DeepLinkRankHelper.RANK_MAIN_ID_VIDEO_HOT.equals(string2) && !DeepLinkRankHelper.RANK_MAIN_ID_EDUCATION_HOT.equals(string2) && !DeepLinkRankHelper.RANK_MAIN_ID_GAME_HOT.equals(string2) && !DeepLinkRankHelper.RANK_MAIN_FLOOR_TYPE_DISCOUNT.equals(string2) && !DeepLinkRankHelper.RANK_MAIN_FLOOR_TYPE_DES.equals(string2) && !DeepLinkRankHelper.RANK_MAIN_FLOOR_TYPE_SHOP.equals(string2) && !DeepLinkRankHelper.RANK_MAIN_FLOOR_TYPE_HOT_SEARCH.equals(string2) && !DeepLinkRankHelper.RANK_MAIN_FLOOR_TYPE_RECOMMEND.equals(string2) && !DeepLinkRankHelper.RANK_MAIN_FLOOR_TYPE_LAST_DAY.equals(string2)) {
                bundle2.putString("rankId", DeepLinkRankHelper.RANK_MAIN_FLOOR_TYPE_HOT);
            } else {
                bundle2.putString("rankId", string2);
            }
            ReactActivityUtils.startJDReactCommonActivity(context, bundle2);
        } else {
            DeepLinkRankHelper.startRankHotListActivity(context, "", "", string, string4, TextUtils.isEmpty(string3) ? DeepLinkRankHelper.SEPARATEPAGE : string3, TextUtils.isEmpty(string2) ? DeepLinkRankHelper.RANK_MAIN_FLOOR_TYPE_HOT : string2);
        }
        c(context);
    }
}
