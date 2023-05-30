package com.jingdong.common.ad;

import android.text.TextUtils;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.XView2.common.XView2Constants;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class AdUtils {
    public static final String AD_CLICK_URL = "ad_click_url";
    public static final String TAG = "AdUtils";
    public static final String TIMEOUT_EXCEPTION = "SocketTimeoutException";

    public static void adReport(String str) {
        String parseFromOpenAppParams = parseFromOpenAppParams(str);
        if (TextUtils.isEmpty(parseFromOpenAppParams)) {
            return;
        }
        sendRequestByUrl(parseFromOpenAppParams);
    }

    public static void mtaClickUrlResponseState(String str, String str2) {
        String str3 = str + CartConstant.KEY_YB_INFO_LINK + str2;
        if (Log.D) {
            Log.d(TAG, "eventParam ----> " + str3);
        }
        JDMtaUtils.sendExposureData(JdSdk.getInstance().getApplicationContext(), "com.jingdong.app.mall.open.InterfaceActivity", "AdRequest", "", "StartUp_OpenApp_AdRequest", str3, "", "", "");
    }

    private static String parseFromOpenAppParams(String str) {
        String str2 = "";
        try {
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        JSONObject jSONObject = new JSONObject(str);
        str2 = jSONObject.optString(AD_CLICK_URL);
        if (jSONObject.has(AD_CLICK_URL) && TextUtils.isEmpty(str2)) {
            mtaClickUrlResponseState("6", "-100");
        }
        if (Log.D) {
            Log.d(TAG, "adClickUrl = " + str2);
        }
        return str2;
    }

    private static void sendRequestByUrl(final String str) {
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setUrl(str);
        httpSetting.setEffect(0);
        httpSetting.setUseFastJsonParser(true);
        httpSetting.setPost(false);
        httpSetting.setAttempts(1);
        httpSetting.setListener(new HttpGroup.OnAllListener() { // from class: com.jingdong.common.ad.AdUtils.1
            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
            public void onEnd(HttpResponse httpResponse) {
                String str2;
                String str3 = "6";
                if (Log.D) {
                    Log.d(AdUtils.TAG, "sendRequestByUrl onEnd ------> " + httpResponse.toString());
                }
                try {
                } catch (Exception e2) {
                    if (Log.D) {
                        Log.d(AdUtils.TAG, "sendRequestByUrl onEnd exception = " + e2.toString());
                    }
                }
                if (httpResponse.getFastJsonObject() != null) {
                    JDJSONObject fastJsonObject = httpResponse.getFastJsonObject();
                    String optString = fastJsonObject.optString(XView2Constants.STATE);
                    if (Log.D) {
                        Log.d(AdUtils.TAG, "sendRequestByUrl onEnd state = " + optString);
                    }
                    if (fastJsonObject.containsKey(XView2Constants.STATE) && !TextUtils.isEmpty(optString)) {
                        if ("1".equals(optString)) {
                            str3 = "1";
                        } else {
                            str2 = "0".equals(optString) ? "2" : "";
                        }
                    }
                    AdUtils.mtaClickUrlResponseState(str3, str);
                }
                str3 = str2;
                AdUtils.mtaClickUrlResponseState(str3, str);
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
            public void onError(HttpError httpError) {
                if (Log.D) {
                    Log.d(AdUtils.TAG, "sendRequestByUrl onError ------> " + httpError.toString());
                }
                String message = httpError.getException().getMessage();
                if (!TextUtils.isEmpty(message) && message.contains(AdUtils.TIMEOUT_EXCEPTION)) {
                    AdUtils.mtaClickUrlResponseState("5", str);
                } else {
                    AdUtils.mtaClickUrlResponseState("6", str);
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
