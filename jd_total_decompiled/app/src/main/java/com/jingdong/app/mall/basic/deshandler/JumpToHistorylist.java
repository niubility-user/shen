package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.os.Bundle;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.common.deeplinkhelper.DeepLinkBrowserHistoryHelper;
import com.jingdong.common.jump.JumpUtil;

@Des(des = JumpUtil.VALUE_DES_HISTORY_LIST)
/* loaded from: classes19.dex */
public class JumpToHistorylist extends a {
    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        DeepLinkBrowserHistoryHelper.startBrowserHistoryActivity2(context, bundle);
        c(context);
    }
}
