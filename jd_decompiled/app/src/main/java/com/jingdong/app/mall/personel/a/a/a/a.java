package com.jingdong.app.mall.personel.a.a.a;

import android.os.Bundle;
import android.text.TextUtils;
import com.jingdong.cleanmvp.presenter.BaseNavigator;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.deeplinkhelper.DeepLinkMyStreetHelper;
import com.jingdong.common.recommend.RecommendMtaUtils;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.utils.FontsUtil;

/* loaded from: classes4.dex */
public class a extends BaseNavigator {
    private static String a(String str) {
        if (TextUtils.isEmpty(str)) {
            return com.jingdong.app.mall.personel.b.a.a();
        }
        com.jingdong.app.mall.personel.b.a.c(str);
        return str;
    }

    public static void b(BaseActivity baseActivity, String str, boolean z) {
        if (baseActivity == null) {
            return;
        }
        if (z && (z = com.jingdong.app.mall.personel.b.a.b())) {
            com.jingdong.app.mall.personel.b.a.d(false);
        }
        JDMtaUtils.onClickWithPageId(baseActivity, "MyJD_MyStreet", RecommendMtaUtils.MyJD_Page_Name, z ? "new" : FontsUtil.KEY_MULTI_REGULAR, RecommendMtaUtils.MyJD_PageId);
        Bundle bundle = new Bundle();
        bundle.putString("title", a(str));
        bundle.putBoolean("isGoToGene", z);
        DeepLinkMyStreetHelper.startMyStreet(baseActivity, bundle);
    }
}
