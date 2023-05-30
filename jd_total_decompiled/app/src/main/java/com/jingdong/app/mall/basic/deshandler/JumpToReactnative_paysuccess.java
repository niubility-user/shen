package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.common.jdreactFramework.modules.JDReactNativeSharedDataModule;
import com.jingdong.common.jdreactFramework.utils.ReactActivityUtils;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.corelib.utils.Log;
import org.json.JSONObject;

@Des(des = JumpUtil.VAULE_DES_REACTNATIVE_PAYSUCCESS)
/* loaded from: classes19.dex */
public class JumpToReactnative_paysuccess extends a {
    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        if (bundle != null) {
            String string = bundle.getString("jdreactparam");
            String string2 = bundle.getString("jdreactmodule");
            Log.d(this.a, "jdreactparam  = " + string);
            if (!TextUtils.isEmpty(string2) && !TextUtils.isEmpty(string) && ("fuelCard".equals(string2) || "oneTreasure".equals(string2))) {
                try {
                    JSONObject jSONObject = new JSONObject(string);
                    Log.d(this.a, "jdreactparam  jo = " + jSONObject);
                    JDReactNativeSharedDataModule.putData("orderId", jSONObject.optString("orderId"));
                    JDReactNativeSharedDataModule.putData("orderPrice", jSONObject.optString("orderPrice"));
                    JDReactNativeSharedDataModule.putData("orderType", jSONObject.optString("orderType"));
                    String optString = jSONObject.optString("cardPwdFlag");
                    if (!TextUtils.isEmpty(optString)) {
                        JDReactNativeSharedDataModule.putData("cardPwdFlag", optString);
                    } else {
                        JDReactNativeSharedDataModule.putData("cardPwdFlag", "0");
                    }
                    JDReactNativeSharedDataModule.putData("from", string2);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
        Intent intent = new Intent();
        if (bundle != null) {
            try {
                intent.putExtra("orderId", JDReactNativeSharedDataModule.getData("orderId"));
                intent.putExtra("orderPrice", JDReactNativeSharedDataModule.getData("orderPrice"));
            } catch (Exception e3) {
                e3.printStackTrace();
            }
        }
        ReactActivityUtils.startJDReactPaySuccessActivity(context, intent);
        c(context);
    }
}
