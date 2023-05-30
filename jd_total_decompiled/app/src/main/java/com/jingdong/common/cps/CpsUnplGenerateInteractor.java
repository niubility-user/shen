package com.jingdong.common.cps;

import android.text.TextUtils;
import com.jingdong.cleanmvp.presenter.BaseInteractor;
import com.jingdong.common.deeplinkhelper.DeeplinkProductDetailHelper;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.common.utils.AdvertUtils;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;

/* loaded from: classes5.dex */
public class CpsUnplGenerateInteractor extends BaseInteractor {
    public static void getData(String str, String str2, String str3, String str4, String str5) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setFunctionId("unplGenerate");
        httpSetting.setUseFastJsonParser(true);
        httpSetting.putJsonParam("sku", str);
        httpSetting.putJsonParam(Configuration.UNION_ID, str2);
        httpSetting.putJsonParam("pin", str3);
        httpSetting.putJsonParam("lastunpl", AdvertUtils.getSi());
        httpSetting.putJsonParam("subPosition", str4);
        httpSetting.putJsonParam("id", str5);
        httpSetting.setHost(Configuration.getPortalHost());
        httpSetting.setAttempts(1);
        httpSetting.setListener(new HttpGroup.OnAllListener() { // from class: com.jingdong.common.cps.CpsUnplGenerateInteractor.1
            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
            public void onEnd(HttpResponse httpResponse) {
                if (httpResponse.getFastJsonObject() != null) {
                    String optString = httpResponse.getFastJsonObject().optString("unpl");
                    if (TextUtils.isEmpty(optString)) {
                        return;
                    }
                    AdvertUtils.initData("", optString);
                }
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
            public void onError(HttpError httpError) {
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

    @Override // com.jingdong.cleanmvp.presenter.BaseInteractor
    public void cancleIO() {
    }

    @Override // com.jingdong.cleanmvp.presenter.BaseInteractor
    public void clearState(int i2) {
    }

    public static void getData(String str, String str2, String str3, String str4, String str5, int i2, String str6, String str7) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setFunctionId("unplGenerate");
        httpSetting.setUseFastJsonParser(true);
        httpSetting.putJsonParam("sku", str);
        httpSetting.putJsonParam(Configuration.UNION_ID, str2);
        httpSetting.putJsonParam("pin", str3);
        httpSetting.putJsonParam("lastunpl", AdvertUtils.getSi());
        httpSetting.putJsonParam("subPosition", str4);
        httpSetting.putJsonParam("id", str5);
        httpSetting.putJsonParam(DeeplinkProductDetailHelper.LAYER_STYLE, Integer.valueOf(i2));
        httpSetting.putJsonParam("channel", str6);
        if (!TextUtils.isEmpty(str7)) {
            httpSetting.putJsonParam("source", str7);
        }
        httpSetting.setHost(Configuration.getPortalHost());
        httpSetting.setAttempts(1);
        httpSetting.setListener(new HttpGroup.OnAllListener() { // from class: com.jingdong.common.cps.CpsUnplGenerateInteractor.2
            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
            public void onEnd(HttpResponse httpResponse) {
                if (httpResponse.getFastJsonObject() != null) {
                    String optString = httpResponse.getFastJsonObject().optString("unpl");
                    if (TextUtils.isEmpty(optString)) {
                        return;
                    }
                    AdvertUtils.initData("", optString);
                }
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
            public void onError(HttpError httpError) {
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnProgressListener
            public void onProgress(int i3, int i4) {
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnStartListener
            public void onStart() {
            }
        });
        HttpGroupUtils.getHttpGroupaAsynPool().add(httpSetting);
    }

    public static void getData(String str, String str2, String str3, String str4, String str5, int i2, String str6) {
        getData(str, str2, str3, str4, str5, i2, str6, "");
    }
}
