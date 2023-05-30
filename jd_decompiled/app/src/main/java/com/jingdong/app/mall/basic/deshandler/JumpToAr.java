package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.os.Bundle;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.common.deeplinkhelper.DeepLinkScanHelper;
import com.jingdong.common.frame.IMyActivity;
import com.jingdong.common.jump.JumpUtil;

@Des(des = JumpUtil.VALUE_DES_AR)
/* loaded from: classes19.dex */
public class JumpToAr extends a {
    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        DeepLinkScanHelper.startArCaptureActivity((IMyActivity) context, bundle);
        c(context);
    }
}
