package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.common.ActivityNumController;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.common.sample.jshop.JShopSignNewActivity;

@Des(des = JumpUtil.VALUE_DES_JSHOP_SIGN)
/* loaded from: classes19.dex */
public class JumpToJshop_sign extends a {
    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        if (bundle == null) {
            return;
        }
        Intent intent = new Intent();
        intent.setClass(context, JShopSignNewActivity.class);
        intent.putExtras(bundle);
        if (bundle.containsKey("backUrl") && !TextUtils.isEmpty(bundle.getString("backUrl"))) {
            intent.putExtra("backFinish", true);
        }
        context.startActivity(intent);
        ActivityNumController.removeActivity(ActivityNumController.WebActivity);
        c(context);
    }
}
