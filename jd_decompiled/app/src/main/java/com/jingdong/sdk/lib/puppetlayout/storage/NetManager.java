package com.jingdong.sdk.lib.puppetlayout.storage;

import android.content.Context;
import android.text.TextUtils;
import com.jingdong.common.XView2.common.XView2Constants;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.tencent.mapsdk.internal.l4;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes8.dex */
public class NetManager {
    private static final String TAG = "NetManager";
    public static final int UPDATE_TYPE_ALL = 0;
    public static final int UPDATE_TYPE_DIFF = 2;
    public static final int UPDATE_TYPE_SPEC = 1;
    private static final NetManager ourInstance = new NetManager();

    public static NetManager getInstance() {
        return ourInstance;
    }

    public void requestUpdateStyles(Context context, String str, String str2, String str3, String str4, String str5, Runnable runnable, Runnable runnable2, boolean z, int i2) {
        requestUpdateStyles(context, str, str2, str3, str4, str5, null, null, null, runnable, runnable2, z, i2, null);
    }

    public void requestUpdateStyles(final Context context, String str, String str2, String str3, final String str4, final String str5, String str6, String str7, String str8, final Runnable runnable, final Runnable runnable2, boolean z, int i2, JDMtaListener jDMtaListener) {
        if (context == null) {
            return;
        }
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setHost(TextUtils.isEmpty(str) ? "api.m.jd.com" : str);
        httpSetting.setFunctionId("apollo_style_stylesQueryList");
        httpSetting.putJsonParam("apolloId", TextUtils.isEmpty(str2) ? "42ae75151e0e471490e4255d9ed64212" : str2);
        httpSetting.putJsonParam("apolloSecret", TextUtils.isEmpty(str3) ? "ef262fb841b9444ebb9afc6e3d48d2f5" : str3);
        if (!TextUtils.isEmpty(str6)) {
            httpSetting.putJsonParam("sdkClient", str6);
        }
        if (!TextUtils.isEmpty(str7)) {
            httpSetting.putJsonParam(l4.f16791e, str7);
        }
        if (!TextUtils.isEmpty(str8)) {
            httpSetting.putJsonParam("sdkName", str8);
        }
        httpSetting.setUseFastJsonParser(true);
        int i3 = 2;
        if (!TextUtils.isEmpty(str4) && !TextUtils.isEmpty(str5)) {
            JSONArray jSONArray = new JSONArray();
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(XView2Constants.STYLEID, str4);
                jSONObject.put("styleVersion", str5);
                jSONArray.put(jSONObject);
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
            httpSetting.putJsonParam("styles", jSONArray);
            httpSetting.putJsonParam("type", 1);
            i3 = 1;
        } else {
            JSONArray localStyles = TemplateUtils.getLocalStyles(context);
            if (localStyles != null && localStyles.length() > 0) {
                httpSetting.putJsonParam("styles", localStyles);
                httpSetting.putJsonParam("type", 2);
            } else {
                httpSetting.putJsonParam("type", 0);
                i3 = 0;
            }
        }
        int i4 = i2 != -1 ? i2 : i3;
        final int i5 = i4;
        HttpGroup.HttpTaskListener httpTaskListener = new HttpGroup.OnCommonListener() { // from class: com.jingdong.sdk.lib.puppetlayout.storage.NetManager.1
            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
            public void onEnd(HttpResponse httpResponse) {
                try {
                    if (TemplateUtils.syncLocalData(context, httpResponse.getFastJsonObject(), i5)) {
                        Runnable runnable3 = runnable;
                        if (runnable3 != null) {
                            runnable3.run();
                        }
                        if (i5 != 1) {
                            JDMtaUtils.onClick(context, "ApolloDynamic_ALLSDKSuccess", "");
                            return;
                        }
                        JDMtaUtils.onClick(context, "ApolloDynamic_SDKSuccess", "", str4 + "#" + str5);
                        return;
                    }
                    Runnable runnable4 = runnable2;
                    if (runnable4 != null) {
                        runnable4.run();
                    }
                } catch (Exception e3) {
                    e3.printStackTrace();
                }
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
            public void onError(HttpError httpError) {
                try {
                    Runnable runnable3 = runnable2;
                    if (runnable3 != null) {
                        runnable3.run();
                    }
                } catch (Exception e3) {
                    e3.printStackTrace();
                }
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnReadyListener
            public void onReady(HttpGroup.HttpSettingParams httpSettingParams) {
            }
        };
        httpSetting.setNotifyUser(false);
        httpSetting.setOnTouchEvent(false);
        httpSetting.setEffect(0);
        httpSetting.setListener(httpTaskListener);
        HttpGroupUtils.getHttpGroupaAsynPool().add(httpSetting);
        try {
            if (i4 != 1) {
                JDMtaUtils.onClick(context, "ApolloDynamic_ALLSDK", "");
            } else {
                JDMtaUtils.onClick(context, "ApolloDynamic_SDK", "", str4 + "#" + str5);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}
