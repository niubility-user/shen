package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.os.Bundle;
import com.jingdong.app.mall.libs.Des;

@Des(des = "goodsRecommend,recommend")
/* loaded from: classes19.dex */
public class JumpToGoods_recommend extends a {
    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        j(context, "jingpintuijian", bundle);
        c(context);
    }
}
