package com.jd.manto.b;

import android.text.TextUtils;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.sdk.oklog.OKLog;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes17.dex */
public class b {

    /* loaded from: classes17.dex */
    class a implements HttpGroup.OnCommonListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ com.jd.manto.b.a f6247g;

        a(com.jd.manto.b.a aVar) {
            this.f6247g = aVar;
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
        public void onEnd(HttpResponse httpResponse) {
            if (httpResponse == null || httpResponse.getFastJsonObject() == null) {
                return;
            }
            JDJSONObject fastJsonObject = httpResponse.getFastJsonObject();
            String optString = fastJsonObject.optString("errcode");
            String optString2 = fastJsonObject.optString("token");
            if ("0".equals(optString) && !TextUtils.isEmpty(optString2) && this.f6247g != null) {
                this.f6247g.a(b.c("1", optString2, ""));
            }
            OKLog.d("isvToken", fastJsonObject.toJSONString());
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
        public void onError(HttpError httpError) {
            if (this.f6247g != null) {
                this.f6247g.onError(b.c("0", "", "requestIsvToken error:" + httpError));
            }
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnReadyListener
        public void onReady(HttpGroup.HttpSettingParams httpSettingParams) {
        }
    }

    public static void b(String str, com.jd.manto.b.a aVar) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            OKLog.d("isvToken", str);
            HttpSetting httpSetting = new HttpSetting();
            httpSetting.setFunctionId("isvObfuscator");
            httpSetting.setHost(Configuration.getNgwHost());
            httpSetting.setEffect(0);
            httpSetting.setNotifyUser(false);
            httpSetting.setConnectTimeout(5000);
            httpSetting.setUseFastJsonParser(true);
            httpSetting.putJsonParam("url", jSONObject.optString("url"));
            httpSetting.putJsonParam("id", jSONObject.optString("id", ""));
            httpSetting.setListener(new a(aVar));
            HttpGroupUtils.getHttpGroupaAsynPool().add(httpSetting);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String c(String str, String str2, String str3) {
        JSONObject jSONObject = new JSONObject();
        try {
            if (TextUtils.isEmpty(str)) {
                str = "";
            }
            jSONObject.put("status", str);
            if (TextUtils.isEmpty(str2)) {
                str2 = "";
            }
            jSONObject.put("data", str2);
            if (TextUtils.isEmpty(str3)) {
                str3 = "";
            }
            jSONObject.put("msg", str3);
            return jSONObject.toString();
        } catch (JSONException e2) {
            e2.printStackTrace();
            return jSONObject.toString();
        }
    }
}
