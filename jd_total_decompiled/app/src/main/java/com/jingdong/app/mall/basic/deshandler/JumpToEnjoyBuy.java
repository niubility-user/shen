package com.jingdong.app.mall.basic.deshandler;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.common.jdreactFramework.JDReactConstant;
import com.jingdong.common.jdreactFramework.utils.ReactActivityUtilsHelperBase;
import com.jingdong.common.jump.JumpUtil;
import jd.wjlogin_sdk.util.f;
import org.json.JSONException;
import org.json.JSONObject;

@Des(des = JumpUtil.VALUE_DES_ENJOYBUY)
/* loaded from: classes19.dex */
public class JumpToEnjoyBuy extends a {
    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        ComponentName componentName = new ComponentName(f.f19954c, "com.jd.lib.enjoybuy.EnjoyBuyMainActivity");
        Intent intent = new Intent();
        intent.setComponent(componentName);
        intent.putExtras(bundle);
        bundle.putString("appname", "JDReactEnjoyBuy");
        bundle.putString(JDReactConstant.IntentConstant.MODULE_NAME, "JDReactEnjoyBuy");
        bundle.putBoolean("transparentenable", true);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("transparentenable", true);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        bundle.putString("param", jSONObject.toString());
        ReactActivityUtilsHelperBase.startJDReactActivity(context, intent, bundle);
        c(context);
    }
}
