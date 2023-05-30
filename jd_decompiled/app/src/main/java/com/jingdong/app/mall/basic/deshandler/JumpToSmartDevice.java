package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.os.Bundle;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.common.deeplinkhelper.DeepLinkSmartDeviceHelper;
import com.jingdong.common.frame.IMyActivity;
import com.jingdong.common.jump.JumpUtil;

@Des(des = JumpUtil.VALUE_DES_SMART_DEVICE)
/* loaded from: classes19.dex */
public class JumpToSmartDevice extends a {
    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        if (context instanceof IMyActivity) {
            DeepLinkSmartDeviceHelper.startSmartDeviceActivity((IMyActivity) context, bundle);
            c(context);
        }
    }
}
