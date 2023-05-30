package com.jingdong.common.recommend;

import android.text.TextUtils;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class RecommendNetUtil {
    public static void recommendFeedBack(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("expoLog", str);
        } catch (JSONException unused) {
        }
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setFunctionId("dataStreamExpo");
        httpSetting.setJsonParams(jSONObject);
        httpSetting.setHost(Configuration.getPersonalHost());
        httpSetting.setEffect(0);
        HttpGroupUtils.getHttpGroupaAsynPool().add(httpSetting);
    }
}
