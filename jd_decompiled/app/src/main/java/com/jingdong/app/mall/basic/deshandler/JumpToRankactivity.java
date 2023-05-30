package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.os.Bundle;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.common.deeplinkhelper.DeepLinkRankHelper;

@Des(des = "goodranking")
/* loaded from: classes19.dex */
public class JumpToRankactivity extends a {
    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        int i2 = bundle.getInt("jumpFrom");
        if (i2 == 1) {
            DeepLinkRankHelper.startToRankHome(context, bundle, "Applications");
        } else if (i2 == 2) {
            DeepLinkRankHelper.startToRankHome(context, bundle, "Applications");
        } else if (i2 == 5) {
            DeepLinkRankHelper.startToRankHome(context, bundle, "MyJD");
        } else {
            DeepLinkRankHelper.startToRankHome(context, bundle, bundle.getString("from"));
        }
        c(context);
    }
}
