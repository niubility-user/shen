package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.app.mall.promotion.PromotionProductListActivity;
import com.jingdong.common.deeplinkhelper.DeepLinkProductListHelper;
import com.jingdong.common.entity.Commercial;
import com.jingdong.common.jump.OpenAppJumpController;

@Des(des = "activity")
/* loaded from: classes19.dex */
public class JumpToActivity extends a {
    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        String string = bundle.getString("activityId");
        if (TextUtils.isEmpty(string)) {
            c(context);
            return;
        }
        String string2 = bundle.getString("type");
        Intent intent = null;
        Bundle bundle2 = new Bundle();
        bundle2.putString("activityId", string);
        bundle2.putString("comeFrom", OpenAppJumpController.FROM_M_DESTINATION);
        String string3 = bundle.getString("title");
        bundle2.putString("name", string3);
        bundle2.putString("title", string3);
        bundle2.putString("landPageId", bundle.getString("landPageId"));
        if ("1".equals(string2)) {
            intent = new Intent(context, PromotionProductListActivity.class);
            bundle2.putString("content", bundle.getString("content"));
        } else if ("2".equals(string2)) {
            Intent intent2 = new Intent();
            Commercial commercial = new Commercial();
            commercial.id = string;
            commercial.title = string3;
            intent2.putExtra("commercial", commercial);
            intent2.putExtras(bundle2);
            intent2.putExtra("com.360buy:navigationDisplayFlag", -1);
            DeepLinkProductListHelper.startProductListActivity(context, intent2.getExtras());
            c(context);
            return;
        }
        if (intent == null) {
            c(context);
            return;
        }
        intent.putExtras(bundle2);
        intent.putExtra("com.360buy:navigationDisplayFlag", -1);
        context.startActivity(intent);
        c(context);
    }
}
