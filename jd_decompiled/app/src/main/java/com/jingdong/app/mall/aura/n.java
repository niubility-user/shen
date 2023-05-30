package com.jingdong.app.mall.aura;

import android.text.TextUtils;
import com.jingdong.app.mall.bundle.mobileConfig.net.ConfigRequestParams;
import com.jingdong.app.mall.bundle.mobileConfig.net.IConfigFetcher;
import com.jingdong.app.mall.bundle.mobileConfig.net.IConfigFetcherCallBack;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.jdsdk.utils.JSONObjectProxy;
import com.jingdong.sdk.oklog.OKLog;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class n implements IConfigFetcher {

    /* loaded from: classes19.dex */
    class a implements HttpGroup.OnCommonListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ IConfigFetcherCallBack f7946g;

        a(n nVar, IConfigFetcherCallBack iConfigFetcherCallBack) {
            this.f7946g = iConfigFetcherCallBack;
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
        public void onEnd(HttpResponse httpResponse) {
            JSONObjectProxy jSONObject;
            JSONObject optJSONObject;
            IConfigFetcherCallBack iConfigFetcherCallBack;
            try {
                if (OKLog.D) {
                    OKLog.d("JDMobileConfig", "request onEnd");
                }
                if (httpResponse == null || (jSONObject = httpResponse.getJSONObject()) == null || !TextUtils.equals(jSONObject.optString("code"), "0") || (optJSONObject = jSONObject.optJSONObject("data")) == null || (iConfigFetcherCallBack = this.f7946g) == null) {
                    return;
                }
                iConfigFetcherCallBack.onSuccess(optJSONObject);
            } catch (Exception e2) {
                if (OKLog.D) {
                    e2.printStackTrace();
                }
                IConfigFetcherCallBack iConfigFetcherCallBack2 = this.f7946g;
                if (iConfigFetcherCallBack2 != null) {
                    iConfigFetcherCallBack2.onError(e2);
                }
            }
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
        public void onError(HttpError httpError) {
            IConfigFetcherCallBack iConfigFetcherCallBack = this.f7946g;
            if (iConfigFetcherCallBack != null) {
                iConfigFetcherCallBack.onError(httpError);
            }
            if (OKLog.D) {
                OKLog.d("JDMobileConfig", "request onError");
            }
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnReadyListener
        public void onReady(HttpGroup.HttpSettingParams httpSettingParams) {
            if (OKLog.D) {
                OKLog.d("JDMobileConfig", "request onReady");
            }
        }
    }

    @Override // com.jingdong.app.mall.bundle.mobileConfig.net.IConfigFetcher
    public void fetch(ConfigRequestParams configRequestParams, IConfigFetcherCallBack iConfigFetcherCallBack) {
        if (configRequestParams == null) {
            if (iConfigFetcherCallBack != null) {
                iConfigFetcherCallBack.onError(new RuntimeException("requestParams is null"));
                return;
            }
            return;
        }
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setEffect(0);
        httpSetting.setFunctionId("basicConfig");
        httpSetting.setPost(false);
        httpSetting.putJsonParam("appId", configRequestParams.appId);
        httpSetting.putJsonParam("userId", configRequestParams.userId);
        httpSetting.setListener(new a(this, iConfigFetcherCallBack));
        HttpGroupUtils.getHttpGroupaAsynPool().add(httpSetting);
    }
}
