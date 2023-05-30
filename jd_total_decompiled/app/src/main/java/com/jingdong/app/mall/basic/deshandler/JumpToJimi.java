package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.common.deeplinkhelper.imhelper.DeeplinkJimiHelper;
import com.jingdong.common.deeplinkhelper.imhelper.JimiParameterBuilder;
import com.jingdong.common.jump.JumpUtil;

@Des(des = JumpUtil.VALUE_DES_JIMI)
/* loaded from: classes19.dex */
public class JumpToJimi extends a {
    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        if (TextUtils.isEmpty(bundle.getString("source"))) {
            bundle.putString("source", "app");
        }
        JimiParameterBuilder convertFromWebParameter = JimiParameterBuilder.convertFromWebParameter(bundle);
        if (convertFromWebParameter != null) {
            DeeplinkJimiHelper.getInstance().startJimiActivity(context, convertFromWebParameter.getAllParameters());
        }
        c(context);
    }
}
