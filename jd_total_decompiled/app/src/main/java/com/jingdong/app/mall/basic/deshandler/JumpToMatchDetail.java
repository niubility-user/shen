package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.os.Bundle;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.common.deeplinkhelper.DeepLinkGoodStuffHelper;
import com.jingdong.common.jump.JumpUtil;

@Des(des = JumpUtil.VALUE_DES_MATCH_DETAIL)
/* loaded from: classes19.dex */
public class JumpToMatchDetail extends a {
    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        if (bundle == null) {
            return;
        }
        String string = bundle.getString("id", "");
        Bundle bundle2 = new Bundle();
        bundle2.putString("templateId", "5");
        bundle2.putString("contentStyle", "7");
        bundle2.putString("from", "21");
        bundle2.putString("id", string);
        bundle2.putString("subPosition", "");
        bundle2.putString("releaseType", "");
        DeepLinkGoodStuffHelper.startUnifyDetailActivity(context, bundle2);
        c(context);
    }
}
