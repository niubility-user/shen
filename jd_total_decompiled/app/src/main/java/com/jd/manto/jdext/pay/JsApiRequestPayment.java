package com.jd.manto.jdext.pay;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.jd.cashier.app.jdlibcutter.protocol.pay.jdpay.JDPayApiKey;
import com.jd.dynamic.DYConstants;
import com.jingdong.manto.MantoCore;
import com.jingdong.manto.jsapi.openmodule.AbstractMantoModule;
import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import com.jingdong.manto.jsapi.openmodule.JsApiMethod;
import com.jingdong.manto.jsapi.openmodule.MantoResultCallBack;
import com.jingdong.manto.network.common.IMantoHttpListener;
import com.jingdong.manto.network.common.MantoJDHttpHandler;
import com.jingdong.manto.sdk.api.IRequestPayment;
import com.jingdong.manto.utils.MantoLog;
import com.jingdong.manto.utils.MantoProcessUtil;
import com.jingdong.manto.utils.MantoTrack;
import com.jingdong.sdk.platform.business.personal.R2;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes17.dex */
public class JsApiRequestPayment extends AbstractMantoModule {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes17.dex */
    public class a extends IMantoHttpListener {
        final /* synthetic */ Bundle a;
        final /* synthetic */ IRequestPayment b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ Activity f6733c;

        a(JsApiRequestPayment jsApiRequestPayment, Bundle bundle, IRequestPayment iRequestPayment, Activity activity) {
            this.a = bundle;
            this.b = iRequestPayment;
            this.f6733c = activity;
        }

        @Override // com.jingdong.manto.network.common.IMantoHttpListener
        public void onError(JSONObject jSONObject, Throwable th) {
            this.a.putString(IRequestPayment.OUT_merchant, "111229368005");
            this.b.requestPaymentOut(this.f6733c, MantoProcessUtil.getProcessName(), this.a, R2.attr.color);
        }

        @Override // com.jingdong.manto.network.common.IMantoHttpListener
        public void onSuccess(JSONObject jSONObject) {
            JSONObject optJSONObject;
            String optString = jSONObject.optString("code");
            this.a.putString(IRequestPayment.OUT_merchant, (TextUtils.isEmpty(optString) || !optString.equals("0") || (optJSONObject = jSONObject.optJSONObject("data")) == null) ? "111229368005" : optJSONObject.optString("merId"));
            this.b.requestPaymentOut(this.f6733c, MantoProcessUtil.getProcessName(), this.a, R2.attr.color);
        }
    }

