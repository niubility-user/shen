package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.jdreactFramework.JDReactConstant;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.common.utils.AdvertUtils;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.mta.JDMtaUtils;

@Des(des = JumpUtil.VALUE_DES_JDREMINDER)
/* loaded from: classes19.dex */
public class JumpToJdreminder extends a {
    private Uri s(Bundle bundle) {
        try {
            JDJSONObject jDJSONObject = new JDJSONObject();
            jDJSONObject.put("category", (Object) "jump");
            jDJSONObject.put("des", (Object) JumpUtil.VALUE_DES_JDREACT_COMMON);
            jDJSONObject.put(JDReactConstant.IntentConstant.MODULE_NAME, (Object) "JDReactMyCalendar");
            jDJSONObject.put("appname", (Object) "JDReactMyCalendar");
            Boolean bool = Boolean.TRUE;
            jDJSONObject.put("ishidden", (Object) bool);
            JDJSONObject jDJSONObject2 = new JDJSONObject();
            jDJSONObject2.put("transparentenable", (Object) bool);
            if (bundle != null) {
                for (String str : bundle.keySet()) {
                    jDJSONObject2.put(str, bundle.get(str));
                }
            }
            jDJSONObject.put("param", (Object) jDJSONObject2);
            Uri.Builder buildUpon = Uri.parse("openapp.jdmobile://virtual").buildUpon();
            buildUpon.appendQueryParameter("params", jDJSONObject.toJSONString());
            Log.d(this.a, buildUpon.toString());
            return buildUpon.build();
        } catch (Exception unused) {
            return Uri.parse("openapp.jdmobile://virtual?params={\"category\":\"jump\",\"des\":\"jdreactcommon\",\"modulename\":\"JDReactMyCalendar\",\"appname\":\"JDReactMyCalendar\",\"ishidden\":true,\"param\":{}}");
        }
    }

    private void t(Context context, Bundle bundle) {
        if (context == null || bundle == null) {
            return;
        }
        boolean z = bundle.getBoolean(BaseActivity.ISFROMNF, false);
        if (Log.D) {
            Log.d("HHH", "MyCalendarActivity, isFromNF: " + z);
        }
        if (z) {
            JDMtaUtils.onClickWithPageId(context, "NotificationMessage_PushMessage", "JDMCActivityReminder", "MyCalendar_Main");
            long currentTimeMillis = System.currentTimeMillis();
            JDMtaUtils.updateJdv(context, "0|kong|t_1000170136|tuiguang|notset|" + currentTimeMillis);
            AdvertUtils.changeJdvToMParam("0|kong|t_1000170136|tuiguang|notset|" + currentTimeMillis);
        }
    }

    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        Intent intent = new Intent();
        intent.setData(s(bundle));
        intent.setPackage(context.getPackageName());
        context.startActivity(intent);
        t(context, bundle);
        c(context);
    }
}
