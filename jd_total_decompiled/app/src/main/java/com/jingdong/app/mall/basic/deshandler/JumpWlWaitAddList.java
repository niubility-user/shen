package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.os.Bundle;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.common.deeplinkhelper.DeepLinkSmartDeviceHelper;
import com.jingdong.common.frame.IMyActivity;
import com.jingdong.common.jump.JumpUtil;

@Des(des = JumpUtil.VALUE_DES_WL_WAITADD_LIST)
/* loaded from: classes19.dex */
public class JumpWlWaitAddList extends a {
    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        if (context instanceof IMyActivity) {
            DeepLinkSmartDeviceHelper.startWaitAddListActivity((IMyActivity) context, bundle);
        }
        c(context);
    }
}
