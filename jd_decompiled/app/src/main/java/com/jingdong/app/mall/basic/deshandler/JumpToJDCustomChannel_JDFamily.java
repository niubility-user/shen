package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.os.Bundle;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.common.deeplinkhelper.DeepLinkJDCustomChannelHelper;
import com.jingdong.common.jump.JumpUtil;

@Des(des = JumpUtil.VALUE_DES_JDCUSTOMCHANNEL_MAIN)
/* loaded from: classes19.dex */
public class JumpToJDCustomChannel_JDFamily extends a {
    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        DeepLinkJDCustomChannelHelper.startJDCustomChannel(context, bundle);
        c(context);
    }
}
