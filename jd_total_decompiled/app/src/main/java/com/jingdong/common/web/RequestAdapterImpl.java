package com.jingdong.common.web;

import android.text.TextUtils;
import com.jd.framework.json.JDJSONObject;
import com.jd.libs.hybrid.adapter.RequestAdapter;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.common.utils.WebViewHelper;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpRequest;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.manto.sdk.api.IMantoServerRequester;
import com.tencent.smtt.sdk.CookieManager;
import java.util.Iterator;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class RequestAdapterImpl extends RequestAdapter<JDJSONObject, HttpError> {
    HttpRequest localHttpRequest;

    @Override // com.jd.libs.hybrid.adapter.RequestAdapter
    public void cancel() {
        HttpRequest httpRequest = this.localHttpRequest;
        if (httpRequest != null) {
            httpRequest.stop();
        }
    }

    @Override // com.jd.libs.hybrid.adapter.RequestAdapter
    public void request(String str, String str2, String str3, JSONObject jSONObject, boolean z, int i2, String str4, String str5, final RequestAdapter.RequestCallback<JDJSONObject, HttpError> requestCallback) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if (TextUtils.isEmpty(str4)) {
            str4 = WebViewHelper.getJdUaInfo2().toString();
        }
        if (TextUtils.isEmpty(str5)) {
            str5 = CookieManager.getInstance().getCookie(str);
        }
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setCacheMode(2);
        if (!IMantoServerRequester.GET.equalsIgnoreCase(str3)) {
            httpSetting.setPost(true);
        }
        if (jSONObject != null) {
            try {
                Iterator<String> keys = jSONObject.keys();
                while (keys.hasNext()) {
                    String next = keys.next();
                    httpSetting.putJsonParam(next, jSONObject.get(next));
                }
            } catch (Throwable unused) {
            }
        }
        if (!TextUtils.isEmpty(str4)) {
            httpSetting.putJsonParam("h5UA", str4);
        }
        if (!TextUtils.isEmpty(str5)) {
            httpSetting.putJsonParam("h5Cookie", str5);
        }
        if (TextUtils.isEmpty(str2)) {
            httpSetting.setUrl(str);
        } else {
            httpSetting.setHost(str);
            httpSetting.setFunctionId(str2);
        }
        httpSetting.setEncryptBody(z);
        if (i2 > 0) {
            httpSetting.setCallTimeout(i2);
        }
        httpSetting.setListener(new HttpGroup.OnCancelListener() { // from class: com.jingdong.common.web.RequestAdapterImpl.1
            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnCancelListener
            public void onCancel() {
                RequestAdapter.RequestCallback requestCallback2 = requestCallback;
                if (requestCallback2 != null) {
                    requestCallback2.onCancel();
                }
            }
        });
        httpSetting.setListener(new HttpGroup.CustomOnAllListener() { // from class: com.jingdong.common.web.RequestAdapterImpl.2
            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
            public void onEnd(HttpResponse httpResponse) {
                RequestAdapter.RequestCallback requestCallback2 = requestCallback;
                if (requestCallback2 != null) {
                    if (httpResponse != null) {
                        requestCallback2.onSusses(httpResponse.getString(), httpResponse.getFastJsonObject());
                    } else {
                        requestCallback2.onSusses("", new JDJSONObject());
                    }
                }
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
            public void onError(HttpError httpError) {
                RequestAdapter.RequestCallback requestCallback2 = requestCallback;
                if (requestCallback2 != null) {
                    requestCallback2.onError(httpError != null ? httpError.getMessage() : "unknown http error", httpError);
                }
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnProgressListener
            public void onProgress(int i3, int i4) {
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnStartListener
            public void onStart() {
                RequestAdapter.RequestCallback requestCallback2 = requestCallback;
                if (requestCallback2 != null) {
                    requestCallback2.onStart();
                }
            }
        });
        this.localHttpRequest = HttpGroupUtils.getHttpGroupaAsynPool().add(httpSetting);
    }
}
