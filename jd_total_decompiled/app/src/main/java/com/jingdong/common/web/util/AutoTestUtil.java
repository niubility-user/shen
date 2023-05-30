package com.jingdong.common.web.util;

import com.jingdong.common.web.IRouterParams;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes12.dex */
public class AutoTestUtil {
    public static void asyncAutoTest(IRouterParams iRouterParams) {
        try {
            JSONObject jSONObject = new JSONObject(iRouterParams.getRouterParam());
            String optString = jSONObject.optString("autoId");
            String optString2 = jSONObject.optString("type");
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("autoId", optString);
            jSONObject2.put("type", optString2);
            iRouterParams.onCallBack(jSONObject2);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    public static JSONObject syncAutoTest(IRouterParams iRouterParams) {
        try {
            JSONObject jSONObject = new JSONObject(iRouterParams.getRouterParam());
            String optString = jSONObject.optString("autoId");
            String optString2 = jSONObject.optString("type");
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("autoId", optString);
            jSONObject2.put("type", optString2);
            return jSONObject2;
        } catch (JSONException e2) {
            e2.printStackTrace();
            return null;
        }
    }
}