    private void a(Activity activity, IRequestPayment iRequestPayment, Bundle bundle) {
        if (iRequestPayment == null) {
            return;
        }
        String string = bundle.getString("appid");
        String string2 = bundle.getString("type");
        MantoJDHttpHandler.commit(new com.jd.manto.jdext.pay.a(string), new a(this, bundle, iRequestPayment, activity));
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("vapp_type", string2);
        } catch (JSONException e2) {
            MantoLog.e(DYConstants.DY_TRACK, e2);
        }
        MantoTrack.sendCommonDataWithExt(activity, "\u6253\u5f00\u6536\u94f6\u53f0", "mp_counter_open", string, "requestMPPayment", "", jSONObject.toString(), "", null);
    }

    @Override // com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public String getModuleName() {
        return "payment";
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoModule, com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public void handleMethod(String str, MantoCore mantoCore, Bundle bundle, MantoResultCallBack mantoResultCallBack) {
        IRequestPayment iRequestPayment = (IRequestPayment) com.jingdong.a.n(IRequestPayment.class);
        Bundle bundle2 = new Bundle(1);
        if (iRequestPayment == null) {
            bundle2.putString("message", "IRequestPayment not impl.");
            mantoResultCallBack.onFailed(bundle2);
            return;
        }
        Activity activity = mantoCore != null ? mantoCore.getActivity() : null;
        if ("requestPaymentInner".equals(str)) {
            iRequestPayment.requestPaymentIn(activity, MantoProcessUtil.getProcessName(), bundle, R2.attr.color);
        } else if ("requestPayment".equals(str)) {
            iRequestPayment.requestPaymentOut(activity, MantoProcessUtil.getProcessName(), bundle, R2.attr.color);
        } else if ("requestVerify".equals(str)) {
            iRequestPayment.requestVerify(activity, MantoProcessUtil.getProcessName(), bundle, R2.attr.color);
        } else if ("requestMPPayment".equals(str)) {
            a(activity, iRequestPayment, bundle);
        }
        MantoLog.d("payImpl", String.format("%s called", str));
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoModule, com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public Bundle handleResult(String str, MantoCore mantoCore, Intent intent, int i2, int i3) {
        boolean z;
        Bundle bundle = new Bundle(2);
        if (i3 == 642) {
            IRequestPayment iRequestPayment = (IRequestPayment) com.jingdong.a.n(IRequestPayment.class);
            Bundle onHandleResult = iRequestPayment != null ? iRequestPayment.onHandleResult(i3, i2, intent) : null;
            boolean z2 = true;
            if (onHandleResult != null) {
                bundle.putString("data", onHandleResult.getString("result", ""));
            } else {
                if (i2 == 1024 || i2 == 1025) {
                    String stringExtra = intent.getStringExtra("jdpay_Result");
                    if (intent == null || !intent.hasExtra("jdpay_Result")) {
                        bundle.putString("data", String.valueOf(i2));
                        z = false;
                    } else {
                        bundle.putString("data", String.valueOf(stringExtra));
                        z = true;
                    }
                    if (i2 != 1025 || TextUtils.isEmpty(stringExtra)) {
                        z2 = z;
                    } else {
                        try {
                            JSONObject jSONObject = new JSONObject(stringExtra);
                            JSONObject jSONObject2 = new JSONObject();
                            JSONObject optJSONObject = jSONObject.optJSONObject("verifyResult");
                            if (optJSONObject != null) {
                                jSONObject2.put("tdVerifyData", optJSONObject.optString("tdVerifyData"));
                                jSONObject2.put("verifyType", optJSONObject.optString("verifyType"));
                            }
                            jSONObject2.put("accountStatus", jSONObject.optString(JDPayApiKey.JD_PAY_STATUS));
                            bundle.putString("data", jSONObject2.toString());
                        } catch (JSONException unused) {
                            bundle.putString("message", stringExtra);
                            bundle.putString("error", "parseJson error");
                        } catch (Exception unused2) {
                        }
                    }
                }
                z2 = false;
            }
            bundle.putString(IMantoBaseModule.ERROR_CODE, z2 ? "1" : "0");
            return bundle;
        }
        return super.handleResult(str, mantoCore, intent, i2, i3);
    }

    @Override // com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public Bundle initData(String str, MantoCore mantoCore, JSONObject jSONObject) {
        String optString;
        Bundle bundle = new Bundle(3);
        if ("requestPaymentInner".equals(str)) {
            String optString2 = jSONObject.optString("appId");
            String optString3 = jSONObject.optString("payParam");
            bundle.putString("appId", optString2);
            bundle.putString("payParam", optString3);
        } else {
            if ("requestPayment".equals(str)) {
                String optString4 = jSONObject.optString(IRequestPayment.OUT_merchant);
                String optString5 = jSONObject.optString("package");
                optString = jSONObject.optString("paySign");
                bundle.putString(IRequestPayment.OUT_merchant, optString4);
                bundle.putString("package", optString5);
            } else if ("requestVerify".equals(str)) {
                String optString6 = jSONObject.optString("mode", "Native");
                String optString7 = jSONObject.optString(IRequestPayment.V_PAY_TYPE, "4");
                String optString8 = jSONObject.optString("extraInfo", "");
                bundle.putString("mode", optString6);
                bundle.putString(IRequestPayment.V_PAY_TYPE, optString7);
                bundle.putString("extraInfo", optString8);
            } else if ("requestMPPayment".equals(str)) {
                String optString9 = jSONObject.optString("orderId");
                optString = jSONObject.optString("paySign");
                bundle.putString("package", optString9);
            }
            bundle.putString("paySign", optString);
        }
        bundle.putInt("requestCode", R2.attr.color);
        return bundle;
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoModule
    protected void injectJsApiMethod(List<JsApiMethod> list) {
        list.add(new JsApiMethod("requestPaymentInner", 57, 2));
        list.add(new JsApiMethod("requestPayment", 59, 2));
        list.add(new JsApiMethod("requestVerify", 224, 2));
        list.add(new JsApiMethod("requestMPPayment", 2));
    }
}
