package com.jingdong.common.deeplinkhelper;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.jdreactFramework.JDReactConstant;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.corelib.utils.Log;

/* loaded from: classes5.dex */
public class DeepLinkMyCalendarHelper {
    private static final String OPEN_APP = "openapp.jdmobile://virtual";
    private static final String TAG = "DeepLinkMyCalendarHelper";

    private static Uri getOpenApp(Bundle bundle) {
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
            Uri.Builder buildUpon = Uri.parse(OPEN_APP).buildUpon();
            buildUpon.appendQueryParameter("params", jDJSONObject.toJSONString());
            Log.d(TAG, buildUpon.toString());
            return buildUpon.build();
        } catch (Exception unused) {
            return Uri.parse("openapp.jdmobile://virtual?params={\"category\":\"jump\",\"des\":\"jdreactcommon\",\"modulename\":\"JDReactMyCalendar\",\"appname\":\"JDReactMyCalendar\",\"ishidden\":true,\"param\":{}}");
        }
    }

    public static void startMyCalendarActivity(Context context, Bundle bundle) {
        Intent intent = new Intent();
        intent.setData(getOpenApp(bundle));
        intent.setPackage(context.getPackageName());
        context.startActivity(intent);
    }
}
