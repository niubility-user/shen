package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.os.Bundle;
import com.jingdong.app.mall.libs.Des;

@Des(des = "phoneSale,mobile-only")
/* loaded from: classes19.dex */
public class JumpToMobile_channel extends a {
    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        j(context, "shoujizhuanxiang", bundle);
        c(context);
    }
}
