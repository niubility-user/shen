package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.os.Bundle;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.common.deeplinkhelper.imhelper.DDParameterBuilder;
import com.jingdong.common.deeplinkhelper.imhelper.DeeplinkDongDongHelper;
import com.jingdong.common.jump.JumpUtil;

@Des(des = JumpUtil.VALUE_DES_GROUP_CHAT_GROUP)
/* loaded from: classes19.dex */
public class JumpToGroupChatSpace extends a {
    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        if (bundle != null) {
            bundle.putString("from", "groupChat");
            DDParameterBuilder generateWithPin = DDParameterBuilder.generateWithPin();
            if (generateWithPin != null) {
                generateWithPin.addFrom(DDParameterBuilder.ACTION_BROADCAST_START_IM_CUSTOMER_SERVICE_MANAGER);
                bundle.putAll(generateWithPin.getBundle());
            }
        }
        DeeplinkDongDongHelper.getInstance().startDongDong(context, bundle);
        c(context);
    }
}
