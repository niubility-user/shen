package com.jingdong.app.mall.bundle.marketing_sdk.contacts;

import android.text.TextUtils;
import com.jingdong.app.mall.bundle.marketing_sdk.contacts.listener.IContactAuthListener;
import com.jingdong.app.mall.bundle.marketing_sdk.contacts.listener.IContactAuthStatusListener;
import com.jingdong.app.mall.bundle.marketing_sdk.contacts.listener.IContactUploadListener;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.jdsdk.utils.JSONObjectProxy;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class b {

    /* loaded from: classes.dex */
    public static class a implements HttpGroup.OnCommonListener {

        /* renamed from: g */
        final /* synthetic */ IContactAuthStatusListener f8217g;

        a(IContactAuthStatusListener iContactAuthStatusListener) {
            this.f8217g = iContactAuthStatusListener;
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
        public void onEnd(HttpResponse httpResponse) {
            JSONObjectProxy jSONObject;
            if (httpResponse == null || httpResponse.getJSONObject() == null || (jSONObject = httpResponse.getJSONObject()) == null) {
                return;
            }
            JSONObject optJSONObject = jSONObject.optJSONObject("data");
            int optInt = jSONObject.optInt("code");
            if (optJSONObject != null) {
                int optInt2 = optJSONObject.optInt("authStatus");
                IContactAuthStatusListener iContactAuthStatusListener = this.f8217g;
                if (iContactAuthStatusListener != null) {
                    iContactAuthStatusListener.updateAuthStatusResult(optInt, optInt2);
                }
            }
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
        public void onError(HttpError httpError) {
            JSONObjectProxy jSONObject;
            if (httpError == null || httpError.getHttpResponse() == null || httpError.getHttpResponse().getJSONObject() == null || (jSONObject = httpError.getHttpResponse().getJSONObject()) == null) {
                return;
            }
            int optInt = jSONObject.optInt("code");
            IContactAuthStatusListener iContactAuthStatusListener = this.f8217g;
            if (iContactAuthStatusListener != null) {
                iContactAuthStatusListener.updateAuthStatusResult(optInt, -1);
            }
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnReadyListener
        public void onReady(HttpGroup.HttpSettingParams httpSettingParams) {
        }
    }

    /* renamed from: com.jingdong.app.mall.bundle.marketing_sdk.contacts.b$b */
    /* loaded from: classes.dex */
    public static class C0256b implements HttpGroup.OnCommonListener {

        /* renamed from: g */
        final /* synthetic */ IContactAuthListener f8218g;

        C0256b(IContactAuthListener iContactAuthListener) {
            this.f8218g = iContactAuthListener;
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
        public void onEnd(HttpResponse httpResponse) {
            JSONObjectProxy jSONObject;
            if (httpResponse == null || httpResponse.getJSONObject() == null || (jSONObject = httpResponse.getJSONObject()) == null) {
                return;
            }
            int optInt = jSONObject.optInt("code");
            String optString = jSONObject.optString("msg");
            IContactAuthListener iContactAuthListener = this.f8218g;
            if (iContactAuthListener != null) {
                iContactAuthListener.updateAuthResult(optInt, optString);
            }
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
        public void onError(HttpError httpError) {
            JSONObjectProxy jSONObject;
            if (httpError == null || httpError.getHttpResponse() == null || httpError.getHttpResponse().getJSONObject() == null || (jSONObject = httpError.getHttpResponse().getJSONObject()) == null) {
                return;
            }
            int optInt = jSONObject.optInt("code");
            String optString = jSONObject.optString("msg");
            IContactAuthListener iContactAuthListener = this.f8218g;
            if (iContactAuthListener != null) {
                iContactAuthListener.updateAuthResult(optInt, optString);
            }
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnReadyListener
        public void onReady(HttpGroup.HttpSettingParams httpSettingParams) {
        }
    }

    /* loaded from: classes.dex */
    public static class c implements HttpGroup.OnCommonListener {

        /* renamed from: g */
        final /* synthetic */ IContactAuthListener f8219g;

        c(IContactAuthListener iContactAuthListener) {
            this.f8219g = iContactAuthListener;
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
        public void onEnd(HttpResponse httpResponse) {
            JSONObjectProxy jSONObject;
            if (httpResponse == null || httpResponse.getJSONObject() == null || (jSONObject = httpResponse.getJSONObject()) == null) {
                return;
            }
            int optInt = jSONObject.optInt("code");
            String optString = jSONObject.optString("msg");
            IContactAuthListener iContactAuthListener = this.f8219g;
            if (iContactAuthListener != null) {
                iContactAuthListener.updateAuthResult(optInt, optString);
            }
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
        public void onError(HttpError httpError) {
            JSONObjectProxy jSONObject;
            if (httpError == null || httpError.getHttpResponse() == null || httpError.getHttpResponse().getJSONObject() == null || (jSONObject = httpError.getHttpResponse().getJSONObject()) == null) {
                return;
            }
            int optInt = jSONObject.optInt("code");
            String optString = jSONObject.optString("msg");
            IContactAuthListener iContactAuthListener = this.f8219g;
            if (iContactAuthListener != null) {
                iContactAuthListener.updateAuthResult(optInt, optString);
            }
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnReadyListener
        public void onReady(HttpGroup.HttpSettingParams httpSettingParams) {
        }
    }

    /* loaded from: classes.dex */
    public static class d implements HttpGroup.OnCommonListener {

        /* renamed from: g */
        final /* synthetic */ IContactUploadListener f8220g;

        d(IContactUploadListener iContactUploadListener) {
            this.f8220g = iContactUploadListener;
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
        public void onEnd(HttpResponse httpResponse) {
            JSONObjectProxy jSONObject;
            if (httpResponse == null || httpResponse.getJSONObject() == null || (jSONObject = httpResponse.getJSONObject()) == null) {
                return;
            }
            jSONObject.optInt("code");
            IContactUploadListener iContactUploadListener = this.f8220g;
            if (iContactUploadListener != null) {
                iContactUploadListener.updateUploadResult(jSONObject);
            }
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
        public void onError(HttpError httpError) {
            JSONObjectProxy jSONObject;
            if (httpError == null || httpError.getHttpResponse() == null || httpError.getHttpResponse().getJSONObject() == null || (jSONObject = httpError.getHttpResponse().getJSONObject()) == null) {
                return;
            }
            jSONObject.optInt("code");
            IContactUploadListener iContactUploadListener = this.f8220g;
            if (iContactUploadListener != null) {
                iContactUploadListener.updateUploadResult(jSONObject);
            }
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnReadyListener
        public void onReady(HttpGroup.HttpSettingParams httpSettingParams) {
        }
    }

    public static void a(String str, IContactAuthListener iContactAuthListener) {
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setHost("api.m.jd.com");
        httpSetting.setFunctionId("phoneBookUnified");
        httpSetting.setNeedRetryOnBusinessLayer(false);
        httpSetting.putQueryParam("appid", "android");
        if (!TextUtils.isEmpty(str)) {
            httpSetting.putJsonParam("addressActId", str);
        }
        httpSetting.putJsonParam("serviceName", "phoneBookAuth");
        httpSetting.setListener(new C0256b(iContactAuthListener));
        HttpGroupUtils.getHttpGroupaAsynPool().add(httpSetting);
    }

    public static void b(String str, IContactAuthStatusListener iContactAuthStatusListener) {
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setHost("api.m.jd.com");
        httpSetting.setFunctionId("phoneBookUnified");
        httpSetting.setNeedRetryOnBusinessLayer(false);
        httpSetting.setPost(false);
        httpSetting.putQueryParam("appid", "android");
        if (!TextUtils.isEmpty(str)) {
            httpSetting.putJsonParam("addressActId", str);
        }
        httpSetting.putJsonParam("serviceName", "phoneBookAuthCheck");
        httpSetting.setListener(new a(iContactAuthStatusListener));
        HttpGroupUtils.getHttpGroupaAsynPool().add(httpSetting);
    }

    public static void c(String str, JSONArray jSONArray, IContactUploadListener iContactUploadListener) {
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setHost("api.m.jd.com");
        httpSetting.setFunctionId("phoneBookUnified");
        httpSetting.setNeedRetryOnBusinessLayer(false);
        httpSetting.putQueryParam("appid", "android");
        if (!TextUtils.isEmpty(str)) {
            httpSetting.putJsonParam("addressActId", str);
        }
        httpSetting.putJsonParam("serviceName", "phoneBookInfoUpload");
        if (jSONArray != null && jSONArray.length() > 0) {
            httpSetting.putJsonParam("bookInfoList", jSONArray.toString());
        }
        httpSetting.putJsonParam("bookInfoEnFlag", "2");
        httpSetting.setListener(new d(iContactUploadListener));
        HttpGroupUtils.getHttpGroupaAsynPool().add(httpSetting);
    }

    public static void d(String str, IContactAuthListener iContactAuthListener) {
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setHost("api.m.jd.com");
        httpSetting.setFunctionId("phoneBookUnified");
        httpSetting.setNeedRetryOnBusinessLayer(false);
        httpSetting.putQueryParam("appid", "android");
        if (!TextUtils.isEmpty(str)) {
            httpSetting.putJsonParam("addressActId", str);
        }
        httpSetting.putJsonParam("serviceName", "phoneBookAuthCancel");
        httpSetting.setListener(new c(iContactAuthListener));
        HttpGroupUtils.getHttpGroupaAsynPool().add(httpSetting);
    }
}
