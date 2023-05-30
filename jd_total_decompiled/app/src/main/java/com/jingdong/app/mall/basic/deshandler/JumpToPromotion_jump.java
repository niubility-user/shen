package com.jingdong.app.mall.basic.deshandler;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.jingdong.app.mall.home.FloorProductListActivity;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.app.mall.promotion.PromotionProductListActivity;
import com.jingdong.app.mall.utils.s;
import com.jingdong.common.entity.SourceEntity;
import com.jingdong.common.jump.JumpUtil;

@Des(des = JumpUtil.VALUE_DES_PROMOTION_JUMP)
/* loaded from: classes19.dex */
public class JumpToPromotion_jump extends a {
    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        if (!(context instanceof Activity)) {
            c(context);
            return;
        }
        int i2 = bundle.getInt("wareDisplayType");
        if (i2 == 0) {
            Bundle bundle2 = new Bundle();
            bundle2.putSerializable("source", new SourceEntity(SourceEntity.SOURCE_TYPE_HOME_ACTIVITY, bundle.getString("sourceValue")));
            s.l((Activity) context, bundle2);
        } else if (i2 == 1) {
            Intent intent = new Intent(context, FloorProductListActivity.class);
            intent.putExtra("source", new SourceEntity(SourceEntity.SOURCE_TYPE_HOME_ACTIVITY, bundle.getString("sourceValue")));
            intent.putExtra("DATA_TYPE_NAME", 1);
            s.m((Activity) context, intent);
        } else if (i2 == 2) {
            Intent intent2 = new Intent(context, PromotionProductListActivity.class);
            intent2.putExtra("source", new SourceEntity(SourceEntity.SOURCE_TYPE_HOME_ACTIVITY, bundle.getString("sourceValue")));
            intent2.putExtra("DATA_TYPE_NAME", 1);
            s.m((Activity) context, intent2);
        }
        c(context);
    }
}
