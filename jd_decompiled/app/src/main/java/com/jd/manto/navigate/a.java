package com.jd.manto.navigate;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.hms.framework.common.ContainerUtils;
import com.jd.cashier.app.jdlibcutter.protocol.pair.PairKey;
import com.jd.manto.router.RouterProxyActivity;
import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVActivityJumpUtil;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.common.unification.router.JDRouterUrlBuilder;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.manto.MantoCore;
import com.jingdong.manto.jsapi.openmodule.AbstractMantoModule;
import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import com.jingdong.manto.jsapi.openmodule.JsApiMethod;
import com.jingdong.manto.jsapi.openmodule.MantoResultCallBack;
import com.jingdong.manto.network.common.IMantoHttpListener;
import com.jingdong.manto.network.common.MantoJDHttpHandler;
import com.jingdong.manto.pkg.db.entity.PkgDetailEntity;
import com.jingdong.manto.sdk.api.ILogin;
import com.jingdong.manto.sdk.api.IRouter;
import java.net.URLEncoder;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes17.dex */
public class a extends AbstractMantoModule {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.jd.manto.navigate.a$a  reason: collision with other inner class name */
    /* loaded from: classes17.dex */
    public class C0202a extends IMantoHttpListener {
        final /* synthetic */ Context a;
        final /* synthetic */ Bundle b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ MantoResultCallBack f6780c;

        C0202a(a aVar, Context context, Bundle bundle, MantoResultCallBack mantoResultCallBack) {
            this.a = context;
            this.b = bundle;
            this.f6780c = mantoResultCallBack;
        }

        @Override // com.jingdong.manto.network.common.IMantoHttpListener
        public void onError(JSONObject jSONObject, Throwable th) {
            Bundle bundle = new Bundle(1);
            bundle.putString("error", th.getMessage());
            this.f6780c.onFailed(bundle);
        }

