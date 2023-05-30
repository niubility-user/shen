package com.jingdong.app.mall.location;

import com.jingdong.common.web.IRouterParams;
import org.json.JSONObject;

@Deprecated
/* loaded from: classes4.dex */
public class JSLocationPermissionUtil {
    private static Object genObject(String str, String str2) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("status", str);
            jSONObject.put("msg", str2);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return jSONObject;
    }

    @Deprecated
    public static void requestLocationPermission(IRouterParams iRouterParams) {
        if (iRouterParams == null || iRouterParams.getContext() == null) {
            return;
        }
        iRouterParams.onCallBack(genObject("-1", "\u8be5\u65b9\u6cd5\u56e0\u6574\u6539\u539f\u56e0\u4e0b\u7ebf"));
    }
}
