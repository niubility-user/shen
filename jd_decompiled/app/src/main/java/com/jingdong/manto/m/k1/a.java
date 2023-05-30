package com.jingdong.manto.m.k1;

import android.os.Bundle;
import com.jd.dynamic.DYConstants;
import com.jingdong.manto.MantoCore;
import com.jingdong.manto.jsapi.openmodule.AbstractMantoModule;
import com.jingdong.manto.jsapi.openmodule.JsApiMethod;
import com.jingdong.manto.jsapi.openmodule.MantoResultCallBack;
import com.jingdong.manto.m.c0;
import com.jingdong.manto.m.h0;
import com.jingdong.manto.network.common.IMantoHttpListener;
import com.jingdong.manto.network.common.MantoJDHttpHandler;
import com.jingdong.manto.network.mantorequests.a0;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class a extends AbstractMantoModule {

    /* renamed from: com.jingdong.manto.m.k1.a$a  reason: collision with other inner class name */
    /* loaded from: classes15.dex */
    class C0581a extends IMantoHttpListener {
        final /* synthetic */ MantoResultCallBack a;

        C0581a(a aVar, MantoResultCallBack mantoResultCallBack) {
            this.a = mantoResultCallBack;
        }

        @Override // com.jingdong.manto.network.common.IMantoHttpListener
        public void onError(JSONObject jSONObject, Throwable th) {
            Bundle bundle = new Bundle();
            bundle.putString("errorMessage", th.getMessage());
            this.a.onFailed(bundle);
        }

        @Override // com.jingdong.manto.network.common.IMantoHttpListener
        public void onSuccess(JSONObject jSONObject) {
            if (jSONObject.optInt("code", -1) == 0) {
                this.a.onSuccess(null);
                return;
            }
            Bundle bundle = new Bundle();
            bundle.putString("errorMessage", jSONObject.optString("errorMessage"));
            this.a.onFailed(bundle);
        }
    }

    @Override // com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public String getModuleName() {
        return "jsapiReport";
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoModule, com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public void handleMethod(String str, MantoCore mantoCore, Bundle bundle, MantoResultCallBack mantoResultCallBack) {
        try {
            StringBuilder sb = new StringBuilder();
            StringBuilder sb2 = new StringBuilder();
            Map<String, c0> b = h0.b();
            Map<String, c0> a = h0.a();
            Iterator<String> it = b.keySet().iterator();
            while (it.hasNext()) {
                sb.append(it.next());
                sb.append(DYConstants.DY_REGEX_COMMA);
            }
            Iterator<String> it2 = a.keySet().iterator();
            while (it2.hasNext()) {
                sb2.append(it2.next());
                sb2.append(DYConstants.DY_REGEX_COMMA);
            }
            MantoJDHttpHandler.commit(new a0(sb.toString(), sb2.toString()), new C0581a(this, mantoResultCallBack));
        } catch (Throwable unused) {
        }
    }

    @Override // com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public Bundle initData(String str, MantoCore mantoCore, JSONObject jSONObject) {
        return new Bundle(1);
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoModule
    protected void injectJsApiMethod(List<JsApiMethod> list) {
        list.add(new JsApiMethod("nativeAbilityReport", 1));
    }
}
