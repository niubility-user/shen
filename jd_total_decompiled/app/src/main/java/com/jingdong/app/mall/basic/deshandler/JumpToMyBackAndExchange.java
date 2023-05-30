package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.app.mall.personel.MyBackAndExchangeActivity;
import com.jingdong.common.jump.JumpUtil;

@Des(des = JumpUtil.VALUE_DES_PERSONAL_MYBACK_AND_EXCHANGE)
/* loaded from: classes19.dex */
public class JumpToMyBackAndExchange extends a {
    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        Intent intent = new Intent(context, MyBackAndExchangeActivity.class);
        intent.putExtra("title", (String) bundle.get("title"));
        context.startActivity(intent);
        c(context);
    }
}
