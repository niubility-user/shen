package com.jd.manto.jdext.zhaixing;

import android.os.Bundle;
import android.text.TextUtils;
import com.jingdong.manto.MantoCore;
import com.jingdong.manto.jsapi.openmodule.AbstractMantoModule;
import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import com.jingdong.manto.jsapi.openmodule.JsApiMethod;
import com.jingdong.manto.jsapi.openmodule.MantoResultCallBack;
import com.jingdong.manto.network.common.IMantoHttpListener;
import com.jingdong.manto.network.common.MantoJDHttpHandler;
import java.util.List;
import org.json.JSONObject;

/* loaded from: classes17.dex */
public class JsApiZhaixing extends AbstractMantoModule {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes17.dex */
    public class a extends IMantoHttpListener {
        final /* synthetic */ MantoResultCallBack a;

        a(JsApiZhaixing jsApiZhaixing, MantoResultCallBack mantoResultCallBack) {
            this.a = mantoResultCallBack;
        }

        @Override // com.jingdong.manto.network.common.IMantoHttpListener
        public void onError(JSONObject jSONObject, Throwable th) {
            Bundle bundle = new Bundle(1);
            bundle.putString("error", th.getMessage());
            this.a.onFailed(bundle);
        }

        @Override // com.jingdong.manto.network.common.IMantoHttpListener
        public void onSuccess(JSONObject jSONObject) {
            Bundle bundle = new Bundle(1);
            if (TextUtils.equals(jSONObject.optString("code"), "0")) {
                bundle.putString("result", jSONObject.optString("result"));
                this.a.onSuccess(bundle);
                return;
            }
            bundle.putString("error", jSONObject.optString("error"));
            this.a.onFailed(bundle);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes17.dex */
    public class b extends IMantoHttpListener {
        final /* synthetic */ MantoResultCallBack a;

        b(JsApiZhaixing jsApiZhaixing, MantoResultCallBack mantoResultCallBack) {
            this.a = mantoResultCallBack;
        }

        @Override // com.jingdong.manto.network.common.IMantoHttpListener
        public void onError(JSONObject jSONObject, Throwable th) {
            Bundle bundle = new Bundle(1);
            bundle.putString("error", th.getMessage());
            this.a.onFailed(bundle);
        }

        @Override // com.jingdong.manto.network.common.IMantoHttpListener
        public void onSuccess(JSONObject jSONObject) {
            Bundle bundle = new Bundle(1);
            if (TextUtils.equals(jSONObject.optString("code"), "0")) {
                bundle.putString("result", jSONObject.optString("result"));
                this.a.onSuccess(bundle);
                return;
            }
            bundle.putString("error", jSONObject.optString("error"));
            this.a.onFailed(bundle);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes17.dex */
    public class c extends IMantoHttpListener {
        final /* synthetic */ MantoResultCallBack a;

        c(JsApiZhaixing jsApiZhaixing, MantoResultCallBack mantoResultCallBack) {
            this.a = mantoResultCallBack;
        }

        @Override // com.jingdong.manto.network.common.IMantoHttpListener
        public void onError(JSONObject jSONObject, Throwable th) {
            Bundle bundle = new Bundle(1);
            bundle.putString("error", th.getMessage());
            this.a.onFailed(bundle);
        }

        @Override // com.jingdong.manto.network.common.IMantoHttpListener
        public void onSuccess(JSONObject jSONObject) {
            Bundle bundle = new Bundle(1);
            if (TextUtils.equals(jSONObject.optString("code"), "0")) {
                bundle.putString("result", jSONObject.optString("result"));
                this.a.onSuccess(bundle);
                return;
            }
            bundle.putString("error", jSONObject.optString("error"));
            this.a.onFailed(bundle);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes17.dex */
    public class d extends IMantoHttpListener {
        final /* synthetic */ MantoResultCallBack a;

        d(JsApiZhaixing jsApiZhaixing, MantoResultCallBack mantoResultCallBack) {
            this.a = mantoResultCallBack;
        }

        @Override // com.jingdong.manto.network.common.IMantoHttpListener
        public void onError(JSONObject jSONObject, Throwable th) {
            Bundle bundle = new Bundle(1);
            bundle.putString("error", th.getMessage());
            this.a.onFailed(bundle);
        }

        @Override // com.jingdong.manto.network.common.IMantoHttpListener
        public void onSuccess(JSONObject jSONObject) {
            Bundle bundle = new Bundle(1);
            if (TextUtils.equals(jSONObject.optString("code"), "0")) {
                bundle.putString("result", jSONObject.optString("result"));
                this.a.onSuccess(bundle);
                return;
            }
            bundle.putString("error", jSONObject.optString("error"));
            this.a.onFailed(bundle);
        }
    }

    private void a(JSONObject jSONObject, Bundle bundle, MantoResultCallBack mantoResultCallBack) {
        MantoJDHttpHandler.commit(new com.jd.manto.jdext.zhaixing.a(bundle.getString("appid"), jSONObject), new c(this, mantoResultCallBack));
    }

    private void b(JSONObject jSONObject, Bundle bundle, MantoResultCallBack mantoResultCallBack) {
        MantoJDHttpHandler.commit(new com.jd.manto.jdext.zhaixing.b(bundle.getString("appid"), jSONObject), new d(this, mantoResultCallBack));
    }

    private void c(JSONObject jSONObject, Bundle bundle, MantoResultCallBack mantoResultCallBack) {
        MantoJDHttpHandler.commit(new com.jd.manto.jdext.zhaixing.c(bundle.getString("appid"), jSONObject, bundle.getString(IMantoBaseModule.ACTION_ID, "")), new b(this, mantoResultCallBack));
    }

    private void d(JSONObject jSONObject, Bundle bundle, MantoResultCallBack mantoResultCallBack) {
        MantoJDHttpHandler.commit(new com.jd.manto.jdext.zhaixing.d(bundle.getString("appid"), jSONObject, bundle.getString(IMantoBaseModule.ACTION_ID, "")), new a(this, mantoResultCallBack));
    }

    @Override // com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public String getModuleName() {
        return "zhaixing";
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoModule, com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public void handleMethod(String str, MantoCore mantoCore, Bundle bundle, MantoResultCallBack mantoResultCallBack) {
        JSONObject jSONObject;
        try {
            jSONObject = new JSONObject(bundle.getString("params"));
        } catch (Exception unused) {
            jSONObject = null;
        }
        if (jSONObject == null) {
            mantoResultCallBack.onFailed(null);
        } else if (TextUtils.equals(str, "mpWareSizeAndColor")) {
            d(jSONObject, bundle, mantoResultCallBack);
        } else if (TextUtils.equals(str, "mpWareDetail")) {
            c(jSONObject, bundle, mantoResultCallBack);
        } else if (TextUtils.equals(str, "mpOrderList")) {
            a(jSONObject, bundle, mantoResultCallBack);
        } else if (TextUtils.equals(str, "getShopSkuList")) {
            b(jSONObject, bundle, mantoResultCallBack);
        } else {
            Bundle bundle2 = new Bundle(1);
            bundle2.putString("error", "no matched method");
            mantoResultCallBack.onFailed(bundle2);
        }
    }

    @Override // com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public Bundle initData(String str, MantoCore mantoCore, JSONObject jSONObject) {
        Bundle bundle = new Bundle(1);
        bundle.putString("params", jSONObject.toString());
        return bundle;
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoModule
    protected void injectJsApiMethod(List<JsApiMethod> list) {
        list.add(new JsApiMethod("mpWareSizeAndColor", 1));
        list.add(new JsApiMethod("mpWareDetail", 0));
        list.add(new JsApiMethod("mpOrderList", 1));
        list.add(new JsApiMethod("getShopSkuList", 0));
    }
}
