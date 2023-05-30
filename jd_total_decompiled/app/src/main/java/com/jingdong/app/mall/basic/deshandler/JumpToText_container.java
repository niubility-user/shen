package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.app.mall.product.CommercialRuleActivity;
import com.jingdong.common.jump.JumpUtil;

@Des(des = JumpUtil.VAULE_DES_TEXT_CONTAINER)
/* loaded from: classes19.dex */
public class JumpToText_container extends a {
    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        Intent intent = new Intent(context, CommercialRuleActivity.class);
        Bundle bundle2 = new Bundle();
        bundle2.putString("title", bundle.getString("title"));
        bundle2.putString("detail", bundle.getString("content"));
        intent.putExtras(bundle2);
        context.startActivity(intent);
        c(context);
    }
}
