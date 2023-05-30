package com.jd.manto.navigate;

import android.content.Intent;
import android.os.Bundle;
import com.jd.cashier.app.jdlibcutter.protocol.pay.jdpay.JDPayApiKey;
import com.jingdong.common.deeplinkhelper.DeeplinkJDpaySdkHelper;
import com.jingdong.common.utils.UserUtil;
import com.jingdong.common.utils.pay.JumpUtils;
import com.jingdong.manto.MantoCore;
import com.jingdong.manto.jsapi.openmodule.AbstractMantoModule;
import com.jingdong.manto.jsapi.openmodule.JsApiMethod;
import com.jingdong.manto.jsapi.openmodule.MantoResultCallBack;
import com.jingdong.sdk.platform.business.personal.R2;
import java.util.List;
import org.json.JSONObject;

/* loaded from: classes17.dex */
public class b extends AbstractMantoModule {
    @Override // com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public String getModuleName() {
        return "jumpToPaySetting";
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
        String optString = jSONObject.optString("transInfo", "");
        Bundle bundle2 = new Bundle();
        bundle2.putString("JDPAY_ENTRANCE_VERIFY", "JDPAY_PAY_SETTING");
        bundle2.putString(JDPayApiKey.JDPAY_SESSION_KEY, UserUtil.getWJLoginHelper().getA2());
        bundle2.putString(JumpUtils.JDPAY_CODE_SOURCE, "0");
        bundle2.putString(JumpUtils.ACCOUNT_MODE, "Native");
        bundle2.putString("TRANS_INFO", optString);
        bundle2.putBoolean("JDPAY_TOAST_PRINT", true);
        DeeplinkJDpaySdkHelper.startJDPayActivityForResult(mantoCore.getActivity(), bundle2, R2.id.cart_clean_follow_tv);
        mantoResultCallBack.onSuccess(new Bundle());
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoModule, com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public Bundle handleResult(String str, MantoCore mantoCore, Intent intent, int i2, int i3) {
        Bundle bundle = new Bundle();
        if (i3 == 12666) {
            String stringExtra = intent != null ? intent.getStringExtra("jdpay_Result") : "";
            bundle.putString("result", stringExtra);
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("result", new JSONObject(stringExtra));
            } catch (Exception unused) {
            }
            mantoCore.dispatchEevent("onPaySettingBack", jSONObject, 0);
        }
        return bundle;
    }

    @Override // com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public Bundle initData(String str, MantoCore mantoCore, JSONObject jSONObject) {
        Bundle bundle = new Bundle();
        bundle.putString("params", jSONObject.toString());
        bundle.putInt("requestCode", R2.id.cart_clean_follow_tv);
        return bundle;
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoModule
    protected void injectJsApiMethod(List<JsApiMethod> list) {
        list.add(new JsApiMethod("jumpToPaySetting", 2));
    }
}
