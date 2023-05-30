package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.jingdong.app.mall.darkmode.DarkModePreferenceActivity;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.common.jump.JumpUtil;

@Des(des = JumpUtil.VALUE_DES_DARK_MODE_PREFERENCE)
/* loaded from: classes19.dex */
public class JumpToDarkModePreference extends a {
    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        Intent intent = new Intent(context, DarkModePreferenceActivity.class);
        intent.putExtras(bundle);
        context.startActivity(intent);
        c(context);
    }
}
