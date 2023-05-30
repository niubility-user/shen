package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.app.mall.promotion.PromotionMessageActivity;
import com.jingdong.common.jump.OpenAppJumpController;

@Des(des = "promotion")
/* loaded from: classes19.dex */
public class JumpToPromotion extends a {
    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        String string = bundle.getString("promotionId");
        if (TextUtils.isEmpty(string)) {
            c(context);
            return;
        }
        Intent intent = new Intent(context, PromotionMessageActivity.class);
        Bundle bundle2 = new Bundle();
        bundle2.putString("promotion_id", string);
        bundle2.putString("name", bundle.getString("title"));
        bundle2.putString("comeFrom", OpenAppJumpController.FROM_M_DESTINATION);
        bundle2.putString("landPageId", bundle.getString("landPageId"));
        intent.putExtras(bundle2);
        intent.putExtra("com.360buy:navigationDisplayFlag", -1);
        context.startActivity(intent);
        c(context);
    }
}
