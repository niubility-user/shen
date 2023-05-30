package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.app.mall.promotion.PromotionActivity;
import com.jingdong.common.entity.SourceEntity;
import com.jingdong.common.jump.JumpUtil;

@Des(des = JumpUtil.VALUE_DES_PROMOTION_NEW)
/* loaded from: classes19.dex */
public class JumpToPromotion_new extends a {
    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        Intent intent = new Intent(context, PromotionActivity.class);
        intent.putExtra("source", new SourceEntity(SourceEntity.SOURCE_TYPE_HOME_ACTIVITY, bundle.getString("sourceValue")));
        context.startActivity(intent);
        c(context);
    }
}
