package com.jingdong.common.recommend;

import com.jingdong.sdk.oklog.OKLog;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class RecommendFunctionId {
    public static String FUNCTIONID = "uniformRecommend";

    public static String getFunctionId(int i2, JSONObject jSONObject) {
        String str = "";
        if (jSONObject != null) {
            try {
                str = jSONObject.optString("sourceExt", "");
            } catch (Exception e2) {
                OKLog.d("RecommendException", e2.getMessage());
            }
        }
        if (i2 != 0) {
            if (i2 != 6) {
                if (i2 != 9) {
                    if (i2 == 48) {
                        if (jSONObject != null && "52".equals(str)) {
                            return "uniformRecommend52";
                        }
                    } else {
                        return FUNCTIONID;
                    }
                }
                if (jSONObject != null && RecommendConstant.HOME_B_SOURCE.equals(str)) {
                    return "uniformRecommend71";
                }
            }
            if (jSONObject != null && RecommendConstant.SHOPPING_B_SOURCE.equals(str)) {
                return "uniformRecommend72";
            }
        }
        if (jSONObject != null) {
            if (RecommendConstant.PERSONAL_B_SOURCE.equals(str)) {
                return "uniformRecommend78";
            }
        }
        return FUNCTIONID;
    }
}
