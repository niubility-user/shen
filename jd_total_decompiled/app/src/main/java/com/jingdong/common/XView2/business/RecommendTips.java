package com.jingdong.common.XView2.business;

import android.content.Context;
import android.text.TextUtils;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.recommend.PerRecRouterImpl;
import com.jingdong.common.XView2.utils.XView2Utils;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.sdk.jdtoast.ToastUtils;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class RecommendTips {
    public static void openRecommendSwitch(Context context, int i2) {
        if (context == null || XView2Utils.isFastClick()) {
            return;
        }
        if (i2 != 13) {
            if (i2 == 14) {
                postPerRecStatus(context);
                return;
            }
            return;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("prstate", "0");
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        PerRecRouterImpl.savePerRecStatus(jSONObject);
        ToastUtils.showToastInCenter(context, "\u5df2\u5f00\u542f", 0);
    }

    private static void postPerRecStatus(final Context context) {
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setListener(new HttpGroup.OnCommonListener() { // from class: com.jingdong.common.XView2.business.RecommendTips.1
            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
            public void onEnd(HttpResponse httpResponse) {
                if (httpResponse == null) {
                    onError(null);
                    return;
                }
                JDJSONObject fastJsonObject = httpResponse.getFastJsonObject();
                if (fastJsonObject == null) {
                    onError(null);
                    return;
                }
                String string = fastJsonObject.getString("msg");
                if (TextUtils.isEmpty(string)) {
                    return;
                }
                ToastUtils.showToastInCenter(context, string, 0);
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
            public void onError(HttpError httpError) {
                if (TextUtils.isEmpty(httpError.getMessage())) {
                    return;
                }
                ToastUtils.showToastInCenter(context, httpError.getMessage(), 0);
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnReadyListener
            public void onReady(HttpGroup.HttpSettingParams httpSettingParams) {
            }
        });
        httpSetting.setFunctionId("advRecommendSwitch");
        httpSetting.setHost(Configuration.getPortalHost());
        httpSetting.setUseFastJsonParser(true);
        httpSetting.setEffect(0);
        HttpGroupUtils.getHttpGroupaAsynPool().add(httpSetting);
    }
}