        @Override // com.jingdong.manto.network.common.IMantoHttpListener
        public void onSuccess(JSONObject jSONObject) {
            Bundle bundle = new Bundle(1);
            String optString = jSONObject.optString("code");
            String optString2 = jSONObject.optString("error");
            if (TextUtils.equals(optString, "0")) {
                boolean optBoolean = jSONObject.optBoolean("result", true);
                if (optBoolean) {
                    NavigateProxyActivity.v(this.a, this.b);
                    this.f6780c.onSuccess(null);
                    return;
                }
                bundle.putBoolean("result", optBoolean);
                this.f6780c.onFailed(bundle);
                return;
            }
            bundle.putString("error", optString2);
            this.f6780c.onFailed(bundle);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes17.dex */
    public class b extends IMantoHttpListener {
        final /* synthetic */ MantoResultCallBack a;
        final /* synthetic */ String b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ Context f6781c;

        b(a aVar, MantoResultCallBack mantoResultCallBack, String str, Context context) {
            this.a = mantoResultCallBack;
            this.b = str;
            this.f6781c = context;
        }

        @Override // com.jingdong.manto.network.common.IMantoHttpListener
        public void onError(JSONObject jSONObject, Throwable th) {
            Bundle bundle = new Bundle(1);
            bundle.putString("error", th.getMessage());
            this.a.onFailed(bundle);
        }

        @Override // com.jingdong.manto.network.common.IMantoHttpListener
        public void onSuccess(JSONObject jSONObject) {
            try {
                String optString = jSONObject.optString("code", "");
                if (!TextUtils.equals(optString, "0")) {
                    Bundle bundle = new Bundle(1);
                    bundle.putString("code", optString);
                    bundle.putString("error", jSONObject.getString("error"));
                    this.a.onFailed(bundle);
                    return;
                }
                JSONObject jSONObject2 = jSONObject.getJSONObject("data");
                if (jSONObject2 == null) {
                    Bundle bundle2 = new Bundle(1);
                    bundle2.putString("error", "data is null");
                    this.a.onFailed(bundle2);
                    return;
                }
                String optString2 = jSONObject2.optString("customerUrl");
                if (TextUtils.isEmpty(optString2)) {
                    Bundle bundle3 = new Bundle(1);
                    bundle3.putString("error", "customerUrl is null");
                    this.a.onFailed(bundle3);
                    return;
                }
                StringBuffer stringBuffer = new StringBuffer(optString2);
                stringBuffer.append("?fromPin=");
                stringBuffer.append(URLEncoder.encode(jSONObject2.optString("fromPin")));
                stringBuffer.append("&fromPinNickname=");
                stringBuffer.append(URLEncoder.encode(jSONObject2.optString("fromPinNickname")));
                stringBuffer.append("&fromPinYunSmaImageUrl=");
                stringBuffer.append(URLEncoder.encode(jSONObject2.optString("fromPinYunSmaImageUrl")));
                stringBuffer.append("&toPin=");
                stringBuffer.append(URLEncoder.encode(jSONObject2.optString("toPin")));
                stringBuffer.append("&toPinNickname=");
                stringBuffer.append(URLEncoder.encode(jSONObject2.optString("toPinNickname")));
                stringBuffer.append("&toPinYunSmaImageUrl=");
                stringBuffer.append(URLEncoder.encode(jSONObject2.optString("toPinYunSmaImageUrl")));
                stringBuffer.append(this.b);
                String stringBuffer2 = stringBuffer.toString();
                Bundle bundle4 = new Bundle(1);
                bundle4.putString("url", stringBuffer2);
                NavigateProxyActivity.y(this.f6781c, bundle4);
                this.a.onSuccess(new Bundle());
            } catch (Throwable th) {
                Bundle bundle5 = new Bundle(1);
                bundle5.putString("error", th.getMessage());
                this.a.onFailed(bundle5);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes17.dex */
    public class c implements IRouter.CallBack {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ MantoResultCallBack f6782g;

        c(a aVar, MantoResultCallBack mantoResultCallBack) {
            this.f6782g = mantoResultCallBack;
        }

        @Override // com.jingdong.manto.sdk.api.IRouter.CallBack
        public void onFail(int i2) {
            Bundle bundle = new Bundle(1);
            bundle.putInt("error", i2);
            this.f6782g.onFailed(bundle);
        }

        @Override // com.jingdong.manto.sdk.api.IRouter.CallBack
        public void onSuccess(Bundle bundle) {
            this.f6782g.onSuccess(bundle);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes17.dex */
    public class d implements IRouter.CallBack {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ MantoResultCallBack f6783g;

        d(a aVar, MantoResultCallBack mantoResultCallBack) {
            this.f6783g = mantoResultCallBack;
        }

        @Override // com.jingdong.manto.sdk.api.IRouter.CallBack
        public void onFail(int i2) {
            Bundle bundle = new Bundle(1);
            bundle.putInt("error", i2);
            this.f6783g.onFailed(bundle);
        }

        @Override // com.jingdong.manto.sdk.api.IRouter.CallBack
        public void onSuccess(Bundle bundle) {
            this.f6783g.onSuccess(bundle);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes17.dex */
    public class e implements IRouter.CallBack {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ MantoResultCallBack f6784g;

        e(a aVar, MantoResultCallBack mantoResultCallBack) {
            this.f6784g = mantoResultCallBack;
        }

        @Override // com.jingdong.manto.sdk.api.IRouter.CallBack
        public void onFail(int i2) {
            Bundle bundle = new Bundle(1);
            bundle.putInt("error", i2);
            this.f6784g.onFailed(bundle);
        }

        @Override // com.jingdong.manto.sdk.api.IRouter.CallBack
        public void onSuccess(Bundle bundle) {
            this.f6784g.onSuccess(bundle);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes17.dex */
    public class f extends IMantoHttpListener {
        final /* synthetic */ Context a;
        final /* synthetic */ Bundle b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ MantoResultCallBack f6785c;

        f(a aVar, Context context, Bundle bundle, MantoResultCallBack mantoResultCallBack) {
            this.a = context;
            this.b = bundle;
            this.f6785c = mantoResultCallBack;
        }

        @Override // com.jingdong.manto.network.common.IMantoHttpListener
        public void onError(JSONObject jSONObject, Throwable th) {
            Bundle bundle = new Bundle(1);
            bundle.putString("error", th.getMessage());
            this.f6785c.onFailed(bundle);
        }

        @Override // com.jingdong.manto.network.common.IMantoHttpListener
        public void onSuccess(JSONObject jSONObject) {
            Bundle bundle = new Bundle(1);
            String optString = jSONObject.optString("code");
            String optString2 = jSONObject.optString("error");
            if (TextUtils.equals(optString, "0")) {
                NavigateProxyActivity.z(this.a, this.b);
                this.f6785c.onSuccess(bundle);
                return;
            }
            bundle.putString("error", optString2);
            this.f6785c.onFailed(bundle);
        }
    }

    private void a(Context context, Bundle bundle, MantoResultCallBack mantoResultCallBack) {
        String string = bundle.getString("appid");
        PkgDetailEntity k2 = com.jingdong.a.k(string, bundle.getString("type"));
        if (k2 == null) {
            Bundle bundle2 = new Bundle();
            bundle2.putString("errorMsg", "app pkg is null");
            mantoResultCallBack.onFailed(bundle2);
        } else if (!b(k2.permissions, 3)) {
            Bundle bundle3 = new Bundle();
            bundle3.putString("errorMsg", "has no permission");
            mantoResultCallBack.onFailed(bundle3);
        } else {
            String string2 = bundle.getString("wareString");
            if (TextUtils.isEmpty(string2)) {
                Bundle bundle4 = new Bundle();
                bundle4.putString("errorMsg", "ware info is empty");
                mantoResultCallBack.onFailed(bundle4);
                return;
            }
            String str = "router://JDCartModule/addCartUniformWithUrl?isNewMode=1&businessName=jdMiniApp&isCancelLoading=1&sourceType=mini&sourceValue=" + string + ContainerUtils.FIELD_DELIMITER + string2;
            Bundle bundle5 = new Bundle();
            bundle5.putString("url", str);
            RouterProxyActivity.v(context, bundle5, new d(this, mantoResultCallBack));
        }
    }

    private boolean b(int i2, int i3) {
        return ((i2 >>> i3) & 1) > 0;
    }

    private void c(Context context, Bundle bundle, MantoResultCallBack mantoResultCallBack) {
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
        PkgDetailEntity k2 = com.jingdong.a.k(bundle.getString("appid"), bundle.getString("type"));
        Bundle bundle2 = new Bundle(1);
        if (k2 == null) {
            bundle2.putString("error", "detailEntity is null");
            mantoResultCallBack.onFailed(bundle2);
        } else if (TextUtils.isEmpty(k2.venderId)) {
            bundle2.putString("error", "venderId is null");
            mantoResultCallBack.onFailed(bundle2);
        } else {
            bundle2.putString("venderId", k2.venderId);
            NavigateProxyActivity.u(context, bundle2);
            mantoResultCallBack.onSuccess(new Bundle());
        }
    }

    private void d(Context context, Bundle bundle, MantoResultCallBack mantoResultCallBack) {
        String string = bundle.getString("appid");
        PkgDetailEntity k2 = com.jingdong.a.k(string, bundle.getString("type"));
        if (k2 == null) {
            Bundle bundle2 = new Bundle();
            bundle2.putString("errorMsg", "app pkg is null");
            mantoResultCallBack.onFailed(bundle2);
        } else if (!b(k2.permissions, 4)) {
            Bundle bundle3 = new Bundle();
            bundle3.putString("errorMsg", "has no permission");
            mantoResultCallBack.onFailed(bundle3);
        } else {
            String str = "router://JDCartModule/show?&sourceType=mini&sourceValue=" + string;
            Bundle bundle4 = new Bundle();
            bundle4.putString("url", str);
            RouterProxyActivity.v(context, bundle4, new e(this, mantoResultCallBack));
        }
    }

    private void e(Context context, Bundle bundle, MantoResultCallBack mantoResultCallBack) {
        PkgDetailEntity k2 = com.jingdong.a.k(bundle.getString("appid"), bundle.getString("type"));
        if (k2 == null) {
            Bundle bundle2 = new Bundle();
            bundle2.putString("errorMsg", "app pkg is null");
            mantoResultCallBack.onFailed(bundle2);
        } else if (!b(k2.permissions, 1)) {
            Bundle bundle3 = new Bundle();
            bundle3.putString("errorMsg", "has no permission");
            mantoResultCallBack.onFailed(bundle3);
        } else {
            String string = bundle.getString("orderId");
            String string2 = bundle.getString("payablePrice");
            String string3 = bundle.getString("orderType");
            String string4 = bundle.getString("from");
            String string5 = bundle.getString("backUrl");
            try {
                new JSONObject().put("from", string4);
            } catch (Throwable unused) {
            }
            if (!TextUtils.isEmpty(string) && !TextUtils.isEmpty(string2) && !TextUtils.isEmpty(string3)) {
                String build = new JDRouterUrlBuilder().setModuleName(OrderISVActivityJumpUtil.ROUTER_MODULE_CASHIER).setMethodName("show").putStringParam("appId", "m_K7rKsh38W1").putStringParam(PairKey.APP_KEY, "40b97b3a799166ffd6c0c9816ebe6347").putStringParam("orderId", string).putStringParam("orderType", string3).putStringParam("payablePrice", string2).putStringParam("paySourceId", "10").putStringParam("back_url", string5).putStringParam("from", string4).build();
                Bundle bundle4 = new Bundle();
                bundle4.putString("url", build);
                RouterProxyActivity.v(context, bundle4, new c(this, mantoResultCallBack));
                return;
            }
            Bundle bundle5 = new Bundle(1);
            bundle5.putString("error", "param not valid, please check");
            mantoResultCallBack.onFailed(bundle5);
        }
    }

    private void f(Context context, Bundle bundle, MantoResultCallBack mantoResultCallBack) {
        MantoJDHttpHandler.commit(new com.jd.manto.navigate.e(bundle.getString("appid"), bundle.getString("wareId", "")), new C0202a(this, context, bundle, mantoResultCallBack));
    }

    private void g(Bundle bundle, MantoResultCallBack mantoResultCallBack) {
        ILogin iLogin = (ILogin) com.jingdong.a.n(ILogin.class);
        if (iLogin != null && iLogin.hasLogin()) {
            String string = bundle.getString("appid");
            String string2 = bundle.getString("type");
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("&sessionFrom=");
            stringBuffer.append(URLEncoder.encode(bundle.getString("sessionFrom")));
            stringBuffer.append("&sendMessageTitle=");
            stringBuffer.append(URLEncoder.encode(bundle.getString("sendMessageTitle")));
            stringBuffer.append("&sendMessagePath=");
            stringBuffer.append(URLEncoder.encode(bundle.getString("sendMessagePath")));
            stringBuffer.append("&sendMessageImg=");
            stringBuffer.append(URLEncoder.encode(bundle.getString("sendMessageImg")));
            stringBuffer.append("&showMessageCard=");
            stringBuffer.append(bundle.getBoolean("showMessageCard"));
            PkgDetailEntity k2 = com.jingdong.a.k(string, string2);
            String str = k2 != null ? k2.name : "";
            stringBuffer.append("&appId=");
            stringBuffer.append(URLEncoder.encode(string));
            stringBuffer.append("&mpName=");
            stringBuffer.append(URLEncoder.encode(str));
            Bundle bundle2 = new Bundle(1);
            bundle2.putString("url", stringBuffer.toString());
            bundle2.putString(IMantoBaseModule.REQUEST_JSAPI_KEY, "enterContactNative");
            mantoResultCallBack.onSuccess(bundle2);
            return;
        }
        Bundle bundle3 = new Bundle(1);
        bundle3.putString("error", "please do login first");
        mantoResultCallBack.onFailed(bundle3);
    }

    private void h(Context context, Bundle bundle, MantoResultCallBack mantoResultCallBack) {
        MantoJDHttpHandler.commit(new com.jd.manto.navigate.c(bundle.getString("appid")), new b(this, mantoResultCallBack, bundle.getString("url"), context));
    }

    private void i(Context context, Bundle bundle, MantoResultCallBack mantoResultCallBack) {
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
        bundle.getString("type");
        MantoJDHttpHandler.commit(new com.jd.manto.navigate.d(string, jSONObject.optJSONArray("skuInfos")), new f(this, context, new Bundle(1), mantoResultCallBack));
    }

    private void j(Context context, Bundle bundle, MantoResultCallBack mantoResultCallBack) {
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
        PkgDetailEntity k2 = com.jingdong.a.k(bundle.getString("appid"), bundle.getString("type"));
        Bundle bundle2 = new Bundle(1);
        if (k2 == null) {
            bundle2.putString("error", "detailEntity is null");
            mantoResultCallBack.onFailed(bundle2);
        } else if (TextUtils.isEmpty(k2.venderId)) {
            bundle2.putString("error", "venderId is null");
            mantoResultCallBack.onFailed(bundle2);
        } else {
            bundle2.putString("venderId", k2.venderId);
            bundle2.putString("entry", jSONObject.optString("entry"));
            if (!TextUtils.isEmpty(jSONObject.optString("sku"))) {
                bundle2.putString("sku", jSONObject.optString("sku"));
            }
            if (!TextUtils.isEmpty(jSONObject.optString("orderId"))) {
                bundle2.putString("orderId", jSONObject.optString("orderId"));
            }
            NavigateProxyActivity.w(context, bundle2);
            mantoResultCallBack.onSuccess(new Bundle());
        }
    }

    @Override // com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public String getModuleName() {
        return "jumpToNative";
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoModule, com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public void handleMethod(String str, MantoCore mantoCore, Bundle bundle, MantoResultCallBack mantoResultCallBack) {
        Activity activity = mantoCore != null ? mantoCore.getActivity() : null;
        if (TextUtils.equals(str, "jumpToCheckOut")) {
            String string = bundle.getString("appid");
            String string2 = bundle.getString("type");
            if (string != null && string2 != null) {
                PkgDetailEntity k2 = com.jingdong.a.k(string, string2);
                if (k2 == null) {
                    Bundle bundle2 = new Bundle();
                    bundle2.putString("errorMsg", "app pkg is null");
                    mantoResultCallBack.onFailed(bundle2);
                    return;
                } else if (!b(k2.permissions, 0)) {
                    Bundle bundle3 = new Bundle();
                    bundle3.putString("errorMsg", "has no permission");
                    mantoResultCallBack.onFailed(bundle3);
                    return;
                } else {
                    f(activity, bundle, mantoResultCallBack);
                    return;
                }
            }
            Bundle bundle4 = new Bundle();
            bundle4.putString("errorMsg", "appid or type is null");
            mantoResultCallBack.onFailed(bundle4);
        } else if (TextUtils.equals(str, "enterContact")) {
            g(bundle, mantoResultCallBack);
        } else if (TextUtils.equals(str, "enterContactNative")) {
            h(activity, bundle, mantoResultCallBack);
        } else if (TextUtils.equals(str, "miniPay")) {
            e(activity, bundle, mantoResultCallBack);
        } else if (TextUtils.equals(str, "addCart")) {
            a(activity, bundle, mantoResultCallBack);
        } else if (TextUtils.equals(str, "routerToNativeCart")) {
            d(activity, bundle, mantoResultCallBack);
        } else if (TextUtils.equals(str, "jumpToMultiCheckOut")) {
            i(activity, bundle, mantoResultCallBack);
        } else if (TextUtils.equals(str, "mpDongdongChat")) {
            j(activity, bundle, mantoResultCallBack);
        } else if (TextUtils.equals(str, "mpCartToCheckout")) {
            c(activity, bundle, mantoResultCallBack);
        }
    }

    @Override // com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public Bundle initData(String str, MantoCore mantoCore, JSONObject jSONObject) {
        JSONArray jSONArray;
        JSONArray jSONArray2;
        JSONObject optJSONObject;
        Bundle bundle = new Bundle();
        if (TextUtils.equals(str, "jumpToCheckOut")) {
            if (jSONObject == null || (optJSONObject = jSONObject.optJSONObject("params")) == null) {
                return bundle;
            }
            bundle.putString("wareId", optJSONObject.optString("wareId"));
            int i2 = 1;
            try {
                i2 = Integer.valueOf(optJSONObject.optString("wareNum", "1")).intValue();
            } catch (Throwable unused) {
            }
            bundle.putInt("wareNum", i2);
            if (optJSONObject.has(CartConstant.KEY_EXTFLAG)) {
                bundle.putString(CartConstant.KEY_EXTFLAG, optJSONObject.optString(CartConstant.KEY_EXTFLAG));
            }
            if (optJSONObject.has(JumpUtil.VALUE_DES_BUSINESS_MAP)) {
                bundle.putString(JumpUtil.VALUE_DES_BUSINESS_MAP, optJSONObject.optString(JumpUtil.VALUE_DES_BUSINESS_MAP));
            }
            if (optJSONObject.has("openAppSkuInfo")) {
                bundle.putString("openAppSkuInfo", optJSONObject.optString("openAppSkuInfo"));
            }
            return bundle;
        } else if (TextUtils.equals(str, "enterContact")) {
            if (jSONObject == null) {
                return bundle;
            }
            bundle.putString("sessionFrom", jSONObject.optString("sessionFrom", ""));
            bundle.putString("sendMessageTitle", jSONObject.optString("sendMessageTitle", ""));
            bundle.putString("sendMessagePath", jSONObject.optString("sendMessagePath", ""));
            bundle.putString("sendMessageImg", jSONObject.optString("sendMessageImg", ""));
            bundle.putBoolean("showMessageCard", jSONObject.optBoolean("showMessageCard", false));
            return bundle;
        } else if (TextUtils.equals(str, "enterContactNative")) {
            if (jSONObject == null) {
                return bundle;
            }
            bundle.putString("url", jSONObject.optString("url", ""));
            return bundle;
        } else if (TextUtils.equals(str, "miniPay")) {
            if (jSONObject == null) {
                return bundle;
            }
            bundle.putString("orderId", jSONObject.optString("orderId", ""));
            bundle.putString("payablePrice", jSONObject.optString("payablePrice", ""));
            bundle.putString("orderType", jSONObject.optString("orderType", ""));
            bundle.putString("from", jSONObject.optString("from", ""));
            bundle.putString("backUrl", jSONObject.optString("backUrl", ""));
            return bundle;
        } else if (!TextUtils.equals(str, "addCart")) {
            if (TextUtils.equals(str, "jumpToMultiCheckOut")) {
                bundle.putString("params", jSONObject.toString());
            } else if (TextUtils.equals(str, "mpDongdongChat")) {
                bundle.putString("params", jSONObject.toString());
            } else if (TextUtils.equals(str, "mpCartToCheckout")) {
                bundle.putString("params", jSONObject.toString());
            }
            return bundle;
        } else if (jSONObject == null) {
            return bundle;
        } else {
            try {
                StringBuffer stringBuffer = new StringBuffer();
                if (jSONObject.has("wareList") && (jSONArray2 = jSONObject.getJSONArray("wareList")) != null) {
                    stringBuffer.append("wareList=");
                    stringBuffer.append(jSONArray2.toString());
                }
                if (jSONObject.has("packList") && (jSONArray = jSONObject.getJSONArray("packList")) != null) {
                    if (stringBuffer.length() > 0) {
                        stringBuffer.append(ContainerUtils.FIELD_DELIMITER);
                    }
                    stringBuffer.append("packList=");
                    stringBuffer.append(jSONArray.toString());
                }
                bundle.putString("wareString", stringBuffer.toString());
            } catch (Throwable unused2) {
            }
            return bundle;
        }
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoModule
    protected void injectJsApiMethod(List<JsApiMethod> list) {
        list.add(new JsApiMethod("jumpToCheckOut", 1));
        list.add(new JsApiMethod("jumpToMultiCheckOut", 1));
        list.add(new JsApiMethod("enterContact", 0));
        list.add(new JsApiMethod("enterContactNative", 1));
        list.add(new JsApiMethod("miniPay", 1));
        list.add(new JsApiMethod("addCart", 1));
        list.add(new JsApiMethod("routerToNativeCart", 1));
        list.add(new JsApiMethod("mpDongdongChat", 1));
        list.add(new JsApiMethod("mpCartToCheckout", 1));
    }
}
