package com.jd.manto.d;

import android.text.TextUtils;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.jdsdk.utils.JSONObjectProxy;
import com.jingdong.manto.sdk.api.IMantoServerRequester;
import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONObject;

/* loaded from: classes17.dex */
public class e implements IMantoServerRequester {

    /* loaded from: classes17.dex */
    class a implements HttpGroup.OnCommonListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ IMantoServerRequester.CallBack f6650g;

        a(e eVar, IMantoServerRequester.CallBack callBack) {
            this.f6650g = callBack;
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
        public void onEnd(HttpResponse httpResponse) {
            if (httpResponse != null && httpResponse.getJSONObject() != null) {
                JSONObjectProxy jSONObject = httpResponse.getJSONObject();
                IMantoServerRequester.CallBack callBack = this.f6650g;
                if (callBack != null) {
                    callBack.onSuccess(jSONObject);
                    return;
                }
                return;
            }
            IMantoServerRequester.CallBack callBack2 = this.f6650g;
            if (callBack2 != null) {
                callBack2.onError(new RuntimeException("HttpSettingRequestImpl data error"));
            }
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
        public void onError(HttpError httpError) {
            RuntimeException runtimeException;
            Throwable exception;
            if (this.f6650g != null) {
                if (httpError == null) {
                    exception = new RuntimeException("HttpSettingRequestImpl error");
                } else {
                    if (httpError.getHttpResponse() != null) {
                        runtimeException = new RuntimeException("" + httpError.getHttpResponse().getString());
                    } else if (httpError.getException() != null) {
                        exception = httpError.getException();
                    } else {
                        runtimeException = new RuntimeException("" + httpError.toString());
                    }
                    exception = runtimeException;
                }
                this.f6650g.onError(exception);
            }
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnReadyListener
        public void onReady(HttpGroup.HttpSettingParams httpSettingParams) {
        }
    }

    @Override // com.jingdong.manto.sdk.api.IMantoServerRequester
    public void request(boolean z, String str, String str2, String str3, JSONObject jSONObject, JSONObject jSONObject2, String str4, IMantoServerRequester.CallBack callBack) {
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setPost(TextUtils.equals(IMantoServerRequester.POST, str));
        if (str2.startsWith("https://")) {
            str2 = str2.replace("https://", "");
        } else if (str2.startsWith("http://")) {
            str2 = str2.replace("http://", "");
        }
        if (jSONObject2.optBoolean("useInnerAppId", false)) {
            httpSetting.setAppId("jda-api");
            httpSetting.setSecretKey(com.jingdong.a.i());
            jSONObject2.remove("useInnerAppId");
        }
        httpSetting.setHost(str2);
        httpSetting.setEncryptBody(true);
        httpSetting.setUseFastJsonParser(false);
        httpSetting.setFunctionId(str3);
        httpSetting.setEffect(0);
        httpSetting.setNotifyUser(false);
        httpSetting.putJsonParam(jSONObject);
        if (jSONObject2 != null) {
            try {
                Iterator<String> keys = jSONObject2.keys();
                while (keys.hasNext()) {
                    String next = keys.next();
                    httpSetting.putJsonParam(next, jSONObject2.get(next));
                }
            } catch (Exception unused) {
            }
        }
        if (!TextUtils.isEmpty(str4)) {
            HashMap hashMap = new HashMap();
            hashMap.put("Cookie", str4);
            httpSetting.setHeaderMap(hashMap);
        }
        httpSetting.setListener(new a(this, callBack));
        HttpGroupUtils.getHttpGroupaAsynPool().add(httpSetting);
    }
}
