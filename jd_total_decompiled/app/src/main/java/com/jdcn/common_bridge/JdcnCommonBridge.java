package com.jdcn.common_bridge;

import android.content.Context;
import com.jdcn.service_router.interfaces.JdcnBridgeService;
import com.jdcn.service_router.interfaces.JdcnBridgeServiceCallback;
import com.jdcn.service_router.service.JdcnRouter;
import com.jdcn.service_router.service.JdcnServiceNotRegistException;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes18.dex */
public class JdcnCommonBridge {

    /* loaded from: classes18.dex */
    public interface JdcnCommonBridgeCallback {
        void callback(String str);
    }

    /* loaded from: classes18.dex */
    class a implements JdcnBridgeServiceCallback {
        final /* synthetic */ JSONObject a;
        final /* synthetic */ JSONObject b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ String f7276c;
        final /* synthetic */ String d;

        /* renamed from: e  reason: collision with root package name */
        final /* synthetic */ JdcnCommonBridgeCallback f7277e;

        a(JSONObject jSONObject, JSONObject jSONObject2, String str, String str2, JdcnCommonBridgeCallback jdcnCommonBridgeCallback) {
            this.a = jSONObject;
            this.b = jSONObject2;
            this.f7276c = str;
            this.d = str2;
            this.f7277e = jdcnCommonBridgeCallback;
        }

        @Override // com.jdcn.service_router.interfaces.JdcnBridgeServiceCallback
        public void serviceCallback(JSONObject jSONObject) {
            try {
                this.a.remove("routerParams");
                this.b.put("routerType", this.f7276c);
                this.b.put("routerCode", "0");
                this.b.put("function", this.d);
                this.b.put("result", jSONObject);
                this.a.put("riskResult", this.b);
                JdcnCommonBridgeCallback jdcnCommonBridgeCallback = this.f7277e;
                if (jdcnCommonBridgeCallback != null) {
                    jdcnCommonBridgeCallback.callback(this.a.toString());
                }
            } catch (JSONException unused) {
            }
        }
    }

    private static void a(String str, String str2, JSONObject jSONObject, JdcnCommonBridgeCallback jdcnCommonBridgeCallback) {
        if (jdcnCommonBridgeCallback == null || jSONObject == null) {
            return;
        }
        try {
            jSONObject.remove("routerParams");
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("routerType", str);
            jSONObject2.put("routerCode", str2);
            jSONObject.put("riskResult", jSONObject2);
            jdcnCommonBridgeCallback.callback(jSONObject.toString());
        } catch (JSONException unused) {
        }
    }

    public static void serviceCall(Context context, String str, JdcnCommonBridgeCallback jdcnCommonBridgeCallback) {
        JSONObject jSONObject;
        JSONObject jSONObject2 = null;
        try {
            jSONObject = new JSONObject(str);
        } catch (JdcnServiceNotRegistException unused) {
        } catch (ClassNotFoundException unused2) {
        } catch (JSONException unused3) {
        }
        try {
            JSONObject optJSONObject = jSONObject.optJSONObject("routerParams");
            if (optJSONObject == null) {
                a("", "-1", jSONObject, jdcnCommonBridgeCallback);
                return;
            }
            String optString = optJSONObject.optString("routerType");
            String optString2 = optJSONObject.optString("function");
            JSONObject jSONObject3 = new JSONObject();
            JdcnBridgeService jdcnBridgeService = (JdcnBridgeService) JdcnRouter.getService(optString);
            if (jdcnBridgeService == null) {
                a(optString, "-1", jSONObject, jdcnCommonBridgeCallback);
            } else {
                jdcnBridgeService.serviceCall(context, optJSONObject, new a(jSONObject, jSONObject3, optString, optString2, jdcnCommonBridgeCallback));
            }
        } catch (JdcnServiceNotRegistException unused4) {
            jSONObject2 = jSONObject;
            a("", "-2", jSONObject2, jdcnCommonBridgeCallback);
        } catch (ClassNotFoundException unused5) {
            jSONObject2 = jSONObject;
            a("", "-2", jSONObject2, jdcnCommonBridgeCallback);
        } catch (JSONException unused6) {
            jSONObject2 = jSONObject;
            a("", "-1", jSONObject2, jdcnCommonBridgeCallback);
        }
    }
}
