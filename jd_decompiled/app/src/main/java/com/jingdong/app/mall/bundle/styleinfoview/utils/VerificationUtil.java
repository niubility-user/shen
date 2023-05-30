package com.jingdong.app.mall.bundle.styleinfoview.utils;

import com.jingdong.common.BaseActivity;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.config.HostConfig;
import com.jingdong.jdsdk.config.HostConstants;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;

/* loaded from: classes3.dex */
public class VerificationUtil {
    private static final String TAG = "VerificationUtil";
    private static long lastClickTime;

    /* loaded from: classes3.dex */
    public interface VerificationListener {
        void onEnd(HttpResponse httpResponse);

        void onError(HttpError httpError);
    }

    public static HttpSetting getVerificationImg(BaseActivity baseActivity, String str, final String str2, final String str3, final String str4, int i2, final VerificationListener verificationListener) {
        long currentTimeMillis = System.currentTimeMillis();
        if (Log.D) {
            Log.d(TAG, " getVerificationImg -->> interval : " + i2);
        }
        if (currentTimeMillis - lastClickTime < i2 * 1200) {
            if (Log.D) {
                Log.d(TAG, " getVerificationImg -->> in  interval < " + i2);
            }
            lastClickTime = currentTimeMillis;
            return null;
        }
        lastClickTime = currentTimeMillis;
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setFunctionId(str);
        httpSetting.setHost(HostConfig.getInstance().getHost(HostConstants.PERSONAL_HOST));
        httpSetting.setType(5000);
        httpSetting.setPriority(5000);
        httpSetting.setCacheMode(2);
        httpSetting.setListener(new HttpGroup.OnCommonListener() { // from class: com.jingdong.app.mall.bundle.styleinfoview.utils.VerificationUtil.1
            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
            public void onEnd(HttpResponse httpResponse) {
                VerificationListener verificationListener2 = verificationListener;
                if (verificationListener2 != null) {
                    verificationListener2.onEnd(httpResponse);
                }
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
            public void onError(HttpError httpError) {
                VerificationListener verificationListener2 = verificationListener;
                if (verificationListener2 != null) {
                    verificationListener2.onError(httpError);
                }
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnReadyListener
            public void onReady(HttpGroup.HttpSettingParams httpSettingParams) {
                httpSettingParams.putJsonParam("type", str2);
                httpSettingParams.putJsonParam("funcId", str3);
                httpSettingParams.putJsonParam("bsid", str4);
            }
        });
        baseActivity.getHttpGroupaAsynPool().add(httpSetting);
        return httpSetting;
    }
}
