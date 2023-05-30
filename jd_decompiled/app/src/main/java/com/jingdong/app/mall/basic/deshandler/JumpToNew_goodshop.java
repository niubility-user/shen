package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.common.deeplinkhelper.DeepLinkJShopHomeHelper;
import com.jingdong.jdsdk.constant.JshopConst;
import org.json.JSONException;
import org.json.JSONObject;

@Des(des = "newgoodshop")
/* loaded from: classes19.dex */
public class JumpToNew_goodshop extends a {
    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        JSONObject jSONObject = new JSONObject();
        String string = bundle.getString("shopId");
        if (!TextUtils.isEmpty(string)) {
            try {
                jSONObject.put("shopId", string);
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
            Intent intent = new Intent();
            intent.putExtra(JshopConst.INTENT_BRAND_JSON, jSONObject.toString());
            DeepLinkJShopHomeHelper.gotoJShopHome(context, intent.getExtras());
        }
        c(context);
    }
}
