package com.jingdong.common.network;

import com.jingdong.app.mall.recommend.PerRecRouterImpl;
import com.jingdong.common.utils.JDPrivacyHelper;
import com.jingdong.jdsdk.JdSdk;
import java.net.URLEncoder;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class ExtParamUtils {
    private static String KEY_CFG_EXT = "cfgExt";
    private static String KEY_PRIVACY_STATUS = "pvcStu";
    private static String KEY_PRSTATE = "prstate";

    public static String getExtParamsStr() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(KEY_PRSTATE, PerRecRouterImpl.getPerRecStatusValue() ? "0" : "1");
            jSONObject.put(KEY_PRIVACY_STATUS, JDPrivacyHelper.isAcceptPrivacy(JdSdk.getInstance().getApplicationContext()) ? "1" : "0");
            jSONObject.put(KEY_CFG_EXT, CfgExtParamUtils.getExtParamsStr());
            return URLEncoder.encode(jSONObject.toString(), "UTF-8");
        } catch (Throwable th) {
            th.printStackTrace();
            return "";
        }
    }
}
