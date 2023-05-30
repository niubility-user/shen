package com.jingdong.manto.network.common;

import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import com.jingdong.manto.network.mantorequests.MantoBaseRequest;
import com.jingdong.manto.sdk.MantoSdkManager;
import com.jingdong.manto.sdk.api.ILogin;
import com.jingdong.manto.sdk.api.IMantoServerRequester;
import com.jingdong.manto.utils.MantoLog;
import org.json.JSONObject;

/* loaded from: classes16.dex */
public class MantoJDHttpHandler {

    /* loaded from: classes16.dex */
    public class a implements IMantoServerRequester.CallBack {
        final /* synthetic */ IMantoHttpListener a;

        a(IMantoHttpListener iMantoHttpListener) {
            this.a = iMantoHttpListener;
        }

        @Override // com.jingdong.manto.sdk.api.IMantoServerRequester.CallBack
        public void onError(Throwable th) {
            if (this.a != null) {
                MantoLog.e("HttpHandler", "onFailure", th);
                JSONObject jSONObject = new JSONObject();
                try {
                    try {
                        jSONObject.put("error", new JSONObject(th.getMessage()));
                    } catch (Throwable unused) {
                        jSONObject.put("error", th.getMessage());
                    }
                } catch (Throwable th2) {
                    th2.printStackTrace();
                }
                if (th == null) {
                    th = new RuntimeException("no data");
                }
                this.a.onError(jSONObject, th);
            }
        }

        @Override // com.jingdong.manto.sdk.api.IMantoServerRequester.CallBack
        public void onSuccess(JSONObject jSONObject) {
            IMantoHttpListener iMantoHttpListener = this.a;
            if (iMantoHttpListener != null) {
                if (jSONObject == null) {
                    iMantoHttpListener.onError(new JSONObject(), new RuntimeException("no data"));
                    return;
                }
                JSONObject optJSONObject = jSONObject.optJSONObject("error");
                if (optJSONObject != null) {
                    this.a.onError(jSONObject, new IllegalStateException(optJSONObject.optString("msg")));
                } else {
                    this.a.onSuccess(jSONObject);
                }
            }
        }
    }

    public static void commit(MantoBaseRequest mantoBaseRequest, IMantoHttpListener iMantoHttpListener) {
        if (mantoBaseRequest == null) {
            throw new IllegalArgumentException("request is null");
        }
        ILogin iLogin = (ILogin) com.jingdong.a.n(ILogin.class);
        if (iLogin == null) {
            throw new IllegalArgumentException(ILogin.class.getName() + " not impl.");
        }
        String cookie = iLogin.getCookie(com.jingdong.manto.b.f());
        if (TextUtils.isEmpty(cookie) || cookie.length() < 1) {
            try {
                Cursor query = com.jingdong.manto.c.a().getContentResolver().query(Uri.parse(String.format("content://%s%s/%s", com.jingdong.manto.c.b(), ".manto.loginInfoProvider", 4)), null, null, null, null);
                if (query != null && query.getCount() > 0) {
                    query.moveToFirst();
                    cookie = query.getString(query.getColumnIndex("result"));
                }
            } catch (Exception unused) {
            }
        }
        String str = cookie;
        String host = mantoBaseRequest.getHost();
        String functionId = mantoBaseRequest.getFunctionId();
        JSONObject requestParams = mantoBaseRequest.getRequestParams();
        JSONObject postBody = mantoBaseRequest.getPostBody();
        if (TextUtils.isEmpty(host)) {
            throw new IllegalArgumentException("url is null");
        }
        IMantoServerRequester iMantoServerRequester = (IMantoServerRequester) MantoSdkManager.instanceOf(IMantoServerRequester.class);
        if (iMantoServerRequester == null) {
            iMantoServerRequester = com.jingdong.manto.network.common.a.a();
        }
        iMantoServerRequester.request(mantoBaseRequest.useJDNet(), mantoBaseRequest.getRequestMethod() == MantoBaseRequest.RequestMethod.GET ? IMantoServerRequester.GET : IMantoServerRequester.POST, host, functionId, requestParams, postBody, str, new a(iMantoHttpListener));
    }
}
