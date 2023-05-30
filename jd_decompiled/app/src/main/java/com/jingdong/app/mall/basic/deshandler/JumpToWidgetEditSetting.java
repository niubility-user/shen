package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.os.Bundle;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.common.deeplinkhelper.DeepLinkWidgetHelper;
import com.jingdong.common.jump.JumpUtil;

@Des(des = JumpUtil.VALUE_DES_WIDGET_EDIT_SETTING)
/* loaded from: classes19.dex */
public class JumpToWidgetEditSetting extends a {
    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        DeepLinkWidgetHelper.WidgetEditSettingActivity(context, bundle);
        c(context);
    }
}
