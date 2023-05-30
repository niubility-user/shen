package com.jingdong.app.mall.bundle.jdrhsdk.c;

import android.text.TextUtils;
import com.jingdong.app.mall.bundle.jdrhsdk.api.JDRiskHandleError;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.jdsdk.utils.JSONObjectProxy;
import com.tencent.mapsdk.internal.l4;
import java.net.URLEncoder;

/* loaded from: classes2.dex */
public class a {
    private static a a;
    private static boolean b;

    /* renamed from: com.jingdong.app.mall.bundle.jdrhsdk.c.a$a  reason: collision with other inner class name */
    /* loaded from: classes2.dex */
    class C0250a implements HttpGroup.OnAllListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ com.jingdong.app.mall.bundle.jdrhsdk.c.c f8146g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ com.jingdong.app.mall.bundle.jdrhsdk.c.e f8147h;

        /* renamed from: i  reason: collision with root package name */
        final /* synthetic */ HttpSetting f8148i;

        C0250a(com.jingdong.app.mall.bundle.jdrhsdk.c.c cVar, com.jingdong.app.mall.bundle.jdrhsdk.c.e eVar, HttpSetting httpSetting) {
            this.f8146g = cVar;
            this.f8147h = eVar;
            this.f8148i = httpSetting;
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
        public void onEnd(HttpResponse httpResponse) {
            if (httpResponse == null || this.f8146g == null) {
                return;
            }
            JSONObjectProxy jSONObject = httpResponse.getJSONObject();
            if (jSONObject == null || jSONObject.isNull("data") || TextUtils.isEmpty(jSONObject.optString("data"))) {
                JDRiskHandleError jDRiskHandleError = new JDRiskHandleError();
                jDRiskHandleError.setCode(JDRiskHandleError.CODE_CHECK_HTTP_RESPONSE_ERROR);
                jDRiskHandleError.setMsg(JDRiskHandleError.MSG_CHECK_HTTP_RESPONSE_ERROR);
                this.f8146g.a(jDRiskHandleError);
                a.this.c(jDRiskHandleError.getCode(), jDRiskHandleError.getMsg(), this.f8147h, this.f8148i, httpResponse);
                return;
            }
            com.jingdong.app.mall.bundle.jdrhsdk.c.b bVar = new com.jingdong.app.mall.bundle.jdrhsdk.c.b();
            bVar.b(jSONObject.optInt("code"));
            bVar.e(jSONObject.optString("msg"));
            bVar.c(jSONObject.optString("data"));
            this.f8146g.a((com.jingdong.app.mall.bundle.jdrhsdk.c.c) bVar);
            a.this.c(bVar.a(), bVar.f(), this.f8147h, this.f8148i, httpResponse);
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
        public void onError(HttpError httpError) {
            if (httpError == null || this.f8146g == null) {
                return;
            }
            JDRiskHandleError jDRiskHandleError = new JDRiskHandleError();
            jDRiskHandleError.setCode(httpError.getErrorCode());
            jDRiskHandleError.setMsg(httpError.getMessage());
            this.f8146g.a(jDRiskHandleError);
            a.this.b(jDRiskHandleError.getCode(), jDRiskHandleError.getMsg(), this.f8147h, this.f8148i, httpError);
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnProgressListener
        public void onProgress(int i2, int i3) {
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnStartListener
        public void onStart() {
        }
    }

    /* loaded from: classes2.dex */
    class b implements HttpGroup.OnAllListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ com.jingdong.app.mall.bundle.jdrhsdk.c.c f8150g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ com.jingdong.app.mall.bundle.jdrhsdk.c.e f8151h;

        /* renamed from: i  reason: collision with root package name */
        final /* synthetic */ HttpSetting f8152i;

        b(com.jingdong.app.mall.bundle.jdrhsdk.c.c cVar, com.jingdong.app.mall.bundle.jdrhsdk.c.e eVar, HttpSetting httpSetting) {
            this.f8150g = cVar;
            this.f8151h = eVar;
            this.f8152i = httpSetting;
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
        public void onEnd(HttpResponse httpResponse) {
            a aVar;
            int code;
            String msg;
            if (httpResponse == null || this.f8150g == null) {
                return;
            }
            JSONObjectProxy jSONObject = httpResponse.getJSONObject();
            if (jSONObject != null) {
                com.jingdong.app.mall.bundle.jdrhsdk.c.b bVar = new com.jingdong.app.mall.bundle.jdrhsdk.c.b();
                bVar.b(jSONObject.optInt("code"));
                bVar.e(jSONObject.optString("msg"));
                bVar.c(jSONObject.isNull("data") ? "" : jSONObject.optString("data"));
                this.f8150g.a((com.jingdong.app.mall.bundle.jdrhsdk.c.c) bVar);
                aVar = a.this;
                code = bVar.a();
                msg = bVar.f();
            } else {
                JDRiskHandleError jDRiskHandleError = new JDRiskHandleError();
                jDRiskHandleError.setCode(JDRiskHandleError.CODE_CHECK_HTTP_RESPONSE_ERROR);
                jDRiskHandleError.setMsg(JDRiskHandleError.MSG_CHECK_HTTP_RESPONSE_ERROR);
                this.f8150g.a(jDRiskHandleError);
                aVar = a.this;
                code = jDRiskHandleError.getCode();
                msg = jDRiskHandleError.getMsg();
            }
            aVar.c(code, msg, this.f8151h, this.f8152i, httpResponse);
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
        public void onError(HttpError httpError) {
            if (httpError == null || this.f8150g == null) {
                return;
            }
            JDRiskHandleError jDRiskHandleError = new JDRiskHandleError();
            jDRiskHandleError.setCode(httpError.getErrorCode());
            jDRiskHandleError.setMsg(httpError.getMessage());
            this.f8150g.a(jDRiskHandleError);
            a.this.b(jDRiskHandleError.getCode(), jDRiskHandleError.getMsg(), this.f8151h, this.f8152i, httpError);
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnProgressListener
        public void onProgress(int i2, int i3) {
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnStartListener
        public void onStart() {
        }
    }

    /* loaded from: classes2.dex */
    class c implements HttpGroup.OnAllListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ com.jingdong.app.mall.bundle.jdrhsdk.c.c f8154g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ com.jingdong.app.mall.bundle.jdrhsdk.c.e f8155h;

        /* renamed from: i  reason: collision with root package name */
        final /* synthetic */ HttpSetting f8156i;

        c(com.jingdong.app.mall.bundle.jdrhsdk.c.c cVar, com.jingdong.app.mall.bundle.jdrhsdk.c.e eVar, HttpSetting httpSetting) {
            this.f8154g = cVar;
            this.f8155h = eVar;
            this.f8156i = httpSetting;
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
        public void onEnd(HttpResponse httpResponse) {
            a aVar;
            int code;
            String msg;
            if (httpResponse == null || this.f8154g == null) {
                return;
            }
            JSONObjectProxy jSONObject = httpResponse.getJSONObject();
            if (jSONObject != null) {
                com.jingdong.app.mall.bundle.jdrhsdk.c.b bVar = new com.jingdong.app.mall.bundle.jdrhsdk.c.b();
                bVar.b(jSONObject.optInt("code"));
                bVar.e(jSONObject.optString("msg"));
                bVar.c(jSONObject.isNull("data") ? "" : jSONObject.optString("data"));
                this.f8154g.a((com.jingdong.app.mall.bundle.jdrhsdk.c.c) bVar);
                aVar = a.this;
                code = bVar.a();
                msg = bVar.f();
            } else {
                JDRiskHandleError jDRiskHandleError = new JDRiskHandleError();
                jDRiskHandleError.setCode(JDRiskHandleError.CODE_CHECK_HTTP_RESPONSE_ERROR);
                jDRiskHandleError.setMsg(JDRiskHandleError.MSG_CHECK_HTTP_RESPONSE_ERROR);
                this.f8154g.a(jDRiskHandleError);
                aVar = a.this;
                code = jDRiskHandleError.getCode();
                msg = jDRiskHandleError.getMsg();
            }
            aVar.c(code, msg, this.f8155h, this.f8156i, httpResponse);
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
        public void onError(HttpError httpError) {
            if (httpError == null || this.f8154g == null) {
                return;
            }
            JDRiskHandleError jDRiskHandleError = new JDRiskHandleError();
            jDRiskHandleError.setCode(httpError.getErrorCode());
            jDRiskHandleError.setMsg(httpError.getMessage());
            this.f8154g.a(jDRiskHandleError);
            a.this.b(jDRiskHandleError.getCode(), jDRiskHandleError.getMsg(), this.f8155h, this.f8156i, httpError);
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnProgressListener
        public void onProgress(int i2, int i3) {
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnStartListener
        public void onStart() {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public class d implements HttpGroup.OnAllListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ com.jingdong.app.mall.bundle.jdrhsdk.c.c f8158g;

        d(a aVar, com.jingdong.app.mall.bundle.jdrhsdk.c.c cVar) {
            this.f8158g = cVar;
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
        public void onEnd(HttpResponse httpResponse) {
            if (httpResponse == null || this.f8158g == null) {
                return;
            }
            JSONObjectProxy jSONObject = httpResponse.getJSONObject();
            if (jSONObject == null) {
                JDRiskHandleError jDRiskHandleError = new JDRiskHandleError();
                jDRiskHandleError.setCode(JDRiskHandleError.CODE_CHECK_HTTP_RESPONSE_ERROR);
                jDRiskHandleError.setMsg(JDRiskHandleError.MSG_CHECK_HTTP_RESPONSE_ERROR);
                this.f8158g.a(jDRiskHandleError);
                return;
            }
            com.jingdong.app.mall.bundle.jdrhsdk.c.b bVar = new com.jingdong.app.mall.bundle.jdrhsdk.c.b();
            bVar.b(jSONObject.optInt("code"));
            bVar.e(jSONObject.optString("msg"));
            bVar.c(jSONObject.optString("data"));
            this.f8158g.a((com.jingdong.app.mall.bundle.jdrhsdk.c.c) bVar);
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
        public void onError(HttpError httpError) {
            if (httpError == null || this.f8158g == null) {
                return;
            }
            JDRiskHandleError jDRiskHandleError = new JDRiskHandleError();
            jDRiskHandleError.setCode(httpError.getErrorCode());
            jDRiskHandleError.setMsg(httpError.getMessage());
            this.f8158g.a(jDRiskHandleError);
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnProgressListener
        public void onProgress(int i2, int i3) {
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnStartListener
        public void onStart() {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public class e implements com.jingdong.app.mall.bundle.jdrhsdk.c.c<com.jingdong.app.mall.bundle.jdrhsdk.c.b> {
        e(a aVar) {
        }

        @Override // com.jingdong.app.mall.bundle.jdrhsdk.c.c
        public void a(JDRiskHandleError jDRiskHandleError) {
        }

        @Override // com.jingdong.app.mall.bundle.jdrhsdk.c.c
        /* renamed from: b  reason: merged with bridge method [inline-methods] */
        public void a(com.jingdong.app.mall.bundle.jdrhsdk.c.b bVar) {
        }
    }

    private String a() {
        return b ? "beta-api.m.jd.com" : "api.m.jd.com";
    }

    public static a h() {
        a aVar;
        a aVar2 = a;
        if (aVar2 != null) {
            return aVar2;
        }
        synchronized (a.class) {
            if (a == null) {
                a = new a();
            }
            aVar = a;
        }
        return aVar;
    }

    void b(int i2, String str, com.jingdong.app.mall.bundle.jdrhsdk.c.e eVar, HttpSetting httpSetting, HttpError httpError) {
        String string;
        if (httpError != null) {
            try {
                if (httpError.getHttpResponse() != null) {
                    string = httpError.getHttpResponse().getString();
                    d(i2, str, eVar, httpSetting, string);
                }
            } catch (Exception unused) {
                return;
            }
        }
        string = "";
        d(i2, str, eVar, httpSetting, string);
    }

    void c(int i2, String str, com.jingdong.app.mall.bundle.jdrhsdk.c.e eVar, HttpSetting httpSetting, HttpResponse httpResponse) {
        String string;
        if (httpResponse != null) {
            try {
                if (httpResponse.getJSONObject() != null) {
                    string = httpResponse.getString();
                    d(i2, str, eVar, httpSetting, string);
                }
            } catch (Exception unused) {
                return;
            }
        }
        string = "";
        d(i2, str, eVar, httpSetting, string);
    }

    void d(int i2, String str, com.jingdong.app.mall.bundle.jdrhsdk.c.e eVar, HttpSetting httpSetting, String str2) {
        String d2;
        if (eVar != null) {
            try {
                d2 = eVar.d();
            } catch (Exception unused) {
                return;
            }
        } else {
            d2 = "";
        }
        e(i2, str, d2, httpSetting != null ? httpSetting.getJsonParamsString() : "", str2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void e(int i2, String str, String str2, String str3, String str4) {
        try {
            com.jingdong.app.mall.bundle.jdrhsdk.c.e eVar = new com.jingdong.app.mall.bundle.jdrhsdk.c.e();
            eVar.b(i2);
            if (TextUtils.isEmpty(str)) {
                str = "";
            }
            eVar.g(str);
            if (TextUtils.isEmpty(str2)) {
                str2 = "";
            }
            eVar.c(str2);
            if (TextUtils.isEmpty(str3)) {
                str3 = "";
            }
            eVar.k(str3);
            if (TextUtils.isEmpty(str4)) {
                str4 = "";
            }
            eVar.m(str4);
            k(eVar, new e(this));
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void f(com.jingdong.app.mall.bundle.jdrhsdk.c.e eVar, com.jingdong.app.mall.bundle.jdrhsdk.c.c<com.jingdong.app.mall.bundle.jdrhsdk.c.b> cVar) {
        if (eVar == null) {
            return;
        }
        try {
            HttpSetting httpSetting = new HttpSetting();
            httpSetting.setHost(a());
            httpSetting.setFunctionId("checkToken");
            httpSetting.putJsonParam("requestId", eVar.j());
            httpSetting.putJsonParam("evApi", eVar.d());
            httpSetting.putJsonParam("evType", eVar.f());
            httpSetting.putJsonParam("sid", eVar.p());
            httpSetting.putJsonParam("token", URLEncoder.encode(eVar.r(), "UTF-8"));
            httpSetting.putJsonParam("sdkClient", "android");
            httpSetting.putJsonParam(l4.f16791e, com.jingdong.app.mall.bundle.jdrhsdk.d.a.A());
            httpSetting.putJsonParam("unionwsws", com.jingdong.app.mall.bundle.jdrhsdk.d.a.C());
            com.jingdong.app.mall.bundle.jdrhsdk.d.a.m(httpSetting);
            httpSetting.setEncryptBody(true);
            httpSetting.setEffect(0);
            httpSetting.setListener(new b(cVar, eVar, httpSetting));
            HttpGroupUtils.getHttpGroupaAsynPool().add(httpSetting);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void g(boolean z) {
        b = z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void i(com.jingdong.app.mall.bundle.jdrhsdk.c.e eVar, com.jingdong.app.mall.bundle.jdrhsdk.c.c<com.jingdong.app.mall.bundle.jdrhsdk.c.b> cVar) {
        if (eVar == null) {
            return;
        }
        try {
            HttpSetting httpSetting = new HttpSetting();
            httpSetting.setHost(a());
            httpSetting.setFunctionId("createSid");
            httpSetting.putJsonParam("requestId", eVar.j());
            httpSetting.putJsonParam("evApi", eVar.d());
            httpSetting.putJsonParam("evType", eVar.f());
            httpSetting.putJsonParam("sdkClient", "android");
            httpSetting.putJsonParam(l4.f16791e, com.jingdong.app.mall.bundle.jdrhsdk.d.a.A());
            httpSetting.putJsonParam("unionwsws", com.jingdong.app.mall.bundle.jdrhsdk.d.a.C());
            com.jingdong.app.mall.bundle.jdrhsdk.d.a.m(httpSetting);
            httpSetting.setEncryptBody(true);
            httpSetting.setEffect(0);
            httpSetting.setListener(new C0250a(cVar, eVar, httpSetting));
            HttpGroupUtils.getHttpGroupaAsynPool().add(httpSetting);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void j(com.jingdong.app.mall.bundle.jdrhsdk.c.e eVar, com.jingdong.app.mall.bundle.jdrhsdk.c.c<com.jingdong.app.mall.bundle.jdrhsdk.c.b> cVar) {
        try {
            if (eVar == null) {
                JDRiskHandleError jDRiskHandleError = new JDRiskHandleError();
                jDRiskHandleError.setCode(-1001);
                jDRiskHandleError.setMsg(JDRiskHandleError.MSG_JAVA_EXCEPTION);
                cVar.a(jDRiskHandleError);
                return;
            }
            HttpSetting httpSetting = new HttpSetting();
            httpSetting.setHost(a());
            httpSetting.setFunctionId("loginCheckToken");
            httpSetting.putJsonParam("requestId", eVar.j());
            httpSetting.putJsonParam("evApi", eVar.d());
            httpSetting.putJsonParam("evType", eVar.f());
            httpSetting.putJsonParam("token", eVar.r());
            httpSetting.putJsonParam("sdkClient", "android");
            httpSetting.putJsonParam(l4.f16791e, com.jingdong.app.mall.bundle.jdrhsdk.d.a.A());
            httpSetting.putJsonParam("unionwsws", com.jingdong.app.mall.bundle.jdrhsdk.d.a.C());
            com.jingdong.app.mall.bundle.jdrhsdk.d.a.m(httpSetting);
            httpSetting.setEncryptBody(true);
            httpSetting.setEffect(0);
            httpSetting.setListener(new c(cVar, eVar, httpSetting));
            HttpGroupUtils.getHttpGroupaAsynPool().add(httpSetting);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    void k(com.jingdong.app.mall.bundle.jdrhsdk.c.e eVar, com.jingdong.app.mall.bundle.jdrhsdk.c.c<com.jingdong.app.mall.bundle.jdrhsdk.c.b> cVar) {
        if (eVar == null) {
            try {
                eVar = new com.jingdong.app.mall.bundle.jdrhsdk.c.e();
            } catch (Exception e2) {
                e2.printStackTrace();
                return;
            }
        }
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setHost(a());
        httpSetting.setFunctionId("reportInvokeLog");
        httpSetting.putJsonParam("code", Integer.valueOf(eVar.a()));
        httpSetting.putJsonParam("msg", eVar.h());
        httpSetting.putJsonParam("evApi", eVar.d());
        httpSetting.putJsonParam("requestInfo", eVar.l());
        httpSetting.putJsonParam("responseInfo", eVar.n());
        httpSetting.putJsonParam("sdkClient", "android");
        httpSetting.putJsonParam(l4.f16791e, com.jingdong.app.mall.bundle.jdrhsdk.d.a.A());
        com.jingdong.app.mall.bundle.jdrhsdk.d.a.m(httpSetting);
        httpSetting.setEncryptBody(true);
        httpSetting.setEffect(0);
        httpSetting.setListener(new d(this, cVar));
        HttpGroupUtils.getHttpGroupaAsynPool().add(httpSetting);
    }
}
