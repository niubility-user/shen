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

@Des(des = JumpUtil.VALUE_DES_MESSAGE_SET_RN)
/* loaded from: classes19.dex */
public class JumpToMessageAccountSet extends a {
    private Uri s(Bundle bundle) {
        try {
            JDJSONObject jDJSONObject = new JDJSONObject();
            jDJSONObject.put("category", (Object) "jump");
            jDJSONObject.put("des", (Object) JumpUtil.VALUE_DES_JDREACT_COMMON);
            jDJSONObject.put(JDReactConstant.IntentConstant.MODULE_NAME, (Object) "JDReactMessageSet");
            jDJSONObject.put("appname", (Object) "JDReactMessageSet");
            jDJSONObject.put("ishidden", (Object) Boolean.TRUE);
            JDJSONObject jDJSONObject2 = new JDJSONObject();
            if (bundle != null) {
                for (String str : bundle.keySet()) {
                    jDJSONObject2.put(str, bundle.get(str));
                    if (str.equals("transparentenable")) {
                        jDJSONObject.put("transparentenable", (Object) Boolean.TRUE);
                    }
                }
            }
            jDJSONObject.put("param", (Object) jDJSONObject2);
            Uri.Builder buildUpon = Uri.parse("openapp.jdmobile://virtual").buildUpon();
            buildUpon.appendQueryParameter("params", jDJSONObject.toJSONString());
            Log.d(this.a, buildUpon.toString());
            return buildUpon.build();
        } catch (Exception unused) {
            return Uri.parse("openapp.jdmobile://virtual?params={\"category\":\"jump\",\"des\":\"jdreactcommon\",\"modulename\":\"JDReactMessageSet\",\"appname\":\"JDReactMessageSet\",\"ishidden\":true,\"param\":{}}");
        }
    }

    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        Intent intent = new Intent();
        intent.setData(s(bundle));
        intent.setPackage(context.getPackageName());
        context.startActivity(intent);
        c(context);
    }
}
