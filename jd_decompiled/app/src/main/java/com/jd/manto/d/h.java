package com.jd.manto.d;

import android.os.Bundle;
import android.text.TextUtils;
import com.jd.sec.LogoManager;
import com.jdjr.risk.biometric.core.BiometricManager;
import com.jdjr.risk.util.httputil.LorasHttpCallback;
import com.jingdong.common.login.LoginUserBase;
import com.jingdong.manto.MantoCore;
import com.jingdong.manto.jsapi.openmodule.AbstractMantoModule;
import com.jingdong.manto.jsapi.openmodule.JsApiMethod;
import com.jingdong.manto.jsapi.openmodule.MantoResultCallBack;
import java.util.List;
import org.json.JSONObject;

/* loaded from: classes17.dex */
public class h extends AbstractMantoModule {

    /* loaded from: classes17.dex */
    class a implements LorasHttpCallback {
        final /* synthetic */ MantoResultCallBack a;

        a(h hVar, MantoResultCallBack mantoResultCallBack) {
            this.a = mantoResultCallBack;
        }

        @Override // com.jdjr.risk.util.httputil.LorasHttpCallback
        public void onFailInCurentThread(int i2, String str) {
            Bundle bundle = new Bundle(1);
            bundle.putInt("errorCode", i2);
            bundle.putString("errorMsg", str);
            this.a.onFailed(bundle);
        }

        @Override // com.jdjr.risk.util.httputil.LorasHttpCallback
        public void onFailInNetThread(int i2, String str) {
            Bundle bundle = new Bundle(1);
            bundle.putInt("errorCode", i2);
            bundle.putString("errorMsg", str);
            this.a.onFailed(bundle);
        }

        @Override // com.jdjr.risk.util.httputil.LorasHttpCallback
        public void onSuccess(String str) {
            Bundle bundle = new Bundle(1);
            bundle.putString("token", str);
            this.a.onSuccess(bundle);
        }
    }

    @Override // com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public String getModuleName() {
        return "eid";
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoModule, com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public void handleMethod(String str, MantoCore mantoCore, Bundle bundle, MantoResultCallBack mantoResultCallBack) {
        JSONObject jSONObject;
        if (TextUtils.equals(str, "getEid")) {
            String logo = LogoManager.getInstance(com.jingdong.a.g()).getLogo();
            Bundle bundle2 = new Bundle();
            bundle2.putString("eid", logo);
            mantoResultCallBack.onSuccess(bundle2);
        } else if (TextUtils.equals(str, "getJDJRRiskToken")) {
            try {
                jSONObject = new JSONObject(bundle.getString("params"));
            } catch (Exception unused) {
                jSONObject = null;
            }
            if (jSONObject == null) {
                mantoResultCallBack.onFailed(null);
                return;
            }
            String optString = jSONObject.optString("bizId");
            String userPin = LoginUserBase.getUserPin();
            if (TextUtils.isEmpty(optString)) {
                Bundle bundle3 = new Bundle(1);
                bundle3.putString("errorMsg", "bizId is null");
                mantoResultCallBack.onFailed(bundle3);
            } else if (TextUtils.isEmpty(userPin)) {
                Bundle bundle4 = new Bundle(1);
                bundle4.putString("errorMsg", "user not login");
                mantoResultCallBack.onFailed(bundle4);
            } else {
                BiometricManager.getInstance().getToken(com.jingdong.a.g(), optString, userPin, new a(this, mantoResultCallBack));
            }
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
        list.add(new JsApiMethod("getEid", 0));
        list.add(new JsApiMethod("getJDJRRiskToken", 0));
    }
}
