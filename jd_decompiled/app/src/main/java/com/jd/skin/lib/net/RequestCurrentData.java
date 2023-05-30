package com.jd.skin.lib.net;

import android.text.TextUtils;
import com.jd.framework.json.JDJSONObject;
import com.jd.skin.lib.JDSkinSDK;
import com.jd.skin.lib.Utils.ConstancesUtils;
import com.jd.skin.lib.inter.OnResultCompletListener;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.sdk.oklog.OKLog;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes18.dex */
public class RequestCurrentData {
    private static RequestCurrentData requestCurrentData = new RequestCurrentData();

    private RequestCurrentData() {
    }

    public static RequestCurrentData getInstance() {
        return requestCurrentData;
    }

    public void requestData(String str, final OnResultCompletListener onResultCompletListener) {
        if (OKLog.D) {
            OKLog.d("JDSkinSDK", "JDSkinSDK--requestData-->" + JDSkinSDK.getInstance().getIsNeedLogined());
        }
        if (!JDSkinSDK.getInstance().getIsNeedLogined() || JDSkinSDK.getInstance().getLoginState()) {
            String appId = JDSkinSDK.getInstance().getAppId();
            String skinUpdateTime = JDSkinSDK.getInstance().getSkinUpdateTime();
            if (OKLog.D) {
                OKLog.d("JDSkinSDK", "JDSkinSDK--requestData-->" + appId);
            }
            if (TextUtils.isEmpty(appId)) {
                return;
            }
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("appCode", appId);
                jSONObject.put("skinId", str);
                jSONObject.put(ConstancesUtils.SP_CURRENT_SKIN_UPDATETIME, skinUpdateTime);
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
            HttpSetting httpSetting = new HttpSetting();
            httpSetting.setFunctionId(ConstancesUtils.GET_THEME_INFO_FUNCTION_ID);
            httpSetting.setHost(Configuration.getNgwHost());
            httpSetting.setCacheMode(2);
            httpSetting.setUseFastJsonParser(true);
            httpSetting.setAttempts(0);
            httpSetting.setJsonParams(jSONObject);
            httpSetting.setListener(new HttpGroup.OnAllListener() { // from class: com.jd.skin.lib.net.RequestCurrentData.1
                @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
                public void onEnd(HttpResponse httpResponse) {
                    JDJSONObject fastJsonObject = httpResponse.getFastJsonObject();
                    if (OKLog.D) {
                        OKLog.d("JDSkinSDK", "JDSkinSDK--httpResponse-->" + fastJsonObject.toString());
                    }
                    if (fastJsonObject == null) {
                        OnResultCompletListener onResultCompletListener2 = onResultCompletListener;
                        if (onResultCompletListener2 != null) {
                            onResultCompletListener2.complet(false, null);
                        }
                    } else if (fastJsonObject.containsKey("success") && fastJsonObject.getBoolean("success").booleanValue() && fastJsonObject.containsKey("result")) {
                        OnResultCompletListener onResultCompletListener3 = onResultCompletListener;
                        if (onResultCompletListener3 != null) {
                            onResultCompletListener3.complet(true, fastJsonObject.getJSONObject("result").toJSONString());
                        }
                    } else {
                        OnResultCompletListener onResultCompletListener4 = onResultCompletListener;
                        if (onResultCompletListener4 != null) {
                            onResultCompletListener4.complet(false, null);
                        }
                    }
                }

                @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
                public void onError(HttpError httpError) {
                    if (OKLog.D) {
                        OKLog.d("JDSkinSDK", "JDSkinSDK--httpResponse-->" + httpError.toString());
                    }
                    OnResultCompletListener onResultCompletListener2 = onResultCompletListener;
                    if (onResultCompletListener2 != null) {
                        onResultCompletListener2.complet(false, null);
                    }
                }

                @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnProgressListener
                public void onProgress(int i2, int i3) {
                }

                @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnStartListener
                public void onStart() {
                }
            });
            HttpGroupUtils.getHttpGroupaAsynPool().add(httpSetting);
        }
    }
}
