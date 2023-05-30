package com.jd.manto.center.e;

import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import org.json.JSONObject;

/* loaded from: classes17.dex */
public class c {
    public static HttpSetting a(JSONObject jSONObject) {
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setFunctionId("tinyAppFindHome");
        httpSetting.setJsonParams(jSONObject);
        httpSetting.setUseFastJsonParser(true);
        httpSetting.setLocalFileCache(false);
        httpSetting.setHost(com.jd.manto.center.c.a());
        httpSetting.setNotifyUser(false);
        httpSetting.setEffect(0);
        return httpSetting;
    }
}
