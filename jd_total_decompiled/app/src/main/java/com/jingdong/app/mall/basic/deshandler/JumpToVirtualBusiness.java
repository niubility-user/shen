package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.common.jdreactFramework.JDReactConstant;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.corelib.utils.Log;

@Des(des = JumpUtil.VALUE_DES_VIRTUAL_BUSINESS)
/* loaded from: classes19.dex */
public class JumpToVirtualBusiness extends a {
    private Uri s(Bundle bundle) {
        try {
            JDJSONObject jDJSONObject = new JDJSONObject();
            jDJSONObject.put("category", "jump");
            jDJSONObject.put("des", JumpUtil.VALUE_DES_JDREACT_COMMON);
            jDJSONObject.put(JDReactConstant.IntentConstant.MODULE_NAME, "JDReactRechargeModule");
            jDJSONObject.put("appname", "JDReactRechargeModule");
            jDJSONObject.put("ishidden", Boolean.TRUE);
            JDJSONObject jDJSONObject2 = new JDJSONObject();
            jDJSONObject2.put("page", (Object) JumpUtil.VALUE_DES_ORDERDETAIL);
            JDJSONObject jDJSONObject3 = new JDJSONObject();
            jDJSONObject3.put("type", (Object) 1);
            jDJSONObject3.put("orderId", (Object) bundle.getString("orderId"));
            jDJSONObject3.put("source", (Object) "message");
            jDJSONObject2.put("routeParams", (Object) jDJSONObject3);
            jDJSONObject.put("param", (Object) jDJSONObject2);
            Uri.Builder buildUpon = Uri.parse("openapp.jdmobile://virtual").buildUpon();
            buildUpon.appendQueryParameter("params", jDJSONObject.toJSONString());
            Log.d(this.a, buildUpon.toString());
            return buildUpon.build();
        } catch (Exception unused) {
            return null;
        }
    }

    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        Intent intent = new Intent();
        Uri s = s(bundle);
        if (s != null) {
            intent.setData(s);
            intent.setPackage(context.getPackageName());
            context.startActivity(intent);
        }
        c(context);
    }
}
