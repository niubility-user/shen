package com.jd.manto.jdext.cart;

import android.os.Bundle;
import android.text.TextUtils;
import com.jingdong.manto.MantoCore;
import com.jingdong.manto.jsapi.openmodule.AbstractMantoModule;
import com.jingdong.manto.jsapi.openmodule.JsApiMethod;
import com.jingdong.manto.jsapi.openmodule.MantoResultCallBack;
import com.jingdong.manto.network.common.IMantoHttpListener;
import com.jingdong.manto.network.common.MantoJDHttpHandler;
import java.util.List;
import org.json.JSONObject;

/* loaded from: classes17.dex */
public class JsApiCart extends AbstractMantoModule {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes17.dex */
    public class a extends IMantoHttpListener {
        final /* synthetic */ MantoResultCallBack a;

        a(JsApiCart jsApiCart, MantoResultCallBack mantoResultCallBack) {
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
            String optString = jSONObject.optString("code");
            String optString2 = jSONObject.optString("error");
            if (TextUtils.equals(optString, "0")) {
                this.a.onSuccess(bundle);
                return;
            }
            bundle.putString("error", optString2);
            this.a.onFailed(bundle);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes17.dex */
    public class b extends IMantoHttpListener {
        final /* synthetic */ MantoResultCallBack a;

        b(JsApiCart jsApiCart, MantoResultCallBack mantoResultCallBack) {
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
            String optString = jSONObject.optString("code");
            String optString2 = jSONObject.optString("error");
            if (TextUtils.equals(optString, "0")) {
                this.a.onSuccess(bundle);
                return;
            }
            bundle.putString("error", optString2);
            this.a.onFailed(bundle);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes17.dex */
    public class c extends IMantoHttpListener {
        final /* synthetic */ MantoResultCallBack a;

        c(JsApiCart jsApiCart, MantoResultCallBack mantoResultCallBack) {
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
            String optString = jSONObject.optString("code");
            String optString2 = jSONObject.optString("error");
            if (TextUtils.equals(optString, "0")) {
                this.a.onSuccess(bundle);
                return;
            }
            bundle.putString("error", optString2);
            this.a.onFailed(bundle);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes17.dex */
    public class d extends IMantoHttpListener {
        final /* synthetic */ MantoResultCallBack a;

        d(JsApiCart jsApiCart, MantoResultCallBack mantoResultCallBack) {
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
            String optString = jSONObject.optString("code");
            String optString2 = jSONObject.optString("error");
            if (TextUtils.equals(optString, "0")) {
                bundle.putString("result", jSONObject.optJSONObject("result").toString());
                this.a.onSuccess(bundle);
                return;
            }
            bundle.putString("error", optString2);
            this.a.onFailed(bundle);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes17.dex */
    public class e extends IMantoHttpListener {
        final /* synthetic */ MantoResultCallBack a;

        e(JsApiCart jsApiCart, MantoResultCallBack mantoResultCallBack) {
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
            String optString = jSONObject.optString("code");
            String optString2 = jSONObject.optString("error");
            if (TextUtils.equals(optString, "0")) {
                this.a.onSuccess(bundle);
                return;
            }
            bundle.putString("error", optString2);
            this.a.onFailed(bundle);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes17.dex */
    public class f extends IMantoHttpListener {
        final /* synthetic */ MantoResultCallBack a;

        f(JsApiCart jsApiCart, MantoResultCallBack mantoResultCallBack) {
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
            String optString = jSONObject.optString("code");
            String optString2 = jSONObject.optString("error");
            if (TextUtils.equals(optString, "0")) {
                this.a.onSuccess(bundle);
                return;
            }
            bundle.putString("error", optString2);
            this.a.onFailed(bundle);
        }
    }

    private void a(String str, MantoResultCallBack mantoResultCallBack) {
        MantoJDHttpHandler.commit(new com.jd.manto.jdext.cart.d(str), new d(this, mantoResultCallBack));
    }

    private void a(String str, JSONObject jSONObject, MantoResultCallBack mantoResultCallBack) {
        MantoJDHttpHandler.commit(new com.jd.manto.jdext.cart.a(str, jSONObject.optJSONArray("skus")), new a(this, mantoResultCallBack));
    }

    private void b(String str, JSONObject jSONObject, MantoResultCallBack mantoResultCallBack) {
        MantoJDHttpHandler.commit(new com.jd.manto.jdext.cart.b(str, jSONObject.optJSONArray("skuInfos")), new b(this, mantoResultCallBack));
    }

    private void c(String str, JSONObject jSONObject, MantoResultCallBack mantoResultCallBack) {
        MantoJDHttpHandler.commit(new com.jd.manto.jdext.cart.c(str, jSONObject.optJSONArray("skuInfos")), new c(this, mantoResultCallBack));
    }

    private void d(String str, JSONObject jSONObject, MantoResultCallBack mantoResultCallBack) {
        MantoJDHttpHandler.commit(new com.jd.manto.jdext.cart.e(str, jSONObject.optJSONArray("skus")), new e(this, mantoResultCallBack));
    }

    private void e(String str, JSONObject jSONObject, MantoResultCallBack mantoResultCallBack) {
        MantoJDHttpHandler.commit(new com.jd.manto.jdext.cart.f(str, jSONObject.optJSONArray("skuInfos")), new f(this, mantoResultCallBack));
    }

    @Override // com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public String getModuleName() {
        return "cartModule";
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
            return;
        }
        String string = bundle.getString("appid");
        if (TextUtils.equals(str, "mpAddCart")) {
            a(string, jSONObject, mantoResultCallBack);
        } else if (TextUtils.equals(str, "mpChangeCart")) {
            b(string, jSONObject, mantoResultCallBack);
        } else if (TextUtils.equals(str, "mpCheckCart")) {
            c(string, jSONObject, mantoResultCallBack);
        } else if (TextUtils.equals(str, "mpGetCart")) {
            a(string, mantoResultCallBack);
        } else if (TextUtils.equals(str, "mpRemoveCart")) {
            d(string, jSONObject, mantoResultCallBack);
        } else if (TextUtils.equals(str, "mpUnCheckCart")) {
            e(string, jSONObject, mantoResultCallBack);
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
        list.add(new JsApiMethod("mpAddCart", 1));
        list.add(new JsApiMethod("mpChangeCart", 1));
        list.add(new JsApiMethod("mpCheckCart", 1));
        list.add(new JsApiMethod("mpGetCart", 1));
        list.add(new JsApiMethod("mpRemoveCart", 1));
        list.add(new JsApiMethod("mpUnCheckCart", 1));
    }
}
