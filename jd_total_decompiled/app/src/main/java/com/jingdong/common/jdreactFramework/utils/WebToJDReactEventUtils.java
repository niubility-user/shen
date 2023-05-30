package com.jingdong.common.jdreactFramework.utils;

import android.os.Bundle;
import android.text.TextUtils;
import androidx.annotation.Keep;
import com.jingdong.common.web.IRouterParams;
import de.greenrobot.event.EventBus;
import org.json.JSONObject;

@Keep
/* loaded from: classes5.dex */
public class WebToJDReactEventUtils {
    public static final String CODE_RESULT_FAIL = "-1";
    public static final String CODE_RESULT_SUCCESS = "0";
    public static final String EXTRA_MODULENAME = "moduleName";
    public static final String EXTRA_NAME = "name";
    public static final String EXTRA_PARAMS = "params";
    public static final String EXTRA_URL = "url";

    public static String sendEvent(IRouterParams iRouterParams) {
        if (iRouterParams == null) {
            return "-1";
        }
        JDReactEvent jDReactEvent = new JDReactEvent();
        Bundle bundle = new Bundle();
        jDReactEvent.setBundle(bundle);
        String routerParam = iRouterParams.getRouterParam();
        if (TextUtils.isEmpty(routerParam)) {
            return "-1";
        }
        try {
            JSONObject jSONObject = new JSONObject(routerParam);
            String optString = jSONObject.optString("moduleName");
            String optString2 = jSONObject.optString("name");
            String optString3 = jSONObject.optString("url");
            String optString4 = jSONObject.optString("params");
            if (!TextUtils.isEmpty(optString) && !TextUtils.isEmpty(optString2)) {
                bundle.putString("moduleName", optString);
                bundle.putString("url", optString3);
                bundle.putString("name", optString2);
                if (!TextUtils.isEmpty(optString4)) {
                    bundle.putString("params", optString4);
                }
                EventBus.getDefault().post(jDReactEvent);
                return "0";
            }
            return "-1";
        } catch (Exception unused) {
            return "-1";
        }
    }
}
